package com.tianos.koketa.ui.activity;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.entity.Client;
import com.tianos.koketa.ui.adapter.ClientAdapter;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClientActivity extends BaseActivity implements InterfaceKoketa2 {

    @BindView(R.id.rv_rows)
    RecyclerView recyclerView;

//    @BindView(R.id.tv_user_name)
//    TextView tvUserName;


//    private Breadcrumb breadcrumb;
//    private BreadcrumbDb breadcrumbDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        initSetup();
        initToolBar();
        initData();
        navigationDrawer();
    }

    @Override
    public void initSetup() {
        super.initSetup();

//        breadcrumbDb = new BreadcrumbDb(Detail1Activity.this);
//        breadcrumb = breadcrumbDb.findLast();
        recyclerView.setLayoutManager(new LinearLayoutManager(ClientActivity.this, RecyclerView.VERTICAL,false));
    }

    @Override
    public void initToolBar() {
        super.initToolBar();

        toolbar.setTitle(R.string.clients_list);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initData() {

        List<Client> lst = new ArrayList<Client>();

        Client a = new Client(1,"4EVER UNIFORMS S.A.C", "20270653929", "test-1@gmail.com");
        lst.add(a);

        Client b = new Client(2,"A TUS PIES E.I.R.L", "20536727524", "test-2@gmail.com");
        lst.add(b);

        Client c = new Client(3,"ABAD CRUZADO, ROSA", "20100014395", "test-3@gmail.com");
        lst.add(c);

        Client d = new Client(4,"A & E INVERSIONES QUIROZ", "20511615136", "test-4@gmail.com");
        lst.add(d);

        Client e = new Client(5,"ASCATE ALAYO, MERCADO PILAR", "20100017491", "test-5@gmail.com");
        lst.add(e);

        ClientAdapter bodyAdapter = new ClientAdapter(ClientActivity.this, lst);
        recyclerView.setAdapter(bodyAdapter);


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
