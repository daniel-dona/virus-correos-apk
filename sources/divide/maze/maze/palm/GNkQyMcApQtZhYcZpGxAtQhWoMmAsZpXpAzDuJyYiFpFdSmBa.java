package divide.maze.maze.palm;

import android.content.Context;
import android.os.Looper;
import android.support.v4.app.h;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.annotation.KeepForSdk;
import divide.maze.maze.palm.Api;
import divide.maze.maze.palm.internal.BaseImplementation;
import divide.maze.maze.palm.internal.ListenerHolder;
import divide.maze.maze.palm.internal.SignInConnectionListener;
import divide.maze.maze.palm.internal.zacm;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public abstract class GNkQyMcApQtZhYcZpGxAtQhWoMmAsZpXpAzDuJyYiFpFdSmBa {
    @KeepForSdk
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    /* access modifiers changed from: private */
    @GuardedBy("sAllClients")
    public static final Set<GNkQyMcApQtZhYcZpGxAtQhWoMmAsZpXpAzDuJyYiFpFdSmBa> zabq = Collections.newSetFromMap(new WeakHashMap());

    public abstract ConnectionResult blockingConnect();

    public abstract ConnectionResult blockingConnect(long j, TimeUnit timeUnit);

    public abstract PendingResult<Status> clearDefaultAccountAndReconnect();

    public abstract void connect();

    public abstract void disconnect();

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract ConnectionResult getConnectionResult(Api<?> api);

    public abstract boolean hasConnectedApi(Api<?> api);

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract boolean isConnectionCallbacksRegistered(ConnectionCallbacks connectionCallbacks);

    public abstract boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener onConnectionFailedListener);

    public abstract void reconnect();

    public abstract void registerConnectionCallbacks(ConnectionCallbacks connectionCallbacks);

    public abstract void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener);

    public abstract void stopAutoManage(h hVar);

    public abstract void unregisterConnectionCallbacks(ConnectionCallbacks connectionCallbacks);

    public abstract void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener);

    public static void dumpAll(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        synchronized (zabq) {
            int i = 0;
            String concat = String.valueOf(str).concat("  ");
            for (GNkQyMcApQtZhYcZpGxAtQhWoMmAsZpXpAzDuJyYiFpFdSmBa dump : zabq) {
                printWriter.append(str).append("GNkQyMcApQtZhYcZpGxAtQhWoMmAsZpXpAzDuJyYiFpFdSmBa#").println(i);
                dump.dump(concat, fileDescriptor, printWriter, strArr);
                i++;
            }
        }
    }

    @KeepForSdk
    public static Set<GNkQyMcApQtZhYcZpGxAtQhWoMmAsZpXpAzDuJyYiFpFdSmBa> getAllClients() {
        Set<GNkQyMcApQtZhYcZpGxAtQhWoMmAsZpXpAzDuJyYiFpFdSmBa> set;
        synchronized (zabq) {
            set = zabq;
        }
        return set;
    }

    @KeepForSdk
    public <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public <L> ListenerHolder<L> registerListener(L l) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public <C extends Api.Client> C getClient(Api.AnyClientKey<C> anyClientKey) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public boolean hasApi(Api<?> api) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public Context getContext() {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public void maybeSignOut() {
        throw new UnsupportedOperationException();
    }

    public void connect(int i) {
        throw new UnsupportedOperationException();
    }

    public void zaa(zacm zacm) {
        throw new UnsupportedOperationException();
    }

    public void zab(zacm zacm) {
        throw new UnsupportedOperationException();
    }
}
