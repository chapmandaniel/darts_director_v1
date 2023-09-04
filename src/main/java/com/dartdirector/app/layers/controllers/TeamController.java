// src/main/java/com/dartdirector/app/controllers/TeamController.java

package com.dartdirector.app.layers.controllers;

import com.dartdirector.app.entities.Team;
import com.dartdirector.app.layers.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team savedTeam = teamService.saveTeam(team);
        return ResponseEntity.ok(savedTeam);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        if (!teamService.getTeamById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        team.setId(id); // Ensure the ID is set on the object
        return ResponseEntity.ok(teamService.saveTeam(team));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        if (!teamService.getTeamById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}
