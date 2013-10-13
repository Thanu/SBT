package com.thanu.schoolbustracker;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DriverActivity extends Activity implements OnClickListener{

	Button busStarted, logout;
	String uname;

	HttpClient httpClient;
	HttpPost httpPost;
	ArrayList<NameValuePair> nameValuePairs;
	HttpResponse httpResponse;
	HttpEntity entity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_driver);
		busStarted = (Button) findViewById(R.id.busStarted);
		logout = (Button) findViewById(R.id.driver_logout);

		busStarted.setOnClickListener(this);
		logout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.busStarted :// notify bus started
			new AttemptAddAnnouncement().execute();
			break;
		case R.id.driver_logout:// log out
			logout();
			break;
		default:
			break;
		}
	}

	public void logout() {
		// log out from the account
		Log.d("Logout!", "Logout success");
		Intent i = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(i);
	}

	class AttemptAddAnnouncement extends AsyncTask<String, String, String> {
		final Context context = DriverActivity.this;
		@Override
		protected String doInBackground(String... args) {
			
			nameValuePairs = new ArrayList<NameValuePair>();
			try {
				
				final String msg = "Your bus has started the journey";
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
	
	

}
