package com.nimbusds.jose.util;

import java.math.BigInteger;

/* compiled from: PG */
public class Base64URL extends Base64 {
    public Base64URL(String str) {
        super(str);
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Base64URL) && toString().equals(obj.toString());
    }

    public static Base64URL encode(byte[] bArr) {
        return new Base64URL(Yu0.a(bArr, true));
    }

    public static Base64URL encode(BigInteger bigInteger) {
        return encode(Zu0.a(bigInteger));
    }

    public static Base64URL encode(String str) {
        return encode(str.getBytes(cv0.a));
    }
}
