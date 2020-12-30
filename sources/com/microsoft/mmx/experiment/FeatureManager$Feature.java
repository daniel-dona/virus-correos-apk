package com.microsoft.mmx.experiment;

/* compiled from: PG */
public enum FeatureManager$Feature {
    EXPERIMENT_SET_DEFAULT_BROWSER("ExperimentSetDefaultBrowser", bh0.g),
    PIP_ROLLOUT("PiP-Rollout", bh0.b),
    READ_ALOUD("ReadAloud", bh0.b),
    TEST_ROLLOUT1("TestRollout1", bh0.b),
    TEST_ROLLOUT2("TestRollout2", bh0.b, ModificationVisibility.APP_START),
    NTP_VIDEOS_ROLLOUT("Ntp-Videos-Rollout", bh0.b),
    NEWSFEEDURL_ROLLOUT("NewsFeed-Rollout", bh0.b),
    BRANDLOGO_ROLLOUT("BrandLogo-Rollout", bh0.b),
    BRANDCOLOR_ROLLOUT("BrandColor-Rollout", bh0.b),
    INDUSTRY_NEWS_ROLLOUT("IndustryNews-Rollout", bh0.b),
    PROMOTE_SEARCH_WIDGET_ROLLOUT("PromoteSearchWidget-Rollout", bh0.b),
    NEWSGUARD_ROLLOUT("NewsGuard-Rollout", bh0.b),
    SHOPPING_ROLLOUT("Shopping-Rollout", bh0.b),
    CITRIX_DISABLE_ROLLOUT("CitrixDisable-Rollout", bh0.b),
    SHARED_FROM_PROMOTION("SharedFromPromotion", bh0.b, ModificationVisibility.APP_START),
    FAVORITES_REARRANGE_ROLLOUT("Favorites-Rearrange-Rollout", bh0.b),
    SET_HOMEPAGE_ROLLOUT("Set-Homepage-Rollout", bh0.b),
    SYNCED_TABS_ROLLOUT("Synced-Tabs-Rollout", bh0.b),
    SYNCED_TABS_POPUP_ROLLOUT("Synced-Tabs-Popup-Rollout", bh0.b),
    SYNCED_PASSWORDS_ROLLOUT("Synced-Passwords-Rollout", bh0.c),
    SYNCED_AUTOFILL_ROLLOUT("Synced-Autofill-Rollout", bh0.c),
    SYNCED_HISTORY_ROLLOUT("Synced-History-Rollout", bh0.b),
    AAD_SYNCED_TABS_ROLLOUT("Aad-Synced-Tabs-Rollout", bh0.b),
    AAD_SYNCED_TABS_POPUP_ROLLOUT("Aad-Synced-Tabs-Popup-Rollout", bh0.b),
    AAD_SYNCED_PASSWORDS_ROLLOUT("Aad-Synced-Passwords-Rollout", bh0.c),
    AAD_SYNCED_AUTOFILL_ROLLOUT("Aad-Synced-Autofill-Rollout", bh0.c),
    AAD_SYNCED_HISTORY_ROLLOUT("Aad-Synced-History-Rollout", bh0.b),
    EXTENSION_FRAMEWORK_ROLLOUT("Extension-Framework-Rollout", bh0.c, ModificationVisibility.APP_START),
    EXPERIMENT_TEST1("ExperimentTest1", bh0.g),
    EXPERIMENT_TEST2("ExperimentTest2", bh0.g),
    EXPERIMENT_TEST3("ExperimentTest3", bh0.e),
    EXPERIMENT_ADDRESS_BAR_STYLE("ExperimentAddressBarStyle", bh0.g),
    EXPERIMENT_DISABLE_BING_ENGINE_IN_ZH_CN("ExperimentDisableBingEngineInZHCN", bh0.g),
    EXPERIMENT_CORE_UX_UPDATE("CoreUXUpdate", bh0.g),
    ANAHEIM_NTP("Anaheim-NTP-mobile", bh0.c),
    TRACKING_PREVENTION_ROLLOUT("Tracking-Prevention-Rollout", bh0.c),
    TABCENTER_1911_ROLLOUT("TabCenter-1911-Rollout", bh0.b),
    COLLECTIONS_BASIC_ENTITY_EXTRACTION_ROLLOUT("CollectionsBasicEntityExtractionRollout", bh0.c),
    COLLECTIONS_ADVANCED_ENTITY_EXTRACTION_ROLLOUT("CollectionsAdvancedEntityExtractionRollout", bh0.b),
    ANAHEIM_SYNC_MIGRATION_ROLLOUT("Sync-Migration-Rollout", bh0.b),
    FAMILY_SAFETY_ALL_FEATURES_ROLLOUT("Family-Safety-All-Features-Rollout", bh0.b),
    FAMILY_SAFETY_ACTIVITY_REPORTING_ROLLOUT("Family-Safety-Activity-Reporting-Rollout", bh0.b),
    FAMILY_SAFETY_WEB_EXCEPTION_ROLLOUT("Family-Safety-Web-Exception-Rollout", bh0.b),
    FAMILY_SAFETY_SAFE_SEARCH_ROLLOUT("Family-Safety-Safe-Search-Rollout", bh0.b),
    FAMILY_SAFETY_SIGN_OUT_ROLLOUT("Family-Safety-Sign-Out-Rollout", bh0.b),
    FAMILY_SAFETY_INPRIVATE_MODE_ROLLOUT("Family-Safety-InPrivate-Mode-Rollout", bh0.b),
    HISTOGRAM_REPORTING_ROLLOUT("Histogram-Reporting-Rollout", bh0.b),
    FAMILY_SAFETY_FAMILY_APP_BROADCAST_ROLLOUT("Family-Safety-Family-App-Broadcast-Rollout", bh0.b),
    FAMILY_SAFETY_BLOCK_EMBED_VIDEO_ROLLOUT("Family-Safety-Embed-Video-Rollout", bh0.b),
    FAMILY_SAFETY_PROFILE_LOGO_ROLLOUT("Family-Safety-Profile-Logo-Rollout", bh0.b),
    PROMPT_RUBY_TO_ANAHEIM("Prompt-Ruby-To-Anaheim-Rollout", bh0.b),
    BING_WIDGET_PIN_SEARCH_HISTORY_ROLLOUT("BingWidgetPinSearchHistoryRollout", bh0.b),
    INSTANT_SEARCH_PROMOTE_ROLLOUT("Instant-Search-Promote-Rollout", bh0.b),
    COUPONS_ENABLE_COPYCOUPONVIEW_ROLLOUT("Coupons-Enable-CopyCouponView-Rollout", bh0.b),
    COUPONS_ENABLE_AUTOAPPLY_ROLLOUT("Coupons-Enable-AutoApply-Rollout", bh0.b),
    COUPONS_ENABLE_DEALS_SITES_ROLLOUT("Coupons-Enable-Deals-Sites-Rollout", bh0.b),
    COUPONS_ENABLE_AUTO_APPLY_WITH_ACCESSIBILITY_ROLLOUT("Coupons-Enable-AutoApply-With-Accessibility-Rollout", bh0.b);
    
    public final dh0<Object> featureDefinition;
    public final String jsonKey;
    public final ModificationVisibility modificationVisibility;

    /* compiled from: PG */
    public enum ModificationVisibility {
        RUNTIME,
        APP_START
    }

    /* access modifiers changed from: public */
    FeatureManager$Feature(String str, dh0 dh0) {
        this(r7, r8, str, dh0, ModificationVisibility.RUNTIME);
    }

    /* access modifiers changed from: public */
    FeatureManager$Feature(String str, dh0 dh0, ModificationVisibility modificationVisibility2) {
        this.jsonKey = str;
        this.featureDefinition = dh0;
        this.modificationVisibility = modificationVisibility2;
    }
}
