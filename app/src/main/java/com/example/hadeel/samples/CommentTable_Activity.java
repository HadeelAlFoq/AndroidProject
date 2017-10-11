package com.example.hadeel.samples;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CommentTable_Activity extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabaseObj;
    SqliteHelper db;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<commntTableModel>commntList;
    EditText comment;
    String CommentText,getintentText;
    ImageButton sendComment;
    Toolbar toolbar;
    Cursor cursor;
    CommentAdapter Adapter ;
    RecyclerView recyclerViewComment;
    TextView back;
    public  boolean edit=false;
    static int  count1;
    static int count2;
    static int count3;

    String Count1,Count2,Count3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_table_);
        Intent g=getIntent();
        getintentText=g.getStringExtra("name");
        String posi=g.getStringExtra("position");
        final int position=Integer.parseInt(posi);
        Toast.makeText(getApplication(),posi,Toast.LENGTH_LONG).show();




        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Comment");
        toolbar.setTitleTextColor(getResources().getColor(R.color.login));

        setSupportActionBar(toolbar);
        back=(TextView) findViewById(R.id.textTool);
        db = new SqliteHelper(this);
        recyclerViewComment=(RecyclerView)findViewById(R.id.recyclerViewComment);
        recyclerViewComment.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        commntList=new ArrayList<commntTableModel>();
        sendComment=(ImageButton)findViewById(R.id.insert);
        comment=(EditText)findViewById(R.id.commentEText);

        sendComment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //SQLiteDataBaseBuild();
                //SQLiteTableBuild();
                insertComment();
                ShowDBData();
                edit=true;
               // counter=commntList.size();
                //String c=String.valueOf(counter);

               Toast.makeText(getApplication(),"dena",Toast.LENGTH_LONG).show();



            }

        });
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position)
                {
                    case 0:
                        if(edit){
                           count1=count1+1;
                            Count1=String.valueOf(count1);
                            Intent i=new Intent(CommentTable_Activity.this,Bottom_tab.class);
                            Bundle bundle = new Bundle();
                            //Add your data from getFactualResults method to bundle
                            bundle.putString("Count1", Count1);
                            //Add the bundle to the intent
                            i.putExtras(bundle);

                            //Fire the second activity
                            startActivity(i);


                        }
                        break;
                    case 1:
                        if(edit){
                            count2=count2+1;
                            Count2=String.valueOf(count2);
                            Intent i=new Intent(CommentTable_Activity.this,Bottom_tab.class);
                            i.putExtra("Count2",Count2);
                            startActivity(i);
                        }
                        break;
                    case 2:
                        if(edit){
                            count3=count3+1;
                            Count3=String.valueOf(count3);
                            Intent i=new Intent(CommentTable_Activity.this,Bottom_tab.class);
                            i.putExtra("Count3",Count3);
                            startActivity(i);
                        }
                        break;


                }
                onBackPressed();


            }
        });
    }



    protected void onResume() {
        //ShowDBData();
      super.onResume();
    }
    public void SQLiteDataBaseBuild() {

        sqLiteDatabaseObj = openOrCreateDatabase(SqliteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    public void SQLiteTableBuild() {

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + SqliteHelper.TABLE_NAME_2 + "(" + SqliteHelper.Table2_Column_ID + " INTEGER PRIMARY KEY, " + SqliteHelper.Table2_Column_1_Comment + " VARCHAR);");

    }
    public void insertComment(){
        CommentText=comment.getText().toString();
        if(CommentText.matches("")){
            Toast.makeText(getApplication(),"Please Enter Comment",Toast.LENGTH_LONG).show();
        }
        else {
            db.insertComment(CommentText);
           // counter=counter+1;
            comment.setText(" ");
            edit=true;


            Toast.makeText(this, "SECCESSFUL INSERT " + CommentText, Toast.LENGTH_LONG).show();
        }
    }
    private void ShowDBData() {
        sqLiteDatabase = db.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SqliteHelper.TABLE_NAME_2 + "", null);
        if (cursor.moveToFirst()) {
            do {
                commntTableModel comment = new commntTableModel();

                comment.setComment(cursor.getString(cursor.getColumnIndex(SqliteHelper.Table2_Column_1_Comment)));
                commntList.add(comment);

            } while (cursor.moveToNext());


        }
        Adapter=new CommentAdapter(CommentTable_Activity.this,commntList);
        recyclerViewComment.setAdapter(Adapter);
        cursor.close();
    }



}
