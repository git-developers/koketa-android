package com.tianos.koketa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tianos.koketa.entity.User;


public class UserDb {

    private SQLiteDatabase db;

    public UserDb(Context context) {
        DatabaseHelper helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert(User object) {

        ContentValues values = new ContentValues();
        values.put(User.COLUMN_ID, object.getId());
        values.put(User.COLUMN_RUC, object.getRuc());
        values.put(User.COLUMN_EMAIL, object.getEmail());
        values.put(User.COLUMN_NAME, object.getName());
        values.put(User.COLUMN_USERNAME, object.getUsername());

        db.insert(User.TABLE_NAME, null, values);
    }

    public User findOneByUsername(String username) {

        User object = new User();

        Cursor cursor = db.query(
            User.TABLE_NAME,
            new String[] {
                User.COLUMN_ID,
                User.COLUMN_USERNAME,
                User.COLUMN_TIMESTAMP
            },
            null,
            new String[]{}, null, null, User.COLUMN_ID + " DESC", "1");

        if (cursor == null || cursor.getCount() <= 0) {
            return object;
        }

        cursor.moveToFirst();

        // prepare object
        object = new User(
            cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)),
            cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME)),
            cursor.getString(cursor.getColumnIndex(User.COLUMN_RUC)),
            cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL))
        );

        // close the db connection
        cursor.close();

        return object;
    }

    public void delete() {
        db.delete(User.TABLE_NAME,null, new String[]{});
        db.close();
    }

}
