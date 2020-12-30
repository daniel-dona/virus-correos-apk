package com.airbnb.lottie.model.content;

/* compiled from: PG */
public class MergePaths implements pj {

    /* renamed from: a */
    public final String f76a;

    /* renamed from: b */
    public final MergePathsMode f77b;

    /* renamed from: c */
    public final boolean f78c;

    /* compiled from: PG */
    public enum MergePathsMode {
        MERGE,
        ADD,
        SUBTRACT,
        INTERSECT,
        EXCLUDE_INTERSECTIONS;

        public static MergePathsMode forId(int i) {
            if (i == 1) {
                return MERGE;
            }
            if (i == 2) {
                return ADD;
            }
            if (i == 3) {
                return SUBTRACT;
            }
            if (i == 4) {
                return INTERSECT;
            }
            if (i != 5) {
                return MERGE;
            }
            return EXCLUDE_INTERSECTIONS;
        }
    }

    public MergePaths(String str, MergePathsMode mergePathsMode, boolean z) {
        this.f76a = str;
        this.f77b = mergePathsMode;
        this.f78c = z;
    }

    /* renamed from: a */
    public ki mo110a(Uh uh, Aj aj) {
        if (uh.x) {
            return new ti(this);
        }
        jk.b("Animation contains merge paths but they are disabled.");
        return null;
    }

    public String toString() {
        StringBuilder a = Eo.a("MergePaths{mode=");
        a.append(this.f77b);
        a.append('}');
        return a.toString();
    }
}
