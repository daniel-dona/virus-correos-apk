package com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory;

/* compiled from: PG */
public class AzureActiveDirectorySlice {
    public static final String DC_PARAMETER = "dc";
    public static final String SLICE_PARAMETER = "slice";
    @bK("dc")
    public String mDataCenter;
    @bK("slice")
    public String mSlice;

    public String getDC() {
        return this.mDataCenter;
    }

    public String getSlice() {
        return this.mSlice;
    }

    public void setDataCenter(String str) {
        this.mDataCenter = str;
    }

    public void setSlice(String str) {
        this.mSlice = str;
    }
}
