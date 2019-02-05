package com.example.ging.jnecourierapps.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ging.jnecourierapps.Interfaces.DeliveryAPI;
import com.example.ging.jnecourierapps.Model.ResponseTask;
import com.example.ging.jnecourierapps.R;
import com.example.ging.jnecourierapps.Url.BaseUrl;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GagalBottomDialogFragment extends BottomSheetDialogFragment {
    BaseUrl baseUrl = new BaseUrl();
    TextView alasan, nomorResi;
    String user_id, no_resi, alasann;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button tutup_history_foto;
        View view = inflater.inflate(R.layout.bottomsheet_gagal, container, false);
        tutup_history_foto = view.findViewById(R.id.gagalSubmit);
        alasan = view.findViewById(R.id.alasanGagal);

        tutup_history_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alasann = alasan.getText().toString();
                Log.i("ARGDSS ON CLICK", no_resi + " - " + user_id+ " - " + alasann);
                paketGagal(no_resi, user_id, alasann);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle mArgs = getArguments();
        user_id = mArgs.getString("id_kurir");
        no_resi = mArgs.getString("noresi");
        Log.i("ARGDSS", no_resi + " - " + user_id);


    }

    private void paketGagal(String no_resi, String user_id, String alasann){
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl.getUrl()).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        DeliveryAPI deliveryAPI = retrofit.create(DeliveryAPI.class);


        Call<ResponseTask> responseCall = deliveryAPI.submitPaketGagal(user_id, no_resi, alasann);
        responseCall.enqueue(new Callback<ResponseTask>() {
            @Override

            public void onResponse(Call<ResponseTask> call, retrofit2.Response<ResponseTask> response) {
                final Toast toast = Toast.makeText(getActivity(), "Loading...", Toast.LENGTH_LONG);
                toast.show();
                if (response.body().getError().equals("0")){
                    toast.cancel();
                    final Toast toastComplete = Toast.makeText(getActivity(), "Complete", Toast.LENGTH_LONG);
                    toastComplete.show();
                    GagalBottomDialogFragment.this.dismiss();
                    getActivity().finish();
//                    DetailPaketActivity detailPaketActivity = new DetailPaketActivity();
//                    detailPaketActivity.closeDetailPaketActivity();
                }
                Log.i("PAKET GAGAL", response.body().getError());
            }

            @Override
            public void onFailure(Call<ResponseTask> call, Throwable t) {
                Log.i("PAKET GAGAL ERR", t.getMessage());
                final Toast toast = Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_LONG);
                toast.show();
            }
        });






    }
}
