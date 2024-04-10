package assign09;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Timer extends TimerTemplate {
    Random random = new Random();
    private HashTable<StudentBadHash, Double> hashTableBad;
    private HashTable<StudentMediumHash, Double> hashTableMedium;
    private HashTable<StudentGoodHash, Double> hashTableGood;
    private HashMap<String, Double> javaHash;
    private ArrayList<String> temp = new ArrayList<>();

    /**
     * Create a timer
     *
     * @param problemSizes array of N's to use
     * @param timesToLoop  number of times to repeat the tests
     */
    public Timer(int[] problemSizes, int timesToLoop) {
        super(problemSizes, timesToLoop);
        hashTableBad = new HashTable<>();
        hashTableMedium = new HashTable<>();
        hashTableGood = new HashTable<>();
        javaHash = new HashMap<>();
    }

    @Override
    protected void setup(int n) {
//        javaHash.clear();
        hashTableBad.clear();
//        hashTableMedium.clear();
//        hashTableGood.clear();
//        temp.clear();
        for (int i = 0; i < n; i++) {
            int uid = i;
            String firstName = "First " + i;
            String lastName = "Last" + i;
//            temp.add(firstName+lastName);
            hashTableBad.put(new StudentBadHash(uid, firstName, lastName), 4.2);
//            hashTableMedium.put(new StudentMediumHash(uid, firstName, lastName), 4.2);
//            hashTableGood.put(new StudentGoodHash(uid, firstName, lastName), 4.2);
//            javaHash.put(firstName+lastName, 4.2);
        }
        System.out.println(n + ", " + hashTableBad.getCollisions());
//        System.out.println(n + ", " + javaHash);
    }

    @Override
    protected void timingIteration(int n) {
//            hashTableBad.get(temp.get(random.nextInt(n)));

//            hashTableMedium.get(temp.get(random.nextInt(n)));

//            hashTableGood.containsKey(temp.get(random.nextInt(n)));
//        javaHash.containsKey(temp.get(random.nextInt(n)));
    }
    @Override
    protected void compensationIteration(int n) {

    }


    public static void main(String[] args) {
        int[] problemSizes = new int[20];
        for (int i = 0; i < problemSizes.length; i++) {
            problemSizes[i] = 10000 + i * 10000;
        }
//        problemSizes[0] = 10000;

        int timesToLoop = 100;
        var timer = new Timer(problemSizes, timesToLoop);
        timer.setup(10);
        var results = timer.run();



        try {
            FileWriter csvWriter = new FileWriter("Result.csv");
            csvWriter.write("n, time\n");

            for (Result result : results) {
                String line = result.n() + ", " + result.avgNanoSecs() + "\n";
                csvWriter.write(line);
            }

            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
