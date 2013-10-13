package com.thanu.schoolbustracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	Button signin, signup;
	boolean isBackButtonPressed;
	//for app home page
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		signin = (Button) findViewById(R.id.btnSignIn);
		signup = (Button) findViewById(R.id.btnSignUp);
		signin.setOnClickListener(this);
		signup.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSignIn:// starting login activity
			Intent i1 = new Intent(getApplicationContext(),LoginActivity.class);
			startActivity(i1);
			break;
		case R.id.btnSignUp:// starting register activity
			Intent i2 = new Intent(getApplicationContext(),
					RegisterActivity.class);
			startActivity(i2);
			break;
		default:
			break;
		}
	}
	
	//handle back button press
    @Override
    public void onBackPressed() 
    {
        isBackButtonPressed = true;
        super.onBackPressed();
    }

}
