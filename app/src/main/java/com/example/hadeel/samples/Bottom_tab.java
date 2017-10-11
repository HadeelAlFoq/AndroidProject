package com.example.hadeel.samples;

import android.content.Intent;
import android.graphics.Bitmap;
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

public class Bottom_tab extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<BottomItem> bottomList;
    ArrayList<commntTableModel> commntList;

    private BottomAdapter adapter;
    String getIntentStr,commentCounter,commentCounter1,commentCounter2,commentCounter3;
    TextView name;
    Toolbar toolbar;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bottom_tab);
        Intent g=getIntent();
        getIntentStr=g.getStringExtra("name");
        commentCounter=g.getStringExtra("count");
        commentCounter1=g.getStringExtra("Count1");
        commentCounter2=g.getStringExtra("Count2");
        commentCounter3=g.getStringExtra("Count3");


//        count=commntList.size();
        String c=String.valueOf(count);
        Toast.makeText(getApplication(), commentCounter1,Toast.LENGTH_LONG).show();

       // name=(TextView) findViewById(R.id.textTool);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getIntentStr);

        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView=( BottomNavigationView)findViewById(R.id.bottom);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerViewBottom);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        bottomList=new ArrayList<BottomItem>();
        commntList=new ArrayList<commntTableModel>();


        adapter=new BottomAdapter(bottomList,getApplicationContext());
        recyclerView.setAdapter(adapter);

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

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.content:
                        Intent c=new Intent(Bottom_tab.this,Bottom_tab.class);
                        startActivity(c);
                        break;
                    case R.id.notification:
                        Toast.makeText(Bottom_tab.this,"Notification",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.person:
                        Intent i=new Intent(Bottom_tab.this,personActivity.class);
                        startActivity(i);
                        break;
                    case R.id.option:
                        Intent o=new Intent(Bottom_tab.this,Option_Activity.class);
                        startActivity(o);
                        break;

                }
                return true;
            }
        });
        preparedData();

    }
    public void preparedData(){
        BottomItem item=new BottomItem();
        item.setImage(R.drawable.person);
        item.setImageBackGround(R.drawable.besycle);
        item.setTitle("Omar Alshrbaji");
        item.setCounterComment(commentCounter1);
        bottomList.add(item);
        BottomItem item2=new BottomItem();
        item2.setImage(R.drawable.person);
        item2.setImageBackGround(R.drawable.home);
        item2.setTitle("qosai  Alshrbaji");
        item2.setCounterComment(commentCounter2);

        bottomList.add(item2);
        BottomItem item3=new BottomItem();
        item3.setImage(R.drawable.person);
        item3.setImageBackGround(R.drawable.girl);
        item3.setTitle("dena Alshrbaji");
        item3.setCounterComment(commentCounter3);
        bottomList.add(item3);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
