package com.google.search.now.feed.client;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import java.io.IOException;

/* compiled from: PG */
public final class StreamDataProto$StreamPayload extends GeneratedMessageLite<StreamDataProto$StreamPayload, aQ> implements StreamDataProto$StreamPayloadOrBuilder {

    /* renamed from: p */
    public static final StreamDataProto$StreamPayload f1209p = new StreamDataProto$StreamPayload();

    /* renamed from: q */
    public static volatile eO<StreamDataProto$StreamPayload> f1210q;

    /* renamed from: d */
    public int f1211d;

    /* renamed from: e */
    public int f1212e = 0;

    /* renamed from: k */
    public Object f1213k;

    /* renamed from: n */
    public byte f1214n = -1;

    /* compiled from: PG */
    public enum PayloadCase implements Internal.EnumLite {
        STREAM_FEATURE(3),
        STREAM_SHARED_STATE(4),
        STREAM_TOKEN(5),
        STREAM_SESSIONS(6),
        SEMANTIC_DATA(7),
        CONSISTENCY_TOKEN(9),
        PAYLOAD_NOT_SET(0);
        
        public final int value;

        /* access modifiers changed from: public */
        PayloadCase(int i) {
            this.value = i;
        }

        public static PayloadCase forNumber(int i) {
            if (i == 0) {
                return PAYLOAD_NOT_SET;
            }
            if (i == 9) {
                return CONSISTENCY_TOKEN;
            }
            if (i == 3) {
                return STREAM_FEATURE;
            }
            if (i == 4) {
                return STREAM_SHARED_STATE;
            }
            if (i == 5) {
                return STREAM_TOKEN;
            }
            if (i == 6) {
                return STREAM_SESSIONS;
            }
            if (i != 7) {
                return null;
            }
            return SEMANTIC_DATA;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static PayloadCase valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        f1209p.mo2496i();
    }

    /* JADX WARNING: type inference failed for: r0v16, types: [com.google.search.now.feed.client.StreamDataProto$StreamSharedState, com.google.protobuf.MessageLite] */
    /* JADX WARNING: type inference failed for: r0v18, types: [com.google.protobuf.MessageLite, com.google.search.now.feed.client.StreamDataProto$StreamFeature] */
    /* renamed from: a */
    public void mo2692a(CodedOutputStream codedOutputStream) throws IOException {
        if (this.f1212e == 3) {
            codedOutputStream.mo2454b(3, (MessageLite) (StreamDataProto$StreamFeature) this.f1213k);
        }
        if (this.f1212e == 4) {
            codedOutputStream.mo2454b(4, (MessageLite) (StreamDataProto$StreamSharedState) this.f1213k);
        }
        if (this.f1212e == 5) {
            codedOutputStream.mo2454b(5, (MessageLite) (hQ) this.f1213k);
        }
        if (this.f1212e == 6) {
            codedOutputStream.mo2454b(6, (MessageLite) (eQ) this.f1213k);
        }
        if (this.f1212e == 7) {
            codedOutputStream.mo2441a(7, (ByteString) this.f1213k);
        }
        if (this.f1212e == 9) {
            codedOutputStream.mo2454b(9, (MessageLite) (VS) this.f1213k);
        }
        this.f1168b.a(codedOutputStream);
    }

    /* JADX WARNING: type inference failed for: r1v23, types: [com.google.search.now.feed.client.StreamDataProto$StreamSharedState, com.google.protobuf.MessageLite] */
    /* JADX WARNING: type inference failed for: r1v26, types: [com.google.protobuf.MessageLite, com.google.search.now.feed.client.StreamDataProto$StreamFeature] */
    /* renamed from: b */
    public int mo2693b() {
        int i = this.f1169c;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.f1212e == 3) {
            i2 = 0 + CodedOutputStream.m1073c(3, (MessageLite) (StreamDataProto$StreamFeature) this.f1213k);
        }
        if (this.f1212e == 4) {
            i2 += CodedOutputStream.m1073c(4, (MessageLite) (StreamDataProto$StreamSharedState) this.f1213k);
        }
        if (this.f1212e == 5) {
            i2 += CodedOutputStream.m1073c(5, (MessageLite) (hQ) this.f1213k);
        }
        if (this.f1212e == 6) {
            i2 += CodedOutputStream.m1073c(6, (MessageLite) (eQ) this.f1213k);
        }
        if (this.f1212e == 7) {
            i2 += CodedOutputStream.m1065b(7, (ByteString) this.f1213k);
        }
        if (this.f1212e == 9) {
            i2 += CodedOutputStream.m1073c(9, (MessageLite) (VS) this.f1213k);
        }
        int a = this.f1168b.a() + i2;
        this.f1169c = a;
        return a;
    }

    public VS getConsistencyToken() {
        if (this.f1212e == 9) {
            return (VS) this.f1213k;
        }
        return VS.k;
    }

    public PayloadCase getPayloadCase() {
        return PayloadCase.forNumber(this.f1212e);
    }

    public ByteString getSemanticData() {
        if (this.f1212e == 7) {
            return (ByteString) this.f1213k;
        }
        return ByteString.EMPTY;
    }

    public StreamDataProto$StreamFeature getStreamFeature() {
        if (this.f1212e == 3) {
            return (StreamDataProto$StreamFeature) this.f1213k;
        }
        return StreamDataProto$StreamFeature.f1201x;
    }

    public eQ getStreamSessions() {
        if (this.f1212e == 6) {
            return (eQ) this.f1213k;
        }
        return eQ.e;
    }

    public StreamDataProto$StreamSharedState getStreamSharedState() {
        if (this.f1212e == 4) {
            return (StreamDataProto$StreamSharedState) this.f1213k;
        }
        return StreamDataProto$StreamSharedState.f1215q;
    }

    public hQ getStreamToken() {
        if (this.f1212e == 5) {
            return (hQ) this.f1213k;
        }
        return hQ.p;
    }

    public boolean hasConsistencyToken() {
        return this.f1212e == 9;
    }

    public boolean hasSemanticData() {
        return this.f1212e == 7;
    }

    public boolean hasStreamFeature() {
        return this.f1212e == 3;
    }

    public boolean hasStreamSessions() {
        return this.f1212e == 6;
    }

    public boolean hasStreamSharedState() {
        return this.f1212e == 4;
    }

    public boolean hasStreamToken() {
        return this.f1212e == 5;
    }

    /* JADX WARNING: type inference failed for: r10v0, types: [com.google.protobuf.MessageLite, com.google.protobuf.GeneratedMessageLite, java.lang.Object, com.google.search.now.feed.client.StreamDataProto$StreamPayload] */
    /* renamed from: a */
    public final Object mo2486a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        StreamDataProto$StreamSharedState streamDataProto$StreamSharedState;
        StreamDataProto$StreamFeature streamDataProto$StreamFeature;
        boolean z = false;
        switch (methodToInvoke.ordinal()) {
            case 0:
                byte b = this.f1214n;
                if (b == 1) {
                    return f1209p;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (this.f1212e == 3) {
                    if (this.f1212e == 3) {
                        streamDataProto$StreamFeature = (StreamDataProto$StreamFeature) this.f1213k;
                    } else {
                        streamDataProto$StreamFeature = StreamDataProto$StreamFeature.f1201x;
                    }
                    if (!streamDataProto$StreamFeature.isInitialized()) {
                        if (booleanValue) {
                            this.f1214n = 0;
                        }
                        return null;
                    }
                }
                if (this.f1212e == 4) {
                    if (this.f1212e == 4) {
                        streamDataProto$StreamSharedState = (StreamDataProto$StreamSharedState) this.f1213k;
                    } else {
                        streamDataProto$StreamSharedState = StreamDataProto$StreamSharedState.f1215q;
                    }
                    if (!streamDataProto$StreamSharedState.isInitialized()) {
                        if (booleanValue) {
                            this.f1214n = 0;
                        }
                        return null;
                    }
                }
                if (booleanValue) {
                    this.f1214n = 1;
                }
                return f1209p;
            case 1:
                XN xn = (GeneratedMessageLite.Visitor) obj;
                StreamDataProto$StreamPayload streamDataProto$StreamPayload = (StreamDataProto$StreamPayload) obj2;
                switch (PayloadCase.forNumber(streamDataProto$StreamPayload.f1212e).ordinal()) {
                    case 0:
                        if (this.f1212e == 3) {
                            z = true;
                        }
                        this.f1213k = xn.visitOneofMessage(z, this.f1213k, streamDataProto$StreamPayload.f1213k);
                        break;
                    case 1:
                        if (this.f1212e == 4) {
                            z = true;
                        }
                        this.f1213k = xn.visitOneofMessage(z, this.f1213k, streamDataProto$StreamPayload.f1213k);
                        break;
                    case 2:
                        if (this.f1212e == 5) {
                            z = true;
                        }
                        this.f1213k = xn.visitOneofMessage(z, this.f1213k, streamDataProto$StreamPayload.f1213k);
                        break;
                    case 3:
                        if (this.f1212e == 6) {
                            z = true;
                        }
                        this.f1213k = xn.visitOneofMessage(z, this.f1213k, streamDataProto$StreamPayload.f1213k);
                        break;
                    case 4:
                        if (this.f1212e == 7) {
                            z = true;
                        }
                        this.f1213k = xn.visitOneofByteString(z, this.f1213k, streamDataProto$StreamPayload.f1213k);
                        break;
                    case 5:
                        if (this.f1212e == 9) {
                            z = true;
                        }
                        this.f1213k = xn.visitOneofMessage(z, this.f1213k, streamDataProto$StreamPayload.f1213k);
                        break;
                    case 6:
                        if (this.f1212e != 0) {
                            z = true;
                        }
                        xn.visitOneofNotSet(z);
                        break;
                }
                if (xn == XN.a) {
                    int i = streamDataProto$StreamPayload.f1212e;
                    if (i != 0) {
                        this.f1212e = i;
                    }
                    this.f1211d |= streamDataProto$StreamPayload.f1211d;
                }
                return this;
            case 2:
                LN ln = (LN) obj;
                RN rn = (RN) obj2;
                while (!z) {
                    try {
                        int n = ln.n();
                        if (n != 0) {
                            if (n == 26) {
                                XP a = this.f1212e == 3 ? ((StreamDataProto$StreamFeature) this.f1213k).m1151a() : null;
                                this.f1213k = ln.a(StreamDataProto$StreamFeature.f1201x.mo2494h(), rn);
                                if (a != null) {
                                    a.a((StreamDataProto$StreamFeature) this.f1213k);
                                    this.f1213k = a.buildPartial();
                                }
                                this.f1212e = 3;
                            } else if (n == 34) {
                                fQ a2 = this.f1212e == 4 ? ((StreamDataProto$StreamSharedState) this.f1213k).m1151a() : null;
                                this.f1213k = ln.a(StreamDataProto$StreamSharedState.f1215q.mo2494h(), rn);
                                if (a2 != null) {
                                    a2.a((StreamDataProto$StreamSharedState) this.f1213k);
                                    this.f1213k = a2.buildPartial();
                                }
                                this.f1212e = 4;
                            } else if (n == 42) {
                                gQ a3 = this.f1212e == 5 ? ((hQ) this.f1213k).m1151a() : null;
                                this.f1213k = ln.a(hQ.p.mo2494h(), rn);
                                if (a3 != null) {
                                    a3.a((hQ) this.f1213k);
                                    this.f1213k = a3.buildPartial();
                                }
                                this.f1212e = 5;
                            } else if (n == 50) {
                                dQ a4 = this.f1212e == 6 ? ((eQ) this.f1213k).m1151a() : null;
                                this.f1213k = ln.a(eQ.e.mo2494h(), rn);
                                if (a4 != null) {
                                    a4.a((eQ) this.f1213k);
                                    this.f1213k = a4.buildPartial();
                                }
                                this.f1212e = 6;
                            } else if (n == 58) {
                                this.f1212e = 7;
                                this.f1213k = ln.c();
                            } else if (n == 74) {
                                US a5 = this.f1212e == 9 ? ((VS) this.f1213k).m1151a() : null;
                                this.f1213k = ln.a(VS.k.mo2494h(), rn);
                                if (a5 != null) {
                                    a5.a((VS) this.f1213k);
                                    this.f1213k = a5.buildPartial();
                                }
                                this.f1212e = 9;
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
                return new StreamDataProto$StreamPayload();
            case 5:
                return new aQ((UP) null);
            case 6:
                break;
            case 7:
                if (f1210q == null) {
                    synchronized (StreamDataProto$StreamPayload.class) {
                        if (f1210q == null) {
                            f1210q = new UN(f1209p);
                        }
                    }
                }
                return f1210q;
            default:
                throw new UnsupportedOperationException();
        }
        return f1209p;
    }
}
