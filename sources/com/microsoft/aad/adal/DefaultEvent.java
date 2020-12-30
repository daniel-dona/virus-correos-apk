package com.microsoft.aad.adal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Pair;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
public class DefaultEvent implements IEvents {
    public static final int EVENT_LIST_SIZE = 30;
    public static String sApplicationName = null;
    public static String sApplicationVersion = "NA";
    public static String sClientId = "NA";
    public static String sDeviceId = "NA";
    public int mDefaultEventCount;
    public final List<Pair<String, String>> mEventList = new ArrayList(30);
    public String mRequestId;

    public DefaultEvent() {
        String str = sApplicationName;
        if (str != null) {
            setProperty("Microsoft.ADAL.application_name", str);
            setProperty("Microsoft.ADAL.application_version", sApplicationVersion);
            setProperty("Microsoft.ADAL.client_id", sClientId);
            setProperty("Microsoft.ADAL.device_id", sDeviceId);
            this.mDefaultEventCount = this.mEventList.size();
        }
    }

    public static boolean isPrivacyCompliant(String str) {
        return C0336Telemetry.getAllowPii() || !TelemetryUtils.GDPR_FILTERED_FIELDS.contains(str);
    }

    public int getDefaultEventCount() {
        return this.mDefaultEventCount;
    }

    public List<Pair<String, String>> getEventList() {
        return this.mEventList;
    }

    public List<Pair<String, String>> getEvents() {
        return Collections.unmodifiableList(this.mEventList);
    }

    public String getTelemetryRequestId() {
        return this.mRequestId;
    }

    public void processEvent(Map<String, String> map) {
        if (sApplicationName != null && isPrivacyCompliant("Microsoft.ADAL.application_name")) {
            map.put("Microsoft.ADAL.application_name", sApplicationName);
        }
        if (sApplicationVersion != null && isPrivacyCompliant("Microsoft.ADAL.application_version")) {
            map.put("Microsoft.ADAL.application_version", sApplicationVersion);
        }
        if (sClientId != null && isPrivacyCompliant("Microsoft.ADAL.client_id")) {
            map.put("Microsoft.ADAL.client_id", sClientId);
        }
        if (sDeviceId != null && isPrivacyCompliant("Microsoft.ADAL.device_id")) {
            map.put("Microsoft.ADAL.device_id", sDeviceId);
        }
    }

    public void setCorrelationId(String str) {
        this.mEventList.add(0, new Pair("Microsoft.ADAL.correlation_id", str));
        this.mDefaultEventCount++;
    }

    @SuppressLint({"HardwareIds"})
    public void setDefaults(Context context, String str) {
        sClientId = str;
        sApplicationName = context.getPackageName();
        try {
            sApplicationVersion = context.getPackageManager().getPackageInfo(sApplicationName, 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            sApplicationVersion = "NA";
        }
        try {
            sDeviceId = StringExtensions.createHash(Settings.Secure.getString(context.getContentResolver(), "android_id"));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException unused2) {
            sDeviceId = BuildConfig.FLAVOR;
        }
        if (this.mDefaultEventCount == 0) {
            setProperty("Microsoft.ADAL.application_name", sApplicationName);
            setProperty("Microsoft.ADAL.application_version", sApplicationVersion);
            setProperty("Microsoft.ADAL.client_id", sClientId);
            setProperty("Microsoft.ADAL.device_id", sDeviceId);
            this.mDefaultEventCount = this.mEventList.size();
        }
    }

    public void setProperty(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Telemetry setProperty on null name");
        } else if (str2 != null && isPrivacyCompliant(str)) {
            this.mEventList.add(Pair.create(str, str2));
        }
    }

    public void setRequestId(String str) {
        this.mRequestId = str;
        this.mEventList.add(0, new Pair("Microsoft.ADAL.request_id", str));
        this.mDefaultEventCount++;
    }
}
