package com.facebook.cache.disk;

import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.disk.DiskStorage;
import com.facebook.common.file.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
public class DefaultDiskStorage implements DiskStorage {

    /* renamed from: f */
    public static final Class<?> f227f = DefaultDiskStorage.class;

    /* renamed from: g */
    public static final long f228g = TimeUnit.MINUTES.toMillis(30);

    /* renamed from: a */
    public final File f229a;

    /* renamed from: b */
    public final boolean f230b;

    /* renamed from: c */
    public final File f231c;

    /* renamed from: d */
    public final CacheErrorLogger f232d;

    /* renamed from: e */
    public final Jq f233e;

    /* compiled from: PG */
    public static class IncompleteFileException extends IOException {
        public final long actual;
        public final long expected;

        public IncompleteFileException(long j, long j2) {
            super("File was not written completely. Expected: " + j + ", found: " + j2);
            this.expected = j;
            this.actual = j2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008d A[SYNTHETIC, Splitter:B:29:0x008d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DefaultDiskStorage(java.io.File r8, int r9, com.facebook.cache.common.CacheErrorLogger r10) {
        /*
            r7 = this;
            r7.<init>()
            if (r8 == 0) goto L_0x00b2
            r7.f229a = r8
            r0 = 0
            r1 = 0
            java.io.File r2 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ Exception -> 0x003c }
            if (r2 == 0) goto L_0x0049
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x003c }
            java.lang.String r8 = r8.getCanonicalPath()     // Catch:{ IOException -> 0x001e }
            boolean r8 = r8.contains(r2)     // Catch:{ IOException -> 0x001c }
            goto L_0x004a
        L_0x001c:
            r2 = move-exception
            goto L_0x0020
        L_0x001e:
            r2 = move-exception
            r8 = r0
        L_0x0020:
            com.facebook.cache.common.CacheErrorLogger$CacheErrorCategory r3 = com.facebook.cache.common.CacheErrorLogger.CacheErrorCategory.OTHER     // Catch:{ Exception -> 0x003c }
            java.lang.Class<?> r4 = f227f     // Catch:{ Exception -> 0x003c }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003c }
            r5.<init>()     // Catch:{ Exception -> 0x003c }
            java.lang.String r6 = "failed to read folder to check if external: "
            r5.append(r6)     // Catch:{ Exception -> 0x003c }
            r5.append(r8)     // Catch:{ Exception -> 0x003c }
            java.lang.String r8 = r5.toString()     // Catch:{ Exception -> 0x003c }
            r5 = r10
            tp r5 = (tp) r5
            r5.a(r3, r4, r8, r2)     // Catch:{ Exception -> 0x003c }
            goto L_0x0049
        L_0x003c:
            r8 = move-exception
            com.facebook.cache.common.CacheErrorLogger$CacheErrorCategory r2 = com.facebook.cache.common.CacheErrorLogger.CacheErrorCategory.OTHER
            java.lang.Class<?> r3 = f227f
            r4 = r10
            tp r4 = (tp) r4
            java.lang.String r5 = "failed to get the external storage directory!"
            r4.a(r2, r3, r5, r8)
        L_0x0049:
            r8 = 0
        L_0x004a:
            r7.f230b = r8
            java.io.File r8 = new java.io.File
            java.io.File r2 = r7.f229a
            r3 = 3
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.String r4 = "v2"
            r3[r1] = r4
            r4 = 100
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r5 = 1
            r3[r5] = r4
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r4 = 2
            r3[r4] = r9
            java.lang.String r9 = "%s.ols%d.%d"
            java.lang.String r9 = java.lang.String.format(r0, r9, r3)
            r8.<init>(r2, r9)
            r7.f231c = r8
            r7.f232d = r10
            java.io.File r8 = r7.f229a
            boolean r8 = r8.exists()
            if (r8 != 0) goto L_0x007d
            goto L_0x008a
        L_0x007d:
            java.io.File r8 = r7.f231c
            boolean r8 = r8.exists()
            if (r8 != 0) goto L_0x008b
            java.io.File r8 = r7.f229a
            bq.b(r8)
        L_0x008a:
            r1 = 1
        L_0x008b:
            if (r1 == 0) goto L_0x00ad
            java.io.File r8 = r7.f231c     // Catch:{ CreateDirectoryException -> 0x0093 }
            com.facebook.common.file.FileUtils.m228a(r8)     // Catch:{ CreateDirectoryException -> 0x0093 }
            goto L_0x00ad
        L_0x0093:
            com.facebook.cache.common.CacheErrorLogger r8 = r7.f232d
            com.facebook.cache.common.CacheErrorLogger$CacheErrorCategory r9 = com.facebook.cache.common.CacheErrorLogger.CacheErrorCategory.WRITE_CREATE_DIR
            java.lang.Class<?> r10 = f227f
            java.lang.String r1 = "version directory could not be created: "
            java.lang.StringBuilder r1 = Eo.a(r1)
            java.io.File r2 = r7.f231c
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            tp r8 = (tp) r8
            r8.a(r9, r10, r1, r0)
        L_0x00ad:
            Lq r8 = Lq.a
            r7.f233e = r8
            return
        L_0x00b2:
            java.lang.NullPointerException r8 = new java.lang.NullPointerException
            r8.<init>()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.cache.disk.DefaultDiskStorage.<init>(java.io.File, int, com.facebook.cache.common.CacheErrorLogger):void");
    }

    /* renamed from: b */
    public final String mo303b(String str) {
        String valueOf = String.valueOf(Math.abs(str.hashCode() % 100));
        StringBuilder sb = new StringBuilder();
        sb.append(this.f231c);
        return Eo.a(sb, File.separator, valueOf);
    }

    /* renamed from: c */
    public np mo306c(String str, Object obj) {
        File a = mo300a(str);
        if (!a.exists()) {
            return null;
        }
        a.setLastModified(this.f233e.a());
        return op.a(a);
    }

    public void clearAll() {
        bq.a(this.f229a);
    }

    public boolean isExternal() {
        return this.f230b;
    }

    public long remove(String str) {
        return mo298a(mo300a(str));
    }

    /* renamed from: a */
    public File mo300a(String str) {
        Ap ap = new Ap(".cnt", str, (xp) null);
        StringBuilder a = Eo.a(mo303b(ap.b));
        a.append(File.separator);
        a.append(ap.b);
        a.append(ap.a);
        return new File(a.toString());
    }

    /* renamed from: b */
    public boolean mo305b(String str, Object obj) {
        return mo300a(str).exists();
    }

    /* renamed from: c */
    public static /* synthetic */ String m211c(String str) {
        if (".cnt".equals(str)) {
            return ".cnt";
        }
        if (".tmp".equals(str)) {
            return ".tmp";
        }
        return null;
    }

    /* renamed from: b */
    public Collection mo304b() throws IOException {
        yp ypVar = new yp(this, (xp) null);
        bq.a(this.f231c, ypVar);
        return Collections.unmodifiableList(ypVar.a);
    }

    /* renamed from: a */
    public void mo301a() {
        bq.a(this.f229a, new Cp(this, (xp) null));
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0039 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003a  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final Ap mo302b(java.io.File r7) {
        /*
            r6 = this;
            java.lang.String r0 = r7.getName()
            r1 = 46
            int r2 = r0.lastIndexOf(r1)
            r3 = 0
            if (r2 > 0) goto L_0x000e
            goto L_0x002c
        L_0x000e:
            java.lang.String r4 = r0.substring(r2)
            java.lang.String r4 = m211c(r4)
            if (r4 != 0) goto L_0x0019
            goto L_0x002c
        L_0x0019:
            r5 = 0
            java.lang.String r0 = r0.substring(r5, r2)
            java.lang.String r2 = ".tmp"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0032
            int r1 = r0.lastIndexOf(r1)
            if (r1 > 0) goto L_0x002e
        L_0x002c:
            r1 = r3
            goto L_0x0037
        L_0x002e:
            java.lang.String r0 = r0.substring(r5, r1)
        L_0x0032:
            Ap r1 = new Ap
            r1.<init>(r4, r0)
        L_0x0037:
            if (r1 != 0) goto L_0x003a
            return r3
        L_0x003a:
            java.lang.String r0 = r1.b
            java.io.File r2 = new java.io.File
            java.lang.String r0 = r6.mo303b((java.lang.String) r0)
            r2.<init>(r0)
            java.io.File r7 = r7.getParentFile()
            boolean r7 = r2.equals(r7)
            if (r7 == 0) goto L_0x0050
            goto L_0x0051
        L_0x0050:
            r1 = r3
        L_0x0051:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.cache.disk.DefaultDiskStorage.mo302b(java.io.File):Ap");
    }

    /* renamed from: a */
    public DiskStorage.Inserter mo299a(String str, Object obj) throws IOException {
        Ap ap = new Ap(".tmp", str, (xp) null);
        File file = new File(mo303b(ap.b));
        if (!file.exists()) {
            try {
                FileUtils.m228a(file);
            } catch (FileUtils.CreateDirectoryException e) {
                this.f232d.a(CacheErrorLogger.CacheErrorCategory.WRITE_CREATE_DIR, f227f, "insert", e);
                throw e;
            }
        }
        try {
            return new Bp(this, str, File.createTempFile(ap.b + ".", ".tmp", file));
        } catch (IOException e2) {
            this.f232d.a(CacheErrorLogger.CacheErrorCategory.WRITE_CREATE_TEMPFILE, f227f, "insert", e2);
            throw e2;
        }
    }

    /* renamed from: a */
    public long mo297a(DiskStorage.Entry entry) {
        return mo298a(((zp) entry).b.a);
    }

    /* renamed from: a */
    public final long mo298a(File file) {
        if (!file.exists()) {
            return 0;
        }
        long length = file.length();
        if (file.delete()) {
            return length;
        }
        return -1;
    }
}
