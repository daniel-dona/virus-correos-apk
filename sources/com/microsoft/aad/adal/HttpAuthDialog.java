package com.microsoft.aad.adal;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/* compiled from: PG */
public class HttpAuthDialog {
    public CancelListener mCancelListener;
    public final Context mContext;
    public AlertDialog mDialog = null;
    public final String mHost;
    public OkListener mOkListener;
    public EditText mPasswordView;
    public final String mRealm;
    public EditText mUsernameView;

    /* compiled from: PG */
    public interface CancelListener {
        void onCancel();
    }

    /* compiled from: PG */
    public interface OkListener {
        void onOk(String str, String str2, String str3, String str4);
    }

    public HttpAuthDialog(Context context, String str, String str2) {
        this.mContext = context;
        this.mHost = str;
        this.mRealm = str2;
        createDialog();
    }

    @SuppressLint({"InflateParams"})
    private void createDialog() {
        View inflate = LayoutInflater.from(this.mContext).inflate(this.mContext.getResources().getLayout(rx0.http_auth_dialog), (ViewGroup) null);
        this.mUsernameView = (EditText) inflate.findViewById(ox0.editUserName);
        this.mPasswordView = (EditText) inflate.findViewById(ox0.editPassword);
        this.mPasswordView.setOnEditorActionListener(new 1(this));
        this.mDialog = new AlertDialog.Builder(this.mContext).setTitle(this.mContext.getText(wx0.http_auth_dialog_title).toString()).setView(inflate).setPositiveButton(wx0.http_auth_dialog_login, new 4(this)).setNegativeButton(wx0.http_auth_dialog_cancel, new 3(this)).setOnCancelListener(new 2(this)).create();
    }

    public void setCancelListener(CancelListener cancelListener) {
        this.mCancelListener = cancelListener;
    }

    public void setOkListener(OkListener okListener) {
        this.mOkListener = okListener;
    }

    public void show() {
        this.mDialog.show();
        this.mUsernameView.requestFocus();
    }
}
