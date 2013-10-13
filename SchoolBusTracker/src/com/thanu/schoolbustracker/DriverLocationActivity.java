package com.thanu.schoolbustracker;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DriverLocationActivity extends Activity implements
		LocationListener, OnClickListener {

	GoogleMap myMap;
	public static double latitude = 0, longitude = 0;
	HttpClient httpClient;
	HttpPost httpPost;
	ArrayList<NameValuePair> nameValuePairs;
	HttpResponse httpResponse;
	HttpEntity entity;
	
	Button notify;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_driver_location);
		notify = (Button) findViewById(R.id.notify);
		
		notify.setOnClickListener(this);
		

		// Getting Google Play availability status
		int status = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getBaseContext());

		// Showing status
		if (status != ConnectionResult.SUCCESS) { // Google Play Services are
													// not available

			int requestCode = 10;
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this,
					requestCode);
			dialog.show();

		} else { // Google Play Services are available

			// Getting reference to the MapFragment
			FragmentManager myFragmentManager = getFragmentManager();
			MapFragment myMapFragment = (MapFragment) myFragmentManager
					.findFragmentById(R.id.map);
			myMap = myMapFragment.getMap();
			
			// Enabling MyLocation Layer of Google Map
			myMap.setMyLocationEnabled(true);
			myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			myMap.getUiSettings().setCompassEnabled(false);
			myMap.getUiSettings().setRotateGesturesEnabled(true);
			myMap.getUiSettings().setScrollGesturesEnabled(true);
			myMap.getUiSettings().setZoomControlsEnabled(true);
			myMap.getUiSettings().setZoomGesturesEnabled(true);
			LatLng loc = new LatLng(6.796923, 79.922433);
			CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(
					6.796923, 79.922433));
			myMap.moveCamera(center);
			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(loc) // Sets the center of the map to Mountain
										// View
					.zoom(10) // Sets the zoom
					.bearing((float) 112.5) // Sets the orientation of the
											// camera to east
					.tilt(30) // Sets the tilt of the camera to 30 degrees
					.build(); // Creates a CameraPosition from the builder
			myMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));


			// Getting LocationManager object from System Service
			// LOCATION_SERVICE
			LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

			// Creating a criteria object to retrieve provider
			Criteria criteria = new Criteria();

			// Getting the name of the best provider
			String provider = locationManager.getBestProvider(criteria, true);

			// Getting Current Location
			Location location = locationManager.getLastKnownLocation(provider);

			if (location != null) {
				onLocationChanged(location);
			}

			locationManager.requestLocationUpdates(provider, 300000, 0, this);
		}
	}

	@Override
	public void onLocationChanged(Location location) {

		TextView tvLocation = (TextView) findViewById(R.id.tv_location);
		// Getting latitude of the current location
		latitude = location.getLatitude();

		// Getting longitude of the current location
		longitude = location.getLongitude();

		// Creating a LatLng object for the current location
		LatLng latLng = new LatLng(latitude, longitude);

		// Showing the current location in Google Map
		myMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

		// Zoom in the Google Map
		myMap.animateCamera(CameraUpdateFactory.zoomTo(15));

		// Setting latitude and longitude in the TextView tv_location
		tvLocation.setText("Latitude:" + latitude + ", Longitude:" + longitude);
		
		new AttemptUpdateLocation().execute();
		

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.driver_location, menu);
		return true;
	}
	
	class AttemptUpdateLocation extends AsyncTask<String, String, String> {
		final Context context = DriverLocationActivity.this;
		
		String lat = Double.valueOf(latitude).toString();
		String lon = Double.valueOf(longitude).toString();
		@Override
		protected String doInBackground(String... args) {
			
			nameValuePairs = new ArrayList<NameValuePair>();
			try {
				
				final String msg = "Your bus is at point latitude:"+lat+", longitude:"+lon;
				ServerUtilities.addAnnouncement(context, msg);

				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getBaseContext(), "Message sent",
								Toast.LENGTH_SHORT).show();
					}
				});
				return null;

			} catch (Exception e) {
				e.printStackTrace();
				Log.d("Error!", "Announcement not sent");

				// Toast.makeText(getBaseContext(),"Connection Error",Toast.LENGTH_SHORT).show();
			}
			return null;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	//notify location details
		new AttemptUpdateLocation().execute();
		
	}

}