package com.branow.memoscorecalculator.simple;

import com.branow.memoscorecalculator.Score;
import com.branow.memoscorecalculator.ScoreCalculator;
import com.branow.memoscorecalculator.ScoreFullParams;
import com.branow.memoscorecalculator.ScoreParams;
import com.branow.outfits.math.Polynomial;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class SimpleScoreCalculator implements ScoreCalculator {

    private final long currentTime;

    public SimpleScoreCalculator() {
        this.currentTime = toSeconds(LocalDateTime.now());
    }

    public SimpleScoreCalculator(LocalDateTime currentTime) {
        this.currentTime = toSeconds(currentTime);
    }

    @Override
    public int calcScore(ScoreParams params) {
        int score = params.getLastScore().getScore();
        long lastTime = toSeconds(params.getLastScore().getTime());
        long resetTime = toSeconds(params.getResetTime());
        double interval = resetTime - lastTime;
        double passed = currentTime - lastTime;
        double passedMp = Math.max((interval - passed) / interval, 0);
        return (int) (score * passedMp);
    }

    @Override
    public ScoreFullParams calcScoreParams(Score score) {
        SimpleScoreParams newParams = new SimpleScoreParams();
        newParams.setLastScore(score);
        newParams.setStudyRepetition(1);
        newParams.setResetTime(calcResetTime(score));
        return newParams;
    }

    @Override
    public ScoreFullParams calcScoreParams(Score score, ScoreFullParams params) {
        SimpleScoreParams newParams = new SimpleScoreParams();
        newParams.setLastScore(score);
        newParams.setStudyRepetition(params.getStudyRepetition() + 1);
        newParams.setResetTime(calcResetTime(score, params));
        return newParams;
    }


    private LocalDateTime calcResetTime(Score score) {
        int newInterval = calcInterval(score.getScore(), 0, 0, 0);
        long newResetTime = currentTime + newInterval;
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(newResetTime), ZoneId.systemDefault());
    }

    private LocalDateTime calcResetTime(Score score, ScoreFullParams params) {
        long last = toSeconds(params.getLastScore().getTime());
        long reset = toSeconds(params.getResetTime());
        if (last > reset) {
            last = reset;
        }
        int interval = (int) (reset - last);
        int passedTime = (int) (currentTime - last);
        int newInterval = calcInterval(score.getScore(), interval, passedTime, params.getStudyRepetition());
        long newResetTime = currentTime + newInterval;
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(newResetTime), ZoneId.systemDefault());
    }


    private int calcInterval(int score, int lastInterval, int passedTime, int studyRepetition) {
        double interval = calcDirectInterval(lastInterval);
        if (lastInterval != 0) {
            interval *= calcPassedTimeMultiplier(lastInterval, passedTime);
            interval += (lastInterval * 0.6);
        }
        interval *= calcScoreMultiplier(score);
        interval *= calcStudyRepetitionMultiplier(studyRepetition);
        return (int) interval;
    }

    private double calcPassedTimeMultiplier(int lastInterval, int passedTime) {
        return Math.min((double) passedTime / lastInterval, 1.5);
    }

    private double calcStudyRepetitionMultiplier(int studyRepetition) {
        return Math.min(1 + studyRepetition / 100., 1.8);
    }

    private double calcScoreMultiplier(int score) {
        if (score < 0 || score > 100)
            throw new IllegalArgumentException("Score must be in scope [0, 100]: " + score);
        return Math.pow(score / 100., 7) * 1.43;
    }

    private int calcDirectInterval(int lastInterval) {
        Polynomial pol = new Polynomial(2.34, 1.24, 0, 0, -5.4E-7);
        double last = Math.pow(lastInterval, 0.25);
        double var = pol.calculate(last);
        return (int) Math.pow(var, 4);
    }

    private long toSeconds(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

}
