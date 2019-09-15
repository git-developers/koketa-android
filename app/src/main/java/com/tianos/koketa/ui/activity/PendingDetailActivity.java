package com.tianos.koketa.ui.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tianos.koketa.R;
import com.tianos.koketa.entity.Product;
import com.tianos.koketa.ui.adapter.ProductAdapter;
import com.tianos.koketa.ui.adapter.ProductPendingAdapter;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PendingDetailActivity extends BaseActivity implements InterfaceKoketa2, OnMapReadyCallback {

    @BindView(R.id.rv_rows_products)
    RecyclerView rvRowsProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_detail);

        initSetup();
        initToolBar();
        initData();
        navigationDrawer();
        map();
    }

    @Override
    public void initSetup() {
        super.initSetup();

        rvRowsProducts.setLayoutManager(new LinearLayoutManager(PendingDetailActivity.this, RecyclerView.VERTICAL,false));
    }

    @Override
    public void initToolBar() {
        super.initToolBar();

        toolbar.setTitle(R.string.pending_detail);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initData() {

        List<Product> lst = new ArrayList<Product>();

        /*
        Product a = new Product(1,"KOKETA B SILUET OI18 CHALECO LATEX L NEGRO", "Koketa", 45, 4.01);
        lst.add(a);

        Product b = new Product(2,"KOKETA B SILUET BODY TRE LATEX M MTE BEIGE", "Koketa", 89, 24.66);
        lst.add(b);

        Product c = new Product(3,"KOKETA B SILUET CAMISETA TA LATEX L MTE NEGRO", "Koketa", 150, 58.88);
        lst.add(c);

        Product d = new Product(4,"KOKETA B SILUET FAJA COMPLETA LATEX M BEIGE", "Koketa", 78, 5.19);
        lst.add(d);

        Product e = new Product(5,"KOKETA CLASSIC M/PANTALON SPT TU PIEL", "Koketa", 564, 7.55);
        lst.add(e);
        */

        ProductPendingAdapter bodyAdapter = new ProductPendingAdapter(PendingDetailActivity.this, lst);
        rvRowsProducts.setAdapter(bodyAdapter);
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

    private void map() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(PendingDetailActivity.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        GoogleMap mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng latLng = new LatLng(-12.0673161,-77.033729);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Ubicacion del pedido Koketa"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}
