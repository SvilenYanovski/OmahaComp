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
                    writer.write("=> Hello World!");
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
