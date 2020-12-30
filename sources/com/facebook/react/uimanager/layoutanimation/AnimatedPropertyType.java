package com.facebook.react.uimanager.layoutanimation;

/* compiled from: PG */
public enum AnimatedPropertyType {
    OPACITY,
    SCALE_X,
    SCALE_Y,
    SCALE_XY;

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.react.uimanager.layoutanimation.AnimatedPropertyType fromString(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = 3
            r2 = 2
            r3 = 1
            switch(r0) {
                case -1267206133: goto L_0x0029;
                case -908189618: goto L_0x001f;
                case -908189617: goto L_0x0015;
                case 1910893003: goto L_0x000b;
                default: goto L_0x000a;
            }
        L_0x000a:
            goto L_0x0033
        L_0x000b:
            java.lang.String r0 = "scaleXY"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0033
            r0 = 3
            goto L_0x0034
        L_0x0015:
            java.lang.String r0 = "scaleY"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0033
            r0 = 2
            goto L_0x0034
        L_0x001f:
            java.lang.String r0 = "scaleX"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0033
            r0 = 1
            goto L_0x0034
        L_0x0029:
            java.lang.String r0 = "opacity"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0033
            r0 = 0
            goto L_0x0034
        L_0x0033:
            r0 = -1
        L_0x0034:
            if (r0 == 0) goto L_0x0051
            if (r0 == r3) goto L_0x004e
            if (r0 == r2) goto L_0x004b
            if (r0 != r1) goto L_0x003f
            com.facebook.react.uimanager.layoutanimation.AnimatedPropertyType r4 = SCALE_XY
            return r4
        L_0x003f:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Unsupported animated property: "
            java.lang.String r4 = Eo.a(r1, r4)
            r0.<init>(r4)
            throw r0
        L_0x004b:
            com.facebook.react.uimanager.layoutanimation.AnimatedPropertyType r4 = SCALE_Y
            return r4
        L_0x004e:
            com.facebook.react.uimanager.layoutanimation.AnimatedPropertyType r4 = SCALE_X
            return r4
        L_0x0051:
            com.facebook.react.uimanager.layoutanimation.AnimatedPropertyType r4 = OPACITY
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.layoutanimation.AnimatedPropertyType.fromString(java.lang.String):com.facebook.react.uimanager.layoutanimation.AnimatedPropertyType");
    }
}
