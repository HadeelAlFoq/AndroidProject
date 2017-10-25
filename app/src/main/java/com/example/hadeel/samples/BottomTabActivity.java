package com.example.hadeel.samples;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class BottomTabActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<BottomItem> bottomList=new ArrayList<>();;
    ArrayList<CommntTableModel> commntList;
    public int cPosition = 0;

    private BottomAdapter adapter;
    static String getIntentStr, commentCounter, commentCounter1, commentCounter2, commentCounter3;
    TextView name;
    Toolbar toolbar;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bottom_tab);
       Intent g = getIntent();
        getIntentStr=g.getStringExtra("name");


        String c = String.valueOf(count);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getIntentStr);

        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewBottom);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        commntList = new ArrayList<CommntTableModel>();


        adapter = new BottomAdapter(bottomList, getApplicationContext(), new BottomAdapter.OnCommentClick() {
            @Override
            public void onClommentClickListener(BottomItem item, int position) {
                startActivityForResult(new Intent(getApplicationContext()
                                , CommentTableActivity.class)
                                .putExtra("name", "comment")
                                .putExtra("position", position)
                        .putExtra("count",Integer.parseInt(item.getCounterComment()!= null
                                ?!item.getCounterComment().isEmpty()?item.getCounterComment():"0":"0"))
                        , 250);

                cPosition = position;
            }
        });
        recyclerView.setAdapter(adapter);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

        }


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(BottomTabActivity.this,HomeActivity.class);
                startActivity(i);
                onBackPressed();
                finish();
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.content:
                        Intent c = new Intent(BottomTabActivity.this, BottomTabActivity.class);
                        startActivity(c);
                        break;
                    case R.id.notification:
                        Toast.makeText(BottomTabActivity.this, "Notification", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.person:
                        Intent i = new Intent(BottomTabActivity.this, PersonActivity.class);
                        startActivity(i);
                        break;
                    case R.id.option:
                        Intent o = new Intent(BottomTabActivity.this, OptionActivity.class);
                        startActivity(o);
                        break;

                }
                return true;
            }
        });
        preparedData();
    }

    public void preparedData() {
        BottomItem item = new BottomItem();
        item.setImage(R.drawable.person);
        item.setImageBackGround(R.drawable.besycle);
        item.setTitle("Omar Alshrbaji");
        item.setCounterComment(commentCounter1);
        bottomList.add(item);
        BottomItem item2 = new BottomItem();
        item2.setImage(R.drawable.person);
        item2.setImageBackGround(R.drawable.home);
        item2.setTitle("qosai  Alshrbaji");
        item2.setCounterComment(commentCounter2);

        bottomList.add(item2);
        BottomItem item3 = new BottomItem();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 250 && resultCode == Activity.RESULT_OK) {
            switch (cPosition) {
                case 0:
                    bottomList.remove(0);
                    BottomItem item = new BottomItem();
                    item.setImage(R.drawable.person);
                    item.setImageBackGround(R.drawable.besycle);
                    item.setTitle("Omar Alshrbaji");
                    item.setCounterComment(data.getExtras().getString("count"));
                    bottomList.add(0,item);
                    break;
                case 1:
                    bottomList.remove(1);
                    BottomItem item2 = new BottomItem();
                    item2.setImage(R.drawable.person);
                    item2.setImageBackGround(R.drawable.home);
                    item2.setTitle("qosai  Alshrbaji");
                    item2.setCounterComment(data.getExtras().getString("count"));
                    bottomList.add(1,item2);
                    break;
                case 2:
                    bottomList.remove(2);
                    BottomItem item3 = new BottomItem();
                    item3.setImage(R.drawable.person);
                    item3.setImageBackGround(R.drawable.girl);
                    item3.setTitle("dena Alshrbaji");
                    item3.setCounterComment(data.getExtras().getString("count"));
                    bottomList.add(2,item3);
                    break;
            }

            adapter.notifyDataSetChanged();

        }
    }

}
