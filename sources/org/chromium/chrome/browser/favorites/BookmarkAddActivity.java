package org.chromium.chrome.browser.favorites;

import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.init.AsyncInitializationActivity;
import org.chromium.components.bookmarks.BookmarkId;

/* compiled from: PG */
public class BookmarkAddActivity extends AsyncInitializationActivity {

    /* renamed from: C3 */
    public BookmarkModel f1806C3;

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkAddActivity$a */
    /* compiled from: PG */
    public class C0387a implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ String f1807a;

        /* renamed from: b */
        public final /* synthetic */ String f1808b;

        public C0387a(String str, String str2) {
            this.f1807a = str;
            this.f1808b = str2;
        }

        /* JADX WARNING: type inference failed for: r0v3, types: [android.app.Activity, org.chromium.chrome.browser.favorites.BookmarkAddActivity] */
        /* JADX WARNING: type inference failed for: r1v1, types: [android.content.Context, org.chromium.chrome.browser.favorites.BookmarkAddActivity] */
        public void run() {
            BookmarkModel bookmarkModel = BookmarkAddActivity.this.f1806C3;
            String str = this.f1807a;
            String str2 = this.f1808b;
            BookmarkId a = LW1.a();
            if (a == null || !bookmarkModel.c(a)) {
                a = bookmarkModel.q();
            }
            BookmarkId a2 = bookmarkModel.a(a, bookmarkModel.g(a), str, str2);
            if (a2 != null) {
                LW1.b(BookmarkAddActivity.this, a2);
            }
            BookmarkAddActivity.this.finish();
        }
    }

    /* renamed from: g0 */
    public void mo8105g0() {
    }

    public void onMAMDestroy() {
        super.onMAMDestroy();
        BookmarkModel bookmarkModel = this.f1806C3;
        if (bookmarkModel != null) {
            bookmarkModel.a();
            this.f1806C3 = null;
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity, org.chromium.chrome.browser.favorites.BookmarkAddActivity] */
    /* renamed from: t */
    public void mo8165t() {
        super.mo8165t();
        String stringExtra = getIntent().getStringExtra("title");
        String stringExtra2 = getIntent().getStringExtra("url");
        zi2.a(this);
        this.f1806C3 = new BookmarkModel();
        this.f1806C3.a(new C0387a(stringExtra, stringExtra2));
    }

    /* renamed from: u */
    public boolean mo8539u() {
        return false;
    }
}
