package com.thanu.schoolbustracker;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AdminActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);

		TabHost tabHost = getTabHost();
		// Tab for Admin functions
		TabSpec profilespec = tabHost.newTabSpec("AdminPage");
		// setting Title and Icon for the Tab
		profilespec.setIndicator("Admin",
				getResources().getDrawable(R.drawable.icon_profile_tab));
		Intent profileIntent = new Intent(this, AdminProfileActivity.class);
		profilespec.setContent(profileIntent);

		

		// Tab for route
		TabSpec routespec = tabHost.newTabSpec("Route");
		routespec.setIndicator("Route",
				getResources().getDrawable(R.drawable.icon_route_tab));
		Intent routeIntent = new Intent(this, RouteActivity.class);
		routespec.setContent(routeIntent);

		// Adding all TabSpec to TabHost
		tabHost.addTab(profilespec); // Adding admin page tab
		tabHost.addTab(routespec); // Adding route tab
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin, menu);
		return true;
	}

}
