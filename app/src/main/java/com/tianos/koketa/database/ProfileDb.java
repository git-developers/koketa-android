package com.tianos.koketa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.tianos.koketa.entity.Profile;


public class ProfileDb extends BaseDb {

    private static final String TAG = ProfileDb.class.getName();

    public ProfileDb(Context context) {
        super.databaseHelper(context);
    }

    public void insertIfNotExist(Profile object) {
        if (!existsBySlug(object)) {
            insert(object);
        }
    }

    public void insert(Profile object) {

        ContentValues values = new ContentValues();
        values.put(Profile.COLUMN_ID, object.getId());
        values.put(Profile.COLUMN_SLUG, object.getSlug());
        values.put(Profile.COLUMN_NAME, object.getName());

        db.insert(Profile.TABLE_NAME, null, values);
    }

    public boolean existsBySlug(Profile object) {

        Cursor cursor = null;
        boolean exists = false;

        try {

//            cursor = db.rawQuery("SELECT EXISTS(" +
//                    "SELECT t1." + Profile.COLUMN_ID + " FROM " + Profile.TABLE_NAME + " AS t1 " +
//                    " WHERE t1." + Profile.COLUMN_SLUG + " = ?)", new String[]{object.getSlug()});

            cursor = db.rawQuery("SELECT t1." + Profile.COLUMN_ID + " FROM " + Profile.TABLE_NAME + " AS t1 " +
                    " WHERE t1." + Profile.COLUMN_SLUG + " = ?", new String[]{object.getSlug()});

            if (cursor.moveToFirst()) {
                exists = true;
            }

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        } finally {
            cursor.close();
//            db.close();
        }

        return exists;
    }

    public Profile findOneBySlug(Profile object) {

        Cursor cursor = null;
        Profile result = null;

        try {

            cursor = db.rawQuery("SELECT t1.* FROM t1." + Profile.TABLE_NAME +
                    " WHERE t1." + Profile.COLUMN_SLUG + " = ?", new String[]{object.getSlug()});

            if (cursor.moveToFirst()) {
                do {
                    result = new Profile(
                            cursor.getInt(cursor.getColumnIndex(Profile.COLUMN_ID)),
                            cursor.getString(cursor.getColumnIndex(Profile.COLUMN_SLUG)),
                            cursor.getString(cursor.getColumnIndex(Profile.COLUMN_NAME)),
                            cursor.getString(cursor.getColumnIndex(Profile.COLUMN_USERNAME))
                    );
                } while(cursor.moveToNext());
            }

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        } finally {
            cursor.close();
            db.close();
        }

        return result;
    }

    public void delete() {
        db.delete(Profile.TABLE_NAME,null, new String[]{});
//        db.close();
    }

}
