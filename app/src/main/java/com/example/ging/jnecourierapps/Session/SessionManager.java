package com.example.ging.jnecourierapps.Session;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.ging.jnecourierapps.Model.Profile;
import com.google.gson.Gson;

public class SessionManager {

    private static String TAG = SessionManager.class.getSimpleName();

    SharedPreferences pref;
    private String user_id;
    private String key;
    private Profile profile;
    private String longitude;
    private String latitude;



    public void setProfile(Profile profile) {
        this.profile = profile;
        Gson gson = new Gson();
        String json = gson.toJson(profile);
        editor.putString("UserProfile", json);
        editor.commit();
    }
    public Profile getProfile() {
        Gson gson = new Gson();
        String json = pref.getString("UserProfile","");
        profile = gson.fromJson(json, Profile.class);
        return profile;
    }


    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "AndroidHiveLogin";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }




    //LOGIN
    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();
        Log.d(TAG, "Profile login session modified!");
    }
    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }
    //LOGIN

    //LOGOUT
    public void Logout(){
        editor.clear();
        editor.commit();
    }
    //LOGOUT


    //USER
    public void setterUserId(String user_id){
        editor.putString("user_id", user_id);
        editor.commit();
    }
    public String getterUserId(){
        return pref.getString("user_id",null);
    }
    //USER


    //KEY
    public void setKey(String key){
        this.key = key;
        editor.putString("key", key);
        editor.commit();
    }
    public String getKey(){
        return pref.getString("key", "no key");
    }
    //KEY


    public String getLongitude() {
        return pref.getString("longitude",null);
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
        editor.putString("longitude", longitude);
        editor.commit();
    }

    public String getLatitude() {
        return pref.getString("latitude",null) ;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
        editor.putString("latitude", latitude);
        editor.commit();

    }



}
