package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Arrays;
import java.util.List;

@KeepForSdk
/* compiled from: PG */
public class DependencyCycleException extends DependencyException {
    public final List<vI<?>> zza;

    @KeepForSdk
    public DependencyCycleException(List<vI<?>> list) {
        super("Dependency cycle detected: " + Arrays.toString(list.toArray()));
        this.zza = list;
    }

    @KeepForSdk
    public List<vI<?>> getComponentsInCycle() {
        return this.zza;
    }
}
