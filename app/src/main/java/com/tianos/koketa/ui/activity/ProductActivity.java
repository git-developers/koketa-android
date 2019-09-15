package com.tianos.koketa.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.database.ProductDb;
import com.tianos.koketa.entity.Product;
import com.tianos.koketa.entity.User;
import com.tianos.koketa.ui.adapter.ProductAdapter;
import com.tianos.koketa.ui.fragment.ProductDetailFragment;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa2;

import java.util.List;

import butterknife.BindView;

public class ProductActivity extends BaseActivity implements InterfaceKoketa2,
        ProductDetailFragment.DialogListener {

    @BindView(R.id.rv_rows)
    RecyclerView recyclerView;

    private ProductAdapter bodyAdapter;

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

        toolbar.setTitle(R.string.product_list);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initData() {

        ProductDb productDb = new ProductDb(ProductActivity.this);
        List<Product> lst = productDb.findAll();

        if (lst.size() == 0) {
            layoutNoData.setVisibility(View.VISIBLE);
            return;
        }

        bodyAdapter = new ProductAdapter(ProductActivity.this, lst);
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
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        SearchView searchView = super.searchViewConfig(menu);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                bodyAdapter.getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                bodyAdapter.getFilter().filter(query);
                return false;
            }
        });

        return true;
    }

}
