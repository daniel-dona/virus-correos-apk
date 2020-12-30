package org.chromium.chrome.browser.preferences;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.preference.Preference;
import android.util.AttributeSet;
import android.widget.TextView;

/* compiled from: PG */
public class ChromeBasePreferenceCompat extends Preference {

    /* renamed from: R3 */
    public ColorStateList f2168R3;

    /* renamed from: S3 */
    public em2 f2169S3;

    /* renamed from: T3 */
    public boolean f2170T3;

    /* renamed from: U3 */
    public boolean f2171U3;

    /* renamed from: V3 */
    public String f2172V3;

    /* renamed from: W3 */
    public Boolean f2173W3;

    /* renamed from: X3 */
    public Boolean f2174X3;

    public ChromeBasePreferenceCompat(Context context) {
        this(context, (AttributeSet) null);
    }

    /* renamed from: D */
    public void mo9010D() {
        ss0.a((String) null, this);
        ss0.a(this, false, false);
        if (gm2.c(this.f2169S3, this)) {
        }
    }

    /* renamed from: M */
    public boolean mo9011M() {
        return this.f2171U3;
    }

    /* renamed from: a */
    public void mo9013a(em2 em2) {
        this.f2169S3 = em2;
        gm2.b(this.f2169S3, this);
    }

    /* renamed from: f */
    public void mo9014f(String str) {
        this.f2172V3 = str;
    }

    /* renamed from: l */
    public void mo9015l(boolean z) {
        this.f2173W3 = Boolean.valueOf(z);
    }

    /* renamed from: m */
    public void mo9016m(boolean z) {
        this.f2174X3 = Boolean.valueOf(z);
    }

    /* renamed from: n */
    public void mo9017n(boolean z) {
        if (this.f2170T3 != z) {
            this.f2170T3 = z;
            A();
        }
    }

    /* renamed from: o */
    public void mo9018o(boolean z) {
        this.f2171U3 = z;
    }

    public ChromeBasePreferenceCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d(rx0.preference_compat);
        j(false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, yx0.ChromeBasePreference);
        this.f2168R3 = obtainStyledAttributes.getColorStateList(yx0.ChromeBasePreference_iconTint);
        this.f2170T3 = obtainStyledAttributes.getBoolean(yx0.ChromeBasePreference_drawDivider, false);
        this.f2171U3 = obtainStyledAttributes.getBoolean(yx0.ChromeBasePreference_notListItemForAccessibility, false);
        obtainStyledAttributes.recycle();
    }

    /* renamed from: a */
    public void mo9012a(Ld ld) {
        ColorStateList colorStateList;
        ChromeBasePreferenceCompat.super.a(ld);
        if (this.f2170T3) {
            int paddingLeft = ld.itemView.getPaddingLeft();
            int paddingRight = ld.itemView.getPaddingRight();
            int paddingTop = ld.itemView.getPaddingTop();
            int paddingBottom = ld.itemView.getPaddingBottom();
            ld.itemView.setBackground(IH3.a(c()));
            ld.itemView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        }
        TextView textView = (TextView) ld.itemView.findViewById(16908310);
        if (textView != null) {
            textView.setSingleLine(false);
            textView.setTextColor(du0.b(c().getResources(), jx0.default_text_color_list));
            String str = this.f2172V3;
            if (str != null) {
                textView.setContentDescription(str);
            }
        }
        TextView textView2 = (TextView) ld.itemView.findViewById(16908304);
        if (textView2 != null) {
            textView2.setTextColor(du0.b(c().getResources(), jx0.default_text_color_secondary_list));
        }
        Drawable g = g();
        if (!(g == null || (colorStateList = this.f2168R3) == null)) {
            g.setColorFilter(colorStateList.getDefaultColor(), PorterDuff.Mode.SRC_IN);
        }
        gm2.a(this.f2169S3, this, ld.itemView);
        Boolean bool = this.f2173W3;
        if (bool != null) {
            ld.b = bool.booleanValue();
        }
        Boolean bool2 = this.f2174X3;
        if (bool2 != null) {
            ld.c = bool2.booleanValue();
        }
    }
}
