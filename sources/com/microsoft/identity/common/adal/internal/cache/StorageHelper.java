package com.microsoft.identity.common.adal.internal.cache;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.security.KeyPairGeneratorSpec;
import android.util.Base64;
import android.util.Log;
import com.microsoft.identity.common.adal.internal.AuthenticationSettings;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.DigestException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.x500.X500Principal;

/* compiled from: PG */
public class StorageHelper implements IStorageHelper {
    public static final String ADALKS = "adalks";
    public static final String ANDROID_KEY_STORE = "AndroidKeyStore";
    public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    public static final int DATA_KEY_LENGTH = 16;
    public static final String ENCODE_VERSION = "E1";
    public static final String HMAC_ALGORITHM = "HmacSHA256";
    public static final String HMAC_KEY_HASH_ALGORITHM = "SHA256";
    public static final int HMAC_LENGTH = 32;
    public static final String KEYSPEC_ALGORITHM = "AES";
    public static final int KEY_FILE_SIZE = 1024;
    public static final int KEY_SIZE = 256;
    public static final String KEY_STORE_CERT_ALIAS = "AdalKey";
    public static final int KEY_VERSION_BLOB_LENGTH = 4;
    public static final String TAG = "StorageHelper";
    public static final String VERSION_ANDROID_KEY_STORE = "A001";
    public static final String VERSION_USER_DEFINED = "U001";
    public static final String WRAP_ALGORITHM = "RSA/ECB/PKCS1Padding";
    public String mBlobVersion;
    public final Context mContext;
    public SecretKey mHMACKey = null;
    public SecretKey mKey = null;
    public KeyPair mKeyPair;
    public final SecureRandom mRandom;
    public SecretKey mSecretKeyFromAndroidKeyStore = null;

    @SuppressLint({"TrulyRandom"})
    public StorageHelper(Context context) {
        this.mContext = context.getApplicationContext();
        this.mRandom = new SecureRandom();
    }

    private void assertHMac(byte[] bArr, int i, int i2, byte[] bArr2) throws DigestException {
        if (bArr2.length == i2 - i) {
            byte b = 0;
            for (int i3 = i; i3 < i2; i3++) {
                b = (byte) (b | (bArr2[i3 - i] ^ bArr[i3]));
            }
            if (b != 0) {
                throw new DigestException();
            }
            return;
        }
        throw new IllegalArgumentException("Unexpected HMAC length");
    }

    private void deleteKeyFile() {
        Context context = this.mContext;
        File file = new File(context.getDir(context.getPackageName(), 0), ADALKS);
        if (file.exists()) {
            boolean delete = file.delete();
        }
    }

    private synchronized boolean doesKeyPairExist() throws GeneralSecurityException, IOException {
        KeyStore instance;
        instance = KeyStore.getInstance(ANDROID_KEY_STORE);
        instance.load((KeyStore.LoadStoreParameter) null);
        try {
        } catch (NullPointerException e) {
            throw new KeyStoreException(e);
        }
        return instance.containsAlias(KEY_STORE_CERT_ALIAS);
    }

    @TargetApi(18)
    private synchronized KeyPair generateKeyPairFromAndroidKeyStore() throws GeneralSecurityException, IOException {
        KeyPairGenerator instance;
        KeyStore.getInstance(ANDROID_KEY_STORE).load((KeyStore.LoadStoreParameter) null);
        Calendar instance2 = Calendar.getInstance();
        Calendar instance3 = Calendar.getInstance();
        instance3.add(1, 100);
        instance = KeyPairGenerator.getInstance("RSA", ANDROID_KEY_STORE);
        instance.initialize(getKeyPairGeneratorSpec(this.mContext, instance2.getTime(), instance3.getTime()));
        try {
        } catch (IllegalStateException e) {
            throw new KeyStoreException(e);
        }
        return instance.generateKeyPair();
    }

    private SecretKey generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator instance = KeyGenerator.getInstance(KEYSPEC_ALGORITHM);
        instance.init(256, this.mRandom);
        return instance.generateKey();
    }

    private char getEncodeVersionLengthPrefix() {
        return (char) 99;
    }

    private SecretKey getHMacKey(SecretKey secretKey) throws NoSuchAlgorithmException {
        byte[] encoded = secretKey.getEncoded();
        return encoded != null ? new SecretKeySpec(MessageDigest.getInstance("SHA256").digest(encoded), KEYSPEC_ALGORITHM) : secretKey;
    }

    private synchronized SecretKey getKey(String str) throws GeneralSecurityException, IOException {
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != 1984080) {
            if (hashCode == 2579900) {
                if (str.equals(VERSION_USER_DEFINED)) {
                    c = 0;
                }
            }
        } else if (str.equals(VERSION_ANDROID_KEY_STORE)) {
            c = 1;
        }
        if (c == 0) {
            return getSecretKey(getSecretKeyData(this.mContext.getPackageName()));
        } else if (c != 1) {
            throw new IOException("Unknown keyVersion.");
        } else if (this.mSecretKeyFromAndroidKeyStore != null) {
            return this.mSecretKeyFromAndroidKeyStore;
        } else {
            this.mKeyPair = readKeyPair();
            this.mSecretKeyFromAndroidKeyStore = getUnwrappedSecretKey();
            return this.mSecretKeyFromAndroidKeyStore;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:8|9|10|11|(1:13)|14|15|16) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x001f */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized javax.crypto.SecretKey getKeyOrCreate(java.lang.String r2) throws java.security.GeneralSecurityException, java.io.IOException {
        /*
            r1 = this;
            monitor-enter(r1)
            java.lang.String r0 = "U001"
            boolean r0 = r0.equals(r2)     // Catch:{ all -> 0x003c }
            if (r0 == 0) goto L_0x0019
            android.content.Context r2 = r1.mContext     // Catch:{ all -> 0x003c }
            java.lang.String r2 = r2.getPackageName()     // Catch:{ all -> 0x003c }
            byte[] r2 = r1.getSecretKeyData(r2)     // Catch:{ all -> 0x003c }
            javax.crypto.SecretKey r2 = r1.getSecretKey(r2)     // Catch:{ all -> 0x003c }
            monitor-exit(r1)
            return r2
        L_0x0019:
            javax.crypto.SecretKey r2 = r1.getKey(r2)     // Catch:{ IOException | GeneralSecurityException -> 0x001f }
            r1.mSecretKeyFromAndroidKeyStore = r2     // Catch:{ IOException | GeneralSecurityException -> 0x001f }
        L_0x001f:
            javax.crypto.SecretKey r2 = r1.mSecretKeyFromAndroidKeyStore     // Catch:{ all -> 0x003c }
            if (r2 != 0) goto L_0x0038
            java.security.KeyPair r2 = r1.generateKeyPairFromAndroidKeyStore()     // Catch:{ all -> 0x003c }
            r1.mKeyPair = r2     // Catch:{ all -> 0x003c }
            javax.crypto.SecretKey r2 = r1.generateSecretKey()     // Catch:{ all -> 0x003c }
            r1.mSecretKeyFromAndroidKeyStore = r2     // Catch:{ all -> 0x003c }
            javax.crypto.SecretKey r2 = r1.mSecretKeyFromAndroidKeyStore     // Catch:{ all -> 0x003c }
            byte[] r2 = r1.wrap(r2)     // Catch:{ all -> 0x003c }
            r1.writeKeyData(r2)     // Catch:{ all -> 0x003c }
        L_0x0038:
            javax.crypto.SecretKey r2 = r1.mSecretKeyFromAndroidKeyStore     // Catch:{ all -> 0x003c }
            monitor-exit(r1)
            return r2
        L_0x003c:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.identity.common.adal.internal.cache.StorageHelper.getKeyOrCreate(java.lang.String):javax.crypto.SecretKey");
    }

    @TargetApi(18)
    private AlgorithmParameterSpec getKeyPairGeneratorSpec(Context context, Date date, Date date2) {
        return new KeyPairGeneratorSpec.Builder(context).setAlias(KEY_STORE_CERT_ALIAS).setSubject(new X500Principal(String.format(Locale.ROOT, "CN=%s, OU=%s", new Object[]{KEY_STORE_CERT_ALIAS, context.getPackageName()}))).setSerialNumber(BigInteger.ONE).setStartDate(date).setEndDate(date2).build();
    }

    private SecretKey getSecretKey(byte[] bArr) {
        if (bArr != null) {
            return new SecretKeySpec(bArr, KEYSPEC_ALGORITHM);
        }
        throw new IllegalArgumentException("rawBytes");
    }

    private byte[] getSecretKeyData(String str) {
        if (AuthenticationSettings.INSTANCE.getBrokerSecretKeys().containsKey(str)) {
            return AuthenticationSettings.INSTANCE.getBrokerSecretKeys().get(str);
        }
        return AuthenticationSettings.INSTANCE.getSecretKeyData();
    }

    @TargetApi(18)
    private synchronized SecretKey getUnwrappedSecretKey() throws GeneralSecurityException, IOException {
        try {
        } catch (IOException | GeneralSecurityException e) {
            Log.e(TAG, "android_keystore_failed");
            this.mKeyPair = null;
            deleteKeyFile();
            resetKeyPairFromAndroidKeyStore();
            throw e;
        }
        return unwrap(readKeyData());
    }

    private byte[] readKeyData() throws IOException {
        Context context = this.mContext;
        File file = new File(context.getDir(context.getPackageName(), 0), ADALKS);
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        return byteArrayOutputStream.toByteArray();
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
            } finally {
                fileInputStream.close();
            }
        } else {
            throw new IOException("Key file to read does not exist");
        }
    }

    private synchronized KeyPair readKeyPair() throws GeneralSecurityException, IOException {
        KeyStore.PrivateKeyEntry privateKeyEntry;
        if (doesKeyPairExist()) {
            KeyStore instance = KeyStore.getInstance(ANDROID_KEY_STORE);
            instance.load((KeyStore.LoadStoreParameter) null);
            try {
                privateKeyEntry = (KeyStore.PrivateKeyEntry) instance.getEntry(KEY_STORE_CERT_ALIAS, (KeyStore.ProtectionParameter) null);
            } catch (RuntimeException e) {
                throw new KeyStoreException(e);
            }
        } else {
            throw new KeyStoreException("KeyPair entry does not exist.");
        }
        return new KeyPair(privateKeyEntry.getCertificate().getPublicKey(), privateKeyEntry.getPrivateKey());
    }

    @TargetApi(18)
    private synchronized void resetKeyPairFromAndroidKeyStore() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
        KeyStore instance = KeyStore.getInstance(ANDROID_KEY_STORE);
        instance.load((KeyStore.LoadStoreParameter) null);
        instance.deleteEntry(KEY_STORE_CERT_ALIAS);
    }

    @SuppressLint({"GetInstance"})
    @TargetApi(18)
    private SecretKey unwrap(byte[] bArr) throws GeneralSecurityException {
        Cipher instance = Cipher.getInstance(WRAP_ALGORITHM);
        instance.init(4, this.mKeyPair.getPrivate());
        try {
            return (SecretKey) instance.unwrap(bArr, KEYSPEC_ALGORITHM, 3);
        } catch (IllegalArgumentException e) {
            throw new KeyStoreException(e);
        }
    }

    @SuppressLint({"GetInstance"})
    @TargetApi(18)
    private byte[] wrap(SecretKey secretKey) throws GeneralSecurityException {
        Cipher instance = Cipher.getInstance(WRAP_ALGORITHM);
        instance.init(3, this.mKeyPair.getPublic());
        return instance.wrap(secretKey);
    }

    private void writeKeyData(byte[] bArr) throws IOException {
        Context context = this.mContext;
        FileOutputStream fileOutputStream = new FileOutputStream(new File(context.getDir(context.getPackageName(), 0), ADALKS));
        try {
            fileOutputStream.write(bArr);
        } finally {
            fileOutputStream.close();
        }
    }

    public String decrypt(String str) throws GeneralSecurityException, IOException {
        if (!StringExtensions.isNullOrBlank(str)) {
            int charAt = str.charAt(0) - 'a';
            if (charAt > 0) {
                int i = charAt + 1;
                if (str.substring(1, i).equals(ENCODE_VERSION)) {
                    byte[] decode = Base64.decode(str.substring(i), 0);
                    String str2 = new String(decode, 0, 4, "UTF-8");
                    Eo.f("Encrypt version:", str2);
                    SecretKey key = getKey(str2);
                    SecretKey hMacKey = getHMacKey(key);
                    int length = (decode.length - 16) - 32;
                    int length2 = decode.length - 32;
                    int i2 = length - 4;
                    if (length < 0 || length2 < 0 || i2 < 0) {
                        throw new IOException("Invalid byte array input for decryption.");
                    }
                    Cipher instance = Cipher.getInstance(CIPHER_ALGORITHM);
                    Mac instance2 = Mac.getInstance(HMAC_ALGORITHM);
                    instance2.init(hMacKey);
                    instance2.update(decode, 0, length2);
                    assertHMac(decode, length2, decode.length, instance2.doFinal());
                    instance.init(2, key, new IvParameterSpec(decode, length, 16));
                    return new String(instance.doFinal(decode, 4, i2), "UTF-8");
                }
                throw new IllegalArgumentException(String.format("Encode version received was: '%s', Encode version supported is: '%s'", new Object[]{str, ENCODE_VERSION}));
            }
            throw new IllegalArgumentException(String.format("Encode version length: '%s' is not valid, it must be greater of equal to 0", new Object[]{Integer.valueOf(charAt)}));
        }
        throw new IllegalArgumentException("Input is empty or null");
    }

    public String encrypt(String str) throws GeneralSecurityException, IOException {
        if (!StringExtensions.isNullOrBlank(str)) {
            this.mKey = loadSecretKeyForEncryption();
            this.mHMACKey = getHMacKey(this.mKey);
            StringBuilder a = Eo.a("Encrypt version:");
            a.append(this.mBlobVersion);
            a.toString();
            byte[] bytes = this.mBlobVersion.getBytes("UTF-8");
            byte[] bytes2 = str.getBytes("UTF-8");
            byte[] bArr = new byte[16];
            this.mRandom.nextBytes(bArr);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
            Cipher instance = Cipher.getInstance(CIPHER_ALGORITHM);
            Mac instance2 = Mac.getInstance(HMAC_ALGORITHM);
            instance.init(1, this.mKey, ivParameterSpec);
            byte[] doFinal = instance.doFinal(bytes2);
            instance2.init(this.mHMACKey);
            instance2.update(bytes);
            instance2.update(doFinal);
            instance2.update(bArr);
            byte[] doFinal2 = instance2.doFinal();
            byte[] bArr2 = new byte[(bytes.length + doFinal.length + bArr.length + doFinal2.length)];
            System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
            System.arraycopy(doFinal, 0, bArr2, bytes.length, doFinal.length);
            System.arraycopy(bArr, 0, bArr2, bytes.length + doFinal.length, bArr.length);
            System.arraycopy(doFinal2, 0, bArr2, bytes.length + doFinal.length + bArr.length, doFinal2.length);
            String str2 = new String(Base64.encode(bArr2, 2), "UTF-8");
            return getEncodeVersionLengthPrefix() + ENCODE_VERSION + str2;
        }
        throw new IllegalArgumentException("Input is empty or null");
    }

    public synchronized SecretKey loadSecretKeyForEncryption() throws IOException, GeneralSecurityException {
        return loadSecretKeyForEncryption(getSecretKeyData(this.mContext.getPackageName()) == null ? VERSION_ANDROID_KEY_STORE : VERSION_USER_DEFINED);
    }

    public synchronized SecretKey loadSecretKeyForEncryption(String str) throws IOException, GeneralSecurityException {
        if (this.mKey == null || this.mHMACKey == null) {
            this.mBlobVersion = str;
            return getKeyOrCreate(this.mBlobVersion);
        }
        return this.mKey;
    }
}
