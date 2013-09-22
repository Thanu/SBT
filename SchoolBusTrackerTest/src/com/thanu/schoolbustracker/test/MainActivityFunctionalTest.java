package com.thanu.schoolbustracker.test;

import com.thanu.schoolbustracker.LoginActivity;
import com.thanu.schoolbustracker.MainActivity;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivityFunctionalTest extends ActivityInstrumentationTestCase2<MainActivity> {
	private MainActivity activity;

	  public MainActivityFunctionalTest() {
	    super(MainActivity.class);
	  }
	  
	  @Override
	  protected void setUp() throws Exception {
	    super.setUp();
	    setActivityInitialTouchMode(false);
	    activity = getActivity();
	  }

	  public void testStartLoginActivity() throws Exception {
	    
	    // Add monitor to check for the login activity
	    ActivityMonitor monitor = getInstrumentation().addMonitor(LoginActivity.class.getName(), null, false);

	    // Find button and click it
	    Button view = (Button) activity.findViewById(com.thanu.schoolbustracker.R.id.btnSignIn);
	    TouchUtils.clickView(this, view);


	    // Wait 2000ms for the start of the activity
	    LoginActivity startedActivity = (LoginActivity) monitor
	        .waitForActivityWithTimeout(2000);
	    assertNotNull(startedActivity);

	    // Search for the textView 
	    TextView textView1 = (TextView) startedActivity.findViewById(com.thanu.schoolbustracker.R.id.txtUserName);
	    
	    // Check that the TextView is on the screen
	    ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
	        textView1);
	    // Validate the text on the TextView
	    assertEquals("Text incorrect", "User Name", textView1.getText().toString());
	    
	    // Search for the textView 
	    TextView textView2 = (TextView) startedActivity.findViewById(com.thanu.schoolbustracker.R.id.txtPassword);
	    
	    // Check that the TextView is on the screen
	    ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
	        textView2);
	    // Validate the text on the TextView
	    assertEquals("Text incorrect", "Password", textView2.getText().toString());
	    
	    // Search for the text1 
	    EditText text1 = (EditText) startedActivity.findViewById(com.thanu.schoolbustracker.R.id.uname);
	    
	    // Check that the EditText is on the screen
	    ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
	        text1);
	   
	    // Search for the text1 
	    EditText text2 = (EditText) startedActivity.findViewById(com.thanu.schoolbustracker.R.id.pword);
	    
	    // Check that the EditText is on the screen
	    ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
	        text2);
	    
	    
	    //search for the button1
	    Button button1 = (Button) startedActivity.findViewById(com.thanu.schoolbustracker.R.id.btnLogin);
	    
	    // Check that the Button is on the screen
	    ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
	        button1);
	    // Validate the text on the Button
	    assertEquals("Text incorrect", "Login", button1.getText().toString());
	    
	    
	    // Press back and click again
	    this.sendKeys(KeyEvent.KEYCODE_BACK);
	    TouchUtils.clickView(this, view);

	  }

}
