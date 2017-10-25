package com.example.hadeel.samples;

import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
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

public class HomeActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ActionBarDrawerToggle mDrawerToggle;
    String url ="http://api.aadv01jo2017001.dev.dot.jo/v1/research/researches/available-countries";
    TextView country;
    RecyclerView countryView;
    RecyclerView navView;
    private List<CountryModel> countryModelList;
    ArrayList<DrawerItemModel> navList;
    private CountryAdapter adapter;
    private NavigationAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.HomeTitle));
        toolbar.setTitleTextColor(getResources().getColor(R.color.login));

        countryModelList =new ArrayList<>();
        navList=new ArrayList<>();
        countryView=(RecyclerView)findViewById(R.id.recyclerView);
        countryView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        navView=(RecyclerView)findViewById(R.id.recyclerViewnav);
        navView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);


       setSupportActionBar(toolbar);
       navList = new ArrayList<DrawerItemModel>();

        NavigationAdapter adapter1 = new NavigationAdapter(navList,getBaseContext());
        navView.setLayoutManager(new LinearLayoutManager(this));
        navView.setAdapter(adapter1);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //TODO Add some action here
                //Executed when drawer closes
                Toast.makeText(HomeActivity.this, "Closed", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //TODO Add some action here
                //executes when drawer open
                Toast.makeText(HomeActivity.this, "Opened", Toast.LENGTH_SHORT).show();
            }
        };
        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        jsonRequest();
        preparedData();





    }
    public void preparedData(){
        DrawerItemModel item = new DrawerItemModel();
        item.setIcon(R.drawable.ic_database1);
        item.setTitle(R.string.DataBase);
        navList.add(item);
        DrawerItemModel item2 = new DrawerItemModel();
        item2.setIcon(R.drawable.ic_bottom1);
        item2.setTitle(R.string.Bottoms);
        navList.add(item2);
        DrawerItemModel item3 = new DrawerItemModel();
        item3.setIcon(R.drawable.ic_tab1);
        item3.setTitle(R.string.Tabs);
        navList.add(item3);

        DrawerItemModel item4 = new DrawerItemModel();
        item4.setIcon(R.drawable.ic_settingic);
        item4.setTitle(R.string.Setting);
        navList.add(item4);

        DrawerItemModel item5 = new DrawerItemModel();
        item5.setIcon(R.drawable.ic_calender);
        item5.setTitle(R.string.Calender);
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
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array= jsonObject.getJSONArray("data");
                    for(int i=0;i<array.length();i++){
                        JSONObject obj=array.getJSONObject(i);
                        CountryModel cont=new CountryModel(obj.getString("name"));
                        countryModelList.add(cont);
                    }
                    adapter=new CountryAdapter(countryModelList,getApplicationContext());
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
        MySingletone.getInstance(HomeActivity.this).addTorequestqueue(stringRequest);

    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText=newText.toLowerCase();
        ArrayList<CountryModel> newList=new ArrayList<>();
        for(CountryModel countr: countryModelList){
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
