package com.tianos.koketa.util.dialog;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tianos.koketa.R;
import com.tianos.koketa.ui.activity.LoginActivity;
import com.tianos.koketa.util.Util;

public class DialogFragment {


    public static void dialogLicense(Context context) {

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
        View view = inflater.inflate(R.layout.dialog_license, null);
        builder.setView(view);

        //BUTTONS
        Button accept = (Button) view.findViewById(R.id.accept);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("POLLO", "eeee");
                return;
            }
        });

        /*
        builder.setView(view)
            .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    // sign in the user ...
                }
            })
            .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
//                        LoginDialogFragment.this.getDialog().cancel();
                }
            });
        */

        if (!((Activity) context).isFinishing()) {
            builder.show();
        }
    }

    public static void dialogImei(Context context) {

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
        builder.setView(view);
        final AlertDialog dialog = builder.create();

        TextView tvImei = (TextView) view.findViewById(R.id.tv_imei);
        String imei = Util.getImei(activity);
        tvImei.setText(imei);


        //BUTTONS
        Button accept = (Button) view.findViewById(R.id.accept);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("POLLO", "XXXXXXXXXXXXXX");

                dialog.cancel();
                return;
            }
        });

        /*
        builder.setView(view)
            .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    return;
                }
            })
            .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //LoginDialogFragment.this.getDialog().cancel();
                }
            })
        ;
        */

//        final AlertDialog alertDialog = builder.create();
//        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
//            @Override
//            public void onShow(DialogInterface dialogInterface) {
//                Log.d("POLLO", "Doung");
//                Button button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
//
//                button.setTextColor(Color.MAGENTA);
//                button.setBackgroundColor(context.getResources().getColor(R.color.danger));
//            }
//        });


        if (!((Activity) context).isFinishing()) {
            builder.show();
        }
    }

    public static void dialogChangePassword(Context context) {

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
        builder.setView(view);

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
            .setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                @SuppressLint("MissingPermission")
                @Override
                public void onClick(DialogInterface dialog, int id) {

                    Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "999888777"));
                    activity.startActivity(i);
                }
            })
//            .setNegativeButton(R.string.dummy, new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int id) {
//
//                    return;
//                }
//            })
        ;

//        return builder.create();

        if (!((Activity) context).isFinishing()) {
            builder.show();
        }
    }



}
