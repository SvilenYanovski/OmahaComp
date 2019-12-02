package com.yanovski.omahacomp.io.writer.impl;

import com.yanovski.omahacomp.io.writer.OutputWriter;
import com.yanovski.omahacomp.models.OmahaDeal;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class OutputWriterImpl implements OutputWriter {
    @Override
    public void writeOutput(String pathStr, Map<String, OmahaDeal> deals) {
        Path path = Paths.get(pathStr);

        try (BufferedWriter writer = Files.newBufferedWriter(path))
        {
            deals.forEach((key, value) -> {
                try {
                    writer.write(key);
                    writer.newLine();
                    writer.write("=> " + value.getEvaluation().getHighWinners().get(0).getName());
                    writer.write(" wins High (" + value.getEvaluation().getHighWinners().get(0).getHiEvaluation().getOmahaHandRank() + "); ");

                    if (value.getEvaluation().getLowWinners().size() > 0 && value.getEvaluation().getLowWinners().get(0) != null) {
                        writer.write(value.getEvaluation().getLowWinners().get(0).getName() + " wins Low (");
                        writer.write(value.getEvaluation().getLowWinners().get(0).getLoEvaluation().getHighCard().getRank().toString() + ")");
                    }
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
