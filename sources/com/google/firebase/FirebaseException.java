package com.google.firebase;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: PG */
public class FirebaseException extends Exception {
    @Deprecated
    public FirebaseException() {
    }

    public FirebaseException(String str) {
        super(Preconditions.checkNotEmpty(str, "Detail message must not be empty"));
    }

    public FirebaseException(String str, Throwable th) {
        super(Preconditions.checkNotEmpty(str, "Detail message must not be empty"), th);
    }
}
