package com.google.protobuf;

import com.citrix.loggersdk.BuildConfig;
import com.facebook.react.uimanager.BaseViewManager;
import java.io.IOException;

/* compiled from: PG */
public abstract class WireFormat {

    /* compiled from: PG */
    public enum FieldType {
        DOUBLE(JavaType.DOUBLE, 1),
        FLOAT(JavaType.FLOAT, 5),
        INT64(JavaType.LONG, 0),
        UINT64(JavaType.LONG, 0),
        INT32(JavaType.INT, 0),
        FIXED64(JavaType.LONG, 1),
        FIXED32(JavaType.INT, 5),
        BOOL(JavaType.BOOLEAN, 0),
        STRING(JavaType.STRING, 2) {
            public boolean isPackable() {
                return false;
            }
        },
        GROUP(JavaType.MESSAGE, 3) {
            public boolean isPackable() {
                return false;
            }
        },
        MESSAGE(JavaType.MESSAGE, 2) {
            public boolean isPackable() {
                return false;
            }
        },
        BYTES(JavaType.BYTE_STRING, 2) {
            public boolean isPackable() {
                return false;
            }
        },
        UINT32(JavaType.INT, 0),
        ENUM(JavaType.ENUM, 0),
        SFIXED32(JavaType.INT, 5),
        SFIXED64(JavaType.LONG, 1),
        SINT32(JavaType.INT, 0),
        SINT64(JavaType.LONG, 0);
        
        public final JavaType javaType;
        public final int wireType;

        public JavaType getJavaType() {
            return this.javaType;
        }

        public int getWireType() {
            return this.wireType;
        }

        public boolean isPackable() {
            return true;
        }

        /* access modifiers changed from: public */
        FieldType(JavaType javaType2, int i) {
            this.javaType = javaType2;
            this.wireType = i;
        }
    }

    /* compiled from: PG */
    public enum JavaType {
        INT(0),
        LONG(0L),
        FLOAT(Float.valueOf(BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER)),
        DOUBLE(Double.valueOf(0.0d)),
        BOOLEAN(false),
        STRING(BuildConfig.FLAVOR),
        BYTE_STRING(ByteString.EMPTY),
        ENUM((String) null),
        MESSAGE((String) null);
        
        public final Object defaultDefault;

        /* access modifiers changed from: public */
        JavaType(Object obj) {
            this.defaultDefault = obj;
        }

        public Object getDefaultDefault() {
            return this.defaultDefault;
        }
    }

    /* compiled from: PG */
    public enum Utf8Validation {
        LOOSE {
            public Object readString(LN ln) throws IOException {
                return ln.m();
            }
        },
        STRICT {
            public Object readString(LN ln) throws IOException {
                byte[] bArr;
                int j = ln.j();
                int i = ln.e;
                int i2 = 0;
                if (j <= ln.c - i && j > 0) {
                    bArr = ln.a;
                    ln.e = i + j;
                    i2 = i;
                } else if (j == 0) {
                    return BuildConfig.FLAVOR;
                } else {
                    if (j <= ln.c) {
                        ln.d(j);
                        bArr = ln.a;
                        ln.e = j + 0;
                    } else {
                        bArr = ln.c(j);
                    }
                }
                if (Utf8.m1198b(bArr, i2, i2 + j)) {
                    return new String(bArr, i2, j, Internal.f1182a);
                }
                throw InvalidProtocolBufferException.invalidUtf8();
            }
        },
        LAZY {
            public Object readString(LN ln) throws IOException {
                return ln.c();
            }
        };

        public abstract Object readString(LN ln) throws IOException;
    }

    /* renamed from: com.google.protobuf.WireFormat$a */
    /* compiled from: PG */
    public static /* synthetic */ class C0297a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f1199a = new int[FieldType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(36:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|(2:33|34)|35|(2:37|38)|39|(2:41|42)|43|(2:45|46)|47|(2:49|50)|51|53|54|55|56|57|58|59|60|(3:61|62|64)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(39:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|29|30|31|(2:33|34)|35|(2:37|38)|39|41|42|43|(2:45|46)|47|(2:49|50)|51|53|54|55|56|57|58|59|60|(3:61|62|64)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(49:0|1|2|3|5|6|7|9|10|11|(2:13|14)|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|49|50|51|53|54|55|56|57|58|59|60|61|62|64) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x0079 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x007f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x0085 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:61:0x008b */
        static {
            /*
                com.google.protobuf.WireFormat$FieldType[] r0 = com.google.protobuf.WireFormat.FieldType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f1199a = r0
                r0 = 1
                int[] r1 = f1199a     // Catch:{ NoSuchFieldError -> 0x0011 }
                com.google.protobuf.WireFormat$FieldType r2 = com.google.protobuf.WireFormat.FieldType.DOUBLE     // Catch:{ NoSuchFieldError -> 0x0011 }
                r2 = 0
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0011 }
            L_0x0011:
                r1 = 2
                int[] r2 = f1199a     // Catch:{ NoSuchFieldError -> 0x0018 }
                com.google.protobuf.WireFormat$FieldType r3 = com.google.protobuf.WireFormat.FieldType.FLOAT     // Catch:{ NoSuchFieldError -> 0x0018 }
                r2[r0] = r1     // Catch:{ NoSuchFieldError -> 0x0018 }
            L_0x0018:
                r0 = 3
                int[] r2 = f1199a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.WireFormat$FieldType r3 = com.google.protobuf.WireFormat.FieldType.INT64     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r1 = 4
                int[] r2 = f1199a     // Catch:{ NoSuchFieldError -> 0x0026 }
                com.google.protobuf.WireFormat$FieldType r3 = com.google.protobuf.WireFormat.FieldType.UINT64     // Catch:{ NoSuchFieldError -> 0x0026 }
                r2[r0] = r1     // Catch:{ NoSuchFieldError -> 0x0026 }
            L_0x0026:
                r0 = 5
                int[] r2 = f1199a     // Catch:{ NoSuchFieldError -> 0x002d }
                com.google.protobuf.WireFormat$FieldType r3 = com.google.protobuf.WireFormat.FieldType.INT32     // Catch:{ NoSuchFieldError -> 0x002d }
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                r1 = 6
                int[] r2 = f1199a     // Catch:{ NoSuchFieldError -> 0x0034 }
                com.google.protobuf.WireFormat$FieldType r3 = com.google.protobuf.WireFormat.FieldType.FIXED64     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2[r0] = r1     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                r0 = 7
                int[] r2 = f1199a     // Catch:{ NoSuchFieldError -> 0x003b }
                com.google.protobuf.WireFormat$FieldType r3 = com.google.protobuf.WireFormat.FieldType.FIXED32     // Catch:{ NoSuchFieldError -> 0x003b }
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x003b }
            L_0x003b:
                r1 = 8
                int[] r2 = f1199a     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.google.protobuf.WireFormat$FieldType r3 = com.google.protobuf.WireFormat.FieldType.BOOL     // Catch:{ NoSuchFieldError -> 0x0043 }
                r2[r0] = r1     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                r0 = 9
                r2 = 11
                int[] r3 = f1199a     // Catch:{ NoSuchFieldError -> 0x004d }
                com.google.protobuf.WireFormat$FieldType r4 = com.google.protobuf.WireFormat.FieldType.BYTES     // Catch:{ NoSuchFieldError -> 0x004d }
                r3[r2] = r0     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                r3 = 10
                r4 = 12
                int[] r5 = f1199a     // Catch:{ NoSuchFieldError -> 0x0057 }
                com.google.protobuf.WireFormat$FieldType r6 = com.google.protobuf.WireFormat.FieldType.UINT32     // Catch:{ NoSuchFieldError -> 0x0057 }
                r5[r4] = r3     // Catch:{ NoSuchFieldError -> 0x0057 }
            L_0x0057:
                r5 = 14
                int[] r6 = f1199a     // Catch:{ NoSuchFieldError -> 0x005f }
                com.google.protobuf.WireFormat$FieldType r7 = com.google.protobuf.WireFormat.FieldType.SFIXED32     // Catch:{ NoSuchFieldError -> 0x005f }
                r6[r5] = r2     // Catch:{ NoSuchFieldError -> 0x005f }
            L_0x005f:
                r2 = 15
                int[] r6 = f1199a     // Catch:{ NoSuchFieldError -> 0x0067 }
                com.google.protobuf.WireFormat$FieldType r7 = com.google.protobuf.WireFormat.FieldType.SFIXED64     // Catch:{ NoSuchFieldError -> 0x0067 }
                r6[r2] = r4     // Catch:{ NoSuchFieldError -> 0x0067 }
            L_0x0067:
                r4 = 13
                r6 = 16
                int[] r7 = f1199a     // Catch:{ NoSuchFieldError -> 0x0071 }
                com.google.protobuf.WireFormat$FieldType r8 = com.google.protobuf.WireFormat.FieldType.SINT32     // Catch:{ NoSuchFieldError -> 0x0071 }
                r7[r6] = r4     // Catch:{ NoSuchFieldError -> 0x0071 }
            L_0x0071:
                r7 = 17
                int[] r8 = f1199a     // Catch:{ NoSuchFieldError -> 0x0079 }
                com.google.protobuf.WireFormat$FieldType r9 = com.google.protobuf.WireFormat.FieldType.SINT64     // Catch:{ NoSuchFieldError -> 0x0079 }
                r8[r7] = r5     // Catch:{ NoSuchFieldError -> 0x0079 }
            L_0x0079:
                int[] r5 = f1199a     // Catch:{ NoSuchFieldError -> 0x007f }
                com.google.protobuf.WireFormat$FieldType r8 = com.google.protobuf.WireFormat.FieldType.STRING     // Catch:{ NoSuchFieldError -> 0x007f }
                r5[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007f }
            L_0x007f:
                int[] r1 = f1199a     // Catch:{ NoSuchFieldError -> 0x0085 }
                com.google.protobuf.WireFormat$FieldType r2 = com.google.protobuf.WireFormat.FieldType.GROUP     // Catch:{ NoSuchFieldError -> 0x0085 }
                r1[r0] = r6     // Catch:{ NoSuchFieldError -> 0x0085 }
            L_0x0085:
                int[] r0 = f1199a     // Catch:{ NoSuchFieldError -> 0x008b }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.MESSAGE     // Catch:{ NoSuchFieldError -> 0x008b }
                r0[r3] = r7     // Catch:{ NoSuchFieldError -> 0x008b }
            L_0x008b:
                int[] r0 = f1199a     // Catch:{ NoSuchFieldError -> 0x0093 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.ENUM     // Catch:{ NoSuchFieldError -> 0x0093 }
                r1 = 18
                r0[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0093 }
            L_0x0093:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.WireFormat.C0297a.<clinit>():void");
        }
    }

    /* renamed from: a */
    public static Object m1199a(LN ln, FieldType fieldType, Utf8Validation utf8Validation) throws IOException {
        switch (fieldType.ordinal()) {
            case 0:
                return Double.valueOf(ln.d());
            case 1:
                return Float.valueOf(ln.g());
            case 2:
                return Long.valueOf(ln.k());
            case 3:
                return Long.valueOf(ln.k());
            case 4:
                return Integer.valueOf(ln.j());
            case 5:
                return Long.valueOf(ln.i());
            case 6:
                return Integer.valueOf(ln.h());
            case 7:
                return Boolean.valueOf(ln.b());
            case 8:
                return utf8Validation.readString(ln);
            case 9:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
            case 10:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
            case 11:
                return ln.c();
            case 12:
                return Integer.valueOf(ln.j());
            case 13:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
            case 14:
                return Integer.valueOf(ln.h());
            case 15:
                return Long.valueOf(ln.i());
            case 16:
                int j = ln.j();
                return Integer.valueOf((-(j & 1)) ^ (j >>> 1));
            case 17:
                long k = ln.k();
                return Long.valueOf((-(k & 1)) ^ (k >>> 1));
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }
}
