package com.thanu.schoolbustracker.test;

import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.thanu.schoolbustracker.LoginActivity;
import com.thanu.schoolbustracker.MainActivity;
import com.thanu.schoolbustracker.UserActivity;

public class UserActivityUnitTest extends
		android.test.ActivityUnitTestCase<UserActivity> {

	private int buttonId1, buttonId2;
	private UserActivity activity;

	public UserActivityUnitTest() {
		super(com.thanu.schoolbustracker.UserActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				UserActivity.class);
		startActivity(intent, null, null);
		activity = getActivity();
	}

	@SmallTest
	public void testLayout() {
		// test the sign in button layout
		buttonId1 = com.thanu.schoolbustracker.R.id.;
		assertNotNull(activity.findViewById(buttonId1));
		Button view1 = (Button) activity.findViewById(buttonId1);
		assertEquals("Incorrect label of the button", "Sign In",
				view1.getText());

		// test the sign up button layout
		buttonId1 = com.thanu.schoolbustracker.R.id.btnSignIn;
		assertNotNull(activity.findViewById(buttonId1));
		Button view2 = (Button) activity.findViewById(buttonId1);
		assertEquals("Incorrect label of the button", "Sign In",
				view2.getText());
	}

	@SmallTest
	public void testIntentTriggerViaOnClick() {
		// check sign in button view
		buttonId1 = com.thanu.schoolbustracker.R.id.btnSignIn;
		Button view1 = (Button) activity.findViewById(buttonId1);
		assertNotNull("Button not allowed to be null", view1);

		// call the sign in method directly via view1
		getActivity().onClick(view1);

		// Check the intent which was started
		Intent triggeredIntent1 = getStartedActivityIntent();
		assertNotNull("Intent was null", triggeredIntent1);

		// check sign up button view
		buttonId2 = com.thanu.schoolbustracker.R.id.btnSignIn;
		Button view2 = (Button) activity.findViewById(buttonId2);
		assertNotNull("Button not allowed to be null", view2);

		// call the sign in method directly via view2
		getActivity().onClick(view1);

		// Check the intent which was started
		Intent triggeredIntent2 = getStartedActivityIntent();
		assertNotNull("Intent was null", triggeredIntent2);

	}

	@Override
	protected void tearDown() throws Exception {

		super.tearDown();
	}

}
