package com.nimbusds.jose;

import java.io.Serializable;
import net.minidev.json.JSONObject;

/* compiled from: PG */
public final class JOSEObjectType implements mB0, Serializable {
    public static final JOSEObjectType JOSE = new JOSEObjectType("JOSE");
    public static final JOSEObjectType JOSE_JSON = new JOSEObjectType("JOSE+JSON");
    public static final JOSEObjectType JWT = new JOSEObjectType("JWT");
    public static final long serialVersionUID = 1;
    public final String type;

    public JOSEObjectType(String str) {
        if (str != null) {
            this.type = str;
            return;
        }
        throw new IllegalArgumentException("The object type must not be null");
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof JOSEObjectType) && toString().equals(obj.toString());
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    public String toJSONString() {
        return "\"" + JSONObject.escape(this.type) + '\"';
    }

    public String toString() {
        return this.type;
    }
}
