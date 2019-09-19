package com.tianos.koketa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.tianos.koketa.entity.Product;
import com.tianos.koketa.entity.Profile;

import java.util.ArrayList;
import java.util.List;


public class ProductDb extends BaseDb {

    private static final String TAG = ProductDb.class.getName();

    public ProductDb(Context context) {
        super.databaseHelper(context);
    }

    public void insert(Product object) {

        ContentValues values = new ContentValues();
        values.put(Product.COLUMN_ID, object.getId());
        values.put(Product.COLUMN_CODE, object.getCode());
        values.put(Product.COLUMN_NAME, object.getName());
        values.put(Product.COLUMN_FAMILY, object.getFamily());
        values.put(Product.COLUMN_STOCK, object.getStock());
        values.put(Product.COLUMN_PRICE, object.getPrice());
        values.put(Product.COLUMN_CATEGORY_ID, object.getCategory().getId());

        db.insert(Product.TABLE_NAME, null, values);
    }

    public Product findOneById(String id) {

        Cursor cursor = null;
        Product product = new Product();

        try {

            cursor = db.rawQuery("SELECT t1.* " +
                                    "FROM " + Product.TABLE_NAME + " AS t1 " +
                                    "WHERE t1." + Product.COLUMN_ID + " = ?", new String[]{id});

            if (cursor.moveToFirst()) {
                do {
                    product = new Product(
                        cursor.getInt(cursor.getColumnIndex(Product.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(Product.COLUMN_CODE)),
                        cursor.getString(cursor.getColumnIndex(Product.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(Product.COLUMN_FAMILY)),
                        cursor.getInt(cursor.getColumnIndex(Product.COLUMN_STOCK)),
                        cursor.getFloat(cursor.getColumnIndex(Product.COLUMN_PRICE))
                    );

                } while(cursor.moveToNext());
            }

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        } finally {
            cursor.close();
        }

        return product;
    }

    public List<Product> findAll() {

        Cursor cursor = null;
        List<Product> lst = new ArrayList<Product>();

        try {

            cursor = db.rawQuery("SELECT t1.* FROM " + Product.TABLE_NAME + " AS t1", new String[]{});

            if (cursor.moveToFirst()) {
                do {
                    Product product = new Product(
                        cursor.getInt(cursor.getColumnIndex(Product.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(Product.COLUMN_CODE)),
                        cursor.getString(cursor.getColumnIndex(Product.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(Product.COLUMN_FAMILY)),
                        cursor.getInt(cursor.getColumnIndex(Product.COLUMN_STOCK)),
                        cursor.getFloat(cursor.getColumnIndex(Product.COLUMN_PRICE))
                    );

                    lst.add(product);

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

    public List<Product> findAllByCategory(String categoryId) {

        Cursor cursor = null;
        List<Product> lst = new ArrayList<Product>();

        try {

            cursor = db.rawQuery("SELECT t1.* " +
                                    "FROM " + Product.TABLE_NAME + " AS t1 " +
                                    "WHERE t1." + Product.COLUMN_CATEGORY_ID + " = ?", new String[]{categoryId});

            if (cursor.moveToFirst()) {
                do {
                    Product product = new Product(
                        cursor.getInt(cursor.getColumnIndex(Product.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(Product.COLUMN_CODE)),
                        cursor.getString(cursor.getColumnIndex(Product.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(Product.COLUMN_FAMILY)),
                        cursor.getInt(cursor.getColumnIndex(Product.COLUMN_STOCK)),
                        cursor.getFloat(cursor.getColumnIndex(Product.COLUMN_PRICE))
                    );

                    lst.add(product);

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
        db.delete(Product.TABLE_NAME,null, new String[]{});
    }

}
