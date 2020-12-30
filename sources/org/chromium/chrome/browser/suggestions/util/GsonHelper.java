package org.chromium.chrome.browser.suggestions.util;

import android.util.Base64;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import java.lang.reflect.Type;

/* compiled from: PG */
public abstract class GsonHelper {

    /* renamed from: a */
    public static final Gson f2206a;

    static {
        MJ mj = new MJ();
        Class<byte[]> cls = byte[].class;
        ByteArrayToBase64TypeAdapter byteArrayToBase64TypeAdapter = new ByteArrayToBase64TypeAdapter();
        eK.a(true);
        mj.f.add(new TreeTypeAdapter.SingleTypeFactory(byteArrayToBase64TypeAdapter, (TK<?>) null, false, cls));
        if (byteArrayToBase64TypeAdapter instanceof TypeAdapter) {
            mj.e.add(TypeAdapters.m919b(cls, (TypeAdapter) byteArrayToBase64TypeAdapter));
        }
        f2206a = mj.a();
    }

    /* compiled from: PG */
    public static class ByteArrayToBase64TypeAdapter implements XJ<byte[]>, QJ<byte[]> {
        /* renamed from: a */
        public byte[] mo9224a(RJ rj) throws JsonParseException {
            String d = rj.d();
            if (Wi0.a(d)) {
                return null;
            }
            return Base64.decode(d, 2);
        }

        public /* bridge */ /* synthetic */ Object deserialize(RJ rj, Type type, PJ pj) throws JsonParseException {
            return mo9224a(rj);
        }

        public /* bridge */ /* synthetic */ RJ serialize(Object obj, Type type, WJ wj) {
            return mo9223a((byte[]) obj);
        }

        /* renamed from: a */
        public RJ mo9223a(byte[] bArr) {
            if (bArr == null) {
                return null;
            }
            return new VJ(Base64.encodeToString(bArr, 2));
        }
    }
}
