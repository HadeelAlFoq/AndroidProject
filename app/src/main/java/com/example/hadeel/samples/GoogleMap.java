package com.example.hadeel.samples;

import android.app.Dialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import static android.app.PendingIntent.getActivity;
//import com.google.android.gms.common.GoogleApiAvailability;
//import com.google.android.gms.maps.MapFragment;
//import com.google.android.gms.maps.OnMapReadyCallback;

public class GoogleMap extends AppCompatActivity implements OnMapReadyCallback {
    com.google.android.gms.maps.GoogleMap mGoogleMap;
    Toolbar toolbar;
    TextView toolBarText;
    String getintentText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_google_map);



        if (googleServicesAvilable()){
            Toast.makeText(getApplication(),"cant connect google services",Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_google_map);

            initMap();
        }
//        final ImageButton option=(ImageButton)findViewById(R.id.option);
//        option.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view){
//                showPopupMenu(option);
//
//            }
//        });
        Intent g=getIntent();
        getintentText=g.getStringExtra("name");


        toolbar=(Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle(getintentText);
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

    }

    private void initMap() {
        MapFragment mapFragment= (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public boolean googleServicesAvilable(){
        GoogleApiAvailability api=GoogleApiAvailability.getInstance();
        int isAvailable=api.isGooglePlayServicesAvailable(this);
        if(isAvailable== ConnectionResult.SUCCESS){
            return true;
        }
        else if(api.isUserResolvableError(isAvailable)){
                Dialog dialog=api.getErrorDialog(this,isAvailable,0);
                dialog.show();
            }
            else {
            Toast.makeText(getApplication(),"cant connect google services",Toast.LENGTH_LONG).show();
        }

        return false;
    }




    @Override
    public void onMapReady(com.google.android.gms.maps.GoogleMap googleMap) {
        mGoogleMap=googleMap;
        goToLocation(31.8354533,36.2278171,5);
        mGoogleMap.setMyLocationEnabled(true);
    }

    private void goToLocation(double lat, double lng, float zoom) {
        LatLng ll=new LatLng(lat,lng);
        CameraUpdate update= CameraUpdateFactory.newLatLngZoom(ll,zoom);
        mGoogleMap.moveCamera(update);
    }
//    public void geoLocate(View view )throws IOException{
//        EditText map=(EditText)findViewById(R.id.mapText);
//        String location=map.getText().toString();
//        Geocoder gc=new Geocoder(this);
//        List<Address> list=gc.getFromLocationName(location,1);
//        Address address=list.get(0);
//        String locality=address.getLocality();
//        Toast.makeText(getApplication(),locality,Toast.LENGTH_LONG).show();
//        double lat=address.getLatitude();
//        double lng=address.getLongitude();
//        goToLocation(lat,lng,7);
//        MarkerOptions options=new MarkerOptions().title(locality).position(new LatLng(lat,lng));
//        mGoogleMap.addMarker(options);
//
//
//    }





//    private void showPopupMenu(View view) {
//        // inflate menu
//        PopupMenu popup = new PopupMenu(getApplication(), view);
//        MenuInflater inflater = popup.getMenuInflater();
//        inflater.inflate(R.menu.map_menu, popup.getMenu());
//        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
//        popup.show();
//    }
//class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
//
//    public MyMenuItemClickListener() {
//    }
//
//    public boolean onMenuItemClick(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.None:
//                mGoogleMap.setMapType(com.google.android.gms.maps.GoogleMap.MAP_TYPE_NONE);
//                break;
//            case R.id.Normal:
//                mGoogleMap.setMapType(com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL);
//                break;
//            case R.id.Satelite:
//                mGoogleMap.setMapType(com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE);
//                break;
//            case R.id.Hybird:
//                mGoogleMap.setMapType(com.google.android.gms.maps.GoogleMap.MAP_TYPE_HYBRID);
//                break;
//
//        }
//        return false;
//    }


}

