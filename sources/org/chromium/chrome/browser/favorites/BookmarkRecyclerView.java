package org.chromium.chrome.browser.favorites;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import java.util.List;
import org.chromium.chrome.browser.favorites.BookmarkContentView;
import org.chromium.components.bookmarks.BookmarkId;

/* compiled from: PG */
public class BookmarkRecyclerView extends RecyclerView implements EW1, MW1 {

    /* renamed from: H4 */
    public BookmarkDelegate f1940H4;

    /* renamed from: I4 */
    public C0409c f1941I4;

    /* renamed from: J4 */
    public ItemTouchHelper f1942J4;

    /* renamed from: K4 */
    public LinearLayoutManager f1943K4;

    /* renamed from: L4 */
    public wW1 f1944L4;

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkRecyclerView$a */
    /* compiled from: PG */
    public class C0407a extends RecyclerView.h {

        /* renamed from: a */
        public final /* synthetic */ RecyclerView.f f1945a;

        public C0407a(RecyclerView.f fVar) {
            this.f1945a = fVar;
        }

        /* renamed from: a */
        public void mo8675a() {
            BookmarkRecyclerView.this.mo8664U();
        }

        /* renamed from: b */
        public void mo8677b(int i, int i2) {
            BookmarkRecyclerView.this.mo8664U();
        }

        /* renamed from: a */
        public void mo8676a(int i, int i2) {
            BookmarkRecyclerView.super.a(i, i2);
            BookmarkRecyclerView.this.mo8664U();
        }
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkRecyclerView$b */
    /* compiled from: PG */
    public class C0408b implements View.OnTouchListener {
        public C0408b(BookmarkRecyclerView bookmarkRecyclerView) {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 1 && motionEvent.getAction() != 3) {
                return false;
            }
            view.performClick();
            BookmarkSwipeLayout bookmarkSwipeLayout = BookmarkSwipeLayout.f1975y3;
            if (bookmarkSwipeLayout == null) {
                return false;
            }
            bookmarkSwipeLayout.mo8717b();
            return false;
        }
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkRecyclerView$c */
    /* compiled from: PG */
    public interface C0409c {
    }

    public BookmarkRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1943K4 = new LinearLayoutManager(context);
        this.f1943K4.l(1);
        setLayoutManager(this.f1943K4);
        setHasFixedSize(true);
    }

    /* renamed from: U */
    public final void mo8664U() {
        C0409c cVar = this.f1941I4;
        if (cVar != null) {
            BookmarkContentView.C0397a aVar = (BookmarkContentView.C0397a) cVar;
            wW1 p = BookmarkContentView.this.f1856b.mo8673p();
            boolean b = p.b();
            BookmarkContentView.this.mo8582a(p, b);
            BookmarkContentView.this.f1859e.setSelectButtonEnabled(b);
        }
    }

    /* renamed from: a */
    public void mo8665a() {
    }

    /* renamed from: b */
    public View mo8670b(BookmarkId bookmarkId) {
        return this.f1943K4.c(this.f1944L4.b(bookmarkId));
    }

    public void onDestroy() {
        ((BookmarkManager) this.f1940H4).f1927e.mo7869b(this);
    }

    public void onSelectionStateChange(List<BookmarkId> list) {
        if (!((BookmarkManager) this.f1940H4).f1934l.c()) {
            for (int i = 0; i < r().e(); i++) {
                View d = r().d(i);
                if (d instanceof Checkable) {
                    ((Checkable) d).setChecked(false);
                }
            }
        }
    }

    public void setAdapter(RecyclerView.f fVar) {
        BookmarkRecyclerView.super.setAdapter(fVar);
        fVar.registerAdapterDataObserver(new C0407a(fVar));
        mo8664U();
    }

    /* renamed from: a */
    public void mo8668a(C0409c cVar) {
        this.f1941I4 = cVar;
    }

    /* renamed from: p */
    public wW1 m2375p() {
        return this.q3;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.support.v7.widget.RecyclerView, MW1, java.lang.Object, android.view.ViewGroup, org.chromium.chrome.browser.favorites.BookmarkRecyclerView] */
    /* renamed from: a */
    public void mo8667a(BookmarkDelegate bookmarkDelegate) {
        this.f1940H4 = bookmarkDelegate;
        ((BookmarkManager) this.f1940H4).f1927e.mo7868a(this);
        this.f1944L4 = new wW1(getContext(), this);
        wW1 ww1 = this.f1944L4;
        ww1.b = this.f1940H4;
        ((BookmarkManager) ww1.b).f1927e.mo7868a(ww1);
        ((BookmarkManager) ww1.b).f1925c.a(ww1.y3);
        ww1.d = new CW1(ww1.c, ww1);
        setAdapter(this.f1944L4);
        setOnTouchListener(new C0408b(this));
        this.f1942J4 = new ItemTouchHelper(new NW1(this.f1944L4, getContext()));
        this.f1942J4.a(this);
    }

    /* renamed from: a */
    public void mo8669a(BookmarkId bookmarkId) {
        h(0);
    }

    /* renamed from: a */
    public void mo8666a(RecyclerView.s sVar) {
        this.f1942J4.b(sVar);
    }
}
