package com.example.hadeel.samples;

import android.content.Intent;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Massage extends AppCompatActivity {
    EditText subject,to,message;
    TextView toolbarText;
    Button record,send,email;
    Toolbar toolbar;
    String Subject,To,Message,getintentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massage);

        Intent g=getIntent();
        getintentText=g.getStringExtra("name");



        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getintentText);
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
        toolbarText= (TextView) findViewById(R.id.textTool);
        subject=(EditText) findViewById(R.id.subjectContent);
        to=(EditText) findViewById(R.id.resever);
        message=(EditText) findViewById(R.id.messageContent);
        email=(Button) findViewById(R.id.sendEmail);

       // record=(Button) findViewById(R.id.record);

        send=(Button) findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                To=to.getText().toString();
                Message=message.getText().toString();
                Intent sendIntent= new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"+To));
                sendIntent.putExtra(Intent.EXTRA_TEXT,Message);
                startActivity(sendIntent);
            }
        });
        email.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                To=to.getText().toString();
                Message=message.getText().toString();
                Subject=subject.getText().toString();
                Intent emailIntent= new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_TEXT,Message);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT,Subject);
                emailIntent.putExtra(Intent.EXTRA_EMAIL,To
                );
                emailIntent.setType("text/html");



                startActivity(Intent.createChooser(emailIntent,"select your Email"));
            }
        });

        message.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent record= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                record.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                record.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                if(record.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(record,10);
                }
                else {
                    Toast.makeText(getApplication(),"Your device cannot support speech",Toast.LENGTH_LONG);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode){
            case 10:
        if(resultCode==RESULT_OK && data != null) {
            ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            message.setText(result.get(0));
        }
        break;
        }
    }
}
