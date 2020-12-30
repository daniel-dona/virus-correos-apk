package com.facebook.react.views.webview;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.citrix.loggersdk.BuildConfig;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.UIManagerModule;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

@Ay(name = "RCTWebView")
/* compiled from: PG */
public class ReactWebViewManager extends SimpleViewManager<WebView> {
    public static final String BLANK_URL = "about:blank";
    public static final String BRIDGE_NAME = "__REACT_WEB_VIEW_BRIDGE";
    public static final int COMMAND_GO_BACK = 1;
    public static final int COMMAND_GO_FORWARD = 2;
    public static final int COMMAND_INJECT_JAVASCRIPT = 6;
    public static final int COMMAND_POST_MESSAGE = 5;
    public static final int COMMAND_RELOAD = 3;
    public static final int COMMAND_STOP_LOADING = 4;
    public static final String HTML_ENCODING = "UTF-8";
    public static final String HTML_MIME_TYPE = "text/html";
    public static final String HTTP_METHOD_POST = "POST";
    public static final String INTENT_URL_PREFIX = "intent://";
    public static final String REACT_CLASS = "RCTWebView";
    public WebView.PictureListener mPictureListener;
    public uE mWebViewConfig;

    /* compiled from: PG */
    public static class ReactWebView extends WebView implements LifecycleEventListener {

        /* renamed from: a */
        public String f834a;

        /* renamed from: b */
        public boolean f835b = false;

        /* renamed from: c */
        public C0195d f836c;

        /* renamed from: com.facebook.react.views.webview.ReactWebViewManager$ReactWebView$a */
        /* compiled from: PG */
        public class C0191a {

            /* renamed from: a */
            public ReactWebView f837a;

            public C0191a(ReactWebView reactWebView, ReactWebView reactWebView2) {
                this.f837a = reactWebView2;
            }

            @JavascriptInterface
            public void postMessage(String str) {
                this.f837a.mo1844b(str);
            }
        }

        public ReactWebView(WA wa) {
            super(wa);
        }

        /* renamed from: a */
        public C0191a mo1840a(ReactWebView reactWebView) {
            return new C0191a(this, reactWebView);
        }

        /* renamed from: b */
        public void mo1844b(String str) {
            ReactWebViewManager.dispatchEvent(this, new yE(getId(), str));
        }

        /* renamed from: c */
        public C0195d mo1845c() {
            return this.f836c;
        }

        /* renamed from: d */
        public void mo1846d() {
            if (this.f835b) {
                mo1842a("(window.originalPostMessage = window.postMessage,window.postMessage = function(data) {__REACT_WEB_VIEW_BRIDGE.postMessage(String(data));})");
            }
        }

        public void onHostDestroy() {
            mo1843b();
        }

        public void onHostPause() {
        }

        public void onHostResume() {
        }

        public void setInjectedJavaScript(String str) {
            this.f834a = str;
        }

        public void setMessagingEnabled(boolean z) {
            if (this.f835b != z) {
                this.f835b = z;
                if (z) {
                    addJavascriptInterface(mo1840a(this), ReactWebViewManager.BRIDGE_NAME);
                    mo1846d();
                    return;
                }
                removeJavascriptInterface(ReactWebViewManager.BRIDGE_NAME);
            }
        }

        public void setWebViewClient(WebViewClient webViewClient) {
            super.setWebViewClient(webViewClient);
            this.f836c = (C0195d) webViewClient;
        }

        /* renamed from: a */
        public void mo1842a(String str) {
            evaluateJavascript(str, (ValueCallback) null);
        }

        /* renamed from: b */
        public void mo1843b() {
            setWebViewClient((WebViewClient) null);
            destroy();
        }

        /* renamed from: a */
        public void mo1841a() {
            String str;
            if (getSettings().getJavaScriptEnabled() && (str = this.f834a) != null && !TextUtils.isEmpty(str)) {
                StringBuilder a = Eo.a("(function() {\n");
                a.append(this.f834a);
                a.append(";\n})();");
                mo1842a(a.toString());
            }
        }
    }

    /* renamed from: com.facebook.react.views.webview.ReactWebViewManager$a */
    /* compiled from: PG */
    public class C0192a implements uE {
        public C0192a(ReactWebViewManager reactWebViewManager) {
        }

        /* renamed from: a */
        public void mo1851a(WebView webView) {
        }
    }

    /* renamed from: com.facebook.react.views.webview.ReactWebViewManager$b */
    /* compiled from: PG */
    public class C0193b extends WebChromeClient {
        public C0193b(ReactWebViewManager reactWebViewManager) {
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return true;
        }

        public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
            callback.invoke(str, true, false);
        }
    }

    /* renamed from: com.facebook.react.views.webview.ReactWebViewManager$c */
    /* compiled from: PG */
    public class C0194c implements WebView.PictureListener {
        public C0194c(ReactWebViewManager reactWebViewManager) {
        }

        public void onNewPicture(WebView webView, Picture picture) {
            ReactWebViewManager.dispatchEvent(webView, new jC(webView.getId(), webView.getWidth(), webView.getContentHeight()));
        }
    }

    public ReactWebViewManager() {
        this.mWebViewConfig = new C0192a(this);
    }

    public static void dispatchEvent(WebView webView, kC kCVar) {
        ((UIManagerModule) ((ReactContext) webView.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().b(kCVar);
    }

    public ReactWebView createReactWebViewInstance(WA wa) {
        return new ReactWebView(wa);
    }

    public Map<String, Integer> getCommandsMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("goBack", 1);
        hashMap.put("goForward", 2);
        hashMap.put("reload", 3);
        hashMap.put("stopLoading", 4);
        hashMap.put("postMessage", 5);
        hashMap.put("injectJavaScript", 6);
        return hashMap;
    }

    public String getName() {
        return REACT_CLASS;
    }

    public WebView.PictureListener getPictureListener() {
        if (this.mPictureListener == null) {
            this.mPictureListener = new C0194c(this);
        }
        return this.mPictureListener;
    }

    @eC(name = "allowFileAccess")
    public void setAllowFileAccess(WebView webView, Boolean bool) {
        webView.getSettings().setAllowFileAccess(bool != null && bool.booleanValue());
    }

    @eC(name = "allowUniversalAccessFromFileURLs")
    public void setAllowUniversalAccessFromFileURLs(WebView webView, boolean z) {
        webView.getSettings().setAllowUniversalAccessFromFileURLs(z);
    }

    @eC(name = "domStorageEnabled")
    public void setDomStorageEnabled(WebView webView, boolean z) {
        webView.getSettings().setDomStorageEnabled(z);
    }

    @eC(name = "geolocationEnabled")
    public void setGeolocationEnabled(WebView webView, Boolean bool) {
        webView.getSettings().setGeolocationEnabled(bool != null && bool.booleanValue());
    }

    @eC(name = "injectedJavaScript")
    public void setInjectedJavaScript(WebView webView, String str) {
        ((ReactWebView) webView).setInjectedJavaScript(str);
    }

    @eC(name = "javaScriptEnabled")
    public void setJavaScriptEnabled(WebView webView, boolean z) {
        webView.getSettings().setJavaScriptEnabled(z);
    }

    @TargetApi(17)
    @eC(name = "mediaPlaybackRequiresUserAction")
    public void setMediaPlaybackRequiresUserAction(WebView webView, boolean z) {
        webView.getSettings().setMediaPlaybackRequiresUserGesture(z);
    }

    @eC(name = "messagingEnabled")
    public void setMessagingEnabled(WebView webView, boolean z) {
        ((ReactWebView) webView).setMessagingEnabled(z);
    }

    @eC(name = "mixedContentMode")
    public void setMixedContentMode(WebView webView, String str) {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        if (str == null || "never".equals(str)) {
            webView.getSettings().setMixedContentMode(1);
        } else if ("always".equals(str)) {
            webView.getSettings().setMixedContentMode(0);
        } else if ("compatibility".equals(str)) {
            webView.getSettings().setMixedContentMode(2);
        }
    }

    @eC(name = "onContentSizeChange")
    public void setOnContentSizeChange(WebView webView, boolean z) {
        if (z) {
            webView.setPictureListener(getPictureListener());
        } else {
            webView.setPictureListener((WebView.PictureListener) null);
        }
    }

    @eC(name = "originWhitelist")
    public void setOriginWhitelist(WebView webView, ReadableArray readableArray) {
        C0195d c = ((ReactWebView) webView).mo1845c();
        if (c != null && readableArray != null) {
            LinkedList linkedList = new LinkedList();
            for (int i = 0; i < readableArray.size(); i++) {
                linkedList.add(Pattern.compile(readableArray.getString(i)));
            }
            c.f840c = linkedList;
        }
    }

    @eC(name = "saveFormDataDisabled")
    public void setSaveFormDataDisabled(WebView webView, boolean z) {
        webView.getSettings().setSaveFormData(!z);
    }

    @eC(name = "scalesPageToFit")
    public void setScalesPageToFit(WebView webView, boolean z) {
        webView.getSettings().setUseWideViewPort(!z);
    }

    @eC(name = "source")
    public void setSource(WebView webView, ReadableMap readableMap) {
        if (readableMap != null) {
            if (readableMap.hasKey("html")) {
                String string = readableMap.getString("html");
                if (readableMap.hasKey("baseUrl")) {
                    webView.loadDataWithBaseURL(readableMap.getString("baseUrl"), string, HTML_MIME_TYPE, "UTF-8", (String) null);
                    return;
                }
                webView.loadData(string, HTML_MIME_TYPE, "UTF-8");
                return;
            } else if (readableMap.hasKey("uri")) {
                String string2 = readableMap.getString("uri");
                String url = webView.getUrl();
                if (url != null && url.equals(string2)) {
                    return;
                }
                if (!readableMap.hasKey("method") || !readableMap.getString("method").equalsIgnoreCase("POST")) {
                    HashMap hashMap = new HashMap();
                    if (readableMap.hasKey("headers")) {
                        ReadableMap map = readableMap.getMap("headers");
                        ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
                        while (keySetIterator.hasNextKey()) {
                            String nextKey = keySetIterator.nextKey();
                            if (!"user-agent".equals(nextKey.toLowerCase(Locale.ENGLISH))) {
                                hashMap.put(nextKey, map.getString(nextKey));
                            } else if (webView.getSettings() != null) {
                                webView.getSettings().setUserAgentString(map.getString(nextKey));
                            }
                        }
                    }
                    webView.loadUrl(string2, hashMap);
                    return;
                }
                byte[] bArr = null;
                if (readableMap.hasKey("body")) {
                    String string3 = readableMap.getString("body");
                    try {
                        bArr = string3.getBytes("UTF-8");
                    } catch (UnsupportedEncodingException unused) {
                        bArr = string3.getBytes();
                    }
                }
                if (bArr == null) {
                    bArr = new byte[0];
                }
                webView.postUrl(string2, bArr);
                return;
            }
        }
        webView.loadUrl("about:blank");
    }

    @eC(name = "thirdPartyCookiesEnabled")
    public void setThirdPartyCookiesEnabled(WebView webView, boolean z) {
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, z);
        }
    }

    @eC(name = "urlPrefixesForDefaultIntent")
    public void setUrlPrefixesForDefaultIntent(WebView webView, ReadableArray readableArray) {
        C0195d c = ((ReactWebView) webView).mo1845c();
        if (c != null && readableArray != null) {
            c.f839b = readableArray;
        }
    }

    @eC(name = "userAgent")
    public void setUserAgent(WebView webView, String str) {
        if (str != null) {
            webView.getSettings().setUserAgentString(str);
        }
    }

    @eC(defaultBoolean = true, name = "hardwareAccelerationEnabledExperimental")
    public void sethardwareAccelerationEnabledExperimental(WebView webView, boolean z) {
        if (!z) {
            webView.setLayerType(1, (Paint) null);
        }
    }

    public void addEventEmitters(WA wa, WebView webView) {
        webView.setWebViewClient(new C0195d());
    }

    @TargetApi(21)
    public WebView createViewInstance(WA wa) {
        ReactWebView createReactWebViewInstance = createReactWebViewInstance(wa);
        createReactWebViewInstance.setWebChromeClient(new C0193b(this));
        wa.a.addLifecycleEventListener(createReactWebViewInstance);
        this.mWebViewConfig.mo1851a(createReactWebViewInstance);
        WebSettings settings = createReactWebViewInstance.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        setAllowUniversalAccessFromFileURLs(createReactWebViewInstance, false);
        setMixedContentMode(createReactWebViewInstance, "never");
        createReactWebViewInstance.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        setGeolocationEnabled(createReactWebViewInstance, false);
        return createReactWebViewInstance;
    }

    public void onDropViewInstance(WebView webView) {
        super.onDropViewInstance(webView);
        ReactWebView reactWebView = (ReactWebView) webView;
        webView.getContext().a.removeLifecycleEventListener(reactWebView);
        reactWebView.mo1843b();
    }

    public void receiveCommand(WebView webView, int i, ReadableArray readableArray) {
        switch (i) {
            case 1:
                webView.goBack();
                return;
            case 2:
                webView.goForward();
                return;
            case 3:
                webView.reload();
                return;
            case 4:
                webView.stopLoading();
                return;
            case 5:
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("data", readableArray.getString(0));
                    ((ReactWebView) webView).mo1842a("(function () {var event;var data = " + jSONObject.toString() + ";try {event = new MessageEvent('message', data);} catch (e) {event = document.createEvent('MessageEvent');event.initMessageEvent('message', true, true, data.data, data.origin, data.lastEventId, data.source);}document.dispatchEvent(event);})();");
                    return;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            case 6:
                ((ReactWebView) webView).mo1842a(readableArray.getString(0));
                return;
            default:
                return;
        }
    }

    public ReactWebViewManager(uE uEVar) {
        this.mWebViewConfig = uEVar;
    }

    /* renamed from: com.facebook.react.views.webview.ReactWebViewManager$d */
    /* compiled from: PG */
    public static class C0195d extends WebViewClient {

        /* renamed from: a */
        public boolean f838a = false;

        /* renamed from: b */
        public ReadableArray f839b;

        /* renamed from: c */
        public List<Pattern> f840c;

        /* JADX WARNING: Removed duplicated region for block: B:10:0x001e  */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0048  */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void mo1856a(android.content.Context r7, java.lang.String r8) {
            /*
                r6 = this;
                java.lang.String r0 = "intent://"
                boolean r0 = r8.startsWith(r0)
                java.lang.String r1 = "ReactNative"
                r2 = 0
                if (r0 == 0) goto L_0x0017
                r0 = 1
                android.content.Intent r0 = android.content.Intent.parseUri(r8, r0)     // Catch:{ URISyntaxException -> 0x0011 }
                goto L_0x0018
            L_0x0011:
                r0 = move-exception
                java.lang.String r3 = "Can't parse intent:// URI"
                pq.a(r1, r3, r0)
            L_0x0017:
                r0 = r2
            L_0x0018:
                java.lang.String r3 = "android.intent.category.BROWSABLE"
                java.lang.String r4 = "android.intent.action.VIEW"
                if (r0 == 0) goto L_0x0048
                r0.addCategory(r3)
                r0.setComponent(r2)
                r0.setSelector(r2)
                android.content.pm.PackageManager r2 = r7.getPackageManager()
                r5 = 65536(0x10000, float:9.18355E-41)
                android.content.pm.ResolveInfo r2 = r2.resolveActivity(r0, r5)
                if (r2 == 0) goto L_0x0037
                r7.startActivity(r0)
                goto L_0x0051
            L_0x0037:
                java.lang.String r2 = "browser_fallback_url"
                java.lang.String r0 = r0.getStringExtra(r2)
                android.content.Intent r2 = new android.content.Intent
                android.net.Uri r0 = android.net.Uri.parse(r0)
                r2.<init>(r4, r0)
                r0 = r2
                goto L_0x0051
            L_0x0048:
                android.content.Intent r0 = new android.content.Intent
                android.net.Uri r2 = android.net.Uri.parse(r8)
                r0.<init>(r4, r2)
            L_0x0051:
                r2 = 268435456(0x10000000, float:2.5243549E-29)
                r0.setFlags(r2)     // Catch:{ ActivityNotFoundException -> 0x005d }
                r0.addCategory(r3)     // Catch:{ ActivityNotFoundException -> 0x005d }
                r7.startActivity(r0)     // Catch:{ ActivityNotFoundException -> 0x005d }
                goto L_0x0072
            L_0x005d:
                r7 = move-exception
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r2 = "activity not found to handle uri scheme for: "
                r0.append(r2)
                r0.append(r8)
                java.lang.String r8 = r0.toString()
                pq.b(r1, r8, r7)
            L_0x0072:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.webview.ReactWebViewManager.C0195d.mo1856a(android.content.Context, java.lang.String):void");
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (!this.f838a) {
                ReactWebView reactWebView = (ReactWebView) webView;
                reactWebView.mo1841a();
                reactWebView.mo1846d();
                ReactWebViewManager.dispatchEvent(webView, new wE(webView.getId(), mo1855a(webView, str)));
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.f838a = false;
            ReactWebViewManager.dispatchEvent(webView, new xE(webView.getId(), mo1855a(webView, str)));
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            this.f838a = true;
            ReactWebViewManager.dispatchEvent(webView, new wE(webView.getId(), mo1855a(webView, str2)));
            WritableMap a = mo1855a(webView, str2);
            a.putDouble("code", (double) i);
            a.putString("description", str);
            ReactWebViewManager.dispatchEvent(webView, new vE(webView.getId(), a));
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            boolean z;
            if (str.equals("about:blank")) {
                return false;
            }
            ReadableArray readableArray = this.f839b;
            if (readableArray != null && readableArray.size() > 0) {
                Iterator<Object> it = this.f839b.toArrayList().iterator();
                while (it.hasNext()) {
                    if (str.startsWith((String) it.next())) {
                        mo1856a(webView.getContext(), str);
                        return true;
                    }
                }
            }
            List<Pattern> list = this.f840c;
            if (list != null) {
                Uri parse = Uri.parse(str);
                String scheme = parse.getScheme();
                String str2 = BuildConfig.FLAVOR;
                String scheme2 = scheme != null ? parse.getScheme() : str2;
                if (parse.getAuthority() != null) {
                    str2 = parse.getAuthority();
                }
                String b = Eo.b(scheme2, "://", str2);
                Iterator<Pattern> it2 = list.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (it2.next().matcher(b).matches()) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    return false;
                }
            }
            mo1856a(webView.getContext(), str);
            return true;
        }

        /* renamed from: a */
        public WritableMap mo1855a(WebView webView, String str) {
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("target", (double) webView.getId());
            createMap.putString("url", str);
            createMap.putBoolean("loading", !this.f838a && webView.getProgress() != 100);
            createMap.putString("title", webView.getTitle());
            createMap.putBoolean("canGoBack", webView.canGoBack());
            createMap.putBoolean("canGoForward", webView.canGoForward());
            return createMap;
        }
    }
}
