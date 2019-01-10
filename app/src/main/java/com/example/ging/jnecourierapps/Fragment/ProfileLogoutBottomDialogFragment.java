package com.example.ging.jnecourierapps.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ging.jnecourierapps.R;

public class ProfileLogoutBottomDialogFragment extends BottomSheetDialogFragment {

    Button logoout_iya;
    Button logout_tidak;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_profile_logout, container, false);
        logoout_iya = view.findViewById(R.id.logout_iya);
        logout_tidak = view.findViewById(R.id.logout_tidak);

        logoout_iya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileLogoutBottomDialogFragment.this.dismiss();
//                Intent goToActivity;
//                goToActivity = new Intent(ProfileLogoutBottomDialogFragment.this, LoginActivity.class);
//                startActivity(goToActivity);
            }
        });

        logout_tidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileLogoutBottomDialogFragment.this.dismiss();
            }
        });

        return view;
    }
}
