package com.google.gson;

import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/* compiled from: PG */
public abstract class TypeAdapter<T> {
    public final T fromJson(Reader reader) throws IOException {
        return read(new VK(reader));
    }

    public final T fromJsonTree(RJ rj) {
        try {
            return read(new LK(rj));
        } catch (IOException e) {
            throw new JsonIOException((Throwable) e);
        }
    }

    public final TypeAdapter<T> nullSafe() {
        return new TypeAdapter<T>() {
            public T read(VK vk) throws IOException {
                if (vk.j0() != JsonToken.NULL) {
                    return TypeAdapter.this.read(vk);
                }
                vk.Y();
                return null;
            }

            public void write(WK wk, T t) throws IOException {
                if (t == null) {
                    wk.E();
                } else {
                    TypeAdapter.this.write(wk, t);
                }
            }
        };
    }

    public abstract T read(VK vk) throws IOException;

    public final void toJson(Writer writer, T t) throws IOException {
        write(new WK(writer), t);
    }

    public final RJ toJsonTree(T t) {
        try {
            NK nk = new NK();
            write(nk, t);
            return nk.H();
        } catch (IOException e) {
            throw new JsonIOException((Throwable) e);
        }
    }

    public abstract void write(WK wk, T t) throws IOException;

    public final T fromJson(String str) throws IOException {
        return fromJson((Reader) new StringReader(str));
    }

    public final String toJson(T t) {
        StringWriter stringWriter = new StringWriter();
        try {
            toJson(stringWriter, t);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
