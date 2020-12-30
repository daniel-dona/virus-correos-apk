package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* compiled from: PG */
public abstract class TypeAdapters {

    /* renamed from: A */
    public static final TypeAdapter<String> f1040A = new TypeAdapter<String>() {
        /* renamed from: a */
        public void write(WK wk, String str) throws IOException {
            wk.B(str);
        }

        public String read(VK vk) throws IOException {
            JsonToken j0 = vk.j0();
            if (j0 == JsonToken.NULL) {
                vk.Y();
                return null;
            } else if (j0 == JsonToken.BOOLEAN) {
                return Boolean.toString(vk.H());
            } else {
                return vk.a0();
            }
        }
    };

    /* renamed from: B */
    public static final TypeAdapter<BigDecimal> f1041B = new TypeAdapter<BigDecimal>() {
        /* renamed from: a */
        public void write(WK wk, BigDecimal bigDecimal) throws IOException {
            wk.a(bigDecimal);
        }

        public BigDecimal read(VK vk) throws IOException {
            if (vk.j0() == JsonToken.NULL) {
                vk.Y();
                return null;
            }
            try {
                return new BigDecimal(vk.a0());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException((Throwable) e);
            }
        }
    };

    /* renamed from: C */
    public static final TypeAdapter<BigInteger> f1042C = new TypeAdapter<BigInteger>() {
        /* renamed from: a */
        public void write(WK wk, BigInteger bigInteger) throws IOException {
            wk.a(bigInteger);
        }

        public BigInteger read(VK vk) throws IOException {
            if (vk.j0() == JsonToken.NULL) {
                vk.Y();
                return null;
            }
            try {
                return new BigInteger(vk.a0());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException((Throwable) e);
            }
        }
    };

    /* renamed from: D */
    public static final YJ f1043D;

    /* renamed from: E */
    public static final TypeAdapter<StringBuilder> f1044E = new TypeAdapter<StringBuilder>() {
        /* renamed from: a */
        public void write(WK wk, StringBuilder sb) throws IOException {
            wk.B(sb == null ? null : sb.toString());
        }

        public StringBuilder read(VK vk) throws IOException {
            if (vk.j0() != JsonToken.NULL) {
                return new StringBuilder(vk.a0());
            }
            vk.Y();
            return null;
        }
    };

    /* renamed from: F */
    public static final YJ f1045F;

    /* renamed from: G */
    public static final TypeAdapter<StringBuffer> f1046G = new TypeAdapter<StringBuffer>() {
        /* renamed from: a */
        public void write(WK wk, StringBuffer stringBuffer) throws IOException {
            wk.B(stringBuffer == null ? null : stringBuffer.toString());
        }

        public StringBuffer read(VK vk) throws IOException {
            if (vk.j0() != JsonToken.NULL) {
                return new StringBuffer(vk.a0());
            }
            vk.Y();
            return null;
        }
    };

    /* renamed from: H */
    public static final YJ f1047H;

    /* renamed from: I */
    public static final TypeAdapter<URL> f1048I = new TypeAdapter<URL>() {
        /* renamed from: a */
        public void write(WK wk, URL url) throws IOException {
            wk.B(url == null ? null : url.toExternalForm());
        }

        public URL read(VK vk) throws IOException {
            if (vk.j0() == JsonToken.NULL) {
                vk.Y();
                return null;
            }
            String a0 = vk.a0();
            if ("null".equals(a0)) {
                return null;
            }
            return new URL(a0);
        }
    };

    /* renamed from: J */
    public static final YJ f1049J;

    /* renamed from: K */
    public static final TypeAdapter<URI> f1050K = new TypeAdapter<URI>() {
        /* renamed from: a */
        public void write(WK wk, URI uri) throws IOException {
            wk.B(uri == null ? null : uri.toASCIIString());
        }

        public URI read(VK vk) throws IOException {
            if (vk.j0() == JsonToken.NULL) {
                vk.Y();
                return null;
            }
            try {
                String a0 = vk.a0();
                if ("null".equals(a0)) {
                    return null;
                }
                return new URI(a0);
            } catch (URISyntaxException e) {
                throw new JsonIOException((Throwable) e);
            }
        }
    };

    /* renamed from: L */
    public static final YJ f1051L;

    /* renamed from: M */
    public static final TypeAdapter<InetAddress> f1052M = new TypeAdapter<InetAddress>() {
        /* renamed from: a */
        public void write(WK wk, InetAddress inetAddress) throws IOException {
            wk.B(inetAddress == null ? null : inetAddress.getHostAddress());
        }

        public InetAddress read(VK vk) throws IOException {
            if (vk.j0() != JsonToken.NULL) {
                return InetAddress.getByName(vk.a0());
            }
            vk.Y();
            return null;
        }
    };

    /* renamed from: N */
    public static final YJ f1053N;

    /* renamed from: O */
    public static final TypeAdapter<UUID> f1054O = new TypeAdapter<UUID>() {
        /* renamed from: a */
        public void write(WK wk, UUID uuid) throws IOException {
            wk.B(uuid == null ? null : uuid.toString());
        }

        public UUID read(VK vk) throws IOException {
            if (vk.j0() != JsonToken.NULL) {
                return UUID.fromString(vk.a0());
            }
            vk.Y();
            return null;
        }
    };

    /* renamed from: P */
    public static final YJ f1055P;

    /* renamed from: Q */
    public static final TypeAdapter<Currency> f1056Q = new TypeAdapter<Currency>() {
        /* renamed from: a */
        public void write(WK wk, Currency currency) throws IOException {
            wk.B(currency.getCurrencyCode());
        }

        public Currency read(VK vk) throws IOException {
            return Currency.getInstance(vk.a0());
        }
    }.nullSafe();

    /* renamed from: R */
    public static final YJ f1057R;

    /* renamed from: S */
    public static final YJ f1058S = new YJ() {
        /* renamed from: a */
        public <T> TypeAdapter<T> mo2260a(Gson gson, TK<T> tk) {
            if (tk.getRawType() != Timestamp.class) {
                return null;
            }
            final TypeAdapter<Date> a = gson.mo2093a(Date.class);
            return new TypeAdapter<Timestamp>(this) {
                /* renamed from: a */
                public void write(WK wk, Timestamp timestamp) throws IOException {
                    a.write(wk, timestamp);
                }

                public Timestamp read(VK vk) throws IOException {
                    Date date = (Date) a.read(vk);
                    if (date != null) {
                        return new Timestamp(date.getTime());
                    }
                    return null;
                }
            };
        }
    };

    /* renamed from: T */
    public static final TypeAdapter<Calendar> f1059T = new TypeAdapter<Calendar>() {
        /* renamed from: a */
        public void write(WK wk, Calendar calendar) throws IOException {
            if (calendar == null) {
                wk.E();
                return;
            }
            wk.c();
            wk.z("year");
            wk.r((long) calendar.get(1));
            wk.z("month");
            wk.r((long) calendar.get(2));
            wk.z("dayOfMonth");
            wk.r((long) calendar.get(5));
            wk.z("hourOfDay");
            wk.r((long) calendar.get(11));
            wk.z("minute");
            wk.r((long) calendar.get(12));
            wk.z("second");
            wk.r((long) calendar.get(13));
            wk.e();
        }

        public Calendar read(VK vk) throws IOException {
            if (vk.j0() == JsonToken.NULL) {
                vk.Y();
                return null;
            }
            vk.b();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (vk.j0() != JsonToken.END_OBJECT) {
                String M = vk.M();
                int J = vk.J();
                if ("year".equals(M)) {
                    i = J;
                } else if ("month".equals(M)) {
                    i2 = J;
                } else if ("dayOfMonth".equals(M)) {
                    i3 = J;
                } else if ("hourOfDay".equals(M)) {
                    i4 = J;
                } else if ("minute".equals(M)) {
                    i5 = J;
                } else if ("second".equals(M)) {
                    i6 = J;
                }
            }
            vk.f();
            return new GregorianCalendar(i, i2, i3, i4, i5, i6);
        }
    };

    /* renamed from: U */
    public static final YJ f1060U;

    /* renamed from: V */
    public static final TypeAdapter<Locale> f1061V = new TypeAdapter<Locale>() {
        /* renamed from: a */
        public void write(WK wk, Locale locale) throws IOException {
            wk.B(locale == null ? null : locale.toString());
        }

        public Locale read(VK vk) throws IOException {
            String str = null;
            if (vk.j0() == JsonToken.NULL) {
                vk.Y();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(vk.a0(), "_");
            String nextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String nextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            if (stringTokenizer.hasMoreElements()) {
                str = stringTokenizer.nextToken();
            }
            if (nextToken2 == null && str == null) {
                return new Locale(nextToken);
            }
            if (str == null) {
                return new Locale(nextToken, nextToken2);
            }
            return new Locale(nextToken, nextToken2, str);
        }
    };

    /* renamed from: W */
    public static final YJ f1062W;

    /* renamed from: X */
    public static final TypeAdapter<RJ> f1063X = new TypeAdapter<RJ>() {
        /* renamed from: a */
        public void write(WK wk, RJ rj) throws IOException {
            if (rj == null || (rj instanceof SJ)) {
                wk.E();
            } else if (rj instanceof VJ) {
                VJ c = rj.c();
                Object obj = c.a;
                if (obj instanceof Number) {
                    wk.a(c.i());
                } else if (obj instanceof Boolean) {
                    wk.a(c.h());
                } else {
                    wk.B(c.d());
                }
            } else {
                boolean z = rj instanceof OJ;
                if (z) {
                    wk.b();
                    if (z) {
                        Iterator it = ((OJ) rj).iterator();
                        while (it.hasNext()) {
                            write(wk, (RJ) it.next());
                        }
                        wk.d();
                        return;
                    }
                    throw new IllegalStateException("Not a JSON Array: " + rj);
                } else if (rj instanceof TJ) {
                    wk.c();
                    for (Map.Entry entry : rj.b().h()) {
                        wk.z((String) entry.getKey());
                        write(wk, (RJ) entry.getValue());
                    }
                    wk.e();
                } else {
                    StringBuilder a = Eo.a("Couldn't write ");
                    a.append(rj.getClass());
                    throw new IllegalArgumentException(a.toString());
                }
            }
        }

        public RJ read(VK vk) throws IOException {
            int ordinal = vk.j0().ordinal();
            if (ordinal == 0) {
                OJ oj = new OJ();
                vk.a();
                while (vk.F()) {
                    oj.a(read(vk));
                }
                vk.e();
                return oj;
            } else if (ordinal == 2) {
                TJ tj = new TJ();
                vk.b();
                while (vk.F()) {
                    tj.a(vk.M(), read(vk));
                }
                vk.f();
                return tj;
            } else if (ordinal == 5) {
                return new VJ(vk.a0());
            } else {
                if (ordinal == 6) {
                    return new VJ(new LazilyParsedNumber(vk.a0()));
                }
                if (ordinal == 7) {
                    return new VJ(Boolean.valueOf(vk.H()));
                }
                if (ordinal == 8) {
                    vk.Y();
                    return SJ.a;
                }
                throw new IllegalArgumentException();
            }
        }
    };

    /* renamed from: Y */
    public static final YJ f1064Y;

    /* renamed from: Z */
    public static final YJ f1065Z = new YJ() {
        /* renamed from: a */
        public <T> TypeAdapter<T> mo2266a(Gson gson, TK<T> tk) {
            Class<? super Enum> rawType = tk.getRawType();
            if (!Enum.class.isAssignableFrom(rawType) || rawType == Enum.class) {
                return null;
            }
            if (!rawType.isEnum()) {
                rawType = rawType.getSuperclass();
            }
            return new EnumTypeAdapter(rawType);
        }
    };

    /* renamed from: a */
    public static final TypeAdapter<Class> f1066a = new TypeAdapter<Class>() {
        /* renamed from: a */
        public void mo2242a(Class cls) throws IOException {
            StringBuilder a = Eo.a("Attempted to serialize java.lang.Class: ");
            a.append(cls.getName());
            a.append(". Forgot to register a type adapter?");
            throw new UnsupportedOperationException(a.toString());
        }

        public /* bridge */ /* synthetic */ Object read(VK vk) throws IOException {
            return mo2241a();
        }

        public /* bridge */ /* synthetic */ void write(WK wk, Object obj) throws IOException {
            mo2242a((Class) obj);
        }

        /* renamed from: a */
        public Class mo2241a() throws IOException {
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    }.nullSafe();

    /* renamed from: b */
    public static final YJ f1067b;

    /* renamed from: c */
    public static final TypeAdapter<BitSet> f1068c = new TypeAdapter<BitSet>() {
        /* renamed from: a */
        public void write(WK wk, BitSet bitSet) throws IOException {
            wk.b();
            int length = bitSet.length();
            for (int i = 0; i < length; i++) {
                wk.r(bitSet.get(i) ? 1 : 0);
            }
            wk.d();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x003e, code lost:
            if (r6.J() != 0) goto L_0x004b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0049, code lost:
            if (java.lang.Integer.parseInt(r1) != 0) goto L_0x004b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x004d, code lost:
            r1 = false;
         */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x0050  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x0053 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.BitSet read(VK r6) throws java.io.IOException {
            /*
                r5 = this;
                java.util.BitSet r0 = new java.util.BitSet
                r0.<init>()
                r6.a()
                com.google.gson.stream.JsonToken r1 = r6.j0()
                r2 = 0
            L_0x000d:
                com.google.gson.stream.JsonToken r3 = com.google.gson.stream.JsonToken.END_ARRAY
                if (r1 == r3) goto L_0x0066
                int r3 = r1.ordinal()
                r4 = 5
                if (r3 == r4) goto L_0x0041
                r4 = 6
                if (r3 == r4) goto L_0x003a
                r4 = 7
                if (r3 != r4) goto L_0x0023
                boolean r1 = r6.H()
                goto L_0x004e
            L_0x0023:
                com.google.gson.JsonSyntaxException r6 = new com.google.gson.JsonSyntaxException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r2 = "Invalid bitset value type: "
                r0.append(r2)
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r6.<init>((java.lang.String) r0)
                throw r6
            L_0x003a:
                int r1 = r6.J()
                if (r1 == 0) goto L_0x004d
                goto L_0x004b
            L_0x0041:
                java.lang.String r1 = r6.a0()
                int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x005a }
                if (r1 == 0) goto L_0x004d
            L_0x004b:
                r1 = 1
                goto L_0x004e
            L_0x004d:
                r1 = 0
            L_0x004e:
                if (r1 == 0) goto L_0x0053
                r0.set(r2)
            L_0x0053:
                int r2 = r2 + 1
                com.google.gson.stream.JsonToken r1 = r6.j0()
                goto L_0x000d
            L_0x005a:
                com.google.gson.JsonSyntaxException r6 = new com.google.gson.JsonSyntaxException
                java.lang.String r0 = "Error: Expecting: bitset number value (1, 0), Found: "
                java.lang.String r0 = Eo.a(r0, r1)
                r6.<init>((java.lang.String) r0)
                throw r6
            L_0x0066:
                r6.e()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.TypeAdapters.C02482.read(VK):java.util.BitSet");
        }
    }.nullSafe();

    /* renamed from: d */
    public static final YJ f1069d;

    /* renamed from: e */
    public static final TypeAdapter<Boolean> f1070e = new TypeAdapter<Boolean>() {
        /* renamed from: a */
        public void write(WK wk, Boolean bool) throws IOException {
            wk.a(bool);
        }

        public Boolean read(VK vk) throws IOException {
            JsonToken j0 = vk.j0();
            if (j0 == JsonToken.NULL) {
                vk.Y();
                return null;
            } else if (j0 == JsonToken.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(vk.a0()));
            } else {
                return Boolean.valueOf(vk.H());
            }
        }
    };

    /* renamed from: f */
    public static final TypeAdapter<Boolean> f1071f = new TypeAdapter<Boolean>() {
        /* renamed from: a */
        public void write(WK wk, Boolean bool) throws IOException {
            wk.B(bool == null ? "null" : bool.toString());
        }

        public Boolean read(VK vk) throws IOException {
            if (vk.j0() != JsonToken.NULL) {
                return Boolean.valueOf(vk.a0());
            }
            vk.Y();
            return null;
        }
    };

    /* renamed from: g */
    public static final YJ f1072g;

    /* renamed from: h */
    public static final TypeAdapter<Number> f1073h = new TypeAdapter<Number>() {
        /* renamed from: a */
        public void write(WK wk, Number number) throws IOException {
            wk.a(number);
        }

        public Number read(VK vk) throws IOException {
            if (vk.j0() == JsonToken.NULL) {
                vk.Y();
                return null;
            }
            try {
                return Byte.valueOf((byte) vk.J());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException((Throwable) e);
            }
        }
    };

    /* renamed from: i */
    public static final YJ f1074i;

    /* renamed from: j */
    public static final TypeAdapter<Number> f1075j = new TypeAdapter<Number>() {
        /* renamed from: a */
        public void write(WK wk, Number number) throws IOException {
            wk.a(number);
        }

        public Number read(VK vk) throws IOException {
            if (vk.j0() == JsonToken.NULL) {
                vk.Y();
                return null;
            }
            try {
                return Short.valueOf((short) vk.J());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException((Throwable) e);
            }
        }
    };

    /* renamed from: k */
    public static final YJ f1076k;

    /* renamed from: l */
    public static final TypeAdapter<Number> f1077l = new TypeAdapter<Number>() {
        /* renamed from: a */
        public void write(WK wk, Number number) throws IOException {
            wk.a(number);
        }

        public Number read(VK vk) throws IOException {
            if (vk.j0() == JsonToken.NULL) {
                vk.Y();
                return null;
            }
            try {
                return Integer.valueOf(vk.J());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException((Throwable) e);
            }
        }
    };

    /* renamed from: m */
    public static final YJ f1078m;

    /* renamed from: n */
    public static final TypeAdapter<AtomicInteger> f1079n = new TypeAdapter<AtomicInteger>() {
        /* renamed from: a */
        public void write(WK wk, AtomicInteger atomicInteger) throws IOException {
            wk.r((long) atomicInteger.get());
        }

        public AtomicInteger read(VK vk) throws IOException {
            try {
                return new AtomicInteger(vk.J());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException((Throwable) e);
            }
        }
    }.nullSafe();

    /* renamed from: o */
    public static final YJ f1080o;

    /* renamed from: p */
    public static final TypeAdapter<AtomicBoolean> f1081p = new TypeAdapter<AtomicBoolean>() {
        /* renamed from: a */
        public void write(WK wk, AtomicBoolean atomicBoolean) throws IOException {
            wk.a(atomicBoolean.get());
        }

        public AtomicBoolean read(VK vk) throws IOException {
            return new AtomicBoolean(vk.H());
        }
    }.nullSafe();

    /* renamed from: q */
    public static final YJ f1082q;

    /* renamed from: r */
    public static final TypeAdapter<AtomicIntegerArray> f1083r = new TypeAdapter<AtomicIntegerArray>() {
        /* renamed from: a */
        public void write(WK wk, AtomicIntegerArray atomicIntegerArray) throws IOException {
            wk.b();
            int length = atomicIntegerArray.length();
            for (int i = 0; i < length; i++) {
                wk.r((long) atomicIntegerArray.get(i));
            }
            wk.d();
        }

        public AtomicIntegerArray read(VK vk) throws IOException {
            ArrayList arrayList = new ArrayList();
            vk.a();
            while (vk.F()) {
                try {
                    arrayList.add(Integer.valueOf(vk.J()));
                } catch (NumberFormatException e) {
                    throw new JsonSyntaxException((Throwable) e);
                }
            }
            vk.e();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i = 0; i < size; i++) {
                atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
            }
            return atomicIntegerArray;
        }
    }.nullSafe();

    /* renamed from: s */
    public static final YJ f1084s;

    /* renamed from: t */
    public static final TypeAdapter<Number> f1085t = new TypeAdapter<Number>() {
        /* renamed from: a */
        public void write(WK wk, Number number) throws IOException {
            wk.a(number);
        }

        public Number read(VK vk) throws IOException {
            if (vk.j0() == JsonToken.NULL) {
                vk.Y();
                return null;
            }
            try {
                return Long.valueOf(vk.K());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException((Throwable) e);
            }
        }
    };

    /* renamed from: u */
    public static final TypeAdapter<Number> f1086u = new TypeAdapter<Number>() {
        /* renamed from: a */
        public void write(WK wk, Number number) throws IOException {
            wk.a(number);
        }

        public Number read(VK vk) throws IOException {
            if (vk.j0() != JsonToken.NULL) {
                return Float.valueOf((float) vk.I());
            }
            vk.Y();
            return null;
        }
    };

    /* renamed from: v */
    public static final TypeAdapter<Number> f1087v = new TypeAdapter<Number>() {
        /* renamed from: a */
        public void write(WK wk, Number number) throws IOException {
            wk.a(number);
        }

        public Number read(VK vk) throws IOException {
            if (vk.j0() != JsonToken.NULL) {
                return Double.valueOf(vk.I());
            }
            vk.Y();
            return null;
        }
    };

    /* renamed from: w */
    public static final TypeAdapter<Number> f1088w = new TypeAdapter<Number>() {
        /* renamed from: a */
        public void write(WK wk, Number number) throws IOException {
            wk.a(number);
        }

        public Number read(VK vk) throws IOException {
            JsonToken j0 = vk.j0();
            int ordinal = j0.ordinal();
            if (ordinal == 5 || ordinal == 6) {
                return new LazilyParsedNumber(vk.a0());
            }
            if (ordinal == 8) {
                vk.Y();
                return null;
            }
            throw new JsonSyntaxException("Expecting number, got: " + j0);
        }
    };

    /* renamed from: x */
    public static final YJ f1089x;

    /* renamed from: y */
    public static final TypeAdapter<Character> f1090y = new TypeAdapter<Character>() {
        /* renamed from: a */
        public void write(WK wk, Character ch) throws IOException {
            wk.B(ch == null ? null : String.valueOf(ch));
        }

        public Character read(VK vk) throws IOException {
            if (vk.j0() == JsonToken.NULL) {
                vk.Y();
                return null;
            }
            String a0 = vk.a0();
            if (a0.length() == 1) {
                return Character.valueOf(a0.charAt(0));
            }
            throw new JsonSyntaxException(Eo.a("Expecting character, got: ", a0));
        }
    };

    /* renamed from: z */
    public static final YJ f1091z;

    /* compiled from: PG */
    public static final class EnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T> {

        /* renamed from: a */
        public final Map<String, T> f1107a = new HashMap();

        /* renamed from: b */
        public final Map<T, String> f1108b = new HashMap();

        public EnumTypeAdapter(Class<T> cls) {
            try {
                for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
                    String name = enumR.name();
                    bK annotation = cls.getField(name).getAnnotation(bK.class);
                    if (annotation != null) {
                        name = annotation.value();
                        for (String put : annotation.alternate()) {
                            this.f1107a.put(put, enumR);
                        }
                    }
                    this.f1107a.put(name, enumR);
                    this.f1108b.put(enumR, name);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError(e);
            }
        }

        /* renamed from: a */
        public void write(WK wk, T t) throws IOException {
            wk.B(t == null ? null : this.f1108b.get(t));
        }

        public T read(VK vk) throws IOException {
            if (vk.j0() != JsonToken.NULL) {
                return (Enum) this.f1107a.get(vk.a0());
            }
            vk.Y();
            return null;
        }
    }

    static {
        final Class<Class> cls = Class.class;
        final TypeAdapter<Class> typeAdapter = f1066a;
        f1067b = new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2268a(Gson gson, TK<T> tk) {
                if (tk.getRawType() == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        };
        final Class<BitSet> cls2 = BitSet.class;
        final TypeAdapter<BitSet> typeAdapter2 = f1068c;
        f1069d = new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2268a(Gson gson, TK<T> tk) {
                if (tk.getRawType() == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        };
        final Class cls3 = Boolean.TYPE;
        final Class<Boolean> cls4 = Boolean.class;
        final TypeAdapter<Boolean> typeAdapter3 = f1070e;
        f1072g = new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2270a(Gson gson, TK<T> tk) {
                Class rawType = tk.getRawType();
                if (rawType == r0 || rawType == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append("+");
                a.append(r0.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        };
        final Class cls5 = Byte.TYPE;
        final Class<Byte> cls6 = Byte.class;
        final TypeAdapter<Number> typeAdapter4 = f1073h;
        f1074i = new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2270a(Gson gson, TK<T> tk) {
                Class rawType = tk.getRawType();
                if (rawType == r0 || rawType == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append("+");
                a.append(r0.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        };
        final Class cls7 = Short.TYPE;
        final Class<Short> cls8 = Short.class;
        final TypeAdapter<Number> typeAdapter5 = f1075j;
        f1076k = new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2270a(Gson gson, TK<T> tk) {
                Class rawType = tk.getRawType();
                if (rawType == r0 || rawType == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append("+");
                a.append(r0.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        };
        final Class cls9 = Integer.TYPE;
        final Class<Integer> cls10 = Integer.class;
        final TypeAdapter<Number> typeAdapter6 = f1077l;
        f1078m = new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2270a(Gson gson, TK<T> tk) {
                Class rawType = tk.getRawType();
                if (rawType == r0 || rawType == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append("+");
                a.append(r0.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        };
        final Class<AtomicInteger> cls11 = AtomicInteger.class;
        final TypeAdapter<AtomicInteger> typeAdapter7 = f1079n;
        f1080o = new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2268a(Gson gson, TK<T> tk) {
                if (tk.getRawType() == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        };
        final Class<AtomicBoolean> cls12 = AtomicBoolean.class;
        final TypeAdapter<AtomicBoolean> typeAdapter8 = f1081p;
        f1082q = new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2268a(Gson gson, TK<T> tk) {
                if (tk.getRawType() == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        };
        final Class<AtomicIntegerArray> cls13 = AtomicIntegerArray.class;
        final TypeAdapter<AtomicIntegerArray> typeAdapter9 = f1083r;
        f1084s = new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2268a(Gson gson, TK<T> tk) {
                if (tk.getRawType() == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        };
        final Class<Number> cls14 = Number.class;
        final TypeAdapter<Number> typeAdapter10 = f1088w;
        f1089x = new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2268a(Gson gson, TK<T> tk) {
                if (tk.getRawType() == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        };
        final Class cls15 = Character.TYPE;
        final Class<Character> cls16 = Character.class;
        final TypeAdapter<Character> typeAdapter11 = f1090y;
        f1091z = new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2270a(Gson gson, TK<T> tk) {
                Class rawType = tk.getRawType();
                if (rawType == cls15 || rawType == cls16) {
                    return typeAdapter11;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(cls16.getName());
                a.append("+");
                a.append(cls15.getName());
                a.append(",adapter=");
                a.append(typeAdapter11);
                a.append("]");
                return a.toString();
            }
        };
        final Class<String> cls17 = String.class;
        final TypeAdapter<String> typeAdapter12 = f1040A;
        f1043D = new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2268a(Gson gson, TK<T> tk) {
                if (tk.getRawType() == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        };
        final Class<StringBuilder> cls18 = StringBuilder.class;
        final TypeAdapter<StringBuilder> typeAdapter13 = f1044E;
        f1045F = new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2268a(Gson gson, TK<T> tk) {
                if (tk.getRawType() == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        };
        final Class<StringBuffer> cls19 = StringBuffer.class;
        final TypeAdapter<StringBuffer> typeAdapter14 = f1046G;
        f1047H = new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2268a(Gson gson, TK<T> tk) {
                if (tk.getRawType() == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        };
        final Class<URL> cls20 = URL.class;
        final TypeAdapter<URL> typeAdapter15 = f1048I;
        f1049J = new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2268a(Gson gson, TK<T> tk) {
                if (tk.getRawType() == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        };
        final Class<URI> cls21 = URI.class;
        final TypeAdapter<URI> typeAdapter16 = f1050K;
        f1051L = new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2268a(Gson gson, TK<T> tk) {
                if (tk.getRawType() == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        };
        final Class<InetAddress> cls22 = InetAddress.class;
        final TypeAdapter<InetAddress> typeAdapter17 = f1052M;
        f1053N = new YJ() {
            /* renamed from: a */
            public <T2> TypeAdapter<T2> mo2274a(Gson gson, TK<T2> tk) {
                final Class rawType = tk.getRawType();
                if (!r1.isAssignableFrom(rawType)) {
                    return null;
                }
                return new TypeAdapter<T1>() {
                    public T1 read(VK vk) throws IOException {
                        T1 read = r2.read(vk);
                        if (read == null || rawType.isInstance(read)) {
                            return read;
                        }
                        StringBuilder a = Eo.a("Expected a ");
                        a.append(rawType.getName());
                        a.append(" but was ");
                        a.append(read.getClass().getName());
                        throw new JsonSyntaxException(a.toString());
                    }

                    public void write(WK wk, T1 t1) throws IOException {
                        r2.write(wk, t1);
                    }
                };
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[typeHierarchy=");
                a.append(r1.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        };
        final Class<UUID> cls23 = UUID.class;
        final TypeAdapter<UUID> typeAdapter18 = f1054O;
        f1055P = new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2268a(Gson gson, TK<T> tk) {
                if (tk.getRawType() == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        };
        final Class<Currency> cls24 = Currency.class;
        final TypeAdapter<Currency> typeAdapter19 = f1056Q;
        f1057R = new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2268a(Gson gson, TK<T> tk) {
                if (tk.getRawType() == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        };
        final Class<Calendar> cls25 = Calendar.class;
        final Class<GregorianCalendar> cls26 = GregorianCalendar.class;
        final TypeAdapter<Calendar> typeAdapter20 = f1059T;
        f1060U = new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2272a(Gson gson, TK<T> tk) {
                Class rawType = tk.getRawType();
                if (rawType == cls25 || rawType == cls26) {
                    return typeAdapter20;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(cls25.getName());
                a.append("+");
                a.append(cls26.getName());
                a.append(",adapter=");
                a.append(typeAdapter20);
                a.append("]");
                return a.toString();
            }
        };
        final Class<Locale> cls27 = Locale.class;
        final TypeAdapter<Locale> typeAdapter21 = f1061V;
        f1062W = new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2268a(Gson gson, TK<T> tk) {
                if (tk.getRawType() == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        };
        final Class<RJ> cls28 = RJ.class;
        final TypeAdapter<RJ> typeAdapter22 = f1063X;
        f1064Y = new YJ() {
            /* renamed from: a */
            public <T2> TypeAdapter<T2> mo2274a(Gson gson, TK<T2> tk) {
                final Class rawType = tk.getRawType();
                if (!r1.isAssignableFrom(rawType)) {
                    return null;
                }
                return new TypeAdapter<T1>() {
                    public T1 read(VK vk) throws IOException {
                        T1 read = r2.read(vk);
                        if (read == null || rawType.isInstance(read)) {
                            return read;
                        }
                        StringBuilder a = Eo.a("Expected a ");
                        a.append(rawType.getName());
                        a.append(" but was ");
                        a.append(read.getClass().getName());
                        throw new JsonSyntaxException(a.toString());
                    }

                    public void write(WK wk, T1 t1) throws IOException {
                        r2.write(wk, t1);
                    }
                };
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[typeHierarchy=");
                a.append(r1.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        };
    }

    /* renamed from: a */
    public static <TT> YJ m917a(final TK<TT> tk, final TypeAdapter<TT> typeAdapter) {
        return new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2267a(Gson gson, TK<T> tk) {
                if (tk.equals(tk)) {
                    return typeAdapter;
                }
                return null;
            }
        };
    }

    /* renamed from: b */
    public static <T1> YJ m919b(final Class<T1> cls, final TypeAdapter<T1> typeAdapter) {
        return new YJ() {
            /* renamed from: a */
            public <T2> TypeAdapter<T2> mo2274a(Gson gson, TK<T2> tk) {
                final Class rawType = tk.getRawType();
                if (!cls.isAssignableFrom(rawType)) {
                    return null;
                }
                return new TypeAdapter<T1>() {
                    public T1 read(VK vk) throws IOException {
                        T1 read = typeAdapter.read(vk);
                        if (read == null || rawType.isInstance(read)) {
                            return read;
                        }
                        StringBuilder a = Eo.a("Expected a ");
                        a.append(rawType.getName());
                        a.append(" but was ");
                        a.append(read.getClass().getName());
                        throw new JsonSyntaxException(a.toString());
                    }

                    public void write(WK wk, T1 t1) throws IOException {
                        typeAdapter.write(wk, t1);
                    }
                };
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[typeHierarchy=");
                a.append(cls.getName());
                a.append(",adapter=");
                a.append(typeAdapter);
                a.append("]");
                return a.toString();
            }
        };
    }

    /* renamed from: a */
    public static <TT> YJ m918a(final Class<TT> cls, final TypeAdapter<TT> typeAdapter) {
        return new YJ() {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2268a(Gson gson, TK<T> tk) {
                if (tk.getRawType() == cls) {
                    return typeAdapter;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(cls.getName());
                a.append(",adapter=");
                a.append(typeAdapter);
                a.append("]");
                return a.toString();
            }
        };
    }
}
