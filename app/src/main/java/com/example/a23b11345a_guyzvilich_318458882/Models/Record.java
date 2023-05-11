package com.example.a23b11345a_guyzvilich_318458882.Models;

public class Record {

    private String difficulty = "";
    private double xCoordinate = 0;
    private double yCoordinate = 0;
    private int score = 0;

    public Record(String difficulty, double xCoordinate, double yCoordinate, int score)
    {
        this.difficulty = difficulty;
        this.score = score;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public Record setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public Record setxCoordinate(long xCoordinate) {
        this.xCoordinate = xCoordinate;
        return this;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public Record setyCoordinate(long yCoordinate) {
        this.yCoordinate = yCoordinate;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Record setScore(int score) {
        this.score = score;
        return this;
    }


    @Override
    public String toString() {
        return "Record:" +"\nScore=" + score+
                "\nDifficulty='" + difficulty + '\'' +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate
                ;
    }
}
