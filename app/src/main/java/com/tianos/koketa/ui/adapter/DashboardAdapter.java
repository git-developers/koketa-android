package com.tianos.koketa.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.entity.Dashboard;
import com.tianos.koketa.ui.activity.DashboardActivity;
import com.tianos.koketa.util.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder> {

    private Context context;
    private List<?> data;
    private LayoutInflater inflater;

    public DashboardAdapter(Context context, List<?> data) {
        this.data = data;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public DashboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_dashboard, parent, false);
        return new DashboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DashboardViewHolder holder, int position) {

        final Dashboard d = (Dashboard) data.get(position);

        if (d == null) {
            return;
        }

        holder.tvName.setText(d.getName());
        holder.ivIcon.setImageResource(d.getIcon());
        holder.ivIcon.setColorFilter(context.getResources().getColor(d.getTint()));
        holder.llItem.setTag(d.getTag());
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (d.getId()) {
                    case Constant.DASH_PROFILE:
                        ((DashboardActivity) context).openProfile(v);
                        break;
                    case Constant.DASH_CLIENTS:
                        ((DashboardActivity) context).openClient(v);
                        break;
                    case Constant.DASH_PRODUCTS:
                        ((DashboardActivity) context).openProduct(v);
                        break;
                    case Constant.DASH_ROUTES:
                        ((DashboardActivity) context).openRoute(v);
                        break;
                    case Constant.DASH_VISITS:
                        ((DashboardActivity) context).openVisit(v);
                        break;
                    case Constant.DASH_PENDIENTS:
                        ((DashboardActivity) context).openPendient(v);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public class DashboardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_item)
        LinearLayout llItem;

        @BindView(R.id.iv_icon)
        ImageView ivIcon;

        @BindView(R.id.tv_name)
        TextView tvName;

        public DashboardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
