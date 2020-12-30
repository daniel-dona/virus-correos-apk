package com.facebook.react.packagerconnection;

import okio.ByteString;

/* compiled from: PG */
public interface ReconnectingWebSocket$MessageCallback {
    void onMessage(String str);

    void onMessage(ByteString byteString);
}
