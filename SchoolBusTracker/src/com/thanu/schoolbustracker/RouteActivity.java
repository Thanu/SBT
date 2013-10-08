package com.thanu.schoolbustracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class RouteActivity extends Activity implements OnMapClickListener,
		OnMapLongClickListener, OnMarkerClickListener {

	final int RQS_GooglePlayServices = 1;
	private GoogleMap myMap;

	Location myLocation;
	boolean markerClicked;
	PolylineOptions rectOptions;
	Polyline polyline;

	String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_route);

		FragmentManager myFragmentManager = getFragmentManager();
		MapFragment myMapFragment = (MapFragment) myFragmentManager
				.findFragmentById(R.id.map);
		myMap = myMapFragment.getMap();

		myMap.setMyLocationEnabled(true);

		myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

		myMap.setOnMapClickListener(this);
		myMap.setOnMapLongClickListener(this);
		myMap.setOnMarkerClickListener(this);

		markerClicked = false;
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		name = bundle.getString("uname");
		System.out.println(name);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.route, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_legalnotices:
			String LicenseInfo = GooglePlayServicesUtil
					.getOpenSourceSoftwareLicenseInfo(getApplicationContext());
			AlertDialog.Builder LicenseDialog = new AlertDialog.Builder(
					RouteActivity.this);
			LicenseDialog.setTitle("Legal Notices");
			LicenseDialog.setMessage(LicenseInfo);
			LicenseDialog.show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getApplicationContext());

		if (resultCode == ConnectionResult.SUCCESS) {
			Toast.makeText(getApplicationContext(),
					"isGooglePlayServicesAvailable SUCCESS", Toast.LENGTH_LONG)
					.show();
		} else {
			GooglePlayServicesUtil.getErrorDialog(resultCode, this,
					RQS_GooglePlayServices);
		}

	}

	@Override
	public void onMapClick(LatLng point) {
		myMap.animateCamera(CameraUpdateFactory.newLatLng(point));

		markerClicked = false;
	}

	@Override
	public void onMapLongClick(LatLng point) {
		if (name.equalsIgnoreCase("Admin")) {
			myMap.addMarker(new MarkerOptions().position(point).title(
					point.toString()));

			markerClicked = false;
		}
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		if (name.equalsIgnoreCase("Admin")) {
			if (markerClicked) {

				if (polyline != null) {
					polyline.remove();
					polyline = null;
				}

				rectOptions.add(marker.getPosition());
				rectOptions.color(Color.RED);
				polyline = myMap.addPolyline(rectOptions);
			} else {
				if (polyline != null) {
					polyline.remove();
					polyline = null;
				}

				rectOptions = new PolylineOptions().add(marker.getPosition());
				markerClicked = true;
			}
		}
		return true;

	}

}
