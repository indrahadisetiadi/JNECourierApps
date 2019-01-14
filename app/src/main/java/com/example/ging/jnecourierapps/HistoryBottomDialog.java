package com.example.ging.jnecourierapps;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HistoryBottomDialog extends BottomSheetDialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button tutup_history_berhasil,history_foto_lihat;
        View view = inflater.inflate(R.layout.bottomsheet_history, container, false);
        tutup_history_berhasil = view.findViewById(R.id.tutup_history_berhasil);
        tutup_history_berhasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryBottomDialog.this.dismiss();
            }
        });

        history_foto_lihat = view.findViewById(R.id.history_foto_lihat);
        history_foto_lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogFragment bottomSheetDialogFragment = new HistoryFotoBottomDialog();
                bottomSheetDialogFragment.show(getFragmentManager(),bottomSheetDialogFragment.getTag());
            }
        });
        return view;
    }
}
