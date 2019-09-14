package com.tianos.koketa.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class BaseDb {

    protected SQLiteDatabase db;

    public BaseDb() {

    }

    public void databaseHelper(Context context) {
        DatabaseHelper helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void closeDatabase() {
        db.close();
    }
}
