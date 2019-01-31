package com.example.ging.jnecourierapps.Fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ging.jnecourierapps.R;

public class HistoryBottomDialogFragment extends BottomSheetDialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button tutup_history_berhasil,history_foto_lihat;
        View view = inflater.inflate(R.layout.bottomsheet_history, container, false);
//        tutup_history_berhasil = view.findViewById(R.id.gagalSubmit);
//        tutup_history_berhasil.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                HistoryBottomDialogFragment.this.dismiss();
//            }
//        });

        return view;
    }
}
