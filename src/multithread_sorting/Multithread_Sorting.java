/** Praveen Munjala
 * 700691976
 * I certify that the code in the method functions including method function main of this project are
 * entirely my own work.”
 */
package multithread_sorting;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.stream.IntStream;

public class Multithread_Sorting {

    private static String Keystroke;

    public static void main(String[] args) {

        do {
            System.out.println("*******************Disclaimer !!*****************************\n");
            System.out.println("Merge sort is Sorted Using the recurssive multi threading while Quick sort is by Two threads.\n");
            System.out.println("Please Enter the  array in a single line with single spacing");
            Scanner Sc = new Scanner(System.in);
            String y = Sc.nextLine();
            String[] str = y.split(" ");
            int[] ar = new int[str.length];
            for (int i = 0; i < str.length; i++) {
                ar[i] = Integer.parseInt(str[i]);
            }

            System.out.println("Please Select Which Algorithm you want to Execute......\n 1. Merge Sort \n 2. Quick Sort");
            Scanner Sc2 = new Scanner(System.in);
            String input = Sc2.nextLine();

            switch (input) {
                case "1":
                    long startTime = System.currentTimeMillis();
                    MergeSort mr = new MergeSort(ar, 4);
                    mr.run();
                    int[] o = mr.getresult();
                    long stopTime = System.currentTimeMillis();
                    System.out.println("Sorted Array------> " + Arrays.toString(o));
                    System.out.println("Sorted Time------> " + (stopTime - startTime) + " miliseconds.");
                    Keystroke = Userinput();
                    break;
                case "2":
                    long startTime2 = System.currentTimeMillis();

                    int[] thread0 = Arrays.copyOfRange(ar, 0, ar.length / 2);
                    int[] thread1 = Arrays.copyOfRange(ar, ar.length / 2, ar.length);

                    Thread t = new Thread(new QuickSort_Algo(thread0, 0, thread0.length - 1));
                    Thread t2 = new Thread(new QuickSort_Algo(thread1, 0, thread1.length - 1));
                    t.start();
                    t2.start();

                    try {
                        t.join();
                        t2.join();
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }

                    int[] finalAns = IntStream.concat(Arrays.stream(thread0), Arrays.stream(thread1)).toArray();
                    Thread t3 = new Thread(new QuickSort_Algo(finalAns, 0, finalAns.length - 1));
                    t3.start();
                    try {
                        t3.join();
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    long stopTime2 = System.currentTimeMillis();
                    System.out.println("Sorted Array------> " + Arrays.toString(finalAns));
                    System.out.println("Sorted Time------> " + (stopTime2 - startTime2) + " miliseconds.");
                    Keystroke = Userinput();
                    break;
                default:
                    System.out.println("Please Enter only given options. Thank You");
            }
        } while (Keystroke.toUpperCase().trim().equals("Y"));
    }

    public static String Userinput() {
        System.out.println(" Do you wish to Run the algorithm again ? Y/ N  ");
        Scanner Sc2 = new Scanner(System.in);
        return Sc2.nextLine();
    }
}
