package org.chromium.chrome.browser.init;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertController;
import android.util.Log;
import com.microsoft.intune.mam.client.support.v4.app.MAMDialogFragment;

/* compiled from: PG */
public class InvalidStartupDialog extends MAMDialogFragment {
    /* renamed from: a */
    public static void m2501a(Activity activity, int i) {
        int i2;
        if (i == 2) {
            i2 = wx0.os_version_missing_features;
        } else if (i != 3) {
            i2 = wx0.native_startup_failed;
        } else {
            i2 = wx0.incompatible_libraries;
        }
        String string = activity.getResources().getString(i2);
        if (!(activity instanceof FragmentActivity)) {
            Log.e("InvalidStartupDialog", "Unable to start chrome due to: " + i2);
            System.exit(-1);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("InvalidStartupErrorKey", string);
        InvalidStartupDialog invalidStartupDialog = new InvalidStartupDialog();
        invalidStartupDialog.setArguments(bundle);
        invalidStartupDialog.show(((FragmentActivity) activity).getSupportFragmentManager(), "InvalidStartupDialog");
    }

    public void onDismiss(DialogInterface dialogInterface) {
        InvalidStartupDialog.super.onDismiss(dialogInterface);
        System.exit(-1);
    }

    public Dialog onMAMCreateDialog(Bundle bundle) {
        String string = getArguments().getString("InvalidStartupErrorKey", "Failed to start");
        qd0 qd0 = new qd0(getActivity());
        AlertController.AlertParams alertParams = qd0.a;
        alertParams.h = string;
        alertParams.m = true;
        qd0.b(getResources().getString(17039370), (DialogInterface.OnClickListener) null);
        return qd0.a();
    }
}
