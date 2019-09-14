package com.tianos.koketa.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.tianos.koketa.R;
import com.tianos.koketa.database.BreadcrumbDb;
import com.tianos.koketa.database.ProfileDb;
import com.tianos.koketa.database.UserDb;
import com.tianos.koketa.entity.Breadcrumb;
import com.tianos.koketa.entity.Profile;
import com.tianos.koketa.entity.ResponseWeb;
import com.tianos.koketa.entity.User;
import com.tianos.koketa.retrofit.APIClient;
import com.tianos.koketa.retrofit.APIInterface;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa;
import com.tianos.koketa.util.Constant;
import com.tianos.koketa.util.PreferencesManager;
import com.tianos.koketa.util.Util;
import com.tianos.koketa.util.dialog.DialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements InterfaceKoketa {

    private static final String TAG = LoginActivity.class.getName();

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.edt_username_login)
    EditText edtUsernameLogin;

    @BindView(R.id.edt_password_login)
    EditText edtPasswordLogin;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initSetup();
        initToolBar();
        logOut();
    }

    @Override
    public void initSetup() {
        ButterKnife.bind(this);

        username = PreferencesManager.getInstance(this).getUsername();
        edtUsernameLogin.setText(username);
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @OnClick(R.id.btn_login)
    public void btnLogin() {

        int permission = ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_PHONE_STATE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            DialogFragment.dialogPermission(LoginActivity.this);
            return;
        }

        login();
    }

    @OnClick(R.id.ib_imei)
    public void btnImei() {
        DialogFragment.dialogImei(LoginActivity.this);
    }

    @OnClick(R.id.ib_license)
    public void btnLicense() {
        DialogFragment.dialogLicense(LoginActivity.this);
    }

    public void login() {

        if (Util.isEmpty(edtUsernameLogin)) {
            edtUsernameLogin.setError(getString(R.string.empty_code));
            return;
        }

        if (Util.isEmpty(edtPasswordLogin)) {
            edtPasswordLogin.setError(getString(R.string.empty_password));
            return;
        }

        btnLogin.setEnabled(false);
        Util.progressDialogShow(LoginActivity.this, getString(R.string.validating));

        User user = new User(Util.getEtString(edtUsernameLogin), Util.getEtString(edtPasswordLogin));

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        apiInterface.logIn(user).enqueue(new Callback<ResponseWeb>() {
            @Override
            public void onResponse(Call<ResponseWeb> call, Response<ResponseWeb> response) {

                try {

                    ResponseWeb responseWeb = response.body();

                    Util.progressDialogHide();
                    btnLogin.setEnabled(true);

                    if (response.code() != 200) {
                        throw new Exception(getString(R.string.error));
                    }

                    if (responseWeb.getStatus() != ResponseWeb.STATUS_SUCCESS) {
                        throw new Exception(responseWeb.getMessage());
                    }

                    /**
                     * DATABASE INSERT
                     */
                    UserDb userDb = new UserDb(LoginActivity.this);
                    userDb.insert(responseWeb.getUser());

                    BreadcrumbDb breadcrumbDb = new BreadcrumbDb(LoginActivity.this);
                    breadcrumbDb.insert(new Breadcrumb(responseWeb.getUser().getUsername()));

                    ProfileDb profileDb = new ProfileDb(LoginActivity.this);
                    profileDb.insert(
                        new Profile(
                            responseWeb.getUser().getProfile().getId(),
                            responseWeb.getUser().getProfile().getSlug(),
                            responseWeb.getUser().getUsername()
                    ));

                    /**
                     * PREFERENCES
                     */
                    PreferencesManager.getInstance(LoginActivity.this).setLogged();
                    PreferencesManager.getInstance(LoginActivity.this).setUsername(responseWeb.getUser());
                    PreferencesManager.getInstance(LoginActivity.this).realmSetUser(responseWeb.getUser());


                    /**
                     * REDIRECT
                     */
//                    Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
                    Intent i = new Intent(LoginActivity.this, SynchronizeActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();

                } catch (Exception e) {
                    Util.showSnackbar(LoginActivity.this, e.getMessage());
                    Log.d(TAG, "EXCEPTION::: " + e.getMessage());
                    return;
                }
            }

            @Override
            public void onFailure(Call<ResponseWeb> call, Throwable t) {
                call.cancel();
                btnLogin.setEnabled(true);
                Util.progressDialogHide();
                Util.showSnackbar(LoginActivity.this, getString(R.string.error));
            }
        });
    }

    public void logOut() {
        PreferencesManager.getInstance(LoginActivity.this).logOut();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if ((grantResults.length < 0) || (grantResults[0] != PackageManager.PERMISSION_GRANTED)) {
            return;
        }


        switch (requestCode) {
            case Constant.REQUEST_READ_PHONE_STATE:
                Util.showDialog(
                    LoginActivity.this,
                    getString(R.string.permission),
                    getString(R.string.accept),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
                            return;
                        }
                    });
            break;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_out_right);
    }
}
