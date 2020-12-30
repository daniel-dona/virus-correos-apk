package com.citrix.worx.sdk;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* compiled from: PG */
public class Utils {
    public static final int BUFFER_SIZE = 4096;
    public static final String TAG = "CtxLog.Utils";

    public static boolean archive(File[] fileArr, File file) {
        ZipOutputStream zipOutputStream;
        if (fileArr == null || fileArr.length == 0 || file == null) {
            CtxLog.Error(TAG, "archive: Invalid Arguments");
            return false;
        }
        FileInputStream fileInputStream = null;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
            try {
                byte[] bArr = new byte[4096];
                int length = fileArr.length;
                int i = 0;
                while (i < length) {
                    File file2 = fileArr[i];
                    FileInputStream fileInputStream2 = new FileInputStream(file2);
                    try {
                        CtxLog.Info(TAG, "zipping file: " + file2.getAbsolutePath());
                        zipOutputStream.putNextEntry(new ZipEntry(file2.getName()));
                        while (true) {
                            int read = fileInputStream2.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            zipOutputStream.write(bArr, 0, read);
                        }
                        zipOutputStream.closeEntry();
                        fileInputStream2.close();
                        i++;
                    } catch (IOException e) {
                        e = e;
                        fileInputStream = fileInputStream2;
                        try {
                            CtxLog.Error(TAG, "IOException while creating zip file: " + e.getMessage());
                            closeStreamQuietly(fileInputStream);
                            closeStreamQuietly(zipOutputStream);
                            return false;
                        } catch (Throwable th) {
                            th = th;
                            closeStreamQuietly(fileInputStream);
                            closeStreamQuietly(zipOutputStream);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = fileInputStream2;
                        closeStreamQuietly(fileInputStream);
                        closeStreamQuietly(zipOutputStream);
                        throw th;
                    }
                }
                closeStreamQuietly((Closeable) null);
                closeStreamQuietly(zipOutputStream);
                return true;
            } catch (IOException e2) {
                e = e2;
                CtxLog.Error(TAG, "IOException while creating zip file: " + e.getMessage());
                closeStreamQuietly(fileInputStream);
                closeStreamQuietly(zipOutputStream);
                return false;
            }
        } catch (IOException e3) {
            e = e3;
            zipOutputStream = null;
            CtxLog.Error(TAG, "IOException while creating zip file: " + e.getMessage());
            closeStreamQuietly(fileInputStream);
            closeStreamQuietly(zipOutputStream);
            return false;
        } catch (Throwable th3) {
            th = th3;
            zipOutputStream = null;
            closeStreamQuietly(fileInputStream);
            closeStreamQuietly(zipOutputStream);
            throw th;
        }
    }

    public static void closeStreamQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                StringBuilder a = Eo.a("Stream close error : ");
                a.append(e.getMessage());
                CtxLog.Error(TAG, a.toString());
            }
        }
    }

    public static boolean emptyFolder(File file) {
        if (!file.isDirectory()) {
            return true;
        }
        String[] list = file.list();
        boolean z = true;
        for (String file2 : list) {
            z = removeFolder(new File(file, file2));
            if (!z) {
                return false;
            }
        }
        return z;
    }

    public static List<File> getRecursiveFileList(File[] fileArr) {
        ArrayList arrayList = new ArrayList();
        for (File file : fileArr) {
            if (file.isDirectory()) {
                arrayList.addAll(getRecursiveFileList(file.listFiles()));
            } else {
                arrayList.add(file);
            }
        }
        return arrayList;
    }

    public static boolean removeFolder(File file) {
        boolean z = true;
        if (file.isDirectory()) {
            String[] list = file.list();
            boolean z2 = true;
            for (String file2 : list) {
                z2 = removeFolder(new File(file, file2));
                if (!z2) {
                    return false;
                }
            }
            z = z2;
        }
        return file.exists() ? file.delete() : z;
    }

    public static void sortFilesByModifiedTimeFirst(File[] fileArr) {
        if (fileArr != null && fileArr.length >= 2) {
            Arrays.sort(fileArr, new Comparator<File>() {
                public int compare(File file, File file2) {
                    return Long.valueOf(file2.lastModified()).compareTo(Long.valueOf(file.lastModified()));
                }
            });
        }
    }
}
