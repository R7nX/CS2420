package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ArrayListSorter {
    private static final int INSERTION_SORT_THRESHOLD = 1;

    public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr) {
        ArrayList<T> temp = new ArrayList<>(arr);// I modified here
        for (int i = 0; i < arr.size(); i++){
            temp.add(null);
        }
        mergesort(arr, temp, 0, arr.size() - 1);
    }

    private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr, ArrayList<T> temp, int left, int right) {
        if (left >= right)
            return;
        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(arr, left, right);
        }
        int mid = left + (right - left) / 2;
        mergesort(arr, temp, left, mid);
        mergesort(arr, temp, mid + 1, right);
        merge(arr, temp, left, mid, right);

    }


    private static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, ArrayList<T> temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp.set(i, arr.get(i));
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp.get(i).compareTo(temp.get(j)) <= 0) {
                arr.set(k, temp.get(i));
                i++;
            } else {
                arr.set(k, temp.get(j));
                j++;
            }
            k++;
        }

        // Add all the remaining values in the left subarray
        while (i <= mid) {
            arr.set(k, temp.get(i));
            i++;
            k++;
        }


        // Add all the remaining values in the right subarray
        while (j <= right){
            arr.set(k, temp.get(j));
            j++;
            k++;
        }
    }

    public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr) {
        quicksort(arr, 0, arr.size() - 1);
    }

    private static <T extends Comparable<? super T>> void quicksort(
            ArrayList<T> arr, int low, int high) {
        if (low < high) {

//                int pivotIndex = low + (high - low) / 2;
                int pivotIndex = getPivotMedianOfThree(low, high, arr);
                int partitionIndex = partition(arr, low, high, pivotIndex);
                quicksort(arr, low, partitionIndex - 1);
                quicksort(arr, partitionIndex + 1, high);
            }
    }

    private static <T extends Comparable<? super T>> int partition(ArrayList<T> arr, int low, int high, int pivotIndex) {
        T pivotValue = arr.get(pivotIndex);
        Collections.swap(arr, pivotIndex, high);
        int i = low;
        for (int j = low; j < high; j++) {
            if (arr.get(j).compareTo(pivotValue) <= 0) {
                Collections.swap(arr, i, j);
                i++;
            }
        }
        Collections.swap(arr, i, high);
        return i;
    }

    private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            T key = arr.get(i);
            int j = i - 1;
            while (j >= left && arr.get(j).compareTo(key) > 0) {
                arr.set(j + 1, arr.get(j));
                j--;
            }
            arr.set(j + 1, key);
        }
    }

    private static <T extends Comparable<? super T>> int getPivotMedianOfThree(int low, int high, ArrayList<T> arr) {
        int mid = low + (high - low) / 2;
        T lowValue = arr.get(low);
        T midValue = arr.get(mid);
        T highValue = arr.get(high);

        if (lowValue.compareTo(midValue) > 0) {
            if (midValue.compareTo(highValue) > 0) {
                return mid;
            } else if (lowValue.compareTo(highValue) > 0) {
                return high;
            } else {
                return low;
            }
        } else {
            if (lowValue.compareTo(highValue) > 0) {
                return low;
            } else if (midValue.compareTo(highValue) > 0) {
                return high;
            } else {
                return mid;
            }
        }
    }
    public static ArrayList<Integer> generateAscending(int size) {
        ArrayList<Integer> ascendingList = new ArrayList<>(size);
        for (int i = 1; i <= size; i++) {
            ascendingList.add(i);
        }
        return ascendingList;
    }

    public static ArrayList<Integer> generatePermuted(int size) {
        ArrayList<Integer> permutedList = generateAscending(size);
        Collections.shuffle(permutedList, new Random());
        return permutedList;
    }

    public static ArrayList<Integer> generateDescending(int size) {
        ArrayList<Integer> descendingList = new ArrayList<>(size);
        for (int i = size ; i > 0; i--) { //I modified here,
            descendingList.add(i);
        }
        return descendingList;
    }
}
