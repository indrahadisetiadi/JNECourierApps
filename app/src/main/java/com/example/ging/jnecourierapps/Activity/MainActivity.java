package com.example.ging.jnecourierapps.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.ging.jnecourierapps.Fragment.HistoryFragment;
import com.example.ging.jnecourierapps.Fragment.ProfileFragment;
import com.example.ging.jnecourierapps.Fragment.TaskFragment;
import com.example.ging.jnecourierapps.GPSHelper.GPSHelper;
import com.example.ging.jnecourierapps.R;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity{

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.nav_history);

        intent = new Intent(this,GPSHelper.class);
//        intent.putExtra("Context",Main);
        startService(intent);

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
                            break;
                        case R.id.nav_history:
                            selectedFragment = new HistoryFragment();
                            pos = 1;
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            pos = 2;
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

}
