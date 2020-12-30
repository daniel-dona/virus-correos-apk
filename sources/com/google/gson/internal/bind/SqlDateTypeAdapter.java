package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* compiled from: PG */
public final class SqlDateTypeAdapter extends TypeAdapter<Date> {

    /* renamed from: b */
    public static final YJ f1020b = new YJ() {
        /* renamed from: a */
        public <T> TypeAdapter<T> mo2237a(Gson gson, TK<T> tk) {
            if (tk.getRawType() == Date.class) {
                return new SqlDateTypeAdapter();
            }
            return null;
        }
    };

    /* renamed from: a */
    public final DateFormat f1021a = new SimpleDateFormat("MMM d, yyyy");

    /* renamed from: a */
    public synchronized void write(WK wk, Date date) throws IOException {
        wk.B(date == null ? null : this.f1021a.format(date));
    }

    public synchronized Date read(VK vk) throws IOException {
        if (vk.j0() == JsonToken.NULL) {
            vk.Y();
            return null;
        }
        try {
            return new Date(this.f1021a.parse(vk.a0()).getTime());
        } catch (ParseException e) {
            throw new JsonSyntaxException((Throwable) e);
        }
    }
}
