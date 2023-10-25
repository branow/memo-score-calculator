package com.branow.memoscorecalculator;

import java.time.LocalDateTime;

public interface ScoreParams {

    Score getLastScore();
    LocalDateTime getResetTime();
    int getStudyRepetition();

}
