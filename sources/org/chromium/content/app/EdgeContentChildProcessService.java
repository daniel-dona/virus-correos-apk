package org.chromium.content.app;

import org.chromium.base.process_launcher.ChildProcessService;

/* compiled from: PG */
public class EdgeContentChildProcessService extends ChildProcessService {
    public EdgeContentChildProcessService() {
        super(new EdgeContentChildProcessServiceDelegate());
    }
}
