package org.chromium.mojo.system;

import java.nio.ByteBuffer;

/* compiled from: PG */
public interface DataPipe$ConsumerHandle extends lk3 {
    ByteBuffer beginReadData(int i, ik3 ik3);

    int discardData(int i, ik3 ik3);

    void endReadData(int i);

    DataPipe$ConsumerHandle pass();

    ResultAnd<Integer> readData(ByteBuffer byteBuffer, ik3 ik3);
}
