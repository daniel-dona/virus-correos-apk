package com.facebook.react.bridge;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayDeque;
import java.util.Deque;

/* compiled from: PG */
public class JsonWriter implements Closeable {
    public final Deque<Scope> mScopes = new ArrayDeque();
    public final Writer mWriter;

    /* renamed from: com.facebook.react.bridge.JsonWriter$1 */
    /* compiled from: PG */
    public static /* synthetic */ class C01291 {
        public static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$JsonWriter$Scope = new int[Scope.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0011 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0018 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001f */
        static {
            /*
                com.facebook.react.bridge.JsonWriter$Scope[] r0 = com.facebook.react.bridge.JsonWriter.Scope.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$react$bridge$JsonWriter$Scope = r0
                r0 = 1
                r1 = 2
                int[] r2 = $SwitchMap$com$facebook$react$bridge$JsonWriter$Scope     // Catch:{ NoSuchFieldError -> 0x0011 }
                com.facebook.react.bridge.JsonWriter$Scope r3 = com.facebook.react.bridge.JsonWriter.Scope.EMPTY_ARRAY     // Catch:{ NoSuchFieldError -> 0x0011 }
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0011 }
            L_0x0011:
                int[] r2 = $SwitchMap$com$facebook$react$bridge$JsonWriter$Scope     // Catch:{ NoSuchFieldError -> 0x0018 }
                com.facebook.react.bridge.JsonWriter$Scope r3 = com.facebook.react.bridge.JsonWriter.Scope.EMPTY_OBJECT     // Catch:{ NoSuchFieldError -> 0x0018 }
                r3 = 0
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0018 }
            L_0x0018:
                int[] r1 = $SwitchMap$com$facebook$react$bridge$JsonWriter$Scope     // Catch:{ NoSuchFieldError -> 0x001f }
                com.facebook.react.bridge.JsonWriter$Scope r2 = com.facebook.react.bridge.JsonWriter.Scope.ARRAY     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 3
                r1[r2] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r1 = $SwitchMap$com$facebook$react$bridge$JsonWriter$Scope     // Catch:{ NoSuchFieldError -> 0x0026 }
                com.facebook.react.bridge.JsonWriter$Scope r2 = com.facebook.react.bridge.JsonWriter.Scope.OBJECT     // Catch:{ NoSuchFieldError -> 0x0026 }
                r2 = 4
                r1[r0] = r2     // Catch:{ NoSuchFieldError -> 0x0026 }
            L_0x0026:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.JsonWriter.C01291.<clinit>():void");
        }
    }

    /* compiled from: PG */
    public enum Scope {
        EMPTY_OBJECT,
        OBJECT,
        EMPTY_ARRAY,
        ARRAY
    }

    public JsonWriter(Writer writer) {
        this.mWriter = writer;
    }

    private void beforeName() throws IOException {
        Scope peek = this.mScopes.peek();
        int ordinal = peek.ordinal();
        if (ordinal == 0) {
            replace(Scope.OBJECT);
        } else if (ordinal == 1) {
            this.mWriter.write(44);
        } else if (ordinal == 2 || ordinal == 3) {
            throw new IllegalStateException("name not allowed in array");
        } else {
            throw new IllegalStateException("Unknown scope: " + peek);
        }
    }

    private void beforeValue() throws IOException {
        Scope peek = this.mScopes.peek();
        int ordinal = peek.ordinal();
        if (ordinal == 0) {
            Scope scope = Scope.EMPTY_OBJECT;
            throw new IllegalArgumentException("EMPTY_OBJECT");
        } else if (ordinal == 1) {
        } else {
            if (ordinal == 2) {
                replace(Scope.ARRAY);
            } else if (ordinal == 3) {
                this.mWriter.write(44);
            } else {
                throw new IllegalStateException("Unknown scope: " + peek);
            }
        }
    }

    private void open(Scope scope, char c) throws IOException {
        this.mScopes.push(scope);
        this.mWriter.write(c);
    }

    private void replace(Scope scope) {
        this.mScopes.pop();
        this.mScopes.push(scope);
    }

    private void string(String str) throws IOException {
        this.mWriter.write(34);
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == 12) {
                this.mWriter.write("\\f");
            } else if (charAt != 13) {
                if (charAt != '\"' && charAt != '\\') {
                    if (charAt != 8232 && charAt != 8233) {
                        switch (charAt) {
                            case 8:
                                this.mWriter.write("\\b");
                                break;
                            case 9:
                                this.mWriter.write("\\t");
                                break;
                            case 10:
                                this.mWriter.write("\\n");
                                break;
                            default:
                                if (charAt > 31) {
                                    this.mWriter.write(charAt);
                                    break;
                                } else {
                                    this.mWriter.write(String.format("\\u%04x", new Object[]{Integer.valueOf(charAt)}));
                                    break;
                                }
                        }
                    } else {
                        this.mWriter.write(String.format("\\u%04x", new Object[]{Integer.valueOf(charAt)}));
                    }
                } else {
                    this.mWriter.write(92);
                    this.mWriter.write(charAt);
                }
            } else {
                this.mWriter.write("\\r");
            }
        }
        this.mWriter.write(34);
    }

    public JsonWriter beginArray() throws IOException {
        open(Scope.EMPTY_ARRAY, '[');
        return this;
    }

    public JsonWriter beginObject() throws IOException {
        open(Scope.EMPTY_OBJECT, '{');
        return this;
    }

    public void close() throws IOException {
        this.mWriter.close();
    }

    public JsonWriter endArray() throws IOException {
        close(']');
        return this;
    }

    public JsonWriter endObject() throws IOException {
        close('}');
        return this;
    }

    public JsonWriter name(String str) throws IOException {
        if (str != null) {
            beforeName();
            string(str);
            this.mWriter.write(58);
            return this;
        }
        throw new NullPointerException("name can not be null");
    }

    public JsonWriter nullValue() throws IOException {
        beforeValue();
        this.mWriter.write("null");
        return this;
    }

    public JsonWriter rawValue(String str) throws IOException {
        beforeValue();
        this.mWriter.write(str);
        return this;
    }

    public JsonWriter value(String str) throws IOException {
        if (str == null) {
            return nullValue();
        }
        beforeValue();
        string(str);
        return this;
    }

    private void close(char c) throws IOException {
        this.mScopes.pop();
        this.mWriter.write(c);
    }

    public JsonWriter value(boolean z) throws IOException {
        beforeValue();
        this.mWriter.write(z ? "true" : "false");
        return this;
    }

    public JsonWriter value(double d) throws IOException {
        beforeValue();
        this.mWriter.append(Double.toString(d));
        return this;
    }

    public JsonWriter value(long j) throws IOException {
        beforeValue();
        this.mWriter.write(Long.toString(j));
        return this;
    }

    public JsonWriter value(Number number) throws IOException {
        if (number == null) {
            return nullValue();
        }
        beforeValue();
        this.mWriter.append(number.toString());
        return this;
    }
}
