package com.thanu.schoolbustracker.test;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.thanu.schoolbustracker.LoginActivity;
import com.thanu.schoolbustracker.UserActivity;

public class LoginActivityFunctionalTest extends ActivityInstrumentationTestCase2<LoginActivity> {
	private LoginActivity activity;

	  public LoginActivityFunctionalTest() {
	    super(LoginActivity.class);
	  }
	  
	  @Override
	  protected void setUp() throws Exception {
	    super.setUp();
	    setActivityInitialTouchMode(false);
	    activity = getActivity();
	  }

	  public void testStartUserActivity() throws Exception {
	    
	    // Add monitor to check for the user activity
	    ActivityMonitor monitor = getInstrumentation().addMonitor(UserActivity.class.getName(), null, false);

	    // Find button and click it
	    Button view = (Button) activity.findViewById(com.thanu.schoolbustracker.R.id.btnLogin);
	    TouchUtils.clickView(this, view);


	    // Wait 2000ms for the start of the activity
	    UserActivity startedActivity = (UserActivity) monitor
	        .waitForActivityWithTimeout(2000);
	    assertNotNull(startedActivity);

	    // Search for the tab
	    TabHost tabHost = (TabHost) startedActivity.findViewById(com.thanu.schoolbustracker.R.id.tabhost);
	    // Check that the T is on the screen
	    ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
	        textView1);
	    // Validate the text on the TextView
	    assertEquals("Text incorrect", "User Name", textView1.getText().toString());
	    
	    
	    // Press back and click again
	    this.sendKeys(KeyEvent.KEYCODE_BACK);
	    TouchUtils.clickView(this, view);

	  }


}
