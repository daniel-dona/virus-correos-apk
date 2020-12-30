package org.chromium.p012ui.events.devices;

import android.hardware.input.InputManager;
import android.os.Handler;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

@MainDex
/* renamed from: org.chromium.ui.events.devices.InputDeviceObserver */
/* compiled from: PG */
public class InputDeviceObserver implements InputManager.InputDeviceListener {

    /* renamed from: c */
    public static final InputDeviceObserver f2666c = new InputDeviceObserver();

    /* renamed from: a */
    public InputManager f2667a;

    /* renamed from: b */
    public int f2668b;

    static {
        Class<InputDeviceObserver> cls = InputDeviceObserver.class;
    }

    @CalledByNative
    public static void addObserver() {
        InputDeviceObserver inputDeviceObserver = f2666c;
        int i = inputDeviceObserver.f2668b;
        inputDeviceObserver.f2668b = i + 1;
        if (i == 0) {
            inputDeviceObserver.f2667a = (InputManager) RN0.a.getSystemService("input");
            inputDeviceObserver.f2667a.registerInputDeviceListener(inputDeviceObserver, (Handler) null);
        }
    }

    private native void nativeInputConfigurationChanged();

    @CalledByNative
    public static void removeObserver() {
        InputDeviceObserver inputDeviceObserver = f2666c;
        int i = inputDeviceObserver.f2668b - 1;
        inputDeviceObserver.f2668b = i;
        if (i == 0) {
            inputDeviceObserver.f2667a.unregisterInputDeviceListener(inputDeviceObserver);
            inputDeviceObserver.f2667a = null;
        }
    }

    public void onInputDeviceAdded(int i) {
        nativeInputConfigurationChanged();
    }

    public void onInputDeviceChanged(int i) {
        nativeInputConfigurationChanged();
    }

    public void onInputDeviceRemoved(int i) {
        nativeInputConfigurationChanged();
    }
}
