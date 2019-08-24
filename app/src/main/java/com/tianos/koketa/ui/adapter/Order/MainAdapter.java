package com.tianos.koketa.ui.adapter.Order;


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
                TotalFragment a = new TotalFragment();
                return a;
            case 1:
                ApuestaDeportivaFragment b = new ApuestaDeportivaFragment();
                return b;
            case 2:
                LoteriaFragment c = new LoteriaFragment();
                return c;
            default:
                return null;
        }
    }
}
