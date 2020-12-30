package org.chromium.mojo.system.impl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import nk3;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;
import org.chromium.mojo.system.DataPipe$ConsumerHandle;
import org.chromium.mojo.system.DataPipe$ProducerHandle;
import org.chromium.mojo.system.MojoException;
import org.chromium.mojo.system.ResultAnd;
import org.chromium.mojo.system.Watcher;
import qk3;

@MainDex
/* compiled from: PG */
public class CoreImpl implements fk3 {

    /* renamed from: a */
    public final ThreadLocal<BaseRunLoop> f2528a = new ThreadLocal<>();

    /* renamed from: b */
    public final int f2529b = nativeGetNativeBufferOffset(ByteBuffer.allocateDirect(8), 8);

    /* renamed from: org.chromium.mojo.system.impl.CoreImpl$b */
    /* compiled from: PG */
    public static final class C0462b extends ok3<Integer, Integer> {
        public C0462b(Integer num, Integer num2) {
            super(num, num2);
        }
    }

    /* renamed from: org.chromium.mojo.system.impl.CoreImpl$c */
    /* compiled from: PG */
    public static class C0463c {

        /* renamed from: a */
        public static final fk3 f2530a = new CoreImpl((C0461a) null);
    }

    public /* synthetic */ CoreImpl(C0461a aVar) {
    }

    private native ResultAnd<ByteBuffer> nativeBeginReadData(int i, int i2, int i3);

    private native ResultAnd<ByteBuffer> nativeBeginWriteData(int i, int i2, int i3);

    private native int nativeClose(int i);

    private native ResultAnd<C0462b> nativeCreateDataPipe(ByteBuffer byteBuffer);

    private native ResultAnd<C0462b> nativeCreateMessagePipe(ByteBuffer byteBuffer);

    private native ResultAnd<Integer> nativeCreateSharedBuffer(ByteBuffer byteBuffer, long j);

    private native ResultAnd<Integer> nativeDuplicate(int i, ByteBuffer byteBuffer);

    private native int nativeEndReadData(int i, int i2);

    private native int nativeEndWriteData(int i, int i2);

    private native int nativeGetNativeBufferOffset(ByteBuffer byteBuffer, int i);

    private native long nativeGetTimeTicksNow();

    private native ResultAnd<ByteBuffer> nativeMap(int i, long j, long j2, int i2);

    private native int nativeQueryHandleSignalsState(int i, ByteBuffer byteBuffer);

    private native ResultAnd<Integer> nativeReadData(int i, ByteBuffer byteBuffer, int i2, int i3);

    private native ResultAnd<nk3.d> nativeReadMessage(int i, int i2);

    private native int nativeUnmap(ByteBuffer byteBuffer);

    private native ResultAnd<Integer> nativeWriteData(int i, ByteBuffer byteBuffer, int i2, int i3);

    private native int nativeWriteMessage(int i, ByteBuffer byteBuffer, int i2, ByteBuffer byteBuffer2, int i3);

    @CalledByNative
    public static ResultAnd<C0462b> newNativeCreationResult(int i, int i2, int i3) {
        return new ResultAnd<>(i, new C0462b(Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    @CalledByNative
    public static ResultAnd<nk3.d> newReadMessageResult(int i, byte[] bArr, int[] iArr) {
        nk3.d dVar = new nk3.d();
        if (i == 0) {
            dVar.a = bArr;
            dVar.b = iArr;
        }
        return new ResultAnd<>(i, dVar);
    }

    @CalledByNative
    public static ResultAnd<ByteBuffer> newResultAndBuffer(int i, ByteBuffer byteBuffer) {
        return new ResultAnd<>(i, byteBuffer);
    }

    @CalledByNative
    public static ResultAnd<Integer> newResultAndInteger(int i, int i2) {
        return new ResultAnd<>(i, Integer.valueOf(i2));
    }

    /* renamed from: a */
    public ok3<nk3, nk3> mo9877a(nk3.b bVar) {
        ByteBuffer byteBuffer;
        if (bVar != null) {
            byteBuffer = mo9888b(8);
            byteBuffer.putInt(0, 8);
            byteBuffer.putInt(4, bVar.a.a);
        } else {
            byteBuffer = null;
        }
        ResultAnd<C0462b> nativeCreateMessagePipe = nativeCreateMessagePipe(byteBuffer);
        if (nativeCreateMessagePipe.mo9867a() == 0) {
            return new ok3<>(new vk3(this, ((Integer) nativeCreateMessagePipe.mo9868b().a).intValue()), new vk3(this, ((Integer) nativeCreateMessagePipe.mo9868b().b).intValue()));
        }
        throw new MojoException(nativeCreateMessagePipe.mo9867a());
    }

    /* renamed from: b */
    public int mo9887b(sk3 sk3, int i, ik3 ik3) {
        ResultAnd<Integer> nativeReadData = nativeReadData(sk3.a, (ByteBuffer) null, i, ik3.a | 2);
        if (nativeReadData.mo9867a() == 0) {
            return nativeReadData.mo9868b().intValue();
        }
        throw new MojoException(nativeReadData.mo9867a());
    }

    /* renamed from: c */
    public void mo9889c(int i) {
        int nativeClose = nativeClose(i);
        if (nativeClose != 0) {
            throw new MojoException(nativeClose);
        }
    }

    /* renamed from: d */
    public int mo9890d(int i) {
        return nativeClose(i);
    }

    /* renamed from: b */
    public final ByteBuffer mo9888b(int i) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i + this.f2529b);
        int i2 = this.f2529b;
        if (i2 != 0) {
            allocateDirect.position(i2);
            allocateDirect = allocateDirect.slice();
        }
        return allocateDirect.order(ByteOrder.nativeOrder());
    }

    /* renamed from: a */
    public ByteBuffer mo9873a(sk3 sk3, int i, ik3 ik3) {
        ResultAnd<ByteBuffer> nativeBeginReadData = nativeBeginReadData(sk3.a, i, ik3.a);
        if (nativeBeginReadData.mo9867a() == 0) {
            return nativeBeginReadData.mo9868b().asReadOnlyBuffer();
        }
        throw new MojoException(nativeBeginReadData.mo9867a());
    }

    /* renamed from: a */
    public ByteBuffer mo9874a(tk3 tk3, int i, jk3 jk3) {
        ResultAnd<ByteBuffer> nativeBeginWriteData = nativeBeginWriteData(tk3.a, i, jk3.a);
        if (nativeBeginWriteData.mo9867a() == 0) {
            return nativeBeginWriteData.mo9868b();
        }
        throw new MojoException(nativeBeginWriteData.mo9867a());
    }

    /* renamed from: a */
    public void mo9884a(sk3 sk3, int i) {
        int nativeEndReadData = nativeEndReadData(sk3.a, i);
        if (nativeEndReadData != 0) {
            throw new MojoException(nativeEndReadData);
        }
    }

    /* renamed from: a */
    public void mo9885a(tk3 tk3, int i) {
        int nativeEndWriteData = nativeEndWriteData(tk3.a, i);
        if (nativeEndWriteData != 0) {
            throw new MojoException(nativeEndWriteData);
        }
    }

    /* renamed from: a */
    public ByteBuffer mo9875a(wk3 wk3, long j, long j2, qk3.c cVar) {
        ResultAnd<ByteBuffer> nativeMap = nativeMap(wk3.a, j, j2, cVar.a);
        if (nativeMap.mo9867a() == 0) {
            return nativeMap.mo9868b();
        }
        throw new MojoException(nativeMap.mo9867a());
    }

    /* renamed from: a */
    public ResultAnd<Integer> mo9878a(sk3 sk3, ByteBuffer byteBuffer, ik3 ik3) {
        int i;
        int i2 = sk3.a;
        if (byteBuffer == null) {
            i = 0;
        } else {
            i = byteBuffer.capacity();
        }
        ResultAnd<Integer> nativeReadData = nativeReadData(i2, byteBuffer, i, ik3.a);
        if (nativeReadData.mo9867a() == 0 || nativeReadData.mo9867a() == 17) {
            if (nativeReadData.mo9867a() == 0 && byteBuffer != null) {
                byteBuffer.limit(nativeReadData.mo9868b().intValue());
            }
            return nativeReadData;
        }
        throw new MojoException(nativeReadData.mo9867a());
    }

    /* renamed from: a */
    public ResultAnd<nk3.d> mo9880a(vk3 vk3, nk3.c cVar) {
        ResultAnd<nk3.d> nativeReadMessage = nativeReadMessage(vk3.a, cVar.a);
        if (nativeReadMessage.mo9867a() == 0 || nativeReadMessage.mo9867a() == 17) {
            nk3.d b = nativeReadMessage.mo9868b();
            int[] iArr = b.b;
            if (iArr == null || iArr.length == 0) {
                b.c = new ArrayList(0);
            } else {
                b.c = new ArrayList(iArr.length);
                for (int xk3 : iArr) {
                    b.c.add(new xk3(this, xk3));
                }
            }
            return nativeReadMessage;
        }
        throw new MojoException(nativeReadMessage.mo9867a());
    }

    /* renamed from: a */
    public ResultAnd<Integer> mo9879a(tk3 tk3, ByteBuffer byteBuffer, jk3 jk3) {
        return nativeWriteData(tk3.a, byteBuffer, byteBuffer.limit(), jk3.a);
    }

    /* renamed from: a */
    public ok3<DataPipe$ProducerHandle, DataPipe$ConsumerHandle> mo9876a(hk3 hk3) {
        ByteBuffer byteBuffer;
        if (hk3 != null) {
            byteBuffer = mo9888b(16);
            byteBuffer.putInt(0, 16);
            byteBuffer.putInt(4, hk3.a.a);
            byteBuffer.putInt(8, hk3.b);
            byteBuffer.putInt(12, hk3.c);
        } else {
            byteBuffer = null;
        }
        ResultAnd<C0462b> nativeCreateDataPipe = nativeCreateDataPipe(byteBuffer);
        if (nativeCreateDataPipe.mo9867a() == 0) {
            return new ok3<>(new tk3(this, ((Integer) nativeCreateDataPipe.mo9868b().a).intValue()), new sk3(this, ((Integer) nativeCreateDataPipe.mo9868b().b).intValue()));
        }
        throw new MojoException(nativeCreateDataPipe.mo9867a());
    }

    /* renamed from: a */
    public qk3 mo9882a(qk3.b bVar, long j) {
        ByteBuffer byteBuffer;
        if (bVar != null) {
            byteBuffer = mo9888b(8);
            byteBuffer.putInt(0, 8);
            byteBuffer.putInt(4, bVar.a.a);
        } else {
            byteBuffer = null;
        }
        ResultAnd<Integer> nativeCreateSharedBuffer = nativeCreateSharedBuffer(byteBuffer, j);
        if (nativeCreateSharedBuffer.mo9867a() == 0) {
            return new wk3(this, nativeCreateSharedBuffer.mo9868b().intValue());
        }
        throw new MojoException(nativeCreateSharedBuffer.mo9867a());
    }

    /* renamed from: a */
    public rk3 mo9883a(int i) {
        return new xk3(this, i);
    }

    /* renamed from: a */
    public Watcher mo9881a() {
        return new WatcherImpl();
    }

    /* renamed from: a */
    public void mo9886a(vk3 vk3, ByteBuffer byteBuffer, List<? extends lk3> list, nk3.e eVar) {
        ByteBuffer byteBuffer2;
        int i;
        if (list == null || list.isEmpty()) {
            byteBuffer2 = null;
        } else {
            byteBuffer2 = mo9888b(list.size() * 4);
            Iterator<? extends lk3> it = list.iterator();
            while (it.hasNext()) {
                uk3 uk3 = (lk3) it.next();
                byteBuffer2.putInt(uk3.isValid() ? uk3.a : 0);
            }
            byteBuffer2.position(0);
        }
        ByteBuffer byteBuffer3 = byteBuffer2;
        int i2 = vk3.a;
        if (byteBuffer == null) {
            i = 0;
        } else {
            i = byteBuffer.limit();
        }
        int nativeWriteMessage = nativeWriteMessage(i2, byteBuffer, i, byteBuffer3, eVar.a);
        if (nativeWriteMessage != 0) {
            throw new MojoException(nativeWriteMessage);
        } else if (list != null) {
            Iterator<? extends lk3> it2 = list.iterator();
            while (it2.hasNext()) {
                uk3 uk32 = (lk3) it2.next();
                if (uk32.isValid()) {
                    uk32.a = 0;
                }
            }
        }
    }
}
