package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Base64;
import com.google.android.gms.common.util.PlatformVersion;

/* compiled from: PG */
public final class FirebaseInstanceIdReceiver extends WakefulBroadcastReceiver {

    /* renamed from: a */
    public static mJ f917a;

    /* renamed from: b */
    public static mJ f918b;

    /* renamed from: a */
    public static synchronized mJ m827a(Context context, String str) {
        synchronized (FirebaseInstanceIdReceiver.class) {
            if ("com.google.firebase.MESSAGING_EVENT".equals(str)) {
                if (f918b == null) {
                    f918b = new mJ(context, str);
                }
                mJ mJVar = f918b;
                return mJVar;
            }
            if (f917a == null) {
                f917a = new mJ(context, str);
            }
            mJ mJVar2 = f917a;
            return mJVar2;
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.google.firebase.iid.FirebaseInstanceIdReceiver, android.content.BroadcastReceiver] */
    /* renamed from: a */
    public final void mo2033a(Context context, Intent intent, String str) {
        String str2 = null;
        intent.setComponent((ComponentName) null);
        intent.setPackage(context.getPackageName());
        String stringExtra = intent.getStringExtra("gcm.rawData64");
        boolean z = false;
        if (stringExtra != null) {
            intent.putExtra("rawData", Base64.decode(stringExtra, 0));
            intent.removeExtra("gcm.rawData64");
        }
        if ("google.com/iid".equals(intent.getStringExtra("from")) || "com.google.firebase.INSTANCE_ID_EVENT".equals(str)) {
            str2 = "com.google.firebase.INSTANCE_ID_EVENT";
        } else if ("com.google.android.c2dm.intent.RECEIVE".equals(str) || "com.google.firebase.MESSAGING_EVENT".equals(str)) {
            str2 = "com.google.firebase.MESSAGING_EVENT";
        }
        int i = -1;
        if (str2 != null) {
            if (PlatformVersion.isAtLeastO() && context.getApplicationInfo().targetSdkVersion >= 26) {
                z = true;
            }
            if (z) {
                if (isOrderedBroadcast()) {
                    setResultCode(-1);
                }
                m827a(context, str2).a(intent, goAsync());
            } else {
                i = bJ.a().a(context, str2, intent);
            }
        }
        if (isOrderedBroadcast()) {
            setResultCode(i);
        }
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Parcelable parcelableExtra = intent.getParcelableExtra("wrapped_intent");
            if (parcelableExtra instanceof Intent) {
                mo2033a(context, (Intent) parcelableExtra, intent.getAction());
            } else {
                mo2033a(context, intent, intent.getAction());
            }
        }
    }
}
