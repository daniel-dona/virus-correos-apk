package com.facebook.react.uimanager.util;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
public abstract class ReactFindViewUtil {

    /* renamed from: a */
    public static final List<OnViewFoundListener> f564a = new ArrayList();

    /* renamed from: b */
    public static final Map<OnMultipleViewsFoundListener, Set<String>> f565b = new HashMap();

    /* compiled from: PG */
    public interface OnMultipleViewsFoundListener {
        void onViewFound(View view, String str);
    }

    /* compiled from: PG */
    public interface OnViewFoundListener {
        String getNativeId();

        void onViewFound(View view);
    }

    /* renamed from: a */
    public static void m540a(View view) {
        Object tag = view.getTag(ox0.view_tag_native_id);
        String str = tag instanceof String ? (String) tag : null;
        if (str != null) {
            Iterator<OnViewFoundListener> it = f564a.iterator();
            while (it.hasNext()) {
                OnViewFoundListener next = it.next();
                if (str.equals(next.getNativeId())) {
                    next.onViewFound(view);
                    it.remove();
                }
            }
            for (Map.Entry next2 : f565b.entrySet()) {
                Set set = (Set) next2.getValue();
                if (set != null && set.contains(str)) {
                    ((OnMultipleViewsFoundListener) next2.getKey()).onViewFound(view, str);
                }
            }
        }
    }
}
