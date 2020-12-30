package com.microsoft.aad.adal;

import android.content.Context;
import android.content.res.Resources;
import com.microsoft.identity.common.exception.BaseException;
import com.microsoft.identity.common.exception.ClientException;
import com.microsoft.identity.common.exception.ServiceException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
public enum ADALError {
    DEVELOPER_AUTHORITY_CAN_NOT_BE_VALIDED("Authority validation returned an error"),
    DEVELOPER_AUTHORITY_IS_NOT_VALID_INSTANCE("Authority is not a valid instance"),
    DEVELOPER_AUTHORITY_IS_NOT_VALID_URL("Authority url is not valid"),
    DEVELOPER_AUTHORITY_IS_EMPTY("Authority is empty"),
    DEVELOPER_ASYNC_TASK_REUSED("Async tasks can only be executed one time. They are not supposed to be reused."),
    DEVELOPER_RESOURCE_IS_EMPTY("Resource is empty"),
    DEVELOPER_CONTEXT_IS_NOT_PROVIDED("Context is not provided"),
    DEVELOPER_BEARER_HEADER_MULTIPLE_ITEMS("Key/value pair list contains redundant items in the header"),
    CALLBACK_IS_NOT_FOUND("Active callback is not found"),
    DEVELOPER_ACTIVITY_IS_NOT_RESOLVED("Activity is not resolved. Verify the activity name in your manifest file"),
    DEVELOPER_INTERNET_PERMISSION_MISSING("android.permission.INTERNET is not added to AndroidManifest file"),
    DEVELOPER_BROKER_PERMISSIONS_MISSING("GET_ACCOUNTS, MANAGE_ACCOUNTS, USE_CREDENTIALS are not added to AndroidManifest file"),
    DEVELOPER_CALLING_ON_MAIN_THREAD("Calling from main thread for background operation"),
    DEVELOPER_DIALOG_LAYOUT_INVALID("dialog_authentication.xml file has invalid elements"),
    DEVELOPER_DIALOG_INFLATION_ERROR("An error occur when attempting to inflate the authentication dialog"),
    SERVER_INVALID_REQUEST("Invalid request to server"),
    SERVER_ERROR("Server returned an error"),
    IO_EXCEPTION("I/O exception"),
    SOCKET_TIMEOUT_EXCEPTION("Socket timeout exception"),
    ARGUMENT_EXCEPTION("Invalid argument"),
    ERROR_FAILED_SSL_HANDSHAKE("Webview returned error for SSL"),
    ERROR_WEBVIEW("Webview returned an error"),
    ACTIVITY_REQUEST_INTENT_DATA_IS_NULL("Request object is null"),
    BROADCAST_RECEIVER_ERROR("Broadcast receiver has an error"),
    AUTH_FAILED("Authorization failed"),
    AUTH_REFRESH_FAILED_PROMPT_NOT_ALLOWED("Refresh token is failed and prompt is not allowed"),
    AUTH_FAILED_SERVER_ERROR("The Authorization Server returned an unrecognized response"),
    AUTH_FAILED_NO_RESOURCES("The required resource bundle could not be loaded"),
    AUTH_FAILED_NO_STATE("The authorization server response has incorrectly encoded state"),
    AUTH_FAILED_BAD_STATE("The authorization server response has no encoded state"),
    AUTH_FAILED_NO_TOKEN("The requested access token could not be found"),
    AUTH_FAILED_CANCELLED("The user cancelled the authorization request"),
    AUTH_FAILED_INTERNAL_ERROR("Invalid parameters for authorization operation"),
    AUTH_FAILED_USER_MISMATCH("User returned by service does not match the one in the request"),
    AUTH_FAILED_INTUNE_POLICY_REQUIRED("Intune App Protection Policy required"),
    DEVICE_INTERNET_IS_NOT_AVAILABLE("Internet permissions are not set for the app"),
    NO_NETWORK_CONNECTION_POWER_OPTIMIZATION("Unable to access the network due to power optimizations"),
    ON_ACTIVITY_RESULT_INTENT_NULL("onActivityResult is called with null intent data"),
    ON_ACTIVITY_RESULT_CALLBACK_NOT_FOUND("onActivityResult is called, but callback is not found"),
    DEVICE_SHARED_PREF_IS_NOT_AVAILABLE("Shared preferences are not available"),
    DEVICE_CACHE_IS_NOT_WORKING("Cache is not saving the changes."),
    DEVICE_FILE_CACHE_IS_NOT_LOADED_FROM_FILE("Cache is not loaded from File"),
    DEVICE_FILE_CACHE_IS_NOT_WRITING_TO_FILE("FileCache could not write to the File"),
    DEVICE_FILE_CACHE_FORMAT_IS_WRONG("Wrong cache file format"),
    DEVICE_CONNECTION_IS_NOT_AVAILABLE("Connection is not available"),
    DEVICE_PRNG_FIX_ERROR("PRNG fixes are not applied"),
    IDTOKEN_PARSING_FAILURE("Cannot parse IdToken"),
    DATE_PARSING_FAILURE("Cannot parse date"),
    AUTHORIZATION_CODE_NOT_EXCHANGED_FOR_TOKEN("Authorization code not exchanged for token"),
    BROADCAST_CANCEL_NOT_SUCCESSFUL("Cancel message is not successfully delivered to broadcast receiver."),
    CORRELATION_ID_FORMAT("Correlationid is not in UUID format"),
    CORRELATION_ID_NOT_MATCHING_REQUEST_RESPONSE("Correlationid provided in request is not matching the response"),
    ENCODING_IS_NOT_SUPPORTED("Encoding format is not supported"),
    SERVER_INVALID_JSON_RESPONSE("Server returned invalid JSON response"),
    AUTH_REFRESH_FAILED("Refresh token request failed"),
    ENCRYPTION_FAILED("Encryption failed"),
    DECRYPTION_FAILED("Decryption failed"),
    ANDROIDKEYSTORE_FAILED("Failed to use AndroidKeyStore"),
    ANDROIDKEYSTORE_KEYPAIR_GENERATOR_FAILED("Failed to use KeyPairGeneratorSpec"),
    DISCOVERY_NOT_SUPPORTED("Authority validation is not supported for ADFS authority."),
    BROKER_PACKAGE_NAME_NOT_FOUND("Broker is not installed in your system"),
    BROKER_PRT_FAILED("Broker failed to get PRT"),
    BROKER_AUTHENTICATOR_NOT_RESPONDING("Authenticator is not responding"),
    BROKER_AUTHENTICATOR_OPERATION_CANCEL("Operation was cancelled by the broker"),
    BROKER_AUTHENTICATOR_ERROR_GETAUTHTOKEN("Authenticator error"),
    BROKER_AUTHENTICATOR_BAD_ARGUMENTS("Invalid arguments for Authenticator request"),
    BROKER_AUTHENTICATOR_BAD_AUTHENTICATION("Authentication request failed"),
    BROKER_AUTHENTICATOR_UNSUPPORTED_OPERATION("Authenticator is not supporting this operation"),
    BROKER_AUTHENTICATOR_IO_EXCEPTION("Authenticator has IO Exception"),
    BROKER_AUTHENTICATOR_EXCEPTION("Authenticator has an Exception"),
    BROKER_VERIFICATION_FAILED("Signature could not be verified"),
    PACKAGE_NAME_NOT_FOUND("Package name is not resolved"),
    DIGEST_ERROR("Error in generating hash with MessageDigest"),
    BROKER_AUTHENTICATION_REQUEST_IS_NULL("Authentication request is null"),
    BROKER_APP_VERIFICATION_FAILED("Calling app could not be verified"),
    BROKER_ACTIVITY_INFO_NOT_FOUND("Activity information is not retrieved"),
    BROKER_SIGNATURE_NOT_SAVED("Signature is not saved"),
    BROKER_ACCOUNT_DEVICE_REGISTRY_FAILURE("Device registration failed"),
    BROKER_ACCOUNT_FAILED_RESOLVED_INTERRUPT("Token request after resolving web interrupt failed"),
    DEVICE_NO_SUCH_ALGORITHM("Device does not support the algorithm"),
    DEVICE_ALGORITHM_PADDING_EXCEPTION("Requested padding is not available"),
    APP_PACKAGE_NAME_NOT_FOUND("App package name is not found in the package manager"),
    ENCRYPTION_ERROR("Encryption related error"),
    BROKER_ACTIVITY_IS_NOT_RESOLVED("Broker activity is not resolved"),
    BROKER_ACTIVITY_INVALID_REQUEST("Invalid request parameters"),
    BROKER_ACCOUNT_SAVE_FAILED("Broker could not save the new account"),
    BROKER_ACCOUNT_DOES_NOT_EXIST("Broker account does not exist"),
    BROKER_SINGLE_USER_EXPECTED("Single user is expected"),
    KEY_CHAIN_PRIVATE_KEY_EXCEPTION("Key Chain private key exception"),
    SIGNATURE_EXCEPTION("Signature exception"),
    DEVICE_CERTIFICATE_RESPONSE_FAILED("It is failed to create device certificate response"),
    WEBVIEW_RETURNED_AUTHENTICATION_EXCEPTION("Webview returned Authentication Exception"),
    WEBVIEW_RETURNED_INVALID_AUTHENTICATION_EXCEPTION("Webview returned invalid or null Authentication Exception"),
    WEBVIEW_RETURNED_EMPTY_REDIRECT_URL("Webview returned empty redirect url"),
    WEBVIEW_REDIRECTURL_NOT_SSL_PROTECTED("The webview was redirected to an unsafe URL"),
    DEVICE_CERTIFICATE_API_EXCEPTION("Device certificate API has exception"),
    DEVICE_CERTIFICATE_REQUEST_INVALID("Device certificate request is valid"),
    RESOURCE_NOT_FOUND("Resource is not found in your project. Please include resource files."),
    CERTIFICATE_ENCODING_ERROR("Certificate encoding is not generated"),
    ERROR_SILENT_REQUEST("Error in silent token request"),
    DEVELOPER_REDIRECTURI_INVALID("The redirectUri for broker is invalid"),
    DEVICE_CHALLENGE_FAILURE("Device challenge failure"),
    RESOURCE_AUTHENTICATION_CHALLENGE_FAILURE("Resource authentication challenge failure"),
    INVALID_TOKEN_CACHE_ITEM("Invalid token cache item"),
    FAIL_TO_EXPORT("Fail to export"),
    FAIL_TO_IMPORT("Fail to import"),
    INCOMPATIBLE_BLOB_VERSION("Fail to deserialize because the blob version is incompatible"),
    TOKEN_CACHE_ITEM_NOT_FOUND("Token cache item is not found"),
    JSON_PARSE_ERROR("Fail to parse JSON"),
    DRS_METADATA_URL_INVALID("Malformed DRS metadata URL"),
    DRS_FAILED_SERVER_ERROR("Enrollment server returned an unrecognized response"),
    DRS_DISCOVERY_FAILED_UNKNOWN_HOST("DRS discovery failed: unknown host"),
    BROKER_APP_INSTALLATION_STARTED("Broker app installation started"),
    PARALLEL_UI_REQUESTS("Parallel UI requests, cancelling and only one request will be allowed."),
    X_MS_CLITELEM_VERSION_UNRECOGNIZED("Unrecognized x-ms-clitelem header version"),
    X_MS_CLITELEM_MALFORMED("Malformed x-ms-clitelem header"),
    DEVICE_CERT_PROXY_UNINITIALIZED("WPJ Device Certificate Proxy class was not initialized."),
    BROKER_BIND_SERVICE_FAILED("Failed to bind the service in broker app"),
    MAPPING_FAILURE("Common core returned an exception code that ADAL cannot parse");
    
    public static final Map<String, ADALError> exceptionMap = null;
    public String mDescription;

    /* access modifiers changed from: public */
    static {
        exceptionMap = new HashMap();
        exceptionMap.put("android_keystore_failed", ANDROIDKEYSTORE_FAILED);
        exceptionMap.put("authority_url_not_valid", DEVELOPER_AUTHORITY_IS_NOT_VALID_INSTANCE);
        exceptionMap.put(ClientException.AUTHORITY_VALIDATION_NOT_SUPPORTED, DEVELOPER_AUTHORITY_CAN_NOT_BE_VALIDED);
        exceptionMap.put("decryption_error", DECRYPTION_FAILED);
        exceptionMap.put(ClientException.DEVICE_NETWORK_NOT_AVAILABLE, DEVICE_CONNECTION_IS_NOT_AVAILABLE);
        exceptionMap.put("device_network_not_available_doze_mode", NO_NETWORK_CONNECTION_POWER_OPTIMIZATION);
        exceptionMap.put("encryption_error", ENCRYPTION_ERROR);
        exceptionMap.put(ClientException.INVALID_JWT, JSON_PARSE_ERROR);
        exceptionMap.put(ClientException.IO_ERROR, IO_EXCEPTION);
        exceptionMap.put("socket_timeout", SOCKET_TIMEOUT_EXCEPTION);
        exceptionMap.put(ClientException.JSON_PARSE_FAILURE, JSON_PARSE_ERROR);
        exceptionMap.put(ClientException.MALFORMED_URL, DEVELOPER_AUTHORITY_IS_NOT_VALID_URL);
        exceptionMap.put(ClientException.MULTIPLE_MATCHING_TOKENS_DETECTED, AUTH_FAILED_USER_MISMATCH);
        exceptionMap.put(ClientException.USER_MISMATCH, AUTH_FAILED_USER_MISMATCH);
        exceptionMap.put(ClientException.NO_SUCH_ALGORITHM, DEVICE_NO_SUCH_ALGORITHM);
        exceptionMap.put(ClientException.UNSUPPORTED_ENCODING, ENCODING_IS_NOT_SUPPORTED);
        exceptionMap.put(ServiceException.ACCESS_DENIED, AUTH_REFRESH_FAILED_PROMPT_NOT_ALLOWED);
        exceptionMap.put(ServiceException.INVALID_INSTANCE, AUTH_REFRESH_FAILED_PROMPT_NOT_ALLOWED);
        exceptionMap.put(ServiceException.INVALID_REQUEST, SERVER_INVALID_REQUEST);
        exceptionMap.put(ServiceException.REQUEST_TIMEOUT, SERVER_ERROR);
        exceptionMap.put(ServiceException.SERVICE_NOT_AVAILABLE, SERVER_ERROR);
        exceptionMap.put("unknown_error", SERVER_ERROR);
        exceptionMap.put("Package name is not resolved", PACKAGE_NAME_NOT_FOUND);
        exceptionMap.put("Calling app could not be verified", BROKER_APP_VERIFICATION_FAILED);
        exceptionMap.put("App package name is not found in the package manager", APP_PACKAGE_NAME_NOT_FOUND);
    }

    /* access modifiers changed from: public */
    ADALError(String str) {
        this.mDescription = str;
    }

    public static AuthenticationException fromCommon(BaseException baseException) {
        ADALError aDALError = exceptionMap.get(baseException.getErrorCode());
        if (aDALError == null) {
            return new AuthenticationException(MAPPING_FAILURE, baseException.getMessage(), (Throwable) baseException);
        }
        return new AuthenticationException(aDALError, baseException.getMessage(), (Throwable) baseException);
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getLocalizedDescription(Context context) {
        if (context == null) {
            return this.mDescription;
        }
        Resources resources = new Resources(context.getAssets(), context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
        return resources.getString(gs1.b(resources, name(), "string", context.getPackageName()));
    }
}
