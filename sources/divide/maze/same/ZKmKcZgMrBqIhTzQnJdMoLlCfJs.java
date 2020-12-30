package divide.maze.same;

import android.accounts.Account;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.h;
import android.support.v4.f.a;
import android.view.View;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zaa;
import com.google.android.gms.signin.zad;
import divide.maze.same.Api;
import divide.maze.same.GoogleApiClient;
import divide.maze.same.internal.LifecycleActivity;
import divide.maze.same.internal.zaaw;
import divide.maze.same.internal.zaj;
import divide.maze.same.internal.zaq;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

@KeepForSdk
public final class ZKmKcZgMrBqIhTzQnJdMoLlCfJs {
    private final Context mContext;
    private Looper zabj;
    private final Set<Scope> zabr;
    private final Set<Scope> zabs;
    private int zabt;
    private View zabu;
    private String zabv;
    private String zabw;
    private final Map<Api<?>, ClientSettings.OptionalApiSettings> zabx;
    private final Map<Api<?>, Api.ApiOptions> zaby;
    private LifecycleActivity zabz;
    private int zaca;
    private GoogleApiClient.OnConnectionFailedListener zacb;
    private GoogleApiAvailability zacc;
    private Api.AbstractClientBuilder<? extends zad, SignInOptions> zacd;
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> zace;
    private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zacf;
    private boolean zacg;
    private Account zax;

    @KeepForSdk
    public ZKmKcZgMrBqIhTzQnJdMoLlCfJs(Context context) {
        this.zabr = new HashSet();
        this.zabs = new HashSet();
        this.zabx = new a();
        this.zaby = new a();
        this.zaca = -1;
        this.zacc = GoogleApiAvailability.getInstance();
        this.zacd = zaa.zapg;
        this.zace = new ArrayList<>();
        this.zacf = new ArrayList<>();
        this.zacg = false;
        this.mContext = context;
        this.zabj = context.getMainLooper();
        this.zabv = context.getPackageName();
        this.zabw = context.getClass().getName();
    }

    @KeepForSdk
    public ZKmKcZgMrBqIhTzQnJdMoLlCfJs(Context context, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context);
        Preconditions.checkNotNull(connectionCallbacks, "Must provide a connected listener");
        this.zace.add(connectionCallbacks);
        Preconditions.checkNotNull(onConnectionFailedListener, "Must provide a connection failed listener");
        this.zacf.add(onConnectionFailedListener);
    }

    public final ZKmKcZgMrBqIhTzQnJdMoLlCfJs setHandler(Handler handler) {
        Preconditions.checkNotNull(handler, "Handler must not be null");
        this.zabj = handler.getLooper();
        return this;
    }

    public final ZKmKcZgMrBqIhTzQnJdMoLlCfJs addConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        Preconditions.checkNotNull(connectionCallbacks, "Listener must not be null");
        this.zace.add(connectionCallbacks);
        return this;
    }

    public final ZKmKcZgMrBqIhTzQnJdMoLlCfJs addOnConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(onConnectionFailedListener, "Listener must not be null");
        this.zacf.add(onConnectionFailedListener);
        return this;
    }

    public final ZKmKcZgMrBqIhTzQnJdMoLlCfJs setViewForPopups(View view) {
        Preconditions.checkNotNull(view, "View must not be null");
        this.zabu = view;
        return this;
    }

    public final ZKmKcZgMrBqIhTzQnJdMoLlCfJs addScope(Scope scope) {
        Preconditions.checkNotNull(scope, "Scope must not be null");
        this.zabr.add(scope);
        return this;
    }

    @KeepForSdk
    public final ZKmKcZgMrBqIhTzQnJdMoLlCfJs addScopeNames(String[] strArr) {
        for (String scope : strArr) {
            this.zabr.add(new Scope(scope));
        }
        return this;
    }

    public final ZKmKcZgMrBqIhTzQnJdMoLlCfJs addApi(Api<? extends Api.ApiOptions.NotRequiredOptions> api) {
        Preconditions.checkNotNull(api, "Api must not be null");
        this.zaby.put(api, (Object) null);
        List impliedScopes = api.zah().getImpliedScopes((Object) null);
        this.zabs.addAll(impliedScopes);
        this.zabr.addAll(impliedScopes);
        return this;
    }

    public final ZKmKcZgMrBqIhTzQnJdMoLlCfJs addApiIfAvailable(Api<? extends Api.ApiOptions.NotRequiredOptions> api, Scope... scopeArr) {
        Preconditions.checkNotNull(api, "Api must not be null");
        this.zaby.put(api, (Object) null);
        zaa(api, (Api.ApiOptions) null, scopeArr);
        return this;
    }

    public final <O extends Api.ApiOptions.HasOptions> ZKmKcZgMrBqIhTzQnJdMoLlCfJs addApi(Api<O> api, O o) {
        Preconditions.checkNotNull(api, "Api must not be null");
        Preconditions.checkNotNull(o, "Null options are not permitted for this Api");
        this.zaby.put(api, o);
        List impliedScopes = api.zah().getImpliedScopes(o);
        this.zabs.addAll(impliedScopes);
        this.zabr.addAll(impliedScopes);
        return this;
    }

    public final <O extends Api.ApiOptions.HasOptions> ZKmKcZgMrBqIhTzQnJdMoLlCfJs addApiIfAvailable(Api<O> api, O o, Scope... scopeArr) {
        Preconditions.checkNotNull(api, "Api must not be null");
        Preconditions.checkNotNull(o, "Null options are not permitted for this Api");
        this.zaby.put(api, o);
        zaa(api, o, scopeArr);
        return this;
    }

    public final ZKmKcZgMrBqIhTzQnJdMoLlCfJs setAccountName(String str) {
        this.zax = str == null ? null : new Account(str, "com.google");
        return this;
    }

    public final ZKmKcZgMrBqIhTzQnJdMoLlCfJs useDefaultAccount() {
        return setAccountName("<<default account>>");
    }

    public final ZKmKcZgMrBqIhTzQnJdMoLlCfJs setGravityForPopups(int i) {
        this.zabt = i;
        return this;
    }

    public final ZKmKcZgMrBqIhTzQnJdMoLlCfJs enableAutoManage(h hVar, int i, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        LifecycleActivity lifecycleActivity = new LifecycleActivity(hVar);
        Preconditions.checkArgument(i >= 0, "clientId must be non-negative");
        this.zaca = i;
        this.zacb = onConnectionFailedListener;
        this.zabz = lifecycleActivity;
        return this;
    }

    public final ZKmKcZgMrBqIhTzQnJdMoLlCfJs enableAutoManage(h hVar, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return enableAutoManage(hVar, 0, onConnectionFailedListener);
    }

    @KeepForSdk
    @VisibleForTesting
    public final ClientSettings buildClientSettings() {
        SignInOptions signInOptions = SignInOptions.DEFAULT;
        if (this.zaby.containsKey(zaa.API)) {
            signInOptions = (SignInOptions) this.zaby.get(zaa.API);
        }
        return new ClientSettings(this.zax, this.zabr, this.zabx, this.zabt, this.zabu, this.zabv, this.zabw, signInOptions);
    }

    public final GoogleApiClient build() {
        boolean z;
        Preconditions.checkArgument(!this.zaby.isEmpty(), "must call addApi() to add at least one API");
        ClientSettings buildClientSettings = buildClientSettings();
        Api api = null;
        Map optionalApiSettings = buildClientSettings.getOptionalApiSettings();
        Map aVar = new a();
        a aVar2 = new a();
        ArrayList arrayList = new ArrayList();
        Iterator<Api<?>> it = this.zaby.keySet().iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            Api api2 = (Api) it.next();
            Api.ApiOptions apiOptions = this.zaby.get(api2);
            boolean z3 = optionalApiSettings.get(api2) != null;
            aVar.put(api2, Boolean.valueOf(z3));
            zaq zaq = new zaq(api2, z3);
            arrayList.add(zaq);
            Api.AbstractClientBuilder zai = api2.zai();
            Api.AbstractClientBuilder abstractClientBuilder = zai;
            zaq zaq2 = zaq;
            Map map = optionalApiSettings;
            Api api3 = api2;
            Iterator<Api<?>> it2 = it;
            Api.Client buildClient = zai.buildClient(this.mContext, this.zabj, buildClientSettings, apiOptions, zaq2, zaq2);
            aVar2.put(api3.getClientKey(), buildClient);
            if (abstractClientBuilder.getPriority() == 1) {
                z2 = apiOptions != null;
            }
            if (buildClient.providesSignIn()) {
                if (api != null) {
                    String name = api3.getName();
                    String name2 = api.getName();
                    StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 21 + String.valueOf(name2).length());
                    sb.append(name);
                    sb.append(" cannot be used with ");
                    sb.append(name2);
                    throw new IllegalStateException(sb.toString());
                }
                api = api3;
            }
            optionalApiSettings = map;
            it = it2;
        }
        if (api == null) {
            z = true;
        } else if (z2) {
            String name3 = api.getName();
            StringBuilder sb2 = new StringBuilder(String.valueOf(name3).length() + 82);
            sb2.append("With using ");
            sb2.append(name3);
            sb2.append(", GamesOptions can only be specified within GoogleSignInOptions.Builder");
            throw new IllegalStateException(sb2.toString());
        } else {
            z = true;
            Preconditions.checkState(this.zax == null, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", new Object[]{api.getName()});
            Preconditions.checkState(this.zabr.equals(this.zabs), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", new Object[]{api.getName()});
        }
        ClientSettings clientSettings = buildClientSettings;
        zaaw zaaw = new zaaw(this.mContext, new ReentrantLock(), this.zabj, clientSettings, this.zacc, this.zacd, aVar, this.zace, this.zacf, aVar2, this.zaca, zaaw.zaa(aVar2.values(), z), arrayList, false);
        synchronized (GoogleApiClient.zal()) {
            try {
                GoogleApiClient.zal().add(zaaw);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (this.zaca >= 0) {
            zaj.zaa(this.zabz).zaa(this.zaca, zaaw, this.zacb);
        }
        return zaaw;
    }

    private final <O extends Api.ApiOptions> void zaa(Api<O> api, O o, Scope... scopeArr) {
        HashSet hashSet = new HashSet(api.zah().getImpliedScopes(o));
        for (Scope add : scopeArr) {
            hashSet.add(add);
        }
        this.zabx.put(api, new ClientSettings.OptionalApiSettings(hashSet));
    }
}
