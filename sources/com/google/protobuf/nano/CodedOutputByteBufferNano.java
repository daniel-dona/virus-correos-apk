package com.google.protobuf.nano;

import com.google.protobuf.ByteString;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

/* compiled from: PG */
public final class CodedOutputByteBufferNano {

    /* renamed from: a */
    public final ByteBuffer f1200a;

    /* compiled from: PG */
    public static class OutOfSpaceException extends IOException {
        public static final long serialVersionUID = -6947486886997889499L;

        public OutOfSpaceException(int i, int i2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space (pos " + i + " limit " + i2 + ").");
        }
    }

    public CodedOutputByteBufferNano(byte[] bArr, int i, int i2) {
        this.f1200a = ByteBuffer.wrap(bArr, i, i2);
        this.f1200a.order(ByteOrder.LITTLE_ENDIAN);
    }

    /* renamed from: a */
    public static CodedOutputByteBufferNano m1202a(byte[] bArr, int i, int i2) {
        return new CodedOutputByteBufferNano(bArr, i, i2);
    }

    /* renamed from: b */
    public static int m1205b(int i, long j) {
        return m1211d(i) + ((-128 & j) == 0 ? 1 : (-16384 & j) == 0 ? 2 : (-2097152 & j) == 0 ? 3 : (-268435456 & j) == 0 ? 4 : (-34359738368L & j) == 0 ? 5 : (-4398046511104L & j) == 0 ? 6 : (-562949953421312L & j) == 0 ? 7 : (-72057594037927936L & j) == 0 ? 8 : (j & Long.MIN_VALUE) == 0 ? 9 : 10);
    }

    /* renamed from: c */
    public static int m1210c(int i) {
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

    /* renamed from: d */
    public static int m1211d(int i) {
        return m1210c((i << 3) | 0);
    }

    /* renamed from: a */
    public static int m1200a(CharSequence charSequence) {
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
                                throw new IllegalArgumentException(Eo.b("Unpaired surrogate at index ", i2));
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

    /* renamed from: b */
    public static int m1204b(int i, int i2) {
        return (i2 >= 0 ? m1210c(i2) : 10) + m1211d(i);
    }

    /* renamed from: b */
    public static int m1208b(int i, boolean z) {
        return m1211d(i) + 1;
    }

    /* renamed from: b */
    public static int m1206b(int i, String str) {
        int d = m1211d(i);
        int a = m1200a((CharSequence) str);
        return d + m1210c(a) + a;
    }

    /* renamed from: b */
    public static int m1207b(int i, xO xOVar) {
        int d = m1211d(i);
        int c = xOVar.c();
        return d + m1210c(c) + c;
    }

    /* renamed from: a */
    public static void m1203a(CharSequence charSequence, ByteBuffer byteBuffer) {
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        } else if (byteBuffer.hasArray()) {
            try {
                byteBuffer.position(m1201a(charSequence, byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining()) - byteBuffer.arrayOffset());
            } catch (ArrayIndexOutOfBoundsException e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        } else {
            int length = charSequence.length();
            int i = 0;
            while (i < length) {
                char charAt = charSequence.charAt(i);
                if (charAt < 128) {
                    byteBuffer.put((byte) charAt);
                } else if (charAt < 2048) {
                    byteBuffer.put((byte) ((charAt >>> 6) | 960));
                    byteBuffer.put((byte) ((charAt & '?') | 128));
                } else if (charAt < 55296 || 57343 < charAt) {
                    byteBuffer.put((byte) ((charAt >>> 12) | 480));
                    byteBuffer.put((byte) (((charAt >>> 6) & 63) | ByteString.CONCATENATE_BY_COPY_SIZE));
                    byteBuffer.put((byte) ((charAt & '?') | 128));
                } else {
                    int i2 = i + 1;
                    if (i2 != charSequence.length()) {
                        char charAt2 = charSequence.charAt(i2);
                        if (Character.isSurrogatePair(charAt, charAt2)) {
                            int codePoint = Character.toCodePoint(charAt, charAt2);
                            byteBuffer.put((byte) ((codePoint >>> 18) | 240));
                            byteBuffer.put((byte) (((codePoint >>> 12) & 63) | ByteString.CONCATENATE_BY_COPY_SIZE));
                            byteBuffer.put((byte) (((codePoint >>> 6) & 63) | ByteString.CONCATENATE_BY_COPY_SIZE));
                            byteBuffer.put((byte) ((codePoint & 63) | ByteString.CONCATENATE_BY_COPY_SIZE));
                            i = i2;
                        } else {
                            i = i2;
                        }
                    }
                    StringBuilder a = Eo.a("Unpaired surrogate at index ");
                    a.append(i - 1);
                    throw new IllegalArgumentException(a.toString());
                }
                i++;
            }
        }
    }

    /* renamed from: b */
    public static int m1209b(int i, byte[] bArr) {
        return m1211d(i) + m1210c(bArr.length) + bArr.length;
    }

    /* renamed from: b */
    public void mo2642b(int i) throws IOException {
        while ((i & -128) != 0) {
            mo2633a((i & 127) | ByteString.CONCATENATE_BY_COPY_SIZE);
            i >>>= 7;
        }
        mo2633a(i);
    }

    /* renamed from: a */
    public static int m1201a(CharSequence charSequence, byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        char charAt;
        int length = charSequence.length();
        int i6 = i2 + i;
        int i7 = 0;
        while (i7 < length && (i5 = i7 + i) < i6 && (charAt = charSequence.charAt(i7)) < 128) {
            bArr[i5] = (byte) charAt;
            i7++;
        }
        if (i7 == length) {
            return i + length;
        }
        int i8 = i + i7;
        while (i7 < length) {
            char charAt2 = charSequence.charAt(i7);
            if (charAt2 < 128 && i8 < i6) {
                i3 = i8 + 1;
                bArr[i8] = (byte) charAt2;
            } else if (charAt2 < 2048 && i8 <= i6 - 2) {
                int i9 = i8 + 1;
                bArr[i8] = (byte) ((charAt2 >>> 6) | 960);
                i8 = i9 + 1;
                bArr[i9] = (byte) ((charAt2 & '?') | 128);
                i7++;
            } else if ((charAt2 < 55296 || 57343 < charAt2) && i8 <= i6 - 3) {
                int i10 = i8 + 1;
                bArr[i8] = (byte) ((charAt2 >>> 12) | 480);
                int i11 = i10 + 1;
                bArr[i10] = (byte) (((charAt2 >>> 6) & 63) | ByteString.CONCATENATE_BY_COPY_SIZE);
                i3 = i11 + 1;
                bArr[i11] = (byte) ((charAt2 & '?') | 128);
            } else if (i8 <= i6 - 4) {
                int i12 = i7 + 1;
                if (i12 != charSequence.length()) {
                    char charAt3 = charSequence.charAt(i12);
                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                        int i13 = i8 + 1;
                        bArr[i8] = (byte) ((codePoint >>> 18) | 240);
                        int i14 = i13 + 1;
                        bArr[i13] = (byte) (((codePoint >>> 12) & 63) | ByteString.CONCATENATE_BY_COPY_SIZE);
                        int i15 = i14 + 1;
                        bArr[i14] = (byte) (((codePoint >>> 6) & 63) | ByteString.CONCATENATE_BY_COPY_SIZE);
                        i8 = i15 + 1;
                        bArr[i15] = (byte) ((codePoint & 63) | ByteString.CONCATENATE_BY_COPY_SIZE);
                        i7 = i12;
                        i7++;
                    } else {
                        i7 = i12;
                    }
                }
                StringBuilder a = Eo.a("Unpaired surrogate at index ");
                a.append(i7 - 1);
                throw new IllegalArgumentException(a.toString());
            } else if (55296 > charAt2 || charAt2 > 57343 || ((i4 = i7 + 1) != charSequence.length() && Character.isSurrogatePair(charAt2, charSequence.charAt(i4)))) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i8);
            } else {
                throw new IllegalArgumentException(Eo.b("Unpaired surrogate at index ", i7));
            }
            i8 = i3;
            i7++;
        }
        return i8;
    }

    /* renamed from: a */
    public void mo2633a(int i) throws IOException {
        byte b = (byte) i;
        if (this.f1200a.hasRemaining()) {
            this.f1200a.put(b);
            return;
        }
        throw new OutOfSpaceException(this.f1200a.position(), this.f1200a.limit());
    }

    /* renamed from: a */
    public void mo2641a(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this.f1200a.remaining() >= length) {
            this.f1200a.put(bArr, 0, length);
            return;
        }
        throw new OutOfSpaceException(this.f1200a.position(), this.f1200a.limit());
    }

    /* renamed from: a */
    public void mo2638a(int i, boolean z) throws IOException {
        mo2642b((i << 3) | 0);
        mo2633a(z ? 1 : 0);
    }

    /* renamed from: a */
    public void mo2639a(int i, byte[] bArr) throws IOException {
        mo2642b((i << 3) | 2);
        mo2642b(bArr.length);
        mo2641a(bArr);
    }

    /* renamed from: a */
    public void mo2634a(int i, int i2) throws IOException {
        mo2642b((i << 3) | 0);
        if (i2 >= 0) {
            mo2642b(i2);
        } else {
            mo2640a((long) i2);
        }
    }

    /* renamed from: a */
    public void mo2635a(int i, long j) throws IOException {
        mo2642b((i << 3) | 0);
        mo2640a(j);
    }

    /* renamed from: a */
    public void mo2637a(int i, xO xOVar) throws IOException {
        mo2642b((i << 3) | 2);
        mo2642b(xOVar.b());
        xOVar.a(this);
    }

    /* renamed from: a */
    public void mo2636a(int i, String str) throws IOException {
        mo2642b((i << 3) | 2);
        try {
            int c = m1210c(str.length());
            if (c == m1210c(str.length() * 3)) {
                int position = this.f1200a.position();
                if (this.f1200a.remaining() >= c) {
                    this.f1200a.position(position + c);
                    m1203a((CharSequence) str, this.f1200a);
                    int position2 = this.f1200a.position();
                    this.f1200a.position(position);
                    mo2642b((position2 - position) - c);
                    this.f1200a.position(position2);
                    return;
                }
                throw new OutOfSpaceException(position + c, this.f1200a.limit());
            }
            mo2642b(m1200a((CharSequence) str));
            m1203a((CharSequence) str, this.f1200a);
        } catch (BufferOverflowException e) {
            OutOfSpaceException outOfSpaceException = new OutOfSpaceException(this.f1200a.position(), this.f1200a.limit());
            outOfSpaceException.initCause(e);
            throw outOfSpaceException;
        }
    }

    /* renamed from: a */
    public void mo2640a(long j) throws IOException {
        while ((-128 & j) != 0) {
            mo2633a((((int) j) & 127) | ByteString.CONCATENATE_BY_COPY_SIZE);
            j >>>= 7;
        }
        mo2633a((int) j);
    }
}
