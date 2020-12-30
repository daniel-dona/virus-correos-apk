package com.airbnb.lottie.model.layer;

import com.airbnb.lottie.model.content.Mask;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.internal.cache.AbstractAccountCredentialCache;
import java.util.List;
import java.util.Locale;

/* compiled from: PG */
public class Layer {

    /* renamed from: a */
    public final List<pj> f105a;

    /* renamed from: b */
    public final vh f106b;

    /* renamed from: c */
    public final String f107c;

    /* renamed from: d */
    public final long f108d;

    /* renamed from: e */
    public final LayerType f109e;

    /* renamed from: f */
    public final long f110f;

    /* renamed from: g */
    public final String f111g;

    /* renamed from: h */
    public final List<Mask> f112h;

    /* renamed from: i */
    public final lj f113i;

    /* renamed from: j */
    public final int f114j;

    /* renamed from: k */
    public final int f115k;

    /* renamed from: l */
    public final int f116l;

    /* renamed from: m */
    public final float f117m;

    /* renamed from: n */
    public final float f118n;

    /* renamed from: o */
    public final int f119o;

    /* renamed from: p */
    public final int f120p;

    /* renamed from: q */
    public final jj f121q;

    /* renamed from: r */
    public final kj f122r;

    /* renamed from: s */
    public final bj f123s;

    /* renamed from: t */
    public final List<ok<Float>> f124t;

    /* renamed from: u */
    public final MatteType f125u;

    /* renamed from: v */
    public final boolean f126v;

    /* compiled from: PG */
    public enum LayerType {
        PRE_COMP,
        SOLID,
        IMAGE,
        NULL,
        SHAPE,
        TEXT,
        UNKNOWN
    }

    /* compiled from: PG */
    public enum MatteType {
        NONE,
        ADD,
        INVERT,
        UNKNOWN
    }

    public Layer(List<pj> list, vh vhVar, String str, long j, LayerType layerType, long j2, String str2, List<Mask> list2, lj ljVar, int i, int i2, int i3, float f, float f2, int i4, int i5, jj jjVar, kj kjVar, List<ok<Float>> list3, MatteType matteType, bj bjVar, boolean z) {
        this.f105a = list;
        this.f106b = vhVar;
        this.f107c = str;
        this.f108d = j;
        this.f109e = layerType;
        this.f110f = j2;
        this.f111g = str2;
        this.f112h = list2;
        this.f113i = ljVar;
        this.f114j = i;
        this.f115k = i2;
        this.f116l = i3;
        this.f117m = f;
        this.f118n = f2;
        this.f119o = i4;
        this.f120p = i5;
        this.f121q = jjVar;
        this.f122r = kjVar;
        this.f124t = list3;
        this.f125u = matteType;
        this.f123s = bjVar;
        this.f126v = z;
    }

    /* renamed from: a */
    public String mo118a(String str) {
        StringBuilder a = Eo.a(str);
        a.append(this.f107c);
        a.append(AbstractAccountCredentialCache.NEW_LINE);
        Layer a2 = this.f106b.a(this.f110f);
        if (a2 != null) {
            a.append("\t\tParents: ");
            a.append(a2.f107c);
            Layer a3 = this.f106b.a(a2.f110f);
            while (a3 != null) {
                a.append("->");
                a.append(a3.f107c);
                a3 = this.f106b.a(a3.f110f);
            }
            a.append(str);
            a.append(AbstractAccountCredentialCache.NEW_LINE);
        }
        if (!this.f112h.isEmpty()) {
            a.append(str);
            a.append("\tMasks: ");
            a.append(this.f112h.size());
            a.append(AbstractAccountCredentialCache.NEW_LINE);
        }
        if (!(this.f114j == 0 || this.f115k == 0)) {
            a.append(str);
            a.append("\tBackground: ");
            a.append(String.format(Locale.US, "%dx%d %X\n", new Object[]{Integer.valueOf(this.f114j), Integer.valueOf(this.f115k), Integer.valueOf(this.f116l)}));
        }
        if (!this.f105a.isEmpty()) {
            a.append(str);
            a.append("\tShapes:\n");
            for (pj next : this.f105a) {
                a.append(str);
                a.append("\t\t");
                a.append(next);
                a.append(AbstractAccountCredentialCache.NEW_LINE);
            }
        }
        return a.toString();
    }

    public String toString() {
        return mo118a(BuildConfig.FLAVOR);
    }
}
