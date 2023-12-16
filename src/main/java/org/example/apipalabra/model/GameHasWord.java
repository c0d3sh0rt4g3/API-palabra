package org.example.apipalabra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "game_has_word")
public class GameHasWord {
    @Id
    private int gameId;
    private int wordId;
    private String wordDifficulty;
}
