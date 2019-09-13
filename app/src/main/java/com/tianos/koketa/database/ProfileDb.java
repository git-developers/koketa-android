package com.tianos.koketa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tianos.koketa.entity.Profile;


public class ProfileDb {

    private SQLiteDatabase db;

    public ProfileDb(Context context) {
        DatabaseHelper helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert(Profile object) {

        ContentValues values = new ContentValues();
        values.put(Profile.COLUMN_ID, object.getId());
        values.put(Profile.COLUMN_SLUG, object.getSlug());
        values.put(Profile.COLUMN_USERNAME, object.getUsername());

        Log.d("POLLO", "SSSSS::: " + object.getId() + "--" + object.getSlug() + "--" + object.getUsername());

        db.insert(Profile.TABLE_NAME, null, values);
    }

    public void delete() {
        db.delete(Profile.TABLE_NAME,null, new String[]{});
        db.close();
    }

}
