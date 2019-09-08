package com.tianos.koketa.ui.activity;

import android.os.Bundle;

import com.tianos.koketa.R;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa2;

public class SynchronizeActivity extends BaseActivity implements InterfaceKoketa2 {

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
