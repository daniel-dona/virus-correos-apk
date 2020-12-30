package com.nimbusds.jose;

import com.nimbusds.jose.util.Base64URL;
import java.text.ParseException;

/* compiled from: PG */
public class JWEObject extends JOSEObject {
    public static final long serialVersionUID = 1;
    public Base64URL authTag;
    public Base64URL cipherText;
    public Base64URL encryptedKey;
    public JWEHeader header;

    /* renamed from: iv */
    public Base64URL f1331iv;
    public State state;

    /* compiled from: PG */
    public enum State {
        UNENCRYPTED,
        ENCRYPTED,
        DECRYPTED
    }

    public JWEObject(JWEHeader jWEHeader, Payload payload) {
        if (jWEHeader != null) {
            this.header = jWEHeader;
            if (payload != null) {
                setPayload(payload);
                this.encryptedKey = null;
                this.cipherText = null;
                this.state = State.UNENCRYPTED;
                return;
            }
            throw new IllegalArgumentException("The payload must not be null");
        }
        throw new IllegalArgumentException("The JWE header must not be null");
    }

    private void ensureEncryptedOrDecryptedState() {
        State state2 = this.state;
        if (state2 != State.ENCRYPTED && state2 != State.DECRYPTED) {
            throw new IllegalStateException("The JWE object must be in an encrypted or decrypted state");
        }
    }

    private void ensureEncryptedState() {
        if (this.state != State.ENCRYPTED) {
            throw new IllegalStateException("The JWE object must be in an encrypted state");
        }
    }

    private void ensureJWEEncrypterSupport(Ku0 ku0) throws JOSEException {
        if (!ku0.b().contains(getHeader().getAlgorithm())) {
            throw new JOSEException("The \"" + getHeader().getAlgorithm() + "\" algorithm is not supported by the JWE encrypter: Supported algorithms: " + ku0.b());
        } else if (!ku0.a().contains(getHeader().getEncryptionMethod())) {
            throw new JOSEException("The \"" + getHeader().getEncryptionMethod() + "\" encryption method or key size is not supported by the JWE encrypter: Supported methods: " + ku0.a());
        }
    }

    private void ensureUnencryptedState() {
        if (this.state != State.UNENCRYPTED) {
            throw new IllegalStateException("The JWE object must be in an unencrypted state");
        }
    }

    public synchronized void decrypt(Ju0 ju0) throws JOSEException {
        ensureEncryptedState();
        try {
            setPayload(new Payload(ju0.a(getHeader(), getEncryptedKey(), getIV(), getCipherText(), getAuthTag())));
            this.state = State.DECRYPTED;
        } catch (JOSEException e) {
            throw e;
        } catch (Exception e2) {
            throw new JOSEException(e2.getMessage(), e2);
        }
    }

    public synchronized void encrypt(Ku0 ku0) throws JOSEException {
        ensureUnencryptedState();
        ensureJWEEncrypterSupport(ku0);
        try {
            ku0.a(getHeader(), getPayload().toBytes());
            throw null;
        } catch (JOSEException e) {
            throw e;
        } catch (Exception e2) {
            throw new JOSEException(e2.getMessage(), e2);
        }
    }

    public Base64URL getAuthTag() {
        return this.authTag;
    }

    public Base64URL getCipherText() {
        return this.cipherText;
    }

    public Base64URL getEncryptedKey() {
        return this.encryptedKey;
    }

    public Base64URL getIV() {
        return this.f1331iv;
    }

    public State getState() {
        return this.state;
    }

    public String serialize() {
        ensureEncryptedOrDecryptedState();
        StringBuilder sb = new StringBuilder(this.header.toBase64URL().toString());
        sb.append('.');
        Base64URL base64URL = this.encryptedKey;
        if (base64URL != null) {
            sb.append(base64URL.toString());
        }
        sb.append('.');
        Base64URL base64URL2 = this.f1331iv;
        if (base64URL2 != null) {
            sb.append(base64URL2.toString());
        }
        sb.append('.');
        sb.append(this.cipherText.toString());
        sb.append('.');
        Base64URL base64URL3 = this.authTag;
        if (base64URL3 != null) {
            sb.append(base64URL3.toString());
        }
        return sb.toString();
    }

    public static JWEObject parse(String str) throws ParseException {
        Base64URL[] split = JOSEObject.split(str);
        if (split.length == 5) {
            return new JWEObject(split[0], split[1], split[2], split[3], split[4]);
        }
        throw new ParseException("Unexpected number of Base64URL parts, must be five", 0);
    }

    public JWEHeader getHeader() {
        return this.header;
    }

    public JWEObject(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4, Base64URL base64URL5) throws ParseException {
        if (base64URL != null) {
            try {
                this.header = JWEHeader.parse(base64URL);
                if (base64URL2 == null || base64URL2.toString().isEmpty()) {
                    this.encryptedKey = null;
                } else {
                    this.encryptedKey = base64URL2;
                }
                if (base64URL3 == null || base64URL3.toString().isEmpty()) {
                    this.f1331iv = null;
                } else {
                    this.f1331iv = base64URL3;
                }
                if (base64URL4 != null) {
                    this.cipherText = base64URL4;
                    if (base64URL5 == null || base64URL5.toString().isEmpty()) {
                        this.authTag = null;
                    } else {
                        this.authTag = base64URL5;
                    }
                    this.state = State.ENCRYPTED;
                    setParsedParts(base64URL, base64URL2, base64URL3, base64URL4, base64URL5);
                    return;
                }
                throw new IllegalArgumentException("The fourth part must not be null");
            } catch (ParseException e) {
                throw new ParseException("Invalid JWE header: " + e.getMessage(), 0);
            }
        } else {
            throw new IllegalArgumentException("The first part must not be null");
        }
    }
}
