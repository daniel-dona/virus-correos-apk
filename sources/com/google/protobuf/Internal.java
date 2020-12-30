package com.google.protobuf;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: PG */
public abstract class Internal {

    /* renamed from: a */
    public static final Charset f1182a = Charset.forName("UTF-8");

    /* renamed from: b */
    public static final byte[] f1183b = new byte[0];

    /* compiled from: PG */
    public interface BooleanList extends ProtobufList<Boolean> {
        void addBoolean(boolean z);

        boolean getBoolean(int i);

        BooleanList mutableCopyWithCapacity(int i);

        boolean setBoolean(int i, boolean z);
    }

    /* compiled from: PG */
    public interface DoubleList extends ProtobufList<Double> {
        void addDouble(double d);

        double getDouble(int i);

        DoubleList mutableCopyWithCapacity(int i);

        double setDouble(int i, double d);
    }

    /* compiled from: PG */
    public interface EnumLite {
        int getNumber();
    }

    /* compiled from: PG */
    public interface EnumLiteMap<T extends EnumLite> {
        T findValueByNumber(int i);
    }

    /* compiled from: PG */
    public interface FloatList extends ProtobufList<Float> {
        void addFloat(float f);

        float getFloat(int i);

        FloatList mutableCopyWithCapacity(int i);

        float setFloat(int i, float f);
    }

    /* compiled from: PG */
    public interface IntList extends ProtobufList<Integer> {
        void addInt(int i);

        int getInt(int i);

        IntList mutableCopyWithCapacity(int i);

        int setInt(int i, int i2);
    }

    /* compiled from: PG */
    public static class ListAdapter<F, T> extends AbstractList<T> {

        /* renamed from: a */
        public final List<F> f1184a;

        /* renamed from: b */
        public final Converter<F, T> f1185b;

        /* compiled from: PG */
        public interface Converter<F, T> {
            T convert(F f);
        }

        public ListAdapter(List<F> list, Converter<F, T> converter) {
            this.f1184a = list;
            this.f1185b = converter;
        }

        public T get(int i) {
            return this.f1185b.convert(this.f1184a.get(i));
        }

        public int size() {
            return this.f1184a.size();
        }
    }

    /* compiled from: PG */
    public interface LongList extends ProtobufList<Long> {
        void addLong(long j);

        long getLong(int i);

        LongList mutableCopyWithCapacity(int i);

        long setLong(int i, long j);
    }

    /* compiled from: PG */
    public interface ProtobufList<E> extends List<E>, RandomAccess {
        boolean isModifiable();

        void makeImmutable();

        ProtobufList<E> mutableCopyWithCapacity(int i);
    }

    static {
        Charset.forName("ISO-8859-1");
        ByteBuffer.wrap(f1183b);
        LN.a(f1183b);
    }

    /* renamed from: a */
    public static int m1178a(long j) {
        return (int) (j ^ (j >>> 32));
    }

    /* renamed from: a */
    public static int m1179a(boolean z) {
        return z ? 1231 : 1237;
    }

    /* renamed from: a */
    public static int m1180a(byte[] bArr) {
        int length = bArr.length;
        int a = m1177a(length, bArr, 0, length);
        if (a == 0) {
            return 1;
        }
        return a;
    }

    /* renamed from: a */
    public static int m1177a(int i, byte[] bArr, int i2, int i3) {
        int i4 = i;
        for (int i5 = i2; i5 < i2 + i3; i5++) {
            i4 = (i4 * 31) + bArr[i5];
        }
        return i4;
    }
}
