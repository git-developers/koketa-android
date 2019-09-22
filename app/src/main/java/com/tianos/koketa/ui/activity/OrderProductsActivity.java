package com.tianos.koketa.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.database.BreadcrumbDb;
import com.tianos.koketa.database.CategoryDb;
import com.tianos.koketa.database.OrderDb;
import com.tianos.koketa.database.ProductDb;
import com.tianos.koketa.entity.Breadcrumb;
import com.tianos.koketa.entity.Category;
import com.tianos.koketa.entity.Order;
import com.tianos.koketa.entity.Product;
import com.tianos.koketa.ui.adapter.OrderProductsAdapter;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa2;
import com.tianos.koketa.util.Constant;

import java.util.List;

import butterknife.BindView;

public class OrderProductsActivity extends BaseActivity implements InterfaceKoketa2 {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.tv_category)
    TextView tvCategory;

    private String categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_products);

        initSetup();
        initToolBar();
        initData();
        navigationDrawer();
    }

    @Override
    public void initSetup() {
        super.initSetup();

        categoryId = getIntent().getStringExtra(Constant.CATEGORY_ID);

        recyclerView.setLayoutManager(new LinearLayoutManager(OrderProductsActivity.this, RecyclerView.VERTICAL,false));
    }

    @Override
    public void initToolBar() {
        super.initToolBar();

        toolbar.setTitle(R.string.category_product_list);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initData() {

        CategoryDb categoryDb = new CategoryDb(OrderProductsActivity.this);
        Category category = categoryDb.findOneById(categoryId);
        tvCategory.setText(category.getName());

        /**
         * PRODUCTS
         */
        ProductDb productDb = new ProductDb(OrderProductsActivity.this);
        List<Product> lst = productDb.findAllByCategory(categoryId);

        if (lst.size() == 0) {
            layoutNoData.setVisibility(View.VISIBLE);
            return;
        }

        OrderProductsAdapter bodyAdapter = new OrderProductsAdapter(OrderProductsActivity.this, lst);
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
