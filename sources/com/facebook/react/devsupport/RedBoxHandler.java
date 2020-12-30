package com.facebook.react.devsupport;

import android.text.SpannedString;

/* compiled from: PG */
public interface RedBoxHandler {

    /* compiled from: PG */
    public enum ErrorType {
        JS("JS"),
        NATIVE("Native");
        
        public final String name;

        /* access modifiers changed from: public */
        ErrorType(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }

    /* compiled from: PG */
    public interface ReportCompletedListener {
        void onReportError(SpannedString spannedString);

        void onReportSuccess(SpannedString spannedString);
    }
}
