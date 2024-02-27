package lab02;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RemoveFrontTimer extends TimerTemplates {
    ArrayList<Integer> myList = new ArrayList<>();

    /**
     * Create a timer
     *
     * @param problemSizes array of N's to use
     * @param timesToLoop  number of times to repeat the tests
     */
    public RemoveFrontTimer(int[] problemSizes, int timesToLoop) {
        super(problemSizes, timesToLoop);
    }

    @Override
    protected void setup(int n) {
        for (int i = 0; i < n; i++) {
            myList.add(i);
        }
    }

    @Override
    protected void timingIteration(int n) {
        myList.remove(0);
        myList.add(n);
    }

    @Override
    protected void compensationIteration(int n) {
        myList.set(myList.size() - 1, n);
    }

    public static void main(String[] args) {

        ArrayList<Integer> ns = new ArrayList<>();
        for (double n = 100; n < 1000000; n *= 1.5) {
            ns.add((int) n);
        }

        //convert to int[]
        int[] problemSizes = new int[ns.size()];
        for (int i = 0; i < problemSizes.length; i++) {
            problemSizes[i] = ns.get(i);
        }

        var timer = new RemoveFrontTimer(problemSizes, 10);
        var results = timer.run();
        System.out.println("n, time");
        for (var result : results) {
            System.out.println(result.n() + ", " + result.avgNanoSecs());
        }

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
    }
}

