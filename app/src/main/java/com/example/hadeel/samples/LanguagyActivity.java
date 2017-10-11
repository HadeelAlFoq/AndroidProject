package com.example.hadeel.samples;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LanguagyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private List<String> lang;
    Toolbar toolbar;
    TextView back;
    String getintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_languagy);

        Intent g=getIntent();
        getintent=g.getStringExtra("name");



        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getintent);
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

        back= (TextView) findViewById(R.id.textTool);
        spinner=(Spinner)findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(this);
        lang=new ArrayList<String>();
        lang.add("Franch");
        lang.add("عربي");
        lang.add("english");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lang);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("Language");
        spinner.setAdapter(adapter);

//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                switch (position){
//                    case 1:
//                        setLang("ar");
//                        Toast.makeText(getApplication(),"Arbic",Toast.LENGTH_LONG).show();
//                        break;
//                    case 2 :
//                        setLang("en");
//                        Toast.makeText(getApplication(),"english",Toast.LENGTH_LONG).show();
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });



    }
   private void setLang(String lang){
       Locale myLocal= new Locale(lang);
       Locale.setDefault(myLocal);
       Configuration config = new Configuration();
       config.locale = myLocal;
       getBaseContext().getResources().updateConfiguration(config,
               getBaseContext().getResources().getDisplayMetrics());
       finish();
       Intent i=new Intent(LanguagyActivity.this,MainActivity.class);
       startActivity(i);
   }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String language= parent.getItemAtPosition(position).toString();
        switch (language){
            case "Language":
               // setLang("ar");
                Toast.makeText(getApplication(),"Arbic",Toast.LENGTH_LONG).show();
                break;

                    case "عربي":
                        setLang("ar");
                        Toast.makeText(getApplication(),"Arbic",Toast.LENGTH_LONG).show();
                        break;
                    case "english":
                        setLang("en");
                        Toast.makeText(getApplication(),"english",Toast.LENGTH_LONG).show();

                }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
