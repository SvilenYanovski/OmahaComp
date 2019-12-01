package com.yanovski.omahacomp.factories;

import com.yanovski.omahacomp.engine.runner.OmahaCompRunner;
import com.yanovski.omahacomp.engine.runner.impl.OmahaCompRunnerImpl;

public interface RunnerFactory {
    static OmahaCompRunner createRunner() {
        return new OmahaCompRunnerImpl();
    }
}
