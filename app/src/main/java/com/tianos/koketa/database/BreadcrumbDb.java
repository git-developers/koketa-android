package com.tianos.koketa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.tianos.koketa.entity.Breadcrumb;


public class BreadcrumbDb extends BaseDb {

    public BreadcrumbDb(Context context) {
        super.databaseHelper(context);
    }

    public void insert(Breadcrumb breadcrumb) {

        ContentValues values = new ContentValues();
        values.put(Breadcrumb.COLUMN_USERNAME, breadcrumb.getUsername());

        db.insert(Breadcrumb.TABLE_NAME, null, values);
    }

    public Breadcrumb findLast() {

        Breadcrumb object = new Breadcrumb();

        Cursor cursor = db.query(
            Breadcrumb.TABLE_NAME,
            new String[] {
                Breadcrumb.COLUMN_ID_INCR,
                Breadcrumb.COLUMN_USERNAME,
                Breadcrumb.COLUMN_TIMESTAMP
            },
            null,
            new String[]{}, null, null, Breadcrumb.COLUMN_ID_INCR + " DESC", "1");

        if (cursor == null || cursor.getCount() <= 0) {
            return object;
        }

        cursor.moveToFirst();

        // prepare object
        object = new Breadcrumb(
            cursor.getString(cursor.getColumnIndex(Breadcrumb.COLUMN_USERNAME))
        );

        // close the db connection
        cursor.close();

        return object;
    }

    public void delete() {
        db.delete(Breadcrumb.TABLE_NAME,null, new String[]{});
//        db.close();
    }

}
