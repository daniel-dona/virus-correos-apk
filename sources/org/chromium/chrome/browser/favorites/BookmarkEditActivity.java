package org.chromium.chrome.browser.favorites;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.chromium.chrome.browser.SynchronousInitializationActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.url_formatter.UrlFormatter;

/* compiled from: PG */
public class BookmarkEditActivity extends SynchronousInitializationActivity implements View.OnClickListener {

    /* renamed from: d */
    public BookmarkModel f1868d;

    /* renamed from: e */
    public BookmarkId f1869e;

    /* renamed from: k */
    public BookmarkTextInputLayout f1870k;

    /* renamed from: n */
    public BookmarkTextInputLayout f1871n;

    /* renamed from: p */
    public TextView f1872p;

    /* renamed from: q */
    public TextView f1873q;

    /* renamed from: q3 */
    public Button f1874q3;

    /* renamed from: r3 */
    public boolean f1875r3;

    /* renamed from: s3 */
    public BookmarkBridge.b f1876s3 = new C0401a();

    /* renamed from: t3 */
    public TextWatcher f1877t3 = new C0402b();

    /* renamed from: x */
    public AppCompatImageButton f1878x;

    /* renamed from: y */
    public TextView f1879y;

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkEditActivity$a */
    /* compiled from: PG */
    public class C0401a extends BookmarkBridge.b {
        public C0401a() {
        }

        /* JADX WARNING: type inference failed for: r0v3, types: [org.chromium.chrome.browser.favorites.BookmarkEditActivity, android.app.Activity] */
        /* renamed from: a */
        public void mo8606a() {
            BookmarkEditActivity bookmarkEditActivity = BookmarkEditActivity.this;
            if (bookmarkEditActivity.f1868d.c(bookmarkEditActivity.f1869e)) {
                BookmarkEditActivity.this.mo8602d(true);
            } else {
                BookmarkEditActivity.this.finish();
            }
        }
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkEditActivity$b */
    /* compiled from: PG */
    public class C0402b implements TextWatcher {
        public C0402b() {
        }

        public void afterTextChanged(Editable editable) {
            BookmarkEditActivity bookmarkEditActivity = BookmarkEditActivity.this;
            bookmarkEditActivity.f1874q3.setEnabled(!bookmarkEditActivity.f1870k.mo8732k() && !BookmarkEditActivity.this.f1871n.mo8732k());
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    /* renamed from: d */
    public final void mo8602d(boolean z) {
        BookmarkBridge.BookmarkItem d = this.f1868d.d(this.f1869e);
        if (!z) {
            this.f1870k.c().setText(d.d());
            String e = d.e();
            String str = null;
            if (e.startsWith("chrome")) {
                str = e.replace("chrome", "edge");
                this.f1875r3 = true;
            } else if (e.startsWith("chrome-native")) {
                str = e.replace("chrome-native", "edge-native");
                this.f1875r3 = true;
            }
            if (this.f1875r3) {
                this.f1871n.c().setText(str);
            } else {
                this.f1871n.c().setText(e);
            }
        }
        this.f1872p.setText(this.f1868d.k(d.b()));
        this.f1870k.setEnabled(d.f());
        this.f1871n.setEnabled(this.f1875r3 ? false : d.j());
        this.f1872p.setEnabled(d.i());
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [org.chromium.chrome.browser.favorites.BookmarkEditActivity, android.app.Activity] */
    public void onClick(View view) {
        String a;
        if (view.getId() == ox0.back) {
            finish();
        } else if (view.getId() == ox0.delete) {
            this.f1868d.a(this.f1869e);
            finish();
        } else if (view.getId() == ox0.save) {
            if (this.f1868d.c(this.f1869e)) {
                String e = this.f1868d.d(this.f1869e).e();
                String j = this.f1870k.mo8731j();
                String j2 = this.f1871n.mo8731j();
                if (!this.f1870k.mo8732k()) {
                    this.f1868d.a(this.f1869e, j);
                }
                if (!this.f1871n.mo8732k() && !this.f1875r3 && this.f1868d.d(this.f1869e).j() && (a = UrlFormatter.m3350a(j2)) != null && !a.equals(e)) {
                    this.f1868d.c(this.f1869e, a);
                }
            }
            finish();
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [org.chromium.chrome.browser.SynchronousInitializationActivity, org.chromium.chrome.browser.favorites.BookmarkEditActivity, android.view.View$OnClickListener, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    public void onMAMCreate(Bundle bundle) {
        BookmarkEditActivity.super.onMAMCreate(bundle);
        this.f1875r3 = false;
        this.f1868d = new BookmarkModel();
        this.f1869e = BookmarkId.a(getIntent().getStringExtra("BookmarkEditActivity.BookmarkId"));
        this.f1868d.a(this.f1876s3);
        BookmarkBridge.BookmarkItem d = this.f1868d.d(this.f1869e);
        if (!this.f1868d.c(this.f1869e) || d == null) {
            finish();
            return;
        }
        setContentView(rx0.favorite_edit);
        this.f1870k = (BookmarkTextInputLayout) findViewById(ox0.title_text);
        this.f1872p = (TextView) findViewById(ox0.folder_text);
        this.f1871n = (BookmarkTextInputLayout) findViewById(ox0.url_text);
        this.f1870k.c().addTextChangedListener(this.f1877t3);
        this.f1872p.setOnClickListener((View.OnClickListener) null);
        this.f1871n.c().addTextChangedListener(this.f1877t3);
        this.f1873q = (TextView) findViewById(ox0.title);
        this.f1878x = findViewById(ox0.back);
        this.f1879y = (TextView) findViewById(ox0.delete);
        this.f1874q3 = (Button) findViewById(ox0.save);
        this.f1873q.setText(wx0.edit_bookmark);
        this.f1878x.setOnClickListener(this);
        this.f1879y.setOnClickListener(this);
        EE2.a(this.f1879y);
        this.f1874q3.setOnClickListener(this);
        this.f1874q3.setEnabled(!this.f1870k.mo8732k() && !this.f1871n.mo8732k());
        mo8602d(false);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [org.chromium.chrome.browser.favorites.BookmarkEditActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity] */
    public void onMAMDestroy() {
        this.f1868d.b(this.f1876s3);
        this.f1868d.a();
        this.f1868d = null;
        this.f1870k.c().removeTextChangedListener(this.f1877t3);
        this.f1871n.c().removeTextChangedListener(this.f1877t3);
        BookmarkEditActivity.super.onMAMDestroy();
    }
}
