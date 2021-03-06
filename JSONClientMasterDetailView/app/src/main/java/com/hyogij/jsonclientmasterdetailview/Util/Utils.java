package com.hyogij.jsonclientmasterdetailview.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.hyogij.jsonclientmasterdetailview.R;

/**
 * An utility class for sting and toast message.
 */
public class Utils {
    public static String getActvityTitle(String title, String header, String
            id) {
        StringBuilder actvityTitle = new StringBuilder(title);
        actvityTitle.append(" : ");
        actvityTitle.append(header);
        actvityTitle.append("(");
        actvityTitle.append(id);
        actvityTitle.append(")");
        return actvityTitle.toString();
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message,
                Toast.LENGTH_SHORT).show();
    }

    private static ProgressDialog progressDialog = null;

    public static void showProgresDialog(Context context) {
        // Showing progress dialog
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.wait));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void hideProgresDialog() {
        // Dismiss the progress dialog
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
