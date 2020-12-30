package com.google.protobuf;

import TN;
import com.google.protobuf.FieldSet;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLite;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
public abstract class GeneratedMessageLite<MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends TN<MessageType, BuilderType>> extends FN<MessageType, BuilderType> {

    /* renamed from: b */
    public mO f1168b = mO.f;

    /* renamed from: c */
    public int f1169c = -1;

    /* compiled from: PG */
    public static class EqualsVisitor implements Visitor {

        /* renamed from: a */
        public static final EqualsVisitor f1170a = new EqualsVisitor();

        /* renamed from: b */
        public static final NotEqualsException f1171b = new NotEqualsException();

        /* compiled from: PG */
        public static final class NotEqualsException extends RuntimeException {
        }

        public boolean visitBoolean(boolean z, boolean z2, boolean z3, boolean z4) {
            if (z == z3 && z2 == z4) {
                return z2;
            }
            throw f1171b;
        }

        public Internal.BooleanList visitBooleanList(Internal.BooleanList booleanList, Internal.BooleanList booleanList2) {
            if (booleanList.equals(booleanList2)) {
                return booleanList;
            }
            throw f1171b;
        }

        public ByteString visitByteString(boolean z, ByteString byteString, boolean z2, ByteString byteString2) {
            if (z == z2 && byteString.equals(byteString2)) {
                return byteString;
            }
            throw f1171b;
        }

        public double visitDouble(boolean z, double d, boolean z2, double d2) {
            if (z == z2 && d == d2) {
                return d;
            }
            throw f1171b;
        }

        public Internal.DoubleList visitDoubleList(Internal.DoubleList doubleList, Internal.DoubleList doubleList2) {
            if (doubleList.equals(doubleList2)) {
                return doubleList;
            }
            throw f1171b;
        }

        public FieldSet<C0284c> visitExtensions(FieldSet<C0284c> fieldSet, FieldSet<C0284c> fieldSet2) {
            if (fieldSet.equals(fieldSet2)) {
                return fieldSet;
            }
            throw f1171b;
        }

        public float visitFloat(boolean z, float f, boolean z2, float f2) {
            if (z == z2 && f == f2) {
                return f;
            }
            throw f1171b;
        }

        public Internal.FloatList visitFloatList(Internal.FloatList floatList, Internal.FloatList floatList2) {
            if (floatList.equals(floatList2)) {
                return floatList;
            }
            throw f1171b;
        }

        public int visitInt(boolean z, int i, boolean z2, int i2) {
            if (z == z2 && i == i2) {
                return i;
            }
            throw f1171b;
        }

        public Internal.IntList visitIntList(Internal.IntList intList, Internal.IntList intList2) {
            if (intList.equals(intList2)) {
                return intList;
            }
            throw f1171b;
        }

        public aO visitLazyMessage(aO aOVar, aO aOVar2) {
            if (aOVar == null && aOVar2 == null) {
                return null;
            }
            if (aOVar == null || aOVar2 == null) {
                throw f1171b;
            } else if (aOVar.equals(aOVar2)) {
                return aOVar;
            } else {
                throw f1171b;
            }
        }

        public <T> Internal.ProtobufList<T> visitList(Internal.ProtobufList<T> protobufList, Internal.ProtobufList<T> protobufList2) {
            if (protobufList.equals(protobufList2)) {
                return protobufList;
            }
            throw f1171b;
        }

        public long visitLong(boolean z, long j, boolean z2, long j2) {
            if (z == z2 && j == j2) {
                return j;
            }
            throw f1171b;
        }

        public Internal.LongList visitLongList(Internal.LongList longList, Internal.LongList longList2) {
            if (longList.equals(longList2)) {
                return longList;
            }
            throw f1171b;
        }

        public <K, V> MapFieldLite<K, V> visitMap(MapFieldLite<K, V> mapFieldLite, MapFieldLite<K, V> mapFieldLite2) {
            if (mapFieldLite.equals(mapFieldLite2)) {
                return mapFieldLite;
            }
            throw f1171b;
        }

        public <T extends MessageLite> T visitMessage(T t, T t2) {
            if (t == null && t2 == null) {
                return null;
            }
            if (t == null || t2 == null) {
                throw f1171b;
            }
            ((GeneratedMessageLite) t).mo2490a(this, (MessageLite) t2);
            return t;
        }

        public Object visitOneofBoolean(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw f1171b;
        }

        public Object visitOneofByteString(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw f1171b;
        }

        public Object visitOneofDouble(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw f1171b;
        }

        public Object visitOneofFloat(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw f1171b;
        }

        public Object visitOneofInt(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw f1171b;
        }

        public Object visitOneofLazyMessage(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw f1171b;
        }

        public Object visitOneofLong(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw f1171b;
        }

        public Object visitOneofMessage(boolean z, Object obj, Object obj2) {
            if (z && ((GeneratedMessageLite) obj).mo2490a(this, (MessageLite) obj2)) {
                return obj;
            }
            throw f1171b;
        }

        public void visitOneofNotSet(boolean z) {
            if (z) {
                throw f1171b;
            }
        }

        public Object visitOneofString(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw f1171b;
        }

        public String visitString(boolean z, String str, boolean z2, String str2) {
            if (z == z2 && str.equals(str2)) {
                return str;
            }
            throw f1171b;
        }

        public mO visitUnknownFields(mO mOVar, mO mOVar2) {
            if (mOVar.equals(mOVar2)) {
                return mOVar;
            }
            throw f1171b;
        }
    }

    /* compiled from: PG */
    public interface ExtendableMessageOrBuilder<MessageType extends C0282b<MessageType, BuilderType>, BuilderType extends C0281a<MessageType, BuilderType>> extends cO {
        <Type> Type getExtension(ON<MessageType, Type> on);

        <Type> Type getExtension(ON<MessageType, List<Type>> on, int i);

        <Type> int getExtensionCount(ON<MessageType, List<Type>> on);

        <Type> boolean hasExtension(ON<MessageType, Type> on);
    }

    /* compiled from: PG */
    public enum MethodToInvoke {
        IS_INITIALIZED,
        VISIT,
        MERGE_FROM_STREAM,
        MAKE_IMMUTABLE,
        NEW_MUTABLE_INSTANCE,
        NEW_BUILDER,
        GET_DEFAULT_INSTANCE,
        GET_PARSER
    }

    /* compiled from: PG */
    public static final class SerializedForm implements Serializable {
        public static final long serialVersionUID = 0;
        public final byte[] asBytes;
        public final String messageClassName;

        public SerializedForm(MessageLite messageLite) {
            this.messageClassName = messageLite.getClass().getName();
            this.asBytes = ((FN) messageLite).f();
        }

        /* renamed from: of */
        public static SerializedForm m1164of(MessageLite messageLite) {
            return new SerializedForm(messageLite);
        }

        @Deprecated
        private Object readResolveFallback() throws ObjectStreamException {
            try {
                Field declaredField = Class.forName(this.messageClassName).getDeclaredField("defaultInstance");
                declaredField.setAccessible(true);
                return ((MessageLite) declaredField.get((Object) null)).mo2585c().mergeFrom(this.asBytes).buildPartial();
            } catch (ClassNotFoundException e) {
                StringBuilder a = Eo.a("Unable to find proto buffer class: ");
                a.append(this.messageClassName);
                throw new RuntimeException(a.toString(), e);
            } catch (NoSuchFieldException e2) {
                StringBuilder a2 = Eo.a("Unable to find defaultInstance in ");
                a2.append(this.messageClassName);
                throw new RuntimeException(a2.toString(), e2);
            } catch (SecurityException e3) {
                StringBuilder a3 = Eo.a("Unable to call defaultInstance in ");
                a3.append(this.messageClassName);
                throw new RuntimeException(a3.toString(), e3);
            } catch (IllegalAccessException e4) {
                throw new RuntimeException("Unable to call parsePartialFrom", e4);
            } catch (InvalidProtocolBufferException e5) {
                throw new RuntimeException("Unable to understand proto buffer", e5);
            }
        }

        public Object readResolve() throws ObjectStreamException {
            try {
                Field declaredField = Class.forName(this.messageClassName).getDeclaredField("DEFAULT_INSTANCE");
                declaredField.setAccessible(true);
                return ((MessageLite) declaredField.get((Object) null)).mo2585c().mergeFrom(this.asBytes).buildPartial();
            } catch (ClassNotFoundException e) {
                StringBuilder a = Eo.a("Unable to find proto buffer class: ");
                a.append(this.messageClassName);
                throw new RuntimeException(a.toString(), e);
            } catch (NoSuchFieldException unused) {
                return readResolveFallback();
            } catch (SecurityException e2) {
                StringBuilder a2 = Eo.a("Unable to call DEFAULT_INSTANCE in ");
                a2.append(this.messageClassName);
                throw new RuntimeException(a2.toString(), e2);
            } catch (IllegalAccessException e3) {
                throw new RuntimeException("Unable to call parsePartialFrom", e3);
            } catch (InvalidProtocolBufferException e4) {
                throw new RuntimeException("Unable to understand proto buffer", e4);
            }
        }
    }

    /* compiled from: PG */
    public interface Visitor {
        boolean visitBoolean(boolean z, boolean z2, boolean z3, boolean z4);

        Internal.BooleanList visitBooleanList(Internal.BooleanList booleanList, Internal.BooleanList booleanList2);

        ByteString visitByteString(boolean z, ByteString byteString, boolean z2, ByteString byteString2);

        double visitDouble(boolean z, double d, boolean z2, double d2);

        Internal.DoubleList visitDoubleList(Internal.DoubleList doubleList, Internal.DoubleList doubleList2);

        FieldSet<C0284c> visitExtensions(FieldSet<C0284c> fieldSet, FieldSet<C0284c> fieldSet2);

        float visitFloat(boolean z, float f, boolean z2, float f2);

        Internal.FloatList visitFloatList(Internal.FloatList floatList, Internal.FloatList floatList2);

        int visitInt(boolean z, int i, boolean z2, int i2);

        Internal.IntList visitIntList(Internal.IntList intList, Internal.IntList intList2);

        aO visitLazyMessage(aO aOVar, aO aOVar2);

        <T> Internal.ProtobufList<T> visitList(Internal.ProtobufList<T> protobufList, Internal.ProtobufList<T> protobufList2);

        long visitLong(boolean z, long j, boolean z2, long j2);

        Internal.LongList visitLongList(Internal.LongList longList, Internal.LongList longList2);

        <K, V> MapFieldLite<K, V> visitMap(MapFieldLite<K, V> mapFieldLite, MapFieldLite<K, V> mapFieldLite2);

        <T extends MessageLite> T visitMessage(T t, T t2);

        Object visitOneofBoolean(boolean z, Object obj, Object obj2);

        Object visitOneofByteString(boolean z, Object obj, Object obj2);

        Object visitOneofDouble(boolean z, Object obj, Object obj2);

        Object visitOneofFloat(boolean z, Object obj, Object obj2);

        Object visitOneofInt(boolean z, Object obj, Object obj2);

        Object visitOneofLazyMessage(boolean z, Object obj, Object obj2);

        Object visitOneofLong(boolean z, Object obj, Object obj2);

        Object visitOneofMessage(boolean z, Object obj, Object obj2);

        void visitOneofNotSet(boolean z);

        Object visitOneofString(boolean z, Object obj, Object obj2);

        String visitString(boolean z, String str, boolean z2, String str2);

        mO visitUnknownFields(mO mOVar, mO mOVar2);
    }

    /* renamed from: com.google.protobuf.GeneratedMessageLite$a */
    /* compiled from: PG */
    public static abstract class C0281a<MessageType extends C0282b<MessageType, BuilderType>, BuilderType extends C0281a<MessageType, BuilderType>> extends TN<MessageType, BuilderType> implements ExtendableMessageOrBuilder<MessageType, BuilderType> {
        public C0281a(MessageType messagetype) {
            super(messagetype);
            GeneratedMessageLite generatedMessageLite = this.b;
            ((C0282b) generatedMessageLite).f1172d = ((C0282b) generatedMessageLite).f1172d.clone();
        }

        /* renamed from: e */
        public void mo2534e() {
            if (this.c) {
                GeneratedMessageLite.super.e();
                GeneratedMessageLite generatedMessageLite = this.b;
                ((C0282b) generatedMessageLite).f1172d = ((C0282b) generatedMessageLite).f1172d.clone();
            }
        }

        public final <Type> Type getExtension(ON<MessageType, Type> on) {
            return ((C0282b) this.b).getExtension(on);
        }

        public final <Type> int getExtensionCount(ON<MessageType, List<Type>> on) {
            return ((C0282b) this.b).getExtensionCount(on);
        }

        public final <Type> boolean hasExtension(ON<MessageType, Type> on) {
            return ((C0282b) this.b).hasExtension(on);
        }

        public final <Type> Type getExtension(ON<MessageType, List<Type>> on, int i) {
            return ((C0282b) this.b).getExtension(on, i);
        }

        public final MessageType buildPartial() {
            if (this.c) {
                return (C0282b) this.b;
            }
            ((C0282b) this.b).f1172d.mo2470b();
            return (C0282b) GeneratedMessageLite.super.buildPartial();
        }

        public BuilderType clone() {
            return (C0281a) GeneratedMessageLite.super.clone();
        }
    }

    /* renamed from: com.google.protobuf.GeneratedMessageLite$c */
    /* compiled from: PG */
    public static final class C0284c implements FieldSet.FieldDescriptorLite<C0284c> {

        /* renamed from: a */
        public final Internal.EnumLiteMap<?> f1177a;

        /* renamed from: b */
        public final int f1178b;

        /* renamed from: c */
        public final WireFormat.FieldType f1179c;

        /* renamed from: d */
        public final boolean f1180d;

        /* renamed from: e */
        public final boolean f1181e;

        public C0284c(Internal.EnumLiteMap<?> enumLiteMap, int i, WireFormat.FieldType fieldType, boolean z, boolean z2) {
            this.f1177a = enumLiteMap;
            this.f1178b = i;
            this.f1179c = fieldType;
            this.f1180d = z;
            this.f1181e = z2;
        }

        public int compareTo(Object obj) {
            return this.f1178b - ((C0284c) obj).f1178b;
        }

        public Internal.EnumLiteMap<?> getEnumType() {
            return this.f1177a;
        }

        public WireFormat.JavaType getLiteJavaType() {
            return this.f1179c.getJavaType();
        }

        public WireFormat.FieldType getLiteType() {
            return this.f1179c;
        }

        public int getNumber() {
            return this.f1178b;
        }

        public MessageLite.Builder internalMergeFrom(MessageLite.Builder builder, MessageLite messageLite) {
            TN tn = (TN) builder;
            tn.a((GeneratedMessageLite) messageLite);
            return tn;
        }

        public boolean isPacked() {
            return this.f1181e;
        }

        public boolean isRepeated() {
            return this.f1180d;
        }
    }

    /* renamed from: a */
    public abstract Object mo2486a(MethodToInvoke methodToInvoke, Object obj, Object obj2);

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!m1161d().getClass().isInstance(obj)) {
            return false;
        }
        try {
            mo2488a((Visitor) EqualsVisitor.f1170a, (GeneratedMessageLite) obj);
            return true;
        } catch (EqualsVisitor.NotEqualsException unused) {
            return false;
        }
    }

    /* renamed from: h */
    public final eO<MessageType> mo2494h() {
        return (eO) mo2485a(MethodToInvoke.GET_PARSER);
    }

    public int hashCode() {
        if (this.a == 0) {
            WN wn = new WN((SN) null);
            mo2488a((Visitor) wn, this);
            this.a = wn.a;
        }
        return this.a;
    }

    /* renamed from: i */
    public void mo2496i() {
        mo2485a(MethodToInvoke.MAKE_IMMUTABLE);
        this.f1168b.e = false;
    }

    public final boolean isInitialized() {
        return mo2486a(MethodToInvoke.IS_INITIALIZED, (Object) Boolean.TRUE, (Object) null) != null;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.google.protobuf.MessageLite, com.google.protobuf.GeneratedMessageLite, java.lang.Object] */
    public String toString() {
        String obj = GeneratedMessageLite.super.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(obj);
        dO.a(this, sb, 0);
        return sb.toString();
    }

    /* renamed from: a */
    public boolean mo2490a(EqualsVisitor equalsVisitor, MessageLite messageLite) {
        if (this == messageLite) {
            return true;
        }
        if (!m1161d().getClass().isInstance(messageLite)) {
            return false;
        }
        mo2488a((Visitor) equalsVisitor, (GeneratedMessageLite) messageLite);
        return true;
    }

    /* renamed from: c */
    public final BuilderType m1159c() {
        return (TN) mo2485a(MethodToInvoke.NEW_BUILDER);
    }

    /* renamed from: d */
    public final MessageType m1161d() {
        return (GeneratedMessageLite) mo2485a(MethodToInvoke.GET_DEFAULT_INSTANCE);
    }

    /* renamed from: a */
    public void mo2487a(int i, int i2) {
        if (this.f1168b == mO.f) {
            this.f1168b = new mO();
        }
        mO mOVar = this.f1168b;
        if (!mOVar.e) {
            throw new UnsupportedOperationException();
        } else if (i != 0) {
            mOVar.a((i << 3) | 0, Long.valueOf((long) i2));
        } else {
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }
    }

    /* renamed from: com.google.protobuf.GeneratedMessageLite$b */
    /* compiled from: PG */
    public static abstract class C0282b<MessageType extends C0282b<MessageType, BuilderType>, BuilderType extends C0281a<MessageType, BuilderType>> extends GeneratedMessageLite<MessageType, BuilderType> implements ExtendableMessageOrBuilder<MessageType, BuilderType> {

        /* renamed from: d */
        public FieldSet<C0284c> f1172d = new FieldSet<>();

        /* renamed from: com.google.protobuf.GeneratedMessageLite$b$a */
        /* compiled from: PG */
        public class C0283a {

            /* renamed from: a */
            public final Iterator<Map.Entry<C0284c, Object>> f1173a = C0282b.this.f1172d.mo2465a();

            /* renamed from: b */
            public Map.Entry<C0284c, Object> f1174b;

            /* renamed from: c */
            public final boolean f1175c;

            public /* synthetic */ C0283a(boolean z, SN sn) {
                if (this.f1173a.hasNext()) {
                    this.f1174b = this.f1173a.next();
                }
                this.f1175c = z;
            }

            /* renamed from: a */
            public void mo2540a(int i, CodedOutputStream codedOutputStream) throws IOException {
                while (true) {
                    Map.Entry<C0284c, Object> entry = this.f1174b;
                    if (entry != null && entry.getKey().f1178b < i) {
                        C0284c key = this.f1174b.getKey();
                        if (!this.f1175c || key.getLiteJavaType() != WireFormat.JavaType.MESSAGE || key.f1180d) {
                            FieldSet.m1129a((FieldSet.FieldDescriptorLite<?>) key, this.f1174b.getValue(), codedOutputStream);
                        } else {
                            int i2 = key.f1178b;
                            NN nn = (NN) codedOutputStream;
                            nn.d(1, 3);
                            nn.e(2, i2);
                            nn.b(3, (MessageLite) this.f1174b.getValue());
                            nn.d(1, 4);
                        }
                        if (this.f1173a.hasNext()) {
                            this.f1174b = this.f1173a.next();
                        } else {
                            this.f1174b = null;
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x0041  */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0046  */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public <MessageType extends com.google.protobuf.MessageLite> boolean mo2536a(MessageType r7, LN r8, RN r9, int r10) throws java.io.IOException {
            /*
                r6 = this;
                r0 = r10 & 7
                int r1 = r10 >>> 3
                java.util.Map r2 = r9.a
                QN r3 = new QN
                r3.<init>(r7, r1)
                java.lang.Object r7 = r2.get(r3)
                VN r7 = (VN) r7
                r2 = 1
                r3 = 0
                if (r7 != 0) goto L_0x0016
                goto L_0x003d
            L_0x0016:
                com.google.protobuf.GeneratedMessageLite$c r4 = r7.d
                com.google.protobuf.WireFormat$FieldType r4 = r4.f1179c
                int r4 = com.google.protobuf.FieldSet.m1125a((com.google.protobuf.WireFormat.FieldType) r4, (boolean) r3)
                if (r0 != r4) goto L_0x0022
                r0 = 0
                goto L_0x003e
            L_0x0022:
                com.google.protobuf.GeneratedMessageLite$c r4 = r7.d
                boolean r5 = r4.f1180d
                if (r5 == 0) goto L_0x003d
                com.google.protobuf.WireFormat$FieldType r4 = r4.f1179c
                boolean r4 = r4.isPackable()
                if (r4 == 0) goto L_0x003d
                com.google.protobuf.GeneratedMessageLite$c r4 = r7.d
                com.google.protobuf.WireFormat$FieldType r4 = r4.f1179c
                int r4 = com.google.protobuf.FieldSet.m1125a((com.google.protobuf.WireFormat.FieldType) r4, (boolean) r2)
                if (r0 != r4) goto L_0x003d
                r0 = 0
                r4 = 1
                goto L_0x003f
            L_0x003d:
                r0 = 1
            L_0x003e:
                r4 = 0
            L_0x003f:
                if (r0 == 0) goto L_0x0046
                boolean r7 = r6.mo2489a((int) r10, (LN) r8)
                return r7
            L_0x0046:
                if (r4 == 0) goto L_0x0096
                int r9 = r8.j()
                int r9 = r8.b(r9)
                com.google.protobuf.GeneratedMessageLite$c r10 = r7.d
                com.google.protobuf.WireFormat$FieldType r10 = r10.f1179c
                com.google.protobuf.WireFormat$FieldType r0 = com.google.protobuf.WireFormat.FieldType.ENUM
                if (r10 != r0) goto L_0x0079
            L_0x0058:
                int r10 = r8.a()
                if (r10 <= 0) goto L_0x008f
                int r10 = r8.j()
                com.google.protobuf.GeneratedMessageLite$c r0 = r7.d
                com.google.protobuf.Internal$EnumLiteMap<?> r0 = r0.f1177a
                com.google.protobuf.Internal$EnumLite r10 = r0.findValueByNumber(r10)
                if (r10 != 0) goto L_0x006d
                return r2
            L_0x006d:
                com.google.protobuf.FieldSet<com.google.protobuf.GeneratedMessageLite$c> r0 = r6.f1172d
                com.google.protobuf.GeneratedMessageLite$c r1 = r7.d
                java.lang.Object r10 = r7.b(r10)
                r0.mo2466a(r1, (java.lang.Object) r10)
                goto L_0x0058
            L_0x0079:
                int r10 = r8.a()
                if (r10 <= 0) goto L_0x008f
                com.google.protobuf.GeneratedMessageLite$c r10 = r7.d
                com.google.protobuf.WireFormat$FieldType r10 = r10.f1179c
                java.lang.Object r10 = com.google.protobuf.FieldSet.m1126a((LN) r8, (com.google.protobuf.WireFormat.FieldType) r10, (boolean) r3)
                com.google.protobuf.FieldSet<com.google.protobuf.GeneratedMessageLite$c> r0 = r6.f1172d
                com.google.protobuf.GeneratedMessageLite$c r1 = r7.d
                r0.mo2466a(r1, (java.lang.Object) r10)
                goto L_0x0079
            L_0x008f:
                r8.j = r9
                r8.p()
                goto L_0x0153
            L_0x0096:
                com.google.protobuf.GeneratedMessageLite$c r10 = r7.d
                com.google.protobuf.WireFormat$JavaType r10 = r10.getLiteJavaType()
                int r10 = r10.ordinal()
                r0 = 7
                if (r10 == r0) goto L_0x0127
                r0 = 8
                if (r10 == r0) goto L_0x00b1
                com.google.protobuf.GeneratedMessageLite$c r9 = r7.d
                com.google.protobuf.WireFormat$FieldType r9 = r9.f1179c
                java.lang.Object r8 = com.google.protobuf.FieldSet.m1126a((LN) r8, (com.google.protobuf.WireFormat.FieldType) r9, (boolean) r3)
                goto L_0x013a
            L_0x00b1:
                r10 = 0
                com.google.protobuf.GeneratedMessageLite$c r0 = r7.d
                boolean r1 = r0.f1180d
                if (r1 != 0) goto L_0x00c8
                com.google.protobuf.FieldSet<com.google.protobuf.GeneratedMessageLite$c> r1 = r6.f1172d
                kO<FieldDescriptorType, java.lang.Object> r1 = r1.f1165a
                java.lang.Object r0 = r1.get(r0)
                com.google.protobuf.MessageLite r0 = (com.google.protobuf.MessageLite) r0
                if (r0 == 0) goto L_0x00c8
                com.google.protobuf.MessageLite$Builder r10 = r0.mo2582a()
            L_0x00c8:
                if (r10 != 0) goto L_0x00d0
                com.google.protobuf.MessageLite r10 = r7.c
                com.google.protobuf.MessageLite$Builder r10 = r10.mo2585c()
            L_0x00d0:
                com.google.protobuf.GeneratedMessageLite$c r0 = r7.d
                com.google.protobuf.WireFormat$FieldType r1 = r0.f1179c
                com.google.protobuf.WireFormat$FieldType r4 = com.google.protobuf.WireFormat.FieldType.GROUP
                if (r1 != r4) goto L_0x00f9
                int r0 = r0.f1178b
                int r1 = r8.k
                int r3 = r8.l
                if (r1 >= r3) goto L_0x00f4
                int r1 = r1 + r2
                r8.k = r1
                r10.mergeFrom((LN) r8, (RN) r9)
                int r9 = r0 << 3
                r9 = r9 | 4
                r8.a(r9)
                int r9 = r8.k
                int r9 = r9 + -1
                r8.k = r9
                goto L_0x011d
            L_0x00f4:
                com.google.protobuf.InvalidProtocolBufferException r7 = com.google.protobuf.InvalidProtocolBufferException.recursionLimitExceeded()
                throw r7
            L_0x00f9:
                int r0 = r8.j()
                int r1 = r8.k
                int r4 = r8.l
                if (r1 >= r4) goto L_0x0122
                int r0 = r8.b(r0)
                int r1 = r8.k
                int r1 = r1 + r2
                r8.k = r1
                r10.mergeFrom((LN) r8, (RN) r9)
                r8.a(r3)
                int r9 = r8.k
                int r9 = r9 + -1
                r8.k = r9
                r8.j = r0
                r8.p()
            L_0x011d:
                com.google.protobuf.MessageLite r8 = r10.build()
                goto L_0x013a
            L_0x0122:
                com.google.protobuf.InvalidProtocolBufferException r7 = com.google.protobuf.InvalidProtocolBufferException.recursionLimitExceeded()
                throw r7
            L_0x0127:
                int r8 = r8.j()
                com.google.protobuf.GeneratedMessageLite$c r9 = r7.d
                com.google.protobuf.Internal$EnumLiteMap<?> r9 = r9.f1177a
                com.google.protobuf.Internal$EnumLite r9 = r9.findValueByNumber(r8)
                if (r9 != 0) goto L_0x0139
                r6.mo2487a((int) r1, (int) r8)
                return r2
            L_0x0139:
                r8 = r9
            L_0x013a:
                com.google.protobuf.GeneratedMessageLite$c r9 = r7.d
                boolean r10 = r9.f1180d
                if (r10 == 0) goto L_0x014a
                com.google.protobuf.FieldSet<com.google.protobuf.GeneratedMessageLite$c> r10 = r6.f1172d
                java.lang.Object r7 = r7.b(r8)
                r10.mo2466a(r9, (java.lang.Object) r7)
                goto L_0x0153
            L_0x014a:
                com.google.protobuf.FieldSet<com.google.protobuf.GeneratedMessageLite$c> r10 = r6.f1172d
                java.lang.Object r7 = r7.b(r8)
                r10.mo2471b(r9, (java.lang.Object) r7)
            L_0x0153:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.GeneratedMessageLite.C0282b.mo2536a(com.google.protobuf.MessageLite, LN, RN, int):boolean");
        }

        /* renamed from: c */
        public /* bridge */ /* synthetic */ MessageLite.Builder mo2491c() {
            return GeneratedMessageLite.super.m1159c();
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [com.google.protobuf.MessageLite, com.google.protobuf.GeneratedMessageLite] */
        /* renamed from: d */
        public /* bridge */ /* synthetic */ MessageLite mo2492d() {
            return GeneratedMessageLite.super.m1161d();
        }

        public final <Type> Type getExtension(ON<MessageType, Type> on) {
            on.a();
            VN vn = (VN) on;
            mo2535a(vn);
            Type a = this.f1172d.mo2462a(vn.d);
            if (a == null) {
                return vn.b;
            }
            C0284c cVar = vn.d;
            if (!cVar.f1180d) {
                return vn.a(a);
            }
            if (cVar.getLiteJavaType() != WireFormat.JavaType.ENUM) {
                return a;
            }
            Type arrayList = new ArrayList();
            for (Object a2 : (List) a) {
                arrayList.add(vn.a(a2));
            }
            return arrayList;
        }

        public final <Type> int getExtensionCount(ON<MessageType, List<Type>> on) {
            on.a();
            VN vn = (VN) on;
            mo2535a(vn);
            return this.f1172d.mo2469b(vn.d);
        }

        public final <Type> boolean hasExtension(ON<MessageType, Type> on) {
            on.a();
            VN vn = (VN) on;
            mo2535a(vn);
            return this.f1172d.mo2473c(vn.d);
        }

        /* renamed from: i */
        public final void mo2496i() {
            GeneratedMessageLite.super.mo2496i();
            this.f1172d.mo2470b();
        }

        /* renamed from: j */
        public boolean mo2537j() {
            FieldSet<C0284c> fieldSet = this.f1172d;
            for (int i = 0; i < fieldSet.f1165a.b(); i++) {
                if (!fieldSet.mo2468a((Map.Entry<C0284c, Object>) fieldSet.f1165a.a(i))) {
                    return false;
                }
            }
            for (Map.Entry a : fieldSet.f1165a.c()) {
                if (!fieldSet.mo2468a((Map.Entry<C0284c, Object>) a)) {
                    return false;
                }
            }
            return true;
        }

        /* renamed from: k */
        public int mo2538k() {
            FieldSet<C0284c> fieldSet = this.f1172d;
            int i = 0;
            for (int i2 = 0; i2 < fieldSet.f1165a.b(); i2++) {
                Map.Entry a = fieldSet.f1165a.a(i2);
                i += FieldSet.m1131c((FieldSet.FieldDescriptorLite) a.getKey(), a.getValue());
            }
            for (Map.Entry entry : fieldSet.f1165a.c()) {
                i += FieldSet.m1131c((FieldSet.FieldDescriptorLite) entry.getKey(), entry.getValue());
            }
            return i;
        }

        /* renamed from: l */
        public C0282b<MessageType, BuilderType>.a mo2539l() {
            return new C0283a(false, (SN) null);
        }

        public final <Type> Type getExtension(ON<MessageType, List<Type>> on, int i) {
            on.a();
            VN vn = (VN) on;
            mo2535a(vn);
            return vn.a(this.f1172d.mo2463a(vn.d, i));
        }

        /* renamed from: a */
        public /* bridge */ /* synthetic */ MessageLite.Builder mo2484a() {
            return GeneratedMessageLite.super.m1151a();
        }

        /* renamed from: a */
        public void mo2488a(Visitor visitor, GeneratedMessageLite generatedMessageLite) {
            C0282b bVar = (C0282b) generatedMessageLite;
            GeneratedMessageLite.super.mo2488a(visitor, bVar);
            this.f1172d = visitor.visitExtensions(this.f1172d, bVar.f1172d);
        }

        /* renamed from: a */
        public final void mo2535a(VN<MessageType, ?> vn) {
            if (vn.a != m1161d()) {
                throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
            }
        }
    }

    /* renamed from: a */
    public boolean mo2489a(int i, LN ln) throws IOException {
        if ((i & 7) == 4) {
            return false;
        }
        if (this.f1168b == mO.f) {
            this.f1168b = new mO();
        }
        return this.f1168b.a(i, ln);
    }

    /* renamed from: a */
    public final BuilderType m1151a() {
        BuilderType buildertype = (TN) mo2485a(MethodToInvoke.NEW_BUILDER);
        buildertype.e();
        buildertype.b.mo2488a((Visitor) XN.a, this);
        return buildertype;
    }

    /* renamed from: a */
    public Object mo2485a(MethodToInvoke methodToInvoke) {
        return mo2486a(methodToInvoke, (Object) null, (Object) null);
    }

    /* renamed from: a */
    public void mo2488a(Visitor visitor, MessageType messagetype) {
        mo2486a(MethodToInvoke.VISIT, (Object) visitor, (Object) messagetype);
        this.f1168b = visitor.visitUnknownFields(this.f1168b, messagetype.f1168b);
    }

    /* renamed from: a */
    public static <ContainingType extends MessageLite, Type> VN<ContainingType, Type> m1144a(ContainingType containingtype, Type type, MessageLite messageLite, Internal.EnumLiteMap<?> enumLiteMap, int i, WireFormat.FieldType fieldType, Class cls) {
        return new VN<>(containingtype, type, messageLite, new C0284c(enumLiteMap, i, fieldType, false, false));
    }

    /* renamed from: a */
    public static Object m1149a(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    /* renamed from: a */
    public static Internal.IntList m1147a(Internal.IntList intList) {
        int size = intList.size();
        return intList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
    }

    /* renamed from: a */
    public static <E> Internal.ProtobufList<E> m1148a(Internal.ProtobufList<E> protobufList) {
        int size = protobufList.size();
        return protobufList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
    }

    /* renamed from: a */
    public static <T extends GeneratedMessageLite<T, ?>> T m1145a(T t, LN ln, RN rn) throws InvalidProtocolBufferException {
        T t2 = (GeneratedMessageLite) t.mo2485a(MethodToInvoke.NEW_MUTABLE_INSTANCE);
        try {
            t2.mo2486a(MethodToInvoke.MERGE_FROM_STREAM, (Object) ln, (Object) rn);
            t2.mo2496i();
            return t2;
        } catch (RuntimeException e) {
            if (e.getCause() instanceof InvalidProtocolBufferException) {
                throw ((InvalidProtocolBufferException) e.getCause());
            }
            throw e;
        }
    }

    /* renamed from: a */
    public static <T extends GeneratedMessageLite<T, ?>> T m1146a(T t, byte[] bArr) throws InvalidProtocolBufferException {
        T a;
        RN a2 = RN.a();
        try {
            LN a3 = LN.a(bArr, 0, bArr.length);
            a = m1145a(t, a3, a2);
            a3.a(0);
            if (a == null || a.isInitialized()) {
                return a;
            }
            throw a.e().asInvalidProtocolBufferException().setUnfinishedMessage(a);
        } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(a);
        } catch (InvalidProtocolBufferException e2) {
            throw e2;
        }
    }
}
