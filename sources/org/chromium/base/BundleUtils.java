package org.chromium.base;

import android.annotation.SuppressLint;
import android.os.BaseBundle;
import android.os.Bundle;
import android.os.PersistableBundle;
import org.chromium.base.annotations.CalledByNative;

/* compiled from: PG */
public class BundleUtils {

    /* renamed from: a */
    public static final boolean f1416a;

    static {
        boolean z;
        try {
            Class.forName("org.chromium.base.BundleCanary");
            z = true;
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        f1416a = z;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public static PersistableBundle m1374a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        PersistableBundle persistableBundle = new PersistableBundle();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if ((obj instanceof PersistableBundle) || (obj instanceof Integer) || (obj instanceof int[]) || (obj instanceof Long) || (obj instanceof long[]) || (obj instanceof Double) || (obj instanceof double[]) || (obj instanceof String) || (obj instanceof String[]) || (obj instanceof Boolean) || (obj instanceof boolean[])) {
                if (obj == null) {
                    throw new IllegalArgumentException("Unable to determine type of null values");
                } else if (obj instanceof Integer) {
                    persistableBundle.putInt(str, ((Integer) obj).intValue());
                } else if (obj instanceof int[]) {
                    persistableBundle.putIntArray(str, (int[]) obj);
                } else if (obj instanceof Long) {
                    persistableBundle.putLong(str, ((Long) obj).longValue());
                } else if (obj instanceof long[]) {
                    persistableBundle.putLongArray(str, (long[]) obj);
                } else if (obj instanceof Double) {
                    persistableBundle.putDouble(str, ((Double) obj).doubleValue());
                } else if (obj instanceof double[]) {
                    persistableBundle.putDoubleArray(str, (double[]) obj);
                } else if (obj instanceof String) {
                    persistableBundle.putString(str, (String) obj);
                } else if (obj instanceof String[]) {
                    persistableBundle.putStringArray(str, (String[]) obj);
                } else if (obj instanceof Boolean) {
                    persistableBundle.putBoolean(str, ((Boolean) obj).booleanValue());
                } else if (obj instanceof boolean[]) {
                    persistableBundle.putBooleanArray(str, (boolean[]) obj);
                } else if (obj instanceof PersistableBundle) {
                    persistableBundle.putAll((PersistableBundle) obj);
                } else {
                    StringBuilder a = Eo.a("Objects of type ");
                    a.append(obj.getClass().getSimpleName());
                    a.append(" can not be put into a ");
                    a.append(BaseBundle.class.getSimpleName());
                    throw new IllegalArgumentException(a.toString());
                }
            }
        }
        return persistableBundle;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        qI.a.a(r3, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0016, code lost:
        r1 = move-exception;
     */
    @org.chromium.base.annotations.CalledByNative
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getNativeLibraryPath(java.lang.String r3) {
        /*
            pO0 r0 = pO0.a()
            android.content.Context r1 = RN0.a     // Catch:{ all -> 0x0014 }
            java.lang.ClassLoader r1 = r1.getClassLoader()     // Catch:{ all -> 0x0014 }
            dalvik.system.BaseDexClassLoader r1 = (dalvik.system.BaseDexClassLoader) r1     // Catch:{ all -> 0x0014 }
            java.lang.String r3 = r1.findLibrary(r3)     // Catch:{ all -> 0x0014 }
            r0.close()
            return r3
        L_0x0014:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0016 }
        L_0x0016:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x001b }
            goto L_0x0021
        L_0x001b:
            r0 = move-exception
            kI r2 = qI.a
            r2.a(r3, r0)
        L_0x0021:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.BundleUtils.getNativeLibraryPath(java.lang.String):java.lang.String");
    }

    @CalledByNative
    public static boolean isBundle() {
        return f1416a;
    }
}
