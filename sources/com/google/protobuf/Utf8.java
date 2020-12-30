package com.google.protobuf;

import java.nio.ByteBuffer;

/* compiled from: PG */
public abstract class Utf8 {

    /* renamed from: a */
    public static final pO f1198a = (oO.c() && oO.b ? new rO() : new qO());

    /* compiled from: PG */
    public static class UnpairedSurrogateException extends IllegalArgumentException {
        public UnpairedSurrogateException(int i, int i2) {
            super(Eo.a("Unpaired surrogate at index ", i, " of ", i2));
        }
    }

    /* renamed from: a */
    public static int m1191a(int i) {
        if (i > -12) {
            return -1;
        }
        return i;
    }

    /* renamed from: a */
    public static int m1192a(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    /* renamed from: a */
    public static int m1193a(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    /* renamed from: a */
    public static /* synthetic */ int m1197a(byte[] bArr, int i, int i2) {
        byte b = bArr[i - 1];
        int i3 = i2 - i;
        if (i3 == 0) {
            return m1191a((int) b);
        }
        if (i3 == 1) {
            return m1192a(b, bArr[i]);
        }
        if (i3 == 2) {
            return m1193a((int) b, (int) bArr[i], (int) bArr[i + 1]);
        }
        throw new AssertionError();
    }

    /* renamed from: b */
    public static boolean m1198b(byte[] bArr, int i, int i2) {
        if (f1198a.a(0, bArr, i, i2) == 0) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public static /* synthetic */ int m1196a(ByteBuffer byteBuffer, int i, int i2, int i3) {
        if (i3 == 0) {
            return m1191a(i);
        }
        if (i3 == 1) {
            return m1192a(i, byteBuffer.get(i2));
        }
        if (i3 == 2) {
            return m1193a(i, (int) byteBuffer.get(i2), (int) byteBuffer.get(i2 + 1));
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public static int m1194a(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt = charSequence.charAt(i2);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 2048) {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) >= 65536) {
                                i2++;
                            } else {
                                throw new UnpairedSurrogateException(i2, length2);
                            }
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        StringBuilder a = Eo.a("UTF-8 length does not fit in int: ");
        a.append(((long) i3) + 4294967296L);
        throw new IllegalArgumentException(a.toString());
    }

    /* renamed from: a */
    public static /* synthetic */ int m1195a(ByteBuffer byteBuffer, int i, int i2) {
        int i3 = i2 - 7;
        int i4 = i;
        while (i4 < i3 && (byteBuffer.getLong(i4) & -9187201950435737472L) == 0) {
            i4 += 8;
        }
        return i4 - i;
    }
}
