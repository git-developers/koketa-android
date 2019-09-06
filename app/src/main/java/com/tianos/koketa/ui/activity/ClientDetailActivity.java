package com.tianos.koketa.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.entity.Contact;
import com.tianos.koketa.entity.Product;
import com.tianos.koketa.ui.adapter.ContactAdapter;
import com.tianos.koketa.ui.adapter.ProductPendingAdapter;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ClientDetailActivity extends BaseActivity implements InterfaceKoketa2 {

    @BindView(R.id.rv_rows_contacts)
    RecyclerView rvRowsContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail);

        initSetup();
        initToolBar();
        initData();
        navigationDrawer();
    }

    @OnClick(R.id.fab_shopping_cart)
    public void btnFabShoppingCart() {

        Intent i = new Intent(ClientDetailActivity.this, OrderTabsActivity.class);
        startActivity(i);
    }

    @Override
    public void initSetup() {
        super.initSetup();

        rvRowsContacts.setLayoutManager(new LinearLayoutManager(ClientDetailActivity.this, RecyclerView.VERTICAL,false));
    }

    @Override
    public void initToolBar() {
        super.initToolBar();

        toolbar.setTitle(R.string.client_detail);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initData() {

        List<Contact> lst = new ArrayList<Contact>();

        Contact a = new Contact(1,"4EVER UNIFORMS S.A.C", "20270653929", "test-1@gmail.com");
        lst.add(a);

        Contact b = new Contact(2,"A TUS PIES E.I.R.L", "20536727524", "test-2@gmail.com");
        lst.add(b);

        ContactAdapter bodyAdapter = new ContactAdapter(ClientDetailActivity.this, lst);
        rvRowsContacts.setAdapter(bodyAdapter);
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
