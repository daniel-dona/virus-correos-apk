package org.chromium.mojo.system;

/* compiled from: PG */
public class MojoException extends RuntimeException {
    public final int mCode;

    public MojoException(int i) {
        this.mCode = i;
    }

    public int getMojoResult() {
        return this.mCode;
    }

    public String toString() {
        StringBuilder a = Eo.a("MojoResult(");
        a.append(this.mCode);
        a.append("): ");
        String str = "UNKNOWN";
        switch (this.mCode) {
            case 0:
                str = "OK";
                break;
            case 1:
                str = "CANCELLED";
                break;
            case 3:
                str = "INVALID_ARGUMENT";
                break;
            case 4:
                str = "DEADLINE_EXCEEDED";
                break;
            case 5:
                str = "NOT_FOUND";
                break;
            case 6:
                str = "ALREADY_EXISTS";
                break;
            case 7:
                str = "PERMISSION_DENIED";
                break;
            case 8:
                str = "RESOURCE_EXHAUSTED";
                break;
            case 9:
                str = "FAILED_PRECONDITION";
                break;
            case 10:
                str = "ABORTED";
                break;
            case 11:
                str = "OUT_OF_RANGE";
                break;
            case 12:
                str = "UNIMPLEMENTED";
                break;
            case 13:
                str = "INTERNAL";
                break;
            case 14:
                str = "UNAVAILABLE";
                break;
            case 15:
                str = "DATA_LOSS";
                break;
            case 16:
                str = "BUSY";
                break;
            case 17:
                str = "SHOULD_WAIT";
                break;
        }
        a.append(str);
        return a.toString();
    }

    public MojoException(Throwable th) {
        super(th);
        this.mCode = 2;
    }
}
