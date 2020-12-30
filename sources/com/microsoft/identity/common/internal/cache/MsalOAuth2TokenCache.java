package com.microsoft.identity.common.internal.cache;

import android.content.Context;
import com.microsoft.identity.common.BaseAccount;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.exception.ClientException;
import com.microsoft.identity.common.internal.dto.AccessTokenRecord;
import com.microsoft.identity.common.internal.dto.AccountRecord;
import com.microsoft.identity.common.internal.dto.Credential;
import com.microsoft.identity.common.internal.dto.CredentialType;
import com.microsoft.identity.common.internal.dto.IdTokenRecord;
import com.microsoft.identity.common.internal.dto.RefreshTokenRecord;
import com.microsoft.identity.common.internal.logging.Logger;
import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftAccount;
import com.microsoft.identity.common.internal.providers.oauth2.AuthorizationRequest;
import com.microsoft.identity.common.internal.providers.oauth2.OAuth2Strategy;
import com.microsoft.identity.common.internal.providers.oauth2.OAuth2TokenCache;
import com.microsoft.identity.common.internal.providers.oauth2.RefreshToken;
import com.microsoft.identity.common.internal.providers.oauth2.TokenResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
public class MsalOAuth2TokenCache<GenericOAuth2Strategy extends OAuth2Strategy, GenericAuthorizationRequest extends AuthorizationRequest, GenericTokenResponse extends TokenResponse, GenericAccount extends BaseAccount, GenericRefreshToken extends RefreshToken> extends OAuth2TokenCache<GenericOAuth2Strategy, GenericAuthorizationRequest, GenericTokenResponse> implements IShareSingleSignOnState<GenericAccount, GenericRefreshToken> {
    public static final String TAG = "MsalOAuth2TokenCache";
    public final IAccountCredentialAdapter<GenericOAuth2Strategy, GenericAuthorizationRequest, GenericTokenResponse, GenericAccount, GenericRefreshToken> mAccountCredentialAdapter;
    public IAccountCredentialCache mAccountCredentialCache;

    public MsalOAuth2TokenCache(Context context, IAccountCredentialCache iAccountCredentialCache, IAccountCredentialAdapter<GenericOAuth2Strategy, GenericAuthorizationRequest, GenericTokenResponse, GenericAccount, GenericRefreshToken> iAccountCredentialAdapter) {
        super(context);
        String str = TAG;
        StringBuilder a = Eo.a("Init: ");
        a.append(TAG);
        Logger.verbose(str, a.toString());
        this.mAccountCredentialCache = iAccountCredentialCache;
        this.mAccountCredentialAdapter = iAccountCredentialAdapter;
    }

    private boolean accountHasCredential(AccountRecord accountRecord, List<Credential> list) {
        String homeAccountId = accountRecord.getHomeAccountId();
        String environment = accountRecord.getEnvironment();
        String a = Eo.a(new StringBuilder(), TAG, ":accountHasCredential");
        Logger.infoPII(a, "HomeAccountId: [" + homeAccountId + "]");
        String a2 = Eo.a(new StringBuilder(), TAG, ":accountHasCredential");
        Logger.infoPII(a2, "Environment: [" + environment + "]");
        for (Credential next : list) {
            if (homeAccountId.equals(next.getHomeAccountId()) && environment.equals(next.getEnvironment())) {
                Logger.info(TAG + ":accountHasCredential", "Credentials located for account.");
                return true;
            }
        }
        return false;
    }

    private void deleteAccessTokensWithIntersectingScopes(AccessTokenRecord accessTokenRecord) {
        List<Credential> credentialsFilteredBy = this.mAccountCredentialCache.getCredentialsFilteredBy(accessTokenRecord.getHomeAccountId(), accessTokenRecord.getEnvironment(), CredentialType.AccessToken, accessTokenRecord.getClientId(), accessTokenRecord.getRealm(), (String) null);
        String a = Eo.a(new StringBuilder(), TAG, ":", "deleteAccessTokensWithIntersectingScopes");
        StringBuilder a2 = Eo.a("Inspecting ");
        a2.append(credentialsFilteredBy.size());
        a2.append(" accessToken[s].");
        Logger.verbose(a, a2.toString());
        for (Credential next : credentialsFilteredBy) {
            if (scopesIntersect(accessTokenRecord, (AccessTokenRecord) next)) {
                String a3 = Eo.a(new StringBuilder(), TAG, ":", "deleteAccessTokensWithIntersectingScopes");
                Logger.infoPII(a3, "Removing credential: " + next);
                this.mAccountCredentialCache.removeCredential(next);
            }
        }
    }

    private boolean isAccessTokenSchemaCompliant(AccessTokenRecord accessTokenRecord) {
        return isSchemaCompliant(AccessTokenRecord.class, new String[][]{new String[]{"credential_type", accessTokenRecord.getCredentialType()}, new String[]{"home_account_id", accessTokenRecord.getHomeAccountId()}, new String[]{"environment", accessTokenRecord.getEnvironment()}, new String[]{"client_id", accessTokenRecord.getClientId()}, new String[]{"target", accessTokenRecord.getTarget()}, new String[]{"cached_at", accessTokenRecord.getCachedAt()}, new String[]{"expires_on", accessTokenRecord.getExpiresOn()}, new String[]{"secret", accessTokenRecord.getSecret()}});
    }

    private boolean isAccountSchemaCompliant(AccountRecord accountRecord) {
        return isSchemaCompliant(AccountRecord.class, new String[][]{new String[]{"home_account_id", accountRecord.getHomeAccountId()}, new String[]{"environment", accountRecord.getEnvironment()}, new String[]{"local_account_id", accountRecord.getLocalAccountId()}, new String[]{"username", accountRecord.getUsername()}, new String[]{"authority_type", accountRecord.getAuthorityType()}});
    }

    private boolean isIdTokenSchemaCompliant(IdTokenRecord idTokenRecord) {
        return isSchemaCompliant(IdTokenRecord.class, new String[][]{new String[]{"home_account_id", idTokenRecord.getHomeAccountId()}, new String[]{"environment", idTokenRecord.getEnvironment()}, new String[]{"credential_type", idTokenRecord.getCredentialType()}, new String[]{"client_id", idTokenRecord.getClientId()}, new String[]{"secret", idTokenRecord.getSecret()}});
    }

    private boolean isRefreshTokenSchemaCompliant(RefreshTokenRecord refreshTokenRecord) {
        return isSchemaCompliant(RefreshTokenRecord.class, new String[][]{new String[]{"credential_type", refreshTokenRecord.getCredentialType()}, new String[]{"environment", refreshTokenRecord.getEnvironment()}, new String[]{"home_account_id", refreshTokenRecord.getHomeAccountId()}, new String[]{"client_id", refreshTokenRecord.getClientId()}, new String[]{"secret", refreshTokenRecord.getSecret()}});
    }

    public static boolean isSchemaCompliant(Class<?> cls, String[][] strArr) {
        boolean z = true;
        for (String[] strArr2 : strArr) {
            z = z && !StringExtensions.isNullOrBlank(strArr2[1]);
        }
        if (!z) {
            Logger.warn(Eo.a(new StringBuilder(), TAG, ":", "isSchemaCompliant"), cls.getSimpleName() + " does not contain all required fields.");
            for (String[] strArr3 : strArr) {
                Logger.warn(Eo.a(new StringBuilder(), TAG, ":", "isSchemaCompliant"), strArr3[0] + " is null? [" + StringExtensions.isNullOrBlank(strArr3[1]) + "]");
            }
        }
        return z;
    }

    private int removeCredentialsOfTypeForAccount(String str, String str2, CredentialType credentialType, AccountRecord accountRecord, boolean z) {
        String str3;
        IAccountCredentialCache iAccountCredentialCache = this.mAccountCredentialCache;
        String homeAccountId = accountRecord.getHomeAccountId();
        if (z) {
            str3 = null;
        } else {
            str3 = accountRecord.getRealm();
        }
        int i = 0;
        for (Credential removeCredential : iAccountCredentialCache.getCredentialsFilteredBy(homeAccountId, str, credentialType, str2, str3, (String) null)) {
            if (this.mAccountCredentialCache.removeCredential(removeCredential)) {
                i++;
            }
        }
        return i;
    }

    private int removeRefreshTokensForAccount(AccountRecord accountRecord, boolean z, String str, String str2) {
        if (z) {
            str2 = null;
        }
        return removeCredentialsOfTypeForAccount(str, str2, CredentialType.RefreshToken, accountRecord, true);
    }

    private void saveAccounts(AccountRecord... accountRecordArr) {
        for (AccountRecord saveAccount : accountRecordArr) {
            this.mAccountCredentialCache.saveAccount(saveAccount);
        }
    }

    private void saveCredentials(Credential... credentialArr) {
        for (AccessTokenRecord accessTokenRecord : credentialArr) {
            if (accessTokenRecord instanceof AccessTokenRecord) {
                deleteAccessTokensWithIntersectingScopes(accessTokenRecord);
            }
            this.mAccountCredentialCache.saveCredential(accessTokenRecord);
        }
    }

    private Set<String> scopesAsSet(AccessTokenRecord accessTokenRecord) {
        HashSet hashSet = new HashSet();
        String target = accessTokenRecord.getTarget();
        if (!StringExtensions.isNullOrBlank(target)) {
            hashSet.addAll(Arrays.asList(target.split("\\s+")));
        }
        return hashSet;
    }

    private boolean scopesIntersect(AccessTokenRecord accessTokenRecord, AccessTokenRecord accessTokenRecord2) {
        Set<String> scopesAsSet = scopesAsSet(accessTokenRecord);
        for (String next : scopesAsSet(accessTokenRecord2)) {
            if (scopesAsSet.contains(next)) {
                Logger.info(TAG + ":" + "scopesIntersect", "Scopes intersect.");
                String a = Eo.a(new StringBuilder(), TAG, ":", "scopesIntersect");
                Logger.infoPII(a, scopesAsSet.toString() + " contains [" + next + "]");
                return true;
            }
        }
        return false;
    }

    private void validateCacheArtifacts(AccountRecord accountRecord, AccessTokenRecord accessTokenRecord, RefreshTokenRecord refreshTokenRecord, IdTokenRecord idTokenRecord) throws ClientException {
        Logger.info(TAG + ":validateCacheArtifacts", "Validating cache artifacts...");
        boolean isAccountSchemaCompliant = isAccountSchemaCompliant(accountRecord);
        boolean z = accessTokenRecord == null || isAccessTokenSchemaCompliant(accessTokenRecord);
        boolean isRefreshTokenSchemaCompliant = isRefreshTokenSchemaCompliant(refreshTokenRecord);
        boolean isIdTokenSchemaCompliant = isIdTokenSchemaCompliant(idTokenRecord);
        if (!isAccountSchemaCompliant) {
            throw new ClientException("Account is missing schema-required fields.");
        } else if (!z || !isRefreshTokenSchemaCompliant || !isIdTokenSchemaCompliant) {
            String str = "[";
            if (!z) {
                str = Eo.a(str, "(AT)");
            }
            if (!isRefreshTokenSchemaCompliant) {
                str = Eo.a(str, "(RT)");
            }
            if (!isIdTokenSchemaCompliant) {
                str = Eo.a(str, "(ID)");
            }
            throw new ClientException("Credential is missing schema-required fields.", Eo.a(str, "]"));
        }
    }

    public AccountRecord getAccount(String str, String str2, String str3, String str4) {
        String a = Eo.a(new StringBuilder(), TAG, ":getAccount");
        Logger.infoPII(a, "Environment: [" + str + "]");
        String a2 = Eo.a(new StringBuilder(), TAG, ":getAccount");
        Logger.infoPII(a2, "ClientId: [" + str2 + "]");
        String a3 = Eo.a(new StringBuilder(), TAG, ":getAccount");
        Logger.infoPII(a3, "HomeAccountId: [" + str3 + "]");
        String a4 = Eo.a(new StringBuilder(), TAG, ":getAccount");
        Logger.infoPII(a4, "Realm: [" + str4 + "]");
        List<AccountRecord> accounts = getAccounts(str, str2);
        String a5 = Eo.a(new StringBuilder(), TAG, ":getAccount");
        StringBuilder a6 = Eo.a("Found ");
        a6.append(accounts.size());
        a6.append(" accounts");
        Logger.info(a5, a6.toString());
        for (AccountRecord next : accounts) {
            if (str3.equals(next.getHomeAccountId()) && (str4 == null || str4.equals(next.getRealm()))) {
                return next;
            }
        }
        Logger.warn(TAG + ":getAccount", "No matching account found.");
        return null;
    }

    public IAccountCredentialCache getAccountCredentialCache() {
        return this.mAccountCredentialCache;
    }

    public AccountRecord getAccountWithLocalAccountId(String str, String str2, String str3) {
        List<AccountRecord> accounts = getAccounts(str, str2);
        String a = Eo.a(new StringBuilder(), TAG, ":getAccountWithLocalAccountId");
        Logger.infoPII(a, "LocalAccountId: [" + str3 + "]");
        for (AccountRecord next : accounts) {
            if (str3.equals(next.getLocalAccountId())) {
                return next;
            }
        }
        return null;
    }

    public List<AccountRecord> getAccounts(String str, String str2) {
        String str3 = str;
        String a = Eo.a(new StringBuilder(), TAG, ":getAccounts");
        Logger.infoPII(a, "Environment: [" + str3 + "]");
        String a2 = Eo.a(new StringBuilder(), TAG, ":getAccounts");
        Logger.infoPII(a2, "ClientId: [" + str2 + "]");
        ArrayList arrayList = new ArrayList();
        List<AccountRecord> accountsFilteredBy = this.mAccountCredentialCache.getAccountsFilteredBy((String) null, str3, (String) null);
        String a3 = Eo.a(new StringBuilder(), TAG, ":getAccounts");
        StringBuilder a4 = Eo.a("Found ");
        a4.append(accountsFilteredBy.size());
        a4.append(" accounts for this environment");
        Logger.info(a3, a4.toString());
        String str4 = str;
        String str5 = str2;
        List<Credential> credentialsFilteredBy = this.mAccountCredentialCache.getCredentialsFilteredBy((String) null, str4, CredentialType.IdToken, str5, (String) null, (String) null);
        credentialsFilteredBy.addAll(this.mAccountCredentialCache.getCredentialsFilteredBy((String) null, str4, CredentialType.V1IdToken, str5, (String) null, (String) null));
        for (AccountRecord next : accountsFilteredBy) {
            if (accountHasCredential(next, credentialsFilteredBy)) {
                arrayList.add(next);
            }
        }
        String a5 = Eo.a(new StringBuilder(), TAG, ":getAccounts");
        StringBuilder a6 = Eo.a("Found ");
        a6.append(arrayList.size());
        a6.append(" accounts for this clientId");
        Logger.info(a5, a6.toString());
        return Collections.unmodifiableList(arrayList);
    }

    public Set<String> getAllClientIds() {
        HashSet hashSet = new HashSet();
        for (Credential clientId : this.mAccountCredentialCache.getCredentials()) {
            hashSet.add(clientId.getClientId());
        }
        String a = Eo.a(new StringBuilder(), TAG, ":getAllClientIds");
        StringBuilder a2 = Eo.a("Found [");
        a2.append(hashSet.size());
        a2.append("] clientIds/");
        Logger.verbose(a, a2.toString());
        return hashSet;
    }

    public GenericRefreshToken getSingleSignOnState(GenericAccount genericaccount) {
        throw new UnsupportedOperationException("Unimplemented!");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: com.microsoft.identity.common.internal.dto.IdTokenRecord} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.microsoft.identity.common.internal.cache.ICacheRecord load(java.lang.String r11, java.lang.String r12, com.microsoft.identity.common.internal.dto.AccountRecord r13) {
        /*
            r10 = this;
            java.lang.String r0 = r13.getAuthorityType()
            java.lang.String r1 = "MSSTS"
            boolean r0 = r1.equals(r0)
            com.microsoft.identity.common.internal.cache.IAccountCredentialCache r1 = r10.mAccountCredentialCache
            java.lang.String r2 = r13.getHomeAccountId()
            java.lang.String r3 = r13.getEnvironment()
            com.microsoft.identity.common.internal.dto.CredentialType r4 = com.microsoft.identity.common.internal.dto.CredentialType.AccessToken
            java.lang.String r6 = r13.getRealm()
            r5 = r11
            r7 = r12
            java.util.List r1 = r1.getCredentialsFilteredBy(r2, r3, r4, r5, r6, r7)
            com.microsoft.identity.common.internal.cache.IAccountCredentialCache r2 = r10.mAccountCredentialCache
            java.lang.String r3 = r13.getHomeAccountId()
            java.lang.String r4 = r13.getEnvironment()
            com.microsoft.identity.common.internal.dto.CredentialType r5 = com.microsoft.identity.common.internal.dto.CredentialType.RefreshToken
            r9 = 0
            if (r0 == 0) goto L_0x0031
            r7 = r9
            goto L_0x0036
        L_0x0031:
            java.lang.String r6 = r13.getRealm()
            r7 = r6
        L_0x0036:
            if (r0 == 0) goto L_0x003a
            r8 = r9
            goto L_0x003b
        L_0x003a:
            r8 = r12
        L_0x003b:
            r6 = r11
            java.util.List r12 = r2.getCredentialsFilteredBy(r3, r4, r5, r6, r7, r8)
            com.microsoft.identity.common.internal.cache.IAccountCredentialCache r2 = r10.mAccountCredentialCache
            java.lang.String r3 = r13.getHomeAccountId()
            java.lang.String r4 = r13.getEnvironment()
            com.microsoft.identity.common.internal.dto.CredentialType r5 = com.microsoft.identity.common.internal.dto.CredentialType.IdToken
            java.lang.String r7 = r13.getRealm()
            r8 = 0
            java.util.List r0 = r2.getCredentialsFilteredBy(r3, r4, r5, r6, r7, r8)
            com.microsoft.identity.common.internal.cache.IAccountCredentialCache r2 = r10.mAccountCredentialCache
            java.lang.String r3 = r13.getHomeAccountId()
            java.lang.String r4 = r13.getEnvironment()
            com.microsoft.identity.common.internal.dto.CredentialType r5 = com.microsoft.identity.common.internal.dto.CredentialType.V1IdToken
            java.lang.String r7 = r13.getRealm()
            java.util.List r11 = r2.getCredentialsFilteredBy(r3, r4, r5, r6, r7, r8)
            com.microsoft.identity.common.internal.cache.CacheRecord r2 = new com.microsoft.identity.common.internal.cache.CacheRecord
            r2.<init>()
            r2.setAccount(r13)
            boolean r13 = r1.isEmpty()
            r3 = 0
            if (r13 == 0) goto L_0x007a
            r13 = r9
            goto L_0x0080
        L_0x007a:
            java.lang.Object r13 = r1.get(r3)
            com.microsoft.identity.common.internal.dto.AccessTokenRecord r13 = (com.microsoft.identity.common.internal.dto.AccessTokenRecord) r13
        L_0x0080:
            r2.setAccessToken(r13)
            boolean r13 = r12.isEmpty()
            if (r13 == 0) goto L_0x008b
            r12 = r9
            goto L_0x0091
        L_0x008b:
            java.lang.Object r12 = r12.get(r3)
            com.microsoft.identity.common.internal.dto.RefreshTokenRecord r12 = (com.microsoft.identity.common.internal.dto.RefreshTokenRecord) r12
        L_0x0091:
            r2.setRefreshToken(r12)
            boolean r12 = r0.isEmpty()
            if (r12 == 0) goto L_0x009c
            r12 = r9
            goto L_0x00a2
        L_0x009c:
            java.lang.Object r12 = r0.get(r3)
            com.microsoft.identity.common.internal.dto.IdTokenRecord r12 = (com.microsoft.identity.common.internal.dto.IdTokenRecord) r12
        L_0x00a2:
            r2.setIdToken(r12)
            boolean r12 = r11.isEmpty()
            if (r12 == 0) goto L_0x00ac
            goto L_0x00b3
        L_0x00ac:
            java.lang.Object r11 = r11.get(r3)
            r9 = r11
            com.microsoft.identity.common.internal.dto.IdTokenRecord r9 = (com.microsoft.identity.common.internal.dto.IdTokenRecord) r9
        L_0x00b3:
            r2.setV1IdToken(r9)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.identity.common.internal.cache.MsalOAuth2TokenCache.load(java.lang.String, java.lang.String, com.microsoft.identity.common.internal.dto.AccountRecord):com.microsoft.identity.common.internal.cache.ICacheRecord");
    }

    public AccountDeletionRecord removeAccount(String str, String str2, String str3, String str4) {
        AccountRecord account;
        String str5 = str;
        String str6 = str2;
        String str7 = str3;
        String str8 = str4;
        Logger.infoPII(Eo.a(new StringBuilder(), TAG, ":removeAccount"), Eo.a(Eo.a("Environment: [", str5, "]\nClientId: [", str6, "]\nHomeAccountId: ["), str7, "]\nRealm: [", str8, "]"));
        if (str5 == null || str6 == null || str7 == null || (account = getAccount(str, str2, str3, str4)) == null) {
            Logger.warn(TAG + ":removeAccount", "Insufficient filtering provided for account removal - preserving Account.");
            return new AccountDeletionRecord((List<AccountRecord>) null);
        }
        boolean z = str8 == null;
        Logger.info(Eo.a(new StringBuilder(), TAG, ":removeAccount"), "IsRealmAgnostic? " + z);
        String str9 = str;
        String str10 = str2;
        AccountRecord accountRecord = account;
        boolean z2 = z;
        int removeCredentialsOfTypeForAccount = removeCredentialsOfTypeForAccount(str9, str10, CredentialType.AccessToken, accountRecord, z);
        int removeCredentialsOfTypeForAccount2 = removeCredentialsOfTypeForAccount(str9, str10, CredentialType.RefreshToken, accountRecord, z);
        int removeCredentialsOfTypeForAccount3 = removeCredentialsOfTypeForAccount(str9, str10, CredentialType.IdToken, accountRecord, z);
        int removeCredentialsOfTypeForAccount4 = removeCredentialsOfTypeForAccount(str9, str10, CredentialType.V1IdToken, accountRecord, z);
        ArrayList arrayList = new ArrayList();
        if (z2) {
            for (AccountRecord next : this.mAccountCredentialCache.getAccountsFilteredBy(str7, str5, (String) null)) {
                if (this.mAccountCredentialCache.removeAccount(next)) {
                    arrayList.add(next);
                }
            }
        } else if (this.mAccountCredentialCache.removeAccount(account)) {
            arrayList.add(account);
        }
        for (String[] strArr : new String[][]{new String[]{"Access tokens", String.valueOf(removeCredentialsOfTypeForAccount)}, new String[]{"Refresh tokens", String.valueOf(removeCredentialsOfTypeForAccount2)}, new String[]{"Id tokens (v1)", String.valueOf(removeCredentialsOfTypeForAccount4)}, new String[]{"Id tokens (v2)", String.valueOf(removeCredentialsOfTypeForAccount3)}, new String[]{"Accounts", String.valueOf(arrayList.size())}}) {
            Logger.info(Eo.a(new StringBuilder(), TAG, ":removeAccount"), strArr[0] + " removed: [" + strArr[1] + "]");
        }
        return new AccountDeletionRecord(arrayList);
    }

    public boolean removeCredential(Credential credential) {
        Logger.info(TAG + ":removeCredential", "Removing credential...");
        String a = Eo.a(new StringBuilder(), TAG, ":removeCredential");
        StringBuilder a2 = Eo.a("ClientId: [");
        a2.append(credential.getClientId());
        a2.append("]");
        Logger.infoPII(a, a2.toString());
        String a3 = Eo.a(new StringBuilder(), TAG, ":removeCredential");
        StringBuilder a4 = Eo.a("CredentialType: [");
        a4.append(credential.getCredentialType());
        a4.append("]");
        Logger.infoPII(a3, a4.toString());
        String a5 = Eo.a(new StringBuilder(), TAG, ":removeCredential");
        StringBuilder a6 = Eo.a("CachedAt: [");
        a6.append(credential.getCachedAt());
        a6.append("]");
        Logger.infoPII(a5, a6.toString());
        String a7 = Eo.a(new StringBuilder(), TAG, ":removeCredential");
        StringBuilder a8 = Eo.a("Environment: [");
        a8.append(credential.getEnvironment());
        a8.append("]");
        Logger.infoPII(a7, a8.toString());
        String a9 = Eo.a(new StringBuilder(), TAG, ":removeCredential");
        StringBuilder a10 = Eo.a("HomeAccountId: [");
        a10.append(credential.getHomeAccountId());
        a10.append("]");
        Logger.infoPII(a9, a10.toString());
        String a11 = Eo.a(new StringBuilder(), TAG, ":removeCredential");
        StringBuilder a12 = Eo.a("IsExpired?: [");
        a12.append(credential.isExpired());
        a12.append("]");
        Logger.infoPII(a11, a12.toString());
        return this.mAccountCredentialCache.removeCredential(credential);
    }

    public ICacheRecord save(AccountRecord accountRecord, IdTokenRecord idTokenRecord, AccessTokenRecord accessTokenRecord) throws ClientException {
        boolean isAccountSchemaCompliant = isAccountSchemaCompliant(accountRecord);
        boolean isIdTokenSchemaCompliant = isIdTokenSchemaCompliant(idTokenRecord);
        boolean isAccessTokenSchemaCompliant = isAccessTokenSchemaCompliant(accessTokenRecord);
        if (!isAccountSchemaCompliant) {
            throw new ClientException("Account is missing schema-required fields.");
        } else if (!isIdTokenSchemaCompliant) {
            throw new ClientException("Credential is missing schema-required fields.", "[(ID)]");
        } else if (isAccessTokenSchemaCompliant) {
            Eo.d(new StringBuilder(), TAG, ":save (broker 3 arg)", "Accounts/Credentials are valid.... proceeding");
            saveAccounts(accountRecord);
            saveCredentials(idTokenRecord, accessTokenRecord);
            CacheRecord cacheRecord = new CacheRecord();
            cacheRecord.setAccount(accountRecord);
            cacheRecord.setIdToken(idTokenRecord);
            cacheRecord.setAccessToken(accessTokenRecord);
            return cacheRecord;
        } else {
            throw new ClientException("Credential is missing schema-required fields.", "[(AT)]");
        }
    }

    public void setSingleSignOnState(GenericAccount genericaccount, GenericRefreshToken genericrefreshtoken) throws ClientException {
        AccountRecord asAccount = this.mAccountCredentialAdapter.asAccount(genericaccount);
        RefreshTokenRecord asRefreshToken = this.mAccountCredentialAdapter.asRefreshToken(genericrefreshtoken);
        IdTokenRecord asIdToken = this.mAccountCredentialAdapter.asIdToken(genericaccount, genericrefreshtoken);
        validateCacheArtifacts(asAccount, (AccessTokenRecord) null, asRefreshToken, asIdToken);
        boolean z = !StringExtensions.isNullOrBlank(genericrefreshtoken.getFamilyId());
        boolean equals = MicrosoftAccount.AUTHORITY_TYPE_V1_V2.equals(asAccount.getAuthorityType());
        if (z || equals) {
            int removeRefreshTokensForAccount = removeRefreshTokensForAccount(asAccount, z, asAccount.getEnvironment(), asRefreshToken.getClientId());
            Logger.info(Eo.a(new StringBuilder(), TAG, "setSingleSignOnState"), "Refresh tokens removed: [" + removeRefreshTokensForAccount + "]");
            if (removeRefreshTokensForAccount > 1) {
                Logger.warn(TAG + "setSingleSignOnState", "Multiple refresh tokens found for Account.");
            }
        }
        saveAccounts(asAccount);
        saveCredentials(asIdToken, asRefreshToken);
    }

    public ICacheRecord save(GenericOAuth2Strategy genericoauth2strategy, GenericAuthorizationRequest genericauthorizationrequest, GenericTokenResponse generictokenresponse) throws ClientException {
        AccountRecord createAccount = this.mAccountCredentialAdapter.createAccount(genericoauth2strategy, genericauthorizationrequest, generictokenresponse);
        AccessTokenRecord createAccessToken = this.mAccountCredentialAdapter.createAccessToken(genericoauth2strategy, genericauthorizationrequest, generictokenresponse);
        RefreshTokenRecord createRefreshToken = this.mAccountCredentialAdapter.createRefreshToken(genericoauth2strategy, genericauthorizationrequest, generictokenresponse);
        IdTokenRecord createIdToken = this.mAccountCredentialAdapter.createIdToken(genericoauth2strategy, genericauthorizationrequest, generictokenresponse);
        validateCacheArtifacts(createAccount, createAccessToken, createRefreshToken, createIdToken);
        boolean z = !StringExtensions.isNullOrBlank(createRefreshToken.getFamilyId());
        Logger.info(Eo.a(new StringBuilder(), TAG, ":save"), "isFamilyRefreshToken? [" + z + "]");
        boolean equals = MicrosoftAccount.AUTHORITY_TYPE_V1_V2.equals(createAccount.getAuthorityType());
        Logger.info(Eo.a(new StringBuilder(), TAG, ":save"), "isMultiResourceCapable? [" + equals + "]");
        if (z || equals) {
            int removeRefreshTokensForAccount = removeRefreshTokensForAccount(createAccount, z, createAccount.getEnvironment(), createRefreshToken.getClientId());
            Logger.info(Eo.a(new StringBuilder(), TAG, ":save"), "Refresh tokens removed: [" + removeRefreshTokensForAccount + "]");
            if (removeRefreshTokensForAccount > 1) {
                Logger.warn(TAG + ":save", "Multiple refresh tokens found for Account.");
            }
        }
        saveAccounts(createAccount);
        saveCredentials(createAccessToken, createRefreshToken, createIdToken);
        CacheRecord cacheRecord = new CacheRecord();
        cacheRecord.setAccount(createAccount);
        cacheRecord.setAccessToken(createAccessToken);
        cacheRecord.setRefreshToken(createRefreshToken);
        CredentialType credentialType = CredentialType.V1IdToken;
        if ("V1IdToken".equalsIgnoreCase(createIdToken.getCredentialType())) {
            cacheRecord.setV1IdToken(createIdToken);
        } else {
            cacheRecord.setIdToken(createIdToken);
        }
        return cacheRecord;
    }

    public ICacheRecord save(AccountRecord accountRecord, IdTokenRecord idTokenRecord) {
        Eo.d(new StringBuilder(), TAG, ":save", "Importing AccountRecord, IdTokenRecord (direct)");
        boolean isAccountSchemaCompliant = isAccountSchemaCompliant(accountRecord);
        boolean isIdTokenSchemaCompliant = isIdTokenSchemaCompliant(idTokenRecord);
        CacheRecord cacheRecord = new CacheRecord();
        if (!isAccountSchemaCompliant || !isIdTokenSchemaCompliant) {
            String str = "[";
            if (!isAccountSchemaCompliant) {
                str = Eo.a(str, "(Account)");
            }
            if (!isIdTokenSchemaCompliant) {
                str = Eo.a(str, "(ID)");
            }
            String a = Eo.a(str, "]");
            String a2 = Eo.a(new StringBuilder(), TAG, ":save");
            Logger.warn(a2, "Skipping persistence of non-compliant credentials: " + a);
        } else {
            saveAccounts(accountRecord);
            saveCredentials(idTokenRecord);
            cacheRecord.setAccount(accountRecord);
            cacheRecord.setIdToken(idTokenRecord);
        }
        return cacheRecord;
    }
}
