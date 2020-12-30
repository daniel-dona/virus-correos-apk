package com.google.search.now.p005ui.action;

import com.citrix.loggersdk.BuildConfig;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import java.io.IOException;

/* renamed from: com.google.search.now.ui.action.FeedActionProto$TooltipData */
/* compiled from: PG */
public final class FeedActionProto$TooltipData extends GeneratedMessageLite<FeedActionProto$TooltipData, xQ> implements FeedActionProto$TooltipDataOrBuilder {

    /* renamed from: q */
    public static final FeedActionProto$TooltipData f1245q = new FeedActionProto$TooltipData();

    /* renamed from: x */
    public static volatile eO<FeedActionProto$TooltipData> f1246x;

    /* renamed from: d */
    public int f1247d;

    /* renamed from: e */
    public String f1248e = BuildConfig.FLAVOR;

    /* renamed from: k */
    public String f1249k = BuildConfig.FLAVOR;

    /* renamed from: n */
    public int f1250n;

    /* renamed from: p */
    public pQ f1251p;

    /* renamed from: com.google.search.now.ui.action.FeedActionProto$TooltipData$FeatureName */
    /* compiled from: PG */
    public enum FeatureName implements Internal.EnumLite {
        UNKNOWN(0),
        CARD_MENU(1);
        
        public static final int CARD_MENU_VALUE = 1;
        public static final int UNKNOWN_VALUE = 0;
        public static final Internal.EnumLiteMap<FeatureName> internalValueMap = null;
        public final int value;

        /* renamed from: com.google.search.now.ui.action.FeedActionProto$TooltipData$FeatureName$a */
        /* compiled from: PG */
        public class C0303a implements Internal.EnumLiteMap<FeatureName> {
            public Internal.EnumLite findValueByNumber(int i) {
                return FeatureName.forNumber(i);
            }
        }

        /* access modifiers changed from: public */
        static {
            internalValueMap = new C0303a();
        }

        /* access modifiers changed from: public */
        FeatureName(int i) {
            this.value = i;
        }

        public static FeatureName forNumber(int i) {
            if (i == 0) {
                return UNKNOWN;
            }
            if (i != 1) {
                return null;
            }
            return CARD_MENU;
        }

        public static Internal.EnumLiteMap<FeatureName> internalGetValueMap() {
            return internalValueMap;
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static FeatureName valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        f1245q.mo2496i();
    }

    /* renamed from: a */
    public void mo2809a(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.f1247d & 1) == 1) {
            codedOutputStream.mo2443a(1, this.f1248e);
        }
        if ((this.f1247d & 2) == 2) {
            codedOutputStream.mo2443a(2, this.f1249k);
        }
        if ((this.f1247d & 4) == 4) {
            codedOutputStream.mo2452b(3, this.f1250n);
        }
        if ((this.f1247d & 8) == 8) {
            codedOutputStream.mo2454b(4, (MessageLite) getInsets());
        }
        this.f1168b.a(codedOutputStream);
    }

    /* renamed from: b */
    public int mo2810b() {
        int i = this.f1169c;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if ((this.f1247d & 1) == 1) {
            i2 = 0 + CodedOutputStream.m1066b(1, this.f1248e);
        }
        if ((this.f1247d & 2) == 2) {
            i2 += CodedOutputStream.m1066b(2, this.f1249k);
        }
        if ((this.f1247d & 4) == 4) {
            i2 += CodedOutputStream.m1084f(3, this.f1250n);
        }
        if ((this.f1247d & 8) == 8) {
            i2 += CodedOutputStream.m1073c(4, (MessageLite) getInsets());
        }
        int a = this.f1168b.a() + i2;
        this.f1169c = a;
        return a;
    }

    public String getAccessibilityLabel() {
        return this.f1249k;
    }

    public ByteString getAccessibilityLabelBytes() {
        return ByteString.copyFromUtf8(this.f1249k);
    }

    public FeatureName getFeatureName() {
        FeatureName forNumber = FeatureName.forNumber(this.f1250n);
        return forNumber == null ? FeatureName.UNKNOWN : forNumber;
    }

    public pQ getInsets() {
        pQ pQVar = this.f1251p;
        return pQVar == null ? pQ.n : pQVar;
    }

    public String getLabel() {
        return this.f1248e;
    }

    public ByteString getLabelBytes() {
        return ByteString.copyFromUtf8(this.f1248e);
    }

    public boolean hasAccessibilityLabel() {
        return (this.f1247d & 2) == 2;
    }

    public boolean hasFeatureName() {
        return (this.f1247d & 4) == 4;
    }

    public boolean hasInsets() {
        return (this.f1247d & 8) == 8;
    }

    public boolean hasLabel() {
        return (this.f1247d & 1) == 1;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.google.protobuf.MessageLite, com.google.search.now.ui.action.FeedActionProto$TooltipData, com.google.protobuf.GeneratedMessageLite, java.lang.Object] */
    /* renamed from: a */
    public final Object mo2486a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke.ordinal()) {
            case 0:
                return f1245q;
            case 1:
                XN xn = (GeneratedMessageLite.Visitor) obj;
                FeedActionProto$TooltipData feedActionProto$TooltipData = (FeedActionProto$TooltipData) obj2;
                this.f1248e = xn.visitString(hasLabel(), this.f1248e, feedActionProto$TooltipData.hasLabel(), feedActionProto$TooltipData.f1248e);
                this.f1249k = xn.visitString(hasAccessibilityLabel(), this.f1249k, feedActionProto$TooltipData.hasAccessibilityLabel(), feedActionProto$TooltipData.f1249k);
                this.f1250n = xn.visitInt(hasFeatureName(), this.f1250n, feedActionProto$TooltipData.hasFeatureName(), feedActionProto$TooltipData.f1250n);
                this.f1251p = xn.visitMessage(this.f1251p, feedActionProto$TooltipData.f1251p);
                if (xn == XN.a) {
                    this.f1247d |= feedActionProto$TooltipData.f1247d;
                }
                return this;
            case 2:
                LN ln = (LN) obj;
                RN rn = (RN) obj2;
                boolean z = false;
                while (!z) {
                    try {
                        int n = ln.n();
                        if (n != 0) {
                            if (n == 10) {
                                String m = ln.m();
                                this.f1247d |= 1;
                                this.f1248e = m;
                            } else if (n == 18) {
                                String m2 = ln.m();
                                this.f1247d |= 2;
                                this.f1249k = m2;
                            } else if (n == 24) {
                                int j = ln.j();
                                if (FeatureName.forNumber(j) == null) {
                                    super.mo2487a(3, j);
                                } else {
                                    this.f1247d |= 4;
                                    this.f1250n = j;
                                }
                            } else if (n == 34) {
                                oQ a = (this.f1247d & 8) == 8 ? this.f1251p.m1151a() : null;
                                this.f1251p = ln.a(pQ.n.mo2494h(), rn);
                                if (a != null) {
                                    a.a(this.f1251p);
                                    this.f1251p = a.buildPartial();
                                }
                                this.f1247d |= 8;
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
                return new FeedActionProto$TooltipData();
            case 5:
                return new xQ((kQ) null);
            case 6:
                break;
            case 7:
                if (f1246x == null) {
                    synchronized (FeedActionProto$TooltipData.class) {
                        if (f1246x == null) {
                            f1246x = new UN(f1245q);
                        }
                    }
                }
                return f1246x;
            default:
                throw new UnsupportedOperationException();
        }
        return f1245q;
    }
}
