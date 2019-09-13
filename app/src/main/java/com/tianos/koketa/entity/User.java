package com.tianos.koketa.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

public class User extends RealmObject implements Serializable {

    public static final String TABLE_NAME = "t_user";

    public static final String COLUMN_ID_INCR = "ID_INCR";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_TIMESTAMP = "TIMESTAMP";
    public static final String COLUMN_EMAIL = "EMAIL";
    public static final String COLUMN_RUC = "RUC";
    public static final String COLUMN_LAST_NAME = "LAST_NAME";
    public static final String COLUMN_PROFILE_ID = "PROFILE_ID";

    public static final String CREATE_TABLE =
        "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID_INCR + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ID + " INTEGER(11),"
            + COLUMN_USERNAME + " VARCHAR(45),"
            + COLUMN_RUC + " VARCHAR(11) DEFAULT NULL,"
            + COLUMN_EMAIL + " VARCHAR(45) DEFAULT NULL,"
            + COLUMN_NAME + " VARCHAR(45) DEFAULT NULL,"
            + COLUMN_LAST_NAME + " VARCHAR(45) DEFAULT NULL,"
            + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ")";

    @SerializedName("id")
    private Integer id;

    @SerializedName("code")
    private String code;

    @SerializedName("password")
    private String password;

    @SerializedName("username")
    private String username;

    @SerializedName("name")
    public String name;

    @SerializedName("last_name")
    public String lastName;

    @SerializedName("email")
    private String email;

    @SerializedName("profile")
    private Profile profile;

    @SerializedName("ruc")
    private String ruc;

    private String dni;
    private String phone;
    private String comment;
    private float creditLine;
    private float balance;
    private String paymentCondition;

    public User() {

    }

    public User(Integer id, String name, String ruc, String email) {
        this.id = id;
        this.name = name;
        this.ruc = ruc;
        this.email = email;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(float creditLine) {
        this.creditLine = creditLine;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getPaymentCondition() {
        return paymentCondition;
    }

    public void setPaymentCondition(String paymentCondition) {
        this.paymentCondition = paymentCondition;
    }
}
