package com.example.ging.jnecourierapps.GPSHelper;

import android.content.Context;
import android.util.Log;

import com.example.ging.jnecourierapps.Activity.MainActivity;
import com.example.ging.jnecourierapps.Interfaces.SendLocationAPI;
import com.example.ging.jnecourierapps.Model.SendLocation;
import com.example.ging.jnecourierapps.Session.SessionManager;
import com.example.ging.jnecourierapps.Url.BaseUrl;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendDeviceDetailsHelper {
    BaseUrl baseUrl = new BaseUrl();
    public void sendJSON(Context context,Double latitude,Double longitude, String token, String userId){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl.getUrl()).addConverterFactory(GsonConverterFactory.create()).build();
        SendLocationAPI sendLocationAPI = retrofit.create(SendLocationAPI.class);
        Call<SendLocation> sendlocation = sendLocationAPI.sendlocation(token,userId,latitude.toString(),longitude.toString());
        sendlocation.enqueue(new Callback<SendLocation>() {
            @Override
            public void onResponse(Call<SendLocation> call, Response<SendLocation> response) {
                Log.i("SUKSES",response.message());
            }

            @Override
            public void onFailure(Call<SendLocation> call, Throwable t) {
                Log.i("ERRRR", t.getMessage());
            }
        });
    }
}
