package com.facebook.react.devsupport;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Ay(name = "JSCSamplingProfiler", needsEagerInit = true)
/* compiled from: PG */
public class JSCSamplingProfiler extends ReactContextBaseJavaModule {
    public static final HashSet<JSCSamplingProfiler> sRegisteredDumpers = new HashSet<>();
    public String mOperationError = null;
    public boolean mOperationInProgress = false;
    public int mOperationToken = 0;
    public SamplingProfiler mSamplingProfiler = null;
    public String mSamplingProfilerResult = null;

    /* compiled from: PG */
    public static class ProfilerException extends Exception {
        public ProfilerException(String str) {
            super(str);
        }
    }

    /* compiled from: PG */
    public interface SamplingProfiler extends JavaScriptModule {
        void poke(int i);
    }

    public JSCSamplingProfiler(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private int getOperationToken() throws ProfilerException {
        if (!this.mOperationInProgress) {
            this.mOperationInProgress = true;
            int i = this.mOperationToken + 1;
            this.mOperationToken = i;
            return i;
        }
        throw new ProfilerException("Another operation already in progress.");
    }

    public static synchronized List<String> poke(long j) throws ProfilerException {
        LinkedList linkedList;
        synchronized (JSCSamplingProfiler.class) {
            linkedList = new LinkedList();
            if (!sRegisteredDumpers.isEmpty()) {
                Iterator<JSCSamplingProfiler> it = sRegisteredDumpers.iterator();
                while (it.hasNext()) {
                    JSCSamplingProfiler next = it.next();
                    next.pokeHelper(j);
                    linkedList.add(next.mSamplingProfilerResult);
                }
            } else {
                throw new ProfilerException("No JSC registered");
            }
        }
        return linkedList;
    }

    private synchronized void pokeHelper(long j) throws ProfilerException {
        if (this.mSamplingProfiler != null) {
            this.mSamplingProfiler.poke(getOperationToken());
            waitForOperation(j);
        } else {
            throw new ProfilerException("SamplingProfiler.js module not connected");
        }
    }

    public static synchronized void registerSamplingProfiler(JSCSamplingProfiler jSCSamplingProfiler) {
        synchronized (JSCSamplingProfiler.class) {
            if (!sRegisteredDumpers.contains(jSCSamplingProfiler)) {
                sRegisteredDumpers.add(jSCSamplingProfiler);
            } else {
                throw new RuntimeException("a JSCSamplingProfiler registered more than once");
            }
        }
    }

    public static synchronized void unregisterSamplingProfiler(JSCSamplingProfiler jSCSamplingProfiler) {
        synchronized (JSCSamplingProfiler.class) {
            sRegisteredDumpers.remove(jSCSamplingProfiler);
        }
    }

    private void waitForOperation(long j) throws ProfilerException {
        try {
            wait(j);
            if (!this.mOperationInProgress) {
                String str = this.mOperationError;
                if (str != null) {
                    throw new ProfilerException(str);
                }
                return;
            }
            this.mOperationInProgress = false;
            throw new ProfilerException("heap capture timed out.");
        } catch (InterruptedException e) {
            StringBuilder a = Eo.a("Waiting for heap capture failed: ");
            a.append(e.getMessage());
            throw new ProfilerException(a.toString());
        }
    }

    public String getName() {
        return "JSCSamplingProfiler";
    }

    public void initialize() {
        super.initialize();
        this.mSamplingProfiler = (SamplingProfiler) getReactApplicationContext().getJSModule(SamplingProfiler.class);
        registerSamplingProfiler(this);
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        unregisterSamplingProfiler(this);
        this.mSamplingProfiler = null;
    }

    @ReactMethod
    public synchronized void operationComplete(int i, String str, String str2) {
        if (i == this.mOperationToken) {
            this.mOperationInProgress = false;
            this.mSamplingProfilerResult = str;
            this.mOperationError = str2;
            notify();
        } else {
            throw new RuntimeException("Completed operation is not in progress.");
        }
    }
}
