package com.microsoft.bing.commonlib.instrumentation;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* compiled from: PG */
public abstract class TelemetryMgrBase {
    public static final int BUFFER_SIZE_MAX = 20;
    public static final String TAG = "TelemetryMgrBase";
    public ArrayList<Map.Entry<String, Map<String, String>>> mEventList;
    public final Object mEventLock = new Object();
    public Executor mExecutor;
    public InstrumentationDelegate mInstrumentationDelegate = null;

    private synchronized Executor getFlushExecutor() {
        if (this.mExecutor == null) {
            this.mExecutor = Executors.newSingleThreadExecutor(new c((a) null));
        }
        return this.mExecutor;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001d, code lost:
        flushEventLog();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addEvent(java.lang.String r2, java.util.Map<java.lang.String, java.lang.String> r3) {
        /*
            r1 = this;
            r1.addEventIgnoreFlush(r2, r3)
            java.lang.Object r2 = r1.mEventLock
            monitor-enter(r2)
            boolean r3 = r1.enableCache()     // Catch:{ all -> 0x0021 }
            if (r3 == 0) goto L_0x001c
            java.util.ArrayList<java.util.Map$Entry<java.lang.String, java.util.Map<java.lang.String, java.lang.String>>> r3 = r1.mEventList     // Catch:{ all -> 0x0021 }
            if (r3 == 0) goto L_0x001a
            java.util.ArrayList<java.util.Map$Entry<java.lang.String, java.util.Map<java.lang.String, java.lang.String>>> r3 = r1.mEventList     // Catch:{ all -> 0x0021 }
            int r3 = r3.size()     // Catch:{ all -> 0x0021 }
            r0 = 20
            if (r3 >= r0) goto L_0x001c
        L_0x001a:
            monitor-exit(r2)     // Catch:{ all -> 0x0021 }
            return
        L_0x001c:
            monitor-exit(r2)     // Catch:{ all -> 0x0021 }
            r1.flushEventLog()
            return
        L_0x0021:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0021 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.bing.commonlib.instrumentation.TelemetryMgrBase.addEvent(java.lang.String, java.util.Map):void");
    }

    public void addEventAndFlushAsync(String str, Map<String, String> map) {
        addEventIgnoreFlush(str, map);
        Executor flushExecutor = getFlushExecutor();
        if (flushExecutor != null) {
            flushExecutor.execute(new b(this));
        }
    }

    public void addEventIgnoreFlush(String str, Map<String, String> map) {
        synchronized (this.mEventLock) {
            if (this.mEventList == null) {
                this.mEventList = new ArrayList<>();
            }
            this.mEventList.add(new AbstractMap.SimpleEntry(str, map));
        }
    }

    public abstract void appendBasicProperties(Map<String, String> map);

    public abstract boolean enableCache();

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void flushEventLog() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mEventLock
            monitor-enter(r0)
            java.util.ArrayList<java.util.Map$Entry<java.lang.String, java.util.Map<java.lang.String, java.lang.String>>> r1 = r5.mEventList     // Catch:{ all -> 0x004f }
            if (r1 == 0) goto L_0x004d
            java.util.ArrayList<java.util.Map$Entry<java.lang.String, java.util.Map<java.lang.String, java.lang.String>>> r1 = r5.mEventList     // Catch:{ all -> 0x004f }
            int r1 = r1.size()     // Catch:{ all -> 0x004f }
            if (r1 <= 0) goto L_0x004d
            com.microsoft.bing.commonlib.instrumentation.InstrumentationDelegate r1 = r5.mInstrumentationDelegate     // Catch:{ all -> 0x004f }
            if (r1 != 0) goto L_0x0014
            goto L_0x004d
        L_0x0014:
            r1 = 0
        L_0x0015:
            java.util.ArrayList<java.util.Map$Entry<java.lang.String, java.util.Map<java.lang.String, java.lang.String>>> r2 = r5.mEventList     // Catch:{ all -> 0x004f }
            int r2 = r2.size()     // Catch:{ all -> 0x004f }
            if (r1 >= r2) goto L_0x0046
            java.util.ArrayList<java.util.Map$Entry<java.lang.String, java.util.Map<java.lang.String, java.lang.String>>> r2 = r5.mEventList     // Catch:{ all -> 0x004f }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ all -> 0x004f }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x004f }
            if (r2 != 0) goto L_0x0028
            goto L_0x0043
        L_0x0028:
            java.lang.Object r3 = r2.getKey()     // Catch:{ all -> 0x004f }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x004f }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x004f }
            if (r4 == 0) goto L_0x0035
            goto L_0x0043
        L_0x0035:
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x004f }
            java.util.Map r2 = (java.util.Map) r2     // Catch:{ all -> 0x004f }
            if (r2 != 0) goto L_0x003e
            goto L_0x0043
        L_0x003e:
            com.microsoft.bing.commonlib.instrumentation.InstrumentationDelegate r4 = r5.mInstrumentationDelegate     // Catch:{ all -> 0x004f }
            r4.trackEvent(r3, r2)     // Catch:{ all -> 0x004f }
        L_0x0043:
            int r1 = r1 + 1
            goto L_0x0015
        L_0x0046:
            java.util.ArrayList<java.util.Map$Entry<java.lang.String, java.util.Map<java.lang.String, java.lang.String>>> r1 = r5.mEventList     // Catch:{ all -> 0x004f }
            r1.clear()     // Catch:{ all -> 0x004f }
            monitor-exit(r0)     // Catch:{ all -> 0x004f }
            return
        L_0x004d:
            monitor-exit(r0)     // Catch:{ all -> 0x004f }
            return
        L_0x004f:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004f }
            goto L_0x0053
        L_0x0052:
            throw r1
        L_0x0053:
            goto L_0x0052
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.bing.commonlib.instrumentation.TelemetryMgrBase.flushEventLog():void");
    }

    public InstrumentationDelegate get() {
        return this.mInstrumentationDelegate;
    }

    public void logClickEvent(String str, String str2, Map<String, String> map) {
        logEvent("BingClientSDK.ClickEvent", str, str2, map);
    }

    public void logEvent(String str, String str2, String str3, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        if (str2 != null && str2.length() > 0) {
            map.put("BingClientSDK.EventKey.Page", str2);
        }
        if (str3 != null && str3.length() > 0) {
            map.put("BingClientSDK.EventKey.Target", str3);
        }
        appendBasicProperties(map);
        addEvent(str, map);
        StringBuilder sb = new StringBuilder();
        sb.append("Bing SDK logEvent: ");
        Eo.b(sb, str, ", page: ", str2, ", target: ");
        sb.append(str3);
        sb.toString();
    }

    public void logGestureEvent(String str, String str2, Map<String, String> map) {
        logEvent("BingClientSDK.GestureEvent", str, str2, map);
    }

    public void logLongClickEvent(String str, String str2, Map<String, String> map) {
        logEvent("BingClientSDK.LongClickEvent", str, str2, map);
    }

    public void logPerfEvent(String str, String str2, Map<String, String> map) {
        logEvent("BingClientSDK.PerfEvent", str, str2, map);
    }

    public void logSearchEvent(String str, String str2, String str3, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put("BingClientSDK.EventKey.Source", str3);
        map.put("BingClientSDK.EventKey.FormCode", str);
        map.put("BingClientSDK.EventKey.Scope", str2);
        logEvent("BingClientSDK.SearchEvent", (String) null, (String) null, map);
    }

    public void logShowEvent(String str, String str2, Map<String, String> map) {
        logEvent("BingClientSDK.ShowEvent", str, str2, map);
    }

    public void set(InstrumentationDelegate instrumentationDelegate) {
        this.mInstrumentationDelegate = instrumentationDelegate;
    }
}
