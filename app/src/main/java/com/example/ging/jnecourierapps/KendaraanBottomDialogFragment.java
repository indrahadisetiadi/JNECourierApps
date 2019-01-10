package com.example.ging.jnecourierapps;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class KendaraanBottomDialogFragment extends BottomSheetDialogFragment {

    Button kendaraan_tutup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_profile_kendaraan, container, false);
        kendaraan_tutup = view.findViewById(R.id.kendaraan_tutup);
        kendaraan_tutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KendaraanBottomDialogFragment.this.dismiss();
            }
        });
        return view;
    }
}
