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
import com.tianos.koketa.entity.Visit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VisitAdapter extends RecyclerView.Adapter<VisitAdapter.VisitViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Visit> lst;

    public VisitAdapter(Context context, List<Visit> lst) {
        this.context = context;
        this.lst = lst;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public VisitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_visit, parent, false);
        return new VisitViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(VisitViewHolder holder, int position) {

        Visit o = this.lst.get(position);

        holder.tvName.setText(o.getName());
        holder.tvId.setText(String.valueOf(o.getId()));
    }

    @Override
    public int getItemCount() {
        return (this.lst != null) ? this.lst.size() : 0;
    }

    public class VisitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;

        @BindView(R.id.ll_body)
        LinearLayout llBody;

        @BindView(R.id.ll_body_image)
        LinearLayout llBodyImage;

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_id)
        TextView tvId;

        public VisitViewHolder(View view, Context context) {
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
