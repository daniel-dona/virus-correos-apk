package com.microsoft.bond;

import com.citrix.loggersdk.BuildConfig;
import com.microsoft.bond.ProtocolReader;
import com.microsoft.bond.Variant;
import com.microsoft.bond.internal.Marshaler;
import com.microsoft.bond.internal.ReadHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
public class Metadata implements BondSerializable, BondMirror {
    public HashMap<String, String> attributes;
    public Variant default_value;
    public Modifier modifier;
    public String name;
    public String qualified_name;

    public Metadata() {
        reset();
    }

    public static SchemaDef getRuntimeSchema() {
        return Schema.schemaDef;
    }

    private void readFieldImpl_attributes(ProtocolReader protocolReader, BondDataType bondDataType) throws IOException {
        ReadHelper.validateType(bondDataType, BondDataType.BT_MAP);
        ProtocolReader.MapTag readMapContainerBegin = protocolReader.readMapContainerBegin();
        for (int i = 0; i < readMapContainerBegin.size; i++) {
            this.attributes.put(ReadHelper.readString(protocolReader, readMapContainerBegin.keyType), ReadHelper.readString(protocolReader, readMapContainerBegin.valueType));
        }
        protocolReader.readContainerEnd();
    }

    public BondSerializable clone() {
        return null;
    }

    public BondMirror createInstance(StructDef structDef) {
        if (Variant.Schema.metadata == structDef.getMetadata()) {
            return new Variant();
        }
        return null;
    }

    public final HashMap<String, String> getAttributes() {
        return this.attributes;
    }

    public final Variant getDefault_value() {
        return this.default_value;
    }

    public Object getField(FieldDef fieldDef) {
        short id = fieldDef.getId();
        if (id == 0) {
            return this.name;
        }
        if (id == 1) {
            return this.qualified_name;
        }
        if (id == 2) {
            return this.attributes;
        }
        if (id == 3) {
            return this.modifier;
        }
        if (id != 4) {
            return null;
        }
        return this.default_value;
    }

    public final Modifier getModifier() {
        return this.modifier;
    }

    public final String getName() {
        return this.name;
    }

    public final String getQualified_name() {
        return this.qualified_name;
    }

    public SchemaDef getSchema() {
        return getRuntimeSchema();
    }

    public void marshal(ProtocolWriter protocolWriter) throws IOException {
        Marshaler.marshal(this, protocolWriter);
    }

    public boolean memberwiseCompare(Object obj) {
        if (obj == null) {
            return false;
        }
        Metadata metadata = (Metadata) obj;
        if (!memberwiseCompareQuick(metadata) || !memberwiseCompareDeep(metadata)) {
            return false;
        }
        return true;
    }

    public boolean memberwiseCompareDeep(Metadata metadata) {
        Variant variant;
        HashMap<String, String> hashMap;
        boolean z;
        String str;
        String str2 = this.name;
        boolean z2 = (str2 == null || str2.equals(metadata.name)) && ((str = this.qualified_name) == null || str.equals(metadata.qualified_name));
        if (z2 && (hashMap = this.attributes) != null && hashMap.size() != 0) {
            for (Map.Entry next : this.attributes.entrySet()) {
                String str3 = (String) next.getValue();
                String str4 = metadata.attributes.get(next.getKey());
                boolean z3 = z2 && metadata.attributes.containsKey(next.getKey());
                if (z3) {
                    if (z3) {
                        if ((str3 == null) == (str4 == null)) {
                            z = true;
                            if ((!z && (str3 == null || str3.length() == str4.length())) || (str3 != null && !str3.equals(str4))) {
                                z3 = false;
                                continue;
                            } else {
                                z3 = true;
                                continue;
                            }
                        }
                    }
                    z = false;
                    if (!z && (str3 == null || str3.length() == str4.length())) {
                    }
                    z3 = false;
                    continue;
                }
                if (!z2) {
                    break;
                }
            }
        }
        if (!z2 || ((variant = this.default_value) != null && !variant.memberwiseCompare(metadata.default_value))) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x008a A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:60:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean memberwiseCompareQuick(com.microsoft.bond.Metadata r5) {
        /*
            r4 = this;
            java.lang.String r0 = r4.name
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0008
            r0 = 1
            goto L_0x0009
        L_0x0008:
            r0 = 0
        L_0x0009:
            java.lang.String r3 = r5.name
            if (r3 != 0) goto L_0x000f
            r3 = 1
            goto L_0x0010
        L_0x000f:
            r3 = 0
        L_0x0010:
            if (r0 != r3) goto L_0x0014
            r0 = 1
            goto L_0x0015
        L_0x0014:
            r0 = 0
        L_0x0015:
            if (r0 == 0) goto L_0x002a
            java.lang.String r0 = r4.name
            if (r0 != 0) goto L_0x001c
            goto L_0x0028
        L_0x001c:
            int r0 = r0.length()
            java.lang.String r3 = r5.name
            int r3 = r3.length()
            if (r0 != r3) goto L_0x002a
        L_0x0028:
            r0 = 1
            goto L_0x002b
        L_0x002a:
            r0 = 0
        L_0x002b:
            if (r0 == 0) goto L_0x003f
            java.lang.String r0 = r4.qualified_name
            if (r0 != 0) goto L_0x0033
            r0 = 1
            goto L_0x0034
        L_0x0033:
            r0 = 0
        L_0x0034:
            java.lang.String r3 = r5.qualified_name
            if (r3 != 0) goto L_0x003a
            r3 = 1
            goto L_0x003b
        L_0x003a:
            r3 = 0
        L_0x003b:
            if (r0 != r3) goto L_0x003f
            r0 = 1
            goto L_0x0040
        L_0x003f:
            r0 = 0
        L_0x0040:
            if (r0 == 0) goto L_0x0055
            java.lang.String r0 = r4.qualified_name
            if (r0 != 0) goto L_0x0047
            goto L_0x0053
        L_0x0047:
            int r0 = r0.length()
            java.lang.String r3 = r5.qualified_name
            int r3 = r3.length()
            if (r0 != r3) goto L_0x0055
        L_0x0053:
            r0 = 1
            goto L_0x0056
        L_0x0055:
            r0 = 0
        L_0x0056:
            if (r0 == 0) goto L_0x006a
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r4.attributes
            if (r0 != 0) goto L_0x005e
            r0 = 1
            goto L_0x005f
        L_0x005e:
            r0 = 0
        L_0x005f:
            java.util.HashMap<java.lang.String, java.lang.String> r3 = r5.attributes
            if (r3 != 0) goto L_0x0065
            r3 = 1
            goto L_0x0066
        L_0x0065:
            r3 = 0
        L_0x0066:
            if (r0 != r3) goto L_0x006a
            r0 = 1
            goto L_0x006b
        L_0x006a:
            r0 = 0
        L_0x006b:
            if (r0 == 0) goto L_0x0080
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r4.attributes
            if (r0 != 0) goto L_0x0072
            goto L_0x007e
        L_0x0072:
            int r0 = r0.size()
            java.util.HashMap<java.lang.String, java.lang.String> r3 = r5.attributes
            int r3 = r3.size()
            if (r0 != r3) goto L_0x0080
        L_0x007e:
            r0 = 1
            goto L_0x0081
        L_0x0080:
            r0 = 0
        L_0x0081:
            if (r0 == 0) goto L_0x008a
            com.microsoft.bond.Modifier r0 = r4.modifier
            com.microsoft.bond.Modifier r5 = r5.modifier
            if (r0 != r5) goto L_0x008a
            goto L_0x008b
        L_0x008a:
            r1 = 0
        L_0x008b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.bond.Metadata.memberwiseCompareQuick(com.microsoft.bond.Metadata):boolean");
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
                    this.name = ReadHelper.readString(protocolReader, bondDataType);
                } else if (i == 1) {
                    this.qualified_name = ReadHelper.readString(protocolReader, bondDataType);
                } else if (i == 2) {
                    readFieldImpl_attributes(protocolReader, bondDataType);
                } else if (i == 3) {
                    this.modifier = Modifier.fromValue(ReadHelper.readInt32(protocolReader, bondDataType));
                } else if (i != 4) {
                    protocolReader.skip(bondDataType);
                } else {
                    ReadHelper.validateType(bondDataType, BondDataType.BT_STRUCT);
                    this.default_value.readNested(protocolReader);
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
            this.name = protocolReader.readString();
        }
        if (!hasCapability || !protocolReader.readFieldOmitted()) {
            this.qualified_name = protocolReader.readString();
        }
        if (!hasCapability || !protocolReader.readFieldOmitted()) {
            readFieldImpl_attributes(protocolReader, BondDataType.BT_MAP);
        }
        if (!hasCapability || !protocolReader.readFieldOmitted()) {
            this.modifier = Modifier.fromValue(protocolReader.readInt32());
        }
        if (!hasCapability || !protocolReader.readFieldOmitted()) {
            this.default_value.read(protocolReader);
        }
        protocolReader.readStructEnd();
    }

    public void reset() {
        reset("Metadata", "com.microsoft.bond.Metadata");
    }

    public final void setAttributes(HashMap<String, String> hashMap) {
        this.attributes = hashMap;
    }

    public final void setDefault_value(Variant variant) {
        this.default_value = variant;
    }

    public void setField(FieldDef fieldDef, Object obj) {
        short id = fieldDef.getId();
        if (id == 0) {
            this.name = (String) obj;
        } else if (id == 1) {
            this.qualified_name = (String) obj;
        } else if (id == 2) {
            this.attributes = (HashMap) obj;
        } else if (id == 3) {
            this.modifier = (Modifier) obj;
        } else if (id == 4) {
            this.default_value = (Variant) obj;
        }
    }

    public final void setModifier(Modifier modifier2) {
        this.modifier = modifier2;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final void setQualified_name(String str) {
        this.qualified_name = str;
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
        if (!hasCapability || this.name != Schema.access$000().getDefault_value().getString_value()) {
            protocolWriter.writeFieldBegin(BondDataType.BT_STRING, 0, Schema.access$000());
            protocolWriter.writeString(this.name);
            protocolWriter.writeFieldEnd();
        } else {
            protocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 0, Schema.access$000());
        }
        if (!hasCapability || this.qualified_name != Schema.access$100().getDefault_value().getString_value()) {
            protocolWriter.writeFieldBegin(BondDataType.BT_STRING, 1, Schema.access$100());
            protocolWriter.writeString(this.qualified_name);
            protocolWriter.writeFieldEnd();
        } else {
            protocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 1, Schema.access$100());
        }
        int size = this.attributes.size();
        if (!hasCapability || size != 0) {
            protocolWriter.writeFieldBegin(BondDataType.BT_MAP, 2, Schema.access$200());
            int size2 = this.attributes.size();
            BondDataType bondDataType = BondDataType.BT_STRING;
            protocolWriter.writeContainerBegin(size2, bondDataType, bondDataType);
            for (Map.Entry next : this.attributes.entrySet()) {
                protocolWriter.writeString((String) next.getKey());
                protocolWriter.writeString((String) next.getValue());
            }
            protocolWriter.writeContainerEnd();
            protocolWriter.writeFieldEnd();
        } else {
            protocolWriter.writeFieldOmitted(BondDataType.BT_MAP, 2, Schema.access$200());
        }
        if (!hasCapability || ((long) this.modifier.getValue()) != Schema.access$300().getDefault_value().getInt_value()) {
            protocolWriter.writeFieldBegin(BondDataType.BT_INT32, 3, Schema.access$300());
            protocolWriter.writeInt32(this.modifier.getValue());
            protocolWriter.writeFieldEnd();
        } else {
            protocolWriter.writeFieldOmitted(BondDataType.BT_INT32, 3, Schema.access$300());
        }
        protocolWriter.writeFieldBegin(BondDataType.BT_STRUCT, 4, Schema.access$400());
        this.default_value.writeNested(protocolWriter, false);
        protocolWriter.writeFieldEnd();
        protocolWriter.writeStructEnd(z);
    }

    public void reset(String str, String str2) {
        this.name = BuildConfig.FLAVOR;
        this.qualified_name = BuildConfig.FLAVOR;
        HashMap<String, String> hashMap = this.attributes;
        if (hashMap == null) {
            this.attributes = new HashMap<>();
        } else {
            hashMap.clear();
        }
        this.modifier = Modifier.Optional;
        this.default_value = new Variant();
    }

    public void unmarshal(InputStream inputStream, BondSerializable bondSerializable) throws IOException {
        Marshaler.unmarshal(inputStream, (SchemaDef) bondSerializable, this);
    }
}
