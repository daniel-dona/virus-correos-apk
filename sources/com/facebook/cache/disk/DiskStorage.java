package com.facebook.cache.disk;

import java.io.IOException;
import java.util.Collection;

/* compiled from: PG */
public interface DiskStorage {

    /* compiled from: PG */
    public interface Entry {
        String getId();

        np getResource();

        long getSize();

        long getTimestamp();
    }

    /* compiled from: PG */
    public interface Inserter {
        boolean cleanUp();

        np commit(Object obj) throws IOException;

        void writeData(wp wpVar, Object obj) throws IOException;
    }

    /* renamed from: a */
    long mo297a(Entry entry) throws IOException;

    /* renamed from: a */
    Inserter mo299a(String str, Object obj) throws IOException;

    /* renamed from: a */
    void mo301a();

    /* renamed from: b */
    Collection<Entry> mo304b() throws IOException;

    /* renamed from: b */
    boolean mo305b(String str, Object obj) throws IOException;

    /* renamed from: c */
    np mo306c(String str, Object obj) throws IOException;

    void clearAll() throws IOException;

    boolean isExternal();

    long remove(String str) throws IOException;
}
