package com.thanu.schoolbustracker;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class UserActivity extends TabActivity {
	HttpClient httpClient;
	HttpPost httpPost;
	ArrayList<NameValuePair> nameValuePairs;
	HttpResponse httpResponse;
	HttpEntity entity;
	

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String uname = bundle.getString("uname");
		String name = bundle.getString("name");
		String mail = bundle.getString("email");
		

		TabHost tabHost = getTabHost();
		// Tab for Profile
		TabSpec profilespec = tabHost.newTabSpec("MyProfile");
		// setting Title and Icon for the Tab
		profilespec.setIndicator("",
				getResources().getDrawable(R.drawable.icon_profile_tab));
		Intent profileIntent = new Intent(this, MyProfileActivity.class);
		profileIntent.putExtra("uname",uname);
		profileIntent.putExtra("name",name);
		profileIntent.putExtra("email",mail);
		profilespec.setContent(profileIntent);

		// Tab for notification
		TabSpec notificationspec = tabHost.newTabSpec("Notifications");
		notificationspec.setIndicator("", getResources()
				.getDrawable(R.drawable.icon_notification_tab));
		Intent notificationIntent = new Intent(this, NotificationActivity.class);
		notificationIntent.putExtra("uname",uname);
		notificationIntent.putExtra("name",name);
		notificationIntent.putExtra("email",mail);
		notificationspec.setContent(notificationIntent);

		// Tab for route
		TabSpec routespec = tabHost.newTabSpec("Route");
		routespec.setIndicator("",
				getResources().getDrawable(R.drawable.icon_route_tab));
		Intent routeIntent = new Intent(this, RouteActivity.class);
		routeIntent.putExtra("uname",uname);
		routeIntent.putExtra("name",name);
		routeIntent.putExtra("email",mail);
		routespec.setContent(routeIntent);

		// Adding all TabSpec to TabHost
		tabHost.addTab(profilespec); // Adding profile tab
		tabHost.addTab(notificationspec); // Adding notification tab
		tabHost.addTab(routespec); // Adding route tab
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user, menu);
		return true;
	}

}
