package com.tianos.koketa.ui.adapter.Order;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.tianos.koketa.R;
import com.tianos.koketa.ui.fragment.BaseFragment;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketaFragment;
import com.tianos.koketa.util.DatePickerUtil;

import butterknife.BindView;
import butterknife.ButterKnife;



public class SummaryFragment extends BaseFragment implements InterfaceKoketaFragment {

    @BindView(R.id.textview_16)
    Spinner spinner;

    @BindView(R.id.textview_18)
    EditText deliveryDate;

    private View viewInflate;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        initSetup(inflater, viewGroup, savedInstanceState);
        initData();
        spinnerPayment();
        deliveryDate();

        return viewInflate;
    }

    @Override
    public void initSetup(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        this.context = viewGroup.getContext();
        viewInflate = inflater.inflate(R.layout.tab_order_summary, viewGroup, false);

        ButterKnife.bind(this, viewInflate);
    }

    @Override
    public void initData() {

        return;
    }

    private void deliveryDate() {
        new DatePickerUtil(deliveryDate, this.context);
    }

    private void spinnerPayment() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this.context,
                R.array.array_payment,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}