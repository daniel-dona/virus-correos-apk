package com.microsoft.bing.usbsdk.api;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.google.gson.Gson;
import com.microsoft.bing.answer.api.AnswerSDKManager;
import com.microsoft.bing.answer.api.asbeans.ASWebCurrency;
import com.microsoft.bing.answer.api.asbeans.ASWebEntity;
import com.microsoft.bing.answer.api.asbeans.ASWebFinance;
import com.microsoft.bing.answer.api.asbeans.ASWebHistory;
import com.microsoft.bing.answer.api.asbeans.ASWebWeather;
import com.microsoft.bing.answer.api.asbeans.ASWebsite;
import com.microsoft.bing.answer.api.asbeans.basic.ASWebNormal;
import com.microsoft.bing.answer.api.asbeans.basic.BingBusinessBaseBean;
import com.microsoft.bing.answer.api.contexts.builder.BasicASBuilderContext;
import com.microsoft.bing.answer.api.datamodels.BasicASAnswerData;
import com.microsoft.bing.answer.api.interfaces.IASAnswerData;
import com.microsoft.bing.answer.api.interfaces.IAnswerBuilderDelegate;
import com.microsoft.bing.answer.api.interfaces.callback.IAnswerViewEventCallback;
import com.microsoft.bing.answerlib.answers.IAnswerView;
import com.microsoft.bing.answerlib.interfaces.IBuilder;
import com.microsoft.bing.answerlib.interfaces.IContext;
import com.microsoft.bing.answerlib.interfaces.IData;
import com.microsoft.bing.answerlib.interfaces.ITransform;
import com.microsoft.bing.commonlib.componentchooser.BrowserChooser;
import com.microsoft.bing.commonlib.componentchooser.BrowserItem;
import com.microsoft.bing.commonlib.componentchooser.OpenBrowserCallBack;
import com.microsoft.bing.commonlib.customize.Product;
import com.microsoft.bing.commonlib.history.JournalStore;
import com.microsoft.bing.commonlib.imageloader.api.ImageLoader;
import com.microsoft.bing.commonlib.interfaces.ExperimentFeatureManager;
import com.microsoft.bing.commonlib.interfaces.TokenDelegate;
import com.microsoft.bing.commonlib.marketcode.MarketInfo;
import com.microsoft.bing.commonlib.model.searchengine.PrepopulatedEngine;
import com.microsoft.bing.commonlib.model.searchengine.SearchEnginesData;
import com.microsoft.bing.commonlib.preference.PreferenceUtil;
import com.microsoft.bing.commonlib.utils.CPUProfiler;
import com.microsoft.bing.commonlib.utils.CommonUtility;
import com.microsoft.bing.commonlib.utils.thread.ThreadUtils;
import com.microsoft.bing.commonuilib.marketcode.MarketCodeManager;
import com.microsoft.bing.usbsdk.api.Theme;
import com.microsoft.bing.usbsdk.api.config.BingClientConfig;
import com.microsoft.bing.usbsdk.api.interfaces.BingSDKDataProvider;
import com.microsoft.bing.usbsdk.api.interfaces.BingSearchViewDataSourceDelegate;
import com.microsoft.bing.usbsdk.api.interfaces.BingSearchViewEventListener;
import com.microsoft.bing.usbsdk.api.interfaces.PermissionRequestDelegate;
import com.microsoft.bing.usbsdk.api.interfaces.VisualAIDelegate;
import com.microsoft.bing.usbsdk.api.interfaces.VoiceAIDelegate;
import com.microsoft.bing.usbsdk.api.models.SearchEngineInfo;
import com.microsoft.bing.usbsdk.api.utils.USBTelemetryMgr;
import com.microsoft.bing.usbsdk.internal.utils.Utility;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/* compiled from: PG */
public final class BingClientManager {
    public static final String TAG = "libBingWidgets";
    public static volatile BingClientManager sBWidgetManagerInstance;
    public static final Object sCallBackLock = new Object();
    public Theme currentTheme;
    public boolean isAutoPageEnable = false;
    public SparseArray<IAnswerViewEventCallback<BasicASAnswerData>> mAnswerActionDelegates;
    public BingSDKDataProvider mBingSDKDataProvider;
    public BingSearchViewDataSourceDelegate mBingSearchViewDataSourceDelegate = new Y80();
    public BingSearchViewEventListener mBingSearchViewEventListener;
    public CPUProfiler mCPUProfiler2Init = null;
    public final BingClientConfig mConfig = new BingClientConfig();
    public ConnectivityManager mConnectivityManager;
    public final boolean mEnableSdkInitProfiler = false;
    public ExperimentFeatureManager mExperimentFeatureManager;
    public JournalStore mHistoryManager;
    public volatile int mInitFlag = -1;
    public y70 mOnPrimaryClipChangedListener;
    public PermissionRequestDelegate mPermissionRequestDelegate;
    public IAnswerBuilderDelegate mPluginAnswerBuilderDelegate;
    public TokenDelegate mTokenDelegate;
    public VisualAIDelegate mVisualAIDelegate;
    public VoiceAIDelegate mVoiceAIDelegate;
    public A70 mWifiCallback;

    public static BingClientManager getInstance() {
        if (sBWidgetManagerInstance == null) {
            synchronized (BingClientManager.class) {
                if (sBWidgetManagerInstance == null) {
                    sBWidgetManagerInstance = new BingClientManager();
                }
            }
        }
        return sBWidgetManagerInstance;
    }

    public IAnswerView buildAnswerView(int i, BasicASBuilderContext basicASBuilderContext) {
        return AnswerSDKManager.getInstance().buildAnswerView(i, basicASBuilderContext);
    }

    public <T11 extends BasicASBuilderContext, T22 extends IASAnswerData, T33 extends IAnswerView> T33 buildAnswerView(T11 t11, Class<? extends T22> cls, Class<? extends T33> cls2) {
        return AnswerSDKManager.getInstance().buildAnswerView(t11, cls, cls2);
    }

    public IAnswerView buildAnswerView(IASAnswerData iASAnswerData, Context context, BasicASBuilderContext basicASBuilderContext) {
        return AnswerSDKManager.getInstance().buildAnswerView(iASAnswerData, basicASBuilderContext);
    }

    public void clearAnswerActionDelegate() {
        SparseArray<IAnswerViewEventCallback<BasicASAnswerData>> sparseArray = this.mAnswerActionDelegates;
        if (sparseArray != null) {
            sparseArray.clear();
        }
    }

    public void clearSearchCache() {
        Z80.a().evictAll();
        a90.a().evictAll();
    }

    public void clearSearchHistory() {
        JournalStore journalStore = this.mHistoryManager;
        if (journalStore != null) {
            journalStore.removeAll();
        }
    }

    public void dismissChooseBrowserDialog(FragmentManager fragmentManager) {
        Fragment findFragmentByTag;
        if (fragmentManager != null && (findFragmentByTag = fragmentManager.findFragmentByTag("choose_browser_fragment")) != null) {
            fragmentManager.beginTransaction().remove(findFragmentByTag).commit();
        }
    }

    public void forceChangeBingSwitch(Context context, boolean z) {
        PrepopulatedEngine prepopulatedEngine;
        if (PreferenceUtil.getInstance(context).getBoolean("PREFERENCE_KEY_DISABLE_BING_SEARCH", false) != z) {
            PreferenceUtil.getInstance(context).saveBoolean("PREFERENCE_KEY_DISABLE_BING_SEARCH", z);
            if (!z) {
                prepopulatedEngine = SearchEnginesData.BING;
            } else {
                PrepopulatedEngine[] enginesByCountryCode = SearchEnginesData.getEnginesByCountryCode("CN");
                if (enginesByCountryCode != null && enginesByCountryCode.length > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (PrepopulatedEngine prepopulatedEngine2 : enginesByCountryCode) {
                        if (prepopulatedEngine2 != null && !prepopulatedEngine2.getKeyword().equals(SearchEnginesData.BING.getKeyword())) {
                            arrayList.add(prepopulatedEngine2);
                        }
                    }
                    int size = arrayList.size();
                    if (size > 0) {
                        prepopulatedEngine = (PrepopulatedEngine) arrayList.get(new Random().nextInt(size));
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            setSearchEngine(context, prepopulatedEngine.getKeyword());
        }
    }

    public String generateBingAuthWebCookies(List<String> list) {
        int indexOf;
        HashMap e = Eo.e("domain", ".bing.com");
        ArrayList arrayList = new ArrayList();
        for (String next : list) {
            String substring = next.substring(0, next.indexOf(";"));
            if (!TextUtils.isEmpty(substring) && (indexOf = substring.indexOf("=")) > 0) {
                HashMap hashMap = new HashMap();
                hashMap.put("key", substring.substring(0, indexOf));
                hashMap.put("value", substring.substring(indexOf + 1));
                arrayList.add(hashMap);
            }
        }
        e.put("cookies", arrayList);
        HashMap hashMap2 = new HashMap();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(e);
        hashMap2.put("cookie_set", arrayList2);
        MJ mj = new MJ();
        mj.l = false;
        String a = mj.a().mo2100a((Object) hashMap2);
        Eo.f("generateBingAuthWebCookies: ", a);
        return a;
    }

    public List<SearchEngineInfo> getAllPrepopulatedEngines() {
        List<PrepopulatedEngine> allPrepopulatedEngine = SearchEnginesData.getAllPrepopulatedEngine();
        ArrayList arrayList = new ArrayList();
        for (PrepopulatedEngine prepopulatedEngine : allPrepopulatedEngine) {
            arrayList.add(new SearchEngineInfo(prepopulatedEngine.getId(), prepopulatedEngine.getName()));
        }
        return arrayList;
    }

    public List<SearchEngineInfo> getAllSearchEnginesHost() {
        PrepopulatedEngine[] allEnginesHost = SearchEnginesData.getAllEnginesHost();
        ArrayList arrayList = new ArrayList();
        for (PrepopulatedEngine prepopulatedEngine : allEnginesHost) {
            arrayList.add(new SearchEngineInfo(prepopulatedEngine.getId(), prepopulatedEngine.getName()));
        }
        return arrayList;
    }

    public IAnswerViewEventCallback<BasicASAnswerData> getAnswerActionDelegate(int i) {
        SparseArray<IAnswerViewEventCallback<BasicASAnswerData>> sparseArray = this.mAnswerActionDelegates;
        if (sparseArray != null) {
            return (IAnswerViewEventCallback) sparseArray.get(i);
        }
        return null;
    }

    public String getAnswerInstrumentationType(BasicASAnswerData basicASAnswerData) {
        switch (basicASAnswerData.getTypeInGroup()) {
            case 4:
                return "AS.Web.Normal";
            case 5:
                return "AS.Web.Entity";
            case 6:
                return "AS.Web.Weather";
            case 7:
                return "AS.Web.Currency";
            case 8:
                return "AS.Web.Finance";
            case 9:
                return "AS.Web.Website";
            case 10:
                return "AS.Web.History";
            case 12:
                return "AS.MSB.Person";
            case 13:
                return "AS.MSB.Bookmark";
            case 15:
                return "AS.MSB.Building";
            case 16:
                return "AS.MSB.Qna";
            default:
                return "unknown";
        }
    }

    public Drawable getBackground() {
        BingSDKDataProvider bingSDKDataProvider = this.mBingSDKDataProvider;
        if (bingSDKDataProvider != null) {
            return bingSDKDataProvider.getSDKBackgroundDrawable();
        }
        return null;
    }

    public String getBingClientDefaultSettingsJson() {
        a aVar = new a();
        getConfiguration().getSearchEngineID();
        MarketInfo checkedItem = MarketCodeManager.getInstance().getCheckedItem();
        if (checkedItem != null) {
            int i = checkedItem.index;
        }
        return new Gson().mo2100a((Object) aVar);
    }

    public BingSearchViewDataSourceDelegate getBingSearchViewDataSourceDelegate() {
        BingSearchViewDataSourceDelegate bingSearchViewDataSourceDelegate;
        synchronized (sCallBackLock) {
            bingSearchViewDataSourceDelegate = this.mBingSearchViewDataSourceDelegate;
        }
        return bingSearchViewDataSourceDelegate;
    }

    public BingSearchViewEventListener getBingSearchViewEventListener() {
        BingSearchViewEventListener bingSearchViewEventListener;
        synchronized (sCallBackLock) {
            bingSearchViewEventListener = this.mBingSearchViewEventListener;
        }
        return bingSearchViewEventListener;
    }

    public LinkedHashSet<BrowserItem> getBrowserItemsFromDevice(Context context) {
        return new BrowserChooser().getAllComponentItems(context);
    }

    public BingClientConfig getConfiguration() {
        return this.mConfig;
    }

    public Theme getCurrentTheme() {
        if (this.currentTheme == null) {
            this.currentTheme = new Theme.Builder().setThemeType(1).build();
        }
        return this.currentTheme;
    }

    public SearchEngineInfo getDefaultSearchEngine() {
        PrepopulatedEngine prepopulatedEngine = SearchEnginesData.BING;
        return new SearchEngineInfo(prepopulatedEngine.getId(), prepopulatedEngine.getName());
    }

    public ExperimentFeatureManager getExperimentFeatureManager() {
        return this.mExperimentFeatureManager;
    }

    public String getFeatureConfig() {
        return new Gson().mo2100a((Object) getConfiguration().getFeatureCfg());
    }

    public JournalStore getHistoryManager() {
        return this.mHistoryManager;
    }

    public y70 getOnPrimaryClipChangedListener() {
        return this.mOnPrimaryClipChangedListener;
    }

    public PermissionRequestDelegate getPermissionRequestDelegate() {
        return this.mPermissionRequestDelegate;
    }

    public IAnswerBuilderDelegate getPluginAnswerBuilderDelegate() {
        return this.mPluginAnswerBuilderDelegate;
    }

    public String getSearchEngineKeyword() {
        PrepopulatedEngine engineById;
        BingClientConfig bingClientConfig = this.mConfig;
        if (bingClientConfig == null || (engineById = SearchEnginesData.getEngineById(bingClientConfig.getSearchEngineID())) == null) {
            return null;
        }
        return engineById.getKeyword();
    }

    public USBTelemetryMgr getTelemetryMgr() {
        return USBTelemetryMgr.getInstance();
    }

    public TokenDelegate getTokenDelegate() {
        return this.mTokenDelegate;
    }

    public BrowserItem getValidChooseBrowserItemBySpecifiedComponentName(ComponentName componentName, LinkedHashSet<BrowserItem> linkedHashSet) {
        return BrowserChooser.getChooseBrowserItemBySpecifiedComponentName(componentName, linkedHashSet);
    }

    public BrowserItem getValidChooseBrowserItemBySpecifiedComponentName(Context context, ComponentName componentName) {
        return BrowserChooser.getChooseBrowserItemBySpecifiedComponentName(context, componentName);
    }

    public List<SearchEngineInfo> getValidlySearchEngines() {
        PrepopulatedEngine[] prepopulatedEngineArr;
        String region = this.mConfig.getRegion();
        PrepopulatedEngine[] prepopulatedEngineArr2 = null;
        if (!TextUtils.isEmpty(region)) {
            prepopulatedEngineArr = SearchEnginesData.getEnginesByCountryCode(region);
            if (prepopulatedEngineArr == null || prepopulatedEngineArr.length < 1) {
                Log.i(TAG, String.format("Current count '%s' has not be surpported.", new Object[]{region}));
            }
        } else {
            prepopulatedEngineArr = null;
        }
        String preferringRegion = this.mConfig.getPreferringRegion();
        if (!TextUtils.isEmpty(preferringRegion) && ((prepopulatedEngineArr2 = SearchEnginesData.getEnginesByCountryCode(preferringRegion)) == null || prepopulatedEngineArr2.length == 0)) {
            Log.i(TAG, String.format("Current count '%s' has not be surpported.", new Object[]{preferringRegion}));
        }
        LinkedHashSet<PrepopulatedEngine> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(SearchEnginesData.BING);
        if (prepopulatedEngineArr != null) {
            linkedHashSet.addAll(Arrays.asList(prepopulatedEngineArr));
        }
        if (prepopulatedEngineArr2 != null) {
            linkedHashSet.addAll(Arrays.asList(prepopulatedEngineArr2));
        }
        ArrayList arrayList = new ArrayList();
        for (PrepopulatedEngine prepopulatedEngine : linkedHashSet) {
            arrayList.add(new SearchEngineInfo(prepopulatedEngine.getId(), prepopulatedEngine.getName()));
        }
        return arrayList;
    }

    public VisualAIDelegate getVisualAIDelegate() {
        return this.mVisualAIDelegate;
    }

    public VoiceAIDelegate getVoiceAIDelegate() {
        return this.mVoiceAIDelegate;
    }

    public String getWebASAnswerEventName(BasicASAnswerData basicASAnswerData) {
        return basicASAnswerData instanceof ASWebsite ? "EVENT_LOGGER_CLICK_AS_URL_LOAD" : ((basicASAnswerData instanceof ASWebEntity) || (basicASAnswerData instanceof ASWebCurrency) || (basicASAnswerData instanceof ASWebFinance) || (basicASAnswerData instanceof BingBusinessBaseBean)) ? "EVENT_LOGGER_CLICK_AS_ENTITY_SEARCH" : basicASAnswerData instanceof ASWebWeather ? "EVENT_LOGGER_CLICK_AS_WEATHER_SEARCH" : basicASAnswerData instanceof ASWebHistory ? "EVENT_LOGGER_CLICK_AS_HISTORY_SEARCH" : "EVENT_LOGGER_CLICK_AS_OTHER_SEARCH";
    }

    public Map<String, String> getWebASAnswerEventParams(BasicASAnswerData basicASAnswerData, int i) {
        String businessType;
        if (basicASAnswerData instanceof ASWebsite) {
            return null;
        }
        HashMap hashMap = new HashMap();
        if (i >= 0) {
            hashMap.put("AS position", String.valueOf(i));
        }
        if (basicASAnswerData instanceof ASWebNormal) {
            businessType = ((ASWebNormal) basicASAnswerData).getRichType();
        } else {
            if (basicASAnswerData instanceof BingBusinessBaseBean) {
                businessType = ((BingBusinessBaseBean) basicASAnswerData).getBusinessType();
            }
            return hashMap;
        }
        hashMap.put("entity type", businessType);
        return hashMap;
    }

    public void init(Context context) {
        if (this.mInitFlag < 0) {
            this.mInitFlag = 0;
            StringBuilder a = Eo.a("Initializing Bing SDK... thread = ");
            a.append(Thread.currentThread().getName());
            a.toString();
            Context applicationContext = context.getApplicationContext();
            ThreadUtils.init();
            Product.getInstance().init(applicationContext);
            USBTelemetryMgr.initialize(applicationContext);
            ActivityManager activityManager = (ActivityManager) applicationContext.getSystemService("activity");
            int i = 1024;
            if (activityManager != null) {
                i = (activityManager.getMemoryClass() * 1024) / 8;
            }
            Z80.b = i;
            String string = PreferenceUtil.getInstance(applicationContext).getString("PREFERENCE_KEY_X_MSEDGE_CLIENTID", (String) null);
            if (string != null) {
                getConfiguration().setBingClientID(string);
            }
            initHistoryManager(applicationContext);
            new p70().start();
            this.mConfig.initSearchEngine(applicationContext);
            ImageLoader.getInstance().makeSureInited(applicationContext);
            MarketCodeManager.getInstance().init(context, getConfiguration().getSDKLocale());
            Utility.a(applicationContext, string);
            this.mInitFlag = 1;
        }
    }

    public void initHistoryManager(Context context) {
        if (this.mHistoryManager == null) {
            this.mHistoryManager = JournalStore.getInstance(context);
        }
    }

    public boolean isContainsBuilder(IData iData, IAnswerView iAnswerView) {
        return AnswerSDKManager.getInstance().isContainsBuilder(iData, iAnswerView);
    }

    public boolean isEnableBingExperiment(Context context) {
        boolean z = PreferenceUtil.getInstance(context).getBoolean("PREFERENCE_KEY_DISABLE_BING_SEARCH", false);
        Locale currentLocale = CommonUtility.getCurrentLocale(context);
        return currentLocale != null && currentLocale.getCountry() != null && currentLocale.getCountry().equalsIgnoreCase("cn") && currentLocale.getLanguage() != null && currentLocale.getLanguage().equalsIgnoreCase("zh") && (z || getConfiguration().getSearchEngineID() == SearchEnginesData.BING.getId());
    }

    public void onHostActivityDestroyHandler(Activity activity) {
        String bingClientID = getInstance().getConfiguration().getBingClientID();
        if (bingClientID != null) {
            PreferenceUtil.getInstance(activity).saveString("PREFERENCE_KEY_X_MSEDGE_CLIENTID", bingClientID);
        }
    }

    public void registerASBuilder(Class<? extends IASAnswerData> cls, Class<? extends IAnswerView> cls2, Class<? extends IBuilder> cls3) {
        AnswerSDKManager.getInstance().registerASBuilder(cls, cls2, cls3);
    }

    public void registerASTransform(Class<?> cls, Class<? extends IData> cls2, Class<? extends ITransform> cls3) {
        AnswerSDKManager.getInstance().registerASTransform(cls, cls2, cls3);
    }

    public void registerAnswerActionDelegate(int i, IAnswerViewEventCallback<BasicASAnswerData> iAnswerViewEventCallback) {
        if (this.mAnswerActionDelegates == null) {
            this.mAnswerActionDelegates = new SparseArray<>();
        }
        this.mAnswerActionDelegates.append(i, iAnswerViewEventCallback);
    }

    public void registerClipboardService(Context context) {
        ClipboardManager clipboardManager;
        if (getInstance().getConfiguration().isCopyBubbleEnabled() && (clipboardManager = (ClipboardManager) context.getApplicationContext().getSystemService("clipboard")) != null) {
            y70 y70 = this.mOnPrimaryClipChangedListener;
            if (y70 == null) {
                this.mOnPrimaryClipChangedListener = new y70(context);
            } else {
                clipboardManager.removePrimaryClipChangedListener(y70);
            }
            clipboardManager.addPrimaryClipChangedListener(this.mOnPrimaryClipChangedListener);
        }
    }

    public void registerGlobalOpenBrowserCallBack(OpenBrowserCallBack openBrowserCallBack) {
        o50.b = openBrowserCallBack;
    }

    public void removeBingSearchViewEventListener() {
        synchronized (sCallBackLock) {
            this.mBingSearchViewEventListener = null;
        }
    }

    public void setBingSDKDataProvider(BingSDKDataProvider bingSDKDataProvider) {
        this.mBingSDKDataProvider = bingSDKDataProvider;
    }

    public void setBingSearchViewDataSourceDelegate(BingSearchViewDataSourceDelegate bingSearchViewDataSourceDelegate) {
        synchronized (sCallBackLock) {
            this.mBingSearchViewDataSourceDelegate = bingSearchViewDataSourceDelegate;
        }
    }

    public void setBingSearchViewEventListener(BingSearchViewEventListener bingSearchViewEventListener) {
        synchronized (sCallBackLock) {
            this.mBingSearchViewEventListener = bingSearchViewEventListener;
        }
    }

    public void setCurrentTheme(Theme theme) {
        this.currentTheme = theme;
    }

    public void setDefaultBrowser(Context context, ComponentName componentName) {
        o50.a(context, componentName);
    }

    public void setExperimentFeatureManager(ExperimentFeatureManager experimentFeatureManager) {
        this.mExperimentFeatureManager = experimentFeatureManager;
    }

    public void setPermissionRequestDelegate(PermissionRequestDelegate permissionRequestDelegate) {
        this.mPermissionRequestDelegate = permissionRequestDelegate;
    }

    public void setPluginAnswerBuilderDelegate(IAnswerBuilderDelegate iAnswerBuilderDelegate) {
        this.mPluginAnswerBuilderDelegate = iAnswerBuilderDelegate;
    }

    public void setSearchEngine(Context context, String str) {
        BingClientConfig configuration = getConfiguration();
        if (!CommonUtility.isStringNullOrEmpty(str)) {
            try {
                configuration.setSearchEngineByKeyword(context, str);
            } catch (IllegalArgumentException e) {
                qI.a.a(e);
            }
        }
    }

    public void setTheme(Theme theme) {
        this.currentTheme = theme;
    }

    public void setTokenDelegate(TokenDelegate tokenDelegate) {
        this.mTokenDelegate = tokenDelegate;
    }

    public void setVisualAIDelegate(VisualAIDelegate visualAIDelegate) {
        this.mVisualAIDelegate = visualAIDelegate;
    }

    public void setVoiceAIDelegate(VoiceAIDelegate voiceAIDelegate) {
        this.mVoiceAIDelegate = voiceAIDelegate;
    }

    public <T00 extends IContext, T11, T22 extends IData> T22 transform(T00 t00, T11 t11, Class<? extends T22> cls) {
        return AnswerSDKManager.getInstance().transform(t00, t11, cls);
    }

    public void unregisterASBuilder(Class<? extends IASAnswerData> cls, Class<? extends IAnswerView> cls2) {
        AnswerSDKManager.getInstance().unregisterASBuilder(cls, cls2);
    }

    public void unregisterASTransform(Class<?> cls, Class<? extends IData> cls2) {
        AnswerSDKManager.getInstance().unregisterASTransform(cls, cls2);
    }

    public void unregisterAnswerActionDelegate(int i) {
        SparseArray<IAnswerViewEventCallback<BasicASAnswerData>> sparseArray = this.mAnswerActionDelegates;
        if (sparseArray != null) {
            sparseArray.delete(i);
        }
    }

    public void unregisterClipboardService(Context context) {
        y70 y70;
        if (Product.getInstance().IS_ENABLE_CLIPBOARD_BUBBLE_Enabled()) {
            ClipboardManager clipboardManager = (ClipboardManager) context.getApplicationContext().getSystemService("clipboard");
            if (!(clipboardManager == null || (y70 = this.mOnPrimaryClipChangedListener) == null)) {
                clipboardManager.removePrimaryClipChangedListener(y70);
            }
            this.mOnPrimaryClipChangedListener = null;
        }
    }
}
