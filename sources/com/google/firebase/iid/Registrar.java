package com.google.firebase.iid;

import android.support.annotation.Keep;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseApp;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Keep
@KeepForSdk
/* compiled from: PG */
public final class Registrar implements yI {

    /* renamed from: com.google.firebase.iid.Registrar$a */
    /* compiled from: PG */
    public static class C0201a implements MI {
        public C0201a(FirebaseInstanceId firebaseInstanceId) {
        }
    }

    @Keep
    public final List<vI<?>> getComponents() {
        Class<FirebaseInstanceId> cls = FirebaseInstanceId.class;
        Class[] clsArr = new Class[0];
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        Preconditions.checkNotNull(cls, "Null interface");
        hashSet.add(cls);
        for (Class checkNotNull : clsArr) {
            Preconditions.checkNotNull(checkNotNull, "Null interface");
        }
        Collections.addAll(hashSet, clsArr);
        boolean z = true;
        zI zIVar = new zI(FirebaseApp.class, 1, 0);
        Preconditions.checkNotNull(zIVar, "Null dependency");
        Preconditions.checkArgument(!hashSet.contains(zIVar.a), "Components are not allowed to depend on interfaces they themselves provide.");
        hashSet2.add(zIVar);
        xI xIVar = (xI) Preconditions.checkNotNull(UI.a, "Null factory");
        Preconditions.checkState(!false, "Instantiation type has already been set.");
        Preconditions.checkState(xIVar != null, "Missing required property: factory.");
        HashSet hashSet4 = new HashSet(hashSet);
        HashSet hashSet5 = new HashSet(hashSet2);
        Object obj = "Missing required property: factory.";
        vI vIVar = new vI(hashSet4, hashSet5, 1, xIVar, hashSet3, (byte) 0);
        Class<MI> cls2 = MI.class;
        Class[] clsArr2 = new Class[0];
        HashSet hashSet6 = new HashSet();
        HashSet hashSet7 = new HashSet();
        HashSet hashSet8 = new HashSet();
        Preconditions.checkNotNull(cls2, "Null interface");
        hashSet6.add(cls2);
        for (Class checkNotNull2 : clsArr2) {
            Preconditions.checkNotNull(checkNotNull2, "Null interface");
        }
        Collections.addAll(hashSet6, clsArr2);
        zI zIVar2 = new zI(cls, 1, 0);
        Preconditions.checkNotNull(zIVar2, "Null dependency");
        Preconditions.checkArgument(!hashSet6.contains(zIVar2.a), "Components are not allowed to depend on interfaces they themselves provide.");
        hashSet7.add(zIVar2);
        xI xIVar2 = (xI) Preconditions.checkNotNull(VI.a, "Null factory");
        if (xIVar2 == null) {
            z = false;
        }
        Preconditions.checkState(z, obj);
        return Arrays.asList(new vI[]{vIVar, new vI(new HashSet(hashSet6), new HashSet(hashSet7), 0, xIVar2, hashSet8, (byte) 0)});
    }
}
