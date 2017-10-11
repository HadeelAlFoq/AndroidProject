package com.instinctcoder.searchwidget;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CustomAdapter customAdapter;
    ListView listView;
    Cursor cursor;
    StudentRepo studentRepo ;
    private final static String TAG= MainActivity.class.getName().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentRepo = new StudentRepo(this);
        cursor=studentRepo.getStudentList();
        customAdapter = new CustomAdapter(MainActivity.this,  cursor, 0);
        listView = (ListView) findViewById(R.id.lstStudent);
        listView.setAdapter(customAdapter);

        if(cursor==null) insertDummy();
    }

    private void insertDummy(){
        Student student= new Student();

        student.age=22;
        student.email="tanwoonhow@intstinctcoder.com";
        student.name="Tan Woon How";
        studentRepo.insert(student);

        studentRepo = new StudentRepo(this);
        student.age=20;
        student.email="Jimmy@intstinctcoder.com";
        student.name="Jimmy Tan Yao Lin";
        studentRepo.insert(student);

        studentRepo = new StudentRepo(this);
        student.age=21;
        student.email="Robert@intstinctcoder.com";
        student.name="Robert Pattinson";
        studentRepo.insert(student);

        studentRepo = new StudentRepo(this);
        student.age=19;
        student.email="jason@intstinctcoder.com";
        student.name="Jason Tan";
        studentRepo.insert(student);


        studentRepo = new StudentRepo(this);
        student.age=18;
        student.email="bftan@intstinctcoder.com";
        student.name="Tan Bor Feng";
        studentRepo.insert(student);


        studentRepo = new StudentRepo(this);
        student.age=23;
        student.email="janet@intstinctcoder.com";
        student.name="Janelle Monae";
        studentRepo.insert(student);


        studentRepo = new StudentRepo(this);
        student.age=21;
        student.email="james@intstinctcoder.com";
        student.name="James Harden";
        studentRepo.insert(student);

    }

    @Override
    public void onResume(){
        super.onResume();

    }

    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options_menu, menu);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
            search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String s) {
                    Log.d(TAG, "onQueryTextSubmit ");
                    cursor=studentRepo.getStudentListByKeyword(s);
                    if (cursor==null){
                        Toast.makeText(MainActivity.this,"No records found!",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this, cursor.getCount() + " records found!",Toast.LENGTH_LONG).show();
                    }
                    customAdapter.swapCursor(cursor);

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    Log.d(TAG, "onQueryTextChange ");
                    cursor=studentRepo.getStudentListByKeyword(s);
                    if (cursor!=null){
                        customAdapter.swapCursor(cursor);
                    }
                    return false;
                }

            });

        }

        return true;

    }

}
