package com.nimbusds.jose;

import java.io.Serializable;
import net.minidev.json.JSONObject;

/* compiled from: PG */
public final class CompressionAlgorithm implements mB0, Serializable {
    public static final CompressionAlgorithm DEF = new CompressionAlgorithm("DEF");
    public static final long serialVersionUID = 1;
    public final String name;

    public CompressionAlgorithm(String str) {
        if (str != null) {
            this.name = str;
            return;
        }
        throw new IllegalArgumentException("The compression algorithm name must not be null");
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof CompressionAlgorithm) && toString().equals(obj.toString());
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toJSONString() {
        return "\"" + JSONObject.escape(this.name) + '\"';
    }

    public String toString() {
        return this.name;
    }
}
