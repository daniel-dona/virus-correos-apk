package com.microsoft.aad.adal;

import java.io.UnsupportedEncodingException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import org.json.JSONException;

/* compiled from: PG */
public interface IJWSBuilder {
    String generateJWT(Map<String, String> map, Map<String, String> map2, int i) throws JSONException, UnsupportedEncodingException;

    String generateSignedJWT(String str, String str2, RSAPrivateKey rSAPrivateKey, RSAPublicKey rSAPublicKey, X509Certificate x509Certificate) throws AuthenticationException;
}
