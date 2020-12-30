package com.facebook.react.views.picker;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

/* compiled from: PG */
public class ReactPicker extends AppCompatSpinner {

    /* renamed from: q3 */
    public Integer f616q3;

    /* renamed from: r3 */
    public final AdapterView.OnItemSelectedListener f617r3 = new C0154a();

    /* renamed from: s3 */
    public final Runnable f618s3 = new C0155b();

    /* renamed from: x */
    public Integer f619x;

    /* renamed from: y */
    public OnSelectListener f620y;

    /* compiled from: PG */
    public interface OnSelectListener {
        void onItemSelected(int i);
    }

    /* renamed from: com.facebook.react.views.picker.ReactPicker$a */
    /* compiled from: PG */
    public class C0154a implements AdapterView.OnItemSelectedListener {
        public C0154a() {
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            OnSelectListener onSelectListener = ReactPicker.this.f620y;
            if (onSelectListener != null) {
                onSelectListener.onItemSelected(i);
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
            OnSelectListener onSelectListener = ReactPicker.this.f620y;
            if (onSelectListener != null) {
                onSelectListener.onItemSelected(-1);
            }
        }
    }

    /* renamed from: com.facebook.react.views.picker.ReactPicker$b */
    /* compiled from: PG */
    public class C0155b implements Runnable {
        public C0155b() {
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [com.facebook.react.views.picker.ReactPicker, android.widget.Spinner] */
        /* JADX WARNING: type inference failed for: r3v0, types: [com.facebook.react.views.picker.ReactPicker, android.widget.Spinner] */
        /* JADX WARNING: type inference failed for: r0v1, types: [com.facebook.react.views.picker.ReactPicker, android.widget.Spinner] */
        /* JADX WARNING: type inference failed for: r2v2, types: [com.facebook.react.views.picker.ReactPicker, android.widget.Spinner] */
        /* JADX WARNING: type inference failed for: r3v2, types: [com.facebook.react.views.picker.ReactPicker, android.widget.Spinner] */
        /* JADX WARNING: type inference failed for: r4v0, types: [com.facebook.react.views.picker.ReactPicker, android.widget.Spinner] */
        public void run() {
            ? r0 = ReactPicker.this;
            r0.measure(View.MeasureSpec.makeMeasureSpec(r0.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactPicker.this.getHeight(), 1073741824));
            ? r02 = ReactPicker.this;
            r02.layout(r02.getLeft(), ReactPicker.this.getTop(), ReactPicker.this.getRight(), ReactPicker.this.getBottom());
        }
    }

    public ReactPicker(Context context) {
        super(context);
    }

    /* renamed from: a */
    public Integer mo1313a() {
        return this.f619x;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.facebook.react.views.picker.ReactPicker, android.widget.Spinner] */
    /* renamed from: b */
    public void mo1314b() {
        Integer num = this.f616q3;
        if (num != null) {
            int intValue = num.intValue();
            if (intValue != getSelectedItemPosition()) {
                setOnItemSelectedListener((AdapterView.OnItemSelectedListener) null);
                setSelection(intValue, false);
                setOnItemSelectedListener(this.f617r3);
            }
            this.f616q3 = null;
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.facebook.react.views.picker.ReactPicker, android.widget.Spinner] */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ReactPicker.super.onLayout(z, i, i2, i3, i4);
        if (getOnItemSelectedListener() == null) {
            setOnItemSelectedListener(this.f617r3);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.facebook.react.views.picker.ReactPicker, android.widget.Spinner] */
    public void requestLayout() {
        ReactPicker.super.requestLayout();
        post(this.f618s3);
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.f620y = onSelectListener;
    }

    public void setPrimaryColor(Integer num) {
        this.f619x = num;
    }

    public void setStagedSelection(int i) {
        this.f616q3 = Integer.valueOf(i);
    }

    public ReactPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ReactPicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ReactPicker(Context context, int i) {
        super(context, (AttributeSet) null, hx0.spinnerStyle, i);
    }
}
