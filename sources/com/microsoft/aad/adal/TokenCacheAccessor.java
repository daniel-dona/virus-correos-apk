package com.microsoft.aad.adal;

import android.content.Context;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.identity.common.adal.internal.cache.IStorageHelper;
import com.microsoft.identity.common.adal.internal.cache.StorageHelper;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.internal.cache.ADALOAuth2TokenCache;
import com.microsoft.identity.common.internal.cache.CacheKeyValueDelegate;
import com.microsoft.identity.common.internal.cache.MicrosoftStsAccountCredentialAdapter;
import com.microsoft.identity.common.internal.cache.MsalOAuth2TokenCache;
import com.microsoft.identity.common.internal.cache.SharedPreferencesAccountCredentialCache;
import com.microsoft.identity.common.internal.cache.SharedPreferencesFileManager;
import com.microsoft.identity.common.internal.logging.Logger;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.AzureActiveDirectory;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.AzureActiveDirectoryAuthorizationRequest;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.AzureActiveDirectoryOAuth2Configuration;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.AzureActiveDirectoryOAuth2Strategy;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.AzureActiveDirectoryTokenResponse;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
public class TokenCacheAccessor {
    public static final String TAG = "TokenCacheAccessor";
    public String mAuthority;
    public ADALOAuth2TokenCache mCommonCache = null;
    public final String mTelemetryRequestId;
    public final ITokenCacheStore mTokenCacheStore;
    public boolean mUseCommonCache = false;
    public boolean mValidateAuthorityHost = true;

    /* compiled from: PG */
    public interface KeyMakerStrategy {
        boolean isFrt();

        String makeKey(String str, String str2, String str3);
    }

    public TokenCacheAccessor(Context context, ITokenCacheStore iTokenCacheStore, String str, String str2) {
        if (iTokenCacheStore == null) {
            throw new IllegalArgumentException("tokenCacheStore");
        } else if (StringExtensions.isNullOrBlank(str)) {
            throw new IllegalArgumentException("authority");
        } else if (!StringExtensions.isNullOrBlank(str2)) {
            this.mTokenCacheStore = iTokenCacheStore;
            this.mAuthority = str;
            this.mTelemetryRequestId = str2;
            ArrayList arrayList = new ArrayList();
            arrayList.add(new MsalOAuth2TokenCache(context, new SharedPreferencesAccountCredentialCache(new CacheKeyValueDelegate(), new SharedPreferencesFileManager(context, SharedPreferencesAccountCredentialCache.DEFAULT_ACCOUNT_CREDENTIAL_SHARED_PREFERENCES, (IStorageHelper) new StorageHelper(context))), new MicrosoftStsAccountCredentialAdapter()));
            this.mCommonCache = new ADALOAuth2TokenCache(context, arrayList);
            if (this.mTokenCacheStore instanceof DefaultTokenCacheStore) {
                this.mUseCommonCache = true;
            }
        } else {
            throw new IllegalArgumentException("requestId");
        }
    }

    private void addDeletionKeyForFRTIfRTValueIsStale(List<String> list, TokenCacheItem tokenCacheItem, String str) {
        TokenCacheItem item = this.mTokenCacheStore.getItem(str);
        if (item != null && tokenCacheItem.getRefreshToken().equalsIgnoreCase(item.getRefreshToken())) {
            list.add(str);
        }
    }

    private void addDeletionKeyForMRRTOrFRTEntry(List<String> list, TokenCacheItem tokenCacheItem, String str, String str2, String str3, KeyMakerStrategy keyMakerStrategy) {
        try {
            String makeKey = keyMakerStrategy.makeKey(str, str2, str3);
            if (keyMakerStrategy.isFrt()) {
                addDeletionKeyForFRTIfRTValueIsStale(list, tokenCacheItem, makeKey);
            } else {
                list.add(makeKey);
            }
        } catch (Exception unused) {
            String str4 = TAG;
            StringBuilder a = Eo.a("Exception encountered during key generation.\nCacheItem client_id: ");
            a.append(tokenCacheItem.getClientId());
            a.append("\nCacheItem family_id: ");
            a.append(tokenCacheItem.getFamilyClientId());
            Logger.m1252w(str4, a.toString());
        }
    }

    private void addDeletionKeysForMRRTOrFRTEntry(String str, TokenCacheItem tokenCacheItem, List<String> list, KeyMakerStrategy keyMakerStrategy) {
        UserInfo userInfo = tokenCacheItem.getUserInfo();
        String clientId = tokenCacheItem.getClientId();
        if (keyMakerStrategy.isFrt()) {
            clientId = tokenCacheItem.getFamilyClientId();
        }
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add((Object) null);
        if (userInfo != null) {
            if (userInfo.getDisplayableId() != null) {
                arrayList.add(userInfo.getDisplayableId());
            }
            if (userInfo.getUserId() != null) {
                arrayList.add(userInfo.getUserId());
                if (tokenCacheItem.getTenantId() != null) {
                    arrayList.add(getUniqueIdentifierForCacheKey(userInfo.getUserId(), tokenCacheItem.getTenantId()));
                }
            }
        }
        for (String addDeletionKeyForMRRTOrFRTEntry : arrayList) {
            addDeletionKeyForMRRTOrFRTEntry(list, tokenCacheItem, str, clientId, addDeletionKeyForMRRTOrFRTEntry, keyMakerStrategy);
        }
    }

    private void addDeletionKeysForRTEntry(String str, TokenCacheItem tokenCacheItem, List<String> list) {
        String resource = tokenCacheItem.getResource();
        String clientId = tokenCacheItem.getClientId();
        UserInfo userInfo = tokenCacheItem.getUserInfo();
        list.add(CacheKey.createCacheKeyForRTEntry(str, resource, clientId, (String) null));
        if (userInfo != null) {
            if (userInfo.getDisplayableId() != null) {
                list.add(CacheKey.createCacheKeyForRTEntry(str, resource, clientId, userInfo.getDisplayableId()));
            }
            if (userInfo.getUserId() != null) {
                list.add(CacheKey.createCacheKeyForRTEntry(str, resource, clientId, userInfo.getUserId()));
                if (tokenCacheItem.getTenantId() != null) {
                    list.add(CacheKey.createCacheKeyForRTEntry(str, resource, clientId, getUniqueIdentifierForCacheKey(userInfo.getUserId(), tokenCacheItem.getTenantId())));
                }
            }
        }
    }

    private String constructAuthorityUrl(String str) throws MalformedURLException {
        URL url = new URL(this.mAuthority);
        if (url.getHost().equalsIgnoreCase(str)) {
            return this.mAuthority;
        }
        return Discovery.constructAuthorityUrl(url, str).toString();
    }

    private String getCacheKey(String str, String str2, String str3, String str4, String str5, TokenEntryType tokenEntryType) {
        int ordinal = tokenEntryType.ordinal();
        if (ordinal == 0) {
            return CacheKey.createCacheKeyForRTEntry(str, str2, str3, str4);
        }
        if (ordinal == 1) {
            return CacheKey.createCacheKeyForMRRT(str, str3, str4);
        }
        if (ordinal != 2) {
            return null;
        }
        return CacheKey.createCacheKeyForFRT(str, str5, str4);
    }

    private InstanceDiscoveryMetadata getInstanceDiscoveryMetadata() throws MalformedURLException {
        return AuthorityValidationMetadataCache.getCachedInstanceDiscoveryMetadata(new URL(this.mAuthority));
    }

    private List<String> getKeyListToRemoveForMRRTOrFRT(TokenCacheItem tokenCacheItem, final boolean z) {
        ArrayList arrayList = new ArrayList();
        C03381 r1 = new KeyMakerStrategy() {
            public boolean isFrt() {
                return z;
            }

            public String makeKey(String str, String str2, String str3) {
                if (z) {
                    return CacheKey.createCacheKeyForFRT(str, str2, str3);
                }
                return CacheKey.createCacheKeyForMRRT(str, str2, str3);
            }
        };
        try {
            String authorityUrlWithPreferredCache = getAuthorityUrlWithPreferredCache();
            if (authorityUrlWithPreferredCache != null) {
                addDeletionKeysForMRRTOrFRTEntry(authorityUrlWithPreferredCache, tokenCacheItem, arrayList, r1);
            }
        } catch (MalformedURLException e) {
            Logger.error(TAG, "Authority from preferred cache is invalid", (Throwable) null);
            Logger.errorPII(TAG, "Failed with exception", e);
        }
        addDeletionKeysForMRRTOrFRTEntry(this.mAuthority, tokenCacheItem, arrayList, r1);
        if (!this.mAuthority.equalsIgnoreCase(tokenCacheItem.getAuthority())) {
            addDeletionKeysForMRRTOrFRTEntry(tokenCacheItem.getAuthority(), tokenCacheItem, arrayList, r1);
        }
        return arrayList;
    }

    private List<String> getKeyListToRemoveForRT(TokenCacheItem tokenCacheItem) {
        ArrayList arrayList = new ArrayList();
        try {
            String authorityUrlWithPreferredCache = getAuthorityUrlWithPreferredCache();
            if (authorityUrlWithPreferredCache != null) {
                addDeletionKeysForRTEntry(authorityUrlWithPreferredCache, tokenCacheItem, arrayList);
            }
        } catch (MalformedURLException e) {
            Logger.error(TAG, "Authority from preferred cache is invalid", (Throwable) null);
            Logger.errorPII(TAG, "Failed with exception", e);
        }
        addDeletionKeysForRTEntry(this.mAuthority, tokenCacheItem, arrayList);
        if (!this.mAuthority.equalsIgnoreCase(tokenCacheItem.getAuthority())) {
            addDeletionKeysForRTEntry(tokenCacheItem.getAuthority(), tokenCacheItem, arrayList);
        }
        return arrayList;
    }

    private TokenCacheItem getTokenCacheItemFromAliasedHost(String str, String str2, String str3, String str4, TokenEntryType tokenEntryType) throws MalformedURLException {
        TokenCacheItem item;
        InstanceDiscoveryMetadata instanceDiscoveryMetadata = getInstanceDiscoveryMetadata();
        if (instanceDiscoveryMetadata == null) {
            return null;
        }
        for (String constructAuthorityUrl : instanceDiscoveryMetadata.getAliases()) {
            String constructAuthorityUrl2 = constructAuthorityUrl(constructAuthorityUrl);
            if (!constructAuthorityUrl2.equalsIgnoreCase(this.mAuthority) && !constructAuthorityUrl2.equalsIgnoreCase(getAuthorityUrlWithPreferredCache()) && (item = this.mTokenCacheStore.getItem(getCacheKey(constructAuthorityUrl2, str, str2, str4, str3, tokenEntryType))) != null) {
                return item;
            }
        }
        return null;
    }

    private TokenCacheItem getTokenCacheItemFromPassedInAuthority(String str, String str2, String str3, String str4, TokenEntryType tokenEntryType) throws MalformedURLException {
        if (getAuthorityUrlWithPreferredCache().equalsIgnoreCase(this.mAuthority)) {
            return null;
        }
        return this.mTokenCacheStore.getItem(getCacheKey(this.mAuthority, str, str2, str4, str3, tokenEntryType));
    }

    private String getUniqueIdentifierForCacheKey(String str, String str2) {
        return StringExtensions.base64UrlEncodeToString(str) + "." + StringExtensions.base64UrlEncodeToString(str2);
    }

    private boolean isUserMisMatch(String str, TokenCacheItem tokenCacheItem) {
        if (StringExtensions.isNullOrBlank(str) || tokenCacheItem.getUserInfo() == null || str.equalsIgnoreCase(tokenCacheItem.getUserInfo().getDisplayableId()) || str.equalsIgnoreCase(tokenCacheItem.getUserInfo().getUserId())) {
            return false;
        }
        return true;
    }

    private void logReturnedToken(AuthenticationResult authenticationResult) {
        if (authenticationResult != null && authenticationResult.getAccessToken() != null) {
            Logger.m1248i(TAG, "Access tokenID and refresh tokenID returned. ", (String) null);
        }
    }

    private TokenCacheItem performAdditionalCacheLookup(String str, String str2, String str3, String str4, TokenEntryType tokenEntryType) throws MalformedURLException {
        TokenCacheItem tokenCacheItemFromPassedInAuthority = getTokenCacheItemFromPassedInAuthority(str, str2, str3, str4, tokenEntryType);
        return tokenCacheItemFromPassedInAuthority == null ? getTokenCacheItemFromAliasedHost(str, str2, str3, str4, tokenEntryType) : tokenCacheItemFromPassedInAuthority;
    }

    private void setItemToCacheForUser(String str, String str2, AuthenticationResult authenticationResult, String str3) throws MalformedURLException {
        logReturnedToken(authenticationResult);
        Logger.m1250v(TAG + ":setItemToCacheForUser", "Save regular token into cache.");
        CacheEvent cacheEvent = new CacheEvent("Microsoft.ADAL.token_cache_write");
        cacheEvent.setRequestId(this.mTelemetryRequestId);
        C0336Telemetry.getInstance().startEvent(this.mTelemetryRequestId, "Microsoft.ADAL.token_cache_write");
        this.mTokenCacheStore.setItem(CacheKey.createCacheKeyForRTEntry(getAuthorityUrlWithPreferredCache(), str, str2, str3), TokenCacheItem.createRegularTokenCacheItem(getAuthorityUrlWithPreferredCache(), str, str2, authenticationResult));
        cacheEvent.setTokenTypeRT(true);
        if (authenticationResult.getIsMultiResourceRefreshToken()) {
            Eo.c(new StringBuilder(), TAG, ":setItemToCacheForUser", "Save Multi Resource Refresh token to cache.");
            this.mTokenCacheStore.setItem(CacheKey.createCacheKeyForMRRT(getAuthorityUrlWithPreferredCache(), str2, str3), TokenCacheItem.createMRRTTokenCacheItem(getAuthorityUrlWithPreferredCache(), str2, authenticationResult));
            cacheEvent.setTokenTypeMRRT(true);
        }
        if (!StringExtensions.isNullOrBlank(authenticationResult.getFamilyClientId()) && !StringExtensions.isNullOrBlank(str3)) {
            Logger.m1250v(TAG + ":setItemToCacheForUser", "Save Family Refresh token into cache.");
            this.mTokenCacheStore.setItem(CacheKey.createCacheKeyForFRT(getAuthorityUrlWithPreferredCache(), authenticationResult.getFamilyClientId(), str3), TokenCacheItem.createFRRTTokenCacheItem(getAuthorityUrlWithPreferredCache(), authenticationResult));
            cacheEvent.setTokenTypeFRT(true);
        }
        C0336Telemetry.getInstance().stopEvent(this.mTelemetryRequestId, cacheEvent, "Microsoft.ADAL.token_cache_write");
    }

    private CacheEvent startCacheTelemetryRequest(String str) {
        CacheEvent cacheEvent = new CacheEvent("Microsoft.ADAL.token_cache_lookup");
        cacheEvent.setTokenType(str);
        cacheEvent.setRequestId(this.mTelemetryRequestId);
        C0336Telemetry.getInstance().startEvent(this.mTelemetryRequestId, "Microsoft.ADAL.token_cache_lookup");
        return cacheEvent;
    }

    private void throwIfMultipleATExisted(String str, String str2, String str3) throws AuthenticationException {
        if (StringExtensions.isNullOrBlank(str3) && isMultipleRTsMatchingGivenAppAndResource(str, str2)) {
            throw new AuthenticationException(ADALError.AUTH_FAILED_USER_MISMATCH, "No user is provided and multiple access tokens exist for the given app and resource.");
        }
    }

    public TokenCacheItem getATFromCache(String str, String str2, String str3) throws AuthenticationException {
        try {
            TokenCacheItem regularRefreshTokenCacheItem = getRegularRefreshTokenCacheItem(str, str2, str3);
            if (regularRefreshTokenCacheItem == null) {
                Eo.c(new StringBuilder(), TAG, ":getATFromCache", "No access token exists.");
                return null;
            }
            throwIfMultipleATExisted(str2, str, str3);
            if (!StringExtensions.isNullOrBlank(regularRefreshTokenCacheItem.getAccessToken())) {
                if (TokenCacheItem.isTokenExpired(regularRefreshTokenCacheItem.getExpiresOn())) {
                    Eo.c(new StringBuilder(), TAG, ":getATFromCache", "Access token exists, but already expired.");
                    return null;
                } else if (isUserMisMatch(str3, regularRefreshTokenCacheItem)) {
                    throw new AuthenticationException(ADALError.AUTH_FAILED_USER_MISMATCH);
                }
            }
            return regularRefreshTokenCacheItem;
        } catch (MalformedURLException e) {
            throw new AuthenticationException(ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_URL, e.getMessage(), (Throwable) e);
        }
    }

    public String getAuthorityUrlWithPreferredCache() throws MalformedURLException {
        InstanceDiscoveryMetadata instanceDiscoveryMetadata = getInstanceDiscoveryMetadata();
        if (instanceDiscoveryMetadata == null || !instanceDiscoveryMetadata.isValidated()) {
            return this.mAuthority;
        }
        return constructAuthorityUrl(instanceDiscoveryMetadata.getPreferredCache());
    }

    public TokenCacheItem getFRTItem(String str, String str2) throws MalformedURLException {
        CacheEvent startCacheTelemetryRequest = startCacheTelemetryRequest("Microsoft.ADAL.frt");
        if (StringExtensions.isNullOrBlank(str2)) {
            C0336Telemetry.getInstance().stopEvent(this.mTelemetryRequestId, startCacheTelemetryRequest, "Microsoft.ADAL.token_cache_lookup");
            return null;
        }
        TokenCacheItem item = this.mTokenCacheStore.getItem(CacheKey.createCacheKeyForFRT(getAuthorityUrlWithPreferredCache(), str, str2));
        if (item == null) {
            item = performAdditionalCacheLookup((String) null, (String) null, str, str2, TokenEntryType.FRT_TOKEN_ENTRY);
        }
        if (item != null) {
            startCacheTelemetryRequest.setTokenTypeFRT(true);
        }
        C0336Telemetry.getInstance().stopEvent(this.mTelemetryRequestId, startCacheTelemetryRequest, "Microsoft.ADAL.token_cache_lookup");
        return item;
    }

    public TokenCacheItem getMRRTItem(String str, String str2) throws MalformedURLException {
        CacheEvent startCacheTelemetryRequest = startCacheTelemetryRequest("Microsoft.ADAL.mrrt");
        TokenCacheItem item = this.mTokenCacheStore.getItem(CacheKey.createCacheKeyForMRRT(getAuthorityUrlWithPreferredCache(), str, str2));
        if (item == null) {
            item = performAdditionalCacheLookup((String) null, str, (String) null, str2, TokenEntryType.MRRT_TOKEN_ENTRY);
        }
        if (item != null) {
            startCacheTelemetryRequest.setTokenTypeMRRT(true);
            startCacheTelemetryRequest.setTokenTypeFRT(item.isFamilyToken());
        }
        C0336Telemetry.getInstance().stopEvent(this.mTelemetryRequestId, startCacheTelemetryRequest, "Microsoft.ADAL.token_cache_lookup");
        return item;
    }

    public TokenCacheItem getRegularRefreshTokenCacheItem(String str, String str2, String str3) throws MalformedURLException {
        CacheEvent startCacheTelemetryRequest = startCacheTelemetryRequest("Microsoft.ADAL.rt");
        TokenCacheItem item = this.mTokenCacheStore.getItem(CacheKey.createCacheKeyForRTEntry(getAuthorityUrlWithPreferredCache(), str, str2, str3));
        if (item == null) {
            item = performAdditionalCacheLookup(str, str2, (String) null, str3, TokenEntryType.REGULAR_TOKEN_ENTRY);
        }
        if (item != null) {
            startCacheTelemetryRequest.setTokenTypeRT(true);
            startCacheTelemetryRequest.setSpeRing(item.getSpeRing());
        }
        C0336Telemetry.getInstance().stopEvent(this.mTelemetryRequestId, startCacheTelemetryRequest, "Microsoft.ADAL.token_cache_lookup");
        return item;
    }

    public TokenCacheItem getStaleToken(AuthenticationRequest authenticationRequest) throws AuthenticationException {
        try {
            TokenCacheItem regularRefreshTokenCacheItem = getRegularRefreshTokenCacheItem(authenticationRequest.getResource(), authenticationRequest.getClientId(), authenticationRequest.getUserFromRequest());
            if (regularRefreshTokenCacheItem == null || StringExtensions.isNullOrBlank(regularRefreshTokenCacheItem.getAccessToken()) || regularRefreshTokenCacheItem.getExtendedExpiresOn() == null || TokenCacheItem.isTokenExpired(regularRefreshTokenCacheItem.getExtendedExpiresOn())) {
                Logger.m1248i(TAG + ":getStaleToken", "The stale access token is not found.", BuildConfig.FLAVOR);
                return null;
            }
            throwIfMultipleATExisted(authenticationRequest.getClientId(), authenticationRequest.getResource(), authenticationRequest.getUserFromRequest());
            Logger.m1248i(TAG + ":getStaleToken", "The stale access token is returned.", BuildConfig.FLAVOR);
            return regularRefreshTokenCacheItem;
        } catch (MalformedURLException e) {
            throw new AuthenticationException(ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_URL, e.getMessage(), (Throwable) e);
        }
    }

    public boolean isMultipleMRRTsMatchingGivenApp(String str) {
        Iterator<TokenCacheItem> all = this.mTokenCacheStore.getAll();
        ArrayList arrayList = new ArrayList();
        while (all.hasNext()) {
            TokenCacheItem next = all.next();
            if (next.getAuthority().equalsIgnoreCase(this.mAuthority) && next.getClientId().equalsIgnoreCase(str)) {
                if (next.getIsMultiResourceRefreshToken() || StringExtensions.isNullOrBlank(next.getResource())) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList.size() > 1;
    }

    public boolean isMultipleRTsMatchingGivenAppAndResource(String str, String str2) {
        Iterator<TokenCacheItem> all = this.mTokenCacheStore.getAll();
        ArrayList arrayList = new ArrayList();
        while (all.hasNext()) {
            TokenCacheItem next = all.next();
            if (this.mAuthority.equalsIgnoreCase(next.getAuthority()) && str.equalsIgnoreCase(next.getClientId()) && str2.equalsIgnoreCase(next.getResource()) && !next.getIsMultiResourceRefreshToken()) {
                arrayList.add(next);
            }
        }
        return arrayList.size() > 1;
    }

    public boolean isValidateAuthorityHost() {
        return this.mValidateAuthorityHost;
    }

    public void removeTokenCacheItem(TokenCacheItem tokenCacheItem, String str) throws AuthenticationException {
        List<String> list;
        CacheEvent cacheEvent = new CacheEvent("Microsoft.ADAL.token_cache_delete");
        cacheEvent.setRequestId(this.mTelemetryRequestId);
        C0336Telemetry.getInstance().startEvent(this.mTelemetryRequestId, "Microsoft.ADAL.token_cache_delete");
        int ordinal = tokenCacheItem.getTokenEntryType().ordinal();
        if (ordinal == 0) {
            cacheEvent.setTokenTypeRT(true);
            Eo.c(new StringBuilder(), TAG, ":removeTokenCacheItem", "Regular RT was used to get access token, remove entries for regular RT entries.");
            list = getKeyListToRemoveForRT(tokenCacheItem);
        } else if (ordinal == 1) {
            cacheEvent.setTokenTypeMRRT(true);
            Eo.c(new StringBuilder(), TAG, ":removeTokenCacheItem", "MRRT was used to get access token, remove entries for both MRRT entries and regular RT entries.");
            List<String> keyListToRemoveForMRRTOrFRT = getKeyListToRemoveForMRRTOrFRT(tokenCacheItem, false);
            TokenCacheItem tokenCacheItem2 = new TokenCacheItem(tokenCacheItem);
            tokenCacheItem2.setResource(str);
            keyListToRemoveForMRRTOrFRT.addAll(getKeyListToRemoveForRT(tokenCacheItem2));
            list = keyListToRemoveForMRRTOrFRT;
        } else if (ordinal == 2) {
            cacheEvent.setTokenTypeFRT(true);
            Eo.c(new StringBuilder(), TAG, ":removeTokenCacheItem", "FRT was used to get access token, remove entries for FRT entries.");
            list = getKeyListToRemoveForMRRTOrFRT(tokenCacheItem, true);
        } else {
            throw new AuthenticationException(ADALError.INVALID_TOKEN_CACHE_ITEM);
        }
        for (String removeItem : list) {
            this.mTokenCacheStore.removeItem(removeItem);
        }
        C0336Telemetry.getInstance().stopEvent(this.mTelemetryRequestId, cacheEvent, "Microsoft.ADAL.token_cache_delete");
    }

    public void setValidateAuthorityHost(boolean z) {
        this.mValidateAuthorityHost = z;
    }

    public void updateCachedItemWithResult(AuthenticationRequest authenticationRequest, AuthenticationResult authenticationResult, TokenCacheItem tokenCacheItem) throws AuthenticationException {
        if (authenticationResult != null) {
            if (!StringExtensions.isNullOrBlank(authenticationResult.getAuthority())) {
                this.mAuthority = authenticationResult.getAuthority();
            }
            if (authenticationResult.getStatus() == AuthenticationResult.AuthenticationStatus.Succeeded) {
                Eo.c(new StringBuilder(), TAG, ":updateCachedItemWithResult", "Save returned AuthenticationResult into cache.");
                if (!(tokenCacheItem == null || tokenCacheItem.getUserInfo() == null || authenticationResult.getUserInfo() != null)) {
                    authenticationResult.setUserInfo(tokenCacheItem.getUserInfo());
                    authenticationResult.setIdToken(tokenCacheItem.getRawIdToken());
                    authenticationResult.setTenantId(tokenCacheItem.getTenantId());
                }
                try {
                    if (!this.mUseCommonCache || UrlExtensions.isADFSAuthority(new URL(this.mAuthority))) {
                        updateTokenCache(authenticationRequest, authenticationResult);
                    } else {
                        updateTokenCacheUsingCommonCache(authenticationRequest, authenticationResult);
                    }
                } catch (MalformedURLException e) {
                    throw new AuthenticationException(ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_URL, e.getMessage(), (Throwable) e);
                }
            } else if ("invalid_grant".equalsIgnoreCase(authenticationResult.getErrorCode())) {
                Logger.m1250v(TAG + ":updateCachedItemWithResult", "Received INVALID_GRANT error code, remove existing cache entry.");
                removeTokenCacheItem(tokenCacheItem, authenticationRequest.getResource());
            }
        } else {
            Logger.m1250v(TAG + ":updateCachedItemWithResult", "AuthenticationResult is null, cannot update cache.");
            throw new IllegalArgumentException("result");
        }
    }

    public void updateTokenCache(AuthenticationRequest authenticationRequest, AuthenticationResult authenticationResult) throws MalformedURLException {
        if (authenticationResult != null && !StringExtensions.isNullOrBlank(authenticationResult.getAccessToken())) {
            if (!this.mUseCommonCache || UrlExtensions.isADFSAuthority(new URL(this.mAuthority))) {
                if (authenticationResult.getUserInfo() != null) {
                    if (!StringExtensions.isNullOrBlank(authenticationResult.getUserInfo().getDisplayableId())) {
                        setItemToCacheForUser(authenticationRequest.getResource(), authenticationRequest.getClientId(), authenticationResult, authenticationResult.getUserInfo().getDisplayableId());
                    }
                    if (!StringExtensions.isNullOrBlank(authenticationResult.getUserInfo().getUserId())) {
                        setItemToCacheForUser(authenticationRequest.getResource(), authenticationRequest.getClientId(), authenticationResult, authenticationResult.getUserInfo().getUserId());
                    }
                }
                setItemToCacheForUser(authenticationRequest.getResource(), authenticationRequest.getClientId(), authenticationResult, (String) null);
                return;
            }
            updateTokenCacheUsingCommonCache(authenticationRequest, authenticationResult);
        }
    }

    public void updateTokenCacheUsingCommonCache(AuthenticationRequest authenticationRequest, AuthenticationResult authenticationResult) throws MalformedURLException {
        AzureActiveDirectory azureActiveDirectory = new AzureActiveDirectory();
        AzureActiveDirectoryTokenResponse asAadTokenResponse = CoreAdapter.asAadTokenResponse(authenticationResult);
        AzureActiveDirectoryOAuth2Configuration azureActiveDirectoryOAuth2Configuration = new AzureActiveDirectoryOAuth2Configuration();
        azureActiveDirectoryOAuth2Configuration.setAuthorityHostValidationEnabled(isValidateAuthorityHost());
        String str = this.mAuthority;
        if (str != null) {
            azureActiveDirectoryOAuth2Configuration.setAuthorityUrl(new URL(str));
        }
        AzureActiveDirectoryOAuth2Strategy createOAuth2Strategy = azureActiveDirectory.createOAuth2Strategy(azureActiveDirectoryOAuth2Configuration);
        AzureActiveDirectoryAuthorizationRequest.Builder builder = new AzureActiveDirectoryAuthorizationRequest.Builder();
        ((AzureActiveDirectoryAuthorizationRequest.Builder) ((AzureActiveDirectoryAuthorizationRequest.Builder) ((AzureActiveDirectoryAuthorizationRequest.Builder) ((AzureActiveDirectoryAuthorizationRequest.Builder) builder.setClientId(authenticationRequest.getClientId())).setResource(authenticationRequest.getResource()).setScope(authenticationRequest.getResource())).setRedirectUri(authenticationRequest.getRedirectUri())).setLoginHint(authenticationRequest.getLoginHint())).setCorrelationId(authenticationRequest.getCorrelationId());
        String str2 = this.mAuthority;
        if (str2 != null) {
            builder.setAuthority(new URL(str2));
        }
        this.mCommonCache.save(createOAuth2Strategy, builder.build(), asAadTokenResponse);
    }
}
