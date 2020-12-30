package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Qw
/* compiled from: PG */
public class Inspector {
    public final HybridData mHybridData;

    @Qw
    /* compiled from: PG */
    public static class LocalConnection {
        public final HybridData mHybridData;

        public LocalConnection(HybridData hybridData) {
            this.mHybridData = hybridData;
        }

        public native void disconnect();

        public native void sendMessage(String str);
    }

    @Qw
    /* compiled from: PG */
    public static class Page {
        public final int mId;
        public final String mTitle;
        public final String mVM;

        @Qw
        public Page(int i, String str, String str2) {
            this.mId = i;
            this.mTitle = str;
            this.mVM = str2;
        }

        public int getId() {
            return this.mId;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getVM() {
            return this.mVM;
        }

        public String toString() {
            StringBuilder a = Eo.a("Page{mId=");
            a.append(this.mId);
            a.append(", mTitle='");
            a.append(this.mTitle);
            a.append('\'');
            a.append('}');
            return a.toString();
        }
    }

    @Qw
    /* compiled from: PG */
    public interface RemoteConnection {
        @Qw
        void onDisconnect();

        @Qw
        void onMessage(String str);
    }

    static {
        ReactBridge.staticInit();
    }

    public Inspector(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    public static LocalConnection connect(int i, RemoteConnection remoteConnection) {
        try {
            return instance().connectNative(i, remoteConnection);
        } catch (UnsatisfiedLinkError e) {
            pq.a("ReactNative", "Inspector doesn't work in open source yet", e);
            throw new RuntimeException(e);
        }
    }

    private native LocalConnection connectNative(int i, RemoteConnection remoteConnection);

    public static List<Page> getPages() {
        try {
            return Arrays.asList(instance().getPagesNative());
        } catch (UnsatisfiedLinkError e) {
            pq.a("ReactNative", "Inspector doesn't work in open source yet", e);
            return Collections.emptyList();
        }
    }

    private native Page[] getPagesNative();

    public static native Inspector instance();
}
