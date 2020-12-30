package org.chromium.chrome.browser.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import org.chromium.chrome.browser.SnackbarActivity;
import org.chromium.components.bookmarks.BookmarkId;

/* compiled from: PG */
public class BookmarkActivity extends SnackbarActivity {

    /* renamed from: e */
    public BookmarkManager f1805e;

    static {
        Class<BookmarkActivity> cls = BookmarkActivity.class;
    }

    public void onBackPressed() {
        if (!this.f1805e.mo8658e()) {
            BookmarkActivity.super.onBackPressed();
        }
    }

    public void onMAMActivityResult(int i, int i2, Intent intent) {
        BookmarkActivity.super.onMAMActivityResult(i, i2, intent);
        if (i == 14 && i2 == -1) {
            this.f1805e.mo8654a(BookmarkId.a(intent.getStringExtra("BookmarkEditActivity.VisitBookmarkId")), 5);
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [org.chromium.chrome.browser.favorites.BookmarkActivity, android.app.Activity, org.chromium.chrome.browser.SnackbarActivity, android.support.v7.app.AppCompatActivity] */
    public void onMAMCreate(Bundle bundle) {
        BookmarkActivity.super.onMAMCreate(bundle);
        this.f1805e = new BookmarkManager(this, true, (ViewGroup) null);
        String dataString = getIntent().getDataString();
        if (TextUtils.isEmpty(dataString)) {
            dataString = "chrome-native://bookmarks/";
        }
        this.f1805e.mo8652a(dataString);
        setContentView(this.f1805e.f1924b);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.ChromeBaseAppCompatActivity, org.chromium.chrome.browser.favorites.BookmarkActivity] */
    public void onMAMDestroy() {
        BookmarkActivity.super.onMAMDestroy();
        this.f1805e.mo8655b();
    }
}
