package com.tianos.koketa.entity;

import io.realm.RealmObject;

public class Visit extends RealmObject {

    private Integer id;
    private String code;
    private String name;

    public Visit() {

    }

    public Visit(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
