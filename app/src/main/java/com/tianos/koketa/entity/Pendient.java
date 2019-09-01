package com.tianos.koketa.entity;

import io.realm.RealmObject;

public class Pendient extends RealmObject {

    private Integer id;
    private String code;
    private String name;
    private String status;
    private String client;
    private String fechaRegistro;
    private String fechaEnvio;

    public Pendient() {

    }

    public Pendient(Integer id, String client, String status, String fechaRegistro, String fechaEnvio) {
        this.id = id;
        this.client = client;
        this.status = status;
        this.fechaRegistro = fechaRegistro;
        this.fechaEnvio = fechaEnvio;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
}
