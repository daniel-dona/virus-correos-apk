package com.citrix.MAM.Android.ManagedAppHelper.Interface;

/* compiled from: PG */
public class MAMAppInfo {
    public static final String ACTION_AG_CERT_REVOKED = "AGCertRevoked";
    public static final String ACTION_COOKIE_EXPIRES = "AGCookieExpired";
    public static final String ACTION_DICTIONARY_NOTIFICATION = "DictionaryNotice";
    public static final String ACTION_MITMPROXYINITFAILED = "MITMProxyInitFailed";
    public static final String ACTION_MITM_RESTARTED = "MITMRestarted";
    public static final String ACTION_POLICIES_UPDATED = "PoliciesUpdated";
    public static final String ACTION_PROXY_INIT = "com.citrix.MAM.intent.action.PROXYINIT";
    public static final String ACTION_USER_CHANGED = "UserChanged";
    public static final String ACTION_VPN_BAD_COOKIES = "VpnBadCookies";
    public static final String ACTION_VPN_DISCONNECTED = "VpnDisconnected";
    public static final String ACTION_VPN_ERROR = "VpnError";
    public static final String ACTION_VPN_RESTARTED = "VpnRestarted";
    public static final String ACTION_VPN_RESTARTING = "VpnRestarting";
    public static final String ACTION_VPN_STARTED = "VpnStarted";
    public static final String ACTION_VPN_TIMEOUT = "VpnTimeout";
    public static final String ACTION_WIPE_DATA = "com.citrix.MAM.intent.action.WIPE";
    public static final String AUTHENTICATION_TYPE = "AuthenticationType";
    public static final String BUGFIX_1824_SPLIT_TUNNEL = "xmhelp-1824-split-tunnel";
    public static final String CITRIX_RECEIVER_MDX_SERVICE_CLASS = "com.citrix.MAM.Android.ManagedAppHelper.ManagedAppHelperService";
    public static final String CITRIX_RECEIVER_PACKAGE = "com.citrix.Receiver";
    public static final String COLUMN_USERNAME = "Username";
    public static final String CTX_APP_MANAGER = "com.citrix.MAM.Android.ManagedApp.CtxAppManager";
    public static final String EXTRA_CLIPDATA = "ExtraClipData";
    public static final String EXTRA_CLIPTEXT = "ExtraClipText";
    public static final String EXTRA_CLIPTIME = "ExtraClipTime";
    public static final String EXTRA_DNS_IP = "ExtraDnsIP";
    public static final String EXTRA_HASCLIP = "ExtraHasClip";
    public static final String EXTRA_SECURITYGROUP = "ExtraSecurityGroup";
    public static final String GEOFENCE_CURRENT_LATITUDE = "GeofenceCurrentLatitude";
    public static final String GEOFENCE_CURRENT_LONGITUDE = "GeofenceCurrentLongitude";
    public static final int GEOFENCE_ERROR = 5;
    public static final String GEOFENCE_ERROR_STATE = "GeofenceState";
    public static final int GET_LOCATION_SUCCESS = 7;
    public static final String INTENT_EXTRA_BLOCK_DNS_FROM_UNMANAGED_APPS_IN_FULL_VPN = "BlockDnsFromUnmanagedAppsInFullVpn";
    public static final String INTENT_EXTRA_DO_DEVICE_CHECK = "DoDeviceCheck";
    public static final String INTENT_EXTRA_ENCRYPTION_KEYS = "GetEncryptionKeys";
    public static final String INTENT_EXTRA_FORCE_LOGON = "Force";
    public static final String INTENT_EXTRA_GET_POLICIES = "GetPolicies";
    public static final String INTENT_EXTRA_GET_SF_CLIENT_CERTIFICATES = "GetSFClientCertificate";
    public static final String INTENT_EXTRA_GET_SHAREFILE_CONNECTOR = "GetShareFileConnector";
    public static final String INTENT_EXTRA_GET_STA_TICKET = "GetSTATicket";
    public static final String INTENT_EXTRA_ISSUBSCRIBE = "Subscribe";
    public static final String INTENT_EXTRA_LOGON_ATTEMPTS = "LogonAttempts";
    public static final String INTENT_EXTRA_LOGON_REASON = "LogonReason";
    public static final String INTENT_EXTRA_PROFILE_ID = "ProfileId";
    public static final String INTENT_EXTRA_REFRESH = "Refresh";
    public static final String INTENT_EXTRA_REQUEST_CODE = "RequestCode";
    public static final String INTENT_EXTRA_SHAREFILE_DEVICE_ID = "ShareFileDeviceId";
    public static final String INTENT_EXTRA_UPDATE = "Update";
    public static final String INTENT_EXTRA_WRAPPER_VERSION = "wrapperversion";
    public static final int INTENT_LOGON = 11;
    public static final int INTENT_STATE_CHECK = 12;
    public static final int INVALID_PROFILE_ID = -1;
    public static final String KEY_ACCOUNT_CONFIGURED = "accountConfigured";
    public static final String KEY_ACCOUNT_LOGGEDOFF = "acouuntLoggedOff";
    public static final String KEY_AG_ADDRESS = "ADDRESS";
    public static final String KEY_AG_FQDN_LIST = "AG_FQDN_LIST";
    public static final String KEY_AG_IP = "gatewayIP";
    public static final String KEY_ANALYTICS_CLIENT_ID = "analyticsClientId";
    public static final String KEY_APP_LOCKED_REASON = "Reason";
    public static final String KEY_APP_NAME = "AppName";
    public static final String KEY_APP_PACKAGE = "AppPackage";
    public static final String KEY_APP_PKGID = "PackageID";
    public static final String KEY_APP_STATE = "AppState";
    public static final String KEY_AUTH_RESULT = "AuthResult";
    public static final String KEY_BLOCK_DNS_FROM_UNMANAGED_APPS_IN_FULL_VPN = "BlockDnsFromUnmanagedAppInFullVpn";
    public static final String KEY_CERT_ALIAS = "CertAlias";
    public static final String KEY_CERT_ALIAS_LEGACY = "certAlias";
    public static final String KEY_CERT_BLOB = "CertBlob";
    public static final String KEY_CERT_CHAIN = "certChain";
    public static final String KEY_CERT_ID = "CertID";
    public static final String KEY_CERT_ID_LEGACY = "certID";
    public static final String KEY_CERT_PRIVATE_KEY = "certPrivateKey";
    public static final String KEY_CERT_TYPE = "CertType";
    public static final String KEY_CHKSUM = "Checksum";
    public static final String KEY_CLIENT_PROPERTIES = "ClientProperties";
    public static final String KEY_CLIPBOARD_ACTION = "ClipboardAction";
    public static final String KEY_COMMAND = "Command";
    public static final String KEY_CONTAINER_SELF_DESTRUCT_PERIOD = "ContainerSelfDestructPeriod";
    public static final String KEY_CRASH_REPORTING = "enableCrashReporting";
    public static final String KEY_CUSTOMER_ENVIRONMENT = "customer.environment";
    public static final String KEY_DATA_SAML_RESULT = "DataSAMLResult";
    public static final String KEY_DEVICE_CHECK_CODE = "DeviceCheckCode";
    public static final String KEY_DEVICE_MANAGEMENT_ID = "DeviceManagementId";
    public static final String KEY_DEVICE_STATE = "DeviceState";
    public static final String KEY_DICTIONARY_NAME = "name";
    public static final String KEY_DICTIONARY_WHAT = "what";
    public static final String KEY_ENABLE_LOGGING = "enableLogging";
    public static final String KEY_ENTERPRISE_LOGON_REASON = "enterpriseLogonReason";
    public static final String KEY_GATEWAY_SUPPORTS_FULL_VPN = "GatewaySupportsFullVpn";
    public static final String KEY_INACTIVITY_TIMER = "InactivityTimer";
    public static final String KEY_IS_UPGRADING = "isupgrading";
    public static final String KEY_LAST_STATE_CHECK_TIME = "LastStateCheckTime";
    public static final String KEY_LAST_USER_INTERACTION = "lastUserInteraction";
    public static final String KEY_LOGGED_ON = "LoggedOn";
    public static final String KEY_LOGONOFF_TIME = "LogOnOffTime";
    public static final String KEY_LOGON_ATTEMPTS = "logonAttempts";
    public static final String KEY_LOG_FILE_COUNT = "logFileCount";
    public static final String KEY_LOG_FILE_SIZE = "logFileSize";
    public static final String KEY_LOG_LEVEL = "logLevel";
    public static final String KEY_LOG_TARGET = "logTarget";
    public static final String KEY_MAM_APP_PACKAGES = "MamAppPackages";
    public static final String KEY_MAM_ENCRYPTION = "Mam_Encryption_Key";
    public static final String KEY_MAM_ENCRYPTION1 = "Mam_Encryption_Key1";
    public static final String KEY_MAM_ENCRYPTION2 = "Mam_Encryption_Key2";
    public static final String KEY_MAX_LOGON_ATTEMPTS = "maxLogonAttempts";
    public static final String KEY_MIGRATION_STATUS = "MigrationStatus";
    public static final String KEY_MOBILE_APP_AGGREGATION = "MobileAppAggregation";
    public static final String KEY_NETWORK_LOCATION = "NetworkLocation";
    public static final String KEY_OPT_IN_PROFILE_HASH = "OPT_IN_PROFILE_HASH";
    public static final String KEY_OPT_IN_PROFILE_ID = "OPT_IN_PROFILE_ID";
    public static final String KEY_PKCS_BLOB_LENGTH = "pkcsbloblength";
    public static final String KEY_POLICIES = "Policies";
    public static final String KEY_POLICIES_TIME = "PolicyTime";
    public static final String KEY_PROPERTY_DO_NOT_TUNNEL = "TUNNEL_EXCLUDE_DOMAINS";
    public static final String KEY_REFRESH_RESULT = "RefreshResult";
    public static final String KEY_ROUTE_LIST = "RouteList";
    public static final String KEY_SERVER_TYPE = "ServerType";
    public static final String KEY_SHARED_DEVICE_MODE = "SharedDeviceMode";
    public static final String KEY_SPLIT_DNS = "SplitDNS";
    public static final String KEY_SPLIT_TUNNEL_OFF = "SplitTunnelOff";
    public static final String KEY_SPLIT_TUNNEL_REVERSE = "SplitTunnelReverse";
    public static final String KEY_STA_TICKET = "STATicket";
    public static final String KEY_SUBSCRIPTION_STATE = "SubscriptionState";
    public static final String KEY_SUFFIX_LIST = "SuffixList";
    public static final String KEY_SWITCH_ENTERPRISE_LOGON_REASON = "enterpriseLogonReason";
    public static final String KEY_SWITCH_GATEWAYS_REQUIRED = "switchGatewaysRequired";
    public static final String KEY_TRANSLATED_URL = "translatedurl";
    public static final String KEY_TUNNEL_EXCLUDE_DOMAINS = "ExcludeDomains";
    public static final String KEY_TUNNEL_EXCLUDE_IPS = "ExcludeIPs";
    public static final String KEY_UE_NEEDED_FOR_ENCRYPTION = "UE_Needed_For_Encryption";
    public static final String KEY_UPDATE_AVAIL_TIME = "UpdateTime";
    public static final String KEY_USERNAME = "UserName";
    public static final String KEY_USER_CHALLENGED = "userChallenged";
    public static final String KEY_USING_AG = "UsingAG";
    public static final String KEY_VERSION = "Version";
    public static final String KEY_VPN_REQUIRED = "VpnRequired";
    public static final String KEY_VPN_TRUST_FAILED = "VpnTrust";
    public static final String KEY_WORKSPACE_STORE_ID = "StoreID";
    public static final String KEY_XM_SUPPORT_EMAIL_ADDRESS = "XMSupportEmailAddress";
    public static final int LOCATION_NOT_AVAILABLE = 8;
    public static final int LOCATION_SERVICES_DISABLED = 6;
    public static final String MDX_CREDENTIALS_DICTIONARY_NAME = "MDXCredentialsDictionary";
    public static final String MDX_USER_DICTIONARY_NAME = "MDXUserDictionary";
    public static final String SERVER_TYPE_INVALID = "INVALID";
    public static final String SERVER_TYPE_MDM_ONLY = "MDM_TYPE";
    public static final int SESSION_ERROR_DELIVERY_SERVICES_EXCEPTION = 2;
    public static final int SESSION_ERROR_FAILED = 1;
    public static final int SESSION_ERROR_MALFORMED_URL = 3;
    public static final int SESSION_ERROR_MISSING_AG_ADDRESS = 4;
    public static final int SESSION_ERROR_NETWORK = -1;
    public static final int SESSION_ERROR_SUCCESS = 0;
    public static final String VALUE_CERT_TYPE_BLOB = "Blob";
    public static final String VALUE_CL_CLEAR = "Clear";
    public static final String VALUE_CL_GET_CLIPDATA = "GetClipData";
    public static final String VALUE_CL_GET_CLIPTEXT = "GetClipText";
    public static final String VALUE_CL_HAS_CLIPDATA = "HasClipData";
    public static final String VALUE_CL_HAS_CLIPTEXT = "HasClipText";
    public static final String VALUE_CL_PUT_CLIPDATA = "PutClipData";
    public static final String VALUE_CL_PUT_CLIPTEXT = "PutClipText";
    public static final String VALUE_COMMAND_CLIPBOARD_ACTION = "Clipboard";
    public static final String VALUE_COMMAND_GET_APP_INFO = "GetAppInfo";
    public static final String VALUE_COMMAND_LOCK_APP = "LockApp";
    public static final String VALUE_COMMAND_LOGON = "Logon";
    public static final String VALUE_CUSTOMER_ENV_COMMERCIAL = "commercial";
    public static final String VALUE_CUSTOMER_ENV_US_GOVT = "us_government";
    public static final int VALUE_VERSION = 3;
    public static final long VALUE_WRAPPER_VERSION_KRATOS = 1;

    /* compiled from: PG */
    public enum AppState {
        Enable,
        Disabled,
        NotFound,
        ServicesDisabled,
        Unknown;

        public static AppState fromInt(int i) {
            return i != 0 ? i != 1 ? i != 2 ? i != 3 ? Unknown : ServicesDisabled : NotFound : Disabled : Enable;
        }

        public static AppState fromString(String str) {
            return "Enable".equalsIgnoreCase(str) ? Enable : "Disabled".equalsIgnoreCase(str) ? Disabled : "ServicesDisabled".equals(str) ? ServicesDisabled : Unknown;
        }
    }

    /* compiled from: PG */
    public enum AuthResult {
        SUCCESSFUL,
        NOT_AUTHENTICATED,
        CANCELLED,
        APPLICATION_INVALID,
        PROFILE_MISSING,
        PROFILE_INVALID,
        NETWORK_UNREACHABLE,
        NETWORK_LOCATION_ERROR,
        GATEWAY_ERROR,
        STOREFRONT_ERROR,
        CREDENTIAL_ERROR,
        NETWORK_UNAVAILABLE
    }

    /* compiled from: PG */
    public enum AuthenticationType {
        ONLINE,
        OFFLINE
    }

    /* compiled from: PG */
    public enum DeviceCheckCode {
        DEVICE_CHECK_UNKNOWN,
        DEVICE_CHECK_OK,
        DEVICE_CHECK_FAILED,
        DEVICE_CHECK_LOCK,
        DEVICE_CHECK_WIPE,
        DEVICE_CHECK_CANCELLED,
        DEVICE_CHECK_INVALID
    }

    /* compiled from: PG */
    public enum DeviceState {
        WIPE,
        LOCK,
        OK,
        UNKNOWN;

        public static DeviceState fromInt(int i) {
            return i != 0 ? i != 1 ? i != 2 ? UNKNOWN : OK : LOCK : WIPE;
        }

        public static DeviceState fromString(String str) {
            return "WIPE".equalsIgnoreCase(str) ? WIPE : "LOCK".equalsIgnoreCase(str) ? LOCK : "OK".equalsIgnoreCase(str) ? OK : UNKNOWN;
        }
    }

    /* compiled from: PG */
    public enum NetworkLocation {
        Unknown,
        Inside,
        Outside,
        Paywall,
        Found;

        public static NetworkLocation fromString(String str) {
            return "Inside".equalsIgnoreCase(str) ? Inside : "Outside".equalsIgnoreCase(str) ? Outside : "Paywall".equalsIgnoreCase(str) ? Paywall : "Found".equalsIgnoreCase(str) ? Found : Unknown;
        }
    }

    /* compiled from: PG */
    public enum RefreshResult {
        SUCCESSFUL,
        NOT_PERFORMED,
        UNAVAILABLE,
        APP_NOT_FOUND
    }

    /* compiled from: PG */
    public enum SubscriptionState {
        AVAILABLE,
        NOT_AVAILABLE,
        SUBSCRIBED;

        public static SubscriptionState fromInt(int i) {
            return i == 0 ? AVAILABLE : i == 1 ? NOT_AVAILABLE : i == 2 ? SUBSCRIBED : NOT_AVAILABLE;
        }
    }
}
