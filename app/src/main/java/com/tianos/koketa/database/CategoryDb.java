package com.tianos.koketa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.tianos.koketa.entity.Category;
import com.tianos.koketa.entity.Profile;

import java.util.ArrayList;
import java.util.List;


public class CategoryDb extends BaseDb {

    private static final String TAG = CategoryDb.class.getName();

    public CategoryDb(Context context) {
        super.databaseHelper(context);
    }

    public void insert(Category object) {

        ContentValues values = new ContentValues();
        values.put(Category.COLUMN_ID, object.getId());
        values.put(Category.COLUMN_CODE, object.getCode());
        values.put(Category.COLUMN_NAME, object.getName());
        values.put(Category.COLUMN_STOCK, object.getStock());

        db.insert(Category.TABLE_NAME, null, values);
    }

    public List<Category> findAll() {

        Cursor cursor = null;
        List<Category> lst = new ArrayList<Category>();

        try {

            cursor = db.rawQuery("SELECT t1.* FROM " + Category.TABLE_NAME + " AS t1 ", new String[]{Profile.SLUG_CLIENT});

            if (cursor.moveToFirst()) {
                do {

                    Category category = new Category(
                        cursor.getInt(cursor.getColumnIndex(Category.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(Category.COLUMN_CODE)),
                        cursor.getString(cursor.getColumnIndex(Category.COLUMN_NAME)),
                        cursor.getInt(cursor.getColumnIndex(Category.COLUMN_STOCK))
                    );

                    lst.add(category);

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
        db.delete(Category.TABLE_NAME,null, new String[]{});
//        db.close();
    }

}
