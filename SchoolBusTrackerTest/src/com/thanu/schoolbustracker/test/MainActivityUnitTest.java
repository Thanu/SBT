package com.thanu.schoolbustracker.test;

import android.content.Intent;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import com.thanu.schoolbustracker.MainActivity;

public class MainActivityUnitTest extends
		android.test.ActivityUnitTestCase<MainActivity> {

	private int buttonId1, buttonId2;
	private MainActivity activity;

	public MainActivityUnitTest() {
		super(com.thanu.schoolbustracker.MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				MainActivity.class);
		startActivity(intent, null, null);
		activity = getActivity();
	}

	@SmallTest
	public void testLayout() {
		// test the sign in button layout
		buttonId1 = com.thanu.schoolbustracker.R.id.btnSignIn;
		assertNotNull(activity.findViewById(buttonId1));
		Button view1 = (Button) activity.findViewById(buttonId1);
		assertEquals("Incorrect label of the button", "Sign In",
				view1.getText());

		// test the sign up button layout
		buttonId2 = com.thanu.schoolbustracker.R.id.btnSignUp;
		assertNotNull(activity.findViewById(buttonId2));
		Button view2 = (Button) activity.findViewById(buttonId2);
		assertEquals("Incorrect label of the button", "Sign Up",
				view2.getText());
	}

	@SmallTest
	public void testIntentTriggerViaOnClick() {
		
		// -----------------for sign in --------------------------
		// check sign in button view
		buttonId1 = com.thanu.schoolbustracker.R.id.btnSignIn;
		Button view1 = (Button) activity.findViewById(buttonId1);
		assertNotNull("Button not allowed to be null", view1);

		// call the sign in method directly via view1
		getActivity().onClick(view1);

		// Check the intent which was started
		Intent triggeredIntent1 = getStartedActivityIntent();
		assertNotNull("Intent was null", triggeredIntent1);
		
		

		// -----------------for sign up --------------------------
		// check sign up button view
		buttonId2 = com.thanu.schoolbustracker.R.id.btnSignUp;
		Button view2 = (Button) activity.findViewById(buttonId2);
		assertNotNull("Button not allowed to be null", view2);

		// call the sign up method directly via view2
		getActivity().onClick(view2);

		// Check the intent which was started
		Intent triggeredIntent2 = getStartedActivityIntent();
		assertNotNull("Intent was null", triggeredIntent2);

	}

	@Override
	protected void tearDown() throws Exception {

		super.tearDown();
	}

}
