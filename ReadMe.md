This application contains a SpringBoot application and exposes 2 methods 

1. @GetMapping("/addWord/{sentence}")
2. @GetMapping("/count/{countWord}")

The first endpoint /addWord/{sentence} expects a sentence/word to be sent. The Setence needs to be seperated by " " for multiple words. If the method is able to insert 
the word successfully, returns a string message with HTTPStatus.OK

The second endPoint /count/{countWord} returns the count of the word, if it cant find any occurences will return 0.

For various use cases covered, the test class WordControllerTest( src/test/java) folder can be looked.

<b>To Run this application</b>

1.  Clone this repo to your IDE and run SpringBootApplication.java class, this should start the spring boot application on port 8080

<B>To TEST this application</b>
1.  http://localhost:8080/addWord/{YOUR SENTENCE} where {Your sentence} could be any valid sequence of Words, for e.g http://localhost:8080/addWord/this application works
2.  To get the count of words use http://localhost:8080/count/{WORD} where {WORD} needs to be the word whose occurnces needs to be found for e.g 
http://localhost:8080/count/application should return 1 (provided the sentence in call 1 has word application)

3. Use mvn test to run the test cases and test the application



