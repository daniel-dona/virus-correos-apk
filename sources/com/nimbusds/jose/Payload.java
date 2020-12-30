package com.nimbusds.jose;

import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jwt.SignedJWT;
import java.io.Serializable;
import java.text.ParseException;
import net.minidev.json.JSONObject;

/* compiled from: PG */
public final class Payload implements Serializable {
    public static final long serialVersionUID = 1;
    public final Base64URL base64URL;
    public final byte[] bytes;
    public final JSONObject jsonObject;
    public final JWSObject jwsObject;
    public final Origin origin;
    public final SignedJWT signedJWT;
    public final String string;

    /* compiled from: PG */
    public enum Origin {
        JSON,
        STRING,
        BYTE_ARRAY,
        BASE64URL,
        JWS_OBJECT,
        SIGNED_JWT
    }

    public Payload(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.jsonObject = jSONObject;
            this.string = null;
            this.bytes = null;
            this.base64URL = null;
            this.jwsObject = null;
            this.signedJWT = null;
            this.origin = Origin.JSON;
            return;
        }
        throw new IllegalArgumentException("The JSON object must not be null");
    }

    public static String byteArrayToString(byte[] bArr) {
        if (bArr != null) {
            return new String(bArr, cv0.a);
        }
        return null;
    }

    public static byte[] stringToByteArray(String str) {
        if (str != null) {
            return str.getBytes(cv0.a);
        }
        return null;
    }

    public Origin getOrigin() {
        return this.origin;
    }

    public Base64URL toBase64URL() {
        Base64URL base64URL2 = this.base64URL;
        if (base64URL2 != null) {
            return base64URL2;
        }
        return Base64URL.encode(toBytes());
    }

    public byte[] toBytes() {
        byte[] bArr = this.bytes;
        if (bArr != null) {
            return bArr;
        }
        Base64URL base64URL2 = this.base64URL;
        if (base64URL2 != null) {
            return base64URL2.decode();
        }
        return stringToByteArray(toString());
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = this.jsonObject;
        if (jSONObject != null) {
            return jSONObject;
        }
        String payload = toString();
        if (payload == null) {
            return null;
        }
        try {
            return bv0.a(payload);
        } catch (ParseException unused) {
            return null;
        }
    }

    public JWSObject toJWSObject() {
        JWSObject jWSObject = this.jwsObject;
        if (jWSObject != null) {
            return jWSObject;
        }
        try {
            return JWSObject.parse(toString());
        } catch (ParseException unused) {
            return null;
        }
    }

    public SignedJWT toSignedJWT() {
        SignedJWT signedJWT2 = this.signedJWT;
        if (signedJWT2 != null) {
            return signedJWT2;
        }
        try {
            return SignedJWT.parse(toString());
        } catch (ParseException unused) {
            return null;
        }
    }

    public String toString() {
        String str = this.string;
        if (str != null) {
            return str;
        }
        JWSObject jWSObject = this.jwsObject;
        if (jWSObject == null) {
            JSONObject jSONObject = this.jsonObject;
            if (jSONObject != null) {
                return jSONObject.toString();
            }
            byte[] bArr = this.bytes;
            if (bArr != null) {
                return byteArrayToString(bArr);
            }
            Base64URL base64URL2 = this.base64URL;
            if (base64URL2 != null) {
                return base64URL2.decodeToString();
            }
            return null;
        } else if (jWSObject.getParsedString() != null) {
            return this.jwsObject.getParsedString();
        } else {
            return this.jwsObject.serialize();
        }
    }

    public <T> T toType(Ou0<T> ou0) {
        return ou0.a(this);
    }

    public Payload(String str) {
        if (str != null) {
            this.jsonObject = null;
            this.string = str;
            this.bytes = null;
            this.base64URL = null;
            this.jwsObject = null;
            this.signedJWT = null;
            this.origin = Origin.STRING;
            return;
        }
        throw new IllegalArgumentException("The string must not be null");
    }

    public Payload(byte[] bArr) {
        if (bArr != null) {
            this.jsonObject = null;
            this.string = null;
            this.bytes = bArr;
            this.base64URL = null;
            this.jwsObject = null;
            this.signedJWT = null;
            this.origin = Origin.BYTE_ARRAY;
            return;
        }
        throw new IllegalArgumentException("The byte array must not be null");
    }

    public Payload(Base64URL base64URL2) {
        if (base64URL2 != null) {
            this.jsonObject = null;
            this.string = null;
            this.bytes = null;
            this.base64URL = base64URL2;
            this.jwsObject = null;
            this.signedJWT = null;
            this.origin = Origin.BASE64URL;
            return;
        }
        throw new IllegalArgumentException("The Base64URL-encoded object must not be null");
    }

    public Payload(JWSObject jWSObject) {
        if (jWSObject == null) {
            throw new IllegalArgumentException("The JWS object must not be null");
        } else if (jWSObject.getState() != JWSObject.State.UNSIGNED) {
            this.jsonObject = null;
            this.string = null;
            this.bytes = null;
            this.base64URL = null;
            this.jwsObject = jWSObject;
            this.signedJWT = null;
            this.origin = Origin.JWS_OBJECT;
        } else {
            throw new IllegalArgumentException("The JWS object must be signed");
        }
    }

    public Payload(SignedJWT signedJWT2) {
        if (signedJWT2 == null) {
            throw new IllegalArgumentException("The signed JWT must not be null");
        } else if (signedJWT2.getState() != JWSObject.State.UNSIGNED) {
            this.jsonObject = null;
            this.string = null;
            this.bytes = null;
            this.base64URL = null;
            this.signedJWT = signedJWT2;
            this.jwsObject = signedJWT2;
            this.origin = Origin.SIGNED_JWT;
        } else {
            throw new IllegalArgumentException("The JWT must be signed");
        }
    }
}
