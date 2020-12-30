package com.google.ipc.invalidation.external.client.types;

/* compiled from: PG */
public final class Status {

    /* renamed from: a */
    public final Code f1119a;

    /* renamed from: b */
    public final String f1120b;

    /* compiled from: PG */
    public enum Code {
        SUCCESS,
        TRANSIENT_FAILURE,
        PERMANENT_FAILURE
    }

    public Status(Code code, String str) {
        this.f1119a = code;
        this.f1120b = str;
    }

    /* renamed from: a */
    public boolean mo2349a() {
        return this.f1119a == Code.SUCCESS;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        if (this.f1119a != status.f1119a) {
            return false;
        }
        String str = this.f1120b;
        if (str != null) {
            return str.equals(status.f1120b);
        }
        if (status.f1120b == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.f1119a.hashCode();
        String str = this.f1120b;
        return hashCode ^ (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        StringBuilder a = Eo.a("Code: ");
        a.append(this.f1119a);
        a.append(", ");
        a.append(this.f1120b);
        return a.toString();
    }
}
