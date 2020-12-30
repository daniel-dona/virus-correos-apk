package com.firebase.jobdispatcher;

import java.util.List;

/* compiled from: PG */
public class ValidationEnforcer implements HF {

    /* renamed from: a */
    public final HF f887a;

    /* compiled from: PG */
    public static final class ValidationException extends RuntimeException {
        public final List<String> errors;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public ValidationException(java.lang.String r2, java.util.List<java.lang.String> r3) {
            /*
                r1 = this;
                java.lang.String r0 = ": "
                java.lang.StringBuilder r2 = Eo.c(r2, r0)
                java.lang.String r0 = "\n  - "
                java.lang.String r0 = android.text.TextUtils.join(r0, r3)
                r2.append(r0)
                java.lang.String r2 = r2.toString()
                r1.<init>(r2)
                r1.errors = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.firebase.jobdispatcher.ValidationEnforcer.ValidationException.<init>(java.lang.String, java.util.List):void");
        }

        public List<String> getErrors() {
            return this.errors;
        }
    }

    public ValidationEnforcer(HF hf) {
        this.f887a = hf;
    }

    /* renamed from: a */
    public List<String> mo1993a(DF df) {
        return this.f887a.a(df);
    }
}
