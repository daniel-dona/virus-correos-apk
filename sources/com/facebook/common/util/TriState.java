package com.facebook.common.util;

/* compiled from: PG */
public enum TriState {
    YES,
    NO,
    UNSET;

    public static TriState fromDbValue(int i) {
        if (i == 1) {
            return YES;
        }
        if (i != 2) {
            return UNSET;
        }
        return NO;
    }

    public boolean asBoolean() {
        int ordinal = ordinal();
        if (ordinal == 0) {
            return true;
        }
        if (ordinal == 1) {
            return false;
        }
        if (ordinal != 2) {
            throw new IllegalStateException("Unrecognized TriState value: " + this);
        }
        throw new IllegalStateException("No boolean equivalent for UNSET");
    }

    public Boolean asBooleanObject() {
        int ordinal = ordinal();
        if (ordinal == 0) {
            return Boolean.TRUE;
        }
        if (ordinal == 1) {
            return Boolean.FALSE;
        }
        if (ordinal == 2) {
            return null;
        }
        throw new IllegalStateException("Unrecognized TriState value: " + this);
    }

    public int getDbValue() {
        int ordinal = ordinal();
        if (ordinal != 0) {
            return ordinal != 1 ? 3 : 2;
        }
        return 1;
    }

    public boolean isSet() {
        return this != UNSET;
    }

    public static TriState valueOf(boolean z) {
        return z ? YES : NO;
    }

    public static TriState valueOf(Boolean bool) {
        return bool != null ? valueOf(bool.booleanValue()) : UNSET;
    }

    public boolean asBoolean(boolean z) {
        int ordinal = ordinal();
        if (ordinal == 0) {
            return true;
        }
        if (ordinal == 1) {
            return false;
        }
        if (ordinal == 2) {
            return z;
        }
        throw new IllegalStateException("Unrecognized TriState value: " + this);
    }
}
