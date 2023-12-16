package org.example.apipalabra.controller;

import org.example.apipalabra.exceptions.NotFoundException;
import org.example.apipalabra.model.Player;
import org.example.apipalabra.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wordapi")
public class PlayerController {
    private final PlayerRepository playerRepository;
    private final String PLAYER = "Player";
    @Autowired
    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/players")
    public List<Player> allPlayers(){
        return playerRepository.findAll();
    }

    @GetMapping("/player/{id}")
    public Player getPlayerById(@PathVariable Integer id){
        return playerRepository.findById(id).orElseThrow(() -> new NotFoundException(PLAYER, id));
    }
    @DeleteMapping("/player/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable Integer id) {
        return playerRepository.findById(id)
                .map(player -> {
                    playerRepository.delete(player);
                    return ResponseEntity.status(HttpStatus.OK).body(player.getPlayerName() + " deleted succesfully");
                })
                .orElseThrow(() -> new NotFoundException(PLAYER, id));
    }
    @PostMapping("/player")
    public ResponseEntity<Player> addPlayer(@RequestBody Player givenPlayer){
        return ResponseEntity.status(HttpStatus.CREATED).body(playerRepository.save(givenPlayer));
    }

    @PutMapping("/player/{id}")
    public Player updatePlayer(@PathVariable Integer id, @RequestBody Player givenPlayer) {
        return playerRepository.findById(id)
                .map(existingPlayer -> {
                    existingPlayer.setScore(0);
                    existingPlayer.setPlayerName(givenPlayer.getPlayerName());
                    existingPlayer.setPfp(givenPlayer.getPfp());
                    existingPlayer.setMatchId(givenPlayer.getMatchId());
                    existingPlayer.setTeamId(givenPlayer.getTeamId());
                    return ResponseEntity.status(HttpStatus.OK).body(playerRepository.save(existingPlayer)).getBody();
                })
                .orElseThrow(() -> new NotFoundException(PLAYER, id));
    }

}
