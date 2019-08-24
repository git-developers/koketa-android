package com.tianos.koketa.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tianos.koketa.R;
import com.tianos.koketa.ui.adapter.Order.MainAdapter;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa2;

import butterknife.BindView;


public class OrderTabsActivity extends BaseActivity implements InterfaceKoketa2 {

    @BindView(R.id.tab_layout_main)
    TabLayout tabLayoutMain;

    @BindView(R.id.view_pager_main)
    ViewPager viewPagerMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_tabs);

        initSetup();
        initToolBar();
        initData();
        navigationDrawer();
    }

    @Override
    public void initSetup() {
        super.initSetup();


    }

    @Override
    public void initToolBar() {
        super.initToolBar();

        toolbar.setTitle(R.string.order);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initData() {

        tabLayoutMain.addTab(tabLayoutMain.newTab().setCustomView(R.layout.layout_tab_article));
        tabLayoutMain.addTab(tabLayoutMain.newTab().setCustomView(R.layout.layout_tab_order));
        tabLayoutMain.addTab(tabLayoutMain.newTab().setCustomView(R.layout.layout_tab_summary));
        tabLayoutMain.setTabGravity(TabLayout.GRAVITY_FILL);

        MainAdapter adapter = new MainAdapter(getSupportFragmentManager(), tabLayoutMain.getTabCount());
        viewPagerMain.setAdapter(adapter);
        viewPagerMain.setOffscreenPageLimit(tabLayoutMain.getTabCount() - 1);
        viewPagerMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutMain));
        tabLayoutMain.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPagerMain.setCurrentItem(tab.getPosition());

                View tabView = tab.getCustomView();
                TextView textView = (TextView) tabView.findViewById(R.id.text);
                textView.setTypeface(null, Typeface.BOLD);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                View tabView = tab.getCustomView();
                TextView textView = (TextView) tabView.findViewById(R.id.text);
                textView.setTypeface(null, Typeface.NORMAL);
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_out_right);
    }
}
