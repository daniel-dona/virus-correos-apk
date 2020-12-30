package com.microsoft.aad.adal;

/* compiled from: PG */
public class AuthenticationRequestState {
    public final APIEvent mAPIEvent;
    public boolean mCancelled = false;
    public AuthenticationCallback<AuthenticationResult> mDelegate = null;
    public AuthenticationRequest mRequest = null;
    public int mRequestId = 0;

    public AuthenticationRequestState(int i, AuthenticationRequest authenticationRequest, AuthenticationCallback<AuthenticationResult> authenticationCallback, APIEvent aPIEvent) {
        this.mRequestId = i;
        this.mDelegate = authenticationCallback;
        this.mRequest = authenticationRequest;
        this.mAPIEvent = aPIEvent;
    }

    public APIEvent getAPIEvent() {
        return this.mAPIEvent;
    }

    public AuthenticationCallback<AuthenticationResult> getDelegate() {
        return this.mDelegate;
    }

    public AuthenticationRequest getRequest() {
        return this.mRequest;
    }

    public int getRequestId() {
        return this.mRequestId;
    }

    public boolean isCancelled() {
        return this.mCancelled;
    }

    public void setCancelled(boolean z) {
        this.mCancelled = z;
    }

    public void setDelegate(AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        this.mDelegate = authenticationCallback;
    }

    public void setRequest(AuthenticationRequest authenticationRequest) {
        this.mRequest = authenticationRequest;
    }

    public void setRequestId(int i) {
        this.mRequestId = i;
    }
}
