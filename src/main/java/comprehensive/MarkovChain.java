package comprehensive;

import java.util.*;

public class MarkovChain {
    private Map<String, Map<String, Integer>> model;
    private Map<String, Double> result;
    private String curr;
    private String pre;
    private String opts;

    public MarkovChain(String opts) {
        this.model = new HashMap<>();
        this.result = new HashMap<>();
        this.opts = opts;
        this.pre = null;
    }

    public void add(String value) {
        this.curr = value;
        if (pre == null) {
            model.put(curr, new HashMap<>());
        } else {
            if (!(model.containsKey(curr))) {
                model.put(curr, new HashMap<>());
            }
            updateFreq(pre, curr);
        }
        pre = curr;

    }

    private void updateFreq(String preValue, String currValue) {
        if (model.get(preValue).get(currValue) == null) {
            model.get(preValue).put(currValue, 1);
        } else {
            int freq = model.get(preValue).get(currValue);
            model.get(preValue).put(currValue, freq + 1);
        }
    }

    private double freqCalculation(String value, String post) {
        double sum = 0;
        for (Map.Entry<String, Integer> word : model.get(value).entrySet()) {
            sum += word.getValue();
        }
        return model.get(value).get(post) / sum;
    }

    public String getNext(String seed) {
        if (model.get(seed) != null) {
            for (String post : model.get(seed).keySet()) {
                result.put(post, freqCalculation(seed, post));
            }
            if (opts.equals("one")) {
                Map<String, Double> sortedResult = new TreeMap<>(new ValueComparator(result));
                sortedResult.putAll(result);
                if (!sortedResult.isEmpty()) {
                    return sortedResult.entrySet().iterator().next().getKey();
                }
            } else if (opts.equals("all")) {
                List<String> keys = new ArrayList<>(result.keySet());
                Random random = new Random();
                String randomKey = keys.get(random.nextInt(keys.size()));
                Double randomValue = result.get(randomKey);

                Map<String, Double> randomEntry = new HashMap<>();
                randomEntry.put(randomKey, randomValue);
                return randomEntry.entrySet().iterator().next().getKey();
            }
        }
        return null;
    }

    public Set<Map.Entry<String, Double>> getNextWords(String seed) {
        if (model.get(seed) != null) {
            for (String post : model.get(seed).keySet()) {
                // Calculate the frequency and put it into the result map
                result.put(post, freqCalculation(seed, post));
            }
            if (opts == null) {
                // If opts is null or "one", return the word with the highest frequency
                Map<String, Double> sortedResult = new TreeMap<>(new ValueComparator(result));
                sortedResult.putAll(result);
                if (!sortedResult.isEmpty()) {
                    return sortedResult.entrySet();
                }
            }
        }
        return null;
    }

    public static class ValueComparator implements Comparator<String> {
        Map<String, Double> map;

        public ValueComparator(Map<String, Double> map) {
            this.map = map;
        }

        @Override
        public int compare(String o1, String o2) {
            if (map.get(o1).compareTo(map.get(o2)) > 0) {
                return -1;
            } else {
                return 1;
            }
        }
    }

}
