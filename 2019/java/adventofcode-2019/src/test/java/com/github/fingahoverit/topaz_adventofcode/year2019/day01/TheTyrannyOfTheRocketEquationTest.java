package com.github.fingahoverit.topaz_adventofcode.year2019.day01;

import com.github.fingahoverit.topaz_adventofcode.year2019.common.ExpectedLoader;
import com.github.fingahoverit.topaz_adventofcode.year2019.common.InputLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TheTyrannyOfTheRocketEquationTest {

    private static final Logger LOGGER = Logger.getLogger(TheTyrannyOfTheRocketEquationTest.class.getCanonicalName());

    private static final String INPUT_TEST_01_FILE = "01.txt";
    private static final String INPUT_TEST_02_FILE = "02.txt";
    private static final String INPUT_TEST_03_FILE = "03.txt";
    private static final String INPUT_TEST_04_FILE = "04.txt";

    private static final String INPUT_TEST_05_FILE = "05.txt";
    private static final String INPUT_TEST_06_FILE = "06.txt";
    private static final String INPUT_TEST_07_FILE = "07.txt";

    @ParameterizedTest
    @ValueSource(strings = {INPUT_TEST_01_FILE, INPUT_TEST_02_FILE, INPUT_TEST_03_FILE, INPUT_TEST_04_FILE})
    void testSolveFirstPuzzle(String inputTestFileName) {

        String input = InputLoader.load(TheTyrannyOfTheRocketEquation.DAY_INDEX, inputTestFileName);
        String expected = ExpectedLoader.load(TheTyrannyOfTheRocketEquation.DAY_INDEX, inputTestFileName);

        String result = new TheTyrannyOfTheRocketEquation().solveFirstPuzzle(input);

        LOGGER.info(() -> "Testing " + input.lines().collect(Collectors.toList()).toString() +
                " and expecting : " + expected);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {INPUT_TEST_05_FILE, INPUT_TEST_06_FILE, INPUT_TEST_07_FILE})
    void testSolveSecondPuzzle(String inputTestFileName) {

        String input = InputLoader.load(TheTyrannyOfTheRocketEquation.DAY_INDEX, inputTestFileName);
        String expected = ExpectedLoader.load(TheTyrannyOfTheRocketEquation.DAY_INDEX, inputTestFileName);

        String result = new TheTyrannyOfTheRocketEquation().solveSecondPuzzle(input);

        LOGGER.info(() -> "Testing " + input.lines().collect(Collectors.toList()).toString() +
                " and expecting : " + expected);
        Assertions.assertEquals(expected, result);
    }
}
