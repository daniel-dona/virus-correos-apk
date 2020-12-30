package com.facebook.systrace;

/* compiled from: PG */
public abstract class Systrace {

    /* compiled from: PG */
    public enum EventScope {
        THREAD('t'),
        PROCESS('p'),
        GLOBAL('g');
        
        public final char mCode;

        /* access modifiers changed from: public */
        EventScope(char c) {
            this.mCode = c;
        }

        public char getCode() {
            return this.mCode;
        }
    }

    /* renamed from: a */
    public static void m698a() {
    }

    /* renamed from: b */
    public static boolean m699b() {
        return false;
    }
}
