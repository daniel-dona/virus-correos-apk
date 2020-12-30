package com.citrix.mvpn.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import com.citrix.mvpn.MAM.Android.AuthSSO.proxy.Helper;
import com.citrix.mvpn.helper.C0051c;
import com.citrix.mvpn.p003c.C0042e;

/* compiled from: PG */
public class TunnelStateContentProvider extends ContentProvider {

    /* renamed from: a */
    public static C0042e f189a = C0051c.m201b();

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        C0051c.f218b.debug5("MVPN-ContentProvider", "Creating TunnelState ContentProvider.");
        UriMatcher uriMatcher = C0051c.f217a;
        uriMatcher.addURI(getContext().getPackageName() + ".com.citrix.mvpn.tunnelStateProvider", "tunnelState", 1);
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        C0051c.f218b.debug5("MVPN-ContentProvider", "Retrieving TunnelState ContentProvider.");
        boolean z = true;
        if (C0051c.f217a.match(uri) == 1) {
            MatrixCursor matrixCursor = new MatrixCursor(new String[]{"ID", "PROXY_ID", "PROXY_PORT", "MITM_SOCKET_LISTENING", "NSG_COOKIE_EXPIRED"});
            Object[] objArr = new Object[5];
            objArr[0] = Long.valueOf(f189a.mo233a());
            objArr[1] = f189a.mo238b();
            objArr[2] = Integer.valueOf(f189a.mo240c());
            if (Helper.a() && !f189a.mo241d()) {
                z = false;
            }
            objArr[3] = Boolean.valueOf(z);
            objArr[4] = Boolean.valueOf(f189a.mo242e());
            matrixCursor.addRow(objArr);
            return matrixCursor;
        }
        StringBuilder a = Eo.a("Invalid URI:");
        a.append(uri.toString());
        throw new IllegalArgumentException(a.toString());
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        C0051c.f218b.debug5("MVPN-ContentProvider", "Updating TunnelState ContentProvider.");
        if (C0051c.f217a.match(uri) != 1) {
            StringBuilder a = Eo.a("Invalid URI");
            a.append(uri.toString());
            throw new IllegalArgumentException(a.toString());
        } else if (contentValues == null) {
            return 0;
        } else {
            f189a.mo235a(contentValues.getAsLong("ID").longValue());
            f189a.mo236a(contentValues.getAsString("PROXY_ID"));
            f189a.mo234a(contentValues.getAsInteger("PROXY_PORT").intValue());
            f189a.mo237a(contentValues.getAsBoolean("MITM_SOCKET_LISTENING").booleanValue());
            f189a.mo239b(contentValues.getAsBoolean("NSG_COOKIE_EXPIRED").booleanValue());
            return 1;
        }
    }
}
