package com.tianos.koketa.util;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;

public class DatePickerUtil implements View.OnFocusChangeListener, View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private EditText editText;
    private Calendar myCalendar;
    private Context context;

    public DatePickerUtil(EditText editText, Context context){
        this.context = context;
        this.editText = editText;
        this.myCalendar = Calendar.getInstance();
        this.editText.setOnFocusChangeListener(this);
        this.editText.setOnClickListener(this);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

        String format = "MMM dd, yyyy"; //In which you need put here
        SimpleDateFormat sdformat = new SimpleDateFormat(format, Locale.US);

        this.myCalendar.set(Calendar.YEAR, year);
        this.myCalendar.set(Calendar.MONTH, monthOfYear);
        this.myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        editText.setText(sdformat.format(this.myCalendar.getTime()));
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if(hasFocus){

        }
    }

    @Override
    public void onClick(View view) {
        new DatePickerDialog(
                this.context,
                this,
                this.myCalendar.get(Calendar.YEAR),
                this.myCalendar.get(Calendar.MONTH),
                this.myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }
}
