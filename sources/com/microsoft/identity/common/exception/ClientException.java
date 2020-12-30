package com.microsoft.identity.common.exception;

/* compiled from: PG */
public class ClientException extends BaseException {
    public static final String ADFS_AUTHORITY_VALIDATION_FAILED = "adfs_authority_validation_failed";
    public static final String AUTHORITY_VALIDATION_NOT_SUPPORTED = "authority_validation_not_supported";
    public static final String CHROME_NOT_INSTALLED = "chrome_not_installed";
    public static final String DEVICE_NETWORK_NOT_AVAILABLE = "device_network_not_available";
    public static final String DUPLICATE_QUERY_PARAMETER = "duplicate_query_parameter";
    public static final String INVALID_JWT = "invalid_jwt";
    public static final String IO_ERROR = "io_error";
    public static final String JSON_PARSE_FAILURE = "json_parse_failure";
    public static final String MALFORMED_URL = "malformed_url";
    public static final String MULTIPLE_MATCHING_TOKENS_DETECTED = "multiple_matching_tokens_detected";
    public static final String NO_SUCH_ALGORITHM = "no_such_algorithm";
    public static final String SCOPE_EMPTY_OR_NULL = "scope_empty_or_null";
    public static final String STATE_MISMATCH = "state_mismatch";
    public static final String UNKNOWN_AUTHORITY = "unknown_authority";
    public static final String UNKNOWN_ERROR = "unknown_error";
    public static final String UNSUPPORTED_ENCODING = "unsupported_encoding";
    public static final String UNSUPPORTED_URL = "unsupported_url";
    public static final String USER_MISMATCH = "user_mismatch";

    public ClientException(String str) {
        super(str);
    }

    public ClientException(String str, String str2) {
        super(str, str2);
    }

    public ClientException(String str, String str2, Throwable th) {
        super(str, str2, th);
    }
}
