package com.google.ipc.invalidation.external.client;

/* compiled from: PG */
public interface InvalidationListener {

    /* compiled from: PG */
    public enum RegistrationState {
        REGISTERED,
        UNREGISTERED
    }

    /* renamed from: a */
    void mo2283a(bL bLVar);

    /* renamed from: a */
    void mo2284a(bL bLVar, gL gLVar);

    /* renamed from: a */
    void mo2285a(bL bLVar, jL jLVar);

    /* renamed from: a */
    void mo2286a(bL bLVar, kL kLVar, gL gLVar);

    /* renamed from: a */
    void mo2287a(bL bLVar, lL lLVar, RegistrationState registrationState);

    /* renamed from: a */
    void mo2288a(bL bLVar, lL lLVar, gL gLVar);

    /* renamed from: a */
    void mo2289a(bL bLVar, lL lLVar, boolean z, String str);

    /* renamed from: a */
    void mo2290a(bL bLVar, byte[] bArr, int i);
}
