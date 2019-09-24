package com.tianos.koketa.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

public class OrderDetail extends RealmObject implements Serializable {

    public static final String TABLE_NAME = "t_order_detail";

    public static final String COLUMN_ID_INCR = "ID_INCR";
    public static final String COLUMN_ORDER_ID = "ORDER_ID";
    public static final String COLUMN_CATEGORY_ID = "CATEGORY_ID";
    public static final String COLUMN_PRODUCT_ID = "PRODUCT_ID";
    public static final String COLUMN_PRODUCT_QUANTITY = "PRODUCT_QUANTITY";
    public static final String COLUMN_TIMESTAMP = "TIMESTAMP";

    public static final String CREATE_TABLE =
        "CREATE TABLE " + TABLE_NAME + "("
        + COLUMN_ID_INCR + " INTEGER PRIMARY KEY AUTOINCREMENT,"
        + COLUMN_ORDER_ID + " INTEGER(11) NOT NULL,"
        + COLUMN_CATEGORY_ID + " INTEGER(11) DEFAULT NULL,"
        + COLUMN_PRODUCT_ID + " INTEGER(11) DEFAULT NULL,"
        + COLUMN_PRODUCT_QUANTITY + " INTEGER(11) DEFAULT NULL,"
        + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
        + ")";

    @SerializedName("id")
    private Integer id;

    @SerializedName("order_id")
    private Integer orderId;

    @SerializedName("category_id")
    private Integer categoryId;

    @SerializedName("product_id")
    private Integer productId;

    @SerializedName("product_quantity")
    private Integer productQuantity;

    private User client;

    @SerializedName("product")
    private Product product;

    public OrderDetail() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
