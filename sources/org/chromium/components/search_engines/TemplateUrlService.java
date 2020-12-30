package org.chromium.components.search_engines;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ObserverList;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;

/* compiled from: PG */
public class TemplateUrlService {

    /* renamed from: a */
    public final ObserverList<LoadListener> f2364a = new ObserverList<>();

    /* renamed from: b */
    public final ObserverList<TemplateUrlServiceObserver> f2365b = new ObserverList<>();

    /* renamed from: c */
    public long f2366c;

    /* compiled from: PG */
    public interface LoadListener {
        void onTemplateUrlServiceLoaded();
    }

    /* compiled from: PG */
    public interface TemplateUrlServiceObserver {
        void onTemplateURLServiceChanged();
    }

    /* renamed from: org.chromium.components.search_engines.TemplateUrlService$a */
    /* compiled from: PG */
    public class C0432a implements LoadListener {

        /* renamed from: a */
        public final /* synthetic */ Runnable f2367a;

        public C0432a(Runnable runnable) {
            this.f2367a = runnable;
        }

        public void onTemplateUrlServiceLoaded() {
            TemplateUrlService.this.mo9626c((LoadListener) this);
            this.f2367a.run();
        }
    }

    public TemplateUrlService(long j) {
        this.f2366c = j;
    }

    @CalledByNative
    public static void addTemplateUrlToList(List<TemplateUrl> list, TemplateUrl templateUrl) {
        list.add(templateUrl);
    }

    @CalledByNative
    private void clearNativePtr() {
        this.f2366c = 0;
    }

    @CalledByNative
    public static TemplateUrlService create(long j) {
        return new TemplateUrlService(j);
    }

    private native String nativeAddSearchEngineForTesting(long j, String str, int i);

    private native TemplateUrl nativeGetDefaultSearchEngine(long j);

    private native int nativeGetSearchEngineTypeFromTemplateUrl(long j, String str);

    private native String nativeGetSearchEngineUrlFromTemplateUrl(long j, String str);

    private native void nativeGetTemplateUrls(long j, List<TemplateUrl> list);

    private native String nativeGetUrlForContextualSearchQuery(long j, String str, String str2, boolean z, String str3);

    private native String nativeGetUrlForSearchQuery(long j, String str);

    private native String nativeGetUrlForVoiceSearchQuery(long j, String str);

    private native boolean nativeIsDefaultSearchEngineGoogle(long j);

    private native boolean nativeIsDefaultSearchManaged(long j);

    private native boolean nativeIsLoaded(long j);

    private native boolean nativeIsSearchByImageAvailable(long j);

    private native boolean nativeIsSearchResultsPageFromDefaultSearchProvider(long j, String str);

    private native void nativeLoad(long j);

    private native void nativeSetUserSelectedDefaultSearchProvider(long j, String str);

    private native String nativeUpdateLastVisitedForTesting(long j, String str);

    @CalledByNative
    private void onTemplateURLServiceChanged() {
        Iterator<TemplateUrlServiceObserver> it = this.f2365b.iterator();
        while (it.hasNext()) {
            it.next().onTemplateURLServiceChanged();
        }
    }

    @CalledByNative
    private void templateUrlServiceLoaded() {
        ThreadUtils.m1462c();
        Iterator<LoadListener> it = this.f2364a.iterator();
        while (it.hasNext()) {
            it.next().onTemplateUrlServiceLoaded();
        }
    }

    /* renamed from: a */
    public void mo9618a(Runnable runnable) {
        if (mo9630e()) {
            runnable.run();
            return;
        }
        mo9623b((LoadListener) new C0432a(runnable));
        mo9632g();
    }

    /* renamed from: b */
    public List<TemplateUrl> mo9622b() {
        ThreadUtils.m1462c();
        ArrayList arrayList = new ArrayList();
        nativeGetTemplateUrls(this.f2366c, arrayList);
        return arrayList;
    }

    /* renamed from: c */
    public boolean mo9627c() {
        return nativeIsDefaultSearchEngineGoogle(this.f2366c);
    }

    /* renamed from: d */
    public void mo9628d(String str) {
        ThreadUtils.m1462c();
        Eo.a(QN0.a, "app_search_engine_preference", str);
        nativeSetUserSelectedDefaultSearchProvider(this.f2366c, str);
    }

    /* renamed from: e */
    public boolean mo9630e() {
        ThreadUtils.m1462c();
        return nativeIsLoaded(this.f2366c);
    }

    /* renamed from: f */
    public boolean mo9631f() {
        ThreadUtils.m1462c();
        return nativeIsSearchByImageAvailable(this.f2366c);
    }

    /* renamed from: g */
    public void mo9632g() {
        ThreadUtils.m1462c();
        nativeLoad(this.f2366c);
    }

    /* renamed from: c */
    public void mo9626c(LoadListener loadListener) {
        ThreadUtils.m1462c();
        this.f2364a.mo7869b(loadListener);
    }

    /* renamed from: b */
    public void mo9623b(LoadListener loadListener) {
        ThreadUtils.m1462c();
        this.f2364a.mo7868a(loadListener);
        if (mo9630e()) {
            PostTask.m1566a(iQ2.a, new sL2(this, loadListener), 0);
        }
    }

    /* renamed from: c */
    public String mo9625c(String str) {
        return nativeGetUrlForVoiceSearchQuery(this.f2366c, str);
    }

    /* renamed from: a */
    public TemplateUrl mo9617a() {
        if (!mo9630e()) {
            return null;
        }
        return nativeGetDefaultSearchEngine(this.f2366c);
    }

    /* renamed from: d */
    public boolean mo9629d() {
        return nativeIsDefaultSearchManaged(this.f2366c);
    }

    /* renamed from: a */
    public final /* synthetic */ void mo9619a(LoadListener loadListener) {
        if (this.f2364a.f1453a.contains(loadListener)) {
            loadListener.onTemplateUrlServiceLoaded();
        }
    }

    /* renamed from: b */
    public void mo9624b(TemplateUrlServiceObserver templateUrlServiceObserver) {
        this.f2365b.mo7869b(templateUrlServiceObserver);
    }

    /* renamed from: a */
    public void mo9620a(TemplateUrlServiceObserver templateUrlServiceObserver) {
        this.f2365b.mo7868a(templateUrlServiceObserver);
    }

    /* renamed from: b */
    public String mo9621b(String str) {
        return nativeGetUrlForSearchQuery(this.f2366c, str);
    }

    /* renamed from: a */
    public String mo9616a(String str, String str2, boolean z, String str3) {
        return nativeGetUrlForContextualSearchQuery(this.f2366c, str, str2, z, str3);
    }

    /* renamed from: a */
    public String mo9615a(String str) {
        return nativeGetSearchEngineUrlFromTemplateUrl(this.f2366c, str);
    }
}
