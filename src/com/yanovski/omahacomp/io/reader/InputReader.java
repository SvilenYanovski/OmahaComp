package com.yanovski.omahacomp.io.reader;

import com.yanovski.omahacomp.models.OmahaDeal;

import java.util.Map;

public interface InputReader {
    Map<String, OmahaDeal> loadInput(String filePath);
}
