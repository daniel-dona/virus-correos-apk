package org.chromium.chrome.browser.preferences.website;

import java.io.Serializable;
import org.chromium.chrome.browser.preferences.PrefServiceBridge;

/* compiled from: PG */
public class ContentSettingException implements Serializable {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public final Integer mContentSetting;
    public final int mContentSettingType;
    public final String mPattern;
    public final String mSource;

    static {
        Class<ContentSettingException> cls = ContentSettingException.class;
    }

    public ContentSettingException(int i, String str, Integer num, String str2) {
        this.mContentSettingType = i;
        this.mPattern = str;
        this.mContentSetting = num;
        this.mSource = str2;
    }

    public static int getContentSettingsType(int i) {
        switch (i) {
            case 0:
                return 26;
            case 1:
                return 23;
            case 2:
                return 22;
            case 3:
                return 0;
            case 4:
                return 2;
            case 5:
                return 4;
            case 6:
                return 31;
            case 7:
                return 13;
            case 8:
                return 46;
            default:
                return -1;
        }
    }

    public Integer getContentSetting() {
        return this.mContentSetting;
    }

    public int getContentSettingType() {
        return this.mContentSettingType;
    }

    public String getPattern() {
        return this.mPattern;
    }

    public String getSource() {
        return this.mSource;
    }

    public void setContentSetting(Integer num) {
        PrefServiceBridge.m2758o0().nativeSetContentSettingForPattern(this.mContentSettingType, this.mPattern, num.intValue());
    }
}
