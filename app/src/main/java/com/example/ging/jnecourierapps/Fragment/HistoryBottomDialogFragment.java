package com.example.ging.jnecourierapps.Fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ging.jnecourierapps.Activity.MainActivity;
import com.example.ging.jnecourierapps.R;

public class HistoryBottomDialogFragment extends BottomSheetDialogFragment {
    String foto;
    Bundle bundle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button tutup_history_berhasil,history_foto_lihat;
        View view = inflater.inflate(R.layout.bottomsheet_history, container, false);

        TextView noResi = view.findViewById(R.id.no_resi);
        noResi.setText(getArguments().getString("noResi"));

        TextView namaPengirim = view.findViewById(R.id.nama_pengirim);
        namaPengirim.setText(getArguments().getString("namaPengirim"));

        TextView nohpPengirim = view.findViewById(R.id.nohp_pengirim);
        nohpPengirim.setText(getArguments().getString("nohpPengirim"));

        TextView namaPenerima = view.findViewById(R.id.nama_penerima);
        namaPenerima.setText(getArguments().getString("namaPenerima"));

        TextView nohpPenerima = view.findViewById(R.id.nohp_penerima);
        nohpPenerima.setText(getArguments().getString("nohpPenerima"));

        TextView jenisLayanan = view.findViewById(R.id.jenis_layanan);
        jenisLayanan.setText(getArguments().getString("jenisLayanan"));

        TextView waktuDikirim = view.findViewById(R.id.waktu_dikirim);
        waktuDikirim.setText(getArguments().getString("tanggalDikirim"));

        TextView berat = view.findViewById(R.id.berat_paket);
        berat.setText(getArguments().getString("berat") + " Kg");

        TextView detailAlamat = view.findViewById(R.id.detailAlamat);
        detailAlamat.setText(getArguments().getString("tujuan"));

        foto = getArguments().getString("foto");
        Log.i("FOTO",foto);


        tutup_history_berhasil = view.findViewById(R.id.tutup_history_berhasil);
        tutup_history_berhasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryBottomDialogFragment.this.dismiss();
            }
        });

        history_foto_lihat = view.findViewById(R.id.history_foto_lihat);
        history_foto_lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putString("foto",foto);
                BottomSheetDialogFragment bottomSheetDialogFragment = new HistoryFotoBottomDialogFragment();
                bottomSheetDialogFragment.setArguments(bundle);
                bottomSheetDialogFragment.show(getActivity().getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
            }
        });

        return view;
    }
}
