package assign07;


import javax.crypto.spec.PSource;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Timer extends TimerTemplate {
    Random random = new Random();
    public int vertexCount;
    ArrayList<String> sources = new ArrayList<>();
    ArrayList<String> destination = new ArrayList<>();
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
        vertexCount = n;
        // TopologicalSort
//        PrintWriter out = null;
//        try {
//            out = new PrintWriter("input.txt");
//        } catch (IOException e) {
//            System.out.println(e);
//            return; // Exit the program if unable to create the file
//        }
//
//        Random rng = new Random();
//
//        // Always construct a digraph
//        String edgeOp = "->";
//        out.println("digraph G {");
//
//        // generate a list of vertices
//        String[] vertex = new String[vertexCount];
//        for (int i = 0; i < vertexCount; i++) {
//            vertex[i] = "v" + i;
//        }
//
//        // randomly connect the vertices using 2 * |V| edges
//        for(int i = 0; i < vertexCount - 1; i++)
//            out.println("\t" + vertex[i] + "->" + vertex[i + 1 + rng.nextInt(vertexCount - (i + 1))]);
//
//        out.println("}");
//        out.close(); // Close the PrintWriter to flush and release resources

        // Digraph
//        PrintWriter out = null;
//        try {
//            out = new PrintWriter("input.txt");
//        } catch (IOException e) {
//            System.out.println(e);
//            return; // Exit the program if unable to create the file
//        }
//
//        Random rng = new Random();
//
//        // Always construct a digraph
//        String edgeOp = "->";
//        out.println("digraph G {");
//
//        // generate a list of vertices
//        String[] vertex = new String[vertexCount];
//        for (int i = 0; i < vertexCount; i++) {
//            vertex[i] = "v" + i;
//        }
//
//        // randomly connect the vertices using 2 * |V| edges
//        for (int i = 0; i < 2 * vertexCount; i++) {
//            out.println("\t" + vertex[rng.nextInt(vertexCount)] + edgeOp + vertex[rng.nextInt(vertexCount)]);
//        }
//
//        out.println("}");
//        out.close(); // Close the PrintWriter to flush and release resources

        PrintWriter out = null;
        try {
            out = new PrintWriter("input.txt");
        } catch (IOException e) {
            System.out.println(e);
            return; // Exit the program if unable to create the file
        }

        Random rng = new Random();

        // Always construct a digraph
        String edgeOp = "->";
        out.println("digraph G {");

        // generate a list of vertices
        String[] vertex = new String[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            vertex[i] = "v" + i;
        }

        // connect the vertices in a path to ensure a connected graph
        for (int i = 0; i < vertexCount - 1; i++) {
            out.println("\t" + vertex[i] + edgeOp + vertex[i + 1]);
        }

        // randomly connect the vertices using additional edges
        for (int i = 0; i < vertexCount; i++) {
            out.println("\t" + vertex[rng.nextInt(vertexCount)] + edgeOp + vertex[rng.nextInt(vertexCount)]);
        }

        out.println("}");
        out.close(); // Close the PrintWriter to flush and release resources


        GraphUtility.buildListsFromDot("input.txt", sources, destination);
    }

    @Override
    protected void timingIteration(int n) {
//        GraphUtility.areConnected(sources, destination, sources.get(random.nextInt(sources.size())), destination.get(random.nextInt(destination.size())));
        GraphUtility.shortestPath(sources, destination, sources.get(random.nextInt(sources.size())), destination.get(random.nextInt(destination.size())));
//        GraphUtility.sort(sources,destination);
    }

    @Override
    protected void compensationIteration(int n) {

    }

    public static void main(String[] args) {
        int[] problemSizes = new int[11];
        for (int i = 0; i < problemSizes.length; i++) {
            problemSizes[i] = 1000 + i * 100;
        }

        int timesToLoop = 1500;
        var timer = new Timer(problemSizes, timesToLoop);
        timer.setup(10);
        var results = timer.run();

        try {
            FileWriter csvWriter = new FileWriter("Result.csv");
            csvWriter.write("n, time\n");

            for (Result result : results) {
                String line = result.n() + ", " + result.avgNanoSecs() / 2 + "\n";
                csvWriter.write(line);
            }

            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
