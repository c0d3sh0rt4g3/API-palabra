package org.example.apipalabra.controller;

import org.example.apipalabra.exceptions.NotFoundException;
import org.example.apipalabra.model.Word;
import org.example.apipalabra.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wordapi")
public class WordController {
    private final WordRepository wordRepository;
    private final String WORD = "Word";
    @Autowired
    public WordController(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @GetMapping("/words")
    public List<Word> allWords(){
        return wordRepository.findAll();
    }

    @GetMapping("/word/{id}")
    public Word getWordById(@PathVariable Integer id){
        return wordRepository.findById(id).orElseThrow(() -> new NotFoundException(WORD, id));
    }
    @DeleteMapping("/word/{id}")
    public ResponseEntity<?> deleteWord(@PathVariable Integer id) {
        return wordRepository.findById(id)
                .map(word -> {
                    wordRepository.delete(word);
                    return ResponseEntity.status(HttpStatus.OK).body(word.getWord() + " deleted succesfully");
                })
                .orElseThrow(() -> new NotFoundException(WORD, id));
    }
    @PostMapping("/word")
    public ResponseEntity<Word> addWord(@RequestBody Word givenWord){
        return ResponseEntity.status(HttpStatus.CREATED).body(wordRepository.save(givenWord));
    }
    @PutMapping("/word/{id}")
    public Word updateWord(@PathVariable Integer id, @RequestBody Word givenWord) {
        return wordRepository.findById(id)
                .map(existingWord -> {
                    existingWord.setWord(givenWord.getWord());
                    return wordRepository.save(existingWord);
                })
                .orElseThrow(() -> new NotFoundException(WORD, id));
    }
}
