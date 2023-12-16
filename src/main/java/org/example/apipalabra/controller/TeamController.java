package org.example.apipalabra.controller;

import org.example.apipalabra.exceptions.NotFoundException;
import org.example.apipalabra.model.Team;
import org.example.apipalabra.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wordapi")
public class TeamController {
    private final TeamRepository teamRepository;
    private final String TEAM = "Team";
    @Autowired
    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/teams")
    public List<Team> allTeams(){
        return teamRepository.findAll();
    }

    @GetMapping("/team/{id}")
    public Team getTeamById(@PathVariable Integer id){
        return teamRepository.findById(id).orElseThrow(() -> new NotFoundException(TEAM, id));
    }
    @DeleteMapping("/team/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Integer id) {
        return teamRepository.findById(id)
                .map(team -> {
                    teamRepository.delete(team);
                    return ResponseEntity.status(HttpStatus.OK).body(team.getTeamName() + " deleted succesfully");
                })
                .orElseThrow(() -> new NotFoundException(TEAM, id));
    }
    @PostMapping("/team")
    public ResponseEntity<Team> addTeam(@RequestBody Team givenTeam){
        return ResponseEntity.status(HttpStatus.CREATED).body(teamRepository.save(givenTeam));
    }
    @PutMapping("/team/{id}")
    public Team updateTeam(@PathVariable Integer id, @RequestBody Team givenTeam) {
        return teamRepository.findById(id)
                .map(existingTeam -> {
                    existingTeam.setTeamName(givenTeam.getTeamName());
                    existingTeam.setBadge(givenTeam.getBadge());
                    existingTeam.setTeamScore(0);
                    return teamRepository.save(existingTeam);
                })
                .orElseThrow(() -> new NotFoundException(TEAM, id));
    }
}