package com.microsoft.identity.common.internal.util;

import android.util.Pair;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
public final class StringUtil {
    public static int compareSemanticVersion(String str, String str2) {
        if (str2 == null) {
            return 1;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int max = Math.max(split.length, split2.length);
        int i = 0;
        while (i < max) {
            int parseInt = i < split.length ? Integer.parseInt(split[i]) : 0;
            int parseInt2 = i < split2.length ? Integer.parseInt(split2[i]) : 0;
            if (parseInt < parseInt2) {
                return -1;
            }
            if (parseInt > parseInt2) {
                return 1;
            }
            i++;
        }
        return 0;
    }

    public static String convertSetToString(Set<String> set, String str) {
        if (set == null || set.isEmpty() || str == null) {
            return BuildConfig.FLAVOR;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = set.iterator();
        sb.append(it.next());
        while (it.hasNext()) {
            sb.append(str);
            sb.append(it.next());
        }
        return sb.toString();
    }

    public static Pair<String, String> getTenantInfo(String str) {
        String str2;
        String[] split = str.split("\\.");
        String str3 = null;
        if (2 != split.length || StringExtensions.isNullOrBlank(split[0]) || StringExtensions.isNullOrBlank(split[1])) {
            str2 = null;
        } else {
            str3 = split[0];
            str2 = split[1];
        }
        return new Pair<>(str3, str2);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String join(char c, List<String> list) {
        StringBuilder sb = new StringBuilder();
        char c2 = 0;
        for (String next : list) {
            if (c2 != 0) {
                sb.append(c2);
            }
            sb.append(next);
            c2 = c;
        }
        return sb.toString();
    }
}
