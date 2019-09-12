package com.tianos.koketa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tianos.koketa.entity.Breadcrumb;


public class BreadcrumbDb {

    private SQLiteDatabase db;

    public BreadcrumbDb(Context context) {
        DatabaseHelper helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert(Breadcrumb breadcrumb) {

        ContentValues values = new ContentValues();
        values.put(Breadcrumb.COLUMN_USERNAME, breadcrumb.getUsername());

        db.insert(Breadcrumb.TABLE_NAME, null, values);
    }

    public Breadcrumb findLast() {

        Breadcrumb breadcrumb = new Breadcrumb();

        Cursor cursor = db.query(
            Breadcrumb.TABLE_NAME,
            new String[] {
                Breadcrumb.COLUMN_ID,
                Breadcrumb.COLUMN_USERNAME,
                Breadcrumb.COLUMN_TIMESTAMP
            },
            null,
            new String[]{}, null, null, Breadcrumb.COLUMN_ID + " DESC", "1");

        if (cursor == null || cursor.getCount() <= 0) {
            return breadcrumb;
        }

        cursor.moveToFirst();

        // prepare object
        breadcrumb = new Breadcrumb(
            cursor.getInt(cursor.getColumnIndex(Breadcrumb.COLUMN_ID)),
            cursor.getString(cursor.getColumnIndex(Breadcrumb.COLUMN_USERNAME))
        );

        // close the db connection
        cursor.close();

        return breadcrumb;
    }

    public void delete() {
        db.delete(Breadcrumb.TABLE_NAME,null, new String[]{});
        db.close();
    }

}
