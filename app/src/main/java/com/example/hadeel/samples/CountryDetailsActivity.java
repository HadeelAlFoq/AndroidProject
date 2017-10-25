package com.example.hadeel.samples;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CountryDetailsActivity extends AppCompatActivity {
    String nameCountry;
    TextView countryToo,call,location,website;
    FloatingActionButton message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);
        Intent g=getIntent();
        nameCountry=g.getStringExtra("name");
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(nameCountry);
        toolbar.setTitleTextColor(getResources().getColor(R.color.login));

        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

        }
        message=(FloatingActionButton) findViewById(R.id.message);
        call=(TextView) findViewById(R.id.call);
        location=(TextView)findViewById(R.id.location);
        website=(TextView)findViewById(R.id.website);

        Toast.makeText(getApplication(),nameCountry,Toast.LENGTH_LONG).show();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        website.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i=new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse("https://www.orange.jo/ar/pages/default.aspx"));
                            startActivity(i);
            }
        });
        call.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent g=new Intent(Intent.ACTION_DIAL);
                g.setData(Uri.parse("tel:1555"));
                startActivity(g);

            }
        });
        location.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent l=new Intent(CountryDetailsActivity.this,GoogleMapActivity.class);
                l.putExtra("name",getString(R.string.maptitle));
                startActivity(l);

            }
        });
        message.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent m=new Intent(CountryDetailsActivity.this,MassageActivity.class);
                m.putExtra("name",getString(R.string.message));
                startActivity(m);
            }
        });
    }
}
