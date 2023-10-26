package com.branow.memoscorecalculator;

public interface ScoreCalculator {


    int calcScore(ScoreParams params);

    ScoreFullParams calcScoreParams(Score score);

    ScoreFullParams calcScoreParams(Score score, ScoreFullParams params);

}
