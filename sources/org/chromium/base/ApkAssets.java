package org.chromium.base;

import android.content.res.AssetFileDescriptor;
import android.util.Log;
import com.citrix.loggersdk.BuildConfig;
import java.io.IOException;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

@MainDex
/* compiled from: PG */
public class ApkAssets {
    @CalledByNative
    public static long[] open(String str) {
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            assetFileDescriptor = RN0.a.getAssets().openNonAssetFd(str);
            long[] jArr = {(long) assetFileDescriptor.getParcelFileDescriptor().detachFd(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength()};
            try {
                assetFileDescriptor.close();
            } catch (IOException e) {
                Log.e("ApkAssets", "Unable to close AssetFileDescriptor", e);
            }
            return jArr;
        } catch (IOException e2) {
            if (!e2.getMessage().equals(BuildConfig.FLAVOR) && !e2.getMessage().equals(str)) {
                Log.e("ApkAssets", "Error while loading asset " + str + ": " + e2);
            }
            long[] jArr2 = {-1, -1, -1};
            if (assetFileDescriptor != null) {
                try {
                    assetFileDescriptor.close();
                } catch (IOException e3) {
                    Log.e("ApkAssets", "Unable to close AssetFileDescriptor", e3);
                }
            }
            return jArr2;
        } catch (Throwable th) {
            if (assetFileDescriptor != null) {
                try {
                    assetFileDescriptor.close();
                } catch (IOException e4) {
                    Log.e("ApkAssets", "Unable to close AssetFileDescriptor", e4);
                }
            }
            throw th;
        }
    }
}
