package org.chromium.chrome.browser.favorites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatImageButton;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.microsoft.ruby.sync.RubySyncClient;
import com.microsoft.ruby.telemetry.TelemetryConstants;
import java.util.ArrayList;
import java.util.List;
import org.chromium.chrome.browser.SynchronousInitializationActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.widget.selection.SelectionDelegate;
import org.chromium.components.bookmarks.BookmarkId;

/* compiled from: PG */
public class BookmarkAddEditFolderActivity extends SynchronousInitializationActivity implements View.OnClickListener {

    /* renamed from: d */
    public boolean f1810d;

    /* renamed from: e */
    public BookmarkId f1811e;

    /* renamed from: k */
    public BookmarkModel f1812k;

    /* renamed from: n */
    public TextView f1813n;

    /* renamed from: p */
    public BookmarkTextInputLayout f1814p;

    /* renamed from: q */
    public List<BookmarkId> f1815q;

    /* renamed from: q3 */
    public AppCompatImageButton f1816q3;

    /* renamed from: r3 */
    public TextView f1817r3;

    /* renamed from: s3 */
    public Button f1818s3;

    /* renamed from: t3 */
    public String f1819t3;

    /* renamed from: u3 */
    public BookmarkBridge.b f1820u3 = new C0388a();

    /* renamed from: v3 */
    public TextWatcher f1821v3 = new C0389b();

    /* renamed from: x */
    public BookmarkId f1822x;

    /* renamed from: y */
    public TextView f1823y;

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity$b */
    /* compiled from: PG */
    public class C0389b implements TextWatcher {
        public C0389b() {
        }

        public void afterTextChanged(Editable editable) {
            BookmarkAddEditFolderActivity.this.f1818s3.setEnabled(!TextUtils.isEmpty(editable.toString().trim()) && !editable.toString().equalsIgnoreCase("_Favorites_Bar_"));
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity$c */
    /* compiled from: PG */
    public class C0390c extends N2 {
        public C0390c() {
        }

        /* JADX WARNING: type inference failed for: r1v2, types: [android.app.Activity, org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity] */
        /* renamed from: a */
        public void mo8553a(Object obj, int i) {
            Snackbar snackbar = (Snackbar) obj;
            BookmarkAddEditFolderActivity.this.finish();
        }
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity$d */
    /* compiled from: PG */
    public class C0391d implements View.OnClickListener {

        /* renamed from: a */
        public final /* synthetic */ Intent f1827a;

        public C0391d(Intent intent) {
            this.f1827a = intent;
        }

        /* JADX WARNING: type inference failed for: r5v6, types: [android.app.Activity, org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity] */
        /* JADX WARNING: type inference failed for: r5v7, types: [android.app.Activity, org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity] */
        public void onClick(View view) {
            BookmarkAddEditFolderActivity.this.f1812k.p();
            for (BookmarkId next : BookmarkAddEditFolderActivity.this.f1815q) {
                BookmarkAddEditFolderActivity.this.f1812k.a(next, System.currentTimeMillis());
                RubySyncClient.i().b(BookmarkAddEditFolderActivity.this.f1812k.d(next));
            }
            BookmarkAddEditFolderActivity.this.setResult(0, this.f1827a);
            BookmarkAddEditFolderActivity.this.finish();
        }
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity$e */
    /* compiled from: PG */
    public class C0392e extends N2 {
        public C0392e() {
        }

        /* JADX WARNING: type inference failed for: r1v2, types: [android.app.Activity, org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity] */
        /* renamed from: a */
        public void mo8555a(Object obj, int i) {
            Snackbar snackbar = (Snackbar) obj;
            BookmarkAddEditFolderActivity.this.finish();
        }
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity$f */
    /* compiled from: PG */
    public class C0393f implements View.OnClickListener {
        public C0393f() {
        }

        /* JADX WARNING: type inference failed for: r1v1, types: [android.app.Activity, org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity] */
        public void onClick(View view) {
            BookmarkAddEditFolderActivity.this.finish();
        }
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity$g */
    /* compiled from: PG */
    public class C0394g extends N2 {
        public C0394g() {
        }

        /* JADX WARNING: type inference failed for: r1v2, types: [android.app.Activity, org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity] */
        /* renamed from: a */
        public void mo8557a(Object obj, int i) {
            Snackbar snackbar = (Snackbar) obj;
            BookmarkAddEditFolderActivity.this.finish();
        }
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity$h */
    /* compiled from: PG */
    public class C0395h implements View.OnClickListener {
        public C0395h() {
        }

        public void onClick(View view) {
            BookmarkAddEditFolderActivity.this.f1812k.p();
        }
    }

    static {
        Class<BookmarkAddEditFolderActivity> cls = BookmarkAddEditFolderActivity.class;
    }

    /* renamed from: a */
    public static void m2297a(Context context, BookmarkId bookmarkId) {
        Intent intent = new Intent(context, BookmarkAddEditFolderActivity.class);
        intent.putExtra("BookmarkAddEditFolderActivity.isAddMode", false);
        intent.putExtra("BookmarkAddEditFolderActivity.BookmarkId", bookmarkId.toString());
        context.startActivity(intent);
    }

    /* JADX WARNING: type inference failed for: r0v9, types: [android.widget.LinearLayout, org.chromium.chrome.browser.favorites.BookmarkTextInputLayout] */
    /* JADX WARNING: type inference failed for: r12v5, types: [android.widget.LinearLayout, org.chromium.chrome.browser.favorites.BookmarkTextInputLayout] */
    public void onClick(View view) {
        if (view.getId() == ox0.parent_folder) {
            if (this.f1810d) {
                BookmarkFolderSelectActivity.m2345a(this, this.f1815q);
            } else {
                BookmarkFolderSelectActivity.m2344a(this, (SelectionDelegate<BookmarkId>) null, false, this.f1822x);
            }
        } else if (view.getId() == ox0.back) {
            finish();
        } else if (view.getId() == ox0.delete) {
            this.f1812k.a(new BookmarkId[]{this.f1822x});
            mo8541a("bookmark.delete", (Intent) null);
        } else if (view.getId() == ox0.save) {
            if (this.f1810d) {
                if (this.f1814p.mo8732k()) {
                    this.f1814p.mo8733l();
                    this.f1814p.requestFocus();
                    return;
                }
                BookmarkModel bookmarkModel = this.f1812k;
                BookmarkId bookmarkId = this.f1811e;
                BookmarkId a = bookmarkModel.a(bookmarkId, bookmarkModel.g(bookmarkId), this.f1814p.mo8731j());
                Intent intent = new Intent();
                intent.putExtra("BookmarkAddEditFolderActivity.createdBookmark", a.toString());
                setResult(-1, intent);
                if (this.f1815q != null) {
                    mo8541a("bookmark.move", intent);
                } else {
                    mo8541a("bookmark.create", intent);
                }
            } else if (!this.f1812k.c(this.f1822x) || this.f1814p.mo8732k()) {
                this.f1814p.mo8733l();
                this.f1814p.requestFocus();
            } else {
                this.f1812k.a(this.f1822x, this.f1814p.mo8731j());
                finish();
            }
        }
        ss0.a("Hub", this.f1819t3, (String) null, TelemetryConstants.Actions.Click, Pp0.b(Pp0.a(view.getId())), new String[0]);
        ss0.b("Hub", this.f1819t3, (String) null, new String[0]);
    }

    public void onMAMActivityResult(int i, int i2, Intent intent) {
        BookmarkAddEditFolderActivity.super.onMAMActivityResult(i, i2, intent);
        if (i == 10 && i2 == -1) {
            mo8542a(BookmarkId.a(intent.getStringExtra("BookmarkFolderSelectActivity.selectedFolder")));
        }
    }

    /* JADX WARNING: type inference failed for: r5v51, types: [android.widget.LinearLayout, org.chromium.chrome.browser.favorites.BookmarkTextInputLayout] */
    public void onMAMCreate(Bundle bundle) {
        BookmarkAddEditFolderActivity.super.onMAMCreate(bundle);
        this.f1812k = new BookmarkModel();
        this.f1812k.a(this.f1820u3);
        this.f1810d = getIntent().getBooleanExtra("BookmarkAddEditFolderActivity.isAddMode", false);
        if (this.f1810d) {
            setTitle(getString(wx0.add_folder));
            ArrayList<String> stringArrayListExtra = getIntent().getStringArrayListExtra("BookmarkFolderSelectActivity.bookmarksToMove");
            if (stringArrayListExtra != null) {
                this.f1815q = new ArrayList(stringArrayListExtra.size());
                for (String a : stringArrayListExtra) {
                    this.f1815q.add(BookmarkId.a(a));
                }
            }
        } else {
            setTitle(getString(wx0.edit_folder));
            this.f1822x = BookmarkId.a(getIntent().getStringExtra("BookmarkAddEditFolderActivity.BookmarkId"));
        }
        setContentView(rx0.favorite_add_edit_folder_activity);
        this.f1813n = (TextView) findViewById(ox0.parent_folder);
        EE2.a(this.f1813n);
        this.f1814p = (BookmarkTextInputLayout) findViewById(ox0.folder_title);
        this.f1814p.c().addTextChangedListener(this.f1821v3);
        this.f1813n.setOnClickListener(this);
        this.f1823y = (TextView) findViewById(ox0.title);
        this.f1816q3 = findViewById(ox0.back);
        this.f1817r3 = (TextView) findViewById(ox0.delete);
        this.f1818s3 = (Button) findViewById(ox0.save);
        this.f1816q3.setOnClickListener(this);
        this.f1817r3.setOnClickListener(this);
        EE2.a(this.f1817r3);
        this.f1818s3.setOnClickListener(this);
        this.f1818s3.setEnabled(!this.f1814p.mo8732k());
        if (this.f1810d) {
            mo8542a(this.f1812k.q());
            this.f1823y.setText(wx0.add_folder);
            this.f1814p.c().setHint(wx0.add_folder);
            this.f1817r3.setVisibility(8);
            this.f1819t3 = "BookmarkAddFolder";
            this.f1814p.requestFocus();
        } else {
            BookmarkBridge.BookmarkItem d = this.f1812k.d(this.f1822x);
            mo8542a(d.b());
            this.f1814p.c().setText(d.d());
            this.f1814p.c().setSelection(this.f1814p.mo8731j().length());
            this.f1813n.setEnabled(d.i());
            this.f1823y.setText(wx0.edit_folder);
            this.f1817r3.setVisibility(0);
            this.f1819t3 = "BookmarkEditFolder";
        }
        this.f1813n.setText(this.f1812k.k(this.f1811e));
        TextView textView = this.f1813n;
        textView.setContentDescription(this.f1813n.getText() + ", " + getResources().getString(wx0.bookmark_parent_folder));
        ss0.a("Hub", this.f1819t3, (String) null, new String[0]);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [org.chromium.chrome.browser.ChromeBaseAppCompatActivity, org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity] */
    public void onMAMDestroy() {
        BookmarkAddEditFolderActivity.super.onMAMDestroy();
        this.f1812k.b(this.f1820u3);
        this.f1812k.a();
        this.f1812k = null;
        this.f1814p.c().removeTextChangedListener(this.f1821v3);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [org.chromium.chrome.browser.favorites.BookmarkFolderSelectActivity, android.content.Context, com.microsoft.intune.mam.client.support.v7.app.MAMAppCompatActivityBase] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m2298a(org.chromium.chrome.browser.favorites.BookmarkFolderSelectActivity r3, java.util.List<org.chromium.components.bookmarks.BookmarkId> r4) {
        /*
            android.content.Intent r0 = new android.content.Intent
            java.lang.Class<org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity> r1 = org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity.class
            r0.<init>(r3, r1)
            r1 = 1
            java.lang.String r2 = "BookmarkAddEditFolderActivity.isAddMode"
            r0.putExtra(r2, r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r4.size()
            r1.<init>(r2)
            java.util.Iterator r4 = r4.iterator()
        L_0x001a:
            boolean r2 = r4.hasNext()
            if (r2 == 0) goto L_0x002e
            java.lang.Object r2 = r4.next()
            org.chromium.components.bookmarks.BookmarkId r2 = (org.chromium.components.bookmarks.BookmarkId) r2
            java.lang.String r2 = r2.toString()
            r1.add(r2)
            goto L_0x001a
        L_0x002e:
            java.lang.String r4 = "BookmarkFolderSelectActivity.bookmarksToMove"
            r0.putStringArrayListExtra(r4, r1)
            r4 = 13
            r3.startActivityForResult(r0, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity.m2298a(org.chromium.chrome.browser.favorites.BookmarkFolderSelectActivity, java.util.List):void");
    }

    /* renamed from: a */
    public static void m2296a(Context context) {
        Intent intent = new Intent(context, BookmarkAddEditFolderActivity.class);
        intent.putExtra("BookmarkAddEditFolderActivity.isAddMode", true);
        context.startActivity(intent);
    }

    /* renamed from: a */
    public final void mo8542a(BookmarkId bookmarkId) {
        this.f1811e = bookmarkId;
        this.f1813n.setText(this.f1812k.k(this.f1811e));
        TextView textView = this.f1813n;
        textView.setContentDescription(this.f1813n.getText() + ", " + getResources().getString(wx0.bookmark_parent_folder));
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [android.app.Activity, android.support.v7.app.AppCompatActivity, org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity] */
    /* renamed from: a */
    public final void mo8541a(String str, Intent intent) {
        View currentFocus = getCurrentFocus() != null ? getCurrentFocus() : this.f1823y;
        if (str != null && str.equals("bookmark.move")) {
            Snackbar a = Snackbar.a(currentFocus, getResources().getQuantityString(ux0.bookmarks_moved, this.f1815q.size(), new Object[]{Integer.valueOf(this.f1815q.size())}), -1);
            a.a(new C0390c());
            a.a(getString(wx0.undo), new C0391d(intent));
            a.e();
        } else if (str != null && str.equals("bookmark.create")) {
            Snackbar a2 = Snackbar.a(currentFocus, getResources().getString(wx0.added_folder), -1);
            a2.a(new C0392e());
            a2.a(getString(wx0.ok), new C0393f());
            a2.e();
        } else if (str != null && str.equals("bookmark.delete")) {
            Snackbar a3 = Snackbar.a(currentFocus, getResources().getString(wx0.accessibility_favorite_deleted), -1);
            a3.a(new C0394g());
            a3.a(getString(wx0.undo), new C0395h());
            a3.e();
        }
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity$a */
    /* compiled from: PG */
    public class C0388a extends BookmarkBridge.b {
        public C0388a() {
        }

        /* JADX WARNING: type inference failed for: r0v3, types: [android.app.Activity, org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity] */
        /* renamed from: a */
        public void mo8547a() {
            BookmarkAddEditFolderActivity bookmarkAddEditFolderActivity = BookmarkAddEditFolderActivity.this;
            if (bookmarkAddEditFolderActivity.f1810d) {
                if (bookmarkAddEditFolderActivity.f1812k.c(bookmarkAddEditFolderActivity.f1811e)) {
                    BookmarkAddEditFolderActivity bookmarkAddEditFolderActivity2 = BookmarkAddEditFolderActivity.this;
                    bookmarkAddEditFolderActivity2.mo8542a(bookmarkAddEditFolderActivity2.f1811e);
                    return;
                }
                BookmarkAddEditFolderActivity bookmarkAddEditFolderActivity3 = BookmarkAddEditFolderActivity.this;
                bookmarkAddEditFolderActivity3.mo8542a(bookmarkAddEditFolderActivity3.f1812k.q());
            } else if (bookmarkAddEditFolderActivity.f1812k.c(bookmarkAddEditFolderActivity.f1822x)) {
                BookmarkAddEditFolderActivity bookmarkAddEditFolderActivity4 = BookmarkAddEditFolderActivity.this;
                bookmarkAddEditFolderActivity4.mo8542a(bookmarkAddEditFolderActivity4.f1812k.d(bookmarkAddEditFolderActivity4.f1822x).b());
            } else {
                BookmarkAddEditFolderActivity.this.finish();
            }
        }

        /* renamed from: a */
        public void mo8548a(BookmarkBridge.BookmarkItem bookmarkItem, int i, BookmarkBridge.BookmarkItem bookmarkItem2, int i2) {
            if (!bookmarkItem.a().equals(bookmarkItem2.a()) && BookmarkAddEditFolderActivity.this.f1812k.a(bookmarkItem2.a(), i2).equals(BookmarkAddEditFolderActivity.this.f1822x)) {
                BookmarkAddEditFolderActivity.this.mo8542a(bookmarkItem2.a());
            }
        }

        /* renamed from: a */
        public void mo8549a(BookmarkBridge.BookmarkItem bookmarkItem, int i, BookmarkBridge.BookmarkItem bookmarkItem2, boolean z) {
            if (!bookmarkItem2.a().equals(BookmarkAddEditFolderActivity.this.f1822x)) {
            }
        }
    }
}
