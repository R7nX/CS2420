package assign03;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Timer extends TimerTemplate {
    private PriorityQueue<Integer> priorityQueue;
    Random random = new Random();


    /**
     * Create a timer
     *
     * @param problemSizes array of N's to use
     * @param timesToLoop  number of times to repeat the tests
     */
    public Timer(int[] problemSizes, int timesToLoop) {
        super(problemSizes, timesToLoop);
        this.priorityQueue = new SimplePriorityQueue<>();
    }

    @Override
    protected void setup(int n) {
        this.priorityQueue.clear();
        for (int i = 0; i < n; i++) {
            priorityQueue.insert(random.nextInt(n));
        }
    }

    @Override
    protected void timingIteration(int n) {
        priorityQueue.insert(random.nextInt(n));
        priorityQueue.deleteMax();
    }

    @Override
    protected void compensationIteration(int n) {
        priorityQueue.findMax();
    }

    public static void main(String[] args) {
        ArrayList<Integer> ns = new ArrayList<>();
        for (double n = 100000; n < 2000000; n += 100000) {
            ns.add((int) n);
        }

        int[] problemSize = new int[ns.size()];
        for (int i = 0; i < problemSize.length; i++) {
            problemSize[i] = ns.get(i);
        }

        var timer = new Timer(problemSize, 1000);
        var results = timer.run();

        for (var result : results) {
            System.out.println(result.n() + ", " + result.avgNanoSecs());
        }

        try {
            FileWriter csvWriter = new FileWriter("findMaxResults2.csv");
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

//    /**
//     * Create a timer
//     *
//     * @param problemSizes array of N's to use
//     * @param timesToLoop  number of times to repeat the tests
//     */
//    public Timer(int[] problemSizes, int timesToLoop) {
//        super(problemSizes, timesToLoop);
//        this.priorityQueue = new SimplePriorityQueue<>();
//    }
//
//    @Override
//    protected void setup(int n) {
//        Random random = new Random();
//        priorityQueue.clear();
//        for (int i = 0; i < 1000; i++) {
//            priorityQueue.insert(random.nextInt());
//        }
//    }
//
//    @Override
//    protected void timingIteration(int n) {
//        priorityQueue.findMax();
//    }
//
//    @Override
//    protected void compensationIteration(int n) {
//    }
//
//    public static void main(String[] args) {
//        int[] problemSize = new int[20];
//        for (int i = 0; i < 20; i++) {
//            problemSize[i] = (i + 1) * 100000;
//        }
//
//        var timer = new Timer(problemSize, 10000);
//        var results = timer.run();
//
//        for (var result : results) {
//            System.out.println(result.n() + ", " + result.avgNanoSecs());
//        }
//
//        try {
//            FileWriter csvWriter = new FileWriter("findMaxResults1.csv");
//            csvWriter.write("n, time\n");
//
//            for (Result result : results) {
//                String line = result.n() + ", " + result.avgNanoSecs() + "\n";
//                csvWriter.write(line);
//            }
//
//            csvWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
