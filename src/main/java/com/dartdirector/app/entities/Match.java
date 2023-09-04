package com.dartdirector.app.entities;

import java.util.ArrayList;

public class Match {

    private final Team homeTeam;
    private final Team awayTeam;
    private final int totalLegs;
    private boolean isComplete = false;
    private int currentLeg = 0;
    private final Team throwsFirst;
    private ArrayList<Leg> legs = new ArrayList<Leg>();

    public Match(Team homeTeam, Team awayTeam, int totalLegs ) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.totalLegs = totalLegs;
        this.throwsFirst = this.setThrowsFirst();
    }

    public void playMatch() {

        while (legs.size() < totalLegs) {
            Leg leg = new Leg(1001, homeTeam, awayTeam);
            leg.playLeg();
            legs.add(leg);
        }
        isComplete = true;
    }

    public String getMatchResults() {
        return "\nMatch Results:\n----------------\n" + homeTeam.getName() + " " + countLegWins(homeTeam) + " - " + awayTeam.getName() + " " + countLegWins(awayTeam);
    }

    public void printMatchReport() {
        System.out.println("\nMatch Report:\n----------------");
        for (Leg leg : legs) {
            leg.printLegResults();
        }
    }

    private int countLegWins(Team team) {
        return (int) legs.stream().filter(leg -> leg.getWinner() == team).count();
    }

    public Team flipCoin() {
        int coin = (int) Math.floor(Math.random() * 2);
        if (coin == 0) {
            return homeTeam;
        } else {
            return awayTeam;
        }
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public int getTotalLegs() {
        return totalLegs;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public int getCurrentLeg() {
        return currentLeg;
    }

    public void setCurrentLeg(int currentLeg) {
        this.currentLeg = currentLeg;
    }

    public Team getThrowsFirst() {
        return throwsFirst;
    }

    public Team setThrowsFirst() {
        return this.flipCoin();
    }
}
