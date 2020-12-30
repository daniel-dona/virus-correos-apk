package com.facebook.react.views.text;

import java.util.Map;

@Ay(name = "RCTText")
/* compiled from: PG */
public class ReactTextViewManager extends ReactTextAnchorViewManager<ReactTextView, LD> {
    public static final String REACT_CLASS = "RCTText";

    public Map getExportedCustomDirectEventTypeConstants() {
        return hy.a("topTextLayout", hy.a("registrationName", "onTextLayout"));
    }

    public String getName() {
        return REACT_CLASS;
    }

    public Class<LD> getShadowNodeClass() {
        return LD.class;
    }

    /* JADX WARNING: type inference failed for: r10v10, types: [android.text.BoringLayout] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long measure(com.facebook.react.bridge.ReactContext r9, com.facebook.react.bridge.ReadableNativeMap r10, com.facebook.react.bridge.ReadableNativeMap r11, float r12, com.facebook.yoga.YogaMeasureMode r13, float r14, com.facebook.yoga.YogaMeasureMode r15) {
        /*
            r8 = this;
            android.text.TextPaint r2 = TD.a
            android.text.Spannable r1 = TD.a(r9, r10)
            if (r1 == 0) goto L_0x00f9
            android.text.BoringLayout$Metrics r6 = android.text.BoringLayout.isBoring(r1, r2)
            if (r6 != 0) goto L_0x0013
            float r9 = android.text.Layout.getDesiredWidth(r1, r2)
            goto L_0x0015
        L_0x0013:
            r9 = 2143289344(0x7fc00000, float:NaN)
        L_0x0015:
            com.facebook.yoga.YogaMeasureMode r10 = com.facebook.yoga.YogaMeasureMode.UNDEFINED
            r14 = 0
            r15 = 1
            r0 = 0
            if (r13 == r10) goto L_0x0023
            int r10 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r10 >= 0) goto L_0x0021
            goto L_0x0023
        L_0x0021:
            r10 = 0
            goto L_0x0024
        L_0x0023:
            r10 = 1
        L_0x0024:
            r13 = 1065353216(0x3f800000, float:1.0)
            r3 = 23
            if (r6 != 0) goto L_0x0072
            if (r10 != 0) goto L_0x0036
            boolean r4 = eF.a(r9)
            if (r4 != 0) goto L_0x0072
            int r4 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r4 > 0) goto L_0x0072
        L_0x0036:
            double r9 = (double) r9
            double r9 = java.lang.Math.ceil(r9)
            int r9 = (int) r9
            int r10 = android.os.Build.VERSION.SDK_INT
            if (r10 >= r3) goto L_0x004f
            android.text.StaticLayout r10 = new android.text.StaticLayout
            android.text.Layout$Alignment r4 = android.text.Layout.Alignment.ALIGN_NORMAL
            r5 = 1065353216(0x3f800000, float:1.0)
            r6 = 0
            r7 = 1
            r0 = r10
            r3 = r9
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            goto L_0x00c3
        L_0x004f:
            int r10 = r1.length()
            android.text.StaticLayout$Builder r9 = android.text.StaticLayout.Builder.obtain(r1, r0, r10, r2, r9)
            android.text.Layout$Alignment r10 = android.text.Layout.Alignment.ALIGN_NORMAL
            android.text.StaticLayout$Builder r9 = r9.setAlignment(r10)
            android.text.StaticLayout$Builder r9 = r9.setLineSpacing(r14, r13)
            android.text.StaticLayout$Builder r9 = r9.setIncludePad(r15)
            android.text.StaticLayout$Builder r9 = r9.setBreakStrategy(r15)
            android.text.StaticLayout$Builder r9 = r9.setHyphenationFrequency(r15)
            android.text.StaticLayout r10 = r9.build()
            goto L_0x00c3
        L_0x0072:
            if (r6 == 0) goto L_0x008d
            if (r10 != 0) goto L_0x007d
            int r9 = r6.width
            float r9 = (float) r9
            int r9 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r9 > 0) goto L_0x008d
        L_0x007d:
            int r9 = r6.width
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_NORMAL
            r4 = 1065353216(0x3f800000, float:1.0)
            r5 = 0
            r7 = 1
            r0 = r1
            r1 = r2
            r2 = r9
            android.text.BoringLayout r10 = android.text.BoringLayout.make(r0, r1, r2, r3, r4, r5, r6, r7)
            goto L_0x00c3
        L_0x008d:
            int r9 = android.os.Build.VERSION.SDK_INT
            if (r9 >= r3) goto L_0x00a0
            android.text.StaticLayout r9 = new android.text.StaticLayout
            int r3 = (int) r12
            android.text.Layout$Alignment r4 = android.text.Layout.Alignment.ALIGN_NORMAL
            r5 = 1065353216(0x3f800000, float:1.0)
            r6 = 0
            r7 = 1
            r0 = r9
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            r10 = r9
            goto L_0x00c3
        L_0x00a0:
            int r9 = r1.length()
            int r10 = (int) r12
            android.text.StaticLayout$Builder r9 = android.text.StaticLayout.Builder.obtain(r1, r0, r9, r2, r10)
            android.text.Layout$Alignment r10 = android.text.Layout.Alignment.ALIGN_NORMAL
            android.text.StaticLayout$Builder r9 = r9.setAlignment(r10)
            android.text.StaticLayout$Builder r9 = r9.setLineSpacing(r14, r13)
            android.text.StaticLayout$Builder r9 = r9.setIncludePad(r15)
            android.text.StaticLayout$Builder r9 = r9.setBreakStrategy(r15)
            android.text.StaticLayout$Builder r9 = r9.setHyphenationFrequency(r15)
            android.text.StaticLayout r10 = r9.build()
        L_0x00c3:
            java.lang.String r9 = "maximumNumberOfLines"
            boolean r12 = r11.hasKey(r9)
            r13 = -1
            if (r12 == 0) goto L_0x00d1
            int r9 = r11.getInt(r9)
            goto L_0x00d2
        L_0x00d1:
            r9 = -1
        L_0x00d2:
            int r11 = r10.getWidth()
            float r11 = (float) r11
            if (r9 == r13) goto L_0x00e7
            if (r9 == 0) goto L_0x00e7
            int r12 = r10.getLineCount()
            if (r9 >= r12) goto L_0x00e7
            int r9 = r9 - r15
            int r9 = r10.getLineBottom(r9)
            goto L_0x00eb
        L_0x00e7:
            int r9 = r10.getHeight()
        L_0x00eb:
            float r9 = (float) r9
            float r10 = GA.d(r11)
            float r9 = GA.d(r9)
            long r9 = fF.a(r10, r9)
            return r9
        L_0x00f9:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "Spannable element has not been prepared in onBeforeLayout"
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactTextViewManager.measure(com.facebook.react.bridge.ReactContext, com.facebook.react.bridge.ReadableNativeMap, com.facebook.react.bridge.ReadableNativeMap, float, com.facebook.yoga.YogaMeasureMode, float, com.facebook.yoga.YogaMeasureMode):long");
    }

    public LD createShadowNodeInstance() {
        return new LD();
    }

    public ReactTextView createViewInstance(WA wa) {
        return new ReactTextView(wa);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.facebook.react.views.text.ReactTextView, android.view.View] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAfterUpdateTransaction(com.facebook.react.views.text.ReactTextView r1) {
        /*
            r0 = this;
            super.onAfterUpdateTransaction(r1)
            r1.mo1526a()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactTextViewManager.onAfterUpdateTransaction(com.facebook.react.views.text.ReactTextView):void");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.widget.TextView, com.facebook.react.views.text.ReactTextView] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateExtraData(com.facebook.react.views.text.ReactTextView r2, java.lang.Object r3) {
        /*
            r1 = this;
            MD r3 = (MD) r3
            boolean r0 = r3.c
            if (r0 == 0) goto L_0x000b
            android.text.Spannable r0 = r3.a
            RD.a(r0, r2)
        L_0x000b:
            r2.setText(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactTextViewManager.updateExtraData(com.facebook.react.views.text.ReactTextView, java.lang.Object):void");
    }

    /* JADX WARNING: type inference failed for: r13v0, types: [android.widget.TextView, com.facebook.react.views.text.ReactTextView] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object updateLocalData(com.facebook.react.views.text.ReactTextView r13, PA r14, PA r15) {
        /*
            r12 = this;
            com.facebook.react.bridge.ReadableMap r15 = r15.a
            java.lang.String r0 = "attributedString"
            com.facebook.react.bridge.ReadableMap r15 = r15.getMap(r0)
            android.content.Context r0 = r13.getContext()
            android.text.Spannable r2 = TD.a(r0, r15)
            r13.setSpanned(r2)
            PD r13 = new PD
            r13.<init>(r14)
            r10 = 1
            r11 = 0
            MD r14 = new MD
            r3 = -1
            r4 = 0
            java.lang.String r15 = "paddingStart"
            float r5 = r13.a(r15)
            java.lang.String r15 = "paddingTop"
            float r6 = r13.a(r15)
            java.lang.String r15 = "paddingEnd"
            float r7 = r13.a(r15)
            java.lang.String r15 = "paddingBottom"
            float r8 = r13.a(r15)
            int r13 = r13.l
            com.facebook.yoga.YogaDirection r15 = com.facebook.yoga.YogaDirection.LTR
            com.facebook.yoga.YogaDirection r0 = com.facebook.yoga.YogaDirection.RTL
            r1 = 8388613(0x800005, float:1.175495E-38)
            r9 = 8388611(0x800003, float:1.1754948E-38)
            if (r15 != r0) goto L_0x004d
            if (r13 != r1) goto L_0x0047
            goto L_0x004e
        L_0x0047:
            if (r13 != r9) goto L_0x004d
            r9 = 8388613(0x800005, float:1.175495E-38)
            goto L_0x004e
        L_0x004d:
            r9 = r13
        L_0x004e:
            r1 = r14
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactTextViewManager.updateLocalData(com.facebook.react.views.text.ReactTextView, PA, PA):java.lang.Object");
    }
}
