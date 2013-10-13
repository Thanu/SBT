package com.thanu.schoolbustracker;

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
	EditText txt_uname, txt_password, txt_password1, txt_email, txt_bus_hault,
			txt_fname, txt_address, txt_phone;
	Button updateProfile, update, changePassword, save, logout;
	RadioGroup gender;
	RadioButton male,female,button;
	TextView status;

	HttpClient httpClient;
	HttpPost httpPost;
	ArrayList<NameValuePair> nameValuePairs;
	HttpResponse httpResponse;
	HttpEntity entity;
	String pword, pword1, fullname, place, phoneNo, mail, busHault, name;
	String userName, password, email, bus_hault, fullName, sex, address, phone;
	User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_profile);
		initialise();
		updateProfile.setOnClickListener(this);
		update.setOnClickListener(this);
		changePassword.setOnClickListener(this);
		save.setOnClickListener(this);
		logout.setOnClickListener(this);
	}

	public void initialise() {

		txt_uname = (EditText) findViewById(R.id.uname);
		txt_fname = (EditText) findViewById(R.id.fname);
		gender = (RadioGroup) findViewById(R.id.gender);
		male = (RadioButton)findViewById(R.id.male);
		female = (RadioButton)findViewById(R.id.female);
		txt_address = (EditText) findViewById(R.id.address);
		txt_phone = (EditText) findViewById(R.id.phone);
		txt_password = (EditText) findViewById(R.id.password);
		txt_password1 = (EditText) findViewById(R.id.password1);
		txt_email = (EditText) findViewById(R.id.email);
		txt_bus_hault = (EditText) findViewById(R.id.bus_hault);
		changePassword = (Button) findViewById(R.id.btnChangePassword);
		updateProfile = (Button) findViewById(R.id.btnUpdateProfile);
		save = (Button) findViewById(R.id.btnSave);
		update = (Button) findViewById(R.id.btnUpdate);
		logout = (Button) findViewById(R.id.btnLogout);
		status = (TextView) findViewById(R.id.register_status);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		name = bundle.getString("uname");
		//System.out.println(name);

		new AttemptToRetrieveUser().execute();

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
			txt_fname.setEnabled(true);
			txt_address.setEnabled(true);
			txt_phone.setEnabled(true);
			txt_email.setEnabled(true);
			txt_bus_hault.setEnabled(true);
			txt_fname.setFocusable(true);
			update.setEnabled(true);
			update.setClickable(true);
			break;
		case R.id.btnChangePassword:// change password
			txt_password.setEnabled(true);
			txt_password1.setEnabled(true);
			txt_password.setClickable(true);
			txt_password1.setClickable(true);
			txt_password.setFocusable(true);
			save.setEnabled(true);
			save.setClickable(true);
			break;
		case R.id.btnSave:// save changes;
			new AttemptSavePassword().execute();
			break;
		case R.id.btnUpdate:// save changes
			new AttemptUpdateProfile().execute();
			break;
		case R.id.btnLogout:// log out
			logout();
			break;
		default:
			break;
		}

	}

	public void logout() {
		Log.d("Logout!", "Logout success");
		Intent i = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(i);
	}

	class AttemptToRetrieveUser extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... args) {
			String url = "http://10.0.2.2:8080/SBT/getUser.php";//192.168.42.11:8080/SBT/getUser.php";
			JSONParser parser = new JSONParser();

			try {
				nameValuePairs = new ArrayList<NameValuePair>();

				nameValuePairs.add(new BasicNameValuePair("username", name));

				JSONObject jsonResponse = new JSONObject(
						parser.makeHttpRequest(url, nameValuePairs));
				System.out.println("JSONResponse:" + jsonResponse);

				userName = jsonResponse.getString("username");
				fullName = jsonResponse.getString("full_name");
				sex = jsonResponse.getString("gender");
				address = jsonResponse.getString("address");
				phone = jsonResponse.getString("phone_no");
				password = jsonResponse.getString("password");
				email = jsonResponse.getString("email");
				bus_hault = jsonResponse.getString("bus_hault");

				user = new User(userName, fullName, sex, address, phone,
						password, email, bus_hault);
				// System.out.println("User name of :"+user.getUserName());

				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						txt_uname.setText(userName);
						// System.out.println("Username:"+userName);
						txt_fname.setText(fullName);
						System.out.println(fullName);
						int genderId = sex.equals("Male") ? 0 : 1;
						System.out.println(sex);
						if(genderId==0){
							male.setChecked(true);
						}else{
							female.setChecked(true);
						}
						
						txt_address.setText(address);
						System.out.println(address);
						txt_phone.setText(phone);
						txt_password.setText(password);
						txt_password1.setText(password);
						txt_email.setText(email);
						txt_bus_hault.setText(bus_hault);

					}
				});

			} catch (JSONException e) {
				e.printStackTrace();
				Log.d("Error!", "JSONException");
				Log.d("Error!", e.toString());
				// Toast.makeText(getBaseContext(),"Connection Error",Toast.LENGTH_SHORT).show();
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
			String url = "http://10.0.2.2:8080/SBT/changePassword.php";//192.168.42.11:8080/SBT/changePassword.php";

			pword = txt_password.getText().toString();
			pword1 = txt_password1.getText().toString();

			try {

				if (pword.equals(pword1)) {
					JSONParser parser = new JSONParser();
					nameValuePairs
							.add(new BasicNameValuePair("username", name));
					nameValuePairs
							.add(new BasicNameValuePair("password", pword));

					String success = parser.makeHttpRequest(url, nameValuePairs).trim();
					// validate password update
					if (success.equalsIgnoreCase("true")) {
						Log.d("Password!", "Password changed");
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(getBaseContext(),
										"Password Changed", Toast.LENGTH_SHORT)
										.show();
								txt_password.setEnabled(false);
								txt_password1.setEnabled(false);
								save.setEnabled(false);
							}
						});
						

					} else {
						Log.d("Password!", "Password not changed");
						Log.d("Success", success + "9");
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								Toast.makeText(getBaseContext(),
										"Password mismatching",
										Toast.LENGTH_SHORT).show();
								txt_password.setText("");
								txt_password1.setText("");

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
						 Toast.makeText(getBaseContext(),"Connection Error",Toast.LENGTH_SHORT).show();
					}
				});
				}
			return null;
		}
	}

	class AttemptUpdateProfile extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... args) {
			String url = "http://10.0.2.2:8080/SBT/updateProfile.php";//192.168.42.11:8080/SBT/updateProfile.php";
			fullname = txt_fname.getText().toString();
			place = txt_address.getText().toString();
			phoneNo = txt_phone.getText().toString();
			mail = txt_email.getText().toString();
			busHault = txt_bus_hault.getText().toString();

			try {
				JSONParser parser = new JSONParser();
				nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("username", name));
				nameValuePairs
						.add(new BasicNameValuePair("fullname", fullname));
				nameValuePairs.add(new BasicNameValuePair("address", place));
				nameValuePairs.add(new BasicNameValuePair("phone", phoneNo));
				nameValuePairs.add(new BasicNameValuePair("email", mail));
				nameValuePairs
						.add(new BasicNameValuePair("bus_hault", busHault));

				String success = parser.makeHttpRequest(url, nameValuePairs).trim();

				if (success.equalsIgnoreCase("true")) {
					Log.d("Profile Update!", "Profile updated");
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(getBaseContext(), "Profile Updated",
									Toast.LENGTH_SHORT).show();
							txt_fname.setEnabled(false);
							txt_address.setEnabled(false);
							txt_phone.setEnabled(false);
							txt_email.setEnabled(false);
							txt_bus_hault.setEnabled(false);
							txt_fname.setFocusable(false);
							update.setEnabled(false);
						}
					});

				} else {
					Log.d("Profile Update!", "Profile not updated");
					Log.d("Success", success);

					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							Toast.makeText(getBaseContext(),
									"Profile Not Updated", Toast.LENGTH_SHORT)
									.show();
							txt_fname.setEnabled(true);
							txt_address.setEnabled(true);
							txt_phone.setEnabled(true);
							txt_email.setEnabled(true);
							txt_fname.setFocusable(true);
						}
					});

				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.d("Error!", "Connection error");
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						 Toast.makeText(getBaseContext(),"Connection Error",Toast.LENGTH_SHORT).show();
					}
				});
}
			return null;
		}
	}

}
