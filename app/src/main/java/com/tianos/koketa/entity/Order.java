package com.tianos.koketa.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

public class Order extends RealmObject implements Serializable {

    public static final String TABLE_NAME = "t_order";

    public static final String COLUMN_ID_INCR = "ID_INCR";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_CLIENT_ID = "CLIENT_ID";
    public static final String COLUMN_CATEGORY_ID = "CATEGORY_ID";
    public static final String COLUMN_PRODUCT_ID = "PRODUCT_ID";
    public static final String COLUMN_PRODUCT_STOCK = "PRODUCT_STOCK";
    public static final String COLUMN_STATUS = "STATUS";
    public static final String COLUMN_TIMESTAMP = "TIMESTAMP";

    public static final String CREATE_TABLE =
        "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID_INCR + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USERNAME + " VARCHAR(45),"
            + COLUMN_CLIENT_ID + " INTEGER(11),"
            + COLUMN_CATEGORY_ID + " INTEGER(11) DEFAULT NULL,"
            + COLUMN_PRODUCT_ID + " INTEGER(11) DEFAULT NULL,"
            + COLUMN_PRODUCT_STOCK + " INTEGER(11) DEFAULT NULL,"
            + COLUMN_STATUS + " VARCHAR(1),"
            + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ")";

    public static final String STATUS_PENDING = "1";
    public static final String STATUS_DONE = "2";
    public static final String STATUS_VOIDED = "3";

    @SerializedName("id")
    private Integer id;

    @SerializedName("username")
    private String username;

    @SerializedName("client_id")
    private Integer clientId;

    @SerializedName("category_id")
    private Integer categoryId;

    @SerializedName("product_id")
    private Integer productId;

    @SerializedName("product_stock")
    private Integer productStock;

    @SerializedName("status")
    private String status;

    public Order() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
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

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
