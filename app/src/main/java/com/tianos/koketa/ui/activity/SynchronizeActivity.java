package com.tianos.koketa.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.tianos.koketa.R;
import com.tianos.koketa.database.UserDb;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synchronize);

        initSetup();
        initToolBar();
        initData();
        navigationDrawer();
    }

    @Override
    public void initSetup() {
        super.initSetup();

    }

    @Override
    public void initToolBar() {
        super.initToolBar();

        toolbar.setTitle(R.string.synchronize);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initData() {


    }

    @OnClick(R.id.btn_synchronize)
    public void btnSynchronize() {


        User user = new User("xxxx");

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        apiInterface.logIn(user).enqueue(new Callback<ResponseWeb>() {
            @Override
            public void onResponse(Call<ResponseWeb> call, Response<ResponseWeb> response) {

                try {

                    ResponseWeb responseWeb = response.body();

//                    Util.progressDialogHide();
                    btnSynchronize.setEnabled(true);

                    if (response.code() != 200) {
                        throw new Exception(getString(R.string.error));
                    }

                    if (responseWeb.getStatus() != ResponseWeb.STATUS_SUCCESS) {
                        throw new Exception(responseWeb.getMessage());
                    }

                    /**
                     * USER SAVE
                     */
                    UserDb userDb = new UserDb(SynchronizeActivity.this);
                    userDb.insert(responseWeb.getUser());


                } catch (Exception e) {
                    Util.showSnackbar(SynchronizeActivity.this, e.getMessage());
                    return;
                }
            }

            @Override
            public void onFailure(Call<ResponseWeb> call, Throwable t) {
                call.cancel();
                btnSynchronize.setEnabled(true);
//                Util.progressDialogHide();
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
