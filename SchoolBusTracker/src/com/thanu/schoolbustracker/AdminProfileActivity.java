package com.thanu.schoolbustracker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdminProfileActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_profile);
		Button logout = (Button)findViewById(R.id.admin_logout);
		logout.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		logout();
		
	}
	
	public void logout(){
		//log out from the account
		Log.d("Logout!", "Logout success");
		Intent i = new Intent(getApplicationContext(),
				MainActivity.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_profile, menu);
		return true;
	}

}
