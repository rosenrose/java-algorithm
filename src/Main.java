import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            int[] arr = createRandomArray();
            mergeSort(arr, 0, arr.length - 1);
            System.out.println(Arrays.toString(arr));
        }

    }

    private static int[] createRandomArray() {
        Random rand = new Random();
        return IntStream.of(new int[rand.nextInt(1, 11)]).map(i -> rand.nextInt(31)).toArray();
    }

    private static void mergeSort(int[] arr, int left, int right) {
        int len = right - left + 1;

        if (len <= 1) {
            return;
        }

        int pivot = (left + right) / 2;

        mergeSort(arr, left, pivot);
        mergeSort(arr, pivot + 1, right);

        int a = left;
        int b = pivot + 1;
        int[] temp = new int[len];

        for (int i = 0; i < len; i++) {
            if (a < pivot + 1 && b < right + 1) {
                if (arr[a] < arr[b]) {
                    temp[i] = arr[a];
                    a++;
                } else {
                    temp[i] = arr[b];
                    b++;
                }
            } else {
                if (a == pivot + 1) {
                    temp[i] = arr[b];
                    b++;
                } else {
                    temp[i] = arr[a];
                    a++;
                }
            }
        }

        System.arraycopy(temp, 0, arr, left, len);
    }
}
