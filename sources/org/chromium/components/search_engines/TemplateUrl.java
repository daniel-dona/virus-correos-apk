package org.chromium.components.search_engines;

import java.util.Locale;
import org.chromium.base.annotations.CalledByNative;

/* compiled from: PG */
public class TemplateUrl {

    /* renamed from: a */
    public final long f2363a;

    public TemplateUrl(long j) {
        this.f2363a = j;
    }

    @CalledByNative
    public static TemplateUrl create(long j) {
        return new TemplateUrl(j);
    }

    public static native String nativeGetKeyword(long j);

    public static native long nativeGetLastVisitedTime(long j);

    public static native int nativeGetPrepopulatedId(long j);

    public static native String nativeGetShortName(long j);

    public static native String nativeGetURL(long j);

    public static native boolean nativeIsPrepopulatedOrCreatedByPolicy(long j);

    /* renamed from: a */
    public boolean mo9608a() {
        return nativeIsPrepopulatedOrCreatedByPolicy(this.f2363a);
    }

    /* renamed from: b */
    public String mo9609b() {
        return nativeGetKeyword(this.f2363a);
    }

    /* renamed from: c */
    public long mo9610c() {
        return nativeGetLastVisitedTime(this.f2363a);
    }

    /* renamed from: d */
    public int mo9611d() {
        return nativeGetPrepopulatedId(this.f2363a);
    }

    /* renamed from: e */
    public String mo9612e() {
        return nativeGetShortName(this.f2363a);
    }

    public boolean equals(Object obj) {
        if ((obj instanceof TemplateUrl) && this.f2363a == ((TemplateUrl) obj).f2363a) {
            return true;
        }
        return false;
    }

    public String toString() {
        return String.format(Locale.US, "TemplateURL -- keyword: %s, short name: %s, prepopulated: %b", new Object[]{mo9609b(), mo9612e(), Boolean.valueOf(mo9608a())});
    }
}
