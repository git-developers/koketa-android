package com.tianos.koketa.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.database.UserDb;
import com.tianos.koketa.entity.User;
import com.tianos.koketa.ui.activity.ClientDetailActivity;
import com.tianos.koketa.ui.activity.OrderTabsActivity;
import com.tianos.koketa.ui.adapter.ContactAdapter;
import com.tianos.koketa.util.Constant;
import com.tianos.koketa.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClientDetailFragment extends DialogFragment {

    @BindView(R.id.rv_rows_contacts)
    RecyclerView rvRowsContacts;

    @BindView(R.id.textview_12)
    TextView tvName;

    @BindView(R.id.textview_14)
    TextView tvRuc;

    @BindView(R.id.textview_16)
    TextView tvEmail;

    @BindView(R.id.textview_18)
    TextView tvPhone;

    @BindView(R.id.textview_110)
    TextView tvAddress;

    @BindView(R.id.textview_112)
    TextView tvAboutMe;

    @BindView(R.id.textview_22)
    TextView tvCreditLine;

    @BindView(R.id.textview_24)
    TextView tvBalance;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle(getString(R.string.client_detail));
        View v = inflater.inflate(R.layout.fragment_client_detail, container, false);

        return v;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        UserDb userDb = new UserDb(getActivity());
        User user = userDb.findOneById(getArguments().getString(Constant.PRODUCT_ID));

        tvName.setText(user.getName());
        tvRuc.setText(user.getRuc());
        tvEmail.setText(user.getEmail());
        tvPhone.setText(user.getPhone());
        tvAddress.setText(user.getAddress());
        tvAboutMe.setText(user.getAboutMe());
        tvCreditLine.setText("SOL " + Util.money(user.getCreditLine()));
        tvBalance.setText("SOL " + Util.money(user.getBalance()));


        /**
         * CONTACTS
         */
        List<User> lst = new ArrayList<User>();

        User a = new User(1,"4EVER UNIFORMS S.A.C", "20270653929", "test-1@gmail.com");
        lst.add(a);

        User b = new User(2,"A TUS PIES E.I.R.L", "20536727524", "test-2@gmail.com");
        lst.add(b);

        ContactAdapter bodyAdapter = new ContactAdapter(getActivity(), lst);
        rvRowsContacts.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));
        rvRowsContacts.setAdapter(bodyAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public interface DialogListener {
        void onFinishEditDialog(String inputText);
    }

    @OnClick(R.id.fab_shopping_cart)
    public void btnFabShoppingCart() {

        Util.progressDialogShow(getActivity(), getString(R.string.in_progress));

        Intent i = new Intent(getActivity(), OrderTabsActivity.class);
        startActivity(i);
    }

}
