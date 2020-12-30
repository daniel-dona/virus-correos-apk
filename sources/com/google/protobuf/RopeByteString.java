package com.google.protobuf;

import com.google.protobuf.ByteString;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/* compiled from: PG */
public final class RopeByteString extends ByteString {
    public static final int[] minLengthByDepth;
    public static final long serialVersionUID = 1;
    public final ByteString left;
    public final int leftLength;
    public final ByteString right;
    public final int totalLength;
    public final int treeDepth;

    /* renamed from: com.google.protobuf.RopeByteString$c */
    /* compiled from: PG */
    public static class C0288c implements Iterator<ByteString.LeafByteString> {

        /* renamed from: a */
        public final Stack<RopeByteString> f1189a = new Stack<>();

        /* renamed from: b */
        public ByteString.LeafByteString f1190b;

        public /* synthetic */ C0288c(ByteString byteString, C0286a aVar) {
            this.f1190b = mo2612a(byteString);
        }

        /* renamed from: a */
        public final ByteString.LeafByteString mo2612a(ByteString byteString) {
            while (byteString instanceof RopeByteString) {
                RopeByteString ropeByteString = (RopeByteString) byteString;
                this.f1189a.push(ropeByteString);
                byteString = ropeByteString.left;
            }
            return (ByteString.LeafByteString) byteString;
        }

        public boolean hasNext() {
            return this.f1190b != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public ByteString.LeafByteString next() {
            ByteString.LeafByteString leafByteString;
            ByteString.LeafByteString leafByteString2 = this.f1190b;
            if (leafByteString2 != null) {
                while (true) {
                    if (!this.f1189a.isEmpty()) {
                        leafByteString = mo2612a(this.f1189a.pop().right);
                        if (!leafByteString.isEmpty()) {
                            break;
                        }
                    } else {
                        leafByteString = null;
                        break;
                    }
                }
                this.f1190b = leafByteString;
                return leafByteString2;
            }
            throw new NoSuchElementException();
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        int i = 1;
        int i2 = 1;
        while (i > 0) {
            arrayList.add(Integer.valueOf(i));
            int i3 = i2 + i;
            i2 = i;
            i = i3;
        }
        arrayList.add(Integer.MAX_VALUE);
        minLengthByDepth = new int[arrayList.size()];
        int i4 = 0;
        while (true) {
            int[] iArr = minLengthByDepth;
            if (i4 < iArr.length) {
                iArr[i4] = ((Integer) arrayList.get(i4)).intValue();
                i4++;
            } else {
                return;
            }
        }
    }

    public static ByteString concatenate(ByteString byteString, ByteString byteString2) {
        if (byteString2.size() == 0) {
            return byteString;
        }
        if (byteString.size() == 0) {
            return byteString2;
        }
        int size = byteString2.size() + byteString.size();
        if (size < 128) {
            return concatenateBytes(byteString, byteString2);
        }
        if (byteString instanceof RopeByteString) {
            RopeByteString ropeByteString = (RopeByteString) byteString;
            if (byteString2.size() + ropeByteString.right.size() < 128) {
                return new RopeByteString(ropeByteString.left, concatenateBytes(ropeByteString.right, byteString2));
            } else if (ropeByteString.left.getTreeDepth() > ropeByteString.right.getTreeDepth() && ropeByteString.getTreeDepth() > byteString2.getTreeDepth()) {
                return new RopeByteString(ropeByteString.left, new RopeByteString(ropeByteString.right, byteString2));
            }
        }
        if (size >= minLengthByDepth[Math.max(byteString.getTreeDepth(), byteString2.getTreeDepth()) + 1]) {
            return new RopeByteString(byteString, byteString2);
        }
        C0287b bVar = new C0287b((C0286a) null);
        bVar.mo2611a(byteString);
        bVar.mo2611a(byteString2);
        ByteString pop = bVar.f1188a.pop();
        while (!bVar.f1188a.isEmpty()) {
            pop = new RopeByteString(bVar.f1188a.pop(), pop);
        }
        return pop;
    }

    public static ByteString concatenateBytes(ByteString byteString, ByteString byteString2) {
        int size = byteString.size();
        int size2 = byteString2.size();
        byte[] bArr = new byte[(size + size2)];
        byteString.copyTo(bArr, 0, 0, size);
        byteString2.copyTo(bArr, 0, size, size2);
        return ByteString.wrap(bArr);
    }

    private boolean equalsFragments(ByteString byteString) {
        boolean z;
        C0288c cVar = new C0288c(this, (C0286a) null);
        ByteString.LeafByteString leafByteString = (ByteString.LeafByteString) cVar.next();
        C0288c cVar2 = new C0288c(byteString, (C0286a) null);
        ByteString.LeafByteString leafByteString2 = (ByteString.LeafByteString) cVar2.next();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int size = leafByteString.size() - i;
            int size2 = leafByteString2.size() - i2;
            int min = Math.min(size, size2);
            if (i == 0) {
                z = leafByteString.equalsRange(leafByteString2, i2, min);
            } else {
                z = leafByteString2.equalsRange(leafByteString, i, min);
            }
            if (!z) {
                return false;
            }
            i3 += min;
            int i4 = this.totalLength;
            if (i3 < i4) {
                if (min == size) {
                    leafByteString = (ByteString.LeafByteString) cVar.next();
                    i = 0;
                } else {
                    i += min;
                }
                if (min == size2) {
                    leafByteString2 = (ByteString.LeafByteString) cVar2.next();
                    i2 = 0;
                } else {
                    i2 += min;
                }
            } else if (i3 == i4) {
                return true;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public static RopeByteString newInstanceForTest(ByteString byteString, ByteString byteString2) {
        return new RopeByteString(byteString, byteString2);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        throw new InvalidObjectException("RopeByteStream instances are not to be serialized directly");
    }

    public ByteBuffer asReadOnlyByteBuffer() {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    public List<ByteBuffer> asReadOnlyByteBufferList() {
        ArrayList arrayList = new ArrayList();
        C0288c cVar = new C0288c(this, (C0286a) null);
        while (cVar.hasNext()) {
            arrayList.add(cVar.next().asReadOnlyByteBuffer());
        }
        return arrayList;
    }

    public byte byteAt(int i) {
        ByteString.checkIndex(i, this.totalLength);
        int i2 = this.leftLength;
        if (i < i2) {
            return this.left.byteAt(i);
        }
        return this.right.byteAt(i - i2);
    }

    public void copyTo(ByteBuffer byteBuffer) {
        this.left.copyTo(byteBuffer);
        this.right.copyTo(byteBuffer);
    }

    public void copyToInternal(byte[] bArr, int i, int i2, int i3) {
        int i4 = i + i3;
        int i5 = this.leftLength;
        if (i4 <= i5) {
            this.left.copyToInternal(bArr, i, i2, i3);
        } else if (i >= i5) {
            this.right.copyToInternal(bArr, i - i5, i2, i3);
        } else {
            int i6 = i5 - i;
            this.left.copyToInternal(bArr, i, i2, i6);
            this.right.copyToInternal(bArr, 0, i2 + i6, i3 - i6);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByteString)) {
            return false;
        }
        ByteString byteString = (ByteString) obj;
        if (this.totalLength != byteString.size()) {
            return false;
        }
        if (this.totalLength == 0) {
            return true;
        }
        int peekCachedHashCode = peekCachedHashCode();
        int peekCachedHashCode2 = byteString.peekCachedHashCode();
        if (peekCachedHashCode == 0 || peekCachedHashCode2 == 0 || peekCachedHashCode == peekCachedHashCode2) {
            return equalsFragments(byteString);
        }
        return false;
    }

    public int getTreeDepth() {
        return this.treeDepth;
    }

    public boolean isBalanced() {
        return this.totalLength >= minLengthByDepth[this.treeDepth];
    }

    public boolean isValidUtf8() {
        int partialIsValidUtf8 = this.left.partialIsValidUtf8(0, 0, this.leftLength);
        ByteString byteString = this.right;
        if (byteString.partialIsValidUtf8(partialIsValidUtf8, 0, byteString.size()) == 0) {
            return true;
        }
        return false;
    }

    public LN newCodedInput() {
        return LN.a(new C0289d());
    }

    public InputStream newInput() {
        return new C0289d();
    }

    public int partialHash(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.leftLength;
        if (i4 <= i5) {
            return this.left.partialHash(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.right.partialHash(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.right.partialHash(this.left.partialHash(i, i2, i6), 0, i3 - i6);
    }

    public int partialIsValidUtf8(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.leftLength;
        if (i4 <= i5) {
            return this.left.partialIsValidUtf8(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.right.partialIsValidUtf8(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.right.partialIsValidUtf8(this.left.partialIsValidUtf8(i, i2, i6), 0, i3 - i6);
    }

    public int size() {
        return this.totalLength;
    }

    public ByteString substring(int i, int i2) {
        int checkRange = ByteString.checkRange(i, i2, this.totalLength);
        if (checkRange == 0) {
            return ByteString.EMPTY;
        }
        if (checkRange == this.totalLength) {
            return this;
        }
        int i3 = this.leftLength;
        if (i2 <= i3) {
            return this.left.substring(i, i2);
        }
        if (i >= i3) {
            return this.right.substring(i - i3, i2 - i3);
        }
        return new RopeByteString(this.left.substring(i), this.right.substring(0, i2 - this.leftLength));
    }

    public String toStringInternal(Charset charset) {
        return new String(toByteArray(), charset);
    }

    public Object writeReplace() {
        return ByteString.wrap(toByteArray());
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        this.left.writeTo(outputStream);
        this.right.writeTo(outputStream);
    }

    public void writeToInternal(OutputStream outputStream, int i, int i2) throws IOException {
        int i3 = i + i2;
        int i4 = this.leftLength;
        if (i3 <= i4) {
            this.left.writeToInternal(outputStream, i, i2);
        } else if (i >= i4) {
            this.right.writeToInternal(outputStream, i - i4, i2);
        } else {
            int i5 = i4 - i;
            this.left.writeToInternal(outputStream, i, i5);
            this.right.writeToInternal(outputStream, 0, i2 - i5);
        }
    }

    public RopeByteString(ByteString byteString, ByteString byteString2) {
        this.left = byteString;
        this.right = byteString2;
        this.leftLength = byteString.size();
        this.totalLength = byteString2.size() + this.leftLength;
        this.treeDepth = Math.max(byteString.getTreeDepth(), byteString2.getTreeDepth()) + 1;
    }

    public void writeTo(KN kn) throws IOException {
        this.left.writeTo(kn);
        this.right.writeTo(kn);
    }

    /* renamed from: com.google.protobuf.RopeByteString$d */
    /* compiled from: PG */
    public class C0289d extends InputStream {

        /* renamed from: a */
        public C0288c f1191a;

        /* renamed from: b */
        public ByteString.LeafByteString f1192b;

        /* renamed from: c */
        public int f1193c;

        /* renamed from: d */
        public int f1194d;

        /* renamed from: e */
        public int f1195e;

        /* renamed from: k */
        public int f1196k;

        public C0289d() {
            mo2619b();
        }

        /* renamed from: a */
        public final int mo2616a(byte[] bArr, int i, int i2) {
            int i3 = i;
            int i4 = i2;
            while (true) {
                if (i4 <= 0) {
                    break;
                }
                mo2617a();
                if (this.f1192b != null) {
                    int min = Math.min(this.f1193c - this.f1194d, i4);
                    if (bArr != null) {
                        this.f1192b.copyTo(bArr, this.f1194d, i3, min);
                        i3 += min;
                    }
                    this.f1194d += min;
                    i4 -= min;
                } else if (i4 == i2) {
                    return -1;
                }
            }
            return i2 - i4;
        }

        public int available() throws IOException {
            return RopeByteString.this.size() - (this.f1195e + this.f1194d);
        }

        /* renamed from: b */
        public final void mo2619b() {
            this.f1191a = new C0288c(RopeByteString.this, (C0286a) null);
            this.f1192b = this.f1191a.next();
            this.f1193c = this.f1192b.size();
            this.f1194d = 0;
            this.f1195e = 0;
        }

        public void mark(int i) {
            this.f1196k = this.f1195e + this.f1194d;
        }

        public boolean markSupported() {
            return true;
        }

        public int read(byte[] bArr, int i, int i2) {
            if (bArr == null) {
                throw new NullPointerException();
            } else if (i >= 0 && i2 >= 0 && i2 <= bArr.length - i) {
                return mo2616a(bArr, i, i2);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        public synchronized void reset() {
            mo2619b();
            mo2616a((byte[]) null, 0, this.f1196k);
        }

        public long skip(long j) {
            if (j >= 0) {
                if (j > 2147483647L) {
                    j = 2147483647L;
                }
                return (long) mo2616a((byte[]) null, 0, (int) j);
            }
            throw new IndexOutOfBoundsException();
        }

        public int read() throws IOException {
            mo2617a();
            ByteString.LeafByteString leafByteString = this.f1192b;
            if (leafByteString == null) {
                return -1;
            }
            int i = this.f1194d;
            this.f1194d = i + 1;
            return leafByteString.byteAt(i) & 255;
        }

        /* renamed from: a */
        public final void mo2617a() {
            int i;
            if (this.f1192b != null && this.f1194d == (i = this.f1193c)) {
                this.f1195e += i;
                this.f1194d = 0;
                if (this.f1191a.hasNext()) {
                    this.f1192b = this.f1191a.next();
                    this.f1193c = this.f1192b.size();
                    return;
                }
                this.f1192b = null;
                this.f1193c = 0;
            }
        }
    }

    /* renamed from: com.google.protobuf.RopeByteString$b */
    /* compiled from: PG */
    public static class C0287b {

        /* renamed from: a */
        public final Stack<ByteString> f1188a = new Stack<>();

        public /* synthetic */ C0287b(C0286a aVar) {
        }

        /* renamed from: a */
        public final void mo2611a(ByteString byteString) {
            if (byteString.isBalanced()) {
                int a = mo2610a(byteString.size());
                int i = RopeByteString.minLengthByDepth[a + 1];
                if (this.f1188a.isEmpty() || this.f1188a.peek().size() >= i) {
                    this.f1188a.push(byteString);
                    return;
                }
                int i2 = RopeByteString.minLengthByDepth[a];
                ByteString pop = this.f1188a.pop();
                while (!this.f1188a.isEmpty() && this.f1188a.peek().size() < i2) {
                    pop = new RopeByteString(this.f1188a.pop(), pop);
                }
                RopeByteString ropeByteString = new RopeByteString(pop, byteString);
                while (!this.f1188a.isEmpty()) {
                    if (this.f1188a.peek().size() >= RopeByteString.minLengthByDepth[mo2610a(ropeByteString.size()) + 1]) {
                        break;
                    }
                    ropeByteString = new RopeByteString(this.f1188a.pop(), ropeByteString);
                }
                this.f1188a.push(ropeByteString);
            } else if (byteString instanceof RopeByteString) {
                RopeByteString ropeByteString2 = (RopeByteString) byteString;
                mo2611a(ropeByteString2.left);
                mo2611a(ropeByteString2.right);
            } else {
                StringBuilder a2 = Eo.a("Has a new type of ByteString been created? Found ");
                a2.append(byteString.getClass());
                throw new IllegalArgumentException(a2.toString());
            }
        }

        /* renamed from: a */
        public final int mo2610a(int i) {
            int binarySearch = Arrays.binarySearch(RopeByteString.minLengthByDepth, i);
            return binarySearch < 0 ? (-(binarySearch + 1)) - 1 : binarySearch;
        }
    }
}
