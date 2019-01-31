package com.example.ging.jnecourierapps.Adapter;

import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ging.jnecourierapps.Activity.DetailPaketActivity;
import com.example.ging.jnecourierapps.Activity.MainActivity;
import com.example.ging.jnecourierapps.Model.GetTask;
import com.example.ging.jnecourierapps.R;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.RequiresApi;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{

    private Context context;
    private List<GetTask> getTaskList;


    public TaskAdapter(Context context, List<GetTask> getTaskList) {
        this.context = context;
        this.getTaskList = getTaskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_list_card, viewGroup, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    private String timeFormatHelper(Integer tot_seconds){
        int p1 = tot_seconds % 60;
        int p2 = tot_seconds / 60;
        int p3 = p2 % 60;
        p2 = p2 / 60;
        return String.format("%02d Hour %02d Minutes", p2, p3);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        GetTask getTask = getTaskList.get(i);
        viewHolder.resi.setText(getTask.getNo_resi());
        viewHolder.waktu.setText(timeFormatHelper(getTask.getEstimasi_sampe()));
        viewHolder.penerima.setText(getTask.getNama_penerima());
        String jarak = getTask.getJarak() + " - " + getTask.getKecamatan();
        viewHolder.jarak.setText(jarak);

        viewHolder.hpPengirim.setText(getTask.getNo_hp_pengirim());
        viewHolder.hpPenerima.setText(getTask.getNo_hp_penerima());
        viewHolder.alamat.setText(getTask.getAlamat());
        viewHolder.berat.setText(getTask.getBerat());
        viewHolder.tanggal.setText(getTask.getTanggal_masuk());
        viewHolder.layanan.setText(getTask.getTipe_paket());
        viewHolder.namaPengirim.setText(getTask.getNama_pengirim());

        viewHolder.latitude.setText(getTask.getLatitude().toString());
        viewHolder.longitude.setText(getTask.getLongitude().toString());
    }

    @Override
    public int getItemCount() {
        Integer sum = getTaskList.size();
        Log.i("arr size", sum.toString());
        return getTaskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.resi) TextView resi;
        @BindView(R.id.penerima) TextView penerima;
        @BindView(R.id.waktu) TextView waktu;
        @BindView(R.id.jarak) TextView jarak;

        TextView alamat = new TextView(context);
        TextView layanan = new TextView(context);
        TextView tanggal = new TextView(context);
        TextView berat = new TextView(context);
        TextView hpPenerima = new TextView(context);
        TextView namaPengirim = new TextView(context);
        TextView hpPengirim = new TextView(context);
        TextView latitude = new TextView(context);
        TextView longitude = new TextView(context);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goToDetailPacket = new Intent(context, DetailPaketActivity.class);
                    goToDetailPacket.putExtra("resi", resi.getText());
                    goToDetailPacket.putExtra("namaPengirim", namaPengirim.getText());
                    goToDetailPacket.putExtra("hpPengirim", hpPengirim.getText());
                    goToDetailPacket.putExtra("namaPenerima", penerima.getText());
                    goToDetailPacket.putExtra("hpPenerima", hpPenerima.getText());
                    goToDetailPacket.putExtra("layanan", layanan.getText());
                    goToDetailPacket.putExtra("berat", berat.getText());
                    goToDetailPacket.putExtra("tanggal", tanggal.getText());
                    goToDetailPacket.putExtra("alamat", alamat.getText());
                    goToDetailPacket.putExtra("latitude", latitude.getText());
                    goToDetailPacket.putExtra("longitude", longitude.getText());
                    context.startActivity(goToDetailPacket);
                }
            });
        }
    }
}
