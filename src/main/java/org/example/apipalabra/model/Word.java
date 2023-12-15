package org.example.apipalabra.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Word", schema = "words-api")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int wordId;
    @Column
    private String word;
}