package com.thanu.schoolbustracker.test;

import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.widget.Button;
import com.thanu.schoolbustracker.MainActivity;
import com.thanu.schoolbustracker.MyProfileActivity;

public class MyProfileActivityFunctionalTest extends
		ActivityInstrumentationTestCase2<MyProfileActivity> {
	private MyProfileActivity activity;

	public MyProfileActivityFunctionalTest() {
		super(MyProfileActivity.class);
		
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(false);
		activity = getActivity();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),activity.getClass());
		intent.putExtra("uname", "100553");
	}

	public void testStartMainActivity() throws Exception {
		
		// Add monitor to check for the main activity
		ActivityMonitor monitor = getInstrumentation().addMonitor(
				MainActivity.class.getName(), null, false);

		// Find button and click it
		Button view = (Button) activity
				.findViewById(com.thanu.schoolbustracker.R.id.btnLogout);
		TouchUtils.clickView(this, view);

		// Wait 2000ms for the start of the activity
		MainActivity startedActivity = (MainActivity) monitor
				.waitForActivityWithTimeout(2000);
		assertNotNull(startedActivity);

		// Search for the sign_in button view
		Button btnId1 = (Button) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.btnSignIn);

		// Check that the button is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				btnId1);
		// Validate the text on the button
		assertEquals("Text incorrect", "Sign In", btnId1.getText().toString());

		// Search for the sign_in button view
		Button btnId2 = (Button) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.btnSignUp);

		// Check that the button is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				btnId2);
		// Validate the text on the button
		assertEquals("Text incorrect", "Sign Up", btnId2.getText().toString());

		// Press back and click again
		this.sendKeys(KeyEvent.KEYCODE_BACK);
		TouchUtils.clickView(this, view);

	}

}
