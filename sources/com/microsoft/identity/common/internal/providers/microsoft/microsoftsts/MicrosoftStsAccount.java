package com.microsoft.identity.common.internal.providers.microsoft.microsoftsts;

import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.internal.logging.Logger;
import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftAccount;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.ClientInfo;
import com.microsoft.identity.common.internal.providers.oauth2.IDToken;
import java.util.Map;

/* compiled from: PG */
public class MicrosoftStsAccount extends MicrosoftAccount {
    public static final String TAG = "MicrosoftStsAccount";

    public MicrosoftStsAccount() {
        String str = TAG;
        StringBuilder a = Eo.a("Init: ");
        a.append(TAG);
        Logger.verbose(str, a.toString());
    }

    public String getAuthorityType() {
        return MicrosoftAccount.AUTHORITY_TYPE_V1_V2;
    }

    public String getDisplayableId(Map<String, String> map) {
        if (!StringExtensions.isNullOrBlank(map.get(IDToken.PREFERRED_USERNAME))) {
            return map.get(IDToken.PREFERRED_USERNAME);
        }
        if (!StringExtensions.isNullOrBlank(map.get(IDToken.EMAIL))) {
            return map.get(IDToken.EMAIL);
        }
        if (!StringExtensions.isNullOrBlank(map.get("upn"))) {
            return map.get("upn");
        }
        Logger.warn(TAG, "The preferred username is not returned from the IdToken.");
        return "Missing from the token response";
    }

    public MicrosoftStsAccount(IDToken iDToken, ClientInfo clientInfo) {
        super(iDToken, clientInfo);
        String str = TAG;
        StringBuilder a = Eo.a("Init: ");
        a.append(TAG);
        Logger.verbose(str, a.toString());
    }
}
