
package multithread_sorting;

/**
 *
 * @author munja
 */
public class Insertion_Sort {

    int i, key;

    public int[] Sort(int[] A) {

        int i, key;
        for (int j = 1; j < A.length; j++) {

            key = A[j];
            //i = j - 1;
            i = j - 1;
            while (i >= 0) {
                if (A[i] > key) {

                    A[i + 1] = A[i];
                    i = i - 1;
                }
            }
            A[i + 1] = key;
        }
        return A;
    }
}
