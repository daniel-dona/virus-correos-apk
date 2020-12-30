package org.chromium.chrome.browser.preferences;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.v7.preference.CheckBoxPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/* compiled from: PG */
public class ChromeBaseCheckBoxPreferenceCompat extends CheckBoxPreference {

    /* renamed from: X3 */
    public boolean f2163X3;

    /* renamed from: Y3 */
    public em2 f2164Y3;

    /* renamed from: Z3 */
    public ColorStateList f2165Z3;

    /* renamed from: a4 */
    public int f2166a4;

    /* renamed from: b4 */
    public WeakReference<View> f2167b4;

    public ChromeBaseCheckBoxPreferenceCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, yx0.ChromeBasePreference);
        this.f2163X3 = obtainStyledAttributes.getBoolean(yx0.ChromeBaseCheckBoxPreference_useDisabledColor, false);
        obtainStyledAttributes.recycle();
        this.f2166a4 = du0.a(context.getResources(), jx0.default_text_color);
    }

    /* renamed from: D */
    public void mo9005D() {
        ss0.a((String) null, this);
        ss0.a(this, true, M());
        if (!gm2.c(this.f2164Y3, this)) {
            ChromeBaseCheckBoxPreferenceCompat.super.D();
        }
    }

    /* renamed from: a */
    public void mo9007a(em2 em2) {
        this.f2164Y3 = em2;
        gm2.b(this.f2164Y3, this);
    }

    /* renamed from: d */
    public final void mo9008d(View view) {
        TextView textView = (TextView) view.findViewById(16908310);
        if (this.f2163X3) {
            textView.setTextColor(du0.a(c().getResources(), jx0.pref_privacy_share_browsing_history_disabled_title_color));
        } else {
            ColorStateList colorStateList = this.f2165Z3;
            if (colorStateList == null) {
                textView.setTextColor(this.f2166a4);
            } else {
                textView.setTextColor(colorStateList);
            }
        }
        view.findViewById(16908289).setEnabled(!this.f2163X3);
    }

    /* renamed from: n */
    public void mo9009n(boolean z) {
        this.f2163X3 = z;
        WeakReference<View> weakReference = this.f2167b4;
        if (weakReference != null && weakReference.get() != null) {
            mo9008d((View) this.f2167b4.get());
        }
    }

    /* renamed from: a */
    public void mo9006a(Ld ld) {
        ChromeBaseCheckBoxPreferenceCompat.super.a(ld);
        ((TextView) ld.findViewById(16908310)).setSingleLine(false);
        TextView textView = (TextView) ld.findViewById(16908310);
        if (this.f2165Z3 == null) {
            this.f2165Z3 = textView.getTextColors();
        }
        this.f2167b4 = new WeakReference<>(ld.itemView);
        gm2.a(this.f2164Y3, this, ld.itemView);
        mo9008d(ld.itemView);
    }
}
