package com.microsoft.aad.adal;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.microsoft.tokenshare.AccountInfo;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
public final class SSOStateSerializer {
    public static final Gson GSON;
    @bK("tokenCacheItems")
    public final List<TokenCacheItem> mTokenCacheItems = new ArrayList();
    @bK("version")
    public final int version = 1;

    static {
        MJ mj = new MJ();
        mj.a(TokenCacheItem.class, new TokenCacheItemSerializationAdapater());
        GSON = mj.a();
    }

    public SSOStateSerializer() {
    }

    public static TokenCacheItem deserialize(String str) throws AuthenticationException {
        return new SSOStateSerializer().internalDeserialize(str);
    }

    private TokenCacheItem getTokenItem() throws AuthenticationException {
        List<TokenCacheItem> list = this.mTokenCacheItems;
        if (list != null && !list.isEmpty()) {
            return this.mTokenCacheItems.get(0);
        }
        throw new AuthenticationException(ADALError.TOKEN_CACHE_ITEM_NOT_FOUND, "There is no token cache item in the SSOStateContainer.");
    }

    private int getVersion() {
        return 1;
    }

    private TokenCacheItem internalDeserialize(String str) throws AuthenticationException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.getInt(AccountInfo.VERSION_KEY) == getVersion()) {
                Class cls = SSOStateSerializer.class;
                return ((SSOStateSerializer) BK.a(cls).cast(GSON.mo2098a(str, (Type) cls))).getTokenItem();
            }
            throw new DeserializationAuthenticationException("Fail to deserialize because the blob version is incompatible. The version of the serializedBlob is " + jSONObject.getInt(AccountInfo.VERSION_KEY) + ". And the target class version is " + getVersion());
        } catch (JsonParseException | JSONException e) {
            throw new DeserializationAuthenticationException(e.getMessage());
        }
    }

    private String internalSerialize() {
        return GSON.mo2100a((Object) this);
    }

    public static String serialize(TokenCacheItem tokenCacheItem) {
        return new SSOStateSerializer(tokenCacheItem).internalSerialize();
    }

    public SSOStateSerializer(TokenCacheItem tokenCacheItem) {
        if (tokenCacheItem != null) {
            this.mTokenCacheItems.add(tokenCacheItem);
            return;
        }
        throw new IllegalArgumentException("tokenItem is null");
    }
}
