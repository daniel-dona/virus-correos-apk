package com.facebook.cache.common;

/* compiled from: PG */
public interface CacheEventListener {

    /* compiled from: PG */
    public enum EvictionReason {
        CACHE_FULL,
        CONTENT_STALE,
        USER_FORCED,
        CACHE_MANAGER_TRIMMED
    }
}
