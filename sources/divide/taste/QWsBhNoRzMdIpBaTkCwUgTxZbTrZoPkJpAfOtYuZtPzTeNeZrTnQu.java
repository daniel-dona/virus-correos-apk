package divide.taste;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.base.R;
import com.google.android.gms.dynamic.RemoteCreator;
import divide.taste.api.Scope;
import divide.taste.internal.QWsBhNoRzMdIpBaTkCwUgTxZbTrZoPkJpAfOtYuZtPzTeNeZrTnQuCreator;
import divide.taste.internal.QWsBhNoRzMdIpBaTkCwUgTxZbTrZoPkJpAfOtYuZtPzTeNeZrTnQuImpl;

public final class QWsBhNoRzMdIpBaTkCwUgTxZbTrZoPkJpAfOtYuZtPzTeNeZrTnQu extends FrameLayout implements View.OnClickListener {
    public static final int COLOR_AUTO = 2;
    public static final int COLOR_DARK = 0;
    public static final int COLOR_LIGHT = 1;
    public static final int SIZE_ICON_ONLY = 2;
    public static final int SIZE_STANDARD = 0;
    public static final int SIZE_WIDE = 1;
    private int mColor;
    private int mSize;
    private View zaas;
    private View.OnClickListener zaat;

    public QWsBhNoRzMdIpBaTkCwUgTxZbTrZoPkJpAfOtYuZtPzTeNeZrTnQu(Context context) {
        this(context, (AttributeSet) null);
    }

    public QWsBhNoRzMdIpBaTkCwUgTxZbTrZoPkJpAfOtYuZtPzTeNeZrTnQu(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: finally extract failed */
    public QWsBhNoRzMdIpBaTkCwUgTxZbTrZoPkJpAfOtYuZtPzTeNeZrTnQu(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zaat = null;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.QWsBhNoRzMdIpBaTkCwUgTxZbTrZoPkJpAfOtYuZtPzTeNeZrTnQu, 0, 0);
        try {
            this.mSize = obtainStyledAttributes.getInt(R.styleable.QWsBhNoRzMdIpBaTkCwUgTxZbTrZoPkJpAfOtYuZtPzTeNeZrTnQu_buttonSize, 0);
            this.mColor = obtainStyledAttributes.getInt(R.styleable.QWsBhNoRzMdIpBaTkCwUgTxZbTrZoPkJpAfOtYuZtPzTeNeZrTnQu_colorScheme, 2);
            obtainStyledAttributes.recycle();
            setStyle(this.mSize, this.mColor);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public final void setSize(int i) {
        setStyle(i, this.mColor);
    }

    public final void setColorScheme(int i) {
        setStyle(this.mSize, i);
    }

    @Deprecated
    public final void setScopes(Scope[] scopeArr) {
        setStyle(this.mSize, this.mColor);
    }

    public final void setStyle(int i, int i2) {
        this.mSize = i;
        this.mColor = i2;
        Context context = getContext();
        if (this.zaas != null) {
            removeView(this.zaas);
        }
        try {
            this.zaas = QWsBhNoRzMdIpBaTkCwUgTxZbTrZoPkJpAfOtYuZtPzTeNeZrTnQuCreator.createView(context, this.mSize, this.mColor);
        } catch (RemoteCreator.RemoteCreatorException unused) {
            Log.w("QWsBhNoRzMdIpBaTkCwUgTxZbTrZoPkJpAfOtYuZtPzTeNeZrTnQu", "Sign in button not found, using placeholder instead");
            int i3 = this.mSize;
            int i4 = this.mColor;
            QWsBhNoRzMdIpBaTkCwUgTxZbTrZoPkJpAfOtYuZtPzTeNeZrTnQuImpl qWsBhNoRzMdIpBaTkCwUgTxZbTrZoPkJpAfOtYuZtPzTeNeZrTnQuImpl = new QWsBhNoRzMdIpBaTkCwUgTxZbTrZoPkJpAfOtYuZtPzTeNeZrTnQuImpl(context);
            qWsBhNoRzMdIpBaTkCwUgTxZbTrZoPkJpAfOtYuZtPzTeNeZrTnQuImpl.configure(context.getResources(), i3, i4);
            this.zaas = qWsBhNoRzMdIpBaTkCwUgTxZbTrZoPkJpAfOtYuZtPzTeNeZrTnQuImpl;
        }
        addView(this.zaas);
        this.zaas.setEnabled(isEnabled());
        this.zaas.setOnClickListener(this);
    }

    @Deprecated
    public final void setStyle(int i, int i2, Scope[] scopeArr) {
        setStyle(i, i2);
    }

    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.zaat = onClickListener;
        if (this.zaas != null) {
            this.zaas.setOnClickListener(this);
        }
    }

    public final void setEnabled(boolean z) {
        super.setEnabled(z);
        this.zaas.setEnabled(z);
    }

    public final void onClick(View view) {
        if (this.zaat != null && view == this.zaas) {
            this.zaat.onClick(this);
        }
    }
}
