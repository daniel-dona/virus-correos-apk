package com.facebook.react;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import com.facebook.react.bridge.Callback;

/* compiled from: PG */
public abstract class ReactActivity extends AppCompatActivity implements Gy, Hy {

    /* renamed from: a */
    public final fx f418a = mo585D();

    /* renamed from: D */
    public fx mo585D() {
        return new fx(this, mo586E());
    }

    /* renamed from: E */
    public String mo586E() {
        return null;
    }

    /* renamed from: a */
    public void mo587a(String[] strArr, int i, Iy iy) {
        fx fxVar = this.f418a;
        fxVar.e = iy;
        fxVar.b().requestPermissions(strArr, i);
    }

    /* renamed from: g */
    public void mo588g() {
        ReactActivity.super.onBackPressed();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        fx fxVar = this.f418a;
        if (fxVar.c().c()) {
            fxVar.c().a().mo606a(fxVar.b(), i, i2, intent);
        }
    }

    public void onBackPressed() {
        boolean z;
        fx fxVar = this.f418a;
        if (fxVar.c().c()) {
            fxVar.c().a().mo625h();
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            ReactActivity.super.onBackPressed();
        }
    }

    public void onCreate(Bundle bundle) {
        ReactActivity.super.onCreate(bundle);
        fx fxVar = this.f418a;
        String str = fxVar.b;
        if (str != null) {
            if (fxVar.c == null) {
                fxVar.c = new ReactRootView(fxVar.a());
                fxVar.c.mo629a(fxVar.c().a(), str, (Bundle) null);
                fxVar.b().setContentView(fxVar.c);
            } else {
                throw new IllegalStateException("Cannot loadApp while app is already running.");
            }
        }
        fxVar.d = new ny();
    }

    public void onDestroy() {
        ReactActivity.super.onDestroy();
        fx fxVar = this.f418a;
        ReactRootView reactRootView = fxVar.c;
        if (reactRootView != null) {
            reactRootView.mo649n();
            fxVar.c = null;
        }
        if (fxVar.c().c()) {
            fxVar.c().a().mo605a(fxVar.b());
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        fx fxVar = this.f418a;
        if (fxVar.c().c()) {
            fxVar.c().b();
        }
        return ReactActivity.super.onKeyDown(i, keyEvent);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.app.Activity, com.facebook.react.ReactActivity] */
    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        fx fxVar = this.f418a;
        if (fxVar.c().c()) {
            fxVar.c().b();
        }
        return ReactActivity.super.onKeyLongPress(i, keyEvent);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.app.Activity, com.facebook.react.ReactActivity] */
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        fx fxVar = this.f418a;
        if (fxVar.c().c()) {
            fxVar.c().b();
        }
        return ReactActivity.super.onKeyUp(i, keyEvent);
    }

    public void onNewIntent(Intent intent) {
        boolean z;
        fx fxVar = this.f418a;
        if (fxVar.c().c()) {
            fxVar.c().a().mo608a(intent);
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            ReactActivity.super.onNewIntent(intent);
        }
    }

    public void onPause() {
        ReactActivity.super.onPause();
        fx fxVar = this.f418a;
        if (fxVar.c().c()) {
            fxVar.c().a().mo616b(fxVar.b());
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.f418a.a(i, strArr, iArr);
    }

    public void onResume() {
        ReactActivity.super.onResume();
        fx fxVar = this.f418a;
        if (fxVar.c().c()) {
            fxVar.c().a().mo607a(fxVar.b(), fxVar.b());
        }
        Callback callback = fxVar.f;
        if (callback != null) {
            callback.invoke(new Object[0]);
            fxVar.f = null;
        }
    }
}
