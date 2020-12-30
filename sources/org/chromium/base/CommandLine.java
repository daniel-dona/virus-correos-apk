package org.chromium.base;

import com.citrix.loggersdk.BuildConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.annotations.MainDex;

@MainDex
/* compiled from: PG */
public abstract class CommandLine {

    /* renamed from: a */
    public static final AtomicReference<CommandLine> f1417a = new AtomicReference<>();

    /* renamed from: org.chromium.base.CommandLine$b */
    /* compiled from: PG */
    public static class C0361b extends CommandLine {

        /* renamed from: b */
        public HashMap<String, String> f1418b = new HashMap<>();

        /* renamed from: c */
        public ArrayList<String> f1419c = new ArrayList<>();

        /* renamed from: d */
        public int f1420d = 1;

        public C0361b(String[] strArr) {
            super((C0360a) null);
            if (strArr == null || strArr.length == 0 || strArr[0] == null) {
                this.f1419c.add(BuildConfig.FLAVOR);
                return;
            }
            this.f1419c.add(strArr[0]);
            mo7860a(strArr, 1);
        }

        /* renamed from: a */
        public void mo7854a(String str) {
            mo7855a(str, (String) null);
        }

        /* renamed from: b */
        public String[] mo7858b() {
            ArrayList<String> arrayList = this.f1419c;
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }

        /* renamed from: c */
        public boolean mo7859c(String str) {
            return this.f1418b.containsKey(str);
        }

        /* renamed from: a */
        public void mo7855a(String str, String str2) {
            this.f1418b.put(str, str2 == null ? BuildConfig.FLAVOR : str2);
            String str3 = "--" + str;
            if (str2 != null && !str2.isEmpty()) {
                str3 = Eo.b(str3, "=", str2);
            }
            ArrayList<String> arrayList = this.f1419c;
            int i = this.f1420d;
            this.f1420d = i + 1;
            arrayList.add(i, str3);
        }

        /* renamed from: b */
        public String mo7857b(String str) {
            String str2 = this.f1418b.get(str);
            if (str2 == null || str2.isEmpty()) {
                return null;
            }
            return str2;
        }

        /* renamed from: a */
        public void mo7856a(String[] strArr) {
            mo7860a(strArr, 0);
        }

        /* renamed from: a */
        public final void mo7860a(String[] strArr, int i) {
            int i2 = i;
            boolean z = true;
            for (String str : strArr) {
                if (i2 > 0) {
                    i2--;
                } else {
                    if (str.equals("--")) {
                        z = false;
                    }
                    if (!z || !str.startsWith("--")) {
                        this.f1419c.add(str);
                    } else {
                        String[] split = str.split("=", 2);
                        mo7855a(split[0].substring(2), split.length > 1 ? split[1] : null);
                    }
                }
            }
        }
    }

    /* renamed from: org.chromium.base.CommandLine$c */
    /* compiled from: PG */
    public static class C0362c extends CommandLine {
        public C0362c(String[] strArr) {
            super((C0360a) null);
            CommandLine.nativeInit(strArr);
        }

        /* renamed from: a */
        public void mo7854a(String str) {
            CommandLine.nativeAppendSwitch(str);
        }

        /* renamed from: b */
        public String mo7857b(String str) {
            return CommandLine.nativeGetSwitchValue(str);
        }

        /* renamed from: b */
        public String[] mo7858b() {
            return null;
        }

        /* renamed from: c */
        public boolean mo7859c(String str) {
            return CommandLine.nativeHasSwitch(str);
        }

        /* renamed from: a */
        public void mo7855a(String str, String str2) {
            CommandLine.nativeAppendSwitchWithValue(str, str2);
        }

        /* renamed from: a */
        public void mo7856a(String[] strArr) {
            CommandLine.nativeAppendSwitchesAndArguments(strArr);
        }

        /* renamed from: a */
        public void mo7853a() {
            throw new IllegalStateException("Can't destroy native command line after startup");
        }
    }

    public /* synthetic */ CommandLine(C0360a aVar) {
    }

    /* renamed from: b */
    public static void m1383b(String[] strArr) {
        CommandLine andSet = f1417a.getAndSet(new C0361b(strArr));
        if (andSet != null) {
            andSet.mo7853a();
        }
    }

    /* renamed from: c */
    public static CommandLine m1384c() {
        return f1417a.get();
    }

    /* renamed from: d */
    public static boolean m1386d() {
        return f1417a.get() != null;
    }

    public static native void nativeAppendSwitch(String str);

    public static native void nativeAppendSwitchWithValue(String str, String str2);

    public static native void nativeAppendSwitchesAndArguments(String[] strArr);

    public static native String nativeGetSwitchValue(String str);

    public static native boolean nativeHasSwitch(String str);

    public static native void nativeInit(String[] strArr);

    /* renamed from: a */
    public void mo7853a() {
    }

    /* renamed from: a */
    public abstract void mo7854a(String str);

    /* renamed from: a */
    public abstract void mo7855a(String str, String str2);

    /* renamed from: a */
    public abstract void mo7856a(String[] strArr);

    /* renamed from: b */
    public abstract String mo7857b(String str);

    /* renamed from: b */
    public abstract String[] mo7858b();

    /* renamed from: c */
    public abstract boolean mo7859c(String str);

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0021, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002c, code lost:
        throw r1;
     */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m1385d(java.lang.String r9) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r9)
            r9 = 0
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ IOException -> 0x002d }
            r2.<init>(r0)     // Catch:{ IOException -> 0x002d }
            long r3 = r0.length()     // Catch:{ all -> 0x001f }
            int r0 = (int) r3     // Catch:{ all -> 0x001f }
            char[] r0 = new char[r0]     // Catch:{ all -> 0x001f }
            int r3 = r2.read(r0)     // Catch:{ all -> 0x001f }
            char[] r0 = java.util.Arrays.copyOfRange(r0, r1, r3)     // Catch:{ all -> 0x001f }
            r2.close()     // Catch:{ IOException -> 0x002d }
            goto L_0x002e
        L_0x001f:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0021 }
        L_0x0021:
            r1 = move-exception
            r2.close()     // Catch:{ all -> 0x0026 }
            goto L_0x002c
        L_0x0026:
            r2 = move-exception
            kI r3 = qI.a     // Catch:{ IOException -> 0x002d }
            r3.a(r0, r2)     // Catch:{ IOException -> 0x002d }
        L_0x002c:
            throw r1     // Catch:{ IOException -> 0x002d }
        L_0x002d:
            r0 = r9
        L_0x002e:
            if (r0 != 0) goto L_0x0032
            goto L_0x00c4
        L_0x0032:
            int r1 = r0.length
            r2 = 65536(0x10000, float:9.18355E-41)
            if (r1 > r2) goto L_0x00c8
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            int r2 = r0.length
            r3 = 0
            r4 = 0
            r5 = 0
            r4 = r9
        L_0x0041:
            if (r3 >= r2) goto L_0x0097
            char r6 = r0[r3]
            if (r5 != 0) goto L_0x004f
            r7 = 39
            if (r6 == r7) goto L_0x0051
            r7 = 34
            if (r6 == r7) goto L_0x0051
        L_0x004f:
            if (r6 != r5) goto L_0x0077
        L_0x0051:
            if (r4 == 0) goto L_0x0071
            int r7 = r4.length()
            if (r7 <= 0) goto L_0x0071
            int r7 = r4.length()
            int r7 = r7 + -1
            char r7 = r4.charAt(r7)
            r8 = 92
            if (r7 != r8) goto L_0x0071
            int r7 = r4.length()
            int r7 = r7 + -1
            r4.setCharAt(r7, r6)
            goto L_0x0094
        L_0x0071:
            if (r5 != 0) goto L_0x0074
            goto L_0x0075
        L_0x0074:
            r6 = 0
        L_0x0075:
            r5 = r6
            goto L_0x0094
        L_0x0077:
            if (r5 != 0) goto L_0x008a
            boolean r7 = java.lang.Character.isWhitespace(r6)
            if (r7 == 0) goto L_0x008a
            if (r4 == 0) goto L_0x0094
            java.lang.String r4 = r4.toString()
            r1.add(r4)
            r4 = r9
            goto L_0x0094
        L_0x008a:
            if (r4 != 0) goto L_0x0091
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
        L_0x0091:
            r4.append(r6)
        L_0x0094:
            int r3 = r3 + 1
            goto L_0x0041
        L_0x0097:
            if (r4 == 0) goto L_0x00b8
            if (r5 == 0) goto L_0x00b1
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = "Unterminated quoted string: "
            r9.append(r0)
            r9.append(r4)
            java.lang.String r9 = r9.toString()
            java.lang.String r0 = "CommandLine"
            android.util.Log.w(r0, r9)
        L_0x00b1:
            java.lang.String r9 = r4.toString()
            r1.add(r9)
        L_0x00b8:
            int r9 = r1.size()
            java.lang.String[] r9 = new java.lang.String[r9]
            java.lang.Object[] r9 = r1.toArray(r9)
            java.lang.String[] r9 = (java.lang.String[]) r9
        L_0x00c4:
            m1383b((java.lang.String[]) r9)
            return
        L_0x00c8:
            java.lang.RuntimeException r9 = new java.lang.RuntimeException
            java.lang.String r1 = "Flags file too big: "
            java.lang.StringBuilder r1 = Eo.a(r1)
            int r0 = r0.length
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r9.<init>(r0)
            goto L_0x00dd
        L_0x00dc:
            throw r9
        L_0x00dd:
            goto L_0x00dc
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.CommandLine.m1385d(java.lang.String):void");
    }
}
