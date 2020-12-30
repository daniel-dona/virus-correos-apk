package com.microsoft.aad.adal;

import com.google.gson.JsonSyntaxException;
import com.microsoft.identity.common.adal.internal.net.HttpWebResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/* compiled from: PG */
public class WebFingerMetadataRequestor extends AbstractMetadataRequestor<WebFingerMetadata, WebFingerMetadataRequestParameters> {
    public static final String TAG = "WebFingerMetadataRequestor";

    public static URL buildWebFingerUrl(URL url, DRSMetadata dRSMetadata) throws MalformedURLException {
        URL url2 = new URL(dRSMetadata.getIdentityProviderService().getPassiveAuthEndpoint());
        String str = "https://" + url2.getHost() + "/.well-known/webfinger?resource=" + url.toString();
        Logger.m1248i(TAG, "Validator will use WebFinger URL. ", "WebFinger URL: " + str);
        return new URL(str);
    }

    public WebFingerMetadata parseMetadata(HttpWebResponse httpWebResponse) throws AuthenticationException {
        Logger.m1250v(TAG, "Parsing WebFinger response.");
        try {
            return (WebFingerMetadata) parser().mo2097a(httpWebResponse.getBody(), WebFingerMetadata.class);
        } catch (JsonSyntaxException unused) {
            throw new AuthenticationException(ADALError.JSON_PARSE_ERROR);
        }
    }

    public WebFingerMetadata requestMetadata(WebFingerMetadataRequestParameters webFingerMetadataRequestParameters) throws AuthenticationException {
        URL domain = webFingerMetadataRequestParameters.getDomain();
        DRSMetadata drsMetadata = webFingerMetadataRequestParameters.getDrsMetadata();
        String str = TAG;
        StringBuilder a = Eo.a("Auth endpoint: ");
        a.append(domain.toString());
        Logger.m1248i(str, "Validating authority for auth endpoint. ", a.toString());
        try {
            HttpWebResponse sendGet = getWebrequestHandler().sendGet(buildWebFingerUrl(domain, drsMetadata), new HashMap());
            if (200 == sendGet.getStatusCode()) {
                return parseMetadata(sendGet);
            }
            throw new AuthenticationException(ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_INSTANCE);
        } catch (IOException e) {
            throw new AuthenticationException(ADALError.IO_EXCEPTION, "Unexpected error", (Throwable) e);
        }
    }
}
