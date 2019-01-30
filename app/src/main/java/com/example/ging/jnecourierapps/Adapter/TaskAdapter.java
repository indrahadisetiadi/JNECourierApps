package com.example.ging.jnecourierapps.Adapter;

import android.content.Context;
import android.content.Intent;
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

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    private String timeFormatHelper(Integer tot_second){
        int tot_seconds = 5000;
        int hours = tot_seconds / 3600;
        int minutes = (tot_seconds % 3600) / 60;
        int seconds = tot_seconds % 60;

        return String.format("%02d Hour %02d Minutes", hours, minutes);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        GetTask getTask = getTaskList.get(i);
        viewHolder.resi.setText(getTask.getNo_resi());
        viewHolder.waktu.setText(timeFormatHelper(getTask.getEstimasi()));
        viewHolder.penerima.setText(getTask.getNama_penerima());
        String jarak = getTask.getJarak() + " - " + getTask.getKecamatan();
        viewHolder.jarak.setText(jarak);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("NORESI TASK", resi.getText().toString());
                    Intent goToDetailPacket = new Intent(context, DetailPaketActivity.class);
                    context.startActivity(goToDetailPacket);
                }
            });
        }
    }
}
