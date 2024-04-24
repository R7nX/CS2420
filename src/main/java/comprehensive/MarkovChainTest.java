package comprehensive;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MarkovChainTest {
    MarkovChain chain;

    @BeforeEach
    void setUp() throws Exception {
        chain = new MarkovChain("one");

    }

    //predict sequence requiring a resetting to seed (0/5)
    @Test
    void testResettingToSeed() {
        chain.add("hello");
        chain.add("world");

        assertEquals("world", chain.getNext("hello"));
        // reset to the seed
        assertEquals("hello", chain.getNext("world"));
    }

    //Select best option of 1 possibility (0/5)
    @Test
    void testBestOptionOf1Possiblity() {
        // Add some sample data
        MarkovChain markovChain = new MarkovChain("one");

        markovChain.add("hello");
        markovChain.add("world");
        markovChain.add("good");
        markovChain.add("morning");
        markovChain.add("neighbor");

        // Get next words for the seed "hello"
        Set<Map.Entry<String, Double>> nextWords = markovChain.getNextWords("hello");

        // Ensure there's only one next word
        assertEquals(1, nextWords.size());

        // Extract the single entry from the set
        Map.Entry<String, Double> entry = nextWords.iterator().next();

        // Check if the next word is "world"
        assertEquals("world", entry.getKey());
    }

    // Long Sequence (0/5)
    @Test
    void testLongSequence() {
        MarkovChain chain = new MarkovChain("one");
        chain.add("a");
        chain.add("r");
        chain.add("a");
        chain.add("g");
        chain.add("a");
        chain.add("r");

        String seedWord = "a";
        String generatedText = seedWord;
        int wordsToGenerate = 5;

        // Generate text using the Markov chain
        for (int i = 0; i < wordsToGenerate - 1; i++) {
            String nextWord = chain.getNext(seedWord);
            if (nextWord != null) {
                generatedText += " " + nextWord;
                seedWord = nextWord;
            } else {
                // No possible next word, reset to seed
                seedWord = "a";
            }
        }

        // Verify the generated text matches the expected output
        assertEquals("a r a r a", generatedText);
        assertEquals(5, generatedText.split(" ").length);
    }

    // random prediction has possible result (0/5)
    @Test
    void testRandomPrediction() {
        MarkovChain chain = new MarkovChain("one");

        chain.add("hello");
        chain.add("world");
        chain.add("good");
        chain.add("morning");
        chain.add("neighbor");

        Random rnd = new Random();
        String seedWord = "hello";
        String generatedText = seedWord;

        for (int i = 0; i < rnd.nextInt(100); i++) {
            String nextWord = chain.getNext(seedWord);
            if (nextWord != null) {
                generatedText += " " + nextWord;
                seedWord = nextWord;
            } else {
                break; // Exit loop if nextWord is null
            }
        }
        // Check if generated text contains more than just the seed word
        assertTrue(generatedText.length() > 1);
        // You can add more assertions to verify the format/content of the generated
        // text
    }

    // Select more options than (0/5)
    @Test
    void testMoreOptions() {
        MarkovChain markovChain = new MarkovChain("one");

        markovChain.add("hello");
        markovChain.add("world");
        markovChain.add("hello");
        markovChain.add("good");
        markovChain.add("hello");
        markovChain.add("morning");
        markovChain.add("neighbor");

        // Get next words for the seed "hello"
        Set<Map.Entry<String, Double>> nextWords = markovChain.getNextWords("hello");

        // Ensure that all expected possibilities are present
        assertTrue(nextWords.size() >= 2); // At least 2 possibilities expected
        assertTrue(nextWords.stream().anyMatch(entry -> entry.getKey().equals("world")));
        assertTrue(nextWords.stream().anyMatch(entry -> entry.getKey().equals("good")));
        assertTrue(nextWords.stream().anyMatch(entry -> entry.getKey().equals("morning")));
        assertTrue(nextWords.stream().anyMatch(entry -> entry.getKey().equals("neighbor")));
    }

    @Test
    void testWordsFrequency() {

        MarkovChain chain = new MarkovChain("all");
        chain.add("hello");
        chain.add("world");

        // Get word frequencies from the Markov chain
        Set<Map.Entry<String, Double>> frequenciesMap = chain.getNextWords("world");

        // Check if any frequency value is null or unexpected
        for (Map.Entry<String, Double> entry : frequenciesMap) {
            assertNotNull(entry.getValue(), "Frequency should not be null");
            assertNotEquals(5000, entry.getValue(), "Frequency should not be double the expected value");
        }
    }

    // Words frequency used when picking words randomly (0/5)

    // When theres no word make seed the next word
    @Test
    void testNoNextWordReturnSeedOptsAll() {
        MarkovChain chain = new MarkovChain("all");

        // Add some data to the Markov chain
        chain.add("hello");
        chain.add("world");

        String seedWord = "hello";
        String generatedText = seedWord;
        int wordsToGenerate = 7;
        // Generate text using the Markov chain
        for (int i = 0; i < wordsToGenerate; i++) {
            String nextWord = chain.getNext(seedWord);
            if (nextWord != null) {
                generatedText += " " + nextWord;
                seedWord = nextWord;
            } else {
                // No possible next word, use the seed word again
                generatedText += " " + seedWord;
            }
        }

        // Verify the generated text matches the expected output
        assertEquals("hello world hello world hello world hello", generatedText);
    }

    @Test
    void testNoNextWordReturnSeedOptsOne() {
        MarkovChain chain = new MarkovChain("one");

        // Add some data to the Markov chain
        chain.add("hello");
        chain.add("world");

        String seedWord = "hello";
        String generatedText = seedWord;
        int wordsToGenerate = 7;
        // Generate text using the Markov chain
        for (int i = 0; i < wordsToGenerate; i++) {
            String nextWord = chain.getNext(seedWord);
            if (nextWord != null) {
                generatedText += " " + nextWord;
                seedWord = nextWord;
            } else {
                // No possible next word, use the seed word again
                generatedText += " " + seedWord;
            }
        }

        // Verify the generated text matches the expected output
        assertEquals("hello world hello world hello world hello", generatedText);
    }


}