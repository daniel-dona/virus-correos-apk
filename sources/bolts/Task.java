package bolts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* compiled from: PG */
public class Task<TResult> {

    /* renamed from: i */
    public static final Executor f0i;

    /* renamed from: j */
    public static Task<?> f1j = new Task<>((Object) null);

    /* renamed from: k */
    public static Task<Boolean> f2k = new Task<>(true);

    /* renamed from: l */
    public static Task<Boolean> f3l = new Task<>(false);

    /* renamed from: a */
    public final Object f4a = new Object();

    /* renamed from: b */
    public boolean f5b;

    /* renamed from: c */
    public boolean f6c;

    /* renamed from: d */
    public TResult f7d;

    /* renamed from: e */
    public Exception f8e;

    /* renamed from: f */
    public boolean f9f;

    /* renamed from: g */
    public qh f10g;

    /* renamed from: h */
    public List<lh<TResult, Void>> f11h = new ArrayList();

    /* compiled from: PG */
    public interface UnobservedExceptionHandler {
        void unobservedException(Task<?> task, UnobservedTaskException unobservedTaskException);
    }

    static {
        jh jhVar = jh.c;
        ExecutorService executorService = jhVar.a;
        f0i = jhVar.b;
        Executor executor = gh.b.a;
        new Task(true);
    }

    public Task() {
    }

    /* renamed from: b */
    public static <TResult> Task<TResult> m2b(Exception exc) {
        Task<TResult> task = new Task<>();
        if (task.mo9a(exc)) {
            return task;
        }
        throw new IllegalStateException("Cannot set the error on a completed task.");
    }

    /* renamed from: a */
    public Exception mo8a() {
        Exception exc;
        synchronized (this.f4a) {
            if (this.f8e != null) {
                this.f9f = true;
                if (this.f10g != null) {
                    this.f10g.a = null;
                    this.f10g = null;
                }
            }
            exc = this.f8e;
        }
        return exc;
    }

    /* renamed from: c */
    public boolean mo12c() {
        boolean z;
        synchronized (this.f4a) {
            z = this.f6c;
        }
        return z;
    }

    /* renamed from: d */
    public boolean mo13d() {
        boolean z;
        synchronized (this.f4a) {
            z = this.f5b;
        }
        return z;
    }

    /* renamed from: e */
    public boolean mo14e() {
        boolean z;
        synchronized (this.f4a) {
            z = mo8a() != null;
        }
        return z;
    }

    /* renamed from: f */
    public final void mo15f() {
        synchronized (this.f4a) {
            Iterator<lh<TResult, Void>> it = this.f11h.iterator();
            while (it.hasNext()) {
                try {
                    ((lh) it.next()).then(this);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            this.f11h = null;
        }
    }

    /* renamed from: g */
    public boolean mo16g() {
        synchronized (this.f4a) {
            if (this.f5b) {
                return false;
            }
            this.f5b = true;
            this.f6c = true;
            this.f4a.notifyAll();
            mo15f();
            return true;
        }
    }

    public Task(TResult tresult) {
        mo10a(tresult);
    }

    /* renamed from: b */
    public TResult mo11b() {
        TResult tresult;
        synchronized (this.f4a) {
            tresult = this.f7d;
        }
        return tresult;
    }

    /* renamed from: b */
    public static <TResult> Task<TResult> m3b(TResult tresult) {
        if (tresult == null) {
            return f1j;
        }
        if (tresult instanceof Boolean) {
            return ((Boolean) tresult).booleanValue() ? f2k : f3l;
        }
        Task<TResult> task = new Task<>();
        if (task.mo10a(tresult)) {
            return task;
        }
        throw new IllegalStateException("Cannot set the result of a completed task.");
    }

    public Task(boolean z) {
        if (z) {
            mo16g();
        } else {
            mo10a((Object) null);
        }
    }

    /* renamed from: a */
    public static <TResult> Task<TResult> m0a(Callable<TResult> callable, Executor executor) {
        ph phVar = new ph();
        try {
            executor.execute(new oh(phVar, callable));
        } catch (Exception e) {
            phVar.a(new ExecutorException(e));
        }
        return phVar.a;
    }

    /* renamed from: a */
    public <TContinuationResult> Task<TContinuationResult> mo7a(lh<TResult, TContinuationResult> lhVar, Executor executor, kh khVar) {
        boolean d;
        ph phVar = new ph();
        synchronized (this.f4a) {
            d = mo13d();
            if (!d) {
                this.f11h.add(new mh(this, phVar, lhVar, executor));
            }
        }
        if (d) {
            try {
                executor.execute(new nh(phVar, lhVar, this));
            } catch (Exception e) {
                phVar.a(new ExecutorException(e));
            }
        }
        return phVar.a;
    }

    /* renamed from: a */
    public static <TContinuationResult, TResult> void m1a(ph phVar, lh lhVar, Task task, Executor executor) {
        try {
            executor.execute(new nh(phVar, lhVar, task));
        } catch (Exception e) {
            phVar.a(new ExecutorException(e));
        }
    }

    /* renamed from: a */
    public boolean mo10a(TResult tresult) {
        synchronized (this.f4a) {
            if (this.f5b) {
                return false;
            }
            this.f5b = true;
            this.f7d = tresult;
            this.f4a.notifyAll();
            mo15f();
            return true;
        }
    }

    /* renamed from: a */
    public boolean mo9a(Exception exc) {
        synchronized (this.f4a) {
            if (this.f5b) {
                return false;
            }
            this.f5b = true;
            this.f8e = exc;
            this.f9f = false;
            this.f4a.notifyAll();
            mo15f();
            boolean z = this.f9f;
            return true;
        }
    }
}
