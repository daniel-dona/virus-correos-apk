package com.nimbusds.jose;

import com.microsoft.bing.visualsearch.camerasearchv2.content.model.OCRItem$OCRActionType;
import java.io.Serializable;
import net.minidev.json.JSONObject;

/* compiled from: PG */
public class Algorithm implements mB0, Serializable {
    public static final Algorithm NONE = new Algorithm(OCRItem$OCRActionType.OCR_NONE, Requirement.REQUIRED);
    public static final long serialVersionUID = 1;
    public final String name;
    public final Requirement requirement;

    public Algorithm(String str, Requirement requirement2) {
        if (str != null) {
            this.name = str;
            this.requirement = requirement2;
            return;
        }
        throw new IllegalArgumentException("The algorithm name must not be null");
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Algorithm) && toString().equals(obj.toString());
    }

    public final String getName() {
        return this.name;
    }

    public final Requirement getRequirement() {
        return this.requirement;
    }

    public final int hashCode() {
        return this.name.hashCode();
    }

    public final String toJSONString() {
        return "\"" + JSONObject.escape(this.name) + '\"';
    }

    public final String toString() {
        return this.name;
    }

    public Algorithm(String str) {
        this(str, (Requirement) null);
    }
}
