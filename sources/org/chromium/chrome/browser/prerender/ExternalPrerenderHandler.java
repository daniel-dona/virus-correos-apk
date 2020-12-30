package org.chromium.chrome.browser.prerender;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.WindowManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content_public.browser.WebContents;

/* compiled from: PG */
public class ExternalPrerenderHandler {
    /* renamed from: a */
    public static Rect m2910a(Context context, boolean z) {
        Rect rect = new Rect();
        Point point = new Point();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getSize(point);
        Resources resources = context.getResources();
        try {
            point.y -= resources.getDimensionPixelSize(gs1.b(resources, "status_bar_height", "dimen", "android"));
        } catch (Resources.NotFoundException unused) {
        }
        rect.set(0, resources.getDimensionPixelSize(kx0.custom_tabs_control_container_height), point.x, point.y);
        if (z) {
            float f = resources.getDisplayMetrics().density;
            rect.top = (int) Math.ceil((double) (((float) rect.top) / f));
            rect.left = (int) Math.ceil((double) (((float) rect.left) / f));
            rect.right = (int) Math.ceil((double) (((float) rect.right) / f));
            rect.bottom = (int) Math.ceil((double) (((float) rect.bottom) / f));
        }
        return rect;
    }

    public static native WebContents nativeAddPrerender(long j, Profile profile, WebContents webContents, String str, String str2, int i, int i2, int i3, int i4, boolean z);

    public static native void nativeCancelCurrentPrerender(long j);

    public static native void nativeClearPrefetchInformationForTesting(Profile profile);

    public static native boolean nativeHasPrerenderedUrl(Profile profile, String str, WebContents webContents);

    public static native boolean nativeHasRecentlyPrefetchedUrlForTesting(Profile profile, String str);

    public static native long nativeInit();
}
