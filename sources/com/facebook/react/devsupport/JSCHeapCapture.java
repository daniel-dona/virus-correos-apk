package com.facebook.react.devsupport;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.io.File;

@Ay(name = "JSCHeapCapture", needsEagerInit = true)
/* compiled from: PG */
public class JSCHeapCapture extends ReactContextBaseJavaModule {
    public CaptureCallback mCaptureInProgress = null;

    /* compiled from: PG */
    public interface CaptureCallback {
        void onFailure(CaptureException captureException);

        void onSuccess(File file);
    }

    /* compiled from: PG */
    public static class CaptureException extends Exception {
        public CaptureException(String str) {
            super(str);
        }

        public CaptureException(String str, Throwable th) {
            super(str, th);
        }
    }

    /* compiled from: PG */
    public interface HeapCapture extends JavaScriptModule {
        void captureHeap(String str);
    }

    public JSCHeapCapture(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public synchronized void captureComplete(String str, String str2) {
        if (this.mCaptureInProgress != null) {
            if (str2 == null) {
                this.mCaptureInProgress.onSuccess(new File(str));
            } else {
                this.mCaptureInProgress.onFailure(new CaptureException(str2));
            }
            this.mCaptureInProgress = null;
        }
    }

    public synchronized void captureHeap(String str, CaptureCallback captureCallback) {
        if (this.mCaptureInProgress != null) {
            captureCallback.onFailure(new CaptureException("Heap capture already in progress."));
            return;
        }
        File file = new File(str + "/capture.json");
        file.delete();
        HeapCapture heapCapture = (HeapCapture) getReactApplicationContext().getJSModule(HeapCapture.class);
        if (heapCapture == null) {
            captureCallback.onFailure(new CaptureException("Heap capture js module not registered."));
            return;
        }
        this.mCaptureInProgress = captureCallback;
        heapCapture.captureHeap(file.getPath());
    }

    public String getName() {
        return "JSCHeapCapture";
    }
}
