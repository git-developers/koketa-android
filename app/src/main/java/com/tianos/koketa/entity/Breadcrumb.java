package com.tianos.koketa.entity;

import java.io.Serializable;


public class Breadcrumb implements Serializable {

    public static final String TABLE_NAME = "t_breadcrumb";

    public static final String COLUMN_ID_INCR = "ID_INCR";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_CLIENT_ID = "CLIENT_ID";
    public static final String COLUMN_CLIENT_USERNAME = "CLIENT_USERNAME";
    public static final String COLUMN_TIMESTAMP = "TIMESTAMP";

    public static final String CREATE_TABLE =
        "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID_INCR + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USERNAME + " VARCHAR(45),"
            + COLUMN_CLIENT_ID + " INTEGER(11) DEFAULT NULL,"
            + COLUMN_CLIENT_USERNAME + " VARCHAR(45) DEFAULT NULL,"
            + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ")";

    public Integer id;
    public String username;
    public Integer clientId;
    public String clientUsername;

    public Breadcrumb() {

    }

    public Breadcrumb(String username) {
        this.username = username;
    }

    public Breadcrumb(String username, String clientUsername) {
        this.username = username;
        this.clientUsername = clientUsername;
    }

    public Breadcrumb(String username, Integer clientId) {
        this.username = username;
        this.clientId = clientId;
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

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }
}
