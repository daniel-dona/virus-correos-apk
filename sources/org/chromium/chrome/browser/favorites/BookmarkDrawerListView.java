package org.chromium.chrome.browser.favorites;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.List;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.components.bookmarks.BookmarkId;

@SuppressLint({"Instantiatable"})
/* compiled from: PG */
public class BookmarkDrawerListView extends ListView implements EW1 {

    /* renamed from: a */
    public BookmarkDelegate f1863a;

    /* renamed from: b */
    public BookmarkBridge.b f1864b = new C0399a();

    /* renamed from: c */
    public final gW1 f1865c = new gW1();

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkDrawerListView$a */
    /* compiled from: PG */
    public class C0399a extends BookmarkBridge.b {
        public C0399a() {
        }

        /* renamed from: a */
        public void mo8600a() {
            BookmarkDrawerListView bookmarkDrawerListView = BookmarkDrawerListView.this;
            ((BookmarkManager) bookmarkDrawerListView.f1863a).mo8650a((EW1) bookmarkDrawerListView);
        }
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkDrawerListView$b */
    /* compiled from: PG */
    public class C0400b implements AdapterView.OnItemClickListener {
        static {
            Class<BookmarkDrawerListView> cls = BookmarkDrawerListView.class;
        }

        public C0400b() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            BookmarkManager bookmarkManager = (BookmarkManager) BookmarkDrawerListView.this.f1863a;
            if (bookmarkManager.mo8656c()) {
                bookmarkManager.f1932j.a(8388611);
            }
            fW1 fw1 = (fW1) BookmarkDrawerListView.this.f1865c.getItem(i);
            if (fw1.a == 0) {
                ((BookmarkManager) BookmarkDrawerListView.this.f1863a).mo8653a(fw1.b);
            }
        }
    }

    public BookmarkDrawerListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setAdapter(this.f1865c);
        setOnItemClickListener(new C0400b());
    }

    /* renamed from: a */
    public void mo8594a() {
    }

    /* renamed from: a */
    public void mo8595a(BookmarkDelegate bookmarkDelegate) {
        this.f1863a = bookmarkDelegate;
        ((BookmarkManager) bookmarkDelegate).f1925c.a(this.f1864b);
        this.f1865c.a = bookmarkDelegate;
        ((BookmarkManager) bookmarkDelegate).f1927e.mo7868a(this);
    }

    /* renamed from: b */
    public void mo8597b() {
        gW1 gw1 = this.f1865c;
        gw1.b.clear();
        gw1.c.clear();
        this.f1865c.notifyDataSetChanged();
        clearChoices();
    }

    public void onDestroy() {
        ((BookmarkManager) this.f1863a).f1925c.b(this.f1864b);
        ((BookmarkManager) this.f1863a).f1927e.mo7869b(this);
    }

    public void onSelectionStateChange(List<BookmarkId> list) {
    }

    /* renamed from: a */
    public void mo8596a(BookmarkId bookmarkId) {
        gW1 gw1 = this.f1865c;
        gw1.e = ((BookmarkManager) gw1.a).f1925c.c();
        gw1.k = ((BookmarkManager) gw1.a).f1925c.d();
        gw1.n = ((BookmarkManager) gw1.a).f1925c.e();
        gw1.p = ((BookmarkManager) gw1.a).f1925c.a(true, false);
        gw1.b.clear();
        if (((BookmarkManager) gw1.a).f1925c.i(gw1.k)) {
            gw1.b.add(new fW1(gw1.k));
        }
        if (((BookmarkManager) gw1.a).f1925c.i(gw1.e)) {
            gw1.b.add(new fW1(gw1.e));
        }
        if (((BookmarkManager) gw1.a).f1925c.i(gw1.n)) {
            gw1.b.add(new fW1(gw1.n));
        }
        List<BookmarkId> list = gw1.p;
        if (list != null) {
            for (BookmarkId fw1 : list) {
                gw1.b.add(new fW1(fw1));
            }
        }
        List<BookmarkId> a = ((BookmarkManager) gw1.a).f1925c.a(false, true);
        gw1.c.clear();
        if (a.size() > 0) {
            gw1.c.add(new fW1(-1));
            gw1.c.add(new fW1(-2));
        }
        for (BookmarkId fw12 : a) {
            gw1.c.add(new fW1(fw12));
        }
        gw1.notifyDataSetChanged();
        setItemChecked(this.f1865c.a(2, bookmarkId), true);
    }
}
