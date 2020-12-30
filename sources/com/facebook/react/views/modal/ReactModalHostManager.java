package com.facebook.react.views.modal;

import android.content.DialogInterface;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.views.modal.ReactModalHostView;
import hy;
import java.util.Map;

@Ay(name = "RCTModalHostView")
/* compiled from: PG */
public class ReactModalHostManager extends ViewGroupManager<ReactModalHostView> {
    public static final String REACT_CLASS = "RCTModalHostView";

    /* renamed from: com.facebook.react.views.modal.ReactModalHostManager$a */
    /* compiled from: PG */
    public class C0150a implements ReactModalHostView.OnRequestCloseListener {

        /* renamed from: a */
        public final /* synthetic */ qC f602a;

        /* renamed from: b */
        public final /* synthetic */ ReactModalHostView f603b;

        public C0150a(ReactModalHostManager reactModalHostManager, qC qCVar, ReactModalHostView reactModalHostView) {
            this.f602a = qCVar;
            this.f603b = reactModalHostView;
        }

        public void onRequestClose(DialogInterface dialogInterface) {
            this.f602a.b(new iD(this.f603b.getId()));
        }
    }

    /* renamed from: com.facebook.react.views.modal.ReactModalHostManager$b */
    /* compiled from: PG */
    public class C0151b implements DialogInterface.OnShowListener {

        /* renamed from: a */
        public final /* synthetic */ qC f604a;

        /* renamed from: b */
        public final /* synthetic */ ReactModalHostView f605b;

        public C0151b(ReactModalHostManager reactModalHostManager, qC qCVar, ReactModalHostView reactModalHostView) {
            this.f604a = qCVar;
            this.f605b = reactModalHostView;
        }

        public void onShow(DialogInterface dialogInterface) {
            this.f604a.b(new jD(this.f605b.getId()));
        }
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        hy.a a = hy.a();
        a.a("topRequestClose", hy.a("registrationName", "onRequestClose"));
        a.a("topShow", hy.a("registrationName", "onShow"));
        return a.a();
    }

    public String getName() {
        return REACT_CLASS;
    }

    public Class<? extends xA> getShadowNodeClass() {
        return gD.class;
    }

    @eC(name = "animationType")
    public void setAnimationType(ReactModalHostView reactModalHostView, String str) {
        reactModalHostView.mo1289a(str);
    }

    @eC(name = "hardwareAccelerated")
    public void setHardwareAccelerated(ReactModalHostView reactModalHostView, boolean z) {
        reactModalHostView.mo1290a(z);
    }

    @eC(name = "transparent")
    public void setTransparent(ReactModalHostView reactModalHostView, boolean z) {
        reactModalHostView.mo1294b(z);
    }

    public void addEventEmitters(WA wa, ReactModalHostView reactModalHostView) {
        qC eventDispatcher = ((UIManagerModule) wa.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        reactModalHostView.mo1288a((ReactModalHostView.OnRequestCloseListener) new C0150a(this, eventDispatcher, reactModalHostView));
        reactModalHostView.mo1287a((DialogInterface.OnShowListener) new C0151b(this, eventDispatcher, reactModalHostView));
    }

    public xA createShadowNodeInstance() {
        return new gD();
    }

    public ReactModalHostView createViewInstance(WA wa) {
        return new ReactModalHostView(wa);
    }

    public void onAfterUpdateTransaction(ReactModalHostView reactModalHostView) {
        super.onAfterUpdateTransaction(reactModalHostView);
        reactModalHostView.mo1295c();
    }

    public void onDropViewInstance(ReactModalHostView reactModalHostView) {
        super.onDropViewInstance(reactModalHostView);
        reactModalHostView.mo1293b();
    }
}
