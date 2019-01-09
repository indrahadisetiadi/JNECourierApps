package com.example.ging.jnecourierapps.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ging.jnecourierapps.Activity.DetailPaketActivity;
import com.example.ging.jnecourierapps.Activity.MainActivity;
import com.example.ging.jnecourierapps.R;

import butterknife.ButterKnife;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{

    private Context context;


    public TaskAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_list_card, viewGroup, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goToDetailPacket = new Intent(context, DetailPaketActivity.class);
                    context.startActivity(goToDetailPacket);

                }
            });
        }
    }
}
