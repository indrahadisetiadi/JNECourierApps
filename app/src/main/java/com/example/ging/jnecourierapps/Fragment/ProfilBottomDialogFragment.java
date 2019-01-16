package com.example.ging.jnecourierapps.Fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ging.jnecourierapps.R;
import com.example.ging.jnecourierapps.Session.SessionManager;

public class ProfilBottomDialogFragment extends BottomSheetDialogFragment {

    Button kendaraan_tutup;
    SessionManager sessionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_profile_profil, container, false);
        kendaraan_tutup = view.findViewById(R.id.kendaraan_tutup);

        TextView nama, nik, status,kecamatan;
        nama = view.findViewById(R.id.namaKurir);
        nik = view.findViewById(R.id.nikKurir);
        status = view.findViewById(R.id.status);
        kecamatan = view.findViewById(R.id.kecamatan);

        sessionManager = new SessionManager(getContext());
        nama.setText(sessionManager.getProfile().getNama_kurir());
        nik.setText(sessionManager.getKey());
        status.setText(sessionManager.getProfile().getStatus());
        kecamatan.setText(sessionManager.getProfile().getKecamatan() + " | " + sessionManager.getProfile().getKendaraan_kurir());


        kendaraan_tutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfilBottomDialogFragment.this.dismiss();
            }
        });
        return view;
    }
}
