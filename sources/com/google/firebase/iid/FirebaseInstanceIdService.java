package com.google.firebase.iid;

import android.content.Intent;
import android.util.Log;

/* compiled from: PG */
public class FirebaseInstanceIdService extends zzb {
    /* renamed from: a */
    public void mo2035a() {
    }

    /* renamed from: b */
    public final Intent mo2036b(Intent intent) {
        return (Intent) bJ.a().c.poll();
    }

    /* renamed from: d */
    public final void mo2037d(Intent intent) {
        if ("com.google.firebase.iid.TOKEN_REFRESH".equals(intent.getAction())) {
            mo2035a();
            return;
        }
        String stringExtra = intent.getStringExtra("CMD");
        if (stringExtra != null) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf = String.valueOf(intent.getExtras());
                StringBuilder sb = new StringBuilder(valueOf.length() + stringExtra.length() + 21);
                sb.append("Received command: ");
                sb.append(stringExtra);
                sb.append(" - ");
                sb.append(valueOf);
                sb.toString();
            }
            if ("RST".equals(stringExtra) || "RST_FULL".equals(stringExtra)) {
                FirebaseInstanceId.m808h().mo2030e();
            } else if ("SYNC".equals(stringExtra)) {
                FirebaseInstanceId.m808h().mo2031f();
            }
        }
    }
}
