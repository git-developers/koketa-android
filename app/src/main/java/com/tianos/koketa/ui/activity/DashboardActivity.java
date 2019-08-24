package com.tianos.koketa.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.tianos.koketa.R;
import com.tianos.koketa.entity.Dashboard;
import com.tianos.koketa.ui.adapter.DashboardAdapter;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa;
import com.tianos.koketa.util.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DashboardActivity extends BaseActivity implements InterfaceKoketa {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initSetup();
        initToolBar();
        dashboardIcons();
        navigationDrawer();
    }

    @Override
    public void initSetup() {
        super.initSetup();

        recyclerView.setLayoutManager(new GridLayoutManager(DashboardActivity.this, 2));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void initToolBar() {
        super.initToolBar();

        toolbar.setTitle(R.string.dashboard);
        setSupportActionBar(toolbar);

//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

    }

    public void dashboardIcons() {

        List<Dashboard> lst = new ArrayList<>();

        Dashboard a = new Dashboard(
            Constant.DASH_ROUTES,
            getString(R.string.routes),
            R.drawable.ic_location_on_black_24dp,
            "A0" + Constant.DASH_ROUTES);
        lst.add(a);

        Dashboard b = new Dashboard(
            Constant.DASH_CLIENTS,
            getString(R.string.clients),
            R.drawable.ic_supervisor_account_black_24dp,
            "A1" + Constant.DASH_CLIENTS);
        lst.add(b);

        Dashboard c = new Dashboard(
            Constant.DASH_ARTICLES,
            getString(R.string.articles),
            R.drawable.ic_view_quilt_black_24dp,
            "A2" + Constant.DASH_ARTICLES);
        lst.add(c);

        Dashboard d = new Dashboard(
            Constant.DASH_PENDIENTS,
            getString(R.string.pendings),
            R.drawable.ic_cloud_upload_black_24dp,
            "A3" + Constant.DASH_PENDIENTS);
        lst.add(d);

        Dashboard e = new Dashboard(
            Constant.DASH_SYNCHRONIZE,
            getString(R.string.synchronize),
            R.drawable.ic_sync_black_24dp,
            "A4" + Constant.DASH_SYNCHRONIZE);
        lst.add(e);

        Dashboard f = new Dashboard(
            Constant.DASH_VISITS,
            getString(R.string.visits),
            R.drawable.ic_my_location_black_24dp,
            "A5" + Constant.DASH_VISITS);
        lst.add(f);

        Dashboard g = new Dashboard(
            Constant.DASH_PENDING_STATUS,
            getString(R.string.pending_status),
            R.drawable.ic_assignment_black_24dp,
            "A6" + Constant.DASH_PENDING_STATUS);
        lst.add(g);

        Dashboard k = new Dashboard(
            Constant.DASH_PROFILE,
            getString(R.string.profile),
            R.drawable.ic_face_black_24dp,
            "A7" + Constant.DASH_PROFILE);
        lst.add(k);

        DashboardAdapter adapter = new DashboardAdapter(DashboardActivity.this, lst);
        recyclerView.setAdapter(adapter);
    }

    public void openProfile(View view) {
        Intent i = new Intent(DashboardActivity.this, ProfileActivity.class);
        startActivity(i);
    }

    public void openClient(View view) {
        Intent i = new Intent(DashboardActivity.this, ClientActivity.class);
        startActivity(i);
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
