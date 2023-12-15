package org.example.apipalabra.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Game", schema = "words-api")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int gameId;
    @Column
    private String difficulty;
    @Column
    private String description;
    @Column
    private int maxTries;
}