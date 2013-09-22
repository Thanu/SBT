package com.thanu.schoolbustracker.test;

import android.content.Intent;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import com.thanu.schoolbustracker.LoginActivity;

public class LoginActivityUnitTest extends
		android.test.ActivityUnitTestCase<LoginActivity> {

	private int buttonId1;
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
		// test the login button layout
		buttonId1 = com.thanu.schoolbustracker.R.id.btnLogin;
		assertNotNull(activity.findViewById(buttonId1));
		Button view1 = (Button) activity.findViewById(buttonId1);
		assertEquals("Incorrect label of the button", "Login",
				view1.getText());

	}

	@SmallTest
	public void testIntentTriggerViaOnClick() {
		// check login button view
		buttonId1 = com.thanu.schoolbustracker.R.id.btnLogin;
		Button view1 = (Button) activity.findViewById(buttonId1);
		assertNotNull("Button not allowed to be null", view1);

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
