package com.google.gson;

import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* compiled from: PG */
public final class DefaultDateTypeAdapter extends TypeAdapter<Date> {

    /* renamed from: a */
    public final Class<? extends Date> f929a;

    /* renamed from: b */
    public final List<DateFormat> f930b = new ArrayList();

    public DefaultDateTypeAdapter(Class<? extends Date> cls, int i, int i2) {
        m849a(cls);
        this.f929a = cls;
        this.f930b.add(DateFormat.getDateTimeInstance(i, i2, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            this.f930b.add(DateFormat.getDateTimeInstance(i, i2));
        }
        if (XK.a >= 9) {
            this.f930b.add(AK.a(i, i2));
        }
    }

    /* renamed from: a */
    public static Class<? extends Date> m849a(Class<? extends Date> cls) {
        if (cls == Date.class || cls == java.sql.Date.class || cls == Timestamp.class) {
            return cls;
        }
        throw new IllegalArgumentException("Date type must be one of " + Date.class + ", " + Timestamp.class + ", or " + java.sql.Date.class + " but was " + cls);
    }

    public String toString() {
        DateFormat dateFormat = this.f930b.get(0);
        if (dateFormat instanceof SimpleDateFormat) {
            StringBuilder a = Eo.a("DefaultDateTypeAdapter(");
            a.append(((SimpleDateFormat) dateFormat).toPattern());
            a.append(')');
            return a.toString();
        }
        StringBuilder a2 = Eo.a("DefaultDateTypeAdapter(");
        a2.append(dateFormat.getClass().getSimpleName());
        a2.append(')');
        return a2.toString();
    }

    public Date read(VK vk) throws IOException {
        if (vk.j0() == JsonToken.NULL) {
            vk.Y();
            return null;
        }
        Date a = mo2078a(vk.a0());
        Class<? extends Date> cls = this.f929a;
        if (cls == Date.class) {
            return a;
        }
        if (cls == Timestamp.class) {
            return new Timestamp(a.getTime());
        }
        if (cls == java.sql.Date.class) {
            return new java.sql.Date(a.getTime());
        }
        throw new AssertionError();
    }

    public void write(WK wk, Date date) throws IOException {
        if (date == null) {
            wk.E();
            return;
        }
        synchronized (this.f930b) {
            wk.B(this.f930b.get(0).format(date));
        }
    }

    /* renamed from: a */
    public final Date mo2078a(String str) {
        synchronized (this.f930b) {
            for (DateFormat parse : this.f930b) {
                try {
                    Date parse2 = parse.parse(str);
                    return parse2;
                } catch (ParseException unused) {
                }
            }
            try {
                Date a = PK.a(str, new ParsePosition(0));
                return a;
            } catch (ParseException e) {
                throw new JsonSyntaxException(str, e);
            }
        }
    }
}
