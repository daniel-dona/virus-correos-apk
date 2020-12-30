package com.microsoft.identity.common.internal.dto;

import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
public abstract class AccountCredentialBase {
    public transient Map<String, RJ> mAdditionalFields = new HashMap();

    public Map<String, RJ> getAdditionalFields() {
        return this.mAdditionalFields;
    }

    public void setAdditionalFields(Map<String, RJ> map) {
        this.mAdditionalFields = map;
    }

    public String toString() {
        StringBuilder a = Eo.a("AccountCredentialBase{mAdditionalFields=");
        a.append(this.mAdditionalFields);
        a.append('}');
        return a.toString();
    }
}
