package divide.maze.insect;

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
@SafeParcelable.Class(creator = "NKfFbIhYkEeCreator")
public final class NKfFbIhYkEe extends AbstractSafeParcelable implements Result, ReflectedParcelable {
    public static final Parcelable.Creator<NKfFbIhYkEe> CREATOR = new zzb();
    @KeepForSdk
    public static final NKfFbIhYkEe RESULT_CANCELED = new NKfFbIhYkEe(16);
    @KeepForSdk
    public static final NKfFbIhYkEe RESULT_DEAD_CLIENT = new NKfFbIhYkEe(18);
    @KeepForSdk
    public static final NKfFbIhYkEe RESULT_INTERNAL_ERROR = new NKfFbIhYkEe(8);
    @KeepForSdk
    public static final NKfFbIhYkEe RESULT_INTERRUPTED = new NKfFbIhYkEe(14);
    @KeepForSdk
    @VisibleForTesting
    public static final NKfFbIhYkEe RESULT_SUCCESS = new NKfFbIhYkEe(0);
    @KeepForSdk
    public static final NKfFbIhYkEe RESULT_TIMEOUT = new NKfFbIhYkEe(15);
    private static final NKfFbIhYkEe zzaq = new NKfFbIhYkEe(17);
    @SafeParcelable.VersionField(id = 1000)
    private final int zzg;
    @SafeParcelable.Field(getter = "getNKfFbIhYkEeCode", id = 1)
    private final int zzh;
    @SafeParcelable.Field(getter = "getPendingIntent", id = 3)
    private final PendingIntent zzi;
    @SafeParcelable.Field(getter = "getNKfFbIhYkEeMessage", id = 2)
    private final String zzj;

    @SafeParcelable.Constructor
    @KeepForSdk
    NKfFbIhYkEe(@SafeParcelable.Param(id = 1000) int i, @SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) PendingIntent pendingIntent) {
        this.zzg = i;
        this.zzh = i2;
        this.zzj = str;
        this.zzi = pendingIntent;
    }

    @KeepForSdk
    public final NKfFbIhYkEe getNKfFbIhYkEe() {
        return this;
    }

    @KeepForSdk
    public NKfFbIhYkEe(int i) {
        this(i, (String) null);
    }

    @KeepForSdk
    public NKfFbIhYkEe(int i, String str) {
        this(1, i, str, (PendingIntent) null);
    }

    @KeepForSdk
    public NKfFbIhYkEe(int i, String str, PendingIntent pendingIntent) {
        this(1, i, str, pendingIntent);
    }

    public final void startResolutionForResult(Activity activity, int i) {
        if (hasResolution()) {
            activity.startIntentSenderForResult(this.zzi.getIntentSender(), i, (Intent) null, 0, 0, 0);
        }
    }

    public final String getNKfFbIhYkEeMessage() {
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

    public final int getNKfFbIhYkEeCode() {
        return this.zzh;
    }

    public final PendingIntent getResolution() {
        return this.zzi;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zzg), Integer.valueOf(this.zzh), this.zzj, this.zzi});
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof NKfFbIhYkEe)) {
            return false;
        }
        NKfFbIhYkEe nKfFbIhYkEe = (NKfFbIhYkEe) obj;
        if (this.zzg != nKfFbIhYkEe.zzg || this.zzh != nKfFbIhYkEe.zzh || !Objects.equal(this.zzj, nKfFbIhYkEe.zzj) || !Objects.equal(this.zzi, nKfFbIhYkEe.zzi)) {
            return false;
        }
        return true;
    }

    public final String zzg() {
        if (this.zzj != null) {
            return this.zzj;
        }
        return CommonNKfFbIhYkEeCodes.getNKfFbIhYkEeCodeString(this.zzh);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("statusCode", zzg()).add("resolution", this.zzi).toString();
    }

    @KeepForSdk
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getNKfFbIhYkEeCode());
        SafeParcelWriter.writeString(parcel, 2, getNKfFbIhYkEeMessage(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzi, i, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzg);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
