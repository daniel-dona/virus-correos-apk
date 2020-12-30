package com.facebook.react.bridge;

@Qw
/* compiled from: PG */
public interface JavaJSExecutor {

    /* compiled from: PG */
    public interface Factory {
        JavaJSExecutor create() throws Exception;
    }

    /* compiled from: PG */
    public static class ProxyExecutorException extends Exception {
        public ProxyExecutorException(Throwable th) {
            super(th);
        }
    }

    void close();

    @Qw
    String executeJSCall(String str, String str2) throws ProxyExecutorException;

    @Qw
    void loadApplicationScript(String str) throws ProxyExecutorException;

    @Qw
    void setGlobalVariable(String str, String str2);
}
