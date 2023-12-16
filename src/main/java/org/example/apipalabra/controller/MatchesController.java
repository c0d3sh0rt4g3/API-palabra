package org.example.apipalabra.controller;

import org.example.apipalabra.exceptions.NotFoundException;
import org.example.apipalabra.model.Matches;
import org.example.apipalabra.repository.MatchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wordapi")
public class MatchesController {
    private final MatchesRepository matchesRepository;
    private final String MATCH = "Match";
    @Autowired
    public MatchesController(MatchesRepository matchesRepository) {
        this.matchesRepository = matchesRepository;
    }

    @GetMapping("/matches")
    public List<Matches> allMatches(){
        return matchesRepository.findAll();
    }

    @GetMapping("/match/{id}")
    public Matches getMatchById(@PathVariable Integer id){
        return matchesRepository.findById(id).orElseThrow(() -> new NotFoundException(MATCH, id));
    }
    @DeleteMapping("/match/{id}")
    public ResponseEntity<?> deleteMatch(@PathVariable Integer id) {
        return matchesRepository.findById(id)
                .map(match -> {
                    matchesRepository.delete(match);
                    return ResponseEntity.status(HttpStatus.OK).body(match.getWord() + " deleted succesfully");
                })
                .orElseThrow(() -> new NotFoundException(MATCH, id));
    }
    @PostMapping("/match")
    public ResponseEntity<Matches> addMatch(@RequestBody Matches givenMatch){
        return ResponseEntity.status(HttpStatus.CREATED).body(matchesRepository.save(givenMatch));
    }
    @PutMapping("/match/{id}")
    public Matches updateMatch(@PathVariable Integer id, @RequestBody Matches givenMatch) {
        return matchesRepository.findById(id)
                .map(existingMatch -> {
                    existingMatch.setWord(givenMatch.getWord());
                    existingMatch.setMaxTries(givenMatch.getMaxTries());
                    existingMatch.setMatchDate(givenMatch.getMatchDate());
                    return matchesRepository.save(existingMatch);
                })
                .orElseThrow(() -> new NotFoundException(MATCH, id));
    }
}