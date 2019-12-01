package com.yanovski.omahacomp.factories;

import com.yanovski.omahacomp.engine.OmahaCompRunner;
import com.yanovski.omahacomp.engine.impl.OmahaCompRunnerImpl;

public interface RunnerFactory {
    static OmahaCompRunner createRunner() {
        return new OmahaCompRunnerImpl();
    }
}
