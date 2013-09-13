package com.thanu.schoolbustracker;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	

	private HttpClient httpClient;
	private HttpPost httpPost;
	private HttpResponse httpResponse;
	private HttpEntity entity;
	
	String response;

	public JSONParser() {

	}

	public String makeHttpRequest(String url, List<NameValuePair> param) {
		// Making HTTP request
		httpClient = new DefaultHttpClient();
		httpPost = new HttpPost(url);

		try {

			httpPost.setEntity(new UrlEncodedFormEntity(param));
			httpResponse = httpClient.execute(httpPost);//getting httpResponse

			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				entity = httpResponse.getEntity();//get the entity
				if (entity != null) {
					InputStream ins = entity.getContent();
					response = convertStreamToString(ins);					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.d("Error!", "Connection error");
			// Toast.makeText(getBaseContext(),"Connection Error",Toast.LENGTH_SHORT).show();
		}
		return response;
	}

	private static String convertStreamToString(InputStream is) {
		//parsing response into a string
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

	}
}
