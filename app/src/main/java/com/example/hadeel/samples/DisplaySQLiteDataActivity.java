package com.example.hadeel.samples;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplaySQLiteDataActivity extends AppCompatActivity {
    SqliteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    DataBaseAdapter Adapter ;
    RecyclerView recyclerView;
    TextView delete,name,lastName;
    FloatingActionButton add;
    Toolbar toolbar;
    String getintent;
    ArrayList<String> NAME_Array;
    ArrayList<String> Phone_Array;
    ArrayList<String> Email_Array;
    ArrayList<String> Age_Array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_sqlite_data);
        Intent g=getIntent();
        getintent=g.getStringExtra("name");


        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.DataBase));
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

        recyclerView=(RecyclerView)findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        NAME_Array = new ArrayList<String>();

        Phone_Array = new ArrayList<String>();
        Email_Array = new ArrayList<String>();
        Age_Array = new ArrayList<String>();

        sqLiteHelper = new SqliteHelper(this);
        add=(FloatingActionButton) findViewById(R.id.add);
        lastName=(TextView) findViewById(R.id.lastNameText);

        delete=(TextView) findViewById(R.id.delete);
        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(DisplaySQLiteDataActivity.this,InsertDataActivity.class);
               startActivity(i);

            }
        });

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getintent);

        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

        }


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DisplaySQLiteDataActivity.this,HomeActivity.class);
                startActivity(i);
                onBackPressed();

                finish();
            }
        });

    }  @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        SharedPreferences prefer=getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor=prefer.edit();
        String Name=prefer.getString("NAME",null);
        lastName.setText(Name);






        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SqliteHelper.TABLE_NAME+"", null);


        NAME_Array.clear();
        Phone_Array.clear();
        Email_Array.clear();
        Age_Array.clear();

        if (cursor.moveToFirst()) {
            do {



                NAME_Array.add(cursor.getString(cursor.getColumnIndex(SqliteHelper.Table_Column_1_Name)));

                Phone_Array.add(cursor.getString(cursor.getColumnIndex(SqliteHelper.Table_Column_2_PhoneNumber)));
                Email_Array.add(cursor.getString(cursor.getColumnIndex(SqliteHelper.Table_Column_3_Email)));
                Age_Array.add(cursor.getString(cursor.getColumnIndex(SqliteHelper.Table_Column_4_Age)));


            } while (cursor.moveToNext());
        }

        Adapter = new DataBaseAdapter(DisplaySQLiteDataActivity.this,


                NAME_Array,
                Phone_Array,
                Email_Array,
                Age_Array
        );

        recyclerView.setAdapter(Adapter);

        cursor.close();
    }

    public void deleteRow()
    {
        String value="hala";
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + SqliteHelper.TABLE_NAME+ " WHERE "+SqliteHelper.Table_Column_1_Name+"='"+value+"'");
        db.close();
    }

}