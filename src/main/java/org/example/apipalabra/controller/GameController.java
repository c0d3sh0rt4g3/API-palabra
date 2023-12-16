package org.example.apipalabra.controller;

import org.example.apipalabra.exceptions.NotFoundException;
import org.example.apipalabra.model.Game;
import org.example.apipalabra.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wordapi")
public class GameController {
    private final GameRepository gameRepository;
    private final String GAME = "Game";
    @Autowired
    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/games")
    public List<Game> allGames(){
        return gameRepository.findAll();
    }

    @GetMapping("/game/{id}")
    public Game getGameById(@PathVariable Integer id){
        return gameRepository.findById(id).orElseThrow(() -> new NotFoundException(GAME, id));
    }
    @DeleteMapping("/game/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable Integer id) {
        return gameRepository.findById(id)
                .map(game -> {
                    gameRepository.delete(game);
                    return ResponseEntity.status(HttpStatus.OK).body(game.getGameName() + " deleted succesfully");
                })
                .orElseThrow(() -> new NotFoundException(GAME, id));
    }
    @PostMapping("/game")
    public ResponseEntity<Game> addGame(@RequestBody Game givenGame){
        return ResponseEntity.status(HttpStatus.CREATED).body(gameRepository.save(givenGame));
    }

    @PutMapping("/game/{id}")
    public Game updateGame(@PathVariable Integer id, @RequestBody Game givenGame) {
        return gameRepository.findById(id)
                .map(existingGame -> {
                    existingGame.setGameName(givenGame.getGameName());
                    existingGame.setDifficulty(givenGame.getDifficulty());
                    existingGame.setDescription(givenGame.getDescription());
                    existingGame.setMaxTries(givenGame.getMaxTries());
                    return ResponseEntity.status(HttpStatus.OK).body(gameRepository.save(existingGame)).getBody();
                })
                .orElseThrow(() -> new NotFoundException(GAME, id));
    }

}

