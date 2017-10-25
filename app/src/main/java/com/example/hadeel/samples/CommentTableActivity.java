package com.example.hadeel.samples;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CommentTableActivity extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabaseObj;
    SqliteHelper db;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<CommntTableModel>commntList;
    EditText comment;
    String CommentText,getintentText;
    ImageButton sendComment;
    Toolbar toolbar;
    Cursor cursor;
    CommentAdapter Adapter ;
    RecyclerView recyclerViewComment;
    TextView back;
    public  boolean edit=false;
    int  count=0;

    String cCount;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_table_);
        Intent g = getIntent();
        getintentText = g.getStringExtra("name");
        int posi = g.getExtras().getInt("position");
        final int position = posi;
        count = g.getExtras().getInt("count");
        //  Toast.makeText(getApplication(),posi+"",Toast.LENGTH_LONG).show();


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.commenttitle));
        toolbar.setTitleTextColor(getResources().getColor(R.color.login));

        setSupportActionBar(toolbar);

        db = new SqliteHelper(this);
        recyclerViewComment = (RecyclerView) findViewById(R.id.recyclerViewComment);
        recyclerViewComment.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        commntList = new ArrayList<CommntTableModel>();

        sendComment = (ImageButton) findViewById(R.id.insert);
        comment = (EditText) findViewById(R.id.commentEText);
        sendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (insertComment()) {
                    switch (position) {
                        case 0:
                            if (edit) {
                                count = count + 1;
                                cCount = String.valueOf(count);


                            }
                            break;
                        case 1:
                            if (edit) {
                                count = count + 1;
                                cCount = String.valueOf(count);

                            }
                            break;
                        case 2:
                            if (edit) {
                                count = count + 1;
                                cCount = String.valueOf(count);


                            }
                    }
                }


//


            }

        });

        ShowDBData();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setResult(Activity.RESULT_OK,new Intent(this,BottomTabActivity.class)
        .putExtra("count",cCount));
        finish();
        return super.onOptionsItemSelected(item);
    }

    protected void onStart(){
        super.onStart();

    }



    protected void onResume() {
       // ShowDBData();
      super.onResume();

    }

    public boolean insertComment(){
        CommentText=comment.getText().toString();
        if(CommentText.matches("")){
            Toast.makeText(getApplication(),"Please Enter Comment",Toast.LENGTH_LONG).show();
            edit=false;
        }
        else {
            db.insertComment(CommentText);

            ShowDBData();

            edit=true;

            comment.setText("");



            Toast.makeText(this, "SECCESSFUL INSERT " + CommentText, Toast.LENGTH_LONG).show();

        }
        return edit;
    }
     void ShowDBData() {
        sqLiteDatabase = db.getWritableDatabase();
         commntList.clear();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SqliteHelper.TABLE_NAME_2 + "", null);
        if (cursor.moveToFirst()) {
            do {
                CommntTableModel comment = new CommntTableModel();

                comment.setComment(cursor.getString(cursor.getColumnIndex(SqliteHelper.Table2_Column_1_Comment)));
                commntList.add(comment);



            } while (cursor.moveToNext());


        }

        Adapter=new CommentAdapter(CommentTableActivity.this,commntList);
        recyclerViewComment.setAdapter(Adapter);

        //Adapter.notifyDataSetChanged();
        cursor.close();


    }


}
