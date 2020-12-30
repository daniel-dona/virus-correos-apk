package com.facebook.imagepipeline.memory;

import java.io.IOException;

/* compiled from: PG */
public class NativePooledByteBufferOutputStream extends Bq {

    /* renamed from: a */
    public final qu f370a;

    /* renamed from: b */
    public Eq<NativeMemoryChunk> f371b;

    /* renamed from: c */
    public int f372c;

    /* compiled from: PG */
    public static class InvalidStreamException extends RuntimeException {
        public InvalidStreamException() {
            super("OutputStream no longer valid");
        }
    }

    public NativePooledByteBufferOutputStream(qu quVar, int i) {
        kq.a(i > 0);
        if (quVar != null) {
            this.f370a = quVar;
            this.f372c = 0;
            this.f371b = Eq.a(this.f370a.mo502c(i), this.f370a);
            return;
        }
        throw new NullPointerException();
    }

    /* renamed from: a */
    public final void mo525a() {
        if (!Eq.c(this.f371b)) {
            throw new InvalidStreamException();
        }
    }

    /* renamed from: b */
    public ru mo526b() {
        mo525a();
        return new ru(this.f371b, this.f372c);
    }

    public void close() {
        Eq.b(this.f371b);
        this.f371b = null;
        this.f372c = -1;
        NativePooledByteBufferOutputStream.super.close();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.io.OutputStream, com.facebook.imagepipeline.memory.NativePooledByteBufferOutputStream] */
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i});
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            StringBuilder a = Eo.a("length=");
            a.append(bArr.length);
            a.append("; regionStart=");
            a.append(i);
            a.append("; regionLength=");
            a.append(i2);
            throw new ArrayIndexOutOfBoundsException(a.toString());
        }
        mo525a();
        int i3 = this.f372c + i2;
        mo525a();
        if (i3 > ((NativeMemoryChunk) this.f371b.b()).mo516a()) {
            NativeMemoryChunk nativeMemoryChunk = (NativeMemoryChunk) this.f370a.mo502c(i3);
            ((NativeMemoryChunk) this.f371b.b()).mo519a(0, nativeMemoryChunk, 0, this.f372c);
            this.f371b.close();
            this.f371b = Eq.a(nativeMemoryChunk, this.f370a);
        }
        ((NativeMemoryChunk) this.f371b.b()).mo520b(this.f372c, bArr, i, i2);
        this.f372c += i2;
    }

    public NativePooledByteBufferOutputStream(qu quVar) {
        this(quVar, quVar.j[0]);
    }
}
