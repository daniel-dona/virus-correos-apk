package org.chromium.chrome.browser.favorites;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import java.util.List;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.widget.selection.SelectionDelegate;
import org.chromium.components.bookmarks.BookmarkId;

/* compiled from: PG */
public class BookmarkBottomBar extends FrameLayout implements SelectionDelegate.SelectionObserver<BookmarkId>, View.OnClickListener, EW1 {

    /* renamed from: a */
    public AppCompatImageButton f1845a;

    /* renamed from: b */
    public AppCompatImageButton f1846b;

    /* renamed from: c */
    public AppCompatImageButton f1847c;

    /* renamed from: d */
    public AppCompatImageButton f1848d;

    /* renamed from: e */
    public BookmarkBridge.BookmarkItem f1849e;

    /* renamed from: k */
    public BookmarkDelegate f1850k;

    /* renamed from: n */
    public BookmarkBridge.b f1851n = new C0396a();

    /* renamed from: p */
    public SelectionDelegate<BookmarkId> f1852p;

    /* renamed from: q */
    public int f1853q;

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkBottomBar$a */
    /* compiled from: PG */
    public class C0396a extends BookmarkBridge.b {
        public C0396a() {
        }

        /* renamed from: a */
        public void mo8577a() {
            BookmarkBottomBar bookmarkBottomBar = BookmarkBottomBar.this;
            bookmarkBottomBar.onSelectionStateChange(((BookmarkManager) bookmarkBottomBar.f1850k).f1934l.b());
        }
    }

    static {
        Class<BookmarkBottomBar> cls = BookmarkBottomBar.class;
    }

    public BookmarkBottomBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: a */
    public void mo8568a() {
    }

    /* renamed from: a */
    public void mo8569a(BookmarkDelegate bookmarkDelegate) {
        this.f1850k = bookmarkDelegate;
        ((BookmarkManager) this.f1850k).f1927e.mo7868a(this);
        ((BookmarkManager) bookmarkDelegate).f1925c.a(this.f1851n);
        this.f1852p = ((BookmarkManager) this.f1850k).f1934l;
        this.f1852p.e.mo7868a(this);
    }

    /* renamed from: b */
    public void mo8571b() {
        this.f1846b.setVisibility(8);
        this.f1847c.setVisibility(8);
        this.f1848d.setVisibility(8);
    }

    public void onClick(View view) {
        if (view == this.f1845a) {
            int i = this.f1853q;
            if (i == 1) {
                getContext();
            } else if (i == 2) {
                ((BookmarkManager) this.f1850k).mo8653a(this.f1849e.b());
            } else if (i == 3) {
                this.f1852p.a(false);
            }
        } else if (view == this.f1846b) {
            BookmarkSwipeLayout bookmarkSwipeLayout = BookmarkSwipeLayout.f1975y3;
            if (bookmarkSwipeLayout != null) {
                bookmarkSwipeLayout.mo8717b();
            } else {
                this.f1852p.a(true);
            }
        } else if (view == this.f1847c) {
            ((BookmarkManager) this.f1850k).f1925c.a((BookmarkId[]) this.f1852p.b().toArray(new BookmarkId[0]));
            this.f1852p.a(false);
        } else if (view == this.f1848d) {
            List b = this.f1852p.b();
            if (b.size() >= 1) {
                BookmarkFolderSelectActivity.m2344a(getContext(), this.f1852p, false, (BookmarkId[]) b.toArray(new BookmarkId[b.size()]));
                this.f1852p.a(false);
            }
        }
    }

    public void onDestroy() {
        ((BookmarkManager) this.f1850k).f1927e.mo7869b(this);
        ((BookmarkManager) this.f1850k).f1925c.b(this.f1851n);
        SelectionDelegate<BookmarkId> selectionDelegate = this.f1852p;
        if (selectionDelegate != null) {
            selectionDelegate.e.mo7869b(this);
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.f1845a = findViewById(ox0.back);
        this.f1846b = findViewById(ox0.select);
        this.f1847c = findViewById(ox0.delete);
        this.f1848d = findViewById(ox0.move);
        this.f1845a.setOnClickListener(this);
        this.f1846b.setOnClickListener(this);
        this.f1847c.setOnClickListener(this);
        this.f1848d.setOnClickListener(this);
    }

    public void onSelectionStateChange(List<BookmarkId> list) {
        if (this.f1852p.c) {
            this.f1853q = 3;
            this.f1846b.setVisibility(8);
            if (list.size() > 0) {
                this.f1847c.setVisibility(0);
                return;
            }
            this.f1847c.setVisibility(8);
            this.f1848d.setVisibility(8);
            return;
        }
        setVisibility(8);
        this.f1846b.setVisibility(0);
        this.f1847c.setVisibility(8);
        this.f1848d.setVisibility(8);
        ((BookmarkManager) this.f1850k).mo8650a((EW1) this);
    }

    public void setSelectButtonEnabled(boolean z) {
        this.f1846b.setAlpha(z ? 0.5f : 1.0f);
        this.f1846b.setEnabled(!z);
    }

    /* renamed from: a */
    public void mo8570a(BookmarkId bookmarkId) {
        this.f1849e = ((BookmarkManager) this.f1850k).f1925c.d(bookmarkId);
        if (((BookmarkManager) this.f1850k).f1925c.g().contains(this.f1849e.b())) {
            this.f1853q = 1;
        } else {
            this.f1853q = 2;
        }
    }
}
