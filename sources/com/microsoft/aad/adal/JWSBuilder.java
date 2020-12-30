package com.microsoft.aad.adal;

import android.util.Base64;
import com.google.gson.Gson;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.nimbusds.jwt.JWTClaimsSet;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
public class JWSBuilder implements IJWSBuilder {
    public static final String JWS_ALGORITHM = "SHA256withRSA";
    public static final String JWS_HEADER_ALG = "RS256";
    public static final long SECONDS_MS = 1000;
    public static final String TAG = "JWSBuilder";

    /* compiled from: PG */
    public final class Claims {
        @bK("aud")
        public String mAudience;
        @bK("iat")
        public long mIssueAt;
        @bK("nonce")
        public String mNonce;

        public Claims() {
        }
    }

    /* compiled from: PG */
    public final class JwsHeader {
        @bK("alg")
        public String mAlgorithm;
        @bK("x5c")
        public String[] mCert;
        @bK("typ")
        public String mType;

        public JwsHeader() {
        }
    }

    private JSONObject generateJson(Map<String, String> map, int i) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        long j = ((long) i) + currentTimeMillis;
        for (Map.Entry next : map.entrySet()) {
            if (((String) next.getKey()).equalsIgnoreCase(JWTClaimsSet.ISSUED_AT_CLAIM) || ((String) next.getKey()).equalsIgnoreCase(JWTClaimsSet.NOT_BEFORE_CLAIM)) {
                jSONObject.put((String) next.getKey(), currentTimeMillis);
            } else if (((String) next.getKey()).equalsIgnoreCase(JWTClaimsSet.EXPIRATION_TIME_CLAIM)) {
                jSONObject.put((String) next.getKey(), j);
            } else {
                jSONObject.put((String) next.getKey(), next.getValue());
            }
        }
        return jSONObject;
    }

    public static String sign(RSAPrivateKey rSAPrivateKey, byte[] bArr) throws AuthenticationException {
        try {
            Signature instance = Signature.getInstance(JWS_ALGORITHM);
            instance.initSign(rSAPrivateKey);
            instance.update(bArr);
            return StringExtensions.encodeBase64URLSafeString(instance.sign());
        } catch (InvalidKeyException e) {
            ADALError aDALError = ADALError.KEY_CHAIN_PRIVATE_KEY_EXCEPTION;
            StringBuilder a = Eo.a("Invalid private RSA key: ");
            a.append(e.getMessage());
            throw new AuthenticationException(aDALError, a.toString(), (Throwable) e);
        } catch (SignatureException e2) {
            ADALError aDALError2 = ADALError.SIGNATURE_EXCEPTION;
            StringBuilder a2 = Eo.a("RSA signature exception: ");
            a2.append(e2.getMessage());
            throw new AuthenticationException(aDALError2, a2.toString(), (Throwable) e2);
        } catch (UnsupportedEncodingException unused) {
            throw new AuthenticationException(ADALError.ENCODING_IS_NOT_SUPPORTED);
        } catch (NoSuchAlgorithmException e3) {
            ADALError aDALError3 = ADALError.DEVICE_NO_SUCH_ALGORITHM;
            StringBuilder a3 = Eo.a("Unsupported RSA algorithm: ");
            a3.append(e3.getMessage());
            throw new AuthenticationException(aDALError3, a3.toString(), (Throwable) e3);
        }
    }

    public String generateJWT(Map<String, String> map, Map<String, String> map2, int i) throws JSONException, UnsupportedEncodingException {
        Logger.m1250v(TAG, "Generating JWT.");
        JSONObject generateJson = generateJson(map, i);
        JSONObject generateJson2 = generateJson(map2, i);
        return StringExtensions.encodeBase64URLSafeString(generateJson.toString().getBytes("UTF-8")) + "." + StringExtensions.encodeBase64URLSafeString(generateJson2.toString().getBytes("UTF-8"));
    }

    public String generateSignedJWT(String str, String str2, RSAPrivateKey rSAPrivateKey, RSAPublicKey rSAPublicKey, X509Certificate x509Certificate) throws AuthenticationException {
        if (StringExtensions.isNullOrBlank(str)) {
            throw new IllegalArgumentException("nonce");
        } else if (StringExtensions.isNullOrBlank(str2)) {
            throw new IllegalArgumentException("audience");
        } else if (rSAPrivateKey == null) {
            throw new IllegalArgumentException("privateKey");
        } else if (rSAPublicKey != null) {
            Gson gson = new Gson();
            Claims claims = new Claims();
            String unused = claims.mNonce = str;
            String unused2 = claims.mAudience = str2;
            long unused3 = claims.mIssueAt = System.currentTimeMillis() / 1000;
            JwsHeader jwsHeader = new JwsHeader();
            String unused4 = jwsHeader.mAlgorithm = JWS_HEADER_ALG;
            String unused5 = jwsHeader.mType = "JWT";
            try {
                String[] unused6 = jwsHeader.mCert = new String[1];
                jwsHeader.mCert[0] = new String(Base64.encode(x509Certificate.getEncoded(), 2), "UTF-8");
                String a = gson.mo2100a((Object) jwsHeader);
                String a2 = gson.mo2100a((Object) claims);
                Logger.m1251v("JWSBuilder:generateSignedJWT", "Generate client certificate challenge response JWS Header. ", "Header: " + a, (ADALError) null);
                String str3 = StringExtensions.encodeBase64URLSafeString(a.getBytes("UTF-8")) + "." + StringExtensions.encodeBase64URLSafeString(a2.getBytes("UTF-8"));
                return Eo.b(str3, ".", sign(rSAPrivateKey, str3.getBytes("UTF-8")));
            } catch (UnsupportedEncodingException e) {
                throw new AuthenticationException(ADALError.ENCODING_IS_NOT_SUPPORTED, "Unsupported encoding", (Throwable) e);
            } catch (CertificateEncodingException e2) {
                throw new AuthenticationException(ADALError.CERTIFICATE_ENCODING_ERROR, "Certificate encoding error", (Throwable) e2);
            }
        } else {
            throw new IllegalArgumentException("pubKey");
        }
    }
}
