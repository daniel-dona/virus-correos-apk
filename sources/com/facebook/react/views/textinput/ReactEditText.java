package com.facebook.react.views.textinput;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.method.QwertyKeyListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.views.scroll.ScrollEventType;
import com.facebook.react.views.textinput.ReactTextInputManager;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: PG */
public class ReactEditText extends EditText {

    /* renamed from: B3 */
    public static final KeyListener f713B3 = QwertyKeyListener.getInstanceForFullKeyboard();

    /* renamed from: A3 */
    public qE f714A3;

    /* renamed from: a */
    public final InputMethodManager f715a;

    /* renamed from: b */
    public boolean f716b;

    /* renamed from: c */
    public boolean f717c;

    /* renamed from: d */
    public int f718d;

    /* renamed from: e */
    public int f719e;

    /* renamed from: k */
    public int f720k;

    /* renamed from: n */
    public int f721n;

    /* renamed from: p */
    public ArrayList<TextWatcher> f722p;

    /* renamed from: q */
    public C0169c f723q;

    /* renamed from: q3 */
    public Boolean f724q3;

    /* renamed from: r3 */
    public boolean f725r3;

    /* renamed from: s3 */
    public String f726s3;

    /* renamed from: t3 */
    public kE f727t3;

    /* renamed from: u3 */
    public WD f728u3;

    /* renamed from: v3 */
    public jE f729v3;

    /* renamed from: w3 */
    public final C0168b f730w3;

    /* renamed from: x */
    public int f731x;

    /* renamed from: x3 */
    public boolean f732x3 = false;

    /* renamed from: y */
    public boolean f733y;

    /* renamed from: y3 */
    public boolean f734y3 = false;

    /* renamed from: z3 */
    public QD f735z3;

    /* renamed from: com.facebook.react.views.textinput.ReactEditText$b */
    /* compiled from: PG */
    public static class C0168b implements KeyListener {

        /* renamed from: a */
        public int f736a = 0;

        public void clearMetaKeyState(View view, Editable editable, int i) {
            ReactEditText.f713B3.clearMetaKeyState(view, editable, i);
        }

        public int getInputType() {
            return this.f736a;
        }

        public boolean onKeyDown(View view, Editable editable, int i, KeyEvent keyEvent) {
            return ReactEditText.f713B3.onKeyDown(view, editable, i, keyEvent);
        }

        public boolean onKeyOther(View view, Editable editable, KeyEvent keyEvent) {
            return ReactEditText.f713B3.onKeyOther(view, editable, keyEvent);
        }

        public boolean onKeyUp(View view, Editable editable, int i, KeyEvent keyEvent) {
            return ReactEditText.f713B3.onKeyUp(view, editable, i, keyEvent);
        }
    }

    /* renamed from: com.facebook.react.views.textinput.ReactEditText$c */
    /* compiled from: PG */
    public class C0169c implements TextWatcher {
        public /* synthetic */ C0169c(C0167a aVar) {
        }

        public void afterTextChanged(Editable editable) {
            ArrayList<TextWatcher> arrayList;
            ReactEditText reactEditText = ReactEditText.this;
            if (!reactEditText.f716b && (arrayList = reactEditText.f722p) != null) {
                Iterator<TextWatcher> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().afterTextChanged(editable);
                }
            }
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            ArrayList<TextWatcher> arrayList;
            ReactEditText reactEditText = ReactEditText.this;
            if (!reactEditText.f716b && (arrayList = reactEditText.f722p) != null) {
                Iterator<TextWatcher> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().beforeTextChanged(charSequence, i, i2, i3);
                }
            }
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            ArrayList<TextWatcher> arrayList;
            ReactEditText reactEditText = ReactEditText.this;
            if (!reactEditText.f716b && (arrayList = reactEditText.f722p) != null) {
                Iterator<TextWatcher> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().onTextChanged(charSequence, i, i2, i3);
                }
            }
            ReactEditText.this.mo1562h();
        }
    }

    public ReactEditText(Context context) {
        super(context);
        setFocusableInTouchMode(false);
        this.f714A3 = new qE(this);
        Object systemService = getContext().getSystemService("input_method");
        Kw.a(systemService);
        this.f715a = (InputMethodManager) systemService;
        this.f718d = getGravity() & 8388615;
        this.f719e = getGravity() & 112;
        this.f720k = 0;
        this.f721n = 0;
        this.f716b = false;
        this.f717c = false;
        this.f724q3 = null;
        this.f725r3 = false;
        this.f722p = null;
        this.f723q = null;
        this.f731x = getInputType();
        this.f730w3 = new C0168b();
        this.f729v3 = null;
        this.f735z3 = new QD();
        mo1549a();
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00b2 A[SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo1551a(MD r14) {
        /*
            r13 = this;
            int r0 = r13.getInputType()
            r0 = r0 & 144(0x90, float:2.02E-43)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x000c
            r0 = 1
            goto L_0x000d
        L_0x000c:
            r0 = 0
        L_0x000d:
            if (r0 == 0) goto L_0x001c
            android.text.Editable r0 = r13.getText()
            android.text.Spannable r3 = r14.a
            boolean r0 = android.text.TextUtils.equals(r0, r3)
            if (r0 == 0) goto L_0x001c
            return
        L_0x001c:
            int r0 = r14.b
            r13.f721n = r0
            int r0 = r13.f721n
            int r3 = r13.f720k
            if (r0 >= r3) goto L_0x0027
            return
        L_0x0027:
            android.text.SpannableStringBuilder r0 = new android.text.SpannableStringBuilder
            android.text.Spannable r3 = r14.a
            r0.<init>(r3)
            android.text.Editable r3 = r13.getText()
            int r4 = r13.length()
            java.lang.Class<java.lang.Object> r5 = java.lang.Object.class
            java.lang.Object[] r3 = r3.getSpans(r2, r4, r5)
            r4 = 0
        L_0x003d:
            int r5 = r3.length
            if (r4 >= r5) goto L_0x00b5
            r5 = r3[r4]
            boolean r5 = r5 instanceof HD
            if (r5 == 0) goto L_0x004f
            android.text.Editable r5 = r13.getText()
            r6 = r3[r4]
            r5.removeSpan(r6)
        L_0x004f:
            android.text.Editable r5 = r13.getText()
            r6 = r3[r4]
            int r5 = r5.getSpanFlags(r6)
            r6 = 33
            r5 = r5 & r6
            if (r5 == r6) goto L_0x005f
            goto L_0x00b2
        L_0x005f:
            r5 = r3[r4]
            android.text.Editable r6 = r13.getText()
            r7 = r3[r4]
            int r6 = r6.getSpanStart(r7)
            android.text.Editable r7 = r13.getText()
            r8 = r3[r4]
            int r7 = r7.getSpanEnd(r8)
            android.text.Editable r8 = r13.getText()
            r9 = r3[r4]
            int r8 = r8.getSpanFlags(r9)
            android.text.Editable r9 = r13.getText()
            r10 = r3[r4]
            r9.removeSpan(r10)
            android.text.Editable r9 = r13.getText()
            int r10 = r0.length()
            if (r6 > r10) goto L_0x00ac
            int r10 = r0.length()
            if (r7 <= r10) goto L_0x0099
            goto L_0x00ac
        L_0x0099:
            r10 = r6
        L_0x009a:
            if (r10 >= r7) goto L_0x00aa
            char r11 = r9.charAt(r10)
            char r12 = r0.charAt(r10)
            if (r11 == r12) goto L_0x00a7
            goto L_0x00ac
        L_0x00a7:
            int r10 = r10 + 1
            goto L_0x009a
        L_0x00aa:
            r9 = 1
            goto L_0x00ad
        L_0x00ac:
            r9 = 0
        L_0x00ad:
            if (r9 == 0) goto L_0x00b2
            r0.setSpan(r5, r6, r7, r8)
        L_0x00b2:
            int r4 = r4 + 1
            goto L_0x003d
        L_0x00b5:
            boolean r3 = r14.c
            r13.f733y = r3
            r13.f716b = r1
            android.text.Editable r1 = r13.getText()
            int r3 = r13.length()
            r1.replace(r2, r3, r0)
            r13.f716b = r2
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 23
            if (r0 < r1) goto L_0x00d9
            int r0 = r13.getBreakStrategy()
            int r14 = r14.i
            if (r0 == r14) goto L_0x00d9
            r13.setBreakStrategy(r14)
        L_0x00d9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.textinput.ReactEditText.mo1551a(MD):void");
    }

    public void addTextChangedListener(TextWatcher textWatcher) {
        if (this.f722p == null) {
            this.f722p = new ArrayList<>();
            if (this.f723q == null) {
                this.f723q = new C0169c((C0167a) null);
            }
            super.addTextChangedListener(this.f723q);
        }
        this.f722p.add(textWatcher);
    }

    /* renamed from: b */
    public void mo1553b() {
        clearFocus();
    }

    /* renamed from: c */
    public void mo1556c(int i) {
        this.f731x = i;
    }

    public void clearFocus() {
        setFocusableInTouchMode(false);
        super.clearFocus();
        this.f715a.hideSoftInputFromWindow(getWindowToken(), 0);
    }

    /* renamed from: d */
    public boolean mo1558d() {
        Boolean bool = this.f724q3;
        if (bool == null) {
            return !mo1561g();
        }
        return bool.booleanValue();
    }

    /* renamed from: e */
    public int mo1559e() {
        return this.f731x;
    }

    /* renamed from: f */
    public int mo1560f() {
        int i = this.f720k + 1;
        this.f720k = i;
        return i;
    }

    /* renamed from: g */
    public final boolean mo1561g() {
        return (getInputType() & 131072) != 0;
    }

    /* renamed from: h */
    public final void mo1562h() {
        ReactTextInputManager.C0173d dVar = this.f728u3;
        if (dVar != null) {
            ReactTextInputManager.C0173d dVar2 = dVar;
            int width = dVar2.f743a.getWidth();
            int height = dVar2.f743a.getHeight();
            if (dVar2.f743a.getLayout() != null) {
                width = dVar2.f743a.getCompoundPaddingRight() + dVar2.f743a.getLayout().getWidth() + dVar2.f743a.getCompoundPaddingLeft();
                height = dVar2.f743a.getCompoundPaddingBottom() + dVar2.f743a.getLayout().getHeight() + dVar2.f743a.getCompoundPaddingTop();
            }
            if (!(width == dVar2.f745c && height == dVar2.f746d)) {
                dVar2.f746d = height;
                dVar2.f745c = width;
                dVar2.f744b.b(new XD(dVar2.f743a.getId(), GA.a((float) width), GA.a((float) height)));
            }
        }
        ((UIManagerModule) ((ReactContext) getContext()).getNativeModule(UIManagerModule.class)).setViewLocalData(getId(), new fE(this));
    }

    /* renamed from: i */
    public void mo1563i() {
        this.f717c = true;
        requestFocus();
        this.f717c = false;
    }

    public void invalidateDrawable(Drawable drawable) {
        if (this.f733y) {
            Editable text = getText();
            for (VD vd : (RD[]) text.getSpans(0, text.length(), RD.class)) {
                if (vd.a == drawable) {
                    invalidate();
                }
            }
        }
        super.invalidateDrawable(drawable);
    }

    public boolean isLayoutRequested() {
        return false;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* renamed from: j */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo1566j() {
        /*
            r9 = this;
            java.lang.String r0 = r9.f726s3
            r1 = 4
            r2 = 3
            r3 = 1
            r4 = 5
            r5 = 2
            r6 = 6
            if (r0 == 0) goto L_0x0068
            r7 = -1
            int r8 = r0.hashCode()
            switch(r8) {
                case -1273775369: goto L_0x004f;
                case -906336856: goto L_0x0045;
                case 3304: goto L_0x003b;
                case 3089282: goto L_0x0031;
                case 3377907: goto L_0x0027;
                case 3387192: goto L_0x001d;
                case 3526536: goto L_0x0013;
                default: goto L_0x0012;
            }
        L_0x0012:
            goto L_0x0059
        L_0x0013:
            java.lang.String r8 = "send"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0059
            r0 = 5
            goto L_0x005a
        L_0x001d:
            java.lang.String r8 = "none"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0059
            r0 = 2
            goto L_0x005a
        L_0x0027:
            java.lang.String r8 = "next"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0059
            r0 = 1
            goto L_0x005a
        L_0x0031:
            java.lang.String r8 = "done"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0059
            r0 = 6
            goto L_0x005a
        L_0x003b:
            java.lang.String r8 = "go"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0059
            r0 = 0
            goto L_0x005a
        L_0x0045:
            java.lang.String r8 = "search"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0059
            r0 = 4
            goto L_0x005a
        L_0x004f:
            java.lang.String r8 = "previous"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0059
            r0 = 3
            goto L_0x005a
        L_0x0059:
            r0 = -1
        L_0x005a:
            switch(r0) {
                case 0: goto L_0x0066;
                case 1: goto L_0x0064;
                case 2: goto L_0x0062;
                case 3: goto L_0x0060;
                case 4: goto L_0x005e;
                case 5: goto L_0x0069;
                case 6: goto L_0x0068;
                default: goto L_0x005d;
            }
        L_0x005d:
            goto L_0x0068
        L_0x005e:
            r1 = 3
            goto L_0x0069
        L_0x0060:
            r1 = 7
            goto L_0x0069
        L_0x0062:
            r1 = 1
            goto L_0x0069
        L_0x0064:
            r1 = 5
            goto L_0x0069
        L_0x0066:
            r1 = 2
            goto L_0x0069
        L_0x0068:
            r1 = 6
        L_0x0069:
            boolean r0 = r9.f725r3
            if (r0 == 0) goto L_0x0074
            r0 = 33554432(0x2000000, float:9.403955E-38)
            r0 = r0 | r1
            r9.setImeOptions(r0)
            goto L_0x0077
        L_0x0074:
            r9.setImeOptions(r1)
        L_0x0077:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.textinput.ReactEditText.mo1566j():void");
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f733y) {
            Editable text = getText();
            for (VD vd : (RD[]) text.getSpans(0, text.length(), RD.class)) {
                vd.c.f();
            }
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        ReactContext reactContext = (ReactContext) getContext();
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        if (onCreateInputConnection != null && this.f734y3) {
            onCreateInputConnection = new YD(onCreateInputConnection, reactContext, this);
        }
        if (mo1561g() && mo1558d()) {
            editorInfo.imeOptions &= -1073741825;
        }
        return onCreateInputConnection;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f733y) {
            Editable text = getText();
            for (VD vd : (RD[]) text.getSpans(0, text.length(), RD.class)) {
                vd.c.g();
            }
        }
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        if (this.f733y) {
            Editable text = getText();
            for (VD vd : (RD[]) text.getSpans(0, text.length(), RD.class)) {
                vd.c.f();
            }
        }
    }

    public void onFocusChanged(boolean z, int i, Rect rect) {
        ReactTextInputManager.C0175f fVar;
        super.onFocusChanged(z, i, rect);
        if (z && (fVar = this.f727t3) != null) {
            fVar.mo1657a(getSelectionStart(), getSelectionEnd());
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 66 || mo1561g()) {
            return super.onKeyUp(i, keyEvent);
        }
        this.f715a.hideSoftInputFromWindow(getWindowToken(), 0);
        return true;
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        mo1562h();
    }

    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        ReactTextInputManager.C0174e eVar = this.f729v3;
        if (eVar != null) {
            ReactTextInputManager.C0174e eVar2 = eVar;
            if (eVar2.f749c != i || eVar2.f750d != i2) {
                eVar2.f748b.b(qD.a(eVar2.f747a.getId(), ScrollEventType.SCROLL, i, i2, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, 0, 0, eVar2.f747a.getWidth(), eVar2.f747a.getHeight()));
                eVar2.f749c = i;
                eVar2.f750d = i2;
            }
        }
    }

    public void onSelectionChanged(int i, int i2) {
        super.onSelectionChanged(i, i2);
        if (this.f727t3 != null && hasFocus()) {
            this.f727t3.mo1657a(i, i2);
        }
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        if (this.f733y) {
            Editable text = getText();
            for (VD vd : (RD[]) text.getSpans(0, text.length(), RD.class)) {
                vd.c.g();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f732x3 = true;
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (action == 2 && this.f732x3) {
            if (!canScrollVertically(-1) && !canScrollVertically(1) && !canScrollHorizontally(-1) && !canScrollHorizontally(1)) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
            this.f732x3 = false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void removeTextChangedListener(TextWatcher textWatcher) {
        ArrayList<TextWatcher> arrayList = this.f722p;
        if (arrayList != null) {
            arrayList.remove(textWatcher);
            if (this.f722p.isEmpty()) {
                this.f722p = null;
                if (this.f723q == null) {
                    this.f723q = new C0169c((C0167a) null);
                }
                super.removeTextChangedListener(this.f723q);
            }
        }
    }

    public boolean requestFocus(int i, Rect rect) {
        if (isFocused()) {
            return true;
        }
        if (!this.f717c) {
            return false;
        }
        setFocusableInTouchMode(true);
        boolean requestFocus = super.requestFocus(i, rect);
        this.f715a.showSoftInput(this, 0);
        return requestFocus;
    }

    public void setAllowFontScaling(boolean z) {
        QD qd = this.f735z3;
        if (qd.a != z) {
            qd.a = z;
            mo1549a();
        }
    }

    public void setBackgroundColor(int i) {
        this.f714A3.a(i);
    }

    public void setBlurOnSubmit(Boolean bool) {
        this.f724q3 = bool;
    }

    public void setBorderColor(int i, float f, float f2) {
        this.f714A3.a().mo1703a(i, f, f2);
    }

    public void setBorderRadius(float f) {
        this.f714A3.a().mo1701a(f);
    }

    public void setBorderStyle(String str) {
        this.f714A3.a().mo1705a(str);
    }

    public void setBorderWidth(int i, float f) {
        this.f714A3.a().mo1702a(i, f);
    }

    public void setContentSizeWatcher(WD wd) {
        this.f728u3 = wd;
    }

    public void setDisableFullscreenUI(boolean z) {
        this.f725r3 = z;
        mo1566j();
    }

    public void setFontSize(float f) {
        this.f735z3.b = f;
        mo1549a();
    }

    public void setInputType(int i) {
        Typeface typeface = super.getTypeface();
        super.setInputType(i);
        this.f731x = i;
        super.setTypeface(typeface);
        C0168b bVar = this.f730w3;
        bVar.f736a = i;
        setKeyListener(bVar);
    }

    public void setLetterSpacingPt(float f) {
        this.f735z3.d = f;
        mo1549a();
    }

    public void setMaxFontSizeMultiplier(float f) {
        QD qd = this.f735z3;
        if (f != qd.e) {
            qd.a(f);
            mo1549a();
        }
    }

    public void setOnKeyPress(boolean z) {
        this.f734y3 = z;
    }

    public void setReturnKeyType(String str) {
        this.f726s3 = str;
        mo1566j();
    }

    public void setScrollWatcher(jE jEVar) {
        this.f729v3 = jEVar;
    }

    public void setSelection(int i, int i2) {
        if (this.f721n >= this.f720k) {
            super.setSelection(i, i2);
        }
    }

    public void setSelectionWatcher(kE kEVar) {
        this.f727t3 = kEVar;
    }

    public boolean verifyDrawable(Drawable drawable) {
        if (this.f733y) {
            Editable text = getText();
            for (VD vd : (RD[]) text.getSpans(0, text.length(), RD.class)) {
                if (vd.a == drawable) {
                    return true;
                }
            }
        }
        return super.verifyDrawable(drawable);
    }

    /* renamed from: b */
    public void mo1554b(int i) {
        if (i == 0) {
            i = this.f719e;
        }
        setGravity(i | (getGravity() & -113));
    }

    /* renamed from: c */
    public void mo1555c() {
        if (getInputType() != this.f731x) {
            int selectionStart = getSelectionStart();
            int selectionEnd = getSelectionEnd();
            setInputType(this.f731x);
            setSelection(selectionStart, selectionEnd);
        }
    }

    public void setBorderRadius(float f, int i) {
        this.f714A3.a().mo1707b(f, i);
    }

    /* renamed from: a */
    public void mo1550a(int i) {
        if (i == 0) {
            i = this.f718d;
        }
        setGravity(i | (getGravity() & -8 & -8388616));
    }

    /* renamed from: a */
    public void mo1549a() {
        setTextSize(0, (float) this.f735z3.a());
        if (Build.VERSION.SDK_INT >= 21) {
            float b = this.f735z3.b();
            if (!Float.isNaN(b)) {
                setLetterSpacing(b);
            }
        }
    }
}
