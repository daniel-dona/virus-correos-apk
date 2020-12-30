package com.microsoft.bond;

import com.google.protobuf.ByteString;

/* compiled from: PG */
public enum BondDataType {
    BT_STOP(0),
    BT_STOP_BASE(1),
    BT_BOOL(2),
    BT_UINT8(3),
    BT_UINT16(4),
    BT_UINT32(5),
    BT_UINT64(6),
    BT_FLOAT(7),
    BT_DOUBLE(8),
    BT_STRING(9),
    BT_STRUCT(10),
    BT_LIST(11),
    BT_SET(12),
    BT_MAP(13),
    BT_INT8(14),
    BT_INT16(15),
    BT_INT32(16),
    BT_INT64(17),
    BT_WSTRING(18),
    BT_UNAVAILABLE(127),
    __INVALID_ENUM_VALUE(ByteString.CONCATENATE_BY_COPY_SIZE);
    
    public final int value;

    /* access modifiers changed from: public */
    BondDataType(int i) {
        this.value = i;
    }

    public static BondDataType fromValue(int i) {
        if (i == 127) {
            return BT_UNAVAILABLE;
        }
        switch (i) {
            case 0:
                return BT_STOP;
            case 1:
                return BT_STOP_BASE;
            case 2:
                return BT_BOOL;
            case 3:
                return BT_UINT8;
            case 4:
                return BT_UINT16;
            case 5:
                return BT_UINT32;
            case 6:
                return BT_UINT64;
            case 7:
                return BT_FLOAT;
            case 8:
                return BT_DOUBLE;
            case 9:
                return BT_STRING;
            case 10:
                return BT_STRUCT;
            case 11:
                return BT_LIST;
            case 12:
                return BT_SET;
            case 13:
                return BT_MAP;
            case 14:
                return BT_INT8;
            case 15:
                return BT_INT16;
            case 16:
                return BT_INT32;
            case 17:
                return BT_INT64;
            case 18:
                return BT_WSTRING;
            default:
                return __INVALID_ENUM_VALUE;
        }
    }

    public int getValue() {
        return this.value;
    }
}
