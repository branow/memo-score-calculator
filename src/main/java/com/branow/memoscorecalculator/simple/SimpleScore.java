package com.branow.memoscorecalculator.simple;

import com.branow.memoscorecalculator.Score;

import java.time.LocalDateTime;

public class SimpleScore implements Score {

    private int score;
    private LocalDateTime time;

    public SimpleScore(int score, LocalDateTime time) {
        this.score = score;
        this.time = time;
    }

    @Override
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SimpleScore{" +
                "score=" + score +
                ", time=" + time +
                '}';
    }
}
