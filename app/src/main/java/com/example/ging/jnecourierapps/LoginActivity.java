package com.example.ging.jnecourierapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.app.AlertDialog;
import android.app.Dialog;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.Gravity;
import android.graphics.Color;
import android.widget.LinearLayout;


public class LoginActivity extends AppCompatActivity {
    EditText Email, Password;
    Button LoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginButton = findViewById(R.id.login);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    private void openDialog(){

        LinearLayout layout = new LinearLayout(LoginActivity.this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(20,20,20,20);

        //progress bar
        ProgressBar progressBar = new ProgressBar(LoginActivity.this, null, android.R.attr.progressBarStyleLarge);
        layout.addView(progressBar);

        //progress text
        TextView msg = new TextView(this);
        msg.setText("Harap Tunggu");
        msg.setPadding(10, 80, 10, 30);
        msg.setGravity(Gravity.CENTER_HORIZONTAL);
        msg.setTextColor(Color.BLACK);
        layout.addView(msg);

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setView(layout);

        new Dialog(getApplicationContext());
        alertDialog.show();
    }
}
