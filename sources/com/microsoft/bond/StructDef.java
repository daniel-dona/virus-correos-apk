package com.microsoft.bond;

import com.microsoft.bond.FieldDef;
import com.microsoft.bond.Metadata;
import com.microsoft.bond.ProtocolReader;
import com.microsoft.bond.TypeDef;
import com.microsoft.bond.internal.Marshaler;
import com.microsoft.bond.internal.ReadHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: PG */
public class StructDef implements BondSerializable, BondMirror {
    public TypeDef base_def;
    public ArrayList<FieldDef> fields;
    public Metadata metadata;

    public StructDef() {
        reset();
    }

    public static SchemaDef getRuntimeSchema() {
        return Schema.schemaDef;
    }

    private void readFieldImpl_base_def(ProtocolReader protocolReader, BondDataType bondDataType) throws IOException {
        ReadHelper.validateType(bondDataType, BondDataType.BT_LIST);
        ProtocolReader.ListTag readContainerBegin = protocolReader.readContainerBegin();
        ReadHelper.validateType(readContainerBegin.type, BondDataType.BT_STRUCT);
        if (readContainerBegin.size == 1) {
            if (this.base_def == null) {
                this.base_def = new TypeDef();
            }
            this.base_def.readNested(protocolReader);
        }
        protocolReader.readContainerEnd();
    }

    private void readFieldImpl_fields(ProtocolReader protocolReader, BondDataType bondDataType) throws IOException {
        ReadHelper.validateType(bondDataType, BondDataType.BT_LIST);
        ProtocolReader.ListTag readContainerBegin = protocolReader.readContainerBegin();
        ReadHelper.validateType(readContainerBegin.type, BondDataType.BT_STRUCT);
        this.fields.ensureCapacity(readContainerBegin.size);
        for (int i = 0; i < readContainerBegin.size; i++) {
            FieldDef fieldDef = new FieldDef();
            fieldDef.readNested(protocolReader);
            this.fields.add(fieldDef);
        }
        protocolReader.readContainerEnd();
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
        if (FieldDef.Schema.metadata == structDef.getMetadata()) {
            return new FieldDef();
        }
        return null;
    }

    public final TypeDef getBase_def() {
        return this.base_def;
    }

    public Object getField(FieldDef fieldDef) {
        short id = fieldDef.getId();
        if (id == 0) {
            return this.metadata;
        }
        if (id == 1) {
            return this.base_def;
        }
        if (id != 2) {
            return null;
        }
        return this.fields;
    }

    public final ArrayList<FieldDef> getFields() {
        return this.fields;
    }

    public final Metadata getMetadata() {
        return this.metadata;
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
        StructDef structDef = (StructDef) obj;
        if (!memberwiseCompareQuick(structDef) || !memberwiseCompareDeep(structDef)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0085 A[LOOP:0: B:33:0x004b->B:53:0x0085, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0089 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean memberwiseCompareDeep(com.microsoft.bond.StructDef r8) {
        /*
            r7 = this;
            com.microsoft.bond.Metadata r0 = r7.metadata
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0007
            goto L_0x000f
        L_0x0007:
            com.microsoft.bond.Metadata r3 = r8.metadata
            boolean r0 = r0.memberwiseCompare(r3)
            if (r0 == 0) goto L_0x0011
        L_0x000f:
            r0 = 1
            goto L_0x0012
        L_0x0011:
            r0 = 0
        L_0x0012:
            if (r0 == 0) goto L_0x003d
            com.microsoft.bond.TypeDef r3 = r7.base_def
            if (r3 == 0) goto L_0x003d
            if (r0 == 0) goto L_0x002a
            if (r3 != 0) goto L_0x001e
            r0 = 1
            goto L_0x001f
        L_0x001e:
            r0 = 0
        L_0x001f:
            com.microsoft.bond.TypeDef r3 = r8.base_def
            if (r3 != 0) goto L_0x0025
            r3 = 1
            goto L_0x0026
        L_0x0025:
            r3 = 0
        L_0x0026:
            if (r0 != r3) goto L_0x002a
            r0 = 1
            goto L_0x002b
        L_0x002a:
            r0 = 0
        L_0x002b:
            if (r0 == 0) goto L_0x003c
            com.microsoft.bond.TypeDef r0 = r7.base_def
            if (r0 != 0) goto L_0x0032
            goto L_0x003a
        L_0x0032:
            com.microsoft.bond.TypeDef r3 = r8.base_def
            boolean r0 = r0.memberwiseCompare(r3)
            if (r0 == 0) goto L_0x003c
        L_0x003a:
            r0 = 1
            goto L_0x003d
        L_0x003c:
            r0 = 0
        L_0x003d:
            if (r0 == 0) goto L_0x0088
            java.util.ArrayList<com.microsoft.bond.FieldDef> r3 = r7.fields
            if (r3 == 0) goto L_0x0088
            int r3 = r3.size()
            if (r3 == 0) goto L_0x0088
            r3 = r0
            r0 = 0
        L_0x004b:
            java.util.ArrayList<com.microsoft.bond.FieldDef> r4 = r7.fields
            int r4 = r4.size()
            if (r0 >= r4) goto L_0x0089
            java.util.ArrayList<com.microsoft.bond.FieldDef> r4 = r7.fields
            java.lang.Object r4 = r4.get(r0)
            com.microsoft.bond.FieldDef r4 = (com.microsoft.bond.FieldDef) r4
            java.util.ArrayList<com.microsoft.bond.FieldDef> r5 = r8.fields
            java.lang.Object r5 = r5.get(r0)
            com.microsoft.bond.FieldDef r5 = (com.microsoft.bond.FieldDef) r5
            if (r3 == 0) goto L_0x0073
            if (r4 != 0) goto L_0x0069
            r3 = 1
            goto L_0x006a
        L_0x0069:
            r3 = 0
        L_0x006a:
            if (r5 != 0) goto L_0x006e
            r6 = 1
            goto L_0x006f
        L_0x006e:
            r6 = 0
        L_0x006f:
            if (r3 != r6) goto L_0x0073
            r3 = 1
            goto L_0x0074
        L_0x0073:
            r3 = 0
        L_0x0074:
            if (r3 == 0) goto L_0x0081
            if (r4 != 0) goto L_0x0079
            goto L_0x007f
        L_0x0079:
            boolean r3 = r4.memberwiseCompare(r5)
            if (r3 == 0) goto L_0x0081
        L_0x007f:
            r3 = 1
            goto L_0x0082
        L_0x0081:
            r3 = 0
        L_0x0082:
            if (r3 != 0) goto L_0x0085
            goto L_0x0089
        L_0x0085:
            int r0 = r0 + 1
            goto L_0x004b
        L_0x0088:
            r3 = r0
        L_0x0089:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.bond.StructDef.memberwiseCompareDeep(com.microsoft.bond.StructDef):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x003e A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean memberwiseCompareQuick(com.microsoft.bond.StructDef r5) {
        /*
            r4 = this;
            com.microsoft.bond.TypeDef r0 = r4.base_def
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0008
            r0 = 1
            goto L_0x0009
        L_0x0008:
            r0 = 0
        L_0x0009:
            com.microsoft.bond.TypeDef r3 = r5.base_def
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
            if (r0 == 0) goto L_0x0029
            java.util.ArrayList<com.microsoft.bond.FieldDef> r0 = r4.fields
            if (r0 != 0) goto L_0x001d
            r0 = 1
            goto L_0x001e
        L_0x001d:
            r0 = 0
        L_0x001e:
            java.util.ArrayList<com.microsoft.bond.FieldDef> r3 = r5.fields
            if (r3 != 0) goto L_0x0024
            r3 = 1
            goto L_0x0025
        L_0x0024:
            r3 = 0
        L_0x0025:
            if (r0 != r3) goto L_0x0029
            r0 = 1
            goto L_0x002a
        L_0x0029:
            r0 = 0
        L_0x002a:
            if (r0 == 0) goto L_0x003e
            java.util.ArrayList<com.microsoft.bond.FieldDef> r0 = r4.fields
            if (r0 != 0) goto L_0x0031
            goto L_0x003f
        L_0x0031:
            int r0 = r0.size()
            java.util.ArrayList<com.microsoft.bond.FieldDef> r5 = r5.fields
            int r5 = r5.size()
            if (r0 != r5) goto L_0x003e
            goto L_0x003f
        L_0x003e:
            r1 = 0
        L_0x003f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.bond.StructDef.memberwiseCompareQuick(com.microsoft.bond.StructDef):boolean");
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
                    readFieldImpl_base_def(protocolReader, bondDataType);
                } else if (i != 2) {
                    protocolReader.skip(bondDataType);
                } else {
                    readFieldImpl_fields(protocolReader, bondDataType);
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
            readFieldImpl_base_def(protocolReader, BondDataType.BT_LIST);
        }
        if (!hasCapability || !protocolReader.readFieldOmitted()) {
            readFieldImpl_fields(protocolReader, BondDataType.BT_LIST);
        }
        protocolReader.readStructEnd();
    }

    public void reset() {
        reset("StructDef", "com.microsoft.bond.StructDef");
    }

    public final void setBase_def(TypeDef typeDef) {
        this.base_def = typeDef;
    }

    public void setField(FieldDef fieldDef, Object obj) {
        short id = fieldDef.getId();
        if (id == 0) {
            this.metadata = (Metadata) obj;
        } else if (id == 1) {
            this.base_def = (TypeDef) obj;
        } else if (id == 2) {
            this.fields = (ArrayList) obj;
        }
    }

    public final void setFields(ArrayList<FieldDef> arrayList) {
        this.fields = arrayList;
    }

    public final void setMetadata(Metadata metadata2) {
        this.metadata = metadata2;
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
        int i = this.base_def != null ? 1 : 0;
        if (!hasCapability || i != 0) {
            protocolWriter.writeFieldBegin(BondDataType.BT_LIST, 1, Schema.access$100());
            protocolWriter.writeContainerBegin(i, BondDataType.BT_STRUCT);
            if (i != 0) {
                this.base_def.writeNested(protocolWriter, false);
            }
            protocolWriter.writeContainerEnd();
            protocolWriter.writeFieldEnd();
        } else {
            protocolWriter.writeFieldOmitted(BondDataType.BT_LIST, 1, Schema.access$100());
        }
        int size = this.fields.size();
        if (!hasCapability || size != 0) {
            protocolWriter.writeFieldBegin(BondDataType.BT_LIST, 2, Schema.access$200());
            protocolWriter.writeContainerBegin(size, BondDataType.BT_STRUCT);
            Iterator<FieldDef> it = this.fields.iterator();
            while (it.hasNext()) {
                it.next().writeNested(protocolWriter, false);
            }
            protocolWriter.writeContainerEnd();
            protocolWriter.writeFieldEnd();
        } else {
            protocolWriter.writeFieldOmitted(BondDataType.BT_LIST, 2, Schema.access$200());
        }
        protocolWriter.writeStructEnd(z);
    }

    public void reset(String str, String str2) {
        this.metadata = new Metadata();
        this.base_def = null;
        ArrayList<FieldDef> arrayList = this.fields;
        if (arrayList == null) {
            this.fields = new ArrayList<>();
        } else {
            arrayList.clear();
        }
    }

    public void unmarshal(InputStream inputStream, BondSerializable bondSerializable) throws IOException {
        Marshaler.unmarshal(inputStream, (SchemaDef) bondSerializable, this);
    }
}
