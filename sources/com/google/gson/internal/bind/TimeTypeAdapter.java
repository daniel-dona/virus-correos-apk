package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* compiled from: PG */
public final class TimeTypeAdapter extends TypeAdapter<Time> {

    /* renamed from: b */
    public static final YJ f1022b = new YJ() {
        /* renamed from: a */
        public <T> TypeAdapter<T> mo2239a(Gson gson, TK<T> tk) {
            if (tk.getRawType() == Time.class) {
                return new TimeTypeAdapter();
            }
            return null;
        }
    };

    /* renamed from: a */
    public final DateFormat f1023a = new SimpleDateFormat("hh:mm:ss a");

    /* renamed from: a */
    public synchronized void write(WK wk, Time time) throws IOException {
        wk.B(time == null ? null : this.f1023a.format(time));
    }

    public synchronized Time read(VK vk) throws IOException {
        if (vk.j0() == JsonToken.NULL) {
            vk.Y();
            return null;
        }
        try {
            return new Time(this.f1023a.parse(vk.a0()).getTime());
        } catch (ParseException e) {
            throw new JsonSyntaxException((Throwable) e);
        }
    }
}
