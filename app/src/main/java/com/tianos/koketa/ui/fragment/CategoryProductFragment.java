package com.tianos.koketa.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.tianos.koketa.R;
import com.tianos.koketa.database.BreadcrumbDb;
import com.tianos.koketa.database.OrderDb;
import com.tianos.koketa.database.ProductDb;
import com.tianos.koketa.entity.Breadcrumb;
import com.tianos.koketa.entity.Order;
import com.tianos.koketa.entity.Product;
import com.tianos.koketa.entity.User;
import com.tianos.koketa.ui.activity.LoginActivity;
import com.tianos.koketa.util.Constant;
import com.tianos.koketa.util.PreferencesManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryProductFragment extends DialogFragment {

    private static final String TAG = CategoryProductFragment.class.getName();

    @BindView(R.id.tv_product_name)
    TextView tvProductName;

    @BindView(R.id.tr_stock_1)
    TableRow trStock1;

    @BindView(R.id.tr_stock_2)
    TableRow trStock2;

    private Product product;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().setTitle(R.string.add_product);
        View v = inflater.inflate(R.layout.fragment_category_product, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        ProductDb productDb = new ProductDb(getActivity());
        product = productDb.findOneById(getArguments().getString(Constant.PRODUCT_ID));

        tvProductName.setText(product.getName());
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public interface DialogListener {
        void onFinishEditDialog(String inputText);
    }

    @OnClick(R.id.btn_add)
    public void btnAdd() {

        dismiss();


        /**
         * ROW 1
         */
        TextView v11 = (TextView) trStock1.getChildAt(0);
        String val1 = v11.getText().toString();

        TextView v12 = (TextView) trStock1.getChildAt(1);
        String val2 = v12.getText().toString();

        TextView v13 = (TextView) trStock1.getChildAt(2);
        String val3 = v13.getText().toString();

        TextView v14 = (TextView) trStock1.getChildAt(3);
        String val4 = v14.getText().toString();


        /**
         * ROW 2
         */
        TextView v21 = (TextView) trStock1.getChildAt(0);
        String val21 = v21.getText().toString();

        TextView v22 = (TextView) trStock1.getChildAt(1);
        String val22 = v22.getText().toString();

        TextView v23 = (TextView) trStock1.getChildAt(2);
        String val23 = v23.getText().toString();

        TextView v24 = (TextView) trStock1.getChildAt(3);
        String val24 = v24.getText().toString();


//        User user = PreferencesManager.getInstance(getActivity()).realmGetUser();

        BreadcrumbDb breadcrumbDb = new BreadcrumbDb(getActivity());
        Breadcrumb breadcrumb = breadcrumbDb.findLast();

        Order order = new Order();
        order.setUsername(breadcrumb.getUsername());
        order.setClientId(breadcrumb.getClientId());
        order.setProductId(product.getId());
        order.setStatus(Order.STATUS_PENDING);
        order.setProductStock(5);

        OrderDb orderDb = new OrderDb(getActivity());
        orderDb.insert(order);
    }
}
