package com.tianos.koketa.util.dialog;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tianos.koketa.R;
import com.tianos.koketa.ui.activity.LoginActivity;
import com.tianos.koketa.util.Util;

public class DialogFragment {


    public static void dialog1(Context context) {

        Activity activity = (Activity) context;

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//        builder.setIcon(R.mipmap.ic_launcher);
//        builder.setTitle(R.string.app_name);
//        builder.setMessage(Html.fromHtml(message));
        builder.setCancelable(true);

        // Get the layout inflater
        LayoutInflater inflater = activity.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.dialog_iemi, null);
        TextView tvImei = (TextView) view.findViewById(R.id.tv_imei);

        String imei = Util.getImei(activity);

        tvImei.setText(imei);

        builder.setView(view)
            // Add action buttons
            .setPositiveButton(R.string.dummy, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    // sign in the user ...
                }
            })
            .setNegativeButton(R.string.dummy, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
//                        LoginDialogFragment.this.getDialog().cancel();
                }
            });

//        return builder.create();

        if (!((Activity) context).isFinishing()) {
            builder.show();
        }
    }

    public static void dialog2(Context context) {

        Activity activity = (Activity) context;

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//        builder.setIcon(R.mipmap.ic_launcher);
//        builder.setTitle(R.string.app_name);
//        builder.setMessage(Html.fromHtml(message));
        builder.setCancelable(true);

        // Get the layout inflater
        LayoutInflater inflater = activity.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.dialog_change_password, null);

        builder.setView(view)
            // Add action buttons
            .setPositiveButton(R.string.dummy, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    // sign in the user ...
                }
            })
            .setNegativeButton(R.string.dummy, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
//                        LoginDialogFragment.this.getDialog().cancel();
                }
            });

//        return builder.create();

        if (!((Activity) context).isFinishing()) {
            builder.show();
        }
    }

    public static void dialogClientPhone(Context context) {

        Activity activity = (Activity) context;

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//        builder.setIcon(R.mipmap.ic_launcher);
//        builder.setTitle(R.string.app_name);
//        builder.setMessage(Html.fromHtml(message));
        builder.setCancelable(true);

        // Get the layout inflater
        LayoutInflater inflater = activity.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.dialog_client_phone, null);

        builder.setView(view)
            // Add action buttons
            .setPositiveButton(R.string.dummy, new DialogInterface.OnClickListener() {
                @SuppressLint("MissingPermission")
                @Override
                public void onClick(DialogInterface dialog, int id) {

                    Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "999888777"));
                    activity.startActivity(i);
                }
            })
            .setNegativeButton(R.string.dummy, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "test@gmail.com"));
                    i.putExtra(Intent.EXTRA_SUBJECT, "Test subject");
                    i.putExtra(Intent.EXTRA_TEXT, "Test body");
                    //emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, body); //If you are using HTML in your body text

                    activity.startActivity(Intent.createChooser(i, "Chooser Title"));
                }
            });

//        return builder.create();

        if (!((Activity) context).isFinishing()) {
            builder.show();
        }
    }



}
