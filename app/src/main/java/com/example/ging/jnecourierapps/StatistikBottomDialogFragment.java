package com.example.ging.jnecourierapps;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class StatistikBottomDialogFragment extends BottomSheetDialogFragment {

    Button rating_tutup;
    String[] x_values = {"Sukses", "Gagal", "Pending"};
    PieChart mChart;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_profile_statistik, container, false);
//        setPieChart();
        rating_tutup = view.findViewById(R.id.statistik_tutup);
        rating_tutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatistikBottomDialogFragment.this.dismiss();
            }
        });
        return view;
    }

}