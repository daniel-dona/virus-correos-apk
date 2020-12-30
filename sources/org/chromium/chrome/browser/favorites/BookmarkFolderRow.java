package org.chromium.chrome.browser.favorites;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.microsoft.ruby.telemetry.TelemetryConstants;
import com.microsoft.theme.ThemeManager;
import java.util.List;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.widget.selection.SelectionDelegate;
import org.chromium.components.bookmarks.BookmarkId;

/* compiled from: PG */
public class BookmarkFolderRow extends BookmarkSelectableRow implements EW1, SelectionDelegate.SelectionObserver<BookmarkId>, ThemeManager.OnThemeChangedListener {

    /* renamed from: r3 */
    public boolean f1894r3;

    /* renamed from: s3 */
    public SelectionDelegate<BookmarkId> f1895s3;

    public BookmarkFolderRow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: a */
    public void mo8620a() {
    }

    /* renamed from: a */
    public void mo8621a(BookmarkDelegate bookmarkDelegate) {
        SelectionDelegate<BookmarkId> selectionDelegate = ((BookmarkManager) bookmarkDelegate).f1934l;
        SelectionDelegate<BookmarkId> selectionDelegate2 = this.f1895s3;
        if (selectionDelegate2 != selectionDelegate) {
            if (selectionDelegate2 != null) {
                selectionDelegate2.e.mo7869b(this);
            }
            this.f1895s3 = selectionDelegate;
            this.f1895s3.e.mo7868a(this);
        }
        this.f1969p = bookmarkDelegate;
        super.mo8621a(bookmarkDelegate);
    }

    /* renamed from: b */
    public BookmarkBridge.BookmarkItem mo8622b(BookmarkId bookmarkId) {
        BookmarkBridge.BookmarkItem b = super.mo8622b(bookmarkId);
        String d = b.d();
        int h = ((BookmarkManager) this.f1969p).f1925c.h(bookmarkId);
        Resources resources = getResources();
        int i = wx0.folder_name;
        Integer valueOf = Integer.valueOf(h);
        boolean z = true;
        this.f1964c.setText(resources.getString(i, new Object[]{d, valueOf}));
        BookmarkBridge.BookmarkItem d2 = ((BookmarkManager) this.f1969p).f1925c.d(this.f1970q);
        if (cg0.a.c(bookmarkId) || !d2.f()) {
            z = false;
        }
        this.f1894r3 = z;
        return b;
    }

    /* renamed from: c */
    public void mo8624c() {
        ss0.a("HubClick", "favorite_folder");
        ss0.a("Hub", "Favorites", (String) null, TelemetryConstants.Actions.Click, "FavoriteFolder", new String[0]);
        ((BookmarkManager) this.f1969p).mo8653a(this.f1970q);
    }

    public void onDestroy() {
        ThemeManager.f1300h.f1306f.mo7869b(this);
        super.onDestroy();
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.f1963b.setImageDrawable(HH2.a(getContext(), lx0.favorite_hub_folder));
        ThemeManager.f1300h.mo4502a(this.f1963b, 16843033, lx0.favorite_hub_folder);
        u6.a(this.f1963b.getDrawable(), du0.b(getResources(), jx0.hub_new_primary_icon_color));
        this.f1964c.setTextColor(du0.a(getResources(), jx0.hub_new_folder_title));
        ON0.a(this.f1964c, xx0.Body1);
        ViewGroup.LayoutParams layoutParams = this.f1962a.getLayoutParams();
        layoutParams.height = getResources().getDimensionPixelSize(kx0.hub_favorite_folder_height);
        this.f1962a.setLayoutParams(layoutParams);
        ThemeManager.f1300h.f1306f.mo7868a(this);
    }

    public void onSelectionStateChange(List<BookmarkId> list) {
        super.onSelectionStateChange(list);
    }

    public void onThemeChanged() {
        u6.a(this.f1963b.getDrawable(), du0.b(getResources(), jx0.hub_new_primary_icon_color));
    }

    public void setChecked(boolean z) {
        if (mo8623b()) {
            this.f1968n.setChecked(z);
        }
    }

    /* renamed from: b */
    public boolean mo8623b() {
        return this.f1894r3;
    }
}
