package com.tianos.koketa.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.tianos.koketa.R;
import com.tianos.koketa.ui.adapter.ClientDetailExpandableAdapter;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa2;
import com.tianos.koketa.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ClientDetail2Activity extends BaseActivity implements InterfaceKoketa2 {

    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail_2);

        initSetup();
        initToolBar();
        initData();
        navigationDrawer();

        expandableListView();
    }

    @OnClick(R.id.fab_shopping_cart)
    public void btnFabShoppingCart() {

        Intent i = new Intent(ClientDetail2Activity.this, OrderTabsActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.fab_visits)
    public void btnFabVisits() {

        Util.showToast(
            getApplicationContext(),
            "btnFabVisits");
    }

    public void expandableListView() {

        HashMap<String, List<String>> expandableListDetail = getData();
        List<String> expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        ExpandableListAdapter expandableListAdapter = new ClientDetailExpandableAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

                Util.showToast(
                    getApplicationContext(),
                    expandableListTitle.get(groupPosition) + " List Expanded.");
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

                Util.showToast(
                    getApplicationContext(),
                    expandableListTitle.get(groupPosition) + " List Collapsed.");

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                Util.showToast(
                    getApplicationContext(),
            expandableListTitle.get(groupPosition)
                    + " -> "
                    + expandableListDetail.get(
                    expandableListTitle.get(groupPosition)).get(childPosition));

                return false;
            }
        });

    }

    public static HashMap<String, List<String>> getData() {

        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> general = new ArrayList<String>();
        general.add("Ruc: 111222333444");
        general.add("Correo: test@gmail.com");
        general.add("Telefono: 999.888.777");
        general.add("Direccion: Ave. test 123, Lima Cercado.");
        general.add("Comentario");

        List<String> administrative = new ArrayList<String>();
        administrative.add("Linea de Credito: SOL 0.00");
        administrative.add("Saldo: SOL 0.00");
        administrative.add("Condicion de pago: Contado");

        List<String> contact = new ArrayList<String>();
        contact.add("Ursula Ruckts");
        contact.add("Test 11");
        contact.add("Test 22");
        contact.add("Test 33");
        contact.add("Test 44");

        expandableListDetail.put("Datos Generales", general);
        expandableListDetail.put("Datos Administrativos", administrative);
        expandableListDetail.put("Personas de Contacto", contact);

        return expandableListDetail;
    }

    @Override
    public void initSetup() {
        super.initSetup();

    }

    @Override
    public void initToolBar() {
        super.initToolBar();

        toolbar.setTitle(R.string.client_detail);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initData() {

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
