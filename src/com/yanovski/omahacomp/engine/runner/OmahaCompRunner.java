package com.yanovski.omahacomp.engine.runner;

/**
 * Workflow runner for the Omaha hand evaluation.
 * tTHe process passes through 3 steps:
 * 1. Parse input into a data structure for processing
 * 2. Process (evaluate) the Omaha deal and determine winning hands
 * 3. Write output into output file
 *
 * @author SV
 */
public interface OmahaCompRunner {

    /**
     * Entry point for the workflow
     */
    void start();
}
