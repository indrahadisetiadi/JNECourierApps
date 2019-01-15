package com.example.ging.jnecourierapps.GPSHelper;

import android.content.Context;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import im.delight.android.location.SimpleLocation;

public class SendDeviceDetailsHelper {
    double latitude,longitude;
    private SimpleLocation location;
    public void SendJSON(final Context context){
        location = new SimpleLocation(context);
        if (!location.hasLocationEnabled()) {
            SimpleLocation.openSettings(context);
        }
        ScheduledExecutorService scheduleTaskExecutor = Executors.newScheduledThreadPool(1);
        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                SendDeviceDetailsHelper sendDeviceDetailsHelper = new SendDeviceDetailsHelper();
                sendDeviceDetailsHelper.SendJSON(context);
                latitude = location.getLatitude();
                longitude = location.getLongitude();
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
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

}
