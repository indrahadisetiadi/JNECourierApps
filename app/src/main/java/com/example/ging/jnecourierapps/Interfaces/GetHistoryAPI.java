package com.example.ging.jnecourierapps.Interfaces;

import com.example.ging.jnecourierapps.Model.ResponseHistory;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GetHistoryAPI {
    @FormUrlEncoded
    @POST("gethistory")
    Call<ResponseHistory> getHistory(
            @Field("id_kurir") String id_kurir
    );
}
