package com.microsoft.intune.mam.http;

/* compiled from: PG */
public enum KnownClouds {
    WORLDWIDE("https://login.windows.net", "https://go.microsoft.com/fwlink/?linkid=2138939", "https://go.microsoft.com/fwlink/?linkid=2131071", Hd0.e, Hd0.g),
    ARLINGTON("https://login.microsoftonline.us", "https://go.microsoft.com/fwlink/?linkid=851103", "https://go.microsoft.com/fwlink/?linkid=2130378", Bd0.c, Bd0.f),
    GALLATIN("https://login.chinacloudapi.cn", "https://go.microsoft.com/fwlink/?linkid=2112757&clcid=0x804", "https://go.microsoft.com/fwlink/?linkid=2131070", Cd0.b, Cd0.d),
    MOONCAKE("https://login.partner.microsoftonline.cn", "https://go.microsoft.com/fwlink/?linkid=2112757&clcid=0x804", "https://go.microsoft.com/fwlink/?linkid=2131070", Cd0.b, Cd0.d),
    BLACKFOREST("https://login.microsoftonline.de", (int) null, (String) null, (String) null, (String) null);
    
    public static final Ld0 LOGGER = null;
    public final String mAuthority;
    public final String mInstallationFWLink;
    public byte[][] mIntermediateCertPubkeys;
    public final String mMAMServiceFWLink;
    public byte[][] mRootCertPubkey;

    /* access modifiers changed from: public */
    static {
        LOGGER = Md0.a(KnownClouds.class);
    }

    /* access modifiers changed from: public */
    KnownClouds(String str, String str2, String str3, byte[][] bArr, byte[][] bArr2) {
        this.mAuthority = str;
        this.mMAMServiceFWLink = str2;
        this.mInstallationFWLink = str3;
        this.mIntermediateCertPubkeys = bArr;
        this.mRootCertPubkey = bArr2;
    }

    public static KnownClouds fromAuthority(String str) {
        if (str == null) {
            LOGGER.c("null authority, using worldwide");
            return WORLDWIDE;
        } else if (str.startsWith(ARLINGTON.mAuthority)) {
            LOGGER.c("detected arlington authority");
            return ARLINGTON;
        } else if (str.startsWith(GALLATIN.mAuthority)) {
            LOGGER.c("detected gallatin authority");
            return GALLATIN;
        } else if (str.startsWith(MOONCAKE.mAuthority)) {
            LOGGER.c("detected mooncake authority");
            return MOONCAKE;
        } else if (str.startsWith(BLACKFOREST.mAuthority)) {
            LOGGER.c("detected unsupported blackforest authority");
            return BLACKFOREST;
        } else {
            LOGGER.c("defaulting to worldwide");
            return WORLDWIDE;
        }
    }

    public static boolean isSupported(String str) {
        int ordinal = fromAuthority(str).ordinal();
        if (ordinal == 0 || ordinal == 1 || ordinal == 2 || ordinal == 3) {
            return true;
        }
        if (ordinal != 4) {
            Ld0 ld0 = LOGGER;
            ld0.d("Unknown cloud detected for authority - programmer error: " + str);
            return false;
        }
        LOGGER.c("Attempting to enroll into an unsupported cloud");
        return false;
    }

    public String getAuthority() {
        return this.mAuthority;
    }

    public String getInstallationFWLink() {
        return this.mInstallationFWLink;
    }

    public byte[][] getIntermediateCertPubkeys() {
        return this.mIntermediateCertPubkeys;
    }

    public String getMAMServiceFWLink() {
        return this.mMAMServiceFWLink;
    }

    public byte[][] getRootCertPubkey() {
        return this.mRootCertPubkey;
    }
}
