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

import com.thanu.schoolbustracker.AlertDialogManager;
import com.thanu.schoolbustracker.ConnectionDetector;
import com.thanu.schoolbustracker.RegisterActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

//Activity for registration action
public class RegisterActivity extends Activity implements OnClickListener {

	private String username, pword, pword1, mail, busHault;
	private String fullname, sex, place, phoneNo;

	EditText uname, password, password1, email, bus_hault, fname, address,
			phone;
	TextView status;
	Button register;
	RadioGroup gender;
	RadioButton button;

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
		setContentView(R.layout.activity_register);
		cd = new ConnectionDetector(getApplicationContext());

		// Check if Internet present
		if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			alert.showAlertDialog(RegisterActivity.this,
					"Internet Connection Error",
					"Please connect to working Internet connection", false);
			// stop executing code by return
			return;
		}

		// Check if GCM configuration is set
		if (SERVER_URL == null || SENDER_ID == null || SERVER_URL.length() == 0
				|| SENDER_ID.length() == 0) {
			// GCM sernder id / server url is missing
			alert.showAlertDialog(RegisterActivity.this,
					"Configuration Error!",
					"Please set your Server URL and GCM Sender ID", false);
			// stop executing code by return
			return;
		}
		initialise();
		register.setOnClickListener(this);
	}

	private void initialise() {// getting elements of activity_register
		uname = (EditText) findViewById(R.id.uname);
		fname = (EditText) findViewById(R.id.fname);
		gender = (RadioGroup) findViewById(R.id.gender);
		address = (EditText) findViewById(R.id.address);
		phone = (EditText) findViewById(R.id.phone);
		password = (EditText) findViewById(R.id.password);
		password1 = (EditText) findViewById(R.id.password1);
		email = (EditText) findViewById(R.id.email);
		bus_hault = (EditText) findViewById(R.id.bus_hault);
		status = (TextView) findViewById(R.id.register_status);
		register = (Button) findViewById(R.id.btnRegister);
	}

	public void onClick(View v) {
		new AttemptRegister().execute();
	}

	class AttemptRegister extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... args) {

			username = uname.getText().toString();
			fullname = fname.getText().toString();
			button = (RadioButton) findViewById(gender
					.getCheckedRadioButtonId());
			sex = button.getText().toString();
			place = address.getText().toString();
			phoneNo = phone.getText().toString();
			pword = password.getText().toString();
			pword1 = password1.getText().toString();
			mail = email.getText().toString();
			busHault = bus_hault.getText().toString();

			try {
				if (pword.equals(pword1)) {// if the password and re-entered
											// password are equal

					String url = "http://10.0.2.2:8080/SBT/signup.php";//192.168.42.11:8080/SBT/signup.php";// url of
																		// the
																		// signup.php
					JSONParser parser = new JSONParser();

					nameValuePairs = new ArrayList<NameValuePair>();

					nameValuePairs.add(new BasicNameValuePair("username",
							username));
					nameValuePairs.add(new BasicNameValuePair("fullname",
							fullname));
					nameValuePairs.add(new BasicNameValuePair("gender", sex));
					nameValuePairs
							.add(new BasicNameValuePair("address", place));
					nameValuePairs
							.add(new BasicNameValuePair("phone", phoneNo));
					nameValuePairs.add(new BasicNameValuePair("pword", pword));
					nameValuePairs.add(new BasicNameValuePair("email", mail));
					nameValuePairs.add(new BasicNameValuePair("bus_hault",
							busHault));

					String success = parser
							.makeHttpRequest(url, nameValuePairs).trim();// getting
																			// the
																			// response
																			// from
																			// httpPOST
																			// request

					// validate registration
					if (success.equalsIgnoreCase("true")) {
						Log.d("Register!", "Register Success");
						if (fullname.trim().length() > 0
								&& mail.trim().length() > 0) {
							// Launch User Activity
							Intent i = new Intent(getApplicationContext(),
									UserActivity.class);
							// Registering user on our server
							// Sending registraiton details to UserActivity
							i.putExtra("uname", username);
							i.putExtra("name", fullname);
							i.putExtra("email", mail);
							startActivity(i);
							finish();
						} else {
							// user doen't filled that data
							// ask him to fill the form
							alert.showAlertDialog(RegisterActivity.this,
									"Registration Error!",
									"Please enter your details", false);
						}

					} else {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								status.setText("You are not registered. Try again");
								uname.setText("");
								fname.setText("");
								gender.clearCheck();
								address.setText("");
								phone.setText("");
								password.setText("");
								password1.setText("");
								email.setText("");
								bus_hault.setText("");

							}
						});
					}

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
