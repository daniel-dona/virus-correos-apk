package com.google.search.now.feed.client;

import GS;
import com.citrix.loggersdk.BuildConfig;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.search.now.ui.stream.StreamStructureProto;
import java.io.IOException;

/* compiled from: PG */
public final class StreamDataProto$StreamFeature extends GeneratedMessageLite<StreamDataProto$StreamFeature, XP> implements StreamDataProto$StreamFeatureOrBuilder {

    /* renamed from: x */
    public static final StreamDataProto$StreamFeature f1201x = new StreamDataProto$StreamFeature();

    /* renamed from: y */
    public static volatile eO<StreamDataProto$StreamFeature> f1202y;

    /* renamed from: d */
    public int f1203d;

    /* renamed from: e */
    public int f1204e = 0;

    /* renamed from: k */
    public Object f1205k;

    /* renamed from: n */
    public String f1206n = BuildConfig.FLAVOR;

    /* renamed from: p */
    public String f1207p = BuildConfig.FLAVOR;

    /* renamed from: q */
    public byte f1208q = -1;

    /* compiled from: PG */
    public enum FeaturePayloadCase implements Internal.EnumLite {
        STREAM(4),
        CARD(5),
        CONTENT(6),
        CLUSTER(7),
        LEGACY_CONTENT(8),
        FEATUREPAYLOAD_NOT_SET(0);
        
        public final int value;

        /* access modifiers changed from: public */
        FeaturePayloadCase(int i) {
            this.value = i;
        }

        public static FeaturePayloadCase forNumber(int i) {
            if (i == 0) {
                return FEATUREPAYLOAD_NOT_SET;
            }
            switch (i) {
                case 4:
                    return STREAM;
                case 5:
                    return CARD;
                case 6:
                    return CONTENT;
                case 7:
                    return CLUSTER;
                case 8:
                    return LEGACY_CONTENT;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static FeaturePayloadCase valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        f1201x.mo2496i();
    }

    /* renamed from: a */
    public void mo2661a(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.f1203d & 1) == 1) {
            codedOutputStream.mo2443a(1, this.f1206n);
        }
        if ((this.f1203d & 2) == 2) {
            codedOutputStream.mo2443a(2, this.f1207p);
        }
        if (this.f1204e == 4) {
            codedOutputStream.mo2454b(4, (MessageLite) (OS) this.f1205k);
        }
        if (this.f1204e == 5) {
            codedOutputStream.mo2454b(5, (MessageLite) (GS) this.f1205k);
        }
        if (this.f1204e == 6) {
            codedOutputStream.mo2454b(6, (MessageLite) (StreamStructureProto.Content) this.f1205k);
        }
        if (this.f1204e == 7) {
            codedOutputStream.mo2454b(7, (MessageLite) (IS) this.f1205k);
        }
        if (this.f1204e == 8) {
            codedOutputStream.mo2454b(8, (MessageLite) (ZP) this.f1205k);
        }
        this.f1168b.a(codedOutputStream);
    }

    /* renamed from: b */
    public int mo2662b() {
        int i = this.f1169c;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if ((this.f1203d & 1) == 1) {
            i2 = 0 + CodedOutputStream.m1066b(1, this.f1206n);
        }
        if ((this.f1203d & 2) == 2) {
            i2 += CodedOutputStream.m1066b(2, this.f1207p);
        }
        if (this.f1204e == 4) {
            i2 += CodedOutputStream.m1073c(4, (MessageLite) (OS) this.f1205k);
        }
        if (this.f1204e == 5) {
            i2 += CodedOutputStream.m1073c(5, (MessageLite) (GS) this.f1205k);
        }
        if (this.f1204e == 6) {
            i2 += CodedOutputStream.m1073c(6, (MessageLite) (StreamStructureProto.Content) this.f1205k);
        }
        if (this.f1204e == 7) {
            i2 += CodedOutputStream.m1073c(7, (MessageLite) (IS) this.f1205k);
        }
        if (this.f1204e == 8) {
            i2 += CodedOutputStream.m1073c(8, (MessageLite) (ZP) this.f1205k);
        }
        int a = this.f1168b.a() + i2;
        this.f1169c = a;
        return a;
    }

    public GS getCard() {
        if (this.f1204e == 5) {
            return (GS) this.f1205k;
        }
        return GS.k;
    }

    public IS getCluster() {
        if (this.f1204e == 7) {
            return (IS) this.f1205k;
        }
        return IS.d;
    }

    public StreamStructureProto.Content getContent() {
        if (this.f1204e == 6) {
            return (StreamStructureProto.Content) this.f1205k;
        }
        return StreamStructureProto.Content.y;
    }

    public String getContentId() {
        return this.f1206n;
    }

    public ByteString getContentIdBytes() {
        return ByteString.copyFromUtf8(this.f1206n);
    }

    public FeaturePayloadCase getFeaturePayloadCase() {
        return FeaturePayloadCase.forNumber(this.f1204e);
    }

    public ZP getLegacyContent() {
        if (this.f1204e == 8) {
            return (ZP) this.f1205k;
        }
        return ZP.n;
    }

    public String getParentId() {
        return this.f1207p;
    }

    public ByteString getParentIdBytes() {
        return ByteString.copyFromUtf8(this.f1207p);
    }

    public OS getStream() {
        if (this.f1204e == 4) {
            return (OS) this.f1205k;
        }
        return OS.d;
    }

    public boolean hasCard() {
        return this.f1204e == 5;
    }

    public boolean hasCluster() {
        return this.f1204e == 7;
    }

    public boolean hasContent() {
        return this.f1204e == 6;
    }

    public boolean hasContentId() {
        return (this.f1203d & 1) == 1;
    }

    public boolean hasLegacyContent() {
        return this.f1204e == 8;
    }

    public boolean hasParentId() {
        return (this.f1203d & 2) == 2;
    }

    public boolean hasStream() {
        return this.f1204e == 4;
    }

    /* JADX WARNING: type inference failed for: r11v0, types: [com.google.protobuf.MessageLite, com.google.protobuf.GeneratedMessageLite, java.lang.Object, com.google.search.now.feed.client.StreamDataProto$StreamFeature] */
    /* renamed from: a */
    public final Object mo2486a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        StreamStructureProto.Content content;
        GS gs;
        boolean z = false;
        switch (methodToInvoke.ordinal()) {
            case 0:
                byte b = this.f1208q;
                if (b == 1) {
                    return f1201x;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (this.f1204e == 5) {
                    if (this.f1204e == 5) {
                        gs = (GS) this.f1205k;
                    } else {
                        gs = GS.k;
                    }
                    if (!gs.isInitialized()) {
                        if (booleanValue) {
                            this.f1208q = 0;
                        }
                        return null;
                    }
                }
                if (this.f1204e == 6) {
                    if (this.f1204e == 6) {
                        content = (StreamStructureProto.Content) this.f1205k;
                    } else {
                        content = StreamStructureProto.Content.y;
                    }
                    if (!content.isInitialized()) {
                        if (booleanValue) {
                            this.f1208q = 0;
                        }
                        return null;
                    }
                }
                if (booleanValue) {
                    this.f1208q = 1;
                }
                return f1201x;
            case 1:
                XN xn = (GeneratedMessageLite.Visitor) obj;
                StreamDataProto$StreamFeature streamDataProto$StreamFeature = (StreamDataProto$StreamFeature) obj2;
                this.f1206n = xn.visitString(hasContentId(), this.f1206n, streamDataProto$StreamFeature.hasContentId(), streamDataProto$StreamFeature.f1206n);
                this.f1207p = xn.visitString(hasParentId(), this.f1207p, streamDataProto$StreamFeature.hasParentId(), streamDataProto$StreamFeature.f1207p);
                int ordinal = FeaturePayloadCase.forNumber(streamDataProto$StreamFeature.f1204e).ordinal();
                if (ordinal == 0) {
                    if (this.f1204e == 4) {
                        z = true;
                    }
                    this.f1205k = xn.visitOneofMessage(z, this.f1205k, streamDataProto$StreamFeature.f1205k);
                } else if (ordinal == 1) {
                    if (this.f1204e == 5) {
                        z = true;
                    }
                    this.f1205k = xn.visitOneofMessage(z, this.f1205k, streamDataProto$StreamFeature.f1205k);
                } else if (ordinal == 2) {
                    if (this.f1204e == 6) {
                        z = true;
                    }
                    this.f1205k = xn.visitOneofMessage(z, this.f1205k, streamDataProto$StreamFeature.f1205k);
                } else if (ordinal == 3) {
                    if (this.f1204e == 7) {
                        z = true;
                    }
                    this.f1205k = xn.visitOneofMessage(z, this.f1205k, streamDataProto$StreamFeature.f1205k);
                } else if (ordinal == 4) {
                    if (this.f1204e == 8) {
                        z = true;
                    }
                    this.f1205k = xn.visitOneofMessage(z, this.f1205k, streamDataProto$StreamFeature.f1205k);
                } else if (ordinal == 5) {
                    if (this.f1204e != 0) {
                        z = true;
                    }
                    xn.visitOneofNotSet(z);
                }
                if (xn == XN.a) {
                    int i = streamDataProto$StreamFeature.f1204e;
                    if (i != 0) {
                        this.f1204e = i;
                    }
                    this.f1203d |= streamDataProto$StreamFeature.f1203d;
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
                                this.f1203d |= 1;
                                this.f1206n = m;
                            } else if (n == 18) {
                                String m2 = ln.m();
                                this.f1203d |= 2;
                                this.f1207p = m2;
                            } else if (n == 34) {
                                NS a = this.f1204e == 4 ? ((OS) this.f1205k).m1151a() : null;
                                this.f1205k = ln.a(OS.d.mo2494h(), rn);
                                if (a != null) {
                                    a.a((OS) this.f1205k);
                                    this.f1205k = a.buildPartial();
                                }
                                this.f1204e = 4;
                            } else if (n == 42) {
                                GS.a a2 = this.f1204e == 5 ? ((GS) this.f1205k).m1151a() : null;
                                this.f1205k = ln.a(GS.k.mo2494h(), rn);
                                if (a2 != null) {
                                    a2.a((GS) this.f1205k);
                                    this.f1205k = a2.buildPartial();
                                }
                                this.f1204e = 5;
                            } else if (n == 50) {
                                StreamStructureProto.Content.a a3 = this.f1204e == 6 ? ((StreamStructureProto.Content) this.f1205k).m1151a() : null;
                                this.f1205k = ln.a(StreamStructureProto.Content.y.mo2494h(), rn);
                                if (a3 != null) {
                                    a3.a((StreamStructureProto.Content) this.f1205k);
                                    this.f1205k = a3.buildPartial();
                                }
                                this.f1204e = 6;
                            } else if (n == 58) {
                                HS a4 = this.f1204e == 7 ? ((IS) this.f1205k).m1151a() : null;
                                this.f1205k = ln.a(IS.d.mo2494h(), rn);
                                if (a4 != null) {
                                    a4.a((IS) this.f1205k);
                                    this.f1205k = a4.buildPartial();
                                }
                                this.f1204e = 7;
                            } else if (n == 66) {
                                YP a5 = this.f1204e == 8 ? ((ZP) this.f1205k).m1151a() : null;
                                this.f1205k = ln.a(ZP.n.mo2494h(), rn);
                                if (a5 != null) {
                                    a5.a((ZP) this.f1205k);
                                    this.f1205k = a5.buildPartial();
                                }
                                this.f1204e = 8;
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
                return new StreamDataProto$StreamFeature();
            case 5:
                return new XP((UP) null);
            case 6:
                break;
            case 7:
                if (f1202y == null) {
                    synchronized (StreamDataProto$StreamFeature.class) {
                        if (f1202y == null) {
                            f1202y = new UN(f1201x);
                        }
                    }
                }
                return f1202y;
            default:
                throw new UnsupportedOperationException();
        }
        return f1201x;
    }
}
