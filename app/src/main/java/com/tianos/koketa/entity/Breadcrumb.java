package com.tianos.koketa.entity;

import java.io.Serializable;


public class Breadcrumb implements Serializable {

    public static final String TABLE_NAME = "t_breadcrumb";

    public static final String COLUMN_ID_INCR = "ID_INCR";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_TIMESTAMP = "TIMESTAMP";

    public static final String CREATE_TABLE =
        "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID_INCR + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USERNAME + " VARCHAR(45),"
            + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ")";

    public int id;
    public String username;

    public Breadcrumb() {

    }

    public Breadcrumb(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
