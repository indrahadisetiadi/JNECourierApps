package com.example.ging.jnecourierapps.Fragment;


import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ging.jnecourierapps.R;
import com.example.ging.jnecourierapps.Session.SessionManager;

public class ProfileFragment extends Fragment {

    View view;
    SessionManager sessionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sessionManager = new SessionManager(getContext());
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView userName = view.findViewById(R.id.user_profile_name);

        userName.setText(sessionManager.getProfile().getNama_kurir());

        return view;
    }


    @Override
    public void onStart() {
        CardView logout = view.findViewById(R.id.logout_akun);
        CardView rating = view.findViewById(R.id.rating);
        CardView kendaraan = view.findViewById(R.id.kendaraan);
        CardView statistik = view.findViewById(R.id.statistik_paket);

        super.onStart();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogFragment bottomSheetDialogFragment = new ProfileLogoutBottomDialogFragment();
                bottomSheetDialogFragment.show(getFragmentManager(),bottomSheetDialogFragment.getTag());
            }
        });

        super.onStart();
        rating.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                BottomSheetDialogFragment bottomSheetDialogFragment = new RatingBottomDialogFragment();
                bottomSheetDialogFragment.show(getFragmentManager(),bottomSheetDialogFragment.getTag());
            }
        });

        super.onStart();
        statistik.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                BottomSheetDialogFragment bottomSheetDialogFragment = new StatistikBottomDialogFragment();
                bottomSheetDialogFragment.show(getFragmentManager(),bottomSheetDialogFragment.getTag());
            }
        });

        super.onStart();
        kendaraan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                BottomSheetDialogFragment bottomSheetDialogFragment = new ProfilBottomDialogFragment();
                bottomSheetDialogFragment.show(getFragmentManager(),bottomSheetDialogFragment.getTag());
            }
        });

    }
}

