package com.thanu.schoolbustracker;

import java.util.ArrayList;
import static com.thanu.schoolbustracker.CommonUtilities.SERVER_IP;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class RouteActivity extends Activity implements OnMapClickListener,
		OnMapLongClickListener, OnMarkerClickListener, OnClickListener {

	SharedPreferences sharedPreferences;
	int locationCount = 0;

	final int RQS_GooglePlayServices = 1;
	private GoogleMap myMap;

	Location myLocation;
	boolean markerClicked;
	PolylineOptions rectOptions;
	Polyline polyline;
	Button modifyRoute;
	String name;
	
	public static final String PROVIDER_NAME = "com.thanu.schoolbustracker";
	HttpClient httpClient;
	HttpPost httpPost;
	ArrayList<NameValuePair> nameValuePairs;
	HttpResponse httpResponse;
	HttpEntity entity;
	String latitude, longitude, bus_hault, zoom;
	double lat, lon;
	float zoomLevel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_route);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		name = bundle.getString("uname");

		FragmentManager myFragmentManager = getFragmentManager();
		MapFragment myMapFragment = (MapFragment) myFragmentManager
				.findFragmentById(R.id.map);
		modifyRoute = (Button) findViewById(R.id.btnModifyRoute);
		
		myMap = myMapFragment.getMap();

		if (myMap != null) {
			
			myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			myMap.getUiSettings().setCompassEnabled(false);
			myMap.getUiSettings().setRotateGesturesEnabled(true);
			myMap.getUiSettings().setScrollGesturesEnabled(true);
			myMap.getUiSettings().setZoomControlsEnabled(true);
			myMap.getUiSettings().setZoomGesturesEnabled(true);
			LatLng location = new LatLng(6.796923, 79.922433);
			CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(
					6.796923, 79.922433));
			myMap.moveCamera(center);
			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(location) // Sets the center of the map to Mountain
										// View
					.zoom(10) // Sets the zoom
					.bearing((float) 112.5) // Sets the orientation of the
											// camera to east
					.tilt(30) // Sets the tilt of the camera to 30 degrees
					.build(); // Creates a CameraPosition from the builder
			myMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));

			// Opening the sharedPreferences object
			sharedPreferences = getSharedPreferences("location", 0);

			// Getting number of locations already stored
			locationCount = sharedPreferences.getInt("locationCount", 0);

			// Getting stored zoom level if exists else return 0
			String zoom = sharedPreferences.getString("zoom", "0");

			// If locations are already saved
			if (locationCount != 0) {

				String lat = "";
				String lng = "";

				// Iterating through all the locations stored
				for (int i = 0; i < locationCount; i++) {

					// Getting the latitude of the i-th location
					lat = sharedPreferences.getString("lat" + i, "0");

					// Getting the longitude of the i-th location
					lng = sharedPreferences.getString("lng" + i, "0");

					// Drawing marker on the map
					drawMarker(new LatLng(Double.parseDouble(lat),
							Double.parseDouble(lng)));
				}

				// Moving CameraPosition to last clicked position
				myMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(
						Double.parseDouble(lat), Double.parseDouble(lng))));

				// Setting the zoom level in the map on last position is clicked
				myMap.animateCamera(CameraUpdateFactory.zoomTo(Float
						.parseFloat(zoom)));
			}

			modifyRoute.setOnClickListener(this);
			myMap.setOnMapClickListener(this);
			myMap.setOnMapLongClickListener(this);
			myMap.setOnMarkerClickListener(this);
		}

		markerClicked = false;

		if (name.equalsIgnoreCase("Admin")) {
			modifyRoute.setEnabled(true);
		}
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
		if (name == null || name.equalsIgnoreCase("Admin")) {
			locationCount++;
			
			// Drawing marker on the map
			drawMarker(point);
	        
	        /** Opening the editor object to write data to sharedPreferences */
	        SharedPreferences.Editor editor = sharedPreferences.edit();	        
	        
	        // Storing the latitude for the i-th location
	        editor.putString("lat"+ Integer.toString((locationCount-1)), Double.toString(point.latitude));
	        
	        // Storing the longitude for the i-th location
	        editor.putString("lng"+ Integer.toString((locationCount-1)), Double.toString(point.longitude));
	        
	        // Storing the count of locations or marker count
	        editor.putInt("locationCount", locationCount);		        
	        
	        /** Storing the zoom level to the shared preferences */
	        editor.putString("zoom", Float.toString(myMap.getCameraPosition().zoom));		        

	        /** Saving the values stored in the shared preferences */
	        editor.commit();	
	        
	        // Setting latitude in ContentValues
			lat = point.latitude;
			latitude = (Double.valueOf(lat)).toString();
			
			// Setting longitude in ContentValues
			lon = point.longitude;
			longitude = (Double.valueOf(lon)).toString();
			
			// Setting zoom in ContentValues
			zoomLevel = myMap.getCameraPosition().zoom;
			zoom = (Float.valueOf(zoomLevel)).toString();
			
			// Creating an instance of LocationInsertTask
			new LocationInsertTask().execute();

	        
	        Toast.makeText(getBaseContext(), "Marker is added to the Map", Toast.LENGTH_SHORT).show();			        
	      

			markerClicked = false;
		}
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		if (name == null || name.equalsIgnoreCase("Admin")) {
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

	private void drawMarker(LatLng point) {
		// Creating an instance of MarkerOptions
		MarkerOptions markerOptions = new MarkerOptions();

		// Setting latitude and longitude for the marker
		markerOptions.position(point);

		// Adding marker on the Google Map
		myMap.addMarker(markerOptions);
	}

	@Override
	public void onClick(View v) {
		
			// Removing the marker and circle from the Google Map
			myMap.clear();			
			// Opening the editor object to delete data from sharedPreferences
	        SharedPreferences.Editor editor = sharedPreferences.edit();	        
	        // Clearing the editor
	        editor.clear();			
	        // Committing the changes
			editor.commit();			
			// Setting locationCount to zero
			locationCount=0;
			new LocationDeleteTask().execute();
			
	}
	private class LocationInsertTask extends AsyncTask<String, String, String> {
		@Override
		protected String doInBackground(String... args) {
			try {

				// Storing the latitude, longitude and zoom level to database

				String url = SERVER_IP+"insert.php";				// url of
																	// the
																	// signup.php
				JSONParser parser = new JSONParser();
				nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs
						.add(new BasicNameValuePair("latitude", latitude));
				nameValuePairs.add(new BasicNameValuePair("longitude",
						longitude));
				nameValuePairs.add(new BasicNameValuePair("zoom", zoom));

				String success = parser.makeHttpRequest(url, nameValuePairs)
						.trim();// getting the response from httpPOST request

				// validate registration
				if (success.equalsIgnoreCase("true")) {
					Log.d("Register", "Places are inserted in db");

				} else {
					Log.d("Register", "Places are inserted in db");

				}

			} catch (Exception e) {
				e.printStackTrace();
				Log.d("Error!", "Connection error");

				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getBaseContext(), "Connection Error",
								Toast.LENGTH_SHORT).show();
					}
				});
			}
			return null;
		}
	}

	private class LocationDeleteTask extends AsyncTask<String, String, String> {
		// Deleting all the rows from database table
		@Override
		protected String doInBackground(String... args) {
			try {

				String url = SERVER_IP+"delete.php";//"http://192.168.42.11:8080/SBT/delete.php";// 192.168.42.11:8080/SBT/signup.php";//
																	// url of
																	// the
																	// signup.php
				JSONParser parser = new JSONParser();
				nameValuePairs = new ArrayList<NameValuePair>();

				String success = parser.makeHttpRequest(url, nameValuePairs)
						.trim();// getting the response from httpPOST request

				// validate registration
				if (success.equalsIgnoreCase("true")) {
					Log.d("Register", "All places are deleted in db");

				} else {
					Log.d("Register", "All places not deleted in db");

				}

			} catch (Exception e) {
				e.printStackTrace();
				Log.d("Error!", "Connection error");

				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getBaseContext(), "Connection Error",
								Toast.LENGTH_SHORT).show();
					}
				});
			}
			return null;
		}
	}

}
