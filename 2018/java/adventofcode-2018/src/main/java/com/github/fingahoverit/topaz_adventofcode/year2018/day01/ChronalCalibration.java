package com.github.fingahoverit.topaz_adventofcode.year2018.day01;

import com.github.fingahoverit.topaz_adventofcode.year2018.common.InputLoader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * {@link} https://adventofcode.com/2018/day/1
 */
public class ChronalCalibration {

    private static final Logger LOGGER = Logger.getLogger(ChronalCalibration.class.getCanonicalName());

    public static final String DAY_INDEX = "01";
    private static final String INPUT_01_FILE = "01.txt";
    private static final String INPUT_02_FILE = "02.txt";

    public static void main(String[] args) {

        ChronalCalibration cc = new ChronalCalibration();

        var result1 = cc.solveFirstPuzzle(InputLoader.load(DAY_INDEX, INPUT_01_FILE));
        LOGGER.info(() -> "Puzzle 1 : " + result1);

        var result2 = cc.solveSecondPuzzle(InputLoader.load(DAY_INDEX, INPUT_02_FILE));
        LOGGER.info(() -> "Puzzle 2 : " + result2);
    }

    public String solveFirstPuzzle(String input) {
        return input.lines().map(Integer::parseInt).reduce(0, Integer::sum).toString();
    }

    public String solveSecondPuzzle(String input) {

        Set<Integer> frequencies = new HashSet<>();
        int frequencyIndex = 0;
        Integer currentFrequency = 0;
        List<Integer> changes = input.lines().map(Integer::parseInt).collect(Collectors.toList());

        while (!frequencies.contains(currentFrequency)) {
            frequencies.add(currentFrequency);
            currentFrequency = currentFrequency + changes.get(frequencyIndex % changes.size());
            frequencyIndex++;
        }

        return "" + currentFrequency;
    }
}
