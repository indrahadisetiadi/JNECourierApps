package com.example.ging.jnecourierapps.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.ging.jnecourierapps.Activity.MainActivity;
import com.example.ging.jnecourierapps.Fragment.HistoryGagalBottomDialogFragment;
import com.example.ging.jnecourierapps.Model.GetHistory;
import com.example.ging.jnecourierapps.R;

import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ging.jnecourierapps.Fragment.HistoryBottomDialogFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private Context mContext;
    CardView history_card, history_card_1;
    private List<GetHistory> getHistoryList;
    Bundle bundle = new Bundle();

    public HistoryAdapter(Context mContext, List<GetHistory> getHistoryList) {
        this.mContext = mContext;
        this.getHistoryList = getHistoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_list_card, viewGroup, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        GetHistory getHistory = getHistoryList.get(i);
        if(getHistory.getStatus_pengiriman().equals("GAGAL")){
            Log.i("GAGAL","INI YANG GAGAL");
            viewHolder.card_berhasil.setVisibility(View.GONE);
            viewHolder.resi_gagal.setText(getHistory.getNo_resi());
            viewHolder.penerima_gagal.setText(getHistory.getNama_penerima());
            viewHolder.waktu_gagal.setText(getHistory.getWaktu_penerima());
            viewHolder.status_gagal.setText(getHistory.getStatus_pengiriman());
            viewHolder.alasan_gagal.setText(getHistory.getAlasan_gagal());
        }
        else if(getHistory.getStatus_pengiriman().equals("SUKSES")){
            Log.i("SUKSES","INI YANG SUKSES");
            viewHolder.card_gagal.setVisibility(View.GONE);
            viewHolder.resi_berhasil.setText(getHistory.getNo_resi());
            viewHolder.penerima_berhasil.setText(getHistory.getNama_penerima());
            viewHolder.waktu_berhasil.setText(getHistory.getWaktu_penerima());
            viewHolder.status_berhasil.setText(getHistory.getStatus_pengiriman());
            viewHolder.foto.setText(getHistory.getFoto_pengiriman());
        }
        viewHolder.tujuan.setText(getHistory.getAlamat() + getHistory.getDesa_kelurahan() + getHistory.getPropinsi());
        viewHolder.berat.setText(getHistory.getBerat());
        viewHolder.tanggalDikirim.setText(getHistory.getTanggal_masuk());
        viewHolder.jenisLayanan.setText(getHistory.getTipe_paket());
        viewHolder.nohpPenerima.setText(getHistory.getNo_hp_penerima());
        viewHolder.namaPenerima.setText(getHistory.getNo_hp_pengirim());
        viewHolder.nohpPengirim.setText(getHistory.getNo_hp_pengirim());
        viewHolder.namaPengirim.setText(getHistory.getNama_pengirim());
        viewHolder.noResi.setText(getHistory.getNo_resi());
    }

    @Override
    public int getItemCount() {
        Integer sum = getHistoryList.size();
        Log.i("arr size", sum.toString());
        return sum;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.history_card_berhasil) CardView card_berhasil;
        @BindView(R.id.resi_berhasil) TextView resi_berhasil;
        @BindView(R.id.penerima_berhasil) TextView penerima_berhasil;
        @BindView(R.id.waktu_berhasil) TextView waktu_berhasil;
        @BindView(R.id.status_berhasil) TextView status_berhasil;

        @BindView(R.id.history_card_gagal) CardView card_gagal;
        @BindView(R.id.resi_gagal) TextView resi_gagal;
        @BindView(R.id.penerima_gagal) TextView penerima_gagal;
        @BindView(R.id.waktu_gagal) TextView waktu_gagal;
        @BindView(R.id.status_gagal) TextView status_gagal;

        TextView noResi = new TextView(mContext);
        TextView namaPengirim = new TextView(mContext);
        TextView nohpPengirim = new TextView(mContext);
        TextView namaPenerima = new TextView(mContext);
        TextView nohpPenerima = new TextView(mContext);
        TextView jenisLayanan = new TextView(mContext);
        TextView tanggalDikirim = new TextView(mContext);
        TextView berat = new TextView(mContext);
        TextView tujuan = new TextView(mContext);
        TextView foto = new TextView(mContext);
        TextView alasan_gagal = new TextView(mContext);




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            bundle.putString("noResi",noResi.toString());
            bundle.putString("namaPengirim",namaPengirim.toString());
            bundle.putString("nohpPengirim",nohpPengirim.toString());
            bundle.putString("namaPenerima",namaPenerima.toString());
            bundle.putString("nohpPenerima",nohpPenerima.toString());
            bundle.putString("jenisLayanan",jenisLayanan.toString());
            bundle.putString("tanggalDikirim",tanggalDikirim.toString());
            bundle.putString("berat",berat.toString());
            bundle.putString("tujuan",tujuan.toString());
            history_card = itemView.findViewById(R.id.history_card_gagal);
            history_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("HEHEHE", "BISA DONG BROOOOOOOOO");
                    bundle.putString("alasan_gagal",alasan_gagal.toString());
                    BottomSheetDialogFragment bottomSheetDialogFragment = new HistoryGagalBottomDialogFragment();
                    bottomSheetDialogFragment.setArguments(bundle);
                    bottomSheetDialogFragment.show(((MainActivity)mContext).getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
                }
            });

            history_card_1 = itemView.findViewById(R.id.history_card_berhasil);
            history_card_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("HEHEHE", "BISA DONG BROOOOOOOOO");
                    bundle.putString("foto",foto.toString());
                    BottomSheetDialogFragment bottomSheetDialogFragment = new HistoryBottomDialogFragment();
                    bottomSheetDialogFragment.setArguments(bundle);
                    bottomSheetDialogFragment.show(((MainActivity)mContext).getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
                }
            });
        }
    }
}
