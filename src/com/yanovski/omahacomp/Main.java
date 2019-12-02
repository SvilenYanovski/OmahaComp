package com.yanovski.omahacomp;

import com.yanovski.omahacomp.engine.runner.OmahaCompRunner;
import com.yanovski.omahacomp.factories.RunnerFactory;

/**
 * Main entry point
 * Receives 2 parameters from the command line for input and output
 * Exits after execution.
 *
 * @author SV
 */
public class Main {

    public static void main(String[] args) {
        OmahaCompRunner runner = RunnerFactory.createRunner();
        runner.start();
    }
}
