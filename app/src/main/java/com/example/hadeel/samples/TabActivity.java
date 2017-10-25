package com.example.hadeel.samples;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TabActivity extends AppCompatActivity {
    TextView name;
    String getIntentStr;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        Intent i=getIntent();
        getIntentStr=i.getStringExtra("name");


        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getIntentStr);
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
        viewPager=(ViewPager)findViewById(R.id.viewpager);


        tabLayout=(TabLayout)findViewById(R.id.tablayout);

        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstFragment(),"ORDER Orange");
        adapter.addFragment(new InOrderFragment(),"In Order");
        adapter.addFragment(new SnakFragment(),"OUT ORDER");
        adapter.addFragment(new AppChoosesFragment(),"App ORDER");


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
       // name=(TextView) findViewById(R.id.textTool);

    }
}