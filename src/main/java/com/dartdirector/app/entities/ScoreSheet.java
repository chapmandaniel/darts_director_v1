package com.dartdirector.app.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class ScoreSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Team team1;

    @ManyToOne
    private Team team2;

    @OneToMany
    private List<Player> activeLineupTeam1;

    @OneToMany
    private List<Player> activeLineupTeam2;

    @OneToMany(mappedBy = "scoreSheet", cascade = CascadeType.ALL)
    private List<Turn> turns;

    @OneToOne
    private Player doubleFinisher;

    private int doubleFinisherScore;

    public ScoreSheet(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.activeLineupTeam1 = team1.getActiveLineup();
        this.activeLineupTeam2 = team2.getActiveLineup();
    }

    public ScoreSheet() {

    }

    public void changeActiveLineupTeam1(List<Player> newLineup) {
        this.activeLineupTeam1 = newLineup;
    }

    public void changeActiveLineupTeam2(List<Player> newLineup) {
        this.activeLineupTeam2 = newLineup;
    }

    public void recordTurn(Turn turn) {
        this.turns.add(turn);
    }

    public void setDoubleFinisher(Player player, int score) {
        this.doubleFinisher = player;
        this.doubleFinisherScore = score;
    }

    public void playMatch() {
        for (int i = 0; i < 6; i++) {
            playLeg();
            updateLineup();
        }
        determineWinner();
    }

    private void playLeg() {
        // logic for playing a single leg
        for (Player player : activeLineupTeam1) {
            Turn turn = new Turn(player);
            recordTurn(turn);
        }
        for (Player player : activeLineupTeam2) {
            Turn turn = new Turn(player);
            recordTurn(turn);
        }
    }

    private void updateLineup() {
        // logic for updating the lineup
        // This is a placeholder. The actual implementation will depend on the rules of the game.
        changeActiveLineupTeam1(team1.getActiveLineup());
        changeActiveLineupTeam2(team2.getActiveLineup());
    }

    private void determineWinner() {
        // logic for determining the winner
        // This is a placeholder. The actual implementation will depend on the rules of the game.
        int scoreTeam1 = calculateScoreTeam(activeLineupTeam1);
        int scoreTeam2 = calculateScoreTeam(activeLineupTeam2);
        if (scoreTeam1 > scoreTeam2) {
            setDoubleFinisher(activeLineupTeam1.get(activeLineupTeam1.size() - 1), scoreTeam1);
        } else {
            setDoubleFinisher(activeLineupTeam2.get(activeLineupTeam2.size() - 1), scoreTeam2);
        }
    }

    private int calculateScoreTeam(List<Player> activeLineup) {
        // logic for calculating the score of a team
        // This is a placeholder. The actual implementation will depend on the rules of the game.
        int score = 0;
        for (Player player : activeLineup) {
            for (Turn turn : turns) {
                if (turn.getPlayer().equals(player)) {
                    score += turn.getScore();
                }
            }
        }
        return score;
    }

    // ... getters and setters ...

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public List<Player> getActiveLineupTeam1() {
        return activeLineupTeam1;
    }

    public void setActiveLineupTeam1(List<Player> activeLineupTeam1) {
        this.activeLineupTeam1 = activeLineupTeam1;
    }

    public List<Player> getActiveLineupTeam2() {
        return activeLineupTeam2;
    }

    public void setActiveLineupTeam2(List<Player> activeLineupTeam2) {
        this.activeLineupTeam2 = activeLineupTeam2;
    }

    public List<Turn> getTurns() {
        return turns;
    }

    public void setTurns(List<Turn> turns) {
        this.turns = turns;
    }

    public Player getDoubleFinisher() {
        return doubleFinisher;
    }

    public void setDoubleFinisher(Player doubleFinisher) {
        this.doubleFinisher = doubleFinisher;
    }

    public int getDoubleFinisherScore() {
        return doubleFinisherScore;
    }

    public void setDoubleFinisherScore(int doubleFinisherScore) {
        this.doubleFinisherScore = doubleFinisherScore;
    }

    public void setId(Long id) {
    }
}