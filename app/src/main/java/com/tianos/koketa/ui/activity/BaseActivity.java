package com.tianos.koketa.ui.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.tianos.koketa.R;
import com.tianos.koketa.entity.Dashboard;
import com.tianos.koketa.entity.User;
import com.tianos.koketa.retrofit.APIClient;
import com.tianos.koketa.retrofit.APIInterface;
import com.tianos.koketa.ui.fragment.AboutFragment;
import com.tianos.koketa.ui.fragment.ImeiFragment;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa;
import com.tianos.koketa.util.PreferencesManager;
import com.tianos.koketa.util.Util;
import com.tianos.koketa.util.dialog.DialogFragment;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity implements InterfaceKoketa, NavigationView.OnNavigationItemSelectedListener {

    @Nullable
    @BindView(R.id.ll_main)
    public LinearLayout llMain;

    @Nullable
    @BindView(R.id.layout_no_data)
    public RelativeLayout layoutNoData;

    @Nullable
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @Nullable
    @BindView(R.id.drawer_layout)
    public DrawerLayout drawer;

    public APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);

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

        /**
         * SET TEXT
         */
        User user = PreferencesManager.getInstance(BaseActivity.this).realmGetUser();

        if (user == null) {
            return;
        }

        View v = navigationView.getHeaderView(0);

        TextView tvNav1 = (TextView ) v.findViewById(R.id.tv_nav_1);
        tvNav1.setText(user.getName() + " " + user.getLastName());

        TextView tvNav2 = (TextView ) v.findViewById(R.id.tv_nav_2);
        tvNav2.setText(user.getEmail());
    }

    @Override
    public void initSetup() {
        ButterKnife.bind(this);

        apiInterface = APIClient.getClient().create(APIInterface.class);
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

            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    protected SearchView searchViewConfig(Menu menu) {

        MenuItem menuItem = menu.findItem(R.id.action_search);
        menuItem.setVisible(true);

        SearchView searchView = (androidx.appcompat.widget.SearchView) menuItem.getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Buscar...");
        searchView.setSoundEffectsEnabled(true);
//        searchView.setBackgroundColor(this.getResources().getColor(R.color.colorAccent));

        EditText searchEditText = searchView.findViewById(R.id.search_src_text);
        searchEditText.setTextColor(Color.WHITE);
        searchEditText.setTextSize(16);
        searchEditText.setHintTextColor(this.getResources().getColor(R.color.colorPrimaryDark));

        Drawable d = getResources().getDrawable(R.drawable.search_widget_background);
        searchView.setBackground(d);

        return searchView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Drawable drawable = item.getIcon();

        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }

        switch (item.getItemId()) {
            case R.id.action_settings:
                Util.showToast(BaseActivity.this,"click Settings");
                return true;
            case R.id.action_cut:
                Util.showToast(BaseActivity.this,"click cut");
                return true;
            case R.id.action_logout:
                Intent i = new Intent(BaseActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Intent i = new Intent();

        switch (item.getItemId()) {
            case R.id.nav_home:
                i.setClass(BaseActivity.this, DashboardActivity.class);
                break;
            case R.id.nav_routes:
                i.setClass(BaseActivity.this, ClientActivity.class);
                break;
            case R.id.nav_clients:
                i.setClass(BaseActivity.this, ClientActivity.class);
                break;
            case R.id.nav_products:
                i.setClass(BaseActivity.this, ProductActivity.class);
                break;
            case R.id.nav_profile:
                i.setClass(BaseActivity.this, ProfileActivity.class);
                break;
            case R.id.nav_share:
                Util.showToast(BaseActivity.this, "Share koketa");
                return true;
            case R.id.nav_help:
                Util.showToast(BaseActivity.this, "Help Center");
                return true;
            case R.id.nav_about:
                AboutFragment fragment = new AboutFragment();
                FragmentTransaction ft = ((AppCompatActivity) BaseActivity.this).getSupportFragmentManager().beginTransaction();
                fragment.show(ft, "dialog");
                return true;
            case R.id.nav_logout:
                Util.progressDialogShow(BaseActivity.this, getString(R.string.in_progress));
                i.setClass(BaseActivity.this, LoginActivity.class);
                break;
        }

        startActivity(i);
        finish();

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
