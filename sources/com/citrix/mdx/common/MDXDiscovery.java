package com.citrix.mdx.common;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;
import com.citrix.worx.sdk.MDXConstants;

/* compiled from: PG */
public class MDXDiscovery {
    public static final String COLUMN_MIGRATION_COMPLETED = "migrationCompleted";
    public static final int CitrixCertHash_Receiver_production = 6409724;
    public static final int CitrixCertHash_WorxHome_production = -165073;
    public static final int CitrixCertHash_androiddebugkey = 749826;
    public static final int CitrixCertHash_androiddebugkey2 = 1187513;
    public static final int CitrixCertHash_droid = 3149260;
    public static final int CitrixCertHash_platform_x509_pem = 5829634;
    public static final String DEFAULT_IMPLEMENTATION_LABEL = "Secure Hub";
    public static final String SHARED_PREFERENCES_DISCOVERY = "ctxmdx_discovery";
    public static final String SHARED_PREFERENCES_PKGNAME = "pkgName";
    public static final String TAG = "MDXDiscovery";
    public static final String URI_PATH_GET_MIGRATION_COMPLETED = "migrationCompleted";
    public static final String URI_SCHEME = "citrix";
    public static boolean bDiscovered;
    public static MDXImplementation discoveredProvider;
    public static MDXImplementation[] implementations = {new MDXImplementation("Receiver", "com.citrix.receiver.android", receiverHashes, "com.citrix.work.MAM.AuthenticateOffline", "com.citrix.work.MAM.AuthenticateToStore", "com.citrix.work.MAM.MDXProvider", "com.citrix.work.v2.MDXProvider", "com.citrix.work.MAM.MAMProvider", "com.citrix.work.v2.MAMProvider", "com.citrix.work.v2.MDXProvider", false), new MDXImplementation("ReceiverX1", "com.citrix.receiver.android", receiverHashes, "com.citrix.work.MAM.AuthenticateOffline", "com.citrix.work.MAM.AuthenticateToStore", "com.citrix.work.MAM.MDXProvider", "com.citrix.work.v2.MDXProvider;v2.mdxmdm", "com.citrix.work.MAM.MAMProvider", "com.citrix.work.v2.MAMProvider", "com.citrix.work.v2.MDXProvider", false), new MDXImplementation(DEFAULT_IMPLEMENTATION_LABEL, "com.zenprise", meatworkHashes, "com.citrix.work.MAM.AuthenticateOffline", "com.citrix.work.MAM.AuthenticateToStore", "com.citrix.work.MAM.MDXProvider", "mdxmdm;com.citrix.work.MDXProvider", "com.citrix.work.MAM.MAMProvider", "com.citrix.work.MAMProvider", MDXConstants.CONTENT_PROVIDER, true), new MDXImplementation(DEFAULT_IMPLEMENTATION_LABEL, "com.zenprise", meatworkHashes, "com.citrix.work.MAM.AuthenticateOffline", "com.citrix.work.MAM.AuthenticateToStore", "com.citrix.work.MAM.MDXProvider", "com.citrix.work.MDXProvider;mdxmdm", "com.citrix.work.MAM.MAMProvider", "com.citrix.work.MAMProvider", MDXConstants.URI_AUTHORITY, false), new MDXImplementation("Secure Hub - Samsung", "com.zenprise.samsung", meatworkHashes, "com.citrix.work.MAM.AuthenticateOffline", "com.citrix.work.MAM.AuthenticateToStore", "com.citrix.work.MAM.MDXProvider", MDXConstants.CONTENT_PROVIDER, "com.citrix.work.MAM.MAMProvider", "com.citrix.work.MAMProvider", MDXConstants.URI_AUTHORITY, false)};
    public static int[] meatworkHashes = {CitrixCertHash_WorxHome_production, CitrixCertHash_droid, CitrixCertHash_platform_x509_pem, CitrixCertHash_androiddebugkey, CitrixCertHash_androiddebugkey2};
    public static int[] receiverHashes = {CitrixCertHash_Receiver_production, CitrixCertHash_droid, CitrixCertHash_platform_x509_pem, CitrixCertHash_androiddebugkey, CitrixCertHash_androiddebugkey2};

    static {
        int i = 0;
        discoveredProvider = null;
        MDXImplementation[] mDXImplementationArr = implementations;
        int length = mDXImplementationArr.length;
        while (true) {
            if (i >= length) {
                break;
            }
            MDXImplementation mDXImplementation = mDXImplementationArr[i];
            if (mDXImplementation.label.equals(DEFAULT_IMPLEMENTATION_LABEL)) {
                discoveredProvider = mDXImplementation;
                break;
            }
            i++;
        }
        if (discoveredProvider == null) {
            Log.w(TAG, "Failed to find default provider implementation = Secure Hub");
        }
    }

    public static boolean confirmOrMigrateProvider(Context context) {
        if (!bDiscovered) {
            recoverOrDiscoverProvider(context);
        }
        rebind(context);
        return validateMDXProvider(context, discoveredProvider);
    }

    public static MDXImplementation forceImplementation(MDXImplementation mDXImplementation) {
        MDXImplementation mDXImplementation2 = discoveredProvider;
        discoveredProvider = mDXImplementation;
        return mDXImplementation2;
    }

    public static String getMAMAuthority() {
        return discoveredProvider.mamAuth;
    }

    public static String getMDXAuthority() {
        return discoveredProvider.mdxAuth;
    }

    public static Intent getOfflineIntent() {
        Intent intent = new Intent();
        MDXImplementation mDXImplementation = discoveredProvider;
        intent.setClassName(mDXImplementation.pkgName, mDXImplementation.offline);
        return intent;
    }

    public static Intent getOnlineIntent() {
        Intent intent = new Intent();
        MDXImplementation mDXImplementation = discoveredProvider;
        intent.setClassName(mDXImplementation.pkgName, mDXImplementation.online);
        return intent;
    }

    public static String getProvider() {
        return discoveredProvider.pkgName;
    }

    public static String getProviderLabel() {
        return discoveredProvider.label;
    }

    public static String getUriAuthority() {
        return discoveredProvider.uriAuth;
    }

    public static boolean isImplementationInstalled(Context context, MDXImplementation mDXImplementation) {
        try {
            ProviderInfo[] providerInfoArr = context.getPackageManager().getPackageInfo(mDXImplementation.pkgName, 8).providers;
            if (providerInfoArr == null) {
                return false;
            }
            int length = providerInfoArr.length;
            int i = 0;
            while (i < length) {
                ProviderInfo providerInfo = providerInfoArr[i];
                if (!providerInfo.name.equals(mDXImplementation.mdxClass) || !providerInfo.authority.equals(mDXImplementation.mdxAuth)) {
                    i++;
                } else if (!validateMDXProvider(context, mDXImplementation)) {
                    return false;
                } else {
                    discoveredProvider = mDXImplementation;
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean isMigrationCompleted(Context context) {
        String uriAuthority = getUriAuthority();
        ContentProviderClient acquireContentProviderClient = context.getContentResolver().acquireContentProviderClient(uriAuthority);
        boolean z = false;
        if (acquireContentProviderClient != null) {
            try {
                Uri.Builder builder = new Uri.Builder();
                builder.authority(uriAuthority);
                builder.scheme("citrix");
                builder.path("migrationCompleted");
                Cursor query = acquireContentProviderClient.query(builder.build(), (String[]) null, (String) null, (String[]) null, (String) null);
                if (query.moveToFirst() && query.getInt(query.getColumnIndex("migrationCompleted")) != 0) {
                    z = true;
                }
                query.close();
            } catch (RemoteException | NullPointerException unused) {
            }
            acquireContentProviderClient.release();
        }
        return z;
    }

    public static boolean rebind(Context context) {
        return false;
    }

    public static boolean recoverOrDiscoverProvider(Context context) {
        if (recoverProvider(context)) {
            bDiscovered = isImplementationInstalled(context, discoveredProvider);
        }
        if (!bDiscovered) {
            MDXImplementation[] mDXImplementationArr = implementations;
            int length = mDXImplementationArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (isImplementationInstalled(context, mDXImplementationArr[i])) {
                    bDiscovered = true;
                    saveProvider(context);
                    break;
                } else {
                    i++;
                }
            }
        }
        return bDiscovered;
    }

    public static boolean recoverProvider(Context context) {
        try {
            String string = context.getSharedPreferences(SHARED_PREFERENCES_DISCOVERY, 4).getString("pkgName", (String) null);
            if (string == null) {
                return false;
            }
            "recovered provider = ".concat(string);
            return setMDXProvider(context, string);
        } catch (ClassCastException unused) {
            Log.e(TAG, "Invalid value for ctxmdx_discovery.pkgName");
            return false;
        }
    }

    public static void saveProvider(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(SHARED_PREFERENCES_DISCOVERY, 4).edit();
        edit.putString("pkgName", discoveredProvider.label);
        edit.commit();
    }

    public static boolean setMDXProvider(Context context, String str) {
        MDXImplementation[] mDXImplementationArr = implementations;
        int length = mDXImplementationArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            MDXImplementation mDXImplementation = mDXImplementationArr[i];
            if (mDXImplementation.label.equals(str)) {
                bDiscovered = true;
                discoveredProvider = mDXImplementation;
                break;
            }
            i++;
        }
        if (!bDiscovered) {
            Log.w(TAG, "Failed to find MDX Provider = ".concat(String.valueOf(str)));
        }
        return bDiscovered;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0063  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean validateMDXProvider(android.content.Context r10, com.citrix.mdx.common.MDXImplementation r11) {
        /*
            r0 = 1
            android.content.pm.PackageManager r10 = r10.getPackageManager()     // Catch:{ NameNotFoundException | CertificateException -> 0x0050 }
            java.lang.String r1 = r11.pkgName     // Catch:{ NameNotFoundException | CertificateException -> 0x0050 }
            r2 = 64
            android.content.pm.PackageInfo r10 = r10.getPackageInfo(r1, r2)     // Catch:{ NameNotFoundException | CertificateException -> 0x0050 }
            android.content.pm.Signature[] r10 = r10.signatures     // Catch:{ NameNotFoundException | CertificateException -> 0x0050 }
            java.lang.String r1 = "X509"
            java.security.cert.CertificateFactory r1 = java.security.cert.CertificateFactory.getInstance(r1)     // Catch:{ NameNotFoundException | CertificateException -> 0x0051 }
            int r2 = r10.length     // Catch:{ NameNotFoundException | CertificateException -> 0x0051 }
            r3 = 0
            r4 = 0
        L_0x0018:
            if (r3 >= r2) goto L_0x0052
            r5 = r10[r3]     // Catch:{ NameNotFoundException | CertificateException -> 0x004e }
            byte[] r5 = r5.toByteArray()     // Catch:{ NameNotFoundException | CertificateException -> 0x004e }
            java.io.ByteArrayInputStream r6 = new java.io.ByteArrayInputStream     // Catch:{ NameNotFoundException | CertificateException -> 0x004e }
            r6.<init>(r5)     // Catch:{ NameNotFoundException | CertificateException -> 0x004e }
            java.security.cert.Certificate r5 = r1.generateCertificate(r6)     // Catch:{ CertificateException -> 0x004b }
            java.security.cert.X509Certificate r5 = (java.security.cert.X509Certificate) r5     // Catch:{ CertificateException -> 0x004b }
            byte[] r5 = r5.getEncoded()     // Catch:{ CertificateException -> 0x004b }
            r6 = 0
            r7 = 0
        L_0x0031:
            int r8 = r5.length     // Catch:{ CertificateException -> 0x004b }
            if (r6 >= r8) goto L_0x003c
            byte r8 = r5[r6]     // Catch:{ CertificateException -> 0x004b }
            int r8 = r8 * r6
            int r7 = r7 + r8
            int r6 = r6 + 1
            goto L_0x0031
        L_0x003c:
            int[] r5 = r11.hashes     // Catch:{ CertificateException -> 0x004b }
            int r6 = r5.length     // Catch:{ CertificateException -> 0x004b }
            r8 = 0
        L_0x0040:
            if (r8 >= r6) goto L_0x004b
            r9 = r5[r8]     // Catch:{ CertificateException -> 0x004b }
            if (r9 != r7) goto L_0x0048
            r4 = 1
            goto L_0x004b
        L_0x0048:
            int r8 = r8 + 1
            goto L_0x0040
        L_0x004b:
            int r3 = r3 + 1
            goto L_0x0018
        L_0x004e:
            goto L_0x0052
        L_0x0050:
            r0 = 0
        L_0x0051:
            r4 = 0
        L_0x0052:
            java.lang.String r10 = " as a valid Citrix product"
            if (r4 == 0) goto L_0x0063
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Validated "
            r0.<init>(r1)
            java.lang.String r11 = r11.pkgName
            Eo.b(r0, r11, r10)
            goto L_0x007d
        L_0x0063:
            if (r0 == 0) goto L_0x007d
            java.lang.String r0 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Failed to validate "
            r1.<init>(r2)
            java.lang.String r11 = r11.pkgName
            r1.append(r11)
            r1.append(r10)
            java.lang.String r10 = r1.toString()
            android.util.Log.w(r0, r10)
        L_0x007d:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.citrix.mdx.common.MDXDiscovery.validateMDXProvider(android.content.Context, com.citrix.mdx.common.MDXImplementation):boolean");
    }
}
