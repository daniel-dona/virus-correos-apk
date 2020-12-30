package org.chromium.base.library_loader;

import android.os.Bundle;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.annotations.AccessedByNative;

/* compiled from: PG */
public abstract class Linker {

    /* renamed from: b */
    public static final Object f1512b = new Object();

    /* renamed from: c */
    public static Linker f1513c;

    /* renamed from: a */
    public String f1514a;

    /* compiled from: PG */
    public interface TestRunner {
        boolean runChecks(boolean z);
    }

    /* renamed from: e */
    public static final void m1514e(String str) {
        synchronized (f1512b) {
            m1516h().f1514a = str;
        }
    }

    /* renamed from: g */
    public static boolean m1515g() {
        return xO0.c;
    }

    /* renamed from: h */
    public static Linker m1516h() {
        Linker linker;
        synchronized (f1512b) {
            if (f1513c == null) {
                f1513c = new LegacyLinker();
                VN0.b("LibraryLoader", "Using linker: LegacyLinker", new Object[0]);
            }
            linker = f1513c;
        }
        return linker;
    }

    public static native long nativeGetRandomBaseLoadAddress();

    /* renamed from: a */
    public abstract void mo7890a();

    /* renamed from: a */
    public abstract void mo7891a(long j);

    /* renamed from: a */
    public abstract void mo7892a(String str, boolean z);

    /* renamed from: a */
    public final void mo7914a(boolean z) {
        synchronized (f1512b) {
            if (this.f1514a == null) {
                VN0.d("LibraryLoader", "Linker runtime tests not set up for this process", new Object[0]);
            }
            TestRunner testRunner = null;
            try {
                testRunner = (TestRunner) Class.forName(this.f1514a).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Exception e) {
                VN0.d("LibraryLoader", "Could not instantiate test runner class by name", new Object[]{e});
            }
            if (!testRunner.runChecks(z)) {
                VN0.d("LibraryLoader", "Linker runtime tests failed in this process", new Object[0]);
            }
            VN0.b("LibraryLoader", "All linker tests passed", new Object[0]);
        }
    }

    /* renamed from: b */
    public abstract void mo7893b();

    /* renamed from: b */
    public abstract void mo7894b(Bundle bundle);

    /* renamed from: b */
    public void mo7917b(String str) {
        mo7892a(str, true);
    }

    /* renamed from: c */
    public abstract long mo7895c();

    /* renamed from: c */
    public void mo7918c(String str) {
        mo7892a(str, false);
    }

    /* renamed from: d */
    public long mo7919d() {
        return nativeGetRandomBaseLoadAddress();
    }

    /* renamed from: d */
    public abstract void mo7897d(String str);

    /* renamed from: e */
    public abstract Bundle mo7898e();

    /* renamed from: f */
    public final String mo7920f() {
        String str;
        synchronized (f1512b) {
            str = this.f1514a;
        }
        return str;
    }

    /* compiled from: PG */
    public static class LibInfo implements Parcelable {
        public static final Parcelable.Creator<LibInfo> CREATOR = new C0375a();
        @AccessedByNative
        public long mLoadAddress;
        @AccessedByNative
        public long mLoadSize;
        @AccessedByNative
        public int mRelroFd = -1;
        @AccessedByNative
        public long mRelroSize;
        @AccessedByNative
        public long mRelroStart;

        /* renamed from: org.chromium.base.library_loader.Linker$LibInfo$a */
        /* compiled from: PG */
        public class C0375a implements Parcelable.Creator<LibInfo> {
            public Object createFromParcel(Parcel parcel) {
                return new LibInfo(parcel);
            }

            public Object[] newArray(int i) {
                return new LibInfo[i];
            }
        }

        public LibInfo() {
        }

        /* renamed from: a */
        public void mo7921a() {
            int i = this.mRelroFd;
            if (i >= 0) {
                oO0.a(ParcelFileDescriptor.adoptFd(i));
                this.mRelroFd = -1;
            }
        }

        public int describeContents() {
            return 1;
        }

        public void writeToParcel(Parcel parcel, int i) {
            if (this.mRelroFd >= 0) {
                parcel.writeLong(this.mLoadAddress);
                parcel.writeLong(this.mLoadSize);
                parcel.writeLong(this.mRelroStart);
                parcel.writeLong(this.mRelroSize);
                try {
                    ParcelFileDescriptor fromFd = ParcelFileDescriptor.fromFd(this.mRelroFd);
                    fromFd.writeToParcel(parcel, 0);
                    fromFd.close();
                } catch (IOException e) {
                    VN0.a("LibraryLoader", "Can't write LibInfo file descriptor to parcel", new Object[]{e});
                }
            }
        }

        public LibInfo(Parcel parcel) {
            this.mLoadAddress = parcel.readLong();
            this.mLoadSize = parcel.readLong();
            this.mRelroStart = parcel.readLong();
            this.mRelroSize = parcel.readLong();
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
            if (parcelFileDescriptor != null) {
                this.mRelroFd = parcelFileDescriptor.detachFd();
            }
        }
    }

    /* renamed from: b */
    public Bundle mo7916b(HashMap<String, LibInfo> hashMap) {
        Bundle bundle = new Bundle(hashMap.size());
        for (Map.Entry next : hashMap.entrySet()) {
            bundle.putParcelable((String) next.getKey(), (Parcelable) next.getValue());
        }
        return bundle;
    }

    /* renamed from: a */
    public boolean mo7915a(String str) {
        return str.equals("chromium_android_linker");
    }

    /* renamed from: a */
    public HashMap<String, LibInfo> mo7912a(Bundle bundle) {
        HashMap<String, LibInfo> hashMap = new HashMap<>();
        for (String str : bundle.keySet()) {
            hashMap.put(str, (LibInfo) bundle.getParcelable(str));
        }
        return hashMap;
    }

    /* renamed from: a */
    public void mo7913a(HashMap<String, LibInfo> hashMap) {
        for (Map.Entry<String, LibInfo> value : hashMap.entrySet()) {
            ((LibInfo) value.getValue()).mo7921a();
        }
    }
}
