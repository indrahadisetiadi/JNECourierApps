package com.example.ging.jnecourierapps.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.ging.jnecourierapps.Activity.MainActivity;
import com.example.ging.jnecourierapps.R;

import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ging.jnecourierapps.HistoryBottomDialog;

import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{

    private Context context;
    CardView history_card;

    public HistoryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_list_card, viewGroup, false);
        ViewHolder holder = new ViewHolder(v);
//        history_card = viewGroup.findViewById(R.id.history_card);
//        history_card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("HEHEHE", "BISA DONG BROOOOOOOOO");
////                BottomSheetDialogFragment bottomSheetDialogFragment = new HistoryBottomDialog();
////                bottomSheetDialogFragment.show(((MainActivity)mContext).getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
//            }
//        });
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
                    Log.i("HEHEHE", "BISA DONG BROOOOOOOOO");
                }
            });
        }
    }
}
