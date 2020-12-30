package com.facebook.jni;

import java.util.Iterator;

@Qw
/* compiled from: PG */
public class IteratorHelper {

    /* renamed from: a */
    public final Iterator f415a;
    @Qw
    public Object mElement;

    @Qw
    public IteratorHelper(Iterator it) {
        this.f415a = it;
    }

    @Qw
    public boolean hasNext() {
        if (this.f415a.hasNext()) {
            this.mElement = this.f415a.next();
            return true;
        }
        this.mElement = null;
        return false;
    }

    @Qw
    public IteratorHelper(Iterable iterable) {
        this.f415a = iterable.iterator();
    }
}
