package com.microsoft.identity.common.internal.logging;

import java.util.Map;

/* compiled from: PG */
public interface IRequestContext extends Map<String, String> {
    String toJsonString();
}
