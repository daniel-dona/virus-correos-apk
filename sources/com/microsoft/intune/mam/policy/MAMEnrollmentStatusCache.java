package com.microsoft.intune.mam.policy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.microsoft.intune.mam.client.app.startup.ADALConnectionDetails;
import com.microsoft.intune.mam.log.MAMLogPIIFactory;
import com.microsoft.intune.mam.policy.MAMEnrollmentManager;
import com.microsoft.intune.mam.policy.MAMServiceLookupThread;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"ApplySharedPref"})
/* compiled from: PG */
public class MAMEnrollmentStatusCache {
    public static final String JSON_KEY_RESULT_CODE = "code";
    public static final String JSON_KEY_RESULT_TIMESTAMP = "timestamp";
    public static final String KEY_ADAL_DETAILS_PREFIX = "adaldetails_";
    public static final String KEY_COMPANY_PORTAL_REQUIRED = "companyportalrequired";
    public static final String KEY_CURRENT_CERT_VERSION = "currentcertversion";
    public static final String KEY_IDENTITY = "identity";
    public static final String KEY_IMPLICIT_WIPE_HAPPENED = "implicitwipehappened";
    public static final String KEY_MAMSERVICE_URL = "mamserviceurl";
    public static final String KEY_MAMSERVICE_URL_IDENTITY = "mamserviceurlidentity";
    public static final String KEY_MAMSERVICE_URL_PREFIX = "mamserviceurl_";
    public static final String KEY_MAMSERVICE_URL_REQUERY_INTERVAL = "mamserviceurlrequeryinterval";
    public static final String KEY_MAMSERVICE_URL_TIMESTAMP = "mamserviceurltime";
    public static final String KEY_OFFLINE_ENROLLMENT_RESULTS = "offlineenrollmentresults";
    public static final String KEY_SYSTEM_WIPE = "requiresystemwipe";
    public static final String KEY_WAS_MANAGED = "wasmanaged";
    public static final int LATEST_CERT_VERSION = 1;
    public static final Ld0 LOGGER = Md0.a(MAMEnrollmentStatusCache.class);
    public static final String SHARED_PREFS_NAME = "com.microsoft.intune.mam.enrollmentStatus";
    public static final String STATUS_NOT_AVAILABLE = "NOT AVAILABLE";
    public HashMap<String, C0346a> mCachedResults;
    public final Context mContext;
    public final MAMLogPIIFactory mMAMLogPIIFactory;
    public final gd0 mThreadIdentityOperations;

    /* renamed from: com.microsoft.intune.mam.policy.MAMEnrollmentStatusCache$a */
    /* compiled from: PG */
    public class C0346a {

        /* renamed from: a */
        public long f1265a;

        /* renamed from: b */
        public int f1266b;

        public C0346a(MAMEnrollmentStatusCache mAMEnrollmentStatusCache, long j, MAMEnrollmentManager.Result result) {
            this.f1265a = j;
            this.f1266b = result.getCode();
        }
    }

    public MAMEnrollmentStatusCache(Context context, MAMLogPIIFactory mAMLogPIIFactory, gd0 gd0) {
        this.mContext = context;
        this.mMAMLogPIIFactory = mAMLogPIIFactory;
        this.mThreadIdentityOperations = gd0;
    }

    private boolean clearCacheAndSetNotice(String str) {
        this.mThreadIdentityOperations.b();
        try {
            return getEditor().clear().putBoolean(str, true).commit();
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    private synchronized void clearCachedEnrollmentResults() {
        this.mThreadIdentityOperations.b();
        try {
            getEditor().remove(KEY_OFFLINE_ENROLLMENT_RESULTS).commit();
            this.mCachedResults = new HashMap<>();
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    private void deleteMAMServiceUrls(SharedPreferences.Editor editor) {
        for (String remove : getMamServiceUrlKeys()) {
            editor.remove(remove);
        }
    }

    private SharedPreferences.Editor getEditor() {
        return getPrefs().edit();
    }

    private Set<String> getMamServiceUrlKeys() {
        HashSet hashSet = new HashSet();
        for (String next : getPrefs().getAll().keySet()) {
            if (next.startsWith(KEY_MAMSERVICE_URL_PREFIX)) {
                hashSet.add(next);
            }
        }
        return hashSet;
    }

    private String getMamServiceUrlMapKey(String str) {
        if (!str.startsWith(KEY_MAMSERVICE_URL_PREFIX)) {
            return null;
        }
        String substring = str.substring(14);
        if (substring.isEmpty()) {
            return null;
        }
        return substring;
    }

    private String getMamServiceUrlPrefsKey(String str) {
        return Eo.a(KEY_MAMSERVICE_URL_PREFIX, str);
    }

    private SharedPreferences getPrefs() {
        return this.mContext.getSharedPreferences(SHARED_PREFS_NAME, 0);
    }

    private synchronized void loadCachedResults() {
        if (this.mCachedResults == null) {
            this.mThreadIdentityOperations.b();
            try {
                String string = getPrefs().getString(KEY_OFFLINE_ENROLLMENT_RESULTS, (String) null);
                this.mCachedResults = new HashMap<>();
                if (string == null) {
                    this.mThreadIdentityOperations.a();
                    return;
                }
                JSONObject jSONObject = new JSONObject(string);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                    this.mCachedResults.put(next, new C0346a(this, jSONObject2.getLong(JSON_KEY_RESULT_TIMESTAMP), MAMEnrollmentManager.Result.fromCode(jSONObject2.getInt("code"))));
                }
                this.mThreadIdentityOperations.a();
            } catch (JSONException e) {
                try {
                    LOGGER.a(Level.WARNING, "Somehow the cached offline enrollment results became corrupt. Clearing them.", e);
                    clearCachedEnrollmentResults();
                } catch (Throwable th) {
                    this.mThreadIdentityOperations.a();
                    throw th;
                }
            }
        }
    }

    private synchronized void storeCachedResults() {
        this.mThreadIdentityOperations.b();
        try {
            JSONObject jSONObject = new JSONObject();
            for (String next : this.mCachedResults.keySet()) {
                JSONObject jSONObject2 = new JSONObject();
                C0346a aVar = this.mCachedResults.get(next);
                jSONObject2.put("code", aVar.f1266b);
                jSONObject2.put(JSON_KEY_RESULT_TIMESTAMP, aVar.f1265a);
                jSONObject.put(next, jSONObject2);
            }
            SharedPreferences.Editor editor = getEditor();
            editor.putString(KEY_OFFLINE_ENROLLMENT_RESULTS, jSONObject.toString());
            editor.commit();
            this.mThreadIdentityOperations.a();
        } catch (JSONException e) {
            try {
                LOGGER.a(Level.SEVERE, "Unable to construct json to store enrollment result", e);
            } finally {
                this.mThreadIdentityOperations.a();
            }
        }
    }

    public void clear() {
        this.mThreadIdentityOperations.b();
        try {
            if (getEditor().clear().commit()) {
                LOGGER.c("Cleared MAM enrollment cache");
            } else {
                LOGGER.c("Failed to clear enrollment status cache.");
            }
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public void clearCacheAndSetImplicitWipeNotice() {
        if (clearCacheAndSetNotice(KEY_IMPLICIT_WIPE_HAPPENED)) {
            LOGGER.c("Cleared MAM enrollment status cache and set implicit wipe notice.");
        } else {
            LOGGER.c("Failed to clear MAM enrollment status cache and set implicit wipe notice.");
        }
    }

    public void clearCacheAndSetSystemWipeNotice() {
        if (clearCacheAndSetNotice(KEY_SYSTEM_WIPE)) {
            LOGGER.c("Cleared MAM enrollment status cache and set system wipe notice.");
        } else {
            LOGGER.c("Failed to clear MAM enrollment status cache and set system wipe notice.");
        }
    }

    public void clearCompanyPortalRequired() {
        this.mThreadIdentityOperations.b();
        try {
            LOGGER.c("Clearing Company Portal required.");
            SharedPreferences.Editor editor = getEditor();
            editor.remove(KEY_COMPANY_PORTAL_REQUIRED);
            editor.commit();
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public void clearEnrolledIdentity(String str) {
        this.mThreadIdentityOperations.b();
        try {
            SharedPreferences prefs = getPrefs();
            String string = prefs.getString(KEY_IDENTITY, (String) null);
            if (string != null && string.equalsIgnoreCase(str)) {
                LOGGER.a("Clearing MAM enrollment status for identity {0}", this.mMAMLogPIIFactory.getPIIUPN(str));
                SharedPreferences.Editor edit = prefs.edit();
                edit.remove(KEY_IDENTITY);
                edit.remove(KEY_WAS_MANAGED);
                edit.commit();
                clearCompanyPortalRequired();
            }
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public void clearImplicitWipeNotice() {
        this.mThreadIdentityOperations.b();
        try {
            SharedPreferences prefs = getPrefs();
            if (prefs.getBoolean(KEY_IMPLICIT_WIPE_HAPPENED, false)) {
                LOGGER.c("Clear Implicit Wipe Type");
                SharedPreferences.Editor edit = prefs.edit();
                edit.remove(KEY_IMPLICIT_WIPE_HAPPENED);
                edit.commit();
            }
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public void clearMAMServiceUrls() {
        this.mThreadIdentityOperations.b();
        try {
            LOGGER.c("Clearing cached MAM service URLs");
            SharedPreferences.Editor editor = getEditor();
            deleteMAMServiceUrls(editor);
            editor.remove(KEY_MAMSERVICE_URL_IDENTITY);
            editor.remove(KEY_MAMSERVICE_URL_TIMESTAMP);
            editor.remove(KEY_MAMSERVICE_URL_REQUERY_INTERVAL);
            editor.commit();
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public ADALConnectionDetails getADALConnectionDetails(String str) {
        this.mThreadIdentityOperations.b();
        try {
            SharedPreferences prefs = getPrefs();
            String string = prefs.getString(KEY_ADAL_DETAILS_PREFIX + str.toLowerCase(Locale.getDefault()), (String) null);
            if (string == null) {
                return null;
            }
            ADALConnectionDetails a = ADALConnectionDetails.a(string);
            this.mThreadIdentityOperations.a();
            return a;
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public String getEnrolledIdentity() {
        this.mThreadIdentityOperations.b();
        try {
            String string = getPrefs().getString(KEY_IDENTITY, (String) null);
            if (string == null) {
                LOGGER.c("No MAM enrollment status found.");
            } else {
                LOGGER.a("MAM enrollment status found for identity {0}", this.mMAMLogPIIFactory.getPIIUPN(string));
            }
            return string;
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public boolean getImplicitWipeNotice() {
        this.mThreadIdentityOperations.b();
        try {
            return getPrefs().getBoolean(KEY_IMPLICIT_WIPE_HAPPENED, false);
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004e, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String getLatestOfflineEnrollmentResult() {
        /*
            r8 = this;
            monitor-enter(r8)
            r8.loadCachedResults()     // Catch:{ all -> 0x0053 }
            java.util.HashMap<java.lang.String, com.microsoft.intune.mam.policy.MAMEnrollmentStatusCache$a> r0 = r8.mCachedResults     // Catch:{ all -> 0x0053 }
            if (r0 == 0) goto L_0x004f
            java.util.HashMap<java.lang.String, com.microsoft.intune.mam.policy.MAMEnrollmentStatusCache$a> r0 = r8.mCachedResults     // Catch:{ all -> 0x0053 }
            int r0 = r0.size()     // Catch:{ all -> 0x0053 }
            if (r0 != 0) goto L_0x0011
            goto L_0x004f
        L_0x0011:
            r0 = 0
            r1 = 0
            java.util.HashMap<java.lang.String, com.microsoft.intune.mam.policy.MAMEnrollmentStatusCache$a> r3 = r8.mCachedResults     // Catch:{ all -> 0x0053 }
            java.util.Set r3 = r3.entrySet()     // Catch:{ all -> 0x0053 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0053 }
        L_0x001e:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x0053 }
            if (r4 == 0) goto L_0x0044
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x0053 }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ all -> 0x0053 }
            java.lang.Object r5 = r4.getValue()     // Catch:{ all -> 0x0053 }
            com.microsoft.intune.mam.policy.MAMEnrollmentStatusCache$a r5 = (com.microsoft.intune.mam.policy.MAMEnrollmentStatusCache.C0346a) r5     // Catch:{ all -> 0x0053 }
            long r5 = r5.f1265a     // Catch:{ all -> 0x0053 }
            int r7 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r7 <= 0) goto L_0x001e
            java.lang.Object r0 = r4.getValue()     // Catch:{ all -> 0x0053 }
            com.microsoft.intune.mam.policy.MAMEnrollmentStatusCache$a r0 = (com.microsoft.intune.mam.policy.MAMEnrollmentStatusCache.C0346a) r0     // Catch:{ all -> 0x0053 }
            int r0 = r0.f1266b     // Catch:{ all -> 0x0053 }
            com.microsoft.intune.mam.policy.MAMEnrollmentManager$Result r0 = com.microsoft.intune.mam.policy.MAMEnrollmentManager.Result.fromCode(r0)     // Catch:{ all -> 0x0053 }
            r1 = r5
            goto L_0x001e
        L_0x0044:
            if (r0 != 0) goto L_0x0049
            java.lang.String r0 = "NOT AVAILABLE"
            goto L_0x004d
        L_0x0049:
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0053 }
        L_0x004d:
            monitor-exit(r8)
            return r0
        L_0x004f:
            java.lang.String r0 = "NOT AVAILABLE"
            monitor-exit(r8)
            return r0
        L_0x0053:
            r0 = move-exception
            monitor-exit(r8)
            goto L_0x0057
        L_0x0056:
            throw r0
        L_0x0057:
            goto L_0x0056
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.intune.mam.policy.MAMEnrollmentStatusCache.getLatestOfflineEnrollmentResult():java.lang.String");
    }

    public String getMAMServiceUrl() {
        this.mThreadIdentityOperations.b();
        try {
            SharedPreferences prefs = getPrefs();
            String string = prefs.getString(getMamServiceUrlPrefsKey("mam.api.application"), (String) null);
            if (string == null) {
                string = prefs.getString(KEY_MAMSERVICE_URL, (String) null);
            }
            return string;
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public String getMAMServiceUrlIdentity() {
        this.mThreadIdentityOperations.b();
        try {
            return getPrefs().getString(KEY_MAMSERVICE_URL_IDENTITY, (String) null);
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public long getMAMServiceUrlRequeryInterval() {
        this.mThreadIdentityOperations.b();
        try {
            return getPrefs().getLong(KEY_MAMSERVICE_URL_REQUERY_INTERVAL, MAMServiceLookupThread.Operations.DEFAULT_REQUERY_INTERVAL_MS);
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public long getMAMServiceUrlTimestamp() {
        this.mThreadIdentityOperations.b();
        try {
            return getPrefs().getLong(KEY_MAMSERVICE_URL_TIMESTAMP, 0);
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public Map<String, String> getMAMServiceUrls() {
        String string;
        String mamServiceUrlMapKey;
        this.mThreadIdentityOperations.b();
        try {
            SharedPreferences prefs = getPrefs();
            if (prefs.getInt(KEY_CURRENT_CERT_VERSION, 0) < 1) {
                LOGGER.c("Cached MAM Service URLs are not using latest certs, clearing them.");
                clearMAMServiceUrls();
            } else {
                HashMap hashMap = new HashMap();
                for (String next : getMamServiceUrlKeys()) {
                    String string2 = prefs.getString(next, (String) null);
                    if (!(string2 == null || (mamServiceUrlMapKey = getMamServiceUrlMapKey(next)) == null)) {
                        hashMap.put(mamServiceUrlMapKey, string2);
                    }
                }
                if (!hashMap.containsKey("mam.api.application") && (string = prefs.getString(KEY_MAMSERVICE_URL, (String) null)) != null) {
                    hashMap.put("mam.api.application", string);
                }
                if (!hashMap.isEmpty()) {
                    this.mThreadIdentityOperations.a();
                    return hashMap;
                }
            }
            return null;
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public synchronized MAMEnrollmentManager.Result getOfflineEnrollmentResult(String str) {
        loadCachedResults();
        C0346a aVar = this.mCachedResults.get(str.toLowerCase(Locale.getDefault()));
        if (aVar == null) {
            return null;
        }
        return MAMEnrollmentManager.Result.fromCode(aVar.f1266b);
    }

    public synchronized long getOfflineEnrollmentTimestamp(String str) {
        loadCachedResults();
        C0346a aVar = this.mCachedResults.get(str.toLowerCase(Locale.getDefault()));
        if (aVar == null) {
            return 0;
        }
        return aVar.f1265a;
    }

    public boolean getSystemWipeNotice() {
        this.mThreadIdentityOperations.b();
        try {
            return getPrefs().getBoolean(KEY_SYSTEM_WIPE, false);
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public boolean getWasManaged() {
        this.mThreadIdentityOperations.b();
        try {
            boolean z = getPrefs().getBoolean(KEY_WAS_MANAGED, false);
            Ld0 ld0 = LOGGER;
            ld0.c("app was managed: " + String.valueOf(z));
            return z;
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public boolean isCompanyPortalRequired() {
        this.mThreadIdentityOperations.b();
        try {
            return getPrefs().getBoolean(KEY_COMPANY_PORTAL_REQUIRED, false);
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public void setADALConnectionDetails(String str, ADALConnectionDetails aDALConnectionDetails) {
        StringBuilder a = Eo.a(KEY_ADAL_DETAILS_PREFIX);
        a.append(str.toLowerCase(Locale.getDefault()));
        String sb = a.toString();
        this.mThreadIdentityOperations.b();
        try {
            String b = aDALConnectionDetails.b();
            Ld0 ld0 = LOGGER;
            ld0.c("Recording ADAL connection details: " + b);
            SharedPreferences.Editor edit = getPrefs().edit();
            edit.putString(sb, b);
            edit.commit();
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public void setCompanyPortalRequired() {
        this.mThreadIdentityOperations.b();
        try {
            LOGGER.c("Setting Company Portal required");
            SharedPreferences.Editor editor = getEditor();
            editor.putBoolean(KEY_COMPANY_PORTAL_REQUIRED, true);
            editor.commit();
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public void setEnrolledIdentity(String str, boolean z) {
        this.mThreadIdentityOperations.b();
        try {
            LOGGER.a("Recording MAM enrollment for identity {0}, isManaged: {1}", new Object[]{this.mMAMLogPIIFactory.getPIIUPN(str), String.valueOf(z)});
            SharedPreferences.Editor editor = getEditor();
            editor.putString(KEY_IDENTITY, str);
            editor.putBoolean(KEY_WAS_MANAGED, z);
            editor.commit();
            clearCompanyPortalRequired();
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public void setImplicitWipeNotice() {
        this.mThreadIdentityOperations.b();
        try {
            SharedPreferences prefs = getPrefs();
            if (!prefs.getBoolean(KEY_IMPLICIT_WIPE_HAPPENED, false)) {
                LOGGER.c("Implicit wipe just happened and need to notify user");
                SharedPreferences.Editor edit = prefs.edit();
                edit.putBoolean(KEY_IMPLICIT_WIPE_HAPPENED, true);
                edit.commit();
            }
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public void setMAMServiceUrls(String str, Map<String, String> map, long j) {
        this.mThreadIdentityOperations.b();
        try {
            SharedPreferences.Editor editor = getEditor();
            deleteMAMServiceUrls(editor);
            if (map != null) {
                for (String next : map.keySet()) {
                    String str2 = map.get(next);
                    LOGGER.a("Recording MAM service URL: {0}: {1} for: {2}", new Object[]{next, str2, this.mMAMLogPIIFactory.getPIIUPN(str)});
                    editor.putString(getMamServiceUrlPrefsKey(next), str2);
                }
            }
            editor.putString(KEY_MAMSERVICE_URL_IDENTITY, str);
            editor.putLong(KEY_MAMSERVICE_URL_TIMESTAMP, System.currentTimeMillis());
            editor.putLong(KEY_MAMSERVICE_URL_REQUERY_INTERVAL, j);
            editor.putInt(KEY_CURRENT_CERT_VERSION, 1);
            editor.commit();
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public synchronized void setOfflineEnrollmentResult(String str, MAMEnrollmentManager.Result result, long j) {
        LOGGER.a("Recording offline MAM enrollment result: {0} for identity {1}", new Object[]{result.toString(), this.mMAMLogPIIFactory.getPIIUPN(str)});
        C0346a aVar = new C0346a(this, System.currentTimeMillis(), result);
        if (this.mCachedResults == null) {
            this.mCachedResults = new HashMap<>();
        }
        long currentTimeMillis = System.currentTimeMillis() - j;
        Iterator<Map.Entry<String, C0346a>> it = this.mCachedResults.entrySet().iterator();
        while (it.hasNext()) {
            if (((C0346a) it.next().getValue()).f1265a < currentTimeMillis) {
                it.remove();
            }
        }
        this.mCachedResults.put(str.toLowerCase(Locale.getDefault()), aVar);
        storeCachedResults();
    }

    public void setSystemWipeNotice() {
        this.mThreadIdentityOperations.b();
        try {
            SharedPreferences prefs = getPrefs();
            if (!prefs.getBoolean(KEY_SYSTEM_WIPE, false)) {
                LOGGER.c("Setting flag for System Wipe Notification.");
                SharedPreferences.Editor edit = prefs.edit();
                edit.putBoolean(KEY_SYSTEM_WIPE, true);
                edit.commit();
            }
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }

    public void setWasManaged() {
        this.mThreadIdentityOperations.b();
        try {
            SharedPreferences prefs = getPrefs();
            if (!prefs.getBoolean(KEY_WAS_MANAGED, false)) {
                LOGGER.c("Recording transition from unmanaged to managed.");
                SharedPreferences.Editor edit = prefs.edit();
                edit.putBoolean(KEY_WAS_MANAGED, true);
                edit.commit();
            }
        } finally {
            this.mThreadIdentityOperations.a();
        }
    }
}
