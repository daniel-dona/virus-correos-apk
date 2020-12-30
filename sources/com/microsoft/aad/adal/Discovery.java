package com.microsoft.aad.adal;

import android.content.Context;
import android.net.Uri;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.adal.internal.net.HttpWebResponse;
import com.microsoft.identity.common.adal.internal.net.IWebRequestHandler;
import com.microsoft.identity.common.adal.internal.net.WebRequestHandler;
import com.microsoft.identity.common.adal.internal.util.HashMapExtensions;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftTokenRequest;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.AzureActiveDirectory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

/* compiled from: PG */
public class Discovery {
    public static final Set<String> AAD_WHITELISTED_HOSTS = Collections.synchronizedSet(new HashSet());
    public static final Map<String, Set<URI>> ADFS_VALIDATED_AUTHORITIES = Collections.synchronizedMap(new HashMap());
    public static final String API_VERSION_KEY = "api-version";
    public static final String API_VERSION_VALUE = "1.1";
    public static final String AUTHORIZATION_COMMON_ENDPOINT = "/common/oauth2/authorize";
    public static final String AUTHORIZATION_ENDPOINT_KEY = "authorization_endpoint";
    public static final String INSTANCE_DISCOVERY_SUFFIX = "common/discovery/instance";
    public static final String TAG = "Discovery";
    public static final String TRUSTED_QUERY_INSTANCE = "login.microsoftonline.com";
    public static volatile ReentrantLock sInstanceDiscoveryNetworkRequestLock;
    public Context mContext;
    public UUID mCorrelationId;
    public final IWebRequestHandler mWebrequestHandler = new WebRequestHandler();

    public Discovery(Context context) {
        initValidList();
        this.mContext = context;
    }

    private URL buildQueryString(String str, String str2) throws MalformedURLException {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(Oauth2.HTTPS_PROTOCOL_STRING).authority(str);
        builder.appendEncodedPath(INSTANCE_DISCOVERY_SUFFIX).appendQueryParameter("api-version", "1.1").appendQueryParameter("authorization_endpoint", str2);
        return new URL(builder.build().toString());
    }

    public static URL constructAuthorityUrl(URL url, String str) throws MalformedURLException {
        return new URL(new Uri.Builder().scheme(url.getProtocol()).authority(str).appendPath(url.getPath().replaceFirst("/", BuildConfig.FLAVOR)).build().toString());
    }

    private String getAuthorizationCommonEndpoint(URL url) {
        return new Uri.Builder().scheme(Oauth2.HTTPS_PROTOCOL_STRING).authority(url.getHost()).appendPath(AUTHORIZATION_COMMON_ENDPOINT).build().toString();
    }

    public static ReentrantLock getLock() {
        if (sInstanceDiscoveryNetworkRequestLock == null) {
            synchronized (Discovery.class) {
                if (sInstanceDiscoveryNetworkRequestLock == null) {
                    sInstanceDiscoveryNetworkRequestLock = new ReentrantLock();
                }
            }
        }
        return sInstanceDiscoveryNetworkRequestLock;
    }

    public static Set<String> getValidHosts() {
        return AAD_WHITELISTED_HOSTS;
    }

    private void initValidList() {
        if (AAD_WHITELISTED_HOSTS.isEmpty()) {
            AAD_WHITELISTED_HOSTS.add("login.windows.net");
            AAD_WHITELISTED_HOSTS.add(TRUSTED_QUERY_INSTANCE);
            AAD_WHITELISTED_HOSTS.add("login.chinacloudapi.cn");
            AAD_WHITELISTED_HOSTS.add("login.microsoftonline.de");
            AAD_WHITELISTED_HOSTS.add("login-us.microsoftonline.com");
            AAD_WHITELISTED_HOSTS.add("login.microsoftonline.us");
        }
    }

    private Map<String, String> parseResponse(HttpWebResponse httpWebResponse) throws JSONException {
        return HashMapExtensions.getJsonResponse(httpWebResponse);
    }

    private void performInstanceDiscovery(URL url, String str) throws AuthenticationException {
        if (!AuthorityValidationMetadataCache.containsAuthorityHost(url)) {
            HttpWebRequest.throwIfNetworkNotAvailable(this.mContext);
            try {
                Map<String, String> sendRequest = sendRequest(buildQueryString(str, getAuthorizationCommonEndpoint(url)));
                AzureActiveDirectory.initializeCloudMetadata(url.getHost().toLowerCase(Locale.US), sendRequest);
                AuthorityValidationMetadataCache.processInstanceDiscoveryMetadata(url, sendRequest);
                if (!AuthorityValidationMetadataCache.containsAuthorityHost(url)) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(url.getHost());
                    AuthorityValidationMetadataCache.updateInstanceDiscoveryMap(url.getHost(), new InstanceDiscoveryMetadata(url.getHost(), url.getHost(), arrayList));
                }
                if (!AuthorityValidationMetadataCache.isAuthorityValidated(url)) {
                    throw new AuthenticationException(ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_INSTANCE);
                }
            } catch (JSONException e) {
                Logger.m1246e("Discovery:performInstanceDiscovery", "Error when validating authority. ", BuildConfig.FLAVOR, ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_INSTANCE, e);
                throw new AuthenticationException(ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_INSTANCE, e.getMessage(), (Throwable) e);
            } catch (SocketTimeoutException e2) {
                Logger.m1246e("Discovery:performInstanceDiscovery", "Error when validating authority. ", BuildConfig.FLAVOR, ADALError.DEVICE_CONNECTION_IS_NOT_AVAILABLE, e2);
                throw new AuthenticationException(ADALError.DEVICE_CONNECTION_IS_NOT_AVAILABLE, e2.getMessage(), (Throwable) e2);
            } catch (IOException e3) {
                Logger.m1246e("Discovery:performInstanceDiscovery", "Error when validating authority. ", BuildConfig.FLAVOR, ADALError.IO_EXCEPTION, e3);
                throw new AuthenticationException(ADALError.IO_EXCEPTION, e3.getMessage(), (Throwable) e3);
            }
        }
    }

    private Map<String, String> sendRequest(URL url) throws IOException, JSONException, AuthenticationException {
        Logger.m1251v(TAG, "Sending discovery request to query url. ", "queryUrl: " + url, (ADALError) null);
        HashMap hashMap = new HashMap();
        hashMap.put(WebRequestHandler.HEADER_ACCEPT, WebRequestHandler.HEADER_ACCEPT_JSON);
        UUID uuid = this.mCorrelationId;
        if (uuid != null) {
            hashMap.put(MicrosoftTokenRequest.CORRELATION_ID, uuid.toString());
            hashMap.put("return-client-request-id", "true");
        }
        try {
            ClientMetrics.INSTANCE.beginClientMetricsRecord(url, this.mCorrelationId, hashMap);
            HttpWebResponse sendGet = this.mWebrequestHandler.sendGet(url, hashMap);
            ClientMetrics.INSTANCE.setLastError((String) null);
            Map<String, String> parseResponse = parseResponse(sendGet);
            if (!parseResponse.containsKey("error_codes")) {
                return parseResponse;
            }
            String str = parseResponse.get("error_codes");
            ClientMetrics.INSTANCE.setLastError(str);
            ADALError aDALError = ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_INSTANCE;
            throw new AuthenticationException(aDALError, "Fail to valid authority with errors: " + str);
        } finally {
            ClientMetrics.INSTANCE.endClientMetricsRecord("instance", this.mCorrelationId);
        }
    }

    public static void validateADFS(URL url, String str) throws AuthenticationException {
        try {
            URI uri = url.toURI();
            if (ADFS_VALIDATED_AUTHORITIES.get(str) == null || !ADFS_VALIDATED_AUTHORITIES.get(str).contains(uri)) {
                if (ADFSWebFingerValidator.realmIsTrusted(uri, new WebFingerMetadataRequestor().requestMetadata(new WebFingerMetadataRequestParameters(url, new DRSMetadataRequestor().requestMetadata(str))))) {
                    if (ADFS_VALIDATED_AUTHORITIES.get(str) == null) {
                        ADFS_VALIDATED_AUTHORITIES.put(str, new HashSet());
                    }
                    ADFS_VALIDATED_AUTHORITIES.get(str).add(uri);
                    return;
                }
                throw new AuthenticationException(ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_INSTANCE);
            }
        } catch (URISyntaxException unused) {
            throw new AuthenticationException(ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_URL, "Authority URL/URI must be RFC 2396 compliant to use AD FS validation");
        }
    }

    public static void verifyAuthorityValidInstance(URL url) throws AuthenticationException {
        if (url == null || StringExtensions.isNullOrBlank(url.getHost()) || !url.getProtocol().equals(Oauth2.HTTPS_PROTOCOL_STRING) || !StringExtensions.isNullOrBlank(url.getQuery()) || !StringExtensions.isNullOrBlank(url.getRef()) || StringExtensions.isNullOrBlank(url.getPath())) {
            throw new AuthenticationException(ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_INSTANCE);
        }
    }

    public void setCorrelationId(UUID uuid) {
        this.mCorrelationId = uuid;
    }

    public void validateAuthority(URL url) throws AuthenticationException {
        verifyAuthorityValidInstance(url);
        if (!AuthorityValidationMetadataCache.containsAuthorityHost(url)) {
            String lowerCase = url.getHost().toLowerCase(Locale.US);
            if (!AAD_WHITELISTED_HOSTS.contains(url.getHost().toLowerCase(Locale.US))) {
                lowerCase = TRUSTED_QUERY_INSTANCE;
            }
            try {
                sInstanceDiscoveryNetworkRequestLock = getLock();
                sInstanceDiscoveryNetworkRequestLock.lock();
                performInstanceDiscovery(url, lowerCase);
            } finally {
                sInstanceDiscoveryNetworkRequestLock.unlock();
            }
        }
    }

    public void validateAuthorityADFS(URL url, String str) throws AuthenticationException {
        if (!StringExtensions.isNullOrBlank(str)) {
            validateADFS(url, str);
            return;
        }
        throw new IllegalArgumentException("Cannot validate AD FS Authority with domain [null]");
    }
}
