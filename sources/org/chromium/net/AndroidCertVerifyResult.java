package org.chromium.net;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;

/* compiled from: PG */
public class AndroidCertVerifyResult {

    /* renamed from: a */
    public final int f2533a;

    /* renamed from: b */
    public final boolean f2534b;

    /* renamed from: c */
    public final List<X509Certificate> f2535c;

    public AndroidCertVerifyResult(int i, boolean z, List<X509Certificate> list) {
        this.f2533a = i;
        this.f2534b = z;
        this.f2535c = new ArrayList(list);
    }

    @CalledByNative
    public byte[][] getCertificateChainEncoded() {
        byte[][] bArr = new byte[this.f2535c.size()][];
        int i = 0;
        while (i < this.f2535c.size()) {
            try {
                bArr[i] = this.f2535c.get(i).getEncoded();
                i++;
            } catch (CertificateEncodingException unused) {
                return new byte[0][];
            }
        }
        return bArr;
    }

    @CalledByNative
    public int getStatus() {
        return this.f2533a;
    }

    @CalledByNative
    public boolean isIssuedByKnownRoot() {
        return this.f2534b;
    }

    public AndroidCertVerifyResult(int i) {
        this.f2533a = i;
        this.f2534b = false;
        this.f2535c = Collections.emptyList();
    }
}
