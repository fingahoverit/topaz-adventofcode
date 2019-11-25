package com.github.fingahoverit.topaz_adventofcode.year2018.common;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExpectedLoader {

    private static final Logger LOGGER = Logger.getLogger(ExpectedLoader.class.getCanonicalName());
    private static final String DEFAULT_INPUT_FOLDER_NAME = "expected";

    private ExpectedLoader() {
    }

    public static String load(String dayIndex, String expectedFile) {

        try {
            Path path = Paths.get(
                    Objects.requireNonNull(
                            InputLoader.class.getClassLoader().getResource(dayIndex + File.separator
                                    + DEFAULT_INPUT_FOLDER_NAME + File.separator + expectedFile)
                    ).toURI());

            return Files.readString(path);

        } catch (URISyntaxException | IOException | NullPointerException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

        return "";
    }
}
