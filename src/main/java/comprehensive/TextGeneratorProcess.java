package comprehensive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextGeneratorProcess {
    private MarkovChain model;

    public TextGeneratorProcess(String path, String seed, int K, String opts) {
        model = new MarkovChain(seed);
        toMarkovChain(path);
        System.out.println(model.getResult());
    }
    // The method transfer the txt file to markov chain class
    public void toMarkovChain(String file) {
        // read file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // go through each line
            while ((line = reader.readLine()) != null) {
                // iterate and add each word
                String[] words = line.split("[^\\p{L}']+");

                for (int i = 0; i < words.length; i++) {
                    if (words[i].length() > 0) {
                        // add
                        model.add(words[i]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
