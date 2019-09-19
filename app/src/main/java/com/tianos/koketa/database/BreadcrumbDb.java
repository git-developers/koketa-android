package com.tianos.koketa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.tianos.koketa.entity.Breadcrumb;
import com.tianos.koketa.entity.User;


public class BreadcrumbDb extends BaseDb {

    private static final String TAG = BreadcrumbDb.class.getName();

    public BreadcrumbDb(Context context) {
        super.databaseHelper(context);
    }

    public void insert(Breadcrumb breadcrumb) {

        ContentValues values = new ContentValues();
        values.put(Breadcrumb.COLUMN_USERNAME, breadcrumb.getUsername());
        values.put(Breadcrumb.COLUMN_CLIENT_USERNAME, breadcrumb.getClientUsername());

        db.insert(Breadcrumb.TABLE_NAME, null, values);
    }

    public void insertClient(Breadcrumb breadcrumb) {

        ContentValues values = new ContentValues();
        values.put(Breadcrumb.COLUMN_USERNAME, breadcrumb.getUsername());
        values.put(Breadcrumb.COLUMN_CLIENT_ID, breadcrumb.getClientId());
        values.put(Breadcrumb.COLUMN_CLIENT_USERNAME, breadcrumb.getClientUsername());

        db.insert(Breadcrumb.TABLE_NAME, null, values);
    }

    public Breadcrumb findLast() {

        Cursor cursor = null;
        Breadcrumb breadcrumb = new Breadcrumb();

        try {

            cursor = db.rawQuery("SELECT t1.* " +
                    "FROM " + Breadcrumb.TABLE_NAME + " AS t1 " +
                    "ORDER BY t1." + Breadcrumb.COLUMN_ID_INCR + " DESC LIMIT 1", new String[]{});

            if (cursor.moveToFirst()) {
                do {

                    breadcrumb.setId(cursor.getInt(cursor.getColumnIndex(Breadcrumb.COLUMN_ID_INCR)));
                    breadcrumb.setUsername(cursor.getString(cursor.getColumnIndex(Breadcrumb.COLUMN_USERNAME)));
                    breadcrumb.setClientId(cursor.getInt(cursor.getColumnIndex(Breadcrumb.COLUMN_CLIENT_ID)));
                    breadcrumb.setClientUsername(cursor.getString(cursor.getColumnIndex(Breadcrumb.COLUMN_CLIENT_USERNAME)));

                } while(cursor.moveToNext());
            }

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        } finally {
            cursor.close();
        }

        return breadcrumb;
    }

    public void delete() {
        db.delete(Breadcrumb.TABLE_NAME,null, new String[]{});
    }

}
