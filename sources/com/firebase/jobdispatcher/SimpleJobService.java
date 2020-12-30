package com.firebase.jobdispatcher;

import android.os.AsyncTask;

/* compiled from: PG */
public abstract class SimpleJobService extends JobService {

    /* renamed from: e */
    public final T8<DF, C0200b> f884e = new T8<>();

    /* renamed from: com.firebase.jobdispatcher.SimpleJobService$b */
    /* compiled from: PG */
    public static class C0200b extends AsyncTask<Void, Void, Integer> {

        /* renamed from: a */
        public final SimpleJobService f885a;

        /* renamed from: b */
        public final DF f886b;

        public /* synthetic */ C0200b(SimpleJobService simpleJobService, DF df, C0199a aVar) {
            this.f885a = simpleJobService;
            this.f886b = df;
        }

        public Object doInBackground(Object[] objArr) {
            Void[] voidArr = (Void[]) objArr;
            return Integer.valueOf(this.f885a.mo1989c(this.f886b));
        }

        public void onPostExecute(Object obj) {
            SimpleJobService simpleJobService = this.f885a;
            DF df = this.f886b;
            boolean z = true;
            if (((Integer) obj).intValue() != 1) {
                z = false;
            }
            simpleJobService.mo1990c(df, z);
        }
    }

    /* renamed from: b */
    public boolean mo1971b(DF df) {
        synchronized (this.f884e) {
            C0200b bVar = (C0200b) this.f884e.remove(df);
            if (bVar == null) {
                return false;
            }
            bVar.cancel(true);
            return true;
        }
    }

    /* renamed from: c */
    public abstract int mo1989c(DF df);

    /* renamed from: c */
    public final void mo1990c(DF df, boolean z) {
        synchronized (this.f884e) {
            this.f884e.remove(df);
        }
        mo1970b(df, z);
    }

    /* renamed from: a */
    public boolean mo1969a(DF df) {
        C0200b bVar = new C0200b(this, df, (C0199a) null);
        synchronized (this.f884e) {
            this.f884e.put(df, bVar);
        }
        bVar.execute(new Void[0]);
        return true;
    }
}
