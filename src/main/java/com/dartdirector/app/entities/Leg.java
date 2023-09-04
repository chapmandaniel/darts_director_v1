package com.dartdirector.app.entities;

import java.util.ArrayList;
import java.util.List;

public class Leg {

    private final int targetScore;
    private final Team homeTeam;
    private final Team awayTeam;
    private int homeScore;
    private int awayScore;
    private ArrayList<Turn> turns = new ArrayList<>();
    private boolean isComplete = false;
    private Team winner;

    private Team currentTeam; // Currently playing team

    public Leg(int targetScore, Team homeTeam, Team awayTeam) {
        this.targetScore = targetScore;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
    }

    public void playLeg() {
        if (currentTeam == null) {currentTeam = homeTeam;}
        System.out.println("Playing leg between " + homeTeam.getName() + " and " + awayTeam.getName() + " to " + targetScore + " points.");

        // Load the active lineup for each team
        List<Player> homeLineup = homeTeam.getActiveLineup();
        List<Player> awayLineup = awayTeam.getActiveLineup();

        // Create indexes to keep track of which player is currently playing for each team
        int homePlayerIndex = 0;
        int awayPlayerIndex = 0;

        // Continue the turns until leg is complete.
        while (!isComplete) {

            // Determine which player index to use based on the current team
            int currentPlayerIndex = (currentTeam == homeTeam) ? homePlayerIndex : awayPlayerIndex;

            // Retrieve the current player from the current team's active lineup
            Player currentPlayer = currentTeam.getActiveLineup().get(currentPlayerIndex);


            // Simulate the current player's turn.
            // TODO: implement user input for the turn score
            Turn turn = new Turn(currentPlayer);
            int score = (int) (Math.random() * 100) + 1;  // Generate a random score between 1 and 100
            turn.setScore(score);
            turn.setDartsThrown(3);  // We assume 3 darts are thrown by a player

            // Record the current player's turn.
            turns.add(turn);

            // Update the current team's score
            if (currentTeam == homeTeam) {
                this.homeScore += score;
            } else {
                this.awayScore += score;
            }

            // Check if the leg is complete
            if (this.homeScore >= this.targetScore || this.awayScore >= this.targetScore) {
                isComplete = true;
                System.out.println("Leg is complete.");
                if (this.homeScore >= this.targetScore) {
                    System.out.println(this.homeTeam.getName() + " won the leg.");
                    this.winner = homeTeam;
                } else {
                    System.out.println(this.awayTeam.getName() + " won the leg.");
                    this.winner = awayTeam;
                }
            }

            // Switch to the other team
            currentTeam = (currentTeam == homeTeam) ? awayTeam : homeTeam;

            if (currentTeam == homeTeam) {
                awayPlayerIndex = (awayPlayerIndex + 1) % awayTeam.getActiveLineup().size();
            } else {
                homePlayerIndex = (homePlayerIndex + 1) % homeTeam.getActiveLineup().size();
            }
        }
    }

    public int getTargetScore() {
        return targetScore;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public Team getWinner() {
        return winner;
    }

    public void printLegResults(){
        System.out.println("Leg Results:");
        System.out.println("------------");
        System.out.println(this.homeTeam.getName() + " " + this.homeScore + " - " + this.awayTeam.getName() + " " + this.awayScore);
        System.out.println("------------");
        System.out.println("Turns:");
        System.out.println("------------");
        for (Turn turn : turns) {
            System.out.println(turn.getPlayer().getName() + " scored " + turn.getScore() + " with " + turn.getDartsThrown() + " darts.");
        }
    }
}
