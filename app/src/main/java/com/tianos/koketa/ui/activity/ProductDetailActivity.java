package com.tianos.koketa.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa2;

import butterknife.BindView;

public class ProductDetailActivity extends BaseActivity implements InterfaceKoketa2 {

    @BindView(R.id.rv_rows)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

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

        toolbar.setTitle(R.string.products_detail);
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
