package com.microsoft.bing.usbsdk.api.utils;

import android.content.Context;
import com.microsoft.bing.commonlib.customize.Product;
import com.microsoft.bing.commonlib.instrumentation.TelemetryMgrBase;
import com.microsoft.bing.commonlib.model.search.SourceType;
import com.microsoft.bing.commonlib.model.searchengine.PrepopulatedEngine;
import com.microsoft.bing.commonlib.model.searchengine.SearchEnginesData;
import com.microsoft.bing.commonuilib.marketcode.MarketCodeManager;
import com.microsoft.bing.partnercodelib.api.PartnerCodeManager;
import com.microsoft.bing.usbsdk.api.BingClientManager;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
public class USBTelemetryMgr extends TelemetryMgrBase {
    public static volatile Context sAppContext;
    public static volatile USBTelemetryMgr sTelemetryMgrInstance;

    public static USBTelemetryMgr getInstance() {
        if (sTelemetryMgrInstance == null) {
            synchronized (USBTelemetryMgr.class) {
                if (sTelemetryMgrInstance == null) {
                    sTelemetryMgrInstance = new USBTelemetryMgr();
                }
            }
        }
        return sTelemetryMgrInstance;
    }

    public static void initialize(Context context) {
        sAppContext = context;
    }

    public void appendBasicProperties(Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        PrepopulatedEngine engineById = SearchEnginesData.getEngineById(BingClientManager.getInstance().getConfiguration().getSearchEngineID());
        String str = null;
        if (engineById != null) {
            str = engineById.getName();
        }
        if (str != null) {
            map.put("BingClientSDK.EventKey.SearchEngine", str);
        }
        map.put("BingClientSDK.EventKey.Market", MarketCodeManager.getInstance().getMarketCode());
        map.put("BingClientSDK.EventKey.PartnerCode", PartnerCodeManager.getInstance().getPartnerCode(sAppContext));
    }

    public boolean enableCache() {
        return Product.getInstance().IS_ENABLE_CACHE_INSTRUMENTATION();
    }

    public Map<String, String> get_KEY_OF_EVENT_WIDGET_OPEN_FROM(SourceType sourceType) {
        return get_KEY_OF_EVENT_WIDGET_OPEN_FROM(sourceType, false);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0030, code lost:
        if (r5 != false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0038, code lost:
        if (r5 != false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0040, code lost:
        if (r5 != false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0048, code lost:
        if (r5 != false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0050, code lost:
        if (r5 != false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001d, code lost:
        if (r5 != false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001f, code lost:
        r0.put("query search from", r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0028, code lost:
        if (r5 != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.String, java.lang.String> get_KEY_OF_EVENT_WIDGET_OPEN_FROM(com.microsoft.bing.commonlib.model.search.SourceType r4, boolean r5) {
        /*
            r3 = this;
            if (r4 != 0) goto L_0x0004
            com.microsoft.bing.commonlib.model.search.SourceType r4 = com.microsoft.bing.commonlib.model.search.SourceType.FROM_UNKNOWN
        L_0x0004:
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            int[] r1 = com.microsoft.bing.usbsdk.api.utils.USBTelemetryMgr.a.a
            int r4 = r4.ordinal()
            r4 = r1[r4]
            java.lang.String r1 = "query search from"
            java.lang.String r2 = "widget open from"
            switch(r4) {
                case 1: goto L_0x004b;
                case 2: goto L_0x0043;
                case 3: goto L_0x003b;
                case 4: goto L_0x0033;
                case 5: goto L_0x002b;
                case 6: goto L_0x0023;
                default: goto L_0x0018;
            }
        L_0x0018:
            java.lang.String r4 = "others"
            r0.put(r2, r4)
            if (r5 == 0) goto L_0x0053
        L_0x001f:
            r0.put(r1, r4)
            goto L_0x0053
        L_0x0023:
            java.lang.String r4 = "setting"
            r0.put(r2, r4)
            if (r5 == 0) goto L_0x0053
            goto L_0x001f
        L_0x002b:
            java.lang.String r4 = "pull_down"
            r0.put(r2, r4)
            if (r5 == 0) goto L_0x0053
            goto L_0x001f
        L_0x0033:
            java.lang.String r4 = "widget"
            r0.put(r2, r4)
            if (r5 == 0) goto L_0x0053
            goto L_0x001f
        L_0x003b:
            java.lang.String r4 = "drawer"
            r0.put(r2, r4)
            if (r5 == 0) goto L_0x0053
            goto L_0x001f
        L_0x0043:
            java.lang.String r4 = "dock"
            r0.put(r2, r4)
            if (r5 == 0) goto L_0x0053
            goto L_0x001f
        L_0x004b:
            java.lang.String r4 = "minus"
            r0.put(r2, r4)
            if (r5 == 0) goto L_0x0053
            goto L_0x001f
        L_0x0053:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.bing.usbsdk.api.utils.USBTelemetryMgr.get_KEY_OF_EVENT_WIDGET_OPEN_FROM(com.microsoft.bing.commonlib.model.search.SourceType, boolean):java.util.Map");
    }
}
