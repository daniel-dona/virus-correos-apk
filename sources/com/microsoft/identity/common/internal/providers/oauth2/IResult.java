package com.microsoft.identity.common.internal.providers.oauth2;

/* compiled from: PG */
public interface IResult {
    IErrorResponse getErrorResponse();

    boolean getSuccess();

    ISuccessResponse getSuccessResponse();
}
