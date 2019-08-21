package com.tianos.koketa.util;

import android.app.ProgressDialog;
import android.content.Context;

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

}
