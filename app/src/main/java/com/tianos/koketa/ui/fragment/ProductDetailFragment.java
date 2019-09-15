package com.tianos.koketa.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.tianos.koketa.R;
import com.tianos.koketa.database.ProductDb;
import com.tianos.koketa.entity.Product;
import com.tianos.koketa.util.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailFragment extends DialogFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_stock)
    TextView tvStock;

    @BindView(R.id.tv_price)
    TextView tvPrice;

    @BindView(R.id.textview_2)
    TextView tvCode;

    @BindView(R.id.textview_4)
    TextView tvFamily;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle(getString(R.string.product_detail));
        View v = inflater.inflate(R.layout.fragment_product_detail, container, false);

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

        ProductDb productDb = new ProductDb(getActivity());
        Product product = productDb.findOneById(getArguments().getString(Constant.PRODUCT_ID));

        tvTitle.setText(product.getName());
        tvStock.setText(product.getStock() + " Unidades");
        tvPrice.setText("SOL " + product.getPrice());
        tvCode.setText(product.getCode());
        tvFamily.setText(product.getFamily());

        /*
        final EditText editText = view.findViewById(R.id.inEmail);

        if (getArguments() != null && !TextUtils.isEmpty(getArguments().getEtString("email")))
            editText.setText(getArguments().getEtString("email"));

        Button btnDone = view.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogListener dialogListener = (DialogListener) getActivity();
                dialogListener.onFinishEditDialog(editText.getText().toString());
                dismiss();
            }
        });
        */
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

}
