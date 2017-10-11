package com.example.hadeel.samples;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

public class Option_Activity extends AppCompatActivity {
    static final int img_Req=1;
    public static final int CAMERA_REQUEST_CODE=10;
    ImageView image;
    Toolbar toolbar;
    TextView back,name,numberPhone;
    String getintent,getName,getNumber;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_);
        if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED) {

            Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cam, 1);
        }
        else{
            ActivityCompat.requestPermissions(Option_Activity.this,new String[]{Manifest.permission.CAMERA},200);
        }
        Intent g=getIntent();
        getintent=g.getStringExtra("namepage");
        getName=g.getStringExtra("name");
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getName);
        toolbar.setTitleTextColor(getResources().getColor(R.color.login));

        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        numberPhone=(TextView) findViewById(R.id.numPhone);
        image=(ImageView) findViewById(R.id.profile_image);
        //BottomNavigationView bottomNavigationView=( BottomNavigationView)findViewById(R.id.bottom);
        back=(TextView) findViewById(R.id.textTool);
        name=(TextView) findViewById(R.id.nameOption);


        toolbar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
//                Intent b=new Intent(Option_Activity.this,Bottom_tab.class);
//                startActivity(b);
                finish();
            }
        });
        numberPhone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getNumber=numberPhone.getText().toString();
                Intent g=new Intent(Intent.ACTION_DIAL);
                g.setData(Uri.parse("tel:"+getNumber));
                startActivity(g);
            }
        });

        image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                Intent cam=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cam,img_Req);
                selectImage();
            }
        });

//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.content:
//                        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.album1);
//                        Intent c=new Intent(Option_Activity.this,Bottom_tab.class);
//                        c.putExtra("Bitmap",bitmap);
//                        startActivity(c);
//                        break;
//                    case R.id.notification:
////                        Intent intent=new Intent(Option_Activity.this,Bottom_tab.class);
////
////                        image.buildDrawingCache();
////                        Bitmap image1= image.getDrawingCache();
////
////                        Bundle extras = new Bundle();
////                        extras.putParcelable("imagebitmap", image1);
////                        intent.putExtras(extras);
////                        startActivity(intent);
//                                             break;
//                    case R.id.person:
//                        Intent i=new Intent(Option_Activity.this,personActivity.class);
//                        startActivity(i);
//                        break;
//                    case R.id.option:
////                        Intent o=new Intent(Option_Activity.this,Option_Activity.class);
////                        startActivity(o);
//                        Toast.makeText(getApplication(),"you in option already",Toast.LENGTH_LONG).show();
//                        break;
//
//                }
//                return true;
//            }
//        });

    }
//    @Override
//    protected void onActivityResult(int requestCode,int resultCode,Intent data){
//      if(img_Req==requestCode && resultCode==RESULT_OK) {
//          Bundle bundle=data.getExtras();
//          Bitmap bitmapImage=(Bitmap)bundle.get("data");
//          image.setImageBitmap(bitmapImage);
//      }
//    }
private void selectImage() {

    final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

    AlertDialog.Builder builder = new AlertDialog.Builder(Option_Activity.this);
    builder.setTitle("SET Photo!");
    builder.setItems(options, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int item) {
            if (options[item].equals("Take Photo"))
            {

                    if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED) {

                        Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cam, 1);
                    }
                    else{
                       ActivityCompat.requestPermissions(Option_Activity.this,new String[]{Manifest.permission.CAMERA},200);
                    }

//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
//                startActivityForResult(intent, 1);
            }
            else if (options[item].equals("Choose from Gallery"))
            {
                Intent intent = new   Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);

            }
            else if (options[item].equals("Cancel")) {
                dialog.dismiss();
            }
        }
    });
    builder.show();

}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED){
                Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cam, 1);
            }else{
                Toast.makeText(getApplication(),"HHHHHHHHHHHHHHH",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Bundle bundle=data.getExtras();
                Bitmap bitmapImage=(Bitmap)bundle.get("data");
                image.setImageBitmap(bitmapImage);
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                image.setImageBitmap(thumbnail);
            }
        }
    }
}