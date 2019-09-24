package com.tianos.koketa.util.dialog;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.tianos.koketa.R;

public class DialogFragment {

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
