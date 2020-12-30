package com.google.protobuf;

import com.google.protobuf.Utf8;
import java.io.IOException;
import java.util.logging.Logger;

/* compiled from: PG */
public abstract class CodedOutputStream extends KN {

    /* renamed from: a */
    public static final Logger f1162a = Logger.getLogger(CodedOutputStream.class.getName());

    /* renamed from: b */
    public static final boolean f1163b = oO.c;

    /* renamed from: c */
    public static final long f1164c = oO.d;

    /* compiled from: PG */
    public static class OutOfSpaceException extends IOException {
        public static final String MESSAGE = "CodedOutputStream was writing to a flat byte array and ran out of space.";
        public static final long serialVersionUID = -6947486886997889499L;

        public OutOfSpaceException() {
            super(MESSAGE);
        }

        public OutOfSpaceException(Throwable th) {
            super(MESSAGE, th);
        }

        public OutOfSpaceException(String str, Throwable th) {
            super(Eo.a("CodedOutputStream was writing to a flat byte array and ran out of space.: ", str), th);
        }
    }

    public /* synthetic */ CodedOutputStream(MN mn) {
    }

    /* renamed from: b */
    public static int m1064b(int i, float f) {
        return m1086g(i) + 4;
    }

    /* renamed from: c */
    public static int m1071c() {
        return 1;
    }

    /* renamed from: c */
    public static int m1074c(long j) {
        int i;
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if ((-34359738368L & j) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if ((-2097152 & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & -16384) != 0 ? i + 1 : i;
    }

    /* renamed from: c */
    public static CodedOutputStream m1076c(byte[] bArr) {
        return new NN(bArr, 0, bArr.length);
    }

    /* renamed from: d */
    public static int m1077d() {
        return 8;
    }

    /* renamed from: d */
    public static int m1078d(MessageLite messageLite) {
        return m1083f(messageLite.mo2584b());
    }

    /* renamed from: d */
    public static long m1079d(long j) {
        return (j >> 63) ^ (j << 1);
    }

    /* renamed from: e */
    public static int m1080e() {
        return 4;
    }

    /* renamed from: e */
    public static int m1081e(int i) {
        if (i >= 0) {
            return m1089h(i);
        }
        return 10;
    }

    /* renamed from: f */
    public static int m1082f() {
        return 8;
    }

    /* renamed from: f */
    public static int m1084f(int i, int i2) {
        return m1086g(i) + m1081e(i2);
    }

    /* renamed from: g */
    public static int m1085g() {
        return 4;
    }

    /* renamed from: g */
    public static int m1087g(int i, int i2) {
        return m1086g(i) + 4;
    }

    /* renamed from: h */
    public static int m1088h() {
        return 4;
    }

    /* renamed from: h */
    public static int m1089h(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (i & -268435456) == 0 ? 4 : 5;
    }

    /* renamed from: h */
    public static int m1090h(int i, int i2) {
        return m1081e(i2) + m1086g(i);
    }

    /* renamed from: i */
    public static int m1091i() {
        return 8;
    }

    /* renamed from: i */
    public static int m1092i(int i) {
        return (i >> 31) ^ (i << 1);
    }

    /* renamed from: i */
    public static int m1093i(int i, int i2) {
        return m1089h(i2) + m1086g(i);
    }

    /* renamed from: a */
    public abstract void mo2433a(byte b) throws IOException;

    /* renamed from: a */
    public final void mo2438a(int i, float f) throws IOException {
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        NN nn = (NN) this;
        nn.d((i << 3) | 5);
        nn.b(floatToRawIntBits);
    }

    /* renamed from: a */
    public abstract void mo2439a(int i, int i2) throws IOException;

    /* renamed from: a */
    public abstract void mo2440a(int i, long j) throws IOException;

    /* renamed from: a */
    public abstract void mo2441a(int i, ByteString byteString) throws IOException;

    /* renamed from: a */
    public abstract void mo2443a(int i, String str) throws IOException;

    /* renamed from: a */
    public abstract void mo2444a(int i, boolean z) throws IOException;

    /* renamed from: a */
    public abstract void mo2445a(long j) throws IOException;

    /* renamed from: a */
    public abstract void mo2446a(ByteString byteString) throws IOException;

    /* renamed from: a */
    public abstract void mo2448a(String str) throws IOException;

    /* renamed from: b */
    public abstract int mo2450b();

    /* renamed from: b */
    public abstract void mo2451b(int i) throws IOException;

    /* renamed from: b */
    public abstract void mo2452b(int i, int i2) throws IOException;

    /* renamed from: b */
    public abstract void mo2453b(int i, long j) throws IOException;

    /* renamed from: b */
    public abstract void mo2454b(int i, MessageLite messageLite) throws IOException;

    /* renamed from: b */
    public abstract void mo2455b(long j) throws IOException;

    /* renamed from: b */
    public abstract void mo2456b(MessageLite messageLite) throws IOException;

    /* renamed from: c */
    public abstract void mo2457c(int i) throws IOException;

    /* renamed from: d */
    public abstract void mo2459d(int i) throws IOException;

    /* renamed from: d */
    public abstract void mo2460d(int i, int i2) throws IOException;

    /* renamed from: e */
    public abstract void mo2461e(int i, int i2) throws IOException;

    /* renamed from: b */
    public static int m1067b(int i, boolean z) {
        return m1086g(i) + 1;
    }

    /* renamed from: g */
    public static int m1086g(int i) {
        return m1089h((i << 3) | 0);
    }

    /* renamed from: b */
    public static int m1066b(int i, String str) {
        return m1069b(str) + m1086g(i);
    }

    /* renamed from: f */
    public static int m1083f(int i) {
        return m1089h(i) + i;
    }

    /* renamed from: c */
    public final void mo2458c(int i, int i2) throws IOException {
        NN nn = (NN) this;
        nn.d((i << 3) | 5);
        nn.b(i2);
    }

    /* renamed from: b */
    public static int m1065b(int i, ByteString byteString) {
        return m1086g(i) + m1083f(byteString.size());
    }

    /* renamed from: a */
    public final void mo2437a(int i, double d) throws IOException {
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        NN nn = (NN) this;
        nn.d((i << 3) | 1);
        nn.a(doubleToRawLongBits);
    }

    /* renamed from: b */
    public static int m1069b(String str) {
        int i;
        try {
            i = Utf8.m1194a((CharSequence) str);
        } catch (Utf8.UnpairedSurrogateException unused) {
            i = str.getBytes(Internal.f1182a).length;
        }
        return m1083f(i);
    }

    /* renamed from: c */
    public static int m1072c(int i, long j) {
        return m1074c(j) + m1086g(i);
    }

    /* renamed from: a */
    public final void mo2435a(float f) throws IOException {
        mo2451b(Float.floatToRawIntBits(f));
    }

    /* renamed from: c */
    public static int m1073c(int i, MessageLite messageLite) {
        return m1078d(messageLite) + m1086g(i);
    }

    /* renamed from: a */
    public final void mo2434a(double d) throws IOException {
        mo2445a(Double.doubleToRawLongBits(d));
    }

    @Deprecated
    /* renamed from: c */
    public static int m1075c(MessageLite messageLite) {
        return messageLite.mo2584b();
    }

    /* renamed from: a */
    public final void mo2436a(int i) throws IOException {
        NN nn = (NN) this;
        if (i >= 0) {
            nn.d(i);
        } else {
            nn.b((long) i);
        }
    }

    /* renamed from: b */
    public static int m1068b(ByteString byteString) {
        return m1083f(byteString.size());
    }

    /* renamed from: b */
    public static int m1070b(byte[] bArr) {
        return m1083f(bArr.length);
    }

    /* renamed from: a */
    public final void mo2449a(byte[] bArr) throws IOException {
        int length = bArr.length;
        NN nn = (NN) this;
        nn.d(length);
        nn.a(bArr, 0, length);
    }

    /* renamed from: a */
    public final void mo2432a() {
        if (mo2450b() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    @Deprecated
    /* renamed from: a */
    public final void mo2442a(int i, MessageLite messageLite) throws IOException {
        NN nn = (NN) this;
        int i2 = i << 3;
        nn.d(i2 | 3);
        messageLite.mo2583a(this);
        nn.d(i2 | 4);
    }

    @Deprecated
    /* renamed from: a */
    public final void mo2447a(MessageLite messageLite) throws IOException {
        messageLite.mo2583a(this);
    }
}
