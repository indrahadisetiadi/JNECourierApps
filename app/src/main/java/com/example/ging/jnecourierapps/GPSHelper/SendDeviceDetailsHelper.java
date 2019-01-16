package com.example.ging.jnecourierapps.GPSHelper;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class SendDeviceDetailsHelper {
    public void sendJSON(Context context,Double latitude,Double longitude){
        JSONObject postData = new JSONObject();
        try {
            postData.put("LATITUDE",latitude);
            postData.put("LONGTITUDE",longitude);
            new SendDeviceDetails().execute("https://webhook.site/cbde2e87-127f-479d-890c-463a8ad00f55", postData.toString());
        } catch (JSONException e) {
            Log.i("ERROR","Gagal ngirim json");
        }
    }
}
