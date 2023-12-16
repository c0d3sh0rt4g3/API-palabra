package org.example.apipalabra.repository;

import org.example.apipalabra.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
