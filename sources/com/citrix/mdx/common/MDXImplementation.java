package com.citrix.mdx.common;

/* compiled from: PG */
public class MDXImplementation {
    public boolean bUpgradable;
    public int[] hashes;
    public String label;
    public String mamAuth;
    public String mamClass;
    public String mdxAuth;
    public String mdxClass;
    public String offline;
    public String online;
    public String pkgName;
    public String uriAuth;

    public MDXImplementation(MDXImplementation mDXImplementation, String str, String str2) {
        this.label = str;
        this.pkgName = str2;
        this.hashes = mDXImplementation.hashes;
        this.offline = mDXImplementation.offline;
        this.online = mDXImplementation.online;
        this.mdxClass = mDXImplementation.mdxClass;
        this.mdxAuth = mDXImplementation.mdxAuth;
        this.mamClass = mDXImplementation.mamClass;
        this.mamAuth = mDXImplementation.mamAuth;
        this.uriAuth = mDXImplementation.uriAuth;
        this.bUpgradable = mDXImplementation.bUpgradable;
    }

    public MDXImplementation(String str, String str2, int[] iArr, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z) {
        this.label = str;
        this.pkgName = str2;
        this.hashes = iArr;
        this.offline = str3;
        this.online = str4;
        this.mdxClass = str5;
        this.mdxAuth = str6;
        this.mamClass = str7;
        this.mamAuth = str8;
        this.uriAuth = str9;
        this.bUpgradable = z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.label);
        sb.append(" (");
        return Eo.a(sb, this.pkgName, ")");
    }
}
