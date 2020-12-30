package com.google.firebase.auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseException;

/* compiled from: PG */
public class FirebaseAuthException extends FirebaseException {
    public final String zza;

    public FirebaseAuthException(String str, String str2) {
        super(str2);
        this.zza = Preconditions.checkNotEmpty(str);
    }

    public String getErrorCode() {
        return this.zza;
    }
}
