package com.microsoft.applications.experimentation.common;

import android.content.Context;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.applications.telemetry.EventProperties;
import com.microsoft.applications.telemetry.ILogger;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
public abstract class EXPClient<T extends Serializable, T2> {
    public static final int CORE_POOL_SIZE = (CPU_COUNT + 1);
    public static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    public static final String LOG_TAG = Eo.a(EXPClient.class, Eo.a("[EXP]:"));
    public T activeConfig = null;
    public String activeQueryParametersAsString = BuildConfig.FLAVOR;
    public HashMap<String, String> activeRequestHeaders = new HashMap<>();
    public Map<String, String> activeRequestParameter = new HashMap();
    public final String clientName;
    public final String clientVersion;
    public EXPClient<T, T2>.ConfigValidator configValidator = new ConfigValidator(this, (1) null);
    public final boolean isEXPClientTelemetryEnabled;
    public HashSet<T2> listeners = new HashSet<>();
    public ConcurrentHashMap<ILogger, String> loggers = new ConcurrentHashMap<>();
    public Object objectToNotify = new Object();
    public ScheduledFuture<?> scheduledFutureTask;
    public final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(CORE_POOL_SIZE);
    public final Object start_stop_lock = new Object();
    public boolean started = false;

    public EXPClient(Context context, String str, String str2, boolean z) {
        TraceHelper.TraceInformation(LOG_TAG, "EXP Client created");
        Preconditions.isNotNull(context, "context can't be null");
        this.clientName = Preconditions.isNotNullOrEmpty(str, "clientName can't be empty");
        this.clientVersion = Preconditions.isNotNullOrEmpty(str2, "clientVersion can't be empty");
        this.isEXPClientTelemetryEnabled = z;
    }

    private void addConfigInformationToLogger(String str, ILogger iLogger) {
        String activeConfigETag = getActiveConfigETag();
        if (activeConfigETag != null && !activeConfigETag.isEmpty()) {
            iLogger.getSemanticContext().setAppExperimentETag(activeConfigETag);
        }
        String activeConfigImpressionId = getActiveConfigImpressionId();
        if (activeConfigImpressionId != null && !activeConfigImpressionId.isEmpty()) {
            iLogger.getSemanticContext().setAppExperimentImpressionId(activeConfigImpressionId);
        }
        String configIds = getConfigIds(str);
        if (configIds != null && !configIds.isEmpty()) {
            iLogger.getSemanticContext().setAppExperimentIds(configIds);
        }
        ArrayList<String> eventNamesForWhichToSetConfigIds = getEventNamesForWhichToSetConfigIds(str);
        if (eventNamesForWhichToSetConfigIds != null) {
            Iterator<String> it = eventNamesForWhichToSetConfigIds.iterator();
            while (it.hasNext()) {
                String next = it.next();
                String eventSpecificConfigIds = getEventSpecificConfigIds(str, next);
                if (eventSpecificConfigIds != null && !eventSpecificConfigIds.isEmpty()) {
                    iLogger.getSemanticContext().setEventExperimentIds(next, eventSpecificConfigIds);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateConfigFromServer() {
        TraceHelper.TraceInformation(LOG_TAG, String.format("Update config from server. QueryParameters: %s", new Object[]{this.activeQueryParametersAsString}));
        checkServerAsyncForConfig();
    }

    public void addConfigInformationToLoggers() {
        for (Map.Entry next : this.loggers.entrySet()) {
            addConfigInformationToLogger((String) next.getValue(), (ILogger) next.getKey());
        }
    }

    public boolean addListener(T2 t2) {
        if (t2 == null) {
            TraceHelper.TraceError(LOG_TAG, "Tried to add null callback");
            return false;
        }
        synchronized (this.listeners) {
            if (this.listeners.contains(t2)) {
                TraceHelper.TraceError(LOG_TAG, "Tried to add callback that was already added");
                return false;
            }
            boolean add = this.listeners.add(t2);
            return add;
        }
    }

    public abstract boolean alwaysForceFetchOnStart();

    public abstract void checkServerAsyncForConfig();

    public abstract String getActiveConfigClientVersion();

    public abstract String getActiveConfigETag();

    public abstract long getActiveConfigExpireTime();

    public abstract String getActiveConfigImpressionId();

    public abstract T getConfigFromStorage();

    public abstract String getConfigIds(String str);

    public abstract String getEXPClientUpdateEventName();

    public abstract String getEXPConfigUpdateEventName();

    public abstract ArrayList<String> getEventNamesForWhichToSetConfigIds(String str);

    public abstract String getEventSpecificConfigIds(String str, String str2);

    public abstract String getQueryParameters();

    public abstract HashMap<String, String> getRequestHeadersFromActiveConfig();

    public abstract String getRequestParametersFromActiveConfig();

    public void logEXPClientUpdate(EXPClientState eXPClientState) {
        if (this.isEXPClientTelemetryEnabled) {
            TraceHelper.TraceInformation(LOG_TAG, "logEXPClientUpdate");
            for (Map.Entry<ILogger, String> key : this.loggers.entrySet()) {
                EventProperties eventProperties = new EventProperties(getEXPClientUpdateEventName());
                eventProperties.setProperty("State", eXPClientState.toString());
                eventProperties.setProperty("ClientName", this.clientName);
                eventProperties.setProperty("ClientVersion", this.clientVersion);
                ((ILogger) key.getKey()).logEvent(eventProperties);
            }
        }
    }

    public void logEXPConfigUpdate(EXPConfigUpdate eXPConfigUpdate, EXPConfigSource eXPConfigSource) {
        if (this.isEXPClientTelemetryEnabled) {
            TraceHelper.TraceInformation(LOG_TAG, String.format("logEXPConfigUpdate. request parameter: %s", new Object[]{this.activeQueryParametersAsString}));
            for (Map.Entry<ILogger, String> key : this.loggers.entrySet()) {
                EventProperties eventProperties = new EventProperties(getEXPConfigUpdateEventName());
                eventProperties.setProperty("Result", eXPConfigUpdate.toString());
                eventProperties.setProperty("Source", eXPConfigSource.toString());
                eventProperties.setProperty("ClientName", this.clientName);
                eventProperties.setProperty("ClientVersion", this.clientVersion);
                ((ILogger) key.getKey()).logEvent(eventProperties);
            }
        }
    }

    public abstract void onConfigurationReturnedFromServer(T t, String str, HashMap<String, String> hashMap);

    public boolean registerLogger(ILogger iLogger, String str) {
        if (str == null || str.isEmpty()) {
            TraceHelper.TraceError(LOG_TAG, "Tried to register logger with null or empty agent name");
            return false;
        } else if (iLogger == null) {
            TraceHelper.TraceError(LOG_TAG, "Tried to register null logger");
            return false;
        } else {
            if (this.activeConfig != null && getActiveConfigExpireTime() < TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())) {
                addConfigInformationToLogger(str, iLogger);
            }
            this.loggers.put(iLogger, str);
            return true;
        }
    }

    public boolean removeListener(T2 t2) {
        if (t2 == null) {
            TraceHelper.TraceError(LOG_TAG, "Tried to remove null callback");
            return false;
        }
        synchronized (this.listeners) {
            if (!this.listeners.contains(t2)) {
                TraceHelper.TraceError(LOG_TAG, "Tried to remove callback that was not added");
                return false;
            }
            boolean remove = this.listeners.remove(t2);
            return remove;
        }
    }

    public void restartIfAlreadyStarted() {
        boolean z = this.started;
        if (z) {
            stop(false);
        }
        if (z) {
            start(0, false);
        }
    }

    public abstract boolean restartOnRequestParameterChange();

    public boolean resume() {
        return resume(false);
    }

    public void scheduleUpdate(boolean z) {
        if (z) {
            this.scheduledFutureTask = this.scheduledThreadPoolExecutor.schedule(this.configValidator, 30, TimeUnit.MINUTES);
            return;
        }
        long activeConfigExpireTime = getActiveConfigExpireTime() - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        if (activeConfigExpireTime > 0) {
            this.scheduledFutureTask = this.scheduledThreadPoolExecutor.schedule(this.configValidator, activeConfigExpireTime, TimeUnit.MILLISECONDS);
        } else {
            updateConfigFromServer();
        }
    }

    public abstract void sendOutCallbacks(boolean z, long j, String str, HashMap<String, String> hashMap, boolean z2);

    public abstract void setActiveConfig(T t);

    public boolean setRequestParameters(Map<String, String> map) {
        Preconditions.isNotNull(map, "requestParameters can't be null");
        this.activeRequestParameter = map;
        String queryParameters = getQueryParameters();
        if (this.activeQueryParametersAsString.equals(queryParameters)) {
            return false;
        }
        logEXPClientUpdate(EXPClientState.REQUEST_PARAMETER_CHANGED);
        this.activeQueryParametersAsString = queryParameters;
        if (!restartOnRequestParameterChange()) {
            return true;
        }
        restartIfAlreadyStarted();
        return true;
    }

    public boolean start() {
        return start(0);
    }

    public boolean stop() {
        return stop(true);
    }

    public boolean suspend() {
        synchronized (this.start_stop_lock) {
            if (!this.started) {
                return false;
            }
            logEXPClientUpdate(EXPClientState.SUSPEND);
            if (this.scheduledFutureTask != null) {
                this.scheduledFutureTask.cancel(false);
            }
            return true;
        }
    }

    private boolean stop(boolean z) {
        synchronized (this.start_stop_lock) {
            if (!this.started) {
                return false;
            }
            TraceHelper.TraceInformation(LOG_TAG, "EXPClient Stoped");
            if (z) {
                logEXPClientUpdate(EXPClientState.STOPPED);
            }
            if (this.scheduledFutureTask != null) {
                this.scheduledFutureTask.cancel(false);
            }
            this.started = false;
            return true;
        }
    }

    public boolean resume(boolean z) {
        synchronized (this.start_stop_lock) {
            if (!this.started) {
                return false;
            }
            logEXPClientUpdate(EXPClientState.RESUME);
            if (z) {
                updateConfigFromServer();
            } else {
                scheduleUpdate(false);
            }
            return true;
        }
    }

    public boolean start(long j) {
        return start(j, true);
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    private boolean start(long r16, boolean r18) {
        /*
            r15 = this;
            r8 = r15
            r9 = r16
            java.lang.Object r11 = r8.start_stop_lock
            monitor-enter(r11)
            boolean r0 = r8.started     // Catch:{ all -> 0x00a4 }
            r12 = 0
            if (r0 != 0) goto L_0x00a2
            java.lang.String r0 = LOG_TAG     // Catch:{ all -> 0x00a4 }
            java.lang.String r1 = "EXPClient Started"
            com.microsoft.applications.experimentation.common.TraceHelper.TraceInformation(r0, r1)     // Catch:{ all -> 0x00a4 }
            if (r18 == 0) goto L_0x0019
            com.microsoft.applications.experimentation.common.EXPClientState r0 = com.microsoft.applications.experimentation.common.EXPClientState.STARTED     // Catch:{ all -> 0x00a4 }
            r15.logEXPClientUpdate(r0)     // Catch:{ all -> 0x00a4 }
        L_0x0019:
            java.io.Serializable r0 = r15.getConfigFromStorage()     // Catch:{ all -> 0x00a4 }
            r15.setActiveConfig(r0)     // Catch:{ all -> 0x00a4 }
            T r0 = r8.activeConfig     // Catch:{ all -> 0x00a4 }
            r13 = 0
            if (r0 == 0) goto L_0x0054
            com.microsoft.applications.experimentation.common.EXPConfigUpdate r0 = com.microsoft.applications.experimentation.common.EXPConfigUpdate.SUCCEEDED     // Catch:{ all -> 0x00a4 }
            com.microsoft.applications.experimentation.common.EXPConfigSource r1 = com.microsoft.applications.experimentation.common.EXPConfigSource.LOCAL     // Catch:{ all -> 0x00a4 }
            r15.logEXPConfigUpdate(r0, r1)     // Catch:{ all -> 0x00a4 }
            r15.addConfigInformationToLoggers()     // Catch:{ all -> 0x00a4 }
            long r0 = r15.getActiveConfigExpireTime()     // Catch:{ all -> 0x00a4 }
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x00a4 }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00a4 }
            long r2 = r2.toSeconds(r3)     // Catch:{ all -> 0x00a4 }
            long r0 = r0 - r2
            r2 = 1
            int r3 = (r0 > r13 ? 1 : (r0 == r13 ? 0 : -1))
            if (r3 < 0) goto L_0x0046
            r3 = r0
            goto L_0x0047
        L_0x0046:
            r3 = r13
        L_0x0047:
            java.lang.String r5 = r15.getRequestParametersFromActiveConfig()     // Catch:{ all -> 0x00a4 }
            java.util.HashMap r6 = r15.getRequestHeadersFromActiveConfig()     // Catch:{ all -> 0x00a4 }
            r7 = 0
            r1 = r15
            r1.sendOutCallbacks(r2, r3, r5, r6, r7)     // Catch:{ all -> 0x00a4 }
        L_0x0054:
            T r0 = r8.activeConfig     // Catch:{ all -> 0x00a4 }
            if (r0 == 0) goto L_0x0081
            boolean r0 = r15.alwaysForceFetchOnStart()     // Catch:{ all -> 0x00a4 }
            if (r0 != 0) goto L_0x0081
            long r0 = r15.getActiveConfigExpireTime()     // Catch:{ all -> 0x00a4 }
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x00a4 }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00a4 }
            long r2 = r2.toSeconds(r3)     // Catch:{ all -> 0x00a4 }
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x0081
            java.lang.String r0 = r8.clientVersion     // Catch:{ all -> 0x00a4 }
            java.lang.String r1 = r15.getActiveConfigClientVersion()     // Catch:{ all -> 0x00a4 }
            boolean r0 = r0.equals(r1)     // Catch:{ all -> 0x00a4 }
            if (r0 != 0) goto L_0x007d
            goto L_0x0081
        L_0x007d:
            r15.scheduleUpdate(r12)     // Catch:{ all -> 0x00a4 }
            goto L_0x009d
        L_0x0081:
            r15.updateConfigFromServer()     // Catch:{ all -> 0x00a4 }
            int r0 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1))
            if (r0 <= 0) goto L_0x009d
            java.lang.Object r1 = r8.objectToNotify     // Catch:{ InterruptedException -> 0x0095 }
            monitor-enter(r1)     // Catch:{ InterruptedException -> 0x0095 }
            java.lang.Object r0 = r8.objectToNotify     // Catch:{ all -> 0x0092 }
            r0.wait(r9)     // Catch:{ all -> 0x0092 }
            monitor-exit(r1)     // Catch:{ all -> 0x0092 }
            goto L_0x009d
        L_0x0092:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0092 }
            throw r0     // Catch:{ InterruptedException -> 0x0095 }
        L_0x0095:
            r0 = move-exception
            java.lang.String r1 = LOG_TAG     // Catch:{ all -> 0x00a4 }
            java.lang.String r2 = "Caught Exception when trying to wait for config. Exception:"
            com.microsoft.applications.experimentation.common.TraceHelper.TraceError(r1, r2, r0)     // Catch:{ all -> 0x00a4 }
        L_0x009d:
            r0 = 1
            r8.started = r0     // Catch:{ all -> 0x00a4 }
            monitor-exit(r11)     // Catch:{ all -> 0x00a4 }
            return r0
        L_0x00a2:
            monitor-exit(r11)     // Catch:{ all -> 0x00a4 }
            return r12
        L_0x00a4:
            r0 = move-exception
            monitor-exit(r11)     // Catch:{ all -> 0x00a4 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.applications.experimentation.common.EXPClient.start(long, boolean):boolean");
    }
}
