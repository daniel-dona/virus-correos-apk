package org.chromium.chrome.browser.tab;

import android.util.Pair;
import com.citrix.loggersdk.BuildConfig;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;

/* compiled from: PG */
public class TabState {

    /* renamed from: a */
    public a f2265a;

    /* renamed from: b */
    public int f2266b = -1;

    /* renamed from: c */
    public int f2267c;

    /* renamed from: d */
    public long f2268d = -1;

    /* renamed from: e */
    public String f2269e;

    /* renamed from: f */
    public int f2270f = 0;

    /* renamed from: g */
    public Integer f2271g;

    /* renamed from: h */
    public boolean f2272h;

    /* JADX WARNING: Can't wrap try/catch for region: R(30:0|(1:5)(1:4)|(1:7)|(4:9|10|11|(2:13|14))|(8:15|16|17|(2:19|20)(2:21|(1:23))|24|25|26|(1:28))|33|34|35|36|(1:38)(1:39)|40|41|42|43|44|49|50|51|52|53|54|55|56|(1:58)|61|62|63|64|65|66) */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r3 = QJ1.a.a(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        VN0.c("TabState", "Failed to read shouldPreserve flag from tab state. Assuming shouldPreserve is false", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        r3.f2271g = null;
        VN0.c("TabState", "Failed to read tab launch type at creation from tab state. Assuming tab launch type is null", new java.lang.Object[0]);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00cb */
    /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00f7 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:52:0x010b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x012a */
    /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x013a */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d5 A[Catch:{ EOFException -> 0x00bb, all -> 0x0147 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d7 A[Catch:{ EOFException -> 0x00bb, all -> 0x0147 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0127 A[Catch:{ EOFException -> 0x012a }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.chromium.chrome.browser.tab.TabState m3120a(java.io.FileInputStream r17, boolean r18) throws java.io.IOException {
        /*
            r0 = r17
            r1 = r18
            r2 = 0
            if (r1 == 0) goto L_0x001b
            org.chromium.chrome.browser.crypto.CipherFactory r3 = QJ1.a
            r4 = 2
            javax.crypto.Cipher r3 = r3.a(r4)
            if (r3 == 0) goto L_0x001b
            java.io.DataInputStream r4 = new java.io.DataInputStream
            javax.crypto.CipherInputStream r5 = new javax.crypto.CipherInputStream
            r5.<init>(r0, r3)
            r4.<init>(r5)
            goto L_0x001c
        L_0x001b:
            r4 = r2
        L_0x001c:
            if (r4 != 0) goto L_0x0023
            java.io.DataInputStream r4 = new java.io.DataInputStream
            r4.<init>(r0)
        L_0x0023:
            if (r1 == 0) goto L_0x0033
            long r5 = r4.readLong()     // Catch:{ all -> 0x0147 }
            r7 = 0
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x0033
            r4.close()
            return r2
        L_0x0033:
            org.chromium.chrome.browser.tab.TabState r3 = new org.chromium.chrome.browser.tab.TabState     // Catch:{ all -> 0x0147 }
            r3.<init>()     // Catch:{ all -> 0x0147 }
            long r5 = r4.readLong()     // Catch:{ all -> 0x0147 }
            r3.f2268d = r5     // Catch:{ all -> 0x0147 }
            int r5 = r4.readInt()     // Catch:{ all -> 0x0147 }
            java.lang.String r6 = "TabState"
            r7 = 0
            if (r1 == 0) goto L_0x005f
            byte[] r0 = new byte[r5]     // Catch:{ all -> 0x0147 }
            r4.readFully(r0)     // Catch:{ all -> 0x0147 }
            org.chromium.chrome.browser.tab.TabState$a r8 = new org.chromium.chrome.browser.tab.TabState$a     // Catch:{ all -> 0x0147 }
            java.nio.ByteBuffer r5 = java.nio.ByteBuffer.allocateDirect(r5)     // Catch:{ all -> 0x0147 }
            r8.<init>(r5)     // Catch:{ all -> 0x0147 }
            r3.f2265a = r8     // Catch:{ all -> 0x0147 }
            org.chromium.chrome.browser.tab.TabState$a r5 = r3.f2265a     // Catch:{ all -> 0x0147 }
            java.nio.ByteBuffer r5 = r5.a     // Catch:{ all -> 0x0147 }
            r5.put(r0)     // Catch:{ all -> 0x0147 }
            goto L_0x00a2
        L_0x005f:
            java.nio.channels.FileChannel r8 = r17.getChannel()     // Catch:{ all -> 0x0147 }
            org.chromium.chrome.browser.tab.TabState$a r14 = new org.chromium.chrome.browser.tab.TabState$a     // Catch:{ all -> 0x0147 }
            java.nio.channels.FileChannel$MapMode r9 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch:{ all -> 0x0147 }
            long r10 = r8.position()     // Catch:{ all -> 0x0147 }
            long r12 = (long) r5     // Catch:{ all -> 0x0147 }
            r15 = r12
            java.nio.MappedByteBuffer r8 = r8.map(r9, r10, r12)     // Catch:{ all -> 0x0147 }
            r14.<init>(r8)     // Catch:{ all -> 0x0147 }
            r3.f2265a = r14     // Catch:{ all -> 0x0147 }
            r8 = r15
            long r10 = r0.skip(r8)     // Catch:{ all -> 0x0147 }
            int r0 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r0 == 0) goto L_0x00a2
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0147 }
            r0.<init>()     // Catch:{ all -> 0x0147 }
            java.lang.String r8 = "Only skipped "
            r0.append(r8)     // Catch:{ all -> 0x0147 }
            r0.append(r10)     // Catch:{ all -> 0x0147 }
            java.lang.String r8 = " bytes when "
            r0.append(r8)     // Catch:{ all -> 0x0147 }
            r0.append(r5)     // Catch:{ all -> 0x0147 }
            java.lang.String r5 = " should've been skipped. Tab restore may fail."
            r0.append(r5)     // Catch:{ all -> 0x0147 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0147 }
            java.lang.Object[] r5 = new java.lang.Object[r7]     // Catch:{ all -> 0x0147 }
            VN0.a(r6, r0, r5)     // Catch:{ all -> 0x0147 }
        L_0x00a2:
            int r0 = r4.readInt()     // Catch:{ all -> 0x0147 }
            r3.f2266b = r0     // Catch:{ all -> 0x0147 }
            java.lang.String r0 = r4.readUTF()     // Catch:{ EOFException -> 0x00bb }
            r3.f2269e = r0     // Catch:{ EOFException -> 0x00bb }
            java.lang.String r0 = ""
            java.lang.String r5 = r3.f2269e     // Catch:{ EOFException -> 0x00bb }
            boolean r0 = r0.equals(r5)     // Catch:{ EOFException -> 0x00bb }
            if (r0 == 0) goto L_0x00c2
            r3.f2269e = r2     // Catch:{ EOFException -> 0x00bb }
            goto L_0x00c2
        L_0x00bb:
            java.lang.String r0 = "Failed to read opener app id state from tab state"
            java.lang.Object[] r5 = new java.lang.Object[r7]     // Catch:{ all -> 0x0147 }
            VN0.c(r6, r0, r5)     // Catch:{ all -> 0x0147 }
        L_0x00c2:
            org.chromium.chrome.browser.tab.TabState$a r0 = r3.f2265a     // Catch:{ EOFException -> 0x00cb }
            int r5 = r4.readInt()     // Catch:{ EOFException -> 0x00cb }
            r0.b = r5     // Catch:{ EOFException -> 0x00cb }
            goto L_0x00f4
        L_0x00cb:
            org.chromium.chrome.browser.tab.TabState$a r0 = r3.f2265a     // Catch:{ all -> 0x0147 }
            java.lang.String r5 = "stable"
            boolean r5 = r5.equals(r2)     // Catch:{ all -> 0x0147 }
            if (r5 == 0) goto L_0x00d7
            r5 = 0
            goto L_0x00d8
        L_0x00d7:
            r5 = 1
        L_0x00d8:
            r0.b = r5     // Catch:{ all -> 0x0147 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0147 }
            r0.<init>()     // Catch:{ all -> 0x0147 }
            java.lang.String r5 = "Failed to read saved state version id from tab state. Assuming version "
            r0.append(r5)     // Catch:{ all -> 0x0147 }
            org.chromium.chrome.browser.tab.TabState$a r5 = r3.f2265a     // Catch:{ all -> 0x0147 }
            int r5 = r5.b     // Catch:{ all -> 0x0147 }
            r0.append(r5)     // Catch:{ all -> 0x0147 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0147 }
            java.lang.Object[] r5 = new java.lang.Object[r7]     // Catch:{ all -> 0x0147 }
            VN0.c(r6, r0, r5)     // Catch:{ all -> 0x0147 }
        L_0x00f4:
            r4.readLong()     // Catch:{ EOFException -> 0x00f7 }
        L_0x00f7:
            r4.readBoolean()     // Catch:{ EOFException -> 0x00fb }
            goto L_0x0102
        L_0x00fb:
            java.lang.String r0 = "Failed to read shouldPreserve flag from tab state. Assuming shouldPreserve is false"
            java.lang.Object[] r5 = new java.lang.Object[r7]     // Catch:{ all -> 0x0147 }
            VN0.c(r6, r0, r5)     // Catch:{ all -> 0x0147 }
        L_0x0102:
            r3.f2272h = r1     // Catch:{ all -> 0x0147 }
            int r0 = r4.readInt()     // Catch:{ EOFException -> 0x010b }
            r3.f2270f = r0     // Catch:{ EOFException -> 0x010b }
            goto L_0x0114
        L_0x010b:
            r3.f2270f = r7     // Catch:{ all -> 0x0147 }
            java.lang.String r0 = "Failed to read theme color from tab state. Assuming theme color is TabState#UNSPECIFIED_THEME_COLOR"
            java.lang.Object[] r1 = new java.lang.Object[r7]     // Catch:{ all -> 0x0147 }
            VN0.c(r6, r0, r1)     // Catch:{ all -> 0x0147 }
        L_0x0114:
            r0 = -1
            int r1 = r4.readInt()     // Catch:{ EOFException -> 0x012a }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ EOFException -> 0x012a }
            r3.f2271g = r1     // Catch:{ EOFException -> 0x012a }
            java.lang.Integer r1 = r3.f2271g     // Catch:{ EOFException -> 0x012a }
            int r1 = r1.intValue()     // Catch:{ EOFException -> 0x012a }
            if (r1 != r0) goto L_0x0133
            r3.f2271g = r2     // Catch:{ EOFException -> 0x012a }
            goto L_0x0133
        L_0x012a:
            r3.f2271g = r2     // Catch:{ all -> 0x0147 }
            java.lang.String r1 = "Failed to read tab launch type at creation from tab state. Assuming tab launch type is null"
            java.lang.Object[] r2 = new java.lang.Object[r7]     // Catch:{ all -> 0x0147 }
            VN0.c(r6, r1, r2)     // Catch:{ all -> 0x0147 }
        L_0x0133:
            int r1 = r4.readInt()     // Catch:{ EOFException -> 0x013a }
            r3.f2267c = r1     // Catch:{ EOFException -> 0x013a }
            goto L_0x0143
        L_0x013a:
            r3.f2267c = r0     // Catch:{ all -> 0x0147 }
            java.lang.String r0 = "Failed to read tab root id from tab state. Assuming root id is Tab.INVALID_TAB_ID"
            java.lang.Object[] r1 = new java.lang.Object[r7]     // Catch:{ all -> 0x0147 }
            VN0.c(r6, r0, r1)     // Catch:{ all -> 0x0147 }
        L_0x0143:
            r4.close()
            return r3
        L_0x0147:
            r0 = move-exception
            r4.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.tab.TabState.m3120a(java.io.FileInputStream, boolean):org.chromium.chrome.browser.tab.TabState");
    }

    public static native void nativeCreateHistoricalTab(ByteBuffer byteBuffer, int i);

    public static native ByteBuffer nativeCreateSingleNavigationStateAsByteBuffer(String str, String str2, int i, String str3, boolean z);

    public static native ByteBuffer nativeDeleteNavigationEntries(ByteBuffer byteBuffer, int i, long j);

    public static native ByteBuffer nativeGetContentsStateAsByteBuffer(Tab tab);

    public static native String nativeGetDisplayTitleFromByteBuffer(ByteBuffer byteBuffer, int i);

    public static native String nativeGetVirtualUrlFromByteBuffer(ByteBuffer byteBuffer, int i);

    public static native WebContents nativeRestoreContentsFromByteBuffer(ByteBuffer byteBuffer, int i, boolean z);

    /* renamed from: b */
    public int mo9414b() {
        return this.f2270f;
    }

    /* renamed from: c */
    public String mo9415c() {
        a aVar = this.f2265a;
        return nativeGetVirtualUrlFromByteBuffer(aVar.a, aVar.b);
    }

    /* renamed from: d */
    public boolean mo9416d() {
        int i = this.f2270f;
        if (i != 0) {
            if (JE2.b(i) <= 0.94f) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: e */
    public boolean mo9417e() {
        return this.f2272h;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: org.chromium.chrome.browser.tab.TabState} */
    /* JADX WARNING: type inference failed for: r2v1 */
    /* JADX WARNING: type inference failed for: r2v2, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.chromium.chrome.browser.tab.TabState m3119a(java.io.File r5, int r6) {
        /*
            r0 = 0
            java.io.File r1 = m3117a((java.io.File) r5, (int) r6, (boolean) r0)
            boolean r2 = r1.exists()
            r3 = 1
            if (r2 != 0) goto L_0x0012
            java.io.File r1 = m3117a((java.io.File) r5, (int) r6, (boolean) r3)
            r5 = 1
            goto L_0x0013
        L_0x0012:
            r5 = 0
        L_0x0013:
            boolean r6 = r1.exists()
            r2 = 0
            if (r6 != 0) goto L_0x001b
            return r2
        L_0x001b:
            java.lang.String r6 = "TabState"
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x003a, IOException -> 0x002e, all -> 0x002c }
            r4.<init>(r1)     // Catch:{ FileNotFoundException -> 0x003a, IOException -> 0x002e, all -> 0x002c }
            org.chromium.chrome.browser.tab.TabState r2 = m3120a((java.io.FileInputStream) r4, (boolean) r5)     // Catch:{ FileNotFoundException -> 0x003b, IOException -> 0x002a }
            goto L_0x0051
        L_0x0027:
            r5 = move-exception
            r2 = r4
            goto L_0x0055
        L_0x002a:
            r5 = move-exception
            goto L_0x0030
        L_0x002c:
            r5 = move-exception
            goto L_0x0055
        L_0x002e:
            r5 = move-exception
            r4 = r2
        L_0x0030:
            java.lang.String r1 = "Failed to restore tab state."
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0027 }
            r3[r0] = r5     // Catch:{ all -> 0x0027 }
            VN0.a(r6, r1, r3)     // Catch:{ all -> 0x0027 }
            goto L_0x0051
        L_0x003a:
            r4 = r2
        L_0x003b:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0027 }
            r5.<init>()     // Catch:{ all -> 0x0027 }
            java.lang.String r3 = "Failed to restore tab state for tab: "
            r5.append(r3)     // Catch:{ all -> 0x0027 }
            r5.append(r1)     // Catch:{ all -> 0x0027 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0027 }
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x0027 }
            VN0.a(r6, r5, r0)     // Catch:{ all -> 0x0027 }
        L_0x0051:
            oO0.a(r4)
            return r2
        L_0x0055:
            oO0.a(r2)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.tab.TabState.m3119a(java.io.File, int):org.chromium.chrome.browser.tab.TabState");
    }

    /* renamed from: a */
    public static TabState m3121a(Tab tab) {
        ByteBuffer byteBuffer;
        a aVar = null;
        if (!tab.mo9316Y()) {
            return null;
        }
        TabState tabState = new TabState();
        int i = 0;
        if (tab.mo9393q() != null) {
            aVar = tab.mo9393q();
        } else {
            LoadUrlParams y = tab.mo9409y();
            if (y == null) {
                byteBuffer = nativeGetContentsStateAsByteBuffer(tab);
            } else {
                oQ2 m = y.mo9731m();
                byteBuffer = nativeCreateSingleNavigationStateAsByteBuffer(y.mo9735q(), m != null ? m.a : null, m != null ? m.b : 0, y.mo9725g(), tab.mo9315X());
            }
            if (byteBuffer != null) {
                aVar = new a(byteBuffer);
                aVar.b = 2;
            }
        }
        tabState.f2265a = aVar;
        tabState.f2269e = Ov2.b(tab);
        tabState.f2266b = tab.mo9407x();
        tabState.f2268d = tab.mo9298G();
        tabState.f2271g = tab.mo9399t();
        if (!fw2.r(tab)) {
            i = fw2.q(tab);
        }
        tabState.f2270f = i;
        tabState.f2267c = tab.mo9292B();
        return tabState;
    }

    /* renamed from: a */
    public static void m3122a(File file, TabState tabState, boolean z) {
        a aVar;
        FileOutputStream fileOutputStream;
        if (tabState != null && (aVar = tabState.f2265a) != null) {
            ByteBuffer byteBuffer = aVar.a;
            byte[] bArr = new byte[byteBuffer.limit()];
            byteBuffer.rewind();
            byteBuffer.get(bArr);
            DataOutputStream dataOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file);
                if (z) {
                    try {
                        Cipher a = QJ1.a.a(1);
                        if (a != null) {
                            dataOutputStream = new DataOutputStream(new BufferedOutputStream(new CipherOutputStream(fileOutputStream, a)));
                        } else {
                            oO0.a((Closeable) null);
                            oO0.a(fileOutputStream);
                            return;
                        }
                    } catch (FileNotFoundException unused) {
                        VN0.c("TabState", "FileNotFoundException while attempting to save TabState.", new Object[0]);
                        oO0.a(dataOutputStream);
                        oO0.a(fileOutputStream);
                    } catch (IOException unused2) {
                        VN0.c("TabState", "IOException while attempting to save TabState.", new Object[0]);
                        oO0.a(dataOutputStream);
                        oO0.a(fileOutputStream);
                    }
                } else {
                    dataOutputStream = new DataOutputStream(new BufferedOutputStream(fileOutputStream));
                }
                if (z) {
                    dataOutputStream.writeLong(0);
                }
                dataOutputStream.writeLong(tabState.f2268d);
                dataOutputStream.writeInt(bArr.length);
                dataOutputStream.write(bArr);
                dataOutputStream.writeInt(tabState.f2266b);
                String str = tabState.f2269e;
                if (str == null) {
                    str = BuildConfig.FLAVOR;
                }
                dataOutputStream.writeUTF(str);
                dataOutputStream.writeInt(tabState.f2265a.b);
                dataOutputStream.writeLong(-1);
                dataOutputStream.writeBoolean(false);
                dataOutputStream.writeInt(tabState.f2270f);
                Integer num = tabState.f2271g;
                dataOutputStream.writeInt(num != null ? num.intValue() : -1);
                dataOutputStream.writeInt(tabState.f2267c);
            } catch (FileNotFoundException unused3) {
                fileOutputStream = null;
                VN0.c("TabState", "FileNotFoundException while attempting to save TabState.", new Object[0]);
                oO0.a(dataOutputStream);
                oO0.a(fileOutputStream);
            } catch (IOException unused4) {
                fileOutputStream = null;
                VN0.c("TabState", "IOException while attempting to save TabState.", new Object[0]);
                oO0.a(dataOutputStream);
                oO0.a(fileOutputStream);
            } catch (Throwable th) {
                th = th;
                oO0.a((Closeable) null);
                oO0.a(fileOutputStream);
                throw th;
            }
            oO0.a(dataOutputStream);
            oO0.a(fileOutputStream);
        }
    }

    /* renamed from: a */
    public static File m3117a(File file, int i, boolean z) {
        return new File(file, m3118a(i, z));
    }

    /* renamed from: a */
    public String mo9413a() {
        a aVar = this.f2265a;
        return nativeGetDisplayTitleFromByteBuffer(aVar.a, aVar.b);
    }

    /* renamed from: a */
    public static String m3118a(int i, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "cryptonito" : "tab");
        sb.append(i);
        return sb.toString();
    }

    /* renamed from: a */
    public static Pair<Integer, Boolean> m3116a(String str) {
        try {
            if (str.startsWith("cryptonito")) {
                return Pair.create(Integer.valueOf(Integer.parseInt(str.substring(10))), true);
            }
            if (str.startsWith("tab")) {
                return Pair.create(Integer.valueOf(Integer.parseInt(str.substring(3))), false);
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }
}
