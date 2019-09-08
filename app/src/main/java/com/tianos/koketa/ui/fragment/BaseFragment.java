package com.tianos.koketa.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tianos.koketa.R;

import butterknife.BindView;


public class BaseFragment extends Fragment {

    @Nullable
    @BindView(R.id.ll_main)
    public LinearLayout llMain;

    @Nullable
    @BindView(R.id.layout_no_data)
    public RelativeLayout layoutNoData;

//    public UserFactory userFactory;

    public BaseFragment() {
//        userFactory = getUserFactory();
    }

    public void showMainLinearLayout() {
        llMain.setVisibility(View.VISIBLE);
    }

    public void hideMainLinearLayout() {
        llMain.setVisibility(View.GONE);
    }

    public void showNoDataLayout() {
        layoutNoData.setVisibility(View.VISIBLE);
    }

    public void hideNoDataLayout() {
        layoutNoData.setVisibility(View.GONE);
    }

}