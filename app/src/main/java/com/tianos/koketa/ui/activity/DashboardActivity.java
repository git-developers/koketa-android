package com.tianos.koketa.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.tianos.koketa.R;
import com.tianos.koketa.entity.Dashboard;
import com.tianos.koketa.ui.adapter.DashboardAdapter;
import com.tianos.koketa.ui.adapter.ImageSliderAdapter;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa;
import com.tianos.koketa.util.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DashboardActivity extends BaseActivity implements InterfaceKoketa {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.imageSlider)
    SliderView imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initSetup();
        initToolBar();
        dashboardIcons();
        navigationDrawer();
        imageSlider();

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
    }

    public void dashboardIcons() {

        List<Dashboard> lst = new ArrayList<>();

        Dashboard a = new Dashboard(
            Constant.DASH_ROUTES,
            getString(R.string.routes),
            R.drawable.ic_location_on_black_24dp,
            "A0" + Constant.DASH_ROUTES,
            R.color.slider_1);
        lst.add(a);

        Dashboard b = new Dashboard(
            Constant.DASH_CLIENTS,
            getString(R.string.clients),
            R.drawable.ic_supervisor_account_black_24dp,
            "A1" + Constant.DASH_CLIENTS,
            R.color.slider_2);
        lst.add(b);

        Dashboard c = new Dashboard(
            Constant.DASH_PRODUCTS,
            getString(R.string.articles),
            R.drawable.ic_view_quilt_black_24dp,
            "A2" + Constant.DASH_PRODUCTS,
            R.color.slider_3);
        lst.add(c);

        Dashboard d = new Dashboard(
            Constant.DASH_PENDIENTS,
            getString(R.string.pendings),
            R.drawable.ic_cloud_upload_black_24dp,
            "A3" + Constant.DASH_PENDIENTS,
            R.color.slider_4);
        lst.add(d);

        Dashboard e = new Dashboard(
            Constant.DASH_SYNCHRONIZE,
            getString(R.string.synchronize),
            R.drawable.ic_sync_black_24dp,
            "A4" + Constant.DASH_SYNCHRONIZE,
            R.color.slider_5);
        lst.add(e);

        Dashboard f = new Dashboard(
            Constant.DASH_VISITS,
            getString(R.string.visits),
            R.drawable.ic_my_location_black_24dp,
            "A5" + Constant.DASH_VISITS,
            R.color.slider_6);
        lst.add(f);

        Dashboard g = new Dashboard(
            Constant.DASH_PENDING_STATUS,
            getString(R.string.pending_status),
            R.drawable.ic_assignment_black_24dp,
            "A6" + Constant.DASH_PENDING_STATUS,
            R.color.slider_8);
        lst.add(g);

        Dashboard k = new Dashboard(
            Constant.DASH_PROFILE,
            getString(R.string.profile),
            R.drawable.ic_face_black_24dp,
            "A7" + Constant.DASH_PROFILE,
            R.color.slider_7);
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

    public void openRoute(View view) {
        Intent i = new Intent(DashboardActivity.this, RouteActivity.class);
        startActivity(i);
    }

    public void openProduct(View view) {
        Intent i = new Intent(DashboardActivity.this, ProductActivity.class);
        startActivity(i);
    }

    public void openVisit(View view) {
        Intent i = new Intent(DashboardActivity.this, VisitActivity.class);
        startActivity(i);
    }

    public void openPendient(View view) {
        Intent i = new Intent(DashboardActivity.this, PendientActivity.class);
        startActivity(i);
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

    private void imageSlider() {
        imageSlider.setSliderAdapter(new ImageSliderAdapter(DashboardActivity.this));
        imageSlider.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSlider.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);
        imageSlider.setAutoCycleDirection(imageSlider.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        imageSlider.setIndicatorSelectedColor(Color.WHITE);
        imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        imageSlider.setScrollTimeInSec(4);
        imageSlider.startAutoCycle();
    }

}
