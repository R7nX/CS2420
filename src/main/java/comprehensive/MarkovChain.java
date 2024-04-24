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
        result.clear();
        if (model.get(seed) != null) {
            if (opts.equals("one")) {
                for (String post : model.get(seed).keySet()) {
                    result.put(post, freqCalculation(seed, post));
                }
                Map<String, Double> sortedResult = new TreeMap<>(new ValueComparator(result));
                sortedResult.putAll(result);
                if (!sortedResult.isEmpty()) {
                    return sortedResult.entrySet().iterator().next().getKey();
                }
            } else if (opts.equals("all")) {
                Random random = new Random();

                int sum = 1;
                for (Map.Entry<String, Integer> word : model.get(seed).entrySet()) {
                    sum += word.getValue();
                }
                int rnd = random.nextInt(sum);
                for (Map.Entry<String, Integer> entry: model.get(seed).entrySet()){
                    rnd -= entry.getValue();
                    if (rnd <= 0)
                        return entry.getKey();
                }
            }
        }
        return null;
    }

    public Set<Map.Entry<String, Double>> getNextWords(String seed) {
        result.clear();
        if (model.get(seed) != null) {
            for (String post : model.get(seed).keySet()) {
                // Calculate the frequency and put it into the result map
                result.put(post, freqCalculation(seed, post));
            }
            // If opts is null or "one", return the word with the highest frequency
            Map<String, Double> sortedResult = new TreeMap<>(new ValueComparator(result));
            sortedResult.putAll(result);
            if (!sortedResult.isEmpty()) {
                return sortedResult.entrySet();
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
            int comparison = Double.compare(map.get(o2), map.get(o1));
            if (comparison != 0) {
                // If the values are different, return the comparison result
                return comparison;
            } else {
                // If the values are equal, compare by the lexicographical ordering of the keys
                return o1.compareTo(o2);
            }
        }
    }

}
