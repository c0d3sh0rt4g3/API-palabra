package org.example.apipalabra.repository;

import org.example.apipalabra.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}
