package com.github.fingahoverit.topaz_adventofcode.year2019.day02;

import com.github.fingahoverit.topaz_adventofcode.year2019.common.ExpectedLoader;
import com.github.fingahoverit.topaz_adventofcode.year2019.common.InputLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProgramAlarm1202Test {

    private static final Logger LOGGER = Logger.getLogger(ProgramAlarm1202Test.class.getCanonicalName());

    private static final String INPUT_TEST_01_FILE = "01.txt";
    private static final String INPUT_TEST_02_FILE = "02.txt";
    private static final String INPUT_TEST_03_FILE = "03.txt";
    private static final String INPUT_TEST_04_FILE = "04.txt";

    @ParameterizedTest
    @ValueSource(strings = {INPUT_TEST_01_FILE, INPUT_TEST_02_FILE, INPUT_TEST_03_FILE, INPUT_TEST_04_FILE})
    void testFirstPuzzleIntCode(String inputTestFileName) {

        String input = InputLoader.load(ProgramAlarm1202.DAY_INDEX, inputTestFileName);
        String expected = ExpectedLoader.load(ProgramAlarm1202.DAY_INDEX, inputTestFileName);

        ProgramAlarm1202 pa = new ProgramAlarm1202();
        List<Integer> intCode = pa.convertInput(input);
        intCode = pa.runIntCode(intCode);
        String result = intCodeListToString(intCode);

        LOGGER.info(() -> "Testing " + input + " and expecting : " + expected);
        Assertions.assertEquals(expected, result);
    }

    String intCodeListToString(List<Integer> intCodeList) {
        return intCodeList.stream().map(String::valueOf).collect(Collectors.joining(","));
    }
}
