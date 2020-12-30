package com.microsoft.bond;

import com.microsoft.bond.ProtocolReader;
import com.microsoft.bond.StructDef;
import com.microsoft.bond.TypeDef;
import com.microsoft.bond.internal.Marshaler;
import com.microsoft.bond.internal.ReadHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: PG */
public class SchemaDef implements BondSerializable, BondMirror {
    public TypeDef root;
    public ArrayList<StructDef> structs;

    public SchemaDef() {
        reset();
    }

    public static SchemaDef getRuntimeSchema() {
        return Schema.schemaDef;
    }

    private void readFieldImpl_structs(ProtocolReader protocolReader, BondDataType bondDataType) throws IOException {
        ReadHelper.validateType(bondDataType, BondDataType.BT_LIST);
        ProtocolReader.ListTag readContainerBegin = protocolReader.readContainerBegin();
        ReadHelper.validateType(readContainerBegin.type, BondDataType.BT_STRUCT);
        this.structs.ensureCapacity(readContainerBegin.size);
        for (int i = 0; i < readContainerBegin.size; i++) {
            StructDef structDef = new StructDef();
            structDef.readNested(protocolReader);
            this.structs.add(structDef);
        }
        protocolReader.readContainerEnd();
    }

    public BondSerializable clone() {
        return null;
    }

    public BondMirror createInstance(StructDef structDef) {
        if (StructDef.Schema.metadata == structDef.getMetadata()) {
            return new StructDef();
        }
        if (TypeDef.Schema.metadata == structDef.getMetadata()) {
            return new TypeDef();
        }
        return null;
    }

    public Object getField(FieldDef fieldDef) {
        short id = fieldDef.getId();
        if (id == 0) {
            return this.structs;
        }
        if (id != 1) {
            return null;
        }
        return this.root;
    }

    public final TypeDef getRoot() {
        return this.root;
    }

    public SchemaDef getSchema() {
        return getRuntimeSchema();
    }

    public final ArrayList<StructDef> getStructs() {
        return this.structs;
    }

    public void marshal(ProtocolWriter protocolWriter) throws IOException {
        Marshaler.marshal(this, protocolWriter);
    }

    public boolean memberwiseCompare(Object obj) {
        if (obj == null) {
            return false;
        }
        SchemaDef schemaDef = (SchemaDef) obj;
        if (!memberwiseCompareQuick(schemaDef) || !memberwiseCompareDeep(schemaDef)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0048 A[LOOP:0: B:5:0x000e->B:25:0x0048, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x004c A[EDGE_INSN: B:33:0x004c->B:27:0x004c ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean memberwiseCompareDeep(com.microsoft.bond.SchemaDef r8) {
        /*
            r7 = this;
            java.util.ArrayList<com.microsoft.bond.StructDef> r0 = r7.structs
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x004b
            int r0 = r0.size()
            if (r0 == 0) goto L_0x004b
            r0 = 0
            r3 = 1
        L_0x000e:
            java.util.ArrayList<com.microsoft.bond.StructDef> r4 = r7.structs
            int r4 = r4.size()
            if (r0 >= r4) goto L_0x004c
            java.util.ArrayList<com.microsoft.bond.StructDef> r4 = r7.structs
            java.lang.Object r4 = r4.get(r0)
            com.microsoft.bond.StructDef r4 = (com.microsoft.bond.StructDef) r4
            java.util.ArrayList<com.microsoft.bond.StructDef> r5 = r8.structs
            java.lang.Object r5 = r5.get(r0)
            com.microsoft.bond.StructDef r5 = (com.microsoft.bond.StructDef) r5
            if (r3 == 0) goto L_0x0036
            if (r4 != 0) goto L_0x002c
            r3 = 1
            goto L_0x002d
        L_0x002c:
            r3 = 0
        L_0x002d:
            if (r5 != 0) goto L_0x0031
            r6 = 1
            goto L_0x0032
        L_0x0031:
            r6 = 0
        L_0x0032:
            if (r3 != r6) goto L_0x0036
            r3 = 1
            goto L_0x0037
        L_0x0036:
            r3 = 0
        L_0x0037:
            if (r3 == 0) goto L_0x0044
            if (r4 != 0) goto L_0x003c
            goto L_0x0042
        L_0x003c:
            boolean r3 = r4.memberwiseCompare(r5)
            if (r3 == 0) goto L_0x0044
        L_0x0042:
            r3 = 1
            goto L_0x0045
        L_0x0044:
            r3 = 0
        L_0x0045:
            if (r3 != 0) goto L_0x0048
            goto L_0x004c
        L_0x0048:
            int r0 = r0 + 1
            goto L_0x000e
        L_0x004b:
            r3 = 1
        L_0x004c:
            if (r3 == 0) goto L_0x005c
            com.microsoft.bond.TypeDef r0 = r7.root
            if (r0 != 0) goto L_0x0053
            goto L_0x005b
        L_0x0053:
            com.microsoft.bond.TypeDef r8 = r8.root
            boolean r8 = r0.memberwiseCompare(r8)
            if (r8 == 0) goto L_0x005c
        L_0x005b:
            r1 = 1
        L_0x005c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.bond.SchemaDef.memberwiseCompareDeep(com.microsoft.bond.SchemaDef):boolean");
    }

    public boolean memberwiseCompareQuick(SchemaDef schemaDef) {
        ArrayList<StructDef> arrayList;
        if (!((this.structs == null) == (schemaDef.structs == null)) || ((arrayList = this.structs) != null && arrayList.size() != schemaDef.structs.size())) {
            return false;
        }
        return true;
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
                    readFieldImpl_structs(protocolReader, bondDataType);
                } else if (i != 1) {
                    protocolReader.skip(bondDataType);
                } else {
                    ReadHelper.validateType(bondDataType, BondDataType.BT_STRUCT);
                    this.root.readNested(protocolReader);
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
            readFieldImpl_structs(protocolReader, BondDataType.BT_LIST);
        }
        if (!hasCapability || !protocolReader.readFieldOmitted()) {
            this.root.read(protocolReader);
        }
        protocolReader.readStructEnd();
    }

    public void reset() {
        reset("SchemaDef", "com.microsoft.bond.SchemaDef");
    }

    public void setField(FieldDef fieldDef, Object obj) {
        short id = fieldDef.getId();
        if (id == 0) {
            this.structs = (ArrayList) obj;
        } else if (id == 1) {
            this.root = (TypeDef) obj;
        }
    }

    public final void setRoot(TypeDef typeDef) {
        this.root = typeDef;
    }

    public final void setStructs(ArrayList<StructDef> arrayList) {
        this.structs = arrayList;
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
        int size = this.structs.size();
        if (!hasCapability || size != 0) {
            protocolWriter.writeFieldBegin(BondDataType.BT_LIST, 0, Schema.access$000());
            protocolWriter.writeContainerBegin(size, BondDataType.BT_STRUCT);
            Iterator<StructDef> it = this.structs.iterator();
            while (it.hasNext()) {
                it.next().writeNested(protocolWriter, false);
            }
            protocolWriter.writeContainerEnd();
            protocolWriter.writeFieldEnd();
        } else {
            protocolWriter.writeFieldOmitted(BondDataType.BT_LIST, 0, Schema.access$000());
        }
        protocolWriter.writeFieldBegin(BondDataType.BT_STRUCT, 1, Schema.access$100());
        this.root.writeNested(protocolWriter, false);
        protocolWriter.writeFieldEnd();
        protocolWriter.writeStructEnd(z);
    }

    public void reset(String str, String str2) {
        ArrayList<StructDef> arrayList = this.structs;
        if (arrayList == null) {
            this.structs = new ArrayList<>();
        } else {
            arrayList.clear();
        }
        this.root = new TypeDef();
    }

    public void unmarshal(InputStream inputStream, BondSerializable bondSerializable) throws IOException {
        Marshaler.unmarshal(inputStream, (SchemaDef) bondSerializable, this);
    }
}
