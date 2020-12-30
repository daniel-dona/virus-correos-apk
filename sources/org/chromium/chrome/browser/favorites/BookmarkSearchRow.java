package org.chromium.chrome.browser.favorites;

import android.content.Context;
import android.util.AttributeSet;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.components.bookmarks.BookmarkId;

/* compiled from: PG */
public class BookmarkSearchRow extends BookmarkItemRow {
    public BookmarkSearchRow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: b */
    public BookmarkBridge.BookmarkItem mo8622b(BookmarkId bookmarkId) {
        return super.mo8622b(bookmarkId);
    }

    /* renamed from: b */
    public boolean mo8623b() {
        return false;
    }

    /* renamed from: c */
    public void mo8624c() {
        ss0.a("HubClick", this);
        ((BookmarkManager) this.f1969p).mo8654a(this.f1970q, 4);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.f1967k.setVisibility(8);
    }
}
