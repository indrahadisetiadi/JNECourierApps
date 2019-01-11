package com.example.ging.jnecourierapps.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.example.ging.jnecourierapps.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailPaketActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 20;
    ImagePopup imagePopup;
    boolean isImageNotNull = (imagePopup != null);
    public @BindView(R.id.navigasiMaps) Button googleMaps;
    public @BindView(R.id.openCamera) FloatingActionButton openCamera;
    public @BindView(R.id.imagePlaceholder) ImageView imagePlaceholder;
    String tujuan = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_paket);
        ButterKnife.bind(this);

        imagePopup = new ImagePopup(this);
        googleMaps = this.findViewById(R.id.navigasiMaps);
        openCamera = this.findViewById(R.id.openCamera);
        TextView alamat = this.findViewById(R.id.detailAlamat);
        final String tujuan = alamat.getText().toString();

        googleMapsButton();
        openCameraButton();
        scaleImage();
    }

    protected void scaleImage(){
        if(isImageNotNull) {
            imagePlaceholder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imagePopup.viewPopup();
                }
            });
        }
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
        }
    }



    protected void googleMapsButton(){

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
