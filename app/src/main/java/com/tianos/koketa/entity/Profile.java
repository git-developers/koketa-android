package com.tianos.koketa.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

public class Profile extends RealmObject implements Serializable {

    public static final String SLUG_PDV_ADMIN = "pdv-administrator";
    public static final String SLUG_SELLER = "seller";
    public static final String SLUG_CLIENT = "client";

    public static final String TABLE_NAME = "t_profile";

    public static final String COLUMN_ID_INCR = "ID_INCR";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_SLUG = "SLUG";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_CODE = "CODE";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_TIMESTAMP = "TIMESTAMP";

    public static final String CREATE_TABLE =
        "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID_INCR + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ID + " INTEGER(11),"
            + COLUMN_USERNAME + " VARCHAR(45),"
            + COLUMN_CODE + " VARCHAR(45) DEFAULT NULL,"
            + COLUMN_SLUG + " VARCHAR(45) DEFAULT NULL,"
            + COLUMN_NAME + " VARCHAR(45) DEFAULT NULL,"
            + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ")";

    @SerializedName("id")
    private Integer id;

    @SerializedName("code")
    private String code;

    @SerializedName("slug")
    private String slug;

    @SerializedName("name")
    public String name;

    @SerializedName("username")
    private String username;

    public Profile() {

    }

    public Profile(Integer id, String slug, String username) {
        this.id = id;
        this.slug = slug;
        this.username = username;
    }

    public Profile(Integer id, String slug) {
        this.id = id;
        this.slug = slug;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
