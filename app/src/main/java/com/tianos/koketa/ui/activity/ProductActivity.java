package com.tianos.koketa.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.entity.Product;
import com.tianos.koketa.ui.adapter.ProductAdapter;
import com.tianos.koketa.ui.fragment.ProductDetailFragment;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ProductActivity extends BaseActivity implements InterfaceKoketa2, ProductDetailFragment.DialogListener {

    @BindView(R.id.rv_rows)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        initSetup();
        initToolBar();
        initData();
        navigationDrawer();
    }

    @Override
    public void initSetup() {
        super.initSetup();

        recyclerView.setLayoutManager(new LinearLayoutManager(ProductActivity.this, RecyclerView.VERTICAL,false));
    }

    @Override
    public void initToolBar() {
        super.initToolBar();

        toolbar.setTitle(R.string.products_list);
        setSupportActionBar(toolbar);
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

        ProductAdapter bodyAdapter = new ProductAdapter(ProductActivity.this, lst);
        recyclerView.setAdapter(bodyAdapter);

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

    @Override
    public void onFinishEditDialog(String inputText) {

        if (TextUtils.isEmpty(inputText)) {
//            textView.setText("Email was not entered");
        } else {
//            textView.setText("Email entered: " + inputText);
        }
    }


}
