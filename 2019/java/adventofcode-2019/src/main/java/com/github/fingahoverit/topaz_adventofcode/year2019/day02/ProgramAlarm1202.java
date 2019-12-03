package com.github.fingahoverit.topaz_adventofcode.year2019.day02;

import com.github.fingahoverit.topaz_adventofcode.year2019.common.InputLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

/**
 * {@link} https://adventofcode.com/2019/day/2
 */
public class ProgramAlarm1202 {

    private static final Logger LOGGER = Logger.getLogger(ProgramAlarm1202.class.getCanonicalName());

    public static final String DAY_INDEX = "02";
    private static final String INPUT_01_FILE = "01.txt";

    private enum OpCode {
        ADD(1), MULTIPLY(2), END(99);
        private final Integer code;

        OpCode(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

        static final Map<Integer, OpCode> byCode =
                Stream.of(OpCode.values()).collect(Collectors.toMap(OpCode::getCode, Function.identity()));

        public static OpCode getByCode(Integer code) {
            return byCode.get(code);
        }
    }

    public static void main(String[] args) {

        ProgramAlarm1202 cc = new ProgramAlarm1202();

        var result1 = cc.solveFirstPuzzle(InputLoader.load(DAY_INDEX, INPUT_01_FILE));
        LOGGER.info(() -> "Puzzle 1 : " + result1);

        var result2 = cc.solveSecondPuzzle(InputLoader.load(DAY_INDEX, INPUT_01_FILE));
        LOGGER.info(() -> "Puzzle 2 : " + result2);
    }

    public String solveFirstPuzzle(String input) {

        // Convert intCode
        List<Integer> intCode = convertInput(input);

        // Reposition intCode
        intCode = repositionIntCode(intCode);

        // Run intCode
        intCode = runIntCode(intCode);

        return intCode.get(0).toString();
    }

    public List<Integer> convertInput(String input) {
        return Arrays.stream(input.split(",")).map(Integer::parseInt).collect(toCollection(ArrayList::new));
    }

    public List<Integer> repositionIntCode(List<Integer> intCode) {

        intCode.set(1, 12);
        intCode.set(2, 2);
        return intCode;
    }

    public List<Integer> runIntCode(List<Integer> intCode) {
        for (int index = 0; index < intCode.size() && !OpCode.END.getCode().equals(intCode.get(index)); index += 4) {

            switch (OpCode.getByCode(intCode.get(index))) {
                case ADD:
                    intCode.set(intCode.get(index + 3),
                            intCode.get(intCode.get(index + 1)) + intCode.get(intCode.get(index + 2)));
                    break;
                case MULTIPLY:
                    intCode.set(intCode.get(index + 3),
                            intCode.get(intCode.get(index + 1)) * intCode.get(intCode.get(index + 2)));
                    break;
                default:
                    break;
            }
        }
        return intCode;
    }

    public String solveSecondPuzzle(String input) {

        int noun = 0;
        int verb = 0;
        Integer result = 0;

        // Convert intCode
        List<Integer> intCode = convertInput(input);

        while (result != 19690720 && verb < 100) {
            List<Integer> instructions = new ArrayList<>(intCode);

            instructions.set(1, noun);
            instructions.set(2, verb);
            instructions = runIntCode(instructions);
            result = instructions.get(0);

            if (result != 19690720) {
                if (noun != verb) {
                    verb++;
                } else {
                    noun++;
                    verb = 0;
                }
            }
        }
        return String.valueOf(100 * noun + verb);
    }
}
