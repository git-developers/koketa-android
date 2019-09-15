package com.tianos.koketa.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tianos.koketa.R;
import com.tianos.koketa.database.CategoryDb;
import com.tianos.koketa.database.ProductDb;
import com.tianos.koketa.database.ProfileDb;
import com.tianos.koketa.database.UserDb;
import com.tianos.koketa.entity.Category;
import com.tianos.koketa.entity.Product;
import com.tianos.koketa.entity.Profile;
import com.tianos.koketa.entity.ResponseWeb;
import com.tianos.koketa.entity.User;
import com.tianos.koketa.retrofit.APIClient;
import com.tianos.koketa.retrofit.APIInterface;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa2;
import com.tianos.koketa.util.PreferencesManager;
import com.tianos.koketa.util.Util;

import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SynchronizeActivity extends BaseActivity implements InterfaceKoketa2 {

    private static final String TAG = SynchronizeActivity.class.getName();

    @BindView(R.id.btn_synchronize)
    Button btnSynchronize;

    @BindView(R.id.pb_1)
    ProgressBar pb1;

    @BindView(R.id.pb_2)
    ProgressBar pb2;

    @BindView(R.id.pb_3)
    ProgressBar pb3;

    @BindView(R.id.tv_status_1)
    TextView tvStatus1;

    @BindView(R.id.tv_status_2)
    TextView tvStatus2;

    @BindView(R.id.tv_status_3)
    TextView tvStatus3;

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
        tvStatus1.setText(getString(R.string.in_progress));

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
                    tvStatus1.setText(getString(R.string.done) + " " + responseWeb.getClients().size() + " Items.");
                    apiCategory(user);

                } catch (Exception e) {
                    Log.d(TAG, "apiClients::: " + e.getMessage());

                    tvStatus1.setText(getString(R.string.error));
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
                tvStatus1.setText(getString(R.string.error));
                Util.showSnackbar(SynchronizeActivity.this, getString(R.string.error));
            }
        });
    }

    public void apiCategory(User user) {

        pb2.setVisibility(View.VISIBLE);
        tvStatus2.setText(getString(R.string.in_progress));

        apiInterface.category(user).enqueue(new Callback<ResponseWeb>() {
            @Override
            public void onResponse(Call<ResponseWeb> call, Response<ResponseWeb> response) {

                try {

                    ResponseWeb responseWeb = response.body();
                    btnSynchronize.setEnabled(true);
                    pb2.setVisibility(View.GONE);

                    if (response.code() != 200) {
                        throw new Exception(getString(R.string.error));
                    }

                    if (responseWeb.getStatus() != ResponseWeb.STATUS_SUCCESS) {
                        throw new Exception(responseWeb.getMessage());
                    }

                    CategoryDb categoryDb = new CategoryDb(SynchronizeActivity.this);
                    categoryDb.delete();

                    /**
                     * DATABASE SAVE
                     */
                    Random r = new Random();
                    int low = 10;
                    int high = 100;

                    for (Category category : responseWeb.getCategory()) {

                        int stock = r.nextInt(high-low) + low;

                        Category xx = category.getParent();
                        xx.setStock(stock);
                        categoryDb.insert(xx);
                    }


                    /**
                     * REDIRECT
                     */
                    tvStatus2.setText(getString(R.string.done) + " " + responseWeb.getCategory().size() + " Items.");
                    apiProduct(user);

                } catch (Exception e) {
                    Log.d(TAG, "apiCategory::: " + e.getMessage());
                    pb2.setVisibility(View.GONE);
                    tvStatus2.setText(getString(R.string.error));
                    Util.showSnackbar(SynchronizeActivity.this, e.getMessage());
                    return;
                }
            }

            @Override
            public void onFailure(Call<ResponseWeb> call, Throwable t) {
                call.cancel();
                btnSynchronize.setEnabled(true);
                pb2.setVisibility(View.GONE);
                tvStatus2.setText(getString(R.string.error));
                Util.showSnackbar(SynchronizeActivity.this, getString(R.string.error));
            }
        });
    }

    public void apiProduct(User user) {

        pb3.setVisibility(View.VISIBLE);
        tvStatus3.setText(getString(R.string.in_progress));

        apiInterface.product(user).enqueue(new Callback<ResponseWeb>() {
            @Override
            public void onResponse(Call<ResponseWeb> call, Response<ResponseWeb> response) {

                try {

                    ResponseWeb responseWeb = response.body();
                    btnSynchronize.setEnabled(true);
                    pb3.setVisibility(View.GONE);

                    if (response.code() != 200) {
                        throw new Exception(getString(R.string.error));
                    }

                    if (responseWeb.getStatus() != ResponseWeb.STATUS_SUCCESS) {
                        throw new Exception(responseWeb.getMessage());
                    }

                    ProductDb productDb = new ProductDb(SynchronizeActivity.this);
                    productDb.delete();

                    /**
                     * DATABASE SAVE
                     */
                    Random r = new Random();
                    int low = 10;
                    int high = 100;

                    for (Product product : responseWeb.getProduct()) {
                        int stock = r.nextInt(high-low) + low;
                        product.setStock(stock);
                        product.setFamily("Koketa");
                        productDb.insert(product);
                    }

                    tvStatus3.setText(getString(R.string.done) + " " + responseWeb.getProduct().size() + " Items.");

                    /**
                     * REDIRECT
                     */
                    Intent i = new Intent(SynchronizeActivity.this, DashboardActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();

                } catch (Exception e) {
                    Log.d(TAG, "apiProduct::: " + e.getMessage());
                    pb3.setVisibility(View.GONE);
                    tvStatus3.setText(getString(R.string.error));
                    Util.showSnackbar(SynchronizeActivity.this, e.getMessage());
                    return;
                }
            }

            @Override
            public void onFailure(Call<ResponseWeb> call, Throwable t) {
                call.cancel();
                btnSynchronize.setEnabled(true);
                pb3.setVisibility(View.GONE);
                tvStatus3.setText(getString(R.string.error));
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
