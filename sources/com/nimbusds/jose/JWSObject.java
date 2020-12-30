package com.nimbusds.jose;

import com.nimbusds.jose.util.Base64URL;
import java.text.ParseException;

/* compiled from: PG */
public class JWSObject extends JOSEObject {
    public static final long serialVersionUID = 1;
    public final JWSHeader header;
    public Base64URL signature;
    public final String signingInputString;
    public State state;

    /* compiled from: PG */
    public enum State {
        UNSIGNED,
        SIGNED,
        VERIFIED
    }

    public JWSObject(JWSHeader jWSHeader, Payload payload) {
        if (jWSHeader != null) {
            this.header = jWSHeader;
            if (payload != null) {
                setPayload(payload);
                this.signingInputString = composeSigningInput(jWSHeader.toBase64URL(), payload.toBase64URL());
                this.signature = null;
                this.state = State.UNSIGNED;
                return;
            }
            throw new IllegalArgumentException("The payload must not be null");
        }
        throw new IllegalArgumentException("The JWS header must not be null");
    }

    public static String composeSigningInput(Base64URL base64URL, Base64URL base64URL2) {
        return String.valueOf(base64URL.toString()) + '.' + base64URL2.toString();
    }

    private void ensureJWSSignerSupport(Mu0 mu0) throws JOSEException {
        Pu0 pu0 = (Pu0) mu0;
        if (!pu0.a().contains(getHeader().getAlgorithm())) {
            throw new JOSEException("The \"" + getHeader().getAlgorithm() + "\" algorithm is not allowed or supported by the JWS signer: Supported algorithms: " + pu0.a());
        }
    }

    private void ensureSignedOrVerifiedState() {
        State state2 = this.state;
        if (state2 != State.SIGNED && state2 != State.VERIFIED) {
            throw new IllegalStateException("The JWS object must be in a signed or verified state");
        }
    }

    private void ensureUnsignedState() {
        if (this.state != State.UNSIGNED) {
            throw new IllegalStateException("The JWS object must be in an unsigned state");
        }
    }

    public Base64URL getSignature() {
        return this.signature;
    }

    public byte[] getSigningInput() {
        return this.signingInputString.getBytes(cv0.a);
    }

    public State getState() {
        return this.state;
    }

    public String serialize() {
        ensureSignedOrVerifiedState();
        return String.valueOf(this.signingInputString) + '.' + this.signature.toString();
    }

    public synchronized void sign(Mu0 mu0) throws JOSEException {
        ensureUnsignedState();
        ensureJWSSignerSupport(mu0);
        try {
            this.signature = ((Ru0) mu0).a(getHeader(), getSigningInput());
            this.state = State.SIGNED;
        } catch (JOSEException e) {
            throw e;
        } catch (Exception e2) {
            throw new JOSEException(e2.getMessage(), e2);
        }
    }

    public synchronized boolean verify(Nu0 nu0) throws JOSEException {
        boolean a;
        ensureSignedOrVerifiedState();
        try {
            a = nu0.a(getHeader(), getSigningInput(), getSignature());
            if (a) {
                this.state = State.VERIFIED;
            }
        } catch (JOSEException e) {
            throw e;
        } catch (Exception e2) {
            throw new JOSEException(e2.getMessage(), e2);
        }
        return a;
    }

    public static JWSObject parse(String str) throws ParseException {
        Base64URL[] split = JOSEObject.split(str);
        if (split.length == 3) {
            return new JWSObject(split[0], split[1], split[2]);
        }
        throw new ParseException("Unexpected number of Base64URL parts, must be three", 0);
    }

    public JWSHeader getHeader() {
        return this.header;
    }

    public JWSObject(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3) throws ParseException {
        if (base64URL != null) {
            try {
                this.header = JWSHeader.parse(base64URL);
                if (base64URL2 != null) {
                    setPayload(new Payload(base64URL2));
                    this.signingInputString = composeSigningInput(base64URL, base64URL2);
                    if (base64URL3 != null) {
                        this.signature = base64URL3;
                        this.state = State.SIGNED;
                        setParsedParts(base64URL, base64URL2, base64URL3);
                        return;
                    }
                    throw new IllegalArgumentException("The third part must not be null");
                }
                throw new IllegalArgumentException("The second part must not be null");
            } catch (ParseException e) {
                throw new ParseException("Invalid JWS header: " + e.getMessage(), 0);
            }
        } else {
            throw new IllegalArgumentException("The first part must not be null");
        }
    }
}
