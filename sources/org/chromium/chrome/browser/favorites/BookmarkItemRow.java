package org.chromium.chrome.browser.favorites;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.favicon.LargeIconBridge;
import org.chromium.chrome.browser.hub.HubUIManager;
import org.chromium.components.bookmarks.BookmarkId;

/* compiled from: PG */
public class BookmarkItemRow extends BookmarkSelectableRow implements LargeIconBridge.LargeIconCallback {

    /* renamed from: r3 */
    public String f1917r3;

    /* renamed from: s3 */
    public rH2 f1918s3;

    /* renamed from: t3 */
    public final int f1919t3 = Math.min(this.f1920u3, 48);

    /* renamed from: u3 */
    public final int f1920u3 = getResources().getDimensionPixelSize(kx0.hub_favorite_favicon_size);

    /* renamed from: v3 */
    public final int f1921v3 = getResources().getDimensionPixelSize(kx0.hub_favorite_favicon_corner_radius);

    /* renamed from: w3 */
    public boolean f1922w3;

    static {
        Class<BookmarkItemRow> cls = BookmarkItemRow.class;
    }

    public BookmarkItemRow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int dimensionPixelSize = getResources().getDimensionPixelSize(kx0.hub_favorite_icon_text_size);
        int a = ON0.a(getResources(), jx0.default_favicon_background_color);
        int i = this.f1920u3;
        this.f1918s3 = new rH2(i, i, this.f1921v3, a, (float) dimensionPixelSize);
    }

    /* renamed from: a */
    public void mo8647a() {
    }

    /* renamed from: b */
    public BookmarkBridge.BookmarkItem mo8622b(BookmarkId bookmarkId) {
        BookmarkBridge.BookmarkItem b = super.mo8622b(bookmarkId);
        this.f1917r3 = b.e();
        this.f1963b.setImageDrawable((Drawable) null);
        this.f1964c.setText(b.d());
        this.f1965d.setText(HubUIManager.a(this.f1917r3));
        ((BookmarkManager) this.f1969p).f1936n.a(this.f1917r3, this.f1919t3, this);
        this.f1922w3 = cg0.a.b(bookmarkId);
        return b;
    }

    /* renamed from: c */
    public void mo8624c() {
        ss0.a("HubClick", "favorite_item");
        int d = ((BookmarkManager) this.f1969p).mo8657d();
        int i = 2;
        if (d == 1 || d != 2) {
            i = -1;
        }
        ((BookmarkManager) this.f1969p).mo8654a(this.f1970q, i);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.f1965d.setVisibility(0);
        this.f1966e.setVisibility(8);
    }

    public void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        if (bitmap == null) {
            this.f1918s3.e.setColor(i);
            this.f1963b.setImageDrawable(new BitmapDrawable(getResources(), this.f1918s3.b(this.f1917r3)));
            return;
        }
        Resources resources = getResources();
        int i3 = this.f1920u3;
        D6 a = F6.a(resources, Bitmap.createScaledBitmap(bitmap, i3, i3, false));
        a.a((float) this.f1921v3);
        this.f1963b.setImageDrawable(a);
    }

    public void setChecked(boolean z) {
        if (mo8623b()) {
            this.f1968n.setChecked(z);
        }
    }

    /* renamed from: b */
    public boolean mo8623b() {
        return !this.f1922w3;
    }
}
