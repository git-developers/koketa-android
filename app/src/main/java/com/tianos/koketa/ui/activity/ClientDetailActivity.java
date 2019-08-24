package com.tianos.koketa.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.entity.Client;
import com.tianos.koketa.ui.adapter.ClientAdapter;
import com.tianos.koketa.ui.adapter.CustomExpandableListAdapter;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class ClientDetailActivity extends BaseActivity implements InterfaceKoketa2 {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail);

        initSetup();
        initToolBar();
        initData();
        navigationDrawer();





        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });






    }




    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> cricket = new ArrayList<String>();
        cricket.add("India");
        cricket.add("Pakistan");
        cricket.add("Australia");
        cricket.add("England");
        cricket.add("South Africa");

        List<String> football = new ArrayList<String>();
        football.add("Brazil");
        football.add("Spain");
        football.add("Germany");
        football.add("Netherlands");
        football.add("Italy");

        List<String> basketball = new ArrayList<String>();
        basketball.add("United States");
        basketball.add("Spain");
        basketball.add("Argentina");
        basketball.add("France");
        basketball.add("Russia");

        expandableListDetail.put("CRICKET TEAMS", cricket);
        expandableListDetail.put("FOOTBALL TEAMS", football);
        expandableListDetail.put("BASKETBALL TEAMS", basketball);
        return expandableListDetail;
    }












    @Override
    public void initSetup() {
        super.initSetup();

    }

    @Override
    public void initToolBar() {
        super.initToolBar();

        toolbar.setTitle(R.string.clients_detail);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initData() {




        /*
        hideMainLinearLayout();
        Util.progressDialogShow(Detail1Activity.this, getString(R.string.processing));

        Client user = PreferencesManager.getInstance(Detail1Activity.this).realmGetUser();

        JsonObject json = new JsonObject();
        json.addProperty(Constant.JSON_SESSION, user.getSession());
        json.addProperty(Constant.JSON_NIVEL, user.getNivel());
        json.addProperty(Constant.JSON_ORIGEN, user.getOrigen());
        json.addProperty(Constant.JSON_PANEL, breadcrumb.getPanel());
        json.addProperty(Constant.JSON_USER_CODE, breadcrumb.getUserCode());
        json.addProperty(Constant.JSON_ID, Util.getUniqueID(Detail1Activity.this));

        IntralotApplication.getInstance().getServices().avanceDelMesDetail1(json).
        enqueue(new CustomCallback<ResponseWeb>(Detail1Activity.this){
            @Override
            public void onResponse(retrofit2.Call<ResponseWeb> call, Response<ResponseWeb> response) {
                super.onResponse(call, response);

                Util.progressDialogHide();
                ResponseWeb responseWeb = response.body();

                if (response.code() != 200 || !responseWeb.getStatus()) {
                    Util.showDialog(
                        Detail1Activity.this,
                        getString(R.string.no_data),
                        getString(R.string.close),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                    return;
                }

                Procedure procedure = responseWeb.getAvanceDelMes();

                if (procedure.getItem() == null) {
                    showNoDataLayout();
                    return;
                }

                showMainLinearLayout();
                procedure.parseTable();
                procedure.parseHeaderFooter();
                procedure.parseTableDB();
                procedure.parseHeaderFooterDB();

                tvUserName.setText(breadcrumb.getUserName());
                tvAyuda.setText(procedure.getAyuda());


                RealmList<RealmString> header = procedure.getHeaderDB();
                //tvHeader1.setText(Util.data(header, 0) + " y " + Util.data(header, 1));
                tvHeader1.setText(Util.data(header, 1));
                tvHeader2.setText(Util.data(header, 3));
                tvHeader3.setText(Util.data(header, 4));
                tvHeader4.setText(Util.data(header, 5));


                BodyDetail1Adapter bodyAdapter = new BodyDetail1Adapter(Detail1Activity.this, procedure);
                recyclerView.setAdapter(bodyAdapter);


                RealmList<RealmString> footer = procedure.getFooterDB();
                tvFooter1.setText(Util.data(footer, 0));
                tvFooter2.setText(Util.data(footer, 1));
                tvFooter3.setText(Util.data(footer, 2));
                tvFooter4.setText(Util.data(footer, 3));


                Drawable img = getBaseContext().getResources().getDrawable(R.drawable.ic_monetization_on_black_24dp);
                img.setBounds(0, 0, 25, 25);
                tvFooter1.setCompoundDrawables(img, null, null, null);
                tvFooter1.setGravity(Gravity.CENTER);
                //tvFooter1.setCompoundDrawablePadding(30);
            }

            @Override
            public void onFailure(Call<ResponseWeb> call, Throwable t) {
                super.onFailure(call, t);
                showNoDataLayout();
                Util.progressDialogHide();
            }
        });
        */
    }

}
