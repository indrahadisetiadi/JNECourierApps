package com.example.ging.jnecourierapps.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ging.jnecourierapps.Adapter.HistoryAdapter;
import com.example.ging.jnecourierapps.Adapter.TaskAdapter;
import com.example.ging.jnecourierapps.Interfaces.GetHistoryAPI;
import com.example.ging.jnecourierapps.Interfaces.GetTaskAPI;
import com.example.ging.jnecourierapps.Model.GetHistory;
import com.example.ging.jnecourierapps.Model.ResponseHistory;
import com.example.ging.jnecourierapps.R;
import com.example.ging.jnecourierapps.Url.BaseUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryFragment extends Fragment {
    ProgressBar progressBarTask;
    SwipeRefreshLayout mySwipeRefreshLayout;
    RecyclerView historyList;
    HistoryAdapter historyAdapter;
    View viewTemp;
    private List<GetHistory> getHistoryList = new ArrayList<>();
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
                load_task();
                Log.i("LOAD","Lagi load brok");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        viewTemp = view;
        historyAdapter = new HistoryAdapter(getContext(),getHistoryList);
        historyList = view.findViewById(R.id.historyList);
        historyList.setLayoutManager(new LinearLayoutManager(getActivity()));
        historyList.setItemAnimator(new DefaultItemAnimator());
        historyList.setAdapter(historyAdapter);
        mySwipeRefreshLayout = view.findViewById(R.id.historyRefresh);
        load_task();
        return view;
    }

    private void load_task(){
        final Toast toast = Toast.makeText(getActivity(), "Updating...", Toast.LENGTH_LONG);
        toast.show();

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl.getUrl()).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        GetHistoryAPI getHistoryAPI = retrofit.create(GetHistoryAPI.class);

        Call<ResponseHistory> responseCall = getHistoryAPI.getHistory("22");
        responseCall.enqueue(new Callback<ResponseHistory>() {
            @Override
            public void onResponse(Call<ResponseHistory> call, retrofit2.Response<ResponseHistory> response) {
                Log.i("TASK", response.body().getError());
                if (response.body().getError().equals("0")) {
                    mySwipeRefreshLayout.setRefreshing(false);
                    toast.cancel();
                    historyAdapter = new HistoryAdapter(getContext(), response.body().getMessage());
                    historyList.setAdapter(historyAdapter);
                } else {
                    mySwipeRefreshLayout.setRefreshing(false);
                    final Toast toast = Toast.makeText(getActivity(), "Try Again, Swipe to refresh", Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<ResponseHistory> call, Throwable t) {
                Log.i("TASK onFail", t.getMessage());
                final Toast toast = Toast.makeText(getActivity(), "Try Again, Swipe to refresh", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
