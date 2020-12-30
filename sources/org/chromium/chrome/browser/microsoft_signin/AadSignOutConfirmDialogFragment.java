package org.chromium.chrome.browser.microsoft_signin;

import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.chromium.chrome.browser.edge_widget.BaseDialogFragment;

/* compiled from: PG */
public class AadSignOutConfirmDialogFragment extends BaseDialogFragment {

    /* renamed from: k */
    public SignOutListener f2073k;

    /* compiled from: PG */
    public interface SignOutListener {
        void onAcceptSignout();
    }

    /* renamed from: a */
    public void mo8844a(SignOutListener signOutListener) {
        this.f2073k = signOutListener;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        ss0.b("Settings", "SignOut", (String) null, new String[0]);
    }

    /* renamed from: p */
    public int mo8526p() {
        return rx0.aad_notify_signout_dialog;
    }

    /* renamed from: a */
    public void mo8519a(View view) {
        int i;
        ss0.a("Settings", "SignOut", (String) null, new String[0]);
        ((Button) mo8518a(ox0.cancel)).setOnClickListener(new a(this));
        ((Button) mo8518a(ox0.signout)).setOnClickListener(new b(this));
        TextView textView = (TextView) mo8518a(ox0.aad_account_signout_text);
        if (q92.d()) {
            i = wx0.aad_premium_signout_message;
        } else {
            i = wx0.aad_account_signout_message;
        }
        textView.setText(i);
    }
}
