package com.example.ging.jnecourierapps.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ging.jnecourierapps.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailPaketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_paket);
        ButterKnife.bind(this);

        Button googleMaps = this.findViewById(R.id.navigasiMaps);
        TextView alamat = this.findViewById(R.id.detailAlamat);
        final String tujuan = alamat.getText().toString();

        googleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+tujuan);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

    }
}
