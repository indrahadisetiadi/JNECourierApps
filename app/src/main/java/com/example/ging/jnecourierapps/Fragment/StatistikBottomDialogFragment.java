package com.example.ging.jnecourierapps.Fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ging.jnecourierapps.R;

public class StatistikBottomDialogFragment extends BottomSheetDialogFragment {

    Button rating_tutup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_profile_statistik, container, false);
        rating_tutup = view.findViewById(R.id.statistik_tutup);
        rating_tutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatistikBottomDialogFragment.this.dismiss();
            }
        });
        return view;
    }

}