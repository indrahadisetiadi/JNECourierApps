package com.example.ging.jnecourierapps.GPSHelper;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


import com.example.ging.jnecourierapps.Activity.MainActivity;

import androidx.annotation.Nullable;

public class GPSHelper extends Service{

    MainActivity ma;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        GPSTracker gpsTracker = new GPSTracker(ma.getApplicationContext());
        double latitude = gpsTracker.getLatitude();
        double altitude = gpsTracker.getLongitude();
        Log.i("TOLONG",String.format("%.2f", latitude));
        Log.i("TOLONGGG",String.format("%.2f", altitude));
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

//    public void startGPS(Context context)
}
