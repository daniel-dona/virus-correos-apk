package com.microsoft.aad.adal;

import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* compiled from: PG */
public final class DateTimeAdapter implements QJ<Date>, XJ<Date> {
    public static final String TAG = "DateTimeAdapter";
    public final DateFormat mEnUs24HourFormat = buildEnUs24HourDateFormat();
    public final DateFormat mEnUsFormat = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    public final DateFormat mISO8601Format = buildIso8601Format();
    public final DateFormat mLocal24HourFormat = buildLocal24HourDateFormat();
    public final DateFormat mLocalFormat = DateFormat.getDateTimeInstance(2, 2);

    public static DateFormat buildEnUs24HourDateFormat() {
        return new SimpleDateFormat("MMM dd, yyyy HH:mm:ss", Locale.US);
    }

    public static DateFormat buildIso8601Format() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }

    public static DateFormat buildLocal24HourDateFormat() {
        return new SimpleDateFormat("MMM dd, yyyy HH:mm:ss", Locale.getDefault());
    }

    public synchronized Date deserialize(RJ rj, Type type, PJ pj) throws JsonParseException {
        String d;
        d = rj.d();
        try {
        } catch (ParseException e) {
            Logger.m1246e("DateTimeAdapter:deserialize", "Could not parse date. ", e.getMessage(), ADALError.DATE_PARSING_FAILURE, e);
            throw new JsonParseException("Could not parse date: " + d);
        } catch (ParseException unused) {
            Logger.m1250v("DateTimeAdapter:deserialize", "Cannot parse with ISO8601, try again with local format.");
            try {
                return this.mLocalFormat.parse(d);
            } catch (ParseException unused2) {
                Logger.m1250v("DateTimeAdapter:deserialize", "Cannot parse with local format, try again with local 24 hour format.");
                try {
                    return this.mLocal24HourFormat.parse(d);
                } catch (ParseException unused3) {
                    Logger.m1250v("DateTimeAdapter:deserialize", "Cannot parse with local 24 hour format, try again with en us format.");
                    try {
                        return this.mEnUsFormat.parse(d);
                    } catch (ParseException unused4) {
                        Logger.m1250v("DateTimeAdapter:deserialize", "Cannot parse with en us format, try again with en us 24 hour format.");
                        return this.mEnUs24HourFormat.parse(d);
                    }
                }
            }
        }
        return this.mISO8601Format.parse(d);
    }

    public synchronized RJ serialize(Date date, Type type, WJ wj) {
        return new VJ(this.mISO8601Format.format(date));
    }
}
