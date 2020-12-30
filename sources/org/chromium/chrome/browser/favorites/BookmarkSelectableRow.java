package org.chromium.chrome.browser.favorites;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.PopupMenu;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.microsoft.ruby.telemetry.TelemetryConstants;
import java.util.List;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.widget.selection.SelectionDelegate;
import org.chromium.components.bookmarks.BookmarkId;

/* compiled from: PG */
public abstract class BookmarkSelectableRow extends FrameLayout implements EW1, View.OnClickListener, Checkable, SelectionDelegate.SelectionObserver<BookmarkId> {

    /* renamed from: a */
    public LinearLayout f1962a;

    /* renamed from: b */
    public ImageView f1963b;

    /* renamed from: c */
    public TextView f1964c;

    /* renamed from: d */
    public TextView f1965d;

    /* renamed from: e */
    public ImageView f1966e;

    /* renamed from: k */
    public AppCompatImageButton f1967k;

    /* renamed from: n */
    public CheckBox f1968n;

    /* renamed from: p */
    public BookmarkDelegate f1969p;

    /* renamed from: q */
    public BookmarkId f1970q;

    /* renamed from: q3 */
    public PopupMenu f1971q3;

    /* renamed from: x */
    public boolean f1972x;

    /* renamed from: y */
    public SelectionDelegate<BookmarkId> f1973y;

    public BookmarkSelectableRow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: a */
    public void mo8621a(BookmarkDelegate bookmarkDelegate) {
        SelectionDelegate<BookmarkId> selectionDelegate = ((BookmarkManager) bookmarkDelegate).f1934l;
        SelectionDelegate<BookmarkId> selectionDelegate2 = this.f1973y;
        if (selectionDelegate2 != selectionDelegate) {
            if (selectionDelegate2 != null) {
                selectionDelegate2.e.mo7869b(this);
            }
            this.f1973y = selectionDelegate;
            this.f1973y.e.mo7868a(this);
        }
        this.f1969p = bookmarkDelegate;
        if (this.f1972x) {
            ((BookmarkManager) this.f1969p).f1927e.mo7868a(this);
        }
    }

    /* renamed from: a */
    public void mo8707a(BookmarkId bookmarkId) {
    }

    /* renamed from: b */
    public BookmarkBridge.BookmarkItem mo8622b(BookmarkId bookmarkId) {
        this.f1970q = bookmarkId;
        BookmarkBridge.BookmarkItem d = ((BookmarkManager) this.f1969p).f1925c.d(bookmarkId);
        if (mo8623b()) {
            setChecked(((BookmarkManager) this.f1969p).f1934l.d.contains(bookmarkId));
        }
        return d;
    }

    /* renamed from: b */
    public boolean mo8623b() {
        return true;
    }

    /* renamed from: c */
    public abstract void mo8624c();

    /* renamed from: d */
    public final void mo8709d() {
        SelectionDelegate<BookmarkId> selectionDelegate;
        if (!mo8623b() || (selectionDelegate = this.f1973y) == null) {
            SelectionDelegate<BookmarkId> selectionDelegate2 = this.f1973y;
            if (selectionDelegate2 == null || !selectionDelegate2.c) {
                this.f1968n.setVisibility(8);
                return;
            }
            this.f1966e.setVisibility(8);
            this.f1968n.setVisibility(8);
            return;
        }
        if (selectionDelegate.c) {
            this.f1968n.setVisibility(0);
            this.f1966e.setVisibility(8);
        } else {
            this.f1968n.setVisibility(8);
        }
        setChecked(this.f1973y.a(this.f1970q));
    }

    public boolean isChecked() {
        return mo8623b() && this.f1973y.c && this.f1968n.isChecked();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mo8709d();
        this.f1972x = true;
        BookmarkDelegate bookmarkDelegate = this.f1969p;
        if (bookmarkDelegate != null) {
            ((BookmarkManager) bookmarkDelegate).f1927e.mo7868a(this);
        }
    }

    /* JADX WARNING: type inference failed for: r1v8, types: [org.chromium.chrome.browser.ChromeActivity, android.app.Activity] */
    public void onClick(View view) {
        if (view.getId() == ox0.bookmark_row) {
            if (!this.f1973y.c) {
                mo8624c();
            } else if (mo8623b()) {
                this.f1973y.b(this.f1970q);
            }
        } else if (view.getId() == ox0.favorite_options) {
            ss0.a("EditFavoritesOptions", view);
            boolean z = false;
            ss0.a("Hub", "Favorites", (String) null, TelemetryConstants.Actions.Click, "EditFavoritesOptions", new String[0]);
            ss0.a("Hub", "BookmarkPopMenu", (String) null, new String[0]);
            if (this.f1971q3 == null) {
                this.f1971q3 = new PopupMenu(getContext(), this.f1967k);
                this.f1971q3.a().inflate(sx0.hub_favorite_popup_menu, this.f1971q3.b);
                if (Build.VERSION.SDK_INT >= 23) {
                    this.f1971q3.c.g = 8388693;
                }
                this.f1971q3.d = new DW1(this);
            }
            BookmarkBridge.BookmarkItem d = ((BookmarkManager) this.f1969p).f1925c.d(this.f1970q);
            if (cg0.a.c(this.f1970q) || cg0.a.b(this.f1970q)) {
                this.f1971q3.b.findItem(ox0.cannot_edit_item).setVisible(true);
                this.f1971q3.b.findItem(ox0.cannot_edit_item).setTitle(getResources().getString(wx0.cannot_edit_managed_items));
                this.f1971q3.b.setGroupVisible(ox0.edit_delete_group, false);
            } else if (d == null || d.f()) {
                this.f1971q3.b.findItem(ox0.cannot_edit_item).setVisible(false);
                this.f1971q3.b.setGroupVisible(ox0.edit_delete_group, true);
            } else {
                this.f1971q3.b.findItem(ox0.cannot_edit_item).setVisible(true);
                this.f1971q3.b.findItem(ox0.cannot_edit_item).setTitle(getResources().getString(wx0.cannot_edit_special_folder));
                this.f1971q3.b.setGroupVisible(ox0.edit_delete_group, false);
            }
            ? a = ME2.a(getContext());
            if (a != 0) {
                MenuItem findItem = this.f1971q3.b.findItem(ox0.contextmenu_open_in_other_window);
                if (gb2.c.e(a) && !d.g()) {
                    z = true;
                }
                findItem.setVisible(z);
            }
            this.f1971q3.c.e();
        } else if (view.getId() == ox0.check_box && this.f1973y.c && mo8623b()) {
            this.f1973y.b(this.f1970q);
        }
    }

    public void onDestroy() {
        BookmarkDelegate bookmarkDelegate = this.f1969p;
        if (bookmarkDelegate != null) {
            ((BookmarkManager) bookmarkDelegate).f1927e.mo7869b(this);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChecked(false);
        this.f1972x = false;
        BookmarkDelegate bookmarkDelegate = this.f1969p;
        if (bookmarkDelegate != null) {
            ((BookmarkManager) bookmarkDelegate).f1927e.mo7869b(this);
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.f1962a = (LinearLayout) findViewById(ox0.bookmark_row);
        this.f1963b = (ImageView) findViewById(ox0.bookmark_image);
        this.f1964c = (TextView) findViewById(ox0.title);
        this.f1965d = (TextView) findViewById(ox0.domain);
        this.f1966e = (ImageView) findViewById(ox0.open_folder_view);
        this.f1966e.getDrawable().setAutoMirrored(true);
        this.f1967k = findViewById(ox0.favorite_options);
        this.f1967k.setVisibility(0);
        this.f1968n = (CheckBox) findViewById(ox0.check_box);
        this.f1968n.setContentDescription(",");
        this.f1962a.setOnClickListener(this);
        this.f1968n.setClickable(true);
        this.f1968n.setOnClickListener(this);
        this.f1967k.setOnClickListener(this);
    }

    public void onSelectionStateChange(List<BookmarkId> list) {
        mo8709d();
    }

    public void setChecked(boolean z) {
        if (mo8623b()) {
            this.f1968n.setChecked(z);
        }
    }

    public void toggle() {
        setChecked(!isChecked());
    }

    /* JADX WARNING: type inference failed for: r0v11, types: [org.chromium.chrome.browser.ChromeActivity, android.app.Activity] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f2  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ boolean mo8708a(android.view.MenuItem r11) {
        /*
            r10 = this;
            int r11 = r11.getItemId()
            int r0 = ox0.edit_item
            r1 = 0
            r2 = 0
            r3 = 1
            if (r11 != r0) goto L_0x0042
            org.chromium.chrome.browser.favorites.BookmarkDelegate r11 = r10.f1969p
            if (r11 == 0) goto L_0x003d
            r0 = r11
            org.chromium.chrome.browser.favorites.BookmarkManager r0 = (org.chromium.chrome.browser.favorites.BookmarkManager) r0
            org.chromium.chrome.browser.bookmarks.BookmarkModel r0 = r0.f1925c
            if (r0 == 0) goto L_0x003d
            org.chromium.chrome.browser.favorites.BookmarkManager r11 = (org.chromium.chrome.browser.favorites.BookmarkManager) r11
            org.chromium.chrome.browser.bookmarks.BookmarkModel r11 = r11.f1925c
            org.chromium.components.bookmarks.BookmarkId r0 = r10.f1970q
            org.chromium.chrome.browser.bookmarks.BookmarkBridge$BookmarkItem r11 = r11.d(r0)
            boolean r0 = r11.g()
            if (r0 == 0) goto L_0x0032
            android.content.Context r0 = r10.getContext()
            org.chromium.components.bookmarks.BookmarkId r11 = r11.a()
            org.chromium.chrome.browser.favorites.BookmarkAddEditFolderActivity.m2297a((android.content.Context) r0, (org.chromium.components.bookmarks.BookmarkId) r11)
            goto L_0x003d
        L_0x0032:
            android.content.Context r0 = r10.getContext()
            org.chromium.components.bookmarks.BookmarkId r11 = r11.a()
            LW1.a(r0, r11)
        L_0x003d:
            java.lang.String r11 = "EditItem"
        L_0x003f:
            r8 = r11
            goto L_0x00f0
        L_0x0042:
            int r0 = ox0.delete_item
            if (r11 != r0) goto L_0x0072
            org.chromium.chrome.browser.favorites.BookmarkDelegate r11 = r10.f1969p
            if (r11 == 0) goto L_0x006f
            r0 = r11
            org.chromium.chrome.browser.favorites.BookmarkManager r0 = (org.chromium.chrome.browser.favorites.BookmarkManager) r0
            org.chromium.chrome.browser.bookmarks.BookmarkModel r0 = r0.f1925c
            if (r0 == 0) goto L_0x006f
            org.chromium.chrome.browser.favorites.BookmarkManager r11 = (org.chromium.chrome.browser.favorites.BookmarkManager) r11
            org.chromium.chrome.browser.bookmarks.BookmarkModel r11 = r11.f1925c
            org.chromium.components.bookmarks.BookmarkId[] r0 = new org.chromium.components.bookmarks.BookmarkId[r3]
            org.chromium.components.bookmarks.BookmarkId r4 = r10.f1970q
            r0[r2] = r4
            r11.a(r0)
            android.widget.LinearLayout r11 = r10.f1962a
            if (r11 == 0) goto L_0x006f
            android.content.res.Resources r0 = r10.getResources()
            int r4 = wx0.accessibility_favorite_deleted
            java.lang.String r0 = r0.getString(r4)
            r11.announceForAccessibility(r0)
        L_0x006f:
            java.lang.String r11 = "DeleteItem"
            goto L_0x003f
        L_0x0072:
            int r0 = ox0.select_item
            if (r11 != r0) goto L_0x00a5
            org.chromium.chrome.browser.widget.selection.SelectionDelegate<org.chromium.components.bookmarks.BookmarkId> r11 = r10.f1973y
            r11.a(r3)
            org.chromium.chrome.browser.widget.selection.SelectionDelegate<org.chromium.components.bookmarks.BookmarkId> r11 = r10.f1973y
            org.chromium.components.bookmarks.BookmarkId r0 = r10.f1970q
            r11.b(r0)
            android.widget.LinearLayout r11 = r10.f1962a
            if (r11 == 0) goto L_0x009b
            android.content.res.Resources r0 = r10.getResources()
            int r4 = ux0.favorites_item_selected
            java.lang.Object[] r5 = new java.lang.Object[r3]
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)
            r5[r2] = r6
            java.lang.String r0 = r0.getQuantityString(r4, r3, r5)
            r11.announceForAccessibility(r0)
        L_0x009b:
            java.lang.String r11 = "MultiSelectFavorites"
            java.lang.String r0 = "favorite_select"
            ss0.a(r11, r0)
            java.lang.String r11 = "SelectItem"
            goto L_0x003f
        L_0x00a5:
            int r0 = ox0.contextmenu_open_in_other_window
            if (r11 != r0) goto L_0x00ef
            org.chromium.chrome.browser.favorites.BookmarkDelegate r11 = r10.f1969p
            if (r11 == 0) goto L_0x00eb
            r0 = r11
            org.chromium.chrome.browser.favorites.BookmarkManager r0 = (org.chromium.chrome.browser.favorites.BookmarkManager) r0
            org.chromium.chrome.browser.bookmarks.BookmarkModel r0 = r0.f1925c
            if (r0 == 0) goto L_0x00eb
            org.chromium.chrome.browser.favorites.BookmarkManager r11 = (org.chromium.chrome.browser.favorites.BookmarkManager) r11
            org.chromium.chrome.browser.bookmarks.BookmarkModel r11 = r11.f1925c
            org.chromium.components.bookmarks.BookmarkId r0 = r10.f1970q
            org.chromium.chrome.browser.bookmarks.BookmarkBridge$BookmarkItem r11 = r11.d(r0)
            boolean r0 = r11.g()
            if (r0 != 0) goto L_0x00eb
            java.lang.String r11 = r11.e()
            android.content.Context r0 = r10.getContext()
            org.chromium.chrome.browser.ChromeActivity r0 = ME2.a(r0)
            if (r0 == 0) goto L_0x00eb
            org.chromium.chrome.browser.tab.Tab r4 = r0.mo8172v0()
            Nx2 r5 = new Nx2
            boolean r6 = r4.mo9315X()
            r5.<init>(r6)
            org.chromium.content_public.browser.LoadUrlParams r6 = new org.chromium.content_public.browser.LoadUrlParams
            r6.<init>(r11, r2)
            int r11 = r4.mo9407x()
            r5.a(r6, r0, r11)
        L_0x00eb:
            java.lang.String r11 = "OpenInNewWindow"
            goto L_0x003f
        L_0x00ef:
            r8 = r1
        L_0x00f0:
            if (r8 == 0) goto L_0x00fe
            r6 = 0
            com.microsoft.ruby.telemetry.TelemetryConstants$Actions r7 = com.microsoft.ruby.telemetry.TelemetryConstants.Actions.Click
            java.lang.String[] r9 = new java.lang.String[r2]
            java.lang.String r4 = "Hub"
            java.lang.String r5 = "BookmarkPopMenu"
            ss0.a(r4, r5, r6, r7, r8, r9)
        L_0x00fe:
            java.lang.String[] r11 = new java.lang.String[r2]
            java.lang.String r0 = "Hub"
            java.lang.String r2 = "BookmarkPopMenu"
            ss0.b(r0, r2, r1, r11)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.favorites.BookmarkSelectableRow.mo8708a(android.view.MenuItem):boolean");
    }
}
