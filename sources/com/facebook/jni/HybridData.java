package com.facebook.jni;

import Mw;
import com.facebook.soloader.SoLoader;

@Qw
/* compiled from: PG */
public class HybridData {
    @Qw
    public Destructor mDestructor = new Destructor(this);

    /* compiled from: PG */
    public static class Destructor extends Mw.a {
        @Qw
        public long mNativePointer;

        public Destructor(Object obj) {
            super(obj);
        }

        public static native void deleteNative(long j);

        /* renamed from: a */
        public void mo570a() {
            deleteNative(this.mNativePointer);
            this.mNativePointer = 0;
        }
    }

    static {
        SoLoader.m690a("fb");
    }

    /* renamed from: a */
    public synchronized void mo569a() {
        this.mDestructor.mo570a();
    }
}
