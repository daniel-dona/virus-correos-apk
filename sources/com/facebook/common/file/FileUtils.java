package com.facebook.common.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* compiled from: PG */
public abstract class FileUtils {

    /* compiled from: PG */
    public static class CreateDirectoryException extends IOException {
        public CreateDirectoryException(String str) {
            super(str);
        }

        public CreateDirectoryException(String str, Throwable th) {
            super(str);
            initCause(th);
        }
    }

    /* compiled from: PG */
    public static class FileDeleteException extends IOException {
        public FileDeleteException(String str) {
            super(str);
        }
    }

    /* compiled from: PG */
    public static class ParentDirNotFoundException extends FileNotFoundException {
        public ParentDirNotFoundException(String str) {
            super(str);
        }
    }

    /* compiled from: PG */
    public static class RenameException extends IOException {
        public RenameException(String str) {
            super(str);
        }

        public RenameException(String str, Throwable th) {
            super(str);
            initCause(th);
        }
    }

    /* renamed from: a */
    public static void m228a(File file) throws CreateDirectoryException {
        if (file.exists()) {
            if (!file.isDirectory()) {
                if (!file.delete()) {
                    throw new CreateDirectoryException(file.getAbsolutePath(), new FileDeleteException(file.getAbsolutePath()));
                }
            } else {
                return;
            }
        }
        if (!file.mkdirs() && !file.isDirectory()) {
            throw new CreateDirectoryException(file.getAbsolutePath());
        }
    }

    /* renamed from: a */
    public static void m229a(File file, File file2) throws RenameException {
        if (file == null) {
            throw new NullPointerException();
        } else if (file2 != null) {
            file2.delete();
            if (!file.renameTo(file2)) {
                Throwable th = null;
                if (file2.exists()) {
                    th = new FileDeleteException(file2.getAbsolutePath());
                } else if (!file.getParentFile().exists()) {
                    th = new ParentDirNotFoundException(file.getAbsolutePath());
                } else if (!file.exists()) {
                    th = new FileNotFoundException(file.getAbsolutePath());
                }
                StringBuilder a = Eo.a("Unknown error renaming ");
                a.append(file.getAbsolutePath());
                a.append(" to ");
                a.append(file2.getAbsolutePath());
                throw new RenameException(a.toString(), th);
            }
        } else {
            throw new NullPointerException();
        }
    }
}
