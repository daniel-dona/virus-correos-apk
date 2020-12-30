package com.facebook.imagepipeline.systrace;

/* compiled from: PG */
public abstract class FrescoSystrace {

    /* renamed from: a */
    public static final ArgsBuilder f413a = new Dw((Cw) null);

    /* renamed from: b */
    public static volatile Systrace f414b;

    /* compiled from: PG */
    public interface ArgsBuilder {
        ArgsBuilder arg(String str, double d);

        ArgsBuilder arg(String str, int i);

        ArgsBuilder arg(String str, long j);

        ArgsBuilder arg(String str, Object obj);

        void flush();
    }

    /* compiled from: PG */
    public interface Systrace {
        void beginSection(String str);

        ArgsBuilder beginSectionWithArgs(String str);

        void endSection();

        boolean isTracing();
    }

    /* renamed from: a */
    public static void m408a() {
        m409b().endSection();
    }

    /* renamed from: b */
    public static Systrace m409b() {
        if (f414b == null) {
            synchronized (FrescoSystrace.class) {
                if (f414b == null) {
                    f414b = new Bw();
                }
            }
        }
        return f414b;
    }
}
