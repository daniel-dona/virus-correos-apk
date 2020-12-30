package com.google.firebase.iid;

import android.os.Build;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.firebase_messaging.zze;
import com.google.android.gms.internal.firebase_messaging.zzf;

/* compiled from: PG */
public class zzi implements Parcelable {
    public static final Parcelable.Creator<zzi> CREATOR = new nJ();

    /* renamed from: a */
    public Messenger f924a;

    /* renamed from: b */
    public zze f925b;

    /* renamed from: com.google.firebase.iid.zzi$a */
    /* compiled from: PG */
    public static final class C0202a extends ClassLoader {
        public final Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
            if (!"com.google.android.gms.iid.MessengerCompat".equals(str)) {
                return super.loadClass(str, z);
            }
            boolean j = FirebaseInstanceId.m810j();
            return zzi.class;
        }
    }

    public zzi(IBinder iBinder) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f924a = new Messenger(iBinder);
        } else {
            this.f925b = zzf.zza(iBinder);
        }
    }

    /* renamed from: a */
    public final IBinder mo2056a() {
        Messenger messenger = this.f924a;
        return messenger != null ? messenger.getBinder() : this.f925b.asBinder();
    }

    /* renamed from: a */
    public final void mo2057a(Message message) throws RemoteException {
        Messenger messenger = this.f924a;
        if (messenger != null) {
            messenger.send(message);
        } else {
            this.f925b.send(message);
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return mo2056a().equals(((zzi) obj).mo2056a());
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public int hashCode() {
        return mo2056a().hashCode();
    }

    public void writeToParcel(Parcel parcel, int i) {
        Messenger messenger = this.f924a;
        parcel.writeStrongBinder(messenger != null ? messenger.getBinder() : this.f925b.asBinder());
    }
}
