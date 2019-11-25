package com.github.fingahoverit.topaz_adventofcode.year2018.day01;

import com.github.fingahoverit.topaz_adventofcode.year2018.common.ExpectedLoader;
import com.github.fingahoverit.topaz_adventofcode.year2018.common.InputLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.stream.Collectors;

class ChronalCalibrationTest {

    private static Logger LOGGER = Logger.getLogger(ChronalCalibrationTest.class.getCanonicalName());

    private static final String INPUT_TEST_01_FILE = "01.txt";
    private static final String INPUT_TEST_02_FILE = "02.txt";
    private static final String INPUT_TEST_03_FILE = "03.txt";
    private static final String INPUT_TEST_04_FILE = "04.txt";

    private static final String INPUT_TEST_05_FILE = "05.txt";
    private static final String INPUT_TEST_06_FILE = "06.txt";
    private static final String INPUT_TEST_07_FILE = "07.txt";
    private static final String INPUT_TEST_08_FILE = "08.txt";
    private static final String INPUT_TEST_09_FILE = "09.txt";


    @ParameterizedTest
    @ValueSource(strings = {INPUT_TEST_01_FILE, INPUT_TEST_02_FILE, INPUT_TEST_03_FILE, INPUT_TEST_04_FILE})
    void testSolveFirstPuzzle(String inputTestFileName) {

        String input = InputLoader.load(ChronalCalibration.DAY_INDEX, inputTestFileName);
        String expected = ExpectedLoader.load(ChronalCalibration.DAY_INDEX, inputTestFileName);

        String result = new ChronalCalibration().solveFirstPuzzle(input);

        LOGGER.info("Testing " + input.lines().collect(Collectors.toList()).toString() +
                " and expecting : " + expected);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {INPUT_TEST_05_FILE, INPUT_TEST_06_FILE, INPUT_TEST_07_FILE,
            INPUT_TEST_08_FILE, INPUT_TEST_09_FILE})
    void testSolveSecondPuzzle(String inputTestFileName) {

        String input = InputLoader.load(ChronalCalibration.DAY_INDEX, inputTestFileName);
        String expected = ExpectedLoader.load(ChronalCalibration.DAY_INDEX, inputTestFileName);

        String result = new ChronalCalibration().solveSecondPuzzle(input);

        LOGGER.info("Testing " + input.lines().collect(Collectors.toList()).toString() +
                " and expecting : " + expected);
        Assertions.assertEquals(expected, result);
    }
}
