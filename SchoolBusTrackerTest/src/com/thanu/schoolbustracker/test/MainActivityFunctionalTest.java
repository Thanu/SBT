package com.thanu.schoolbustracker.test;

import com.thanu.schoolbustracker.LoginActivity;
import com.thanu.schoolbustracker.MainActivity;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.widget.Button;
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
	    Button view = (Button) activity.findViewById(R.id.button1);
	    TouchUtils.clickView(this, view);

	    // To click on a click, e.g. in a listview
	    // listView.getChildAt(0);

	    // Wait 2 seconds for the start of the activity
	    LoginActivity startedActivity = (LoginActivity) monitor
	        .waitForActivityWithTimeout(2000);
	    assertNotNull(startedActivity);

	    // Search for the textView 
	    TextView textView = (TextView) startedActivity.findViewById(R.id);
	    
	    // Check that the TextView is on the screen
	    ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
	        textView);
	    // Validate the text on the TextView
	    assertEquals("Text incorrect", "Started", textView.getText().toString());
	    
	    // Press back and click again
	    this.sendKeys(KeyEvent.KEYCODE_BACK);
	    TouchUtils.clickView(this, view);

	  }

}
