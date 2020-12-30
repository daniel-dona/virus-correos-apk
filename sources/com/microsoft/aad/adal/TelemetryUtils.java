package com.microsoft.aad.adal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* compiled from: PG */
public final class TelemetryUtils {
    public static final Set<String> GDPR_FILTERED_FIELDS = new HashSet();
    public static final String TAG = TelemetryUtils.class.getSimpleName();

    /* compiled from: PG */
    public static class CliTelemInfo extends com.microsoft.identity.common.internal.telemetry.CliTelemInfo {
        public void _setRefreshTokenAge(String str) {
            super.setRefreshTokenAge(str);
        }

        public void _setServerErrorCode(String str) {
            super.setServerErrorCode(str);
        }

        public void _setServerSubErrorCode(String str) {
            super.setServerSubErrorCode(str);
        }

        public void _setSpeRing(String str) {
            super.setSpeRing(str);
        }

        public void _setVersion(String str) {
            super.setVersion(str);
        }

        public CliTelemInfo() {
        }

        public CliTelemInfo(com.microsoft.identity.common.internal.telemetry.CliTelemInfo cliTelemInfo) {
            super(cliTelemInfo);
        }
    }

    static {
        initializeGdprFilteredFields();
    }

    public static void initializeGdprFilteredFields() {
        GDPR_FILTERED_FIELDS.addAll(Arrays.asList(new String[]{"Microsoft.ADAL.login_hint", "Microsoft.ADAL.user_id", "Microsoft.ADAL.tenant_id"}));
    }

    public static CliTelemInfo parseXMsCliTelemHeader(String str) {
        return new CliTelemInfo(com.microsoft.identity.common.internal.telemetry.CliTelemInfo.fromXMsCliTelemHeader(str));
    }
}
