package com.tianos.koketa.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.entity.Category;
import com.tianos.koketa.entity.Product;
import com.tianos.koketa.entity.User;
import com.tianos.koketa.ui.activity.CategoryProductActivity;
import com.tianos.koketa.ui.fragment.ProductDetailFragment;
import com.tianos.koketa.util.Constant;
import com.tianos.koketa.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> implements Filterable {

    private Context context;
    private LayoutInflater inflater;
    private List<Category> lst;
    private List<Category> lstFiltered;

    public CategoryAdapter(Context context, List<Category> lst) {
        this.context = context;
        this.lst = lst;
        this.lstFiltered = lst;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_category, parent, false);
        return new CategoryViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {

        Category o = this.lstFiltered.get(position);

        holder.tvName.setText(o.getName());
        holder.tvStock.setText(String.valueOf(o.getStock()) + " stock");
        holder.tvCode.setText(o.getCode());
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

                    List<Category> filteredList = new ArrayList<>();

                    for (Category row : lst) {
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

                lstFiltered = (ArrayList<Category>) filterResults.values;

                notifyDataSetChanged();

                if (lstFiltered.size() == 0) {
                    Util.showToast(context, "No se encontraron resultados.");
                }
            }
        };
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;

        @BindView(R.id.rl_body)
        RelativeLayout rlBody;

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_stock)
        TextView tvStock;

        @BindView(R.id.tv_code)
        TextView tvCode;

        public CategoryViewHolder(View view, Context context) {
            super(view);
            this.context = context;
            ButterKnife.bind(this, view);

            rlBody.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (v.getId() == rlBody.getId()) {

                int position = getAdapterPosition();
                Category category = lst.get(position);

                Intent i = new Intent(this.context, CategoryProductActivity.class);
                i.putExtra(Constant.CATEGORY_ID, String.valueOf(category.getId()));
                this.context.startActivity(i);
            }

        }
    }
}
