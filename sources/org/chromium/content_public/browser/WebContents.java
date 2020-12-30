package org.chromium.content_public.browser;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Parcelable;
import org.chromium.content_public.browser.ViewEventSink;
import org.chromium.p012ui.base.EventForwarder;
import org.chromium.p012ui.base.WindowAndroid;
import org.chromium.ui.OverscrollRefreshHandler;
import org.chromium.ui.base.ViewAndroidDelegate;

/* compiled from: PG */
public interface WebContents extends Parcelable {
    /* renamed from: A0 */
    String mo9739A0();

    /* renamed from: D */
    void mo9740D();

    /* renamed from: E */
    String mo9741E();

    /* renamed from: F */
    NavigationController mo9742F();

    /* renamed from: G */
    cQ2 mo9743G();

    /* renamed from: H */
    String mo9744H();

    /* renamed from: I */
    void mo9745I();

    /* renamed from: J */
    void mo9746J();

    /* renamed from: J1 */
    boolean mo9747J1();

    /* renamed from: K */
    RenderFrameHost mo9748K();

    /* renamed from: M */
    boolean mo9749M();

    /* renamed from: M1 */
    void mo9750M1();

    /* renamed from: N0 */
    Rect mo9751N0();

    /* renamed from: T1 */
    void mo9752T1();

    /* renamed from: U0 */
    ViewAndroidDelegate mo9753U0();

    /* renamed from: Y */
    void mo9754Y();

    /* renamed from: a */
    int mo9755a(String str, boolean z, int i, boolean z2, ImageDownloadCallback imageDownloadCallback);

    /* renamed from: a */
    void mo9756a(int i);

    /* renamed from: a */
    void mo9757a(int i, int i2, int i3, int i4);

    /* renamed from: a */
    void mo9758a(int i, int i2, boolean z);

    /* renamed from: a */
    void mo9759a(Rect rect);

    /* renamed from: a */
    void mo9760a(String str, String str2, String str3, MessagePort[] messagePortArr);

    /* renamed from: a */
    void mo9761a(String str, JavaScriptCallback javaScriptCallback);

    /* renamed from: a */
    void mo9762a(String str, ViewAndroidDelegate viewAndroidDelegate, ViewEventSink.InternalAccessDelegate internalAccessDelegate, WindowAndroid windowAndroid, InternalsHolder internalsHolder);

    /* renamed from: a */
    void mo9763a(lQ2 lq2);

    /* renamed from: a */
    void mo9764a(OverscrollRefreshHandler overscrollRefreshHandler);

    /* renamed from: a0 */
    boolean mo9765a0();

    /* renamed from: b */
    void mo9766b(int i, int i2);

    /* renamed from: b */
    void mo9767b(int i, String str);

    /* renamed from: b */
    void mo9768b(String str, JavaScriptCallback javaScriptCallback);

    /* renamed from: b */
    void mo9769b(lQ2 lq2);

    /* renamed from: b */
    void mo9770b(WindowAndroid windowAndroid);

    void destroy();

    /* renamed from: e */
    void mo9772e(boolean z);

    /* renamed from: e1 */
    boolean mo9773e1();

    int getHeight();

    String getTitle();

    int getWidth();

    /* renamed from: h0 */
    int mo9777h0();

    /* renamed from: h1 */
    EventForwarder mo9778h1();

    boolean isDestroyed();

    boolean isIncognito();

    /* renamed from: j0 */
    boolean mo9781j0();

    /* renamed from: o0 */
    WindowAndroid mo9782o0();

    /* renamed from: q0 */
    void mo9783q0();

    void setSmartClipResultHandler(Handler handler);

    void stop();

    /* renamed from: v */
    void mo9786v(boolean z);

    /* renamed from: w0 */
    boolean mo9787w0();

    /* renamed from: y0 */
    void mo9788y0();

    /* renamed from: z0 */
    MessagePort[] mo9789z0();
}
