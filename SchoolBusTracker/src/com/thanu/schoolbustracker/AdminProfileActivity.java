package com.thanu.schoolbustracker;

import static com.thanu.schoolbustracker.CommonUtilities.SERVER_IP;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
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

public class AdminProfileActivity extends Activity implements OnClickListener {
	EditText username,message;
	Button addAnnouncement, dropUser, logout,send;
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

		addAnnouncement = (Button) findViewById(R.id.btnAnnouncement);
		dropUser = (Button) findViewById(R.id.btnDropUser);
		logout = (Button) findViewById(R.id.btnAdmin_logout);
		send = (Button) findViewById(R.id.send);
		username = (EditText) findViewById(R.id.drop_uder_id);
		message = (EditText) findViewById(R.id.message);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		uname = bundle.getString("uname");

		addAnnouncement.setOnClickListener(this);
		dropUser.setOnClickListener(this);
		logout.setOnClickListener(this);
		send.setOnClickListener(this);
	}

	public void switchTabInActivity(int indexTabToSwitchTo) {
		AdminActivity parentActivity;
		parentActivity = (AdminActivity) this.getParent();
		parentActivity.switchTab(indexTabToSwitchTo);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.btnAnnouncement:// add announcements
			message.setEnabled(true);
			message.setFocusable(true);
			send.setEnabled(true);
			break;
		case R.id.send:// send announcements	
			new AttemptAddAnnouncement().execute();
			send.setEnabled(false);
			message.setEnabled(false);
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

	public void logout() {
		// log out from the account
		Log.d("Logout!", "Logout success");
		Intent i = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(i);
	}

	class AttemptAddAnnouncement extends AsyncTask<String, String, String> {
		final Context context = AdminProfileActivity.this;
		@Override
		protected String doInBackground(String... args) {
			
			nameValuePairs = new ArrayList<NameValuePair>();
			try {
				
				final String msg = message.getText().toString();
				ServerUtilities.addAnnouncement(context, msg);

				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getBaseContext(), "Message sent",
								Toast.LENGTH_SHORT).show();
						message.setText("");
					}
				});
				return null;

			} catch (Exception e) {
				e.printStackTrace();
				Log.d("Error!", "Message not sent");

				// Toast.makeText(getBaseContext(),"Connection Error",Toast.LENGTH_SHORT).show();
			}
			return null;
		}
	}

	class AttemptDeleteUser extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... args) {
			String url = SERVER_IP+"dropUser.php";
			nameValuePairs = new ArrayList<NameValuePair>();
			uname = username.getText().toString();
			System.out.println(uname);
			try {

				if (!(uname.equals(null))) {
					System.out.println(uname);
					JSONParser parser = new JSONParser();
					nameValuePairs
							.add(new BasicNameValuePair("username", uname));
					String success = parser
							.makeHttpRequest(url, nameValuePairs).trim();
					System.out.println(success);

					// validate user drop
					if (success.equalsIgnoreCase("true")) {
						Log.d("Drop!", "User is deleted");
						// GCMRegistrar.unregister(context);//.register(this,
						// SENDER_ID);

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
										"Unknown User", Toast.LENGTH_SHORT)
										.show();
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
