package com.nimbusds.jwt;

import com.nimbusds.jose.Header;
import com.nimbusds.jose.JOSEObject;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.util.Base64URL;
import java.text.ParseException;
import net.minidev.json.JSONObject;

/* compiled from: PG */
public class EncryptedJWT extends JWEObject implements JWT {
    public static final long serialVersionUID = 1;

    public EncryptedJWT(JWEHeader jWEHeader, JWTClaimsSet jWTClaimsSet) {
        super(jWEHeader, new Payload(jWTClaimsSet.toJSONObject()));
    }

    public /* bridge */ /* synthetic */ Header getHeader() {
        return getHeader();
    }

    public JWTClaimsSet getJWTClaimsSet() throws ParseException {
        Payload payload = getPayload();
        if (payload == null) {
            return null;
        }
        JSONObject jSONObject = payload.toJSONObject();
        if (jSONObject != null) {
            return JWTClaimsSet.parse(jSONObject);
        }
        throw new ParseException("Payload of JWE object is not a valid JSON object", 0);
    }

    public EncryptedJWT(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4, Base64URL base64URL5) throws ParseException {
        super(base64URL, base64URL2, base64URL3, base64URL4, base64URL5);
    }

    public static EncryptedJWT parse(String str) throws ParseException {
        Base64URL[] split = JOSEObject.split(str);
        if (split.length == 5) {
            return new EncryptedJWT(split[0], split[1], split[2], split[3], split[4]);
        }
        throw new ParseException("Unexpected number of Base64URL parts, must be five", 0);
    }
}
