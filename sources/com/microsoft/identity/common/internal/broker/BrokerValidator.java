package com.microsoft.identity.common.internal.broker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.microsoft.identity.common.adal.internal.AuthenticationSettings;
import com.microsoft.identity.common.exception.ClientException;
import com.microsoft.identity.common.internal.logging.Logger;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertPathValidator;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: PG */
public class BrokerValidator {
    public static final String TAG = "BrokerValidator";
    public final String mCompanyPortalSignature = AuthenticationSettings.INSTANCE.getBrokerSignature();
    public final Context mContext;

    public BrokerValidator(Context context) {
        this.mContext = context;
    }

    public static String getBrokerRedirectUri(Context context, String str) {
        return PackageHelper.getBrokerRedirectUrl(str, new PackageHelper(context.getPackageManager()).getCurrentSignatureForPackage(str));
    }

    private X509Certificate getSelfSignedCert(List<X509Certificate> list) throws ClientException {
        int i = 0;
        X509Certificate x509Certificate = null;
        for (X509Certificate next : list) {
            if (next.getSubjectDN().equals(next.getIssuerDN())) {
                i++;
                x509Certificate = next;
            }
        }
        if (i <= 1 && x509Certificate != null) {
            return x509Certificate;
        }
        throw new ClientException("Calling app could not be verified", "Multiple self signed certs found or no self signed cert existed.");
    }

    public static boolean isValidBrokerRedirect(String str, Context context, String str2) {
        return !TextUtils.isEmpty(str) && str.equalsIgnoreCase(getBrokerRedirectUri(context, str2));
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    private List<X509Certificate> readCertDataForBrokerApp(String str) throws PackageManager.NameNotFoundException, ClientException, IOException, GeneralSecurityException {
        PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(str, 64);
        if (packageInfo != null) {
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr == null || signatureArr.length == 0) {
                throw new ClientException("Calling app could not be verified", "No signature associated with the broker package.");
            }
            ArrayList arrayList = new ArrayList(signatureArr.length);
            Signature[] signatureArr2 = packageInfo.signatures;
            int length = signatureArr2.length;
            int i = 0;
            while (i < length) {
                try {
                    arrayList.add((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(signatureArr2[i].toByteArray())));
                    i++;
                } catch (CertificateException unused) {
                    throw new ClientException("Calling app could not be verified");
                }
            }
            return arrayList;
        }
        throw new ClientException("App package name is not found in the package manager", "No broker package existed.");
    }

    private void verifyCertificateChain(List<X509Certificate> list) throws GeneralSecurityException, ClientException {
        PKIXParameters pKIXParameters = new PKIXParameters(Collections.singleton(new TrustAnchor(getSelfSignedCert(list), (byte[]) null)));
        pKIXParameters.setRevocationEnabled(false);
        CertPathValidator.getInstance("PKIX").validate(CertificateFactory.getInstance("X.509").generateCertPath(list), pKIXParameters);
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void verifySignatureHash(java.util.List<java.security.cert.X509Certificate> r3) throws java.security.NoSuchAlgorithmException, java.security.cert.CertificateEncodingException, com.microsoft.identity.common.exception.ClientException {
        /*
            r2 = this;
            java.util.Iterator r3 = r3.iterator()
        L_0x0004:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0037
            java.lang.Object r0 = r3.next()
            java.security.cert.X509Certificate r0 = (java.security.cert.X509Certificate) r0
            java.lang.String r1 = "SHA"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)
            byte[] r0 = r0.getEncoded()
            r1.update(r0)
            byte[] r0 = r1.digest()
            r1 = 2
            java.lang.String r0 = android.util.Base64.encodeToString(r0, r1)
            java.lang.String r1 = r2.mCompanyPortalSignature
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0036
            java.lang.String r1 = "ho040S3ffZkmxqtQrSwpTVOn9r0="
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0004
        L_0x0036:
            return
        L_0x0037:
            com.microsoft.identity.common.exception.ClientException r3 = new com.microsoft.identity.common.exception.ClientException
            java.lang.String r0 = "Calling app could not be verified"
            r3.<init>(r0)
            goto L_0x0040
        L_0x003f:
            throw r3
        L_0x0040:
            goto L_0x003f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.identity.common.internal.broker.BrokerValidator.verifySignatureHash(java.util.List):void");
    }

    public boolean verifySignature(String str) {
        try {
            List<X509Certificate> readCertDataForBrokerApp = readCertDataForBrokerApp(str);
            verifySignatureHash(readCertDataForBrokerApp);
            if (readCertDataForBrokerApp.size() > 1) {
                verifyCertificateChain(readCertDataForBrokerApp);
            }
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.error("BrokerValidator:verifySignature", "Broker related package does not exist", e);
            return false;
        } catch (NoSuchAlgorithmException e2) {
            Logger.error("BrokerValidator:verifySignature", "Digest SHA algorithm does not exists", e2);
            return false;
        } catch (ClientException | IOException | GeneralSecurityException e3) {
            Logger.error("BrokerValidator:verifySignature", "Signature could not be verified", e3);
            return false;
        }
    }
}
