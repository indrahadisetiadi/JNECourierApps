package com.example.ging.jnecourierapps.Fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ging.jnecourierapps.R;
import com.example.ging.jnecourierapps.Url.BaseUrl;
import com.squareup.picasso.Picasso;

public class HistoryFotoBottomDialogFragment extends BottomSheetDialogFragment {
    ImageView foto_bukti;
    BaseUrl baseUrl = new BaseUrl();
    String foto;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button tutup_history_foto;
//        Log.i("Fotonya",getArguments().getString("foto"));
        foto = baseUrl.getImagesUrl() + getArguments().getString("foto");
        Log.i("FOTO",foto);
        View view = inflater.inflate(R.layout.bottomsheet_history_foto, container, false);
<<<<<<< HEAD
        foto_bukti = view.findViewById(R.id.foto_bukti);
        Picasso.get().load(foto).into(foto_bukti);
        tutup_history_foto = view.findViewById(R.id.tutup_history_foto);
        tutup_history_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryFotoBottomDialogFragment.this.dismiss();
            }
        });
=======
>>>>>>> mail2
        return view;
    }
}
