package com.google.search.now.feed.client;

import com.citrix.loggersdk.BuildConfig;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import java.io.IOException;

/* compiled from: PG */
public final class StreamDataProto$StreamSharedState extends GeneratedMessageLite<StreamDataProto$StreamSharedState, fQ> implements StreamDataProto$StreamSharedStateOrBuilder {

    /* renamed from: q */
    public static final StreamDataProto$StreamSharedState f1215q = new StreamDataProto$StreamSharedState();

    /* renamed from: x */
    public static volatile eO<StreamDataProto$StreamSharedState> f1216x;

    /* renamed from: d */
    public int f1217d;

    /* renamed from: e */
    public int f1218e = 0;

    /* renamed from: k */
    public Object f1219k;

    /* renamed from: n */
    public String f1220n = BuildConfig.FLAVOR;

    /* renamed from: p */
    public byte f1221p = -1;

    /* compiled from: PG */
    public enum ShareStateCase implements Internal.EnumLite {
        PIET_SHARED_STATE_ITEM(2),
        SHARESTATE_NOT_SET(0);
        
        public final int value;

        /* access modifiers changed from: public */
        ShareStateCase(int i) {
            this.value = i;
        }

        public static ShareStateCase forNumber(int i) {
            if (i == 0) {
                return SHARESTATE_NOT_SET;
            }
            if (i != 2) {
                return null;
            }
            return PIET_SHARED_STATE_ITEM;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ShareStateCase valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        f1215q.mo2496i();
    }

    /* renamed from: a */
    public void mo2717a(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.f1217d & 1) == 1) {
            codedOutputStream.mo2443a(1, this.f1220n);
        }
        if (this.f1218e == 2) {
            codedOutputStream.mo2454b(2, (MessageLite) (CT) this.f1219k);
        }
        this.f1168b.a(codedOutputStream);
    }

    /* renamed from: b */
    public int mo2718b() {
        int i = this.f1169c;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if ((this.f1217d & 1) == 1) {
            i2 = 0 + CodedOutputStream.m1066b(1, this.f1220n);
        }
        if (this.f1218e == 2) {
            i2 += CodedOutputStream.m1073c(2, (MessageLite) (CT) this.f1219k);
        }
        int a = this.f1168b.a() + i2;
        this.f1169c = a;
        return a;
    }

    public String getContentId() {
        return this.f1220n;
    }

    public ByteString getContentIdBytes() {
        return ByteString.copyFromUtf8(this.f1220n);
    }

    public CT getPietSharedStateItem() {
        if (this.f1218e == 2) {
            return (CT) this.f1219k;
        }
        return CT.p;
    }

    public ShareStateCase getShareStateCase() {
        return ShareStateCase.forNumber(this.f1218e);
    }

    public boolean hasContentId() {
        return (this.f1217d & 1) == 1;
    }

    public boolean hasPietSharedStateItem() {
        return this.f1218e == 2;
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [com.google.search.now.feed.client.StreamDataProto$StreamSharedState, com.google.protobuf.MessageLite, com.google.protobuf.GeneratedMessageLite, java.lang.Object] */
    /* renamed from: a */
    public final Object mo2486a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        CT ct;
        boolean z = false;
        switch (methodToInvoke.ordinal()) {
            case 0:
                byte b = this.f1221p;
                if (b == 1) {
                    return f1215q;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (this.f1218e == 2) {
                    if (this.f1218e == 2) {
                        ct = (CT) this.f1219k;
                    } else {
                        ct = CT.p;
                    }
                    if (!ct.isInitialized()) {
                        if (booleanValue) {
                            this.f1221p = 0;
                        }
                        return null;
                    }
                }
                if (booleanValue) {
                    this.f1221p = 1;
                }
                return f1215q;
            case 1:
                XN xn = (GeneratedMessageLite.Visitor) obj;
                StreamDataProto$StreamSharedState streamDataProto$StreamSharedState = (StreamDataProto$StreamSharedState) obj2;
                this.f1220n = xn.visitString(hasContentId(), this.f1220n, streamDataProto$StreamSharedState.hasContentId(), streamDataProto$StreamSharedState.f1220n);
                int ordinal = ShareStateCase.forNumber(streamDataProto$StreamSharedState.f1218e).ordinal();
                if (ordinal == 0) {
                    if (this.f1218e == 2) {
                        z = true;
                    }
                    this.f1219k = xn.visitOneofMessage(z, this.f1219k, streamDataProto$StreamSharedState.f1219k);
                } else if (ordinal == 1) {
                    if (this.f1218e != 0) {
                        z = true;
                    }
                    xn.visitOneofNotSet(z);
                }
                if (xn == XN.a) {
                    int i = streamDataProto$StreamSharedState.f1218e;
                    if (i != 0) {
                        this.f1218e = i;
                    }
                    this.f1217d |= streamDataProto$StreamSharedState.f1217d;
                }
                return this;
            case 2:
                LN ln = (LN) obj;
                RN rn = (RN) obj2;
                while (!z) {
                    try {
                        int n = ln.n();
                        if (n != 0) {
                            if (n == 10) {
                                String m = ln.m();
                                this.f1217d |= 1;
                                this.f1220n = m;
                            } else if (n == 18) {
                                BT a = this.f1218e == 2 ? ((CT) this.f1219k).m1151a() : null;
                                this.f1219k = ln.a(CT.p.mo2494h(), rn);
                                if (a != null) {
                                    a.a((CT) this.f1219k);
                                    this.f1219k = a.buildPartial();
                                }
                                this.f1218e = 2;
                            } else if (!mo2489a(n, ln)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case 3:
                return null;
            case 4:
                return new StreamDataProto$StreamSharedState();
            case 5:
                return new fQ((UP) null);
            case 6:
                break;
            case 7:
                if (f1216x == null) {
                    synchronized (StreamDataProto$StreamSharedState.class) {
                        if (f1216x == null) {
                            f1216x = new UN(f1215q);
                        }
                    }
                }
                return f1216x;
            default:
                throw new UnsupportedOperationException();
        }
        return f1215q;
    }
}
