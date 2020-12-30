package com.facebook.drawee.components;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/* compiled from: PG */
public class DraweeEventTracker {

    /* renamed from: b */
    public static final DraweeEventTracker f259b = new DraweeEventTracker();

    /* renamed from: c */
    public static boolean f260c = true;

    /* renamed from: a */
    public final Queue<Event> f261a = new ArrayBlockingQueue(20);

    /* compiled from: PG */
    public enum Event {
        ON_SET_HIERARCHY,
        ON_CLEAR_HIERARCHY,
        ON_SET_CONTROLLER,
        ON_CLEAR_OLD_CONTROLLER,
        ON_CLEAR_CONTROLLER,
        ON_INIT_CONTROLLER,
        ON_ATTACH_CONTROLLER,
        ON_DETACH_CONTROLLER,
        ON_RELEASE_CONTROLLER,
        ON_DATASOURCE_SUBMIT,
        ON_DATASOURCE_RESULT,
        ON_DATASOURCE_RESULT_INT,
        ON_DATASOURCE_FAILURE,
        ON_DATASOURCE_FAILURE_INT,
        ON_HOLDER_ATTACH,
        ON_HOLDER_DETACH,
        ON_DRAWABLE_SHOW,
        ON_DRAWABLE_HIDE,
        ON_ACTIVITY_START,
        ON_ACTIVITY_STOP,
        ON_RUN_CLEAR_CONTROLLER,
        ON_SCHEDULE_CLEAR_CONTROLLER,
        ON_SAME_CONTROLLER_SKIPPED,
        ON_SUBMIT_CACHE_HIT
    }

    /* renamed from: a */
    public static DraweeEventTracker m273a() {
        return f260c ? new DraweeEventTracker() : f259b;
    }

    public String toString() {
        return this.f261a.toString();
    }

    /* renamed from: a */
    public void mo359a(Event event) {
        if (f260c) {
            if (this.f261a.size() + 1 > 20) {
                this.f261a.poll();
            }
            this.f261a.add(event);
        }
    }
}
