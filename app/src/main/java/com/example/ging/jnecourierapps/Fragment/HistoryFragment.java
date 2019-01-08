package com.example.ging.jnecourierapps.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ging.jnecourierapps.Adapter.HistoryAdapter;
import com.example.ging.jnecourierapps.Adapter.TaskAdapter;
import com.example.ging.jnecourierapps.R;

public class HistoryFragment extends Fragment {

    SwipeRefreshLayout mySwipeRefreshLayout;
    RecyclerView historyList;
    HistoryAdapter historyAdapter;
    View viewTemp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        viewTemp = view;

        historyAdapter = new HistoryAdapter(getContext());


        historyList = view.findViewById(R.id.historyList);
        historyList.setLayoutManager(new LinearLayoutManager(getActivity()));
        historyList.setItemAnimator(new DefaultItemAnimator());
        historyList.setAdapter(historyAdapter);

        return view;
    }
}
