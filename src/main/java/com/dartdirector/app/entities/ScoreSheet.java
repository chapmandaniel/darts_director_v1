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

    private Team currentTeam; // Currently playing team
    private boolean isComplete = false;
    private int homeScore;
    private int awayScore;
    private final int targetScore = 501; // Standard game of darts target score

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

    public void playLeg() {
        if (currentTeam == null) {currentTeam = team1;}

        // Load the active lineup for each team
        List<Player> team1Lineup = activeLineupTeam1;
        List<Player> team2Lineup = activeLineupTeam2;

        // Create indexes to keep track of which player is currently playing for each team
        int team1PlayerIndex = 0;
        int team2PlayerIndex = 0;

        // Continue the turns until leg is complete.
        while (!isComplete) {

            // Determine which player index to use based on the current team
            int currentPlayerIndex = (currentTeam == team1) ? team1PlayerIndex : team2PlayerIndex;

            // Retrieve the current player from the current team's active lineup
            Player currentPlayer = currentTeam.getActiveLineup().get(currentPlayerIndex);

            // Simulate the current player's turn.
            Turn turn = new Turn(currentPlayer);
            int score = (int) (Math.random() * 100) + 1;  // Generate a random score between 1 and 100
            turn.setScore(score);
            turn.setDartsThrown(3);  // We assume 3 darts are thrown by a player

            // Record the current player's turn.
            turns.add(turn);

            // Update the current team's score
            if (currentTeam == team1) {
                this.homeScore += score;
            } else {
                this.awayScore += score;
            }

            // Check if the leg is complete
            if (this.homeScore >= this.targetScore || this.awayScore >= this.targetScore) {
                isComplete = true;
                if (this.homeScore >= this.targetScore) {
                    setDoubleFinisher(currentPlayer, homeScore);
                } else {
                    setDoubleFinisher(currentPlayer, awayScore);
                }
            }

            // Switch to the other team
            currentTeam = (currentTeam == team1) ? team2 : team1;

            if (currentTeam == team1) {
                team2PlayerIndex = (team2PlayerIndex + 1) % team2.getActiveLineup().size();
            } else {
                team1PlayerIndex = (team1PlayerIndex + 1) % team1.getActiveLineup().size();
            }
        }
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