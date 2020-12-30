package com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory;

import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.internal.logging.Logger;
import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftAccount;
import com.microsoft.identity.common.internal.providers.oauth2.IDToken;
import java.util.Map;

/* compiled from: PG */
public class AzureActiveDirectoryAccount extends MicrosoftAccount {
    public static final String TAG = "AzureActiveDirectoryAccount";
    public String mIdentityProvider;

    public AzureActiveDirectoryAccount() {
    }

    public String getAuthorityType() {
        return MicrosoftAccount.AUTHORITY_TYPE_V1_V2;
    }

    public String getDisplayableId(Map<String, String> map) {
        if (!StringExtensions.isNullOrBlank(map.get("upn"))) {
            Logger.info(TAG + ":" + "getDisplayableId", "Returning upn as displayableId");
            return map.get("upn");
        } else if (StringExtensions.isNullOrBlank(map.get(IDToken.EMAIL))) {
            return null;
        } else {
            Logger.info(TAG + ":" + "getDisplayableId", "Returning email as displayableId");
            return map.get(IDToken.EMAIL);
        }
    }

    public String getIdentityProvider() {
        return this.mIdentityProvider;
    }

    public void setIdentityProvider(String str) {
        this.mIdentityProvider = str;
    }

    public String toString() {
        StringBuilder a = Eo.a("AzureActiveDirectoryAccount{} ");
        a.append(super.toString());
        a.append(", mIdentityProvider='");
        a.append(this.mIdentityProvider);
        a.append('\'');
        return a.toString();
    }

    public AzureActiveDirectoryAccount(IDToken iDToken, ClientInfo clientInfo) {
        super(iDToken, clientInfo);
        this.mIdentityProvider = iDToken.getTokenClaims().get("idp");
        String str = TAG;
        StringBuilder a = Eo.a("Init: ");
        a.append(TAG);
        Logger.verbose(str, a.toString());
    }
}
