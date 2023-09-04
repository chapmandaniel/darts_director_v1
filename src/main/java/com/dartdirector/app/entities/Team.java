package com.dartdirector.app.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Player> roster = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Player> activeLineup = new ArrayList<>();

    public Team() {}  // Default constructor for JPA

    public Team(String name) {
        this.name = name;
    }

        public void setDefaultLineup() {
            for (int i = 0; i < 5; i++) {
                activeLineup.add(roster.get(i));
            }
        }

        public ArrayList<Player> getActiveLineup() {
            return (ArrayList<Player>) activeLineup;
        }

        public void addPlayer(Player player) {
            roster.add(player);
        }

        public String getName() {
            return name;
        }

        public ArrayList<Player> getRoster() {
            return (ArrayList<Player>) roster;
        }

    public void setId(Long id) {
    }
}
