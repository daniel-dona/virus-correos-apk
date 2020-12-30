package org.chromium.chrome.browser.favorites;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.microsoft.ruby.telemetry.TelemetryConstants;
import java.util.HashMap;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.edge_feedback.FeedbackSessionManager;
import org.chromium.chrome.browser.edge_widget.BaseDialogFragment;
import org.chromium.chrome.browser.widget.selection.SelectionDelegate;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.url_formatter.UrlFormatter;

/* compiled from: PG */
public class BookmarkEditDialog extends BaseDialogFragment implements View.OnClickListener, TextWatcher {

    /* renamed from: k */
    public BookmarkModel f1882k;

    /* renamed from: n */
    public BookmarkId f1883n;

    /* renamed from: p */
    public TextInputEditText f1884p;

    /* renamed from: q */
    public TextInputEditText f1885q;

    /* renamed from: q3 */
    public Button f1886q3;

    /* renamed from: r3 */
    public Button f1887r3;

    /* renamed from: s3 */
    public TextInputLayout f1888s3;

    /* renamed from: t3 */
    public TextInputLayout f1889t3;

    /* renamed from: u3 */
    public BookmarkBridge.b f1890u3 = new C0403a();

    /* renamed from: x */
    public TextView f1891x;

    /* renamed from: y */
    public TextView f1892y;

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkEditDialog$a */
    /* compiled from: PG */
    public class C0403a extends BookmarkBridge.b {
        public C0403a() {
        }

        /* renamed from: a */
        public void mo8619a() {
            BookmarkEditDialog bookmarkEditDialog = BookmarkEditDialog.this;
            if (bookmarkEditDialog.f1882k.c(bookmarkEditDialog.f1883n)) {
                BookmarkEditDialog.this.mo8610a(true);
            } else {
                BookmarkEditDialog.this.dismiss();
            }
        }
    }

    static {
        Class<BookmarkEditDialog> cls = BookmarkEditDialog.class;
    }

    /* renamed from: a */
    public void mo8519a(View view) {
        this.f1882k = new BookmarkModel();
        this.f1882k.a(this.f1890u3);
        this.f1883n = BookmarkId.a(this.f1797b.getString("BookmarkEditDialog.BookmarkId"));
        BookmarkBridge.BookmarkItem d = this.f1882k.d(this.f1883n);
        if (!this.f1882k.c(this.f1883n) || d == null) {
            dismiss();
        }
        this.f1884p = mo8518a(ox0.title_text);
        this.f1885q = mo8518a(ox0.url_text);
        this.f1886q3 = (Button) mo8518a(ox0.cancel_button);
        this.f1887r3 = (Button) mo8518a(ox0.save_button);
        this.f1888s3 = mo8518a(ox0.title_wrapper);
        this.f1889t3 = mo8518a(ox0.url_wrapper);
        TextInputLayout textInputLayout = this.f1888s3;
        EditText c = textInputLayout.c();
        if (c != null) {
            c.setAccessibilityDelegate(new lW1(this, textInputLayout));
        }
        TextInputLayout textInputLayout2 = this.f1889t3;
        EditText c2 = textInputLayout2.c();
        if (c2 != null) {
            c2.setAccessibilityDelegate(new lW1(this, textInputLayout2));
        }
        this.f1884p.addTextChangedListener(this);
        this.f1885q.addTextChangedListener(this);
        this.f1892y = (TextView) mo8518a(ox0.folder);
        this.f1892y.setVisibility(0);
        this.f1891x = (TextView) mo8518a(ox0.folder_text);
        this.f1891x.setOnClickListener(new kW1(this));
        EE2.a(this.f1891x);
        this.f1886q3.setOnClickListener(this);
        this.f1887r3.setOnClickListener(this);
        mo8610a(false);
    }

    public void afterTextChanged(Editable editable) {
        boolean z;
        if (mo8611a((TextView) this.f1884p)) {
            this.f1888s3.setError(getString(wx0.bookmark_missing_title));
            z = false;
        } else {
            this.f1888s3.setErrorEnabled(false);
            z = true;
        }
        if (mo8611a((TextView) this.f1885q)) {
            this.f1889t3.setError(getString(wx0.bookmark_missing_url));
            z = false;
        } else {
            this.f1889t3.setErrorEnabled(false);
        }
        this.f1887r3.setEnabled(z);
        if (z) {
            this.f1887r3.setTextColor(du0.a(getResources(), jx0.hub_primary_color));
            this.f1887r3.setContentDescription(String.format(getString(wx0.save_favorite_button), new Object[]{this.f1884p.getText().toString(), this.f1885q.getText().toString()}));
            return;
        }
        this.f1887r3.setTextColor(du0.a(getResources(), jx0.favorites_dialog_save_text_disabled_color));
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* renamed from: o */
    public BaseDialogFragment.a mo8520o() {
        Context context = getContext();
        Configuration configuration = getResources().getConfiguration();
        BaseDialogFragment.a aVar = new BaseDialogFragment.a();
        aVar.b = Math.min(DV1.a(context, (float) configuration.screenWidthDp), DV1.a(context, (float) configuration.screenHeightDp));
        aVar.c = -2;
        return aVar;
    }

    public void onClick(View view) {
        String a;
        ss0.a("Hub", "BookmarkEditDialog", (String) null, TelemetryConstants.Actions.Click, view == this.f1886q3 ? "Cancel" : "Save", new String[0]);
        if (view == this.f1886q3) {
            dismiss();
        } else if (view == this.f1887r3) {
            if (this.f1882k.c(this.f1883n)) {
                String d = this.f1882k.d(this.f1883n).d();
                String e = this.f1882k.d(this.f1883n).e();
                String trim = this.f1884p.getText().toString().trim();
                String trim2 = this.f1885q.getText().toString().trim();
                if (!mo8611a((TextView) this.f1884p) && !trim.equals(d)) {
                    this.f1882k.a(this.f1883n, trim);
                }
                if (!mo8611a((TextView) this.f1885q) && this.f1882k.d(this.f1883n).j() && (a = UrlFormatter.m3350a(trim2)) != null && !a.equals(e)) {
                    this.f1882k.c(this.f1883n, a);
                }
            }
            dismiss();
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        BookmarkEditDialog.super.onDismiss(dialogInterface);
        FeedbackSessionManager.m2270f();
        ss0.b("Hub", "BookmarkEditDialog", (String) null, new String[0]);
    }

    public void onMAMDestroy() {
        this.f1882k.b(this.f1890u3);
        this.f1882k.a();
        this.f1882k = null;
        this.f1884p.removeTextChangedListener(this);
        this.f1885q.removeTextChangedListener(this);
        BookmarkEditDialog.super.onMAMDestroy();
    }

    public void onMAMStart() {
        super.onMAMStart();
        ss0.a("Hub", "BookmarkEditDialog", (String) null, new String[0]);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* renamed from: p */
    public int mo8526p() {
        return rx0.favorite_edit_dialog;
    }

    /* renamed from: r */
    public final /* synthetic */ void mo8618r() {
        ss0.b("EditFavoritesToChooseFolder", (HashMap) null, true, 0, (String) null);
        ss0.a("Hub", "BookmarkEditDialog", (String) null, TelemetryConstants.Actions.Click, "EditFavoritesToChooseFolder", new String[0]);
        BookmarkFolderSelectActivity.m2344a(getContext(), (SelectionDelegate<BookmarkId>) null, false, this.f1883n);
    }

    /* renamed from: a */
    public final void mo8610a(boolean z) {
        BookmarkBridge.BookmarkItem d = this.f1882k.d(this.f1883n);
        if (!z) {
            this.f1884p.setText(d.d());
            this.f1885q.setText(d.e());
        }
        this.f1884p.setEnabled(d.f());
        this.f1885q.setEnabled(d.j());
        this.f1891x.setText(this.f1882k.k(d.b()));
        TextView textView = this.f1891x;
        textView.setContentDescription(this.f1891x.getText() + ", " + this.f1892y.getText());
        this.f1891x.setEnabled(d.i());
        this.f1891x.setVisibility(0);
    }

    /* renamed from: a */
    public final boolean mo8611a(TextView textView) {
        return TextUtils.isEmpty(textView.getText().toString().trim());
    }
}
