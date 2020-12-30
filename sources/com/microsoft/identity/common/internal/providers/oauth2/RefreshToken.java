package com.microsoft.identity.common.internal.providers.oauth2;

import com.microsoft.identity.common.internal.dto.IRefreshTokenRecord;

/* compiled from: PG */
public abstract class RefreshToken implements IRefreshTokenRecord {
    public String mRawRefreshToken;
    public long mTokenReceivedTime;

    public RefreshToken(String str) {
        this.mRawRefreshToken = str;
    }

    public String getRefreshToken() {
        return this.mRawRefreshToken;
    }

    public long getTokenReceivedTime() {
        return this.mTokenReceivedTime;
    }

    public void setRawRefreshToken(String str) {
        this.mRawRefreshToken = str;
    }

    public void setTokenReceivedTime(long j) {
        this.mTokenReceivedTime = j;
    }

    public RefreshToken(TokenResponse tokenResponse) {
        this.mTokenReceivedTime = tokenResponse.getResponseReceivedTime();
        this.mRawRefreshToken = tokenResponse.getRefreshToken();
    }
}
