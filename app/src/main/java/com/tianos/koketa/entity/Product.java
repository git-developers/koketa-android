package com.tianos.koketa.entity;

import io.realm.RealmObject;

public class Product extends RealmObject {

    private Integer id;
    private String code;
    private String username;
    private String name;
    private String family;
    private Integer stock;
    private double price;

    public Product() {

    }

    public Product(Integer id, String name, String family, Integer stock, double price) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
