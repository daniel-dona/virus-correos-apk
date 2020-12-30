package org.chromium.chrome.browser.microsoft_signin;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.citrix.loggersdk.BuildConfig;
import com.facebook.react.uimanager.BaseViewManager;
import com.microsoft.ruby.telemetry.TelemetryConstants;
import com.microsoft.theme.Theme;
import com.microsoft.theme.ThemeManager;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.edge_widget.BaseDialogFragment;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager;

/* compiled from: PG */
public class MergeDataDialogFragment extends BaseDialogFragment implements View.OnClickListener {

    /* renamed from: k */
    public MergeDataDialogDataProvider f2074k;

    /* renamed from: n */
    public LinearLayout f2075n;

    /* renamed from: p */
    public LinearLayout f2076p;

    /* renamed from: q */
    public RadioButton f2077q;

    /* renamed from: q3 */
    public TextView f2078q3;

    /* renamed from: r3 */
    public boolean f2079r3 = true;

    /* renamed from: x */
    public RadioButton f2080x;

    /* renamed from: y */
    public TextView f2081y;

    /* renamed from: b */
    public static void m2550b(AppCompatActivity appCompatActivity, MergeDataDialogDataProvider$UserSelectListener mergeDataDialogDataProvider$UserSelectListener) {
        m2548a((FragmentActivity) appCompatActivity, (MergeDataDialogDataProvider) new x82(appCompatActivity, mergeDataDialogDataProvider$UserSelectListener));
    }

    /* renamed from: c */
    public static void m2551c(AppCompatActivity appCompatActivity, MergeDataDialogDataProvider$UserSelectListener mergeDataDialogDataProvider$UserSelectListener) {
        O92 o92 = new O92(appCompatActivity, mergeDataDialogDataProvider$UserSelectListener);
        o92.c = MicrosoftSigninManager.C0424c.f2104a.mo8922o();
        m2548a((FragmentActivity) appCompatActivity, (MergeDataDialogDataProvider) o92);
    }

    /* renamed from: d */
    public static void m2552d(AppCompatActivity appCompatActivity, MergeDataDialogDataProvider$UserSelectListener mergeDataDialogDataProvider$UserSelectListener) {
        m2548a((FragmentActivity) appCompatActivity, (MergeDataDialogDataProvider) new P92(appCompatActivity, mergeDataDialogDataProvider$UserSelectListener));
    }

    /* renamed from: a */
    public void mo8850a(MergeDataDialogDataProvider mergeDataDialogDataProvider) {
        this.f2074k = mergeDataDialogDataProvider;
    }

    /* renamed from: o */
    public BaseDialogFragment.a mo8520o() {
        if (this.f2074k == null) {
            return new BaseDialogFragment.a();
        }
        Context context = getContext();
        Configuration configuration = getResources().getConfiguration();
        int min = Math.min(DV1.a(context, (float) configuration.screenWidthDp), DV1.a(context, (float) configuration.screenHeightDp));
        if (Og0.d()) {
            min = Math.min(min, Og0.f.e(context).x);
        }
        BaseDialogFragment.a aVar = new BaseDialogFragment.a();
        aVar.b = min - (context.getResources().getDimensionPixelSize(kx0.anaheim_dialog) * 2);
        aVar.c = -2;
        aVar.e = this.f2074k.g();
        aVar.f = this.f2074k.g();
        return aVar;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.f2074k.c().onCancel();
    }

    public void onClick(View view) {
        if (view.getId() == ox0.merge_confirm_button) {
            if (this.f2077q.isChecked()) {
                this.f2074k.c().onPrimaryOptionSelected();
            } else if (this.f2080x.isChecked()) {
                this.f2074k.c().onSecondaryOptionSelected();
            }
            TelemetryConstants.Actions actions = TelemetryConstants.Actions.Click;
            String[] strArr = new String[2];
            strArr[0] = "KeepOrDelete";
            strArr[1] = this.f2079r3 ? "keepData" : "deleteData";
            ss0.a("Settings", "SignOut", (String) null, actions, "Confirm", strArr);
        } else if (view.getId() == ox0.merge_cancel_button) {
            ss0.a("Settings", "SignOut", (String) null, TelemetryConstants.Actions.Click, "Cancel", new String[0]);
            this.f2074k.c().onCancel();
        }
        dismissAllowingStateLoss();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        String[] strArr = new String[2];
        strArr[0] = "KeepOrDelete";
        strArr[1] = this.f2079r3 ? "keepData" : "deleteData";
        ss0.b("Settings", "SignOut", (String) null, strArr);
    }

    public void onMAMCreate(Bundle bundle) {
        super.onMAMCreate(bundle);
        setStyle(1, xx0.FamilyDialogTheme);
        this.f1797b = getArguments();
        ThreadUtils.m1457a((Runnable) new a(this));
    }

    public Dialog onMAMCreateDialog(Bundle bundle) {
        return MergeDataDialogFragment.super.onMAMCreateDialog(bundle);
    }

    /* renamed from: p */
    public int mo8526p() {
        return rx0.merge_data_to_anaheim_dialog;
    }

    /* renamed from: r */
    public final /* synthetic */ void mo8855r() {
        this.f2077q.setChecked(true);
        this.f2080x.setChecked(false);
        this.f2079r3 = true;
        ss0.a("Settings", "SignOut", (String) null, TelemetryConstants.Actions.Click, "KeepData", new String[0]);
    }

    /* renamed from: s */
    public final /* synthetic */ void mo8856s() {
        this.f2080x.setChecked(true);
        this.f2077q.setChecked(false);
        this.f2079r3 = false;
        ss0.a("Settings", "SignOut", (String) null, TelemetryConstants.Actions.Click, "DeleteData", new String[0]);
    }

    public void show(FragmentManager fragmentManager, String str) {
        try {
            R4 a = fragmentManager.a();
            a.a(0, this, str, 1);
            a.b();
        } catch (IllegalStateException unused) {
        }
    }

    /* renamed from: a */
    public void mo8519a(View view) {
        if (this.f2074k != null) {
            ss0.a("Settings", "SignOut", "KeepData", new String[0]);
            mo8518a(ox0.merge_confirm_button).setOnClickListener(this);
            mo8518a(ox0.merge_cancel_button).setOnClickListener(this);
            TextView textView = (TextView) mo8518a(ox0.mergedata_title_view);
            textView.setText(this.f2074k.getTitle());
            textView.setContentDescription(this.f2074k.getTitle() + BuildConfig.FLAVOR + getResources().getString(wx0.accessibility_dialog_box));
            String e = this.f2074k.e();
            if (TextUtils.isEmpty(e)) {
                mo8518a(ox0.textview_dialog_message).setVisibility(8);
            } else {
                ((TextView) mo8518a(ox0.textview_dialog_message)).setText(e);
            }
            this.f2075n = (LinearLayout) mo8518a(ox0.keepdata_layout);
            this.f2076p = (LinearLayout) mo8518a(ox0.deletedata_layout);
            this.f2077q = (RadioButton) mo8518a(ox0.radiobutton_keepdata);
            this.f2077q.setText(this.f2074k.a());
            this.f2077q.setTypeface((Typeface) null, 1);
            this.f2080x = (RadioButton) mo8518a(ox0.radiobutton_deletedata);
            this.f2080x.setText(this.f2074k.b());
            this.f2080x.setTypeface((Typeface) null, 1);
            this.f2081y = (TextView) mo8518a(ox0.textview_keepdata_hint);
            if (!TextUtils.isEmpty(this.f2074k.d())) {
                this.f2081y.setText(this.f2074k.d());
            } else {
                this.f2081y.setVisibility(8);
            }
            this.f2078q3 = (TextView) mo8518a(ox0.textview_deletedata_hint);
            if (!TextUtils.isEmpty(this.f2074k.d())) {
                this.f2078q3.setText(this.f2074k.f());
            } else {
                this.f2078q3.setVisibility(8);
            }
            this.f2081y.setFocusable(false);
            this.f2081y.setFocusableInTouchMode(false);
            this.f2078q3.setFocusable(false);
            this.f2078q3.setFocusableInTouchMode(false);
            EE2.a(this.f2075n, this.f2077q, this.f2081y);
            EE2.a(this.f2076p, this.f2080x, this.f2078q3);
            this.f2075n.setOnClickListener(new W82(this));
            this.f2076p.setOnClickListener(new X82(this));
            if (ThemeManager.f1300h.mo4505b() == Theme.Dark && Build.VERSION.SDK_INT > 21) {
                this.f2077q.setButtonTintList(view.getResources().getColorStateList(jx0.family_radio_drawable_tint_dark));
                this.f2080x.setButtonTintList(view.getResources().getColorStateList(jx0.family_radio_drawable_tint_dark));
            }
            if (!this.f2074k.g()) {
                mo8518a(ox0.cancel_layout).setVisibility(8);
                ((LinearLayout.LayoutParams) ((LinearLayout) mo8518a(ox0.confirm_layout)).getLayoutParams()).setMargins(DV1.a(getContext(), BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER), DV1.a(getContext(), 16.0f), DV1.a(getContext(), BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER), DV1.a(getContext(), 22.0f));
            }
        }
    }

    /* renamed from: a */
    public static void m2549a(AppCompatActivity appCompatActivity, MergeDataDialogDataProvider$UserSelectListener mergeDataDialogDataProvider$UserSelectListener) {
        m2548a((FragmentActivity) appCompatActivity, (MergeDataDialogDataProvider) new w82(appCompatActivity, mergeDataDialogDataProvider$UserSelectListener));
    }

    /* renamed from: a */
    public static void m2547a(FragmentActivity fragmentActivity, MergeDataDialogDataProvider$UserSelectListener mergeDataDialogDataProvider$UserSelectListener) {
        m2548a(fragmentActivity, (MergeDataDialogDataProvider) new H82(fragmentActivity, mergeDataDialogDataProvider$UserSelectListener));
    }

    /* renamed from: a */
    public static void m2548a(FragmentActivity fragmentActivity, MergeDataDialogDataProvider mergeDataDialogDataProvider) {
        MergeDataDialogFragment mergeDataDialogFragment = new MergeDataDialogFragment();
        mergeDataDialogFragment.mo8850a(mergeDataDialogDataProvider);
        mergeDataDialogFragment.show(fragmentActivity.getSupportFragmentManager(), MergeDataDialogFragment.class.getSimpleName());
    }
}
