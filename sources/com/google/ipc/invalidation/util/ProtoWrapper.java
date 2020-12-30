package com.google.ipc.invalidation.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* compiled from: PG */
public abstract class ProtoWrapper extends vN {

    /* renamed from: b */
    public static final List<?> f1150b = Collections.unmodifiableList(new ArrayList(0));

    /* renamed from: a */
    public int f1151a;

    /* compiled from: PG */
    public static final class ValidationArgumentException extends IllegalArgumentException {
        public ValidationArgumentException(String str) {
            super(str);
        }
    }

    /* compiled from: PG */
    public static final class ValidationException extends Exception {
        public ValidationException(String str) {
            super(str);
        }

        public ValidationException(Throwable th) {
            super(th);
        }
    }

    /* renamed from: a */
    public static int m1045a(long j) {
        return (int) (j ^ (j >>> 32));
    }

    /* renamed from: a */
    public static int m1046a(boolean z) {
        return z ? 1231 : 1237;
    }

    /* renamed from: a */
    public static void m1050a(String str, Object obj) {
        if (obj == null) {
            throw new ValidationArgumentException(String.format(Locale.ROOT, "Required field '%s' was not set", new Object[]{str}));
        }
    }

    /* renamed from: b */
    public static <T> List<T> m1054b(String str, Collection<T> collection) {
        List<T> a = m1047a(str, collection);
        if (!collection.isEmpty()) {
            return a;
        }
        throw new ValidationArgumentException(String.format(Locale.ROOT, "Repeated field '%s' must have at least one element", new Object[]{str}));
    }

    /* renamed from: a */
    public abstract int mo2356a();

    public final int hashCode() {
        if (this.f1151a == -1) {
            int a = mo2356a();
            if (a == -1) {
                a = 0;
            }
            this.f1151a = a;
        }
        return this.f1151a;
    }

    /* renamed from: a */
    public static <T> List<T> m1047a(String str, Collection<T> collection) {
        if (collection == null || collection.size() == 0) {
            return f1150b;
        }
        ArrayList arrayList = new ArrayList(collection);
        int i = 0;
        while (i < arrayList.size()) {
            if (arrayList.get(i) != null) {
                i++;
            } else {
                throw new ValidationArgumentException(String.format(Locale.ROOT, "Element %d of repeated field '%s' must not be null.", new Object[]{Integer.valueOf(i), str}));
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* renamed from: b */
    public static void m1056b(String str, String str2) {
        throw new ValidationArgumentException(String.format(Locale.ROOT, "Multiple one-of fields defined, including: %s, %s", new Object[]{str, str2}));
    }

    /* renamed from: b */
    public static void m1055b() {
        throw new ValidationArgumentException("No one-of fields defined");
    }

    /* renamed from: a */
    public static void m1048a(String str, int i) {
        if (i < 0) {
            throw new ValidationArgumentException(String.format(Locale.ROOT, "Field '%s' must be non-negative: %d", new Object[]{str, Integer.valueOf(i)}));
        }
    }

    /* renamed from: a */
    public static void m1049a(String str, long j) {
        if (j < 0) {
            throw new ValidationArgumentException(String.format(Locale.ROOT, "Field '%s' must be non-negative: %d", new Object[]{str, Long.valueOf(j)}));
        }
    }

    /* renamed from: a */
    public static void m1052a(String str, sN sNVar) {
        if (sNVar == null) {
            throw new NullPointerException();
        } else if (sNVar.a.length == 0) {
            throw new ValidationArgumentException(String.format(Locale.ROOT, "Field '%s' must be non-empty", new Object[]{str}));
        }
    }

    /* renamed from: a */
    public static void m1051a(String str, String str2) {
        if (str2 == null) {
            throw new NullPointerException();
        } else if (str2.length() == 0) {
            throw new ValidationArgumentException(String.format(Locale.ROOT, "Field '%s' must be non-empty", new Object[]{str}));
        }
    }

    /* renamed from: a */
    public void mo2382a(boolean z, String str) {
        if (!z) {
            throw new ValidationArgumentException(String.format(Locale.ROOT, "%s: %s", new Object[]{str, this}));
        }
    }

    /* renamed from: a */
    public static boolean m1053a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        if (obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }
}
