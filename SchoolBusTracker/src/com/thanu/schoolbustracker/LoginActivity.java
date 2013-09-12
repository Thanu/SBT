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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener {
	private String username, password;
	EditText uname, pword;
	TextView status;
	Button login;
	HttpClient httpClient;
	HttpPost httpPost;
	ArrayList<NameValuePair> nameValuePairs;
	HttpResponse httpResponse;
	HttpEntity entity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initialise();
		login.setOnClickListener(this);
	}

	private void initialise() {
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

			// httpClient = new DefaultHttpClient();
			// httpPost = new HttpPost("http://10.0.2.2:8080/SBT/signin.php");
			String url = "http://10.0.2.2:8080/SBT/signin.php";
			username = uname.getText().toString();
			password = pword.getText().toString();

			try {
				nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs
						.add(new BasicNameValuePair("username", username));
				nameValuePairs
						.add(new BasicNameValuePair("password", password));

				JSONParser parser = new JSONParser();

				/*
				 * httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				 * httpResponse = httpClient.execute(httpPost);
				 * 
				 * if(httpResponse.getStatusLine().getStatusCode()==200){
				 * entity= httpResponse.getEntity(); if(entity != null){
				 * InputStream ins = entity.getContent(); JSONObject
				 * jsonResponse = new JSONObject(convertStreamToString(ins));
				 */

				JSONObject jsonResponse = new JSONObject(
						parser.makeHttpRequest(url, nameValuePairs));
				String retPword = jsonResponse.getString("password");

				// validate login
				if (password.equals(retPword)) {
					Log.d("Login!", "Login Success");
					// Intent i = new Intent(getApplicationContext(),
					// RouteActivity.class);
					if (username.equals("Admin")) {
						Intent i = new Intent(getApplicationContext(),
								Activity.class);
						i.putExtra("uname", username);
						startActivity(i);
					} else {
						Intent i = new Intent(getApplicationContext(),
								UserActivity.class);
						i.putExtra("uname", username);
						startActivity(i);
					}

				} else {
					Log.d("Login!", "Login Not Success");
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							status.setText("Your usernsme or passsword is incorrect");
							uname.setText("");
							pword.setText("");

						}
					});

					// Toast.makeText(getBaseContext(),"Login unsuccess",Toast.LENGTH_SHORT).show();
				}
				// }
				// }
			} catch (Exception e) {
				e.printStackTrace();
				Log.d("Error!", "Connection error");
				Log.d("Error!", e.getMessage());
				// Toast.makeText(getBaseContext(),"Connection Error",Toast.LENGTH_SHORT).show();
			}
			return null;
		}
	}

	/*
	 * private static String convertStreamToString(InputStream is) {
	 * StringBuilder sb = new StringBuilder(); String line = null; try {
	 * BufferedReader reader = new BufferedReader(new InputStreamReader( is,
	 * "iso-8859-1"), 8); while ((line = reader.readLine()) != null) {
	 * sb.append(line + "\n"); }
	 * 
	 * } catch (Exception e) { Log.d("Error in conversion", "Error: " + e);
	 * e.printStackTrace(); } finally { try { is.close(); } catch (Exception e)
	 * { e.printStackTrace(); } } return sb.toString(); }
	 */

}
