package org.chromium.chrome.browser.favorites;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.microsoft.ruby.telemetry.TelemetryConstants;
import com.microsoft.theme.ThemeManager;
import java.util.List;
import org.chromium.chrome.browser.ChromeActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.favicon.LargeIconBridge;
import org.chromium.chrome.browser.hub.HubUIManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.bookmarks.BookmarkId;

/* compiled from: PG */
public class BookmarkAddPageRow extends FrameLayout implements EW1, LargeIconBridge.LargeIconCallback, View.OnClickListener {

    /* renamed from: a */
    public String f1833a;

    /* renamed from: b */
    public rH2 f1834b;

    /* renamed from: c */
    public final int f1835c = Math.min(this.f1836d, 48);

    /* renamed from: d */
    public final int f1836d = getResources().getDimensionPixelSize(kx0.hub_favorite_favicon_size);

    /* renamed from: e */
    public final int f1837e = getResources().getDimensionPixelSize(kx0.hub_favorite_favicon_corner_radius);

    /* renamed from: k */
    public BookmarkDelegate f1838k;

    /* renamed from: n */
    public Tab f1839n;

    /* renamed from: p */
    public LinearLayout f1840p;

    /* renamed from: q */
    public ImageView f1841q;

    /* renamed from: q3 */
    public ImageView f1842q3;

    /* renamed from: x */
    public TextView f1843x;

    /* renamed from: y */
    public TextView f1844y;

    public BookmarkAddPageRow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int dimensionPixelSize = getResources().getDimensionPixelSize(kx0.hub_favorite_icon_text_size);
        int a = ON0.a(getResources(), jx0.default_favicon_background_color);
        int i = this.f1836d;
        this.f1834b = new rH2(i, i, this.f1837e, a, (float) dimensionPixelSize);
    }

    /* renamed from: a */
    public void mo8559a() {
    }

    /* renamed from: a */
    public void mo8560a(BookmarkDelegate bookmarkDelegate) {
        this.f1838k = bookmarkDelegate;
    }

    /* renamed from: a */
    public void mo8561a(BookmarkId bookmarkId) {
    }

    public void onClick(View view) {
        if (view == this.f1840p) {
            ss0.a("Hub", "Favorites", (String) null, TelemetryConstants.Actions.Click, "AddBookmark", new String[0]);
            Tab tab = this.f1839n;
            if (LW1.a(tab) && (getContext() instanceof ChromeActivity)) {
                long H = tab.mo9299H();
                BookmarkModel bookmarkModel = new BookmarkModel();
                bookmarkModel.a(new eW1(this, H, bookmarkModel, tab, (ChromeActivity) getContext()));
            }
        }
    }

    public void onDestroy() {
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.f1840p = (LinearLayout) findViewById(ox0.bookmark_row);
        this.f1841q = (ImageView) findViewById(ox0.bookmark_image);
        this.f1843x = (TextView) findViewById(ox0.title);
        this.f1844y = (TextView) findViewById(ox0.domain);
        this.f1842q3 = (ImageView) findViewById(ox0.open_folder_view);
        this.f1844y.setVisibility(0);
        this.f1842q3.setImageResource(lx0.favorite_add_page);
        ThemeManager.f1300h.mo4502a(this.f1842q3, 16843033, lx0.favorite_add_page);
        u6.a(this.f1842q3.getDrawable(), du0.b(getResources(), jx0.hub_new_primary_icon_color));
        this.f1842q3.setVisibility(0);
        this.f1840p.setOnClickListener(this);
    }

    public void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        if (bitmap == null) {
            this.f1834b.e.setColor(i);
            this.f1841q.setImageDrawable(new BitmapDrawable(getResources(), this.f1834b.b(pr2.d(this.f1833a))));
            return;
        }
        Resources resources = getResources();
        int i3 = this.f1836d;
        D6 a = F6.a(resources, Bitmap.createScaledBitmap(bitmap, i3, i3, false));
        a.a((float) this.f1837e);
        this.f1841q.setImageDrawable(a);
    }

    public void onSelectionStateChange(List<BookmarkId> list) {
    }

    public void setChromeActivityTab(Tab tab) {
        this.f1839n = tab;
        this.f1833a = pr2.a(tab.getUrl());
        this.f1841q.setImageDrawable((Drawable) null);
        this.f1843x.setText(getContext().getString(wx0.favorites_add_page));
        this.f1844y.setText(HubUIManager.a(this.f1833a));
        ((BookmarkManager) this.f1838k).f1936n.a(pr2.d(this.f1833a), this.f1835c, this);
    }
}
