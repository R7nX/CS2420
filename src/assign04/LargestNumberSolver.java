package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

/**
 * The LargestNumberSolver class provides methods for solving problems related to finding the largest number
 * from a list of integers, performing operations on lists of integers, and reading integer lists from a file.
 *
 * @author Bao Phuc Do, Khang Nguyen
 * @version 7 Feb 2024
 */
public class LargestNumberSolver {

    /**
     * Sorts an array using the insertion sort algorithm.
     *
     * @param arr The array to be sorted.
     * @param cmp The comparator to determine the order of the elements.
     * @param <T> The type of elements in the array.
     */
    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            T key = arr[i];
            int j = i - 1;

            while (j >= 0 && cmp.compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * Finds the largest number by concatenating and comparing integers in a specific order.
     *
     * @param arr The array of integers to find the largest number from.
     * @return The largest number as a BigInteger.
     */
    public static BigInteger findLargestNumber(Integer[] arr) {
        Comparator<Integer> cmp = (a, b) -> {
            String ab = a.toString() + b.toString();
            String ba = b.toString() + a.toString();
            return ba.compareTo(ab);
        };

        Integer[] sortedArr = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++)
            sortedArr[i] = arr[i];

        insertionSort(sortedArr, cmp);

        StringBuilder result = new StringBuilder();
        for (Integer num : sortedArr) {
            result.append(num);
        }

        return new BigInteger(result.toString());
    }

    /**
     * Finds the largest integer from the array, throwing an OutOfRangeException if it exceeds Integer.MAX_VALUE.
     *
     * @param arr The array of integers to find the largest integer from.
     * @return The largest integer value.
     * @throws OutOfRangeException If the result exceeds Integer.MAX_VALUE.
     */
    public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
        BigInteger result = findLargestNumber(arr);
        if (result.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0)
            throw new OutOfRangeException("int");

        return result.intValue();
    }

    /**
     * Finds the largest long from the array, throwing an OutOfRangeException if it exceeds Long.MAX_VALUE.
     *
     * @param arr The array of integers to find the largest long from.
     * @return The largest long value.
     * @throws OutOfRangeException If the result exceeds Long.MAX_VALUE.
     */
    public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
        BigInteger result = findLargestNumber(arr);
        if (result.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0) {
            throw new OutOfRangeException("long");
        }
        return result.longValue();

    }

    /**
     * Calculates the sum of the largest numbers from each array in the list.
     *
     * @param list The list of arrays of integers.
     * @return The sum of the largest numbers as a BigInteger.
     */
    public static BigInteger sum(List<Integer[]> list) {
        BigInteger totalSum = BigInteger.ZERO;
        for (Integer[] arr : list) {
            totalSum = totalSum.add(findLargestNumber(arr));
        }
        return totalSum;
    }

    /**
     * Finds the kth largest array in the list based on the largest number comparison.
     *
     * @param list The list of arrays of integers.
     * @param k    The index of the largest array to retrieve.
     * @return The kth largest array.
     * @throws IllegalArgumentException If the value of k is invalid.
     */
    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
        if (k < 0 || k >= list.size()) {
            throw new IllegalArgumentException("Invalid value of k");
        }

        Comparator<Integer[]> cmp = (a, b) -> findLargestNumber(b).compareTo(findLargestNumber(a));

        Integer[][] sortedList = list.toArray(new Integer[list.size()][]);
        insertionSort(sortedList, cmp);
        return sortedList[k];
    }

    /**
     * Reads a file containing space-separated integers on each line and returns a list of integer arrays.
     *
     * @param filename The name of the file to read.
     * @return A list of integer arrays read from the file.
     */
    public static List<Integer[]> readFile(String filename) {
        List<Integer[]> resultList = new ArrayList<>();

        try {
            Scanner input = new Scanner(new File(filename));

            while (input.hasNext()) {
                String line = input.nextLine();
                String[] number = line.split(" ");
                Integer[] arr = new Integer[number.length];
                for (int i = 0; i < number.length; i++) {
                    arr[i] = Integer.parseInt(number[i]);
                }
                resultList.add(arr);
            }

        } catch (FileNotFoundException e) {
            return resultList;
        }
        return resultList;
    }
}
