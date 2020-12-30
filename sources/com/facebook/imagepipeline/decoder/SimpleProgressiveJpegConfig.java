package com.facebook.imagepipeline.decoder;

import java.util.List;

/* compiled from: PG */
public class SimpleProgressiveJpegConfig implements Pt {

    /* renamed from: a */
    public final DynamicValueConfig f357a = new St((Rt) null);

    /* compiled from: PG */
    public interface DynamicValueConfig {
        int getGoodEnoughScanNumber();

        List<Integer> getScansToDecode();
    }
}
