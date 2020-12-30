package com.facebook.react.views.textinput;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.citrix.mdx.common.MDXDictionary;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.views.scroll.ScrollEventType;
import com.google.protobuf.ByteString;
import com.microsoft.bing.visualsearch.camerasearchv2.content.model.OCRItem$OCRActionType;
import com.microsoft.identity.common.internal.providers.oauth2.IDToken;
import hy;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Map;

@Ay(name = "AndroidTextInput")
/* compiled from: PG */
public class ReactTextInputManager extends BaseViewManager<ReactEditText, xA> {
    public static final int BLUR_TEXT_INPUT = 2;
    public static final InputFilter[] EMPTY_FILTERS = new InputFilter[0];
    public static final int FOCUS_TEXT_INPUT = 1;
    public static final int IME_ACTION_ID = 1648;
    public static final int INPUT_TYPE_KEYBOARD_DECIMAL_PAD = 8194;
    public static final int INPUT_TYPE_KEYBOARD_NUMBERED = 12290;
    public static final int INPUT_TYPE_KEYBOARD_NUMBER_PAD = 2;
    public static final String KEYBOARD_TYPE_DECIMAL_PAD = "decimal-pad";
    public static final String KEYBOARD_TYPE_EMAIL_ADDRESS = "email-address";
    public static final int KEYBOARD_TYPE_FLAGS = 12339;
    public static final String KEYBOARD_TYPE_NUMBER_PAD = "number-pad";
    public static final String KEYBOARD_TYPE_NUMERIC = "numeric";
    public static final String KEYBOARD_TYPE_PHONE_PAD = "phone-pad";
    public static final String KEYBOARD_TYPE_VISIBLE_PASSWORD = "visible-password";
    public static final int PASSWORD_VISIBILITY_FLAG = 16;
    public static final String REACT_CLASS = "AndroidTextInput";
    public static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};
    public static final String TAG = "ReactTextInputManager";
    public static final int UNSET = -1;

    /* renamed from: com.facebook.react.views.textinput.ReactTextInputManager$a */
    /* compiled from: PG */
    public class C0170a implements View.OnLongClickListener {

        /* renamed from: a */
        public final /* synthetic */ boolean f738a;

        public C0170a(ReactTextInputManager reactTextInputManager, boolean z) {
            this.f738a = z;
        }

        public boolean onLongClick(View view) {
            return this.f738a;
        }
    }

    /* renamed from: com.facebook.react.views.textinput.ReactTextInputManager$b */
    /* compiled from: PG */
    public class C0171b implements View.OnFocusChangeListener {

        /* renamed from: a */
        public final /* synthetic */ WA f739a;

        /* renamed from: b */
        public final /* synthetic */ ReactEditText f740b;

        public C0171b(ReactTextInputManager reactTextInputManager, WA wa, ReactEditText reactEditText) {
            this.f739a = wa;
            this.f740b = reactEditText;
        }

        public void onFocusChange(View view, boolean z) {
            qC eventDispatcher = ((UIManagerModule) this.f739a.getNativeModule(UIManagerModule.class)).getEventDispatcher();
            if (z) {
                eventDispatcher.b(new dE(this.f740b.getId()));
                return;
            }
            eventDispatcher.b(new aE(this.f740b.getId()));
            eventDispatcher.b(new bE(this.f740b.getId(), this.f740b.getText().toString()));
        }
    }

    /* renamed from: com.facebook.react.views.textinput.ReactTextInputManager$c */
    /* compiled from: PG */
    public class C0172c implements TextView.OnEditorActionListener {

        /* renamed from: a */
        public final /* synthetic */ ReactEditText f741a;

        /* renamed from: b */
        public final /* synthetic */ WA f742b;

        public C0172c(ReactTextInputManager reactTextInputManager, ReactEditText reactEditText, WA wa) {
            this.f741a = reactEditText;
            this.f742b = wa;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if ((i & 255) > 0 || i == 0) {
                boolean d = this.f741a.mo1558d();
                boolean z = (this.f741a.getInputType() & 131072) != 0;
                ((UIManagerModule) this.f742b.getNativeModule(UIManagerModule.class)).getEventDispatcher().b(new iE(this.f741a.getId(), this.f741a.getText().toString()));
                if (d) {
                    this.f741a.clearFocus();
                }
                if (d || !z) {
                    return true;
                }
                return false;
            } else if (i == 5) {
                return textView.focusSearch(2) != null && !textView.requestFocus(2);
            } else {
                return true;
            }
        }
    }

    /* renamed from: com.facebook.react.views.textinput.ReactTextInputManager$d */
    /* compiled from: PG */
    public class C0173d implements WD {

        /* renamed from: a */
        public ReactEditText f743a;

        /* renamed from: b */
        public qC f744b;

        /* renamed from: c */
        public int f745c = 0;

        /* renamed from: d */
        public int f746d = 0;

        public C0173d(ReactTextInputManager reactTextInputManager, ReactEditText reactEditText) {
            this.f743a = reactEditText;
            this.f744b = ((UIManagerModule) ((ReactContext) reactEditText.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher();
        }
    }

    /* renamed from: com.facebook.react.views.textinput.ReactTextInputManager$e */
    /* compiled from: PG */
    public class C0174e implements jE {

        /* renamed from: a */
        public ReactEditText f747a;

        /* renamed from: b */
        public qC f748b;

        /* renamed from: c */
        public int f749c;

        /* renamed from: d */
        public int f750d;

        public C0174e(ReactTextInputManager reactTextInputManager, ReactEditText reactEditText) {
            this.f747a = reactEditText;
            this.f748b = ((UIManagerModule) ((ReactContext) reactEditText.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher();
        }
    }

    /* renamed from: com.facebook.react.views.textinput.ReactTextInputManager$f */
    /* compiled from: PG */
    public class C0175f implements kE {

        /* renamed from: a */
        public ReactEditText f751a;

        /* renamed from: b */
        public qC f752b;

        /* renamed from: c */
        public int f753c;

        /* renamed from: d */
        public int f754d;

        public C0175f(ReactTextInputManager reactTextInputManager, ReactEditText reactEditText) {
            this.f751a = reactEditText;
            this.f752b = ((UIManagerModule) ((ReactContext) reactEditText.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher();
        }

        /* renamed from: a */
        public void mo1657a(int i, int i2) {
            if (this.f753c != i || this.f754d != i2) {
                this.f752b.b(new gE(this.f751a.getId(), i, i2));
                this.f753c = i;
                this.f754d = i2;
            }
        }
    }

    /* renamed from: com.facebook.react.views.textinput.ReactTextInputManager$g */
    /* compiled from: PG */
    public class C0176g implements TextWatcher {

        /* renamed from: a */
        public qC f755a;

        /* renamed from: b */
        public ReactEditText f756b;

        /* renamed from: c */
        public String f757c = null;

        public C0176g(ReactTextInputManager reactTextInputManager, ReactContext reactContext, ReactEditText reactEditText) {
            this.f755a = ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
            this.f756b = reactEditText;
        }

        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.f757c = charSequence.toString();
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (i3 != 0 || i2 != 0) {
                Kw.a(this.f757c);
                String substring = charSequence.toString().substring(i, i + i3);
                int i4 = i + i2;
                String substring2 = this.f757c.substring(i, i4);
                if (i3 != i2 || !substring.equals(substring2)) {
                    this.f755a.b(new ZD(this.f756b.getId(), charSequence.toString(), this.f756b.mo1560f()));
                    this.f755a.b(new cE(this.f756b.getId(), substring, substring2, i, i4));
                }
            }
        }
    }

    public static void checkPasswordType(ReactEditText reactEditText) {
        if ((reactEditText.mo1559e() & INPUT_TYPE_KEYBOARD_NUMBERED) != 0 && (reactEditText.mo1559e() & ByteString.CONCATENATE_BY_COPY_SIZE) != 0) {
            updateStagedInputTypeFlag(reactEditText, ByteString.CONCATENATE_BY_COPY_SIZE, 16);
        }
    }

    public static int parseNumericFontWeight(String str) {
        if (str.length() != 3 || !str.endsWith("00") || str.charAt(0) > '9' || str.charAt(0) < '1') {
            return -1;
        }
        return (str.charAt(0) - '0') * 100;
    }

    private void setCursorColor(ReactEditText reactEditText, Integer num) {
        try {
            Field declaredField = TextView.class.getDeclaredField("mCursorDrawableRes");
            declaredField.setAccessible(true);
            int i = declaredField.getInt(reactEditText);
            if (i != 0) {
                Drawable c = U5.c(reactEditText.getContext(), i);
                if (num != null) {
                    c.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
                }
                Drawable[] drawableArr = {c, c};
                Field declaredField2 = TextView.class.getDeclaredField("mEditor");
                declaredField2.setAccessible(true);
                Object obj = declaredField2.get(reactEditText);
                Field declaredField3 = obj.getClass().getDeclaredField("mCursorDrawable");
                declaredField3.setAccessible(true);
                declaredField3.set(obj, drawableArr);
            }
        } catch (IllegalAccessException | NoSuchFieldException unused) {
        }
    }

    public static void updateStagedInputTypeFlag(ReactEditText reactEditText, int i, int i2) {
        reactEditText.mo1556c(((i ^ -1) & reactEditText.mo1559e()) | i2);
    }

    public Map<String, Integer> getCommandsMap() {
        return hy.a("focusTextInput", 1, "blurTextInput", 2);
    }

    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        hy.a a = hy.a();
        a.a("topSubmitEditing", hy.a("phasedRegistrationNames", hy.a("bubbled", "onSubmitEditing", "captured", "onSubmitEditingCapture")));
        a.a("topEndEditing", hy.a("phasedRegistrationNames", hy.a("bubbled", "onEndEditing", "captured", "onEndEditingCapture")));
        a.a("topTextInput", hy.a("phasedRegistrationNames", hy.a("bubbled", "onTextInput", "captured", "onTextInputCapture")));
        a.a("topFocus", hy.a("phasedRegistrationNames", hy.a("bubbled", "onFocus", "captured", "onFocusCapture")));
        a.a("topBlur", hy.a("phasedRegistrationNames", hy.a("bubbled", "onBlur", "captured", "onBlurCapture")));
        a.a("topKeyPress", hy.a("phasedRegistrationNames", hy.a("bubbled", "onKeyPress", "captured", "onKeyPressCapture")));
        return a.a();
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        hy.a a = hy.a();
        a.a(ScrollEventType.getJSEventName(ScrollEventType.SCROLL), hy.a("registrationName", "onScroll"));
        return a.a();
    }

    public Map getExportedViewConstants() {
        return hy.a("AutoCapitalizationType", hy.a(OCRItem$OCRActionType.OCR_NONE, 0, "characters", 4096, "words", Integer.valueOf(ByteString.MAX_READ_FROM_CHUNK_SIZE), "sentences", 16384));
    }

    public String getName() {
        return REACT_CLASS;
    }

    public Class<? extends xA> getShadowNodeClass() {
        return hE.class;
    }

    @eC(defaultBoolean = true, name = "allowFontScaling")
    public void setAllowFontScaling(ReactEditText reactEditText, boolean z) {
        reactEditText.setAllowFontScaling(z);
    }

    @eC(name = "autoCapitalize")
    public void setAutoCapitalize(ReactEditText reactEditText, int i) {
        updateStagedInputTypeFlag(reactEditText, 28672, i);
    }

    @eC(name = "autoCorrect")
    public void setAutoCorrect(ReactEditText reactEditText, Boolean bool) {
        updateStagedInputTypeFlag(reactEditText, 557056, bool != null ? bool.booleanValue() ? 32768 : 524288 : 0);
    }

    @eC(name = "blurOnSubmit")
    public void setBlurOnSubmit(ReactEditText reactEditText, Boolean bool) {
        reactEditText.setBlurOnSubmit(bool);
    }

    @fC(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(ReactEditText reactEditText, int i, Integer num) {
        float f = Float.NaN;
        float intValue = num == null ? Float.NaN : (float) (num.intValue() & 16777215);
        if (num != null) {
            f = (float) (num.intValue() >>> 24);
        }
        reactEditText.setBorderColor(SPACING_TYPES[i], intValue, f);
    }

    @fC(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactEditText reactEditText, int i, float f) {
        if (!eF.a(f)) {
            f = GA.b(f);
        }
        if (i == 0) {
            reactEditText.setBorderRadius(f);
        } else {
            reactEditText.setBorderRadius(f, i - 1);
        }
    }

    @eC(name = "borderStyle")
    public void setBorderStyle(ReactEditText reactEditText, String str) {
        reactEditText.setBorderStyle(str);
    }

    @fC(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(ReactEditText reactEditText, int i, float f) {
        if (!eF.a(f)) {
            f = GA.b(f);
        }
        reactEditText.setBorderWidth(SPACING_TYPES[i], f);
    }

    @eC(defaultBoolean = false, name = "caretHidden")
    public void setCaretHidden(ReactEditText reactEditText, boolean z) {
        reactEditText.setCursorVisible(!z);
    }

    @eC(customType = "Color", name = "color")
    public void setColor(ReactEditText reactEditText, Integer num) {
        if (num == null) {
            reactEditText.setTextColor(AD.a(reactEditText.getContext(), 16842904));
        } else {
            reactEditText.setTextColor(num.intValue());
        }
    }

    @eC(defaultBoolean = false, name = "contextMenuHidden")
    public void setContextMenuHidden(ReactEditText reactEditText, boolean z) {
        reactEditText.setOnLongClickListener(new C0170a(this, z));
    }

    @eC(defaultBoolean = false, name = "disableFullscreenUI")
    public void setDisableFullscreenUI(ReactEditText reactEditText, boolean z) {
        reactEditText.setDisableFullscreenUI(z);
    }

    @eC(defaultBoolean = true, name = "editable")
    public void setEditable(ReactEditText reactEditText, boolean z) {
        reactEditText.setEnabled(z);
    }

    @eC(name = "fontFamily")
    public void setFontFamily(ReactEditText reactEditText, String str) {
        reactEditText.setTypeface(FD.a().a(str, reactEditText.getTypeface() != null ? reactEditText.getTypeface().getStyle() : 0, reactEditText.getContext().getAssets()));
    }

    @eC(defaultFloat = 14.0f, name = "fontSize")
    public void setFontSize(ReactEditText reactEditText, float f) {
        reactEditText.setFontSize(f);
    }

    @eC(name = "fontStyle")
    public void setFontStyle(ReactEditText reactEditText, String str) {
        int i;
        if ("italic".equals(str)) {
            i = 2;
        } else {
            i = "normal".equals(str) ? 0 : -1;
        }
        Typeface typeface = reactEditText.getTypeface();
        if (typeface == null) {
            typeface = Typeface.DEFAULT;
        }
        if (i != typeface.getStyle()) {
            reactEditText.setTypeface(typeface, i);
        }
    }

    @eC(name = "fontWeight")
    public void setFontWeight(ReactEditText reactEditText, String str) {
        int i = -1;
        int parseNumericFontWeight = str != null ? parseNumericFontWeight(str) : -1;
        if (parseNumericFontWeight >= 500 || "bold".equals(str)) {
            i = 1;
        } else if ("normal".equals(str) || (parseNumericFontWeight != -1 && parseNumericFontWeight < 500)) {
            i = 0;
        }
        Typeface typeface = reactEditText.getTypeface();
        if (typeface == null) {
            typeface = Typeface.DEFAULT;
        }
        if (i != typeface.getStyle()) {
            reactEditText.setTypeface(typeface, i);
        }
    }

    @eC(name = "importantForAutofill")
    public void setImportantForAutofill(ReactEditText reactEditText, String str) {
        if (Build.VERSION.SDK_INT >= 26) {
            int i = 0;
            if ("no".equals(str)) {
                i = 2;
            } else if ("noExcludeDescendants".equals(str)) {
                i = 8;
            } else if ("yes".equals(str)) {
                i = 1;
            } else if ("yesExcludeDescendants".equals(str)) {
                i = 4;
            }
            reactEditText.setImportantForAutofill(i);
        }
    }

    @eC(name = "inlineImageLeft")
    public void setInlineImageLeft(ReactEditText reactEditText, String str) {
        reactEditText.setCompoundDrawablesWithIntrinsicBounds(eD.a().a(reactEditText.getContext(), str), 0, 0, 0);
    }

    @eC(name = "inlineImagePadding")
    public void setInlineImagePadding(ReactEditText reactEditText, int i) {
        reactEditText.setCompoundDrawablePadding(i);
    }

    @eC(name = "keyboardType")
    public void setKeyboardType(ReactEditText reactEditText, String str) {
        int i;
        if (KEYBOARD_TYPE_NUMERIC.equalsIgnoreCase(str)) {
            i = INPUT_TYPE_KEYBOARD_NUMBERED;
        } else if (KEYBOARD_TYPE_NUMBER_PAD.equalsIgnoreCase(str)) {
            i = 2;
        } else if (KEYBOARD_TYPE_DECIMAL_PAD.equalsIgnoreCase(str)) {
            i = INPUT_TYPE_KEYBOARD_DECIMAL_PAD;
        } else if (KEYBOARD_TYPE_EMAIL_ADDRESS.equalsIgnoreCase(str)) {
            i = 33;
        } else if (KEYBOARD_TYPE_PHONE_PAD.equalsIgnoreCase(str)) {
            i = 3;
        } else {
            i = KEYBOARD_TYPE_VISIBLE_PASSWORD.equalsIgnoreCase(str) ? 144 : 1;
        }
        updateStagedInputTypeFlag(reactEditText, KEYBOARD_TYPE_FLAGS, i);
        checkPasswordType(reactEditText);
    }

    @eC(defaultFloat = 0.0f, name = "letterSpacing")
    public void setLetterSpacing(ReactEditText reactEditText, float f) {
        reactEditText.setLetterSpacingPt(f);
    }

    @eC(defaultFloat = Float.NaN, name = "maxFontSizeMultiplier")
    public void setMaxFontSizeMultiplier(ReactEditText reactEditText, float f) {
        reactEditText.setMaxFontSizeMultiplier(f);
    }

    @eC(name = "maxLength")
    public void setMaxLength(ReactEditText reactEditText, Integer num) {
        InputFilter[] filters = reactEditText.getFilters();
        InputFilter[] inputFilterArr = EMPTY_FILTERS;
        if (num == null) {
            if (filters.length > 0) {
                LinkedList linkedList = new LinkedList();
                for (int i = 0; i < filters.length; i++) {
                    if (!(filters[i] instanceof InputFilter.LengthFilter)) {
                        linkedList.add(filters[i]);
                    }
                }
                if (!linkedList.isEmpty()) {
                    inputFilterArr = (InputFilter[]) linkedList.toArray(new InputFilter[linkedList.size()]);
                }
            }
        } else if (filters.length > 0) {
            boolean z = false;
            for (int i2 = 0; i2 < filters.length; i2++) {
                if (filters[i2] instanceof InputFilter.LengthFilter) {
                    filters[i2] = new InputFilter.LengthFilter(num.intValue());
                    z = true;
                }
            }
            if (!z) {
                inputFilterArr = new InputFilter[(filters.length + 1)];
                System.arraycopy(filters, 0, inputFilterArr, 0, filters.length);
                filters[filters.length] = new InputFilter.LengthFilter(num.intValue());
            } else {
                inputFilterArr = filters;
            }
        } else {
            inputFilterArr = new InputFilter[]{new InputFilter.LengthFilter(num.intValue())};
        }
        reactEditText.setFilters(inputFilterArr);
    }

    @eC(defaultBoolean = false, name = "multiline")
    public void setMultiline(ReactEditText reactEditText, boolean z) {
        int i = 0;
        int i2 = z ? 0 : 131072;
        if (z) {
            i = 131072;
        }
        updateStagedInputTypeFlag(reactEditText, i2, i);
    }

    @eC(defaultInt = 1, name = "numberOfLines")
    public void setNumLines(ReactEditText reactEditText, int i) {
        reactEditText.setLines(i);
    }

    @eC(defaultBoolean = false, name = "onContentSizeChange")
    public void setOnContentSizeChange(ReactEditText reactEditText, boolean z) {
        if (z) {
            reactEditText.setContentSizeWatcher(new C0173d(this, reactEditText));
        } else {
            reactEditText.setContentSizeWatcher((WD) null);
        }
    }

    @eC(defaultBoolean = false, name = "onKeyPress")
    public void setOnKeyPress(ReactEditText reactEditText, boolean z) {
        reactEditText.setOnKeyPress(z);
    }

    @eC(defaultBoolean = false, name = "onScroll")
    public void setOnScroll(ReactEditText reactEditText, boolean z) {
        if (z) {
            reactEditText.setScrollWatcher(new C0174e(this, reactEditText));
        } else {
            reactEditText.setScrollWatcher((jE) null);
        }
    }

    @eC(defaultBoolean = false, name = "onSelectionChange")
    public void setOnSelectionChange(ReactEditText reactEditText, boolean z) {
        if (z) {
            reactEditText.setSelectionWatcher(new C0175f(this, reactEditText));
        } else {
            reactEditText.setSelectionWatcher((kE) null);
        }
    }

    @eC(name = "placeholder")
    public void setPlaceholder(ReactEditText reactEditText, String str) {
        reactEditText.setHint(str);
    }

    @eC(customType = "Color", name = "placeholderTextColor")
    public void setPlaceholderTextColor(ReactEditText reactEditText, Integer num) {
        if (num == null) {
            reactEditText.setHintTextColor(AD.a(reactEditText.getContext(), 16842906));
        } else {
            reactEditText.setHintTextColor(num.intValue());
        }
    }

    @eC(name = "returnKeyLabel")
    public void setReturnKeyLabel(ReactEditText reactEditText, String str) {
        reactEditText.setImeActionLabel(str, IME_ACTION_ID);
    }

    @eC(name = "returnKeyType")
    public void setReturnKeyType(ReactEditText reactEditText, String str) {
        reactEditText.setReturnKeyType(str);
    }

    @eC(defaultBoolean = false, name = "secureTextEntry")
    public void setSecureTextEntry(ReactEditText reactEditText, boolean z) {
        int i = 0;
        int i2 = z ? 0 : 144;
        if (z) {
            i = ByteString.CONCATENATE_BY_COPY_SIZE;
        }
        updateStagedInputTypeFlag(reactEditText, i2, i);
        checkPasswordType(reactEditText);
    }

    @eC(defaultBoolean = false, name = "selectTextOnFocus")
    public void setSelectTextOnFocus(ReactEditText reactEditText, boolean z) {
        reactEditText.setSelectAllOnFocus(z);
    }

    @eC(name = "selection")
    public void setSelection(ReactEditText reactEditText, ReadableMap readableMap) {
        if (readableMap != null && readableMap.hasKey("start") && readableMap.hasKey("end")) {
            reactEditText.setSelection(readableMap.getInt("start"), readableMap.getInt("end"));
        }
    }

    @eC(customType = "Color", name = "selectionColor")
    public void setSelectionColor(ReactEditText reactEditText, Integer num) {
        if (num == null) {
            reactEditText.setHighlightColor(AD.a(reactEditText.getContext(), 16842905).getDefaultColor());
        } else {
            reactEditText.setHighlightColor(num.intValue());
        }
        setCursorColor(reactEditText, num);
    }

    @eC(name = "textAlign")
    public void setTextAlign(ReactEditText reactEditText, String str) {
        if ("justify".equals(str)) {
            if (Build.VERSION.SDK_INT >= 26) {
                reactEditText.setJustificationMode(1);
            }
            reactEditText.mo1550a(8388611);
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            reactEditText.setJustificationMode(0);
        }
        if (str == null || "auto".equals(str)) {
            reactEditText.mo1550a(0);
        } else if ("left".equals(str)) {
            reactEditText.mo1550a(8388611);
        } else if ("right".equals(str)) {
            reactEditText.mo1550a(8388613);
        } else if ("center".equals(str)) {
            reactEditText.mo1550a(1);
        } else {
            throw new JSApplicationIllegalArgumentException(Eo.a("Invalid textAlign: ", str));
        }
    }

    @eC(name = "textAlignVertical")
    public void setTextAlignVertical(ReactEditText reactEditText, String str) {
        if (str == null || "auto".equals(str)) {
            reactEditText.mo1554b(0);
        } else if ("top".equals(str)) {
            reactEditText.mo1554b(48);
        } else if ("bottom".equals(str)) {
            reactEditText.mo1554b(80);
        } else if ("center".equals(str)) {
            reactEditText.mo1554b(16);
        } else {
            throw new JSApplicationIllegalArgumentException(Eo.a("Invalid textAlignVertical: ", str));
        }
    }

    @eC(name = "autoComplete")
    public void setTextContentType(ReactEditText reactEditText, String str) {
        if (Build.VERSION.SDK_INT >= 26) {
            if (str == null) {
                reactEditText.setImportantForAutofill(2);
            } else if ("username".equals(str)) {
                reactEditText.setAutofillHints(new String[]{"username"});
            } else if (MDXDictionary.KEY_MDXCREDENTIALSDICTIONARY_PASSWORD.equals(str)) {
                reactEditText.setAutofillHints(new String[]{MDXDictionary.KEY_MDXCREDENTIALSDICTIONARY_PASSWORD});
            } else if (IDToken.EMAIL.equals(str)) {
                reactEditText.setAutofillHints(new String[]{"emailAddress"});
            } else if ("name".equals(str)) {
                reactEditText.setAutofillHints(new String[]{"name"});
            } else if (OCRItem$OCRActionType.OCR_PHONE.equals(str)) {
                reactEditText.setAutofillHints(new String[]{"phone"});
            } else if ("street-address".equals(str)) {
                reactEditText.setAutofillHints(new String[]{"postalAddress"});
            } else if ("postal-code".equals(str)) {
                reactEditText.setAutofillHints(new String[]{"postalCode"});
            } else if ("cc-number".equals(str)) {
                reactEditText.setAutofillHints(new String[]{"creditCardNumber"});
            } else if ("cc-csc".equals(str)) {
                reactEditText.setAutofillHints(new String[]{"creditCardSecurityCode"});
            } else if ("cc-exp".equals(str)) {
                reactEditText.setAutofillHints(new String[]{"creditCardExpirationDate"});
            } else if ("cc-exp-month".equals(str)) {
                reactEditText.setAutofillHints(new String[]{"creditCardExpirationMonth"});
            } else if ("cc-exp-year".equals(str)) {
                reactEditText.setAutofillHints(new String[]{"creditCardExpirationYear"});
            } else if ("off".equals(str)) {
                reactEditText.setImportantForAutofill(2);
            } else {
                throw new JSApplicationIllegalArgumentException(Eo.a("Invalid autocomplete option: ", str));
            }
        }
    }

    @eC(customType = "Color", name = "underlineColorAndroid")
    public void setUnderlineColor(ReactEditText reactEditText, Integer num) {
        Drawable background = reactEditText.getBackground();
        if (background.getConstantState() != null) {
            try {
                background = background.mutate();
            } catch (NullPointerException e) {
                pq.a(TAG, "NullPointerException when setting underlineColorAndroid for TextInput", e);
            }
        }
        if (num == null) {
            background.clearColorFilter();
        } else {
            background.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    public void addEventEmitters(WA wa, ReactEditText reactEditText) {
        reactEditText.addTextChangedListener(new C0176g(this, wa, reactEditText));
        reactEditText.setOnFocusChangeListener(new C0171b(this, wa, reactEditText));
        reactEditText.setOnEditorActionListener(new C0172c(this, reactEditText, wa));
    }

    public xA createShadowNodeInstance() {
        return new hE();
    }

    public ReactEditText createViewInstance(WA wa) {
        ReactEditText reactEditText = new ReactEditText(wa);
        reactEditText.setInputType(reactEditText.getInputType() & -131073);
        reactEditText.setReturnKeyType("done");
        return reactEditText;
    }

    public void onAfterUpdateTransaction(ReactEditText reactEditText) {
        super.onAfterUpdateTransaction(reactEditText);
        reactEditText.mo1555c();
    }

    public void receiveCommand(ReactEditText reactEditText, int i, ReadableArray readableArray) {
        if (i == 1) {
            reactEditText.mo1563i();
        } else if (i == 2) {
            reactEditText.mo1553b();
        }
    }

    public void updateExtraData(ReactEditText reactEditText, Object obj) {
        if (obj instanceof MD) {
            MD md = (MD) obj;
            reactEditText.setPadding((int) md.d, (int) md.e, (int) md.f, (int) md.g);
            if (md.c) {
                RD.a(md.a, reactEditText);
            }
            reactEditText.mo1551a(md);
        }
    }
}
