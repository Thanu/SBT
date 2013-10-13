package com.thanu.schoolbustracker;

import static com.thanu.schoolbustracker.CommonUtilities.SENDER_ID;
import static com.thanu.schoolbustracker.CommonUtilities.SERVER_URL;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//Activity for login action
public class LoginActivity extends Activity implements OnClickListener {
	private String username, password, mail, fullname;
	EditText uname, pword;
	TextView status;
	Button login;
	HttpClient httpClient;
	HttpPost httpPost;
	ArrayList<NameValuePair> nameValuePairs;
	HttpResponse httpResponse;
	HttpEntity entity;

	// alert dialog manager
	AlertDialogManager alert = new AlertDialogManager();

	// Internet detector
	ConnectionDetector cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		cd = new ConnectionDetector(getApplicationContext());

		// Check if Internet present
		if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			alert.showAlertDialog(LoginActivity.this,
					"Internet Connection Error",
					"Please connect to working Internet connection", false);
			// stop executing code by return
			return;
		}
		// Check if GCM configuration is set
		if (SERVER_URL == null || SENDER_ID == null
				|| SERVER_URL.length() == 0
				|| SENDER_ID.length() == 0) {
			// GCM sernder id / server url is missing
			alert.showAlertDialog(
					LoginActivity.this,
					"Configuration Error!",
					"Please set your Server URL and GCM Sender ID",
					false);
			// stop executing code by return
			return;
		}

		initialise();
		login.setOnClickListener(this);
	}

	private void initialise() {// get the elements
		uname = (EditText) findViewById(R.id.uname);
		pword = (EditText) findViewById(R.id.pword);
		status = (TextView) findViewById(R.id.status);
		login = (Button) findViewById(R.id.btnLogin);
	}

	@Override
	public void onClick(View v) {
		new AttemptLogin().execute();
	}

	class AttemptLogin extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... args) {

			String url = "http://10.0.2.2:8080/SBT/signin.php";//192.168.42.11:8080/SBT/signin.php";// url of php file needed for login
			username = uname.getText().toString();
			password = pword.getText().toString();

			try {

				nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs
						.add(new BasicNameValuePair("username", username));
				nameValuePairs
						.add(new BasicNameValuePair("password", password));

				JSONParser parser = new JSONParser();
				JSONObject jsonResponse = new JSONObject(
						parser.makeHttpRequest(url, nameValuePairs));// calling
																		// http
																		// POST
																		// method
				String retPword = jsonResponse.getString("password");// password
																		// received
																		// from
																		// signin.php

				// validate login
				if (password.equals(retPword)) {// if entered password equals to
												// database password
					Log.d("Login!", "Login Success");

					if (username.equals("Admin")) {// if the user is admin
						Intent i = new Intent(getApplicationContext(),
								AdminActivity.class);
						i.putExtra("uname", username);
						startActivity(i);// starting the admin account
					} else if (username.equals("Driver")) {// if the user is driver
						Intent i = new Intent(getApplicationContext(),
								DriverActivity.class);
						i.putExtra("uname", username);
						startActivity(i);// starting the driver account
					} else {				

						Intent i = new Intent(getApplicationContext(),
								UserActivity.class);
						i.putExtra("uname", username);
						retrieveUser();
						i.putExtra("name", fullname);
						i.putExtra("email", mail);
						startActivity(i);// starting the user account
					}

				} else {// if login failed
					Log.d("Login!", "Login Not Success");
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							status.setText("Your usernsme or passsword is incorrect");// give error msg
							// empty the username, password text box
							uname.setText("");
							pword.setText("");

						}
					});

					// Toast.makeText(getBaseContext(),"Login unsuccess",Toast.LENGTH_SHORT).show();
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

	public void retrieveUser() {

		String url = "http://10.0.2.2:8080/SBT/getUser.php";//192.168.42.11:8080/SBT/getUser.php";
		JSONParser parser = new JSONParser();

		try {
			nameValuePairs = new ArrayList<NameValuePair>();

			nameValuePairs.add(new BasicNameValuePair("username", username));

			JSONObject jsonResponse = new JSONObject(parser.makeHttpRequest(
					url, nameValuePairs));

			mail = jsonResponse.getString("email");
			fullname = jsonResponse.getString("full_name");

		} catch (JSONException e) {
			e.printStackTrace();
			Log.d("Error!", "JSONException");
			Log.d("Error!", e.toString());
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(getBaseContext(), "Connection Error",
							Toast.LENGTH_SHORT).show();

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
			Log.d("Error!", "Connection error");
			Log.d("Error!", e.toString());
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(getBaseContext(), "Connection Error",
							Toast.LENGTH_SHORT).show();
				}
			});

		}

	}

}
