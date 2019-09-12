package com.tianos.koketa.ui.activity;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.entity.Category;
import com.tianos.koketa.ui.adapter.CategoryAdapter;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CategoryActivity extends BaseActivity implements InterfaceKoketa2 {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private CategoryAdapter bodyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        initSetup();
        initToolBar();
        initData();
        navigationDrawer();
    }

    @Override
    public void initSetup() {
        super.initSetup();

        recyclerView.setLayoutManager(new GridLayoutManager(CategoryActivity.this, 2));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void initToolBar() {
        super.initToolBar();

        toolbar.setTitle(R.string.category_list);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initData() {

        List<Category> lst = new ArrayList<Category>();

        Category a = new Category(1,"L01", "Calcetines", 56);
        lst.add(a);

        Category b = new Category(2,"L02", "Fajas Latex", 92);
        lst.add(b);

        Category c = new Category(3,"L03", "Fajas Lin Intel", 60);
        lst.add(c);

        Category d = new Category(4,"L04", "Lingerie", 240);
        lst.add(d);

        Category e = new Category(5,"L05", "Ropa de Baño PV19", 32);
        lst.add(e);

        Category f = new Category(6,"L06", "Media Pantalon", 20);
        lst.add(f);

        Category g = new Category(7,"L07", "SeamLess Maternal", 56);
        lst.add(g);

        Category h = new Category(8,"L08", "Sport", 62);
        lst.add(h);

        bodyAdapter = new CategoryAdapter(CategoryActivity.this, lst);
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
