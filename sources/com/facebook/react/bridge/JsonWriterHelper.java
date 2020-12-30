package com.facebook.react.bridge;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
public class JsonWriterHelper {
    public static void listValue(JsonWriter jsonWriter, List<?> list) throws IOException {
        jsonWriter.beginArray();
        for (Object objectValue : list) {
            objectValue(jsonWriter, objectValue);
        }
        jsonWriter.endArray();
    }

    public static void mapValue(JsonWriter jsonWriter, Map<?, ?> map) throws IOException {
        jsonWriter.beginObject();
        for (Map.Entry next : map.entrySet()) {
            jsonWriter.name(next.getKey().toString());
            value(jsonWriter, next.getValue());
        }
        jsonWriter.endObject();
    }

    public static void objectValue(JsonWriter jsonWriter, Object obj) throws IOException {
        if (obj == null) {
            jsonWriter.nullValue();
        } else if (obj instanceof String) {
            jsonWriter.value((String) obj);
        } else if (obj instanceof Number) {
            jsonWriter.value((Number) obj);
        } else if (obj instanceof Boolean) {
            jsonWriter.value(((Boolean) obj).booleanValue());
        } else {
            throw new IllegalArgumentException("Unknown value: " + obj);
        }
    }

    public static void value(JsonWriter jsonWriter, Object obj) throws IOException {
        if (obj instanceof Map) {
            mapValue(jsonWriter, (Map) obj);
        } else if (obj instanceof List) {
            listValue(jsonWriter, (List) obj);
        } else {
            objectValue(jsonWriter, obj);
        }
    }
}
