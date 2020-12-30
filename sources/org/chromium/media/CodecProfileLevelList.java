package org.chromium.media;

import android.media.MediaCodecInfo;
import com.citrix.mdx.common.MDXDictionary;
import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.annotations.MainDex;

@MainDex
/* compiled from: PG */
public class CodecProfileLevelList {

    /* renamed from: a */
    public final List<CodecProfileLevelAdapter> f2442a = new ArrayList();

    /* compiled from: PG */
    public static class CodecProfileLevelAdapter {

        /* renamed from: a */
        public final int f2443a;

        /* renamed from: b */
        public final int f2444b;

        /* renamed from: c */
        public final int f2445c;

        public CodecProfileLevelAdapter(int i, int i2, int i3) {
            this.f2443a = i;
            this.f2444b = i2;
            this.f2445c = i3;
        }

        public int getCodec() {
            return this.f2443a;
        }

        public int getLevel() {
            return this.f2445c;
        }

        public int getProfile() {
            return this.f2444b;
        }
    }

    /* compiled from: PG */
    public static class UnsupportedCodecProfileException extends RuntimeException {
        public UnsupportedCodecProfileException() {
        }
    }

    /* renamed from: b */
    public static int m3482b(int i, int i2) {
        if (i != 1) {
            if (i != 6) {
                if (i != 7) {
                    if (i != 8) {
                        throw new UnsupportedCodecProfileException();
                    } else if (i2 == 1) {
                        return 16;
                    } else {
                        if (i2 == 2 || i2 == 4096) {
                            return 17;
                        }
                        throw new UnsupportedCodecProfileException();
                    }
                } else if (i2 == 1) {
                    return 12;
                } else {
                    if (i2 == 2) {
                        return 13;
                    }
                    if (i2 == 4) {
                        return 14;
                    }
                    if (i2 == 8) {
                        return 15;
                    }
                    if (i2 == 4096) {
                        return 14;
                    }
                    if (i2 == 8192) {
                        return 15;
                    }
                    throw new UnsupportedCodecProfileException();
                }
            } else if (i2 == 1) {
                return 11;
            } else {
                throw new UnsupportedCodecProfileException();
            }
        } else if (i2 == 1) {
            return 0;
        } else {
            if (i2 == 2) {
                return 1;
            }
            if (i2 == 4) {
                return 2;
            }
            if (i2 == 8) {
                return 3;
            }
            if (i2 == 16) {
                return 4;
            }
            if (i2 == 32) {
                return 5;
            }
            if (i2 == 64) {
                return 6;
            }
            throw new UnsupportedCodecProfileException();
        }
    }

    /* renamed from: a */
    public boolean mo9790a(String str, MediaCodecInfo.CodecProfileLevel codecProfileLevel) {
        int i;
        try {
            if (str.endsWith("vp9")) {
                i = 7;
            } else if (str.endsWith("vp8")) {
                i = 6;
            } else if (str.endsWith("avc")) {
                i = 1;
            } else if (str.endsWith("hevc")) {
                i = 8;
            } else {
                throw new UnsupportedCodecProfileException();
            }
            this.f2442a.add(new CodecProfileLevelAdapter(i, m3482b(i, codecProfileLevel.profile), m3481a(i, codecProfileLevel.level)));
            return true;
        } catch (UnsupportedCodecProfileException unused) {
            return false;
        }
    }

    /* renamed from: a */
    public static int m3481a(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        if (i3 == 1) {
            switch (i4) {
                case 1:
                    return 10;
                case 4:
                    return 11;
                case 8:
                    return 12;
                case 16:
                    return 13;
                case 32:
                    return 20;
                case 64:
                    return 21;
                case ByteString.CONCATENATE_BY_COPY_SIZE:
                    return 22;
                case 256:
                    return 30;
                case 512:
                    return 31;
                case 1024:
                    return 32;
                case 2048:
                    return 40;
                case 4096:
                    return 41;
                case ByteString.MAX_READ_FROM_CHUNK_SIZE:
                    return 42;
                case 16384:
                    return 50;
                case 32768:
                    return 51;
                case MDXDictionary.DICTIONARY_CREATED:
                    return 52;
                default:
                    throw new UnsupportedCodecProfileException();
            }
        } else if (i3 != 6) {
            if (i3 != 7) {
                if (i3 != 8) {
                    throw new UnsupportedCodecProfileException();
                } else if (i4 == 1 || i4 == 2) {
                    return 30;
                } else {
                    switch (i4) {
                        case 4:
                        case 8:
                            return 60;
                        case 16:
                        case 32:
                            return 63;
                        case 64:
                        case ByteString.CONCATENATE_BY_COPY_SIZE:
                            return 90;
                        case 256:
                        case 512:
                            return 93;
                        case 1024:
                        case 2048:
                            return 120;
                        case 4096:
                        case ByteString.MAX_READ_FROM_CHUNK_SIZE:
                            return 123;
                        case 16384:
                        case 32768:
                            return 150;
                        case MDXDictionary.DICTIONARY_CREATED:
                        case 131072:
                            return 153;
                        case 262144:
                        case 524288:
                            return 156;
                        case 1048576:
                        case 2097152:
                            return 180;
                        case 4194304:
                        case 8388608:
                            return 183;
                        case 16777216:
                        case 33554432:
                            return 186;
                        default:
                            throw new UnsupportedCodecProfileException();
                    }
                }
            } else if (i4 == 1) {
                return 10;
            } else {
                if (i4 == 2) {
                    return 11;
                }
                switch (i4) {
                    case 4:
                        return 20;
                    case 8:
                        return 21;
                    case 16:
                        return 30;
                    case 32:
                        return 31;
                    case 64:
                        return 40;
                    case ByteString.CONCATENATE_BY_COPY_SIZE:
                        return 41;
                    case 256:
                        return 50;
                    case 512:
                        return 51;
                    case 1024:
                        return 52;
                    case 2048:
                        return 60;
                    case 4096:
                        return 61;
                    case ByteString.MAX_READ_FROM_CHUNK_SIZE:
                        return 62;
                    default:
                        throw new UnsupportedCodecProfileException();
                }
            }
        } else if (i4 == 1) {
            return 0;
        } else {
            if (i4 == 2) {
                return 1;
            }
            if (i4 == 4) {
                return 2;
            }
            if (i4 == 8) {
                return 3;
            }
            throw new UnsupportedCodecProfileException();
        }
    }
}
