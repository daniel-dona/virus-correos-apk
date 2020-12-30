package com.airbnb.lottie.network;

/* compiled from: PG */
public enum FileExtension {
    JSON(".json"),
    ZIP(".zip");
    
    public final String extension;

    /* access modifiers changed from: public */
    FileExtension(String str) {
        this.extension = str;
    }

    public static FileExtension forFile(String str) {
        for (FileExtension fileExtension : values()) {
            if (str.endsWith(fileExtension.extension)) {
                return fileExtension;
            }
        }
        jk.b("Unable to find correct extension for " + str);
        return JSON;
    }

    public String tempExtension() {
        StringBuilder a = Eo.a(".temp");
        a.append(this.extension);
        return a.toString();
    }

    public String toString() {
        return this.extension;
    }
}
