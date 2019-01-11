package com.example.ging.jnecourierapps;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HistoryFotoBottomDialog  extends BottomSheetDialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button tutup_history_foto;
        View view = inflater.inflate(R.layout.bottomsheet_history_foto, container, false);
        tutup_history_foto = view.findViewById(R.id.tutup_history_foto);
        tutup_history_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryFotoBottomDialog.this.dismiss();
            }
        });
        return view;
    }
}
