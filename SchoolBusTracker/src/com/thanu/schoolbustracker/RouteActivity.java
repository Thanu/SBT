package com.thanu.schoolbustracker;

import android.os.Bundle;
import android.view.Menu;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class RouteActivity extends android.support.v4.app.FragmentActivity
		implements OnMapClickListener {
	private GoogleMap route;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_route);		
		route = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap(); //getting the map
		route.setOnMapClickListener(this); //Adding listener to mark places
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onMapClick(LatLng position) {
		// to add marker icon on intended positions
		route.addMarker(new MarkerOptions().position(position).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));

	}

}

