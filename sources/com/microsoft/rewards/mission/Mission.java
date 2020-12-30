package com.microsoft.rewards.mission;

import com.microsoft.rewards.modernplatform.model.Promotion;

/* compiled from: PG */
public enum Mission {
    ;
    
    public int mActivityType;
    public boolean mEnsureSuccess;
    public String mOfferId;
    public boolean mShowUiNotification;

    public abstract boolean checkLocalStates();

    public int getActivityType() {
        return this.mActivityType;
    }

    public String getOfferId() {
        return this.mOfferId;
    }

    public boolean isEnsureSuccess() {
        return this.mEnsureSuccess;
    }

    public boolean matchPromotion(Promotion promotion) {
        if (promotion == null || promotion.getAttributes() == null) {
            return false;
        }
        return getOfferId().equals(promotion.getAttributes().get("offerid"));
    }

    public void setActivityType(int i) {
        this.mActivityType = i;
    }

    public void setOfferId(String str) {
        this.mOfferId = str;
    }

    public boolean shouldShowUiNotification() {
        return this.mShowUiNotification;
    }

    /* access modifiers changed from: public */
    Mission(int i, String str, boolean z, boolean z2) {
        this.mActivityType = i;
        this.mOfferId = str;
        this.mEnsureSuccess = z;
        this.mShowUiNotification = z2;
    }
}
