package com.microsoft.bond;

import java.io.Closeable;
import java.io.IOException;

/* compiled from: PG */
public abstract class ProtocolWriter implements Closeable {
    public void close() throws IOException {
    }

    public ProtocolWriter getFirstPassWriter() {
        return null;
    }

    public boolean hasCapability(ProtocolCapability protocolCapability) {
        return false;
    }

    public void writeBegin() {
    }

    public abstract void writeBlob(BondBlob bondBlob) throws IOException;

    public abstract void writeBool(boolean z) throws IOException;

    public abstract void writeContainerBegin(int i, BondDataType bondDataType) throws IOException;

    public abstract void writeContainerBegin(int i, BondDataType bondDataType, BondDataType bondDataType2) throws IOException;

    public abstract void writeContainerEnd() throws IOException;

    public abstract void writeDouble(double d) throws IOException;

    public void writeEnd() {
    }

    public void writeFieldBegin(BondDataType bondDataType, int i, BondSerializable bondSerializable) throws IOException {
    }

    public void writeFieldEnd() throws IOException {
    }

    public void writeFieldOmitted(BondDataType bondDataType, int i, BondSerializable bondSerializable) throws IOException {
    }

    public abstract void writeFloat(float f) throws IOException;

    public abstract void writeInt16(short s) throws IOException;

    public abstract void writeInt32(int i) throws IOException;

    public abstract void writeInt64(long j) throws IOException;

    public abstract void writeInt8(byte b) throws IOException;

    public abstract void writeString(String str) throws IOException;

    public void writeStructBegin(BondSerializable bondSerializable, boolean z) throws IOException {
    }

    public void writeStructEnd(boolean z) throws IOException {
    }

    public abstract void writeUInt16(short s) throws IOException;

    public abstract void writeUInt32(int i) throws IOException;

    public abstract void writeUInt64(long j) throws IOException;

    public abstract void writeUInt8(byte b) throws IOException;

    public abstract void writeVersion() throws IOException;

    public abstract void writeWString(String str) throws IOException;
}
