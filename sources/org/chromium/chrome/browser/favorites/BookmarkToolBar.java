package org.chromium.chrome.browser.favorites;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import com.microsoft.ruby.telemetry.TelemetryConstants;
import java.util.HashMap;
import java.util.List;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.widget.selection.SelectionDelegate;
import org.chromium.components.bookmarks.BookmarkId;

/* compiled from: PG */
public class BookmarkToolBar extends FrameLayout implements SelectionDelegate.SelectionObserver<BookmarkId>, EW1, View.OnClickListener {

    /* renamed from: a */
    public BookmarkDelegate f2000a;

    /* renamed from: b */
    public ImageButton f2001b;

    /* renamed from: c */
    public AppCompatImageButton f2002c;

    /* renamed from: d */
    public AppCompatImageButton f2003d;

    /* renamed from: e */
    public AppCompatImageButton f2004e;

    /* renamed from: k */
    public AppCompatImageButton f2005k;

    /* renamed from: n */
    public TextView f2006n;

    /* renamed from: p */
    public BookmarkBridge.b f2007p = new C0419a();

    /* renamed from: q */
    public SelectionDelegate<BookmarkId> f2008q;

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkToolBar$a */
    /* compiled from: PG */
    public class C0419a extends BookmarkBridge.b {
        public C0419a() {
        }

        /* renamed from: a */
        public void mo8746a() {
            BookmarkToolBar bookmarkToolBar = BookmarkToolBar.this;
            bookmarkToolBar.onSelectionStateChange(((BookmarkManager) bookmarkToolBar.f2000a).f1934l.b());
        }
    }

    public BookmarkToolBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: a */
    public void mo8738a() {
    }

    /* renamed from: a */
    public void mo8739a(BookmarkDelegate bookmarkDelegate) {
        this.f2000a = bookmarkDelegate;
        this.f2002c.setOnClickListener(this);
        this.f2003d.setOnClickListener(this);
        this.f2004e.setOnClickListener(this);
        this.f2005k.setOnClickListener(this);
        this.f2001b.setOnClickListener(this);
        ((BookmarkManager) this.f2000a).f1927e.mo7868a(this);
        ((BookmarkManager) this.f2000a).mo8650a((EW1) this);
        ((BookmarkManager) bookmarkDelegate).f1925c.a(this.f2007p);
        this.f2008q = ((BookmarkManager) this.f2000a).f1934l;
        this.f2008q.e.mo7868a(this);
    }

    /* renamed from: a */
    public void mo8740a(BookmarkId bookmarkId) {
    }

    public void onClick(View view) {
        ss0.a("HubClick", view);
        String str = null;
        if (view.getId() == ox0.favorite_search_button) {
            ((BookmarkManager) this.f2000a).mo8659f();
            str = "FavoritesSearch";
        } else if (view.getId() == ox0.favorites_cancel_multiselect) {
            this.f2008q.a(false);
            str = "FavoritesCancelMultiSelect";
        } else if (view.getId() == ox0.favorites_delete_button) {
            this.f2008q.b();
            ((BookmarkManager) this.f2000a).f1925c.a((BookmarkId[]) this.f2008q.b().toArray(new BookmarkId[0]));
            ((BookmarkManager) this.f2000a).f1934l.a(false);
            str = "FavoritesDelete";
        } else if (view.getId() == ox0.favorites_move_button) {
            List b = this.f2008q.b();
            if (b.size() >= 1) {
                BookmarkFolderSelectActivity.m2344a(getContext(), this.f2008q, true, (BookmarkId[]) b.toArray(new BookmarkId[b.size()]));
            }
            ((BookmarkManager) this.f2000a).f1934l.a(false);
            str = "FavoritesMove";
        } else if (view.getId() == ox0.favorites_new_folder_button) {
            ss0.b("AddToFavoritesFolderFromHub", (HashMap) null, true, 0, (String) null);
            BookmarkAddEditFolderActivity.m2296a(getContext());
            this.f2008q.a(false);
            str = "FavoritesNewFolder";
        }
        ss0.a("Hub", "Favorites", (String) null, TelemetryConstants.Actions.Click, str, new String[0]);
    }

    public void onDestroy() {
        ((BookmarkManager) this.f2000a).f1927e.mo7869b(this);
        ((BookmarkManager) this.f2000a).f1925c.b(this.f2007p);
        SelectionDelegate<BookmarkId> selectionDelegate = this.f2008q;
        if (selectionDelegate != null) {
            selectionDelegate.e.mo7869b(this);
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.f2006n = (TextView) findViewById(ox0.favorites_heading);
        this.f2001b = (ImageButton) findViewById(ox0.favorite_search_button);
        this.f2002c = findViewById(ox0.favorites_cancel_multiselect);
        this.f2003d = findViewById(ox0.favorites_delete_button);
        this.f2004e = findViewById(ox0.favorites_move_button);
        this.f2005k = findViewById(ox0.favorites_new_folder_button);
    }

    public void onSelectionStateChange(List<BookmarkId> list) {
        if (this.f2008q.c) {
            this.f2002c.setVisibility(0);
            this.f2006n.setVisibility(8);
            if (list.size() > 0) {
                mo8741a(true);
            } else {
                mo8741a(false);
            }
        } else {
            this.f2002c.setVisibility(8);
            this.f2006n.setVisibility(0);
            mo8741a(false);
            ((BookmarkManager) this.f2000a).mo8650a((EW1) this);
        }
    }

    /* renamed from: a */
    public final void mo8741a(boolean z) {
        if (z) {
            this.f2003d.setVisibility(0);
            this.f2004e.setVisibility(0);
            this.f2001b.setVisibility(8);
            this.f2005k.setVisibility(8);
            return;
        }
        this.f2003d.setVisibility(8);
        this.f2004e.setVisibility(8);
        this.f2001b.setVisibility(0);
        this.f2005k.setVisibility(0);
    }
}
