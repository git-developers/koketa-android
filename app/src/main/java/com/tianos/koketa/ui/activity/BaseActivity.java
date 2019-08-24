package com.tianos.koketa.ui.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.tianos.koketa.R;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity implements InterfaceKoketa, NavigationView.OnNavigationItemSelectedListener {

    @Nullable
    @BindView(R.id.toolbar)
    public Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);

//        initToolBar();

//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


    }

    public void navigationDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void initSetup() {
        ButterKnife.bind(this);
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View content = getLayoutInflater().inflate(layoutResID, null);
        FrameLayout fm = (FrameLayout) findViewById(R.id.layout_container);
        fm.addView(content);
    }

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
        } else if (id == R.id.action_logout) {
            Intent i = new Intent(BaseActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
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


    public String versionName() {
        PackageManager packageManager = BaseActivity.this.getPackageManager();
        String packageName = BaseActivity.this.getPackageName();

        String out = getString(R.string.not_available);

        try {
            out = packageManager.getPackageInfo(packageName, 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return out;
    }

    public String versionCode() {
        PackageManager packageManager = BaseActivity.this.getPackageManager();
        String packageName = BaseActivity.this.getPackageName();

        String out = getString(R.string.not_available);

        try {
            long versionCodeLong = packageManager.getPackageInfo(packageName, 0).versionCode;
            out = Long.toString(versionCodeLong);
        } catch (NoSuchMethodError e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return out;
    }
}
