package com.tianos.koketa.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.tianos.koketa.R;
import com.tianos.koketa.entity.ResponseWeb;
import com.tianos.koketa.entity.User;
import com.tianos.koketa.retrofit.APIClient;
import com.tianos.koketa.retrofit.APIInterface;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa;
import com.tianos.koketa.util.Constant;
import com.tianos.koketa.util.PreferencesManager;
import com.tianos.koketa.util.Util;
import com.tianos.koketa.util.dialog.DialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements InterfaceKoketa {

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
        logOut();

    }

    @Override
    public void initSetup() {
        ButterKnife.bind(this);
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

    /*
    @OnClick(R.id.txt_olvide_contra_login)
    public void forgotPass(){
        Intent i = new Intent(this, PasswordForgotActivity.class);
        startActivity(i);
    }
    */

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

//        JSONObject json = new JSONObject();
//
//        try {
//            json.put(Constant.JSON_USERNAME, edtCodigoLogin.getText().toString());
//            json.put(Constant.JSON_PASSWORD, edtPassLogin.getText().toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        User user = new User("5d79860d17d6a", "123");
        apiInterface.logIn(user).enqueue(new Callback<ResponseWeb>() {
            @Override
            public void onResponse(Call<ResponseWeb> call, Response<ResponseWeb> response) {
                ResponseWeb responseWeb = response.body();

                Log.d("POLLO", "DATAdddd::::: " + responseWeb.getMessage());

                Util.progressDialogHide();
                btnLogin.setEnabled(true);


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
