package com.syncheron.controller;

import com.syncheron.model.Word;
import com.syncheron.service.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Predicate;

@RestController
public class WordController {

    @Autowired
    Word word;

    @Autowired
    Translator translator;

    final Predicate<String> isAlphabetPredicate = x -> x.chars().allMatch(Character::isAlphabetic);

    //Should be post but for testing simplicity have kept it as only getmapping

    /**
     * This Method accepts a Sentence, uses a delimeter " "  to parse through words and then add those words in a hashmap
     * @param sentence
     * @return
     */
    @GetMapping("/addWord/{sentence}")
    public ResponseEntity<String> addWord(@PathVariable String sentence) {

        if (null != sentence) {
            Arrays.stream(sentence.toLowerCase().split(" "))
                    .filter(isAlphabetPredicate)
                    .map(x -> (translator.translate(x) != null) ? translator.translate(x) : x)
                    .forEach(x -> {
                                //Check if this word exists before in hashmap, if not add 1 else increment the counter by 1
                                HashMap<String, Integer> wordCounterMap = word.getWordCounterMap();
                                if (wordCounterMap.putIfAbsent(x, 1) != null) {
                                    wordCounterMap.put(x, wordCounterMap.get(x) + 1);
                                }
                            }
                    );
            return new ResponseEntity<>(sentence+ " has been added successfully " , HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Request");
    }


    /**
     * Return the count of the searched word from Hashmap
     * @param countWord
     * @return
     */
    @GetMapping("/count/{countWord}")
    public int countWord(@PathVariable String countWord) {

        //Return the counter, if no word found then return 0
        return word.getWordCounterMap().getOrDefault(countWord,0);


    }

}
