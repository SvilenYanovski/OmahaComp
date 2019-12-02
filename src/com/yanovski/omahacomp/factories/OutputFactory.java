package com.yanovski.omahacomp.factories;

import com.yanovski.omahacomp.io.writer.OutputWriter;
import com.yanovski.omahacomp.io.writer.impl.OutputWriterImpl;

public interface OutputFactory {
    static OutputWriter createOutputWriter() {
        return new OutputWriterImpl();
    }
}
