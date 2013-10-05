package com.thanu.schoolbustracker.test;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.thanu.schoolbustracker.MyProfileActivity;
import com.thanu.schoolbustracker.UserActivity;

public class UserActivityFunctionalTest extends
		ActivityInstrumentationTestCase2<UserActivity> {
	private UserActivity activity;

	public UserActivityFunctionalTest() {
		super(UserActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(false);
		activity = getActivity();
	}

	public void testStartMyProfileActivity() throws Exception {

		// Add monitor to check for the login activity
		ActivityMonitor monitor = getInstrumentation().addMonitor(
				MyProfileActivity.class.getName(), null, false);

		// Find button and click it
		Button view = (Button) activity
				.findViewById(com.thanu.schoolbustracker.R.id.btnSignIn);
		TouchUtils.clickView(this, view);

		// Wait 2000ms for the start of the activity
		MyProfileActivity startedActivity = (MyProfileActivity) monitor
				.waitForActivityWithTimeout(2000);
		assertNotNull(startedActivity);

		// Search for the textView
		TextView textView1 = (TextView) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.txtUserName);

		// Check that the TextView is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				textView1);
		// Validate the text on the TextView
		assertEquals("Text incorrect", "User Name", textView1.getText()
				.toString());

		// Search for the textView
		TextView textView2 = (TextView) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.txtFullName);

		// Check that the TextView is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				textView2);
		// Validate the text on the TextView
		assertEquals("Text incorrect", "Full Name", textView2.getText()
				.toString());

		// Search for the textView
		TextView textView3 = (TextView) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.txtAddress);

		// Check that the TextView is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				textView3);
		// Validate the text on the TextView
		assertEquals("Text incorrect", "Address", textView3.getText()
				.toString());

		// Search for the textView
		TextView textView4 = (TextView) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.txtPhone);

		// Check that the TextView is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				textView4);
		// Validate the text on the TextView
		assertEquals("Text incorrect", "Phone", textView4.getText().toString());

		// Search for the textView
		TextView textView5 = (TextView) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.txtPassword);

		// Check that the TextView is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				textView5);
		// Validate the text on the TextView
		assertEquals("Text incorrect", "Password", textView5.getText()
				.toString());

		// Search for the textView
		TextView textView6 = (TextView) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.txtReEnterPassword);

		// Check that the TextView is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				textView6);
		// Validate the text on the TextView
		assertEquals("Text incorrect", "Re-enter Password", textView6.getText()
				.toString());

		// Search for the textView
		TextView textView7 = (TextView) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.txtEmail);

		// Check that the TextView is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				textView7);
		// Validate the text on the TextView
		assertEquals("Text incorrect", "Email", textView7.getText().toString());

		// Search for the textView
		TextView textView8 = (TextView) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.txtBusHault);

		// Check that the TextView is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				textView8);
		// Validate the text on the TextView
		assertEquals("Text incorrect", "Bus Hault", textView8.getText()
				.toString());

		// -----------------------------------------------------------------------------

		// Search for the text1
		EditText text1 = (EditText) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.uname);

		// Check that the EditText is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				text1);

		// Search for the text1
		EditText text2 = (EditText) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.fname);

		// Check that the EditText is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				text2);

		// Search for the text1
		EditText text3 = (EditText) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.address);

		// Check that the EditText is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				text3);

		// Search for the text1
		EditText text4 = (EditText) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.phone);

		// Check that the EditText is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				text4);

		// Search for the text1
		EditText text5 = (EditText) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.password);

		// Check that the EditText is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				text5);

		// Search for the text1
		EditText text6 = (EditText) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.password1);

		// Check that the EditText is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				text6);

		// Search for the text1
		EditText text7 = (EditText) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.email);

		// Check that the EditText is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				text7);

		// Search for the text1
		EditText text8 = (EditText) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.bus_hault);

		// Check that the EditText is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				text8);

		// --------------------------------------------------------------------
		RadioGroup group1 = (RadioGroup) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.gender);
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				group1);

		RadioButton btn1 = (RadioButton) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.male);
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				btn1);

		RadioButton btn2 = (RadioButton) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.female);
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				btn2);

		// -----------------------------------------------------------------------
		// search for the button1
		Button button1 = (Button) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.btnChangePassword);

		// Check that the Button is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				button1);
		// Validate the text on the Button
		assertEquals("Text incorrect", "Change Password", button1.getText()
				.toString());

		// search for the button2
		Button button2 = (Button) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.btnUpdateProfile);

		// Check that the Button is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				button2);
		// Validate the text on the Button
		assertEquals("Text incorrect", "Update Profile", button2.getText()
				.toString());

		// search for the button3
		Button button3 = (Button) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.btnSave);

		// Check that the Button is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				button3);
		// Validate the text on the Button
		assertEquals("Text incorrect", "Save", button3.getText().toString());

		// search for the button4
		Button button4 = (Button) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.btnUpdate);

		// Check that the Button is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				button4);
		// Validate the text on the Button
		assertEquals("Text incorrect", "Update", button4.getText().toString());

		// search for the button5
		Button button5 = (Button) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.btnLogout);

		// Check that the Button is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				button5);
		// Validate the text on the Button
		assertEquals("Text incorrect", "Log out", button5.getText().toString());


		// Press back and click again
		this.sendKeys(KeyEvent.KEYCODE_BACK);
		TouchUtils.clickView(this, view);

	}

}
