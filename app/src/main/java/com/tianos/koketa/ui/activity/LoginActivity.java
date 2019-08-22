package com.tianos.koketa.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.NonNull;

import com.tianos.koketa.R;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa;
import com.tianos.koketa.util.PreferencesManager;
import com.tianos.koketa.util.Util;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity implements InterfaceKoketa {

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.edt_codigo_login)
    EditText edtCodigoLogin;

    @BindView(R.id.edt_pass_login)
    EditText edtPassLogin;

    private String userCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initSetup();
        initToolBar();
    }

    @Override
    public void initSetup() {
        ButterKnife.bind(this);
//        userCode = PreferencesManager.getInstance(this).getUserCode();
        edtCodigoLogin.setText(userCode);
    }

    @Override
    public void initToolBar() {

        /*
        toolbar.setTitle(R.string.login);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            }
        );
        */
    }

    @OnClick(R.id.btn_login)
    public void btnLogin() {

        /*
        int permission = ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_PHONE_STATE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Util.showDialog(
                LoginActivity.this,
                getString(R.string.permission),
                getString(R.string.close),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
                        return;
                    }
                });
            return;
        }
        */

        login();
    }

    @OnClick(R.id.txt_olvide_contra_login)
    public void forgotPass(){
//        Intent i = new Intent(this, PasswordForgotActivity.class);
//        startActivity(i);
    }

    public void login() {

        if (edtCodigoLogin.getText().toString().isEmpty()) {
            edtCodigoLogin.setError(getString(R.string.empty_code));
            return;
        }

        if (edtPassLogin.getText().toString().isEmpty()) {
            edtPassLogin.setError(getString(R.string.empty_password));
            return;
        }

        btnLogin.setEnabled(false);
        Util.progressDialogShow(LoginActivity.this, getString(R.string.validating));

        JSONObject json = new JSONObject();


        /**
         * PREFERENCES SET LOGGED
         */
        PreferencesManager.getInstance(LoginActivity.this).setLogged();


        /**
         * REDIRECT
         */
        Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();



        /*
        json.addProperty(Constant.JSON_USER_CODE, edtCodigoLogin.getText().toString());
        json.addProperty(Constant.JSON_PASSWORD, edtPassLogin.getText().toString());
        json.addProperty(Constant.JSON_ID, Util.getUniqueID(LoginActivity.this));

        IntralotApplication.getInstance().getServices().login(json).
        enqueue(new CustomCallback<ResponseWeb>(LoginActivity.this){
            @Override
            public void onResponse(Call<ResponseWeb> call, Response<ResponseWeb> response) {
                super.onResponse(call, response);

                btnLogin.setEnabled(true);
                Util.progressDialogHide();
                ResponseWeb responseWeb = response.body();

                if (response.code() != 200 || !responseWeb.getStatus()) {
                    Util.showSnackbar(LoginActivity.this, getString(R.string.no_data_5));
                    return;
                }

                Login login = responseWeb.getLogin();

                if (login.getEstado().equalsIgnoreCase(Constant.ERROR)) {
                    Util.showDialog(
                        LoginActivity.this,
                        login.getMensaje(),
                        getString(R.string.close),
                        null);
                    return;
                }

                Client user = new Client(
                    login.getSession(),
                    edtCodigoLogin.getText().toString(),
                    login.getNombre(),
                    login.getNivel(),
                    login.getOrigen(),
                    login.getMensaje()
                );

                PreferencesManager.getInstance(LoginActivity.this).realmSetUser(user);
                PreferencesManager.getInstance(LoginActivity.this).setLoggedToken();

                Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }

            @Override
            public void onFailure(Call<ResponseWeb> call, Throwable t) {
                super.onFailure(call, t);
                btnLogin.setEnabled(true);
                Util.progressDialogHide();
            }
        });
        */
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if ((grantResults.length < 0) || (grantResults[0] != PackageManager.PERMISSION_GRANTED)) {
            return;
        }

        /*
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
        */
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
