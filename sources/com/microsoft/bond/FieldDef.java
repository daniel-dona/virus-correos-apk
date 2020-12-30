package com.microsoft.bond;

import com.microsoft.bond.Metadata;
import com.microsoft.bond.ProtocolReader;
import com.microsoft.bond.TypeDef;
import com.microsoft.bond.internal.Marshaler;
import com.microsoft.bond.internal.ReadHelper;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: PG */
public class FieldDef implements BondSerializable, BondMirror {

    /* renamed from: id */
    public short f1262id;
    public Metadata metadata;
    public TypeDef type;

    public FieldDef() {
        reset();
    }

    public static SchemaDef getRuntimeSchema() {
        return Schema.schemaDef;
    }

    public BondSerializable clone() {
        return null;
    }

    public BondMirror createInstance(StructDef structDef) {
        if (Metadata.Schema.metadata == structDef.getMetadata()) {
            return new Metadata();
        }
        if (TypeDef.Schema.metadata == structDef.getMetadata()) {
            return new TypeDef();
        }
        return null;
    }

    public Object getField(FieldDef fieldDef) {
        short id = fieldDef.getId();
        if (id == 0) {
            return this.metadata;
        }
        if (id == 1) {
            return Short.valueOf(this.f1262id);
        }
        if (id != 2) {
            return null;
        }
        return this.type;
    }

    public final short getId() {
        return this.f1262id;
    }

    public final Metadata getMetadata() {
        return this.metadata;
    }

    public SchemaDef getSchema() {
        return getRuntimeSchema();
    }

    public final TypeDef getType() {
        return this.type;
    }

    public void marshal(ProtocolWriter protocolWriter) throws IOException {
        Marshaler.marshal(this, protocolWriter);
    }

    public boolean memberwiseCompare(Object obj) {
        if (obj == null) {
            return false;
        }
        FieldDef fieldDef = (FieldDef) obj;
        if (!memberwiseCompareQuick(fieldDef) || !memberwiseCompareDeep(fieldDef)) {
            return false;
        }
        return true;
    }

    public boolean memberwiseCompareDeep(FieldDef fieldDef) {
        TypeDef typeDef;
        Metadata metadata2 = this.metadata;
        if (!(metadata2 == null || metadata2.memberwiseCompare(fieldDef.metadata)) || ((typeDef = this.type) != null && !typeDef.memberwiseCompare(fieldDef.type))) {
            return false;
        }
        return true;
    }

    public boolean memberwiseCompareQuick(FieldDef fieldDef) {
        return this.f1262id == fieldDef.f1262id;
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
                    ReadHelper.validateType(bondDataType, BondDataType.BT_STRUCT);
                    this.metadata.readNested(protocolReader);
                } else if (i == 1) {
                    this.f1262id = ReadHelper.readUInt16(protocolReader, bondDataType);
                } else if (i != 2) {
                    protocolReader.skip(bondDataType);
                } else {
                    ReadHelper.validateType(bondDataType, BondDataType.BT_STRUCT);
                    this.type.readNested(protocolReader);
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
            this.metadata.read(protocolReader);
        }
        if (!hasCapability || !protocolReader.readFieldOmitted()) {
            this.f1262id = protocolReader.readUInt16();
        }
        if (!hasCapability || !protocolReader.readFieldOmitted()) {
            this.type.read(protocolReader);
        }
        protocolReader.readStructEnd();
    }

    public void reset() {
        reset("FieldDef", "com.microsoft.bond.FieldDef");
    }

    public void setField(FieldDef fieldDef, Object obj) {
        short id = fieldDef.getId();
        if (id == 0) {
            this.metadata = (Metadata) obj;
        } else if (id == 1) {
            this.f1262id = ((Short) obj).shortValue();
        } else if (id == 2) {
            this.type = (TypeDef) obj;
        }
    }

    public final void setId(short s) {
        this.f1262id = s;
    }

    public final void setMetadata(Metadata metadata2) {
        this.metadata = metadata2;
    }

    public final void setType(TypeDef typeDef) {
        this.type = typeDef;
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
        protocolWriter.writeFieldBegin(BondDataType.BT_STRUCT, 0, Schema.access$000());
        this.metadata.writeNested(protocolWriter, false);
        protocolWriter.writeFieldEnd();
        if (!hasCapability || ((long) this.f1262id) != Schema.access$100().getDefault_value().getUint_value()) {
            protocolWriter.writeFieldBegin(BondDataType.BT_UINT16, 1, Schema.access$100());
            protocolWriter.writeUInt16(this.f1262id);
            protocolWriter.writeFieldEnd();
        } else {
            protocolWriter.writeFieldOmitted(BondDataType.BT_UINT16, 1, Schema.access$100());
        }
        protocolWriter.writeFieldBegin(BondDataType.BT_STRUCT, 2, Schema.access$200());
        this.type.writeNested(protocolWriter, false);
        protocolWriter.writeFieldEnd();
        protocolWriter.writeStructEnd(z);
    }

    public void reset(String str, String str2) {
        this.metadata = new Metadata();
        this.f1262id = 0;
        this.type = new TypeDef();
    }

    public void unmarshal(InputStream inputStream, BondSerializable bondSerializable) throws IOException {
        Marshaler.unmarshal(inputStream, (SchemaDef) bondSerializable, this);
    }
}
