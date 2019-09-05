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
import com.tianos.koketa.ui.activity.BaseActivity;

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

    public static String versionName(Context context) {

        Activity activity = (Activity) context;

        PackageManager packageManager = activity.getPackageManager();
        String packageName = activity.getPackageName();

        String out = activity.getString(R.string.not_available);

        try {
            out = packageManager.getPackageInfo(packageName, 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return out;
    }

    public static String versionCode(Context context) {

        Activity activity = (Activity) context;

        PackageManager packageManager = activity.getPackageManager();
        String packageName = activity.getPackageName();

        String out = activity.getString(R.string.not_available);

        try {
            long versionCodeLong = packageManager.getPackageInfo(packageName, 0).versionCode;
            out = Long.toString(versionCodeLong);
        } catch (NoSuchMethodError e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return out;
    }

}
