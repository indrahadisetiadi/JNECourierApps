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
    PieChart pieChart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_profile_statistik, container, false);
        rating_tutup = view.findViewById(R.id.statistik_tutup);
        rating_tutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatistikBottomDialogFragment.this.dismiss();
            }
        });
        //piechart
        pieChart = pieChart.findViewById(R.id.pie_chart);
        pieChart.setUsePercentValues(true);
        ArrayList <PieEntry> y_values = new ArrayList<>();
        y_values.add(new PieEntry(8f, 0));
        y_values.add(new PieEntry(15f, 1));
        y_values.add(new PieEntry(12f, 2));
        y_values.add(new PieEntry(25f, 3));
        y_values.add(new PieEntry(23f, 4));
        y_values.add(new PieEntry(17f, 5));
        PieDataSet dataSet = new PieDataSet(y_values,"Election Results");
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate();
        return view;
    }

}