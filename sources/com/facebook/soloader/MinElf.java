package com.facebook.soloader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

/* compiled from: PG */
public abstract class MinElf {

    /* compiled from: PG */
    public static class ElfError extends RuntimeException {
        public ElfError(String str) {
            super(str);
        }
    }

    /* renamed from: a */
    public static String[] m680a(FileChannel fileChannel) throws IOException {
        long j;
        long j2;
        int i;
        long j3;
        boolean z;
        long j4;
        long j5;
        long j6;
        long j7;
        long j8;
        long j9;
        long j10;
        long j11;
        long j12;
        long j13;
        long j14;
        long j15;
        long j16;
        long j17;
        FileChannel fileChannel2 = fileChannel;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        if (m682c(fileChannel2, allocate, 0) == 1179403647) {
            boolean z2 = true;
            if (m683d(fileChannel2, allocate, 4) != 1) {
                z2 = false;
            }
            if (m683d(fileChannel2, allocate, 5) == 2) {
                allocate.order(ByteOrder.BIG_ENDIAN);
            }
            if (z2) {
                j = m682c(fileChannel2, allocate, 28);
            } else {
                j = m678a(fileChannel2, allocate, 32);
            }
            if (z2) {
                j2 = (long) m681b(fileChannel2, allocate, 44);
            } else {
                j2 = (long) m681b(fileChannel2, allocate, 56);
            }
            if (z2) {
                i = m681b(fileChannel2, allocate, 42);
            } else {
                i = m681b(fileChannel2, allocate, 54);
            }
            if (j2 == 65535) {
                if (z2) {
                    j16 = m682c(fileChannel2, allocate, 32);
                } else {
                    j16 = m678a(fileChannel2, allocate, 40);
                }
                if (z2) {
                    j17 = m682c(fileChannel2, allocate, j16 + 28);
                } else {
                    j17 = m682c(fileChannel2, allocate, j16 + 44);
                }
                j2 = j17;
            }
            long j18 = j;
            long j19 = 0;
            while (true) {
                if (j19 >= j2) {
                    j3 = 0;
                    break;
                }
                if (z2) {
                    j15 = m682c(fileChannel2, allocate, j18 + 0);
                } else {
                    j15 = m682c(fileChannel2, allocate, j18 + 0);
                }
                if (j15 == 2) {
                    j3 = z2 ? m682c(fileChannel2, allocate, j18 + 4) : m678a(fileChannel2, allocate, j18 + 8);
                } else {
                    j18 += (long) i;
                    j19++;
                }
            }
            long j20 = 0;
            if (j3 != 0) {
                long j21 = j3;
                long j22 = 0;
                int i2 = 0;
                while (true) {
                    if (z2) {
                        z = z2;
                        j4 = m682c(fileChannel2, allocate, j21 + j20);
                    } else {
                        z = z2;
                        j4 = m678a(fileChannel2, allocate, j21 + j20);
                    }
                    if (j4 == 1) {
                        j5 = j3;
                        if (i2 != Integer.MAX_VALUE) {
                            i2++;
                        } else {
                            throw new ElfError("malformed DT_NEEDED section");
                        }
                    } else {
                        j5 = j3;
                        if (j4 == 5) {
                            if (z) {
                                j14 = m682c(fileChannel2, allocate, j21 + 4);
                            } else {
                                j14 = m678a(fileChannel2, allocate, j21 + 8);
                            }
                            j22 = j14;
                        }
                    }
                    long j23 = 16;
                    j21 += z ? 8 : 16;
                    j20 = 0;
                    if (j4 != 0) {
                        z2 = z;
                        j3 = j5;
                    } else if (j22 != 0) {
                        int i3 = 0;
                        while (true) {
                            if (((long) i3) >= j2) {
                                j6 = 0;
                                break;
                            }
                            if (z) {
                                j9 = m682c(fileChannel2, allocate, j + j20);
                            } else {
                                j9 = m682c(fileChannel2, allocate, j + j20);
                            }
                            if (j9 == 1) {
                                if (z) {
                                    j11 = m682c(fileChannel2, allocate, j + 8);
                                } else {
                                    j11 = m678a(fileChannel2, allocate, j + j23);
                                }
                                if (z) {
                                    j10 = j2;
                                    j12 = m682c(fileChannel2, allocate, j + 20);
                                } else {
                                    j10 = j2;
                                    j12 = m678a(fileChannel2, allocate, j + 40);
                                }
                                if (j11 <= j22 && j22 < j12 + j11) {
                                    if (z) {
                                        j13 = m682c(fileChannel2, allocate, j + 4);
                                    } else {
                                        j13 = m678a(fileChannel2, allocate, j + 8);
                                    }
                                    j6 = j13 + (j22 - j11);
                                }
                            } else {
                                j10 = j2;
                            }
                            j += (long) i;
                            i3++;
                            j2 = j10;
                            j23 = 16;
                            j20 = 0;
                        }
                        long j24 = 0;
                        if (j6 != 0) {
                            String[] strArr = new String[i2];
                            int i4 = 0;
                            while (true) {
                                if (z) {
                                    j7 = m682c(fileChannel2, allocate, j5 + j24);
                                } else {
                                    j7 = m678a(fileChannel2, allocate, j5 + j24);
                                }
                                if (j7 == 1) {
                                    if (z) {
                                        j8 = m682c(fileChannel2, allocate, j5 + 4);
                                    } else {
                                        j8 = m678a(fileChannel2, allocate, j5 + 8);
                                    }
                                    long j25 = j8 + j6;
                                    StringBuilder sb = new StringBuilder();
                                    while (true) {
                                        long j26 = j25 + 1;
                                        short d = m683d(fileChannel2, allocate, j25);
                                        if (d == 0) {
                                            break;
                                        }
                                        sb.append((char) d);
                                        j25 = j26;
                                    }
                                    strArr[i4] = sb.toString();
                                    if (i4 != Integer.MAX_VALUE) {
                                        i4++;
                                    } else {
                                        throw new ElfError("malformed DT_NEEDED section");
                                    }
                                }
                                j5 += z ? 8 : 16;
                                if (j7 != 0) {
                                    j24 = 0;
                                } else if (i4 == strArr.length) {
                                    return strArr;
                                } else {
                                    throw new ElfError("malformed DT_NEEDED section");
                                }
                            }
                        } else {
                            throw new ElfError("did not find file offset of DT_STRTAB table");
                        }
                    } else {
                        throw new ElfError("Dynamic section string-table not found");
                    }
                }
            } else {
                throw new ElfError("ELF file does not contain dynamic linking information");
            }
        } else {
            throw new ElfError("file is not ELF");
        }
    }

    /* renamed from: b */
    public static int m681b(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        m679a(fileChannel, byteBuffer, 2, j);
        return byteBuffer.getShort() & 65535;
    }

    /* renamed from: c */
    public static long m682c(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        m679a(fileChannel, byteBuffer, 4, j);
        return ((long) byteBuffer.getInt()) & 4294967295L;
    }

    /* renamed from: d */
    public static short m683d(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        m679a(fileChannel, byteBuffer, 1, j);
        return (short) (byteBuffer.get() & 255);
    }

    /* renamed from: a */
    public static void m679a(FileChannel fileChannel, ByteBuffer byteBuffer, int i, long j) throws IOException {
        int read;
        byteBuffer.position(0);
        byteBuffer.limit(i);
        while (byteBuffer.remaining() > 0 && (read = fileChannel.read(byteBuffer, j)) != -1) {
            j += (long) read;
        }
        if (byteBuffer.remaining() <= 0) {
            byteBuffer.position(0);
            return;
        }
        throw new ElfError("ELF file truncated");
    }

    /* renamed from: a */
    public static long m678a(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        m679a(fileChannel, byteBuffer, 8, j);
        return byteBuffer.getLong();
    }
}
