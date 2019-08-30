package com.tianos.koketa.util;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.view.View;
import android.widget.Toast;
import android.app.AlertDialog.Builder;
import androidx.core.app.ActivityCompat;
import android.content.DialogInterface;

import com.tianos.koketa.R;

public class Util {

    private static ProgressDialog progressRetrofit;

    public final static void progressDialogShow(Context context, String substring) {
        progressRetrofit = new ProgressDialog(context, R.style.progressRetrofit);
        progressRetrofit.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressRetrofit.setCanceledOnTouchOutside(false);
        progressRetrofit.setCancelable(false);
        progressRetrofit.setMessage(substring);
        progressRetrofit.show();
    }

    public static String getImei(Activity activity) {
        String imei = Settings.Secure.getString(activity.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            TelephonyManager mTelephony = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);

            if (mTelephony.getDeviceId() != null) {
                imei = mTelephony.getDeviceId();
            }
        }

        return imei;
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showDialog(Context context, String message, String btnMessage, DialogInterface.OnClickListener listener) {

        Builder builder = new Builder(context);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.app_name);
        builder.setMessage(Html.fromHtml(message));
        builder.setCancelable(false);

        btnMessage = btnMessage.isEmpty() ? context.getString(R.string.accept) : btnMessage;

        if (listener == null) {
            builder.setPositiveButton(btnMessage, new android.content.DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialoginterface, int i) {

                }
            });

        }

        if (!((Activity) context).isFinishing()) {
            builder.show();
        }
    }

//    @Override
//    public DialogFragment onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        // Get the layout inflater
//        LayoutInflater inflater = requireActivity().getLayoutInflater();
//
//        // Inflate and set the layout for the dialog
//        // Pass null as the parent view because its going in the dialog layout
//        builder.setView(inflater.inflate(R.layout.dialog_iemi, null))
//                // Add action buttons
//                .setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        // sign in the user ...
//                    }
//                })
//                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        LoginDialogFragment.this.getDialog().cancel();
//                    }
//                });
//        return builder.create();
//    }


}
