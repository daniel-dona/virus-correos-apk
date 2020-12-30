package com.nimbusds.jose.util;

import java.io.Serializable;
import java.math.BigInteger;

/* compiled from: PG */
public class Base64 implements mB0, Serializable {
    public static final long serialVersionUID = 1;
    public final String value;

    public Base64(String str) {
        if (str != null) {
            this.value = str;
            return;
        }
        throw new IllegalArgumentException("The Base64 value must not be null");
    }

    public static Base64 encode(byte[] bArr) {
        return new Base64(Yu0.a(bArr, false));
    }

    public byte[] decode() {
        return Yu0.b(this.value);
    }

    public BigInteger decodeToBigInteger() {
        return new BigInteger(1, decode());
    }

    public String decodeToString() {
        return new String(decode(), cv0.a);
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Base64) && toString().equals(obj.toString());
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toJSONString() {
        String str;
        StringBuilder sb = new StringBuilder("\"");
        String str2 = this.value;
        qB0 qb0 = tB0.a;
        if (str2 == null) {
            str = null;
        } else {
            StringBuilder sb2 = new StringBuilder();
            qb0.a(str2, sb2);
            str = sb2.toString();
        }
        sb.append(str);
        sb.append("\"");
        return sb.toString();
    }

    public String toString() {
        return this.value;
    }

    public static Base64 encode(BigInteger bigInteger) {
        return encode(Zu0.a(bigInteger));
    }

    public static Base64 encode(String str) {
        return encode(str.getBytes(cv0.a));
    }
}
