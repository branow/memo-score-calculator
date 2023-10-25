package com.branow.memoscorecalculator.simple;

import com.branow.memoscorecalculator.Score;
import com.branow.memoscorecalculator.ScoreParams;

import java.time.LocalDateTime;

public class SimpleScoreParams implements ScoreParams {

    private Score lastScore;
    private LocalDateTime resetTime;
    private int studyRepetition;

    @Override
    public Score getLastScore() {
        return lastScore;
    }

    public void setLastScore(Score lastScore) {
        this.lastScore = lastScore;
    }

    @Override
    public LocalDateTime getResetTime() {
        return resetTime;
    }

    public void setResetTime(LocalDateTime resetTime) {
        this.resetTime = resetTime;
    }

    @Override
    public int getStudyRepetition() {
        return studyRepetition;
    }

    public void setStudyRepetition(int studyRepetition) {
        this.studyRepetition = studyRepetition;
    }

    @Override
    public String toString() {
        return "SimpleScoreParams{" +
                "lastScore=" + lastScore +
                ", resetTime=" + resetTime +
                ", studyRepetition=" + studyRepetition +
                '}';
    }
}
