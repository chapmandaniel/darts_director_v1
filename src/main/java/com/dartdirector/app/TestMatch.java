package com.dartdirector.app;
import com.dartdirector.app.entities.Player;
import com.dartdirector.app.entities.Team;
import com.dartdirector.app.entities.Match;

public class TestMatch {

    public static void main(String[] args) {
        System.out.println("Starting Match Test...");

        Team rosie = new Team("Rosie's");
        Team westSide = new Team("West Side Charlies");

        Player player1 = new Player("John Norman Jr", "player1@darts.com");
        Player player2 = new Player("Daryl Norman", "player2@darts.com");
        Player player3 = new Player("Rod Petten", "player3@darts.com");
        Player player4 = new Player("Mitch Burt", "player4@darts.com");
        Player player5 = new Player("Bill St.Croix", "player5@darts.com");
        Player player6 = new Player("Kerry Bungay", "player6@darts.com");
        Player player7 = new Player("Brent Colombe", "player7@darts.com");
        Player player8 = new Player("Albert Smith", "player8@darts.com");
        Player player9 = new Player("Daniel Chapman", "player9@darts.com");
        Player player10 = new Player("Jim Smith", "player10@darts.com");

        rosie.addPlayer(player1);
        rosie.addPlayer(player2);
        rosie.addPlayer(player3);
        rosie.addPlayer(player4);
        rosie.addPlayer(player5);
        rosie.setDefaultLineup();

        westSide.addPlayer(player6);
        westSide.addPlayer(player7);
        westSide.addPlayer(player8);
        westSide.addPlayer(player9);
        westSide.addPlayer(player10);
        westSide.setDefaultLineup();

        Match match = new Match(rosie, westSide, 6);
        match.playMatch();
        match.printMatchReport();
        System.out.println(match.getMatchResults());

    }
}
