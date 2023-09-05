package com.dartdirector.app.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> roster = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
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


    public void addPlayer(Player player) {
        roster.add(player);
    }

    public String getName() {
        return name;
    }

    public List<Player> getActiveLineup() {
        return activeLineup;
    }

    public List<Player> getRoster() {
        return roster;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
    }
}
