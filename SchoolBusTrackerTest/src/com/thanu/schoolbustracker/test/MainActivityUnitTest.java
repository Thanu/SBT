package com.thanu.schoolbustracker.test;

import android.content.Intent;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import com.thanu.schoolbustracker.MainActivity;


public class MainActivityUnitTest extends
		android.test.ActivityUnitTestCase<MainActivity> {

	private int buttonId;
	private MainActivity activity;

	public MainActivityUnitTest() {
		super(MainActivity.class);
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

		buttonId = MainActivity.R.id.btnSignIn;
		assertNotNull(activity.findViewById(buttonId));
		Button view = (Button) activity.findViewById(buttonId);
		assertEquals("Incorrect label of the button", "Start", view.getText());
	}

	@SmallTest
	public void testIntentTriggerViaOnClick() {
		buttonId = MainActivity.R.id.btnSignIn;
		Button view = (Button) activity.findViewById(buttonId);
		assertNotNull("Button not allowed to be null", view);

		// You would call the method directly via
		getActivity().onClick(view);

		
		// Check the intent which was started
		Intent triggeredIntent = getStartedActivityIntent();
		assertNotNull("Intent was null", triggeredIntent);
		
	}

	@Override
	protected void tearDown() throws Exception {

		super.tearDown();
	}

}
