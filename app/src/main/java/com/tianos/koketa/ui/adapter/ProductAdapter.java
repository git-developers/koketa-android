package com.tianos.koketa.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> implements Filterable {

    private Context context;
    private LayoutInflater inflater;
    private List<Product> lst;
    private List<Product> lstFiltered;

    public ProductAdapter(Context context, List<Product> lst) {
        this.context = context;
        this.lst = lst;
        this.lstFiltered = lst;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_product, parent, false);
        return new ProductViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        Product o = this.lstFiltered.get(position);

        holder.tvName.setText(o.getName());
        holder.tvFamily.setText(o.getFamily());
        holder.tvStock.setText(Integer.toString(o.getStock()) + " Unidades");
//        holder.tvPrice.setText("SOL " + Double.toString(o.getPrice()));
        holder.tvPrice.setText("SOL " + Util.price(o.getPrice()));
    }

    @Override
    public int getItemCount() {
        return (this.lstFiltered != null) ? this.lstFiltered.size() : 0;
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                if (Util.charIsEmpty(charSequence)) {
                    lstFiltered = lst;
                } else {

                    List<Product> filteredList = new ArrayList<Product>();

                    for (Product row : lst) {
                        if (Util.charContains(row.getName(), charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    lstFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = lstFiltered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                lstFiltered = (ArrayList<Product>) filterResults.values;

                notifyDataSetChanged();

                if (lstFiltered.size() == 0) {
                    Util.showToast(context, "No se encontraron resultados.");
                }
            }
        };
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

            if (v.getId() != llBody.getId()) {
                return;
            }

            int position = getAdapterPosition();
            Product product = lst.get(position);

            Bundle args = new Bundle();
            args.putString("product_id", String.valueOf(product.getId()));

            ProductDetailFragment fragment = new ProductDetailFragment();
            fragment.setArguments(args);
            FragmentTransaction ft = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
            fragment.show(ft, "dialog");
        }
    }
}
