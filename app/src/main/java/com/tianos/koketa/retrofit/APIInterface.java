package com.tianos.koketa.retrofit;

import com.tianos.koketa.entity.Order;
import com.tianos.koketa.entity.ResponseWeb;
import com.tianos.koketa.entity.User;

import java.util.List;

import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;


public interface APIInterface {

    @POST("log")
    Call<ResponseWeb> sendLog(@Body Object object);

    @POST("user/login")
    Call<ResponseWeb> logIn(@Body User user);

    @POST("user/clients")
    Call<ResponseWeb> clients(@Body User user);

    @POST("category/list")
    Call<ResponseWeb> category(@Body User user);

    @POST("product/list")
    Call<ResponseWeb> product(@Body User user);

    @POST("sales/create")
    Call<ResponseWeb> salesCreate(@Body RealmList<Order> lstOrder);

}
