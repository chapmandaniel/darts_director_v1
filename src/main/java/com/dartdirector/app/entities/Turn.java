package com.dartdirector.app.entities;

import jakarta.persistence.*;

@Entity
public class Turn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    private int score;

    @ManyToOne
    @JoinColumn(name = "scoresheet_id")
    private ScoreSheet scoreSheet;

    private int dartsThrown;

    public Turn(Player currentPlayer) {
    }

    public Turn() {

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
