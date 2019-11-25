package com.github.fingahoverit.topaz_adventofcode.year2018.common;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputLoader {

    private static final Logger LOGGER = Logger.getLogger(InputLoader.class.getCanonicalName());
    private static final String DEFAULT_INPUT_FOLDER_NAME = "inputs";

    private InputLoader() {
    }

    public static String load(String dayIndex, String inputFile) {

        try {
            Path path = Paths.get(
                    Objects.requireNonNull(
                            InputLoader.class.getClassLoader().getResource(dayIndex + File.separator
                                    + DEFAULT_INPUT_FOLDER_NAME + File.separator + inputFile)
                    ).toURI());

            return Files.readString(path);

        } catch (URISyntaxException | IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

        return "";
    }
}
