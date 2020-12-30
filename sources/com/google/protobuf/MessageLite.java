package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: PG */
public interface MessageLite extends cO {

    /* compiled from: PG */
    public interface Builder extends cO, Cloneable {
        MessageLite build();

        MessageLite buildPartial();

        Builder clear();

        Builder clone();

        boolean mergeDelimitedFrom(InputStream inputStream) throws IOException;

        boolean mergeDelimitedFrom(InputStream inputStream, RN rn) throws IOException;

        Builder mergeFrom(LN ln) throws IOException;

        Builder mergeFrom(LN ln, RN rn) throws IOException;

        Builder mergeFrom(ByteString byteString) throws InvalidProtocolBufferException;

        Builder mergeFrom(ByteString byteString, RN rn) throws InvalidProtocolBufferException;

        Builder mergeFrom(MessageLite messageLite);

        Builder mergeFrom(InputStream inputStream) throws IOException;

        Builder mergeFrom(InputStream inputStream, RN rn) throws IOException;

        Builder mergeFrom(byte[] bArr) throws InvalidProtocolBufferException;

        Builder mergeFrom(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException;

        Builder mergeFrom(byte[] bArr, int i, int i2, RN rn) throws InvalidProtocolBufferException;

        Builder mergeFrom(byte[] bArr, RN rn) throws InvalidProtocolBufferException;
    }

    /* renamed from: a */
    Builder mo2582a();

    /* renamed from: a */
    void mo2583a(CodedOutputStream codedOutputStream) throws IOException;

    /* renamed from: b */
    int mo2584b();

    /* renamed from: c */
    Builder mo2585c();
}
