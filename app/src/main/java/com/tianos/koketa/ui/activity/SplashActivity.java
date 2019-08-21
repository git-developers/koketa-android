package com.tianos.koketa.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tianos.koketa.R;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa;
import com.tianos.koketa.util.Constant;

import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements InterfaceKoketa {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initSetup();
        initToolBar();
        thread();
    }

    @Override
    public void initSetup() {
//        super.initSetup();
    }

    @Override
    public void initToolBar() {
//        toolbar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void thread() {
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(Constant.SPLASH_TIME);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    Intent i = new Intent();
                    i.setClass(SplashActivity.this, LoginActivity.class);

//                    if (PreferencesManager.getInstance(SplashActivity.this).isLogged()) {
//                        i.setClass(SplashActivity.this, DashboardActivity.class);
//                    }

                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
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
