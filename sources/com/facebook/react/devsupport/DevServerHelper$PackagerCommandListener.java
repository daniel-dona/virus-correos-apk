package com.facebook.react.devsupport;

import java.util.Map;

/* compiled from: PG */
public interface DevServerHelper$PackagerCommandListener {
    Map<String, Object> customCommandHandlers();

    void onCaptureHeapCommand(Ez ez);

    void onPackagerConnected();

    void onPackagerDevMenuCommand();

    void onPackagerDisconnected();

    void onPackagerReloadCommand();
}
