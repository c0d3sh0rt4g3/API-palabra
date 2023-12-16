package org.example.apipalabra.repository;

import org.example.apipalabra.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Integer> {
}
