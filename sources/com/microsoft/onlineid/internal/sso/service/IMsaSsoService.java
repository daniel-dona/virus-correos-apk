package com.microsoft.onlineid.internal.sso.service;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: PG */
public interface IMsaSsoService extends IInterface {

    /* compiled from: PG */
    public static abstract class Stub extends Binder implements IMsaSsoService {
        public static final String DESCRIPTOR = "com.microsoft.onlineid.internal.sso.service.IMsaSsoService";
        public static final int TRANSACTION_getAccount = 1;
        public static final int TRANSACTION_getAccountById = 2;
        public static final int TRANSACTION_getAccountPickerIntent = 4;
        public static final int TRANSACTION_getAllAccounts = 3;
        public static final int TRANSACTION_getProofOfPossessionTokens = 12;
        public static final int TRANSACTION_getSignInIntent = 5;
        public static final int TRANSACTION_getSignOutIntent = 7;
        public static final int TRANSACTION_getSignUpIntent = 6;
        public static final int TRANSACTION_getTicket = 8;
        public static final int TRANSACTION_getUserKey = 11;
        public static final int TRANSACTION_retrieveBackup = 10;
        public static final int TRANSACTION_storeBackup = 9;

        /* compiled from: PG */
        public static class Proxy implements IMsaSsoService {
            public IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public Bundle getAccount(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getAccountById(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getAccountPickerIntent(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getAllAccounts(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public Bundle getProofOfPossessionTokens(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getSignInIntent(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getSignOutIntent(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getSignUpIntent(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getTicket(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getUserKey(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle retrieveBackup(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle storeBackup(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMsaSsoService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMsaSsoService)) {
                return new Proxy(iBinder);
            }
            return (IMsaSsoService) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v16, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v20, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v24, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v17, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v28, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v20, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v32, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v23, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v36, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v26, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v40, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v29, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v44, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v32, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v48, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v35, resolved type: android.os.Bundle} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r5, android.os.Parcel r6, android.os.Parcel r7, int r8) throws android.os.RemoteException {
            /*
                r4 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                java.lang.String r1 = "com.microsoft.onlineid.internal.sso.service.IMsaSsoService"
                r2 = 1
                if (r5 == r0) goto L_0x01da
                r0 = 0
                r3 = 0
                switch(r5) {
                    case 1: goto L_0x01b4;
                    case 2: goto L_0x018e;
                    case 3: goto L_0x0168;
                    case 4: goto L_0x0142;
                    case 5: goto L_0x011c;
                    case 6: goto L_0x00f6;
                    case 7: goto L_0x00d0;
                    case 8: goto L_0x00aa;
                    case 9: goto L_0x0084;
                    case 10: goto L_0x005e;
                    case 11: goto L_0x0038;
                    case 12: goto L_0x0012;
                    default: goto L_0x000d;
                }
            L_0x000d:
                boolean r5 = super.onTransact(r5, r6, r7, r8)
                return r5
            L_0x0012:
                r6.enforceInterface(r1)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x0024
                android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x0024:
                android.os.Bundle r5 = r4.getProofOfPossessionTokens(r3)
                r7.writeNoException()
                if (r5 == 0) goto L_0x0034
                r7.writeInt(r2)
                r5.writeToParcel(r7, r2)
                goto L_0x0037
            L_0x0034:
                r7.writeInt(r0)
            L_0x0037:
                return r2
            L_0x0038:
                r6.enforceInterface(r1)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x004a
                android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x004a:
                android.os.Bundle r5 = r4.getUserKey(r3)
                r7.writeNoException()
                if (r5 == 0) goto L_0x005a
                r7.writeInt(r2)
                r5.writeToParcel(r7, r2)
                goto L_0x005d
            L_0x005a:
                r7.writeInt(r0)
            L_0x005d:
                return r2
            L_0x005e:
                r6.enforceInterface(r1)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x0070
                android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x0070:
                android.os.Bundle r5 = r4.retrieveBackup(r3)
                r7.writeNoException()
                if (r5 == 0) goto L_0x0080
                r7.writeInt(r2)
                r5.writeToParcel(r7, r2)
                goto L_0x0083
            L_0x0080:
                r7.writeInt(r0)
            L_0x0083:
                return r2
            L_0x0084:
                r6.enforceInterface(r1)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x0096
                android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x0096:
                android.os.Bundle r5 = r4.storeBackup(r3)
                r7.writeNoException()
                if (r5 == 0) goto L_0x00a6
                r7.writeInt(r2)
                r5.writeToParcel(r7, r2)
                goto L_0x00a9
            L_0x00a6:
                r7.writeInt(r0)
            L_0x00a9:
                return r2
            L_0x00aa:
                r6.enforceInterface(r1)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x00bc
                android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x00bc:
                android.os.Bundle r5 = r4.getTicket(r3)
                r7.writeNoException()
                if (r5 == 0) goto L_0x00cc
                r7.writeInt(r2)
                r5.writeToParcel(r7, r2)
                goto L_0x00cf
            L_0x00cc:
                r7.writeInt(r0)
            L_0x00cf:
                return r2
            L_0x00d0:
                r6.enforceInterface(r1)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x00e2
                android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x00e2:
                android.os.Bundle r5 = r4.getSignOutIntent(r3)
                r7.writeNoException()
                if (r5 == 0) goto L_0x00f2
                r7.writeInt(r2)
                r5.writeToParcel(r7, r2)
                goto L_0x00f5
            L_0x00f2:
                r7.writeInt(r0)
            L_0x00f5:
                return r2
            L_0x00f6:
                r6.enforceInterface(r1)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x0108
                android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x0108:
                android.os.Bundle r5 = r4.getSignUpIntent(r3)
                r7.writeNoException()
                if (r5 == 0) goto L_0x0118
                r7.writeInt(r2)
                r5.writeToParcel(r7, r2)
                goto L_0x011b
            L_0x0118:
                r7.writeInt(r0)
            L_0x011b:
                return r2
            L_0x011c:
                r6.enforceInterface(r1)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x012e
                android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x012e:
                android.os.Bundle r5 = r4.getSignInIntent(r3)
                r7.writeNoException()
                if (r5 == 0) goto L_0x013e
                r7.writeInt(r2)
                r5.writeToParcel(r7, r2)
                goto L_0x0141
            L_0x013e:
                r7.writeInt(r0)
            L_0x0141:
                return r2
            L_0x0142:
                r6.enforceInterface(r1)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x0154
                android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x0154:
                android.os.Bundle r5 = r4.getAccountPickerIntent(r3)
                r7.writeNoException()
                if (r5 == 0) goto L_0x0164
                r7.writeInt(r2)
                r5.writeToParcel(r7, r2)
                goto L_0x0167
            L_0x0164:
                r7.writeInt(r0)
            L_0x0167:
                return r2
            L_0x0168:
                r6.enforceInterface(r1)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x017a
                android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x017a:
                android.os.Bundle r5 = r4.getAllAccounts(r3)
                r7.writeNoException()
                if (r5 == 0) goto L_0x018a
                r7.writeInt(r2)
                r5.writeToParcel(r7, r2)
                goto L_0x018d
            L_0x018a:
                r7.writeInt(r0)
            L_0x018d:
                return r2
            L_0x018e:
                r6.enforceInterface(r1)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x01a0
                android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x01a0:
                android.os.Bundle r5 = r4.getAccountById(r3)
                r7.writeNoException()
                if (r5 == 0) goto L_0x01b0
                r7.writeInt(r2)
                r5.writeToParcel(r7, r2)
                goto L_0x01b3
            L_0x01b0:
                r7.writeInt(r0)
            L_0x01b3:
                return r2
            L_0x01b4:
                r6.enforceInterface(r1)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x01c6
                android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x01c6:
                android.os.Bundle r5 = r4.getAccount(r3)
                r7.writeNoException()
                if (r5 == 0) goto L_0x01d6
                r7.writeInt(r2)
                r5.writeToParcel(r7, r2)
                goto L_0x01d9
            L_0x01d6:
                r7.writeInt(r0)
            L_0x01d9:
                return r2
            L_0x01da:
                r7.writeString(r1)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.microsoft.onlineid.internal.sso.service.IMsaSsoService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    Bundle getAccount(Bundle bundle) throws RemoteException;

    Bundle getAccountById(Bundle bundle) throws RemoteException;

    Bundle getAccountPickerIntent(Bundle bundle) throws RemoteException;

    Bundle getAllAccounts(Bundle bundle) throws RemoteException;

    Bundle getProofOfPossessionTokens(Bundle bundle) throws RemoteException;

    Bundle getSignInIntent(Bundle bundle) throws RemoteException;

    Bundle getSignOutIntent(Bundle bundle) throws RemoteException;

    Bundle getSignUpIntent(Bundle bundle) throws RemoteException;

    Bundle getTicket(Bundle bundle) throws RemoteException;

    Bundle getUserKey(Bundle bundle) throws RemoteException;

    Bundle retrieveBackup(Bundle bundle) throws RemoteException;

    Bundle storeBackup(Bundle bundle) throws RemoteException;
}
