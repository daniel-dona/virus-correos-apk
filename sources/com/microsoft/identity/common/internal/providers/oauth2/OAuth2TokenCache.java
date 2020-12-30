package com.microsoft.identity.common.internal.providers.oauth2;

import android.content.Context;
import com.microsoft.identity.common.exception.ClientException;
import com.microsoft.identity.common.internal.cache.AccountDeletionRecord;
import com.microsoft.identity.common.internal.cache.ICacheRecord;
import com.microsoft.identity.common.internal.dto.AccountRecord;
import com.microsoft.identity.common.internal.dto.Credential;
import com.microsoft.identity.common.internal.dto.IdTokenRecord;
import com.microsoft.identity.common.internal.providers.oauth2.AuthorizationRequest;
import com.microsoft.identity.common.internal.providers.oauth2.OAuth2Strategy;
import com.microsoft.identity.common.internal.providers.oauth2.TokenResponse;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
public abstract class OAuth2TokenCache<T extends OAuth2Strategy, U extends AuthorizationRequest, V extends TokenResponse> {
    public final Context mContext;

    public OAuth2TokenCache(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public abstract AccountRecord getAccount(String str, String str2, String str3, String str4);

    public abstract AccountRecord getAccountWithLocalAccountId(String str, String str2, String str3);

    public abstract List<AccountRecord> getAccounts(String str, String str2);

    public abstract Set<String> getAllClientIds();

    public final Context getContext() {
        return this.mContext;
    }

    public abstract ICacheRecord load(String str, String str2, AccountRecord accountRecord);

    public abstract AccountDeletionRecord removeAccount(String str, String str2, String str3, String str4);

    public abstract boolean removeCredential(Credential credential);

    public abstract ICacheRecord save(AccountRecord accountRecord, IdTokenRecord idTokenRecord);

    public abstract ICacheRecord save(T t, U u, V v) throws ClientException;
}
