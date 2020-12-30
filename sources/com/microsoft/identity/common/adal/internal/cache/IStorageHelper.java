package com.microsoft.identity.common.adal.internal.cache;

import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.crypto.SecretKey;

/* compiled from: PG */
public interface IStorageHelper {
    String decrypt(String str) throws GeneralSecurityException, IOException;

    String encrypt(String str) throws GeneralSecurityException, IOException;

    SecretKey loadSecretKeyForEncryption() throws IOException, GeneralSecurityException;

    SecretKey loadSecretKeyForEncryption(String str) throws IOException, GeneralSecurityException;
}
