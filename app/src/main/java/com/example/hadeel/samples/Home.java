package com.example.hadeel.samples;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ActionBarDrawerToggle mDrawerToggle;
    String url ="http://api.aadv01jo2017001.dev.dot.jo/v1/research/researches/available-countries";
    TextView country;
    RecyclerView countryView;
    RecyclerView navView;
    private List<country> countryList;
    ArrayList<DrawerItem> navList;
    private CountryAdapter adapter;
    private NavigationAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        toolbar.setTitleTextColor(getResources().getColor(R.color.login));

        countryList=new ArrayList<>();
        navList=new ArrayList<>();
        countryView=(RecyclerView)findViewById(R.id.recyclerView);
        countryView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        navView=(RecyclerView)findViewById(R.id.recyclerViewnav);
        navView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
       // NavigationView navigationView=(NavigationView)findViewById(R.id.navigationView);


       setSupportActionBar(toolbar);
       navList = new ArrayList<DrawerItem>();

       NavigationAdapter adapter1 = new NavigationAdapter(navList,getBaseContext());
        navView.setLayoutManager(new LinearLayoutManager(this));
        navView.setAdapter(adapter1);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //TODO Add some action here
                //Executed when drawer closes
                Toast.makeText(Home.this, "Closed", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //TODO Add some action here
                //executes when drawer open
                Toast.makeText(Home.this, "Opened", Toast.LENGTH_SHORT).show();
            }
        };
        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        jsonRequest();
        preparedData();





    }
    public void preparedData(){
        DrawerItem item = new DrawerItem();
        item.setIcon(R.drawable.ic_database1);
        item.setTitle("DataBase");
        navList.add(item);
        DrawerItem item2 = new DrawerItem();
        item2.setIcon(R.drawable.ic_bottom1);
        item2.setTitle("Bottoms");
        navList.add(item2);
        DrawerItem item3 = new DrawerItem();
        item3.setIcon(R.drawable.ic_tab1);
        item3.setTitle("Tabs");
        navList.add(item3);

        DrawerItem item4 = new DrawerItem();
        item4.setIcon(R.drawable.ic_settingic);
        item4.setTitle("Setting");
        navList.add(item4);

        DrawerItem item5 = new DrawerItem();
        item5.setIcon(R.drawable.ic_calender);
        item5.setTitle("Calender");
        navList.add(item5);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.searchview,menu);
        MenuItem search=menu.findItem(R.id.search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(search);
        searchView.setQueryHint("Search...");
        searchView.setOnQueryTextListener(this);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return  super.onOptionsItemSelected(item);
    }



    private void jsonRequest() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(getApplication(),response,Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array= jsonObject.getJSONArray("data");
                    for(int i=0;i<array.length();i++){
                        JSONObject obj=array.getJSONObject(i);
                        country cont=new country(obj.getString("name"));
                        countryList.add(cont);
                    }
                    adapter=new CountryAdapter(countryList,getApplicationContext());
                    countryView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication(),error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
        MySingletone.getInstance(Home.this).addTorequestqueue(stringRequest);

    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText=newText.toLowerCase();
        ArrayList<country> newList=new ArrayList<>();
        for(country countr:countryList){
            String name=countr.getCountry().toLowerCase();
            if (name.contains(newText)) {
                newList.add(countr);
            }
        }
        CountryAdapter.Filter(newList);
        adapter.notifyDataSetChanged();

        return true;
    }
}
