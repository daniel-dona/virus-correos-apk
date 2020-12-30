package com.citrix.mvpn.api;

/* compiled from: PG */
public enum ResponseStatusCode {
    START_TUNNEL_SUCCESS(0),
    START_TUNNEL_FAILED(1),
    TUNNEL_ALREADY_RUNNING(2),
    SESSION_EXPIRED(3),
    FOUND_LEGACY_MODE(4),
    FOUND_NON_WEBSSO_MODE(5),
    FOUND_NON_MANAGED_APP(6),
    NO_NETWORK_CONNECTION(7),
    INVALID_APP_CONFIGURATION_DATA(8),
    INVALID_OAUTH_TOKEN(9);
    

    /* renamed from: a */
    public int f141a;

    /* access modifiers changed from: public */
    ResponseStatusCode(int i) {
        this.f141a = i;
    }

    public static ResponseStatusCode fromId(int i) {
        for (ResponseStatusCode responseStatusCode : values()) {
            if (responseStatusCode.getValue() == i) {
                return responseStatusCode;
            }
        }
        return null;
    }

    public int getValue() {
        return this.f141a;
    }
}
