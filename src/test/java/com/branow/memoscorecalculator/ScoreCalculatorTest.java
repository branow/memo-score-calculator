package com.branow.memoscorecalculator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static com.brano.print.ShortPrint.form;

public abstract class ScoreCalculatorTest {

    public void printHistory(List<Score> scores) {
        ScoreParams params = null;
        for (int i=1; i<=scores.size(); i++) {
            Score score = scores.get(i - 1);
            params = printScoreParams(score, params);
            if (i != scores.size())
                printScores(score.getTime(), scores.get(i).getTime(), params);
        }

    }

    public ScoreParams printScoreParams(Score score, ScoreParams params) {
        ScoreCalculator calc = getScoreCalculator(score.getTime());
        ScoreParams newParam =
                params == null ? calc.calcScoreParams(score) : calc.calcScoreParams(score, params);
        form().and(newParam).blue().br().println();
        return newParam;
    }

    public void printScores(LocalDateTime start, LocalDateTime end, ScoreParams params) {
        long st = start.atZone(ZoneId.systemDefault()).toEpochSecond();
        long en = end.atZone(ZoneId.systemDefault()).toEpochSecond();
        double step = (en - st) / 3.;
        for (double i = st; i < en; i += step) {
            printScore(LocalDateTime.ofInstant(Instant.ofEpochSecond((long) i), ZoneId.systemDefault()), params);
        }
    }


    public void printScore(LocalDateTime current, ScoreParams params) {
        ScoreCalculator calc = getScoreCalculator(current);
        form().and(current).tab().tab().and("score -> ").and(calc.calcScore(params)).blue().println();
    }



    protected abstract ScoreCalculator getScoreCalculator(LocalDateTime localDateTime);


}
