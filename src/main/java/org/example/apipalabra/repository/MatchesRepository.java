package org.example.apipalabra.repository;

import org.example.apipalabra.model.Matches;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchesRepository extends JpaRepository<Matches, Integer> {
}
