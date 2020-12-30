package com.google.search.now.p005ui.action;

import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import java.io.IOException;

/* renamed from: com.google.search.now.ui.action.FeedActionProto$FeedActionMetadata */
/* compiled from: PG */
public final class FeedActionProto$FeedActionMetadata extends GeneratedMessageLite<FeedActionProto$FeedActionMetadata, nQ> implements FeedActionProto$FeedActionMetadataOrBuilder {

    /* renamed from: x */
    public static final FeedActionProto$FeedActionMetadata f1229x = new FeedActionProto$FeedActionMetadata();

    /* renamed from: y */
    public static volatile eO<FeedActionProto$FeedActionMetadata> f1230y;

    /* renamed from: d */
    public int f1231d;

    /* renamed from: e */
    public int f1232e = 0;

    /* renamed from: k */
    public Object f1233k;

    /* renamed from: n */
    public int f1234n;

    /* renamed from: p */
    public int f1235p;

    /* renamed from: q */
    public byte f1236q = -1;

    /* renamed from: com.google.search.now.ui.action.FeedActionProto$FeedActionMetadata$DataCase */
    /* compiled from: PG */
    public enum DataCase implements Internal.EnumLite {
        OPEN_URL_DATA(2),
        OPEN_CONTEXT_MENU_DATA(3),
        DISMISS_DATA(4),
        NOT_INTERESTED_IN_DATA(6),
        TOOLTIP_DATA(8),
        DATA_NOT_SET(0);
        
        public final int value;

        /* access modifiers changed from: public */
        DataCase(int i) {
            this.value = i;
        }

        public static DataCase forNumber(int i) {
            if (i == 0) {
                return DATA_NOT_SET;
            }
            if (i == 6) {
                return NOT_INTERESTED_IN_DATA;
            }
            if (i == 8) {
                return TOOLTIP_DATA;
            }
            if (i == 2) {
                return OPEN_URL_DATA;
            }
            if (i == 3) {
                return OPEN_CONTEXT_MENU_DATA;
            }
            if (i != 4) {
                return null;
            }
            return DISMISS_DATA;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static DataCase valueOf(int i) {
            return forNumber(i);
        }
    }

    /* renamed from: com.google.search.now.ui.action.FeedActionProto$FeedActionMetadata$ElementType */
    /* compiled from: PG */
    public enum ElementType implements Internal.EnumLite {
        UNKNOWN_ELEMENT_TYPE(0),
        CARD_LARGE_IMAGE(1),
        CARD_SMALL_IMAGE(2),
        INTEREST_HEADER(3),
        TOOLTIP(4);
        
        public static final int CARD_LARGE_IMAGE_VALUE = 1;
        public static final int CARD_SMALL_IMAGE_VALUE = 2;
        public static final int INTEREST_HEADER_VALUE = 3;
        public static final int TOOLTIP_VALUE = 4;
        public static final int UNKNOWN_ELEMENT_TYPE_VALUE = 0;
        public static final Internal.EnumLiteMap<ElementType> internalValueMap = null;
        public final int value;

        /* renamed from: com.google.search.now.ui.action.FeedActionProto$FeedActionMetadata$ElementType$a */
        /* compiled from: PG */
        public class C0300a implements Internal.EnumLiteMap<ElementType> {
            public Internal.EnumLite findValueByNumber(int i) {
                return ElementType.forNumber(i);
            }
        }

        /* access modifiers changed from: public */
        static {
            internalValueMap = new C0300a();
        }

        /* access modifiers changed from: public */
        ElementType(int i) {
            this.value = i;
        }

        public static ElementType forNumber(int i) {
            if (i == 0) {
                return UNKNOWN_ELEMENT_TYPE;
            }
            if (i == 1) {
                return CARD_LARGE_IMAGE;
            }
            if (i == 2) {
                return CARD_SMALL_IMAGE;
            }
            if (i == 3) {
                return INTEREST_HEADER;
            }
            if (i != 4) {
                return null;
            }
            return TOOLTIP;
        }

        public static Internal.EnumLiteMap<ElementType> internalGetValueMap() {
            return internalValueMap;
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ElementType valueOf(int i) {
            return forNumber(i);
        }
    }

    /* renamed from: com.google.search.now.ui.action.FeedActionProto$FeedActionMetadata$Type */
    /* compiled from: PG */
    public enum Type implements Internal.EnumLite {
        UNKNOWN(0),
        OPEN_URL(1),
        OPEN_URL_INCOGNITO(2),
        OPEN_URL_NEW_WINDOW(3),
        OPEN_CONTEXT_MENU(4),
        DISMISS_LOCAL(5),
        DISMISS(11),
        DOWNLOAD(6),
        OPEN_URL_NEW_TAB(7),
        LEARN_MORE(8),
        VIEW_ELEMENT(12),
        HIDE_ELEMENT(13),
        SHOW_TOOLTIP(14),
        NOT_INTERESTED_IN(15);
        
        public static final int DISMISS_LOCAL_VALUE = 5;
        public static final int DISMISS_VALUE = 11;
        public static final int DOWNLOAD_VALUE = 6;
        public static final int HIDE_ELEMENT_VALUE = 13;
        public static final int LEARN_MORE_VALUE = 8;
        public static final int NOT_INTERESTED_IN_VALUE = 15;
        public static final int OPEN_CONTEXT_MENU_VALUE = 4;
        public static final int OPEN_URL_INCOGNITO_VALUE = 2;
        public static final int OPEN_URL_NEW_TAB_VALUE = 7;
        public static final int OPEN_URL_NEW_WINDOW_VALUE = 3;
        public static final int OPEN_URL_VALUE = 1;
        public static final int SHOW_TOOLTIP_VALUE = 14;
        public static final int UNKNOWN_VALUE = 0;
        public static final int VIEW_ELEMENT_VALUE = 12;
        public static final Internal.EnumLiteMap<Type> internalValueMap = null;
        public final int value;

        /* renamed from: com.google.search.now.ui.action.FeedActionProto$FeedActionMetadata$Type$a */
        /* compiled from: PG */
        public class C0301a implements Internal.EnumLiteMap<Type> {
            public Internal.EnumLite findValueByNumber(int i) {
                return Type.forNumber(i);
            }
        }

        /* access modifiers changed from: public */
        static {
            internalValueMap = new C0301a();
        }

        /* access modifiers changed from: public */
        Type(int i) {
            this.value = i;
        }

        public static Type forNumber(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN;
                case 1:
                    return OPEN_URL;
                case 2:
                    return OPEN_URL_INCOGNITO;
                case 3:
                    return OPEN_URL_NEW_WINDOW;
                case 4:
                    return OPEN_CONTEXT_MENU;
                case 5:
                    return DISMISS_LOCAL;
                case 6:
                    return DOWNLOAD;
                case 7:
                    return OPEN_URL_NEW_TAB;
                case 8:
                    return LEARN_MORE;
                case 11:
                    return DISMISS;
                case 12:
                    return VIEW_ELEMENT;
                case 13:
                    return HIDE_ELEMENT;
                case 14:
                    return SHOW_TOOLTIP;
                case 15:
                    return NOT_INTERESTED_IN;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<Type> internalGetValueMap() {
            return internalValueMap;
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Type valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        f1229x.mo2496i();
    }

    /* JADX WARNING: type inference failed for: r0v12, types: [com.google.protobuf.MessageLite, com.google.search.now.ui.action.FeedActionProto$TooltipData] */
    /* JADX WARNING: type inference failed for: r0v14, types: [com.google.protobuf.MessageLite, com.google.search.now.ui.action.FeedActionProto$NotInterestedInData] */
    /* renamed from: a */
    public void mo2761a(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.f1231d & 1) == 1) {
            codedOutputStream.mo2452b(1, this.f1234n);
        }
        if (this.f1232e == 2) {
            codedOutputStream.mo2454b(2, (MessageLite) (wQ) this.f1233k);
        }
        if (this.f1232e == 3) {
            codedOutputStream.mo2454b(3, (MessageLite) (uQ) this.f1233k);
        }
        if (this.f1232e == 4) {
            codedOutputStream.mo2454b(4, (MessageLite) (mQ) this.f1233k);
        }
        if (this.f1232e == 6) {
            codedOutputStream.mo2454b(6, (MessageLite) (FeedActionProto$NotInterestedInData) this.f1233k);
        }
        if (this.f1232e == 8) {
            codedOutputStream.mo2454b(8, (MessageLite) (FeedActionProto$TooltipData) this.f1233k);
        }
        if ((this.f1231d & 64) == 64) {
            codedOutputStream.mo2452b(9, this.f1235p);
        }
        this.f1168b.a(codedOutputStream);
    }

    /* JADX WARNING: type inference failed for: r1v16, types: [com.google.protobuf.MessageLite, com.google.search.now.ui.action.FeedActionProto$TooltipData] */
    /* JADX WARNING: type inference failed for: r1v19, types: [com.google.protobuf.MessageLite, com.google.search.now.ui.action.FeedActionProto$NotInterestedInData] */
    /* renamed from: b */
    public int mo2762b() {
        int i = this.f1169c;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if ((this.f1231d & 1) == 1) {
            i2 = 0 + CodedOutputStream.m1084f(1, this.f1234n);
        }
        if (this.f1232e == 2) {
            i2 += CodedOutputStream.m1073c(2, (MessageLite) (wQ) this.f1233k);
        }
        if (this.f1232e == 3) {
            i2 += CodedOutputStream.m1073c(3, (MessageLite) (uQ) this.f1233k);
        }
        if (this.f1232e == 4) {
            i2 += CodedOutputStream.m1073c(4, (MessageLite) (mQ) this.f1233k);
        }
        if (this.f1232e == 6) {
            i2 += CodedOutputStream.m1073c(6, (MessageLite) (FeedActionProto$NotInterestedInData) this.f1233k);
        }
        if (this.f1232e == 8) {
            i2 += CodedOutputStream.m1073c(8, (MessageLite) (FeedActionProto$TooltipData) this.f1233k);
        }
        if ((this.f1231d & 64) == 64) {
            i2 += CodedOutputStream.m1090h(9, this.f1235p);
        }
        int a = this.f1168b.a() + i2;
        this.f1169c = a;
        return a;
    }

    public DataCase getDataCase() {
        return DataCase.forNumber(this.f1232e);
    }

    public mQ getDismissData() {
        if (this.f1232e == 4) {
            return (mQ) this.f1233k;
        }
        return mQ.x;
    }

    public int getElementTypeValue() {
        return this.f1235p;
    }

    public FeedActionProto$NotInterestedInData getNotInterestedInData() {
        if (this.f1232e == 6) {
            return (FeedActionProto$NotInterestedInData) this.f1233k;
        }
        return FeedActionProto$NotInterestedInData.f1237x;
    }

    public uQ getOpenContextMenuData() {
        if (this.f1232e == 3) {
            return (uQ) this.f1233k;
        }
        return uQ.k;
    }

    public wQ getOpenUrlData() {
        if (this.f1232e == 2) {
            return (wQ) this.f1233k;
        }
        return wQ.n;
    }

    public FeedActionProto$TooltipData getTooltipData() {
        if (this.f1232e == 8) {
            return (FeedActionProto$TooltipData) this.f1233k;
        }
        return FeedActionProto$TooltipData.f1245q;
    }

    public Type getType() {
        Type forNumber = Type.forNumber(this.f1234n);
        return forNumber == null ? Type.UNKNOWN : forNumber;
    }

    public boolean hasDismissData() {
        return this.f1232e == 4;
    }

    public boolean hasElementTypeValue() {
        return (this.f1231d & 64) == 64;
    }

    public boolean hasNotInterestedInData() {
        return this.f1232e == 6;
    }

    public boolean hasOpenContextMenuData() {
        return this.f1232e == 3;
    }

    public boolean hasOpenUrlData() {
        return this.f1232e == 2;
    }

    public boolean hasTooltipData() {
        return this.f1232e == 8;
    }

    public boolean hasType() {
        return (this.f1231d & 1) == 1;
    }

    /* JADX WARNING: type inference failed for: r10v0, types: [com.google.protobuf.MessageLite, com.google.protobuf.GeneratedMessageLite, com.google.search.now.ui.action.FeedActionProto$FeedActionMetadata, java.lang.Object] */
    /* renamed from: a */
    public final Object mo2486a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        FeedActionProto$NotInterestedInData feedActionProto$NotInterestedInData;
        mQ mQVar;
        uQ uQVar;
        boolean z = false;
        switch (methodToInvoke.ordinal()) {
            case 0:
                byte b = this.f1236q;
                if (b == 1) {
                    return f1229x;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (this.f1232e == 3) {
                    if (this.f1232e == 3) {
                        uQVar = (uQ) this.f1233k;
                    } else {
                        uQVar = uQ.k;
                    }
                    if (!uQVar.isInitialized()) {
                        if (booleanValue) {
                            this.f1236q = 0;
                        }
                        return null;
                    }
                }
                if (this.f1232e == 4) {
                    if (this.f1232e == 4) {
                        mQVar = (mQ) this.f1233k;
                    } else {
                        mQVar = mQ.x;
                    }
                    if (!mQVar.isInitialized()) {
                        if (booleanValue) {
                            this.f1236q = 0;
                        }
                        return null;
                    }
                }
                if (this.f1232e == 6) {
                    if (this.f1232e == 6) {
                        feedActionProto$NotInterestedInData = (FeedActionProto$NotInterestedInData) this.f1233k;
                    } else {
                        feedActionProto$NotInterestedInData = FeedActionProto$NotInterestedInData.f1237x;
                    }
                    if (!feedActionProto$NotInterestedInData.isInitialized()) {
                        if (booleanValue) {
                            this.f1236q = 0;
                        }
                        return null;
                    }
                }
                if (booleanValue) {
                    this.f1236q = 1;
                }
                return f1229x;
            case 1:
                XN xn = (GeneratedMessageLite.Visitor) obj;
                FeedActionProto$FeedActionMetadata feedActionProto$FeedActionMetadata = (FeedActionProto$FeedActionMetadata) obj2;
                this.f1234n = xn.visitInt(hasType(), this.f1234n, feedActionProto$FeedActionMetadata.hasType(), feedActionProto$FeedActionMetadata.f1234n);
                this.f1235p = xn.visitInt(hasElementTypeValue(), this.f1235p, feedActionProto$FeedActionMetadata.hasElementTypeValue(), feedActionProto$FeedActionMetadata.f1235p);
                int ordinal = DataCase.forNumber(feedActionProto$FeedActionMetadata.f1232e).ordinal();
                if (ordinal == 0) {
                    if (this.f1232e == 2) {
                        z = true;
                    }
                    this.f1233k = xn.visitOneofMessage(z, this.f1233k, feedActionProto$FeedActionMetadata.f1233k);
                } else if (ordinal == 1) {
                    if (this.f1232e == 3) {
                        z = true;
                    }
                    this.f1233k = xn.visitOneofMessage(z, this.f1233k, feedActionProto$FeedActionMetadata.f1233k);
                } else if (ordinal == 2) {
                    if (this.f1232e == 4) {
                        z = true;
                    }
                    this.f1233k = xn.visitOneofMessage(z, this.f1233k, feedActionProto$FeedActionMetadata.f1233k);
                } else if (ordinal == 3) {
                    if (this.f1232e == 6) {
                        z = true;
                    }
                    this.f1233k = xn.visitOneofMessage(z, this.f1233k, feedActionProto$FeedActionMetadata.f1233k);
                } else if (ordinal == 4) {
                    if (this.f1232e == 8) {
                        z = true;
                    }
                    this.f1233k = xn.visitOneofMessage(z, this.f1233k, feedActionProto$FeedActionMetadata.f1233k);
                } else if (ordinal == 5) {
                    if (this.f1232e != 0) {
                        z = true;
                    }
                    xn.visitOneofNotSet(z);
                }
                if (xn == XN.a) {
                    int i = feedActionProto$FeedActionMetadata.f1232e;
                    if (i != 0) {
                        this.f1232e = i;
                    }
                    this.f1231d |= feedActionProto$FeedActionMetadata.f1231d;
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
                                if (Type.forNumber(j) == null) {
                                    super.mo2487a(1, j);
                                } else {
                                    this.f1231d |= 1;
                                    this.f1234n = j;
                                }
                            } else if (n == 18) {
                                vQ a = this.f1232e == 2 ? ((wQ) this.f1233k).m1151a() : null;
                                this.f1233k = ln.a(wQ.n.mo2494h(), rn);
                                if (a != null) {
                                    a.a((wQ) this.f1233k);
                                    this.f1233k = a.buildPartial();
                                }
                                this.f1232e = 2;
                            } else if (n == 26) {
                                tQ a2 = this.f1232e == 3 ? ((uQ) this.f1233k).m1151a() : null;
                                this.f1233k = ln.a(uQ.k.mo2494h(), rn);
                                if (a2 != null) {
                                    a2.a((uQ) this.f1233k);
                                    this.f1233k = a2.buildPartial();
                                }
                                this.f1232e = 3;
                            } else if (n == 34) {
                                lQ a3 = this.f1232e == 4 ? ((mQ) this.f1233k).m1151a() : null;
                                this.f1233k = ln.a(mQ.x.mo2494h(), rn);
                                if (a3 != null) {
                                    a3.a((mQ) this.f1233k);
                                    this.f1233k = a3.buildPartial();
                                }
                                this.f1232e = 4;
                            } else if (n == 50) {
                                sQ a4 = this.f1232e == 6 ? ((FeedActionProto$NotInterestedInData) this.f1233k).m1151a() : null;
                                this.f1233k = ln.a(FeedActionProto$NotInterestedInData.f1237x.mo2494h(), rn);
                                if (a4 != null) {
                                    a4.a((FeedActionProto$NotInterestedInData) this.f1233k);
                                    this.f1233k = a4.buildPartial();
                                }
                                this.f1232e = 6;
                            } else if (n == 66) {
                                xQ a5 = this.f1232e == 8 ? ((FeedActionProto$TooltipData) this.f1233k).m1151a() : null;
                                this.f1233k = ln.a(FeedActionProto$TooltipData.f1245q.mo2494h(), rn);
                                if (a5 != null) {
                                    a5.a((FeedActionProto$TooltipData) this.f1233k);
                                    this.f1233k = a5.buildPartial();
                                }
                                this.f1232e = 8;
                            } else if (n == 72) {
                                this.f1231d |= 64;
                                this.f1235p = ln.j();
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
                return new FeedActionProto$FeedActionMetadata();
            case 5:
                return new nQ((kQ) null);
            case 6:
                break;
            case 7:
                if (f1230y == null) {
                    synchronized (FeedActionProto$FeedActionMetadata.class) {
                        if (f1230y == null) {
                            f1230y = new UN(f1229x);
                        }
                    }
                }
                return f1230y;
            default:
                throw new UnsupportedOperationException();
        }
        return f1229x;
    }
}
