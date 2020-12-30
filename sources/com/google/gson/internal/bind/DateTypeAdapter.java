package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* compiled from: PG */
public final class DateTypeAdapter extends TypeAdapter<Date> {

    /* renamed from: b */
    public static final YJ f999b = new YJ() {
        /* renamed from: a */
        public <T> TypeAdapter<T> mo2228a(Gson gson, TK<T> tk) {
            if (tk.getRawType() == Date.class) {
                return new DateTypeAdapter();
            }
            return null;
        }
    };

    /* renamed from: a */
    public final List<DateFormat> f1000a = new ArrayList();

    public DateTypeAdapter() {
        this.f1000a.add(DateFormat.getDateTimeInstance(2, 2, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            this.f1000a.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (XK.a >= 9) {
            this.f1000a.add(AK.a(2, 2));
        }
    }

    /* renamed from: a */
    public final synchronized Date mo2226a(String str) {
        for (DateFormat parse : this.f1000a) {
            try {
                return parse.parse(str);
            } catch (ParseException unused) {
            }
        }
        try {
            return PK.a(str, new ParsePosition(0));
        } catch (ParseException e) {
            throw new JsonSyntaxException(str, e);
        }
    }

    public Date read(VK vk) throws IOException {
        if (vk.j0() != JsonToken.NULL) {
            return mo2226a(vk.a0());
        }
        vk.Y();
        return null;
    }

    public synchronized void write(WK wk, Date date) throws IOException {
        if (date == null) {
            wk.E();
        } else {
            wk.B(this.f1000a.get(0).format(date));
        }
    }
}
