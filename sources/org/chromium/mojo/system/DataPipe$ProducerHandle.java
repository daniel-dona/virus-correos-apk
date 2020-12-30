package org.chromium.mojo.system;

import java.nio.ByteBuffer;

/* compiled from: PG */
public interface DataPipe$ProducerHandle extends lk3 {
    ByteBuffer beginWriteData(int i, jk3 jk3);

    void endWriteData(int i);

    DataPipe$ProducerHandle pass();

    ResultAnd<Integer> writeData(ByteBuffer byteBuffer, jk3 jk3);
}
