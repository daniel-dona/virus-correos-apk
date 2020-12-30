package com.facebook.imagepipeline.memory;

import android.util.Log;
import java.io.Closeable;

@gq
/* compiled from: PG */
public class NativeMemoryChunk implements Closeable {

    /* renamed from: a */
    public final long f367a;

    /* renamed from: b */
    public final int f368b;

    /* renamed from: c */
    public boolean f369c;

    static {
        Bu.a();
    }

    public NativeMemoryChunk(int i) {
        kq.a(i > 0);
        this.f368b = i;
        this.f367a = nativeAllocate(this.f368b);
        this.f369c = false;
    }

    @gq
    public static native long nativeAllocate(int i);

    @gq
    public static native void nativeCopyFromByteArray(long j, byte[] bArr, int i, int i2);

    @gq
    public static native void nativeCopyToByteArray(long j, byte[] bArr, int i, int i2);

    @gq
    public static native void nativeFree(long j);

    @gq
    public static native void nativeMemcpy(long j, long j2, int i);

    @gq
    public static native byte nativeReadByte(long j);

    /* renamed from: a */
    public int mo516a() {
        return this.f368b;
    }

    /* renamed from: b */
    public synchronized int mo520b(int i, byte[] bArr, int i2, int i3) {
        int min;
        if (bArr != null) {
            kq.b(!isClosed());
            min = Math.min(Math.max(0, this.f368b - i), i3);
            mo518a(i, bArr.length, i2, min);
            nativeCopyFromByteArray(this.f367a + ((long) i), bArr, i2, min);
        } else {
            throw new NullPointerException();
        }
        return min;
    }

    public synchronized void close() {
        if (!this.f369c) {
            this.f369c = true;
            nativeFree(this.f367a);
        }
    }

    public void finalize() throws Throwable {
        if (!isClosed()) {
            StringBuilder a = Eo.a("finalize: Chunk ");
            a.append(Integer.toHexString(System.identityHashCode(this)));
            a.append(" still active. Underlying address = ");
            a.append(Long.toHexString(this.f367a));
            Log.w("NativeMemoryChunk", a.toString());
            try {
                close();
            } finally {
                super.finalize();
            }
        }
    }

    public synchronized boolean isClosed() {
        return this.f369c;
    }

    /* renamed from: a */
    public synchronized int mo517a(int i, byte[] bArr, int i2, int i3) {
        int min;
        if (bArr != null) {
            kq.b(!isClosed());
            min = Math.min(Math.max(0, this.f368b - i), i3);
            mo518a(i, bArr.length, i2, min);
            nativeCopyToByteArray(this.f367a + ((long) i), bArr, i2, min);
        } else {
            throw new NullPointerException();
        }
        return min;
    }

    public NativeMemoryChunk() {
        this.f368b = 0;
        this.f367a = 0;
        this.f369c = true;
    }

    /* renamed from: b */
    public final void mo521b(int i, NativeMemoryChunk nativeMemoryChunk, int i2, int i3) {
        kq.b(!isClosed());
        kq.b(!nativeMemoryChunk.isClosed());
        mo518a(i, nativeMemoryChunk.f368b, i2, i3);
        nativeMemcpy(nativeMemoryChunk.f367a + ((long) i2), this.f367a + ((long) i), i3);
    }

    /* renamed from: a */
    public synchronized byte mo515a(int i) {
        boolean z = true;
        kq.b(!isClosed());
        kq.a(i >= 0);
        if (i >= this.f368b) {
            z = false;
        }
        kq.a(z);
        return nativeReadByte(this.f367a + ((long) i));
    }

    /* renamed from: a */
    public void mo519a(int i, NativeMemoryChunk nativeMemoryChunk, int i2, int i3) {
        if (nativeMemoryChunk != null) {
            if (nativeMemoryChunk.f367a == this.f367a) {
                StringBuilder a = Eo.a("Copying from NativeMemoryChunk ");
                a.append(Integer.toHexString(System.identityHashCode(this)));
                a.append(" to NativeMemoryChunk ");
                a.append(Integer.toHexString(System.identityHashCode(nativeMemoryChunk)));
                a.append(" which share the same address ");
                a.append(Long.toHexString(this.f367a));
                Log.w("NativeMemoryChunk", a.toString());
                kq.a(false);
            }
            if (nativeMemoryChunk.f367a < this.f367a) {
                synchronized (nativeMemoryChunk) {
                    synchronized (this) {
                        mo521b(i, nativeMemoryChunk, i2, i3);
                    }
                }
                return;
            }
            synchronized (this) {
                synchronized (nativeMemoryChunk) {
                    mo521b(i, nativeMemoryChunk, i2, i3);
                }
            }
            return;
        }
        throw new NullPointerException();
    }

    /* renamed from: a */
    public final void mo518a(int i, int i2, int i3, int i4) {
        boolean z = true;
        kq.a(i4 >= 0);
        kq.a(i >= 0);
        kq.a(i3 >= 0);
        kq.a(i + i4 <= this.f368b);
        if (i3 + i4 > i2) {
            z = false;
        }
        kq.a(z);
    }
}
