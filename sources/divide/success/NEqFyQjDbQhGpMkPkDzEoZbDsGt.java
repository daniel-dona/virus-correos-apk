package divide.success;

import android.app.PendingIntent;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import divide.success.BaseImplementation;
import divide.success.BasePendingResult;

@KeepForSdk
public abstract class NEqFyQjDbQhGpMkPkDzEoZbDsGt<R extends Result, A extends Api.AnyClient> extends BasePendingResult<R> implements BaseImplementation.ResultHolder<R> {
    @KeepForSdk
    private final Api<?> mApi;
    @KeepForSdk
    private final Api.AnyClientKey<A> mClientKey;

    @KeepForSdk
    @Deprecated
    protected NEqFyQjDbQhGpMkPkDzEoZbDsGt(Api.AnyClientKey<A> anyClientKey, GoogleApiClient googleApiClient) {
        super((GoogleApiClient) Preconditions.checkNotNull(googleApiClient, "GoogleApiClient must not be null"));
        this.mClientKey = (Api.AnyClientKey) Preconditions.checkNotNull(anyClientKey);
        this.mApi = null;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract void doExecute(A a);

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void onSetFailedResult(R r) {
    }

    @KeepForSdk
    protected NEqFyQjDbQhGpMkPkDzEoZbDsGt(Api<?> api, GoogleApiClient googleApiClient) {
        super((GoogleApiClient) Preconditions.checkNotNull(googleApiClient, "GoogleApiClient must not be null"));
        Preconditions.checkNotNull(api, "Api must not be null");
        this.mClientKey = api.getClientKey();
        this.mApi = api;
    }

    @KeepForSdk
    protected NEqFyQjDbQhGpMkPkDzEoZbDsGt(BasePendingResult.CallbackHandler<R> callbackHandler) {
        super(callbackHandler);
        this.mClientKey = null;
        this.mApi = null;
    }

    @KeepForSdk
    public final Api.AnyClientKey<A> getClientKey() {
        return this.mClientKey;
    }

    @KeepForSdk
    public final Api<?> getApi() {
        return this.mApi;
    }

    @KeepForSdk
    public final void run(A a) {
        if (a instanceof SimpleClientAdapter) {
            a = ((SimpleClientAdapter) a).getClient();
        }
        try {
            doExecute(a);
        } catch (DeadObjectException e) {
            setFailedResult((RemoteException) e);
            throw e;
        } catch (RemoteException e2) {
            setFailedResult(e2);
        }
    }

    @KeepForSdk
    public final void setFailedResult(Status status) {
        Preconditions.checkArgument(!status.isSuccess(), "Failed result must not be success");
        Result createFailedResult = createFailedResult(status);
        setResult(createFailedResult);
        onSetFailedResult(createFailedResult);
    }

    @KeepForSdk
    private void setFailedResult(RemoteException remoteException) {
        setFailedResult(new Status(8, remoteException.getLocalizedMessage(), (PendingIntent) null));
    }

    @KeepForSdk
    public /* bridge */ /* synthetic */ void setResult(Object obj) {
        NEqFyQjDbQhGpMkPkDzEoZbDsGt.super.setResult((Result) obj);
    }
}
