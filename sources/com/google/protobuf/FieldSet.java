package com.google.protobuf;

import com.google.protobuf.FieldSet.FieldDescriptorLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLite;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
public final class FieldSet<FieldDescriptorType extends FieldDescriptorLite<FieldDescriptorType>> {

    /* renamed from: a */
    public final kO<FieldDescriptorType, Object> f1165a = kO.c(16);

    /* renamed from: b */
    public boolean f1166b;

    /* renamed from: c */
    public boolean f1167c = false;

    /* compiled from: PG */
    public interface FieldDescriptorLite<T extends FieldDescriptorLite<T>> extends Comparable<T> {
        Internal.EnumLiteMap<?> getEnumType();

        WireFormat.JavaType getLiteJavaType();

        WireFormat.FieldType getLiteType();

        int getNumber();

        MessageLite.Builder internalMergeFrom(MessageLite.Builder builder, MessageLite messageLite);

        boolean isPacked();

        boolean isRepeated();
    }

    static {
        new FieldSet(true);
    }

    public FieldSet() {
    }

    /* renamed from: a */
    public Iterator<Map.Entry<FieldDescriptorType, Object>> mo2465a() {
        if (this.f1167c) {
            return new ZN(this.f1165a.entrySet().iterator());
        }
        return this.f1165a.entrySet().iterator();
    }

    /* renamed from: b */
    public void mo2470b() {
        if (!this.f1166b) {
            this.f1165a.e();
            this.f1166b = true;
        }
    }

    /* renamed from: c */
    public boolean mo2473c(FieldDescriptorType fielddescriptortype) {
        if (!fielddescriptortype.isRepeated()) {
            return this.f1165a.get(fielddescriptortype) != null;
        }
        throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FieldSet)) {
            return false;
        }
        return this.f1165a.equals(((FieldSet) obj).f1165a);
    }

    public int hashCode() {
        return this.f1165a.hashCode();
    }

    public FieldSet<FieldDescriptorType> clone() {
        FieldSet<FieldDescriptorType> fieldSet = new FieldSet<>();
        for (int i = 0; i < this.f1165a.b(); i++) {
            Map.Entry a = this.f1165a.a(i);
            fieldSet.mo2471b((FieldDescriptorType) (FieldDescriptorLite) a.getKey(), a.getValue());
        }
        for (Map.Entry entry : this.f1165a.c()) {
            fieldSet.mo2471b((FieldDescriptorType) (FieldDescriptorLite) entry.getKey(), entry.getValue());
        }
        fieldSet.f1167c = this.f1167c;
        return fieldSet;
    }

    public FieldSet(boolean z) {
        mo2470b();
    }

    /* renamed from: c */
    public static int m1131c(FieldDescriptorLite<?> fieldDescriptorLite, Object obj) {
        WireFormat.FieldType liteType = fieldDescriptorLite.getLiteType();
        int number = fieldDescriptorLite.getNumber();
        if (fieldDescriptorLite.isRepeated()) {
            int i = 0;
            if (fieldDescriptorLite.isPacked()) {
                for (Object a : (List) obj) {
                    i += m1124a(liteType, a);
                }
                return CodedOutputStream.m1086g(number) + i + CodedOutputStream.m1089h(i);
            }
            for (Object next : (List) obj) {
                int g = CodedOutputStream.m1086g(number);
                if (liteType == WireFormat.FieldType.GROUP) {
                    g *= 2;
                }
                i += m1124a(liteType, next) + g;
            }
            return i;
        }
        int g2 = CodedOutputStream.m1086g(number);
        if (liteType == WireFormat.FieldType.GROUP) {
            g2 *= 2;
        }
        return m1124a(liteType, obj) + g2;
    }

    /* renamed from: b */
    public void mo2471b(FieldDescriptorType fielddescriptortype, Object obj) {
        if (!fielddescriptortype.isRepeated()) {
            m1130b(fielddescriptortype.getLiteType(), obj);
        } else if (obj instanceof List) {
            ArrayList<Object> arrayList = new ArrayList<>();
            arrayList.addAll((List) obj);
            for (Object b : arrayList) {
                m1130b(fielddescriptortype.getLiteType(), b);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        this.f1165a.a(fielddescriptortype, obj);
    }

    /* renamed from: a */
    public Object mo2462a(FieldDescriptorType fielddescriptortype) {
        return this.f1165a.get(fielddescriptortype);
    }

    /* renamed from: a */
    public Object mo2463a(FieldDescriptorType fielddescriptortype, int i) {
        if (fielddescriptortype.isRepeated()) {
            Object obj = this.f1165a.get(fielddescriptortype);
            if (obj != null) {
                return ((List) obj).get(i);
            }
            throw new IndexOutOfBoundsException();
        }
        throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    }

    /* renamed from: a */
    public void mo2466a(FieldDescriptorType fielddescriptortype, Object obj) {
        List list;
        if (fielddescriptortype.isRepeated()) {
            m1130b(fielddescriptortype.getLiteType(), obj);
            Object obj2 = this.f1165a.get(fielddescriptortype);
            if (obj2 == null) {
                list = new ArrayList();
                this.f1165a.a(fielddescriptortype, list);
            } else {
                list = (List) obj2;
            }
            list.add(obj);
            return;
        }
        throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
    }

    /* renamed from: b */
    public int mo2469b(FieldDescriptorType fielddescriptortype) {
        if (fielddescriptortype.isRepeated()) {
            Object obj = this.f1165a.get(fielddescriptortype);
            if (obj == null) {
                return 0;
            }
            return ((List) obj).size();
        }
        throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        if ((r3 instanceof byte[]) == false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0012, code lost:
        if ((r3 instanceof com.google.protobuf.MessageLite) == false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0015, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        if ((r3 instanceof com.google.protobuf.Internal.EnumLite) == false) goto L_0x003a;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m1130b(com.google.protobuf.WireFormat.FieldType r2, java.lang.Object r3) {
        /*
            if (r3 == 0) goto L_0x0045
            com.google.protobuf.WireFormat$JavaType r2 = r2.getJavaType()
            int r2 = r2.ordinal()
            r0 = 1
            r1 = 0
            switch(r2) {
                case 0: goto L_0x0038;
                case 1: goto L_0x0035;
                case 2: goto L_0x0032;
                case 3: goto L_0x002f;
                case 4: goto L_0x002c;
                case 5: goto L_0x0029;
                case 6: goto L_0x0020;
                case 7: goto L_0x0017;
                case 8: goto L_0x0010;
                default: goto L_0x000f;
            }
        L_0x000f:
            goto L_0x003a
        L_0x0010:
            boolean r2 = r3 instanceof com.google.protobuf.MessageLite
            if (r2 != 0) goto L_0x0015
            goto L_0x003a
        L_0x0015:
            r1 = 1
            goto L_0x003a
        L_0x0017:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L_0x0015
            boolean r2 = r3 instanceof com.google.protobuf.Internal.EnumLite
            if (r2 == 0) goto L_0x003a
            goto L_0x0015
        L_0x0020:
            boolean r2 = r3 instanceof com.google.protobuf.ByteString
            if (r2 != 0) goto L_0x0015
            boolean r2 = r3 instanceof byte[]
            if (r2 == 0) goto L_0x003a
            goto L_0x0015
        L_0x0029:
            boolean r1 = r3 instanceof java.lang.String
            goto L_0x003a
        L_0x002c:
            boolean r1 = r3 instanceof java.lang.Boolean
            goto L_0x003a
        L_0x002f:
            boolean r1 = r3 instanceof java.lang.Double
            goto L_0x003a
        L_0x0032:
            boolean r1 = r3 instanceof java.lang.Float
            goto L_0x003a
        L_0x0035:
            boolean r1 = r3 instanceof java.lang.Long
            goto L_0x003a
        L_0x0038:
            boolean r1 = r3 instanceof java.lang.Integer
        L_0x003a:
            if (r1 == 0) goto L_0x003d
            return
        L_0x003d:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Wrong object type used with protocol message reflection."
            r2.<init>(r3)
            throw r2
        L_0x0045:
            java.lang.NullPointerException r2 = new java.lang.NullPointerException
            r2.<init>()
            goto L_0x004c
        L_0x004b:
            throw r2
        L_0x004c:
            goto L_0x004b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.FieldSet.m1130b(com.google.protobuf.WireFormat$FieldType, java.lang.Object):void");
    }

    /* renamed from: a */
    public final boolean mo2468a(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorLite fieldDescriptorLite = (FieldDescriptorLite) entry.getKey();
        if (fieldDescriptorLite.getLiteJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        if (fieldDescriptorLite.isRepeated()) {
            for (MessageLite isInitialized : (List) entry.getValue()) {
                if (!isInitialized.isInitialized()) {
                    return false;
                }
            }
            return true;
        }
        Object value = entry.getValue();
        if (!(value instanceof MessageLite)) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        } else if (!((MessageLite) value).isInitialized()) {
            return false;
        } else {
            return true;
        }
    }

    /* renamed from: a */
    public static int m1125a(WireFormat.FieldType fieldType, boolean z) {
        if (z) {
            return 2;
        }
        return fieldType.getWireType();
    }

    /* renamed from: a */
    public void mo2467a(FieldSet<FieldDescriptorType> fieldSet) {
        for (int i = 0; i < fieldSet.f1165a.b(); i++) {
            mo2472b(fieldSet.f1165a.a(i));
        }
        for (Map.Entry b : fieldSet.f1165a.c()) {
            mo2472b(b);
        }
    }

    /* renamed from: b */
    public final void mo2472b(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorLite fieldDescriptorLite = (FieldDescriptorLite) entry.getKey();
        Object value = entry.getValue();
        if (fieldDescriptorLite.isRepeated()) {
            Object obj = this.f1165a.get(fieldDescriptorLite);
            if (obj == null) {
                obj = new ArrayList();
            }
            for (Object a : (List) value) {
                ((List) obj).add(mo2464a(a));
            }
            this.f1165a.a(fieldDescriptorLite, obj);
        } else if (fieldDescriptorLite.getLiteJavaType() == WireFormat.JavaType.MESSAGE) {
            Object obj2 = this.f1165a.get(fieldDescriptorLite);
            if (obj2 == null) {
                this.f1165a.a(fieldDescriptorLite, mo2464a(value));
                return;
            }
            this.f1165a.a(fieldDescriptorLite, fieldDescriptorLite.internalMergeFrom(((MessageLite) obj2).mo2582a(), (MessageLite) value).build());
        } else {
            this.f1165a.a(fieldDescriptorLite, mo2464a(value));
        }
    }

    /* renamed from: a */
    public final Object mo2464a(Object obj) {
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    /* renamed from: a */
    public static Object m1126a(LN ln, WireFormat.FieldType fieldType, boolean z) throws IOException {
        if (z) {
            return WireFormat.m1199a(ln, fieldType, WireFormat.Utf8Validation.STRICT);
        }
        return WireFormat.m1199a(ln, fieldType, WireFormat.Utf8Validation.LOOSE);
    }

    /* renamed from: a */
    public static void m1127a(CodedOutputStream codedOutputStream, WireFormat.FieldType fieldType, int i, Object obj) throws IOException {
        if (fieldType == WireFormat.FieldType.GROUP) {
            codedOutputStream.mo2442a(i, (MessageLite) obj);
            return;
        }
        ((NN) codedOutputStream).d((i << 3) | m1125a(fieldType, false));
        m1128a(codedOutputStream, fieldType, obj);
    }

    /* renamed from: a */
    public static void m1128a(CodedOutputStream codedOutputStream, WireFormat.FieldType fieldType, Object obj) throws IOException {
        switch (fieldType.ordinal()) {
            case 0:
                codedOutputStream.mo2434a(((Double) obj).doubleValue());
                return;
            case 1:
                codedOutputStream.mo2435a(((Float) obj).floatValue());
                return;
            case 2:
                codedOutputStream.mo2455b(((Long) obj).longValue());
                return;
            case 3:
                codedOutputStream.mo2455b(((Long) obj).longValue());
                return;
            case 4:
                codedOutputStream.mo2457c(((Integer) obj).intValue());
                return;
            case 5:
                codedOutputStream.mo2445a(((Long) obj).longValue());
                return;
            case 6:
                codedOutputStream.mo2451b(((Integer) obj).intValue());
                return;
            case 7:
                codedOutputStream.mo2433a(((Boolean) obj).booleanValue() ? (byte) 1 : 0);
                return;
            case 8:
                if (obj instanceof ByteString) {
                    codedOutputStream.mo2446a((ByteString) obj);
                    return;
                } else {
                    codedOutputStream.mo2448a((String) obj);
                    return;
                }
            case 9:
                codedOutputStream.mo2447a((MessageLite) obj);
                return;
            case 10:
                codedOutputStream.mo2456b((MessageLite) obj);
                return;
            case 11:
                if (obj instanceof ByteString) {
                    codedOutputStream.mo2446a((ByteString) obj);
                    return;
                } else {
                    codedOutputStream.mo2449a((byte[]) obj);
                    return;
                }
            case 12:
                codedOutputStream.mo2459d(((Integer) obj).intValue());
                return;
            case 13:
                if (obj instanceof Internal.EnumLite) {
                    codedOutputStream.mo2436a(((Internal.EnumLite) obj).getNumber());
                    return;
                } else {
                    codedOutputStream.mo2436a(((Integer) obj).intValue());
                    return;
                }
            case 14:
                codedOutputStream.mo2451b(((Integer) obj).intValue());
                return;
            case 15:
                codedOutputStream.mo2445a(((Long) obj).longValue());
                return;
            case 16:
                codedOutputStream.mo2459d(CodedOutputStream.m1092i(((Integer) obj).intValue()));
                return;
            case 17:
                codedOutputStream.mo2455b(CodedOutputStream.m1079d(((Long) obj).longValue()));
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public static void m1129a(FieldDescriptorLite<?> fieldDescriptorLite, Object obj, CodedOutputStream codedOutputStream) throws IOException {
        WireFormat.FieldType liteType = fieldDescriptorLite.getLiteType();
        int number = fieldDescriptorLite.getNumber();
        if (fieldDescriptorLite.isRepeated()) {
            List<Object> list = (List) obj;
            if (fieldDescriptorLite.isPacked()) {
                codedOutputStream.mo2460d(number, 2);
                int i = 0;
                for (Object a : list) {
                    i += m1124a(liteType, a);
                }
                codedOutputStream.mo2459d(i);
                for (Object a2 : list) {
                    m1128a(codedOutputStream, liteType, a2);
                }
                return;
            }
            for (Object a3 : list) {
                m1127a(codedOutputStream, liteType, number, a3);
            }
            return;
        }
        m1127a(codedOutputStream, liteType, number, obj);
    }

    /* renamed from: a */
    public static int m1124a(WireFormat.FieldType fieldType, Object obj) {
        switch (fieldType.ordinal()) {
            case 0:
                ((Double) obj).doubleValue();
                CodedOutputStream.m1077d();
                return 8;
            case 1:
                ((Float) obj).floatValue();
                CodedOutputStream.m1085g();
                return 4;
            case 2:
                return CodedOutputStream.m1074c(((Long) obj).longValue());
            case 3:
                return CodedOutputStream.m1074c(((Long) obj).longValue());
            case 4:
                return CodedOutputStream.m1081e(((Integer) obj).intValue());
            case 5:
                ((Long) obj).longValue();
                CodedOutputStream.m1082f();
                return 8;
            case 6:
                ((Integer) obj).intValue();
                CodedOutputStream.m1080e();
                return 4;
            case 7:
                ((Boolean) obj).booleanValue();
                CodedOutputStream.m1071c();
                return 1;
            case 8:
                if (obj instanceof ByteString) {
                    return CodedOutputStream.m1068b((ByteString) obj);
                }
                return CodedOutputStream.m1069b((String) obj);
            case 9:
                return CodedOutputStream.m1075c((MessageLite) obj);
            case 10:
                return CodedOutputStream.m1078d((MessageLite) obj);
            case 11:
                if (obj instanceof ByteString) {
                    return CodedOutputStream.m1068b((ByteString) obj);
                }
                return CodedOutputStream.m1070b((byte[]) obj);
            case 12:
                return CodedOutputStream.m1089h(((Integer) obj).intValue());
            case 13:
                if (obj instanceof Internal.EnumLite) {
                    return CodedOutputStream.m1081e(((Internal.EnumLite) obj).getNumber());
                }
                return CodedOutputStream.m1081e(((Integer) obj).intValue());
            case 14:
                ((Integer) obj).intValue();
                CodedOutputStream.m1088h();
                return 4;
            case 15:
                ((Long) obj).longValue();
                CodedOutputStream.m1091i();
                return 8;
            case 16:
                return CodedOutputStream.m1089h(CodedOutputStream.m1092i(((Integer) obj).intValue()));
            case 17:
                return CodedOutputStream.m1074c(CodedOutputStream.m1079d(((Long) obj).longValue()));
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }
}
