package com.google.search.now.p005ui.action;

import QS;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.search.now.wire.feed.DataOperationProto;
import java.io.IOException;
import java.util.List;

/* renamed from: com.google.search.now.ui.action.FeedActionProto$NotInterestedInData */
/* compiled from: PG */
public final class FeedActionProto$NotInterestedInData extends GeneratedMessageLite<FeedActionProto$NotInterestedInData, sQ> implements FeedActionProto$NotInterestedInDataOrBuilder {

    /* renamed from: x */
    public static final FeedActionProto$NotInterestedInData f1237x = new FeedActionProto$NotInterestedInData();

    /* renamed from: y */
    public static volatile eO<FeedActionProto$NotInterestedInData> f1238y;

    /* renamed from: d */
    public int f1239d;

    /* renamed from: e */
    public zQ f1240e;

    /* renamed from: k */
    public Internal.ProtobufList<DataOperationProto.DataOperation> f1241k = fO.c;

    /* renamed from: n */
    public QS f1242n;

    /* renamed from: p */
    public int f1243p;

    /* renamed from: q */
    public byte f1244q = -1;

    /* renamed from: com.google.search.now.ui.action.FeedActionProto$NotInterestedInData$RecordedInterestType */
    /* compiled from: PG */
    public enum RecordedInterestType implements Internal.EnumLite {
        UNKNOWN_INTEREST_TYPE(0),
        TOPIC(1),
        SOURCE(2);
        
        public static final int SOURCE_VALUE = 2;
        public static final int TOPIC_VALUE = 1;
        public static final int UNKNOWN_INTEREST_TYPE_VALUE = 0;
        public static final Internal.EnumLiteMap<RecordedInterestType> internalValueMap = null;
        public final int value;

        /* renamed from: com.google.search.now.ui.action.FeedActionProto$NotInterestedInData$RecordedInterestType$a */
        /* compiled from: PG */
        public class C0302a implements Internal.EnumLiteMap<RecordedInterestType> {
            public Internal.EnumLite findValueByNumber(int i) {
                return RecordedInterestType.forNumber(i);
            }
        }

        /* access modifiers changed from: public */
        static {
            internalValueMap = new C0302a();
        }

        /* access modifiers changed from: public */
        RecordedInterestType(int i) {
            this.value = i;
        }

        public static RecordedInterestType forNumber(int i) {
            if (i == 0) {
                return UNKNOWN_INTEREST_TYPE;
            }
            if (i == 1) {
                return TOPIC;
            }
            if (i != 2) {
                return null;
            }
            return SOURCE;
        }

        public static Internal.EnumLiteMap<RecordedInterestType> internalGetValueMap() {
            return internalValueMap;
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static RecordedInterestType valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        f1237x.mo2496i();
    }

    /* renamed from: a */
    public void mo2789a(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.f1239d & 1) == 1) {
            codedOutputStream.mo2454b(1, (MessageLite) getUndoAction());
        }
        for (int i = 0; i < this.f1241k.size(); i++) {
            codedOutputStream.mo2454b(2, this.f1241k.get(i));
        }
        if ((this.f1239d & 2) == 2) {
            codedOutputStream.mo2454b(3, (MessageLite) getPayload());
        }
        if ((this.f1239d & 4) == 4) {
            codedOutputStream.mo2452b(5, this.f1243p);
        }
        this.f1168b.a(codedOutputStream);
    }

    /* renamed from: b */
    public int mo2790b() {
        int i = this.f1169c;
        if (i != -1) {
            return i;
        }
        int c = (this.f1239d & 1) == 1 ? CodedOutputStream.m1073c(1, (MessageLite) getUndoAction()) + 0 : 0;
        for (int i2 = 0; i2 < this.f1241k.size(); i2++) {
            c += CodedOutputStream.m1073c(2, this.f1241k.get(i2));
        }
        if ((this.f1239d & 2) == 2) {
            c += CodedOutputStream.m1073c(3, (MessageLite) getPayload());
        }
        if ((this.f1239d & 4) == 4) {
            c += CodedOutputStream.m1090h(5, this.f1243p);
        }
        int a = this.f1168b.a() + c;
        this.f1169c = a;
        return a;
    }

    public DataOperationProto.DataOperation getDataOperations(int i) {
        return this.f1241k.get(i);
    }

    public int getDataOperationsCount() {
        return this.f1241k.size();
    }

    public List<DataOperationProto.DataOperation> getDataOperationsList() {
        return this.f1241k;
    }

    public int getInterestTypeValue() {
        return this.f1243p;
    }

    public QS getPayload() {
        QS qs = this.f1242n;
        return qs == null ? QS.k : qs;
    }

    public zQ getUndoAction() {
        zQ zQVar = this.f1240e;
        return zQVar == null ? zQ.n : zQVar;
    }

    public boolean hasInterestTypeValue() {
        return (this.f1239d & 4) == 4;
    }

    public boolean hasPayload() {
        return (this.f1239d & 2) == 2;
    }

    public boolean hasUndoAction() {
        return (this.f1239d & 1) == 1;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.google.protobuf.MessageLite, com.google.protobuf.GeneratedMessageLite, com.google.search.now.ui.action.FeedActionProto$NotInterestedInData, java.lang.Object] */
    /* renamed from: a */
    public final Object mo2486a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke.ordinal()) {
            case 0:
                byte b = this.f1244q;
                if (b == 1) {
                    return f1237x;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                for (int i = 0; i < this.f1241k.size(); i++) {
                    if (!this.f1241k.get(i).isInitialized()) {
                        if (booleanValue) {
                            this.f1244q = 0;
                        }
                        return null;
                    }
                }
                if (!((this.f1239d & 2) == 2) || getPayload().isInitialized()) {
                    if (booleanValue) {
                        this.f1244q = 1;
                    }
                    return f1237x;
                }
                if (booleanValue) {
                    this.f1244q = 0;
                }
                return null;
            case 1:
                XN xn = (GeneratedMessageLite.Visitor) obj;
                FeedActionProto$NotInterestedInData feedActionProto$NotInterestedInData = (FeedActionProto$NotInterestedInData) obj2;
                this.f1240e = xn.visitMessage(this.f1240e, feedActionProto$NotInterestedInData.f1240e);
                this.f1241k = xn.visitList(this.f1241k, feedActionProto$NotInterestedInData.f1241k);
                this.f1242n = xn.visitMessage(this.f1242n, feedActionProto$NotInterestedInData.f1242n);
                this.f1243p = xn.visitInt(hasInterestTypeValue(), this.f1243p, feedActionProto$NotInterestedInData.hasInterestTypeValue(), feedActionProto$NotInterestedInData.f1243p);
                if (xn == XN.a) {
                    this.f1239d |= feedActionProto$NotInterestedInData.f1239d;
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
                                yQ a = (this.f1239d & 1) == 1 ? this.f1240e.m1151a() : null;
                                this.f1240e = ln.a(zQ.n.mo2494h(), rn);
                                if (a != null) {
                                    a.a(this.f1240e);
                                    this.f1240e = a.buildPartial();
                                }
                                this.f1239d |= 1;
                            } else if (n == 18) {
                                if (!this.f1241k.isModifiable()) {
                                    this.f1241k = GeneratedMessageLite.m1148a(this.f1241k);
                                }
                                this.f1241k.add(ln.a(DataOperationProto.DataOperation.x.mo2494h(), rn));
                            } else if (n == 26) {
                                QS.a a2 = (this.f1239d & 2) == 2 ? this.f1242n.m1151a() : null;
                                this.f1242n = ln.a(QS.m(), rn);
                                if (a2 != null) {
                                    a2.a(this.f1242n);
                                    this.f1242n = a2.buildPartial();
                                }
                                this.f1239d |= 2;
                            } else if (n == 40) {
                                this.f1239d |= 4;
                                this.f1243p = ln.j();
                            } else if (!mo2489a(n, ln)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                break;
            case 3:
                this.f1241k.makeImmutable();
                return null;
            case 4:
                return new FeedActionProto$NotInterestedInData();
            case 5:
                return new sQ((kQ) null);
            case 6:
                break;
            case 7:
                if (f1238y == null) {
                    synchronized (FeedActionProto$NotInterestedInData.class) {
                        if (f1238y == null) {
                            f1238y = new UN(f1237x);
                        }
                    }
                }
                return f1238y;
            default:
                throw new UnsupportedOperationException();
        }
        return f1237x;
    }
}
