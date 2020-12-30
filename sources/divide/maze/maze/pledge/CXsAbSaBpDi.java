package divide.maze.maze.pledge;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;

@KeepForSdk
@SafeParcelable.Class(creator = "CXsAbSaBpDiCreator")
public final class CXsAbSaBpDi extends AbstractSafeParcelable implements Result, ReflectedParcelable {
    public static final Parcelable.Creator<CXsAbSaBpDi> CREATOR = new zzb();
    @KeepForSdk
    public static final CXsAbSaBpDi RESULT_CANCELED = new CXsAbSaBpDi(16);
    @KeepForSdk
    public static final CXsAbSaBpDi RESULT_DEAD_CLIENT = new CXsAbSaBpDi(18);
    @KeepForSdk
    public static final CXsAbSaBpDi RESULT_INTERNAL_ERROR = new CXsAbSaBpDi(8);
    @KeepForSdk
    public static final CXsAbSaBpDi RESULT_INTERRUPTED = new CXsAbSaBpDi(14);
    @KeepForSdk
    @VisibleForTesting
    public static final CXsAbSaBpDi RESULT_SUCCESS = new CXsAbSaBpDi(0);
    @KeepForSdk
    public static final CXsAbSaBpDi RESULT_TIMEOUT = new CXsAbSaBpDi(15);
    private static final CXsAbSaBpDi zzaq = new CXsAbSaBpDi(17);
    @SafeParcelable.VersionField(id = 1000)
    private final int zzg;
    @SafeParcelable.Field(getter = "getCXsAbSaBpDiCode", id = 1)
    private final int zzh;
    @SafeParcelable.Field(getter = "getPendingIntent", id = 3)
    private final PendingIntent zzi;
    @SafeParcelable.Field(getter = "getCXsAbSaBpDiMessage", id = 2)
    private final String zzj;

    @SafeParcelable.Constructor
    @KeepForSdk
    CXsAbSaBpDi(@SafeParcelable.Param(id = 1000) int i, @SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) PendingIntent pendingIntent) {
        this.zzg = i;
        this.zzh = i2;
        this.zzj = str;
        this.zzi = pendingIntent;
    }

    @KeepForSdk
    public final CXsAbSaBpDi getCXsAbSaBpDi() {
        return this;
    }

    @KeepForSdk
    public CXsAbSaBpDi(int i) {
        this(i, (String) null);
    }

    @KeepForSdk
    public CXsAbSaBpDi(int i, String str) {
        this(1, i, str, (PendingIntent) null);
    }

    @KeepForSdk
    public CXsAbSaBpDi(int i, String str, PendingIntent pendingIntent) {
        this(1, i, str, pendingIntent);
    }

    public final void startResolutionForResult(Activity activity, int i) {
        if (hasResolution()) {
            activity.startIntentSenderForResult(this.zzi.getIntentSender(), i, (Intent) null, 0, 0, 0);
        }
    }

    public final String getCXsAbSaBpDiMessage() {
        return this.zzj;
    }

    @VisibleForTesting
    public final boolean hasResolution() {
        return this.zzi != null;
    }

    public final boolean isSuccess() {
        return this.zzh <= 0;
    }

    public final boolean isCanceled() {
        return this.zzh == 16;
    }

    public final boolean isInterrupted() {
        return this.zzh == 14;
    }

    public final int getCXsAbSaBpDiCode() {
        return this.zzh;
    }

    public final PendingIntent getResolution() {
        return this.zzi;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zzg), Integer.valueOf(this.zzh), this.zzj, this.zzi});
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof CXsAbSaBpDi)) {
            return false;
        }
        CXsAbSaBpDi cXsAbSaBpDi = (CXsAbSaBpDi) obj;
        if (this.zzg != cXsAbSaBpDi.zzg || this.zzh != cXsAbSaBpDi.zzh || !Objects.equal(this.zzj, cXsAbSaBpDi.zzj) || !Objects.equal(this.zzi, cXsAbSaBpDi.zzi)) {
            return false;
        }
        return true;
    }

    public final String zzg() {
        if (this.zzj != null) {
            return this.zzj;
        }
        return CommonCXsAbSaBpDiCodes.getCXsAbSaBpDiCodeString(this.zzh);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("statusCode", zzg()).add("resolution", this.zzi).toString();
    }

    @KeepForSdk
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getCXsAbSaBpDiCode());
        SafeParcelWriter.writeString(parcel, 2, getCXsAbSaBpDiMessage(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzi, i, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzg);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
