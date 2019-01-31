package com.example.ging.jnecourierapps.Fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ging.jnecourierapps.R;

public class GagalBottomDialogFragment extends BottomSheetDialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button tutup_history_foto;
        View view = inflater.inflate(R.layout.bottomsheet_gagal, container, false);
        tutup_history_foto = view.findViewById(R.id.gagalSubmit);
        tutup_history_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GagalBottomDialogFragment.this.dismiss();
            }
        });
        return view;
    }
}
