package org.example.apipalabra.controller;

import org.example.apipalabra.model.Player;
import org.example.apipalabra.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("wordsGame/players")
public class PlayerController {
    private final PlayerRepository playerRepository;
    @Autowired
    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/")
    public List<Player> allPlayers(){return playerRepository.findAll();}
}
