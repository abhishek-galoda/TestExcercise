package com.syncheron.controller;

import com.syncheron.model.Word;
import com.syncheron.service.Translator;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WordControllerTest {


    @Mock
    Word word;

    @Mock
    Translator translator;

    @InjectMocks
    WordController wordController ;

    @Test
    public void testInvalidWordsAreNotAdded() {

    wordController.addWord("123 456 789");
    assertTrue(word.getWordCounterMap().size()==0);

    wordController.addWord("123");
    assertTrue(word.getWordCounterMap().size()==0);

    wordController.addWord("***@&@*@");
    assertTrue(word.getWordCounterMap().size()==0);

    wordController.addWord("123ABC");
    assertTrue(word.getWordCounterMap().size()==0);

    wordController.addWord("123ABC DF£$ 9");
    assertTrue(word.getWordCounterMap().size()==0);

    }

    @Test(expected = ResponseStatusException.class)
    public void testThrowsException(){
        wordController.addWord(null);

    }


    @Test
    public void testValidWordsAreAdded() {

    when(word.getWordCounterMap()).thenCallRealMethod();
    when(translator.translate(anyString())).thenCallRealMethod();

    //Test 3 words are added
    wordController.addWord("ABC CDE FGH");
    assertTrue(word.getWordCounterMap().size()==3);

    //Test 2 another valid words are added, taking the count to 5
    wordController.addWord("ABC123 XY Z");
    assertTrue(word.getWordCounterMap().size()==5);

    //Test Duplicate word ABC is not added again
    wordController.addWord("ABC CDE!@££");
    assertTrue(word.getWordCounterMap().size()==5);

    //Test lowercase word word ABC is not added again
    wordController.addWord("abc");
    assertEquals(word.getWordCounterMap().size(),5);

    //Test Only 1 word is added,when same word is added in different language
    wordController.addWord("flower blume flor");
    assertEquals(word.getWordCounterMap().size(),6);

    //Test Only 1 word is added,when same word is added in different language
    wordController.addWord("hello HOLA Hallo");
    assertEquals(word.getWordCounterMap().size(),7);

    }



    @Test
    public void countWord() {

        word.setWordCounterMap(new HashMap<>());
        when(word.getWordCounterMap()).thenCallRealMethod();
        when(translator.translate(anyString())).thenCallRealMethod();

        //Test that count is 3 for same word, even if it is different language
        wordController.addWord("flower blume flor");
        assertEquals(wordController.countWord("flower"),3);

        //Test that count is 3 for same word, even if it is different language
        wordController.addWord("hello hola hallo");
        assertEquals(wordController.countWord("hello"),3);

        //Test that count is increased for  same word, even if it is different language
        wordController.addWord("hello hola hallo");
        assertEquals(wordController.countWord("hello"),6);

        assertEquals(wordController.countWord("thisdoesntexist"),0);
        assertEquals(wordController.countWord(""),0);
        assertEquals(wordController.countWord("!@@£"),0);
        assertEquals(wordController.countWord("hello1"),0);


    }

    @BeforeEach
    public  void before() {
        word.setWordCounterMap(new HashMap<>());
    }


}