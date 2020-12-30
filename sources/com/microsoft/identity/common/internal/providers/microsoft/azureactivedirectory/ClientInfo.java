package com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory;

import android.util.Base64;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.adal.internal.util.JsonExtensions;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.exception.ClientException;
import com.microsoft.identity.common.exception.ServiceException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Map;
import org.json.JSONException;

/* compiled from: PG */
public class ClientInfo implements Serializable {
    public static final String UNIQUE_IDENTIFIER = "uid";
    public static final String UNIQUE_TENANT_IDENTIFIER = "utid";
    public String mRawClientInfo;
    public String mUid;
    public String mUtid;

    public ClientInfo(String str) throws ServiceException {
        if (!StringExtensions.isNullOrBlank(str)) {
            try {
                Map<String, String> extractJsonObjectIntoMap = JsonExtensions.extractJsonObjectIntoMap(new String(Base64.decode(str, 8), Charset.forName("UTF-8")));
                this.mUid = extractJsonObjectIntoMap.get(UNIQUE_IDENTIFIER);
                this.mUtid = extractJsonObjectIntoMap.get(UNIQUE_TENANT_IDENTIFIER);
                this.mRawClientInfo = str;
            } catch (JSONException e) {
                throw new ServiceException(BuildConfig.FLAVOR, ClientException.INVALID_JWT, e);
            }
        } else {
            throw new IllegalArgumentException("ClientInfo cannot be null or blank.");
        }
    }

    public String getRawClientInfo() {
        return this.mRawClientInfo;
    }

    public String getUid() {
        return this.mUid;
    }

    public String getUtid() {
        return this.mUtid;
    }
}
