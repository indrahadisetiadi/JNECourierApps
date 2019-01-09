package com.example.ging.jnecourierapps.Fragment;


import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ging.jnecourierapps.ProfileLogoutBottomDialogFragment;
import com.example.ging.jnecourierapps.R;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ProfileFragment extends Fragment {



    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }


    @Override
    public void onStart() {

        CardView logout = view.findViewById(R.id.logout);

        super.onStart();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogFragment bottomSheetDialogFragment = new ProfileLogoutBottomDialogFragment();
                bottomSheetDialogFragment.show(getFragmentManager(),bottomSheetDialogFragment.getTag());
            }
        });

    }
}
