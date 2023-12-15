package org.example.apipalabra.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Team", schema = "words-api")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int teamId;
    @Column
    private String teamName;
    @Column
    private String badge;
    @Column
    private int teamScore;
}