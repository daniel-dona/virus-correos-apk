package net.minidev.json;

/* compiled from: PG */
public abstract class JStylerObj {

    /* renamed from: a */
    public static final xB0 f1388a = new xB0((xB0) null);

    /* renamed from: b */
    public static final yB0 f1389b = new yB0((yB0) null);

    /* renamed from: c */
    public static final wB0 f1390c = new wB0((wB0) null);

    /* renamed from: d */
    public static final vB0 f1391d = new vB0((vB0) null);

    /* renamed from: e */
    public static final uB0 f1392e = new uB0((uB0) null);

    /* compiled from: PG */
    public interface MustProtect {
        boolean mustBeProtect(String str);
    }

    /* compiled from: PG */
    public interface StringProtector {
        void escape(String str, Appendable appendable);
    }

    /* renamed from: a */
    public static boolean m1355a(char c) {
        return c == 13 || c == 10 || c == 9 || c == ' ';
    }

    /* renamed from: a */
    public static boolean m1356a(String str) {
        if (str.length() < 3) {
            return false;
        }
        char charAt = str.charAt(0);
        if (charAt == 'n') {
            return str.equals("null");
        }
        if (charAt == 't') {
            return str.equals("true");
        }
        if (charAt == 'f') {
            return str.equals("false");
        }
        if (charAt == 'N') {
            return str.equals("NaN");
        }
        return false;
    }

    /* renamed from: b */
    public static boolean m1357b(char c) {
        return c == '{' || c == '[' || c == ',' || c == '}' || c == ']' || c == ':' || c == '\'' || c == '\"';
    }

    /* renamed from: c */
    public static boolean m1358c(char c) {
        if (c >= 0 && c <= 31) {
            return true;
        }
        if (c < 127 || c > 159) {
            return c >= 8192 && c <= 8447;
        }
        return true;
    }
}
