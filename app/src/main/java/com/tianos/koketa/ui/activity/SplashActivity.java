package com.tianos.koketa.ui.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.tianos.koketa.R;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa;
import com.tianos.koketa.util.Constant;
import com.tianos.koketa.util.PreferencesManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements InterfaceKoketa {


    @BindView(R.id.iv_splash)
    ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initSetup();
        initToolBar();
        thread();
        doAnimation();
    }

    @Override
    public void initSetup() {
        super.initSetup();
    }

    @Override
    public void initToolBar() {
        super.initToolBar();

//        toolbar.setVisibility(View.GONE);
//        toolbar.getContext().setTheme(R.style.Base_ThemeOverlay_Toolbar_none);
        toolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));

//        toolbar.setElevation(0);
//        setSupportActionBar(toolbar);
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

                    if (PreferencesManager.getInstance(SplashActivity.this).isLogged()) {
                        i.setClass(SplashActivity.this, DashboardActivity.class);
                    }

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

    private void doAnimation() {
        Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.blink);
//        animation.setInterpolator(new LinearInterpolator());
//        animation.setRepeatCount(Animation.INFINITE);
//        animation.setDuration(400);

        ivSplash.startAnimation(animation);
    }
}
