package com.facebook.react.views.picker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.views.picker.ReactPicker;

/* compiled from: PG */
public abstract class ReactPickerManager extends SimpleViewManager<ReactPicker> {

    /* renamed from: com.facebook.react.views.picker.ReactPickerManager$a */
    /* compiled from: PG */
    public static class C0156a implements ReactPicker.OnSelectListener {

        /* renamed from: a */
        public final ReactPicker f623a;

        /* renamed from: b */
        public final qC f624b;

        public C0156a(ReactPicker reactPicker, qC qCVar) {
            this.f623a = reactPicker;
            this.f624b = qCVar;
        }

        /* JADX WARNING: type inference failed for: r2v0, types: [com.facebook.react.views.picker.ReactPicker, android.widget.Spinner] */
        public void onItemSelected(int i) {
            this.f624b.b(new kD(this.f623a.getId(), i));
        }
    }

    /* renamed from: com.facebook.react.views.picker.ReactPickerManager$b */
    /* compiled from: PG */
    public static class C0157b extends ArrayAdapter<ReadableMap> {

        /* renamed from: a */
        public final LayoutInflater f625a;

        /* renamed from: b */
        public Integer f626b;

        public C0157b(Context context, ReadableMap[] readableMapArr) {
            super(context, 0, readableMapArr);
            Object systemService = context.getSystemService("layout_inflater");
            Kw.a(systemService);
            this.f625a = (LayoutInflater) systemService;
        }

        /* renamed from: a */
        public final View mo1331a(int i, View view, ViewGroup viewGroup, boolean z) {
            Integer num;
            ReadableMap readableMap = (ReadableMap) getItem(i);
            if (view == null) {
                view = this.f625a.inflate(z ? 17367049 : 17367048, viewGroup, false);
            }
            TextView textView = (TextView) view;
            textView.setText(readableMap.getString("label"));
            if (!z && (num = this.f626b) != null) {
                textView.setTextColor(num.intValue());
            } else if (readableMap.hasKey("color") && !readableMap.isNull("color")) {
                textView.setTextColor(readableMap.getInt("color"));
            }
            return view;
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            return mo1331a(i, view, viewGroup, true);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return mo1331a(i, view, viewGroup, false);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.facebook.react.views.picker.ReactPicker, android.widget.Spinner] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @eC(customType = "Color", name = "color")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setColor(com.facebook.react.views.picker.ReactPicker r1, java.lang.Integer r2) {
        /*
            r0 = this;
            r1.setPrimaryColor(r2)
            android.widget.SpinnerAdapter r1 = r1.getAdapter()
            com.facebook.react.views.picker.ReactPickerManager$b r1 = (com.facebook.react.views.picker.ReactPickerManager.C0157b) r1
            if (r1 == 0) goto L_0x0010
            r1.f626b = r2
            r1.notifyDataSetChanged()
        L_0x0010:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.picker.ReactPickerManager.setColor(com.facebook.react.views.picker.ReactPicker, java.lang.Integer):void");
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.facebook.react.views.picker.ReactPicker, android.widget.Spinner] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @eC(defaultBoolean = true, name = "enabled")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setEnabled(com.facebook.react.views.picker.ReactPicker r1, boolean r2) {
        /*
            r0 = this;
            r1.setEnabled(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.picker.ReactPickerManager.setEnabled(com.facebook.react.views.picker.ReactPicker, boolean):void");
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.facebook.react.views.picker.ReactPicker, android.support.v7.widget.AppCompatSpinner, android.widget.Spinner] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @eC(name = "items")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setItems(com.facebook.react.views.picker.ReactPicker r4, com.facebook.react.bridge.ReadableArray r5) {
        /*
            r3 = this;
            if (r5 == 0) goto L_0x002e
            int r0 = r5.size()
            com.facebook.react.bridge.ReadableMap[] r0 = new com.facebook.react.bridge.ReadableMap[r0]
            r1 = 0
        L_0x0009:
            int r2 = r5.size()
            if (r1 >= r2) goto L_0x0018
            com.facebook.react.bridge.ReadableMap r2 = r5.getMap(r1)
            r0[r1] = r2
            int r1 = r1 + 1
            goto L_0x0009
        L_0x0018:
            com.facebook.react.views.picker.ReactPickerManager$b r5 = new com.facebook.react.views.picker.ReactPickerManager$b
            android.content.Context r1 = r4.getContext()
            r5.<init>(r1, r0)
            java.lang.Integer r0 = r4.mo1313a()
            r5.f626b = r0
            r5.notifyDataSetChanged()
            r4.setAdapter(r5)
            goto L_0x0032
        L_0x002e:
            r5 = 0
            r4.setAdapter(r5)
        L_0x0032:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.picker.ReactPickerManager.setItems(com.facebook.react.views.picker.ReactPicker, com.facebook.react.bridge.ReadableArray):void");
    }

    @eC(name = "prompt")
    public void setPrompt(ReactPicker reactPicker, String str) {
        reactPicker.setPrompt(str);
    }

    @eC(name = "selected")
    public void setSelected(ReactPicker reactPicker, int i) {
        reactPicker.setStagedSelection(i);
    }

    public void addEventEmitters(WA wa, ReactPicker reactPicker) {
        reactPicker.setOnSelectListener(new C0156a(reactPicker, ((UIManagerModule) wa.getNativeModule(UIManagerModule.class)).getEventDispatcher()));
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.view.View, com.facebook.react.views.picker.ReactPicker] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAfterUpdateTransaction(com.facebook.react.views.picker.ReactPicker r1) {
        /*
            r0 = this;
            super.onAfterUpdateTransaction(r1)
            r1.mo1314b()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.picker.ReactPickerManager.onAfterUpdateTransaction(com.facebook.react.views.picker.ReactPicker):void");
    }
}
