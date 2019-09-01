package com.tianos.koketa.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.entity.Route;
import com.tianos.koketa.entity.Visit;
import com.tianos.koketa.ui.adapter.RouteAdapter;
import com.tianos.koketa.ui.adapter.VisitAdapter;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class VisitActivity extends BaseActivity implements InterfaceKoketa2 {

    @BindView(R.id.rv_rows)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);

        initSetup();
        initToolBar();
        initData();
        navigationDrawer();
    }

    @Override
    public void initSetup() {
        super.initSetup();

        recyclerView.setLayoutManager(new LinearLayoutManager(VisitActivity.this, RecyclerView.VERTICAL,false));
    }

    @Override
    public void initToolBar() {
        super.initToolBar();

        toolbar.setTitle(R.string.visits_list);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initData() {

        List<Visit> lst = new ArrayList<Visit>();

        Visit a = new Visit(1,"Visita 1");
        lst.add(a);

        Visit b = new Visit(2,"Visita 2");
        lst.add(b);

        Visit c = new Visit(3,"Visita 3");
        lst.add(c);

        Visit d = new Visit(4,"Visita 4");
        lst.add(d);

        Visit e = new Visit(5,"Visita 5");
        lst.add(e);

        VisitAdapter bodyAdapter = new VisitAdapter(VisitActivity.this, lst);
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
