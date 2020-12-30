package com.google.protobuf;

import com.citrix.loggersdk.BuildConfig;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* compiled from: PG */
public abstract class ByteString implements Iterable<Byte>, Serializable {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int CONCATENATE_BY_COPY_SIZE = 128;
    public static final ByteString EMPTY = new LiteralByteString(Internal.f1183b);
    public static final int MAX_READ_FROM_CHUNK_SIZE = 8192;
    public static final int MIN_READ_FROM_CHUNK_SIZE = 256;
    public static final C0277c byteArrayCopier;
    public int hash = 0;

    /* compiled from: PG */
    public static final class BoundedByteString extends LiteralByteString {
        public static final long serialVersionUID = 1;
        public final int bytesLength;
        public final int bytesOffset;

        public BoundedByteString(byte[] bArr, int i, int i2) {
            super(bArr);
            ByteString.checkRange(i, i + i2, bArr.length);
            this.bytesOffset = i;
            this.bytesLength = i2;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException {
            throw new InvalidObjectException("BoundedByteStream instances are not to be serialized directly");
        }

        public byte byteAt(int i) {
            ByteString.checkIndex(i, size());
            return this.bytes[this.bytesOffset + i];
        }

        public void copyToInternal(byte[] bArr, int i, int i2, int i3) {
            System.arraycopy(this.bytes, getOffsetIntoBytes() + i, bArr, i2, i3);
        }

        public int getOffsetIntoBytes() {
            return this.bytesOffset;
        }

        public int size() {
            return this.bytesLength;
        }

        public Object writeReplace() {
            return ByteString.wrap(toByteArray());
        }
    }

    /* compiled from: PG */
    public interface ByteIterator extends Iterator<Byte> {
        byte nextByte();
    }

    /* compiled from: PG */
    public static abstract class LeafByteString extends ByteString {
        public abstract boolean equalsRange(ByteString byteString, int i, int i2);

        public final int getTreeDepth() {
            return 0;
        }

        public final boolean isBalanced() {
            return true;
        }

        public /* bridge */ /* synthetic */ Iterator iterator() {
            return ByteString.super.iterator();
        }
    }

    /* compiled from: PG */
    public static class LiteralByteString extends LeafByteString {
        public static final long serialVersionUID = 1;
        public final byte[] bytes;

        public LiteralByteString(byte[] bArr) {
            this.bytes = bArr;
        }

        public final ByteBuffer asReadOnlyByteBuffer() {
            return ByteBuffer.wrap(this.bytes, getOffsetIntoBytes(), size()).asReadOnlyBuffer();
        }

        public final List<ByteBuffer> asReadOnlyByteBufferList() {
            return Collections.singletonList(asReadOnlyByteBuffer());
        }

        public byte byteAt(int i) {
            return this.bytes[i];
        }

        public final void copyTo(ByteBuffer byteBuffer) {
            byteBuffer.put(this.bytes, getOffsetIntoBytes(), size());
        }

        public void copyToInternal(byte[] bArr, int i, int i2, int i3) {
            System.arraycopy(this.bytes, i, bArr, i2, i3);
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ByteString) || size() != ((ByteString) obj).size()) {
                return false;
            }
            if (size() == 0) {
                return true;
            }
            if (!(obj instanceof LiteralByteString)) {
                return obj.equals(this);
            }
            LiteralByteString literalByteString = (LiteralByteString) obj;
            int peekCachedHashCode = peekCachedHashCode();
            int peekCachedHashCode2 = literalByteString.peekCachedHashCode();
            if (peekCachedHashCode == 0 || peekCachedHashCode2 == 0 || peekCachedHashCode == peekCachedHashCode2) {
                return equalsRange(literalByteString, 0, size());
            }
            return false;
        }

        public final boolean equalsRange(ByteString byteString, int i, int i2) {
            if (i2 <= byteString.size()) {
                int i3 = i + i2;
                if (i3 > byteString.size()) {
                    throw new IllegalArgumentException("Ran off end of other: " + i + ", " + i2 + ", " + byteString.size());
                } else if (!(byteString instanceof LiteralByteString)) {
                    return byteString.substring(i, i3).equals(substring(0, i2));
                } else {
                    LiteralByteString literalByteString = (LiteralByteString) byteString;
                    byte[] bArr = this.bytes;
                    byte[] bArr2 = literalByteString.bytes;
                    int offsetIntoBytes = getOffsetIntoBytes() + i2;
                    int offsetIntoBytes2 = getOffsetIntoBytes();
                    int offsetIntoBytes3 = literalByteString.getOffsetIntoBytes() + i;
                    while (offsetIntoBytes2 < offsetIntoBytes) {
                        if (bArr[offsetIntoBytes2] != bArr2[offsetIntoBytes3]) {
                            return false;
                        }
                        offsetIntoBytes2++;
                        offsetIntoBytes3++;
                    }
                    return true;
                }
            } else {
                throw new IllegalArgumentException("Length too large: " + i2 + size());
            }
        }

        public int getOffsetIntoBytes() {
            return 0;
        }

        public final boolean isValidUtf8() {
            int offsetIntoBytes = getOffsetIntoBytes();
            if (Utf8.f1198a.a(0, this.bytes, offsetIntoBytes, size() + offsetIntoBytes) == 0) {
                return true;
            }
            return false;
        }

        public final LN newCodedInput() {
            byte[] bArr = this.bytes;
            int offsetIntoBytes = getOffsetIntoBytes();
            int size = size();
            LN ln = new LN(bArr, offsetIntoBytes, size, true);
            try {
                ln.b(size);
                return ln;
            } catch (InvalidProtocolBufferException e) {
                throw new IllegalArgumentException(e);
            }
        }

        public final InputStream newInput() {
            return new ByteArrayInputStream(this.bytes, getOffsetIntoBytes(), size());
        }

        public final int partialHash(int i, int i2, int i3) {
            return Internal.m1177a(i, this.bytes, getOffsetIntoBytes() + i2, i3);
        }

        public final int partialIsValidUtf8(int i, int i2, int i3) {
            int offsetIntoBytes = getOffsetIntoBytes() + i2;
            return Utf8.f1198a.a(i, this.bytes, offsetIntoBytes, i3 + offsetIntoBytes);
        }

        public int size() {
            return this.bytes.length;
        }

        public final ByteString substring(int i, int i2) {
            int checkRange = ByteString.checkRange(i, i2, size());
            if (checkRange == 0) {
                return ByteString.EMPTY;
            }
            return new BoundedByteString(this.bytes, getOffsetIntoBytes() + i, checkRange);
        }

        public final String toStringInternal(Charset charset) {
            return new String(this.bytes, getOffsetIntoBytes(), size(), charset);
        }

        public final void writeTo(OutputStream outputStream) throws IOException {
            outputStream.write(toByteArray());
        }

        public final void writeToInternal(OutputStream outputStream, int i, int i2) throws IOException {
            outputStream.write(this.bytes, getOffsetIntoBytes() + i, i2);
        }

        public final void writeTo(KN kn) throws IOException {
            ((NN) kn).a(this.bytes, getOffsetIntoBytes(), size());
        }
    }

    /* renamed from: com.google.protobuf.ByteString$a */
    /* compiled from: PG */
    public class C0275a implements ByteIterator {

        /* renamed from: a */
        public int f1152a = 0;

        /* renamed from: b */
        public final int f1153b = ByteString.this.size();

        public C0275a() {
        }

        public boolean hasNext() {
            return this.f1152a < this.f1153b;
        }

        public Object next() {
            try {
                ByteString byteString = ByteString.this;
                int i = this.f1152a;
                this.f1152a = i + 1;
                return Byte.valueOf(byteString.byteAt(i));
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException(e.getMessage());
            }
        }

        public byte nextByte() {
            try {
                ByteString byteString = ByteString.this;
                int i = this.f1152a;
                this.f1152a = i + 1;
                return byteString.byteAt(i);
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException(e.getMessage());
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* renamed from: com.google.protobuf.ByteString$b */
    /* compiled from: PG */
    public static final class C0276b implements C0277c {
        public /* synthetic */ C0276b(C0275a aVar) {
        }

        /* renamed from: a */
        public byte[] mo2426a(byte[] bArr, int i, int i2) {
            return Arrays.copyOfRange(bArr, i, i2 + i);
        }
    }

    /* renamed from: com.google.protobuf.ByteString$c */
    /* compiled from: PG */
    public interface C0277c {
        /* renamed from: a */
        byte[] mo2426a(byte[] bArr, int i, int i2);
    }

    /* renamed from: com.google.protobuf.ByteString$d */
    /* compiled from: PG */
    public static final class C0278d {

        /* renamed from: a */
        public final CodedOutputStream f1155a = CodedOutputStream.m1076c(this.f1156b);

        /* renamed from: b */
        public final byte[] f1156b;

        public /* synthetic */ C0278d(int i, C0275a aVar) {
            this.f1156b = new byte[i];
        }
    }

    /* renamed from: com.google.protobuf.ByteString$e */
    /* compiled from: PG */
    public static final class C0279e extends OutputStream {

        /* renamed from: a */
        public final int f1157a;

        /* renamed from: b */
        public final ArrayList<ByteString> f1158b;

        /* renamed from: c */
        public int f1159c;

        /* renamed from: d */
        public byte[] f1160d;

        /* renamed from: e */
        public int f1161e;

        public C0279e(int i) {
            if (i >= 0) {
                this.f1157a = i;
                this.f1158b = new ArrayList<>();
                this.f1160d = new byte[i];
                return;
            }
            throw new IllegalArgumentException("Buffer size < 0");
        }

        /* renamed from: a */
        public synchronized int mo2427a() {
            return this.f1159c + this.f1161e;
        }

        public String toString() {
            return String.format("<ByteString.Output@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(mo2427a())});
        }

        public synchronized void write(int i) {
            if (this.f1161e == this.f1160d.length) {
                mo2428a(1);
            }
            byte[] bArr = this.f1160d;
            int i2 = this.f1161e;
            this.f1161e = i2 + 1;
            bArr[i2] = (byte) i;
        }

        /* renamed from: a */
        public final void mo2428a(int i) {
            this.f1158b.add(new LiteralByteString(this.f1160d));
            this.f1159c += this.f1160d.length;
            this.f1160d = new byte[Math.max(this.f1157a, Math.max(i, this.f1159c >>> 1))];
            this.f1161e = 0;
        }

        public synchronized void write(byte[] bArr, int i, int i2) {
            if (i2 <= this.f1160d.length - this.f1161e) {
                System.arraycopy(bArr, i, this.f1160d, this.f1161e, i2);
                this.f1161e += i2;
            } else {
                int length = this.f1160d.length - this.f1161e;
                System.arraycopy(bArr, i, this.f1160d, this.f1161e, length);
                int i3 = i2 - length;
                mo2428a(i3);
                System.arraycopy(bArr, i + length, this.f1160d, 0, i3);
                this.f1161e = i3;
            }
        }
    }

    /* renamed from: com.google.protobuf.ByteString$f */
    /* compiled from: PG */
    public static final class C0280f implements C0277c {
        public /* synthetic */ C0280f(C0275a aVar) {
        }

        /* renamed from: a */
        public byte[] mo2426a(byte[] bArr, int i, int i2) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            return bArr2;
        }
    }

    static {
        Class<ByteString> cls = ByteString.class;
        boolean z = true;
        try {
            Class.forName("android.content.Context");
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        byteArrayCopier = z ? new C0280f((C0275a) null) : new C0276b((C0275a) null);
    }

    public static ByteString balancedConcat(Iterator<ByteString> it, int i) {
        if (i == 1) {
            return it.next();
        }
        int i2 = i >>> 1;
        return balancedConcat(it, i2).concat(balancedConcat(it, i - i2));
    }

    public static void checkIndex(int i, int i2) {
        if (((i2 - (i + 1)) | i) >= 0) {
            return;
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException(Eo.b("Index < 0: ", i));
        }
        throw new ArrayIndexOutOfBoundsException(Eo.a("Index > length: ", i, ", ", i2));
    }

    public static int checkRange(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException(Eo.a("Beginning index: ", i, " < 0"));
        } else if (i2 < i) {
            throw new IndexOutOfBoundsException(Eo.a("Beginning index larger than ending index: ", i, ", ", i2));
        } else {
            throw new IndexOutOfBoundsException(Eo.a("End index: ", i2, " >= ", i3));
        }
    }

    public static ByteString copyFrom(byte[] bArr, int i, int i2) {
        return new LiteralByteString(byteArrayCopier.mo2426a(bArr, i, i2));
    }

    public static ByteString copyFromUtf8(String str) {
        return new LiteralByteString(str.getBytes(Internal.f1182a));
    }

    public static C0278d newCodedBuilder(int i) {
        return new C0278d(i, (C0275a) null);
    }

    public static C0279e newOutput(int i) {
        return new C0279e(i);
    }

    public static ByteString readChunk(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read == -1) {
                break;
            }
            i2 += read;
        }
        if (i2 == 0) {
            return null;
        }
        return copyFrom(bArr, 0, i2);
    }

    public static ByteString readFrom(InputStream inputStream) throws IOException {
        return readFrom(inputStream, 256, MAX_READ_FROM_CHUNK_SIZE);
    }

    public static ByteString wrap(byte[] bArr) {
        return new LiteralByteString(bArr);
    }

    public abstract ByteBuffer asReadOnlyByteBuffer();

    public abstract List<ByteBuffer> asReadOnlyByteBufferList();

    public abstract byte byteAt(int i);

    public final ByteString concat(ByteString byteString) {
        if (Integer.MAX_VALUE - size() >= byteString.size()) {
            return RopeByteString.concatenate(this, byteString);
        }
        StringBuilder a = Eo.a("ByteString would be too long: ");
        a.append(size());
        a.append("+");
        a.append(byteString.size());
        throw new IllegalArgumentException(a.toString());
    }

    public abstract void copyTo(ByteBuffer byteBuffer);

    public void copyTo(byte[] bArr, int i) {
        copyTo(bArr, 0, i, size());
    }

    public abstract void copyToInternal(byte[] bArr, int i, int i2, int i3);

    public final boolean endsWith(ByteString byteString) {
        return size() >= byteString.size() && substring(size() - byteString.size()).equals(byteString);
    }

    public abstract boolean equals(Object obj);

    public abstract int getTreeDepth();

    public final int hashCode() {
        int i = this.hash;
        if (i == 0) {
            int size = size();
            i = partialHash(size, 0, size);
            if (i == 0) {
                i = 1;
            }
            this.hash = i;
        }
        return i;
    }

    public abstract boolean isBalanced();

    public final boolean isEmpty() {
        return size() == 0;
    }

    public abstract boolean isValidUtf8();

    public abstract LN newCodedInput();

    public abstract InputStream newInput();

    public abstract int partialHash(int i, int i2, int i3);

    public abstract int partialIsValidUtf8(int i, int i2, int i3);

    public final int peekCachedHashCode() {
        return this.hash;
    }

    public abstract int size();

    public final boolean startsWith(ByteString byteString) {
        if (size() < byteString.size() || !substring(0, byteString.size()).equals(byteString)) {
            return false;
        }
        return true;
    }

    public final ByteString substring(int i) {
        return substring(i, size());
    }

    public abstract ByteString substring(int i, int i2);

    public final byte[] toByteArray() {
        int size = size();
        if (size == 0) {
            return Internal.f1183b;
        }
        byte[] bArr = new byte[size];
        copyToInternal(bArr, 0, 0, size);
        return bArr;
    }

    public final String toString(String str) throws UnsupportedEncodingException {
        try {
            return toString(Charset.forName(str));
        } catch (UnsupportedCharsetException e) {
            UnsupportedEncodingException unsupportedEncodingException = new UnsupportedEncodingException(str);
            unsupportedEncodingException.initCause(e);
            throw unsupportedEncodingException;
        }
    }

    public abstract String toStringInternal(Charset charset);

    public final String toStringUtf8() {
        return toString(Internal.f1182a);
    }

    public abstract void writeTo(KN kn) throws IOException;

    public abstract void writeTo(OutputStream outputStream) throws IOException;

    public final void writeTo(OutputStream outputStream, int i, int i2) throws IOException {
        checkRange(i, i + i2, size());
        if (i2 > 0) {
            writeToInternal(outputStream, i, i2);
        }
    }

    public abstract void writeToInternal(OutputStream outputStream, int i, int i2) throws IOException;

    public static ByteString copyFrom(byte[] bArr) {
        return copyFrom(bArr, 0, bArr.length);
    }

    public static C0279e newOutput() {
        return new C0279e(CONCATENATE_BY_COPY_SIZE);
    }

    public static ByteString readFrom(InputStream inputStream, int i) throws IOException {
        return readFrom(inputStream, i, i);
    }

    public static ByteString wrap(byte[] bArr, int i, int i2) {
        return new BoundedByteString(bArr, i, i2);
    }

    public final void copyTo(byte[] bArr, int i, int i2, int i3) {
        checkRange(i, i + i3, size());
        checkRange(i2, i2 + i3, bArr.length);
        if (i3 > 0) {
            copyToInternal(bArr, i, i2, i3);
        }
    }

    public final ByteIterator iterator() {
        return new C0275a();
    }

    public static ByteString copyFrom(ByteBuffer byteBuffer, int i) {
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        return new LiteralByteString(bArr);
    }

    public static ByteString readFrom(InputStream inputStream, int i, int i2) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (true) {
            ByteString readChunk = readChunk(inputStream, i);
            if (readChunk == null) {
                return copyFrom((Iterable<ByteString>) arrayList);
            }
            arrayList.add(readChunk);
            i = Math.min(i * 2, i2);
        }
    }

    public final String toString(Charset charset) {
        return size() == 0 ? BuildConfig.FLAVOR : toStringInternal(charset);
    }

    public static ByteString copyFrom(ByteBuffer byteBuffer) {
        return copyFrom(byteBuffer, byteBuffer.remaining());
    }

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size())});
    }

    public static ByteString copyFrom(String str, String str2) throws UnsupportedEncodingException {
        return new LiteralByteString(str.getBytes(str2));
    }

    public static ByteString copyFrom(String str, Charset charset) {
        return new LiteralByteString(str.getBytes(charset));
    }

    public static ByteString copyFrom(Iterable<ByteString> iterable) {
        int i;
        if (!(iterable instanceof Collection)) {
            i = 0;
            Iterator<ByteString> it = iterable.iterator();
            while (it.hasNext()) {
                it.next();
                i++;
            }
        } else {
            i = ((Collection) iterable).size();
        }
        if (i == 0) {
            return EMPTY;
        }
        return balancedConcat(iterable.iterator(), i);
    }
}
