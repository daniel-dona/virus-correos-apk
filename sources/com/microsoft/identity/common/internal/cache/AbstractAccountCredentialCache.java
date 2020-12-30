package com.microsoft.identity.common.internal.cache;

import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.internal.dto.AccessTokenRecord;
import com.microsoft.identity.common.internal.dto.AccountRecord;
import com.microsoft.identity.common.internal.dto.Credential;
import com.microsoft.identity.common.internal.dto.CredentialType;
import com.microsoft.identity.common.internal.dto.IdTokenRecord;
import com.microsoft.identity.common.internal.dto.RefreshTokenRecord;
import com.microsoft.identity.common.internal.logging.Logger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* compiled from: PG */
public abstract class AbstractAccountCredentialCache implements IAccountCredentialCache {
    public static final String NEW_LINE = "\n";
    public static final String TAG = "AbstractAccountCredentialCache";

    public static boolean targetsIntersect(String str, String str2) {
        String[] split = str.split("\\s+");
        String[] split2 = str2.split("\\s+");
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (String lowerCase : split) {
            hashSet.add(lowerCase.toLowerCase());
        }
        for (String lowerCase2 : split2) {
            hashSet2.add(lowerCase2.toLowerCase());
        }
        return hashSet2.containsAll(hashSet);
    }

    public List<AccountRecord> getAccountsFilteredByInternal(String str, String str2, String str3, List<AccountRecord> list) {
        boolean z = !StringExtensions.isNullOrBlank(str);
        boolean z2 = !StringExtensions.isNullOrBlank(str2);
        boolean z3 = !StringExtensions.isNullOrBlank(str3);
        Logger.verbose(TAG, "Account lookup filtered by home_account_id? [" + z + "]" + NEW_LINE + "Account lookup filtered by realm? [" + z3 + "]");
        ArrayList arrayList = new ArrayList();
        for (AccountRecord next : list) {
            boolean equalsIgnoreCase = z ? str.equalsIgnoreCase(next.getHomeAccountId()) : true;
            if (z2) {
                equalsIgnoreCase = equalsIgnoreCase && str2.equalsIgnoreCase(next.getEnvironment());
            }
            if (z3) {
                equalsIgnoreCase = equalsIgnoreCase && str3.equalsIgnoreCase(next.getRealm());
            }
            if (equalsIgnoreCase) {
                arrayList.add(next);
            }
        }
        String str4 = TAG;
        StringBuilder a = Eo.a("Found [");
        a.append(arrayList.size());
        a.append("] matching accounts");
        Logger.verbose(str4, a.toString());
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0110, code lost:
        if (targetsIntersect(r0, r2.getTarget()) != false) goto L_0x0112;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0127, code lost:
        if (targetsIntersect(r0, r2.getTarget()) != false) goto L_0x0112;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.microsoft.identity.common.internal.dto.Credential> getCredentialsFilteredByInternal(java.lang.String r17, java.lang.String r18, com.microsoft.identity.common.internal.dto.CredentialType r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.util.List<com.microsoft.identity.common.internal.dto.Credential> r23) {
        /*
            r16 = this;
            r0 = r22
            boolean r1 = com.microsoft.identity.common.adal.internal.util.StringExtensions.isNullOrBlank(r18)
            r2 = 1
            r1 = r1 ^ r2
            boolean r3 = com.microsoft.identity.common.adal.internal.util.StringExtensions.isNullOrBlank(r17)
            r3 = r3 ^ r2
            boolean r4 = com.microsoft.identity.common.adal.internal.util.StringExtensions.isNullOrBlank(r21)
            r4 = r4 ^ r2
            boolean r5 = com.microsoft.identity.common.adal.internal.util.StringExtensions.isNullOrBlank(r22)
            r5 = r5 ^ r2
            boolean r6 = com.microsoft.identity.common.adal.internal.util.StringExtensions.isNullOrBlank(r20)
            r6 = r6 ^ r2
            if (r19 == 0) goto L_0x0020
            r8 = 1
            goto L_0x0021
        L_0x0020:
            r8 = 0
        L_0x0021:
            java.lang.String r9 = TAG
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "Credential lookup filtered by home_account_id? ["
            r10.append(r11)
            r10.append(r3)
            java.lang.String r11 = "]"
            r10.append(r11)
            java.lang.String r12 = "\n"
            r10.append(r12)
            java.lang.String r13 = "Credential lookup filtered by realm? ["
            r10.append(r13)
            r10.append(r4)
            r10.append(r11)
            r10.append(r12)
            java.lang.String r13 = "Credential lookup filtered by target? ["
            r10.append(r13)
            r10.append(r5)
            r10.append(r11)
            r10.append(r12)
            java.lang.String r13 = "Credential lookup filtered by clientId? ["
            r10.append(r13)
            r10.append(r6)
            r10.append(r11)
            r10.append(r12)
            java.lang.String r12 = "Credential lookup filtered by credential type? ["
            r10.append(r12)
            r10.append(r8)
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            com.microsoft.identity.common.internal.logging.Logger.verbose(r9, r10)
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.Iterator r10 = r23.iterator()
        L_0x007f:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x0139
            java.lang.Object r11 = r10.next()
            com.microsoft.identity.common.internal.dto.Credential r11 = (com.microsoft.identity.common.internal.dto.Credential) r11
            if (r3 == 0) goto L_0x0098
            java.lang.String r12 = r11.getHomeAccountId()
            r13 = r17
            boolean r12 = r13.equalsIgnoreCase(r12)
            goto L_0x009b
        L_0x0098:
            r13 = r17
            r12 = 1
        L_0x009b:
            if (r1 == 0) goto L_0x00b1
            if (r12 == 0) goto L_0x00ad
            java.lang.String r12 = r11.getEnvironment()
            r14 = r18
            boolean r12 = r14.equalsIgnoreCase(r12)
            if (r12 == 0) goto L_0x00af
            r12 = 1
            goto L_0x00b3
        L_0x00ad:
            r14 = r18
        L_0x00af:
            r12 = 0
            goto L_0x00b3
        L_0x00b1:
            r14 = r18
        L_0x00b3:
            if (r8 == 0) goto L_0x00c8
            if (r12 == 0) goto L_0x00c7
            java.lang.String r12 = r19.name()
            java.lang.String r15 = r11.getCredentialType()
            boolean r12 = r12.equalsIgnoreCase(r15)
            if (r12 == 0) goto L_0x00c7
            r12 = 1
            goto L_0x00c8
        L_0x00c7:
            r12 = 0
        L_0x00c8:
            if (r6 == 0) goto L_0x00de
            if (r12 == 0) goto L_0x00da
            java.lang.String r12 = r11.getClientId()
            r15 = r20
            boolean r12 = r15.equalsIgnoreCase(r12)
            if (r12 == 0) goto L_0x00dc
            r12 = 1
            goto L_0x00e0
        L_0x00da:
            r15 = r20
        L_0x00dc:
            r12 = 0
            goto L_0x00e0
        L_0x00de:
            r15 = r20
        L_0x00e0:
            if (r4 == 0) goto L_0x00fd
            boolean r2 = r11 instanceof com.microsoft.identity.common.internal.dto.AccessTokenRecord
            if (r2 == 0) goto L_0x00fd
            r2 = r11
            com.microsoft.identity.common.internal.dto.AccessTokenRecord r2 = (com.microsoft.identity.common.internal.dto.AccessTokenRecord) r2
            if (r12 == 0) goto L_0x00f9
            java.lang.String r2 = r2.getRealm()
            r12 = r21
            boolean r2 = r12.equalsIgnoreCase(r2)
            if (r2 == 0) goto L_0x00fb
            r2 = 1
            goto L_0x00fc
        L_0x00f9:
            r12 = r21
        L_0x00fb:
            r2 = 0
        L_0x00fc:
            r12 = r2
        L_0x00fd:
            if (r5 == 0) goto L_0x0131
            boolean r2 = r11 instanceof com.microsoft.identity.common.internal.dto.AccessTokenRecord
            if (r2 == 0) goto L_0x0116
            r2 = r11
            com.microsoft.identity.common.internal.dto.AccessTokenRecord r2 = (com.microsoft.identity.common.internal.dto.AccessTokenRecord) r2
            if (r12 == 0) goto L_0x0114
            java.lang.String r2 = r2.getTarget()
            boolean r2 = targetsIntersect(r0, r2)
            if (r2 == 0) goto L_0x0114
        L_0x0112:
            r12 = 1
            goto L_0x0131
        L_0x0114:
            r12 = 0
            goto L_0x0131
        L_0x0116:
            boolean r2 = r11 instanceof com.microsoft.identity.common.internal.dto.RefreshTokenRecord
            if (r2 == 0) goto L_0x012a
            r2 = r11
            com.microsoft.identity.common.internal.dto.RefreshTokenRecord r2 = (com.microsoft.identity.common.internal.dto.RefreshTokenRecord) r2
            if (r12 == 0) goto L_0x0114
            java.lang.String r2 = r2.getTarget()
            boolean r2 = targetsIntersect(r0, r2)
            if (r2 == 0) goto L_0x0114
            goto L_0x0112
        L_0x012a:
            java.lang.String r2 = TAG
            java.lang.String r7 = "Query specified target-match, but no target to match."
            com.microsoft.identity.common.internal.logging.Logger.warn(r2, r7)
        L_0x0131:
            if (r12 == 0) goto L_0x0136
            r9.add(r11)
        L_0x0136:
            r2 = 1
            goto L_0x007f
        L_0x0139:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.identity.common.internal.cache.AbstractAccountCredentialCache.getCredentialsFilteredByInternal(java.lang.String, java.lang.String, com.microsoft.identity.common.internal.dto.CredentialType, java.lang.String, java.lang.String, java.lang.String, java.util.List):java.util.List");
    }

    public Class<? extends Credential> getTargetClassForCredentialType(String str, CredentialType credentialType) {
        int ordinal = credentialType.ordinal();
        if (ordinal == 0) {
            return RefreshTokenRecord.class;
        }
        if (ordinal == 1) {
            return AccessTokenRecord.class;
        }
        if (ordinal == 2 || ordinal == 3) {
            return IdTokenRecord.class;
        }
        Logger.warn(TAG, "Could not match CredentialType to class. Did you forget to update this method with a new type?");
        if (str != null) {
            String str2 = TAG;
            Logger.warnPII(str2, "Sought key was: [" + str + "]");
        }
        return null;
    }
}
