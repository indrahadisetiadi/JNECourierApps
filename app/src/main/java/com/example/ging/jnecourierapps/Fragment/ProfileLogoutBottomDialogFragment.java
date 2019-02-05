package com.example.ging.jnecourierapps.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ging.jnecourierapps.Activity.LoginActivity;
import com.example.ging.jnecourierapps.Activity.MainActivity;
import com.example.ging.jnecourierapps.R;
import com.example.ging.jnecourierapps.Session.SessionManager;

public class ProfileLogoutBottomDialogFragment extends BottomSheetDialogFragment {

    Button logoout_iya;
    Button logout_tidak;
    SessionManager sessionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_profile_logout, container, false);
        logoout_iya = view.findViewById(R.id.logout_iya);

        logoout_iya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager = new SessionManager(getContext());
                sessionManager.Logout();
                Intent LoginPage = new Intent(getContext(), LoginActivity.class);
                startActivity(LoginPage);
                getActivity().finish();
            }
        });

        return view;
    }
}
