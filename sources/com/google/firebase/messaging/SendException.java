package com.google.firebase.messaging;

import com.microsoft.identity.common.exception.ServiceException;
import java.util.Locale;

/* compiled from: PG */
public final class SendException extends Exception {
    public static final int ERROR_INVALID_PARAMETERS = 1;
    public static final int ERROR_SIZE = 2;
    public static final int ERROR_TOO_MANY_MESSAGES = 4;
    public static final int ERROR_TTL_EXCEEDED = 3;
    public static final int ERROR_UNKNOWN = 0;
    public final int errorCode;

    public SendException(String str) {
        super(str);
        int i = 0;
        if (str != null) {
            String lowerCase = str.toLowerCase(Locale.US);
            char c = 65535;
            switch (lowerCase.hashCode()) {
                case -1743242157:
                    if (lowerCase.equals(ServiceException.SERVICE_NOT_AVAILABLE)) {
                        c = 3;
                        break;
                    }
                    break;
                case -1290953729:
                    if (lowerCase.equals("toomanymessages")) {
                        c = 4;
                        break;
                    }
                    break;
                case -920906446:
                    if (lowerCase.equals("invalid_parameters")) {
                        c = 0;
                        break;
                    }
                    break;
                case -617027085:
                    if (lowerCase.equals("messagetoobig")) {
                        c = 2;
                        break;
                    }
                    break;
                case -95047692:
                    if (lowerCase.equals("missing_to")) {
                        c = 1;
                        break;
                    }
                    break;
            }
            if (c == 0 || c == 1) {
                i = 1;
            } else if (c == 2) {
                i = 2;
            } else if (c == 3) {
                i = 3;
            } else if (c == 4) {
                i = 4;
            }
        }
        this.errorCode = i;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }
}
