package com.facebook.react.bridge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

/* compiled from: PG */
public final class FallbackJSBundleLoader extends JSBundleLoader {
    public static final String RECOVERABLE = "facebook::react::Recoverable";
    public static final String TAG = "FallbackJSBundleLoader";
    public Stack<JSBundleLoader> mLoaders = new Stack<>();
    public final ArrayList<Exception> mRecoveredErrors = new ArrayList<>();

    public FallbackJSBundleLoader(List<JSBundleLoader> list) {
        ListIterator<JSBundleLoader> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            this.mLoaders.push(listIterator.previous());
        }
    }

    private JSBundleLoader getDelegateLoader() {
        if (!this.mLoaders.empty()) {
            return this.mLoaders.peek();
        }
        Throwable runtimeException = new RuntimeException("No fallback options available");
        Iterator<Exception> it = this.mRecoveredErrors.iterator();
        Throwable th = runtimeException;
        while (it.hasNext()) {
            th.initCause(it.next());
            while (th.getCause() != null) {
                th = th.getCause();
            }
        }
        throw runtimeException;
    }

    public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
        while (true) {
            try {
                return getDelegateLoader().loadScript(jSBundleLoaderDelegate);
            } catch (Exception e) {
                if (e.getMessage() == null || !e.getMessage().startsWith(RECOVERABLE)) {
                    throw e;
                }
                this.mLoaders.pop();
                this.mRecoveredErrors.add(e);
                if (pq.a.a(6)) {
                    pq.a.a(TAG, "Falling back from recoverable error", e);
                }
            }
        }
        throw e;
    }
}
