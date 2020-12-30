package net.minidev.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
public class JSONArray extends ArrayList<Object> implements List<Object>, nB0, pB0 {
    public static final long serialVersionUID = 9106884089231309568L;

    public static String toJSONString(List<? extends Object> list) {
        return toJSONString(list, tB0.a);
    }

    public static void writeJSONString(Iterable<? extends Object> iterable, Appendable appendable, qB0 qb0) throws IOException {
        if (iterable == null) {
            appendable.append("null");
        } else {
            aC0.g.a(iterable, appendable, qb0);
        }
    }

    public JSONArray appendElement(Object obj) {
        add(obj);
        return this;
    }

    public void merge(Object obj) {
        JSONObject.merge(this, obj);
    }

    public String toString() {
        return toJSONString();
    }

    public static String toJSONString(List<? extends Object> list, qB0 qb0) {
        StringBuilder sb = new StringBuilder();
        try {
            writeJSONString(list, sb, qb0);
        } catch (IOException unused) {
        }
        return sb.toString();
    }

    public String toString(qB0 qb0) {
        return toJSONString(qb0);
    }

    public static void writeJSONString(List<? extends Object> list, Appendable appendable) throws IOException {
        writeJSONString(list, appendable, tB0.a);
    }

    public void writeJSONString(Appendable appendable) throws IOException {
        writeJSONString(this, appendable, tB0.a);
    }

    public String toJSONString() {
        return toJSONString(this, tB0.a);
    }

    public void writeJSONString(Appendable appendable, qB0 qb0) throws IOException {
        writeJSONString(this, appendable, qb0);
    }

    public String toJSONString(qB0 qb0) {
        return toJSONString(this, qb0);
    }
}
