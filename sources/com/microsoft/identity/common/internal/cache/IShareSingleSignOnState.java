package com.microsoft.identity.common.internal.cache;

import com.microsoft.identity.common.BaseAccount;
import com.microsoft.identity.common.exception.ClientException;
import com.microsoft.identity.common.internal.providers.oauth2.RefreshToken;

/* compiled from: PG */
public interface IShareSingleSignOnState<T extends BaseAccount, U extends RefreshToken> {
    U getSingleSignOnState(T t);

    void setSingleSignOnState(T t, U u) throws ClientException;
}
