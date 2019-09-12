package com.tianos.koketa.ui.activity;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.widget.SearchView;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.database.BreadcrumbDb;
import com.tianos.koketa.entity.Breadcrumb;
import com.tianos.koketa.entity.User;
import com.tianos.koketa.ui.adapter.ClientAdapter;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ClientActivity extends BaseActivity implements InterfaceKoketa2 {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ClientAdapter bodyAdapter;
    private BreadcrumbDb breadcrumbDb;

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

        List<User> lst = new ArrayList<User>();

        User a = new User(1,"4EVER UNIFORMS S.A.C", "20270653929", "test-1@gmail.com");
        lst.add(a);

        User b = new User(2,"A TUS PIES E.I.R.L", "20536727524", "test-2@gmail.com");
        lst.add(b);

        User c = new User(3,"ABAD CRUZADO, ROSA", "20100014395", "test-3@gmail.com");
        lst.add(c);

        User d = new User(4,"A & E INVERSIONES QUIROZ", "20511615136", "test-4@gmail.com");
        lst.add(d);

        User e = new User(5,"ASCATE ALAYO, MERCADO PILAR", "20100017491", "test-5@gmail.com");
        lst.add(e);

        bodyAdapter = new ClientAdapter(ClientActivity.this, lst);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        SearchView searchView = super.searchViewConfig(menu);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                bodyAdapter.getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                bodyAdapter.getFilter().filter(query);
                return false;
            }
        });

        return true;
    }


}
