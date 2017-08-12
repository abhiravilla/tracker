package com.abhi.tracker;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class map extends AppCompatActivity implements OnMapReadyCallback {
	GoogleMap map=null;
	String lat,lon,phone;
	double lati,loni;
    LatLng latLng;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (map == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFrag.getMapAsync(this);
        }
        Intent in=getIntent();
    	phone=in.getStringExtra("phone");
    	Log.d("in intent", ""+phone);
    	int i=phone.compareTo("null");
        if(i!=0){
        	lat=in.getStringExtra("latitude");
        	lon=in.getStringExtra("longitude");
        	lati=Double.parseDouble(lat);
        	loni=Double.parseDouble(lon);
            LatLng latLng = new LatLng(lati,loni);
            map.addMarker(new MarkerOptions().position(latLng).title(phone));
            CameraUpdate cam=CameraUpdateFactory.newLatLngZoom(latLng, 15);
            map.animateCamera(cam);   
            
        }
        else{
        	lati=13.179275;
        	loni=85.238979;
         latLng = new LatLng(lati,loni);
        map.addMarker(new MarkerOptions().position(latLng).title("User"));
        CameraUpdate cam=CameraUpdateFactory.newLatLngZoom(latLng, 15);
        map.animateCamera(cam);   
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;
        setUpMap();
    }
    public void setUpMap(){

        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.setMyLocationEnabled(true);
        map.setTrafficEnabled(true);
        map.setIndoorEnabled(true);
        map.setBuildingsEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
    }

}
