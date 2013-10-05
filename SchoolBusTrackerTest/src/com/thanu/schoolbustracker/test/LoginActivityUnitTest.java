package com.thanu.schoolbustracker.test;

import android.content.Intent;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.thanu.schoolbustracker.LoginActivity;

public class LoginActivityUnitTest extends
		android.test.ActivityUnitTestCase<LoginActivity> {

	private int buttonId1;
	private int textview1, textview2, text1, text2;
	private LoginActivity activity;

	public LoginActivityUnitTest() {
		super(com.thanu.schoolbustracker.LoginActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				LoginActivity.class);
		startActivity(intent, null, null);
		activity = getActivity();
	}

	@SmallTest
	public void testLayout() {

		// test the username textview layout
		textview1 = com.thanu.schoolbustracker.R.id.txtUserName;
		assertNotNull(activity.findViewById(textview1));
		TextView view1 = (TextView) activity.findViewById(textview1);
		assertEquals("Incorrect label of the button", "User Name",
				view1.getText());

		// test the password textview layout
		textview2 = com.thanu.schoolbustracker.R.id.txtPassword;
		assertNotNull(activity.findViewById(textview2));
		TextView view2 = (TextView) activity.findViewById(textview2);
		assertEquals("Incorrect label of the button", "Password",
				view2.getText());

		// test the username edittext layout
		text1 = com.thanu.schoolbustracker.R.id.uname;
		assertNotNull(activity.findViewById(text1));

		// test the password edittext layout
		text2 = com.thanu.schoolbustracker.R.id.pword;
		assertNotNull(activity.findViewById(text1));

		// test the login button layout
		buttonId1 = com.thanu.schoolbustracker.R.id.btnLogin;
		assertNotNull(activity.findViewById(buttonId1));
		Button view3 = (Button) activity.findViewById(buttonId1);
		assertEquals("Incorrect label of the button", "Login", view3.getText());

	}

	@SmallTest
	public void testIntentTriggerViaOnClick() {
		// check login button view
		buttonId1 = com.thanu.schoolbustracker.R.id.btnLogin;
		Button view1 = (Button) activity.findViewById(buttonId1);
		assertNotNull("Button not allowed to be null", view1);
		
		text1 = com.thanu.schoolbustracker.R.id.uname;
		EditText view4 = (EditText) activity.findViewById(text1);		
		view4.setText("100553");
		text2 = com.thanu.schoolbustracker.R.id.pword;
		EditText view5 = (EditText) activity.findViewById(text2);		
		view5.setText("thanuja");

		// call the login method directly via view1
		getActivity().onClick(view1);

		// Check the intent which was started
		Intent triggeredIntent1 = getStartedActivityIntent();
		assertNotNull("Intent was null", triggeredIntent1);

	}

	@Override
	protected void tearDown() throws Exception {

		super.tearDown();
	}

}
