package com.tianos.koketa.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.entity.Product;
import com.tianos.koketa.ui.fragment.ProductDetailFragment;
import com.tianos.koketa.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductPendingAdapter extends RecyclerView.Adapter<ProductPendingAdapter.ProductViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Product> lst;

    public ProductPendingAdapter(Context context, List<Product> lst) {
        this.context = context;
        this.lst = lst;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_product_pending, parent, false);
        return new ProductViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        Product o = this.lst.get(position);

        holder.tvName.setText(o.getName());
        holder.tvFamily.setText(o.getFamily());
        holder.tvStock.setText(Integer.toString(o.getStock()) + " Unidades");
        holder.tvPrice.setText("SOL " + Util.money(o.getPrice()));
    }

    @Override
    public int getItemCount() {
        return (this.lst != null) ? this.lst.size() : 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;

        @BindView(R.id.ll_body)
        LinearLayout llBody;

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_family)
        TextView tvFamily;

        @BindView(R.id.tv_stock)
        TextView tvStock;

        @BindView(R.id.tv_price)
        TextView tvPrice;


        public ProductViewHolder(View view, Context context) {
            super(view);
            this.context = context;
            ButterKnife.bind(this, view);

            llBody.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (v.getId() == llBody.getId()) {

                ProductDetailFragment dialogFragment = new ProductDetailFragment();
                FragmentTransaction ft = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                dialogFragment.show(ft, "dialog");
            }

        }
    }
}
