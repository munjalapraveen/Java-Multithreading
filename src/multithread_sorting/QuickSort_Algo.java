/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithread_sorting;

import java.util.Arrays;

/**
 *
 * @author munja
 */
public class QuickSort_Algo implements Runnable {

    private int ar[];
    private int low;
    private int high;

    public QuickSort_Algo(int ar[], int low, int high) {
        this.ar = ar;
        this.low = low;
        this.high = high;
    }

    @Override
    public void run() {
        try {
            sort(ar, low, high);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    int partition(int ar[], int low, int high) {
        int pivot = ar[high];
        System.out.println(" Pivoit Element is : "+pivot + " For the Array is : "+Arrays.toString(ar));
        int i = (low - 1); // index of smaller element 
        for (int j = low; j < high; j++) {

            if (ar[j] < pivot) {
                i++;

                int temp = ar[i];
                ar[i] = ar[j];
                ar[j] = temp;
            }
        }

        int temp = ar[i + 1];
        ar[i + 1] = ar[high];
        ar[high] = temp;

        return i + 1;
    }

    void sort(int ar[], int low, int high) {

        if (ar.length <= 4) {
            System.out.println("Array Size is  than 4 Invoking insertion sort ");
            Insertionsort(ar);
        } else {

            if (low < high) {

                int pi = partition(ar, low, high);

                sort(ar, low, pi - 1);
                sort(ar, pi + 1, high);
            }
        }

    }

    void Insertionsort(int ar[]) {
        System.out.println("InsertionSort for  " + Arrays.toString(ar));
        int n = ar.length;
        for (int i = 1; i < n; ++i) {
            int key = ar[i];
            int j = i - 1;

            while (j >= 0 && ar[j] > key) {
                ar[j + 1] = ar[j];
                j = j - 1;
            }
            ar[j + 1] = key;
        }
        System.out.println("Sorted Array :" + Arrays.toString(ar));
    }
}
