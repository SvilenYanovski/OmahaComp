package com.yanovski.omahacomp.engine.runner.impl;

import com.yanovski.omahacomp.config.AppConstants;
import com.yanovski.omahacomp.engine.evaluator.OmahaEvaluator;
import com.yanovski.omahacomp.engine.runner.OmahaCompRunner;
import com.yanovski.omahacomp.factories.InputFactory;
import com.yanovski.omahacomp.factories.OutputFactory;
import com.yanovski.omahacomp.io.reader.InputReader;
import com.yanovski.omahacomp.io.writer.OutputWriter;
import com.yanovski.omahacomp.models.OmahaDeal;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OmahaCompRunnerImpl implements OmahaCompRunner {

    @Override
    public void start() {
        try(Scanner sc = new Scanner(System.in)) {
            String input = sc.nextLine();
            String[] inputTokens = input.split(" ");

            Map<String, OmahaDeal> deals = new HashMap<>();
            //List<OmahaDealEvaluation> outputEvaluation = new ArrayList<>();

            if(AppConstants.OMAHA_COMP_READ_INPUT.equalsIgnoreCase(inputTokens[0])) {
                InputReader reader = InputFactory.createInputReader();
                deals = reader.loadInput(inputTokens[1].trim());

                for(OmahaDeal omahaDeal: deals.values()) {
                    OmahaEvaluator.evaluateHand(omahaDeal);

                }

                OutputWriter writer = OutputFactory.createOutputWriter();
                writer.writeOutput(inputTokens[2].trim(), deals);
            }
        } catch (Exception ex) {
            System.out.println("Encountered error. Closing the program.");
            ex.printStackTrace();
        }
    }
}
