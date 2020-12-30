package org.chromium.chrome.browser.preferences;

import android.content.Context;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.widget.TextView;

/* compiled from: PG */
public class TextMessagePreferenceCompat extends ChromeBasePreferenceCompat {

    /* renamed from: Y3 */
    public MovementMethod f2190Y3;

    public TextMessagePreferenceCompat(Context context) {
        this(context, (AttributeSet) null);
    }

    /* renamed from: a */
    public void mo9012a(Ld ld) {
        super.mo9012a(ld);
        TextView textView = (TextView) ld.findViewById(16908310);
        if (!TextUtils.isEmpty(t())) {
            textView.setVisibility(0);
            textView.setSingleLine(false);
            textView.setMaxLines(Integer.MAX_VALUE);
            MovementMethod movementMethod = this.f2190Y3;
            if (movementMethod != null) {
                textView.setMovementMethod(movementMethod);
            } else {
                textView.setFocusable(true);
            }
        } else {
            textView.setVisibility(8);
        }
        TextView textView2 = (TextView) ld.findViewById(16908304);
        if (this.f2190Y3 != null) {
            textView2.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            textView2.setFocusable(true);
        }
        textView2.setMaxLines(Integer.MAX_VALUE);
        textView2.setLongClickable(false);
        textView2.setClickable(false);
    }

    public TextMessagePreferenceCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2190Y3 = LinkMovementMethod.getInstance();
        h(false);
    }

    /* renamed from: a */
    public void mo9172a(MovementMethod movementMethod) {
        this.f2190Y3 = movementMethod;
    }
}
