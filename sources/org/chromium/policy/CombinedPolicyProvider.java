package org.chromium.policy;

import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* compiled from: PG */
public class CombinedPolicyProvider {

    /* renamed from: f */
    public static CombinedPolicyProvider f2582f;

    /* renamed from: a */
    public long f2583a;

    /* renamed from: b */
    public PolicyConverter f2584b;

    /* renamed from: c */
    public final List<UD3> f2585c = new ArrayList();

    /* renamed from: d */
    public final List<Bundle> f2586d = new ArrayList();

    /* renamed from: e */
    public final List<PolicyChangeListener> f2587e = new ArrayList();

    /* compiled from: PG */
    public interface PolicyChangeListener {
        void terminateIncognitoSession();
    }

    /* renamed from: a */
    public static CombinedPolicyProvider m3619a() {
        if (f2582f == null) {
            f2582f = new CombinedPolicyProvider();
        }
        return f2582f;
    }

    @CalledByNative
    public static CombinedPolicyProvider linkNative(long j, PolicyConverter policyConverter) {
        ThreadUtils.m1462c();
        CombinedPolicyProvider a = m3619a();
        a.f2583a = j;
        a.f2584b = policyConverter;
        if (j != 0) {
            for (UD3 a2 : a.f2585c) {
                a2.a();
            }
        }
        return m3619a();
    }

    /* renamed from: b */
    public void mo9939b(PolicyChangeListener policyChangeListener) {
        this.f2587e.remove(policyChangeListener);
    }

    public native void nativeFlushPolicies(long j);

    @CalledByNative
    public void refreshPolicies() {
        for (int i = 0; i < this.f2586d.size(); i++) {
            this.f2586d.set(i, (Object) null);
        }
        for (UD3 a : this.f2585c) {
            a.a();
        }
    }

    /* renamed from: a */
    public void mo9937a(UD3 ud3) {
        this.f2585c.add(ud3);
        this.f2586d.add((Object) null);
        ud3.b = this.f2585c.size() - 1;
        ud3.a = this;
        TD3 td3 = (SD3) ud3;
        TD3 td32 = td3;
        String str = Build.VERSION.SDK_INT < 21 ? null : "android.intent.action.APPLICATION_RESTRICTIONS_CHANGED";
        if (str != null) {
            td3.c.registerReceiver(td3.d, new IntentFilter(str), (String) null, new Handler(ThreadUtils.m1466f()));
        }
        if (this.f2583a != 0) {
            ud3.a();
        }
    }

    /* renamed from: a */
    public void mo9936a(int i, Bundle bundle) {
        this.f2586d.set(i, bundle);
        for (Bundle bundle2 : this.f2586d) {
            if (bundle2 == null) {
                return;
            }
        }
        if (this.f2583a != 0) {
            for (Bundle next : this.f2586d) {
                for (String str : next.keySet()) {
                    this.f2584b.a(str, next.get(str));
                }
            }
            nativeFlushPolicies(this.f2583a);
        }
    }

    /* renamed from: a */
    public void mo9938a(PolicyChangeListener policyChangeListener) {
        this.f2587e.add(policyChangeListener);
    }
}
