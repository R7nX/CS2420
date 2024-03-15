package assign07;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Timer extends TimerTemplate {
    Random random = new Random();
//    private LinkedListStack<Integer> singlyList;
    /**
     * Create a timer
     *
     * @param problemSizes array of N's to use
     * @param timesToLoop  number of times to repeat the tests
     */
    public Timer(int[] problemSizes, int timesToLoop) {
        super(problemSizes, timesToLoop);
    }

    @Override
    protected void setup(int n) {

    }

    @Override
    protected void timingIteration(int n) {
    }
    @Override
    protected void compensationIteration(int n) {

    }

    public static void main(String[] args) {
        int[] problemSizes = new int[20];
        for (int i = 0; i < problemSizes.length; i++) {
            problemSizes[i] = 10000 + i * 10000;
        }

        int timesToLoop = 800;
        var timer = new Timer(problemSizes, timesToLoop);
        timer.setup(10);
        var results = timer.run();

        try {
            FileWriter csvWriter = new FileWriter("Result.csv");
            csvWriter.write("n, time\n");

            for (Result result : results) {
                String line = result.n() + ", " + result.avgNanoSecs()/2 + "\n";
                csvWriter.write(line);
            }

            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
