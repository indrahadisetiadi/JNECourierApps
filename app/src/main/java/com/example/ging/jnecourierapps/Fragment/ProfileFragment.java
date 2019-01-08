package com.example.ging.jnecourierapps.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.ging.jnecourierapps.Activity.LoginActivity;
import com.example.ging.jnecourierapps.Activity.MainActivity;
import com.example.ging.jnecourierapps.R;

import butterknife.BindView;
import butterknife.OnClick;


public class ProfileFragment extends Fragment {

    @BindView(R.id.logout) CardView logout;
    @BindView(R.id.rating) CardView rating;
    @BindView(R.id.kendaraan) CardView kendaraan;
    @BindView(R.id.statistik_paket) CardView statistik_paket;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
//        logout.callOnClick(logout_click());
//        BottomSheetDialog logoutDialog = new BottomSheetDialog(ProfileFragment.this);
//        logoutDialog.setContentView();
//        logoutDialog.show();
    }
}
