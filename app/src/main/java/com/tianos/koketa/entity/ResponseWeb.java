package com.tianos.koketa.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class ResponseWeb extends RealmObject {

    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_WARNING = 2;
    public static final int STATUS_ERROR = 3;


    @SerializedName("errorCode")
    @Expose
    private Integer errorCode;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("clients")
    @Expose
    private RealmList<User> clients;

    @SerializedName("category")
    @Expose
    private RealmList<Category> category;

    @SerializedName("products")
    @Expose
    private RealmList<Product> product;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RealmList<User> getClients() {
        return clients;
    }

    public void setClients(RealmList<User> clients) {
        this.clients = clients;
    }

    public RealmList<Category> getCategory() {
        return category;
    }

    public void setCategory(RealmList<Category> category) {
        this.category = category;
    }

    public RealmList<Product> getProduct() {
        return product;
    }

    public void setProduct(RealmList<Product> product) {
        this.product = product;
    }
}
