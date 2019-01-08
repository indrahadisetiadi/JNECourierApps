package com.example.ging.jnecourierapps.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ging.jnecourierapps.Adapter.TaskAdapter;
import com.example.ging.jnecourierapps.R;

import butterknife.ButterKnife;

public class TaskFragment extends Fragment {

    SwipeRefreshLayout mySwipeRefreshLayout;
    RecyclerView tasklist;
    TaskAdapter taskAdapter;
    View viewTemp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("TSK", "TASK VIEW");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        viewTemp = view;

        taskAdapter = new TaskAdapter(getContext());

        tasklist = view.findViewById(R.id.taskList);
        tasklist.setLayoutManager(new LinearLayoutManager(getActivity()));
        tasklist.setItemAnimator(new DefaultItemAnimator());
        tasklist.setAdapter(taskAdapter);

        return view;
    }
    




}
