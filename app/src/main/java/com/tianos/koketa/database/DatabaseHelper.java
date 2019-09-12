package com.tianos.koketa.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tianos.koketa.entity.Breadcrumb;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "koketa";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creating Tables
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Breadcrumb.CREATE_TABLE);
    }

    /**
     * Upgrading database
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Breadcrumb.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }
}
