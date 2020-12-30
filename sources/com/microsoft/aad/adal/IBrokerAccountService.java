package com.microsoft.aad.adal;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

/* compiled from: PG */
public interface IBrokerAccountService extends IInterface {

    /* compiled from: PG */
    public static abstract class Stub extends Binder implements IBrokerAccountService {
        public static final String DESCRIPTOR = "com.microsoft.aad.adal.IBrokerAccountService";
        public static final int TRANSACTION_acquireTokenSilently = 2;
        public static final int TRANSACTION_getBrokerUsers = 1;
        public static final int TRANSACTION_getIntentForInteractiveRequest = 3;
        public static final int TRANSACTION_removeAccounts = 4;

        /* compiled from: PG */
        public static class Proxy implements IBrokerAccountService {
            public IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public Bundle acquireTokenSilently(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeMap(map);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public Bundle getBrokerUsers() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent getIntentForInteractiveRequest() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public void removeAccounts() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBrokerAccountService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IBrokerAccountService)) {
                return new Proxy(iBinder);
            }
            return (IBrokerAccountService) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                Bundle brokerUsers = getBrokerUsers();
                parcel2.writeNoException();
                if (brokerUsers != null) {
                    parcel2.writeInt(1);
                    brokerUsers.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                Bundle acquireTokenSilently = acquireTokenSilently(parcel.readHashMap(Stub.class.getClassLoader()));
                parcel2.writeNoException();
                if (acquireTokenSilently != null) {
                    parcel2.writeInt(1);
                    acquireTokenSilently.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                Intent intentForInteractiveRequest = getIntentForInteractiveRequest();
                parcel2.writeNoException();
                if (intentForInteractiveRequest != null) {
                    parcel2.writeInt(1);
                    intentForInteractiveRequest.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            } else if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                removeAccounts();
                parcel2.writeNoException();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }
    }

    Bundle acquireTokenSilently(Map map) throws RemoteException;

    Bundle getBrokerUsers() throws RemoteException;

    Intent getIntentForInteractiveRequest() throws RemoteException;

    void removeAccounts() throws RemoteException;
}
