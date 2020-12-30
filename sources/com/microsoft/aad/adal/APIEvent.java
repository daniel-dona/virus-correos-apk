package com.microsoft.aad.adal;

import android.content.Context;
import android.util.Pair;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/* compiled from: PG */
public final class APIEvent extends DefaultEvent {
    public static final String TAG = DefaultEvent.class.getSimpleName();
    public final String mEventName;

    public APIEvent(String str) {
        setProperty("Microsoft.ADAL.event_name", str);
        this.mEventName = str;
    }

    public String getEventName() {
        return this.mEventName;
    }

    public void processEvent(Map<String, String> map) {
        super.processEvent(map);
        for (Pair next : getEventList()) {
            String str = (String) next.first;
            if (str.equals("Microsoft.ADAL.authority_type") || str.equals("Microsoft.ADAL.is_deprecated") || str.equals("Microsoft.ADAL.authority_validation_status") || str.equals("Microsoft.ADAL.extended_expires_on_setting") || str.equals("Microsoft.ADAL.prompt_behavior") || str.equals("Microsoft.ADAL.is_successful") || str.equals("Microsoft.ADAL.idp") || str.equals("Microsoft.ADAL.tenant_id") || str.equals("Microsoft.ADAL.user_id") || str.equals("Microsoft.ADAL.login_hint") || str.equals("Microsoft.ADAL.response_time") || str.equals("Microsoft.ADAL.correlation_id") || str.equals("Microsoft.ADAL.request_id") || str.equals("Microsoft.ADAL.api_id") || str.equals("Microsoft.ADAL.api_error_code") || str.equals("Microsoft.ADAL.server_error_code") || str.equals("Microsoft.ADAL.server_sub_error_code") || str.equals("Microsoft.ADAL.rt_age") || str.equals("Microsoft.ADAL.spe_info")) {
                map.put(str, next.second);
            }
        }
    }

    public void setAPIId(String str) {
        setProperty("Microsoft.ADAL.api_id", str);
    }

    public void setAuthority(String str) {
        if (!StringExtensions.isNullOrBlank(str)) {
            setProperty("Microsoft.ADAL.authority", str);
            URL url = StringExtensions.getUrl(str);
            if (url != null) {
                if (UrlExtensions.isADFSAuthority(url)) {
                    setAuthorityType("adfs");
                } else {
                    setAuthorityType("aad");
                }
            }
        }
    }

    public void setAuthorityType(String str) {
        setProperty("Microsoft.ADAL.authority_type", str);
    }

    public void setExtendedExpiresOnSetting(boolean z) {
        setProperty("Microsoft.ADAL.extended_expires_on_setting", String.valueOf(z));
    }

    public void setIdToken(String str) {
        if (!StringExtensions.isNullOrBlank(str)) {
            try {
                IdToken idToken = new IdToken(str);
                UserInfo userInfo = new UserInfo(idToken);
                setProperty("Microsoft.ADAL.idp", idToken.getIdentityProvider());
                try {
                    setProperty("Microsoft.ADAL.tenant_id", StringExtensions.createHash(idToken.getTenantId()));
                    setProperty("Microsoft.ADAL.user_id", StringExtensions.createHash(userInfo.getDisplayableId()));
                } catch (UnsupportedEncodingException | NoSuchAlgorithmException unused) {
                    Logger.m1248i(TAG + ":setIdToken", "Skipping TENANT_ID and USER_ID", BuildConfig.FLAVOR);
                }
            } catch (AuthenticationException unused2) {
            }
        }
    }

    public void setIsDeprecated(boolean z) {
        setProperty("Microsoft.ADAL.is_deprecated", String.valueOf(z));
    }

    public void setLoginHint(String str) {
        try {
            setProperty("Microsoft.ADAL.login_hint", StringExtensions.createHash(str));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException unused) {
            Logger.m1248i(TAG + ":setLoginHint", "Skipping telemetry for LOGIN_HINT", BuildConfig.FLAVOR);
        }
    }

    public void setPromptBehavior(String str) {
        setProperty("Microsoft.ADAL.prompt_behavior", str);
    }

    public void setRefreshTokenAge(String str) {
        if (!StringExtensions.isNullOrBlank(str)) {
            setProperty("Microsoft.ADAL.rt_age", str.trim());
        }
    }

    public void setServerErrorCode(String str) {
        if (str != null && !str.equals("0")) {
            setProperty("Microsoft.ADAL.server_error_code", str.trim());
        }
    }

    public void setServerSubErrorCode(String str) {
        if (str != null && !str.equals("0")) {
            setProperty("Microsoft.ADAL.server_sub_error_code", str.trim());
        }
    }

    public void setSpeRing(String str) {
        if (!StringExtensions.isNullOrBlank(str)) {
            setProperty("Microsoft.ADAL.spe_info", str.trim());
        }
    }

    public void setValidationStatus(String str) {
        setProperty("Microsoft.ADAL.authority_validation_status", str);
    }

    public void setWasApiCallSuccessful(boolean z, Exception exc) {
        setProperty("Microsoft.ADAL.is_successful", String.valueOf(z));
        if (exc != null && (exc instanceof AuthenticationException)) {
            setProperty("Microsoft.ADAL.api_error_code", ((AuthenticationException) exc).getCode().toString());
        }
    }

    public void stopTelemetryAndFlush() {
        C0336Telemetry.getInstance().stopEvent(getTelemetryRequestId(), this, getEventName());
        C0336Telemetry.getInstance().flush(getTelemetryRequestId());
    }

    public void setPromptBehavior(PromptBehavior promptBehavior) {
        if (promptBehavior != null) {
            setProperty("Microsoft.ADAL.prompt_behavior", promptBehavior.toString());
        }
    }

    public APIEvent(String str, Context context, String str2) {
        this(str);
        setDefaults(context, str2);
    }
}
