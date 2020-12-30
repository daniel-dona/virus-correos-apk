package com.facebook.common.memory;

import java.io.Closeable;

/* compiled from: PG */
public interface PooledByteBuffer extends Closeable {

    /* compiled from: PG */
    public static class ClosedException extends RuntimeException {
        public ClosedException() {
            super("Invalid bytebuf. Already closed");
        }
    }
}
