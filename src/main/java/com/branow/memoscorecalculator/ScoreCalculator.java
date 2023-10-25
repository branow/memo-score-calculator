package com.branow.memoscorecalculator;

public interface ScoreCalculator {


    int calcScore(ScoreParams params);

    ScoreParams calcScoreParams(Score score);

    ScoreParams calcScoreParams(Score score, ScoreParams params);

}
