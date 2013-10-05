package com.thanu.schoolbustracker.test;

import com.thanu.schoolbustracker.LoginActivity;
import com.thanu.schoolbustracker.MainActivity;
import com.thanu.schoolbustracker.RegisterActivity;

import android.app.Instrumentation.ActivityMonitor;
import android.os.Handler;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivityFunctionalTest extends
		ActivityInstrumentationTestCase2<MainActivity> {
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
		ActivityMonitor monitor = getInstrumentation().addMonitor(
				LoginActivity.class.getName(), null, false);

		// Find button and click it
		Button view = (Button) activity
				.findViewById(com.thanu.schoolbustracker.R.id.btnSignIn);
		TouchUtils.clickView(this, view);

		// Wait 2000ms for the start of the activity
		LoginActivity startedActivity = (LoginActivity) monitor
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
				.findViewById(com.thanu.schoolbustracker.R.id.txtPassword);

		// Check that the TextView is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				textView2);
		// Validate the text on the TextView
		assertEquals("Text incorrect", "Password", textView2.getText()
				.toString());

		// Search for the text1
		EditText text1 = (EditText) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.uname);

		// Check that the EditText is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				text1);

		// Search for the text1
		EditText text2 = (EditText) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.pword);

		// Check that the EditText is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				text2);

		// search for the button1
		Button button1 = (Button) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.btnLogin);

		// Check that the Button is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				button1);
		// Validate the text on the Button
		assertEquals("Text incorrect", "Login", button1.getText().toString());

		// Press back and click again
		this.sendKeys(KeyEvent.KEYCODE_BACK);
		TouchUtils.clickView(this, view);

		this.sendKeys(KeyEvent.KEYCODE_BACK);

	}

	public void testStartRegisterActivity() throws Exception {

		// Add monitor to check for the register activity
		ActivityMonitor monitor = getInstrumentation().addMonitor(
				RegisterActivity.class.getName(), null, false);

		// Find button and click it
		Button view = (Button) activity
				.findViewById(com.thanu.schoolbustracker.R.id.btnSignUp);
		TouchUtils.clickView(this, view);

		// Wait 2000ms for the start of the activity
		RegisterActivity startedActivity = (RegisterActivity) monitor
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

		// Search for the textView
		TextView textView8 = (TextView) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.txtBusHault);

		// Check that the TextView is on the screen
		ViewAsserts.assertOffScreenBelow(startedActivity.getWindow()
				.getDecorView(), textView8);
		// Validate the text on the TextView
		assertEquals("Text incorrect", "Bus Hault", textView8.getText()
				.toString());

		// Search for the text1
		EditText text8 = (EditText) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.bus_hault);

		// Check that the EditText is on the screen
		ViewAsserts.assertOffScreenBelow(startedActivity.getWindow()
				.getDecorView(), text8);

		// search for the button1
		Button button1 = (Button) startedActivity
				.findViewById(com.thanu.schoolbustracker.R.id.btnRegister);

		// Check that the Button is on the screen
		ViewAsserts.assertOffScreenBelow(startedActivity.getWindow()
				.getDecorView(), button1);
		// Validate the text on the Button
		assertEquals("Text incorrect", "Register", button1.getText().toString());

		// Press back and click again
		this.sendKeys(KeyEvent.KEYCODE_BACK);
		TouchUtils.clickView(this, view);

	}

}
