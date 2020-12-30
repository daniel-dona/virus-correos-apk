package com.google.gson;

import com.microsoft.identity.common.internal.cache.CacheKeyValueDelegate;
import java.lang.reflect.Field;
import java.util.Locale;

/* compiled from: PG */
public enum FieldNamingPolicy implements LJ {
    IDENTITY {
        public String translateName(Field field) {
            return field.getName();
        }
    },
    UPPER_CAMEL_CASE {
        public String translateName(Field field) {
            return FieldNamingPolicy.upperCaseFirstLetter(field.getName());
        }
    },
    UPPER_CAMEL_CASE_WITH_SPACES {
        public String translateName(Field field) {
            return FieldNamingPolicy.upperCaseFirstLetter(FieldNamingPolicy.separateCamelCase(field.getName(), " "));
        }
    },
    LOWER_CASE_WITH_UNDERSCORES {
        public String translateName(Field field) {
            return FieldNamingPolicy.separateCamelCase(field.getName(), "_").toLowerCase(Locale.ENGLISH);
        }
    },
    LOWER_CASE_WITH_DASHES {
        public String translateName(Field field) {
            return FieldNamingPolicy.separateCamelCase(field.getName(), CacheKeyValueDelegate.CACHE_VALUE_SEPARATOR).toLowerCase(Locale.ENGLISH);
        }
    },
    LOWER_CASE_WITH_DOTS {
        public String translateName(Field field) {
            return FieldNamingPolicy.separateCamelCase(field.getName(), ".").toLowerCase(Locale.ENGLISH);
        }
    };

    public static String modifyString(char c, String str, int i) {
        if (i >= str.length()) {
            return String.valueOf(c);
        }
        return c + str.substring(i);
    }

    public static String separateCamelCase(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt) && sb.length() != 0) {
                sb.append(str2);
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    public static String upperCaseFirstLetter(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        char charAt = str.charAt(0);
        int length = str.length();
        while (i < length - 1 && !Character.isLetter(charAt)) {
            sb.append(charAt);
            i++;
            charAt = str.charAt(i);
        }
        if (Character.isUpperCase(charAt)) {
            return str;
        }
        sb.append(modifyString(Character.toUpperCase(charAt), str, i + 1));
        return sb.toString();
    }
}
