package com.thanu.schoolbustracker;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MyProfileActivity extends Activity implements OnClickListener {
	EditText uname, password, password1, email, bus_hault, fname, address,
			phone;
	Button update, changePassword, save,logout;
	RadioGroup gender;
	RadioButton button;
	TextView status;

	HttpClient httpClient;
	HttpPost httpPost;
	ArrayList<NameValuePair> nameValuePairs;
	HttpResponse httpResponse;
	HttpEntity entity;
	String pword, pword1, fullname, place, phoneNo, mail, busHault,name;
	User user = new User();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_profile);
		initialise();
		update.setOnClickListener(this);
		changePassword.setOnClickListener(this);
		save.setOnClickListener(this);
		logout.setOnClickListener(this);
	}

	public void initialise() {

		uname = (EditText) findViewById(R.id.uname);
		fname = (EditText) findViewById(R.id.fname);
		gender = (RadioGroup) findViewById(R.id.gender);
		address = (EditText) findViewById(R.id.address);
		phone = (EditText) findViewById(R.id.phone);
		password = (EditText) findViewById(R.id.password);
		password1 = (EditText) findViewById(R.id.password1);
		email = (EditText) findViewById(R.id.email);
		bus_hault = (EditText) findViewById(R.id.bus_hault);
		changePassword = (Button) findViewById(R.id.btnChangePassword);
		update = (Button) findViewById(R.id.btnUpdateProfile);
		save = (Button) findViewById(R.id.btnSave);
		logout = (Button) findViewById(R.id.logout);
		status = (TextView) findViewById(R.id.register_status);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		name = bundle.getString("uname");
		System.out.println(name);
		
		/*new AttemptToRetrieveUser().execute();
		
		uname.setText(user.getUserName());
		System.out.println(user.getUserName());
		fname.setText(user.getFullName());System.out.println(user.getFullName());
		int genderId = user.getGender().equals("Male") ? 0 : 1;System.out.println(user.getGender());
		gender.check(genderId);
		address.setText(user.getAddress());System.out.println(user.getAddress());
		phone.setText(user.getPhone());
		password.setText(user.getPassword());
		password1.setText(user.getPassword());
		email.setText(user.getEmail());
		bus_hault.setText(user.getBus_hault());*/

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_profile, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnUpdateProfile:// updating profile
			fname.setEnabled(true);
			address.setEnabled(true);
			phone.setEnabled(true);
			email.setEnabled(true);
			fname.setFocusable(true);
			break;
		case R.id.btnChangePassword:// change password
			password.setEnabled(true);
			password1.setEnabled(true);
			password.setFocusable(true);
			break;
		case R.id.btnSave:// save changes
			new AttemptSavePassword().execute();
			break;
		case R.id.btnUpdate:// save changes
			new AttemptUpdateProfile().execute();
			break;
		case R.id.logout:// log out 
			logout();
			break;	
		default:
			break;
		}

	}
	
	public void logout(){
		Log.d("Logout!", "Logout success");
		Intent i = new Intent(getApplicationContext(),
				MainActivity.class);
		startActivity(i);
	}
	
	class AttemptToRetrieveUser extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... args) {
			String url = "http://10.0.2.2:8080/SBT/getUser.php";
			JSONParser parser = new JSONParser();
			
			try {
				nameValuePairs = new ArrayList<NameValuePair>();

				nameValuePairs.add(new BasicNameValuePair("username", name));

				JSONObject jsonResponse = new JSONObject(parser.makeHttpRequest(
						url, nameValuePairs));
				System.out.println("JSONResponse:"+jsonResponse);
				user.setUserName(jsonResponse.getString("username"));
				user.setFullName(jsonResponse.getString("full_name"));
				user.setGender(jsonResponse.getString("gender"));
				user.setAddress(jsonResponse.getString("address"));
				user.setPhone(jsonResponse.getString("phone_no"));
				user.setPassword(jsonResponse.getString("password"));
				user.setEmail(jsonResponse.getString("email"));
				user.setBus_hault(jsonResponse.getString("bus_hault"));

			} catch (Exception e) {
				e.printStackTrace();
				Log.d("Error!", "Connection error");
				Log.d("Error!", e.toString());
				// Toast.makeText(getBaseContext(),"Connection Error",Toast.LENGTH_SHORT).show();
			}
			return null;
		}
	}
			
		

	class AttemptSavePassword extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... args) {
			String url = "http://10.0.2.2:8080/SBT/changePassword.php";

			pword = password.getText().toString();
			pword1 = password1.getText().toString();
			try {

				if (pword.equals(pword1)) {
					JSONParser parser = new JSONParser();
					nameValuePairs.add(new BasicNameValuePair("username",name));
					nameValuePairs.add(new BasicNameValuePair("password", pword));

					String success = parser
							.makeHttpRequest(url, nameValuePairs).trim();

					if (success.equalsIgnoreCase("true")) {
						Log.d("Password!", "Password changed");
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								Toast.makeText(getBaseContext(),
										"Password Changed", Toast.LENGTH_SHORT)
										.show();
							}
						});
						password.setEnabled(false);
						password1.setEnabled(false);

					} else {
						Log.d("Password!", "Password not changed");
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								Toast.makeText(getBaseContext(),
										"Password mismatching",
										Toast.LENGTH_SHORT).show();
								password.setText("");
								password1.setText("");

							}
						});
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.d("Error!", "Connection error");
				Log.d("Error!", e.getMessage());
				// Toast.makeText(getBaseContext(),"Connection Error",Toast.LENGTH_SHORT).show();
			}
			return null;
		}
	}

	class AttemptUpdateProfile extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... args) {
			String url = "http://10.0.2.2:8080/SBT/updateProfile.php";
			fullname = fname.getText().toString();
			place = address.getText().toString();
			phoneNo = phone.getText().toString();
			mail = email.getText().toString();
			busHault = bus_hault.getText().toString();

			try {
				JSONParser parser = new JSONParser();
				nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("username",name));
				nameValuePairs
						.add(new BasicNameValuePair("fullname", fullname));
				nameValuePairs.add(new BasicNameValuePair("address", place));
				nameValuePairs.add(new BasicNameValuePair("phone", phoneNo));
				nameValuePairs.add(new BasicNameValuePair("email", mail));
				nameValuePairs
						.add(new BasicNameValuePair("bus_hault", busHault));

				String success = parser.makeHttpRequest(url, nameValuePairs)
						.trim();

				if (success.equalsIgnoreCase("true")) {
					Log.d("Profile Update!", "Profile updated");
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(getBaseContext(), "Profile Updated",
									Toast.LENGTH_SHORT).show();
						}
					});

				} else {
					Log.d("Profile Update!", "Profile not updated");

					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							Toast.makeText(getBaseContext(),
									"Profile Not Updated", Toast.LENGTH_SHORT)
									.show();
							fname.setEnabled(true);
							address.setEnabled(true);
							phone.setEnabled(true);
							email.setEnabled(true);
							fname.setFocusable(true);
						}
					});

				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.d("Error!", "Connection error");
				Log.d("Error!", e.getMessage());
				// Toast.makeText(getBaseContext(),"Connection Error",Toast.LENGTH_SHORT).show();
			}
			return null;
		}
	}

}
