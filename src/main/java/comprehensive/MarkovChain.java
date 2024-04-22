package comprehensive;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MarkovChain {
    private Map<String, Map<String, Integer>> model;
    private Map<String, Double> result;
    private String curr;
    private String pre;
    private String seed;
    private int size;

    public MarkovChain(String seed) {
        this.model = new HashMap<>();
        this.result = new HashMap<>();
        this.pre = null;
        this.seed = seed;
        this.size = 0;
    }

    public void add(String value) {
        this.curr = value;
        if (pre == null) {
            model.put(curr, new HashMap<>());
        } else {
            if (!(model.containsKey(curr))) {
                model.put(curr, new HashMap<>());
                updateFreq(pre, curr);
            } else {
                updateFreq(pre, curr);
            }
        }
        size++;
        pre = curr;
    }

    private void updateFreq(String preValue, String currValue) {
        if (model.get(preValue).get(currValue) == null){
            model.get(preValue).put(currValue, 1);
        }
        else {
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

    public Map<String, Double> getResult() {
        result.clear();
        for (Map.Entry<String, Integer> entries : model.get(seed).entrySet())
            result.put(entries.getKey(), freqCalculation(seed, entries.getKey()));

        Map<String, Double> sortedResult = new TreeMap<>(new ValueComparator(result));
        sortedResult.putAll(result);
        return sortedResult;
    }

    public class ValueComparator implements Comparator<String> {
        Map<String, Double> map;

        public ValueComparator(Map<String, Double> map) {
            this.map = map;
        }

        @Override
        public int compare(String o1, String o2) {
            if (map.get(o1).compareTo(map.get(o2)) > 0){
                return -1;
            }
            else {
                return 1;
            }
        }
    }

}
