package com.microsoft.aad.adal;

import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: PG */
public class PackageHelper {
    public static final String TAG = "CallerInfo";
    public final AccountManager mAcctManager = AccountManager.get(this.mContext);
    public Context mContext;

    public PackageHelper(Context context) {
        this.mContext = context;
    }

    public static String getBrokerRedirectUrl(String str, String str2) {
        if (!StringExtensions.isNullOrBlank(str) && !StringExtensions.isNullOrBlank(str2)) {
            if (str.equals("com.azure.authenticator") && str2.equals("ho040S3ffZkmxqtQrSwpTVOn9r0=")) {
                return "urn:ietf:wg:oauth:2.0:oob";
            }
            try {
                return String.format("%s://%s/%s", new Object[]{"msauth", URLEncoder.encode(str, "UTF-8"), URLEncoder.encode(str2, "UTF-8")});
            } catch (UnsupportedEncodingException e) {
                Logger.m1246e("CallerInfo", ADALError.ENCODING_IS_NOT_SUPPORTED.getDescription(), BuildConfig.FLAVOR, ADALError.ENCODING_IS_NOT_SUPPORTED, e);
            }
        }
        return BuildConfig.FLAVOR;
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    public String getCurrentSignatureForPackage(String str) {
        Signature[] signatureArr;
        try {
            PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(str, 64);
            if (packageInfo == null || (signatureArr = packageInfo.signatures) == null || signatureArr.length <= 0) {
                return null;
            }
            Signature signature = signatureArr[0];
            MessageDigest instance = MessageDigest.getInstance("SHA");
            instance.update(signature.toByteArray());
            return Base64.encodeToString(instance.digest(), 2);
        } catch (PackageManager.NameNotFoundException unused) {
            Logger.m1245e("CallerInfo", "Calling App's package does not exist in PackageManager. ", BuildConfig.FLAVOR, ADALError.APP_PACKAGE_NAME_NOT_FOUND);
            return null;
        } catch (NoSuchAlgorithmException unused2) {
            Logger.m1245e("CallerInfo", "Digest SHA algorithm does not exists. ", BuildConfig.FLAVOR, ADALError.DEVICE_NO_SUCH_ALGORITHM);
            return null;
        }
    }

    public int getUIDForPackage(String str) {
        try {
            ApplicationInfo applicationInfo = this.mContext.getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return applicationInfo.uid;
            }
            return 0;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.m1246e("CallerInfo", "Package is not found. ", Eo.a("Package name: ", str), ADALError.PACKAGE_NAME_NOT_FOUND, e);
            return 0;
        }
    }

    @SuppressLint({"WrongConstant"})
    public Object getValueFromMetaData(String str, ComponentName componentName, String str2) {
        try {
            Logger.m1248i("CallerInfo", BuildConfig.FLAVOR, "Calling package:" + str);
            if (componentName != null) {
                Logger.m1250v("CallerInfo", "component:" + componentName.flattenToString());
                ActivityInfo activityInfo = this.mContext.getPackageManager().getActivityInfo(componentName, 129);
                if (activityInfo == null) {
                    return null;
                }
                Bundle bundle = activityInfo.metaData;
                if (bundle != null) {
                    return bundle.get(str2);
                }
                Logger.m1250v("CallerInfo", "metaData is null. Unable to get meta data for " + str2);
                return null;
            }
            Logger.m1250v("CallerInfo", "calling component is null.");
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.m1246e("CallerInfo", "ActivityInfo is not found", BuildConfig.FLAVOR, ADALError.BROKER_ACTIVITY_INFO_NOT_FOUND, e);
            return null;
        }
    }
}
