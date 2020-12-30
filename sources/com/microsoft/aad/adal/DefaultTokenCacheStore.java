package com.microsoft.aad.adal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import com.citrix.loggersdk.BuildConfig;
import com.google.gson.Gson;
import com.microsoft.identity.common.adal.internal.cache.StorageHelper;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.internal.cache.SharedPreferencesFileManager;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
public class DefaultTokenCacheStore implements ITokenCacheStore, ITokenStoreQuery {
    public static final Object LOCK = new Object();
    public static final String SHARED_PREFERENCE_NAME = "com.microsoft.aad.adal.cache";
    public static final String TAG = "DefaultTokenCacheStore";
    public static final int TOKEN_VALIDITY_WINDOW = 10;
    @SuppressLint({"StaticFieldLeak"})
    public static StorageHelper sHelper = null;
    public static final long serialVersionUID = 1;
    public Context mContext;
    public Gson mGson;
    public SharedPreferencesFileManager mPrefs;

    @SuppressLint({"WrongConstant"})
    public DefaultTokenCacheStore(Context context) {
        MJ mj = new MJ();
        mj.a(Date.class, new DateTimeAdapter());
        this.mGson = mj.a();
        if (context != null) {
            this.mContext = context;
            if (!StringExtensions.isNullOrBlank(AuthenticationSettings.INSTANCE.getSharedPrefPackageName())) {
                try {
                    this.mContext = context.createPackageContext(AuthenticationSettings.INSTANCE.getSharedPrefPackageName(), 0);
                } catch (PackageManager.NameNotFoundException unused) {
                    StringBuilder a = Eo.a("Package name:");
                    a.append(AuthenticationSettings.INSTANCE.getSharedPrefPackageName());
                    a.append(" is not found");
                    throw new IllegalArgumentException(a.toString());
                }
            }
            this.mPrefs = new SharedPreferencesFileManager(this.mContext, "com.microsoft.aad.adal.cache");
            validateSecretKeySetting();
            return;
        }
        throw new IllegalArgumentException("Context is null");
    }

    private String decrypt(String str, String str2) {
        if (!StringExtensions.isNullOrBlank(str)) {
            try {
                return getStorageHelper().decrypt(str2);
            } catch (IOException | GeneralSecurityException e) {
                Logger.m1246e(TAG, "Decryption failure. ", BuildConfig.FLAVOR, ADALError.DECRYPTION_FAILED, e);
                removeItem(str);
                return null;
            }
        } else {
            throw new IllegalArgumentException("key is null or blank");
        }
    }

    private String encrypt(String str) {
        try {
            return getStorageHelper().encrypt(str);
        } catch (IOException | GeneralSecurityException e) {
            Logger.m1246e(TAG, "Encryption failure. ", BuildConfig.FLAVOR, ADALError.ENCRYPTION_FAILED, e);
            return null;
        }
    }

    public static Calendar getTokenValidityTime() {
        Calendar instance = Calendar.getInstance();
        instance.add(13, 10);
        return instance;
    }

    private boolean isAboutToExpire(Date date) {
        return date != null && date.before(getTokenValidityTime().getTime());
    }

    private void validateSecretKeySetting() {
        byte[] secretKeyData = AuthenticationSettings.INSTANCE.getSecretKeyData();
    }

    public void clearTokensForUser(String str) {
        for (TokenCacheItem next : getTokensForUser(str)) {
            if (!(next.getUserInfo() == null || next.getUserInfo().getUserId() == null || !next.getUserInfo().getUserId().equalsIgnoreCase(str))) {
                try {
                    removeItem(CacheKey.createCacheKey(next));
                } catch (AuthenticationException e) {
                    Logger.m1246e(TAG, "Fail to create cache key. ", BuildConfig.FLAVOR, e.getCode(), e);
                }
            }
        }
    }

    public boolean contains(String str) {
        if (str != null) {
            return this.mPrefs.contains(str);
        }
        throw new IllegalArgumentException("key");
    }

    public Iterator<TokenCacheItem> getAll() {
        Map<String, String> all = this.mPrefs.getAll();
        ArrayList arrayList = new ArrayList(all.values().size());
        for (Map.Entry next : all.entrySet()) {
            String decrypt = decrypt((String) next.getKey(), (String) next.getValue());
            if (decrypt != null) {
                Class cls = TokenCacheItem.class;
                arrayList.add((TokenCacheItem) BK.a(cls).cast(this.mGson.mo2098a(decrypt, (Type) cls)));
            }
        }
        return arrayList.iterator();
    }

    public Context getContext() {
        return this.mContext.getApplicationContext();
    }

    public TokenCacheItem getItem(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The key is null.");
        } else if (!this.mPrefs.contains(str)) {
            return null;
        } else {
            String string = this.mPrefs.getString(str);
            if (string == null) {
                string = BuildConfig.FLAVOR;
            }
            String decrypt = decrypt(str, string);
            if (decrypt == null) {
                return null;
            }
            Class cls = TokenCacheItem.class;
            return (TokenCacheItem) BK.a(cls).cast(this.mGson.mo2098a(decrypt, (Type) cls));
        }
    }

    public StorageHelper getStorageHelper() {
        synchronized (LOCK) {
            if (sHelper == null) {
                Logger.m1250v(TAG, "Started to initialize storage helper");
                sHelper = new StorageHelper(this.mContext);
                Logger.m1250v(TAG, "Finished to initialize storage helper");
            }
        }
        return sHelper;
    }

    public List<TokenCacheItem> getTokensAboutToExpire() {
        Iterator<TokenCacheItem> all = getAll();
        ArrayList arrayList = new ArrayList();
        while (all.hasNext()) {
            TokenCacheItem next = all.next();
            if (isAboutToExpire(next.getExpiresOn())) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public List<TokenCacheItem> getTokensForResource(String str) {
        Iterator<TokenCacheItem> all = getAll();
        ArrayList arrayList = new ArrayList();
        while (all.hasNext()) {
            TokenCacheItem next = all.next();
            if (str.equals(next.getResource())) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public List<TokenCacheItem> getTokensForUser(String str) {
        Iterator<TokenCacheItem> all = getAll();
        ArrayList arrayList = new ArrayList();
        while (all.hasNext()) {
            TokenCacheItem next = all.next();
            if (next.getUserInfo() != null && next.getUserInfo().getUserId().equalsIgnoreCase(str)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public Set<String> getUniqueUsersWithTokenCache() {
        Iterator<TokenCacheItem> all = getAll();
        HashSet hashSet = new HashSet();
        while (all.hasNext()) {
            TokenCacheItem next = all.next();
            if (next.getUserInfo() != null && !hashSet.contains(next.getUserInfo().getUserId())) {
                hashSet.add(next.getUserInfo().getUserId());
            }
        }
        return hashSet;
    }

    public void removeAll() {
        this.mPrefs.clear();
    }

    public void removeItem(String str) {
        if (str == null) {
            throw new IllegalArgumentException("key");
        } else if (this.mPrefs.contains(str)) {
            this.mPrefs.remove(str);
        }
    }

    public void setItem(String str, TokenCacheItem tokenCacheItem) {
        if (str == null) {
            throw new IllegalArgumentException("key");
        } else if (tokenCacheItem != null) {
            String encrypt = encrypt(this.mGson.mo2100a((Object) tokenCacheItem));
            if (encrypt != null) {
                this.mPrefs.putString(str, encrypt);
            } else {
                Logger.m1245e(TAG, "Encrypted output is null. ", BuildConfig.FLAVOR, ADALError.ENCRYPTION_FAILED);
            }
        } else {
            throw new IllegalArgumentException("item");
        }
    }
}
