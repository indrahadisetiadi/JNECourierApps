package com.example.ging.jnecourierapps.Interfaces;

import com.example.ging.jnecourierapps.Model.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginAPI {

    @FormUrlEncoded
    @POST("signin")
    Call<Login> login(
            @Field("username") String username,
            @Field("password") String password
    );

}
