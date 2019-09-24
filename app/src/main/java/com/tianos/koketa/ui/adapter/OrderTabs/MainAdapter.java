package com.tianos.koketa.ui.adapter.OrderTabs;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MainAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public MainAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                ProductFragment a = new ProductFragment();
                return a;
            case 1:
                OrderFragment b = new OrderFragment();
                return b;
            case 2:
                SummaryFragment c = new SummaryFragment();
                return c;
            default:
                return null;
        }
    }
}
