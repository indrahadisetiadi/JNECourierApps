package com.example.ging.jnecourierapps.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.example.ging.jnecourierapps.Fragment.GagalBottomDialogFragment;
import com.example.ging.jnecourierapps.Fragment.ProfileLogoutBottomDialogFragment;
import com.example.ging.jnecourierapps.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailPaketActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 20;
    ImagePopup imagePopup;
    public String latitude, longitude;
    public @BindView(R.id.navigasiMaps) Button googleMaps;
    public @BindView(R.id.openCamera) FloatingActionButton openCamera;
    public @BindView(R.id.imagePlaceholder) ImageView imagePlaceholder;
    public @BindView(R.id.nomorHpPengirim) TextView nomorhppengirim;
    public @BindView(R.id.nomorHpPenerima) TextView nomorhppenerima;
    public @BindView(R.id.detailResi) TextView detailResi;
    public @BindView(R.id.berat) TextView berat;
    public @BindView(R.id.dikirim) TextView dikirm;
    public @BindView(R.id.detailAlamat) TextView alamat;
    public @BindView(R.id.layanan) TextView layanan;
    public @BindView(R.id.namaPengirim) TextView namaPengirim;
    public @BindView(R.id.namaPenerima) TextView namaPenerima;
    public @BindView(R.id.gagalBtn) Button gagalBtn;


    String tujuan = new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_paket);
        ButterKnife.bind(this);
        TextView alamat = this.findViewById(R.id.detailAlamat);

        Intent goToDetailPacket = getIntent();
        detailResi.setText(goToDetailPacket.getStringExtra("resi"));
        namaPengirim.setText(goToDetailPacket.getStringExtra("namaPengirim"));
        nomorhppengirim.setText(goToDetailPacket.getStringExtra("hpPengirim"));
        namaPenerima.setText(goToDetailPacket.getStringExtra("namaPenerima"));
        layanan.setText(goToDetailPacket.getStringExtra("layanan"));
        berat.setText(goToDetailPacket.getStringExtra("berat") + " KG");
        dikirm.setText(goToDetailPacket.getStringExtra("tanggal"));
        alamat.setText(goToDetailPacket.getStringExtra("alamat"));
        latitude = goToDetailPacket.getStringExtra("latitude");
        longitude = goToDetailPacket.getStringExtra("longitude");


        imagePopup = new ImagePopup(this);
        googleMaps = this.findViewById(R.id.navigasiMaps);
        openCamera = this.findViewById(R.id.openCamera);

        gagalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogFragment bottomSheetDialogFragment = new GagalBottomDialogFragment();
                bottomSheetDialogFragment.show(getSupportFragmentManager(),"hehe");
            }
        });

        final String tujuan = alamat.getText().toString();
        googleMapsButton();
        openCameraButton();

        if (ContextCompat.checkSelfPermission(DetailPaketActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(DetailPaketActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);
        }

        nomorhppengirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("PHONE KIRIM", nomorhppengirim.getText().toString());
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+nomorhppengirim.getText().toString()));
                startActivity(callIntent);

            }
        });

        nomorhppenerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("PHONE TERIMA", nomorhppenerima.getText().toString());
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+nomorhppenerima.getText().toString()));
                startActivity(callIntent);
            }
        });

    }


    protected void callNoHp(){}

    protected void scaleImage(){
            imagePlaceholder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imagePopup.viewPopup();
                }
            });
    };
    protected void openCameraButton(){
        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoCaptureIntent, CAMERA_REQUEST);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.CAMERA_REQUEST == requestCode && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            imagePlaceholder.setImageBitmap(bitmap);
            imagePopup.initiatePopup(imagePlaceholder.getDrawable());
            scaleImage();
        }
    }
    protected void googleMapsButton(){
        googleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+latitude+","+longitude);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }

}
