package com.tianos.koketa.retrofit;

import com.tianos.koketa.entity.ResponseWeb;
import com.tianos.koketa.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;


public interface APIInterface {


    @POST("log")
    Call<ResponseWeb> sendLog(@Body Object object);

//    @PUT("5d748ced330000de69081975")
    @POST("user/login")
    Call<ResponseWeb> logIn(@Body User user);

    @PUT("5d748ced330000de69081975")
    Call<ResponseWeb> logIn2(@Body Object object);
}
