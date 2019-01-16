package com.example.ging.jnecourierapps.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.Gravity;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.example.ging.jnecourierapps.Interfaces.LoginAPI;
import com.example.ging.jnecourierapps.Model.Login;
import com.example.ging.jnecourierapps.Model.Profile;
import com.example.ging.jnecourierapps.R;
import com.example.ging.jnecourierapps.Session.SessionManager;
import com.example.ging.jnecourierapps.Url.BaseUrl;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    Button Login;
    EditText Username, Password;
    BaseUrl baseUrl = new BaseUrl();
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(LoginActivity.this);

        if(sessionManager.isLoggedIn()){
            Intent MainPage = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(MainPage);
            finish();
        }

        Username =  findViewById(R.id.email);
        Password = findViewById(R.id.password);
        Login = findViewById(R.id.login_action);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(Password.getText().toString()) || TextUtils.isEmpty(Username.getText().toString())){
                    form_validation_error("Email dan password harap diisi.");
                }
                else{
                    loginRequest();
                }
            }
        });
    }
    private void openDialog(){

        LinearLayout layout = new LinearLayout(LoginActivity.this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(20,20,20,20);

        //progressbar
        ProgressBar progressBar = new ProgressBar(LoginActivity.this, null, android.R.attr.progressBarStyleLarge);
//        progressBar.getIndeterminateDrawable().setColorFilter(0xE66E12,PorterDuff.Mode.MULTIPLY);
        layout.addView(progressBar);

        //progress text
        TextView msg = new TextView(this);
        msg.setText("Harap Tunggu");
        msg.setPadding(10, 80, 10, 30);
        msg.setGravity(Gravity.CENTER_HORIZONTAL);
        msg.setTextColor(Color.BLACK);
        layout.addView(msg);

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(LoginActivity.this);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.show();

        final Handler delay = new Handler();
        delay.postDelayed(new Runnable() {
            @Override
            public void run() {
                bottomSheetDialog.hide();
                login_berhasil();
                Intent goToActivity = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(goToActivity);
                finish();
            }
        }, 4000);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void form_validation_error(String pesan){
        LinearLayout layout = new LinearLayout(LoginActivity.this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(20,20,20,20);

        //error image
        ImageView imageView = new ImageView(LoginActivity.this);
        imageView.setImageDrawable(getDrawable(R.drawable.fail));
        layout.addView(imageView);

        //error text
        TextView msg = new TextView(this);
        msg.setText(pesan);
        msg.setPadding(10, 80, 10, 30);
        msg.setGravity(Gravity.CENTER_HORIZONTAL);
        msg.setTextColor(Color.BLACK);
        layout.addView(msg);

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(LoginActivity.this);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.show();
        final Handler delay = new Handler();
        delay.postDelayed(new Runnable() {
            @Override
            public void run() {
                bottomSheetDialog.hide();
            }
        }, 2500);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void login_berhasil() {
        LinearLayout layout = new LinearLayout(LoginActivity.this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(20,20,20,20);

        //berhasil image
        ImageView imageView = new ImageView(LoginActivity.this);
        imageView.setImageDrawable(getDrawable(R.drawable.success));
        layout.addView(imageView);

        //berhasil text
        TextView msg = new TextView(this);
        msg.setText("Login Berhasil");
        msg.setPadding(10, 80, 10, 30);
        msg.setGravity(Gravity.CENTER_HORIZONTAL);
        msg.setTextColor(Color.BLACK);
        layout.addView(msg);

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(LoginActivity.this);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.show();
        final Handler delay = new Handler();
        delay.postDelayed(new Runnable() {
            @Override
            public void run() {
                bottomSheetDialog.hide();
            }
        }, 1000);
    }


    private void loginRequest(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl.getUrl()).addConverterFactory(GsonConverterFactory.create()).build();
        LoginAPI loginAPI = retrofit.create(LoginAPI.class);

        Call<Login> call = loginAPI.login(Username.getText().toString(), Password.getText().toString());
        call.enqueue(new Callback<com.example.ging.jnecourierapps.Model.Login>() {
            @Override
            public void onResponse(Call<com.example.ging.jnecourierapps.Model.Login> call, Response<com.example.ging.jnecourierapps.Model.Login> response) {


                Log.i("True flag", response.message());
                if (response.body().getError() == null){
                    Log.i("Token", response.body().getToken());
                    Log.i("Nama Kurir", response.body().getSuccess_login().getNama_kurir());
                    Profile profile = new Profile();
                    profile = response.body().getSuccess_login();
                    sessionManager.setProfile(profile);
                    sessionManager.setLogin(true);
                    sessionManager.setterUserId(response.body().getSuccess_login().getId_kurir());
                    sessionManager.setKey(response.body().getToken());
                    openDialog();
                }else{
                    Log.i("Error", response.body().getError());
                    form_validation_error("Username & Password Tidak Cocok Broooo");
                }

            }

            @Override
            public void onFailure(Call<com.example.ging.jnecourierapps.Model.Login> call, Throwable t) {
                form_validation_error("Koneksi dengan server error");
            }
        });


    }
}
