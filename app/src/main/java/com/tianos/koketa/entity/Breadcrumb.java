package com.tianos.koketa.entity;

import java.io.Serializable;


public class Breadcrumb implements Serializable {

    public static final String TABLE_NAME = "t_breadcrumb";

    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_TIMESTAMP = "TIMESTAMP";

    public static final String CREATE_TABLE =
        "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USERNAME + " VARCHAR(45),"
            + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ")";

    public int id;
    public String username;
    public String fecha;

    public Breadcrumb() {

    }

    public Breadcrumb(int id, String username) {
        this.id = id;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
