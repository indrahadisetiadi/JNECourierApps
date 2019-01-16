package com.example.ging.jnecourierapps.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.ging.jnecourierapps.Fragment.HistoryFragment;
import com.example.ging.jnecourierapps.Fragment.ProfileFragment;
import com.example.ging.jnecourierapps.Fragment.TaskFragment;
import com.example.ging.jnecourierapps.GPSHelper.SendDeviceDetailsHelper;
import com.example.ging.jnecourierapps.R;
import com.example.ging.jnecourierapps.Session.SessionManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity{
    LocationManager lm;
    Location location;
    public SessionManager sessionManager;
    double longitude,latitude;
    private static final int REQUEST_CODE_PERMISSION = 1;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    SendDeviceDetailsHelper sendDeviceDetailsHelper = new SendDeviceDetailsHelper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManager = new SessionManager(MainActivity.this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.nav_history);
        Log.i("COBA","BERHASIL");
        Fragment defaultFragment = new HistoryFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, defaultFragment).commit();

    }
    Integer pos = 1;
    Integer currPos = 1;
    Integer tempPos = 5;
    
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;


                    switch (item.getItemId()) {
                        case R.id.nav_task:
                            selectedFragment = new TaskFragment();
                            pos = 0;
                            get_location();
                            break;
                        case R.id.nav_history:
                            selectedFragment = new HistoryFragment();
                            pos = 1;
                            get_location();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            pos = 2;
                            get_location();
                            break;
                    }

                    if (currPos == 1 && pos == 0){
                        getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.in_left_to_right, R.anim.out_left_to_right)
                                .replace(R.id.fragment_container,selectedFragment).commit();

                        Log.i("POS", pos.toString() + " <- " + currPos.toString() + " - " + tempPos.toString());
                        currPos = pos;
                    }

                    if (currPos == 0 && (pos == 1 || pos == 2)){
                        getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.in_right_to_left, R.anim.out_right_to_left)
                                .replace(R.id.fragment_container,selectedFragment).commit();

                        Log.i("POS", pos.toString() + " <- " + currPos.toString() + " - " + tempPos.toString());
                        currPos = pos;
                    }


                    if (currPos == 1 && pos == 2){
                        getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.in_right_to_left, R.anim.out_right_to_left)
                                .replace(R.id.fragment_container,selectedFragment).commit();

                        Log.i("POS", pos.toString() + " <- " + currPos.toString() + " - " + tempPos.toString());
                        currPos = pos;

                    }

                    if (currPos == 2 && (pos == 0 || pos == 1)){
                        getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.in_left_to_right, R.anim.out_left_to_right)
                                .replace(R.id.fragment_container,selectedFragment).commit();

                        Log.i("POS", pos.toString() + " <- " + currPos.toString() + " - " + tempPos.toString());
                        currPos = pos;
                    }

                    return true;

                }
            };

    private void get_location(){
        if(Build.VERSION.SDK_INT>= 23) {
            if (checkSelfPermission(mPermission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{mPermission,
                        }, REQUEST_CODE_PERMISSION);
                return;
            }
            else
            {
                FusedLocationProviderClient mFusedLocation = LocationServices.getFusedLocationProviderClient(this);
                mFusedLocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null){
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            Log.i("BERHASIL LAT",String.valueOf(latitude));
                            Log.i("BERHASIL LONG",String.valueOf(longitude));
                            sendDeviceDetailsHelper.sendJSON(MainActivity.this,latitude,longitude,sessionManager.getKey(),sessionManager.getterUserId());
                            Log.i("BERHASIL","BERHASIL");
                        }
                    }
                });
            }
        }
    }

}
