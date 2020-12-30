package org.chromium.chrome.browser.favorites;

import android.util.Pair;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.bookmarks.BookmarkId;

/* compiled from: PG */
public class BookmarkMatch {
    public BookmarkMatch(BookmarkId bookmarkId, List<Pair<Integer, Integer>> list, List<Pair<Integer, Integer>> list2) {
    }

    @CalledByNative
    public static BookmarkMatch createBookmarkMatch(BookmarkId bookmarkId, List<Pair<Integer, Integer>> list, List<Pair<Integer, Integer>> list2) {
        return new BookmarkMatch(bookmarkId, list, list2);
    }
}
