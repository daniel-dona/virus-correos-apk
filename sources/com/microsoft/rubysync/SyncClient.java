package com.microsoft.rubysync;

/* compiled from: PG */
public interface SyncClient extends UserKeyFetcher {
    String addBookmark(SyncBookmark syncBookmark) throws Exception;

    String addPassword(SyncPassword syncPassword) throws Exception;

    String addReadingListItem(SyncReadingListItem syncReadingListItem) throws Exception;

    void deleteBookmark(String str) throws Exception;

    void deletePassword(SyncPassword syncPassword) throws Exception;

    void deleteReadingListItem(String str) throws Exception;

    SyncBookmark[] getAllBookmarks() throws Exception;

    SyncPassword[] getAllPasswords() throws Exception;

    SyncReadingListItem[] getAllReadingListItems() throws Exception;

    String getAppDir();

    SyncTypedUrl[] getTypedUrls() throws Exception;

    void onLog(LogLevel logLevel, LogPiece[] logPieceArr);

    void onUnrecoverableError();

    void updateBookmark(String str, SyncBookmark syncBookmark) throws Exception;

    void updatePassword(String str, SyncPassword syncPassword) throws Exception;

    void updateReadingListItem(String str, SyncReadingListItem syncReadingListItem) throws Exception;

    void updateTypedUrls(SyncTypedUrl[] syncTypedUrlArr) throws Exception;
}
