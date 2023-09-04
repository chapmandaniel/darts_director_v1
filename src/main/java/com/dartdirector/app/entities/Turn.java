package com.dartdirector.app.entities;

public class Turn {


    private final Player player;
    public int score;
    public int dartsThrown;

    public Turn(Player player) {
        this.player = player;
    }


    public Player getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

    public int getDartsThrown() {
        return dartsThrown;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDartsThrown(int dartsThrown) {
        this.dartsThrown = dartsThrown;
    }

    public String getTurnSummary() {
        return player.getName() + " scored " + score + " with " + dartsThrown + " darts.";
    }
}
