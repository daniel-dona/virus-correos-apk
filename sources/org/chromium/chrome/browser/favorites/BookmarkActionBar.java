package org.chromium.chrome.browser.favorites;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.preferences.PrefServiceBridge;
import org.chromium.chrome.browser.widget.selection.SelectableListToolbar;
import org.chromium.components.bookmarks.BookmarkId;

/* compiled from: PG */
public class BookmarkActionBar extends SelectableListToolbar<BookmarkId> implements EW1, Toolbar.OnMenuItemClickListener, View.OnClickListener {

    /* renamed from: F4 */
    public BookmarkBridge.BookmarkItem f1801F4;

    /* renamed from: G4 */
    public BookmarkDelegate f1802G4;

    /* renamed from: H4 */
    public BookmarkBridge.b f1803H4 = new C0386a();

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkActionBar$a */
    /* compiled from: PG */
    public class C0386a extends BookmarkBridge.b {
        public C0386a() {
        }

        /* renamed from: a */
        public void mo8534a() {
            BookmarkActionBar bookmarkActionBar = BookmarkActionBar.this;
            bookmarkActionBar.onSelectionStateChange(((BookmarkManager) bookmarkActionBar.f1802G4).f1934l.b());
        }
    }

    static {
        Class<BookmarkActionBar> cls = BookmarkActionBar.class;
    }

    public BookmarkActionBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setNavigationOnClickListener(this);
        b(sx0.bookmark_action_bar_menu);
        setOnMenuItemClickListener(this);
        q().findItem(ox0.search_menu_id).setTitle(wx0.bookmark_action_bar_search);
        q().findItem(ox0.selection_mode_edit_menu_id).setTitle(wx0.edit_bookmark);
        q().findItem(ox0.selection_mode_move_menu_id).setTitle(wx0.bookmark_action_bar_move);
        q().findItem(ox0.selection_mode_delete_menu_id).setTitle(wx0.bookmark_action_bar_delete);
    }

    /* renamed from: L */
    public void mo8528L() {
        this.f1801F4.b();
        throw null;
    }

    /* renamed from: a */
    public void mo8529a() {
    }

    /* renamed from: a */
    public void mo8530a(BookmarkId bookmarkId) {
        throw null;
    }

    public void onDestroy() {
        throw null;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        throw null;
    }

    public void onSelectionStateChange(List<BookmarkId> list) {
        BookmarkActionBar.super.onSelectionStateChange(list);
        if (this.U3) {
            q().findItem(ox0.selection_mode_edit_menu_id).setVisible(list.size() == 1);
            q().findItem(ox0.selection_open_in_incognito_tab_id).setVisible(PrefServiceBridge.m2758o0().mo9051K());
            Iterator<BookmarkId> it = list.iterator();
            if (!it.hasNext()) {
                for (BookmarkId type : list) {
                    if (type.getType() == 1) {
                        q().findItem(ox0.selection_mode_move_menu_id).setVisible(false);
                        return;
                    }
                }
                return;
            }
            BookmarkId next = it.next();
            throw null;
        }
        throw null;
    }
}
