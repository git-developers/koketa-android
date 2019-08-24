package com.tianos.koketa.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.entity.Client;
import com.tianos.koketa.util.dialog.DialogFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Client> lstClient;

    public ClientAdapter(Context context, List<Client> lstClient) {
        this.context = context;
        this.lstClient = lstClient;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_client, parent, false);
        return new ClientViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ClientAdapter.ClientViewHolder holder, int position) {

        Client cliente = this.lstClient.get(position);

        holder.tvBodyResumen1.setText(Integer.toString(cliente.getId()));
        holder.tvBodyResumen2.setText(cliente.getBusinessName());
    }

    @Override
    public int getItemCount() {
        return (this.lstClient != null) ? this.lstClient.size() : 0;
    }

    public class ClientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;

//        @BindView(R.id.table_tr_row)
//        TableRow tableTrRow;

        @BindView(R.id.tv_body_resumen_1)
        TextView tvBodyResumen1;

        @BindView(R.id.tv_body_resumen_2)
        TextView tvBodyResumen2;

        public ClientViewHolder(View view, Context context) {
            super(view);
            this.context = context;
            view.setOnClickListener(this);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View v) {

            DialogFragment.dialogClientPhone(context);

//            Intent i = new Intent();
//            context.startActivity(i);
        }
    }
}
