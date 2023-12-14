package org.example.apipalabra.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "Match", schema = "words-API")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int matchId;
    @Column
    private String word;
    @Column
    private int matchScore;
    @Column
    private int maxTries;
    @Column
    private Timestamp currentTime;
}