package com.facebook.react.views.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.GenericDraweeView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.UIManagerModule;
import java.util.Arrays;
import java.util.List;

/* compiled from: PG */
public class ReactImageView extends GenericDraweeView {

    /* renamed from: L3 */
    public static float[] f568L3 = new float[4];

    /* renamed from: M3 */
    public static final Matrix f569M3 = new Matrix();

    /* renamed from: N3 */
    public static final Matrix f570N3 = new Matrix();

    /* renamed from: O3 */
    public static final Matrix f571O3 = new Matrix();

    /* renamed from: A3 */
    public boolean f572A3;

    /* renamed from: B3 */
    public final AbstractDraweeControllerBuilder f573B3;

    /* renamed from: C3 */
    public final C0148b f574C3;

    /* renamed from: D3 */
    public final C0149c f575D3;

    /* renamed from: E3 */
    public Ju f576E3;

    /* renamed from: F3 */
    public Fr f577F3;

    /* renamed from: G3 */
    public Fr f578G3;

    /* renamed from: H3 */
    public final Object f579H3;

    /* renamed from: I3 */
    public int f580I3 = -1;

    /* renamed from: J3 */
    public boolean f581J3;

    /* renamed from: K3 */
    public ReadableMap f582K3;

    /* renamed from: n */
    public ImageResizeMethod f583n = ImageResizeMethod.AUTO;

    /* renamed from: p */
    public final List<bD> f584p;

    /* renamed from: q */
    public bD f585q;

    /* renamed from: q3 */
    public Drawable f586q3;

    /* renamed from: r3 */
    public Vr f587r3;

    /* renamed from: s3 */
    public int f588s3 = 0;

    /* renamed from: t3 */
    public int f589t3;

    /* renamed from: u3 */
    public int f590u3;

    /* renamed from: v3 */
    public float f591v3;

    /* renamed from: w3 */
    public float f592w3 = Float.NaN;

    /* renamed from: x */
    public bD f593x;

    /* renamed from: x3 */
    public float[] f594x3;

    /* renamed from: y */
    public Drawable f595y;

    /* renamed from: y3 */
    public ScalingUtils.ScaleType f596y3 = ScalingUtils.ScaleType.CENTER_CROP;

    /* renamed from: z3 */
    public Shader.TileMode f597z3 = Shader.TileMode.CLAMP;

    /* renamed from: com.facebook.react.views.image.ReactImageView$b */
    /* compiled from: PG */
    public class C0148b extends zw {
        public /* synthetic */ C0148b(C0147a aVar) {
        }

        /* renamed from: a */
        public void mo1276a(Bitmap bitmap, Bitmap bitmap2) {
            Bitmap bitmap3 = bitmap;
            ReactImageView.this.mo1248a(ReactImageView.f568L3);
            bitmap3.setHasAlpha(true);
            if (!sA.a(ReactImageView.f568L3[0], BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) || !sA.a(ReactImageView.f568L3[1], BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) || !sA.a(ReactImageView.f568L3[2], BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) || !sA.a(ReactImageView.f568L3[3], BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER)) {
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                paint.setShader(new BitmapShader(bitmap2, tileMode, tileMode));
                Canvas canvas = new Canvas(bitmap3);
                float[] fArr = new float[8];
                float[] fArr2 = ReactImageView.f568L3;
                ReactImageView.this.f596y3.getTransform(ReactImageView.f569M3, new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight()), bitmap2.getWidth(), bitmap2.getHeight(), BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
                ReactImageView.f569M3.invert(ReactImageView.f570N3);
                fArr[0] = ReactImageView.f570N3.mapRadius(fArr2[0]);
                fArr[1] = fArr[0];
                fArr[2] = ReactImageView.f570N3.mapRadius(fArr2[1]);
                fArr[3] = fArr[2];
                fArr[4] = ReactImageView.f570N3.mapRadius(fArr2[2]);
                fArr[5] = fArr[4];
                fArr[6] = ReactImageView.f570N3.mapRadius(fArr2[3]);
                fArr[7] = fArr[6];
                Path path = new Path();
                path.addRoundRect(new RectF(BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, (float) bitmap2.getWidth(), (float) bitmap2.getHeight()), fArr, Path.Direction.CW);
                canvas.drawPath(path, paint);
                return;
            }
            ReactImageView.super.a(bitmap, bitmap2);
        }
    }

    /* renamed from: com.facebook.react.views.image.ReactImageView$c */
    /* compiled from: PG */
    public class C0149c extends zw {
        public /* synthetic */ C0149c(C0147a aVar) {
        }

        /* renamed from: a */
        public Eq<Bitmap> mo1277a(Bitmap bitmap, Es es) {
            Rect rect = new Rect(0, 0, ReactImageView.this.getWidth(), ReactImageView.this.getHeight());
            ReactImageView.this.f596y3.getTransform(ReactImageView.f571O3, rect, bitmap.getWidth(), bitmap.getHeight(), BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            Shader.TileMode tileMode = ReactImageView.this.f597z3;
            BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
            bitmapShader.setLocalMatrix(ReactImageView.f571O3);
            paint.setShader(bitmapShader);
            Eq a = es.a(ReactImageView.this.getWidth(), ReactImageView.this.getHeight());
            try {
                new Canvas((Bitmap) a.b()).drawRect(rect, paint);
                Eq<Bitmap> clone = a.clone();
                a.close();
                return clone;
            } catch (Throwable th) {
                Eq.b(a);
                throw th;
            }
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ReactImageView(android.content.Context r4, com.facebook.drawee.controller.AbstractDraweeControllerBuilder r5, java.lang.Object r6) {
        /*
            r3 = this;
            fs r0 = new fs
            android.content.res.Resources r1 = r4.getResources()
            r0.<init>(r1)
            com.facebook.drawee.generic.RoundingParams r1 = new com.facebook.drawee.generic.RoundingParams
            r1.<init>()
            r2 = 0
            r1.mo399b(r2)
            r0.r = r1
            es r0 = r0.a()
            r3.<init>((android.content.Context) r4, (es) r0)
            com.facebook.react.views.image.ImageResizeMethod r4 = com.facebook.react.views.image.ImageResizeMethod.AUTO
            r3.f583n = r4
            r4 = 0
            r3.f588s3 = r4
            r4 = 2143289344(0x7fc00000, float:NaN)
            r3.f592w3 = r4
            android.graphics.Shader$TileMode r4 = android.graphics.Shader.TileMode.CLAMP
            r3.f597z3 = r4
            r4 = -1
            r3.f580I3 = r4
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r4 = com.facebook.drawee.drawable.ScalingUtils.ScaleType.CENTER_CROP
            r3.f596y3 = r4
            r3.f573B3 = r5
            com.facebook.react.views.image.ReactImageView$b r4 = new com.facebook.react.views.image.ReactImageView$b
            r5 = 0
            r4.<init>(r5)
            r3.f574C3 = r4
            com.facebook.react.views.image.ReactImageView$c r4 = new com.facebook.react.views.image.ReactImageView$c
            r4.<init>(r5)
            r3.f575D3 = r4
            r3.f579H3 = r6
            java.util.LinkedList r4 = new java.util.LinkedList
            r4.<init>()
            r3.f584p = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.image.ReactImageView.<init>(android.content.Context, com.facebook.drawee.controller.AbstractDraweeControllerBuilder, java.lang.Object):void");
    }

    /* renamed from: h */
    public final boolean mo1249h() {
        return this.f584p.size() > 1;
    }

    public boolean hasOverlappingRendering() {
        return false;
    }

    /* renamed from: i */
    public final boolean mo1251i() {
        return this.f597z3 != Shader.TileMode.CLAMP;
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x010c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x010d  */
    /* renamed from: j */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo1252j() {
        /*
            r19 = this;
            r0 = r19
            boolean r1 = r0.f572A3
            if (r1 != 0) goto L_0x0007
            return
        L_0x0007:
            boolean r1 = r19.mo1249h()
            if (r1 == 0) goto L_0x001a
            int r1 = r19.getWidth()
            if (r1 <= 0) goto L_0x0019
            int r1 = r19.getHeight()
            if (r1 > 0) goto L_0x001a
        L_0x0019:
            return
        L_0x001a:
            r1 = 0
            r0.f585q = r1
            java.util.List<bD> r2 = r0.f584p
            boolean r2 = r2.isEmpty()
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0039
            bD r2 = new bD
            android.content.Context r5 = r19.getContext()
            java.lang.String r6 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNkYAAAAAYAAjCB0C8AAAAASUVORK5CYII="
            r2.<init>(r5, r6)
            java.util.List<bD> r5 = r0.f584p
            r5.add(r2)
            goto L_0x00fe
        L_0x0039:
            boolean r2 = r19.mo1249h()
            if (r2 == 0) goto L_0x00fe
            int r2 = r19.getWidth()
            int r5 = r19.getHeight()
            java.util.List<bD> r6 = r0.f584p
            boolean r7 = r6.isEmpty()
            if (r7 == 0) goto L_0x0056
            dD r2 = new dD
            r2.<init>(r1, r1, r1)
            goto L_0x00f5
        L_0x0056:
            int r7 = r6.size()
            if (r7 != r4) goto L_0x0069
            dD r2 = new dD
            java.lang.Object r5 = r6.get(r3)
            bD r5 = (bD) r5
            r2.<init>(r5, r1, r1)
            goto L_0x00f5
        L_0x0069:
            if (r2 <= 0) goto L_0x00f0
            if (r5 > 0) goto L_0x006f
            goto L_0x00f0
        L_0x006f:
            Bt r7 = Bt.j()
            com.facebook.imagepipeline.core.ImagePipeline r7 = r7.e()
            int r2 = r2 * r5
            double r8 = (double) r2
            java.lang.Double.isNaN(r8)
            java.lang.Double.isNaN(r8)
            java.lang.Double.isNaN(r8)
            java.lang.Double.isNaN(r8)
            r10 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            java.lang.Double.isNaN(r8)
            double r8 = r8 * r10
            java.util.Iterator r2 = r6.iterator()
            r5 = 9218868437227405311(0x7fefffffffffffff, double:1.7976931348623157E308)
            r12 = r5
            r14 = r12
            r5 = r1
            r6 = r5
        L_0x009a:
            boolean r16 = r2.hasNext()
            if (r16 == 0) goto L_0x00da
            java.lang.Object r16 = r2.next()
            r4 = r16
            bD r4 = (bD) r4
            r17 = r2
            double r1 = r4.c
            double r1 = r1 / r8
            double r1 = r10 - r1
            double r1 = java.lang.Math.abs(r1)
            int r18 = (r1 > r12 ? 1 : (r1 == r12 ? 0 : -1))
            if (r18 >= 0) goto L_0x00b9
            r12 = r1
            r6 = r4
        L_0x00b9:
            int r18 = (r1 > r14 ? 1 : (r1 == r14 ? 0 : -1))
            if (r18 >= 0) goto L_0x00d3
            android.net.Uri r10 = r4.a()
            boolean r10 = r7.mo471a((android.net.Uri) r10)
            if (r10 != 0) goto L_0x00d1
            android.net.Uri r10 = r4.a()
            boolean r10 = r7.mo476b(r10)
            if (r10 == 0) goto L_0x00d3
        L_0x00d1:
            r14 = r1
            r5 = r4
        L_0x00d3:
            r2 = r17
            r1 = 0
            r4 = 1
            r10 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x009a
        L_0x00da:
            if (r5 == 0) goto L_0x00e9
            if (r6 == 0) goto L_0x00e9
            java.lang.String r1 = r5.b
            java.lang.String r2 = r6.b
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00e9
            r5 = 0
        L_0x00e9:
            dD r2 = new dD
            r1 = 0
            r2.<init>(r6, r5, r1)
            goto L_0x00f5
        L_0x00f0:
            dD r2 = new dD
            r2.<init>(r1, r1, r1)
        L_0x00f5:
            bD r4 = r2.a
            r0.f585q = r4
            bD r2 = r2.b
            r0.f593x = r2
            goto L_0x0108
        L_0x00fe:
            java.util.List<bD> r2 = r0.f584p
            java.lang.Object r2 = r2.get(r3)
            bD r2 = (bD) r2
            r0.f585q = r2
        L_0x0108:
            bD r2 = r0.f585q
            if (r2 != 0) goto L_0x010d
            return
        L_0x010d:
            com.facebook.react.views.image.ImageResizeMethod r4 = r0.f583n
            com.facebook.react.views.image.ImageResizeMethod r5 = com.facebook.react.views.image.ImageResizeMethod.AUTO
            if (r4 != r5) goto L_0x0128
            android.net.Uri r4 = r2.a()
            boolean r4 = Oq.d(r4)
            if (r4 != 0) goto L_0x012c
            android.net.Uri r2 = r2.a()
            boolean r2 = Oq.e(r2)
            if (r2 == 0) goto L_0x012e
            goto L_0x012c
        L_0x0128:
            com.facebook.react.views.image.ImageResizeMethod r2 = com.facebook.react.views.image.ImageResizeMethod.RESIZE
            if (r4 != r2) goto L_0x012e
        L_0x012c:
            r2 = 1
            goto L_0x012f
        L_0x012e:
            r2 = 0
        L_0x012f:
            if (r2 == 0) goto L_0x013e
            int r4 = r19.getWidth()
            if (r4 <= 0) goto L_0x013d
            int r4 = r19.getHeight()
            if (r4 > 0) goto L_0x013e
        L_0x013d:
            return
        L_0x013e:
            boolean r4 = r19.mo1251i()
            if (r4 == 0) goto L_0x0151
            int r4 = r19.getWidth()
            if (r4 <= 0) goto L_0x0150
            int r4 = r19.getHeight()
            if (r4 > 0) goto L_0x0151
        L_0x0150:
            return
        L_0x0151:
            ks r4 = r19.mo411d()
            es r4 = (es) r4
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r5 = r0.f596y3
            if (r5 == 0) goto L_0x033e
            r6 = 2
            Yr r7 = r4.d(r6)
            r7.a(r5)
            android.graphics.drawable.Drawable r5 = r0.f595y
            if (r5 == 0) goto L_0x0175
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r7 = r0.f596y3
            r8 = 1
            r4.a(r8, r5)
            Yr r5 = r4.d(r8)
            r5.a(r7)
            goto L_0x0176
        L_0x0175:
            r8 = 1
        L_0x0176:
            android.graphics.drawable.Drawable r5 = r0.f586q3
            if (r5 == 0) goto L_0x0186
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r7 = com.facebook.drawee.drawable.ScalingUtils.ScaleType.CENTER
            r4.a(r8, r5)
            Yr r5 = r4.d(r8)
            r5.a(r7)
        L_0x0186:
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r5 = r0.f596y3
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r7 = com.facebook.drawee.drawable.ScalingUtils.ScaleType.CENTER_CROP
            if (r5 == r7) goto L_0x0192
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r7 = com.facebook.drawee.drawable.ScalingUtils.ScaleType.FOCUS_CROP
            if (r5 == r7) goto L_0x0192
            r5 = 1
            goto L_0x0193
        L_0x0192:
            r5 = 0
        L_0x0193:
            com.facebook.drawee.generic.RoundingParams r7 = r4.c
            float[] r8 = f568L3
            r0.mo1248a((float[]) r8)
            float[] r8 = f568L3
            r9 = r8[r3]
            r10 = 1
            r11 = r8[r10]
            r6 = r8[r6]
            r10 = 3
            r8 = r8[r10]
            r7.mo397a(r9, r11, r6, r8)
            Vr r6 = r0.f587r3
            if (r6 == 0) goto L_0x01c0
            int r8 = r0.f589t3
            float r9 = r0.f591v3
            r6.a(r8, r9)
            Vr r6 = r0.f587r3
            float[] r8 = r7.f304c
            r6.a(r8)
            Vr r6 = r0.f587r3
            r4.a(r3, r6)
        L_0x01c0:
            r6 = 0
            if (r5 == 0) goto L_0x01c6
            r7.mo399b(r6)
        L_0x01c6:
            int r8 = r0.f589t3
            float r9 = r0.f591v3
            int r10 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r10 < 0) goto L_0x01d0
            r10 = 1
            goto L_0x01d1
        L_0x01d0:
            r10 = 0
        L_0x01d1:
            java.lang.String r11 = "the border width cannot be < 0"
            kq.a(r10, r11)
            r7.f306e = r9
            r7.f307f = r8
            int r8 = r0.f590u3
            if (r8 == 0) goto L_0x01e5
            r7.f305d = r8
            com.facebook.drawee.generic.RoundingParams$RoundingMethod r8 = com.facebook.drawee.generic.RoundingParams.RoundingMethod.OVERLAY_COLOR
            r7.f302a = r8
            goto L_0x01e9
        L_0x01e5:
            com.facebook.drawee.generic.RoundingParams$RoundingMethod r8 = com.facebook.drawee.generic.RoundingParams.RoundingMethod.BITMAP_ONLY
            r7.f302a = r8
        L_0x01e9:
            r4.c = r7
            hs r7 = r4.d
            com.facebook.drawee.generic.RoundingParams r8 = r4.c
            is.a(r7, r8)
            r7 = 0
        L_0x01f3:
            Qr r8 = r4.e
            android.graphics.drawable.Drawable[] r8 = r8.c
            int r8 = r8.length
            if (r7 >= r8) goto L_0x024e
            Nr r8 = r4.c(r7)
            com.facebook.drawee.generic.RoundingParams r9 = r4.c
            android.content.res.Resources r10 = r4.b
        L_0x0202:
            android.graphics.drawable.Drawable r11 = r8.a()
            if (r11 == r8) goto L_0x0211
            boolean r12 = r11 instanceof Nr
            if (r12 != 0) goto L_0x020d
            goto L_0x0211
        L_0x020d:
            r8 = r11
            Nr r8 = (Nr) r8
            goto L_0x0202
        L_0x0211:
            android.graphics.drawable.Drawable r11 = r8.a()
            if (r9 == 0) goto L_0x0236
            com.facebook.drawee.generic.RoundingParams$RoundingMethod r12 = r9.f302a
            com.facebook.drawee.generic.RoundingParams$RoundingMethod r13 = com.facebook.drawee.generic.RoundingParams.RoundingMethod.BITMAP_ONLY
            if (r12 != r13) goto L_0x0236
            boolean r12 = r11 instanceof Tr
            if (r12 == 0) goto L_0x0227
            Tr r11 = (Tr) r11
            is.a(r11, r9)
            goto L_0x024b
        L_0x0227:
            if (r11 == 0) goto L_0x024b
            android.graphics.drawable.Drawable r12 = is.a
            r8.a(r12)
            android.graphics.drawable.Drawable r9 = is.a(r11, r9, r10)
            r8.a(r9)
            goto L_0x024b
        L_0x0236:
            boolean r8 = r11 instanceof Tr
            if (r8 == 0) goto L_0x024b
            Tr r11 = (Tr) r11
            r11.a(r3)
            r11.b(r6)
            r11.a(r3, r6)
            r11.a(r6)
            r11.b(r3)
        L_0x024b:
            int r7 = r7 + 1
            goto L_0x01f3
        L_0x024e:
            int r6 = r0.f580I3
            if (r6 < 0) goto L_0x0253
            goto L_0x025d
        L_0x0253:
            bD r6 = r0.f585q
            boolean r6 = r6.d
            if (r6 == 0) goto L_0x025b
            r6 = 0
            goto L_0x025d
        L_0x025b:
            r6 = 300(0x12c, float:4.2E-43)
        L_0x025d:
            Qr r4 = r4.e
            r4.y = r6
            int r6 = r4.x
            r7 = 1
            if (r6 != r7) goto L_0x0268
            r4.x = r3
        L_0x0268:
            java.util.LinkedList r4 = new java.util.LinkedList
            r4.<init>()
            if (r5 == 0) goto L_0x0274
            com.facebook.react.views.image.ReactImageView$b r5 = r0.f574C3
            r4.add(r5)
        L_0x0274:
            Ju r5 = r0.f576E3
            if (r5 == 0) goto L_0x027b
            r4.add(r5)
        L_0x027b:
            boolean r5 = r19.mo1251i()
            if (r5 == 0) goto L_0x0286
            com.facebook.react.views.image.ReactImageView$c r5 = r0.f575D3
            r4.add(r5)
        L_0x0286:
            int r5 = r4.size()
            if (r5 == 0) goto L_0x029d
            r6 = 1
            if (r5 == r6) goto L_0x0296
            ZC r5 = new ZC
            r5.<init>(r4)
            r4 = r5
            goto L_0x029e
        L_0x0296:
            java.lang.Object r4 = r4.get(r3)
            Aw r4 = (Aw) r4
            goto L_0x029e
        L_0x029d:
            r4 = r1
        L_0x029e:
            if (r2 == 0) goto L_0x02ad
            nt r1 = new nt
            int r2 = r19.getWidth()
            int r5 = r19.getHeight()
            r1.<init>(r2, r5)
        L_0x02ad:
            bD r2 = r0.f585q
            android.net.Uri r2 = r2.a()
            com.facebook.imagepipeline.request.ImageRequestBuilder r2 = com.facebook.imagepipeline.request.ImageRequestBuilder.m403a((android.net.Uri) r2)
            r2.f408j = r4
            r2.f401c = r1
            r5 = 1
            r2.mo556a((boolean) r5)
            boolean r5 = r0.f581J3
            r2.f405g = r5
            com.facebook.react.bridge.ReadableMap r5 = r0.f582K3
            Qy r6 = new Qy
            r6.<init>(r2, r5)
            com.facebook.drawee.controller.AbstractDraweeControllerBuilder r2 = r0.f573B3
            r2.mo366b()
            com.facebook.drawee.controller.AbstractDraweeControllerBuilder r2 = r0.f573B3
            r5 = 1
            r2.f274j = r5
            java.lang.Object r5 = r0.f579H3
            r2.f267c = r5
            js r5 = r19.mo410c()
            r2.f276l = r5
            r2.f268d = r6
            bD r2 = r0.f593x
            if (r2 == 0) goto L_0x0300
            android.net.Uri r2 = r2.a()
            com.facebook.imagepipeline.request.ImageRequestBuilder r2 = com.facebook.imagepipeline.request.ImageRequestBuilder.m403a((android.net.Uri) r2)
            r2.f408j = r4
            r2.f401c = r1
            r1 = 1
            r2.mo556a((boolean) r1)
            boolean r1 = r0.f581J3
            r2.f405g = r1
            com.facebook.imagepipeline.request.ImageRequest r1 = r2.mo554a()
            com.facebook.drawee.controller.AbstractDraweeControllerBuilder r2 = r0.f573B3
            r2.f269e = r1
        L_0x0300:
            Fr r1 = r0.f577F3
            if (r1 == 0) goto L_0x031c
            Fr r1 = r0.f578G3
            if (r1 == 0) goto L_0x031c
            Gr r1 = new Gr
            r1.<init>()
            Fr r2 = r0.f577F3
            r1.a(r2)
            Fr r2 = r0.f578G3
            r1.a(r2)
            com.facebook.drawee.controller.AbstractDraweeControllerBuilder r2 = r0.f573B3
            r2.f272h = r1
            goto L_0x032d
        L_0x031c:
            Fr r1 = r0.f578G3
            if (r1 == 0) goto L_0x0325
            com.facebook.drawee.controller.AbstractDraweeControllerBuilder r2 = r0.f573B3
            r2.f272h = r1
            goto L_0x032d
        L_0x0325:
            Fr r1 = r0.f577F3
            if (r1 == 0) goto L_0x032d
            com.facebook.drawee.controller.AbstractDraweeControllerBuilder r2 = r0.f573B3
            r2.f272h = r1
        L_0x032d:
            com.facebook.drawee.controller.AbstractDraweeControllerBuilder r1 = r0.f573B3
            Br r1 = r1.mo361a()
            r0.setController(r1)
            r0.f572A3 = r3
            com.facebook.drawee.controller.AbstractDraweeControllerBuilder r1 = r0.f573B3
            r1.mo366b()
            return
        L_0x033e:
            java.lang.NullPointerException r1 = new java.lang.NullPointerException
            r1.<init>()
            goto L_0x0345
        L_0x0344:
            throw r1
        L_0x0345:
            goto L_0x0344
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.image.ReactImageView.mo1252j():void");
    }

    /* renamed from: k */
    public final void mo1253k() {
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i > 0 && i2 > 0) {
            this.f572A3 = this.f572A3 || mo1249h() || mo1251i();
            mo1252j();
        }
    }

    public void setBackgroundColor(int i) {
        if (this.f588s3 != i) {
            this.f588s3 = i;
            this.f587r3 = new Vr(i);
            this.f572A3 = true;
        }
    }

    public void setBlurRadius(float f) {
        int b = (int) GA.b(f);
        if (b == 0) {
            this.f576E3 = null;
        } else {
            this.f576E3 = new Ju(b);
        }
        this.f572A3 = true;
    }

    public void setBorderColor(int i) {
        this.f589t3 = i;
        this.f572A3 = true;
    }

    public void setBorderRadius(float f) {
        if (!sA.a(this.f592w3, f)) {
            this.f592w3 = f;
            this.f572A3 = true;
        }
    }

    public void setBorderWidth(float f) {
        this.f591v3 = GA.b(f);
        this.f572A3 = true;
    }

    public void setControllerListener(Fr fr) {
        this.f578G3 = fr;
        this.f572A3 = true;
        mo1252j();
    }

    public void setDefaultSource(String str) {
        eD a = eD.a();
        Context context = getContext();
        int a2 = a.a(context, str);
        this.f595y = a2 > 0 ? context.getResources().getDrawable(a2) : null;
        this.f572A3 = true;
    }

    public void setFadeDuration(int i) {
        this.f580I3 = i;
    }

    public void setHeaders(ReadableMap readableMap) {
        this.f582K3 = readableMap;
    }

    public void setLoadingIndicatorSource(String str) {
        eD a = eD.a();
        Context context = getContext();
        int a2 = a.a(context, str);
        Mr mr = null;
        Drawable drawable = a2 > 0 ? context.getResources().getDrawable(a2) : null;
        if (drawable != null) {
            mr = new Mr(drawable, 1000);
        }
        this.f586q3 = mr;
        this.f572A3 = true;
    }

    public void setOverlayColor(int i) {
        this.f590u3 = i;
        this.f572A3 = true;
    }

    public void setProgressiveRenderingEnabled(boolean z) {
        this.f581J3 = z;
    }

    public void setResizeMethod(ImageResizeMethod imageResizeMethod) {
        this.f583n = imageResizeMethod;
        this.f572A3 = true;
    }

    public void setScaleType(ScalingUtils.ScaleType scaleType) {
        this.f596y3 = scaleType;
        this.f572A3 = true;
    }

    public void setShouldNotifyLoadEvents(boolean z) {
        if (!z) {
            this.f577F3 = null;
        } else {
            this.f577F3 = new C0147a(((UIManagerModule) ((ReactContext) getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher());
        }
        this.f572A3 = true;
    }

    public void setSource(ReadableArray readableArray) {
        this.f584p.clear();
        if (readableArray == null || readableArray.size() == 0) {
            this.f584p.add(new bD(getContext(), "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNkYAAAAAYAAjCB0C8AAAAASUVORK5CYII="));
        } else {
            if (readableArray.size() == 1) {
                bD bDVar = new bD(getContext(), readableArray.getMap(0).getString("uri"));
                this.f584p.add(bDVar);
                if (Uri.EMPTY.equals(bDVar.a())) {
                    mo1253k();
                }
            } else {
                for (int i = 0; i < readableArray.size(); i++) {
                    ReadableMap map = readableArray.getMap(i);
                    bD bDVar2 = new bD(getContext(), map.getString("uri"), map.getDouble("width"), map.getDouble("height"));
                    this.f584p.add(bDVar2);
                    if (Uri.EMPTY.equals(bDVar2.a())) {
                        mo1253k();
                    }
                }
            }
        }
        this.f572A3 = true;
    }

    public void setTileMode(Shader.TileMode tileMode) {
        this.f597z3 = tileMode;
        this.f572A3 = true;
    }

    /* renamed from: a */
    public final void mo1248a(float[] fArr) {
        float f = !eF.a(this.f592w3) ? this.f592w3 : BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        float[] fArr2 = this.f594x3;
        fArr[0] = (fArr2 == null || eF.a(fArr2[0])) ? f : this.f594x3[0];
        float[] fArr3 = this.f594x3;
        fArr[1] = (fArr3 == null || eF.a(fArr3[1])) ? f : this.f594x3[1];
        float[] fArr4 = this.f594x3;
        fArr[2] = (fArr4 == null || eF.a(fArr4[2])) ? f : this.f594x3[2];
        float[] fArr5 = this.f594x3;
        if (fArr5 != null && !eF.a(fArr5[3])) {
            f = this.f594x3[3];
        }
        fArr[3] = f;
    }

    public void setBorderRadius(float f, int i) {
        if (this.f594x3 == null) {
            this.f594x3 = new float[4];
            Arrays.fill(this.f594x3, Float.NaN);
        }
        if (!sA.a(this.f594x3[i], f)) {
            this.f594x3[i] = f;
            this.f572A3 = true;
        }
    }

    /* renamed from: com.facebook.react.views.image.ReactImageView$a */
    /* compiled from: PG */
    public class C0147a extends Er<Yt> {

        /* renamed from: b */
        public final /* synthetic */ qC f598b;

        public C0147a(qC qCVar) {
            this.f598b = qCVar;
        }

        /* renamed from: a */
        public void mo1273a(String str, Object obj, Animatable animatable) {
            Yt yt = (Yt) obj;
            if (yt != null) {
                this.f598b.b(new XC(ReactImageView.this.getId(), 2, ReactImageView.this.f585q.b, yt.getWidth(), yt.getHeight()));
                this.f598b.b(new XC(ReactImageView.this.getId(), 3));
            }
        }

        /* renamed from: b */
        public void mo1275b(String str, Object obj) {
            this.f598b.b(new XC(ReactImageView.this.getId(), 4));
        }

        /* renamed from: a */
        public void mo1274a(String str, Throwable th) {
            this.f598b.b(new XC(ReactImageView.this.getId(), 1, true, th.getMessage()));
        }
    }
}
