package com.facebook.react.uimanager;

/* compiled from: PG */
public enum AccessibilityDelegateUtil$AccessibilityRole {
    NONE,
    BUTTON,
    LINK,
    SEARCH,
    IMAGE,
    IMAGEBUTTON,
    KEYBOARDKEY,
    TEXT,
    ADJUSTABLE,
    SUMMARY,
    HEADER;

    public static AccessibilityDelegateUtil$AccessibilityRole fromValue(String str) {
        for (AccessibilityDelegateUtil$AccessibilityRole accessibilityDelegateUtil$AccessibilityRole : values()) {
            if (accessibilityDelegateUtil$AccessibilityRole.name().equalsIgnoreCase(str)) {
                return accessibilityDelegateUtil$AccessibilityRole;
            }
        }
        throw new IllegalArgumentException(Eo.a("Invalid accessibility role value: ", str));
    }

    public static String getValue(AccessibilityDelegateUtil$AccessibilityRole accessibilityDelegateUtil$AccessibilityRole) {
        switch (accessibilityDelegateUtil$AccessibilityRole.ordinal()) {
            case 0:
                return null;
            case 1:
                return "android.widget.Button";
            case 2:
                return "android.widget.ViewGroup";
            case 3:
                return "android.widget.EditText";
            case 4:
            case 5:
                return "android.widget.ImageView";
            case 6:
                return "android.inputmethodservice.Keyboard$Key";
            case 7:
                return "android.widget.ViewGroup";
            case 8:
                return "android.widget.SeekBar";
            case 9:
            case 10:
                return "android.widget.ViewGroup";
            default:
                throw new IllegalArgumentException("Invalid accessibility role value: " + accessibilityDelegateUtil$AccessibilityRole);
        }
    }
}
