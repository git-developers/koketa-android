package com.tianos.koketa.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Order extends RealmObject implements Serializable {

    public static final String TABLE_NAME = "t_order";

    public static final String COLUMN_ID_INCR = "ID_INCR";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_CLIENT_ID = "CLIENT_ID";
    public static final String COLUMN_STATUS = "STATUS";
    public static final String COLUMN_PAYMENT_VOUCHER = "PAYMENT_VOUCHER";
    public static final String COLUMN_DELIVERY_DATE = "DELIVERY_DATE";
    public static final String COLUMN_TIMESTAMP = "TIMESTAMP";

    public static final String CREATE_TABLE =
        "CREATE TABLE " + TABLE_NAME + "("
        + COLUMN_ID_INCR + " INTEGER PRIMARY KEY AUTOINCREMENT,"
        + COLUMN_USERNAME + " VARCHAR(45),"
        + COLUMN_CLIENT_ID + " INTEGER(11),"
        + COLUMN_PAYMENT_VOUCHER + " INTEGER(11),"
        + COLUMN_STATUS + " VARCHAR(1),"
        + COLUMN_DELIVERY_DATE + " DATETIME,"
        + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
        + ")";

    public static final String STATUS_PENDING = "1";
    public static final String STATUS_DONE = "2";
    public static final String STATUS_VOIDED = "3";

    public static final String PAYMENT_VOUCHER_BOLETA = "1";
    public static final String PAYMENT_VOUCHER_FACTURA = "2";

    @SerializedName("id")
    private Integer id;

    @SerializedName("username")
    private String username;

    @SerializedName("client_id")
    private Integer clientId;

    @SerializedName("payment_voucher")
    private Integer paymentVoucher;

    @SerializedName("status")
    private String status;

    @SerializedName("delivery_date")
    private Date deliveryDate;

    private RealmList<OrderDetail> orderDetail;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RealmList<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(RealmList<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Integer getPaymentVoucher() {
        return paymentVoucher;
    }

    public void setPaymentVoucher(Integer paymentVoucher) {
        this.paymentVoucher = paymentVoucher;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
