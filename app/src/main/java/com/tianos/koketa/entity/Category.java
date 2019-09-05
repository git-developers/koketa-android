package com.tianos.koketa.entity;

import io.realm.RealmObject;

public class Category extends RealmObject {

    private Integer id;
    private String code;
    private String name;
    private Integer stock;

    public Category() {

    }

    public Category(Integer id, String code, String name, Integer stock) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.stock = stock;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
