package com.dartdirector.app.layers.services;

import com.dartdirector.app.entities.Player;
import com.dartdirector.app.layers.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    public Player findPlayerById(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(Long id, Player player) {
        // Add your update logic here
        return playerRepository.save(player);
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }

}
