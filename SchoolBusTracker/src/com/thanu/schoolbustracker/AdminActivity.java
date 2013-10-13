package com.thanu.schoolbustracker;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AdminActivity extends TabActivity {

	TabHost tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);

		tabHost = getTabHost();
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String uname = bundle.getString("uname");
		
		
		// Tab for Admin functions		
		TabSpec profilespec = tabHost.newTabSpec("AdminPage");
		// setting Title and Icon for the Tab
		profilespec.setIndicator("",
				getResources().getDrawable(R.drawable.icon_profile_tab));
		Intent profileIntent = new Intent(this, AdminProfileActivity.class);
		profileIntent.putExtra("uname",uname);
		profilespec.setContent(profileIntent);

		

		// Tab for route
		TabSpec routespec = tabHost.newTabSpec("Route");
		routespec.setIndicator("",
				getResources().getDrawable(R.drawable.icon_route_tab));
		Intent routeIntent = new Intent(this, RouteActivity.class);
		routeIntent.putExtra("uname",uname);
		routespec.setContent(routeIntent);

		// Adding all TabSpec to TabHost
		tabHost.addTab(profilespec); // Adding admin page tab
		tabHost.addTab(routespec); // Adding route tab
	}

	public void switchTab(int tab){
        tabHost.setCurrentTab(tab);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin, menu);
		return true;
	}

}
