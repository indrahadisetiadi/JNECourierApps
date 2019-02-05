package com.example.ging.jnecourierapps.Interfaces;

import com.example.ging.jnecourierapps.Model.ResponseTask;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GetTaskAPI {
    @FormUrlEncoded
    @POST("getpaket")
    Call<ResponseTask> getPaket(
            @Field("id_kurir") String id_kurir,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude
    );
}
