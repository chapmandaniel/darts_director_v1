package com.dartdirector.app;

import com.dartdirector.app.entities.Player;
import com.dartdirector.app.entities.ScoreSheet;
import com.dartdirector.app.entities.Team;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TestScoreSheet {

    @Test
    public void testPlayMatch() {
        Team team1 = new Team("Team 1");
        Team team2 = new Team("Team 2");

        Player player1 = new Player("Player 1", "player1@example.com");
        Player player2 = new Player("Player 2", "player2@example.com");
        Player player3 = new Player("Player 3", "player3@example.com");
        Player player4 = new Player("Player 4", "player4@example.com");

        team1.addPlayer(player1);
        team1.addPlayer(player2);
        team2.addPlayer(player3);
        team2.addPlayer(player4);

        team1.setDefaultLineup();
        team2.setDefaultLineup();

        ScoreSheet scoreSheet = new ScoreSheet(team1, team2);
        scoreSheet.playMatch();

        // Add assertions to check the results of the match
    }
}