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
import com.tianos.koketa.entity.Client;
import com.tianos.koketa.ui.activity.ClientActivity;
import com.tianos.koketa.ui.activity.ClientDetailActivity;
import com.tianos.koketa.ui.activity.DashboardActivity;
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

        @BindView(R.id.ll_client)
        LinearLayout llClient;

        @BindView(R.id.tv_body_resumen_1)
        TextView tvBodyResumen1;

        @BindView(R.id.tv_body_resumen_2)
        TextView tvBodyResumen2;

        @BindView(R.id.iv_phone)
        ImageView ivPhone;

        @BindView(R.id.iv_email)
        ImageView ivEmail;

        public ClientViewHolder(View view, Context context) {
            super(view);
            this.context = context;
            ButterKnife.bind(this, view);

            llClient.setOnClickListener(this);
            ivPhone.setOnClickListener(this);
            ivEmail.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (v.getId() == ivPhone.getId()) {

                DialogFragment.dialogClientPhone(context);

            } else if (v.getId() == llClient.getId()) {

                Intent i = new Intent(this.context, ClientDetailActivity.class);
                this.context.startActivity(i);

            } else if (v.getId() == ivEmail.getId()) {

                Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "test@gmail.com"));
                i.putExtra(Intent.EXTRA_SUBJECT, "Test subject");
                i.putExtra(Intent.EXTRA_TEXT, "Test body");
                //emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, body); //If you are using HTML in your body text

                this.context.startActivity(Intent.createChooser(i, "Chooser Title"));

            }



//            Intent i = new Intent();
//            context.startActivity(i);
        }
    }
}
