package com.thanu.schoolbustracker;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class NotificationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
	}

	
	public void createNotification(View view) {
	    // Prepare intent which is triggered if the notification is selected
	    Intent intent = new Intent(this, NotificationActivity.class);
	    PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

	    // Build notification
	    Notification noti = new Notification.Builder(this)
	        .setContentTitle("New mail from " + "test@sbt.com")
	        .setContentText("Subject").setSmallIcon(R.drawable.ic_notification)
	        .setContentIntent(pIntent)
	        .addAction(R.drawable.ic_notification, "Call", pIntent)
	        .addAction(R.drawable.ic_notification, "More", pIntent)
	        .addAction(R.drawable.ic_notification, "And more", pIntent).build();
	    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	    // Hide the notification after its selected
	    noti.flags |= Notification.FLAG_AUTO_CANCEL;

	    notificationManager.notify(0, noti);

	  }

}
