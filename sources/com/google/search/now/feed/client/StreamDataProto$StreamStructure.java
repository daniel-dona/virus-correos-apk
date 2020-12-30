package com.google.search.now.feed.client;

import com.citrix.loggersdk.BuildConfig;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.IOException;

/* compiled from: PG */
public final class StreamDataProto$StreamStructure extends GeneratedMessageLite.C0282b<StreamDataProto$StreamStructure, C0299a> implements StreamDataProto$StreamStructureOrBuilder {

    /* renamed from: x */
    public static final StreamDataProto$StreamStructure f1222x = new StreamDataProto$StreamStructure();

    /* renamed from: y */
    public static volatile eO<StreamDataProto$StreamStructure> f1223y;

    /* renamed from: e */
    public int f1224e;

    /* renamed from: k */
    public int f1225k;

    /* renamed from: n */
    public String f1226n = BuildConfig.FLAVOR;

    /* renamed from: p */
    public String f1227p = BuildConfig.FLAVOR;

    /* renamed from: q */
    public byte f1228q = -1;

    /* compiled from: PG */
    public enum Operation implements Internal.EnumLite {
        UNKNOWN(0),
        CLEAR_ALL(1),
        UPDATE_OR_APPEND(2),
        REMOVE(3),
        REQUIRED_CONTENT(4);
        
        public static final int CLEAR_ALL_VALUE = 1;
        public static final int REMOVE_VALUE = 3;
        public static final int REQUIRED_CONTENT_VALUE = 4;
        public static final int UNKNOWN_VALUE = 0;
        public static final int UPDATE_OR_APPEND_VALUE = 2;
        public static final Internal.EnumLiteMap<Operation> internalValueMap = null;
        public final int value;

        /* renamed from: com.google.search.now.feed.client.StreamDataProto$StreamStructure$Operation$a */
        /* compiled from: PG */
        public class C0298a implements Internal.EnumLiteMap<Operation> {
            public Internal.EnumLite findValueByNumber(int i) {
                return Operation.forNumber(i);
            }
        }

        /* access modifiers changed from: public */
        static {
            internalValueMap = new C0298a();
        }

        /* access modifiers changed from: public */
        Operation(int i) {
            this.value = i;
        }

        public static Operation forNumber(int i) {
            if (i == 0) {
                return UNKNOWN;
            }
            if (i == 1) {
                return CLEAR_ALL;
            }
            if (i == 2) {
                return UPDATE_OR_APPEND;
            }
            if (i == 3) {
                return REMOVE;
            }
            if (i != 4) {
                return null;
            }
            return REQUIRED_CONTENT;
        }

        public static Internal.EnumLiteMap<Operation> internalGetValueMap() {
            return internalValueMap;
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Operation valueOf(int i) {
            return forNumber(i);
        }
    }

    /* renamed from: com.google.search.now.feed.client.StreamDataProto$StreamStructure$a */
    /* compiled from: PG */
    public static final class C0299a extends GeneratedMessageLite.C0281a<StreamDataProto$StreamStructure, C0299a> implements StreamDataProto$StreamStructureOrBuilder {
        public /* synthetic */ C0299a(UP up) {
            super(StreamDataProto$StreamStructure.f1222x);
        }

        public String getContentId() {
            return ((StreamDataProto$StreamStructure) this.b).f1226n;
        }

        public ByteString getContentIdBytes() {
            return ByteString.copyFromUtf8(((StreamDataProto$StreamStructure) this.b).f1226n);
        }

        public Operation getOperation() {
            Operation forNumber = Operation.forNumber(((StreamDataProto$StreamStructure) this.b).f1225k);
            return forNumber == null ? Operation.UNKNOWN : forNumber;
        }

        public String getParentContentId() {
            return ((StreamDataProto$StreamStructure) this.b).f1227p;
        }

        public ByteString getParentContentIdBytes() {
            return ByteString.copyFromUtf8(((StreamDataProto$StreamStructure) this.b).f1227p);
        }

        public boolean hasContentId() {
            return ((StreamDataProto$StreamStructure) this.b).hasContentId();
        }

        public boolean hasOperation() {
            return ((StreamDataProto$StreamStructure) this.b).hasOperation();
        }

        public boolean hasParentContentId() {
            return ((StreamDataProto$StreamStructure) this.b).hasParentContentId();
        }
    }

    static {
        f1222x.mo2496i();
    }

    /* renamed from: a */
    public void mo2725a(CodedOutputStream codedOutputStream) throws IOException {
        GeneratedMessageLite.C0282b<MessageType, BuilderType>.a l = mo2539l();
        if ((this.f1224e & 1) == 1) {
            codedOutputStream.mo2452b(1, this.f1225k);
        }
        if ((this.f1224e & 2) == 2) {
            codedOutputStream.mo2443a(2, this.f1226n);
        }
        if ((this.f1224e & 4) == 4) {
            codedOutputStream.mo2443a(3, this.f1227p);
        }
        l.mo2540a(536870912, codedOutputStream);
        this.f1168b.a(codedOutputStream);
    }

    /* renamed from: b */
    public int mo2726b() {
        int i = this.f1169c;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if ((this.f1224e & 1) == 1) {
            i2 = 0 + CodedOutputStream.m1084f(1, this.f1225k);
        }
        if ((this.f1224e & 2) == 2) {
            i2 += CodedOutputStream.m1066b(2, this.f1226n);
        }
        if ((this.f1224e & 4) == 4) {
            i2 += CodedOutputStream.m1066b(3, this.f1227p);
        }
        int a = this.f1168b.a() + mo2538k() + i2;
        this.f1169c = a;
        return a;
    }

    public String getContentId() {
        return this.f1226n;
    }

    public ByteString getContentIdBytes() {
        return ByteString.copyFromUtf8(this.f1226n);
    }

    public Operation getOperation() {
        Operation forNumber = Operation.forNumber(this.f1225k);
        return forNumber == null ? Operation.UNKNOWN : forNumber;
    }

    public String getParentContentId() {
        return this.f1227p;
    }

    public ByteString getParentContentIdBytes() {
        return ByteString.copyFromUtf8(this.f1227p);
    }

    public boolean hasContentId() {
        return (this.f1224e & 2) == 2;
    }

    public boolean hasOperation() {
        return (this.f1224e & 1) == 1;
    }

    public boolean hasParentContentId() {
        return (this.f1224e & 4) == 4;
    }

    /* JADX WARNING: type inference failed for: r2v15, types: [com.google.protobuf.MessageLite, com.google.search.now.feed.client.StreamDataProto$StreamStructure] */
    /* renamed from: a */
    public final Object mo2486a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke.ordinal()) {
            case 0:
                byte b = this.f1228q;
                if (b == 1) {
                    return f1222x;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (!mo2537j()) {
                    if (booleanValue) {
                        this.f1228q = 0;
                    }
                    return null;
                }
                if (booleanValue) {
                    this.f1228q = 1;
                }
                return f1222x;
            case 1:
                XN xn = (GeneratedMessageLite.Visitor) obj;
                StreamDataProto$StreamStructure streamDataProto$StreamStructure = (StreamDataProto$StreamStructure) obj2;
                this.f1225k = xn.visitInt(hasOperation(), this.f1225k, streamDataProto$StreamStructure.hasOperation(), streamDataProto$StreamStructure.f1225k);
                this.f1226n = xn.visitString(hasContentId(), this.f1226n, streamDataProto$StreamStructure.hasContentId(), streamDataProto$StreamStructure.f1226n);
                this.f1227p = xn.visitString(hasParentContentId(), this.f1227p, streamDataProto$StreamStructure.hasParentContentId(), streamDataProto$StreamStructure.f1227p);
                if (xn == XN.a) {
                    this.f1224e |= streamDataProto$StreamStructure.f1224e;
                }
                return this;
            case 2:
                LN ln = (LN) obj;
                RN rn = (RN) obj2;
                while (!z) {
                    try {
                        int n = ln.n();
                        if (n != 0) {
                            if (n == 8) {
                                int j = ln.j();
                                if (Operation.forNumber(j) == null) {
                                    super.mo2487a(1, j);
                                } else {
                                    this.f1224e |= 1;
                                    this.f1225k = j;
                                }
                            } else if (n == 18) {
                                String m = ln.m();
                                this.f1224e |= 2;
                                this.f1226n = m;
                            } else if (n == 26) {
                                String m2 = ln.m();
                                this.f1224e |= 4;
                                this.f1227p = m2;
                            } else if (!mo2536a((StreamDataProto$StreamStructure) m1161d(), ln, rn, n)) {
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
                return new StreamDataProto$StreamStructure();
            case 5:
                return new C0299a((UP) null);
            case 6:
                break;
            case 7:
                if (f1223y == null) {
                    synchronized (StreamDataProto$StreamStructure.class) {
                        if (f1223y == null) {
                            f1223y = new UN(f1222x);
                        }
                    }
                }
                return f1223y;
            default:
                throw new UnsupportedOperationException();
        }
        return f1222x;
    }
}
