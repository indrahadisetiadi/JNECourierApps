package com.example.ging.jnecourierapps.Interfaces;

import com.example.ging.jnecourierapps.Model.ResponseTask;

import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface DeliveryAPI {
    @FormUrlEncoded
    @POST("submitPaketGagal")
    Call<ResponseTask> submitPaketGagal(
            @Field("id_kurir") String id_kurir,
            @Field("no_resi") String no_resi,
            @Field("alasan_gagal") String alasan_gagal
    );

/*    @Multipart
    @POST("submitPaketSukses")
    Call<ResponseTask> submitPaketSukses(
            @Part("id_kuri") RequestBody id_kurir,
            @Part("no_resi") RequestBody no_resi,
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file
    );*/

    @Multipart
    @POST("submitPaketSukses")
    Call<ResponseBody> submitPaketSukses(
            @Part MultipartBody.Part foto_pengiriman,
            @Part MultipartBody.Part id_kurir,
            @Part MultipartBody.Part no_resi
    );

}
