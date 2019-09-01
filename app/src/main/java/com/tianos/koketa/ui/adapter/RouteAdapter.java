package com.tianos.koketa.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.entity.Route;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Route> lst;

    public RouteAdapter(Context context, List<Route> lst) {
        this.context = context;
        this.lst = lst;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_route, parent, false);
        return new RouteViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(RouteViewHolder holder, int position) {

        Route o = this.lst.get(position);

        holder.tvName.setText(o.getName());
        holder.tvId.setText(String.valueOf(o.getId()));
    }

    @Override
    public int getItemCount() {
        return (this.lst != null) ? this.lst.size() : 0;
    }

    public class RouteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;

        @BindView(R.id.ll_body)
        LinearLayout llBody;

        @BindView(R.id.ll_body_image)
        LinearLayout llBodyImage;

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_id)
        TextView tvId;

        public RouteViewHolder(View view, Context context) {
            super(view);
            this.context = context;
            ButterKnife.bind(this, view);

            llBody.setOnClickListener(this);
            llBodyImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {



        }
    }
}
