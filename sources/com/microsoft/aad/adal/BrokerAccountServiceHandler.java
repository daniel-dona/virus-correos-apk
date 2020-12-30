package com.microsoft.aad.adal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.aad.adal.IBrokerAccountService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
public final class BrokerAccountServiceHandler {
    public static final String BROKER_ACCOUNT_SERVICE_INTENT_FILTER = "com.microsoft.workaccount.BrokerAccount";
    public static final String TAG = "BrokerAccountServiceHandler";
    public static ExecutorService sThreadExecutor = Executors.newCachedThreadPool();
    public ConcurrentMap<BrokerAccountServiceConnection, CallbackExecutor<BrokerAccountServiceConnection>> mPendingConnections;

    /* compiled from: PG */
    public class BrokerAccountServiceConnection implements ServiceConnection {
        public boolean mBound;
        public IBrokerAccountService mBrokerAccountService;
        public BrokerEvent mEvent;

        public BrokerAccountServiceConnection() {
        }

        public IBrokerAccountService getBrokerAccountServiceProvider() {
            return this.mBrokerAccountService;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Logger.m1250v(BrokerAccountServiceHandler.TAG, "Broker Account service is connected.");
            this.mBrokerAccountService = IBrokerAccountService.Stub.asInterface(iBinder);
            this.mBound = true;
            BrokerEvent brokerEvent = this.mEvent;
            if (brokerEvent != null) {
                brokerEvent.setBrokerAccountServiceConnected();
            }
            CallbackExecutor callbackExecutor = (CallbackExecutor) BrokerAccountServiceHandler.this.mPendingConnections.remove(this);
            if (callbackExecutor != null) {
                callbackExecutor.onSuccess(this);
            } else {
                Logger.m1250v(BrokerAccountServiceHandler.TAG, "No callback is found.");
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Logger.m1250v(BrokerAccountServiceHandler.TAG, "Broker Account service is disconnected.");
            this.mBound = false;
        }

        public void setTelemetryEvent(BrokerEvent brokerEvent) {
            this.mEvent = brokerEvent;
        }

        public void unBindService(final Context context) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (BrokerAccountServiceConnection.this.mBound) {
                        try {
                            context.unbindService(BrokerAccountServiceConnection.this);
                        } catch (IllegalArgumentException e) {
                            Logger.m1246e(BrokerAccountServiceHandler.TAG, "Unbind threw IllegalArgumentException", BuildConfig.FLAVOR, (ADALError) null, e);
                        } catch (Throwable th) {
                            boolean unused = BrokerAccountServiceConnection.this.mBound = false;
                            throw th;
                        }
                        boolean unused2 = BrokerAccountServiceConnection.this.mBound = false;
                    }
                }
            });
        }
    }

    /* compiled from: PG */
    public static final class InstanceHolder {
        public static final BrokerAccountServiceHandler INSTANCE = new BrokerAccountServiceHandler();
    }

    private void bindToBrokerAccountService(Context context, Callback<BrokerAccountServiceConnection> callback, BrokerEvent brokerEvent) {
        String a = Eo.a(new StringBuilder(), TAG, ":bindToBrokerAccountService");
        StringBuilder a2 = Eo.a("uid: ");
        a2.append(Process.myUid());
        Logger.m1251v(a, "Binding to BrokerAccountService for caller uid. ", a2.toString(), (ADALError) null);
        Intent intentForBrokerAccountService = getIntentForBrokerAccountService(context);
        BrokerAccountServiceConnection brokerAccountServiceConnection = new BrokerAccountServiceConnection();
        if (brokerEvent != null) {
            brokerAccountServiceConnection.setTelemetryEvent(brokerEvent);
            brokerEvent.setBrokerAccountServerStartsBinding();
        }
        this.mPendingConnections.put(brokerAccountServiceConnection, new CallbackExecutor(callback));
        boolean bindService = context.bindService(intentForBrokerAccountService, brokerAccountServiceConnection, 1);
        String a3 = Eo.a(new StringBuilder(), TAG, ":bindToBrokerAccountService");
        StringBuilder a4 = Eo.a("The status for brokerAccountService bindService call is: ");
        a4.append(Boolean.valueOf(bindService));
        Logger.m1250v(a3, a4.toString());
        if (brokerEvent != null) {
            brokerEvent.setBrokerAccountServiceBindingSucceed(bindService);
        }
        if (!bindService) {
            brokerAccountServiceConnection.unBindService(context);
            Logger.m1245e(Eo.a(new StringBuilder(), TAG, ":bindToBrokerAccountService"), "Failed to bind service to broker app. ", "'bindService returned false", ADALError.BROKER_BIND_SERVICE_FAILED);
            callback.onError(new AuthenticationException(ADALError.BROKER_BIND_SERVICE_FAILED));
        }
    }

    private UserInfo[] convertUserInfoBundleToArray(Bundle bundle) {
        if (bundle == null) {
            Logger.m1250v(TAG, "No user info returned from broker account service.");
            return new UserInfo[0];
        }
        ArrayList arrayList = new ArrayList();
        for (String bundle2 : bundle.keySet()) {
            Bundle bundle3 = bundle.getBundle(bundle2);
            arrayList.add(new UserInfo(bundle3.getString("account.userinfo.userid"), bundle3.getString("account.userinfo.given.name"), bundle3.getString("account.userinfo.family.name"), bundle3.getString("account.userinfo.identity.provider"), bundle3.getString("account.userinfo.userid.displayable")));
        }
        return (UserInfo[]) arrayList.toArray(new UserInfo[arrayList.size()]);
    }

    public static BrokerAccountServiceHandler getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public static Intent getIntentForBrokerAccountService(Context context) {
        String currentActiveBrokerPackageName = new BrokerProxy(context).getCurrentActiveBrokerPackageName();
        if (currentActiveBrokerPackageName == null) {
            Logger.m1250v(TAG, "No recognized broker is installed on the device.");
            return null;
        }
        Intent intent = new Intent(BROKER_ACCOUNT_SERVICE_INTENT_FILTER);
        intent.setPackage(currentActiveBrokerPackageName);
        intent.setClassName(currentActiveBrokerPackageName, "com.microsoft.aad.adal.BrokerAccountService");
        return intent;
    }

    private void performAsyncCallOnBound(final Context context, final Callback<BrokerAccountServiceConnection> callback, BrokerEvent brokerEvent) {
        bindToBrokerAccountService(context, new Callback<BrokerAccountServiceConnection>() {
            public void onError(Throwable th) {
                callback.onError(th);
            }

            public void onSuccess(final BrokerAccountServiceConnection brokerAccountServiceConnection) {
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    callback.onSuccess(brokerAccountServiceConnection);
                    brokerAccountServiceConnection.unBindService(context);
                    return;
                }
                BrokerAccountServiceHandler.sThreadExecutor.execute(new Runnable() {
                    public void run() {
                        callback.onSuccess(brokerAccountServiceConnection);
                        brokerAccountServiceConnection.unBindService(context);
                    }
                });
            }
        }, brokerEvent);
    }

    /* access modifiers changed from: private */
    public Map<String, String> prepareGetAuthTokenRequestData(Context context, Bundle bundle) {
        Set<String> keySet = bundle.keySet();
        HashMap hashMap = new HashMap();
        for (String str : keySet) {
            if (str.equals("com.microsoft.aad.adal:RequestId") || str.equals("expiration.buffer")) {
                hashMap.put(str, String.valueOf(bundle.getInt(str)));
            } else {
                hashMap.put(str, bundle.getString(str));
            }
        }
        hashMap.put("caller.info.package", context.getPackageName());
        return hashMap;
    }

    public Bundle getAuthToken(Context context, Bundle bundle, BrokerEvent brokerEvent) throws AuthenticationException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicReference atomicReference = new AtomicReference((Object) null);
        AtomicReference atomicReference2 = new AtomicReference((Object) null);
        final AtomicReference atomicReference3 = atomicReference;
        final Context context2 = context;
        final Bundle bundle2 = bundle;
        final AtomicReference atomicReference4 = atomicReference2;
        final CountDownLatch countDownLatch2 = countDownLatch;
        performAsyncCallOnBound(context, new Callback<BrokerAccountServiceConnection>() {
            public void onError(Throwable th) {
                atomicReference4.set(th);
                countDownLatch2.countDown();
            }

            public void onSuccess(BrokerAccountServiceConnection brokerAccountServiceConnection) {
                try {
                    atomicReference3.set(brokerAccountServiceConnection.getBrokerAccountServiceProvider().acquireTokenSilently(BrokerAccountServiceHandler.this.prepareGetAuthTokenRequestData(context2, bundle2)));
                } catch (RemoteException e) {
                    atomicReference4.set(e);
                }
                countDownLatch2.countDown();
            }
        }, brokerEvent);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            atomicReference2.set(e);
        }
        Throwable th = (Throwable) atomicReference2.getAndSet((Object) null);
        if (th == null) {
            return (Bundle) atomicReference.getAndSet((Object) null);
        }
        if (th instanceof RemoteException) {
            Logger.m1246e(Eo.a(new StringBuilder(), TAG, ":getAuthToken"), "Get error when trying to get token from broker. ", th.getMessage(), ADALError.BROKER_AUTHENTICATOR_NOT_RESPONDING, th);
            throw new AuthenticationException(ADALError.BROKER_AUTHENTICATOR_NOT_RESPONDING, th.getMessage(), th);
        } else if (th instanceof InterruptedException) {
            Logger.m1246e(Eo.a(new StringBuilder(), TAG, ":getAuthToken"), "The broker account service binding call is interrupted. ", th.getMessage(), ADALError.BROKER_AUTHENTICATOR_EXCEPTION, th);
            throw new AuthenticationException(ADALError.BROKER_AUTHENTICATOR_NOT_RESPONDING, th.getMessage(), th);
        } else {
            Logger.m1246e(Eo.a(new StringBuilder(), TAG, ":getAuthToken"), "Get error when trying to bind the broker account service.", th.getMessage(), ADALError.BROKER_AUTHENTICATOR_NOT_RESPONDING, th);
            throw new AuthenticationException(ADALError.BROKER_AUTHENTICATOR_NOT_RESPONDING, th.getMessage(), th);
        }
    }

    public UserInfo[] getBrokerUsers(Context context) throws IOException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference atomicReference = new AtomicReference((Object) null);
        final AtomicReference atomicReference2 = new AtomicReference((Object) null);
        performAsyncCallOnBound(context, new Callback<BrokerAccountServiceConnection>() {
            public void onError(Throwable th) {
                atomicReference2.set(th);
                countDownLatch.countDown();
            }

            public void onSuccess(BrokerAccountServiceConnection brokerAccountServiceConnection) {
                try {
                    atomicReference.set(brokerAccountServiceConnection.getBrokerAccountServiceProvider().getBrokerUsers());
                } catch (RemoteException e) {
                    atomicReference2.set(e);
                }
                countDownLatch.countDown();
            }
        }, (BrokerEvent) null);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            atomicReference2.set(e);
        }
        Throwable th = (Throwable) atomicReference2.getAndSet((Object) null);
        if (th == null) {
            return convertUserInfoBundleToArray((Bundle) atomicReference.getAndSet((Object) null));
        }
        throw new IOException(th.getMessage(), th);
    }

    public Intent getIntentForInteractiveRequest(Context context, BrokerEvent brokerEvent) throws AuthenticationException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference atomicReference = new AtomicReference((Object) null);
        final AtomicReference atomicReference2 = new AtomicReference((Object) null);
        performAsyncCallOnBound(context, new Callback<BrokerAccountServiceConnection>() {
            public void onError(Throwable th) {
                atomicReference2.set(th);
                countDownLatch.countDown();
            }

            public void onSuccess(BrokerAccountServiceConnection brokerAccountServiceConnection) {
                try {
                    atomicReference.set(brokerAccountServiceConnection.getBrokerAccountServiceProvider().getIntentForInteractiveRequest());
                } catch (RemoteException e) {
                    atomicReference2.set(e);
                }
                countDownLatch.countDown();
            }
        }, brokerEvent);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            atomicReference2.set(e);
        }
        Throwable th = (Throwable) atomicReference2.getAndSet((Object) null);
        if (th == null) {
            return (Intent) atomicReference.getAndSet((Object) null);
        }
        if (th instanceof RemoteException) {
            Logger.m1246e(TAG, "Get error when trying to get token from broker. ", th.getMessage(), ADALError.BROKER_AUTHENTICATOR_NOT_RESPONDING, th);
            throw new AuthenticationException(ADALError.BROKER_AUTHENTICATOR_NOT_RESPONDING, th.getMessage(), th);
        } else if (th instanceof InterruptedException) {
            Logger.m1246e(TAG, "The broker account service binding call is interrupted. ", th.getMessage(), ADALError.BROKER_AUTHENTICATOR_EXCEPTION, th);
            throw new AuthenticationException(ADALError.BROKER_AUTHENTICATOR_NOT_RESPONDING, th.getMessage(), th);
        } else {
            Logger.m1246e(TAG, "Didn't receive the activity to launch from broker. ", th.getMessage(), ADALError.BROKER_AUTHENTICATOR_NOT_RESPONDING, th);
            ADALError aDALError = ADALError.BROKER_AUTHENTICATOR_NOT_RESPONDING;
            StringBuilder a = Eo.a("Didn't receive the activity to launch from broker: ");
            a.append(th.getMessage());
            throw new AuthenticationException(aDALError, a.toString(), th);
        }
    }

    public void removeAccounts(Context context) {
        performAsyncCallOnBound(context, new Callback<BrokerAccountServiceConnection>() {
            public void onError(Throwable th) {
                Logger.m1246e(BrokerAccountServiceHandler.TAG + ":removeAccounts", "Encounter exception when removing accounts from broker", th.getMessage(), (ADALError) null, th);
            }

            public void onSuccess(BrokerAccountServiceConnection brokerAccountServiceConnection) {
                try {
                    brokerAccountServiceConnection.getBrokerAccountServiceProvider().removeAccounts();
                } catch (RemoteException e) {
                    Logger.m1246e(BrokerAccountServiceHandler.TAG + ":removeAccounts", "Encounter exception when removing accounts from broker", e.getMessage(), (ADALError) null, e);
                }
            }
        }, (BrokerEvent) null);
    }

    public BrokerAccountServiceHandler() {
        this.mPendingConnections = new ConcurrentHashMap();
    }
}
