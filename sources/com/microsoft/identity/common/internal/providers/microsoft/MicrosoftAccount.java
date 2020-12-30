package com.microsoft.identity.common.internal.providers.microsoft;

import android.net.Uri;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.BaseAccount;
import com.microsoft.identity.common.adal.internal.util.DateExtensions;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.internal.cache.SchemaUtil;
import com.microsoft.identity.common.internal.logging.Logger;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.ClientInfo;
import com.microsoft.identity.common.internal.providers.oauth2.IDToken;
import com.microsoft.identity.common.internal.util.StringUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
public abstract class MicrosoftAccount extends BaseAccount {
    public static final String AUTHORITY_TYPE_V1_V2 = "MSSTS";
    public static final String TAG = "MicrosoftAccount";
    public String mDisplayableId;
    public String mEnvironment;
    public String mFamilyName;
    public String mGivenName;
    public IDToken mIDToken;
    public String mMiddleName;
    public String mName;
    public Uri mPasswordChangeUrl;
    public Date mPasswordExpiresOn;
    public String mRawClientInfo;
    public String mTenantId;
    public String mUid;
    public String mUniqueId;
    public String mUtid;

    public MicrosoftAccount() {
        String str = TAG;
        StringBuilder a = Eo.a("Init: ");
        a.append(TAG);
        Logger.verbose(str, a.toString());
    }

    private String getUniqueId(Map<String, String> map) {
        if (!StringExtensions.isNullOrBlank(map.get("oid"))) {
            Logger.info(TAG + ":" + "getUniqueId", "Using ObjectId as uniqueId");
            return map.get("oid");
        } else if (StringExtensions.isNullOrBlank(map.get("sub"))) {
            return null;
        } else {
            Logger.info(TAG + ":" + "getUniqueId", "Using Subject as uniqueId");
            return map.get("sub");
        }
    }

    public String getAlternativeAccountId() {
        return SchemaUtil.getAlternativeAccountId(this.mIDToken);
    }

    public String getAvatarUrl() {
        return SchemaUtil.getAvatarUrl(this.mIDToken);
    }

    public List<String> getCacheIdentifiers() {
        ArrayList arrayList = new ArrayList();
        String str = this.mDisplayableId;
        if (str != null) {
            arrayList.add(str);
        }
        String str2 = this.mUniqueId;
        if (str2 != null) {
            arrayList.add(str2);
        }
        if (getUniqueIdentifier() != null) {
            arrayList.add(getUniqueIdentifier());
        }
        return arrayList;
    }

    public String getClientInfo() {
        return this.mRawClientInfo;
    }

    public String getDisplayableId() {
        return this.mDisplayableId;
    }

    public abstract String getDisplayableId(Map<String, String> map);

    public String getEnvironment() {
        return this.mEnvironment;
    }

    public String getFamilyName() {
        return this.mFamilyName;
    }

    public String getFirstName() {
        return this.mGivenName;
    }

    public String getHomeAccountId() {
        return getUid() + "." + getUtid();
    }

    public IDToken getIDToken() {
        return this.mIDToken;
    }

    public String getLocalAccountId() {
        return getUserId();
    }

    public String getMiddleName() {
        return this.mMiddleName;
    }

    public String getName() {
        return this.mName;
    }

    public Uri getPasswordChangeUrl() {
        return this.mPasswordChangeUrl;
    }

    public Date getPasswordExpiresOn() {
        return DateExtensions.createCopy(this.mPasswordExpiresOn);
    }

    public String getRealm() {
        return this.mTenantId;
    }

    public String getUid() {
        return this.mUid;
    }

    public String getUniqueIdentifier() {
        return StringExtensions.base64UrlEncodeToString(this.mUid) + "." + StringExtensions.base64UrlEncodeToString(this.mUtid);
    }

    public String getUserId() {
        return this.mUniqueId;
    }

    public String getUsername() {
        return getDisplayableId();
    }

    public String getUtid() {
        return this.mUtid;
    }

    public void setDisplayableId(String str) {
        this.mDisplayableId = str;
    }

    public void setEnvironment(String str) {
        this.mEnvironment = str;
    }

    public void setFamilyName(String str) {
        this.mFamilyName = str;
    }

    public void setFirstName(String str) {
        this.mGivenName = str;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setUid(String str) {
        this.mUid = str;
    }

    public void setUserId(String str) {
        this.mUniqueId = str;
    }

    public void setUtid(String str) {
        this.mUtid = str;
    }

    public String toString() {
        StringBuilder a = Eo.a("MicrosoftAccount{mDisplayableId='");
        Eo.a(a, this.mDisplayableId, '\'', ", mUniqueId='");
        Eo.a(a, this.mUniqueId, '\'', ", mName='");
        Eo.a(a, this.mName, '\'', ", mUid='");
        Eo.a(a, this.mUid, '\'', ", mUtid='");
        Eo.a(a, this.mUtid, '\'', ", mIDToken=");
        a.append(this.mIDToken);
        a.append(", mPasswordChangeUrl=");
        a.append(this.mPasswordChangeUrl);
        a.append(", mPasswordExpiresOn=");
        a.append(this.mPasswordExpiresOn);
        a.append(", mTenantId='");
        Eo.a(a, this.mTenantId, '\'', ", mGivenName='");
        Eo.a(a, this.mGivenName, '\'', ", mFamilyName='");
        Eo.a(a, this.mFamilyName, '\'', "} ");
        a.append(super.toString());
        return a.toString();
    }

    public MicrosoftAccount(IDToken iDToken, ClientInfo clientInfo) {
        String str = TAG;
        StringBuilder a = Eo.a("Init: ");
        a.append(TAG);
        Logger.verbose(str, a.toString());
        this.mIDToken = iDToken;
        this.mRawClientInfo = clientInfo.getRawClientInfo();
        Map<String, String> tokenClaims = iDToken.getTokenClaims();
        this.mUniqueId = getUniqueId(tokenClaims);
        this.mDisplayableId = getDisplayableId(tokenClaims);
        this.mName = tokenClaims.get("name");
        this.mGivenName = tokenClaims.get(IDToken.GIVEN_NAME);
        this.mFamilyName = tokenClaims.get(IDToken.FAMILY_NAME);
        this.mMiddleName = tokenClaims.get(IDToken.MIDDLE_NAME);
        if (!StringUtil.isEmpty(tokenClaims.get("tid"))) {
            this.mTenantId = tokenClaims.get("tid");
        } else if (!StringUtil.isEmpty(clientInfo.getUtid())) {
            Logger.warnPII(TAG, "realm is not returned from server. Use utid as realm.");
            this.mTenantId = clientInfo.getUtid();
        } else {
            Logger.warnPII(TAG, "realm and utid is not returned from server. Use empty string as default tid.");
            this.mTenantId = BuildConfig.FLAVOR;
        }
        this.mUid = clientInfo.getUid();
        this.mUtid = clientInfo.getUtid();
        long parseLong = !StringExtensions.isNullOrBlank(tokenClaims.get("pwd_exp")) ? Long.parseLong(tokenClaims.get("pwd_exp")) : 0;
        if (parseLong > 0) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.add(13, (int) parseLong);
            this.mPasswordExpiresOn = gregorianCalendar.getTime();
        }
        this.mPasswordChangeUrl = null;
        if (!StringExtensions.isNullOrBlank(tokenClaims.get("pwd_url"))) {
            this.mPasswordChangeUrl = Uri.parse(tokenClaims.get("pwd_url"));
        }
    }
}
