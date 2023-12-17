package org.example.apipalabra.services;

import org.example.apipalabra.model.Word;
import org.example.apipalabra.repository.WordRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class WordService {
    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<Optional<Word>> randomWords(int numbOfWords){
        var randomNumb = new Random();
        List<Word> allWords = wordRepository.findAll();
        List<Optional<Word>> randomWords = new ArrayList<>();
        for (int i = 0; i < numbOfWords; i++){
            int id = randomNumb.nextInt((allWords.size() + 1) - 1) + 1;
            randomWords.add(wordRepository.findById(id));
        }
        return randomWords;
    }

}
