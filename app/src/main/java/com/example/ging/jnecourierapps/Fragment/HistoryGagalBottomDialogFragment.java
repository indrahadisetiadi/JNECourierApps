package com.example.ging.jnecourierapps.Fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ging.jnecourierapps.R;

import butterknife.BindView;

public class HistoryGagalBottomDialogFragment extends BottomSheetDialogFragment {
    Button tutup_history_gagal;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button tutup_history_gagal;
        View view = inflater.inflate(R.layout.bottomsheet_history_gagal, container, false);
        Log.i("ISI VARIABLE",getArguments().getString("noResi"));
        return view;
    }
}
