package org.chromium.chrome.browser.favorites;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.widget.DrawerLayout;
import android.view.ViewGroup;
import android.widget.ViewSwitcher;
import com.citrix.loggersdk.BuildConfig;
import java.util.Iterator;
import java.util.Objects;
import java.util.Stack;
import org.chromium.base.ObserverList;
import org.chromium.base.ThreadUtils;
import org.chromium.base.metrics.RecordHistogram;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.favicon.LargeIconBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.snackbar.SnackbarManager;
import org.chromium.chrome.browser.snackbar.SnackbarManager$SnackbarManageable;
import org.chromium.chrome.browser.widget.selection.SelectionDelegate;
import org.chromium.components.bookmarks.BookmarkId;

/* compiled from: PG */
public class BookmarkManager implements BookmarkDelegate {

    /* renamed from: a */
    public Activity f1923a;

    /* renamed from: b */
    public ViewGroup f1924b;

    /* renamed from: c */
    public BookmarkModel f1925c;

    /* renamed from: d */
    public HW1 f1926d;

    /* renamed from: e */
    public final ObserverList<EW1> f1927e = new ObserverList<>();

    /* renamed from: f */
    public final ObserverList<BookmarkOpenObserver> f1928f = new ObserverList<>();

    /* renamed from: g */
    public BookmarkContentView f1929g;

    /* renamed from: h */
    public BookmarkSearchView f1930h;

    /* renamed from: i */
    public ViewSwitcher f1931i;

    /* renamed from: j */
    public DrawerLayout f1932j;

    /* renamed from: k */
    public BookmarkDrawerListView f1933k;

    /* renamed from: l */
    public SelectionDelegate<BookmarkId> f1934l;

    /* renamed from: m */
    public final Stack<FW1> f1935m = new Stack<>();

    /* renamed from: n */
    public LargeIconBridge f1936n;

    /* renamed from: o */
    public String f1937o;

    /* renamed from: p */
    public final BookmarkBridge.b f1938p = new yW1(this);

    /* renamed from: q */
    public final Runnable f1939q = new zW1(this);

    /* compiled from: PG */
    public interface BookmarkOpenObserver {
        void onBookmarkOpen(BookmarkId bookmarkId);

        void onFolderOpen(BookmarkId bookmarkId);

        void onSearchUIClose();

        void onSearchUIOpen();
    }

    /* compiled from: PG */
    public enum StateNavigationType {
        INIT,
        ENTER_SUB,
        ENTER_PARENT
    }

    public BookmarkManager(Activity activity, boolean z, ViewGroup viewGroup) {
        this.f1923a = activity;
        this.f1934l = new AW1(this);
        this.f1925c = new BookmarkModel();
        this.f1924b = (ViewGroup) this.f1923a.getLayoutInflater().inflate(rx0.favorite_main, viewGroup, false);
        this.f1932j = this.f1924b.findViewById(ox0.bookmark_drawer_layout);
        this.f1932j.setDrawerLockMode(1);
        this.f1933k = (BookmarkDrawerListView) this.f1924b.findViewById(ox0.bookmark_drawer_list);
        this.f1929g = (BookmarkContentView) this.f1924b.findViewById(ox0.bookmark_content_view);
        this.f1931i = (ViewSwitcher) this.f1924b.findViewById(ox0.bookmark_view_switcher);
        this.f1926d = new HW1(activity, this.f1925c, ((SnackbarManager$SnackbarManageable) activity).getSnackbarManager());
        this.f1930h = (BookmarkSearchView) this.f1924b.findViewById(ox0.bookmark_search_view);
        this.f1925c.a(this.f1938p);
        this.f1929g.mo8586d();
        this.f1933k.mo8597b();
        this.f1929g.mo8586d();
        FW1 fw1 = new FW1();
        fw1.a = 1;
        fw1.b = BuildConfig.FLAVOR;
        mo8651a(fw1);
        this.f1925c.a(this.f1939q);
        zi2.a(activity);
        this.f1936n = new LargeIconBridge(Profile.m2911j().mo9203c());
        this.f1936n.a(Math.min((((ActivityManager) RN0.a.getSystemService("activity")).getMemoryClass() / 4) * 1024 * 1024, 10485760));
    }

    /* renamed from: a */
    public void mo8652a(String str) {
        BookmarkModel bookmarkModel = this.f1925c;
        if (bookmarkModel != null) {
            if (bookmarkModel.c) {
                mo8651a(FW1.a(Uri.parse(str), bookmarkModel));
            } else {
                this.f1937o = str;
            }
        }
    }

    /* renamed from: b */
    public void mo8655b() {
        Iterator<EW1> it = this.f1927e.iterator();
        while (it.hasNext()) {
            it.next().onDestroy();
        }
        HW1 hw1 = this.f1926d;
        if (hw1 != null) {
            hw1.a.h.mo7869b(hw1);
            hw1.b.a(hw1);
            SnackbarManager snackbarManager = hw1.b;
            zt2 zt2 = snackbarManager.b;
            if (zt2 != null && zt2.g()) {
                snackbarManager.b.c();
            }
            this.f1926d = null;
        }
        this.f1925c.b(this.f1938p);
        this.f1925c.a();
        this.f1925c = null;
        this.f1936n.a();
        this.f1936n = null;
    }

    /* renamed from: c */
    public boolean mo8656c() {
        return this.f1932j != null;
    }

    /* renamed from: d */
    public int mo8657d() {
        if (this.f1935m.isEmpty()) {
            return 1;
        }
        return this.f1935m.peek().a;
    }

    /* renamed from: e */
    public boolean mo8658e() {
        if ((this.f1932j != null) && this.f1932j.e(8388611)) {
            this.f1932j.a(8388611);
            return true;
        } else if (this.f1929g.mo8584b()) {
            return true;
        } else {
            if (!this.f1935m.empty()) {
                this.f1935m.pop();
                if (!this.f1935m.empty()) {
                    mo8651a(this.f1935m.pop());
                    return true;
                }
            }
            return false;
        }
    }

    /* renamed from: f */
    public void mo8659f() {
        Iterator<BookmarkOpenObserver> it = this.f1928f.iterator();
        while (it.hasNext()) {
            it.next().onSearchUIOpen();
        }
        this.f1931i.showNext();
    }

    /* renamed from: a */
    public final void mo8651a(FW1 fw1) {
        boolean z;
        if (!fw1.a(this.f1925c)) {
            fw1 = FW1.a(this.f1925c.q(), this.f1925c);
        }
        if (this.f1935m.isEmpty() || !this.f1935m.peek().equals(fw1)) {
            if (!this.f1935m.isEmpty() && this.f1935m.peek().a == 1) {
                this.f1935m.pop();
            }
            if (!this.f1935m.isEmpty()) {
                BookmarkBridge.BookmarkItem d = this.f1925c.d(this.f1935m.peek().c);
                if (d == null) {
                    z = false;
                } else {
                    z = Objects.equals(d.b(), fw1.c);
                }
                if (z) {
                    StateNavigationType stateNavigationType = StateNavigationType.ENTER_PARENT;
                    FW1 pop = this.f1935m.pop();
                    if (!this.f1935m.isEmpty()) {
                        fw1.e = this.f1935m.pop().e;
                    }
                    fw1.d = pop.c;
                } else {
                    StateNavigationType stateNavigationType2 = StateNavigationType.ENTER_SUB;
                    if (this.f1935m.peek().a == 2) {
                        this.f1935m.peek().d = fw1.c;
                        this.f1935m.peek().e = this.f1929g.mo8585c();
                    }
                    if (this.f1925c.g(fw1.c) > 0) {
                        fw1.d = this.f1925c.a(fw1.c, 0);
                    }
                }
            } else if (fw1.d == null) {
                StateNavigationType stateNavigationType3 = StateNavigationType.INIT;
            } else {
                StateNavigationType stateNavigationType4 = StateNavigationType.ENTER_PARENT;
            }
            this.f1935m.push(fw1);
            if (fw1.a != 1) {
                LW1.b().edit().putString("enhanced_bookmark_last_used_url", fw1.b).apply();
            }
            this.f1934l.a();
            Iterator<EW1> it = this.f1927e.iterator();
            while (it.hasNext()) {
                mo8650a(it.next());
            }
            Parcelable parcelable = fw1.e;
            if (parcelable != null) {
                this.f1929g.mo8579a(parcelable);
            }
            BookmarkId bookmarkId = fw1.d;
            if (bookmarkId != null && EE2.a()) {
                ThreadUtils.m1457a((Runnable) new xW1(this, bookmarkId));
            }
        }
    }

    /* renamed from: a */
    public void mo8653a(BookmarkId bookmarkId) {
        mo8649a();
        Iterator<BookmarkOpenObserver> it = this.f1928f.iterator();
        while (it.hasNext()) {
            it.next().onFolderOpen(bookmarkId);
        }
        mo8651a(FW1.a(bookmarkId, this.f1925c));
        if (bookmarkId.getId() != 0) {
            LW1.b().edit().putString("enhanced_bookmark_last_opened_parent_folder", bookmarkId.toString()).apply();
        }
    }

    /* renamed from: a */
    public void mo8650a(EW1 ew1) {
        int d = mo8657d();
        if (d != 1 && d == 2) {
            ew1.a(this.f1935m.peek().c);
        }
    }

    /* renamed from: a */
    public void mo8654a(BookmarkId bookmarkId, int i) {
        this.f1934l.a();
        Iterator<BookmarkOpenObserver> it = this.f1928f.iterator();
        while (it.hasNext()) {
            it.next().onBookmarkOpen(bookmarkId);
        }
        BookmarkModel bookmarkModel = this.f1925c;
        Activity activity = this.f1923a;
        if (bookmarkModel.d(bookmarkId) != null) {
            String e = bookmarkModel.d(bookmarkId).e();
            RecordHistogram.m1539a("Stars.LaunchLocation", i, 6);
            if (!new YY1(activity, e).b()) {
                LW1.a(activity, e, (ComponentName) PE2.c(activity.getIntent(), "org.chromium.chrome.browser.parent_component"));
            }
        }
    }

    /* renamed from: a */
    public void mo8649a() {
        if (this.f1930h.getVisibility() == 0) {
            Iterator<BookmarkOpenObserver> it = this.f1928f.iterator();
            while (it.hasNext()) {
                it.next().onSearchUIClose();
            }
            this.f1931i.showPrevious();
        }
    }
}
