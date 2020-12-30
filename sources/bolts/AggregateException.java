package bolts;

import com.microsoft.identity.common.internal.cache.AbstractAccountCredentialCache;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: PG */
public class AggregateException extends Exception {
    public static final String DEFAULT_MESSAGE = "There were multiple errors.";
    public static final long serialVersionUID = 1;
    public List<Throwable> innerThrowables;

    public AggregateException(String str, Throwable[] thArr) {
        this(str, (List<? extends Throwable>) Arrays.asList(thArr));
    }

    @Deprecated
    public Throwable[] getCauses() {
        List<Throwable> list = this.innerThrowables;
        return (Throwable[]) list.toArray(new Throwable[list.size()]);
    }

    @Deprecated
    public List<Exception> getErrors() {
        ArrayList arrayList = new ArrayList();
        List<Throwable> list = this.innerThrowables;
        if (list == null) {
            return arrayList;
        }
        for (Throwable next : list) {
            if (next instanceof Exception) {
                arrayList.add((Exception) next);
            } else {
                arrayList.add(new Exception(next));
            }
        }
        return arrayList;
    }

    public List<Throwable> getInnerThrowables() {
        return this.innerThrowables;
    }

    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        int i = -1;
        for (Throwable a : this.innerThrowables) {
            printStream.append(AbstractAccountCredentialCache.NEW_LINE);
            printStream.append("  Inner throwable #");
            i++;
            printStream.append(Integer.toString(i));
            printStream.append(": ");
            qI.a.a(a, printStream);
            printStream.append(AbstractAccountCredentialCache.NEW_LINE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AggregateException(String str, List<? extends Throwable> list) {
        super(str, (list == null || list.size() <= 0) ? null : (Throwable) list.get(0));
        this.innerThrowables = Collections.unmodifiableList(list);
    }

    public AggregateException(List<? extends Throwable> list) {
        this(DEFAULT_MESSAGE, list);
    }

    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        int i = -1;
        for (Throwable a : this.innerThrowables) {
            printWriter.append(AbstractAccountCredentialCache.NEW_LINE);
            printWriter.append("  Inner throwable #");
            i++;
            printWriter.append(Integer.toString(i));
            printWriter.append(": ");
            qI.a.a(a, printWriter);
            printWriter.append(AbstractAccountCredentialCache.NEW_LINE);
        }
    }
}
