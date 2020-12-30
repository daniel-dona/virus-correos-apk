package com.microsoft.intune.mam.policy;

import ae0;
import android.content.Context;
import com.microsoft.intune.mam.client.identity.MAMIdentity;
import com.microsoft.intune.mam.log.MAMLogPIIFactory;
import com.microsoft.intune.mam.policy.MAMEnrollmentManager;

/* compiled from: PG */
public class MAMWEAccountManager {
    public final ae0 mAccountRegistry;
    public final boolean mIsPrimaryProcess;
    public final je0 mRetryScheduler;

    public MAMWEAccountManager(ae0 ae0, je0 je0, boolean z) {
        this.mAccountRegistry = ae0;
        this.mRetryScheduler = je0;
        this.mIsPrimaryProcess = z;
    }

    public static MAMWEAccountManager create(Context context, MAMLogPIIFactory mAMLogPIIFactory, gd0 gd0, je0 je0) {
        return new MAMWEAccountManager(new ae0(context, mAMLogPIIFactory, gd0), je0, lb0.c(context));
    }

    public TokenNeededReason getAccountNeedsToken(MAMIdentity mAMIdentity) {
        ae0.a a = this.mAccountRegistry.a(mAMIdentity);
        if (a == null) {
            return TokenNeededReason.NOT_NEEDED;
        }
        return a.d;
    }

    public MAMEnrollmentManager.Result getAccountStatus(MAMIdentity mAMIdentity) {
        ae0.a a = this.mAccountRegistry.a(mAMIdentity);
        if (a == null) {
            return null;
        }
        return a.c;
    }

    public void primaryUserRemoved(MAMIdentity mAMIdentity) {
        this.mRetryScheduler.a(this.mAccountRegistry.a(), mAMIdentity);
    }

    public boolean registerAccount(MAMIdentity mAMIdentity) {
        return this.mAccountRegistry.c(mAMIdentity);
    }

    public boolean removeAccount(MAMIdentity mAMIdentity) {
        boolean d = this.mAccountRegistry.d(mAMIdentity);
        if (d) {
            this.mRetryScheduler.a(mAMIdentity);
        }
        return d;
    }

    public void removeScheduledRetries(MAMIdentity mAMIdentity) {
        this.mRetryScheduler.a(mAMIdentity.canonicalUPN());
    }

    public void retryEnrollmentsAtStartup(MAMIdentity mAMIdentity) {
        if (this.mIsPrimaryProcess) {
            this.mRetryScheduler.b(this.mAccountRegistry.a(), mAMIdentity);
        }
    }

    public void setAccountNeedsToken(MAMIdentity mAMIdentity, TokenNeededReason tokenNeededReason) {
        TokenNeededReason tokenNeededReason2;
        ae0.a a = this.mAccountRegistry.a(mAMIdentity);
        if (a != null && (tokenNeededReason2 = a.d) != tokenNeededReason) {
            if (tokenNeededReason2 != TokenNeededReason.COMPLIANCE || tokenNeededReason == TokenNeededReason.NOT_NEEDED) {
                this.mAccountRegistry.a(mAMIdentity, tokenNeededReason);
            }
        }
    }

    public void updateAccount(MAMIdentity mAMIdentity, MAMEnrollmentManager.Result result, MAMWEError mAMWEError) {
        ae0.a a = this.mAccountRegistry.a(mAMIdentity);
        if (a != null) {
            TokenNeededReason tokenNeededReason = TokenNeededReason.NOT_NEEDED;
            if (result == MAMEnrollmentManager.Result.AUTHORIZATION_NEEDED) {
                MAMEnrollmentManager.Result result2 = a.c;
                if (result2 != MAMEnrollmentManager.Result.PENDING) {
                    mAMWEError = a.f;
                    result = result2;
                }
                TokenNeededReason tokenNeededReason2 = a.d;
                tokenNeededReason = TokenNeededReason.COMPLIANCE;
                if (tokenNeededReason2 != tokenNeededReason) {
                    tokenNeededReason = TokenNeededReason.ENROLLMENT;
                }
            }
            ae0.a a2 = this.mAccountRegistry.a(mAMIdentity, result, mAMWEError, tokenNeededReason);
            if (a2 != null) {
                this.mRetryScheduler.b(a2);
            }
        }
    }
}
