package com.microsoft.security.oss;

import android.os.Build;
import android.os.Process;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/* compiled from: PG */
public abstract class PRNGFixes {

    /* renamed from: a */
    public static final byte[] f1298a;

    static {
        StringBuilder sb = new StringBuilder();
        String str = Build.FINGERPRINT;
        if (str != null) {
            sb.append(str);
        }
        String str2 = null;
        try {
            str2 = (String) Build.class.getField("SERIAL").get((Object) null);
        } catch (Exception unused) {
        }
        if (str2 != null) {
            sb.append(str2);
        }
        try {
            f1298a = sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused2) {
            throw new RuntimeException("UTF-8 encoding not supported");
        }
    }

    /* renamed from: a */
    public static void m1317a() {
    }

    /* renamed from: b */
    public static byte[] m1318b() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeLong(System.currentTimeMillis());
            dataOutputStream.writeLong(System.nanoTime());
            dataOutputStream.writeInt(Process.myPid());
            dataOutputStream.writeInt(Process.myUid());
            dataOutputStream.write(f1298a);
            dataOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new SecurityException("Failed to generate seed", e);
        }
    }
}
