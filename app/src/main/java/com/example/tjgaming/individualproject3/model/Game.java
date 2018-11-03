package com.example.tjgaming.individualproject3.model;

import java.io.Serializable;

/**
 * Created by TJ on 10/28/2018.
 */
public class Game implements Serializable {
    private String difficulty;
    private String level;
    boolean beaten;

    public Game() {

    }

    public Game(String difficulty, String level, boolean complete) {
        this.difficulty = difficulty;
        this.level = level;
        this.beaten = complete;
    }

    public String getDifficulty() {

        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean isComplete() {
        return beaten;
    }

    public void setComplete(boolean beaten) {
        this.beaten = beaten;
}

    @Override
    public String toString() {
       return "Difficulty: " + difficulty + "\n" +
               "Level: " + level + "\n" +
               "Beaten: " + beaten + "\n";
    }
}