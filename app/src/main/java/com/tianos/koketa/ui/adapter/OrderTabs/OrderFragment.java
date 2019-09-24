package com.tianos.koketa.ui.adapter.OrderTabs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tianos.koketa.R;
import com.tianos.koketa.database.BreadcrumbDb;
import com.tianos.koketa.database.UserDb;
import com.tianos.koketa.entity.Breadcrumb;
import com.tianos.koketa.entity.User;
import com.tianos.koketa.ui.fragment.BaseFragment;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketaFragment;
import com.tianos.koketa.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderFragment extends BaseFragment implements InterfaceKoketaFragment, OnMapReadyCallback {

    @BindView(R.id.textview_12)
    TextView tvName;

    @BindView(R.id.textview_14)
    TextView tvRuc;

    @BindView(R.id.textview_16)
    TextView tvEmail;

    @BindView(R.id.textview_18)
    TextView tvPhone;

    @BindView(R.id.textview_110)
    TextView tvAddress;

    @BindView(R.id.textview_112)
    TextView tvAboutMe;

    @BindView(R.id.textview_22)
    TextView tvCreditLine;

    @BindView(R.id.textview_24)
    TextView tvBalance;


    private View viewInflate;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        initSetup(inflater, viewGroup, savedInstanceState);
        initData();
        map();

        return viewInflate;
    }

    @Override
    public void initSetup(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        this.context = viewGroup.getContext();
        viewInflate = inflater.inflate(R.layout.tab_order_order, viewGroup, false);

        ButterKnife.bind(this, viewInflate);
    }

    @Override
    public void initData() {

        BreadcrumbDb breadcrumbDb = new BreadcrumbDb(getActivity());
        Breadcrumb lastBreadcrumb = breadcrumbDb.findLast();

        UserDb userDb = new UserDb(getActivity());
        User client = userDb.findOneById(String.valueOf(lastBreadcrumb.getClientId()));

        tvName.setText(client.getName());
        tvRuc.setText(client.getRuc());
        tvEmail.setText(client.getEmail());
        tvPhone.setText(client.getPhone());
        tvAddress.setText(client.getAddress());
        tvAboutMe.setText(client.getAboutMe());
        tvCreditLine.setText("SOL " + Util.money(client.getCreditLine()));
        tvBalance.setText("SOL " + Util.money(client.getBalance()));
    }

    private void map() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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