package org.chromium.chrome.browser.crash;

import android.annotation.TargetApi;
import android.os.PersistableBundle;
import org.chromium.components.minidump_uploader.MinidumpUploadJobService;
import org.chromium.components.minidump_uploader.MinidumpUploader;

@TargetApi(23)
/* compiled from: PG */
public class ChromeMinidumpUploadJobService extends MinidumpUploadJobService {
    /* renamed from: a */
    public MinidumpUploader mo8340a(PersistableBundle persistableBundle) {
        return new dL2(new FJ1(this, persistableBundle));
    }
}
