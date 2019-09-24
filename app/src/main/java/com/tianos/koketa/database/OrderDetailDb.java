package com.tianos.koketa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.tianos.koketa.entity.OrderDetail;
import com.tianos.koketa.entity.Product;

import java.util.ArrayList;
import java.util.List;


public class OrderDetailDb extends BaseDb {

    private static final String TAG = ProfileDb.class.getName();

    public OrderDetailDb(Context context) {
        super.databaseHelper(context);
    }

    public void insert(OrderDetail object) {

        ContentValues values = new ContentValues();
        values.put(OrderDetail.COLUMN_ORDER_ID, object.getOrderId());
        values.put(OrderDetail.COLUMN_CATEGORY_ID, object.getCategoryId());
        values.put(OrderDetail.COLUMN_PRODUCT_ID, object.getProductId());
        values.put(OrderDetail.COLUMN_PRODUCT_QUANTITY, object.getProductQuantity());

        db.insert(OrderDetail.TABLE_NAME, null, values);
    }

    public List<OrderDetail> findAllByOrderId(String orderId) {

        Cursor cursor = null;
        List<OrderDetail> lst = new ArrayList<OrderDetail>();

        try {

            cursor = db.rawQuery("SELECT t1.*, t2.* FROM " + OrderDetail.TABLE_NAME + " AS t1 " +
                    " INNER JOIN " + Product.TABLE_NAME + " AS t2 ON t2." + Product.COLUMN_ID + " = t1." + OrderDetail.COLUMN_PRODUCT_ID +
                    " WHERE t1." + OrderDetail.COLUMN_ORDER_ID + " = ?", new String[]{orderId});

            if (cursor.moveToFirst()) {
                do {

                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setId(cursor.getInt(cursor.getColumnIndex(OrderDetail.COLUMN_ID_INCR)));
                    orderDetail.setOrderId(cursor.getInt(cursor.getColumnIndex(OrderDetail.COLUMN_ORDER_ID)));
                    orderDetail.setCategoryId(cursor.getInt(cursor.getColumnIndex(OrderDetail.COLUMN_CATEGORY_ID)));
                    orderDetail.setProductId(cursor.getInt(cursor.getColumnIndex(OrderDetail.COLUMN_PRODUCT_ID)));
                    orderDetail.setProductQuantity(cursor.getInt(cursor.getColumnIndex(OrderDetail.COLUMN_PRODUCT_QUANTITY)));

                    Product product = new Product();
                    product.setName(cursor.getString(cursor.getColumnIndex(Product.COLUMN_NAME)));
                    product.setFamily(cursor.getString(cursor.getColumnIndex(Product.COLUMN_FAMILY)));
                    product.setPrice(cursor.getFloat(cursor.getColumnIndex(Product.COLUMN_PRICE)));

                    orderDetail.setProduct(product);

                    lst.add(orderDetail);

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
        db.delete(OrderDetail.TABLE_NAME,null, new String[]{});
    }

    public void deleteById(String id) {
        db.delete(OrderDetail.TABLE_NAME,OrderDetail.COLUMN_ID_INCR + " = ?", new String[]{id});
    }
}
