package org.chromium.chrome.browser.newsguard;

import android.content.SharedPreferences;
import com.microsoft.mmx.experiment.FeatureManager$Feature;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.dom_distiller.core.DomDistillerUrlUtils;

/* compiled from: PG */
public class NewsGuardManager {

    /* renamed from: a */
    public ConcurrentHashMap<Tab, NewsGuardResponseModel> f2116a = new ConcurrentHashMap<>();

    /* renamed from: b */
    public Kb2 f2117b = new Kb2();

    /* renamed from: c */
    public SharedPreferences f2118c = QN0.a;

    /* renamed from: d */
    public boolean f2119d = false;

    /* renamed from: e */
    public boolean f2120e;

    /* renamed from: f */
    public List<OnEnableStateChangeListener> f2121f = new ArrayList();

    /* renamed from: g */
    public Rb2 f2122g;

    /* compiled from: PG */
    public interface OnGetDataCallback {
        void onGetNewsGuardData(NewsGuardResponseModel newsGuardResponseModel);
    }

    public /* synthetic */ NewsGuardManager(ub2 ub2) {
    }

    /* renamed from: a */
    public static boolean m2670a(Tab tab, String str) {
        return !m2669a() || tab == null || tab.mo9315X() || !Vb2.a(tab) || (ft0.d(str) && !DomDistillerUrlUtils.b(str));
    }

    /* renamed from: a */
    public static void m2668a(Tab tab, String str, OnGetDataCallback onGetDataCallback) {
        if (m2669a()) {
            if (m2670a(tab, str)) {
                vb2.a.f2117b.a(onGetDataCallback, (NewsGuardResponseModel) null);
            } else {
                vb2.a.f2117b.a(str, new ub2(tab, onGetDataCallback));
            }
        }
    }

    /* renamed from: a */
    public static boolean m2669a() {
        if (!gh0.b(FeatureManager$Feature.NEWSGUARD_ROLLOUT)) {
            return false;
        }
        if (vb2.a.f2118c.contains("newsguard_enabled")) {
            return vb2.a.f2118c.getBoolean("newsguard_enabled", false);
        }
        return vb2.a.f2119d;
    }

    /* renamed from: a */
    public static NewsGuardResponseModel m2667a(Tab tab) {
        if (tab == null) {
            return null;
        }
        return vb2.a.f2116a.get(tab);
    }
}
