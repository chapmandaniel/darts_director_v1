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
