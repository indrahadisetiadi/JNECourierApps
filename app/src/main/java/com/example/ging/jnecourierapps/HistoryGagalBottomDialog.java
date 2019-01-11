package com.example.ging.jnecourierapps;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HistoryGagalBottomDialog extends BottomSheetDialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button tutup_history_gagal;
        View view = inflater.inflate(R.layout.bottomsheet_history, container, false);
        tutup_history_gagal = view.findViewById(R.id.tutup_history_gagal);
        tutup_history_gagal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryGagalBottomDialog.this.dismiss();
            }
        });
        return view;
    }
}
