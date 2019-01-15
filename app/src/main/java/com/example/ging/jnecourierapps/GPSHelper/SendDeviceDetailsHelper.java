package com.example.ging.jnecourierapps.GPSHelper;

import android.content.Context;
import android.util.Log;

import com.example.ging.jnecourierapps.Activity.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SendDeviceDetailsHelper {
    GPSTracker gps;
    public void SendJSON(Context context){
        gps = new GPSTracker(context);
//        ScheduledExecutorService scheduleTaskExecutor = Executors.newScheduledThreadPool(5);
//        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
//            public void run() {
                double latitude = gps.getLatitude();
                double longitude= gps.getLongitude();
                Log.i("TEST Latitudenya ",String.valueOf(latitude));
                Log.i("TEST Longtitudenya ",String.valueOf(longitude));
                JSONObject postData = new JSONObject();
                try {
                    postData.put("LATITUDE",latitude);
                    postData.put("LONGTITUDE",longitude);
                    new SendDeviceDetails().execute("https://webhook.site/cbde2e87-127f-479d-890c-463a8ad00f55", postData.toString());
                } catch (JSONException e) {
                    Log.i("ERROR","Gagal ngirim json");
                }
//            }
//        }, 0, 1, TimeUnit.SECONDS);
    }
}
