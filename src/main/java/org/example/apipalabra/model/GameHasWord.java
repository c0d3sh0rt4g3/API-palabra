package org.example.apipalabra.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Game_Has_Word", schema = "words-API")
public class GameHasWord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int gameId;
    @Column
    private int wordId;
    @Column
    private String wordDifficulty;
}