package org.chromium.chrome.browser.edge_widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.microsoft.intune.mam.client.support.v4.app.MAMDialogFragment;
import com.microsoft.theme.ThemeManager;

/* compiled from: PG */
public abstract class BaseDialogFragment extends MAMDialogFragment {

    /* renamed from: a */
    public Activity f1796a;

    /* renamed from: b */
    public Bundle f1797b;

    /* renamed from: c */
    public View f1798c;

    /* renamed from: d */
    public Dialog f1799d;

    /* renamed from: e */
    public Window f1800e;

    /* renamed from: a */
    public <T extends View> T mo8518a(int i) {
        return this.f1798c.findViewById(i);
    }

    /* renamed from: a */
    public abstract void mo8519a(View view);

    /* renamed from: o */
    public a mo8520o() {
        return new a();
    }

    public void onConfigurationChanged(Configuration configuration) {
        BaseDialogFragment.super.onConfigurationChanged(configuration);
        mo8527q();
    }

    public void onMAMAttach(Context context) {
        BaseDialogFragment.super.onMAMAttach(context);
        this.f1796a = getActivity();
    }

    public void onMAMCreate(Bundle bundle) {
        BaseDialogFragment.super.onMAMCreate(bundle);
        setStyle(1, du0.b(this.f1796a.getResources(), xx0.DayNightAlertDialogTheme, ThemeManager.f1300h.mo4505b()));
        this.f1797b = getArguments();
    }

    public View onMAMCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f1799d = getDialog();
        this.f1800e = this.f1799d.getWindow();
        this.f1800e.requestFeature(1);
        this.f1798c = layoutInflater.inflate(mo8526p(), viewGroup, false);
        mo8519a(this.f1798c);
        return this.f1798c;
    }

    public void onMAMStart() {
        BaseDialogFragment.super.onMAMStart();
        mo8527q();
    }

    /* renamed from: p */
    public abstract int mo8526p();

    /* renamed from: q */
    public final void mo8527q() {
        a o = mo8520o();
        if (o == null || this.f1800e == null) {
            Log.w("BaseDialogFragment", "cannot setup because dialogParams or window is null");
            return;
        }
        this.f1799d.setCancelable(o.e);
        this.f1799d.setCanceledOnTouchOutside(o.f);
        WindowManager.LayoutParams attributes = this.f1800e.getAttributes();
        attributes.gravity = o.a;
        attributes.width = o.b;
        attributes.height = o.c;
        attributes.dimAmount = o.d;
        this.f1800e.setAttributes(attributes);
        if (Og0.d()) {
            Ng0.a(this.f1800e);
        }
    }
}
