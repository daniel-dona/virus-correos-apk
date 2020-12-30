package divide.maze.bus;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import divide.maze.bus.Api;
import divide.maze.bus.Api.ApiOptions;
import divide.maze.bus.internal.ApiExceptionMapper;
import divide.maze.bus.internal.BaseImplementation;
import divide.maze.bus.internal.ListenerHolder;
import divide.maze.bus.internal.ListenerHolders;
import divide.maze.bus.internal.QFiPaYoQdSlNzGdHpUbWbSsYiLqFoLgPlGiWzAbManager;
import divide.maze.bus.internal.RegisterListenerMethod;
import divide.maze.bus.internal.RegistrationMethods;
import divide.maze.bus.internal.StatusExceptionMapper;
import divide.maze.bus.internal.TaskApiCall;
import divide.maze.bus.internal.UnregisterListenerMethod;
import divide.maze.bus.internal.zaae;
import divide.maze.bus.internal.zabp;
import divide.maze.bus.internal.zace;
import divide.maze.bus.internal.zai;
import java.util.Collections;
import java.util.Set;

@KeepForSdk
public class QFiPaYoQdSlNzGdHpUbWbSsYiLqFoLgPlGiWzAb<O extends Api.ApiOptions> {
    private final Api<O> mApi;
    private final Context mContext;
    private final int mId;
    private final O zabh;
    private final zai<O> zabi;
    private final Looper zabj;
    private final QFiPaYoQdSlNzGdHpUbWbSsYiLqFoLgPlGiWzAbClient zabk;
    private final StatusExceptionMapper zabl;
    protected final QFiPaYoQdSlNzGdHpUbWbSsYiLqFoLgPlGiWzAbManager zabm;

    @KeepForSdk
    protected QFiPaYoQdSlNzGdHpUbWbSsYiLqFoLgPlGiWzAb(Context context, Api<O> api, Looper looper) {
        Preconditions.checkNotNull(context, "Null context is not permitted.");
        Preconditions.checkNotNull(api, "Api must not be null.");
        Preconditions.checkNotNull(looper, "Looper must not be null.");
        this.mContext = context.getApplicationContext();
        this.mApi = api;
        this.zabh = null;
        this.zabj = looper;
        this.zabi = zai.zaa(api);
        this.zabk = new zabp(this);
        this.zabm = QFiPaYoQdSlNzGdHpUbWbSsYiLqFoLgPlGiWzAbManager.zab(this.mContext);
        this.mId = this.zabm.zabd();
        this.zabl = new ApiExceptionMapper();
    }

    @KeepForSdk
    @Deprecated
    public QFiPaYoQdSlNzGdHpUbWbSsYiLqFoLgPlGiWzAb(Context context, Api<O> api, O o, Looper looper, StatusExceptionMapper statusExceptionMapper) {
        this(context, api, o, new Settings.Builder().setLooper(looper).setMapper(statusExceptionMapper).build());
    }

    @KeepForSdk
    public QFiPaYoQdSlNzGdHpUbWbSsYiLqFoLgPlGiWzAb(Activity activity, Api<O> api, O o, Settings settings) {
        Preconditions.checkNotNull(activity, "Null activity is not permitted.");
        Preconditions.checkNotNull(api, "Api must not be null.");
        Preconditions.checkNotNull(settings, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.mContext = activity.getApplicationContext();
        this.mApi = api;
        this.zabh = o;
        this.zabj = settings.zabo;
        this.zabi = zai.zaa(this.mApi, this.zabh);
        this.zabk = new zabp(this);
        this.zabm = QFiPaYoQdSlNzGdHpUbWbSsYiLqFoLgPlGiWzAbManager.zab(this.mContext);
        this.mId = this.zabm.zabd();
        this.zabl = settings.zabn;
        if (!(activity instanceof QFiPaYoQdSlNzGdHpUbWbSsYiLqFoLgPlGiWzAbActivity)) {
            zaae.zaa(activity, this.zabm, this.zabi);
        }
        this.zabm.zaa(this);
    }

    @KeepForSdk
    public QFiPaYoQdSlNzGdHpUbWbSsYiLqFoLgPlGiWzAb(Context context, Api<O> api, O o, Settings settings) {
        Preconditions.checkNotNull(context, "Null context is not permitted.");
        Preconditions.checkNotNull(api, "Api must not be null.");
        Preconditions.checkNotNull(settings, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.mContext = context.getApplicationContext();
        this.mApi = api;
        this.zabh = o;
        this.zabj = settings.zabo;
        this.zabi = zai.zaa(this.mApi, this.zabh);
        this.zabk = new zabp(this);
        this.zabm = QFiPaYoQdSlNzGdHpUbWbSsYiLqFoLgPlGiWzAbManager.zab(this.mContext);
        this.mId = this.zabm.zabd();
        this.zabl = settings.zabn;
        this.zabm.zaa(this);
    }

    @KeepForSdk
    @Deprecated
    public QFiPaYoQdSlNzGdHpUbWbSsYiLqFoLgPlGiWzAb(Activity activity, Api<O> api, O o, StatusExceptionMapper statusExceptionMapper) {
        this(activity, api, o, new Settings.Builder().setMapper(statusExceptionMapper).setLooper(activity.getMainLooper()).build());
    }

    @KeepForSdk
    @Deprecated
    public QFiPaYoQdSlNzGdHpUbWbSsYiLqFoLgPlGiWzAb(Context context, Api<O> api, O o, StatusExceptionMapper statusExceptionMapper) {
        this(context, api, o, new Settings.Builder().setMapper(statusExceptionMapper).build());
    }

    private final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T zaa(int i, T t) {
        t.zau();
        this.zabm.zaa(this, i, t);
        return t;
    }

    private final <TResult, A extends Api.AnyClient> Task<TResult> zaa(int i, TaskApiCall<A, TResult> taskApiCall) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zabm.zaa(this, i, taskApiCall, taskCompletionSource, this.zabl);
        return taskCompletionSource.getTask();
    }

    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doRead(T t) {
        return zaa(0, t);
    }

    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doRead(TaskApiCall<A, TResult> taskApiCall) {
        return zaa(0, taskApiCall);
    }

    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doWrite(T t) {
        return zaa(1, t);
    }

    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doWrite(TaskApiCall<A, TResult> taskApiCall) {
        return zaa(1, taskApiCall);
    }

    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doBestEffortWrite(T t) {
        return zaa(2, t);
    }

    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doBestEffortWrite(TaskApiCall<A, TResult> taskApiCall) {
        return zaa(2, taskApiCall);
    }

    @KeepForSdk
    @Deprecated
    public <A extends Api.AnyClient, T extends RegisterListenerMethod<A, ?>, U extends UnregisterListenerMethod<A, ?>> Task<Void> doRegisterEventListener(T t, U u) {
        Preconditions.checkNotNull(t);
        Preconditions.checkNotNull(u);
        Preconditions.checkNotNull(t.getListenerKey(), "Listener has already been released.");
        Preconditions.checkNotNull(u.getListenerKey(), "Listener has already been released.");
        Preconditions.checkArgument(t.getListenerKey().equals(u.getListenerKey()), "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
        return this.zabm.zaa(this, t, u);
    }

    @KeepForSdk
    public <A extends Api.AnyClient> Task<Void> doRegisterEventListener(RegistrationMethods<A, ?> registrationMethods) {
        Preconditions.checkNotNull(registrationMethods);
        Preconditions.checkNotNull(registrationMethods.zajy.getListenerKey(), "Listener has already been released.");
        Preconditions.checkNotNull(registrationMethods.zajz.getListenerKey(), "Listener has already been released.");
        return this.zabm.zaa(this, registrationMethods.zajy, registrationMethods.zajz);
    }

    @KeepForSdk
    public Task<Boolean> doUnregisterEventListener(ListenerHolder.ListenerKey<?> listenerKey) {
        Preconditions.checkNotNull(listenerKey, "Listener key cannot be null.");
        return this.zabm.zaa(this, listenerKey);
    }

    @KeepForSdk
    public <L> ListenerHolder<L> registerListener(L l, String str) {
        return ListenerHolders.createListenerHolder(l, this.zabj, str);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public Task<Boolean> disconnectService() {
        return this.zabm.zac(this);
    }

    public Api.Client zaa(Looper looper, QFiPaYoQdSlNzGdHpUbWbSsYiLqFoLgPlGiWzAbManager.zaa<O> zaa) {
        return this.mApi.zai().buildClient(this.mContext, looper, createClientSettingsBuilder().build(), this.zabh, zaa, zaa);
    }

    public final Api<O> getApi() {
        return this.mApi;
    }

    @KeepForSdk
    public O getApiOptions() {
        return this.zabh;
    }

    public final zai<O> zak() {
        return this.zabi;
    }

    public final int getInstanceId() {
        return this.mId;
    }

    @KeepForSdk
    public QFiPaYoQdSlNzGdHpUbWbSsYiLqFoLgPlGiWzAbClient asQFiPaYoQdSlNzGdHpUbWbSsYiLqFoLgPlGiWzAbClient() {
        return this.zabk;
    }

    @KeepForSdk
    public Looper getLooper() {
        return this.zabj;
    }

    @KeepForSdk
    public Context getApplicationContext() {
        return this.mContext;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public ClientSettings.Builder createClientSettingsBuilder() {
        Account account;
        Set set;
        GoogleSignInAccount googleSignInAccount;
        GoogleSignInAccount googleSignInAccount2;
        ClientSettings.Builder builder = new ClientSettings.Builder();
        if (!(this.zabh instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) || (googleSignInAccount2 = ((Api.ApiOptions.HasGoogleSignInAccountOptions) this.zabh).getGoogleSignInAccount()) == null) {
            account = this.zabh instanceof Api.ApiOptions.HasAccountOptions ? ((Api.ApiOptions.HasAccountOptions) this.zabh).getAccount() : null;
        } else {
            account = googleSignInAccount2.getAccount();
        }
        ClientSettings.Builder account2 = builder.setAccount(account);
        if (!(this.zabh instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) || (googleSignInAccount = ((Api.ApiOptions.HasGoogleSignInAccountOptions) this.zabh).getGoogleSignInAccount()) == null) {
            set = Collections.emptySet();
        } else {
            set = googleSignInAccount.getRequestedScopes();
        }
        return account2.addAllRequiredScopes(set).setRealClientClassName(this.mContext.getClass().getName()).setRealClientPackageName(this.mContext.getPackageName());
    }

    public zace zaa(Context context, Handler handler) {
        return new zace(context, handler, createClientSettingsBuilder().build());
    }
}
