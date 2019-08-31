package com.tianos.koketa.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.entity.Product;
import com.tianos.koketa.ui.activity.ClientDetailActivity;
import com.tianos.koketa.ui.activity.ProductDetailActivity;
import com.tianos.koketa.util.dialog.DialogFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ClientViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Product> lst;

    public ProductAdapter(Context context, List<Product> lst) {
        this.context = context;
        this.lst = lst;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_product, parent, false);
        return new ClientViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ClientViewHolder holder, int position) {

        Product client = this.lst.get(position);

        holder.tvName.setText(client.getName());
        holder.tvFamily.setText(client.getFamily());
        holder.tvStock.setText(Integer.toString(client.getStock()) + " Unidades");
        holder.tvPrice.setText("SOL " + Double.toString(client.getPrice()));
    }

    @Override
    public int getItemCount() {
        return (this.lst != null) ? this.lst.size() : 0;
    }

    public class ClientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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


        public ClientViewHolder(View view, Context context) {
            super(view);
            this.context = context;
            ButterKnife.bind(this, view);

            llBody.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (v.getId() == llBody.getId()) {
                Intent i = new Intent(this.context, ProductDetailActivity.class);
                this.context.startActivity(i);
            }

        }
    }
}
