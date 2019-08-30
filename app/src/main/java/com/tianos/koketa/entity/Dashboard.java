package com.tianos.koketa.entity;

public class Dashboard {

    private Integer id;
    private String name;
    private Integer icon;
    private String tag;
    private Integer tint;

    public Dashboard(Integer id, String name, Integer icon, String tag, Integer tint) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.tag = tag;
        this.tint = tint;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getTint() {
        return tint;
    }

    public void setTint(Integer tint) {
        this.tint = tint;
    }
}
