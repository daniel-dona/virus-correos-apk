package org.chromium.chrome.browser.favorites;

import Z9;
import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import com.microsoft.ruby.sync.RubySyncClient;
import java.util.List;
import org.chromium.chrome.browser.favorites.BookmarkRecyclerView;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager;
import org.chromium.chrome.browser.widget.LoadingView;
import org.chromium.components.bookmarks.BookmarkId;

/* compiled from: PG */
public class BookmarkContentView extends RelativeLayout implements EW1, RubySyncClient.RubySyncClientObserver {

    /* renamed from: a */
    public BookmarkDelegate f1855a;

    /* renamed from: b */
    public BookmarkRecyclerView f1856b;

    /* renamed from: c */
    public ScrollView f1857c;

    /* renamed from: d */
    public LoadingView f1858d;

    /* renamed from: e */
    public BookmarkBottomBar f1859e;

    /* renamed from: k */
    public BookmarkRecyclerView.C0409c f1860k = new C0397a();

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkContentView$a */
    /* compiled from: PG */
    public class C0397a implements BookmarkRecyclerView.C0409c {
        public C0397a() {
        }
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkContentView$b */
    /* compiled from: PG */
    public class C0398b extends RecyclerViewAccessibilityDelegate {
        public C0398b(RecyclerView recyclerView) {
            super(recyclerView);
        }

        public void onInitializeAccessibilityNodeInfo(View view, Z9 z9) {
            BookmarkContentView.super.onInitializeAccessibilityNodeInfo(view, z9);
            if (BookmarkContentView.this.f1856b.mo8673p().x3 != null) {
                z9.a.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) Z9.b.a(BookmarkContentView.this.f1856b.mo8673p().x3.size(), 1, false, 0).a);
            }
        }
    }

    public BookmarkContentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: a */
    public void mo8578a() {
    }

    /* renamed from: a */
    public void mo8580a(BookmarkDelegate bookmarkDelegate) {
        this.f1855a = bookmarkDelegate;
        ((BookmarkManager) this.f1855a).f1927e.mo7868a(this);
        this.f1856b.mo8667a(this.f1855a);
        this.f1859e.mo8569a(this.f1855a);
    }

    /* renamed from: b */
    public boolean mo8584b() {
        BookmarkDelegate bookmarkDelegate = this.f1855a;
        if (bookmarkDelegate == null || !((BookmarkManager) bookmarkDelegate).f1934l.c) {
            return false;
        }
        ((BookmarkManager) bookmarkDelegate).f1934l.a(false);
        return true;
    }

    /* renamed from: c */
    public Parcelable mo8585c() {
        return this.f1856b.r().F();
    }

    /* renamed from: d */
    public void mo8586d() {
        this.f1859e.mo8571b();
        this.f1858d.b();
    }

    public void onDestroy() {
        ((BookmarkManager) this.f1855a).f1927e.mo7869b(this);
        RubySyncClient.i().b(this);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        RubySyncClient.i().a(this);
        this.f1856b = (BookmarkRecyclerView) findViewById(ox0.bookmark_items_container);
        this.f1857c = (ScrollView) findViewById(ox0.bookmark_empty_container);
        this.f1859e = (BookmarkBottomBar) findViewById(ox0.bookmark_bottom_bar);
        this.f1858d = findViewById(ox0.bookmark_initial_loading_view);
        this.f1856b.mo8668a(this.f1860k);
        RecyclerView recyclerView = this.f1856b;
        I9.a.a(recyclerView, new C0398b(recyclerView));
    }

    public void onSelectionStateChange(List<BookmarkId> list) {
    }

    public void onSyncStateChanged() {
        wW1 p = this.f1856b.mo8673p();
        mo8582a(p, p.b());
    }

    /* renamed from: a */
    public void mo8581a(BookmarkId bookmarkId) {
        this.f1858d.a();
        ((BookmarkManager) this.f1855a).f1925c.d(bookmarkId);
    }

    /* renamed from: b */
    public void mo8583b(BookmarkId bookmarkId) {
        View b = this.f1856b.mo8670b(bookmarkId);
        if (b != null) {
            b.sendAccessibilityEvent(8);
        }
    }

    /* renamed from: a */
    public void mo8579a(Parcelable parcelable) {
        if (parcelable != null) {
            this.f1856b.r().a(parcelable);
        }
    }

    /* JADX WARNING: type inference failed for: r4v4, types: [android.view.ViewGroup, org.chromium.chrome.browser.favorites.BookmarkRecyclerView] */
    /* renamed from: a */
    public final void mo8582a(wW1 ww1, boolean z) {
        if (this.f1856b != null && this.f1857c != null) {
            if (!z || (RubySyncClient.i().n != RubySyncClient.SyncStatus.NOT_START && MicrosoftSigninManager.C0424c.f2104a.mo8868C() && !QN0.a.getBoolean(RubySyncClient.v, false))) {
                this.f1857c.setVisibility(8);
                return;
            }
            this.f1856b.getLayoutParams().height = -1;
            this.f1856b.requestLayout();
            boolean z2 = true;
            int i = ww1.s3.size() == 1 ? 137 : 56;
            if (ww1.v3.size() != 1) {
                z2 = false;
            }
            if (z2) {
                i += 56;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f1857c.getLayoutParams();
            marginLayoutParams.topMargin = DV1.a(getContext(), (float) i);
            this.f1857c.setLayoutParams(marginLayoutParams);
            this.f1857c.setVisibility(0);
        }
    }
}
