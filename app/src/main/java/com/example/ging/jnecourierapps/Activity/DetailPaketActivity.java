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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.example.ging.jnecourierapps.Fragment.GagalBottomDialogFragment;
import com.example.ging.jnecourierapps.Fragment.ProfileLogoutBottomDialogFragment;
import com.example.ging.jnecourierapps.Interfaces.DeliveryAPI;
import com.example.ging.jnecourierapps.Model.ResponseTask;
import com.example.ging.jnecourierapps.R;
import com.example.ging.jnecourierapps.Session.SessionManager;
import com.example.ging.jnecourierapps.Url.BaseUrl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailPaketActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 20;
    BaseUrl baseUrl = new BaseUrl();
    Bitmap theImage;
    EditText yangnerima;


    ImagePopup imagePopup;
    public String latitude, longitude, resi;
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
    public @BindView(R.id.berhasilBtn) Button berhasilBtn;

    String tujuan = new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_paket);
        ButterKnife.bind(this);
        TextView alamat = this.findViewById(R.id.detailAlamat);

        final Intent goToDetailPacket = getIntent();
        resi = goToDetailPacket.getStringExtra("resi");
        detailResi.setText(resi);
        namaPengirim.setText(goToDetailPacket.getStringExtra("namaPengirim"));
        nomorhppengirim.setText(goToDetailPacket.getStringExtra("hpPengirim"));
        namaPenerima.setText(goToDetailPacket.getStringExtra("namaPenerima"));
        layanan.setText(goToDetailPacket.getStringExtra("layanan"));
        berat.setText(goToDetailPacket.getStringExtra("berat") + " KG");
        dikirm.setText(goToDetailPacket.getStringExtra("tanggal"));
        alamat.setText(goToDetailPacket.getStringExtra("alamat"));
        latitude = goToDetailPacket.getStringExtra("latitude");
        longitude = goToDetailPacket.getStringExtra("longitude");

        yangnerima = findViewById(R.id.yangnerima);

        imagePopup = new ImagePopup(this);
        googleMaps = this.findViewById(R.id.navigasiMaps);
        openCamera = this.findViewById(R.id.openCamera);
        final SessionManager sessionManager = new SessionManager(DetailPaketActivity.this);

        gagalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("noresi", detailResi.getText().toString());
                args.putString("id_kurir",sessionManager.getterUserId());
                BottomSheetDialogFragment bottomSheetDialogFragment = new GagalBottomDialogFragment();
                bottomSheetDialogFragment.setArguments(args);
                bottomSheetDialogFragment.show(getSupportFragmentManager(),"hehe");
            }
        });

        berhasilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("BERHASIL", detailResi.getText().toString());
                Log.i("BERHASIL", sessionManager.getterUserId());
                if (imagePlaceholder.getDrawable() == null && yangnerima.getText().toString().isEmpty()){
                    Log.i("BERHASIL", "FOTO KOSONG");
                    Log.i("BERHASIL YG", yangnerima.getText().toString() + " -");
                    final Toast toast = Toast.makeText(DetailPaketActivity.this, "No Photo & Receiver Name", Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    try {
                        berhasil(theImage,sessionManager.getterUserId(), detailResi.getText().toString(), yangnerima.getText().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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

    protected void berhasil(Bitmap bitmap,String id_kurir,String no_resi, String penerima) throws IOException {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();


        File f = new File(this.getCacheDir(), "theImage");
        f.createNewFile();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bitmapdata = bos.toByteArray();

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(bitmapdata);
        fos.flush();
        fos.close();

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), f);
        MultipartBody.Part body = MultipartBody.Part.createFormData("foto_pengiriman", "theImage", reqFile);
        MultipartBody.Part id_kurirF = MultipartBody.Part.createFormData("id_kurir", id_kurir);
        MultipartBody.Part no_resiF = MultipartBody.Part.createFormData("no_resi", no_resi);
        MultipartBody.Part yangnerima = MultipartBody.Part.createFormData("yangnerima", penerima);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl.getUrl()).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        DeliveryAPI deliveryAPI = retrofit.create(DeliveryAPI.class);
        Call<ResponseBody> responseBodyCall = deliveryAPI.submitPaketSukses(body, id_kurirF, no_resiF, yangnerima);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                final Toast toast = Toast.makeText(DetailPaketActivity.this, "Uploading...", Toast.LENGTH_LONG);
                toast.show();
                Log.i("UPLOAD FOTO", response.message());
                if (response.message().equals("OK")){
                    final Toast toastComplete = Toast.makeText(DetailPaketActivity.this, "Complete!", Toast.LENGTH_LONG);
                    toastComplete.show();
                    DetailPaketActivity.this.finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("UPLOAD FOTO", t.getMessage());
                final Toast toast = Toast.makeText(DetailPaketActivity.this, "Error, Try Again", Toast.LENGTH_LONG);
                toast.show();
            }
        });





    }

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
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.CAMERA_REQUEST == requestCode && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            theImage = bitmap;
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

    public void closeDetailPaketActivity(){
        this.finish();
    }

}
