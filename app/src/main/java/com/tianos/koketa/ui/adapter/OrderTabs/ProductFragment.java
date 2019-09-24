package com.tianos.koketa.ui.adapter.OrderTabs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.database.BreadcrumbDb;
import com.tianos.koketa.database.OrderDb;
import com.tianos.koketa.entity.Breadcrumb;
import com.tianos.koketa.entity.OrderDetail;
import com.tianos.koketa.ui.adapter.TabProductAdapter;
import com.tianos.koketa.ui.fragment.BaseFragment;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketaFragment;
import com.tianos.koketa.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductFragment extends BaseFragment implements InterfaceKoketaFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.tv_sub_total)
    TextView tvSubTotal;

    @BindView(R.id.tv_total)
    TextView tvTotal;

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
        viewInflate = inflater.inflate(R.layout.tab_order_product, viewGroup, false);

        ButterKnife.bind(this, viewInflate);

        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,false));
    }

    @Override
    public void initData() {

        dataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();

        dataSetChanged();
    }

    private void dataSetChanged() {

        /**
         * BREADCRUMB
         */
        BreadcrumbDb breadcrumbDb = new BreadcrumbDb(getActivity());
        Breadcrumb breadcrumb = breadcrumbDb.findLast();


        /**
         * GET CLIENT CURRENT ORDER
         */
        OrderDb orderDb = new OrderDb(getActivity());
        List<OrderDetail> lst = orderDb.findAllOrderDetailByClient(breadcrumb);

        layoutNoData.setVisibility(View.GONE);

        if (lst == null || lst.size() == 0) {
            layoutNoData.setVisibility(View.VISIBLE);
            return;
        }

        TabProductAdapter bodyAdapter = new TabProductAdapter(context, lst);
        recyclerView.setAdapter(bodyAdapter);


        /**
         * TOTAL - SUBTOTAL
         */
        float subTotal = 0;
        for (OrderDetail orderDetail : lst) {
            subTotal = subTotal + orderDetail.getProduct().getPrice();
        }

        tvSubTotal.setText("SOL " + Util.money(subTotal));
        tvTotal.setText("SOL " + Util.money(subTotal));
    }


}