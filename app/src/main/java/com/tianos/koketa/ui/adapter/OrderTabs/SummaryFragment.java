package com.tianos.koketa.ui.adapter.OrderTabs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.tianos.koketa.R;
import com.tianos.koketa.database.BreadcrumbDb;
import com.tianos.koketa.database.OrderDb;
import com.tianos.koketa.entity.Breadcrumb;
import com.tianos.koketa.entity.Order;
import com.tianos.koketa.entity.OrderDetail;
import com.tianos.koketa.entity.ResponseWeb;
import com.tianos.koketa.retrofit.APIClient;
import com.tianos.koketa.retrofit.APIInterface;
import com.tianos.koketa.ui.activity.ClientActivity;
import com.tianos.koketa.ui.activity.ClientDetailActivity;
import com.tianos.koketa.ui.fragment.BaseFragment;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketaFragment;
import com.tianos.koketa.util.DatePickerUtil;
import com.tianos.koketa.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SummaryFragment extends BaseFragment implements InterfaceKoketaFragment {

    private static final String TAG = SummaryFragment.class.getName();

    @BindView(R.id.textview_16)
    Spinner spinner;

    @BindView(R.id.textview_18)
    EditText deliveryDate;

    @BindView(R.id.textview_12)
    TextView tvNumberOfProducts;

    @BindView(R.id.textview_110)
    TextView tvSubTotal;

    @BindView(R.id.textview_112)
    TextView tvDiscount;

    @BindView(R.id.textview_114)
    TextView tvTax;

    @BindView(R.id.textview_116)
    TextView tvTotal;

    @BindView(R.id.btn_register)
    Button btnRegister;

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
        dataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();

        dataSetChanged();
    }

    @OnClick(R.id.btn_register)
    public void btnRegister() {

        btnRegister.setEnabled(false);
        Util.progressDialogShow(getActivity(), getString(R.string.in_progress));

        OrderDb orderDb = new OrderDb(getActivity());
        RealmList<Order> lstOrder = orderDb.findAllPending();

        int i = 0;
        for (Order order : lstOrder) {
            order.setOrderDetail(orderDb.findAllOrderDetailByOrderId2(String.valueOf(order.getId())));
            lstOrder.set(i, order);
            i++;
        }

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        apiInterface.salesCreate(lstOrder).enqueue(new Callback<ResponseWeb>() {
            @Override
            public void onResponse(Call<ResponseWeb> call, Response<ResponseWeb> response) {

                try {

                    ResponseWeb responseWeb = response.body();

                    Util.progressDialogHide();
                    btnRegister.setEnabled(true);

                    if (response.code() != 200) {
                        throw new Exception(getString(R.string.error));
                    }

                    if (responseWeb.getStatus() != ResponseWeb.STATUS_SUCCESS) {
                        throw new Exception(responseWeb.getMessage());
                    }

                    Log.d(TAG, "***** CREATE *****");

                    Util.showToast(getActivity(), "Venta creada con exito.");


                    for (Order order : lstOrder) {
                        orderDb.updateStatus(String.valueOf(order.getId()), Order.STATUS_DONE);
                    }

                    Intent i = new Intent(getActivity(), ClientActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);


                } catch (Exception e) {
                    Util.showSnackbar(getActivity(), e.getMessage());
                    Log.d(TAG, "EXCEPTION::: " + e.getMessage());
                    return;
                }
            }

            @Override
            public void onFailure(Call<ResponseWeb> call, Throwable t) {
                call.cancel();
                btnRegister.setEnabled(true);
                Util.progressDialogHide();
                Util.showSnackbar(getActivity(), getString(R.string.error));
            }
        });
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

    private void dataSetChanged() {

        /**
         * BREADCRUMB
         */
        BreadcrumbDb breadcrumbDb = new BreadcrumbDb(getActivity());
        Breadcrumb breadcrumb = breadcrumbDb.findLast();


        /**
         * GET CLIENT CURRENT ORDER
         */
        OrderDb orderDb = new OrderDb(getActivity());
        List<OrderDetail> lst = orderDb.findAllOrderDetailByClient(breadcrumb);


        /**
         * TOTAL - SUBTOTAL
         */
        float subTotal = 0;
        for (OrderDetail orderDetail : lst) {
            subTotal = subTotal + orderDetail.getProduct().getPrice();
        }

        tvNumberOfProducts.setText(String.valueOf(lst.size()));
        tvSubTotal.setText("SOL " + Util.money(subTotal));
        tvTotal.setText("SOL " + Util.money(subTotal));
        tvTax.setText("SOL 0");
        tvDiscount.setText("SOL 0");
    }
}