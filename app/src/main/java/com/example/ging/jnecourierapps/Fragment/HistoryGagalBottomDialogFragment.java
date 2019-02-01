package com.example.ging.jnecourierapps.Fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ging.jnecourierapps.R;

import org.w3c.dom.Text;


public class HistoryGagalBottomDialogFragment extends BottomSheetDialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button tutup_history_gagal;
        View view = inflater.inflate(R.layout.bottomsheet_history_gagal, container, false);
<<<<<<< HEAD

        TextView noResi = view.findViewById(R.id.no_resi);
        noResi.setText(getArguments().getString("noResi"));

        TextView namaPengirim = view.findViewById(R.id.nama_pengirim);
        namaPengirim.setText(getArguments().getString("namaPengirim"));

        TextView nohpPengirim = view.findViewById(R.id.nohp_pengirim);
        nohpPengirim.setText(getArguments().getString("nohpPengirim"));

        TextView namaPenerima = view.findViewById(R.id.nama_penerima);
        namaPenerima.setText(getArguments().getString("namaPenerima"));

        Log.i("Nama Penerima",getArguments().getString("namaPenerima"));

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

        TextView alasanGagal = view.findViewById(R.id.alasan_gagal);
        alasanGagal.setText(getArguments().getString("alasan_gagal"));

        tutup_history_gagal = view.findViewById(R.id.tutup_history_gagal);
        tutup_history_gagal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryGagalBottomDialogFragment.this.dismiss();
            }
        });
=======
        Log.i("ISI VARIABLE",getArguments().getString("noResi"));
>>>>>>> mail2
        return view;
    }
}
