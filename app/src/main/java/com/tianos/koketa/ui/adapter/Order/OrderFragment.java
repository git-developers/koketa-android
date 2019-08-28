package com.tianos.koketa.ui.adapter.Order;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tianos.koketa.R;
import com.tianos.koketa.ui.fragment.BaseFragment;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketaFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderFragment extends BaseFragment implements InterfaceKoketaFragment {

    @BindView(R.id.tv_ayuda)
    TextView tvHeader1;

    private View viewInflate;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        initSetup(inflater, viewGroup, savedInstanceState);
        initData();

        return viewInflate;
    }

    @Override
    public void initSetup(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        context = viewGroup.getContext();
        viewInflate = inflater.inflate(R.layout.tab_order_order, viewGroup, false);

        ButterKnife.bind(this, viewInflate);
//        avanceDelMes = PreferencesManager.getInstance(context).realmGetAvanceDelMesTab();

//        rvRows.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
//        userFactory.setAvanceDelMesWizard(context, viewInflate, Constant.TABS);
    }

    @Override
    public void initData() {

        return;
    }
}