package com.example.ging.jnecourierapps.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ging.jnecourierapps.Activity.MainActivity;
import com.example.ging.jnecourierapps.Adapter.TaskAdapter;
import com.example.ging.jnecourierapps.Interfaces.GetTaskAPI;
import com.example.ging.jnecourierapps.Model.GetTask;
import com.example.ging.jnecourierapps.Model.ResponseTask;
import com.example.ging.jnecourierapps.R;
import com.example.ging.jnecourierapps.Session.SessionManager;
import com.example.ging.jnecourierapps.Url.BaseUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskFragment extends Fragment {

    SwipeRefreshLayout mySwipeRefreshLayout;
    RecyclerView tasklist;
    private List<GetTask> getTaskList = new ArrayList<>();
    TaskAdapter taskAdapter;
    SessionManager sessionManager;
    ProgressBar progressBarTask;
    View viewTemp;
    public int state;
    BaseUrl baseUrl = new BaseUrl();

    @Override
    public void onStart() {
        super.onStart();
        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i("PULLL", "PULLL");
                final Toast toast = Toast.makeText(getActivity(), "Updating...", Toast.LENGTH_LONG);
                toast.show();
                loadTask();
                Log.i("LOAD","Lagi load brok");
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sessionManager = new SessionManager(getActivity());
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        viewTemp = view;

        taskAdapter = new TaskAdapter(getContext(),getTaskList);
        tasklist = view.findViewById(R.id.taskList);
        tasklist.setLayoutManager(new LinearLayoutManager(getActivity()));
        tasklist.setItemAnimator(new DefaultItemAnimator());
        tasklist.setAdapter(taskAdapter);
        progressBarTask = viewTemp.findViewById(R.id.taskProgress);
        loadTask();
        state = 1;
        mySwipeRefreshLayout = view.findViewById(R.id.taskRefresh);

        return view;
    }


    private void loadTask(){

        final Toast toast = Toast.makeText(getActivity(), "Updating...", Toast.LENGTH_LONG);
        toast.show();

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl.getUrl()).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        GetTaskAPI getTaskAPI = retrofit.create(GetTaskAPI.class);


        Call<ResponseTask> responseCall = getTaskAPI.getPaket("22",sessionManager.getLatitude(),sessionManager.getLongitude());
        Toast.makeText(getActivity(),sessionManager.getLatitude() + "," + sessionManager.getLongitude(), Toast.LENGTH_LONG).show();
        responseCall.enqueue(new Callback<ResponseTask>() {
            @Override
            public void onResponse(Call<ResponseTask> call, retrofit2.Response<ResponseTask> response) {
                taskAdapter = new TaskAdapter(getContext(),getTaskList);
                tasklist.setAdapter(taskAdapter);

                if (response.body() != null){
                    if (response.body().getError().equals("0")){
                        mySwipeRefreshLayout.setRefreshing(false);
                        toast.cancel();
                        progressBarTask.setVisibility(View.GONE);

                        taskAdapter = new TaskAdapter(getContext(), response.body().getMessage());
                        tasklist.setAdapter(taskAdapter);
                    }else{
                        mySwipeRefreshLayout.setRefreshing(false);
                        final Toast toast = Toast.makeText(getActivity(), "Try Again, Swipe to refresh", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseTask> call, Throwable t) {
                Log.i("TASK onFail", t.getMessage());
                final Toast toast = Toast.makeText(getActivity(), "Try Again, Swipe to refresh", Toast.LENGTH_LONG);
                toast.show();
                mySwipeRefreshLayout.setRefreshing(false);

            }
        });

    }
}
