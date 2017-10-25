package com.example.hadeel.samples;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OptionActivity extends AppCompatActivity {
    static final int img_Req=1;
    private static final int PICK_FROM_GALLERY = 1;
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
//
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

        name=(TextView) findViewById(R.id.nameOption);
        name.setText(getName);


        toolbar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
//                Intent b=new Intent(OptionActivity.this,BottomTabActivity.class);
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
//
                selectImage();
            }
        });

  }
private void selectImage() {

    final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

    AlertDialog.Builder builder = new AlertDialog.Builder(OptionActivity.this);
    builder.setTitle("SET Photo!");
    builder.setItems(options, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int item) {
            if (options[item].equals("Take Photo"))
            {

                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

                        Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cam, 1);
                    } else {
                        ActivityCompat.requestPermissions(OptionActivity.this, new String[]{Manifest.permission.CAMERA}, 200);
                    }

            }
            else if (options[item].equals("Choose from Gallery"))
            {
                if (ActivityCompat.checkSelfPermission(OptionActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(OptionActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }

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
                Toast.makeText(getApplication(),"No permission",Toast.LENGTH_LONG).show();
            }
        }
        if (requestCode == 2) {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }else{
                Toast.makeText(getApplication(),"No permission",Toast.LENGTH_LONG).show();
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