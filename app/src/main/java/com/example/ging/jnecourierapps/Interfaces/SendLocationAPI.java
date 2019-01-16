package com.example.ging.jnecourierapps.Interfaces;

import com.example.ging.jnecourierapps.Model.Login;
import com.example.ging.jnecourierapps.Model.SendLocation;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SendLocationAPI {
    @FormUrlEncoded
    @POST("updateperjalanan")
    Call<SendLocation> sendlocation(
            @Field("token") String token,
            @Field("id_kurir") String id_kurir,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude
    );
}
