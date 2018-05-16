package com.example.niels.trivia;

public class Highscore {
    private int score;
    private String name;

    public Highscore(){}

    public Highscore(int aScore, String aName) {
        score = aScore;
        name = aName;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
