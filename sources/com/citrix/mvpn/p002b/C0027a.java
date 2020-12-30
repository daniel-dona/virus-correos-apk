package com.citrix.mvpn.p002b;

import android.content.Context;
import com.citrix.mvpn.api.ResponseStatusCode;
import com.citrix.mvpn.p003c.C0042e;

/* renamed from: com.citrix.mvpn.b.a */
/* compiled from: PG */
public final class C0027a {

    /* renamed from: a */
    public final Context f142a;

    /* renamed from: b */
    public final C0042e f143b;

    /* renamed from: c */
    public final ResponseStatusCode f144c;

    public C0027a(Context context, C0042e eVar, ResponseStatusCode responseStatusCode) {
        this.f142a = context;
        this.f143b = eVar;
        this.f144c = responseStatusCode;
    }

    /* renamed from: a */
    public Context mo164a() {
        return this.f142a;
    }

    /* renamed from: b */
    public C0042e mo165b() {
        return this.f143b;
    }

    /* renamed from: c */
    public ResponseStatusCode mo166c() {
        return this.f144c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0027a)) {
            return false;
        }
        C0027a aVar = (C0027a) obj;
        Context a = mo164a();
        Context a2 = aVar.mo164a();
        if (a != null ? !a.equals(a2) : a2 != null) {
            return false;
        }
        C0042e b = mo165b();
        C0042e b2 = aVar.mo165b();
        if (b != null ? !b.equals(b2) : b2 != null) {
            return false;
        }
        ResponseStatusCode c = mo166c();
        ResponseStatusCode c2 = aVar.mo166c();
        return c != null ? c.equals(c2) : c2 == null;
    }

    public int hashCode() {
        Context a = mo164a();
        int i = 43;
        int hashCode = a == null ? 43 : a.hashCode();
        C0042e b = mo165b();
        int hashCode2 = ((hashCode + 59) * 59) + (b == null ? 43 : b.hashCode());
        ResponseStatusCode c = mo166c();
        int i2 = hashCode2 * 59;
        if (c != null) {
            i = c.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder a = Eo.a("EventData(context=");
        a.append(mo164a());
        a.append(", state=");
        a.append(mo165b());
        a.append(", statusCode=");
        a.append(mo166c());
        a.append(")");
        return a.toString();
    }
}
