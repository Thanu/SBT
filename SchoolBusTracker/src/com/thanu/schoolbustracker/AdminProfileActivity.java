package com.thanu.schoolbustracker;

import java.util.ArrayList;
import android.app.TabActivity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

public class AdminProfileActivity extends Activity implements OnClickListener {
		EditText username;
		Button modifyRoute,addAnnouncement,dropUser,logout;
		String uname;
		
		HttpClient httpClient;
		HttpPost httpPost;
		ArrayList<NameValuePair> nameValuePairs;
		HttpResponse httpResponse;
		HttpEntity entity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_profile);
		modifyRoute = (Button)findViewById(R.id.btnModifyRoute);		
		addAnnouncement = (Button)findViewById(R.id.btnAnnouncement);		
		dropUser = (Button)findViewById(R.id.btnDropUser);		
		logout = (Button)findViewById(R.id.btnAdmin_logout);
		username = (EditText)findViewById(R.id.drop_uder_id);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		uname = bundle.getString("uname");
		
		modifyRoute.setOnClickListener(this);
		addAnnouncement.setOnClickListener(this);
		dropUser.setOnClickListener(this);
		logout.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnModifyRoute:// updating bus route
			Intent intent = getIntent();
			Bundle bundle = intent.getExtras();
			uname = bundle.getString("uname");
			(( TabActivity ) getParent()).getTabHost().setCurrentTab(2);
			break;
		case R.id.btnAnnouncement:// add announcements
			break;
		case R.id.btnDropUser:// delete a user from sbt record
			new AttemptDeleteUser().execute();
			break;
		case R.id.btnAdmin_logout:// log out
			logout();
			break;
		default:
			break;
		}	
	}
	
	public void logout(){
		//log out from the account
		Log.d("Logout!", "Logout success");
		Intent i = new Intent(getApplicationContext(),
				MainActivity.class);
		startActivity(i);
	}
	
	class AttemptDeleteUser extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... args) {
			String url = "http://10.0.2.2:8080/SBT/dropUser.php";
			nameValuePairs = new ArrayList<NameValuePair>();
			uname = username.getText().toString();
			System.out.println(uname);
			try {

				if (!(uname.equals(null))) {
					System.out.println(uname);
					JSONParser parser = new JSONParser();
					nameValuePairs
							.add(new BasicNameValuePair("username", uname));
					String success = parser.makeHttpRequest(url, nameValuePairs).trim();
					System.out.println(success);
					
					// validate user drop
					if (success.equalsIgnoreCase("true")) {
						Log.d("Drop!", "User is deleted");
						
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(getBaseContext(),
										"User is deleted", Toast.LENGTH_SHORT)
										.show();
								username.setText("");
							}
						});
						

					} else {
						Log.d("Drop!", "User is not deleted");
						Log.d("Success", success + "9");
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								Toast.makeText(getBaseContext(),
										"Unknown User",
										Toast.LENGTH_SHORT).show();
								username.setText("");
								
							}
						});
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.d("Error!", "Connection error");
				
				// Toast.makeText(getBaseContext(),"Connection Error",Toast.LENGTH_SHORT).show();
			}
			return null;
		}
	}

}
