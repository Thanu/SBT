package com.thanu.schoolbustracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
 
public class AlertDialogManager {
    /**
     * Function to display simple Alert Dialog
     * context - application context
     * title - alert dialog title
     * message - alert message
     * status - success/failure (used to set icon)
     *               - pass null if you don't want icon
     * */
    @SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message,
            Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(title);
 
        // Setting Dialog Message
        alertDialog.setMessage(message);
 
        if(status != null)
            // Setting alert dialog icon
            alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);
 
        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
 
        // Showing Alert Message
        alertDialog.show();
    }
}