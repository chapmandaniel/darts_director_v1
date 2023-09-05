// src/main/java/com/dartdirector/app/services/TeamService.java

package com.dartdirector.app.layers.services;

import com.dartdirector.app.entities.Team;
import com.dartdirector.app.layers.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    public Team saveTeam(Team team) throws Exception {
        if (teamRepository.existsByName(team.getName())) {
            throw new Exception("A team with this name already exists.");
        }
        return teamRepository.save(team);
    }

}
