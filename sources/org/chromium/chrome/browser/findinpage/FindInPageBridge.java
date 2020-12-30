package org.chromium.chrome.browser.findinpage;

import org.chromium.content_public.browser.WebContents;

/* compiled from: PG */
public class FindInPageBridge {

    /* renamed from: a */
    public long f2019a;

    public FindInPageBridge(WebContents webContents) {
        this.f2019a = nativeInit(webContents);
    }

    private native void nativeActivateFindInPageResultForAccessibility(long j);

    private native void nativeActivateNearestFindResult(long j, float f, float f2);

    private native void nativeDestroy(long j);

    private native String nativeGetPreviousFindText(long j);

    private native long nativeInit(WebContents webContents);

    private native void nativeRequestFindMatchRects(long j, int i);

    private native void nativeStartFinding(long j, String str, boolean z, boolean z2);

    private native void nativeStopFinding(long j, boolean z);

    /* renamed from: a */
    public void mo8761a(String str, boolean z, boolean z2) {
        nativeStartFinding(this.f2019a, str, z, z2);
    }

    /* renamed from: b */
    public void mo8763b() {
        nativeDestroy(this.f2019a);
        this.f2019a = 0;
    }

    /* renamed from: c */
    public String mo8764c() {
        return nativeGetPreviousFindText(this.f2019a);
    }

    /* renamed from: a */
    public void mo8758a() {
        nativeActivateFindInPageResultForAccessibility(this.f2019a);
    }

    /* renamed from: a */
    public void mo8762a(boolean z) {
        nativeStopFinding(this.f2019a, z);
    }

    /* renamed from: a */
    public void mo8760a(int i) {
        nativeRequestFindMatchRects(this.f2019a, i);
    }

    /* renamed from: a */
    public void mo8759a(float f, float f2) {
        nativeActivateNearestFindResult(this.f2019a, f, f2);
    }
}
