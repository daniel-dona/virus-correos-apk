package com.facebook.react.uimanager;

import android.graphics.Paint;
import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.util.ReactFindViewUtil;
import com.microsoft.bing.visualsearch.camerasearchv2.content.model.OCRItem$OCRActionType;
import xA;
import yA;

/* compiled from: PG */
public abstract class BaseViewManager<T extends View, C extends xA> extends ViewManager<T, C> {
    public static final float CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER = ((float) Math.sqrt(5.0d));
    public static final int PERSPECTIVE_ARRAY_INVERTED_CAMERA_DISTANCE_INDEX = 2;
    public static final String PROP_ACCESSIBILITY_COMPONENT_TYPE = "accessibilityComponentType";
    public static final String PROP_ACCESSIBILITY_HINT = "accessibilityHint";
    public static final String PROP_ACCESSIBILITY_LABEL = "accessibilityLabel";
    public static final String PROP_ACCESSIBILITY_LIVE_REGION = "accessibilityLiveRegion";
    public static final String PROP_ACCESSIBILITY_ROLE = "accessibilityRole";
    public static final String PROP_ACCESSIBILITY_STATES = "accessibilityStates";
    public static final String PROP_BACKGROUND_COLOR = "backgroundColor";
    public static final String PROP_ELEVATION = "elevation";
    public static final String PROP_IMPORTANT_FOR_ACCESSIBILITY = "importantForAccessibility";
    public static final String PROP_NATIVE_ID = "nativeID";
    public static final String PROP_RENDER_TO_HARDWARE_TEXTURE = "renderToHardwareTextureAndroid";
    public static final String PROP_ROTATION = "rotation";
    public static final String PROP_SCALE_X = "scaleX";
    public static final String PROP_SCALE_Y = "scaleY";
    public static final String PROP_TEST_ID = "testID";
    public static final String PROP_TRANSFORM = "transform";
    public static final String PROP_TRANSLATE_X = "translateX";
    public static final String PROP_TRANSLATE_Y = "translateY";
    public static final String PROP_Z_INDEX = "zIndex";
    public static yA.a sMatrixDecompositionContext = new yA.a();
    public static double[] sTransformDecompositionArray = new double[16];

    public static void resetTransformProperty(View view) {
        view.setTranslationX(GA.b(CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER));
        view.setTranslationY(GA.b(CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER));
        view.setRotation(CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        view.setRotationX(CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        view.setRotationY(CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setCameraDistance(CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00c5 A[LOOP:2: B:28:0x00c3->B:29:0x00c5, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00db A[LOOP:3: B:31:0x00d9->B:32:0x00db, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x01c2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setTransformProperty(android.view.View r26, com.facebook.react.bridge.ReadableArray r27) {
        /*
            r0 = r26
            double[] r1 = sTransformDecompositionArray
            r2 = r27
            ZA.a(r2, r1)
            double[] r1 = sTransformDecompositionArray
            yA$a r2 = sMatrixDecompositionContext
            java.lang.Class<double> r3 = double.class
            int r4 = r1.length
            r5 = 16
            r7 = 0
            if (r4 != r5) goto L_0x0017
            r4 = 1
            goto L_0x0018
        L_0x0017:
            r4 = 0
        L_0x0018:
            Kw.a(r4)
            double[] r4 = r2.a
            double[] r8 = r2.b
            double[] r9 = r2.c
            double[] r10 = r2.d
            double[] r2 = r2.e
            r11 = 15
            r12 = r1[r11]
            boolean r12 = yA.a(r12)
            if (r12 == 0) goto L_0x0031
            goto L_0x0249
        L_0x0031:
            r12 = 4
            int[] r14 = new int[]{r12, r12}
            java.lang.Object r14 = java.lang.reflect.Array.newInstance(r3, r14)
            double[][] r14 = (double[][]) r14
            double[] r5 = new double[r5]
            r15 = 0
        L_0x003f:
            r13 = 3
            r16 = 0
            if (r15 >= r12) goto L_0x0061
            r6 = 0
        L_0x0045:
            if (r6 >= r12) goto L_0x005e
            int r19 = r15 * 4
            int r19 = r19 + r6
            r20 = r1[r19]
            r22 = r1[r11]
            double r20 = r20 / r22
            r22 = r14[r15]
            r22[r6] = r20
            if (r6 != r13) goto L_0x0059
            r20 = r16
        L_0x0059:
            r5[r19] = r20
            int r6 = r6 + 1
            goto L_0x0045
        L_0x005e:
            int r15 = r15 + 1
            goto L_0x003f
        L_0x0061:
            r19 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r5[r11] = r19
            double r21 = yA.a(r5)
            boolean r1 = yA.a(r21)
            if (r1 == 0) goto L_0x0071
            goto L_0x0249
        L_0x0071:
            r1 = r14[r7]
            r21 = r1[r13]
            boolean r1 = yA.a(r21)
            if (r1 == 0) goto L_0x009b
            r1 = 1
            r6 = r14[r1]
            r21 = r6[r13]
            boolean r6 = yA.a(r21)
            if (r6 == 0) goto L_0x009c
            r6 = 2
            r11 = r14[r6]
            r21 = r11[r13]
            boolean r11 = yA.a(r21)
            if (r11 != 0) goto L_0x0092
            goto L_0x009c
        L_0x0092:
            r4[r6] = r16
            r4[r1] = r16
            r4[r7] = r16
            r4[r13] = r19
            goto L_0x00c2
        L_0x009b:
            r1 = 1
        L_0x009c:
            double[] r6 = new double[r12]
            r11 = r14[r7]
            r18 = r11[r13]
            r6[r7] = r18
            r11 = r14[r1]
            r18 = r11[r13]
            r6[r1] = r18
            r1 = 2
            r11 = r14[r1]
            r19 = r11[r13]
            r6[r1] = r19
            r1 = r14[r13]
            r11 = r1[r13]
            r6[r13] = r11
            double[] r1 = yA.b(r5)
            double[] r1 = yA.d(r1)
            yA.a(r6, r1, r4)
        L_0x00c2:
            r1 = 0
        L_0x00c3:
            if (r1 >= r13) goto L_0x00ce
            r4 = r14[r13]
            r5 = r4[r1]
            r10[r1] = r5
            int r1 = r1 + 1
            goto L_0x00c3
        L_0x00ce:
            int[] r1 = new int[]{r13, r13}
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r3, r1)
            double[][] r1 = (double[][]) r1
            r3 = 0
        L_0x00d9:
            if (r3 >= r13) goto L_0x00f8
            r4 = r1[r3]
            r5 = r14[r3]
            r10 = r5[r7]
            r4[r7] = r10
            r4 = r1[r3]
            r5 = r14[r3]
            r6 = 1
            r10 = r5[r6]
            r4[r6] = r10
            r4 = r1[r3]
            r5 = r14[r3]
            r6 = 2
            r10 = r5[r6]
            r4[r6] = r10
            int r3 = r3 + 1
            goto L_0x00d9
        L_0x00f8:
            r3 = r1[r7]
            double r3 = yA.e(r3)
            r8[r7] = r3
            r3 = r1[r7]
            r4 = r8[r7]
            double[] r3 = yA.a(r3, r4)
            r1[r7] = r3
            r3 = r1[r7]
            r4 = 1
            r5 = r1[r4]
            double r5 = yA.b(r3, r5)
            r9[r7] = r5
            r18 = r1[r4]
            r19 = r1[r7]
            r5 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r10 = r9[r7]
            double r10 = -r10
            r20 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r22 = r10
            double[] r3 = yA.a(r18, r19, r20, r22)
            r1[r4] = r3
            r3 = r1[r7]
            r10 = r1[r4]
            double r10 = yA.b(r3, r10)
            r9[r7] = r10
            r18 = r1[r4]
            r19 = r1[r7]
            r10 = r9[r7]
            double r10 = -r10
            r22 = r10
            double[] r3 = yA.a(r18, r19, r20, r22)
            r1[r4] = r3
            r3 = r1[r4]
            double r10 = yA.e(r3)
            r8[r4] = r10
            r3 = r1[r4]
            r10 = r8[r4]
            double[] r3 = yA.a(r3, r10)
            r1[r4] = r3
            r10 = r9[r7]
            r14 = r8[r4]
            double r10 = r10 / r14
            r9[r7] = r10
            r3 = r1[r7]
            r10 = 2
            r11 = r1[r10]
            double r11 = yA.b(r3, r11)
            r9[r4] = r11
            r18 = r1[r10]
            r19 = r1[r7]
            r11 = r9[r4]
            double r11 = -r11
            r22 = r11
            double[] r3 = yA.a(r18, r19, r20, r22)
            r1[r10] = r3
            r3 = r1[r4]
            r11 = r1[r10]
            double r11 = yA.b(r3, r11)
            r9[r10] = r11
            r20 = r1[r10]
            r21 = r1[r4]
            r3 = r9[r10]
            double r3 = -r3
            r22 = r5
            r24 = r3
            double[] r3 = yA.a(r20, r21, r22, r24)
            r1[r10] = r3
            r3 = r1[r10]
            double r3 = yA.e(r3)
            r8[r10] = r3
            r3 = r1[r10]
            r4 = r8[r10]
            double[] r3 = yA.a(r3, r4)
            r1[r10] = r3
            r3 = 1
            r4 = r9[r3]
            r11 = r8[r10]
            double r4 = r4 / r11
            r9[r3] = r4
            r4 = r9[r10]
            r11 = r8[r10]
            double r4 = r4 / r11
            r9[r10] = r4
            r4 = r1[r3]
            r3 = r1[r10]
            double[] r3 = yA.a(r4, r3)
            r4 = r1[r7]
            double r3 = yA.b(r4, r3)
            int r5 = (r3 > r16 ? 1 : (r3 == r16 ? 0 : -1))
            if (r5 >= 0) goto L_0x01ea
            r3 = 0
        L_0x01c3:
            if (r3 >= r13) goto L_0x01ea
            r4 = r8[r3]
            r9 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            double r4 = r4 * r9
            r8[r3] = r4
            r4 = r1[r3]
            r5 = r4[r7]
            double r5 = r5 * r9
            r4[r7] = r5
            r4 = r1[r3]
            r5 = 1
            r11 = r4[r5]
            double r11 = r11 * r9
            r4[r5] = r11
            r4 = r1[r3]
            r5 = 2
            r11 = r4[r5]
            double r11 = r11 * r9
            r4[r5] = r11
            int r3 = r3 + 1
            goto L_0x01c3
        L_0x01ea:
            r5 = 2
            r3 = 4633260481411531256(0x404ca5dc1a63c1f8, double:57.29577951308232)
            r6 = r1[r5]
            r8 = 1
            r9 = r6[r8]
            r6 = r1[r5]
            r11 = r6[r5]
            double r8 = java.lang.Math.atan2(r9, r11)
            double r8 = -r8
            double r8 = r8 * r3
            double r8 = yA.b(r8)
            r2[r7] = r8
            r6 = r1[r5]
            r8 = r6[r7]
            double r8 = -r8
            r6 = r1[r5]
            r10 = 1
            r11 = r6[r10]
            r6 = r1[r5]
            r13 = r6[r10]
            double r11 = r11 * r13
            r6 = r1[r5]
            r13 = r6[r5]
            r6 = r1[r5]
            r15 = r6[r5]
            double r13 = r13 * r15
            double r13 = r13 + r11
            double r5 = java.lang.Math.sqrt(r13)
            double r5 = java.lang.Math.atan2(r8, r5)
            double r5 = -r5
            double r5 = r5 * r3
            double r5 = yA.b(r5)
            r8 = 1
            r2[r8] = r5
            r5 = r1[r8]
            r8 = r5[r7]
            r1 = r1[r7]
            r5 = r1[r7]
            double r5 = java.lang.Math.atan2(r8, r5)
            double r5 = -r5
            double r5 = r5 * r3
            double r3 = yA.b(r5)
            r1 = 2
            r2[r1] = r3
        L_0x0249:
            yA$a r1 = sMatrixDecompositionContext
            double[] r1 = r1.d
            r2 = r1[r7]
            float r1 = (float) r2
            float r1 = GA.b(r1)
            r0.setTranslationX(r1)
            yA$a r1 = sMatrixDecompositionContext
            double[] r1 = r1.d
            r2 = 1
            r3 = r1[r2]
            float r1 = (float) r3
            float r1 = GA.b(r1)
            r0.setTranslationY(r1)
            yA$a r1 = sMatrixDecompositionContext
            double[] r1 = r1.e
            r2 = 2
            r3 = r1[r2]
            float r1 = (float) r3
            r0.setRotation(r1)
            yA$a r1 = sMatrixDecompositionContext
            double[] r1 = r1.e
            r2 = r1[r7]
            float r1 = (float) r2
            r0.setRotationX(r1)
            yA$a r1 = sMatrixDecompositionContext
            double[] r1 = r1.e
            r2 = 1
            r3 = r1[r2]
            float r1 = (float) r3
            r0.setRotationY(r1)
            yA$a r1 = sMatrixDecompositionContext
            double[] r1 = r1.b
            r3 = r1[r7]
            float r1 = (float) r3
            r0.setScaleX(r1)
            yA$a r1 = sMatrixDecompositionContext
            double[] r1 = r1.b
            r2 = r1[r2]
            float r1 = (float) r2
            r0.setScaleY(r1)
            yA$a r1 = sMatrixDecompositionContext
            double[] r1 = r1.a
            int r2 = r1.length
            r3 = 2
            if (r2 <= r3) goto L_0x02bf
            r2 = r1[r3]
            float r1 = (float) r2
            r2 = 0
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 != 0) goto L_0x02ad
            r1 = 978111693(0x3a4ccccd, float:7.8125E-4)
        L_0x02ad:
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r2 = r2 / r1
            android.util.DisplayMetrics r1 = rA.b
            float r1 = r1.density
            float r1 = r1 * r1
            float r1 = r1 * r2
            float r2 = CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER
            float r1 = r1 * r2
            r0.setCameraDistance(r1)
        L_0x02bf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.BaseViewManager.setTransformProperty(android.view.View, com.facebook.react.bridge.ReadableArray):void");
    }

    private void updateViewAccessibility(T t) {
        String str = (String) t.getTag(ox0.accessibility_hint);
        AccessibilityDelegateUtil$AccessibilityRole accessibilityDelegateUtil$AccessibilityRole = (AccessibilityDelegateUtil$AccessibilityRole) t.getTag(ox0.accessibility_role);
        if (I9.n(t)) {
            return;
        }
        if (str != null || accessibilityDelegateUtil$AccessibilityRole != null) {
            I9.a.a(t, new mA(str, accessibilityDelegateUtil$AccessibilityRole, t));
        }
    }

    public void onAfterUpdateTransaction(T t) {
        super.onAfterUpdateTransaction(t);
        updateViewAccessibility(t);
    }

    @eC(name = "accessibilityComponentType")
    public void setAccessibilityComponentType(T t, String str) {
        qA.a(t, str);
    }

    @eC(name = "accessibilityHint")
    public void setAccessibilityHint(T t, String str) {
        t.setTag(ox0.accessibility_hint, str);
    }

    @eC(name = "accessibilityLabel")
    public void setAccessibilityLabel(T t, String str) {
        t.setContentDescription(str);
    }

    @eC(name = "accessibilityLiveRegion")
    public void setAccessibilityLiveRegion(T t, String str) {
        if (str == null || str.equals(OCRItem$OCRActionType.OCR_NONE)) {
            I9.a.c(t, 0);
        } else if (str.equals("polite")) {
            I9.a.c(t, 1);
        } else if (str.equals("assertive")) {
            I9.a.c(t, 2);
        }
    }

    @eC(name = "accessibilityRole")
    public void setAccessibilityRole(T t, String str) {
        if (str != null) {
            t.setTag(ox0.accessibility_role, AccessibilityDelegateUtil$AccessibilityRole.fromValue(str));
        }
    }

    @eC(customType = "Color", defaultInt = 0, name = "backgroundColor")
    public void setBackgroundColor(T t, int i) {
        t.setBackgroundColor(i);
    }

    @eC(name = "elevation")
    public void setElevation(T t, float f) {
        I9.a.a(t, GA.b(f));
    }

    @eC(name = "importantForAccessibility")
    public void setImportantForAccessibility(T t, String str) {
        if (str == null || str.equals("auto")) {
            I9.a.d(t, 0);
        } else if (str.equals("yes")) {
            I9.a.d(t, 1);
        } else if (str.equals("no")) {
            I9.a.d(t, 2);
        } else if (str.equals("no-hide-descendants")) {
            I9.a.d(t, 4);
        }
    }

    @eC(name = "nativeID")
    public void setNativeId(T t, String str) {
        t.setTag(ox0.view_tag_native_id, str);
        ReactFindViewUtil.m540a(t);
    }

    @eC(defaultFloat = 1.0f, name = "opacity")
    public void setOpacity(T t, float f) {
        t.setAlpha(f);
    }

    @eC(name = "renderToHardwareTextureAndroid")
    public void setRenderToHardwareTexture(T t, boolean z) {
        t.setLayerType(z ? 2 : 0, (Paint) null);
    }

    @eC(name = "rotation")
    @Deprecated
    public void setRotation(T t, float f) {
        t.setRotation(f);
    }

    @eC(defaultFloat = 1.0f, name = "scaleX")
    @Deprecated
    public void setScaleX(T t, float f) {
        t.setScaleX(f);
    }

    @eC(defaultFloat = 1.0f, name = "scaleY")
    @Deprecated
    public void setScaleY(T t, float f) {
        t.setScaleY(f);
    }

    @eC(name = "testID")
    public void setTestId(T t, String str) {
        t.setTag(ox0.react_test_id, str);
        t.setTag(str);
    }

    @eC(name = "transform")
    public void setTransform(T t, ReadableArray readableArray) {
        if (readableArray == null) {
            resetTransformProperty(t);
        } else {
            setTransformProperty(t, readableArray);
        }
    }

    @eC(defaultFloat = 0.0f, name = "translateX")
    @Deprecated
    public void setTranslateX(T t, float f) {
        t.setTranslationX(GA.b(f));
    }

    @eC(defaultFloat = 0.0f, name = "translateY")
    @Deprecated
    public void setTranslateY(T t, float f) {
        t.setTranslationY(GA.b(f));
    }

    @eC(name = "accessibilityStates")
    public void setViewStates(T t, ReadableArray readableArray) {
        t.setSelected(false);
        t.setEnabled(true);
        if (readableArray != null) {
            for (int i = 0; i < readableArray.size(); i++) {
                String string = readableArray.getString(i);
                if (string.equals("selected")) {
                    t.setSelected(true);
                } else if (string.equals("disabled")) {
                    t.setEnabled(false);
                }
            }
        }
    }

    @eC(name = "zIndex")
    public void setZIndex(T t, float f) {
        ViewGroupManager.setViewZIndex(t, Math.round(f));
        RA parent = t.getParent();
        if (parent != null && (parent instanceof RA)) {
            parent.c();
        }
    }
}
