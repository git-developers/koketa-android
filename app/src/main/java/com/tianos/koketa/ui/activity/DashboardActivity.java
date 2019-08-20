package com.tianos.koketa.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
        dashboardIcons();


        /*
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        */
    }

    @Override
    public void initSetup() {
        super.initSetup();

        recyclerView.setLayoutManager(new GridLayoutManager(DashboardActivity.this, 2));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void initToolBar() {

    }

    public void dashboardIcons() {

        List<Dashboard> lst = new ArrayList<>();

        Dashboard a = new Dashboard(
                Constant.DASH_AVANCE_DEL_MES,
                getString(R.string.dummy),
                R.drawable.ic_person_black_24dp,
                "A0" + Constant.DASH_AVANCE_DEL_MES);
        lst.add(a);

        /*
        Dashboard b = new Dashboard(
                Constant.DASH_POS_SIN_VENTAS,
                getString(R.string.pos_sin_ventas),
                R.drawable.ic_monetization_on_black_24dp,
                "A0" + Constant.DASH_POS_SIN_VENTAS);
        lst.add(b);
        Dashboard c = new Dashboard(
                Constant.DASH_AVANCE_POR_PRODUCTO,
                getString(R.string.avance_por_producto),
                R.drawable.ic_insert_chart_black_24dp,
                "A0" + Constant.DASH_AVANCE_POR_PRODUCTO);
        lst.add(c);
        Dashboard h = new Dashboard(
                Constant.DASH_CAMBIAR_PASSWORD,
                getString(R.string.change_password),
                R.drawable.ic_vpn_key_black_24dp,
                "A0" + Constant.DASH_CAMBIAR_PASSWORD);
        lst.add(h);
        Dashboard i = new Dashboard(
                Constant.DASH_PERFIL,
                getString(R.string.profile),
                R.drawable.ic_face_black_24dp,
                "A0" + Constant.DASH_PERFIL);
        lst.add(i);
        Dashboard j = new Dashboard(
                Constant.DASH_CERRAR_SESION,
                getString(R.string.cerrar_sesion),
                R.drawable.ic_settings_power_black_24dp,
                "A0" + Constant.DASH_CERRAR_SESION);
        lst.add(j);
        */

        DashboardAdapter adapter = new DashboardAdapter(DashboardActivity.this, lst);
        recyclerView.setAdapter(adapter);
    }


    public void openAvanceDelMes(View view) {

    }




    /*
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    */




}
