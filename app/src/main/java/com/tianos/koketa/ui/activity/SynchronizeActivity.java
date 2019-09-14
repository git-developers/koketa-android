package com.tianos.koketa.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.tianos.koketa.R;
import com.tianos.koketa.database.CategoryDb;
import com.tianos.koketa.database.ProfileDb;
import com.tianos.koketa.database.UserDb;
import com.tianos.koketa.entity.Category;
import com.tianos.koketa.entity.Profile;
import com.tianos.koketa.entity.ResponseWeb;
import com.tianos.koketa.entity.User;
import com.tianos.koketa.retrofit.APIClient;
import com.tianos.koketa.retrofit.APIInterface;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa2;
import com.tianos.koketa.util.PreferencesManager;
import com.tianos.koketa.util.Util;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SynchronizeActivity extends BaseActivity implements InterfaceKoketa2 {

    @BindView(R.id.btn_synchronize)
    Button btnSynchronize;

    @BindView(R.id.pb_1)
    ProgressBar pb1;

    @BindView(R.id.pb_2)
    ProgressBar pb2;

    @BindView(R.id.pb_3)
    ProgressBar pb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synchronize);

        initSetup();
        initToolBar();
        initData();
    }

    @Override
    public void initSetup() {
        super.initSetup();

    }

    @Override
    public void initToolBar() {
        super.initToolBar();

        toolbar.setTitle(R.string.synchronize);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onBackPressed();

                Intent i = new Intent(SynchronizeActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void initData() {


    }

    @OnClick(R.id.btn_synchronize)
    public void btnSynchronize() {

        String username = PreferencesManager.getInstance(SynchronizeActivity.this).getUsername();
        User user = new User(username);

        btnSynchronize.setEnabled(false);

        apiClients(user);
    }

    public void apiClients(User user) {
        pb1.setVisibility(View.VISIBLE);

        apiInterface.clients(user).enqueue(new Callback<ResponseWeb>() {
            @Override
            public void onResponse(Call<ResponseWeb> call, Response<ResponseWeb> response) {

                try {

                    ResponseWeb responseWeb = response.body();
                    btnSynchronize.setEnabled(true);
                    pb1.setVisibility(View.GONE);

                    if (response.code() != 200) {
                        throw new Exception(getString(R.string.error));
                    }

                    if (responseWeb.getStatus() != ResponseWeb.STATUS_SUCCESS) {
                        throw new Exception(responseWeb.getMessage());
                    }

                    UserDb userDb = new UserDb(SynchronizeActivity.this);
                    userDb.deleteClients();

                    ProfileDb profileDb = new ProfileDb(SynchronizeActivity.this);

                    /**
                     * DATABASE SAVE
                     */
                    for (User user : responseWeb.getClients()) {
                        userDb.insert(user);
                        profileDb.insertIfNotExist(user.getProfile());
                    }


                    /**
                     * REDIRECT
                     */
                    apiCategory(user);

                } catch (Exception e) {
                    pb1.setVisibility(View.GONE);
                    Util.showSnackbar(SynchronizeActivity.this, e.getMessage());
                    return;
                }
            }

            @Override
            public void onFailure(Call<ResponseWeb> call, Throwable t) {
                call.cancel();
                btnSynchronize.setEnabled(true);
                pb1.setVisibility(View.GONE);
                Util.showSnackbar(SynchronizeActivity.this, getString(R.string.error));
            }
        });
    }

    public void apiCategory(User user) {
        pb1.setVisibility(View.VISIBLE);

        apiInterface.category(user).enqueue(new Callback<ResponseWeb>() {
            @Override
            public void onResponse(Call<ResponseWeb> call, Response<ResponseWeb> response) {

                try {

                    ResponseWeb responseWeb = response.body();
                    btnSynchronize.setEnabled(true);
                    pb1.setVisibility(View.GONE);

                    if (response.code() != 200) {
                        throw new Exception(getString(R.string.error));
                    }

                    if (responseWeb.getStatus() != ResponseWeb.STATUS_SUCCESS) {
                        throw new Exception(responseWeb.getMessage());
                    }

                    CategoryDb categoryDb = new CategoryDb(SynchronizeActivity.this);

                    /**
                     * DATABASE SAVE
                     */
                    for (Category category : responseWeb.getCategory()) {
                        categoryDb.insert(category.getParent());
                    }


                    /**
                     * REDIRECT
                     */
                    Intent i = new Intent(SynchronizeActivity.this, DashboardActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();

                } catch (Exception e) {
                    pb1.setVisibility(View.GONE);
                    Util.showSnackbar(SynchronizeActivity.this, e.getMessage());
                    return;
                }
            }

            @Override
            public void onFailure(Call<ResponseWeb> call, Throwable t) {
                call.cancel();
                btnSynchronize.setEnabled(true);
                pb1.setVisibility(View.GONE);
                Util.showSnackbar(SynchronizeActivity.this, getString(R.string.error));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        finish();
    }

}
