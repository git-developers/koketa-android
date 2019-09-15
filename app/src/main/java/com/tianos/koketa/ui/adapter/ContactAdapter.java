package com.tianos.koketa.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.entity.User;
import com.tianos.koketa.util.dialog.DialogFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<User> lst;

    public ContactAdapter(Context context, List<User> lst) {
        this.context = context;
        this.lst = lst;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_contact, parent, false);
        return new ContactViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {

        User o = this.lst.get(position);

        holder.tvBusinessName.setText(o.getName());
        holder.tvRuc.setText(o.getRuc());
        holder.tvEmail.setText(o.getEmail());
    }

    @Override
    public int getItemCount() {
        return (this.lst != null) ? this.lst.size() : 0;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

        public ContactViewHolder(View view, Context context) {
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

            }

        }
    }
}
