package com.tianos.koketa.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.entity.Pendient;
import com.tianos.koketa.ui.adapter.PendingAdapter;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PendingActivity extends BaseActivity implements InterfaceKoketa2 {

    @BindView(R.id.rv_rows)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending);

        initSetup();
        initToolBar();
        initData();
        navigationDrawer();
    }

    @Override
    public void initSetup() {
        super.initSetup();

        recyclerView.setLayoutManager(new LinearLayoutManager(PendingActivity.this, RecyclerView.VERTICAL,false));
    }

    @Override
    public void initToolBar() {
        super.initToolBar();

        toolbar.setTitle(R.string.pending_list);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initData() {

        List<Pendient> lst = new ArrayList<Pendient>();

        Pendient a = new Pendient(1,"4EVER UNIFORMS S.A.C", "Pendiente", "2019-08-27 21:49", "2019-08-31");
        lst.add(a);

        Pendient b = new Pendient(2,"A TUS PIES E.I.R.L", "Pendiente", "2019-08-27 21:49", "2019-08-31");
        lst.add(b);

        Pendient c = new Pendient(3,"ABAD CRUZADO, ROSA", "Pendiente", "2019-08-27 21:49", "2019-08-31");
        lst.add(c);

        Pendient d = new Pendient(4,"A & E INVERSIONES QUIROZ", "Pendiente", "2019-08-27 21:49", "2019-08-31");
        lst.add(d);

        Pendient e = new Pendient(5,"ASCATE ALAYO, MERCADO PILAR", "Pendiente", "2019-08-27 21:49", "2019-08-31");
        lst.add(e);

        PendingAdapter bodyAdapter = new PendingAdapter(PendingActivity.this, lst);
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
