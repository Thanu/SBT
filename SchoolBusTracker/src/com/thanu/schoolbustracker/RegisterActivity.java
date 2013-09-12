package com.thanu.schoolbustracker;

import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

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

public class RegisterActivity extends Activity implements OnClickListener {

	private String username, pword, pword1, mail, busHault;
	private String firstname, sex, place, phoneNo;

	EditText uname, password, password1, email, bus_hault, fname,
			address, phone;
	TextView status;
	Button register;
	RadioGroup gender;
	RadioButton button;

	HttpClient httpClient;
	HttpPost httpPost;
	ArrayList<NameValuePair> nameValuePairs;
	HttpResponse httpResponse;
	HttpEntity entity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initialise();
		register.setOnClickListener(this);
	}

	private void initialise() {
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
		new AttemptLogin().execute();
	}

	class AttemptLogin extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... args) {
			//httpClient = new DefaultHttpClient();
			//httpPost = new HttpPost("http://10.0.2.2:8080/SBT/signup.php");
			username = uname.getText().toString();
			firstname = fname.getText().toString();			
			button = (RadioButton)findViewById(gender.getCheckedRadioButtonId());
			sex= button.getText().toString();
			place = address.getText().toString();
			phoneNo = phone.getText().toString();
			pword = password.getText().toString();
			pword1 = password1.getText().toString();
			mail = email.getText().toString();
			busHault = bus_hault.getText().toString();

			try {
				
				if (pword.equals(pword1)) {
					String url = "http://10.0.2.2:8080/SBT/signup.php";
					JSONParser parser = new JSONParser();
					
					nameValuePairs = new ArrayList<NameValuePair>();

					nameValuePairs.add(new BasicNameValuePair("username",
							username));
					nameValuePairs.add(new BasicNameValuePair("fullname",
							firstname));
					nameValuePairs.add(new BasicNameValuePair("gender", sex));
					nameValuePairs
							.add(new BasicNameValuePair("address", place));
					nameValuePairs
							.add(new BasicNameValuePair("phone", phoneNo));
					nameValuePairs.add(new BasicNameValuePair("pword", pword));
					nameValuePairs.add(new BasicNameValuePair("email", mail));
					nameValuePairs.add(new BasicNameValuePair("bus_hault",
							busHault));

					/*httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					httpResponse = httpClient.execute(httpPost);

					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						entity = httpResponse.getEntity();
						if (entity != null) {
							InputStream ins = entity.getContent();*/
							String success = parser.makeHttpRequest(url, nameValuePairs).trim();
							
							// validate registration
							if (success.equalsIgnoreCase("true")) {
								Log.d("Register!", "Register Success");
								Intent i = new Intent(getApplicationContext(),
										UserActivity.class);
								i.putExtra("uname",username);
								startActivity(i);
								// Toast.makeText(getBaseContext(), "SUCCESS",
								// Toast.LENGTH_LONG).show();

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
						//}
					//}
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

	/*private static String convertStreamToString(InputStream is) {
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}

		} catch (Exception e) {
			Log.d("Error in conversion", "Error: " + e);
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Log.d("Response!", sb.toString());
		return sb.toString();

	}*/

}
