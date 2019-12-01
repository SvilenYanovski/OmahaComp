package com.yanovski.omahacomp.io.writer;

import com.yanovski.omahacomp.models.OmahaDeal;

import java.util.Map;

public interface OutputWriter {
    void writeOutput(String path, Map<String, OmahaDeal> deals);
}
