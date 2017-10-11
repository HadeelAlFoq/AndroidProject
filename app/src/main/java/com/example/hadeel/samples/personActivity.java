package com.example.hadeel.samples;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class personActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    personAdapter adapter;
    ArrayList<personItem> personList;
    Toolbar toolbar;
    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_activity);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Add friend");
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
        BottomNavigationView bottomNavigationView=( BottomNavigationView)findViewById(R.id.bottom);





        personList= new ArrayList<personItem>();
        recyclerView=(RecyclerView)findViewById(R.id.personRececlerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
        adapter=new personAdapter(personList,getApplicationContext());
        recyclerView.setAdapter(adapter);
        preparedData();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.content:
                        Intent c=new Intent(personActivity.this,Bottom_tab.class);
                        startActivity(c);
                        break;
                    case R.id.notification:
                        Toast.makeText(personActivity.this,"Notification",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.person:
                       Toast.makeText(getApplication(),"You in person activity",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.option:
                        Intent o=new Intent(personActivity.this,Option_Activity.class);
                        o.putExtra("namepage","Profile");

                        startActivity(o);
                        break;

                }
                return true;
            }
        });



    }
    public void preparedData(){
        personItem item=new personItem();
        item.setPicPerson(R.drawable.person);
        item.setName("Ahmed Kamal");
        personList.add(item);

        personItem item1=new personItem();
        item1.setPicPerson(R.drawable.person);
        item1.setName("karem Kaml");
        personList.add(item1);

        personItem item2=new personItem();
        item2.setPicPerson(R.drawable.person);
        item2.setName("karem Kaml");
        personList.add(item2);

        personItem item3=new personItem();
        item3.setPicPerson(R.drawable.person);
        item3.setName("dena saad");
        personList.add(item3);

        personItem item4=new personItem();
        item4.setPicPerson(R.drawable.person);
        item4.setName("lelas omar");
        personList.add(item4);

    }
}
