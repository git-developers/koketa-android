package com.tianos.koketa.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

public class Product extends RealmObject implements Serializable {

    public static final String TABLE_NAME = "t_product";

    public static final String COLUMN_ID_INCR = "ID_INCR";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_CODE = "CODE";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_STOCK = "STOCK";
    public static final String COLUMN_FAMILY = "FAMILY";
    public static final String COLUMN_PRICE = "PRICE";
    public static final String COLUMN_CATEGORY_ID = "CATEGORY_ID";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_TIMESTAMP = "TIMESTAMP";

    public static final String CREATE_TABLE =
        "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID_INCR + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ID + " INTEGER(11),"
            + COLUMN_CODE + " VARCHAR(45),"
            + COLUMN_NAME + " VARCHAR(45) DEFAULT NULL,"
            + COLUMN_FAMILY + " VARCHAR(11) DEFAULT NULL,"
            + COLUMN_STOCK + " INTEGER(11) DEFAULT NULL,"
            + COLUMN_PRICE + " DECIMAL(8,2) DEFAULT NULL,"
            + COLUMN_USERNAME + " VARCHAR(45),"
            + COLUMN_CATEGORY_ID + " INTEGER(11) DEFAULT NULL,"
            + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ")";

    @SerializedName("id")
    private Integer id;

    @SerializedName("code")
    private String code;

    @SerializedName("username")
    private String username;

    @SerializedName("name")
    private String name;

    @SerializedName("family")
    private String family;

    @SerializedName("stock")
    private Integer stock;

    @SerializedName("price")
    private float price;

    @SerializedName("category")
    private Category category;

    public Product() {

    }

    public Product(Integer id, String code,  String name, String family, Integer stock, float price) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.family = family;
        this.stock = stock;
        this.price = price;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
