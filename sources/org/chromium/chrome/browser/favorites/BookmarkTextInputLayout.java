package org.chromium.chrome.browser.favorites;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import org.chromium.chrome.browser.widget.CompatibilityTextInputLayout;

/* compiled from: PG */
public class BookmarkTextInputLayout extends CompatibilityTextInputLayout {

    /* renamed from: U3 */
    public String f1998U3;

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkTextInputLayout$a */
    /* compiled from: PG */
    public class C0418a implements TextWatcher {
        public C0418a() {
        }

        public void afterTextChanged(Editable editable) {
            BookmarkTextInputLayout.this.mo8733l();
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public BookmarkTextInputLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, yx0.BookmarkTextInputLayout);
        int resourceId = obtainStyledAttributes.getResourceId(yx0.BookmarkTextInputLayout_emptyErrorMessage, 0);
        if (resourceId != 0) {
            this.f1998U3 = context.getResources().getString(resourceId);
        }
        obtainStyledAttributes.recycle();
    }

    /* renamed from: j */
    public String mo8731j() {
        return c().getText().toString().trim();
    }

    /* renamed from: k */
    public boolean mo8732k() {
        return TextUtils.isEmpty(mo8731j());
    }

    /* renamed from: l */
    public void mo8733l() {
        if (this.f1998U3 != null) {
            setError(mo8732k() ? this.f1998U3 : null);
        }
    }

    public void onFinishInflate() {
        BookmarkTextInputLayout.super.onFinishInflate();
        c().addTextChangedListener(new C0418a());
    }
}
