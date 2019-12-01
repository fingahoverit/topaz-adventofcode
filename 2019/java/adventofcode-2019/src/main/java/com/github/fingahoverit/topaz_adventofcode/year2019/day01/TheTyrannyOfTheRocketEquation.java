package com.github.fingahoverit.topaz_adventofcode.year2019.day01;

import com.github.fingahoverit.topaz_adventofcode.year2019.common.InputLoader;

import java.util.logging.Logger;

/**
 * {@link} https://adventofcode.com/2019/day/1
 */
public class TheTyrannyOfTheRocketEquation {

    private static final Logger LOGGER = Logger.getLogger(TheTyrannyOfTheRocketEquation.class.getCanonicalName());

    public static final String DAY_INDEX = "01";
    private static final String INPUT_01_FILE = "01.txt";

    public static void main(String[] args) {

        TheTyrannyOfTheRocketEquation cc = new TheTyrannyOfTheRocketEquation();

        var result1 = cc.solveFirstPuzzle(InputLoader.load(DAY_INDEX, INPUT_01_FILE));
        LOGGER.info(() -> "Puzzle 1 : " + result1);

        var result2 = cc.solveSecondPuzzle(InputLoader.load(DAY_INDEX, INPUT_01_FILE));
        LOGGER.info(() -> "Puzzle 2 : " + result2);
    }

    public String solveFirstPuzzle(String input) {
        return input.lines()
                .map(mass -> Math.floorDiv(Integer.parseInt(mass), 3) - 2)
                .reduce(0, Integer::sum).toString();
    }

    public String solveSecondPuzzle(String input) {

        return input.lines()
                .map(this::massToFullFuel)
                .reduce(0, Integer::sum).toString();
    }

    private Integer massToFullFuel(String mass) {

        int fuel = Integer.parseInt(mass);
        int total = 0;

        while (fuel > 0) {
            fuel = Math.floorDiv(fuel, 3) - 2;
            if (fuel > 0) {
                total += fuel;
            }
        }

        return total;
    }
}
