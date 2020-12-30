package com.google.ipc.invalidation.external.client;

import com.google.ipc.invalidation.external.client.types.Status;

/* compiled from: PG */
public interface SystemResources {

    /* compiled from: PG */
    public interface Logger extends nN, ResourceComponent {
    }

    /* compiled from: PG */
    public interface NetworkChannel extends ResourceComponent {

        /* compiled from: PG */
        public interface NetworkListener {
            void onAddressChange();

            void onMessageReceived(byte[] bArr);

            void onOnlineStatusChange(boolean z);
        }

        void sendMessage(byte[] bArr);

        void setListener(NetworkListener networkListener);
    }

    /* compiled from: PG */
    public interface ResourceComponent {
        void setSystemResources(SystemResources systemResources);
    }

    /* compiled from: PG */
    public interface Scheduler extends ResourceComponent {
        public static final int NO_DELAY = 0;

        long getCurrentTimeMs();

        boolean isRunningOnThread();

        void schedule(int i, Runnable runnable);
    }

    /* compiled from: PG */
    public interface Storage extends ResourceComponent {
        void deleteKey(String str, iL<Boolean> iLVar);

        void readAllKeys(iL<mL<Status, String>> iLVar);

        void readKey(String str, iL<mL<Status, byte[]>> iLVar);

        void writeKey(String str, byte[] bArr, iL<Status> iLVar);
    }
}
