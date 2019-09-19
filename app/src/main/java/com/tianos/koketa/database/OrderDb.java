package com.tianos.koketa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.tianos.koketa.entity.Order;
import com.tianos.koketa.entity.Profile;
import com.tianos.koketa.entity.User;

import java.util.ArrayList;
import java.util.List;


public class OrderDb extends BaseDb {

    private static final String TAG = ProfileDb.class.getName();

    public OrderDb(Context context) {
        super.databaseHelper(context);
    }

    public void insert(Order object) {

        ContentValues values = new ContentValues();
        values.put(Order.COLUMN_STATUS, object.getStatus());
        values.put(Order.COLUMN_USERNAME, object.getUsername());
        values.put(Order.COLUMN_CLIENT_ID, object.getClientId());
        values.put(Order.COLUMN_CATEGORY_ID, object.getCategoryId());
        values.put(Order.COLUMN_PRODUCT_ID, object.getProductId());
        values.put(Order.COLUMN_PRODUCT_STOCK, object.getProductStock());

        db.insert(Order.TABLE_NAME, null, values);
    }

    public List<Order> findAllPending() {

        Cursor cursor = null;
        List<Order> lst = new ArrayList<Order>();

        try {

            cursor = db.rawQuery("SELECT t1.* FROM " + Order.TABLE_NAME + " AS t1 " +
                    " WHERE t2." + Order.COLUMN_STATUS + " = ?", new String[]{Order.STATUS_PENDING});

            if (cursor.moveToFirst()) {
                do {

                    Order order = new Order();
                    order.setId(cursor.getInt(cursor.getColumnIndex(Order.COLUMN_ID)));
                    order.setUsername(cursor.getString(cursor.getColumnIndex(Order.COLUMN_USERNAME)));
                    order.setClientId(cursor.getInt(cursor.getColumnIndex(Order.COLUMN_CLIENT_ID)));
                    order.setClientId(cursor.getInt(cursor.getColumnIndex(Order.COLUMN_CATEGORY_ID)));
                    order.setCategoryId(cursor.getInt(cursor.getColumnIndex(Order.COLUMN_CATEGORY_ID)));
                    order.setProductId(cursor.getInt(cursor.getColumnIndex(Order.COLUMN_PRODUCT_ID)));
                    lst.add(order);

                } while(cursor.moveToNext());
            }

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        } finally {
            cursor.close();
        }

        return lst;
    }

    public void delete() {
        db.delete(User.TABLE_NAME,null, new String[]{});
//        db.close();
    }

}
