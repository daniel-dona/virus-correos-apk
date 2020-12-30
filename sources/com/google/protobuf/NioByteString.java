package com.google.protobuf;

import com.google.protobuf.ByteString;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.InvalidMarkException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

/* compiled from: PG */
public final class NioByteString extends ByteString.LeafByteString {
    public final ByteBuffer buffer;

    public NioByteString(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            this.buffer = byteBuffer.slice().order(ByteOrder.nativeOrder());
            return;
        }
        throw new NullPointerException("buffer");
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        throw new InvalidObjectException("NioByteString instances are not to be serialized directly");
    }

    private ByteBuffer slice(int i, int i2) {
        if (i < this.buffer.position() || i2 > this.buffer.limit() || i > i2) {
            throw new IllegalArgumentException(String.format("Invalid indices [%d, %d]", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        }
        ByteBuffer slice = this.buffer.slice();
        slice.position(i - this.buffer.position());
        slice.limit(i2 - this.buffer.position());
        return slice;
    }

    private Object writeReplace() {
        return ByteString.copyFrom(this.buffer.slice());
    }

    public ByteBuffer asReadOnlyByteBuffer() {
        return this.buffer.asReadOnlyBuffer();
    }

    public List<ByteBuffer> asReadOnlyByteBufferList() {
        return Collections.singletonList(asReadOnlyByteBuffer());
    }

    public byte byteAt(int i) {
        try {
            return this.buffer.get(i);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        } catch (IndexOutOfBoundsException e2) {
            throw new ArrayIndexOutOfBoundsException(e2.getMessage());
        }
    }

    public void copyTo(ByteBuffer byteBuffer) {
        byteBuffer.put(this.buffer.slice());
    }

    public void copyToInternal(byte[] bArr, int i, int i2, int i3) {
        ByteBuffer slice = this.buffer.slice();
        slice.position(i);
        slice.get(bArr, i2, i3);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByteString)) {
            return false;
        }
        ByteString byteString = (ByteString) obj;
        if (size() != byteString.size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (obj instanceof NioByteString) {
            return this.buffer.equals(((NioByteString) obj).buffer);
        }
        if (obj instanceof RopeByteString) {
            return obj.equals(this);
        }
        return this.buffer.equals(byteString.asReadOnlyByteBuffer());
    }

    public boolean equalsRange(ByteString byteString, int i, int i2) {
        return substring(0, i2).equals(byteString.substring(i, i2 + i));
    }

    public boolean isValidUtf8() {
        ByteBuffer byteBuffer = this.buffer;
        if (Utf8.f1198a.a(0, byteBuffer, byteBuffer.position(), byteBuffer.remaining()) == 0) {
            return true;
        }
        return false;
    }

    public LN newCodedInput() {
        ByteBuffer byteBuffer = this.buffer;
        if (byteBuffer.hasArray()) {
            return LN.a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.remaining());
        }
        ByteBuffer duplicate = byteBuffer.duplicate();
        byte[] bArr = new byte[duplicate.remaining()];
        duplicate.get(bArr);
        return LN.a(bArr, 0, bArr.length);
    }

    public InputStream newInput() {
        return new C0285a();
    }

    public int partialHash(int i, int i2, int i3) {
        int i4 = i;
        for (int i5 = i2; i5 < i2 + i3; i5++) {
            i4 = (i4 * 31) + this.buffer.get(i5);
        }
        return i4;
    }

    public int partialIsValidUtf8(int i, int i2, int i3) {
        return Utf8.f1198a.a(i, this.buffer, i2, i3 + i2);
    }

    public int size() {
        return this.buffer.remaining();
    }

    public ByteString substring(int i, int i2) {
        try {
            return new NioByteString(slice(i, i2));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        } catch (IndexOutOfBoundsException e2) {
            throw new ArrayIndexOutOfBoundsException(e2.getMessage());
        }
    }

    public String toStringInternal(Charset charset) {
        int i;
        int i2;
        byte[] bArr;
        if (this.buffer.hasArray()) {
            bArr = this.buffer.array();
            i = this.buffer.position() + this.buffer.arrayOffset();
            i2 = this.buffer.remaining();
        } else {
            bArr = toByteArray();
            i = 0;
            i2 = bArr.length;
        }
        return new String(bArr, i, i2, charset);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(toByteArray());
    }

    public void writeToInternal(OutputStream outputStream, int i, int i2) throws IOException {
        if (this.buffer.hasArray()) {
            outputStream.write(this.buffer.array(), this.buffer.position() + this.buffer.arrayOffset() + i, i2);
            return;
        }
        JN.a(slice(i, i2 + i), outputStream);
    }

    /* renamed from: com.google.protobuf.NioByteString$a */
    /* compiled from: PG */
    public class C0285a extends InputStream {

        /* renamed from: a */
        public final ByteBuffer f1186a = NioByteString.this.buffer.slice();

        public C0285a() {
        }

        public int available() throws IOException {
            return this.f1186a.remaining();
        }

        public void mark(int i) {
            this.f1186a.mark();
        }

        public boolean markSupported() {
            return true;
        }

        public int read() throws IOException {
            if (!this.f1186a.hasRemaining()) {
                return -1;
            }
            return this.f1186a.get() & 255;
        }

        public void reset() throws IOException {
            try {
                this.f1186a.reset();
            } catch (InvalidMarkException e) {
                throw new IOException(e);
            }
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (!this.f1186a.hasRemaining()) {
                return -1;
            }
            int min = Math.min(i2, this.f1186a.remaining());
            this.f1186a.get(bArr, i, min);
            return min;
        }
    }

    public void writeTo(KN kn) throws IOException {
        kn.a(this.buffer.slice());
    }
}
