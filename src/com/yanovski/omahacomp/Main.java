package com.yanovski.omahacomp;

import com.yanovski.omahacomp.engine.OmahaCompRunner;
import com.yanovski.omahacomp.factories.RunnerFactory;

public class Main {

    public static void main(String[] args) {
        OmahaCompRunner runner = RunnerFactory.createRunner();
        runner.start();
    }
}
