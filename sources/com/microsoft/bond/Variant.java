package com.microsoft.bond;

import com.citrix.loggersdk.BuildConfig;
import com.microsoft.bond.ProtocolReader;
import com.microsoft.bond.internal.Marshaler;
import com.microsoft.bond.internal.ReadHelper;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: PG */
public class Variant implements BondSerializable, BondMirror {
    public double double_value;
    public long int_value;
    public boolean nothing;
    public String string_value;
    public long uint_value;
    public String wstring_value;

    public Variant() {
        reset();
    }

    public static SchemaDef getRuntimeSchema() {
        return Schema.schemaDef;
    }

    public BondSerializable clone() {
        return null;
    }

    public BondMirror createInstance(StructDef structDef) {
        return null;
    }

    public final double getDouble_value() {
        return this.double_value;
    }

    public Object getField(FieldDef fieldDef) {
        short id = fieldDef.getId();
        if (id == 0) {
            return Long.valueOf(this.uint_value);
        }
        if (id == 1) {
            return Long.valueOf(this.int_value);
        }
        if (id == 2) {
            return Double.valueOf(this.double_value);
        }
        if (id == 3) {
            return this.string_value;
        }
        if (id == 4) {
            return this.wstring_value;
        }
        if (id != 5) {
            return null;
        }
        return Boolean.valueOf(this.nothing);
    }

    public final long getInt_value() {
        return this.int_value;
    }

    public final boolean getNothing() {
        return this.nothing;
    }

    public SchemaDef getSchema() {
        return getRuntimeSchema();
    }

    public final String getString_value() {
        return this.string_value;
    }

    public final long getUint_value() {
        return this.uint_value;
    }

    public final String getWstring_value() {
        return this.wstring_value;
    }

    public void marshal(ProtocolWriter protocolWriter) throws IOException {
        Marshaler.marshal(this, protocolWriter);
    }

    public boolean memberwiseCompare(Object obj) {
        if (obj == null) {
            return false;
        }
        Variant variant = (Variant) obj;
        if (!memberwiseCompareQuick(variant) || !memberwiseCompareDeep(variant)) {
            return false;
        }
        return true;
    }

    public boolean memberwiseCompareDeep(Variant variant) {
        String str;
        String str2 = this.string_value;
        if (!(str2 == null || str2.equals(variant.string_value)) || ((str = this.wstring_value) != null && !str.equals(variant.wstring_value))) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0097 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:60:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean memberwiseCompareQuick(com.microsoft.bond.Variant r8) {
        /*
            r7 = this;
            long r0 = r7.uint_value
            long r2 = r8.uint_value
            r4 = 1
            r5 = 0
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 != 0) goto L_0x000c
            r0 = 1
            goto L_0x000d
        L_0x000c:
            r0 = 0
        L_0x000d:
            if (r0 == 0) goto L_0x0019
            long r0 = r7.int_value
            long r2 = r8.int_value
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 != 0) goto L_0x0019
            r0 = 1
            goto L_0x001a
        L_0x0019:
            r0 = 0
        L_0x001a:
            if (r0 == 0) goto L_0x0037
            double r0 = r7.double_value
            boolean r0 = java.lang.Double.isNaN(r0)
            if (r0 == 0) goto L_0x002d
            double r0 = r8.double_value
            boolean r0 = java.lang.Double.isNaN(r0)
            if (r0 == 0) goto L_0x0037
            goto L_0x0035
        L_0x002d:
            double r0 = r7.double_value
            double r2 = r8.double_value
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 != 0) goto L_0x0037
        L_0x0035:
            r0 = 1
            goto L_0x0038
        L_0x0037:
            r0 = 0
        L_0x0038:
            if (r0 == 0) goto L_0x004c
            java.lang.String r0 = r7.string_value
            if (r0 != 0) goto L_0x0040
            r0 = 1
            goto L_0x0041
        L_0x0040:
            r0 = 0
        L_0x0041:
            java.lang.String r1 = r8.string_value
            if (r1 != 0) goto L_0x0047
            r1 = 1
            goto L_0x0048
        L_0x0047:
            r1 = 0
        L_0x0048:
            if (r0 != r1) goto L_0x004c
            r0 = 1
            goto L_0x004d
        L_0x004c:
            r0 = 0
        L_0x004d:
            if (r0 == 0) goto L_0x0062
            java.lang.String r0 = r7.string_value
            if (r0 != 0) goto L_0x0054
            goto L_0x0060
        L_0x0054:
            int r0 = r0.length()
            java.lang.String r1 = r8.string_value
            int r1 = r1.length()
            if (r0 != r1) goto L_0x0062
        L_0x0060:
            r0 = 1
            goto L_0x0063
        L_0x0062:
            r0 = 0
        L_0x0063:
            if (r0 == 0) goto L_0x0077
            java.lang.String r0 = r7.wstring_value
            if (r0 != 0) goto L_0x006b
            r0 = 1
            goto L_0x006c
        L_0x006b:
            r0 = 0
        L_0x006c:
            java.lang.String r1 = r8.wstring_value
            if (r1 != 0) goto L_0x0072
            r1 = 1
            goto L_0x0073
        L_0x0072:
            r1 = 0
        L_0x0073:
            if (r0 != r1) goto L_0x0077
            r0 = 1
            goto L_0x0078
        L_0x0077:
            r0 = 0
        L_0x0078:
            if (r0 == 0) goto L_0x008d
            java.lang.String r0 = r7.wstring_value
            if (r0 != 0) goto L_0x007f
            goto L_0x008b
        L_0x007f:
            int r0 = r0.length()
            java.lang.String r1 = r8.wstring_value
            int r1 = r1.length()
            if (r0 != r1) goto L_0x008d
        L_0x008b:
            r0 = 1
            goto L_0x008e
        L_0x008d:
            r0 = 0
        L_0x008e:
            if (r0 == 0) goto L_0x0097
            boolean r0 = r7.nothing
            boolean r8 = r8.nothing
            if (r0 != r8) goto L_0x0097
            goto L_0x0098
        L_0x0097:
            r4 = 0
        L_0x0098:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.bond.Variant.memberwiseCompareQuick(com.microsoft.bond.Variant):boolean");
    }

    public void read(ProtocolReader protocolReader) throws IOException {
        protocolReader.readBegin();
        readNested(protocolReader);
        protocolReader.readEnd();
    }

    public void read(ProtocolReader protocolReader, BondSerializable bondSerializable) throws IOException {
    }

    public void readNested(ProtocolReader protocolReader) throws IOException {
        if (!protocolReader.hasCapability(ProtocolCapability.TAGGED)) {
            readUntagged(protocolReader, false);
        } else if (readTagged(protocolReader, false)) {
            ReadHelper.skipPartialStruct(protocolReader);
        }
    }

    public boolean readTagged(ProtocolReader protocolReader, boolean z) throws IOException {
        ProtocolReader.FieldTag readFieldBegin;
        boolean z2;
        protocolReader.readStructBegin(z);
        while (true) {
            readFieldBegin = protocolReader.readFieldBegin();
            BondDataType bondDataType = readFieldBegin.type;
            z2 = true;
            if (bondDataType != BondDataType.BT_STOP && bondDataType != BondDataType.BT_STOP_BASE) {
                int i = readFieldBegin.id;
                if (i == 0) {
                    this.uint_value = ReadHelper.readUInt64(protocolReader, bondDataType);
                } else if (i == 1) {
                    this.int_value = ReadHelper.readInt64(protocolReader, bondDataType);
                } else if (i == 2) {
                    this.double_value = ReadHelper.readDouble(protocolReader, bondDataType);
                } else if (i == 3) {
                    this.string_value = ReadHelper.readString(protocolReader, bondDataType);
                } else if (i == 4) {
                    this.wstring_value = ReadHelper.readWString(protocolReader, bondDataType);
                } else if (i != 5) {
                    protocolReader.skip(bondDataType);
                } else {
                    this.nothing = ReadHelper.readBool(protocolReader, bondDataType);
                }
                protocolReader.readFieldEnd();
            }
        }
        if (readFieldBegin.type != BondDataType.BT_STOP_BASE) {
            z2 = false;
        }
        protocolReader.readStructEnd();
        return z2;
    }

    public void readUntagged(ProtocolReader protocolReader, boolean z) throws IOException {
        boolean hasCapability = protocolReader.hasCapability(ProtocolCapability.CAN_OMIT_FIELDS);
        protocolReader.readStructBegin(z);
        if (!hasCapability || !protocolReader.readFieldOmitted()) {
            this.uint_value = protocolReader.readUInt64();
        }
        if (!hasCapability || !protocolReader.readFieldOmitted()) {
            this.int_value = protocolReader.readInt64();
        }
        if (!hasCapability || !protocolReader.readFieldOmitted()) {
            this.double_value = protocolReader.readDouble();
        }
        if (!hasCapability || !protocolReader.readFieldOmitted()) {
            this.string_value = protocolReader.readString();
        }
        if (!hasCapability || !protocolReader.readFieldOmitted()) {
            this.wstring_value = protocolReader.readWString();
        }
        if (!hasCapability || !protocolReader.readFieldOmitted()) {
            this.nothing = protocolReader.readBool();
        }
        protocolReader.readStructEnd();
    }

    public void reset() {
        reset("Variant", "com.microsoft.bond.Variant");
    }

    public final void setDouble_value(double d) {
        this.double_value = d;
    }

    public void setField(FieldDef fieldDef, Object obj) {
        short id = fieldDef.getId();
        if (id == 0) {
            this.uint_value = ((Long) obj).longValue();
        } else if (id == 1) {
            this.int_value = ((Long) obj).longValue();
        } else if (id == 2) {
            this.double_value = ((Double) obj).doubleValue();
        } else if (id == 3) {
            this.string_value = (String) obj;
        } else if (id == 4) {
            this.wstring_value = (String) obj;
        } else if (id == 5) {
            this.nothing = ((Boolean) obj).booleanValue();
        }
    }

    public final void setInt_value(long j) {
        this.int_value = j;
    }

    public final void setNothing(boolean z) {
        this.nothing = z;
    }

    public final void setString_value(String str) {
        this.string_value = str;
    }

    public final void setUint_value(long j) {
        this.uint_value = j;
    }

    public final void setWstring_value(String str) {
        this.wstring_value = str;
    }

    public void unmarshal(InputStream inputStream) throws IOException {
        Marshaler.unmarshal(inputStream, this);
    }

    public void write(ProtocolWriter protocolWriter) throws IOException {
        protocolWriter.writeBegin();
        ProtocolWriter firstPassWriter = protocolWriter.getFirstPassWriter();
        if (firstPassWriter != null) {
            writeNested(firstPassWriter, false);
            writeNested(protocolWriter, false);
        } else {
            writeNested(protocolWriter, false);
        }
        protocolWriter.writeEnd();
    }

    public void writeNested(ProtocolWriter protocolWriter, boolean z) throws IOException {
        boolean hasCapability = protocolWriter.hasCapability(ProtocolCapability.CAN_OMIT_FIELDS);
        protocolWriter.writeStructBegin(Schema.metadata, z);
        boolean z2 = false;
        if (!hasCapability || this.uint_value != Schema.access$000().getDefault_value().getUint_value()) {
            protocolWriter.writeFieldBegin(BondDataType.BT_UINT64, 0, Schema.access$000());
            protocolWriter.writeUInt64(this.uint_value);
            protocolWriter.writeFieldEnd();
        } else {
            protocolWriter.writeFieldOmitted(BondDataType.BT_UINT64, 0, Schema.access$000());
        }
        if (!hasCapability || this.int_value != Schema.access$100().getDefault_value().getInt_value()) {
            protocolWriter.writeFieldBegin(BondDataType.BT_INT64, 1, Schema.access$100());
            protocolWriter.writeInt64(this.int_value);
            protocolWriter.writeFieldEnd();
        } else {
            protocolWriter.writeFieldOmitted(BondDataType.BT_INT64, 1, Schema.access$100());
        }
        if (!hasCapability || this.double_value != Schema.access$200().getDefault_value().getDouble_value()) {
            protocolWriter.writeFieldBegin(BondDataType.BT_DOUBLE, 2, Schema.access$200());
            protocolWriter.writeDouble(this.double_value);
            protocolWriter.writeFieldEnd();
        } else {
            protocolWriter.writeFieldOmitted(BondDataType.BT_DOUBLE, 2, Schema.access$200());
        }
        if (!hasCapability || this.string_value != Schema.access$300().getDefault_value().getString_value()) {
            protocolWriter.writeFieldBegin(BondDataType.BT_STRING, 3, Schema.access$300());
            protocolWriter.writeString(this.string_value);
            protocolWriter.writeFieldEnd();
        } else {
            protocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 3, Schema.access$300());
        }
        if (!hasCapability || this.wstring_value != Schema.access$400().getDefault_value().getWstring_value()) {
            protocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 4, Schema.access$400());
            protocolWriter.writeWString(this.wstring_value);
            protocolWriter.writeFieldEnd();
        } else {
            protocolWriter.writeFieldOmitted(BondDataType.BT_WSTRING, 4, Schema.access$400());
        }
        if (hasCapability) {
            boolean z3 = this.nothing;
            if (Schema.access$500().getDefault_value().getUint_value() != 0) {
                z2 = true;
            }
            if (z3 == z2) {
                protocolWriter.writeFieldOmitted(BondDataType.BT_BOOL, 5, Schema.access$500());
                protocolWriter.writeStructEnd(z);
            }
        }
        protocolWriter.writeFieldBegin(BondDataType.BT_BOOL, 5, Schema.access$500());
        protocolWriter.writeBool(this.nothing);
        protocolWriter.writeFieldEnd();
        protocolWriter.writeStructEnd(z);
    }

    public void reset(String str, String str2) {
        this.uint_value = 0;
        this.int_value = 0;
        this.double_value = 0.0d;
        this.string_value = BuildConfig.FLAVOR;
        this.wstring_value = BuildConfig.FLAVOR;
        this.nothing = false;
    }

    public void unmarshal(InputStream inputStream, BondSerializable bondSerializable) throws IOException {
        Marshaler.unmarshal(inputStream, (SchemaDef) bondSerializable, this);
    }
}
