package com.google.gson;

/* compiled from: PG */
public enum LongSerializationPolicy {
    DEFAULT {
        public RJ serialize(Long l) {
            return new VJ(l);
        }
    },
    STRING {
        public RJ serialize(Long l) {
            return new VJ(String.valueOf(l));
        }
    };

    public abstract RJ serialize(Long l);
}
