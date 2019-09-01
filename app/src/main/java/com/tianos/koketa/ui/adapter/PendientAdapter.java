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
import com.tianos.koketa.entity.Pendient;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PendientAdapter extends RecyclerView.Adapter<PendientAdapter.PendientViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Pendient> lst;

    public PendientAdapter(Context context, List<Pendient> lst) {
        this.context = context;
        this.lst = lst;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PendientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_pendient, parent, false);
        return new PendientViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(PendientViewHolder holder, int position) {

        Pendient o = this.lst.get(position);

        holder.tvName.setText(o.getClient());
        holder.tvFechaRegistro.setText(o.getFechaRegistro());
        holder.tvFechaEnvio.setText(o.getFechaEnvio());
        holder.tvStatus.setText(o.getStatus());
    }

    @Override
    public int getItemCount() {
        return (this.lst != null) ? this.lst.size() : 0;
    }

    public class PendientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;

        @BindView(R.id.ll_body)
        LinearLayout llBody;

        @BindView(R.id.ll_body_image)
        LinearLayout llBodyImage;

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_status)
        TextView tvStatus;

        @BindView(R.id.tv_fecha_registro)
        TextView tvFechaRegistro;

        @BindView(R.id.tv_fecha_envio)
        TextView tvFechaEnvio;

        public PendientViewHolder(View view, Context context) {
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
