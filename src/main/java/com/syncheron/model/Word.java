package com.syncheron.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Objects;

@Component
public class Word {

    private HashMap<String,Integer> wordCounterMap;

    public void setWordCounterMap(HashMap<String, Integer> wordCounterMap) {
        this.wordCounterMap = wordCounterMap;
    }

    public HashMap<String, Integer> getWordCounterMap() {
        if(wordCounterMap ==null){
            wordCounterMap = new HashMap<>();
        }
        return wordCounterMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word = (Word) o;
        return getWordCounterMap().equals(word.getWordCounterMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWordCounterMap());
    }


}
