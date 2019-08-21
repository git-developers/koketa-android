package com.tianos.koketa.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;


public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> {

    private Context context;
    private LayoutInflater inflater;

    public ClientAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_client, parent, false);
        return new ClientViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ClientViewHolder holder, int position) {

        /*
        if (procedure.getTableDB().get(position) == null) {
            return;
        }

        RealmList<RealmString> datos = procedure.getTableDB().get(position).getDatos();
        */

        holder.tvBodyResumen1.setText("xxx");
        holder.tvBodyResumen2.setText("cccc");

    }

    @Override
    public int getItemCount() {
        return 0; // (procedure.getTableDB() != null) ? procedure.getTableDB().size() : 0;
    }

    public class ClientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

//        private Procedure procedure;
        private Context context;

        @BindView(R.id.table_tr_row)
        TableRow tableTrRow;

        @BindView(R.id.tv_body_resumen_1)
        TextView tvBodyResumen1;

        @BindView(R.id.tv_body_resumen_2)
        TextView tvBodyResumen2;

        public ClientViewHolder(View view, Context context) {
            super(view);
//            this.procedure = procedure;
            this.context = context;
            view.setOnClickListener(this);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View v) {

            Intent i = new Intent();

            context.startActivity(i);
        }
    }
}
