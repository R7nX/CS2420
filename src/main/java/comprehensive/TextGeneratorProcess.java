package comprehensive;

import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class TextGeneratorProcess {
    private MarkovChain model;

    public TextGeneratorProcess(String path, String seed, int K, String opts) {
        toMarkovChain(path, seed, K, opts);

        if (opts == null) {
            getNextWords(K, seed);
        } else {
            getNextWordOpts(seed, K);
        }
    }

    // The method transfer the txt file to markov chain class
    private void toMarkovChain(String file, String seed, int K, String opts) {
        model = new MarkovChain(opts);
        // read file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // go through each line
            while ((line = reader.readLine()) != null) {
                // iterate and add each word
                String[] words = line.split("[^\\p{L}']+");

                for (String word : words) {
                    if (!word.isEmpty()) {
                        // add
                        model.add(word);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getNextWords(int K, String seed) {
        StringBuilder resultBuilder = new StringBuilder();
        int count = 1;

        Set<Map.Entry<String, Double>> entry;
        entry = model.getNextWords(seed);
        for (Map.Entry<String, Double> mapEntry : entry) {
            // Append key-value pair to StringBuilder
            if (count == K || mapEntry.getKey() == null) {
                break;
            }
            resultBuilder.append(mapEntry.getKey()).append(" "); // Add newline for better readability, adjust as needed
            count++;
        }
        System.out.println(resultBuilder.toString().trim());
    }


    private void getNextWordOpts(String seed, int count) {
        StringBuilder resultBuilder = new StringBuilder();

        resultBuilder.append(seed).append(" "); // Append seed as the first word

        String currentWord = seed;
        for (int i = 0; i < count; i++) {
            String nextWord = model.getNext(currentWord);
            if (nextWord != null) {
                resultBuilder.append(nextWord).append(" ");
                currentWord = nextWord;
            } else {
                break; // If there are no more words, break the loop
            }
        }
        System.out.println(resultBuilder.toString().trim());
    }


}

