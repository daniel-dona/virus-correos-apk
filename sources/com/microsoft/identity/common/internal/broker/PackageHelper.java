package com.microsoft.identity.common.internal.broker;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.internal.logging.Logger;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: PG */
public class PackageHelper {
    public static final String TAG = "CallerInfo";
    public PackageManager mPackageManager;

    public PackageHelper(PackageManager packageManager) {
        this.mPackageManager = packageManager;
    }

    public static String getBrokerRedirectUrl(String str, String str2) {
        if (!StringExtensions.isNullOrBlank(str) && !StringExtensions.isNullOrBlank(str2)) {
            if (str.equals("com.azure.authenticator") && str2.equals("ho040S3ffZkmxqtQrSwpTVOn9r0=")) {
                return "urn:ietf:wg:oauth:2.0:oob";
            }
            try {
                return String.format("%s://%s/%s", new Object[]{"msauth", URLEncoder.encode(str, "UTF-8"), URLEncoder.encode(str2, "UTF-8")});
            } catch (UnsupportedEncodingException e) {
                Logger.error("CallerInfo", BuildConfig.FLAVOR, "Encoding is not supported", e);
            }
        }
        return BuildConfig.FLAVOR;
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    public String getCurrentSignatureForPackage(String str) {
        Signature[] signatureArr;
        try {
            PackageInfo packageInfo = this.mPackageManager.getPackageInfo(str, 64);
            if (packageInfo == null || (signatureArr = packageInfo.signatures) == null || signatureArr.length <= 0) {
                return null;
            }
            Signature signature = signatureArr[0];
            MessageDigest instance = MessageDigest.getInstance("SHA");
            instance.update(signature.toByteArray());
            return Base64.encodeToString(instance.digest(), 2);
        } catch (PackageManager.NameNotFoundException e) {
            Logger.error("CallerInfo", "Calling App's package does not exist in PackageManager. ", BuildConfig.FLAVOR, e);
            return null;
        } catch (NoSuchAlgorithmException e2) {
            Logger.error("CallerInfo", "Digest SHA algorithm does not exists. ", BuildConfig.FLAVOR, e2);
            return null;
        }
    }

    public int getUIDForPackage(String str) {
        try {
            ApplicationInfo applicationInfo = this.mPackageManager.getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return applicationInfo.uid;
            }
            return 0;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.error("CallerInfo", "Package is not found. ", "Package name: " + str, e);
            return 0;
        }
    }
}
