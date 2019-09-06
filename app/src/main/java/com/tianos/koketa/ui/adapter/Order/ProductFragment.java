package com.tianos.koketa.ui.adapter.Order;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.entity.Product;
import com.tianos.koketa.ui.activity.ProductActivity;
import com.tianos.koketa.ui.adapter.ProductAdapter;
import com.tianos.koketa.ui.adapter.TabProductAdapter;
import com.tianos.koketa.ui.fragment.BaseFragment;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketaFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductFragment extends BaseFragment implements InterfaceKoketaFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

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

        List<Product> lst = new ArrayList<Product>();

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

        TabProductAdapter bodyAdapter = new TabProductAdapter(context, lst);
        recyclerView.setAdapter(bodyAdapter);
    }

}