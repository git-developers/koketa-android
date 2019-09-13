package com.tianos.koketa.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
}
