package com.facebook.yoga;

import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.soloader.SoLoader;
import java.util.ArrayList;
import java.util.List;

@Qw
/* compiled from: PG */
public class YogaNode implements Cloneable {

    /* renamed from: a */
    public YogaNode f853a;

    /* renamed from: b */
    public List<YogaNode> f854b;

    /* renamed from: c */
    public YogaMeasureFunction f855c;

    /* renamed from: d */
    public YogaBaselineFunction f856d;

    /* renamed from: e */
    public long f857e;
    @Qw
    public float mBorderBottom;
    @Qw
    public float mBorderLeft;
    @Qw
    public float mBorderRight;
    @Qw
    public float mBorderTop;
    @Qw
    public boolean mDoesLegacyStretchFlagAffectsLayout;
    @Qw
    public int mEdgeSetFlag;
    @Qw
    public boolean mHasNewLayout;
    @Qw
    public float mHeight;
    @Qw
    public int mLayoutDirection;
    @Qw
    public float mLeft;
    @Qw
    public float mMarginBottom;
    @Qw
    public float mMarginLeft;
    @Qw
    public float mMarginRight;
    @Qw
    public float mMarginTop;
    @Qw
    public float mPaddingBottom;
    @Qw
    public float mPaddingLeft;
    @Qw
    public float mPaddingRight;
    @Qw
    public float mPaddingTop;
    @Qw
    public float mTop;
    @Qw
    public float mWidth;

    static {
        SoLoader.m690a("yoga");
    }

    public YogaNode() {
        this.mEdgeSetFlag = 0;
        this.mWidth = Float.NaN;
        this.mHeight = Float.NaN;
        this.mTop = Float.NaN;
        this.mLeft = Float.NaN;
        this.mMarginLeft = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mMarginTop = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mMarginRight = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mMarginBottom = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mPaddingLeft = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mPaddingTop = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mPaddingRight = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mPaddingBottom = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mBorderLeft = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mBorderTop = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mBorderRight = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mBorderBottom = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mLayoutDirection = 0;
        this.mHasNewLayout = true;
        this.mDoesLegacyStretchFlagAffectsLayout = false;
        this.f857e = jni_YGNodeNew();
        if (this.f857e == 0) {
            throw new IllegalStateException("Failed to allocate native memory");
        }
    }

    public static native void jni_YGNodeCalculateLayout(long j, float f, float f2);

    public static native void jni_YGNodeClearChildren(long j);

    private native long jni_YGNodeClone(long j, Object obj);

    public static native void jni_YGNodeCopyStyle(long j, long j2);

    public static native void jni_YGNodeFree(long j);

    public static native int jni_YGNodeGetInstanceCount();

    public static native void jni_YGNodeInsertChild(long j, long j2, int i);

    public static native boolean jni_YGNodeIsDirty(long j);

    public static native boolean jni_YGNodeIsReferenceBaseline(long j);

    public static native void jni_YGNodeMarkDirty(long j);

    public static native void jni_YGNodeMarkDirtyAndPropogateToDescendants(long j);

    private native long jni_YGNodeNew();

    private native long jni_YGNodeNewWithConfig(long j);

    public static native void jni_YGNodePrint(long j);

    public static native void jni_YGNodeRemoveChild(long j, long j2);

    public static native void jni_YGNodeReset(long j);

    public static native void jni_YGNodeSetHasBaselineFunc(long j, boolean z);

    public static native void jni_YGNodeSetHasMeasureFunc(long j, boolean z);

    public static native void jni_YGNodeSetIsReferenceBaseline(long j, boolean z);

    public static native void jni_YGNodeSetOwner(long j, long j2);

    public static native int jni_YGNodeStyleGetAlignContent(long j);

    public static native int jni_YGNodeStyleGetAlignItems(long j);

    public static native int jni_YGNodeStyleGetAlignSelf(long j);

    public static native float jni_YGNodeStyleGetAspectRatio(long j);

    public static native float jni_YGNodeStyleGetBorder(long j, int i);

    public static native int jni_YGNodeStyleGetDirection(long j);

    public static native int jni_YGNodeStyleGetDisplay(long j);

    public static native Object jni_YGNodeStyleGetFlexBasis(long j);

    public static native int jni_YGNodeStyleGetFlexDirection(long j);

    public static native float jni_YGNodeStyleGetFlexGrow(long j);

    public static native float jni_YGNodeStyleGetFlexShrink(long j);

    public static native Object jni_YGNodeStyleGetHeight(long j);

    public static native int jni_YGNodeStyleGetJustifyContent(long j);

    public static native Object jni_YGNodeStyleGetMargin(long j, int i);

    public static native Object jni_YGNodeStyleGetMaxHeight(long j);

    public static native Object jni_YGNodeStyleGetMaxWidth(long j);

    public static native Object jni_YGNodeStyleGetMinHeight(long j);

    public static native Object jni_YGNodeStyleGetMinWidth(long j);

    public static native int jni_YGNodeStyleGetOverflow(long j);

    public static native Object jni_YGNodeStyleGetPadding(long j, int i);

    public static native Object jni_YGNodeStyleGetPosition(long j, int i);

    public static native int jni_YGNodeStyleGetPositionType(long j);

    public static native Object jni_YGNodeStyleGetWidth(long j);

    public static native void jni_YGNodeStyleSetAlignContent(long j, int i);

    public static native void jni_YGNodeStyleSetAlignItems(long j, int i);

    public static native void jni_YGNodeStyleSetAlignSelf(long j, int i);

    public static native void jni_YGNodeStyleSetAspectRatio(long j, float f);

    public static native void jni_YGNodeStyleSetBorder(long j, int i, float f);

    public static native void jni_YGNodeStyleSetDirection(long j, int i);

    public static native void jni_YGNodeStyleSetDisplay(long j, int i);

    public static native void jni_YGNodeStyleSetFlex(long j, float f);

    public static native void jni_YGNodeStyleSetFlexBasis(long j, float f);

    public static native void jni_YGNodeStyleSetFlexBasisAuto(long j);

    public static native void jni_YGNodeStyleSetFlexBasisPercent(long j, float f);

    public static native void jni_YGNodeStyleSetFlexDirection(long j, int i);

    public static native void jni_YGNodeStyleSetFlexGrow(long j, float f);

    public static native void jni_YGNodeStyleSetFlexShrink(long j, float f);

    public static native void jni_YGNodeStyleSetFlexWrap(long j, int i);

    public static native void jni_YGNodeStyleSetHeight(long j, float f);

    public static native void jni_YGNodeStyleSetHeightAuto(long j);

    public static native void jni_YGNodeStyleSetHeightPercent(long j, float f);

    public static native void jni_YGNodeStyleSetJustifyContent(long j, int i);

    public static native void jni_YGNodeStyleSetMargin(long j, int i, float f);

    public static native void jni_YGNodeStyleSetMarginAuto(long j, int i);

    public static native void jni_YGNodeStyleSetMarginPercent(long j, int i, float f);

    public static native void jni_YGNodeStyleSetMaxHeight(long j, float f);

    public static native void jni_YGNodeStyleSetMaxHeightPercent(long j, float f);

    public static native void jni_YGNodeStyleSetMaxWidth(long j, float f);

    public static native void jni_YGNodeStyleSetMaxWidthPercent(long j, float f);

    public static native void jni_YGNodeStyleSetMinHeight(long j, float f);

    public static native void jni_YGNodeStyleSetMinHeightPercent(long j, float f);

    public static native void jni_YGNodeStyleSetMinWidth(long j, float f);

    public static native void jni_YGNodeStyleSetMinWidthPercent(long j, float f);

    public static native void jni_YGNodeStyleSetOverflow(long j, int i);

    public static native void jni_YGNodeStyleSetPadding(long j, int i, float f);

    public static native void jni_YGNodeStyleSetPaddingPercent(long j, int i, float f);

    public static native void jni_YGNodeStyleSetPosition(long j, int i, float f);

    public static native void jni_YGNodeStyleSetPositionPercent(long j, int i, float f);

    public static native void jni_YGNodeStyleSetPositionType(long j, int i);

    public static native void jni_YGNodeStyleSetWidth(long j, float f);

    public static native void jni_YGNodeStyleSetWidthAuto(long j);

    public static native void jni_YGNodeStyleSetWidthPercent(long j, float f);

    @Qw
    private final long replaceChild(YogaNode yogaNode, int i) {
        List<YogaNode> list = this.f854b;
        if (list != null) {
            list.remove(i);
            this.f854b.add(i, yogaNode);
            yogaNode.f853a = this;
            return yogaNode.f857e;
        }
        throw new IllegalStateException("Cannot replace child. YogaNode does not have children");
    }

    /* renamed from: a */
    public void mo1889a(YogaNode yogaNode, int i) {
        if (yogaNode.f853a == null) {
            if (this.f854b == null) {
                this.f854b = new ArrayList(4);
            }
            this.f854b.add(i, yogaNode);
            yogaNode.f853a = this;
            jni_YGNodeInsertChild(this.f857e, yogaNode.f857e, i);
            return;
        }
        throw new IllegalStateException("Child already has a parent, it must be removed first.");
    }

    /* renamed from: a */
    public void mo1893a(Object obj) {
    }

    /* renamed from: b */
    public void mo1894b() {
        long j = this.f857e;
        if (j > 0) {
            this.f857e = 0;
            jni_YGNodeFree(j);
        }
    }

    @Qw
    public final float baseline(float f, float f2) {
        return this.f856d.baseline(this, f, f2);
    }

    /* renamed from: c */
    public void mo1902c(YogaAlign yogaAlign) {
        jni_YGNodeStyleSetAlignSelf(this.f857e, yogaAlign.intValue());
    }

    /* renamed from: d */
    public void mo1906d(float f) {
        jni_YGNodeStyleSetFlexBasisPercent(this.f857e, f);
    }

    /* renamed from: e */
    public void mo1909e(float f) {
        jni_YGNodeStyleSetFlexGrow(this.f857e, f);
    }

    /* renamed from: f */
    public void mo1912f(float f) {
        jni_YGNodeStyleSetFlexShrink(this.f857e, f);
    }

    public void finalize() throws Throwable {
        try {
            mo1894b();
        } finally {
            super.finalize();
        }
    }

    /* renamed from: g */
    public void mo1917g(YogaEdge yogaEdge, float f) {
        jni_YGNodeStyleSetPositionPercent(this.f857e, yogaEdge.intValue(), f);
    }

    /* renamed from: h */
    public boolean mo1919h() {
        return this.mHasNewLayout;
    }

    /* renamed from: i */
    public boolean mo1921i() {
        return jni_YGNodeIsDirty(this.f857e);
    }

    /* renamed from: j */
    public void mo1922j(float f) {
        jni_YGNodeStyleSetMaxHeightPercent(this.f857e, f);
    }

    /* renamed from: k */
    public void mo1924k() {
        this.mHasNewLayout = false;
    }

    /* renamed from: l */
    public void mo1926l() {
        this.mEdgeSetFlag = 0;
        this.mHasNewLayout = true;
        this.mWidth = Float.NaN;
        this.mHeight = Float.NaN;
        this.mTop = Float.NaN;
        this.mLeft = Float.NaN;
        this.mMarginLeft = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mMarginTop = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mMarginRight = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mMarginBottom = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mPaddingLeft = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mPaddingTop = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mPaddingRight = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mPaddingBottom = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mBorderLeft = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mBorderTop = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mBorderRight = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mBorderBottom = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mLayoutDirection = 0;
        this.f855c = null;
        this.f856d = null;
        this.mDoesLegacyStretchFlagAffectsLayout = false;
        jni_YGNodeReset(this.f857e);
    }

    /* renamed from: m */
    public void mo1928m() {
        jni_YGNodeStyleSetFlexBasisAuto(this.f857e);
    }

    @Qw
    public final long measure(float f, int i, float f2, int i2) {
        if (mo1923j()) {
            return this.f855c.measure(this, f, YogaMeasureMode.fromInt(i), f2, YogaMeasureMode.fromInt(i2));
        }
        throw new RuntimeException("Measure function isn't defined!");
    }

    /* renamed from: n */
    public void mo1931n() {
        jni_YGNodeStyleSetHeightAuto(this.f857e);
    }

    /* renamed from: o */
    public void mo1933o() {
        jni_YGNodeStyleSetWidthAuto(this.f857e);
    }

    /* renamed from: p */
    public void mo1935p(float f) {
        jni_YGNodeStyleSetMinWidthPercent(this.f857e, f);
    }

    /* renamed from: q */
    public void mo1936q(float f) {
        jni_YGNodeStyleSetWidth(this.f857e, f);
    }

    /* renamed from: r */
    public void mo1937r(float f) {
        jni_YGNodeStyleSetWidthPercent(this.f857e, f);
    }

    /* renamed from: c */
    public void mo1901c(float f) {
        jni_YGNodeStyleSetFlexBasis(this.f857e, f);
    }

    public YogaNode clone() {
        try {
            YogaNode yogaNode = (YogaNode) super.clone();
            long jni_YGNodeClone = jni_YGNodeClone(this.f857e, yogaNode);
            if (this.f854b != null) {
                for (YogaNode next : this.f854b) {
                    jni_YGNodeSetOwner(next.f857e, 0);
                    next.f853a = null;
                }
            }
            yogaNode.f857e = jni_YGNodeClone;
            yogaNode.f853a = null;
            yogaNode.f854b = this.f854b != null ? (List) ((ArrayList) this.f854b).clone() : null;
            List<YogaNode> list = yogaNode.f854b;
            if (list != null) {
                for (YogaNode yogaNode2 : list) {
                    yogaNode2.f853a = null;
                }
            }
            return yogaNode;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: d */
    public void mo1907d(YogaEdge yogaEdge, float f) {
        this.mEdgeSetFlag |= 2;
        jni_YGNodeStyleSetPadding(this.f857e, yogaEdge.intValue(), f);
    }

    /* renamed from: e */
    public void mo1910e(YogaEdge yogaEdge, float f) {
        this.mEdgeSetFlag |= 2;
        jni_YGNodeStyleSetPaddingPercent(this.f857e, yogaEdge.intValue(), f);
    }

    /* renamed from: f */
    public void mo1913f(YogaEdge yogaEdge, float f) {
        jni_YGNodeStyleSetPosition(this.f857e, yogaEdge.intValue(), f);
    }

    /* renamed from: g */
    public void mo1916g(float f) {
        jni_YGNodeStyleSetHeight(this.f857e, f);
    }

    /* renamed from: h */
    public void mo1918h(float f) {
        jni_YGNodeStyleSetHeightPercent(this.f857e, f);
    }

    /* renamed from: i */
    public void mo1920i(float f) {
        jni_YGNodeStyleSetMaxHeight(this.f857e, f);
    }

    /* renamed from: j */
    public boolean mo1923j() {
        return this.f855c != null;
    }

    /* renamed from: k */
    public void mo1925k(float f) {
        jni_YGNodeStyleSetMaxWidth(this.f857e, f);
    }

    /* renamed from: m */
    public void mo1929m(float f) {
        jni_YGNodeStyleSetMinHeight(this.f857e, f);
    }

    /* renamed from: n */
    public void mo1932n(float f) {
        jni_YGNodeStyleSetMinHeightPercent(this.f857e, f);
    }

    /* renamed from: o */
    public void mo1934o(float f) {
        jni_YGNodeStyleSetMinWidth(this.f857e, f);
    }

    /* renamed from: c */
    public void mo1903c(YogaEdge yogaEdge, float f) {
        this.mEdgeSetFlag |= 1;
        jni_YGNodeStyleSetMarginPercent(this.f857e, yogaEdge.intValue(), f);
    }

    /* renamed from: f */
    public float mo1911f() {
        return this.mLeft;
    }

    /* renamed from: g */
    public float mo1915g() {
        return this.mTop;
    }

    /* renamed from: b */
    public void mo1896b(YogaAlign yogaAlign) {
        jni_YGNodeStyleSetAlignItems(this.f857e, yogaAlign.intValue());
    }

    /* renamed from: d */
    public float mo1905d() {
        return this.mHeight;
    }

    /* renamed from: e */
    public float mo1908e() {
        return this.mWidth;
    }

    /* renamed from: b */
    public void mo1895b(float f) {
        jni_YGNodeStyleSetFlex(this.f857e, f);
    }

    /* renamed from: c */
    public YogaDirection mo1900c() {
        return YogaDirection.fromInt(this.mLayoutDirection);
    }

    /* renamed from: b */
    public void mo1898b(YogaEdge yogaEdge, float f) {
        this.mEdgeSetFlag |= 1;
        jni_YGNodeStyleSetMargin(this.f857e, yogaEdge.intValue(), f);
    }

    /* renamed from: a */
    public YogaNode mo1878a(int i) {
        List<YogaNode> list = this.f854b;
        if (list != null) {
            YogaNode remove = list.remove(i);
            remove.f853a = null;
            jni_YGNodeRemoveChild(this.f857e, remove.f857e);
            return remove;
        }
        throw new IllegalStateException("Trying to remove a child of a YogaNode that does not have children");
    }

    /* renamed from: b */
    public void mo1897b(YogaEdge yogaEdge) {
        this.mEdgeSetFlag |= 1;
        jni_YGNodeStyleSetMarginAuto(this.f857e, yogaEdge.intValue());
    }

    /* renamed from: a */
    public void mo1881a(float f, float f2) {
        jni_YGNodeCalculateLayout(this.f857e, f, f2);
    }

    /* renamed from: a */
    public void mo1879a() {
        jni_YGNodeMarkDirty(this.f857e);
    }

    /* renamed from: a */
    public void mo1883a(YogaDirection yogaDirection) {
        jni_YGNodeStyleSetDirection(this.f857e, yogaDirection.intValue());
    }

    /* renamed from: a */
    public void mo1886a(YogaFlexDirection yogaFlexDirection) {
        jni_YGNodeStyleSetFlexDirection(this.f857e, yogaFlexDirection.intValue());
    }

    /* renamed from: a */
    public void mo1887a(YogaJustify yogaJustify) {
        jni_YGNodeStyleSetJustifyContent(this.f857e, yogaJustify.intValue());
    }

    /* renamed from: a */
    public void mo1882a(YogaAlign yogaAlign) {
        jni_YGNodeStyleSetAlignContent(this.f857e, yogaAlign.intValue());
    }

    /* renamed from: a */
    public void mo1891a(YogaPositionType yogaPositionType) {
        jni_YGNodeStyleSetPositionType(this.f857e, yogaPositionType.intValue());
    }

    /* renamed from: a */
    public void mo1892a(YogaWrap yogaWrap) {
        jni_YGNodeStyleSetFlexWrap(this.f857e, yogaWrap.intValue());
    }

    /* renamed from: a */
    public void mo1890a(YogaOverflow yogaOverflow) {
        jni_YGNodeStyleSetOverflow(this.f857e, yogaOverflow.intValue());
    }

    /* renamed from: a */
    public void mo1884a(YogaDisplay yogaDisplay) {
        jni_YGNodeStyleSetDisplay(this.f857e, yogaDisplay.intValue());
    }

    /* renamed from: a */
    public void mo1885a(YogaEdge yogaEdge, float f) {
        this.mEdgeSetFlag |= 4;
        jni_YGNodeStyleSetBorder(this.f857e, yogaEdge.intValue(), f);
    }

    /* renamed from: l */
    public void mo1927l(float f) {
        jni_YGNodeStyleSetMaxWidthPercent(this.f857e, f);
    }

    public YogaNode(YogaConfig yogaConfig) {
        this.mEdgeSetFlag = 0;
        this.mWidth = Float.NaN;
        this.mHeight = Float.NaN;
        this.mTop = Float.NaN;
        this.mLeft = Float.NaN;
        this.mMarginLeft = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mMarginTop = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mMarginRight = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mMarginBottom = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mPaddingLeft = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mPaddingTop = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mPaddingRight = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mPaddingBottom = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mBorderLeft = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mBorderTop = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mBorderRight = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mBorderBottom = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        this.mLayoutDirection = 0;
        this.mHasNewLayout = true;
        this.mDoesLegacyStretchFlagAffectsLayout = false;
        this.f857e = jni_YGNodeNewWithConfig(yogaConfig.f852a);
        if (this.f857e == 0) {
            throw new IllegalStateException("Failed to allocate native memory");
        }
    }

    /* renamed from: a */
    public void mo1880a(float f) {
        jni_YGNodeStyleSetAspectRatio(this.f857e, f);
    }

    /* renamed from: a */
    public float mo1877a(YogaEdge yogaEdge) {
        int ordinal = yogaEdge.ordinal();
        if (ordinal == 0) {
            return this.mPaddingLeft;
        }
        if (ordinal == 1) {
            return this.mPaddingTop;
        }
        if (ordinal == 2) {
            return this.mPaddingRight;
        }
        if (ordinal == 3) {
            return this.mPaddingBottom;
        }
        if (ordinal == 4) {
            return mo1900c() == YogaDirection.RTL ? this.mPaddingRight : this.mPaddingLeft;
        }
        if (ordinal == 5) {
            return mo1900c() == YogaDirection.RTL ? this.mPaddingLeft : this.mPaddingRight;
        }
        throw new IllegalArgumentException("Cannot get layout paddings of multi-edge shorthands");
    }

    /* renamed from: a */
    public void mo1888a(YogaMeasureFunction yogaMeasureFunction) {
        this.f855c = yogaMeasureFunction;
        jni_YGNodeSetHasMeasureFunc(this.f857e, yogaMeasureFunction != null);
    }
}
