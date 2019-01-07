package com.example.ging.jnecourierapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.app.AlertDialog;
import android.app.Dialog;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.Gravity;
import android.graphics.Color;
import android.content.DialogInterface;
import android.widget.LinearLayout;


public class LoginActivity extends AppCompatActivity {
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
//        final Spinner sp = new Spinner(LoginActivity.this);
//        sp.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        // Set Message
        TextView msg = new TextView(this);
        // Message Properties
        msg.setText("Harap Tunggu");
        msg.setGravity(Gravity.CENTER_HORIZONTAL);
        msg.setTextColor(Color.BLACK);
        alertDialog.setView(msg);

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Batal", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Perform Action on Button
            }
        });

        new Dialog(getApplicationContext());
        alertDialog.show();

        final Button cancelBT = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        LinearLayout.LayoutParams negBtnLP = (LinearLayout.LayoutParams) cancelBT.getLayoutParams();
        negBtnLP.gravity = Gravity.FILL_HORIZONTAL;
        cancelBT.setTextColor(Color.RED);
        cancelBT.setLayoutParams(negBtnLP);
    }
}
