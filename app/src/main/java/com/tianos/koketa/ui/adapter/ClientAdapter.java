package com.tianos.koketa.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.entity.User;
import com.tianos.koketa.ui.activity.ClientDetailActivity;
import com.tianos.koketa.util.Util;
import com.tianos.koketa.util.dialog.DialogFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> implements Filterable {

    private Context context;
    private LayoutInflater inflater;
    private List<User> lst;
    private List<User> lstFiltered;

    public ClientAdapter(Context context, List<User> lst) {
        this.context = context;
        this.lst = lst;
        this.lstFiltered = lst;
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

        User o = this.lstFiltered.get(position);

        holder.tvBusinessName.setText(o.getName());
        holder.tvRuc.setText(o.getRuc());
        holder.tvEmail.setText(o.getEmail());
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

                    List<User> filteredList = new ArrayList<User>();

                    for (User row : lst) {
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

                lstFiltered = (ArrayList<User>) filterResults.values;

                notifyDataSetChanged();

                if (lstFiltered.size() == 0) {
                    Util.showToast(context, "No se encontraron resultados.");
                }
            }
        };
    }

    public class ClientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;

        @BindView(R.id.ll_body)
        LinearLayout llBody;

        @BindView(R.id.ll_body_image)
        LinearLayout llBodyImage;

        @BindView(R.id.tv_business_name)
        TextView tvBusinessName;

        @BindView(R.id.tv_ruc)
        TextView tvRuc;

        @BindView(R.id.tv_email)
        TextView tvEmail;

        @BindView(R.id.iv_phone)
        ImageView ivPhone;

        @BindView(R.id.iv_email)
        ImageView ivEmail;

        public ClientViewHolder(View view, Context context) {
            super(view);
            this.context = context;
            ButterKnife.bind(this, view);

            llBody.setOnClickListener(this);
            llBodyImage.setOnClickListener(this);
            ivPhone.setOnClickListener(this);
            ivEmail.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (v.getId() == ivPhone.getId()) {

                DialogFragment.dialogClientPhone(context);

            } else if (v.getId() == llBody.getId() || v.getId() == llBodyImage.getId()) {

                Intent i = new Intent(this.context, ClientDetailActivity.class);
                this.context.startActivity(i);

            } else if (v.getId() == ivEmail.getId()) {

                Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "test@gmail.com"));
                i.putExtra(Intent.EXTRA_SUBJECT, "Test subject");
                i.putExtra(Intent.EXTRA_TEXT, "Test body");
                //emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, body); //If you are using HTML in your body text

                this.context.startActivity(Intent.createChooser(i, "Chooser Title"));
            }

        }
    }
}
