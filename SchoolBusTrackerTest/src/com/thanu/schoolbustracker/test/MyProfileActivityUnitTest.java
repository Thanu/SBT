package com.thanu.schoolbustracker.test;

import android.content.Intent;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.TextView;

import com.thanu.schoolbustracker.MyProfileActivity;
import com.thanu.schoolbustracker.UserActivity;

public class MyProfileActivityUnitTest extends
		android.test.ActivityUnitTestCase<MyProfileActivity> {

	private int buttonId1, buttonId2, buttonId3, buttonId4, buttonId5, group,
			btn1, btn2, gender;
	private int textview1, textview2, textview3, textview4, textview5,
			textview6, textview7, textview8, textview9;
	private int text1, text2, text3, text4, text5, text6, text7, text8;
	private MyProfileActivity activity;

	public MyProfileActivityUnitTest() {
		super(com.thanu.schoolbustracker.MyProfileActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				MyProfileActivity.class);
		intent.putExtra("uname", "100553");
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

		// test the fullname textview layout
		textview2 = com.thanu.schoolbustracker.R.id.txtFullName;
		assertNotNull(activity.findViewById(textview2));
		TextView view2 = (TextView) activity.findViewById(textview2);
		assertEquals("Incorrect label of the button", "Full Name",
				view2.getText());

		// test the address textview layout
		textview3 = com.thanu.schoolbustracker.R.id.txtAddress;
		assertNotNull(activity.findViewById(textview3));
		TextView view3 = (TextView) activity.findViewById(textview3);
		assertEquals("Incorrect label of the button", "Address",
				view3.getText());

		// test the phone textview layout
		textview4 = com.thanu.schoolbustracker.R.id.txtPhone;
		assertNotNull(activity.findViewById(textview4));
		TextView view4 = (TextView) activity.findViewById(textview4);
		assertEquals("Incorrect label of the button", "Phone", view4.getText());

		// test the password textview layout
		textview5 = com.thanu.schoolbustracker.R.id.txtPassword;
		assertNotNull(activity.findViewById(textview5));
		TextView view5 = (TextView) activity.findViewById(textview5);
		assertEquals("Incorrect label of the button", "Password",
				view5.getText());

		// test the re-enter password textview layout
		textview6 = com.thanu.schoolbustracker.R.id.txtReEnterPassword;
		assertNotNull(activity.findViewById(textview6));
		TextView view6 = (TextView) activity.findViewById(textview6);
		assertEquals("Incorrect label of the button", "Re-enter Password",
				view6.getText());

		// test the email textview layout
		textview7 = com.thanu.schoolbustracker.R.id.txtEmail;
		assertNotNull(activity.findViewById(textview7));
		TextView view7 = (TextView) activity.findViewById(textview7);
		assertEquals("Incorrect label of the button", "Email", view7.getText());

		// test the bus_hault textview layout
		textview8 = com.thanu.schoolbustracker.R.id.txtBusHault;
		assertNotNull(activity.findViewById(textview8));
		TextView view8 = (TextView) activity.findViewById(textview8);
		assertEquals("Incorrect label of the button", "Bus Hault",
				view8.getText());

		// test the gender textview layout
		textview9 = com.thanu.schoolbustracker.R.id.txtGender;
		assertNotNull(activity.findViewById(textview9));
		TextView view9 = (TextView) activity.findViewById(textview9);
		assertEquals("Incorrect label of the button", "Gender", view9.getText());

		// test the gender radio group layout
		group = com.thanu.schoolbustracker.R.id.gender;
		assertNotNull(activity.findViewById(group));

		// test the gender text view layout
		gender = com.thanu.schoolbustracker.R.id.txtGender;
		assertNotNull(activity.findViewById(gender));
		TextView view17 = (TextView) activity.findViewById(gender);
		assertEquals("Incorrect label of the button", "Gender",
				view17.getText());

		// test the male radio button layout
		btn1 = com.thanu.schoolbustracker.R.id.male;
		assertNotNull(activity.findViewById(btn1));
		TextView view10 = (TextView) activity.findViewById(btn1);
		assertEquals("Incorrect label of the button", "Male", view10.getText());

		// test the female radio button layout
		btn2 = com.thanu.schoolbustracker.R.id.female;
		assertNotNull(activity.findViewById(btn2));
		TextView view11 = (TextView) activity.findViewById(btn2);
		assertEquals("Incorrect label of the button", "Female",
				view11.getText());

		// test the username edittext layout
		text1 = com.thanu.schoolbustracker.R.id.uname;
		assertNotNull(activity.findViewById(text1));

		// test the firstname edittext layout
		text2 = com.thanu.schoolbustracker.R.id.fname;
		assertNotNull(activity.findViewById(text2));

		// test the address edittext layout
		text3 = com.thanu.schoolbustracker.R.id.address;
		assertNotNull(activity.findViewById(text3));

		// test the phone edittext layout
		text4 = com.thanu.schoolbustracker.R.id.phone;
		assertNotNull(activity.findViewById(text4));

		// test the password edittext layout
		text5 = com.thanu.schoolbustracker.R.id.password;
		assertNotNull(activity.findViewById(text5));

		// test the password1 edittext layout
		text6 = com.thanu.schoolbustracker.R.id.password1;
		assertNotNull(activity.findViewById(text6));

		// test the email edittext layout
		text7 = com.thanu.schoolbustracker.R.id.email;
		assertNotNull(activity.findViewById(text7));

		// test the bus_hault edittext layout
		text8 = com.thanu.schoolbustracker.R.id.bus_hault;
		assertNotNull(activity.findViewById(text8));

		// test the change_password button layout
		buttonId1 = com.thanu.schoolbustracker.R.id.btnChangePassword;
		assertNotNull(activity.findViewById(buttonId1));
		Button view12 = (Button) activity.findViewById(buttonId1);
		assertEquals("Incorrect label of the button", "Change Password",
				view12.getText());

		// test the update_profile button layout
		buttonId2 = com.thanu.schoolbustracker.R.id.btnUpdateProfile;
		assertNotNull(activity.findViewById(buttonId2));
		Button view13 = (Button) activity.findViewById(buttonId2);
		assertEquals("Incorrect label of the button", "Update Profile",
				view13.getText());

		// test the save button layout
		buttonId3 = com.thanu.schoolbustracker.R.id.btnSave;
		assertNotNull(activity.findViewById(buttonId3));
		Button view14 = (Button) activity.findViewById(buttonId3);
		assertEquals("Incorrect label of the button", "Save", view14.getText());

		// test the update button layout
		buttonId4 = com.thanu.schoolbustracker.R.id.btnUpdate;
		assertNotNull(activity.findViewById(buttonId4));
		Button view15 = (Button) activity.findViewById(buttonId4);
		assertEquals("Incorrect label of the button", "Update",
				view15.getText());

		// test the logout button layout
		buttonId5 = com.thanu.schoolbustracker.R.id.btnLogout;
		assertNotNull(activity.findViewById(buttonId5));
		Button view16 = (Button) activity.findViewById(buttonId5);
		assertEquals("Incorrect label of the button", "Log out",
				view16.getText());

	}

	@SmallTest
	public void testIntentTriggerViaOnClick() {
		// check logout button view
		buttonId5 = com.thanu.schoolbustracker.R.id.btnLogout;
		Button view = (Button) activity.findViewById(buttonId5);
		assertNotNull("Button not allowed to be null", view);

		// call the register method directly via view1
		getActivity().onClick(view);

		// Check the intent which was started
		Intent triggeredIntent1 = getStartedActivityIntent();
		assertNotNull("Intent was null", triggeredIntent1);

	}

	@Override
	protected void tearDown() throws Exception {

		super.tearDown();
	}

}
