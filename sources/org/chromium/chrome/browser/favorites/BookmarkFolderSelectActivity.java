package org.chromium.chrome.browser.favorites;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatImageButton;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.microsoft.ruby.telemetry.TelemetryConstants;
import java.util.ArrayList;
import java.util.List;
import org.chromium.chrome.browser.SynchronousInitializationActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.widget.selection.SelectionDelegate;
import org.chromium.components.bookmarks.BookmarkId;

/* compiled from: PG */
public class BookmarkFolderSelectActivity extends SynchronousInitializationActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    /* renamed from: s3 */
    public static SelectionDelegate<BookmarkId> f1896s3;

    /* renamed from: t3 */
    public static boolean f1897t3;

    /* renamed from: d */
    public BookmarkModel f1898d;

    /* renamed from: e */
    public boolean f1899e;

    /* renamed from: k */
    public List<BookmarkId> f1900k;

    /* renamed from: n */
    public C0405b f1901n;

    /* renamed from: p */
    public ListView f1902p;

    /* renamed from: q */
    public TextView f1903q;

    /* renamed from: q3 */
    public int f1904q3 = -1;

    /* renamed from: r3 */
    public BookmarkBridge.b f1905r3 = new C0404a();

    /* renamed from: x */
    public AppCompatImageButton f1906x;

    /* renamed from: y */
    public Button f1907y;

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkFolderSelectActivity$a */
    /* compiled from: PG */
    public class C0404a extends BookmarkBridge.b {
        public C0404a() {
        }

        /* renamed from: a */
        public void mo8638a() {
            BookmarkFolderSelectActivity.this.mo8630J();
        }

        /* JADX WARNING: type inference failed for: r1v11, types: [org.chromium.chrome.browser.favorites.BookmarkFolderSelectActivity, android.app.Activity] */
        /* renamed from: a */
        public void mo8639a(BookmarkBridge.BookmarkItem bookmarkItem, int i, BookmarkBridge.BookmarkItem bookmarkItem2, boolean z) {
            if (BookmarkFolderSelectActivity.this.f1900k.contains(bookmarkItem2.a())) {
                BookmarkFolderSelectActivity.this.f1900k.remove(bookmarkItem2.a());
                if (BookmarkFolderSelectActivity.this.f1900k.isEmpty()) {
                    BookmarkFolderSelectActivity.this.finish();
                }
            } else if (bookmarkItem2.g()) {
                BookmarkFolderSelectActivity.this.mo8630J();
            }
        }
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkFolderSelectActivity$b */
    /* compiled from: PG */
    public static class C0405b extends BaseAdapter {

        /* renamed from: a */
        public final int f1909a;

        /* renamed from: b */
        public final int f1910b;

        /* renamed from: c */
        public List<C0406c> f1911c = new ArrayList();

        public C0405b(Context context) {
            this.f1909a = context.getResources().getDimensionPixelSize(kx0.bookmark_folder_item_left);
            this.f1910b = this.f1909a * 2;
        }

        /* renamed from: a */
        public void mo8640a(int i, int i2) {
            if (i != -1) {
                this.f1911c.get(i).f1915d = false;
            }
            if (i2 != -1) {
                this.f1911c.get(i2).f1915d = true;
            }
            notifyDataSetChanged();
        }

        public int getCount() {
            return this.f1911c.size();
        }

        public Object getItem(int i) {
            return this.f1911c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public int getItemViewType(int i) {
            return this.f1911c.get(i).f1916e;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            int i2;
            C0406c cVar = this.f1911c.get(i);
            if (view != null && cVar.f1916e != 1) {
                return view;
            }
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(rx0.favorite_folder_select_item, viewGroup, false);
            }
            TextView textView = (TextView) view;
            textView.setText(cVar.f1913b);
            int i3 = cVar.f1916e;
            if (i3 == 1) {
                i2 = lx0.bookmark_folder;
            } else {
                i2 = i3 == 0 ? lx0.bookmark_add_folder : 0;
            }
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(HH2.a(textView.getContext(), i2), (Drawable) null, (Drawable) null, (Drawable) null);
            I9.a(textView, (Math.min(cVar.f1914c, 5) * this.f1910b) + this.f1909a, 0, this.f1909a, 0);
            textView.setActivated(cVar.f1915d);
            if (cVar.f1915d) {
                textView.setContentDescription(textView.getContext().getString(wx0.folder_state_accessibility, new Object[]{textView.getContext().getString(wx0.folder_level_accessibility, new Object[]{cVar.f1913b, Integer.valueOf(cVar.f1914c)})}));
            } else {
                textView.setContentDescription(textView.getContext().getString(wx0.folder_level_accessibility, new Object[]{cVar.f1913b, Integer.valueOf(cVar.f1914c)}));
            }
            return textView;
        }

        public int getViewTypeCount() {
            return 2;
        }
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkFolderSelectActivity$c */
    /* compiled from: PG */
    public static class C0406c {

        /* renamed from: a */
        public BookmarkId f1912a;

        /* renamed from: b */
        public String f1913b;

        /* renamed from: c */
        public int f1914c;

        /* renamed from: d */
        public boolean f1915d;

        /* renamed from: e */
        public int f1916e;

        static {
            Class<BookmarkFolderSelectActivity> cls = BookmarkFolderSelectActivity.class;
        }

        public C0406c(BookmarkId bookmarkId, int i, String str, boolean z, int i2) {
            this.f1914c = i;
            this.f1912a = bookmarkId;
            this.f1913b = str;
            this.f1915d = z;
            this.f1916e = i2;
        }
    }

    static {
        Class<BookmarkFolderSelectActivity> cls = BookmarkFolderSelectActivity.class;
    }

    /* JADX WARNING: type inference failed for: r11v0, types: [org.chromium.chrome.browser.favorites.BookmarkFolderSelectActivity, android.app.Activity] */
    /* renamed from: J */
    public final void mo8630J() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        this.f1898d.a(arrayList, arrayList2, this.f1900k);
        ArrayList arrayList3 = new ArrayList(arrayList.size() + 3);
        if (!this.f1899e) {
            arrayList3.add(new C0406c((BookmarkId) null, 0, getString(wx0.bookmark_add_folder), false, 0));
        }
        for (int i = 0; i < arrayList.size(); i++) {
            BookmarkId bookmarkId = (BookmarkId) arrayList.get(i);
            if (this.f1898d.i(bookmarkId) && !cg0.a.c(bookmarkId)) {
                arrayList3.add(new C0406c(bookmarkId, ((Integer) arrayList2.get(i)).intValue(), this.f1898d.d(bookmarkId).d(), false, 1));
            }
        }
        C0405b bVar = this.f1901n;
        bVar.f1911c = arrayList3;
        bVar.notifyDataSetChanged();
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [org.chromium.chrome.browser.favorites.BookmarkFolderSelectActivity, android.app.Activity] */
    public void onBackPressed() {
        if (this.f1904q3 != -1 || this.f1907y.isEnabled()) {
            this.f1901n.mo8640a(this.f1904q3, -1);
            this.f1904q3 = -1;
            this.f1907y.setEnabled(false);
            return;
        }
        finish();
    }

    /* JADX WARNING: type inference failed for: r10v0, types: [org.chromium.chrome.browser.favorites.BookmarkFolderSelectActivity, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    public void onClick(View view) {
        BookmarkId bookmarkId = null;
        if (view.getId() == ox0.save) {
            C0406c cVar = (C0406c) this.f1902p.getItemAtPosition(this.f1904q3);
            if (this.f1899e) {
                if (cVar.f1916e == 1) {
                    bookmarkId = cVar.f1912a;
                }
                Intent intent = new Intent();
                intent.putExtra("BookmarkFolderSelectActivity.selectedFolder", bookmarkId.toString());
                setResult(-1, intent);
            } else if (cVar.f1916e == 1) {
                this.f1898d.a(this.f1900k, cVar.f1912a);
                LW1.a(cVar.f1912a);
            }
            SelectionDelegate<BookmarkId> selectionDelegate = f1896s3;
            if (selectionDelegate != null) {
                selectionDelegate.a(false);
            }
            if (this.f1900k == null || !f1897t3 || getCurrentFocus() == null) {
                finish();
            } else {
                Snackbar a = Snackbar.a(getCurrentFocus(), getResources().getQuantityString(ux0.bookmarks_moved, this.f1900k.size(), new Object[]{Integer.valueOf(this.f1900k.size())}), -1);
                a.a(new mW1(this));
                a.a(getString(wx0.undo), new nW1(this));
                a.e();
            }
            bookmarkId = "Save";
        } else if (view.getId() == ox0.back) {
            onBackPressed();
            bookmarkId = "Back";
        }
        BookmarkId bookmarkId2 = bookmarkId;
        if (bookmarkId2 != null) {
            ss0.a("Hub", "BookmarkFolderSelect", (String) null, TelemetryConstants.Actions.Click, bookmarkId2, new String[0]);
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [org.chromium.chrome.browser.favorites.BookmarkFolderSelectActivity, android.app.Activity] */
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (((C0406c) adapterView.getItemAtPosition(i)).f1916e == 0) {
            BookmarkAddEditFolderActivity.m2298a((BookmarkFolderSelectActivity) this, this.f1900k);
            return;
        }
        int i2 = this.f1904q3;
        if (i2 != -1 && i2 == i) {
            i = -1;
        }
        this.f1901n.mo8640a(this.f1904q3, i);
        this.f1904q3 = i;
        this.f1907y.setEnabled(this.f1904q3 != -1);
        if (!EE2.a()) {
            return;
        }
        if (i == -1) {
            view.announceForAccessibility(getText(wx0.accessibility_cancel_selection));
        } else {
            EE2.b(view);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.favorites.BookmarkFolderSelectActivity, com.microsoft.intune.mam.client.support.v7.app.MAMAppCompatActivityBase, android.app.Activity] */
    public void onMAMActivityResult(int i, int i2, Intent intent) {
        BookmarkFolderSelectActivity.super.onMAMActivityResult(i, i2, intent);
        if (i == 13 && i2 == -1) {
            BookmarkId a = BookmarkId.a(intent.getStringExtra("BookmarkAddEditFolderActivity.createdBookmark"));
            this.f1898d.a(this.f1900k, a);
            LW1.a(a);
            finish();
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [org.chromium.chrome.browser.favorites.BookmarkFolderSelectActivity, org.chromium.chrome.browser.SynchronousInitializationActivity, android.content.Context, android.view.View$OnClickListener, android.widget.AdapterView$OnItemClickListener, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    public void onMAMCreate(Bundle bundle) {
        BookmarkFolderSelectActivity.super.onMAMCreate(bundle);
        this.f1898d = new BookmarkModel();
        this.f1898d.a(this.f1905r3);
        ArrayList<String> stringArrayListExtra = getIntent().getStringArrayListExtra("BookmarkFolderSelectActivity.bookmarksToMove");
        if (stringArrayListExtra != null) {
            this.f1900k = new ArrayList(stringArrayListExtra.size());
            for (String a : stringArrayListExtra) {
                BookmarkId a2 = BookmarkId.a(a);
                if (this.f1898d.c(a2)) {
                    this.f1900k.add(a2);
                }
            }
            if (this.f1900k.isEmpty()) {
                finish();
                return;
            }
        }
        this.f1899e = getIntent().getBooleanExtra("BookmarkFolderSelectActivity.isCreatingFolder", false);
        if (this.f1899e) {
            this.f1898d.e();
        } else {
            this.f1898d.d(this.f1900k.get(0)).b();
        }
        setContentView(rx0.favorite_folder_select_activity);
        this.f1902p = (ListView) findViewById(ox0.bookmark_folder_list);
        this.f1902p.setOnItemClickListener(this);
        this.f1901n = new C0405b(this);
        this.f1902p.setAdapter(this.f1901n);
        this.f1903q = (TextView) findViewById(ox0.title);
        this.f1903q.setText(wx0.bookmark_choose_folder);
        this.f1906x = findViewById(ox0.back);
        this.f1906x.setOnClickListener(this);
        this.f1907y = (Button) findViewById(ox0.save);
        this.f1907y.setOnClickListener(this);
        this.f1907y.setEnabled(false);
        mo8630J();
        ss0.a("Hub", "BookmarkFolderSelect", (String) null, new String[0]);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [org.chromium.chrome.browser.favorites.BookmarkFolderSelectActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity] */
    public void onMAMDestroy() {
        BookmarkFolderSelectActivity.super.onMAMDestroy();
        this.f1898d.b(this.f1905r3);
        this.f1898d.a();
        this.f1898d = null;
        f1896s3 = null;
        ss0.b("Hub", "BookmarkFolderSelect", (String) null, new String[0]);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [org.chromium.chrome.browser.favorites.BookmarkFolderSelectActivity, android.app.Activity] */
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return BookmarkFolderSelectActivity.super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    /* renamed from: a */
    public static void m2344a(Context context, SelectionDelegate<BookmarkId> selectionDelegate, boolean z, BookmarkId... bookmarkIdArr) {
        Intent intent = new Intent(context, BookmarkFolderSelectActivity.class);
        intent.putExtra("BookmarkFolderSelectActivity.isCreatingFolder", false);
        ArrayList arrayList = new ArrayList(bookmarkIdArr.length);
        for (BookmarkId bookmarkId : bookmarkIdArr) {
            arrayList.add(bookmarkId.toString());
        }
        f1896s3 = selectionDelegate;
        intent.putStringArrayListExtra("BookmarkFolderSelectActivity.bookmarksToMove", arrayList);
        f1897t3 = z;
        context.startActivity(intent);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.microsoft.intune.mam.client.support.v7.app.MAMAppCompatActivityBase, org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m2345a(org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity r3, java.util.List<org.chromium.components.bookmarks.BookmarkId> r4) {
        /*
            android.content.Intent r0 = new android.content.Intent
            java.lang.Class<org.chromium.chrome.browser.favorites.BookmarkFolderSelectActivity> r1 = org.chromium.chrome.browser.favorites.BookmarkFolderSelectActivity.class
            r0.<init>(r3, r1)
            java.lang.String r1 = "BookmarkFolderSelectActivity.isCreatingFolder"
            r2 = 1
            r0.putExtra(r1, r2)
            if (r4 == 0) goto L_0x0035
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r4.size()
            r1.<init>(r2)
            java.util.Iterator r4 = r4.iterator()
        L_0x001c:
            boolean r2 = r4.hasNext()
            if (r2 == 0) goto L_0x0030
            java.lang.Object r2 = r4.next()
            org.chromium.components.bookmarks.BookmarkId r2 = (org.chromium.components.bookmarks.BookmarkId) r2
            java.lang.String r2 = r2.toString()
            r1.add(r2)
            goto L_0x001c
        L_0x0030:
            java.lang.String r4 = "BookmarkFolderSelectActivity.bookmarksToMove"
            r0.putStringArrayListExtra(r4, r1)
        L_0x0035:
            r4 = 0
            f1897t3 = r4
            r4 = 10
            r3.startActivityForResult(r0, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.favorites.BookmarkFolderSelectActivity.m2345a(org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity, java.util.List):void");
    }
}
