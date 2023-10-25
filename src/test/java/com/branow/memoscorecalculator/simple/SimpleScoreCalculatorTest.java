package com.branow.memoscorecalculator.simple;

import com.branow.memoscorecalculator.Score;
import com.branow.memoscorecalculator.ScoreCalculator;
import com.branow.memoscorecalculator.ScoreCalculatorTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public class SimpleScoreCalculatorTest extends ScoreCalculatorTest {

    @ParameterizedTest
    @MethodSource("providePrintHistory")
    public void printHistoryTest(List<Score> score) {
        super.printHistory(score);
    }

    @Override
    protected ScoreCalculator getScoreCalculator(LocalDateTime localDateTime) {
        return new SimpleScoreCalculator(localDateTime);
    }


    private static Stream<Arguments> providePrintHistory() {
        return Stream.of(
                Arguments.of(scores1()),
                Arguments.of(scores2()),
                Arguments.of(scores3()),
                Arguments.of(scores3())
        );
    }


    private static List<Score> scores1() {
        LocalDateTime time = LocalDateTime.of(2023, 1, 12, 12, 45, 30);
        return List.of(
                new SimpleScore(100, time),
                new SimpleScore(100, time.plusSeconds(30)),
                new SimpleScore(100, time.plusMinutes(11)),
                new SimpleScore(100, time.plusHours(1)),
                new SimpleScore(100, time.plusHours(7)),
                new SimpleScore(100, time.plusDays(1)),
                new SimpleScore(100, time.plusDays(3)),
                new SimpleScore(100, time.plusDays(7)),
                new SimpleScore(100, time.plusDays(14)),
                new SimpleScore(100, time.plusMonths(1)),
                new SimpleScore(100, time.plusMonths(3)),
                new SimpleScore(100, time.plusMonths(6)),
                new SimpleScore(100, time.plusYears(1)),
                new SimpleScore(100, time.plusYears(1).plusHours(12)),
                new SimpleScore(100, time.plusYears(1).plusMonths(1))
        );
    }

    private static List<Score> scores2() {
        LocalDateTime time = LocalDateTime.of(2023, 1, 12, 12, 45, 30);
        return List.of(
                new SimpleScore(50, time),
                new SimpleScore(10, time.plusSeconds(30)),
                new SimpleScore(85, time.plusMinutes(1)),
                new SimpleScore(100, time.plusHours(1)),
                new SimpleScore(90, time.plusHours(1)),
                new SimpleScore(100, time.plusDays(7))
        );
    }


    private static List<Score> scores3() {
        LocalDateTime time = LocalDateTime.of(2023, 1, 12, 12, 45, 30);
        return List.of(
                new SimpleScore(0, time),
                new SimpleScore(30, time.plusSeconds(1)),
                new SimpleScore(30, time.plusSeconds(1)),
                new SimpleScore(100, time.plusSeconds(1)),
                new SimpleScore(60, time.plusMinutes(20)),
                new SimpleScore(30, time.plusDays(1)),
                new SimpleScore(70, time.plusDays(1)),
                new SimpleScore(100, time.plusDays(1)),
                new SimpleScore(80, time.plusDays(3)),
                new SimpleScore(60, time.plusDays(7))
        );
    }

    private static List<Score> scores4() {
        LocalDateTime time = LocalDateTime.of(2023, 1, 12, 12, 45, 30);
        return List.of(
                new SimpleScore(50, time),
                new SimpleScore(10, time.plusSeconds(30))
        );
    }

}
