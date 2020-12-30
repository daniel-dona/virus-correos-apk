package com.microsoft.bing.answer.api.interfaces;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
/* compiled from: PG */
public @interface AnswerItemType {
    public static final short ANSWER_TYPE_COMMON_CONTENT_ITEM = 4;
    public static final short ANSWER_TYPE_DIVIDER = 3;
    public static final short ANSWER_TYPE_HEADER = 1;
    public static final short ANSWER_TYPE_SEE_MORE = 2;
    public static final short ANSWER_TYPE_WEB_AUTOSUGGEST_CURRENCY = 7;
    public static final short ANSWER_TYPE_WEB_AUTOSUGGEST_DUMMY = 11;
    public static final short ANSWER_TYPE_WEB_AUTOSUGGEST_ENTITY = 5;
    public static final short ANSWER_TYPE_WEB_AUTOSUGGEST_FINANCE = 8;
    public static final short ANSWER_TYPE_WEB_AUTOSUGGEST_HISTORY = 10;
    public static final short ANSWER_TYPE_WEB_AUTOSUGGEST_INSTANT_CARD = 14;
    public static final short ANSWER_TYPE_WEB_AUTOSUGGEST_NAVIGATOR = 9;
    public static final short ANSWER_TYPE_WEB_AUTOSUGGEST_WEATHER = 6;
    public static final short ANSWER_TYPE_WEB_BING_BUSINESS_BOOKMARK = 13;
    public static final short ANSWER_TYPE_WEB_BING_BUSINESS_BUILDING = 15;
    public static final short ANSWER_TYPE_WEB_BING_BUSINESS_PERSON = 12;
    public static final short ANSWER_TYPE_WEB_BING_BUSINESS_QNA = 16;
}
