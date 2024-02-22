package assign05;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TimerAssign05 extends TimerTemplate {
	Random random = new Random();
	private ArrayList<Integer> num;

	/**
	 * Create a timer
	 *
	 * @param problemSizes array of N's to use
	 * @param timesToLoop  number of times to repeat the tests
	 */
	public TimerAssign05(int[] problemSizes, int timesToLoop) {
		super(problemSizes, timesToLoop);
		num = new ArrayList<>();
	}

	@Override
	protected void setup(int n) {
		num.clear();
	 	num = ArrayListSorter.generatePermuted(n);
	}

	@Override
	protected void timingIteration(int n) {
		Integer[] arr = num.toArray(new Integer[0]); 
		ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(arr)); 
//	    ArrayListSorter.quicksort(arrayList);
		ArrayListSorter.mergesort(arrayList);
	}
	@Override
	protected void compensationIteration(int n) {

	}

	public static void main(String[] args) {
		int[] problemSizes = new int[10];
		for (int i = 0; i < problemSizes.length; i++) {
			problemSizes[i] = (i + 1) * 1000;
		}

		int timesToLoop = 1000;
		var timer = new TimerAssign05(problemSizes, timesToLoop);
		timer.setup(10);
		var results = timer.run();

		for (var result : results) {
			System.out.println(result.n() + ", " + result.avgNanoSecs());
		}
		try {
			FileWriter csvWriter = new FileWriter("Results.csv");
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