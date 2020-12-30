package org.chromium.chrome.browser.favorites;

import android.content.Context;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.ruby.telemetry.TelemetryConstants;
import java.util.List;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.p012ui.KeyboardVisibilityDelegate;

/* compiled from: PG */
public class BookmarkSearchView extends LinearLayout implements AdapterView.OnItemClickListener, TextView.OnEditorActionListener, EW1 {

    /* renamed from: a */
    public BookmarkModel f1947a;

    /* renamed from: b */
    public BookmarkDelegate f1948b;

    /* renamed from: c */
    public EditText f1949c;

    /* renamed from: d */
    public Button f1950d;

    /* renamed from: e */
    public ListView f1951e;

    /* renamed from: k */
    public ListView f1952k;

    /* renamed from: n */
    public HistoryResultSwitcher f1953n;

    /* renamed from: p */
    public UIState f1954p;

    /* renamed from: q */
    public BookmarkBridge.b f1955q = new C0410a();

    /* compiled from: PG */
    public static class HistoryResultSwitcher extends ViewSwitcher {

        /* renamed from: a */
        public ViewSwitcher f1956a;

        public HistoryResultSwitcher(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        /* renamed from: a */
        public void mo8693a() {
            if (getCurrentView().getId() == ox0.bookmark_history_list) {
                showNext();
            }
            if (this.f1956a.getCurrentView().getId() == ox0.bookmark_result_list) {
                this.f1956a.showNext();
                this.f1956a.setImportantForAccessibility(1);
            }
        }

        /* renamed from: b */
        public void mo8694b() {
            if (getCurrentView().getId() != ox0.bookmark_history_list) {
                showNext();
            }
        }

        /* renamed from: c */
        public void mo8695c() {
            if (getCurrentView().getId() == ox0.bookmark_history_list) {
                showNext();
            }
            if (this.f1956a.getCurrentView().getId() == ox0.bookmark_search_empty_view) {
                this.f1956a.showNext();
            }
        }

        public void onFinishInflate() {
            super.onFinishInflate();
            this.f1956a = (ViewSwitcher) findViewById(ox0.result_empty_switcher);
        }
    }

    /* compiled from: PG */
    public enum UIState {
        HISTORY,
        RESULT,
        EMPTY
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkSearchView$b */
    /* compiled from: PG */
    public class C0411b implements TextWatcher {
        public C0411b() {
        }

        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(editable.toString().trim())) {
                BookmarkSearchView.this.mo8684c();
                return;
            }
            BookmarkSearchView.m2382a(BookmarkSearchView.this);
            BookmarkSearchView bookmarkSearchView = BookmarkSearchView.this;
            UIState uIState = bookmarkSearchView.f1954p;
            if (uIState == UIState.EMPTY) {
                bookmarkSearchView.announceForAccessibility(bookmarkSearchView.getResources().getText(wx0.hub_no_results));
            } else if (uIState == UIState.RESULT) {
                BookmarkSearchView.this.announceForAccessibility(String.format(bookmarkSearchView.getResources().getString(wx0.accessibility_hub_have_result), new Object[]{Integer.valueOf(BookmarkSearchView.this.f1951e.getAdapter().getCount())}));
            }
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            CharSequence text = BookmarkSearchView.this.f1950d.getText();
            BookmarkSearchView.this.f1950d.setText(TextUtils.isEmpty(charSequence) ? wx0.cancel : wx0.clear);
            if (!BookmarkSearchView.this.f1950d.getText().equals(text) && TextUtils.isEmpty(charSequence)) {
                EE2.b(BookmarkSearchView.this.f1950d);
            }
        }
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkSearchView$c */
    /* compiled from: PG */
    public class C0412c implements View.OnClickListener {
        public C0412c() {
        }

        public void onClick(View view) {
            String str;
            if (TextUtils.isEmpty(BookmarkSearchView.this.f1949c.getText().toString())) {
                ss0.a("HubClick", "favorite_search_cancel");
                BookmarkSearchView.this.mo8683b();
                str = "SearchCancel";
            } else {
                ss0.a("HubClick", "favorite_search_clear");
                BookmarkSearchView.this.f1949c.setText(BuildConfig.FLAVOR);
                if (EE2.a()) {
                    BookmarkSearchView.this.f1949c.clearFocus();
                    KeyboardVisibilityDelegate.f2588b.mo9950c(BookmarkSearchView.this.f1950d);
                }
                str = "SearchClear";
            }
            ss0.a("Hub", "Favorites", (String) null, TelemetryConstants.Actions.Click, str, new String[0]);
        }
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkSearchView$d */
    /* compiled from: PG */
    public class C0413d extends BaseAdapter {

        /* renamed from: a */
        public BookmarkDelegate f1960a;

        /* renamed from: b */
        public List<BookmarkId> f1961b;

        public C0413d(BookmarkSearchView bookmarkSearchView, List<BookmarkId> list, BookmarkDelegate bookmarkDelegate) {
            this.f1960a = bookmarkDelegate;
            this.f1961b = list;
        }

        public int getCount() {
            return this.f1961b.size();
        }

        public Object getItem(int i) {
            return this.f1961b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            BookmarkId bookmarkId = this.f1961b.get(i);
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(rx0.favorite_search_row, viewGroup, false);
            }
            BookmarkSearchRow bookmarkSearchRow = (BookmarkSearchRow) view;
            bookmarkSearchRow.mo8621a(this.f1960a);
            bookmarkSearchRow.mo8622b(bookmarkId);
            return view;
        }
    }

    static {
        Class<BookmarkSearchView> cls = BookmarkSearchView.class;
    }

    public BookmarkSearchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: a */
    public static /* synthetic */ void m2382a(BookmarkSearchView bookmarkSearchView) {
        String trim = bookmarkSearchView.f1949c.getText().toString().trim();
        if (!TextUtils.isEmpty(trim)) {
            List a = bookmarkSearchView.f1947a.a(trim, 500);
            if (a.isEmpty()) {
                bookmarkSearchView.mo8681a(UIState.EMPTY);
                return;
            }
            bookmarkSearchView.mo8681a(UIState.RESULT);
            bookmarkSearchView.f1951e.setAdapter(new C0413d(bookmarkSearchView, a, bookmarkSearchView.f1948b));
        }
    }

    /* renamed from: a */
    public void mo8679a() {
    }

    /* renamed from: a */
    public void mo8682a(BookmarkId bookmarkId) {
    }

    /* renamed from: c */
    public final void mo8684c() {
        mo8681a(UIState.HISTORY);
        this.f1951e.setAdapter((ListAdapter) null);
        if (!TextUtils.isEmpty(this.f1949c.getText())) {
            this.f1949c.setText(BuildConfig.FLAVOR);
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        KeyEvent.DispatcherState keyDispatcherState;
        if (this.f1948b == null) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (keyEvent.getKeyCode() == 4 && (keyDispatcherState = getKeyDispatcherState()) != null) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                keyDispatcherState.startTracking(keyEvent, this);
                return true;
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && keyDispatcherState.isTracking(keyEvent)) {
                if (this.f1954p == UIState.HISTORY) {
                    ((BookmarkManager) this.f1948b).mo8649a();
                } else {
                    mo8684c();
                }
                return true;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
    }

    public void onDestroy() {
        this.f1947a.b(this.f1955q);
        ((BookmarkManager) this.f1948b).f1927e.mo7869b(this);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 3) {
            return false;
        }
        KeyboardVisibilityDelegate.f2588b.mo9950c(textView);
        return false;
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.f1949c = (EditText) findViewById(ox0.bookmark_search_text);
        this.f1951e = (ListView) findViewById(ox0.bookmark_result_list);
        this.f1951e.setDividerHeight(DV1.a(getContext(), 8.0f));
        this.f1951e.setPadding(DV1.a(getContext(), 16.0f), 0, DV1.a(getContext(), 16.0f), 0);
        this.f1952k = (ListView) findViewById(ox0.bookmark_history_list);
        this.f1953n = (HistoryResultSwitcher) findViewById(ox0.history_result_switcher);
        this.f1952k.setOnItemClickListener(this);
        this.f1949c.setOnEditorActionListener(this);
        this.f1949c.addTextChangedListener(new C0411b());
        this.f1950d = (Button) findViewById(ox0.bookmark_search_cancel_or_clear);
        this.f1950d.setText(TextUtils.isEmpty(this.f1949c.getText().toString()) ? wx0.cancel : wx0.clear);
        this.f1950d.setOnClickListener(new C0412c());
        findViewById(ox0.shadow).a(ON0.a(getResources(), jx0.toolbar_shadow_color), 0);
        this.f1954p = UIState.HISTORY;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.AdapterView<?>, android.widget.AdapterView] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onItemClick(android.widget.AdapterView<?> r1, android.view.View r2, int r3, long r4) {
        /*
            r0 = this;
            android.widget.EditText r2 = r0.f1949c
            android.widget.Adapter r1 = r1.getAdapter()
            java.lang.Object r1 = r1.getItem(r3)
            java.lang.String r1 = (java.lang.String) r1
            r2.setText(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.favorites.BookmarkSearchView.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
    }

    public void onSelectionStateChange(List<BookmarkId> list) {
    }

    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        BookmarkModel bookmarkModel = this.f1947a;
        if (bookmarkModel != null) {
            if (i == 0) {
                bookmarkModel.a(this.f1955q);
                this.f1949c.requestFocus();
                KeyboardVisibilityDelegate.f2588b.mo9951d(this.f1949c);
                return;
            }
            KeyboardVisibilityDelegate.f2588b.mo9950c(this.f1949c);
            this.f1947a.b(this.f1955q);
            mo8684c();
            clearFocus();
        }
    }

    /* renamed from: b */
    public final void mo8683b() {
        if (this.f1954p == UIState.HISTORY) {
            ((BookmarkManager) this.f1948b).mo8649a();
        } else {
            mo8684c();
        }
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkSearchView$a */
    /* compiled from: PG */
    public class C0410a extends BookmarkBridge.b {
        public C0410a() {
        }

        /* renamed from: a */
        public void mo8697a() {
            UIState uIState = BookmarkSearchView.this.f1954p;
            if (uIState == UIState.RESULT || uIState == UIState.EMPTY) {
                BookmarkSearchView.m2382a(BookmarkSearchView.this);
            }
        }

        /* renamed from: a */
        public void mo8698a(BookmarkBridge.BookmarkItem bookmarkItem, int i, BookmarkBridge.BookmarkItem bookmarkItem2, boolean z) {
            if (z) {
                BookmarkSearchView bookmarkSearchView = BookmarkSearchView.this;
                if (bookmarkSearchView.f1954p == UIState.RESULT) {
                    BookmarkSearchView.m2382a(bookmarkSearchView);
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo8681a(UIState uIState) {
        if (this.f1954p != uIState) {
            this.f1954p = uIState;
            if (uIState == UIState.HISTORY) {
                this.f1953n.mo8694b();
            } else if (uIState == UIState.RESULT) {
                this.f1953n.mo8695c();
            } else if (uIState == UIState.EMPTY) {
                this.f1953n.mo8693a();
            }
        }
    }

    /* renamed from: a */
    public void mo8680a(BookmarkDelegate bookmarkDelegate) {
        this.f1948b = bookmarkDelegate;
        ((BookmarkManager) this.f1948b).f1927e.mo7868a(this);
        this.f1947a = ((BookmarkManager) this.f1948b).f1925c;
    }
}
