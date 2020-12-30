package com.microsoft.bond;

import com.microsoft.bond.ProtocolReader;
import com.microsoft.bond.internal.Marshaler;
import com.microsoft.bond.internal.ReadHelper;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: PG */
public class TypeDef implements BondSerializable, BondMirror {
    public boolean bonded_type;
    public TypeDef element;

    /* renamed from: id */
    public BondDataType f1263id;
    public TypeDef key;
    public short struct_def;

    public TypeDef() {
        reset();
    }

    public static SchemaDef getRuntimeSchema() {
        return Schema.schemaDef;
    }

    private void readFieldImpl_element(ProtocolReader protocolReader, BondDataType bondDataType) throws IOException {
        ReadHelper.validateType(bondDataType, BondDataType.BT_LIST);
        ProtocolReader.ListTag readContainerBegin = protocolReader.readContainerBegin();
        ReadHelper.validateType(readContainerBegin.type, BondDataType.BT_STRUCT);
        if (readContainerBegin.size == 1) {
            if (this.element == null) {
                this.element = new TypeDef();
            }
            this.element.readNested(protocolReader);
        }
        protocolReader.readContainerEnd();
    }

    private void readFieldImpl_key(ProtocolReader protocolReader, BondDataType bondDataType) throws IOException {
        ReadHelper.validateType(bondDataType, BondDataType.BT_LIST);
        ProtocolReader.ListTag readContainerBegin = protocolReader.readContainerBegin();
        ReadHelper.validateType(readContainerBegin.type, BondDataType.BT_STRUCT);
        if (readContainerBegin.size == 1) {
            if (this.key == null) {
                this.key = new TypeDef();
            }
            this.key.readNested(protocolReader);
        }
        protocolReader.readContainerEnd();
    }

    public BondSerializable clone() {
        return null;
    }

    public BondMirror createInstance(StructDef structDef) {
        if (Schema.metadata == structDef.getMetadata()) {
            return new TypeDef();
        }
        return null;
    }

    public final boolean getBonded_type() {
        return this.bonded_type;
    }

    public final TypeDef getElement() {
        return this.element;
    }

    public Object getField(FieldDef fieldDef) {
        short id = fieldDef.getId();
        if (id == 0) {
            return this.f1263id;
        }
        if (id == 1) {
            return Short.valueOf(this.struct_def);
        }
        if (id == 2) {
            return this.element;
        }
        if (id == 3) {
            return this.key;
        }
        if (id != 4) {
            return null;
        }
        return Boolean.valueOf(this.bonded_type);
    }

    public final BondDataType getId() {
        return this.f1263id;
    }

    public final TypeDef getKey() {
        return this.key;
    }

    public SchemaDef getSchema() {
        return getRuntimeSchema();
    }

    public final short getStruct_def() {
        return this.struct_def;
    }

    public void marshal(ProtocolWriter protocolWriter) throws IOException {
        Marshaler.marshal(this, protocolWriter);
    }

    public boolean memberwiseCompare(Object obj) {
        if (obj == null) {
            return false;
        }
        TypeDef typeDef = (TypeDef) obj;
        if (!memberwiseCompareQuick(typeDef) || !memberwiseCompareDeep(typeDef)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0052 A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0054 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean memberwiseCompareDeep(com.microsoft.bond.TypeDef r5) {
        /*
            r4 = this;
            com.microsoft.bond.TypeDef r0 = r4.element
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0029
            if (r0 != 0) goto L_0x000a
            r0 = 1
            goto L_0x000b
        L_0x000a:
            r0 = 0
        L_0x000b:
            com.microsoft.bond.TypeDef r3 = r5.element
            if (r3 != 0) goto L_0x0011
            r3 = 1
            goto L_0x0012
        L_0x0011:
            r3 = 0
        L_0x0012:
            if (r0 != r3) goto L_0x0016
            r0 = 1
            goto L_0x0017
        L_0x0016:
            r0 = 0
        L_0x0017:
            if (r0 == 0) goto L_0x0027
            com.microsoft.bond.TypeDef r0 = r4.element
            if (r0 != 0) goto L_0x001e
            goto L_0x0026
        L_0x001e:
            com.microsoft.bond.TypeDef r3 = r5.element
            boolean r0 = r0.memberwiseCompare(r3)
            if (r0 == 0) goto L_0x0027
        L_0x0026:
            goto L_0x0029
        L_0x0027:
            r0 = 0
            goto L_0x002a
        L_0x0029:
            r0 = 1
        L_0x002a:
            if (r0 == 0) goto L_0x0055
            com.microsoft.bond.TypeDef r3 = r4.key
            if (r3 == 0) goto L_0x0055
            if (r0 == 0) goto L_0x0042
            if (r3 != 0) goto L_0x0036
            r0 = 1
            goto L_0x0037
        L_0x0036:
            r0 = 0
        L_0x0037:
            com.microsoft.bond.TypeDef r3 = r5.key
            if (r3 != 0) goto L_0x003d
            r3 = 1
            goto L_0x003e
        L_0x003d:
            r3 = 0
        L_0x003e:
            if (r0 != r3) goto L_0x0042
            r0 = 1
            goto L_0x0043
        L_0x0042:
            r0 = 0
        L_0x0043:
            if (r0 == 0) goto L_0x0054
            com.microsoft.bond.TypeDef r0 = r4.key
            if (r0 != 0) goto L_0x004a
            goto L_0x0052
        L_0x004a:
            com.microsoft.bond.TypeDef r5 = r5.key
            boolean r5 = r0.memberwiseCompare(r5)
            if (r5 == 0) goto L_0x0054
        L_0x0052:
            r0 = 1
            goto L_0x0055
        L_0x0054:
            r0 = 0
        L_0x0055:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.bond.TypeDef.memberwiseCompareDeep(com.microsoft.bond.TypeDef):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0049 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean memberwiseCompareQuick(com.microsoft.bond.TypeDef r5) {
        /*
            r4 = this;
            com.microsoft.bond.BondDataType r0 = r4.f1263id
            com.microsoft.bond.BondDataType r1 = r5.f1263id
            r2 = 1
            r3 = 0
            if (r0 != r1) goto L_0x000a
            r0 = 1
            goto L_0x000b
        L_0x000a:
            r0 = 0
        L_0x000b:
            if (r0 == 0) goto L_0x0015
            short r0 = r4.struct_def
            short r1 = r5.struct_def
            if (r0 != r1) goto L_0x0015
            r0 = 1
            goto L_0x0016
        L_0x0015:
            r0 = 0
        L_0x0016:
            if (r0 == 0) goto L_0x002a
            com.microsoft.bond.TypeDef r0 = r4.element
            if (r0 != 0) goto L_0x001e
            r0 = 1
            goto L_0x001f
        L_0x001e:
            r0 = 0
        L_0x001f:
            com.microsoft.bond.TypeDef r1 = r5.element
            if (r1 != 0) goto L_0x0025
            r1 = 1
            goto L_0x0026
        L_0x0025:
            r1 = 0
        L_0x0026:
            if (r0 != r1) goto L_0x002a
            r0 = 1
            goto L_0x002b
        L_0x002a:
            r0 = 0
        L_0x002b:
            if (r0 == 0) goto L_0x003f
            com.microsoft.bond.TypeDef r0 = r4.key
            if (r0 != 0) goto L_0x0033
            r0 = 1
            goto L_0x0034
        L_0x0033:
            r0 = 0
        L_0x0034:
            com.microsoft.bond.TypeDef r1 = r5.key
            if (r1 != 0) goto L_0x003a
            r1 = 1
            goto L_0x003b
        L_0x003a:
            r1 = 0
        L_0x003b:
            if (r0 != r1) goto L_0x003f
            r0 = 1
            goto L_0x0040
        L_0x003f:
            r0 = 0
        L_0x0040:
            if (r0 == 0) goto L_0x0049
            boolean r0 = r4.bonded_type
            boolean r5 = r5.bonded_type
            if (r0 != r5) goto L_0x0049
            goto L_0x004a
        L_0x0049:
            r2 = 0
        L_0x004a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.bond.TypeDef.memberwiseCompareQuick(com.microsoft.bond.TypeDef):boolean");
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
                    this.f1263id = BondDataType.fromValue(ReadHelper.readInt32(protocolReader, bondDataType));
                } else if (i == 1) {
                    this.struct_def = ReadHelper.readUInt16(protocolReader, bondDataType);
                } else if (i == 2) {
                    readFieldImpl_element(protocolReader, bondDataType);
                } else if (i == 3) {
                    readFieldImpl_key(protocolReader, bondDataType);
                } else if (i != 4) {
                    protocolReader.skip(bondDataType);
                } else {
                    this.bonded_type = ReadHelper.readBool(protocolReader, bondDataType);
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
            this.f1263id = BondDataType.fromValue(protocolReader.readInt32());
        }
        if (!hasCapability || !protocolReader.readFieldOmitted()) {
            this.struct_def = protocolReader.readUInt16();
        }
        if (!hasCapability || !protocolReader.readFieldOmitted()) {
            readFieldImpl_element(protocolReader, BondDataType.BT_LIST);
        }
        if (!hasCapability || !protocolReader.readFieldOmitted()) {
            readFieldImpl_key(protocolReader, BondDataType.BT_LIST);
        }
        if (!hasCapability || !protocolReader.readFieldOmitted()) {
            this.bonded_type = protocolReader.readBool();
        }
        protocolReader.readStructEnd();
    }

    public void reset() {
        reset("TypeDef", "com.microsoft.bond.TypeDef");
    }

    public final void setBonded_type(boolean z) {
        this.bonded_type = z;
    }

    public final void setElement(TypeDef typeDef) {
        this.element = typeDef;
    }

    public void setField(FieldDef fieldDef, Object obj) {
        short id = fieldDef.getId();
        if (id == 0) {
            this.f1263id = (BondDataType) obj;
        } else if (id == 1) {
            this.struct_def = ((Short) obj).shortValue();
        } else if (id == 2) {
            this.element = (TypeDef) obj;
        } else if (id == 3) {
            this.key = (TypeDef) obj;
        } else if (id == 4) {
            this.bonded_type = ((Boolean) obj).booleanValue();
        }
    }

    public final void setId(BondDataType bondDataType) {
        this.f1263id = bondDataType;
    }

    public final void setKey(TypeDef typeDef) {
        this.key = typeDef;
    }

    public final void setStruct_def(short s) {
        this.struct_def = s;
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
        if (!hasCapability || ((long) this.f1263id.getValue()) != Schema.access$000().getDefault_value().getInt_value()) {
            protocolWriter.writeFieldBegin(BondDataType.BT_INT32, 0, Schema.access$000());
            protocolWriter.writeInt32(this.f1263id.getValue());
            protocolWriter.writeFieldEnd();
        } else {
            protocolWriter.writeFieldOmitted(BondDataType.BT_INT32, 0, Schema.access$000());
        }
        if (!hasCapability || ((long) this.struct_def) != Schema.access$100().getDefault_value().getUint_value()) {
            protocolWriter.writeFieldBegin(BondDataType.BT_UINT16, 1, Schema.access$100());
            protocolWriter.writeUInt16(this.struct_def);
            protocolWriter.writeFieldEnd();
        } else {
            protocolWriter.writeFieldOmitted(BondDataType.BT_UINT16, 1, Schema.access$100());
        }
        int i = this.element != null ? 1 : 0;
        if (!hasCapability || i != 0) {
            protocolWriter.writeFieldBegin(BondDataType.BT_LIST, 2, Schema.access$200());
            protocolWriter.writeContainerBegin(i, BondDataType.BT_STRUCT);
            if (i != 0) {
                this.element.writeNested(protocolWriter, false);
            }
            protocolWriter.writeContainerEnd();
            protocolWriter.writeFieldEnd();
        } else {
            protocolWriter.writeFieldOmitted(BondDataType.BT_LIST, 2, Schema.access$200());
        }
        int i2 = this.key != null ? 1 : 0;
        if (!hasCapability || i2 != 0) {
            protocolWriter.writeFieldBegin(BondDataType.BT_LIST, 3, Schema.access$300());
            protocolWriter.writeContainerBegin(i2, BondDataType.BT_STRUCT);
            if (i2 != 0) {
                this.key.writeNested(protocolWriter, false);
            }
            protocolWriter.writeContainerEnd();
            protocolWriter.writeFieldEnd();
        } else {
            protocolWriter.writeFieldOmitted(BondDataType.BT_LIST, 3, Schema.access$300());
        }
        if (hasCapability) {
            boolean z3 = this.bonded_type;
            if (Schema.access$400().getDefault_value().getUint_value() != 0) {
                z2 = true;
            }
            if (z3 == z2) {
                protocolWriter.writeFieldOmitted(BondDataType.BT_BOOL, 4, Schema.access$400());
                protocolWriter.writeStructEnd(z);
            }
        }
        protocolWriter.writeFieldBegin(BondDataType.BT_BOOL, 4, Schema.access$400());
        protocolWriter.writeBool(this.bonded_type);
        protocolWriter.writeFieldEnd();
        protocolWriter.writeStructEnd(z);
    }

    public void reset(String str, String str2) {
        this.f1263id = BondDataType.BT_STRUCT;
        this.struct_def = 0;
        this.element = null;
        this.key = null;
        this.bonded_type = false;
    }

    public void unmarshal(InputStream inputStream, BondSerializable bondSerializable) throws IOException {
        Marshaler.unmarshal(inputStream, (SchemaDef) bondSerializable, this);
    }
}
