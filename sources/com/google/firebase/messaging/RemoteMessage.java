package com.google.firebase.messaging;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Map;

@SafeParcelable.Class(creator = "RemoteMessageCreator")
@SafeParcelable.Reserved({1})
/* compiled from: PG */
public final class RemoteMessage extends AbstractSafeParcelable {
    public static final Parcelable.Creator<RemoteMessage> CREATOR = new IJ();
    @SafeParcelable.Field(id = 2)

    /* renamed from: a */
    public Bundle f927a;

    /* renamed from: b */
    public Map<String, String> f928b;

    @SafeParcelable.Constructor
    public RemoteMessage(@SafeParcelable.Param(id = 2) Bundle bundle) {
        this.f927a = bundle;
    }

    /* renamed from: a */
    public final Map<String, String> mo2067a() {
        if (this.f928b == null) {
            this.f928b = new ArrayMap();
            for (String str : this.f927a.keySet()) {
                Object obj = this.f927a.get(str);
                if (obj instanceof String) {
                    String str2 = (String) obj;
                    if (!str.startsWith("google.") && !str.startsWith("gcm.") && !str.equals("from") && !str.equals("message_type") && !str.equals("collapse_key")) {
                        this.f928b.put(str, str2);
                    }
                }
            }
        }
        return this.f928b;
    }

    /* renamed from: b */
    public final String mo2068b() {
        return this.f927a.getString("from");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, this.f927a, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
