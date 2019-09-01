package com.tianos.koketa.ui.activity;

import android.os.Bundle;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.entity.Client;
import com.tianos.koketa.ui.adapter.ClientAdapter;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ClientActivity extends BaseActivity implements InterfaceKoketa2 {

    @BindView(R.id.rv_rows)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        initSetup();
        initToolBar();
        initData();
        navigationDrawer();
    }

    @Override
    public void initSetup() {
        super.initSetup();

        recyclerView.setLayoutManager(new LinearLayoutManager(ClientActivity.this, RecyclerView.VERTICAL,false));
    }

    @Override
    public void initToolBar() {
        super.initToolBar();

        toolbar.setTitle(R.string.client_list);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initData() {

        List<Client> lst = new ArrayList<Client>();

        Client a = new Client(1,"4EVER UNIFORMS S.A.C", "20270653929", "test-1@gmail.com");
        lst.add(a);

        Client b = new Client(2,"A TUS PIES E.I.R.L", "20536727524", "test-2@gmail.com");
        lst.add(b);

        Client c = new Client(3,"ABAD CRUZADO, ROSA", "20100014395", "test-3@gmail.com");
        lst.add(c);

        Client d = new Client(4,"A & E INVERSIONES QUIROZ", "20511615136", "test-4@gmail.com");
        lst.add(d);

        Client e = new Client(5,"ASCATE ALAYO, MERCADO PILAR", "20100017491", "test-5@gmail.com");
        lst.add(e);

        ClientAdapter bodyAdapter = new ClientAdapter(ClientActivity.this, lst);
        recyclerView.setAdapter(bodyAdapter);
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
