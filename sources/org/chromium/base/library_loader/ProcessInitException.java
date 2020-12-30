package org.chromium.base.library_loader;

/* compiled from: PG */
public class ProcessInitException extends Exception {
    public int mErrorCode = 0;

    public ProcessInitException(int i) {
        this.mErrorCode = i;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public ProcessInitException(int i, Throwable th) {
        super((String) null, th);
        this.mErrorCode = i;
    }
}
