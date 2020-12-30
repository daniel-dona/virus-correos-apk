package com.citrix.auth.model;

/* compiled from: PG */
public final class AAACCookie extends Token {
    public final String aaacCookie;

    public AAACCookie(String str) {
        this.aaacCookie = str;
    }

    public boolean canEqual(Object obj) {
        return obj instanceof AAACCookie;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AAACCookie)) {
            return false;
        }
        AAACCookie aAACCookie = (AAACCookie) obj;
        if (!aAACCookie.canEqual(this)) {
            return false;
        }
        String aaacCookie2 = getAaacCookie();
        String aaacCookie3 = aAACCookie.getAaacCookie();
        return aaacCookie2 != null ? aaacCookie2.equals(aaacCookie3) : aaacCookie3 == null;
    }

    public String getAaacCookie() {
        return this.aaacCookie;
    }

    public Token getToken() {
        throw new UnsupportedOperationException("Method Not supported.");
    }

    public int hashCode() {
        String aaacCookie2 = getAaacCookie();
        return 59 + (aaacCookie2 == null ? 43 : aaacCookie2.hashCode());
    }

    public String toString() {
        StringBuilder a = Eo.a("AAACCookie(aaacCookie=");
        a.append(getAaacCookie());
        a.append(")");
        return a.toString();
    }
}
