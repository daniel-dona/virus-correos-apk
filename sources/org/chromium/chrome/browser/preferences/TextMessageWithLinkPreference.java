package org.chromium.chrome.browser.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.icu.text.BreakIterator;
import android.os.Build;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.citrix.loggersdk.BuildConfig;
import org.chromium.ui.widget.TextViewWithClickableSpans;
import uK3;

/* compiled from: PG */
public class TextMessageWithLinkPreference extends TextMessagePreferenceCompat {

    /* renamed from: Z3 */
    public Runnable f2191Z3;

    /* renamed from: a4 */
    public boolean f2192a4;

    public TextMessageWithLinkPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, yx0.TextMessageWithLinkPreference);
        this.f2192a4 = obtainStyledAttributes.getBoolean(yx0.TextMessageWithLinkPreference_useLinkControlType, false);
        obtainStyledAttributes.recycle();
        d(rx0.text_message_with_link_preference);
        mo9175a(s());
    }

    /* renamed from: N */
    public final /* synthetic */ void mo9173N() {
        Runnable runnable = this.f2191Z3;
        if (runnable != null) {
            runnable.run();
        }
    }

    /* renamed from: a */
    public void mo9176a(Runnable runnable) {
        this.f2191Z3 = runnable;
    }

    /* renamed from: a */
    public void mo9175a(CharSequence charSequence) {
        String charSequence2 = charSequence.toString();
        if (!charSequence2.contains("<link>") || !charSequence2.contains("</link>")) {
            TextMessageWithLinkPreference.super.a(charSequence);
            return;
        }
        TextMessageWithLinkPreference.super.a(uK3.a(charSequence2, new uK3.a[]{new uK3.a("<link>", "</link>", new tK3(c().getResources(), new Km2(this)))}));
    }

    /* renamed from: a */
    public void mo9012a(Ld ld) {
        ((LinearLayout) ld.findViewById(ox0.split_summary_wrapper)).removeAllViews();
        super.mo9012a(ld);
        mo9174a((TextView) ld.findViewById(16908304));
        if (Build.VERSION.SDK_INT >= 24 && EE2.a()) {
            LinearLayout linearLayout = (LinearLayout) ld.findViewById(ox0.split_summary_wrapper);
            ((TextView) ld.findViewById(16908304)).setText(BuildConfig.FLAVOR);
            BreakIterator sentenceInstance = BreakIterator.getSentenceInstance(c().getResources().getConfiguration().getLocales().get(0));
            sentenceInstance.setText(s().toString());
            int first = sentenceInstance.first();
            int next = sentenceInstance.next();
            while (true) {
                int i = next;
                int i2 = first;
                first = i;
                if (first != -1) {
                    CharSequence subSequence = s().subSequence(i2, first);
                    TextView textView = (TextView) LayoutInflater.from(c()).inflate(rx0.text_message_with_link_preference_split_summary_item, linearLayout, false);
                    textView.setText(subSequence, TextView.BufferType.SPANNABLE);
                    mo9174a(textView);
                    linearLayout.addView(textView);
                    next = sentenceInstance.next();
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo9174a(TextView textView) {
        ClickableSpan[] e;
        if (!(textView instanceof TextViewWithClickableSpans) || (e = ((TextViewWithClickableSpans) textView).e()) == null || e.length != 0) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            if (this.f2192a4) {
                I9.a.a(textView, new a(this));
            }
        }
    }
}
