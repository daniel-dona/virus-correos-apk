package com.microsoft.aad.adal;

/* compiled from: PG */
public enum AuthenticationSettings {
    INSTANCE;
    
    public static final int DEFAULT_EXPIRATION_BUFFER = 300;
    public static final int DEFAULT_READ_CONNECT_TIMEOUT = 30000;
    public String mActivityPackageName;
    public Class<?> mClazzDeviceCertProxy;
    public int mConnectTimeOut;
    public boolean mEnableHardwareAcceleration;
    public int mExpirationBuffer;
    public int mReadTimeOut;
    public String mSharedPrefPackageName;
    public boolean mUseBroker;

    public String getActivityPackageName() {
        return com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.getActivityPackageName();
    }

    public String getBrokerPackageName() {
        return com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.getBrokerPackageName();
    }

    public String getBrokerSignature() {
        return com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.getBrokerSignature();
    }

    public int getConnectTimeOut() {
        return com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.getConnectTimeOut();
    }

    public Class<?> getDeviceCertificateProxy() {
        return this.mClazzDeviceCertProxy;
    }

    public boolean getDisableWebViewHardwareAcceleration() {
        return com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.getDisableWebViewHardwareAcceleration();
    }

    public int getExpirationBuffer() {
        return com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.getExpirationBuffer();
    }

    public int getReadTimeOut() {
        return com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.getReadTimeOut();
    }

    public byte[] getSecretKeyData() {
        return com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.getSecretKeyData();
    }

    public String getSharedPrefPackageName() {
        return com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.getSharedPrefPackageName();
    }

    @Deprecated
    public boolean getSkipBroker() {
        return com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.getSkipBroker();
    }

    public boolean getUseBroker() {
        return com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.getUseBroker();
    }

    public void setActivityPackageName(String str) {
        com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.setActivityPackageName(str);
    }

    public void setBrokerPackageName(String str) {
        com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.setBrokerPackageName(str);
    }

    public void setBrokerSignature(String str) {
        com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.setBrokerSignature(str);
    }

    public void setConnectTimeOut(int i) {
        com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.setConnectTimeOut(i);
    }

    public void setDeviceCertificateProxyClass(Class cls) {
        if (IDeviceCertificate.class.isAssignableFrom(cls)) {
            this.mClazzDeviceCertProxy = cls;
            return;
        }
        throw new IllegalArgumentException("clazz");
    }

    public void setDisableWebViewHardwareAcceleration(boolean z) {
        com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.setDisableWebViewHardwareAcceleration(z);
    }

    public void setExpirationBuffer(int i) {
        com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.setExpirationBuffer(i);
    }

    public void setReadTimeOut(int i) {
        com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.setReadTimeOut(i);
    }

    public void setSecretKey(byte[] bArr) {
        com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.setSecretKey(bArr);
    }

    public void setSharedPrefPackageName(String str) {
        com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.setSharedPrefPackageName(str);
    }

    @Deprecated
    public void setSkipBroker(boolean z) {
        com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.setSkipBroker(z);
    }

    public void setUseBroker(boolean z) {
        com.microsoft.identity.common.adal.internal.AuthenticationSettings.INSTANCE.setUseBroker(z);
    }
}
