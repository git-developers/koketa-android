package com.tianos.koketa.util.dialog;


import android.Manifest;
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

import androidx.core.app.ActivityCompat;

import com.tianos.koketa.R;
import com.tianos.koketa.ui.activity.LoginActivity;
import com.tianos.koketa.util.Util;

public class DialogFragment {


    public static void dialogAbout(Context context) {

        Activity activity = (Activity) context;

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(true);

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_about, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();

        TextView tvVersionCode = (TextView) view.findViewById(R.id.tv_version_code);
        TextView tvVersionName = (TextView) view.findViewById(R.id.tv_version_name);
        tvVersionCode.setText("Version code: " + Util.versionCode(activity));
        tvVersionName.setText("Version name: " + Util.versionName(activity));

        //BUTTONS
        Button accept = (Button) view.findViewById(R.id.close);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("POLLO", "dialogAbout");

                dialog.cancel();
                return;
            }
        });

        if (!((Activity) context).isFinishing()) {
            builder.show();
        }
    }

    public static void dialogPermission(Context context) {

        Activity activity = (Activity) context;

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(true);


//        AlertDialog xx = builder.create();

        // Get the layout inflater
        LayoutInflater inflater = activity.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.dialog_permission, null);
        builder.setView(view);

        //BUTTONS
        Button allow = (Button) view.findViewById(R.id.allow);
        allow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("POLLO", "allow");

                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);

                return;
            }
        });

        if (!((Activity) context).isFinishing()) {
            builder.show();
        }
    }

    public static void dialogLicense(Context context) {

        Activity activity = (Activity) context;

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
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
        tvImei.setText(Util.getImei(activity));


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
        builder.setCancelable(true);

        // Get the layout inflater
        LayoutInflater inflater = activity.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.dialog_client_phone, null);
        builder.setView(view);

        //BUTTONS
        Button call = (Button) view.findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {

                Log.d("POLLO", "CALL");

                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "999888777"));
                activity.startActivity(i);
            }
        });

        if (!((Activity) context).isFinishing()) {
            builder.show();
        }
    }

}
