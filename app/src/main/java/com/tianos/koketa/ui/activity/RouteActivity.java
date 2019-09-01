package com.tianos.koketa.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.entity.Client;
import com.tianos.koketa.entity.Route;
import com.tianos.koketa.ui.adapter.ClientAdapter;
import com.tianos.koketa.ui.adapter.RouteAdapter;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RouteActivity extends BaseActivity implements InterfaceKoketa2 {

    @BindView(R.id.rv_rows)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        initSetup();
        initToolBar();
        initData();
        navigationDrawer();
    }

    @Override
    public void initSetup() {
        super.initSetup();

        recyclerView.setLayoutManager(new LinearLayoutManager(RouteActivity.this, RecyclerView.VERTICAL,false));
    }

    @Override
    public void initToolBar() {
        super.initToolBar();

        toolbar.setTitle(R.string.routes_list);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initData() {

        List<Route> lst = new ArrayList<Route>();

        Route a = new Route(1,"Ruta 1");
        lst.add(a);

        Route b = new Route(2,"Ruta 2");
        lst.add(b);

        Route c = new Route(3,"Ruta 3");
        lst.add(c);

        Route d = new Route(4,"Ruta 4");
        lst.add(d);

        Route e = new Route(5,"Ruta 5");
        lst.add(e);

        RouteAdapter bodyAdapter = new RouteAdapter(RouteActivity.this, lst);
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
