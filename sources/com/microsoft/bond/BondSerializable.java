package com.microsoft.bond;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: PG */
public interface BondSerializable {
    BondSerializable clone();

    void marshal(ProtocolWriter protocolWriter) throws IOException;

    boolean memberwiseCompare(Object obj);

    void read(ProtocolReader protocolReader) throws IOException;

    void read(ProtocolReader protocolReader, BondSerializable bondSerializable) throws IOException;

    void readNested(ProtocolReader protocolReader) throws IOException;

    void reset();

    void unmarshal(InputStream inputStream) throws IOException;

    void unmarshal(InputStream inputStream, BondSerializable bondSerializable) throws IOException;

    void write(ProtocolWriter protocolWriter) throws IOException;

    void writeNested(ProtocolWriter protocolWriter, boolean z) throws IOException;
}
