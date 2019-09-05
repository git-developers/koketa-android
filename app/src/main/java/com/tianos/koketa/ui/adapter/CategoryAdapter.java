package com.tianos.koketa.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.entity.Category;
import com.tianos.koketa.ui.activity.ClientDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Category> lst;

    public CategoryAdapter(Context context, List<Category> lst) {
        this.context = context;
        this.lst = lst;
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

        Category o = this.lst.get(position);

        holder.tvName.setText(o.getName());
        holder.tvStock.setText(o.getStock());
        holder.tvCode.setText(o.getCode());
    }

    @Override
    public int getItemCount() {
        return (this.lst != null) ? this.lst.size() : 0;
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

                Intent i = new Intent(this.context, ClientDetailActivity.class);
                this.context.startActivity(i);
            }

        }
    }
}
