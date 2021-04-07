package com.syncheron.service;

import org.springframework.stereotype.Service;

@Service
public class Translator {

    public String translate(String word){

        if(word.equalsIgnoreCase("flor") || word.equalsIgnoreCase("blume"))
        return "flower";
        if(word.equalsIgnoreCase("Hallo") || word.equalsIgnoreCase("hola"))
        return "hello";

        return null;
    };

}
