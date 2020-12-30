package org.chromium.chrome.browser.favorites;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.microsoft.intune.mam.client.widget.MAMAppCompatTextView;

@SuppressLint({"Instantiatable"})
/* compiled from: PG */
public class BookmarkDrawerListItemView extends MAMAppCompatTextView {
    public BookmarkDrawerListItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.widget.TextView, org.chromium.chrome.browser.favorites.BookmarkDrawerListItemView] */
    /* renamed from: a */
    public void mo8593a(int i) {
        if (i == 0) {
            setCompoundDrawablePadding(0);
        } else {
            setCompoundDrawablePadding(getResources().getDimensionPixelSize(kx0.bookmark_drawer_drawable_padding));
        }
        setCompoundDrawablesRelativeWithIntrinsicBounds(HH2.a(getContext(), i), (Drawable) null, (Drawable) null, (Drawable) null);
    }
}
