package com.tianos.koketa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.tianos.koketa.entity.Breadcrumb;
import com.tianos.koketa.entity.Order;
import com.tianos.koketa.entity.OrderDetail;
import com.tianos.koketa.entity.Product;
import com.tianos.koketa.entity.Profile;
import com.tianos.koketa.entity.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;


public class OrderDb extends BaseDb {

    private static final String TAG = ProfileDb.class.getName();

    public OrderDb(Context context) {
        super.databaseHelper(context);
    }

    public Order currentOrder(Breadcrumb breadcrumb) {

        Order currentOrder = findOneByClientId(breadcrumb);

        if (currentOrder != null) {
            return currentOrder;
        }

        Order newOrder = new Order();
        newOrder.setUsername(breadcrumb.getUsername());
        newOrder.setClientId(breadcrumb.getClientId());
        newOrder.setStatus(Order.STATUS_PENDING);

        insert(newOrder);

        return newOrder;
    }

    public Order findOneByClientId(@NotNull Breadcrumb breadcrumb) {

        Cursor cursor = null;
        Order order = null;

        try {

            cursor = db.rawQuery(
                "SELECT t1.* " +
                        " FROM " + Order.TABLE_NAME + " AS t1 " +
                        " WHERE t1." + Order.COLUMN_CLIENT_ID + " = ? " +
                        " AND t1." + Order.COLUMN_STATUS + " = ? " +
                        " LIMIT 1", new String[]{
                        String.valueOf(breadcrumb.getClientId()),
                        Order.STATUS_PENDING
                }
            );

            if (cursor.moveToFirst()) {

                do {
                    order = new Order();
                    order.setId(cursor.getInt(cursor.getColumnIndex(Order.COLUMN_ID_INCR)));
                    order.setUsername(cursor.getString(cursor.getColumnIndex(Order.COLUMN_USERNAME)));
                    order.setClientId(cursor.getInt(cursor.getColumnIndex(Order.COLUMN_CLIENT_ID)));
                } while(cursor.moveToNext());
            }

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        } finally {
            cursor.close();
        }

        return order;
    }

    public List<OrderDetail> findAllOrderDetailByClient(@NotNull Breadcrumb breadcrumb) {

        Cursor cursor = null;
        List<OrderDetail> lst = new ArrayList<OrderDetail>();

        try {

            cursor = db.rawQuery(
            "SELECT t1.*, t3." + Product.COLUMN_PRICE + ", t3." + Product.COLUMN_NAME + ", t3." + Product.COLUMN_FAMILY +
                " FROM " + OrderDetail.TABLE_NAME + " AS t1 " +
                " INNER JOIN " + Order.TABLE_NAME + " AS t2 ON t2." + Order.COLUMN_ID_INCR + " = t1." + OrderDetail.COLUMN_ORDER_ID +
                " LEFT JOIN " + Product.TABLE_NAME + " AS t3 ON t3." + Product.COLUMN_ID + " = t1." + OrderDetail.COLUMN_PRODUCT_ID +
                " WHERE t2." + Order.COLUMN_CLIENT_ID + " =? AND t2." + Order.COLUMN_STATUS + " =? " +
                " ORDER BY t1." + OrderDetail.COLUMN_ID_INCR + " DESC",
                new String[]{String.valueOf(breadcrumb.getClientId()), Order.STATUS_PENDING}
            );

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
                    product.setPrice(cursor.getFloat(cursor.getColumnIndex(Product.COLUMN_PRICE)));
                    product.setFamily(cursor.getString(cursor.getColumnIndex(Product.COLUMN_FAMILY)));
                    product.setStock(cursor.getInt(cursor.getColumnIndex(OrderDetail.COLUMN_PRODUCT_QUANTITY)));
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

    public RealmList<OrderDetail> findAllOrderDetailByOrderId(String orderId) {

        Cursor cursor = null;
        RealmList<OrderDetail> lst = new RealmList<OrderDetail>();

        try {

            cursor = db.rawQuery(
            "SELECT t1.*, t3." + Product.COLUMN_PRICE + ", t3." + Product.COLUMN_NAME + ", t3." + Product.COLUMN_FAMILY +
                " FROM " + OrderDetail.TABLE_NAME + " AS t1 " +
                " INNER JOIN " + Order.TABLE_NAME + " AS t2 ON t2." + Order.COLUMN_ID_INCR + " = t1." + OrderDetail.COLUMN_ORDER_ID +
                " LEFT JOIN " + Product.TABLE_NAME + " AS t3 ON t3." + Product.COLUMN_ID + " = t1." + OrderDetail.COLUMN_PRODUCT_ID +
                " WHERE t1." + OrderDetail.COLUMN_ORDER_ID + " = ? " +
                " ORDER BY t1." + OrderDetail.COLUMN_ID_INCR + " DESC",
                new String[]{orderId}
            );

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
                    product.setPrice(cursor.getFloat(cursor.getColumnIndex(Product.COLUMN_PRICE)));
                    product.setFamily(cursor.getString(cursor.getColumnIndex(Product.COLUMN_FAMILY)));
                    product.setStock(cursor.getInt(cursor.getColumnIndex(OrderDetail.COLUMN_PRODUCT_QUANTITY)));
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

    public RealmList<OrderDetail> findAllOrderDetailByOrderId2(String orderId) {

        Cursor cursor = null;
        RealmList<OrderDetail> lst = new RealmList<OrderDetail>();

        try {

            cursor = db.rawQuery(
            "SELECT t1.* " +
                " FROM " + OrderDetail.TABLE_NAME + " AS t1 " +
                " INNER JOIN " + Order.TABLE_NAME + " AS t2 ON t2." + Order.COLUMN_ID_INCR + " = t1." + OrderDetail.COLUMN_ORDER_ID +
                " WHERE t1." + OrderDetail.COLUMN_ORDER_ID + " = ? " +
                " ORDER BY t1." + OrderDetail.COLUMN_ID_INCR + " DESC",
                new String[]{orderId}
            );

            if (cursor.moveToFirst()) {
                do {

                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setId(cursor.getInt(cursor.getColumnIndex(OrderDetail.COLUMN_ID_INCR)));
                    orderDetail.setOrderId(cursor.getInt(cursor.getColumnIndex(OrderDetail.COLUMN_ORDER_ID)));
                    orderDetail.setCategoryId(cursor.getInt(cursor.getColumnIndex(OrderDetail.COLUMN_CATEGORY_ID)));
                    orderDetail.setProductId(cursor.getInt(cursor.getColumnIndex(OrderDetail.COLUMN_PRODUCT_ID)));
                    orderDetail.setProductQuantity(cursor.getInt(cursor.getColumnIndex(OrderDetail.COLUMN_PRODUCT_QUANTITY)));

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

    public void insert(Order object) {

        ContentValues values = new ContentValues();
        values.put(Order.COLUMN_STATUS, object.getStatus());
        values.put(Order.COLUMN_USERNAME, object.getUsername());
        values.put(Order.COLUMN_CLIENT_ID, object.getClientId());

        db.insert(Order.TABLE_NAME, null, values);
    }

    public void updateStatus(String id, String status) {

        ContentValues values = new ContentValues();
        values.put(Order.COLUMN_STATUS, status);

        db.update(Order.TABLE_NAME, values, Order.COLUMN_ID_INCR + "=?", new String[]{id});
    }

    public RealmList<Order> findAllPending() {

        Cursor cursor = null;
        RealmList<Order> lst = new RealmList<Order>();

        try {

            cursor = db.rawQuery(
                "SELECT t1.* " +
                " FROM " + Order.TABLE_NAME + " AS t1 " +
                " WHERE t1." + Order.COLUMN_STATUS + " = ?", new String[]{Order.STATUS_PENDING});

            if (cursor.moveToFirst()) {
                do {

                    Order order = new Order();
                    order.setId(cursor.getInt(cursor.getColumnIndex(Order.COLUMN_ID_INCR)));
                    order.setUsername(cursor.getString(cursor.getColumnIndex(Order.COLUMN_USERNAME)));
                    order.setClientId(cursor.getInt(cursor.getColumnIndex(Order.COLUMN_CLIENT_ID)));

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

    /*

    public List<Product> findPendingProducts() {

        Cursor cursor = null;
        List<Product> lst = new ArrayList<Product>();

        try {

            cursor = db.rawQuery("SELECT t2.*, t1." + Order.COLUMN_PRODUCT_STOCK + " FROM " + Order.TABLE_NAME + " AS t1 " +
                            "INNER JOIN " + Product.TABLE_NAME + " AS t2 ON t2." + Product.COLUMN_ID + " = t1." + Order.COLUMN_PRODUCT_ID +
                            " WHERE t1." + Order.COLUMN_STATUS + " = ?", new String[]{Order.STATUS_PENDING});

            if (cursor.moveToFirst()) {
                do {
                    Product product = new Product();
                    product.setId(cursor.getInt(cursor.getColumnIndex(Product.COLUMN_ID)));
                    product.setName(cursor.getString(cursor.getColumnIndex(Product.COLUMN_NAME)));
                    product.setFamily(cursor.getString(cursor.getColumnIndex(Product.COLUMN_FAMILY)));
                    product.setStock(cursor.getInt(cursor.getColumnIndex(Order.COLUMN_PRODUCT_STOCK)));
                    product.setPrice(cursor.getFloat(cursor.getColumnIndex(Product.COLUMN_PRICE)));

                    lst.add(product);

                } while(cursor.moveToNext());
            }

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        } finally {
            cursor.close();
        }

        return lst;
    }

     */

    public void delete() {
        db.delete(Order.TABLE_NAME,null, new String[]{});
    }

    public void deleteById(String id) {
        db.delete(Order.TABLE_NAME,Order.COLUMN_ID_INCR + " = ?", new String[]{id});
    }
}
