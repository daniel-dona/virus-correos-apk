package com.nimbusds.jose.jwk;

import java.security.cert.X509Certificate;
import java.text.ParseException;

/* compiled from: PG */
public enum KeyUse {
    SIGNATURE("sig"),
    ENCRYPTION("enc");
    
    public final String identifier;

    /* access modifiers changed from: public */
    KeyUse(String str) {
        if (str != null) {
            this.identifier = str;
            return;
        }
        throw new IllegalArgumentException("The key use identifier must not be null");
    }

    public static KeyUse from(X509Certificate x509Certificate) {
        if (x509Certificate.getKeyUsage() == null) {
            return null;
        }
        if (x509Certificate.getKeyUsage()[1]) {
            return SIGNATURE;
        }
        if (x509Certificate.getKeyUsage()[0] && x509Certificate.getKeyUsage()[2]) {
            return ENCRYPTION;
        }
        if (x509Certificate.getKeyUsage()[0] && x509Certificate.getKeyUsage()[4]) {
            return ENCRYPTION;
        }
        if (x509Certificate.getKeyUsage()[2] || x509Certificate.getKeyUsage()[3] || x509Certificate.getKeyUsage()[4]) {
            return ENCRYPTION;
        }
        if (x509Certificate.getKeyUsage()[5] || x509Certificate.getKeyUsage()[6]) {
            return SIGNATURE;
        }
        return null;
    }

    public static KeyUse parse(String str) throws ParseException {
        if (str == null) {
            return null;
        }
        for (KeyUse keyUse : values()) {
            if (str.equals(keyUse.identifier)) {
                return keyUse;
            }
        }
        throw new ParseException("Invalid JWK use: " + str, 0);
    }

    public String identifier() {
        return this.identifier;
    }

    public String toString() {
        return identifier();
    }
}
