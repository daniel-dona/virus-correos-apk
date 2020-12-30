package com.citrix.auth.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.citrix.MAM.Android.ManagedAppHelper.Interface.MAMAppInfo;
import com.citrix.auth.C0008a;
import com.citrix.auth.exception.AuthenticationException;
import com.citrix.auth.model.AuthResult;
import com.citrix.auth.model.Token;

/* compiled from: PG */
public class SecureHubAuthHelperActivity extends Activity {

    /* renamed from: a */
    public Messenger f128a;

    /* renamed from: a */
    private void m47a(Message message) {
        try {
            if (this.f128a != null) {
                this.f128a.send(message);
            }
        } catch (RemoteException e) {
            StringBuilder a = Eo.a("Unable to send message back to handler ");
            a.append(e.getMessage());
            Log.e("AUTH-SHAuthHelper", a.toString());
        }
    }

    public Context createConfigurationContext(Configuration configuration) {
        boolean d = gs1.d();
        Context createConfigurationContext = super.createConfigurationContext(configuration);
        return !d ? createConfigurationContext : gs1.f(createConfigurationContext);
    }

    public AssetManager getAssets() {
        return !gs1.d() ? super.getAssets() : gs1.g(this);
    }

    public Resources getResources() {
        return !gs1.d() ? super.getResources() : gs1.h(this);
    }

    public Resources.Theme getTheme() {
        return !gs1.d() ? super.getTheme() : gs1.i(this);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        AuthResult authResult;
        if (i == 0) {
            if (i2 == -1) {
                authResult = new AuthResult(0, C0008a.m45a(intent), (AuthenticationException) null);
            } else {
                authResult = new AuthResult(1, (Token) null, new AuthenticationException("Auth Failed..."));
            }
            m47a(m46a(authResult));
        }
        finish();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f128a = (Messenger) getIntent().getParcelableExtra("MVPNMessenger");
        Intent intent = new Intent();
        intent.setClassName("com.zenprise", "com.citrix.work.MAM.AuthenticateToStore");
        intent.setFlags(536870912);
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean(MAMAppInfo.INTENT_EXTRA_FORCE_LOGON, true);
        bundle2.putBoolean(MAMAppInfo.INTENT_EXTRA_REFRESH, true);
        bundle2.putBoolean(MAMAppInfo.INTENT_EXTRA_GET_POLICIES, true);
        bundle2.putBoolean("GetDataSAMLToken", false);
        bundle2.putBoolean(MAMAppInfo.INTENT_EXTRA_GET_STA_TICKET, false);
        bundle2.putBoolean(MAMAppInfo.INTENT_EXTRA_DO_DEVICE_CHECK, false);
        bundle2.putInt(MAMAppInfo.INTENT_EXTRA_REQUEST_CODE, 11);
        bundle2.putBoolean(MAMAppInfo.INTENT_EXTRA_BLOCK_DNS_FROM_UNMANAGED_APPS_IN_FULL_VPN, true);
        bundle2.putBoolean(MAMAppInfo.INTENT_EXTRA_ENCRYPTION_KEYS, false);
        bundle2.putLong(MAMAppInfo.INTENT_EXTRA_WRAPPER_VERSION, 1);
        bundle2.putInt(MAMAppInfo.INTENT_EXTRA_LOGON_ATTEMPTS, 1);
        bundle2.putString(MAMAppInfo.INTENT_EXTRA_LOGON_REASON, "Logon Reason: { online = NoReason }");
        intent.putExtras(bundle2);
        startActivityForResult(intent, 0);
    }

    public void setTheme(int i) {
        if (!gs1.d()) {
            super.setTheme(i);
        } else {
            gs1.b(this, i);
        }
    }

    /* renamed from: a */
    private Message m46a(AuthResult authResult) {
        Message obtain = Message.obtain((Handler) null, authResult.getSuccess());
        Bundle bundle = new Bundle();
        bundle.putParcelable(MAMAppInfo.KEY_AUTH_RESULT, authResult);
        obtain.setData(bundle);
        return obtain;
    }
}
