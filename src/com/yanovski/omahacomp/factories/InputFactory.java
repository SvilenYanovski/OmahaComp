package com.yanovski.omahacomp.factories;

import com.yanovski.omahacomp.io.reader.InputReader;
import com.yanovski.omahacomp.io.reader.impl.InputReaderImpl;

public interface InputFactory {
    static InputReader createInputReader() {
        return new InputReaderImpl();
    }
}
