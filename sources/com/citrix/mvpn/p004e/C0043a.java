package com.citrix.mvpn.p004e;

import android.content.Context;
import com.facebook.react.uimanager.BaseViewManager;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* renamed from: com.citrix.mvpn.e.a */
/* compiled from: PG */
public class C0043a {
    /* renamed from: a */
    public static ho<?> m153a(Context context, Class<?> cls, InvocationHandler invocationHandler, Method[] methodArr) {
        ho<?> hoVar = new ho<>(cls);
        File cacheDir = context.getCacheDir();
        StringBuilder a = Eo.a("v");
        a.append(Integer.toString(1));
        hoVar.d = new File(cacheDir, a.toString());
        hoVar.d.mkdir();
        hoVar.c = invocationHandler;
        Constructor constructor = cls.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        Class[] parameterTypes = constructor.getParameterTypes();
        if (parameterTypes != null && parameterTypes.length > 0) {
            Object[] a2 = m154a(context, parameterTypes);
            hoVar.e = parameterTypes;
            hoVar.f = a2;
        }
        hoVar.h = methodArr;
        return hoVar;
    }

    /* renamed from: a */
    public static Object[] m154a(Context context, Class<?>[] clsArr) {
        Object[] objArr = new Object[clsArr.length];
        for (int i = 0; i < clsArr.length; i++) {
            Class<?> cls = clsArr[i];
            if (cls.isPrimitive()) {
                if (cls == Integer.TYPE) {
                    objArr[i] = new Integer(0);
                } else if (cls == Byte.TYPE) {
                    objArr[i] = new Byte((byte) 0);
                } else if (cls == Character.TYPE) {
                    objArr[i] = new Character('a');
                } else if (cls == Float.TYPE) {
                    objArr[i] = new Float(BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
                } else if (cls == Boolean.TYPE) {
                    objArr[i] = new Boolean(false);
                } else if (cls == Long.TYPE) {
                    objArr[i] = new Long(0);
                } else if (cls == Short.TYPE) {
                    objArr[i] = new Short(0);
                }
            } else if (cls.isInstance(context)) {
                objArr[i] = context;
            } else {
                objArr[i] = null;
            }
        }
        return objArr;
    }
}
