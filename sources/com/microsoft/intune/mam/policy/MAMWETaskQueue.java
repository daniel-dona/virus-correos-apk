package com.microsoft.intune.mam.policy;

import android.os.ConditionVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/* compiled from: PG */
public class MAMWETaskQueue {

    /* renamed from: a */
    public final ConditionVariable f1284a = new ConditionVariable(false);

    /* renamed from: b */
    public final PriorityQueue<Task> f1285b = new PriorityQueue<>(11, new le0((ke0) null));

    /* compiled from: PG */
    public interface Task extends Runnable {
        long dueAt();
    }

    /* renamed from: a */
    public synchronized void mo4398a(Task task) {
        this.f1285b.add(task);
        this.f1284a.open();
    }

    /* renamed from: b */
    public synchronized void mo4400b(Task task) {
        this.f1285b.remove(task);
    }

    /* renamed from: b */
    public List<Task> mo4399b(long j) {
        long a = mo4397a(j);
        if (a > 0) {
            this.f1284a.block(a);
        }
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            this.f1284a.close();
            while (mo4397a(1) <= 0) {
                arrayList.add(this.f1285b.poll());
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public final synchronized long mo4397a(long j) {
        Task peek = this.f1285b.peek();
        if (peek == null) {
            return j;
        }
        return peek.dueAt() - System.currentTimeMillis();
    }
}
