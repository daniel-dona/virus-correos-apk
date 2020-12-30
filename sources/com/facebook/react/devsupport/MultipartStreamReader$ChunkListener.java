package com.facebook.react.devsupport;

import java.io.IOException;
import java.util.Map;

/* compiled from: PG */
public interface MultipartStreamReader$ChunkListener {
    void onChunkComplete(Map<String, String> map, GC0 gc0, boolean z) throws IOException;

    void onChunkProgress(Map<String, String> map, long j, long j2) throws IOException;
}
