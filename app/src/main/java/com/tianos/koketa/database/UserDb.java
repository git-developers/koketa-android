package com.tianos.koketa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.tianos.koketa.entity.Profile;
import com.tianos.koketa.entity.User;

import java.util.ArrayList;
import java.util.List;


public class UserDb extends BaseDb {

    private static final String TAG = ProfileDb.class.getName();

    public UserDb(Context context) {
        super.databaseHelper(context);
    }

    public void insert(User object) {

        ContentValues values = new ContentValues();
        values.put(User.COLUMN_ID, object.getId());
        values.put(User.COLUMN_RUC, object.getRuc());
        values.put(User.COLUMN_EMAIL, object.getEmail());
        values.put(User.COLUMN_NAME, object.getName());
        values.put(User.COLUMN_LAST_NAME, object.getLastName());
        values.put(User.COLUMN_PHONE, object.getPhone());
        values.put(User.COLUMN_USERNAME, object.getUsername());
        values.put(User.COLUMN_PROFILE_ID, object.getProfile().getId());

        db.insert(User.TABLE_NAME, null, values);
    }

    public List<User> findAll() {

        Cursor cursor = null;
        List<User> lst = new ArrayList<User>();

        try {

            cursor = db.rawQuery("SELECT t1.* FROM " + User.TABLE_NAME + " AS t1 " +
                    "INNER JOIN " + Profile.TABLE_NAME + " AS t2 ON t2." + Profile.COLUMN_ID + " = t1." + User.COLUMN_PROFILE_ID +
                    " WHERE t2." + Profile.COLUMN_SLUG + " = ?", new String[]{Profile.SLUG_CLIENT});

            if (cursor.moveToFirst()) {
                do {

                    User user = new User(
                        cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(User.COLUMN_RUC)),
                        cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL))
                    );

                    lst.add(user);

                } while(cursor.moveToNext());
            }

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        } finally {
            cursor.close();
//            db.close();
        }

        return lst;
    }

    public void delete() {
        db.delete(User.TABLE_NAME,null, new String[]{});
//        db.close();
    }

    public void deleteClients() {
        db.delete(User.TABLE_NAME,User.COLUMN_PROFILE_ID + " = ?", new String[]{"4"});
    }

}
