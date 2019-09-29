/** Praveen Munjala,
 * 700691976
 * I certify that the code in the method functions including method function main of this project are
 * entirely my own work.”
 */
package multithread_sorting;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class MergeSort implements Runnable {

    public int[] arr;
    private int threadcount;
    int i, key;
    public static int Thresholdval = 4;

    public MergeSort(int[] arr, int cores) {
        this.arr = arr;
        this.threadcount = cores;
    }

    public MergeSort() {
    }

    public void run() {

        sort(arr, threadcount);

    }

    public int[] getresult() {
        return arr;
    }

    public int[] sort(int[] arr, int threadcount) {

        //Split the Array into Two halfs 
        int[] left = ArrayCopy(arr, 0, arr.length / 2);
        int[] right = ArrayCopy(arr, arr.length / 2, arr.length);

        if (arr.length > Thresholdval) {

            Thread T1 = new Thread(new MergeSort(left, threadcount / 2));
            Thread T2 = new Thread(new MergeSort(right, threadcount / 2));

            System.out.println("Array Passed to Left Thread is : " + Arrays.toString(left));
            System.out.println("Array Passed to Right Thread is : " + Arrays.toString(right));

            T1.start();
            T2.start();
            try {
                T1.join();
                T2.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            merge(left, right, arr);
        } else {
            insertion_sort(arr);
        }
        return arr;
    }

    public void mergeSort(int[] arr) {

        if (arr.length >= 2) {
            // split array in half
            int[] left = ArrayCopy(arr, 0, arr.length / 2);
            int[] right = ArrayCopy(arr, arr.length / 2, arr.length);

            // sort the halves
            mergeSort(left);
            mergeSort(right);

            // merge them back together
            merge(left, right, arr);
        }
    }

    public static void merge(int[] left, int[] right, int[] arr) {
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i2 >= right.length || (i1 < left.length && left[i1] < right[i2])) {
                arr[i] = left[i1];
                i1++;
            } else {
                arr[i] = right[i2];
                i2++;
            }
        }
    }

    public int[] ArrayCopy(int[] arr, int Intial_Length, int End_Length) {
        int[] DestArr = new int[End_Length - Intial_Length];
        int j = 0;
        for (int i = Intial_Length; i < End_Length; i++) {
            DestArr[j] = arr[i];
            j++;
        }
        return DestArr;
    }

    public void insertion_sort(int[] arr) {
        int i, key;
        System.out.println("Insertion Sort algorithm for the array: " + Arrays.toString(arr));
        for (int j = 1; j < arr.length; j++) {

            key = arr[j];
            //i = j - 1;
            i = j - 1;
            while (i >= 0) {
                if (arr[i] > key) {

                    arr[i + 1] = arr[i];
                    i = i - 1;
                }
            }
            arr[i + 1] = key;
        }
    }
}
