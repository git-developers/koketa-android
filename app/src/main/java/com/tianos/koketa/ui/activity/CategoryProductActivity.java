package com.tianos.koketa.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.database.CategoryDb;
import com.tianos.koketa.database.ProductDb;
import com.tianos.koketa.entity.Category;
import com.tianos.koketa.entity.Product;
import com.tianos.koketa.ui.adapter.CategoryProductAdapter;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa2;
import com.tianos.koketa.util.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CategoryProductActivity extends BaseActivity implements InterfaceKoketa2 {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.tv_category)
    TextView tvCategory;

    private String categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_product);

        initSetup();
        initToolBar();
        initData();
        navigationDrawer();
    }

    @Override
    public void initSetup() {
        super.initSetup();

        categoryId = getIntent().getStringExtra(Constant.CATEGORY_ID);

        recyclerView.setLayoutManager(new LinearLayoutManager(CategoryProductActivity.this, RecyclerView.VERTICAL,false));
    }

    @Override
    public void initToolBar() {
        super.initToolBar();

        toolbar.setTitle(R.string.category_product_list);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initData() {

        CategoryDb categoryDb = new CategoryDb(CategoryProductActivity.this);
        Category category = categoryDb.findOneById(categoryId);

        tvCategory.setText(category.getName());

        /**
         * PRODUCTS
         */
        ProductDb productDb = new ProductDb(CategoryProductActivity.this);
        List<Product> lst = productDb.findAllByCategory(categoryId);

        if (lst.size() == 0) {
            layoutNoData.setVisibility(View.VISIBLE);
            return;
        }

        CategoryProductAdapter bodyAdapter = new CategoryProductAdapter(CategoryProductActivity.this, lst);
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

}
